package id.my.hendisantika.transferservice.service;

import id.my.hendisantika.transferservice.domain.Deposit;
import id.my.hendisantika.transferservice.metric.MetricsService;
import id.my.hendisantika.transferservice.repository.DepositRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class DepositService {

    private final DepositRepository depositRepository;
    private final CustomerService customerService;
    private MetricsService serviceMetrics;

    @Transactional
    public Deposit add(Deposit deposit) {
        var depositOptional = depositRepository.findByRequestUid(deposit.getRequestUid());
        return depositOptional.orElseGet(() -> {
            log.debug("New deposit request, requestUid: {}", deposit.getRequestUid());
            customerService.validateCustomer(deposit.getCustomerId());
            var savedDeposit = depositRepository.save(deposit);
            serviceMetrics.countCreatedDeposits(savedDeposit);
            return savedDeposit;
        });
    }
}
