package co.com.mercadolibre.gustavorealpe.mutantesApi.util;

import java.util.Arrays;
import java.util.Locale;

public class MutantUtil {
    public static String[] normalization(String[] data) {
        Arrays.setAll(data, i -> data[i].toUpperCase(Locale.ROOT));
        return data;
    }
}
