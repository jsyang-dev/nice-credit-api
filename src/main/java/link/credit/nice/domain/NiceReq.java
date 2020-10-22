package link.credit.nice.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "TF_CRE_NICE_REQ")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "no", column = @Column(name = "NICE_REQ_NO"))
public class NiceReq extends BaseEntity {

  // 고객 번호
  @Column(name = "MEMBER_NO", nullable = false)
  private Long memberNo;

  // 요청일시
  @Column(name = "REQ_DT", length = 14, nullable = false)
  private String reqDate;

  // 인증유형 (3자리 bit로 구성: 휴대폰인증여부, 신용카드인증여부, 공인인증서인증여부)
  @Column(name = "PERMIT_AUTH_TYPE", length = 3, nullable = false)
  @Builder.Default
  private String permitAuthType = "111";

  // 신용인증송부 서비스 신청 동의
  @Column(name = "AGREE1", length = 10, nullable = false)
  private String agree1;

  // 업체 필수 동의문
  @Column(name = "AGREE2", length = 10, nullable = false)
  @Builder.Default
  private String agree2 = "0000000000";

  // 업체 선택 동의문
  @Column(name = "AGREE3", length = 10, nullable = false)
  @Builder.Default
  private String agree3 = "0000000000";

  // 이름
  @Column(name = "USERNAME", nullable = false)
  private String username;

  // 생년월일
  @Column(name = "BIRTH_DT", length = 8, nullable = false)
  private String birthdate;

  // 성별
  @Column(name = "GENDER", length = 1, nullable = false)
  private String gender;

  // CI 리턴 여부
  @Column(name = "CI_YN", length = 1, nullable = false)
  @Builder.Default
  private String ciYn = "N";

  // 팝업창 취소버튼 여부
  @Column(name = "POPUP_YN", length = 1, nullable = false)
  @Builder.Default
  private String popupYn = "N";

  // Safe-key
  @Column(name = "SAFEKEY")
  private String safekey;

  // 결과 코드
  @Column(name = "RETURN_CODE")
  private String returnCode;

  // 결과 메시지
  @Column(name = "RETURN_MSG")
  private String returnMessage;

  // 본인인증 수단
  @Column(name = "AUTH_TYPE")
  private String authType;

  // 인증 시간
  @Column(name = "AUTH_DATETIME")
  private String authDateTime;

  // 인증 고유 번호
  @Column(name = "RES_REQ")
  private String resSeq;

  // 재조회용 보고서번호
  @Column(name = "CERT_NO")
  private String certNo;

  // 전문 응답 코드
  @Column(name = "RES_CODE")
  private String resCode;

  // NICE 전문 관리번호
  @Column(name = "NICE_MANAGEMENT_NO")
  private String niceManagementNo;

  // Primary Bitmap
  @Column(name = "PRIMARY_BITMAP")
  private String primaryBitmap;

  // Safe-key 요청 일시
  @Column(name = "SAFEKEY_REQ_DT")
  private LocalDateTime safekeyReqDate;

  // 인증송부 요청 일시
  @Column(name = "NPAC_REQ_DT")
  private LocalDateTime npacReqDate;

  // 인증송부 수신 일시
  @Column(name = "NPAC_RES_DT")
  private LocalDateTime npacResDate;
}
