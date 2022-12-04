package io.MichLangner.domain;

import lombok.Data;

@Data
public class CarParks {
    ParkingCoordinates parkingCoordinates;
    ParkingLocation parkingLocation;
    ParkingIdentifier parkingIdentifier;
}
