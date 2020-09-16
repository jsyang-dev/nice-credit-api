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
 * 전문 - "주민등록번호 변동이력 정보내역 Bitmap(38)"
 *
 * <p>Create by jsyang on 2020/09/10
 */
@Entity
@Table(name = "TF_CRE_CREDIT_INFO_38")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "no", column = @Column(name = "CREDIT_INFO_38_NO"))
@ToString
public class CreditInfo38 extends BaseEntity {

  // 주민등록번호 변경 구분
  @Column(name = "CREDIT_INFO_1")
  @FullText(1)
  private String creditInfo1;

  // 변경주민등록번호
  @Column(name = "CREDIT_INFO_2")
  @FullText(13)
  private String creditInfo2;

  // 변경주민등록번호성명
  @Column(name = "CREDIT_INFO_3")
  @FullText(12)
  private String creditInfo3;

  // 등록기관 명
  @Column(name = "CREDIT_INFO_4")
  @FullText(30)
  private String creditInfo4;

  // 등록기관 업계 코드
  @Column(name = "CREDIT_INFO_5")
  @FullText(2)
  private String creditInfo5;

  // 등록기관 코드(NICE 점포코드)
  @Column(name = "CREDIT_INFO_6")
  @FullText(7)
  private String creditInfo6;

  // 등록일
  @Column(name = "CREDIT_INFO_7")
  @FullText(8)
  private String creditInfo7;

  // 공란
  @Transient
  @FullText(15)
  private String blank;
}
