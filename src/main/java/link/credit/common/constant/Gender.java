package link.credit.common.constant;

/**
 * 상수 - 성별
 *
 * <p>Create by jsyang on 2020/09/01
 */
public enum Gender {
  MALE("1"),
  FEMALE("0");

  private final String code;

  Gender(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
