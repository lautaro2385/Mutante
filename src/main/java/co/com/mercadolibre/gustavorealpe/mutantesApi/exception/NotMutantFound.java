package co.com.mercadolibre.gustavorealpe.mutantesApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotMutantFound extends RuntimeException {
}
