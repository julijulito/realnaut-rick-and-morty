package com.example.realnautrickmorty.application.service;

import com.example.realnautrickmorty.application.ports.RickAndMortyClient;
import com.example.realnautrickmorty.domain.client.CharacterApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class RickAndMortyClientImpl implements RickAndMortyClient {
    private static final String API_URL = "https://rickandmortyapi.com/graphql";

    private static final String CHARACTER_GRAPHQL_QUERY =
                    "{\"query\":\"query {\\n    characters(filter: { name: \\\"%s\\\" }) " +
                    "{\\n      results {\\n        name\\n        " +
                    "episode {\\n          name\\n\\t\\t\\t\\t\\tair_date\\n\\t\\t\\t\\t}\\n\\t\\t\\t}\\n\\t}\\n}\"}";

    @Override
    public CharacterApiResponse getCharacterAppearanceInfo(final String characterName) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(createGraphQLQuery(characterName)))
                .build();

        return objectMapper.readValue(
                HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()).body(),
                CharacterApiResponse.class
        );
    }

    private String createGraphQLQuery(final String characterName) {
        return String.format(CHARACTER_GRAPHQL_QUERY, characterName);
    }
}
