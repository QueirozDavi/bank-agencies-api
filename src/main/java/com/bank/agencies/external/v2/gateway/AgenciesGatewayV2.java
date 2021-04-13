package com.bank.agencies.external.v2.gateway;

import java.util.Map;

public interface AgenciesGatewayV2 {
    Map<String, Object> findAllAgencies(int initialPage, int finalPage);
}
