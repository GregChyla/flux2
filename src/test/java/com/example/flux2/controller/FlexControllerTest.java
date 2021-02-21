package com.example.flux2.controller;

import com.example.flux2.api.FluxApi;
import com.example.flux2.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = FluxController.class)
@Slf4j
class FlexControllerTest {

    private static final String ADDRESS = "http://localhost:8080";
    private static final String GET_PATH = "/flux/get";
    @Autowired
    private WebTestClient webClient;
    @MockBean
    private FluxApi fluxApi;

    @Test
    void getOnePerson() {
        //GIVEN
        when(fluxApi.getOne()).thenReturn(Mono.just(getFakePerson()));

        //WHEN
        webClient.get()
                .uri(ADDRESS + GET_PATH)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Person.class)
                .isEqualTo(getFakePerson());
    }

    private Person getFakePerson() {
        return new Person("John", 50);
    }
}
