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
 * 전문 - "평점정보 내역 Bitmap(64)"
 */
@Entity
@Table(name = "TF_CRE_CREDIT_INFO_64")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "no", column = @Column(name = "CREDIT_INFO_64_NO"))
@ToString
public class CreditInfo64 extends BaseEntity {

  // 평정 결과
  @Column(name = "CREDIT_INFO_1")
  @FullText(2)
  private String creditInfo1;

  // CB 스코어 구분명
  @Column(name = "CREDIT_INFO_2")
  @FullText(10)
  private String creditInfo2;

  // 적용배제사유코드1
  @Column(name = "CREDIT_INFO_3")
  @FullText(4)
  private String creditInfo3;

  // 적용배제사유코드2
  @Column(name = "CREDIT_INFO_4")
  @FullText(4)
  private String creditInfo4;

  // 적용배제사유코드3
  @Column(name = "CREDIT_INFO_5")
  @FullText(4)
  private String creditInfo5;

  // 평점 관련 사유코드 1
  @Column(name = "CREDIT_INFO_6")
  @FullText(4)
  private String creditInfo6;

  // 평점 관련 사유코드 2
  @Column(name = "CREDIT_INFO_7")
  @FullText(4)
  private String creditInfo7;

  // 평점 관련 사유코드 3
  @Column(name = "CREDIT_INFO_8")
  @FullText(4)
  private String creditInfo8;

  // 평점 관련 사유코드 4
  @Column(name = "CREDIT_INFO_9")
  @FullText(4)
  private String creditInfo9;

  // 평점 관련 사유코드 5
  @Column(name = "CREDIT_INFO_10")
  @FullText(4)
  private String creditInfo10;

  // 평점 관련 사유코드 6
  @Column(name = "CREDIT_INFO_11")
  @FullText(4)
  private String creditInfo11;

  // 신용평점
  @Column(name = "CREDIT_INFO_12")
  @FullText(4)
  private String creditInfo12;

  // 신용등급
  @Column(name = "CREDIT_INFO_13")
  @FullText(4)
  private String creditInfo13;

  // 평점 관련 결과값1
  @Column(name = "CREDIT_INFO_14")
  @FullText(9)
  private String creditInfo14;

  // 평점 관련 결과값2
  @Column(name = "CREDIT_INFO_15")
  @FullText(9)
  private String creditInfo15;

  // 평점 관련 결과값3
  @Column(name = "CREDIT_INFO_16")
  @FullText(9)
  private String creditInfo16;

  // 에러코드
  @Column(name = "CREDIT_INFO_17")
  @FullText(4)
  private String creditInfo17;

  // Profile Code 1
  @Column(name = "CREDIT_INFO_18")
  @FullText(3)
  private String creditInfo18;

  // Profile Code 2
  @Column(name = "CREDIT_INFO_19")
  @FullText(3)
  private String creditInfo19;

  // Profile Code 3
  @Column(name = "CREDIT_INFO_20")
  @FullText(3)
  private String creditInfo20;

  // 공란
  @Transient
  @FullText(2)
  private String blank;
}
