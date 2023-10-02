package com.example.realnautrickmorty.application;

import com.example.realnautrickmorty.adapter.RickAndMortyApiAdapter;
import com.example.realnautrickmorty.application.service.CharacterAppearanceServiceImpl;
import com.example.realnautrickmorty.domain.client.Episode;
import com.example.realnautrickmorty.domain.client.Result;
import com.example.realnautrickmorty.domain.rest.CharacterAppearanceData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CharacterAppearanceServiceTest {

    @Mock
    private RickAndMortyApiAdapter rickAndMortyApiAdapter;

    @InjectMocks
    private CharacterAppearanceServiceImpl characterAppearanceService;

    @BeforeEach
    public void setUp() {
        this.characterAppearanceService = new CharacterAppearanceServiceImpl(rickAndMortyApiAdapter);
    }

    @Test
    void getCharacterAppearanceData_success() throws IOException, InterruptedException {
        // GIVEN
        String characterName = "Rick Sanchez";
        String airDate = "December 12, 2014";
        String episodeName = "Episode 1";
        Episode episode = new Episode();
        episode.setName(episodeName);
        episode.setAirDate(airDate);
        List<Episode> episodes = List.of(episode);
        Result result = new Result();
        result.setName(characterName);
        result.setEpisode(episodes);

        // WHEN
        when(rickAndMortyApiAdapter.getCharacters(characterName)).thenReturn(result);

        CharacterAppearanceData expected = characterAppearanceService.getCharacterAppearanceData(characterName);

        assertEquals(characterName, expected.getName());
        assertEquals("2014-12-12", expected.getFirstAppearance());
        assertEquals(episodeName, expected.getEpisodes().get(0));
    }

    @Test
    void getCharacterAppearanceData_missingFirstAppearance_failed() throws IOException, InterruptedException {
        // GIVEN
        String characterName = "Rick Sanchez";
        String airDate = "December 12, 2014";
        String episodeName = "Episode 1";
        Episode episode = new Episode();
        episode.setName(episodeName);
        episode.setAirDate(null);
        List<Episode> episodes = List.of(episode);
        Result result = new Result();
        result.setName(characterName);
        result.setEpisode(episodes);

        // WHEN
        when(rickAndMortyApiAdapter.getCharacters(characterName)).thenReturn(result);

        // THEN
        assertThrows(
                ResponseStatusException.class,
                () -> characterAppearanceService.getCharacterAppearanceData(characterName));
    }

    @Test
    void getCharacterAppearanceData_IOException_failed() throws IOException, InterruptedException {
        // GIVEN
        String characterName = "Rick Sanchez";
        // WHEN
        when(rickAndMortyApiAdapter.getCharacters(characterName)).thenThrow(IOException.class);

        assertThrows(
                ResponseStatusException.class,
                () -> characterAppearanceService.getCharacterAppearanceData(characterName));
    }

    @Test
    void getCharacterAppearanceData_InterruptedException_failed() throws IOException, InterruptedException {
        // GIVEN
        String characterName = "Rick Sanchez";
        // WHEN
        when(rickAndMortyApiAdapter.getCharacters(characterName)).thenThrow(InterruptedException.class);

        assertThrows(
                ResponseStatusException.class,
                () -> characterAppearanceService.getCharacterAppearanceData(characterName));
    }

}
