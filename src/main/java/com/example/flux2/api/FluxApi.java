package com.example.flux2.api;

import com.example.flux2.entity.Person;
import com.example.flux2.service.MeterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FluxApi {

    private final WebClient webClient = WebClient.builder().build();

    private final MeterService meterService;

    @Value("${flux1.address}")
    private String address;
    public Mono<Person> getOne() {
        meterService.start();
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(address).path("/person/get");
        Mono<Person> personResponse = webClient.get().uri(uri.build().toUriString()).retrieve().bodyToMono(Person.class);
        meterService.stop();
        return personResponse;
    }

    public Flux<Person> getAll() {
        meterService.start();
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(address).path("/person/get/all");
        Flux<Person> personResponse = webClient.get().uri(uri.build().toUriString()).retrieve().bodyToFlux(Person.class);
        meterService.stop();
        return personResponse;
    }
}
