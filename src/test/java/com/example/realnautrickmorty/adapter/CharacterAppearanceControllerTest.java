package com.example.realnautrickmorty.adapter;


import com.example.realnautrickmorty.application.ports.CharacterAppearanceService;
import com.example.realnautrickmorty.domain.rest.CharacterAppearanceData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CharacterAppearanceController.class)
@ExtendWith(MockitoExtension.class)
public class CharacterAppearanceControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private CharacterAppearanceService characterAppearanceService;

    @InjectMocks
    private CharacterAppearanceController controller;

    @BeforeEach
    public void setUp() {
        this.controller = new CharacterAppearanceController(characterAppearanceService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    void getCharacterAppearanceData_success() throws Exception {
        // GIVEN
        String characterName = "Rick+Sanchez";
        String normalizedCharacterName = "Rick Sanchez";
        CharacterAppearanceData data = CharacterAppearanceData
                .builder()
                .name(normalizedCharacterName)
                .firstAppearance("2017-01-01")
                .episodes(List.of("Episode1", "Episode2"))
                .build();
        when(characterAppearanceService.getCharacterAppearanceData((normalizedCharacterName))).thenReturn(data);

        // WHEN
        mockMvc.perform(get("/search-character-appearance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", characterName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(normalizedCharacterName))
                .andExpect(jsonPath("$.episodes", containsInAnyOrder(data.getEpisodes().toArray())))
                .andExpect(jsonPath("$.first_appearance").value(data.getFirstAppearance()));
    }

    @Test
    void getCharacterAppearanceData_wrongNameFormat_success() throws Exception {
        // GIVEN
        String normalizedCharacterName = "Rick Sanchez";

        // WHEN
        mockMvc.perform(get("/search-character-appearance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", normalizedCharacterName))
                .andExpect(status().is4xxClientError());
    }
}
