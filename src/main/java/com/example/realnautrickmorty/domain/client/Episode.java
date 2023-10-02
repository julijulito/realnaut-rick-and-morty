package com.example.realnautrickmorty.domain.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
public class Episode {

    private String name;

    @JsonProperty(value = "air_date")
    private String airDate;

    public void setAirDate(String airDate) {
        if (airDate == null || airDate.isEmpty()) {
            this.airDate = null;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
            this.airDate = LocalDate.parse(airDate, formatter).toString();
        }
    }

}
