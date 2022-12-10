package io.MichLangner.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingRepository extends MongoRepository<Parking,ParkingId> {

    Optional<Parking> findFirstByParkingIdEquals(ParkingId parkingId);

    void deleteByParkingIdEquals(ParkingId parkingId);
}
