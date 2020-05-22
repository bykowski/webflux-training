package pl.bykowski.webfluxtraining;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class StudentInit {

    private StudentRepo studentRepo;

    public StudentInit(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;

        Flux<Student> studentFlux = Flux.just(
                new Student("Karol", "Nowiski"),
                new Student("Agnieszka", "Nowska")
        );
        studentRepo.saveAll(studentFlux).subscribe();
    }
}
