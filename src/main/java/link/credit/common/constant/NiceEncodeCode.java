package link.credit.common.constant;

public enum NiceEncodeCode {
  SUCCESS(0, "성공"),
  SYSTEM_ERROR(-1, "암호화 시스템 에러입니다."),
  PROCESSING_ERROR(-2, "암호화 처리오류입니다."),
  ENC_DATA_ERROR(-3, "암호화 데이터 오류입니다."),
  INPUT_DATA_ERROR(-9, "입력 데이터 오류입니다."),
  ERROR(-999, "알수 없는 에러 입니다.");

  private final int code;
  private final String message;

  NiceEncodeCode(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public static NiceEncodeCode valueOfCode(int code) {
    for (NiceEncodeCode niceEncodeCode : values()) {
      if (niceEncodeCode.code == code) {
        return niceEncodeCode;
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
