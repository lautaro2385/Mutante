package co.com.mercadolibre.gustavorealpe.mutantesApi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class MutantProcessor {
    public static final byte MUTANT_SEQUENCE = 2;

    /**
     * Valida si la secuencia de Dna pertenece a un mutante
     * @param dna valores de dna
     * @return si es o no mutante
     */
    public static boolean isMutant(String[] dna) {
        int countMutant = 0;

        BiFunction<Integer, Integer, Character> fHor = (x, y) -> dna[x].charAt(y);
        BiFunction<Integer, Integer, Character> fVer = (x, y) -> dna[y].charAt(x);

        // Horizontal validation
        countMutant += hasMutantSequence(dna, fHor, countMutant);
        if (countMutant >= MUTANT_SEQUENCE)
            return true;
        // Vertical Validation
        countMutant += hasMutantSequence(dna, fVer, countMutant);
        if (countMutant >= MUTANT_SEQUENCE)
            return true;
        // Oblique validation
        String[] dnaD = diagonalMatrix(dna);
        countMutant += hasMutantSequence(dnaD, fHor, countMutant);
        return countMutant >= MUTANT_SEQUENCE;
    }

    /**
     * Valida si hay alguna secuencia mutante de vorma horizontal o vertical
     * @param dna valores de dna
     * @param func como se obtienen los datos
     * @param prevCountMutant contador de mutantes
     * @return cantidad de secuancias encontradas
     */
    private static int hasMutantSequence(String[] dna, BiFunction<Integer, Integer, Character> func, int prevCountMutant) {
        final int len = dna[0].length();
        int countMutant = 0;
        char lastChar;
        int count;
        for (int x = 0; x < len; x++) {
            lastChar = ' ';
            count = 0;
            for (int y = 0; y < len; y++) {
                char actChar = func.apply(x, y);
                if (lastChar != actChar) {
                    if (count == 4) {
                        if((++countMutant + prevCountMutant) >= MUTANT_SEQUENCE){
                            return countMutant;
                        }
                    }
                    count = 1;
                    lastChar = actChar;
                } else {
                    count++;
                }
            }
            if (count == 4) {
                if((++countMutant + prevCountMutant) >= MUTANT_SEQUENCE){
                    return countMutant;
                }
            }
        }
        return countMutant;
    }

    /**
     * Vuelve a una matriz horizontal las diagonales de la matriz
     * @param dna valores de dna
     * @return matrix horizontal
     */
    private static String[] diagonalMatrix(String[] dna) {
        List<String> ac = new ArrayList<>(dna.length);
        final int delta = dna.length - 4;
        final int len = dna[0].length();
        final int lenMinus1 = len - 1;
        for (int x = 0; x <= delta; x++) {
            StringBuilder sbX = new StringBuilder(len);
            StringBuilder sbXI = new StringBuilder(len);
            StringBuilder sbY = new StringBuilder(len);
            StringBuilder sbYI = new StringBuilder(len);
            for (int y = 0; y < len - x; y++) {
                sbX.append(dna[x + y].charAt(y));
                sbXI.append(dna[x + y].charAt(lenMinus1 - y));
                if (x != 0) {
                    sbY.append(dna[y].charAt(y + x));
                    sbYI.append(dna[lenMinus1 - y].charAt(x + y));
                }
            }
            ac.add(sbX.toString());
            ac.add(sbXI.toString());
            if (x != 0) {
                ac.add(sbY.toString());
                ac.add(sbYI.toString());
            }
        }
        return ac.toArray(new String[0]);
    }
}
