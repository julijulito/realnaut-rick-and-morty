package com.example.realnautrickmorty.application.service;

import com.example.realnautrickmorty.adapter.RickAndMortyApiAdapter;
import com.example.realnautrickmorty.application.ports.CharacterAppearanceService;
import com.example.realnautrickmorty.domain.client.Episode;
import com.example.realnautrickmorty.domain.client.Result;
import com.example.realnautrickmorty.domain.exceptions.InconsistentApiDataException;
import com.example.realnautrickmorty.domain.rest.CharacterAppearanceData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CharacterAppearanceServiceImpl implements CharacterAppearanceService {

    private final RickAndMortyApiAdapter rickAndMortyApiAdapter;

    @Override
    public CharacterAppearanceData getCharacterAppearanceData(final String characterName) {
        try {
            final Result result = rickAndMortyApiAdapter.getCharacters(characterName);
            final List<Episode> episodes = result.getEpisode();
            return CharacterAppearanceData.builder()
                    .name(result.getName())
                    .episodes(episodes.stream().map(Episode::getName).toList())
                    .firstAppearance(getFirstAppearanceOrThrown(episodes))
                    .build();

        } catch (IOException | InterruptedException | InconsistentApiDataException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static String getFirstAppearanceOrThrown(List<Episode> episodes) {
        return episodes.stream()
                .findFirst()
                .map(Episode::getAirDate)
                .orElseThrow(() -> new InconsistentApiDataException());
    }
}
