package com.example.realnautrickmorty.domain.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Episode {
    private String name;

    @JsonProperty(value = "air_date")
    private String airDate;

}
