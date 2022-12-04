package io.MichLangner.api;


import io.MichLangner.api.dto.CarParksIdDto;
import io.MichLangner.domain.ParkingIdentifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;
@RequestMapping(value = "/admin")
@RestController
public class AdminController {

@PostMapping(value = "/parkings")
    public ResponseEntity<CarParksIdDto> parkingIdentifierResponseEntity(){

    ParkingIdentifier parkingIdentifier = new ParkingIdentifier(UUID.randomUUID().toString());
    return ResponseEntity.created(URI.create("/parkings/" + parkingIdentifier.rawValue())).body(new CarParksIdDto(parkingIdentifier.rawValue()));

}

}
