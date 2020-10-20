package link.credit.nice.domain;

import link.credit.common.annotation.FullText;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * 전문개별응답부
 *
 * <p>Create by jsyang on 2020/09/09
 */
@Entity
@Table(name = "TF_CRE_CREDIT_INFO_MST")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "no", column = @Column(name = "CREDIT_INFO_MST_NO"))
@ToString
public class CreditInfoMaster extends BaseEntity {

  // 개인/사업자/법인 구분
  @Column(name = "MEMBER_TYPE")
  @FullText(1)
  private String memberType;

  // 주민등록번호
  @Column(name = "RRN")
  @FullText(13)
  private String rrn;

  // 정보 연속
  @Column(name = "INFO_CONTINUE")
  @FullText(1)
  private String infoContinue;

  // 재요청횟수
  @Column(name = "RE_REQ_CNT")
  @FullText(2)
  private Integer reReqCount;

  // 성명
  @Column(name = "NAME")
  @FullText(12)
  private String name;

  // 보고서 인증번호
  @Column(name = "REPORT_NO")
  @FullText(12)
  private String reportNo;

  // 부가 정보플래그
  @Column(name = "ADD_INFO_FLAG")
  @FullText(10)
  private String addInfoFlag;

  // BitMap13
  // 고객정보 총건수
  @Column(name = "TOTAL_CNT_13")
  @FullText(3)
  private String totalCount13;

  // 고객정보 응답건수
  @Column(name = "RES_CNT_13")
  @FullText(2)
  private Integer resCount13;

  // BitMap14
  // 채무불이행(신정원) 총건수
  @Column(name = "TOTAL_CNT_14")
  @FullText(3)
  private String totalCount14;

  // 채무불이행(신정원) 응답건수
  @Column(name = "RES_CNT_14")
  @FullText(2)
  private Integer resCount14;

  // BitMap15
  // 채무불이행(신용정보사) 총건수
  @Column(name = "TOTAL_CNT_15")
  @FullText(3)
  private String totalCount15;

  // 채무불이행(신용정보사) 응답건수
  @Column(name = "RES_CNT_15")
  @FullText(2)
  private Integer resCount15;

  // BitMap16
  // 거래개설정보 총건수
  @Column(name = "TOTAL_CNT_16")
  @FullText(3)
  private String totalCount16;

  // 거래개설정보 응답건수
  @Column(name = "RES_CNT_16")
  @FullText(2)
  private Integer resCount16;

  // BitMap18
  // CB연체정보 총건수
  @Column(name = "TOTAL_CNT_18")
  @FullText(3)
  private String totalCount18;

  // CB연체정보 응답건수
  @Column(name = "RES_CNT_18")
  @FullText(2)
  private Integer resCount18;

  // BitMap23
  // 현금서비스정보(신정원) 총건수
  @Column(name = "TOTAL_CNT_23")
  @FullText(3)
  private String totalCount23;

  // 현금서비스정보(신정원) 응답건수
  @Column(name = "RES_CNT_23")
  @FullText(2)
  private Integer resCount23;

  // BitMap24
  // 채무보증정보(신정원) 총건수
  @Column(name = "TOTAL_CNT_24")
  @FullText(3)
  private String totalCount24;

  // 채무보증정보(신정원) 응답건수
  @Column(name = "RES_CNT_24")
  @FullText(2)
  private Integer resCount24;

  // BitMap25
  // CB신용개설정보 총건수
  @Column(name = "TOTAL_CNT_25")
  @FullText(3)
  private String totalCount25;

  // CB신용개설정보 응답건수
  @Column(name = "RES_CNT_25")
  @FullText(2)
  private Integer resCount25;

  // BitMap29
  // CB자택정보 변경이력정보 총건수
  @Column(name = "TOTAL_CNT_29")
  @FullText(3)
  private String totalCount29;

  // CB자택정보 변경이력정보 응답건수
  @Column(name = "RES_CNT_29")
  @FullText(2)
  private Integer resCount29;

  // BitMap30
  // CB직장정보 변경이력정보 총건수
  @Column(name = "TOTAL_CNT_30")
  @FullText(3)
  private String totalCount30;

