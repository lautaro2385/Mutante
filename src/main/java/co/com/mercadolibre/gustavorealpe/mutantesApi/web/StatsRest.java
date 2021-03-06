package co.com.mercadolibre.gustavorealpe.mutantesApi.web;

import co.com.mercadolibre.gustavorealpe.mutantesApi.service.StatsService;
import co.com.mercadolibre.gustavorealpe.mutantesApi.web.DTO.StatsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stats")
public class StatsRest {
    @Autowired
    private StatsService service;

    @GetMapping
    public StatsDTO getStats(){
        return service.stats();
    }
}
