package link.credit.nice.repository;

import link.credit.nice.domain.NiceReq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NiceReqRepository extends JpaRepository<NiceReq, Long> {

  @Query(
      "select s from NiceReq s where s.no = (select max(s1.no) from NiceReq s1 where s1.delYn = false)")
  Optional<NiceReq> findLast();
}
