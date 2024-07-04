package id.my.hendisantika.transferservice.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;

@Data
@Builder(toBuilder = true)
@Table(schema = "transfer_service", name = "customer")
public class Customer {

    @Id
    long id;
    String name;
    String personNumber;
    OffsetDateTime createdAt;
}
