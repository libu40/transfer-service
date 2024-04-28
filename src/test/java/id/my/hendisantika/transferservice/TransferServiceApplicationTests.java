package id.my.hendisantika.transferservice;

import id.my.hendisantika.transferservice.domain.Deposit;
import id.my.hendisantika.transferservice.dto.DepositResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class TransferServiceApplicationTests {

    static final String API_URL = "/api/deposits";
    private static final int CUSTOMER_NOT_FOUND_ERROR_CODE = 404;
    private static final int VALIDATION_ERROR_CODE = 400;

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer postgres = new PostgreSQLContainer<>("postgres:16-alpine3.19");

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void shouldAddDeposit_OnNewDepositRequest() {
        Deposit deposit = DepositFixture.createDeposit();
        DepositResponseDto depositResponse = webTestClient
                .post().uri(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(deposit)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(DepositResponseDto.class)
                .returnResult()
                .getResponseBody();

        assertThat(depositResponse).isNotNull();
        assertThat(depositResponse.requestUid()).isEqualTo("1111-3333");
    }
}
