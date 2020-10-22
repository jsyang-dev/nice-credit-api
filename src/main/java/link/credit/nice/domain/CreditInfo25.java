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
 * 전문 - "CB신용개설 정보 내역 Bitmap(25)"
 */
@Entity
@Table(name = "TF_CRE_CREDIT_INFO_25")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "no", column = @Column(name = "CREDIT_INFO_25_NO"))
@ToString
public class CreditInfo25 extends BaseEntity {

  // Serial 번호
  @Column(name = "CREDIT_INFO_1")
  @FullText(20)
  private String creditInfo1;

  // 상품코드
  @Column(name = "CREDIT_INFO_2")
  @FullText(3)
  private String creditInfo2;

  // 계좌상태코드
  @Column(name = "CREDIT_INFO_3")
  @FullText(1)
  private String creditInfo3;

  // 발생기관명
  @Column(name = "CREDIT_INFO_4")
  @FullText(30)
  private String creditInfo4;

  // 발생지점명
  @Column(name = "CREDIT_INFO_5")
  @FullText(30)
  private String creditInfo5;

  // 발생 기관 업계 코드
  @Column(name = "CREDIT_INFO_6")
  @FullText(2)
  private String creditInfo6;

  // 발생기관코드
  @Column(name = "CREDIT_INFO_7")
  @FullText(7)
  private String creditInfo7;

  // 특수상품코드
  @Column(name = "CREDIT_INFO_8")
  @FullText(2)
  private String creditInfo8;

  // 최초개설일
  @Column(name = "CREDIT_INFO_9")
  @FullText(8)
  private String creditInfo9;

  // 만기일
  @Column(name = "CREDIT_INFO_10")
  @FullText(8)
  private String creditInfo10;

  // 개설총금액(천원)
  @Column(name = "CREDIT_INFO_11")
  @FullText(9)
  private String creditInfo11;

  // 현금서비스한도(천원)
  @Column(name = "CREDIT_INFO_12")
  @FullText(9)
  private String creditInfo12;

  // 미사용
  @Column(name = "CREDIT_INFO_13")
  @FullText(9)
  private String creditInfo13;

  // 보증담보구분
  @Column(name = "CREDIT_INFO_14")
  @FullText(1)
  private String creditInfo14;

  // 물적담보구분
  @Column(name = "CREDIT_INFO_15")
  @FullText(1)
  private String creditInfo15;

  // 최초연체발생일
  @Column(name = "CREDIT_INFO_16")
  @FullText(8)
  private String creditInfo16;

  // 연체발생일
  @Column(name = "CREDIT_INFO_17")
  @FullText(8)
  private String creditInfo17;

  // 연체금액(천원)
  @Column(name = "CREDIT_INFO_18")
  @FullText(9)
  private String creditInfo18;

  // 잔액(천원)
  @Column(name = "CREDIT_INFO_19")
  @FullText(9)
  private String creditInfo19;

  // 최근정보변경일
  @Column(name = "CREDIT_INFO_20")
  @FullText(8)
  private String creditInfo20;

  // 월별상태코드-12개월전
  @Column(name = "CREDIT_INFO_21")
  @FullText(1)
  private String creditInfo21;

  // 월별상태코드-11개월전
  @Column(name = "CREDIT_INFO_22")
  @FullText(1)
  private String creditInfo22;

  // 월별상태코드-10개월전
  @Column(name = "CREDIT_INFO_23")
  @FullText(1)
  private String creditInfo23;

  // 월별상태코드-9개월전
  @Column(name = "CREDIT_INFO_24")
  @FullText(1)
  private String creditInfo24;

  // 월별상태코드-8개월전
  @Column(name = "CREDIT_INFO_25")
  @FullText(1)
  private String creditInfo25;

  // 월별상태코드-7개월전
  @Column(name = "CREDIT_INFO_26")
  @FullText(1)
  private String creditInfo26;

  // 월별상태코드-6개월전
  @Column(name = "CREDIT_INFO_27")
  @FullText(1)
  private String creditInfo27;

  // 월별상태코드-5개월전
  @Column(name = "CREDIT_INFO_28")
  @FullText(1)
  private String creditInfo28;

  // 월별상태코드-4개월전
  @Column(name = "CREDIT_INFO_29")
  @FullText(1)
  private String creditInfo29;

  // 월별상태코드-3개월전
  @Column(name = "CREDIT_INFO_30")
  @FullText(1)
  private String creditInfo30;

  // 월별상태코드-2개월전
  @Column(name = "CREDIT_INFO_31")
  @FullText(1)
  private String creditInfo31;

  // 월별상태코드-1개월전
  @Column(name = "CREDIT_INFO_32")
  @FullText(1)
  private String creditInfo32;

  // 발생일
  @Column(name = "CREDIT_INFO_33")
  @FullText(8)
  private String creditInfo33;

  // 해지일
  @Column(name = "CREDIT_INFO_34")
  @FullText(8)
  private String creditInfo34;

  // 해지사유코드
  @Column(name = "CREDIT_INFO_35")
  @FullText(2)
  private String creditInfo35;

  // 공란
  @Transient
  @FullText(6)
  private String blank;
}
