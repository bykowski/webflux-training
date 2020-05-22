package pl.bykowski.webfluxtraining;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentClient {

    Logger logger = LoggerFactory.getLogger(StudentClient.class);

    public void getDataFromApi() {
        Flux<Student> studentFlux = WebClient.create()
                .get()
                .uri("http://localhost:8080")
                .retrieve()
                .bodyToFlux(Student.class);
        studentFlux.subscribe(element -> logger.info(element.toString()));
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createStudent() {
        Flux<Student> studentFlux = WebClient.create()
                .post()
                .uri("http://localhost:8080")
                .body(Mono.just(new Student("Jacek", "Karolak")), Student.class)
                .retrieve()
                .bodyToFlux(Student.class);
        studentFlux.subscribe(element -> logger.info(element.toString()));
    }
}
