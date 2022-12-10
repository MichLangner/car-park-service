package io.MichLangner.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Parking not found")
public class ParkingNotFoundException extends RuntimeException{
}
