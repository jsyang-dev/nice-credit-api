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
 * 전문 - "채무불이행 (신정원) 정보내역 Bitmap(14)"
 */
@Entity
@Table(name = "TF_CRE_CREDIT_INFO_14")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "no", column = @Column(name = "CREDIT_INFO_14_NO"))
@ToString
public class CreditInfo14 extends BaseEntity {

  // 사유구분/관련인구분
  @Column(name = "CREDIT_INFO_2")
  @FullText(1)
  private String creditInfo2;

  // 연체 사유코드
  @Column(name = "CREDIT_INFO_3")
  @FullText(4)
  private String creditInfo3;

  // 발생 기관명
  @Column(name = "CREDIT_INFO_4")
  @FullText(30)
  private String creditInfo4;

  // 발생 지점명
  @Column(name = "CREDIT_INFO_5")
  @FullText(20)
  private String creditInfo5;

  // 발생 기관 업계 코드
  @Column(name = "CREDIT_INFO_6")
  @FullText(2)
  private String creditInfo6;

  // 발생 기관코드
  @Column(name = "CREDIT_INFO_7")
  @FullText(7)
  private String creditInfo7;

  // 발생일
  @Column(name = "CREDIT_INFO_8")
  @FullText(8)
  private String creditInfo8;

  // 제공일
  @Column(name = "CREDIT_INFO_9")
  @FullText(8)
  private String creditInfo9;

  // 등록 금액
  @Column(name = "CREDIT_INFO_10")
  @FullText(9)
  private Long creditInfo10;

  // 채무불이행금액
  @Column(name = "CREDIT_INFO_11")
  @FullText(9)
  private Long creditInfo11;

  // 해제일
  @Column(name = "CREDIT_INFO_12")
  @FullText(8)
  private String creditInfo12;

  // 해제 구분
  @Column(name = "CREDIT_INFO_13")
  @FullText(2)
  private String creditInfo13;

  // 정정청구, 사실조회중 코드
  @Column(name = "CREDIT_INFO_14")
  @FullText(1)
  private String creditInfo14;

  // 연체,공공정보 구분
  @Column(name = "CREDIT_INFO_15")
  @FullText(1)
  private String creditInfo15;

  // 발생 업권코드
  @Column(name = "CREDIT_INFO_16")
  @FullText(2)
  private String creditInfo16;

  // 공란
  @Transient
  @FullText(6)
  private String blank;
}
