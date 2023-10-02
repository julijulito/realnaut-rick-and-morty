package com.example.realnautrickmorty.adapter.client;

import com.example.realnautrickmorty.application.config.RickAndMortyApiConfig;
import com.example.realnautrickmorty.domain.client.RickAndMortyApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@AllArgsConstructor
@EnableConfigurationProperties(RickAndMortyApiConfig.class)
public class RickAndMortyClientImpl implements RickAndMortyClient {

    private RickAndMortyApiConfig apiConfig;

    private static final String CHARACTER_GRAPHQL_QUERY =
                    "{\"query\":\"query {\\n    characters(filter: { name: \\\"%s\\\" }) " +
                    "{\\n      results {\\n        name\\n        " +
                    "episode {\\n          name\\n\\t\\t\\t\\t\\tair_date\\n\\t\\t\\t\\t}\\n\\t\\t\\t}\\n\\t}\\n}\"}";

    @Override
    public RickAndMortyApiResponse getCharacterAppearanceInfo(final String characterName) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiConfig.getApiUrl()))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(createGraphQLQuery(characterName)))
                .build();
        final ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(
                HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()).body(),
                RickAndMortyApiResponse.class);
    }

    private String createGraphQLQuery(final String characterName) {
        return String.format(CHARACTER_GRAPHQL_QUERY, characterName);
    }
}
