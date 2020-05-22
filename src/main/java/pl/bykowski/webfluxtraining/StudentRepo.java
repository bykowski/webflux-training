package pl.bykowski.webfluxtraining;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface StudentRepo extends ReactiveMongoRepository<Student, String> {

    Mono<Student> findBySurname(String surname);
}
