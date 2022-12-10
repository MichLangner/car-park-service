package io.MichLangner.api;


import io.MichLangner.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RequestMapping(value = "/admin")
@RestController
public class AdminController {

    private final ParkingService parkingService;

    public AdminController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }


    @PostMapping(value = "/parkings")
    public ResponseEntity<Parking> addParking(@Valid @RequestBody CarParkingRequest carParkingRequest) {
        Parking parking = parkingService.createParking(new ParkingCoordinates(carParkingRequest.getParkingLatitude()
                        , carParkingRequest.getParkingLongitude())
                , new ParkingLocation(carParkingRequest.getParkingStreet()
                        , carParkingRequest.getParkingNumber())
                , carParkingRequest.getParkingName());
        return ResponseEntity.created(URI.create("/parkings" + parking.getParkingId().rawValue())).body(parking);

    }

    @PatchMapping(value = "parkigns/{parkingId}")
    public ResponseEntity<Parking> updateParkingValues(@PathVariable String parkingId,
                                                       @RequestParam String parkingName) {
        ParkingId parkingId1 = new ParkingId(parkingId);
        return ResponseEntity.ok().body(parkingService.updateParking(parkingId1, parkingName));

    }
    @DeleteMapping(value = "/parkings/{parkingId}")
    public ResponseEntity<Parking> deleteParkingValues(@PathVariable String parkingId){
        ParkingId parkingId1 = new ParkingId(parkingId);
        parkingService.deleteParking(parkingId1);
        return ResponseEntity.noContent().build();
    }

}
