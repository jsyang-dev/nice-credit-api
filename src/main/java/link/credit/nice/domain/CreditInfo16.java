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
 * 전문 - "신용개설정보 내역 Bitmap(16)"
 */
@Entity
@Table(name = "TF_CRE_CREDIT_INFO_16")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "no", column = @Column(name = "CREDIT_INFO_16_NO"))
@ToString
public class CreditInfo16 extends BaseEntity {

  // 사유 코드
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

  // 발생 업체코드
  @Column(name = "CREDIT_INFO_5")
  @FullText(7)
  private String creditInfo5;

  // 개설일
  @Column(name = "CREDIT_INFO_6")
  @FullText(8)
  private String creditInfo6;

  // 만기일
  @Column(name = "CREDIT_INFO_7")
  @FullText(8)
  private String creditInfo7;

  // 개설 금액
  @Column(name = "CREDIT_INFO_8")
  @FullText(9)
  private Long creditInfo8;

  // 사유구분/관련인구분
  @Column(name = "CREDIT_INFO_9")
  @FullText(1)
  private String creditInfo9;

  // 공란
  @Transient
  @FullText(9)
  private String blank;
}
