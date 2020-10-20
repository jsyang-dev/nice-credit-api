package link.credit.nice.service;

import link.credit.nice.dto.NiceReqDTO;
import link.credit.nice.dto.NiceReqSummaryDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NiceService {

  /**
   * Safe-key 요청 데이터 생성
   *
   * @param memberNo 고객 번호
   * @return Safe-key 요청 데이터 DTO
   */
  NiceReqDTO.SafekeyData requestSafekey(long memberNo);

  /**
   * Safe-key 데이터 수신
   *
   * @param encData Safe-key 수신 데이터
   * @return NICE 요청 번호
   */
  long returnSafekey(String encData);

  /**
   * 인증송부 요청 데이터 생성
   *
   * @param niceReqNo NICE 요청 번호
   * @return 인증송부 요청 데이터 DTO
   */
  NiceReqDTO.NpacData requestNpac(long niceReqNo);

  /**
   * 인증송부 데이터 수신
   *
   * @param returnData 인증송부 수신 데이터
   * @return NICE 요청 번호
   */
  long returnNpac(String returnData);

  /**
   * NICE 요청 조회
   *
   * @param niceReqNo NICE 요청 번호
   * @return NICE 요청 DTO
   */
  NiceReqDTO.Response get(long niceReqNo);

  /**
   * NICE 요청 리스트 조회
   *
   * @param filter 검색 조건
   * @param pageable 페이지 정보
   * @return NICE 요청 리스트
   */
  List<NiceReqSummaryDTO> getList(String filter, Pageable pageable);
}
