package com.example.flux2.api;

import com.example.flux2.entity.Person;
import com.example.flux2.service.MeterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class FluxApi {

    private static final String GET_PATH = "/person/get";
    private static final String GET_ALL_PATH = "/person/get/all";
    private final MeterService meterService;
    private WebClient webClient;
    @Value("${flux1.address}")
    private String address;

    @PostConstruct
    private void setUpWebClient() {
        webClient = WebClient.create(address);
    }

    public Mono<Person> getOne() {
        meterService.start();
        Mono<Person> personResponse = webClient.get().uri(GET_PATH)
                .retrieve()
                .bodyToMono(Person.class);
        meterService.stop();
        return personResponse;
    }

    public Flux<Person> getAll() {
        meterService.start();
        Flux<Person> personResponse = webClient.get().uri(GET_ALL_PATH)
                .retrieve()
                .bodyToFlux(Person.class);
        meterService.stop();
        return personResponse;
    }
}
