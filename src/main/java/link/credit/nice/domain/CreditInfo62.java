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
 * 전문 - "요약정보 내역 Bitmap(62)"
 */
@Entity
@Table(name = "TF_CRE_CREDIT_INFO_62")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "no", column = @Column(name = "CREDIT_INFO_62_NO"))
@ToString
public class CreditInfo62 extends BaseEntity {

  // 요약서비스 코드
  @Column(name = "CREDIT_INFO_1")
  @FullText(9)
  private String creditInfo1;

  // 요약서비스 값
  @Column(name = "CREDIT_INFO_2")
  @FullText(9)
  private String creditInfo2;
}
