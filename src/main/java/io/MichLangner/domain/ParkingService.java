package io.MichLangner.domain;

import io.MichLangner.api.dto.LocationDto;
import io.MichLangner.domain.exceptions.ParkingNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ParkingService {

    ParkingRepository parkingRepository;

    public Parking createParking(ParkingCoordinates parkingCoordinates,ParkingLocation parkingLocation,String parkingName){

        Parking parking = Parking.builder()
                .parkingCoordinates(parkingCoordinates)
                .parkingLocation(parkingLocation)
                .parkingName(parkingName)
                .parkingId(ParkingId.random())
                .build();
        return parkingRepository.save(parking);
    }

    public Page<Parking> getAllParking(){
        return parkingRepository.findAll(PageRequest.of(0,100));
    }

    public Parking updateParking(ParkingId parkingId,String parkingName){
        Optional<Parking> parking = parkingRepository.findById(parkingId);
        if( parkingRepository.findFirstByParkingIdEquals(parkingId).isPresent()){
            Parking unpackedParking = parking.get();
            return parkingRepository.insert(unpackedParking);
       }else {
            throw new ParkingNotFoundException();

        }

    }

    public void deleteParking(ParkingId parkingId){
        parkingRepository.deleteByParkingIdEquals(parkingId);
    }

    public Parking getLocation(ParkingId parkingId){
       return parkingRepository.findFirstByParkingIdEquals(parkingId).orElseThrow(ParkingNotFoundException::new);
    }


}
