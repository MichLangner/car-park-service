package io.MichLangner.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
public class Parking {
    ParkingCoordinates parkingCoordinates;
    ParkingLocation parkingLocation;
    ParkingId parkingId;
    String parkingName;
}
