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
 * 전문 - "요약이력정보 내역 Bitmap(63)"
 *
 * <p>Create by jsyang on 2020/09/10
 */
@Entity
@Table(name = "TF_CRE_CREDIT_INFO_63")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "no", column = @Column(name = "CREDIT_INFO_63_NO"))
@ToString
public class CreditInfo63 extends BaseEntity {

  // 요약서비스 코드
  @Column(name = "CREDIT_INFO_1")
  @FullText(9)
  private String creditInfo1;

  // 요약서비스 현재 값
  @Column(name = "CREDIT_INFO_2")
  @FullText(9)
  private String creditInfo2;

  // 요약서비스 1개월전 값
  @Column(name = "CREDIT_INFO_3")
  @FullText(9)
  private String creditInfo3;

  // 요약서비스 2개월전 값
  @Column(name = "CREDIT_INFO_4")
  @FullText(9)
  private String creditInfo4;

  // 요약서비스 3개월전 값
  @Column(name = "CREDIT_INFO_5")
  @FullText(9)
  private String creditInfo5;

  // 요약서비스 4개월전 값
  @Column(name = "CREDIT_INFO_6")
  @FullText(9)
  private String creditInfo6;

  // 요약서비스 5개월전 값
  @Column(name = "CREDIT_INFO_7")
  @FullText(9)
  private String creditInfo7;

  // 요약서비스 6개월전 값
  @Column(name = "CREDIT_INFO_8")
  @FullText(9)
  private String creditInfo8;

  // 요약서비스 7개월전 값
  @Column(name = "CREDIT_INFO_9")
  @FullText(9)
  private String creditInfo9;

  // 요약서비스 8개월전 값
  @Column(name = "CREDIT_INFO_10")
  @FullText(9)
  private String creditInfo10;

  // 요약서비스 9개월전 값
  @Column(name = "CREDIT_INFO_11")
  @FullText(9)
  private String creditInfo11;

  // 요약서비스 10개월전 값
  @Column(name = "CREDIT_INFO_12")
  @FullText(9)
  private String creditInfo12;

  // 요약서비스 11개월전 값
  @Column(name = "CREDIT_INFO_13")
  @FullText(9)
  private String creditInfo13;

  // 요약서비스 12개월전 값
  @Column(name = "CREDIT_INFO_14")
  @FullText(9)
  private String creditInfo14;

  // 공란
  @Transient
  @FullText(24)
  private String blank;
}
