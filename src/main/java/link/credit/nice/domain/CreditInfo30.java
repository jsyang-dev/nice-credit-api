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
 * 전문 - "CB직장정보 변경이력 정보내역 Bitmap(30)"
 */
@Entity
@Table(name = "TF_CRE_CREDIT_INFO_30")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "no", column = @Column(name = "CREDIT_INFO_30_NO"))
@ToString
public class CreditInfo30 extends BaseEntity {

  // 등록일
  @Column(name = "CREDIT_INFO_1")
  @FullText(8)
  private String creditInfo1;

  // 직장명
  @Column(name = "CREDIT_INFO_2")
  @FullText(20)
  private String creditInfo2;

  // 근무부서명
  @Column(name = "CREDIT_INFO_3")
  @FullText(16)
  private String creditInfo3;

  // 직장우편번호
  @Column(name = "CREDIT_INFO_4")
  @FullText(6)
  private String creditInfo4;

  // 직장주소1
  @Column(name = "CREDIT_INFO_5")
  @FullText(50)
  private String creditInfo5;

  // 직장주소2
  @Column(name = "CREDIT_INFO_6")
  @FullText(40)
  private String creditInfo6;

  // 직장전화번호
  @Column(name = "CREDIT_INFO_7")
  @FullText(12)
  private String creditInfo7;

  // 공란
  @Transient
  @FullText(26)
  private String blank;
}
