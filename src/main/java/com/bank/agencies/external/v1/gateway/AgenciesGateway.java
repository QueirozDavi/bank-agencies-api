package com.bank.agencies.external.v1.gateway;

import com.bank.agencies.domain.AgencyGatewayResponse;

import java.util.List;

public interface AgenciesGateway {
    List<AgencyGatewayResponse> findAllAgencies();
}
