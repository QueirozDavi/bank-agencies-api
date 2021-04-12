package com.bank.agencies.external.v2.api;

import com.bank.agencies.domain.AgencyGatewayResponse;
import com.bank.agencies.external.v2.gateway.AgenciesGatewayV2;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgenciesGatewayV2Impl implements AgenciesGatewayV2 {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    @Value( "${agencies.service.base.url}" )
    private String baseUrl;

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<AgencyGatewayResponse> findAllAgencies(String initialPage, String finalPage) {

        URI apiURI = UriComponentsBuilder
                .fromUriString(baseUrl)
                .queryParam("$format", "json")
                .queryParam("$skip", initialPage)
                .queryParam("$top", finalPage)
                .build().toUri();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(apiURI)
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == HttpStatus.OK.value()) {
                JsonNode parent = mapper.readTree(response.body());
                String content = parent.get("value").toString();

                List<AgencyGatewayResponse> agencyGatewayResponses = Arrays.asList(mapper.readValue(content, AgencyGatewayResponse[].class));
                agencyGatewayResponses.forEach( a -> a.getState());

                return Arrays.asList(mapper.readValue(content, AgencyGatewayResponse[].class));
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error when trying get all Agencies from API");
        }

        return Collections.emptyList();
    }

}
