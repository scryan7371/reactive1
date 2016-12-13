package com.scottryan.reactive.services;

import com.scottryan.reactive.models.Person;
import com.scottryan.reactive.repositories.PersonRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Support for Person management.
 */
@Service
public class PersonService {

    Log log = LogFactory.getLog(PersonService.class);

    private PersonRepository personRepository;

    PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Flux<Person> find() {
        return Flux.fromIterable(personRepository.findAll());
    }

    public Mono<Person> create(Person person) {
        return Mono.just(personRepository.save(person));
    }

    public Flux<Integer> findNumbers() {
        List<Flux<Integer>> allFluxes = new ArrayList<>();
        allFluxes.add(getLowNumbers().doOnComplete(() -> log.error("complete for low " + System.currentTimeMillis())));
        log.error("Added low to array " + System.currentTimeMillis());
        allFluxes.add(getHighNumbers().doOnComplete(() -> log.error("complete for high " + System.currentTimeMillis())));
        log.error(("Added high to array " + System.currentTimeMillis()));
        Flux<Integer> allNumbers = Flux.merge(allFluxes).doOnComplete(() -> log.error("all complete " + System.currentTimeMillis()));
        log.error("Back from merge of fluxes " + System.currentTimeMillis());
        return allNumbers;
    }

    public Flux<Integer> findFastest() {
        List<Flux<Integer>> allFluxes = new ArrayList<>();
        allFluxes.add(getLowNumbers().doOnComplete(() -> log.error("complete for low " + System.currentTimeMillis())));
        log.error("Added low to array " + System.currentTimeMillis());
        allFluxes.add(getHighNumbers().doOnComplete(() -> log.error("complete for high " + System.currentTimeMillis())));
        log.error(("Added high to array " + System.currentTimeMillis()));
        Flux<Integer> allNumbers = Flux.firstEmitting(allFluxes).doOnComplete(() -> log.error("all complete " + System.currentTimeMillis()));
        log.error("Back from find fastest of fluxes " + System.currentTimeMillis());
        return allNumbers;
    }

    private Flux<Integer> getLowNumbers() {
        return Flux.range(1, 10).delayMillis(10);
    }

    private Flux<Integer> getHighNumbers() {
        return Flux.range(20, 30);
    }
}
