package com.example.flux2.controller;

import com.example.flux2.api.FluxApi;
import com.example.flux2.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/flux1")
@RequiredArgsConstructor
public class FluxController {

    private final FluxApi fluxApi;

    @GetMapping("/get")
    public Mono<Person> getOnePerson() {
        return fluxApi.getOne();
    }

    @GetMapping("/get/all")
    public Flux<Person> getAllPersons() {
        return fluxApi.getAll();
    }

}
