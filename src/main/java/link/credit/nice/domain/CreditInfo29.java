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
 * 전문 - "CB자택정보 변경이력 정보내역 Bitmap(29)"
 *
 * <p>Create by jsyang on 2020/09/10
 */
@Entity
@Table(name = "TF_CRE_CREDIT_INFO_29")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "no", column = @Column(name = "CREDIT_INFO_29_NO"))
@ToString
public class CreditInfo29 extends BaseEntity {

  // 등록일
  @Column(name = "CREDIT_INFO_1")
  @FullText(8)
  private String creditInfo1;

  // 자택우편번호
  @Column(name = "CREDIT_INFO_2")
  @FullText(6)
  private String creditInfo2;

  // 자택주소1
  @Column(name = "CREDIT_INFO_3")
  @FullText(50)
  private String creditInfo3;

  // 자택주소2
  @Column(name = "CREDIT_INFO_4")
  @FullText(40)
  private String creditInfo4;

  // 자택전화번호
  @Column(name = "CREDIT_INFO_5")
  @FullText(12)
  private String creditInfo5;

  // 휴대폰번호
  @Column(name = "CREDIT_INFO_6")
  @FullText(12)
  private String creditInfo6;

  // 공란
  @Transient
  @FullText(20)
  private String blank;
}
