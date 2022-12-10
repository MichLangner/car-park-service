package io.MichLangner.domain;

import lombok.Builder;
import lombok.Value;

@Builder
public record ParkingLocation(String street, String plotNumber) {


}
