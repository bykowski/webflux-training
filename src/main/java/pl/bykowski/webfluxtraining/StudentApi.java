package pl.bykowski.webfluxtraining;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class StudentApi {

    Logger logger = LoggerFactory.getLogger(StudentApi.class);
    private StudentRepo studentRepo;

    public StudentApi(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Student> get() {
        return studentRepo.findAll().delayElements(Duration.ofSeconds(1));
    }

    @PostMapping
    public Mono<Student> post(@RequestBody Student student) {
        return studentRepo.insert(student);
    }

    @GetMapping(value = "/id/{studentId}")
    public Mono<Student> getStudentById(@PathVariable String studentId) {
        return studentRepo.findById(studentId);
    }

    @GetMapping(value = "/surname/{surname}")
    public Mono<Student> getBySurname(@PathVariable String surname) {
        return studentRepo.findBySurname(surname);
    }
}
