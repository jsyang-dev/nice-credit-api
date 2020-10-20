package link.credit.nice.dto;

import link.credit.common.annotation.FullText;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FullTextReqDTO extends FullTextCommonDTO {

  // 유치인아이디
  @FullText(20)
  private String reqId;

  // 신용인증송부 인증번호
  @FullText(16)
  private String certNo;
}
