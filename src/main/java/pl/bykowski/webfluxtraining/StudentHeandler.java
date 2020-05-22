package pl.bykowski.webfluxtraining;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class StudentHeandler {

    public Mono<ServerResponse> serverResponseMono(ServerRequest serverRequest) {
        Flux<Student> studentFlux = Flux.just(
                new Student("Przemek", "Bykowski"),
                new Student("Anna", "Nowak"),
                new Student("Jan", "Kowalski"),
                new Student("Brigida", "Nowakowska")
        );
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentFlux, Student.class);
    }

    @Bean
    public RouterFunction<ServerResponse> responseRouterFunction(StudentHeandler studentHeandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/get-students"), studentHeandler::serverResponseMono);
    }
}
