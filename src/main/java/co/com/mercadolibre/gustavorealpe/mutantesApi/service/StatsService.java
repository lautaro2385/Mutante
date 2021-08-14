package co.com.mercadolibre.gustavorealpe.mutantesApi.service;

import co.com.mercadolibre.gustavorealpe.mutantesApi.repository.DnaRepository;
import co.com.mercadolibre.gustavorealpe.mutantesApi.web.DTO.StatsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {
    @Autowired
    private DnaRepository repository;

    public StatsDTO stats() {
        final long countMutant = repository.countByMutant(true);
        final long countHuman = repository.countByMutant(false);
        double ratio = 0;
        if (countHuman != 0)
            ratio = countMutant / (double) countHuman;

        return new StatsDTO(countMutant, countHuman, ratio);
    }
}
