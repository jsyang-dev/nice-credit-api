package link.credit.nice.service;

import link.credit.common.error.NiceException;
import link.credit.common.util.Crypto;
import link.credit.common.util.FullTextConverter;
import link.credit.common.util.SocketSender;
import link.credit.nice.config.NiceProperties;
import link.credit.nice.domain.CreditInfoMaster;
import link.credit.nice.domain.NiceReq;
import link.credit.nice.dto.FullTextCommonDTO;
import link.credit.nice.dto.FullTextReqDTO;
import link.credit.nice.dto.NiceReqDTO;
import link.credit.nice.dto.NiceReqSummaryDTO;
import link.credit.nice.mapper.NiceReqMapper;
import link.credit.nice.mapper.NiceReqSummaryMapper;
import link.credit.nice.repository.CreditInfoMasterRepository;
import link.credit.nice.repository.NiceReqRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * NICE 신용정보 관리
 *
 * <p>Create by jsyang on 2020/08/31
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NiceServiceImpl implements NiceService {

  private final NiceReqRepository niceReqRepository;
  private final CreditInfoMasterRepository creditInfoMasterRepository;
  private final NiceReqMapper niceReqMapper;
  private final NiceReqSummaryMapper niceReqSummaryMapper;
  private final NiceProperties niceProperties;
  private final FullTextConverter fullTextConverter;
  private final SocketSender socketSender;

  @Override
  @Transactional
  public NiceReqDTO.SafekeyData requestSafekey(long memberNo) {

//    MemberDTO.Response memberDTO =
//        Optional.ofNullable(memberClient.getMember(memberNo))
//            .orElseThrow(
//                () ->
//                    new CommonException(
//                        "API 호출을 실패히였습니다. (GET /member/member/" + memberNo + ")",
//                        ResponseCode.COMMON_ERROR))
//            .getData();
//
//    if (!memberDTO.isCreditSendAgree()) {
//      throw new NiceNotAgreeException();
//    }

    NiceReq niceReq =
        NiceReq.builder()
            .memberNo(memberNo)
            .reqDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
//            .agree1(memberDTO.isCreditSendAgree() ? "1000000000" : "0000000000")
//            .username(memberDTO.getMemberName())
//            .birthdate(memberDTO.getBirthDate().format(DateTimeFormatter.ofPattern("yyyyMMdd")))
//            .gender(Gender.valueOf(memberDTO.getGender()).getCode())
            .safekeyReqDate(LocalDateTime.now())
            .build();
    NiceReq savedNiceReq = niceReqRepository.save(niceReq);

    return NiceReqDTO.SafekeyData.builder()
        .no(savedNiceReq.getNo())
        .memberNo(memberNo)
        .encDate(makeSafekeyEncData(savedNiceReq))
        .build();
  }

  @Override
  @Transactional
  public long returnSafekey(String encData) {

    HashMap receiveData = extractSafekeyReceiveData(encData);
    log.debug("safekey received data: " + receiveData.toString());

    long niceReqNo = Long.parseLong((String) receiveData.get("REQ_SEQ"));

    NiceReq niceReq = niceReqRepository.getOne(niceReqNo);
    niceReq.setSafekey((String) receiveData.get("SAFE_KEY"));
    niceReq.setReturnCode((String) receiveData.get("RETURN_CODE"));
    niceReq.setReturnMessage((String) receiveData.get("RETURN_MSG"));
    niceReq.setAuthType((String) receiveData.get("AUTH_TYPE"));
    niceReq.setAuthDateTime((String) receiveData.get("AUTH_DATETIME"));
    niceReq.setResSeq((String) receiveData.get("RES_SEQ"));

    return niceReq.getNo();
  }

  @Override
  @Transactional
  public NiceReqDTO.NpacData requestNpac(long niceReqNo) {

    NiceReq niceReq = niceReqRepository.getOne(niceReqNo);

    if (StringUtils.isEmpty(niceReq.getSafekey())) {
      throw new NiceException("Safe-key 생성이 되지 않아서 인증송부 요청을 할 수 없습니다.");
    }

    NiceProperties.Npac properties = niceProperties.getNpac();
    long timeStamp = System.currentTimeMillis();
    String returnTarget = "P"; // 응답 페이지 호출 방법 (I: IFrame, P: 별도 팝업, S: 현재 창)
    String svcGb = "83";

    niceReq.setNpacReqDate(LocalDateTime.now());

    return NiceReqDTO.NpacData.builder()
        .no(niceReq.getNo())
        .memberNo(niceReq.getMemberNo())
        .userId(properties.getUserId())
        .loginId(properties.getLoginId())
        .passwd(properties.getPasswd())
        .returnUrl(properties.getReturnUrl())
        .resNo1(niceReq.getSafekey().substring(0, 6))
        .resNo2(niceReq.getSafekey().substring(6, 13))
        .resName(niceReq.getUsername())
        .custKey(String.valueOf(niceReq.getNo()))
        .svcGb(svcGb)
        .timeStamp(timeStamp)
        .encDate(makeNpacEncData(niceReq, properties, returnTarget, timeStamp))
        .build();
  }

  @Override
  @Transactional
  public long returnNpac(String returnData) {

    HashMap receiveMap = extractNpacReceiveData(returnData);

    NiceReq niceReq = niceReqRepository.getOne(Long.parseLong((String) receiveMap.get("cust_key")));
    niceReq.setCertNo((String) receiveMap.get("cert_no"));

    receiveCreditInfo(niceReq.getNo(), niceReq.getCertNo());

    return niceReq.getNo();
  }

  @Override
  public NiceReqDTO.Response get(long niceReqNo) {

    NiceReq niceReq = niceReqRepository.getOne(niceReqNo);
    return niceReqMapper.toDTO(niceReq);
  }

  @Override
  public List<NiceReqSummaryDTO> getList(String filter, Pageable pageable) {

    List<NiceReq> niceReqList = niceReqRepository.findAll();

    return niceReqList.stream().map(niceReqSummaryMapper::toDTO).collect(Collectors.toList());
  }

  private String makeSafekeyEncData(NiceReq niceReq) {

    String username;
    try {
      username = URLEncoder.encode(niceReq.getUsername(), "EUC-KR");
    } catch (UnsupportedEncodingException e) {
      log.error(e.toString());
      throw new NiceException("이름을 인코딩하는 중에 에러가 발생하였습니다.");
    }

    return null;

//    CPClient cpClient = new CPClient();
//    String plainData =
//        "7:REQ_SEQ"
//            + String.valueOf(niceReq.getNo()).getBytes().length
//            + ":"
//            + niceReq.getNo()
//            + "12:REQ_DATETIME"
//            + niceReq.getReqDate().getBytes().length
//            + ":"
//            + niceReq.getReqDate()
//            + "8:SITECODE"
//            + niceProperties.getSafekey().getSiteCode().getBytes().length
//            + ":"
//            + niceProperties.getSafekey().getSiteCode()
//            + "9:AUTH_TYPE"
//            + niceReq.getPermitAuthType().getBytes().length
//            + ":"
//            + niceReq.getPermitAuthType()
//            + "10:AGREE1_MAP"
//            + niceReq.getAgree1().getBytes().length
//            + ":"
//            + niceReq.getAgree1()
//            + "10:AGREE2_MAP"
//            + niceReq.getAgree2().getBytes().length
//            + ":"
//            + niceReq.getAgree2()
//            + "10:AGREE3_MAP"
//            + niceReq.getAgree3().getBytes().length
//            + ":"
//            + niceReq.getAgree3()
//            + "8:USERNAME"
//            + username.getBytes().length
//            + ":"
//            + username
//            + "9:BIRTHDATE"
//            + niceReq.getBirthdate().getBytes().length
//            + ":"
//            + niceReq.getBirthdate()
//            + "6:GENDER"
//            + niceReq.getGender().getBytes().length
//            + ":"
//            + niceReq.getGender()
//            + "8:CI_GUBUN"
//            + niceReq.getCiYn().getBytes().length
//            + ":"
//            + niceReq.getCiYn()
//            + "11:POPUP_GUBUN"
//            + niceReq.getPopupYn().getBytes().length
//            + ":"
//            + niceReq.getPopupYn()
//            + "7:RTN_URL"
//            + niceProperties.getSafekey().getReturnUrl().getBytes().length
//            + ":"
//            + niceProperties.getSafekey().getReturnUrl()
//            + "7:ERR_URL"
//            + niceProperties.getSafekey().getReturnUrl().getBytes().length
//            + ":"
//            + niceProperties.getSafekey().getReturnUrl();
//
//    int returnCode =
//        cpClient.fnEncode(
//            niceProperties.getSafekey().getSiteCode(),
//            niceProperties.getSafekey().getSitePasswd(),
//            plainData);
//    NiceEncodeCode niceEncodeCode =
//        Optional.ofNullable(NiceEncodeCode.valueOfCode(returnCode)).orElse(NiceEncodeCode.ERROR);
//    if (!NiceEncodeCode.SUCCESS.equals(niceEncodeCode)) {
//      throw new NiceException(niceEncodeCode);
//    }
//
//    String encData = cpClient.getCipherData();
//    log.debug("safekey encData: " + encData);
//
//    return encData;
  }

  private HashMap extractSafekeyReceiveData(String encData) {

    return null;

//    CPClient cpClient = new CPClient();
//
//    int returnCode =
//        cpClient.fnDecode(
//            niceProperties.getSafekey().getSiteCode(),
//            niceProperties.getSafekey().getSitePasswd(),
//            replaceChar(encData));
//    NiceDecodeCode niceDecodeCode =
//        Optional.ofNullable(NiceDecodeCode.valueOfCode(returnCode)).orElse(NiceDecodeCode.ERROR);
//    if (!NiceDecodeCode.SUCCESS.equals(niceDecodeCode)) {
//      throw new NiceException(niceDecodeCode);
//    }
//
//    String plainData = cpClient.getPlainData();
//    return cpClient.fnParse(plainData);
  }

  private String replaceChar(String value) {

    if (Optional.ofNullable(value).isPresent()) {
      value =
          value
              .replaceAll("<", "&lt;")
              .replaceAll(">", "&gt;")
              .replaceAll("\\*", "")
              .replaceAll("\\?", "")
              .replaceAll("\\[", "")
              .replaceAll("\\{", "")
              .replaceAll("\\(", "")
              .replaceAll("\\)", "")
              .replaceAll("\\^", "")
              .replaceAll("\\$", "")
              .replaceAll("'", "")
              .replaceAll("@", "")
              .replaceAll("%", "")
              .replaceAll(";", "")
              .replaceAll(":", "")
              .replaceAll("-", "")
              .replaceAll("#", "")
              .replaceAll("--", "")
              .replaceAll("-", "")
              .replaceAll(",", "");
    }

    return value;
  }

  private String makeNpacEncData(
      NiceReq niceReq, NiceProperties.Npac npacProperties, String returnTarget, long timeStamp) {

    String plainData =
        "{\"request\" : [{ \"passwd\" : \""
            + npacProperties.getPasswd()
            + "\", \"res_no1\" : \""
            + niceReq.getSafekey().substring(0, 6)
            + "\", \"res_no2\" : \""
            + niceReq.getSafekey().substring(6, 13)
            + "\", \"res_name\" : \""
            + niceReq.getUsername()
            + "\", \"cust_key\" : \""
            + niceReq.getNo()
            + "\", \"return_url\" : \""
            + npacProperties.getReturnUrl()
            + "\", \"return_target\" : \""
            + returnTarget
            + "\", \"time_stamp\" : \""
            + timeStamp
            + "\" }] }";

    String encData;
    try {
      encData =
          Crypto.encrypt(
              plainData, niceProperties.getNpac().getKey(), niceProperties.getNpac().getIv());
      log.debug("npac encData: " + encData);
    } catch (Exception e) {
      log.error(e.toString());
      throw new NiceException("인증송부 요청 데이터를 암호화하는 중에 에러가 발생하였습니다.");
    }
    return encData;
  }

  private HashMap extractNpacReceiveData(String returnData) {

    String plainData =
        Crypto.decrypt(
            returnData, niceProperties.getNpac().getKey(), niceProperties.getNpac().getIv());
    log.debug("safekey received data: " + plainData);

    assert plainData != null;

    String[] splitPlainData = plainData.split("&");
    HashMap hashMap = new HashMap();

    for (String data : splitPlainData) {
      String[] splitData = data.split("=");
      hashMap.put(splitData[0], splitData[1]);
    }
    return hashMap;
  }

  private void receiveCreditInfo(long niceReqNo, String certNo) {

    FullTextReqDTO fullTextReqDTO =
        FullTextReqDTO.builder()
            .userId(niceProperties.getNpac().getUserId())
            .managementNo("system")
            .reqTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
            .reqId(niceProperties.getNpac().getUserId())
            .certNo(certNo)
            .build();
    log.debug("fullTextReqDTO: \"" + fullTextReqDTO.toString() + "\"");

    String fullText = fullTextConverter.objectToFullText(fullTextReqDTO);

    NiceProperties.Npac.SecureConnector properties = niceProperties.getNpac().getSecureConnector();
    String receivedText = socketSender.send(fullText, properties.getHost(), properties.getPort());

    if (!StringUtils.isEmpty(receivedText)) {
      FullTextCommonDTO fullTextCommonDTO = new FullTextCommonDTO();
      CreditInfoMaster creditInfoMaster = new CreditInfoMaster();
      fullTextConverter.fullTextToObject(receivedText, fullTextCommonDTO, creditInfoMaster);

      log.debug("fullTextCommonDTO: \"" + fullTextCommonDTO.toString() + "\"");
      log.debug("creditInfoMaster: \"" + creditInfoMaster.toString() + "\"");

      NiceReq niceReq = niceReqRepository.getOne(niceReqNo);
      niceReq.setResCode(fullTextCommonDTO.getResCode());
      niceReq.setNiceManagementNo(fullTextCommonDTO.getNiceManagementNo());
      niceReq.setPrimaryBitmap(fullTextCommonDTO.getPrimaryBitmap());
      niceReq.setNpacResDate(LocalDateTime.now());

      creditInfoMaster.setNiceReq(niceReq);
      creditInfoMasterRepository.save(creditInfoMaster);
    }
  }
}
