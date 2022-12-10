package io.MichLangner.domain;

import lombok.Builder;
import lombok.Value;

@Builder
public record ParkingCoordinates(String latitude, String longitude) {

}
