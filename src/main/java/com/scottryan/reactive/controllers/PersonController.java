package com.scottryan.reactive.controllers;

import com.scottryan.reactive.models.Person;
import com.scottryan.reactive.services.PersonService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controller to manage people.
 */
@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private Log log = LogFactory.getLog(PersonController.class);
    private PersonService personService;

    PersonController(PersonService service) {
        this.personService = service;
    }

    @GetMapping
    public Flux<Person> find() {
        log.error("Entering the getter");
        Flux<Person> people = personService.find();
        log.error("Exiting the getter");
        return people;
    }

    @PostMapping
    public Mono<Person> save(@RequestBody Mono<Person> mono) {
        return mono.then(personService::create);
    }

    @GetMapping("/numbers")
    public Flux<Integer> findNumbers() {
        Flux<Integer> numbers = personService.findNumbers();
        log.error("Done in Controller");
        return numbers;
    }

    @GetMapping("/fastest")
    public Flux<Integer> findFastest() {
        Flux<Integer> numbers = personService.findFastest();
        log.error("Done in Controller");
        return numbers;
    }
}
