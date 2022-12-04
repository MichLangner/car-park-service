package io.MichLangner.api;

import io.MichLangner.api.dto.CarParksIdDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdminControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void whenAdminAddCarParkApplicationShouldReturnCarParkId(){
        //given
        String carParkId = "8b71e93f-ed63-4857-9c3c-b096894ed923";
        String postCarParkUrl = "/admin/parkings";
        //when
        ResponseEntity<CarParksIdDto> carParksIdDtoResponseEntity = restTemplate.postForEntity(postCarParkUrl, null, CarParksIdDto.class);
        //then
        assertThat(carParksIdDtoResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(carParksIdDtoResponseEntity.getBody()).isNotNull();
        assertThat(carParksIdDtoResponseEntity.getHeaders().get(HttpHeaders.LOCATION)).isNotNull();
        assertThat(carParksIdDtoResponseEntity.getHeaders().getLocation().toString()).startsWith("/parkings");
    }

}