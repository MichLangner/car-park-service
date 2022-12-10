package io.MichLangner.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationDto {

    String street;
    @JsonProperty(value = "plot_number")
    String plotNumber;
    String longitude;
    String latitude;
}
