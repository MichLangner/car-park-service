package io.MichLangner.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CarParkingRequest {

    @NotBlank
    String parkingLatitude;
    @NotBlank
    String parkingLongitude;
    @NotBlank
    String parkingStreet;
    @NotBlank
    String parkingNumber;
    @NotBlank
    String parkingName;


}
