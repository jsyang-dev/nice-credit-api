package link.credit.nice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NiceReqSummaryDTO {

  private Long no;

  private Long memberNo;

  private String reqDate;

  private String agree1;

  private String username;

  private String birthdate;

  private String gender;

  private String safekey;

  private String returnCode;

  private String returnMessage;

  private String authType;

  private String authDateTime;

  private String resSeq;
}
