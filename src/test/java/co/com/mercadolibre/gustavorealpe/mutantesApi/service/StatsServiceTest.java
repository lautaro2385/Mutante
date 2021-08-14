package co.com.mercadolibre.gustavorealpe.mutantesApi.service;

import co.com.mercadolibre.gustavorealpe.mutantesApi.repository.DnaRepository;
import co.com.mercadolibre.gustavorealpe.mutantesApi.web.DTO.StatsDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class StatsServiceTest {
    @Autowired
    StatsService statsService;
    @MockBean
    DnaRepository repository;

    @Test
    public void Should_ReturnStats() {
        given(repository.countByMutant(true)).willReturn(40L);
        given(repository.countByMutant(false)).willReturn(100L);

        StatsDTO stats = statsService.stats();

        assertEquals(stats.getRatio(), 0.4);
    }

    @Test
    public void Should_ReturnRatioZero_When_HumanIsZero() {
        given(repository.countByMutant(true)).willReturn(0L);
        given(repository.countByMutant(false)).willReturn(0L);

        StatsDTO stats = statsService.stats();

        assertEquals(stats.getRatio(), 0);
    }
}