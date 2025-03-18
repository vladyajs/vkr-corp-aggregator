package com.vkr.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/gateway")
public class ApiGatewayController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String LOAN_TRANSPORT_SERVICE_URL = "http://localhost:8082";

    // Прокси-запрос на создание заявителя
    @PostMapping("/applicants")
    public ResponseEntity<?> createApplicant(@RequestBody String body, @RequestHeader("Authorization") String authorization) {
        String url = LOAN_TRANSPORT_SERVICE_URL + "/applicants";

        // Добавляем токен в заголовок запроса
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorization);
        headers.setContentType(MediaType.APPLICATION_JSON); // Добавьте эту строку


        // Передаем токен вместе с запросом
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class);  // Прокси-запрос с токеном
    }

    // Прокси-запрос на получение заявителя по ID
    @GetMapping("/applicants/{applicantId}")
    public ResponseEntity<?> getApplicantById(@PathVariable Long applicantId, @RequestHeader("Authorization") String authorization) {
        String url = LOAN_TRANSPORT_SERVICE_URL + "/applicants/" + applicantId;

        // Добавляем токен в заголовок запроса
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorization);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);  // Прокси-запрос с токеном
    }

    // Прокси-запрос на получение заявителя по email
    @GetMapping("/applicants/email/{email}")
    public ResponseEntity<?> getApplicantByEmail(@PathVariable String email, @RequestHeader("Authorization") String authorization) {
        String url = LOAN_TRANSPORT_SERVICE_URL + "/applicants/email/" + email;

        // Добавляем токен в заголовок запроса
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorization);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);  // Прокси-запрос с токеном
    }

    // Прокси-запрос на получение заявителя по ИНН
    @GetMapping("/applicants/inn/{inn}")
    public ResponseEntity<?> getApplicantByInn(@PathVariable String inn, @RequestHeader("Authorization") String authorization) {
        String url = LOAN_TRANSPORT_SERVICE_URL + "/applicants/inn/" + inn;

        // Добавляем токен в заголовок запроса
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorization);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);  // Прокси-запрос с токеном
    }

    // Прокси-запрос на обновление данных заявителя
    @PutMapping("/applicants/{applicantId}")
    public ResponseEntity<?> updateApplicant(@PathVariable Long applicantId, @RequestBody String body, @RequestHeader("Authorization") String authorization) {
        String url = LOAN_TRANSPORT_SERVICE_URL + "/applicants/" + applicantId;

        // Добавляем токен в заголовок запроса
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorization);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);  // Прокси-запрос с токеном
        return ResponseEntity.ok(body);  // Возвращаем исходное тело запроса
    }
}
