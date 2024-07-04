package id.my.hendisantika.transferservice.metric;

import id.my.hendisantika.transferservice.domain.Deposit;

public interface MetricsService {

    void countCreatedDeposits(Deposit deposit);

    void incrementExceptionCounter(String tageName, String tagValue);
}
