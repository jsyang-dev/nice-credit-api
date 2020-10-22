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
 * 전문 - "평점이력서비스 정보내역 Bitmap(60)"
 */
@Entity
@Table(name = "TF_CRE_CREDIT_INFO_60")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "no", column = @Column(name = "CREDIT_INFO_60_NO"))
@ToString
public class CreditInfo60 extends BaseEntity {

  // 기준년월일
  @Column(name = "CREDIT_INFO_1")
  @FullText(8)
  private String creditInfo1;

  // 평정 결과
  @Column(name = "CREDIT_INFO_2")
  @FullText(2)
  private String creditInfo2;

  // CB 스코어 구분명
  @Column(name = "CREDIT_INFO_3")
  @FullText(10)
  private String creditInfo3;

  // 적용배제사유코드
  @Column(name = "CREDIT_INFO_4")
  @FullText(4)
  private String creditInfo4;

  // 신용평점
  @Column(name = "CREDIT_INFO_5")
  @FullText(4)
  private String creditInfo5;

  // 신용등급
  @Column(name = "CREDIT_INFO_6")
  @FullText(4)
  private String creditInfo6;

  // 에러코드
  @Column(name = "CREDIT_INFO_7")
  @FullText(4)
  private String creditInfo7;

  // 공란
  @Transient
  @FullText(62)
  private String blank;
}
