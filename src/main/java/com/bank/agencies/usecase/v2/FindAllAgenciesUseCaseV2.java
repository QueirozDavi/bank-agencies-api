package com.bank.agencies.usecase.v2;

import com.bank.agencies.domain.AgencyGatewayResponse;
import com.bank.agencies.external.v1.gateway.AgenciesGateway;
import com.bank.agencies.external.v2.gateway.AgenciesGatewayV2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllAgenciesUseCaseV2 {

    private final AgenciesGatewayV2 bankResourcesGateway;

    public FindAllAgenciesUseCaseV2(AgenciesGatewayV2 bankResourcesGateway) {
        this.bankResourcesGateway = bankResourcesGateway;
    }

    public List<AgencyGatewayResponse> execute(String initialPage, String finalPage) {
        return bankResourcesGateway.findAllAgencies(initialPage, finalPage);
    }
}
