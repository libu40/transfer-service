package org.payment.transferservice.metric;

import org.payment.transferservice.domain.Deposit;

public interface MetricsService {

    void countCreatedDeposits(Deposit deposit);

    void incrementExceptionCounter(String tageName, String tagValue);
}
