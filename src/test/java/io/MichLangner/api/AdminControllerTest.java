package io.MichLangner.api;

import io.MichLangner.api.dto.CarParksIdDto;
import io.MichLangner.domain.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdminControllerTest {

    @MockBean
    private ParkingRepository parkingRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    private CarParkingRequest carParkingRequest = CarParkingRequest.builder()
            .parkingLatitude("30")
            .parkingLongitude("50")
            .parkingNumber("17")
            .parkingStreet("Sezamkowa")
            .parkingName("CornerParking")
            .build();

    @Test
    void whenAdminAddCarParkApplicationShouldReturnCarParkId() {
        //given
        ParkingId parkingId = ParkingId.random();
        Parking expectedParking = Parking.builder().parkingId(parkingId)
                .parkingLocation(ParkingLocation.builder().street("Sezamkowa").plotNumber("17").build())
                .parkingCoordinates(ParkingCoordinates.builder()
                        .longitude("30").latitude("50").build()).parkingName("CornerParkings").build();

        Mockito.when(parkingRepository.save(Mockito.any())).thenReturn(expectedParking);
        String postCarParkUrl = "/admin/parkings";

        HttpEntity<CarParkingRequest> carParkingRequestHttpEntity = new HttpEntity<>(carParkingRequest);

        //when
        ResponseEntity<Parking> carParksIdDtoResponseEntity =
                restTemplate.postForEntity(postCarParkUrl, carParkingRequestHttpEntity, Parking.class);

        //then
        assertThat(carParksIdDtoResponseEntity.getStatusCode().equals(HttpStatus.CREATED)).isTrue();
        assertThat(carParksIdDtoResponseEntity.getBody()).isEqualTo(expectedParking);
        assertThat(carParksIdDtoResponseEntity.getHeaders().get(HttpHeaders.LOCATION)).isNotNull();
        assertThat(carParksIdDtoResponseEntity.getHeaders().getLocation().toString())
                .isEqualTo("/parkings" + parkingId.rawValue());


    }

    @Test
    void whenAdminAddCarParkApplicationWithEmptyName() {
        //given
        String postCarParkUrl = "/admin/parkings";

        carParkingRequest.setParkingName("");
        HttpEntity<CarParkingRequest> carParkingRequestHttpEntity = new HttpEntity<>(carParkingRequest);

        //when
        ResponseEntity<Parking> carParksIdDtoResponseEntity =
                restTemplate.postForEntity(postCarParkUrl, carParkingRequestHttpEntity, Parking.class);

        //then
        assertThat(carParksIdDtoResponseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST)).isTrue();


    }
    @Test
    void whenDeletingParkingShouldReturnNoContent(){
        //given
        String deleteParking = "/admin/parkings/";
        String testId = "TestId";
        String deleteParkingEndPoint = deleteParking + testId;

        //when
        restTemplate.delete(deleteParkingEndPoint);

        //then
        Mockito.verify(parkingRepository).deleteByParkingIdEquals(Mockito.any());



    }


}