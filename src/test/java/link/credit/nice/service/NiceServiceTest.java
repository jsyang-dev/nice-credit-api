package link.credit.nice.service;

import link.credit.common.exception.NiceNotAgreeException;
import link.credit.nice.domain.NiceReq;
import link.credit.nice.dto.NiceReqDTO;
import link.credit.nice.dto.NiceReqSummaryDTO;
import link.credit.nice.repository.NiceReqRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.NoResultException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {NiceService.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableJpaRepositories(basePackages = {"link.credit.nice"})
@EntityScan(basePackages = {"link.credit.nice"})
@ComponentScan(basePackages = {"link.credit.nice"})
@EnableAutoConfiguration
@EnableJpaAuditing
class NiceServiceTest {

  @Autowired private NiceService niceService;
  @Autowired private NiceReqRepository niceReqRepository;

  @BeforeEach
  public void setUp() {}

  @Test
  public void _1_Safekey_요청데이터_생성한다() {

    // Given
    long memberNo =
        Optional.ofNullable(memberClient.createMember(memberDTO))
            .orElseThrow(() -> new AssertionError("Test failed"))
            .getData();

    // When
    NiceReqDTO.SafekeyData niceReqDTO = niceService.requestSafekey(memberNo);

    // Then
    MemberDTO.Response memberDTO =
        Optional.ofNullable(memberClient.getMember(memberNo))
            .orElseThrow(() -> new AssertionError("Test failed"))
            .getData();

    NiceReq niceReq =
        niceReqRepository
            .findById(niceReqDTO.getNo())
            .orElseThrow(() -> new AssertionError("Test failed"));

    assertThat(niceReqDTO.getNo()).isNotNull();
    assertThat(niceReqDTO.getEncDate()).isNotBlank();
    assertThat(niceReq.getUsername()).isEqualTo(memberDTO.getMemberName());
    assertThat(niceReq.getBirthdate())
        .isEqualTo(memberDTO.getBirthDate().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    assertThat(niceReq.getGender()).isEqualTo(Gender.valueOf(memberDTO.getGender()).getCode());
    assertThat(niceReq.getAgree1()).isEqualTo("1000000000");
    assertThat(niceReq.getSafekeyReqDate()).isNotNull();
  }

  @Test(expected = NiceNotAgreeException.class)
  public void _2_Safekey_요청데이터_미동의_생성실패한다() {

    // Given
    memberDTO.setCreditSendAgree(false);
    long memberNo =
        Optional.ofNullable(memberClient.createMember(memberDTO))
            .orElseThrow(() -> new AssertionError("Test failed."))
            .getData();

    // When
    niceService.requestSafekey(memberNo);
  }

  @Test
  public void _3_Safekey_리턴데이터_수신한다() {

    // Given
    String encData = "";

    // When
    long niceReqNo = niceService.returnSafekey(encData);

    // Then
    NiceReq niceReq =
        niceReqRepository.findById(niceReqNo).orElseThrow(() -> new AssertionError("Test failed"));

    assertThat(niceReqNo).isNotNull();
    assertThat(niceReq.getSafekey()).isNotBlank();
    assertThat(niceReq.getReturnCode()).isNotBlank();
    assertThat(niceReq.getReturnMessage()).isNotBlank();
    assertThat(niceReq.getAuthType()).isNotBlank();
    assertThat(niceReq.getAuthDateTime()).isNotBlank();
    assertThat(niceReq.getResSeq()).isNotBlank();
  }

  @Test
  public void _4_인증송부_요청데이터_생성한다() {

    // Given
    long niceReqNo = 1;

    // When
    NiceReqDTO.NpacData npacData = niceService.requestNpac(niceReqNo);

    // Then
    NiceReq niceReq =
        niceReqRepository.findById(niceReqNo).orElseThrow(() -> new AssertionError("Test failed"));

    assertThat(npacData.getNo()).isEqualTo(niceReqNo);
    assertThat(npacData.getMemberNo()).isEqualTo(niceReq.getMemberNo());
    assertThat(npacData.getUserId()).isNotBlank();
    assertThat(npacData.getLoginId()).isNotBlank();
    assertThat(npacData.getPasswd()).isNotBlank();
    assertThat(npacData.getReturnUrl()).isNotBlank();
    assertThat(npacData.getResNo1()).isEqualTo(niceReq.getSafekey().substring(0, 6));
    assertThat(npacData.getResNo2()).isEqualTo(niceReq.getSafekey().substring(6, 13));
    assertThat(npacData.getResName()).isEqualTo(niceReq.getUsername());
    assertThat(npacData.getCustKey()).isEqualTo(String.valueOf(niceReqNo));
    assertThat(npacData.getSvcGb()).isNotBlank();
    assertThat(npacData.getTimeStamp()).isNotNull();
    assertThat(npacData.getEncDate()).isNotBlank();
    assertThat(niceReq.getNpacReqDate()).isNotNull();
  }

  @Test
  public void _5_인증송부_리턴데이터_수신한다() {

    // Given
    String returnData = "";

    // When
    long niceReqNo = niceService.returnNpac(returnData);

    // Then
    NiceReq niceReq =
        niceReqRepository.findById(niceReqNo).orElseThrow(() -> new AssertionError("Test failed"));

    assertThat(niceReqNo).isNotNull();
    assertThat(niceReq.getCertNo()).isNotBlank();
  }

  @Test
  public void _6_NICE요청_조회한다() {

    // Given
    NiceReq niceReq =
        niceReqRepository
            .findLast()
            .orElseThrow(() -> new NoResultException("No Result in NiceReq"));

    // When
    NiceReqDTO.Response niceReqDTO = niceService.get(niceReq.getNo());

    // Then
    assertThat(niceReqDTO).isEqualToIgnoringGivenFields(niceReq);
  }

  @Test
  public void _7_NICE요청_리스트_조회한다() {

    // Given
    NiceReq niceReq =
        niceReqRepository
            .findLast()
            .orElseThrow(() -> new NoResultException("No Result in NiceReq"));
    Pageable pageable = PageRequest.of(0, 20, Sort.Direction.DESC, "no");

    // When
    List<NiceReqSummaryDTO> niceReqList = niceService.getList(null, pageable);

    // Then
    assertThat(niceReqList.get(0)).isEqualToIgnoringGivenFields(niceReq);
    assertThat(niceReqList.size()).isGreaterThanOrEqualTo(1);
  }
}
