package com.example.realnautrickmorty.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class CharacterAppearanceData implements Serializable {

    @JsonProperty("name")
    private final String name;

    @JsonProperty("episodes")
    private final List<String> episodes;

    @JsonProperty("first_appearance")
    private String firstAppearance;

}
