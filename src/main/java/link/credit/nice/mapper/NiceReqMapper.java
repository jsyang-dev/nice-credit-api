package link.credit.nice.mapper;

import link.credit.nice.domain.NiceReq;
import link.credit.nice.dto.NiceReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * NICE 신용정보 관리
 *
 * <p>Create by jsyang on 2020/09/03
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NiceReqMapper {

  NiceReqDTO.Response toDTO(NiceReq niceReq);
}
