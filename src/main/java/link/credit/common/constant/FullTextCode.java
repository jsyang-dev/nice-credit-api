package link.credit.common.constant;

import lombok.ToString;

@ToString
public enum FullTextCode {
  SUCCESS("P000", "정상"),
  NO_DATA("U213", "조회가능한 신용인증송부 결과 없음"),
  EXPIRATION_DATE("U026", "신용인증송부내역 유효기간 경과"),
  EXCEEDING_RE_REQ_CNT("U071", "신용인증송부 재조회 유효건수 초과"),
  ERROR("E***", "시스템 오류. NICE로 문의 요망");

  private final String code;
  private final String message;

  FullTextCode(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public static FullTextCode valueOfCode(String code) {
    for (FullTextCode fullTextCode : values()) {
      if (fullTextCode.code.equals(code)) {
        return fullTextCode;
      }
    }
    return null;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
