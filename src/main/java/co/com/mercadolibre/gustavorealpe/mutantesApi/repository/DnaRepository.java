package co.com.mercadolibre.gustavorealpe.mutantesApi.repository;

import co.com.mercadolibre.gustavorealpe.mutantesApi.domain.Dna;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
@EnableScanCount
public interface DnaRepository extends CrudRepository<Dna, String> {
    Optional<Dna> findById(String id);

    long countByMutant(Boolean isMutant);
}
