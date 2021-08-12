package co.com.mercadolibre.gustavorealpe.mutantesApi.service;

import co.com.mercadolibre.gustavorealpe.mutantesApi.domain.Dna;
import co.com.mercadolibre.gustavorealpe.mutantesApi.exception.NotMutantFound;
import co.com.mercadolibre.gustavorealpe.mutantesApi.repository.DnaRepository;
import co.com.mercadolibre.gustavorealpe.mutantesApi.util.MutantUtil;
import co.com.mercadolibre.gustavorealpe.mutantesApi.web.DTO.DnaRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DnaService {

    @Autowired
    private DnaValidations dnaValidations;
    @Autowired
    private DnaRepository repository;

    public void process(DnaRequestDTO data) {
        // Normalizar la data
        String[] dna = MutantUtil.normalization(data.getDna());
        // Validaciones a Dna
        dnaValidations.validateMutant(dna);
        // Si el dna es menor a 4x4 se considera que no es mutante
        if (dna.length <= 3) {
            throw new NotMutantFound();
        }
        // Valida si es Mutante
        boolean isMutant = MutantProcessor.isMutant(dna);

        saveInDb(dna, isMutant);

        if (!isMutant)
            throw new NotMutantFound();
    }

    @Transactional
    private void saveInDb(String[] dna, boolean isMutant) {
        Dna entity = new Dna();
        entity.setDna(String.join(",", dna));
        entity.setMutant(isMutant);
        repository.save(entity);
    }
}
