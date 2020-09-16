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
 * 전문 - "채무불이행 (신용정보사) 정보내역 Bitmap(15)"
 *
 * <p>Create by jsyang on 2020/09/10
 */
@Entity
@Table(name = "TF_CRE_CREDIT_INFO_15")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "no", column = @Column(name = "CREDIT_INFO_15_NO"))
@ToString
public class CreditInfo15 extends BaseEntity {

  // 연체 사유코드
  @Column(name = "CREDIT_INFO_1")
  @FullText(4)
  private String creditInfo1;

  // 발생 기관명
  @Column(name = "CREDIT_INFO_2")
  @FullText(30)
  private String creditInfo2;

  // 발생 지점명
  @Column(name = "CREDIT_INFO_3")
  @FullText(20)
  private String creditInfo3;

  // 발생 기관 업계 코드
  @Column(name = "CREDIT_INFO_4")
  @FullText(2)
  private String creditInfo4;

  // 발생 기관코드
  @Column(name = "CREDIT_INFO_5")
  @FullText(7)
  private String creditInfo5;

  // 발생일
  @Column(name = "CREDIT_INFO_6")
  @FullText(8)
  private String creditInfo6;

  // 제공일
  @Column(name = "CREDIT_INFO_7")
  @FullText(8)
  private String creditInfo7;

  // 등록 금액
  @Column(name = "CREDIT_INFO_8")
  @FullText(9)
  private Long creditInfo8;

  // 정정청구, 사실조회중 코드
  @Column(name = "CREDIT_INFO_9")
  @FullText(1)
  private String creditInfo9;

  // 공란
  @Transient
  @FullText(9)
  private String blank;
}
