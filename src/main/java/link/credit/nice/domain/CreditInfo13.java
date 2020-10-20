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
 * 전문 - "고객정보 내역 Bitmap(13)"
 *
 * <p>Create by jsyang on 2020/09/10
 */
@Entity
@Table(name = "TF_CRE_CREDIT_INFO_13")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "no", column = @Column(name = "CREDIT_INFO_13_NO"))
@ToString
public class CreditInfo13 extends BaseEntity {

  // 직장명
  @Column(name = "CREDIT_INFO_1")
  @FullText(20)
  private String creditInfo1;

  // 근무 부서명
  @Column(name = "CREDIT_INFO_2")
  @FullText(16)
  private String creditInfo2;

  // 자택 전화번호
  @Column(name = "CREDIT_INFO_3")
  @FullText(12)
  private String creditInfo3;

  // 직장 전화번호
  @Column(name = "CREDIT_INFO_4")
  @FullText(12)
  private String creditInfo4;

  // 휴대폰 번호
  @Column(name = "CREDIT_INFO_5")
  @FullText(12)
  private String creditInfo5;

  // 자택 우편번호
  @Column(name = "CREDIT_INFO_6")
  @FullText(6)
  private String creditInfo6;

  // 자택주소1
  @Column(name = "CREDIT_INFO_7")
  @FullText(50)
  private String creditInfo7;

  // 자택주소2
  @Column(name = "CREDIT_INFO_8")
  @FullText(40)
  private String creditInfo8;

  // 직장 우편번호
  @Column(name = "CREDIT_INFO_9")
  @FullText(6)
  private String creditInfo9;

  // 직장주소1
  @Column(name = "CREDIT_INFO_10")
  @FullText(50)
  private String creditInfo10;

  // 직장주소2
  @Column(name = "CREDIT_INFO_11")
  @FullText(40)
  private String creditInfo11;

  // 연간 근로소득(천원)
  @Column(name = "CREDIT_INFO_12")
  @FullText(9)
  private Long creditInfo12;

  // 연간 종합소득(천원)
  @Column(name = "CREDIT_INFO_13")
  @FullText(9)
  private Long creditInfo13;

  // 연간 재산세(천원)
  @Column(name = "CREDIT_INFO_14")
  @FullText(9)
  private Long creditInfo14;

  // 고객정보 최종갱신일
  @Column(name = "CREDIT_INFO_15")
  @FullText(8)
  private String creditInfo15;

  // 사업자 등록번호
  @Column(name = "CREDIT_INFO_16")
  @FullText(10)
  private String creditInfo16;

  // 상호명
  @Column(name = "CREDIT_INFO_17")
  @FullText(20)
  private String creditInfo17;

  // 정보 갱신일
  @Column(name = "CREDIT_INFO_18")
  @FullText(8)
  private String creditInfo18;

  // 사업자 매출액 유무
  @Column(name = "CREDIT_INFO_19")
  @FullText(1)
  private String creditInfo19;

  // 거주 상태
  @Column(name = "CREDIT_INFO_20")
  @FullText(2)
  private String creditInfo20;

  // 공란
  @Transient
  @FullText(28)
  private String blank;
}
