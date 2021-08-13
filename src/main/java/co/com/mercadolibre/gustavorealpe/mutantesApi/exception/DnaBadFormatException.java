package co.com.mercadolibre.gustavorealpe.mutantesApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DnaBadFormatException extends RuntimeException {
    public DnaBadFormatException(String msg) {
        super(msg);
    }
}
