package com.example.realnautrickmorty.application.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@AllArgsConstructor
@ConfigurationProperties
public class RickAndMortyApiConfig {

    @Value("${rick-and-morty-api-url}")
    private String apiUrl;

}
