package org.payment.transferservice.repository;

import org.payment.transferservice.domain.Deposit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepositRepository extends CrudRepository<Deposit, Long> {

    Optional<Deposit> findByRequestUid(String requestUid);
}