  // CB직장정보 변경이력정보 응답건수
  @Column(name = "RES_CNT_30")
  @FullText(2)
  private Integer resCount30;

  // BitMap38
  // 주민등록번호 변동이력정보 총건수
  @Column(name = "TOTAL_CNT_38")
  @FullText(3)
  private String totalCount38;

  // 주민등록번호 변동이력정보 응답건수
  @Column(name = "RES_CNT_38")
  @FullText(2)
  private Integer resCount38;

  // BitMap46
  // 개인계좌별대출정보(신정원) 총건수
  @Column(name = "TOTAL_CNT_46")
  @FullText(3)
  private String totalCount46;

  // 개인계좌별대출정보(신정원) 응답건수
  @Column(name = "RES_CNT_46")
  @FullText(2)
  private Integer resCount46;

  // BitMap60
  // 평점이력서비스 총건수
  @Column(name = "TOTAL_CNT_60")
  @FullText(3)
  private String totalCount60;

  // 평점이력서비스 응답건수
  @Column(name = "RES_CNT_60")
  @FullText(2)
  private Integer resCount60;

  // BitMap61
  // 특별요약정보 총건수
  @Column(name = "TOTAL_CNT_61")
  @FullText(3)
  private String totalCount61;

  // 특별요약정보 응답건수
  @Column(name = "RES_CNT_61")
  @FullText(2)
  private Integer resCount61;

  // BitMap62
  // 요약정보 총건수
  @Column(name = "TOTAL_CNT_62")
  @FullText(3)
  private String totalCount62;

  // 요약정보 응답건수
  @Column(name = "RES_CNT_62")
  @FullText(2)
  private Integer resCount62;

  // BitMap63
  // 요약이력정보 총건수
  @Column(name = "TOTAL_CNT_63")
  @FullText(3)
  private String totalCount63;

  // 요약이력정보 응답건수
  @Column(name = "RES_CNT_63")
  @FullText(2)
  private Integer resCount63;

  // BitMap64
  // 평점정보 총건수
  @Column(name = "TOTAL_CNT_64")
  @FullText(3)
  private String totalCount64;

  // 평점정보 응답건수
  @Column(name = "RES_CNT_64")
  @FullText(2)
  private Integer resCount64;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "CREDIT_INFO_MST_NO")
  private List<CreditInfo13> creditInfo13List = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "CREDIT_INFO_MST_NO")
  private List<CreditInfo14> creditInfo14List = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "CREDIT_INFO_MST_NO")
  private List<CreditInfo15> creditInfo15List = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "CREDIT_INFO_MST_NO")
  private List<CreditInfo16> creditInfo16List = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "CREDIT_INFO_MST_NO")
  private List<CreditInfo18> creditInfo18List = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "CREDIT_INFO_MST_NO")
  private List<CreditInfo23> creditInfo23List = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "CREDIT_INFO_MST_NO")
  private List<CreditInfo24> creditInfo24List = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "CREDIT_INFO_MST_NO")
  private List<CreditInfo25> creditInfo25List = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "CREDIT_INFO_MST_NO")
  private List<CreditInfo29> creditInfo29List = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "CREDIT_INFO_MST_NO")
  private List<CreditInfo30> creditInfo30List = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "CREDIT_INFO_MST_NO")
  private List<CreditInfo38> creditInfo38List = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "CREDIT_INFO_MST_NO")
  private List<CreditInfo46> creditInfo46List = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "CREDIT_INFO_MST_NO")
  private List<CreditInfo60> creditInfo60List = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "CREDIT_INFO_MST_NO")
  private List<CreditInfo61> creditInfo61List = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "CREDIT_INFO_MST_NO")
  private List<CreditInfo62> creditInfo62List = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "CREDIT_INFO_MST_NO")
  private List<CreditInfo63> creditInfo63List = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "CREDIT_INFO_MST_NO")
  private List<CreditInfo64> creditInfo64List = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "NICE_REQ_NO")
  private NiceReq niceReq;
}
