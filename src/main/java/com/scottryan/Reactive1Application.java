package com.scottryan;

import com.scottryan.reactive.models.Person;
import com.scottryan.reactive.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

@SpringBootApplication
public class Reactive1Application {

    @Autowired
    PersonService personService;

    public static void main(String[] args) {
        SpringApplication.run(Reactive1Application.class, args);
    }

    @PostConstruct
    public void buildData() {
        Stream.of("Scott", "John", "Steve").forEach(firstName -> {
            Person person = new Person(firstName, "Ryan");
            personService.create(person);
        });

    }
}
