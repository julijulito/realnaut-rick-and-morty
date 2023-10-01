package com.example.realnautrickmorty.application.service;

import com.example.realnautrickmorty.adapter.client.RickAndMortyApiAdapter;
import com.example.realnautrickmorty.application.ports.CharacterAppearanceService;
import com.example.realnautrickmorty.domain.client.Episode;
import com.example.realnautrickmorty.domain.client.Result;
import com.example.realnautrickmorty.domain.model.CharacterAppearanceData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class CharacterAppearanceServiceImpl implements CharacterAppearanceService {

    private final RickAndMortyApiAdapter rickAndMortyApiAdapter;

    @Autowired
    public CharacterAppearanceServiceImpl(RickAndMortyApiAdapter rickAndMortyApiAdapter) {
        this.rickAndMortyApiAdapter = rickAndMortyApiAdapter;
    }

    @Override
    public CharacterAppearanceData getCharacterInfo(final String characterName) {
        try {
            final Result result = rickAndMortyApiAdapter.getCharacters(characterName);
            final List<Episode> episodes = result.getEpisode();
            return CharacterAppearanceData.builder()
                    .name(result.getName())
                    .episodes(episodes.stream().map(Episode::getName).toList())
                    .firstAppearance(episodes.stream().findFirst().map(Episode::getAirDate).get())
                    .build();

        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
