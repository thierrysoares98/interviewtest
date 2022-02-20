package interview.utils.utilities;

import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe utilitaria de criptografia utilizando JCE (Java Criptography Extensions)
 *
 */
public final class CryptoUtils {

    /**
     * Logger da classe
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(CryptoUtils.class);

    /**
     * seed da criptografia - chave simetrica do 3DES
     */
    private static final String SEED = "HdD3$*aO%002klw";

    /**
     *
     */
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * Cache synchronized da cipher
     */
    private static Cipher cipher = null;
    private static Cipher decipher = null;

    /**
     * Metodo construtor privado
     */
    private CryptoUtils() {
    }

    /**
     * Criptografa texto passado e codifica sua exibicao em base64
     *
     * @param plainText texto a ser criptografado
     * @return texto criptografado codificado em base64
     */
    public static final String encryptBase64(final String plainText) {
        final String cipherTextBase64;
        final byte[] plainTextBytes;
        final byte[] cipherTextBytes;

        LOGGER.trace("encrypting and formatting to base64");
        plainTextBytes = plainText.getBytes(DEFAULT_CHARSET);
        cipherTextBytes = encrypt(plainTextBytes);
        cipherTextBase64 = Base64.encodeBase64String(cipherTextBytes);
        LOGGER.debug("encrypted and formatted to base64 successful");
        return cipherTextBase64;
    }

    /**
     * Criptografa buffer passado e codifica sua exibicao em base64
     *
     * @param plainTextBytes buffer a ser criptografado
     * @return dados criptografados codificados em base64
     */
    public static final byte[] encrypt(final byte[] plainTextBytes) {
        final byte[] cipherTextBytes;

        try {
            LOGGER.trace("encrypting byte array using 3DES");
            cipherTextBytes = getCipher().doFinal(plainTextBytes);
            LOGGER.debug("byte array encrypted using 3DES successful");

        } catch (final IllegalBlockSizeException | BadPaddingException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        return cipherTextBytes;
    }

    /**
     * Descriptografa texto a partir de texto criptografado e codificado em base64
     *
     * @param cipherTextBase64 texto criptografado e codificado em base64
     * @return texto puro descriptografado
     */
    public static final String decryptBase64(final String cipherTextBase64) {
        final String plainText;
        final byte[] cipherTextBytes;
        final byte[] plainTextBytes;

        LOGGER.trace("decrypting from formatted base64");
        cipherTextBytes = Base64.decodeBase64(cipherTextBase64);
        plainTextBytes = decrypt(cipherTextBytes);
        plainText = new String(plainTextBytes, DEFAULT_CHARSET);
        LOGGER.debug("decrypted from formatted base64 successful");
        return plainText;
    }

    /**
     * Descriptografa buffer de dados a partir de massa criptografada e codificada em base64
     *
     * @param cipherTextBytes massa criptografada e codificada em base64
     * @return buffer de dados descriptografado
     */
    public static final byte[] decrypt(final byte[] cipherTextBytes) {
        final byte[] plainTextBytes;

        try {
            LOGGER.trace("decrypting using 3DES");
            plainTextBytes = getDecipher().doFinal(cipherTextBytes);
            LOGGER.debug("decrypted using 3DES successful");

        } catch (final IllegalBlockSizeException | BadPaddingException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }

        return plainTextBytes;
    }

    /**
     * Obtem objeto criptografador do JCE
     *
     * @return objeto criptografador do JCE
     */
    private static final Cipher getCipher() {
        if (cipher==null) {
            cipher = buildCipher(Cipher.ENCRYPT_MODE);
        }
        return cipher;
    }

    /**
     * Obtem objeto descriptografador do JCE
     *
     * @return objeto descriptografador do JCE
     */
    private static final Cipher getDecipher() {
        if (decipher==null) {
            decipher = buildCipher(Cipher.DECRYPT_MODE);
        }
        return decipher;
    }

    /**
     * Constroi um objeto criptografador ou descriptografador do JCE
     *
     * @param cipherMode modo Cipher.DECRYPT_MODE ou Cipher.ENCRYPT_MODE
     * @return objeto criptografador ou descriptografador do JCE
     */
    private static final Cipher buildCipher(final int cipherMode) {
        final Cipher cipher;

        try {
            final MessageDigest md;
            final byte[] digestOfPassword;
            final byte[] keyBytes;
            final SecretKey key;
            final IvParameterSpec iv;
            String privateKey = StringUtils.EMPTY;

            LOGGER.trace("building cipher/decipher 3DES [cipherMode: {}]", cipherMode);
            md = MessageDigest.getInstance("md5");

            //Obtem Private Key vinda de variavel do sistema, e caso nao tenha,
            //utiliza a SEED definida pelo framework
            try {
                privateKey = System.getenv("AUTOMATION_PRIVATE_KEY");

            } catch (SecurityException secExcp){
                LOGGER.error("Nao foi possivel obter a variavel de sistema ", secExcp);

            } finally {
                if ((privateKey == null) || (privateKey.isEmpty()))
                    privateKey = SEED;
            }

            digestOfPassword = md.digest(privateKey.getBytes(DEFAULT_CHARSET));
            keyBytes = Arrays.copyOf(digestOfPassword, 24);

            for (int j = 0, k = 16; j < 8;) {
                keyBytes[k++] = keyBytes[j++];
            }

            key = new SecretKeySpec(keyBytes, "DESede");
            iv = new IvParameterSpec(new byte[8]);
            cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            cipher.init(cipherMode, key, iv);//
            LOGGER.debug("cipher/decipher 3DES builded successful [cipherMode: {}]", cipherMode);

        } catch (final NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException
                | InvalidAlgorithmParameterException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        return cipher;
    }

}