package interview.utils.utilities;

import java.util.Objects;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe utilitaria para geracao aleatoria de numeros validos de CPF e CNPJ.
 *
 */
public final class CpfCnpjUtils {

    /**
     * Logger da classe
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(CpfCnpjUtils.class);

    /**
     * Constantes
     */
    private static final String MASK_CPNJ = "%d%d.%d%d%d.%d%d%d/%d%d%d%d-%d%d";
    private static final String MASK_CNPJ_CLEANED = "%d%d%d%d%d%d%d%d%d%d%d%d%d%d";
    private static final String MASK_CPF = "%d%d%d.%d%d%d.%d%d%d-%d%d";
    private static final String MASK_CPF_CLEANED = "%d%d%d%d%d%d%d%d%d%d%d";

    private static final String MASK_RENAVAM = "%d%d%d.%d%d%d.%d%d%d-%d%d";
    private static final String MASK_RENAVAM_CLEANED = "%d%d%d%d%d%d%d%d%d%d%d";
    /**
     * Gerador de numeros aleatorios
     */
    private static Random randomNumberGenerator = new Random();

    /**
     * metodo privado para evitar instanciacao de objetos de classe Util
     */
    private CpfCnpjUtils() {
        super();
    }

    /**
     * Gera um numero de CNPJ randomico com ou sem mascara
     *
     * <pre>
     * 	-----------------------------------------------------------------
     * 	 _____                    _              _____ _   _______  ___
     * |  __ \                  | |            /  __ \ \ | | ___ \|_  |
     * | |  \/ ___ _ __ __ _  __| | ___  _ __  | /  \/  \| | |_/ /  | |
     * | | __ / _ \ '__/ _` |/ _` |/ _ \| '__| | |   | . ` |  __/   | |
     * | |_\ \  __/ | | (_| | (_| | (_) | |    | \__/\ |\  | |  /\__/ /
     *  \____/\___|_|  \__,_|\__,_|\___/|_|     \____|_| \_|_|  \____/
     * -----------------------------------------------------------------
     * </pre>
     *
     * @param masked
     *            flag de geracao da mascara com pontos e tracos
     * @return
     */
    public static final String generateCNPJ(final Boolean masked) {
        int digito1, digito2, resto;
        final String result;

        LOGGER.trace("generating random valid CNPJ [masked: {}]", masked);
        Objects.requireNonNull(masked);

        // numeros gerados
        int n1 = randomNumberGenerator.nextInt(10);
        int n2 = randomNumberGenerator.nextInt(10);
        int n3 = randomNumberGenerator.nextInt(10);
        int n4 = randomNumberGenerator.nextInt(10);
        int n5 = randomNumberGenerator.nextInt(10);
        int n6 = randomNumberGenerator.nextInt(10);
        int n7 = randomNumberGenerator.nextInt(10);
        int n8 = randomNumberGenerator.nextInt(10);
        int n9 = randomNumberGenerator.nextInt(10);
        int n10 = randomNumberGenerator.nextInt(10);
        int n11 = randomNumberGenerator.nextInt(10);
        int n12 = randomNumberGenerator.nextInt(10);
        int soma = n12 * 2 + n11 * 3 + n10 * 4 + n9 * 5 + n8 * 6 + n7 * 7 + n6
                * 8 + n5 * 9 + n4 * 2 + n3 * 3 + n2 * 4 + n1 * 5;
        int valor = (soma / 11) * 11;
        digito1 = soma - valor;
        // Primeiro resto da divisao por 11.
        resto = (digito1 % 11);
        if (digito1 < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }
        int soma2 = digito1 * 2 + n12 * 3 + n11 * 4 + n10 * 5 + n9 * 6 + n8 * 7
                + n7 * 8 + n6 * 9 + n5 * 2 + n4 * 3 + n3 * 4 + n2 * 5 + n1 * 6;
        int valor2 = (soma2 / 11) * 11;
        digito2 = soma2 - valor2;
        // Primeiro resto da divisao por 11.
        resto = (digito2 % 11);
        if (digito2 < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }

        // Conctenando os numeros
        result = String.format(masked ? MASK_CPNJ : MASK_CNPJ_CLEANED, n1, n2,
                n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, digito1, digito2);

        LOGGER.debug(
                "random valid CNPJ generated successful [masked: {}, result: {}]",
                masked, result);
        return result;
    }

