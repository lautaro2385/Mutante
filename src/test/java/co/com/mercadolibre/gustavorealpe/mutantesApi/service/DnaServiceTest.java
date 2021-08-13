package co.com.mercadolibre.gustavorealpe.mutantesApi.service;

import co.com.mercadolibre.gustavorealpe.mutantesApi.domain.Dna;
import co.com.mercadolibre.gustavorealpe.mutantesApi.exception.DnaBadFormatException;
import co.com.mercadolibre.gustavorealpe.mutantesApi.exception.NotMutantFoundException;
import co.com.mercadolibre.gustavorealpe.mutantesApi.repository.DnaRepository;
import co.com.mercadolibre.gustavorealpe.mutantesApi.web.DTO.DnaRequestDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@SpringBootTest
class DnaServiceTest {

    @Autowired
    DnaService dnaService;

    @MockBean
    private DnaRepository repository;

    private void ValidateSaveDataInDb(String[] dna, boolean isMutant){
        Dna entity = new Dna();
        entity.setDna(String.join(",", dna));
        entity.setMutant(isMutant);
        Mockito.verify(repository, times(1)).save(entity);
    }

    @Test
    void Should_BeHuman_IfDnaSizeIsLessThan4() {
        final String[] dna = {"ATG", "CAG", "TTA"};
        DnaRequestDTO requestDTO = new DnaRequestDTO(dna);
        assertThrows(NotMutantFoundException.class, () -> {
            dnaService.process(requestDTO);
            ValidateSaveDataInDb(dna, false);
        });
    }

    @Test
    void Should_BeMutant() {
        final String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "TACCCC", "TCACTG"};
        DnaRequestDTO requestDTO = new DnaRequestDTO(dna);
        dnaService.process(requestDTO);
        ValidateSaveDataInDb(dna, true);
    }

    @Test
    void Should_BeMutant_WithLowerCase() {
        final String[] dna = {"atgcga", "cagtgc", "ttatgt", "agaagg", "ccccta", "tcactg"};
        DnaRequestDTO requestDTO = new DnaRequestDTO(dna);
        dnaService.process(requestDTO);
        ValidateSaveDataInDb(dna, true);
    }

    @Test
    void Should_BeMutant_Horizontal() {
        final String[] dna = {"AAAATG", "CAGTCG", "TTATTG", "AGAAGG", "CCCCTA", "TCACTG"};
        DnaRequestDTO requestDTO = new DnaRequestDTO(dna);
        dnaService.process(requestDTO);
        ValidateSaveDataInDb(dna, true);
    }

    @Test
    void Should_BeMutant_Oblique() {
        final String[] dna = {"AAGAAT", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        DnaRequestDTO requestDTO = new DnaRequestDTO(dna);
        dnaService.process(requestDTO);
        ValidateSaveDataInDb(dna, true);
    }

    @Test
    void Should_BeHuman() {
        final String[] dna = {"ATGCGA", "CGGTGC", "TTATGT", "AGAAGG", "CCGCTA", "TCACTG"};
        DnaRequestDTO requestDTO = new DnaRequestDTO(dna);
        assertThrows(NotMutantFoundException.class, () -> {
            dnaService.process(requestDTO);
        });
        ValidateSaveDataInDb(dna, false);
    }

    @Test
    void Should_ThrowException_When_DnaIsEmptyArray() {
        DnaRequestDTO requestDTO = new DnaRequestDTO(new String[]{});
        Exception exception = assertThrows(DnaBadFormatException.class, () -> {
            dnaService.process(requestDTO);
        });

        assertEquals("Dna should not be empty", exception.getMessage());
    }

    @Test
    void Should_ThrowException_When_DnaIsNotNxNArray() {
        final String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACT"};
        DnaRequestDTO requestDTO = new DnaRequestDTO(dna);
        Exception exception = assertThrows(DnaBadFormatException.class, () -> {
            dnaService.process(requestDTO);
        });

        assertEquals("Dna Should be a square matrix", exception.getMessage());
    }

    @Test
    void Should_ThrowException_When_DnaIsNotATCGArray() {
        final String[] dna = {"ATGCGR", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        DnaRequestDTO requestDTO = new DnaRequestDTO(dna);
        Exception exception = assertThrows(DnaBadFormatException.class, () -> {
            dnaService.process(requestDTO);
        });

        assertEquals("Only the letters T, A, C, G are accepted.", exception.getMessage());
    }
}