package link.credit.nice.mapper;

import link.credit.nice.domain.NiceReq;
import link.credit.nice.dto.NiceReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NiceReqMapper {

  NiceReqDTO.Response toDTO(NiceReq niceReq);
}
