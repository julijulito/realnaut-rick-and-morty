package com.example.realnautrickmorty.adapter.client;

import com.example.realnautrickmorty.domain.client.RickAndMortyApiResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface RickAndMortyClient {

    RickAndMortyApiResponse getCharacterAppearanceInfo(String characterName) throws IOException, InterruptedException;

}
