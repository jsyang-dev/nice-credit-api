package link.credit.nice.repository;


import link.credit.nice.domain.CreditInfoMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * NICE 신용정보 관리
 *
 * <p>Create by jsyang on 2020/09/01
 */
public interface CreditInfoMasterRepository extends JpaRepository<CreditInfoMaster, Long> {}
