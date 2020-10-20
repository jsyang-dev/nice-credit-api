package link.credit.nice.dto;

import link.credit.common.annotation.FullText;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FullTextCommonDTO {

  // 전문그룹코드
  @FullText(9)
  @Builder.Default
  private String fullTextGroupCode = "NICEIF   ";

  // 거래종별코드
  @FullText(4)
  @Builder.Default
  private String tranClassCode = "0200";

  // 거래구분코드
  @FullText(5)
  @Builder.Default
  private String tranTypeCode = "SP110";

  // 송수신Flag
  @FullText(1)
  @Builder.Default
  private String reqResFlag = "B";

  // 단말기구분
  @FullText(3)
  @Builder.Default
  private String terminalType = "503";

  // 응답코드
  @FullText(4)
  @Builder.Default
  private String resCode = "";

  // User ID
  @FullText(9)
  private String userId;

  // 기관전문 관리번호
  @FullText(10)
  private String managementNo;

  // 기관전문 전송시간
  @FullText(14)
  private String reqTime;

  // NICE 전문 관리번호
  @FullText(10)
  @Builder.Default
  private String niceManagementNo = "";

  // NICE 전문 전송시간
  @FullText(14)
  @Builder.Default
  private String niceReqTime = "";

  // Primary Bitmap
  @FullText(16)
  @Builder.Default
  private String primaryBitmap = "";

  // 조회동의사유코드
  @FullText(1)
  @Builder.Default
  private String agreeReason = "1";
}
