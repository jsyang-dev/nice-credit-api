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
 * 전문 - "현금서비스 정보내역 (연합회) Bitmap(23)"
 */
@Entity
@Table(name = "TF_CRE_CREDIT_INFO_23")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "no", column = @Column(name = "CREDIT_INFO_23_NO"))
@ToString
public class CreditInfo23 extends BaseEntity {

  // 정보원천구분
  @Column(name = "CREDIT_INFO_1")
  @FullText(1)
  private String creditInfo1;

  // 사유코드
  @Column(name = "CREDIT_INFO_2")
  @FullText(4)
  private String creditInfo2;

  // 발생기관명
  @Column(name = "CREDIT_INFO_3")
  @FullText(30)
  private String creditInfo3;

  // 발생지점명
  @Column(name = "CREDIT_INFO_4")
  @FullText(20)
  private String creditInfo4;

  // 발생 업권코드
  @Column(name = "CREDIT_INFO_5")
  @FullText(2)
  private String creditInfo5;

  // 발생기관코드 (신정원 부여)
  @Column(name = "CREDIT_INFO_6")
  @FullText(7)
  private String creditInfo6;

  // 개설일,발생일
  @Column(name = "CREDIT_INFO_7")
  @FullText(8)
  private String creditInfo7;

  // 변경일
  @Column(name = "CREDIT_INFO_8")
  @FullText(8)
  private String creditInfo8;

  // 금액
  @Column(name = "CREDIT_INFO_9")
  @FullText(9)
  private Long creditInfo9;

  // 공란
  @Transient
  @FullText(9)
  private String blank;
}
