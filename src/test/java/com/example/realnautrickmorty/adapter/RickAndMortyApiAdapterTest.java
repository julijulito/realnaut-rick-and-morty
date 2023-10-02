package com.example.realnautrickmorty.adapter;

import com.example.realnautrickmorty.adapter.client.RickAndMortyClient;
import com.example.realnautrickmorty.domain.client.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RickAndMortyApiAdapterTest {

    @Mock
    private RickAndMortyClient rickAndMortyClient;

    @InjectMocks
    private RickAndMortyApiAdapter rickAndMortyApiAdapter;

    @Test
    void testGetCharacters_notEmpty_success() throws IOException, InterruptedException {
        // GIVEN
        String characterName = "Rick Sanchez";
        Episode episode = new Episode();
        episode.setAirDate("December 12, 2014");
        episode.setName("Episode 1");
        Result result = new Result();
        result.setName(characterName);
        result.setEpisode(List.of(episode));
        Characters characters = new Characters();
        characters.setResults(List.of(result));
        Data data = new Data();
        data.setCharacters(characters);
        RickAndMortyApiResponse apiResponse = new RickAndMortyApiResponse();
        apiResponse.setData(data);

        // WHEN
        when(rickAndMortyClient.getCharacterAppearanceInfo(characterName)).thenReturn(apiResponse);

        // THEN
        Result expected = rickAndMortyApiAdapter.getCharacters(characterName);
        assertNotNull(expected);
    }

    @Test
    void testGetCharacters_empty_failed() throws IOException, InterruptedException {
        // GIVEN
        String characterName = "Rick Sanchez";
        List<Result> result = new ArrayList<>();
        Characters characters = new Characters();
        characters.setResults(result);
        Data data = new Data();
        data.setCharacters(characters);
        RickAndMortyApiResponse apiResponse = new RickAndMortyApiResponse();
        apiResponse.setData(data);

        // WHEN
        when(rickAndMortyClient.getCharacterAppearanceInfo(characterName)).thenReturn(apiResponse);

        // THEN
        assertThrows(ResponseStatusException.class, () -> rickAndMortyApiAdapter.getCharacters(characterName));
    }
}

