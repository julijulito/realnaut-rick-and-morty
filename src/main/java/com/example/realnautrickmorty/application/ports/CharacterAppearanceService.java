package com.example.realnautrickmorty.application.ports;

import com.example.realnautrickmorty.domain.rest.CharacterAppearanceData;
import org.springframework.stereotype.Service;

@Service
public interface CharacterAppearanceService {

    CharacterAppearanceData getCharacterAppearanceData(final String characterName);

}
