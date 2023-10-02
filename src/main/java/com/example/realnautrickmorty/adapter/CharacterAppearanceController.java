package com.example.realnautrickmorty.adapter;

import com.example.realnautrickmorty.application.ports.CharacterAppearanceService;
import com.example.realnautrickmorty.domain.error.BaseErrorResponse;
import com.example.realnautrickmorty.domain.rest.CharacterAppearanceData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RestController
@AllArgsConstructor
public class CharacterAppearanceController {

    private static final String CHARACTER_APPEARANCE_ENDPOINT = "/search-character-appearance";

    final CharacterAppearanceService characterAppearanceService;

    @GetMapping(CHARACTER_APPEARANCE_ENDPOINT)
    @Operation(
            summary = "Get Rick & Morty character appearance of the TV show by the list of episodes in which appears" +
                    "and the first date appearance of the character",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CharacterAppearanceData.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400", description = "Bad Request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BaseErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404", description = "Character not Found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BaseErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500", description = "Internal Server Error",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BaseErrorResponse.class)
                            )
                    )
            }
    )
    public CharacterAppearanceData getCharacterAppearanceData(@RequestParam("name") final String characterName) {

        log.info(String.format("Received GET character data for: %s", characterName));
        return characterAppearanceService.getCharacterAppearanceData(normalizeAndValidateCharacterName(characterName));
    }

    private static String normalizeAndValidateCharacterName(final String characterName) {
        final String characterNameFormat = "^[a-zA-Z]+(\\+[a-zA-Z]+)*";
        final Pattern pattern = Pattern.compile(characterNameFormat);
        Matcher matcher = pattern.matcher(characterName);
        if (!matcher.matches()) {
            log.error(String.format("Character name does not match pattern: %s", characterName));
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Character name %s does not match with expected pattern: %s",
                            characterName,
                            characterNameFormat));
        }
        return characterName.replace("+", " ");
    }

}
