package com.example.realnautrickmorty.adapter.client;

import com.example.realnautrickmorty.application.ports.RickAndMortyClient;
import com.example.realnautrickmorty.domain.client.Result;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
@AllArgsConstructor
public class RickAndMortyApiAdapter {

    @Autowired
    private final RickAndMortyClient rickAndMortyClient;

    public Result getCharacters(final String characterName) throws IOException, InterruptedException {
        return rickAndMortyClient.getCharacterAppearanceInfo(characterName)
                .getData()
                .getCharacters()
                .getResults()
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Character: %s not Found", characterName)));
    }

}
