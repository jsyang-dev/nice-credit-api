package link.credit.nice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * NICE 신용정보 관리
 *
 * <p>Create by jsyang on 2020/09/02
 */
public class NiceReqDTO {

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SafekeyData {

    private Long no;

    private Long memberNo;

    private String encDate;
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class NpacData {

    private Long no;

    private Long memberNo;

    private String userId;

    private String loginId;

    private String passwd;

    private String returnUrl;

    private String resNo1;

    private String resNo2;

    private String resName;

    private String custKey;

    private String svcGb;

    private Long timeStamp;

    private String encDate;
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Response {

    private Long no;

    private Long memberNo;

    private String reqDate;

    private String permitAuthType;

    private String agree1;

    private String agree2;

    private String agree3;

    private String username;

    private String birthdate;

    private String gender;

    private String ciYn;

    private String popupYn;

    private String safekey;

    private String returnCode;

    private String returnMessage;

    private String authType;

    private String authDateTime;

    private String resSeq;

    private String certNo;

    private LocalDateTime safekeyReqDate;

    private LocalDateTime npacReqDate;
  }
}
