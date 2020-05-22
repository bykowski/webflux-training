package pl.bykowski.webfluxtraining;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
class StudentApiTest {

    @Autowired
    WebTestClient webTestClient;

    @org.junit.jupiter.api.Test
    public void name() {
        webTestClient
                .get()
                .uri("http://localhost:8080/surname/Nowska")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                    .jsonPath("$.name").isEqualTo("Agnieszka");
    }

    @org.junit.jupiter.api.Test
    public void is_body_list() {
        webTestClient
                .get()
                .uri("http://localhost:8080/")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Student.class);
    }



}
