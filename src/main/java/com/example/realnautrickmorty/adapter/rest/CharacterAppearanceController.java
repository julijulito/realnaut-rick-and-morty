package com.example.realnautrickmorty.adapter.rest;

import com.example.realnautrickmorty.application.ports.CharacterAppearanceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@Slf4j
@RestController
@AllArgsConstructor
public class CharacterAppearanceController {

    private static final String CHARACTER_APPEARANCE_ENDPOINT = "/search-character-appearance";
    @Autowired
    final CharacterAppearanceService characterAppearanceService;

    @GetMapping(CHARACTER_APPEARANCE_ENDPOINT)
    public ResponseEntity<Object> getCharacterInfo(
            @RequestParam("name") final String characterName) {

        return ResponseEntity.ok(characterAppearanceService.getCharacterInfo(normalizeCharacterName(characterName)));
    }

    private static String normalizeCharacterName(final String characterName) {
        return characterName.replace("+", " ");
    }

}
