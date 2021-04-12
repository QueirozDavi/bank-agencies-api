package com.bank.agencies.config;

import com.bank.agencies.domain.AgencyGatewayResponse;
import com.bank.agencies.domain.dto.AgencyResponseDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(getMapAgencyGatewayResponseToAgencyResponseDTO(), AgencyGatewayResponse.class, 
                AgencyResponseDTO.class);
        return modelMapper;
    }

    private Converter<AgencyGatewayResponse, AgencyResponseDTO> getMapAgencyGatewayResponseToAgencyResponseDTO() {

        return context -> AgencyResponseDTO.builder()
                .city(context.getSource().getCity())
                .name(context.getSource().getName())
                .bank(context.getSource().getBank())
                .build();
    }
}
