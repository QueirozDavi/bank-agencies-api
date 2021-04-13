package com.bank.agencies.endpoint.v2;

import com.bank.agencies.usecase.v2.FindAllAgenciesUseCaseV2;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/v2/agencies", produces = MediaType.APPLICATION_JSON_VALUE)
public class AgenciesControllerV2 {

    private final FindAllAgenciesUseCaseV2 findAllAgenciesUseCase;

    private final ObjectMapper objectMapper;

    @Autowired
    public AgenciesControllerV2(FindAllAgenciesUseCaseV2 findAllAgenciesUseCase, ObjectMapper objectMapper) {
        this.findAllAgenciesUseCase = findAllAgenciesUseCase;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public JsonNode findAllAgencies(@RequestParam() int cityIndex,
                                                                @RequestParam() int qty) {

        Map<String, Object> response = findAllAgenciesUseCase.execute(cityIndex, qty);

        return objectMapper.convertValue(response, ObjectNode.class);

//        return new ResponseEntity<>(agencyResponse, HttpStatus.OK);
    }
}
