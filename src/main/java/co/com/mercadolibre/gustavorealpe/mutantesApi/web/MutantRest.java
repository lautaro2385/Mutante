package co.com.mercadolibre.gustavorealpe.mutantesApi.web;

import co.com.mercadolibre.gustavorealpe.mutantesApi.service.DnaService;
import co.com.mercadolibre.gustavorealpe.mutantesApi.web.DTO.DnaRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mutant")
public class MutantRest {

    @Autowired
    private DnaService dnaService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void processDna(@RequestBody DnaRequestDTO dnaRequestDTO){
        dnaService.process(dnaRequestDTO);
    }
}