    /**
     * Gera um numero de CPJ randomico com ou sem mascara.
     *
     * <pre>
     * ------------------------------------------------------------------
     * 	 _____                    _              _________________
     * 	 |  __ \                  | |            /  __ \ ___ \  ___|
     * 	 | |  \/ ___ _ __ __ _  __| | ___  _ __  | /  \/ |_/ / |_
     * 	 | | __ / _ \ '__/ _` |/ _` |/ _ \| '__| | |   |  __/|  _|
     * 	 | |_\ \  __/ | | (_| | (_| | (_) | |    | \__/\ |   | |
     * 	  \____/\___|_|  \__,_|\__,_|\___/|_|     \____|_|   \_|
     * ------------------------------------------------------------------
     * </pre>
     *
     * @param masked
     *            flag de geracao da mascara com pontos e tracos
     * @return
     */
    public static final String generateCPF(final Boolean masked) {
        int digito1, digito2, resto;
        final String result;

        LOGGER.trace("generating random valid CPF [masked: {}]", masked);
        Objects.requireNonNull(masked);

        // numeros gerados
        int n1 = randomNumberGenerator.nextInt(10);
        int n2 = randomNumberGenerator.nextInt(10);
        int n3 = randomNumberGenerator.nextInt(10);
        int n4 = randomNumberGenerator.nextInt(10);
        int n5 = randomNumberGenerator.nextInt(10);
        int n6 = randomNumberGenerator.nextInt(10);
        int n7 = randomNumberGenerator.nextInt(10);
        int n8 = randomNumberGenerator.nextInt(10);
        int n9 = randomNumberGenerator.nextInt(10);
        int soma = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8
                + n2 * 9 + n1 * 10;
        int valor = (soma / 11) * 11;
        digito1 = soma - valor;
        // Primeiro resto da divisao por 11.
        resto = (digito1 % 11);
        if (digito1 < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }
        int soma2 = digito1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7
                + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;
        int valor2 = (soma2 / 11) * 11;
        digito2 = soma2 - valor2;
        // Primeiro resto da divisao por 11.
        resto = (digito2 % 11);
        if (digito2 < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }

        // Conctenando os numeros
        result = String.format(masked ? MASK_CPF : MASK_CPF_CLEANED, n1, n2,
                n3, n4, n5, n6, n7, n8, n9, digito1, digito2);

        LOGGER.debug(
                "random valid CPF generated successful [masked: {}, result: {}]",
                masked, result);
        return result;
    }// fim do metodo geraCPF

    public static String generateRenavam(boolean masked) {
        int digito = 0;
        int n1 = randomNumberGenerator.nextInt(8);
        int n2 = randomNumberGenerator.nextInt(8);
        int n3 = randomNumberGenerator.nextInt(8);
        int n4 = randomNumberGenerator.nextInt(8);
        int n5 = randomNumberGenerator.nextInt(8);
        int n6 = randomNumberGenerator.nextInt(8);
        int n7 = randomNumberGenerator.nextInt(8);
        int n8 = randomNumberGenerator.nextInt(8);
        int n9 = randomNumberGenerator.nextInt(8);
        int n10 = randomNumberGenerator.nextInt(8);
        int soma = (n1 * 2) + (n2 * 3) + (n3 * 4) + (n4 * 5) + (n5 * 6)
                + (n6 * 7) + (n7 * 8) + (n8 * 9) + (n9 * 2) + (n10 * 3);
        int mod = soma % 11;
        digito = mod > 10 ? 0 : 11 - mod;
        return String.format(masked ? MASK_RENAVAM : MASK_RENAVAM_CLEANED, n1,
                n2, n3, n4, n5, n6, n7, n8, n9, n10, digito);
    }


    public static String geraNumeroRenavamValido() {
        Random randomizador = new Random();
        String renavamGeradoAleatoriamente = "";
        for (int i = 0; i < 10; i++) {
            renavamGeradoAleatoriamente += Math.abs(randomizador.nextInt(8));
        }

        String renavamSemDigito = renavamGeradoAleatoriamente.substring(0, 10);
        String renavamReversoSemDigito = new StringBuffer(renavamSemDigito)
                .reverse().toString();

        int soma = 0;
        for (int i = 0; i < 8; i++) {
            Integer algarismo = Integer.parseInt(renavamReversoSemDigito
                    .substring(i, i + 1));
            Integer multiplicador = i + 2;
            soma += algarismo * multiplicador;
        }
        soma += Character.getNumericValue(renavamReversoSemDigito.charAt(8)) * 2;
        soma += Character.getNumericValue(renavamReversoSemDigito.charAt(9)) * 3;
        int mod11 = soma % 11;
        int ultimoDigitoCalculado = 11 - mod11;
        ultimoDigitoCalculado = (ultimoDigitoCalculado >= 10 ? 0
                : ultimoDigitoCalculado);
        return renavamGeradoAleatoriamente + ultimoDigitoCalculado;
    }

    // Gerador de PIS
    public static String generatePIS() {
        Random r = new Random();
        int num = 0;
        String numero = "";
        for (int i = 0; i<10; i++){
            num = r.nextInt(9);
            numero += Integer.toString(num);
        }
        int a = Integer.parseInt(String.valueOf(numero.charAt(0)));
        int b = Integer.parseInt(String.valueOf(numero.charAt(1)));
        int c = Integer.parseInt(String.valueOf(numero.charAt(2)));
        int d = Integer.parseInt(String.valueOf(numero.charAt(3)));
        int e = Integer.parseInt(String.valueOf(numero.charAt(4)));
        int f = Integer.parseInt(String.valueOf(numero.charAt(5)));
        int g = Integer.parseInt(String.valueOf(numero.charAt(6)));
        int h = Integer.parseInt(String.valueOf(numero.charAt(7)));
        int i = Integer.parseInt(String.valueOf(numero.charAt(8)));
        int j = Integer.parseInt(String.valueOf(numero.charAt(9)));
        System.out.println(numero);
        int soma = (3*a) + (2*b) + (9*c) + (8*d) + (7*e) + (6*f) + (5*g) + (4*h) + (3*i) + (2*j);
        //int soma = (3*1) + (2*1) + (9*1) + (8*1) + (7*1) + (6*1) + (5*1) + (4*1) + (3*1) + (2*1);
        int resto = soma%11;
        int digitoVerificador = 11-resto;
        if (digitoVerificador == 10 || digitoVerificador == 11)
            digitoVerificador = 0;

        numero += String.valueOf(digitoVerificador);
        // Conctenando os numeros
        //numero = String.format(masked ? MASK_PIS : MASK_PIS_CLEANED, a, b, c, d, e, f, g, h, i, j);

        LOGGER.debug("random valid PIS generated successful [ result: {}]", numero);

        return numero;
    }

}
