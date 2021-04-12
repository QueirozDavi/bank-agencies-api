package com.bank.agencies.external.v2.api;

import com.bank.agencies.domain.AgencyGatewayResponse;
import com.bank.agencies.domain.StatesEnum;
import com.bank.agencies.domain.dto.AgencyResponseDTO;
import com.bank.agencies.domain.dto.StateResponseDTO;
import com.bank.agencies.external.v2.gateway.AgenciesGatewayV2;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AgenciesGatewayV2Impl implements AgenciesGatewayV2 {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    @Value( "${agencies.service.base.url}" )
    private String baseUrl;

    private final ModelMapper modelMapper;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public AgenciesGatewayV2Impl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Map<String, Object> findAllAgencies(String initialPage, String finalPage) {

        URI apiURI = getApiURI(initialPage, finalPage);
        HttpRequest request = getRequest(apiURI);

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == HttpStatus.OK.value()) {
                JsonNode parent = mapper.readTree(response.body());
                String content = parent.get("value").toString();
                Map<String, Object> result = new HashMap<>();
                StateResponseDTO stateResponseDTO = StateResponseDTO.builder().build();
                stateResponseDTO.setResponse(new ArrayList<>());
                List<AgencyGatewayResponse> agencyGatewayResponses = Arrays.asList(mapper.readValue(content, AgencyGatewayResponse[].class));
                applyingFilterPerStates(result, agencyGatewayResponses);

                return result;
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error when trying get all Agencies from API");
        }

        return null;
    }

    private HttpRequest getRequest(URI apiURI) {
        return HttpRequest.newBuilder()
                .GET()
                .uri(apiURI)
                .build();
    }

    private URI getApiURI(String initialPage, String finalPage) {
        return UriComponentsBuilder
                .fromUriString(baseUrl)
                .queryParam("$format", "json")
                .queryParam("$skip", initialPage)
                .queryParam("$top", finalPage)
                .build().toUri();
    }

    private void applyingFilterPerStates(Map<String, Object> result, List<AgencyGatewayResponse> agencyGatewayResponses) {
        List<AgencyGatewayResponse> tempList;
        for(StatesEnum states : StatesEnum.values()) {
            tempList = agencyGatewayResponses.stream().filter(e -> e.getState().equals(states.getValue()))
                    .collect(Collectors.toList());

            tempList.forEach(t -> {
                result.put(states.getValue(), modelMapper.map(t, AgencyResponseDTO.class));
            });
        }
    }

}
