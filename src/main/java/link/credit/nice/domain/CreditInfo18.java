package link.credit.nice.domain;

import link.credit.common.annotation.FullText;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 전문 - "CB연체정보 내역 Bitmap(18)"
 */
@Entity
@Table(name = "TF_CRE_CREDIT_INFO_18")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "no", column = @Column(name = "CREDIT_INFO_18_NO"))
@ToString
public class CreditInfo18 extends BaseEntity {

  // 발생 기관명
  @Column(name = "CREDIT_INFO_1")
  @FullText(30)
  private String creditInfo1;

  // 발생 지점명
  @Column(name = "CREDIT_INFO_2")
  @FullText(30)
  private String creditInfo2;

  // 발생 기관 업계 코드
  @Column(name = "CREDIT_INFO_3")
  @FullText(2)
  private String creditInfo3;

  // 발생 업체코드
  @Column(name = "CREDIT_INFO_4")
  @FullText(7)
  private String creditInfo4;

  // 발생 기관지점코드
  @Column(name = "CREDIT_INFO_5")
  @FullText(9)
  private String creditInfo5;

  // 상품 코드
  @Column(name = "CREDIT_INFO_6")
  @FullText(3)
  private String creditInfo6;

  // 특수 상품 코드
  @Column(name = "CREDIT_INFO_7")
  @FullText(2)
  private String creditInfo7;

  // 한도
  @Column(name = "CREDIT_INFO_8")
  @FullText(9)
  private String creditInfo8;

  // 최초 연체 발생일
  @Column(name = "CREDIT_INFO_9")
  @FullText(8)
  private String creditInfo9;

  // 최초 연체금액
  @Column(name = "CREDIT_INFO_10")
  @FullText(9)
  private String creditInfo10;

  // 연체 발생일
  @Column(name = "CREDIT_INFO_11")
  @FullText(8)
  private String creditInfo11;

  // 연체 발생 등록일
  @Column(name = "CREDIT_INFO_12")
  @FullText(8)
  private String creditInfo12;

  // 연체 금액
  @Column(name = "CREDIT_INFO_13")
  @FullText(9)
  private String creditInfo13;

  // 잔액
  @Column(name = "CREDIT_INFO_14")
  @FullText(9)
  private String creditInfo14;

  // 연체 구분코드
  @Column(name = "CREDIT_INFO_15")
  @FullText(2)
  private String creditInfo15;

  // 등록 구분 사유코드
  @Column(name = "CREDIT_INFO_16")
  @FullText(2)
  private String creditInfo16;

  // 연체 해제일
  @Column(name = "CREDIT_INFO_17")
  @FullText(8)
  private String creditInfo17;

  // 연체 해제 등록일
  @Column(name = "CREDIT_INFO_18")
  @FullText(8)
  private String creditInfo18;

  // 지점 전화번호(지역)
  @Column(name = "CREDIT_INFO_19")
  @FullText(4)
  private String creditInfo19;

  // 지점 전화번호(국번)
  @Column(name = "CREDIT_INFO_20")
  @FullText(4)
  private String creditInfo20;

  // 지점 전화번호(번호)
  @Column(name = "CREDIT_INFO_21")
  @FullText(4)
  private String creditInfo21;

  // 정정청구, 사실조회중 코드
  @Column(name = "CREDIT_INFO_22")
  @FullText(1)
  private String creditInfo22;

  // 공란
  @Transient
  @FullText(22)
  private String blank;
}
