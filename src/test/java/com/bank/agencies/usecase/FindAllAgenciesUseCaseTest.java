package com.bank.agencies.usecase;

import com.bank.agencies.domain.AgencyGatewayResponse;
import com.bank.agencies.external.v1.gateway.AgenciesGateway;
import com.bank.agencies.fixture.AgenciesFixture;
import com.bank.agencies.usecase.v1.FindAllAgenciesUseCase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class FindAllAgenciesUseCaseTest {

    final AgenciesGateway gateway = mock(AgenciesGateway.class);
    final FindAllAgenciesUseCase findAllAgencies = new FindAllAgenciesUseCase(gateway);

    List<AgencyGatewayResponse> agencies;

    @Test
    void shouldExecuteUseCaseFindAllBBAgenciesWithSuccess() {
        // given
        agencies = AgenciesFixture.gimmeBasicAgencies();
        when(findAllAgencies.execute()).thenReturn(agencies);

        // when
        final List<AgencyGatewayResponse> response = findAllAgencies.execute();

        // then
        verify(gateway, times(1)).findAllAgencies();

        assertNotNull(response);
        assertNotNull(response.get(0));
    }
}
