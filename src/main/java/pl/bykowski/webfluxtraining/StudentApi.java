package pl.bykowski.webfluxtraining;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class StudentApi {

    Logger logger = LoggerFactory.getLogger(StudentApi.class);

    Flux<Student> studentFlux = Flux.just(
            new Student("Przemek", "Bykowski"),
            new Student("Anna", "Nowak"),
            new Student("Jan", "Kowalski"),
            new Student("Brigida", "Nowakowska")
    );

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Student> get() {
        return studentFlux.delayElements(Duration.ofSeconds(3));
    }

    @PostMapping()
    public Flux<Student> post(@RequestBody Student student) {
        return studentFlux.mergeWith(Mono.just(student));
    }
}
