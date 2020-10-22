package link.credit.common.util;

import link.credit.common.annotation.FullText;
import link.credit.common.constant.FullTextCode;
import link.credit.common.exception.NiceException;
import link.credit.nice.domain.CreditInfoMaster;
import link.credit.nice.dto.FullTextCommonDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class FullTextConverter {

  public static final int SIZE_OF_LEN_FIELD = 10;
  public static final int SIZE_OF_INFO_TYPE_FIELD = 2;
  public static final String CHARSET_EUC_KR = "euc-kr";
  public static final String RES_COUNT_COLUMN_PREFIX = "resCount";
  public static final String CREDIT_INFO_CLASS_PREFIX =
      "com.terafunding.trf.credit.nice.domain.CreditInfo";
  public static final Pattern PATTERN = Pattern.compile("[a-zA-z]+([0-9]+)(List|s)$");

  public final <T> String objectToFullText(T instance) {

    StringBuilder fullText = new StringBuilder();
    Field[] superFields = instance.getClass().getSuperclass().getDeclaredFields();
    Field[] fields = instance.getClass().getDeclaredFields();
    AtomicInteger length = new AtomicInteger();

    makeFullText(instance, superFields, fullText, length);
    makeFullText(instance, fields, fullText, length);

    return fullText.insert(0, fillNumber(String.valueOf(length), SIZE_OF_LEN_FIELD)).toString();
  }

  private <T> void makeFullText(
      T instance, Field[] fields, StringBuilder sb, AtomicInteger length) {

    for (Field field : fields) {
      Optional.ofNullable(field.getAnnotation(FullText.class))
          .ifPresent(
              annotation -> {
                String fieldValue = getFieldValue(field, instance, annotation.value());
                sb.append(fieldValue);
                length.addAndGet(annotation.value());
              });
    }
  }

  private <T> String getFieldValue(Field field, T instance, int length) {

    Class<?> type = field.getType();
    field.setAccessible(true);

    try {
      if (type == LocalDateTime.class) {
        LocalDateTime localDateTime = (LocalDateTime) field.get(instance);
        return fillText(
            localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")), length);
      }
      if (type == Integer.class || type == Long.class) {
        return fillNumber(String.valueOf(field.get(instance)), length);
      }
      return fillText((String) field.get(instance), length);

    } catch (IllegalAccessException e) {
      log.error(e.toString());
    }

    return fillText("_", length);
  }

  private String fillText(String text, int length) {
    return fillChars(text, length, ' ', false);
  }

  private String fillNumber(String text, int length) {
    return fillChars(text, length, '0', true);
  }

  private String fillChars(String text, int length, char fillChar, boolean alignRight) {

    if (text.length() == length) {
      return text;
    }
    if (text.length() > length) {
      return text.substring(0, length);
    }

    StringBuilder sb = new StringBuilder(text);
    while (sb.length() < length) {
      if (alignRight) {
        sb.insert(0, fillChar);
      } else {
        sb.append(fillChar);
      }
    }
    return sb.toString();
  }

  public void fullTextToObject(
          String fullText, FullTextCommonDTO fullTextCommonDTO, CreditInfoMaster creditInfoMaster) {

    int length = Integer.parseInt(fullText.substring(0, SIZE_OF_LEN_FIELD));
    int totalLength;

    try {
      totalLength = fullText.getBytes(CHARSET_EUC_KR).length;
      if (length != totalLength - SIZE_OF_LEN_FIELD) {
        throw new RuntimeException("Invalid full text");
      }
    } catch (UnsupportedEncodingException e) {
      log.error(e.toString());
    }

    AtomicInteger position = new AtomicInteger(SIZE_OF_LEN_FIELD);
    Field[] fields = creditInfoMaster.getClass().getDeclaredFields();

    // 공통부
    makeObject(
        fullText, fullTextCommonDTO, fullTextCommonDTO.getClass().getDeclaredFields(), position);

    FullTextCode fullTextCode =
        Optional.ofNullable(FullTextCode.valueOfCode(fullTextCommonDTO.getResCode()))
            .orElse(FullTextCode.ERROR);
    log.debug("fullText response code: " + fullTextCode.toString());

    if (FullTextCode.ERROR.equals(fullTextCode)) {
      throw new NiceException(fullTextCode);
    }
    if (!FullTextCode.SUCCESS.equals(fullTextCode)) {
      return;
    }

    // 개별응답부
    makeObject(fullText, creditInfoMaster, fields, position);

    // 응답정보부
    for (Field field : fields) {
      field.setAccessible(true);
      Matcher matcher = PATTERN.matcher(field.getName());

      if (List.class.equals(field.getType()) && matcher.matches()) {
        makeCreditInfoObject(fullText, creditInfoMaster, field, fields, position, matcher.group(1));
      }
    }
  }

  private void makeCreditInfoObject(
      String fullText,
      CreditInfoMaster creditInfoMaster,
      Field field,
      Field[] fields,
      AtomicInteger position,
      String bitMapNo) {
    Arrays.stream(fields)
        .filter(countField -> countField.getName().equals(RES_COUNT_COLUMN_PREFIX + bitMapNo))
        .findFirst()
        .ifPresent(
            countField -> {
              try {
                int count =
                    Optional.ofNullable((Integer) countField.get(creditInfoMaster)).orElse(0);
                log.debug("#BitMap" + bitMapNo + " count: " + count);

                for (int i = 0; i < count; i++) {
                  String infoType =
                      substringByBytes(
                          fullText,
                          position.getAndAdd(SIZE_OF_INFO_TYPE_FIELD),
                          SIZE_OF_INFO_TYPE_FIELD);
                  if (!infoType.equals(bitMapNo)) {
                    throw new RuntimeException("Mismatch bitMap");
                  }

                  Class<?> clazz = Class.forName(CREDIT_INFO_CLASS_PREFIX + bitMapNo);
                  ((List) field.get(creditInfoMaster))
                      .add(
                          makeObject(
                              fullText, clazz.newInstance(), clazz.getDeclaredFields(), position));
                }
              } catch (Exception e) {
                log.error(e.toString());
              }
            });
  }

  private <T> T makeObject(String fullText, T instance, Field[] fields, AtomicInteger position) {

    int length = 0;
    try {
      length = fullText.getBytes(CHARSET_EUC_KR).length;
    } catch (UnsupportedEncodingException e) {
      log.error(e.toString());
    }

    for (Field field : fields) {
      int finalLength = length;
      Optional.ofNullable(field.getAnnotation(FullText.class))
          .ifPresent(
              annotation -> {
                if (position.get() + annotation.value() > finalLength) {
                  return;
                }

                field.setAccessible(true);
                try {
                  field.set(
                      instance,
                      castFieldValue(
                          substringByBytes(
                              fullText, position.getAndAdd(annotation.value()), annotation.value()),
                          field.getType()));
                } catch (IllegalAccessException e) {
                  log.error("makeObject error: " + instance.getClass().getName() + field.getName());
                  log.error(e.toString());
                }
              });
    }

    return instance;
  }

  private <T> T castFieldValue(String value, Class<T> type) {

    if (type == LocalDateTime.class) {
      return type.cast(LocalDateTime.parse(value, DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
    }
    if (type == Boolean.class) {
      return type.cast("1".equals(value) ? Boolean.TRUE : Boolean.FALSE);
    }
    if (type == Integer.class) {
      return type.cast(Integer.valueOf(value));
    }
    if (type == Long.class) {
      return type.cast(Long.valueOf(value));
    }
    return type.cast(value);
  }

  private String substringByBytes(String str, int startIndex, int length) {

    byte[] bytes = new byte[0];
    try {
      bytes = str.getBytes(CHARSET_EUC_KR);
    } catch (UnsupportedEncodingException e) {
      log.error(e.toString());
    }

    if (bytes.length < startIndex + length) {
      return "";
    }

    byte[] subByte = new byte[length];
    if (length >= 0) System.arraycopy(bytes, startIndex, subByte, 0, length);

    String subStr = "";
    try {
      subStr = new String(subByte, CHARSET_EUC_KR);
    } catch (UnsupportedEncodingException e) {
      log.error(e.toString());
    }

    return subStr;
  }
}
