package com.example.realnautrickmorty.domain.client;

import lombok.Data;

import java.util.List;

@Data
public class Result {

    private String name;

    private List<Episode> episode;
}