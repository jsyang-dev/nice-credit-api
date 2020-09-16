package link.credit.common.constant;

/**
 * 상수 - Safekey 디코딩 코드
 *
 * <p>Create by jsyang on 2020/09/02
 */
public enum NiceDecodeCode {
  SUCCESS(0, "성공"),
  SYSTEM_ERROR(-1, "복호화 시스템 에러입니다."),
  PROCESSING_ERROR(-4, "복호화 처리오류입니다."),
  HASH_DATA_ERROR(-5, "복호화 해쉬 오류입니다."),
  DEC_DATA_ERROR(-6, "복호화 데이터 오류입니다."),
  INPUT_DATA_ERROR(-9, "입력 데이터 오류입니다."),
  SITE_PASSWD_ERROR(-12, "사이트 패스워드 오류입니다."),
  ERROR(-999, "알수 없는 에러 입니다.");

  private final int code;
  private final String message;

  NiceDecodeCode(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public static NiceDecodeCode valueOfCode(int code) {
    for (NiceDecodeCode niceDecodeCode : values()) {
      if (niceDecodeCode.code == code) {
        return niceDecodeCode;
      }
    }
    return null;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
