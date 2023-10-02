package com.example.realnautrickmorty.domain.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class BaseErrorResponse {
    private final HttpStatusCode status;
    private final String reason;
}
