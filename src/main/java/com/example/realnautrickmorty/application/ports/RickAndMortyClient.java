package com.example.realnautrickmorty.application.ports;

import com.example.realnautrickmorty.domain.client.CharacterApiResponse;

import java.io.IOException;

public interface RickAndMortyClient {

    CharacterApiResponse getCharacterAppearanceInfo(String characterName) throws IOException, InterruptedException;
}
