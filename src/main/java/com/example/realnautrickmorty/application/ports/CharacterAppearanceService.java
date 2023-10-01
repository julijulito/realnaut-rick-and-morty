package com.example.realnautrickmorty.application.ports;

import com.example.realnautrickmorty.domain.model.CharacterAppearanceData;

public interface CharacterAppearanceService {

    CharacterAppearanceData getCharacterInfo(final String characterName);
}
