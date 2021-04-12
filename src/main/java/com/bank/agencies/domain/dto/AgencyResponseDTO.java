package com.bank.agencies.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgencyResponseDTO {


    @JsonProperty("name")
    private String name;
    @JsonProperty("bank")
    private String bank;
    @JsonProperty("city")
    private String city;

}

