package co.com.mercadolibre.gustavorealpe.mutantesApi.web.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DnaRequestDTO {
    private String[] dna;
}
