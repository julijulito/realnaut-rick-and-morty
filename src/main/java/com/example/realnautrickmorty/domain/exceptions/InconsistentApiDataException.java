package com.example.realnautrickmorty.domain.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class InconsistentApiDataException extends RuntimeException {

    public InconsistentApiDataException() {
        super("API data is not consistent");
    }
}
