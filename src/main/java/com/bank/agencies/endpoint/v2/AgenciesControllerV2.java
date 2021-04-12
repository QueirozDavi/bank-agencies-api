package com.bank.agencies.endpoint.v2;

import com.bank.agencies.domain.AgencyGatewayResponse;
import com.bank.agencies.domain.AgencyResponse;
import com.bank.agencies.usecase.v1.FindAllAgenciesUseCase;
import com.bank.agencies.usecase.v2.FindAllAgenciesUseCaseV2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v2/agencies", produces = MediaType.APPLICATION_JSON_VALUE)
public class AgenciesControllerV2 {

    private final FindAllAgenciesUseCaseV2 findAllAgenciesUseCase;

    public AgenciesControllerV2(FindAllAgenciesUseCaseV2 findAllAgenciesUseCase) {
        this.findAllAgenciesUseCase = findAllAgenciesUseCase;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AgencyResponse>> findAllAgencies(@RequestParam() String initialPage,
                                                                @RequestParam() String finalPage) {

        List<AgencyGatewayResponse> agencies = findAllAgenciesUseCase.execute(initialPage, finalPage);

        List<AgencyResponse> agencyResponse = agencies.stream()
                .map(agencyGateway -> AgencyResponse.AgencyResponseBuilder.anAgencyResponse()
                        .bank(agencyGateway.getBank())
                        .city(agencyGateway.getCity())
                        .name(agencyGateway.getName())
                        .state(agencyGateway.getState()).build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(agencyResponse, HttpStatus.OK);
    }
}
