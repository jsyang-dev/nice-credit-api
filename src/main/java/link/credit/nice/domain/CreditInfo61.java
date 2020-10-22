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

/**
 * 전문 - "특별요약정보 내역 Bitmap(61)"
 */
@Entity
@Table(name = "TF_CRE_CREDIT_INFO_61")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "no", column = @Column(name = "CREDIT_INFO_61_NO"))
@ToString
public class CreditInfo61 extends BaseEntity {

  // 특별요약서비스 코드
  @Column(name = "CREDIT_INFO_1")
  @FullText(9)
  private String creditInfo1;

  // 특별요약서비스 값
  @Column(name = "CREDIT_INFO_2")
  @FullText(9)
  private String creditInfo2;
}
