package com.bank.agencies.external.v2.gateway;

import com.bank.agencies.domain.AgencyGatewayResponse;

import java.util.List;

public interface AgenciesGatewayV2 {
    List<AgencyGatewayResponse> findAllAgencies(String initialPage, String finalPage);
}
