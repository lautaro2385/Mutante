package co.com.mercadolibre.gustavorealpe.mutantesApi.service;

import co.com.mercadolibre.gustavorealpe.mutantesApi.exception.DnaBadFormat;
import co.com.mercadolibre.gustavorealpe.mutantesApi.web.DTO.DnaRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

@Component
public class DnaValidations {

    private static final Pattern patt = Pattern.compile("([^ATGC])");
    // Validate not an empy array
    private static final Validator emptyArray = new Validator(
            (dna) -> dna.length <= 0,
            "Dna should not be empty");
    // Validate a nxn array
    private static final Validator squareArray = new Validator(
            (dna) -> !Arrays.stream(dna).allMatch(p -> p.length() == dna.length),
            "Dna Should be a square matrix");
    //just allowed  T, A, C, G
    private static final Validator ATCGArray = new Validator(
            (dna) -> Arrays.stream(dna).anyMatch(p -> patt.matcher(p).find()),
            "Only the letters T, A, C, G are accepted.");

    private final List<Validator> validations;

    private DnaValidations() {
        validations = new ArrayList<>();
        validations.add(emptyArray);
        validations.add(squareArray);
        validations.add(ATCGArray);
    }

    public void validateMutant(String[] dna) {
        for (Validator validation : validations) {
            if (validation.fnValidator.test(dna)) {
                throw new DnaBadFormat(validation.Msg);
            }
        }
    }

    @AllArgsConstructor
    private static class Validator{
        private Predicate<String[]> fnValidator;
        private String Msg;
    }
}
