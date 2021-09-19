import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class Generation {
    private static final byte[] key = new byte[16];
    private static final SecureRandom random = new SecureRandom();
    private static String message;
    private static byte[] HMAC;
    private static String[] headers = null;

    private Generation() {
    }

    public static String getHMAC(String[] args) {
        headers = args;
        setHMAC();
        return Hex.encodeHexString(HMAC);
    }

    private static void setHMAC() {
        setMessage();
        setKey();
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(key, "HmacSHA256"));
            HMAC = mac.doFinal(message.getBytes());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    private static void setMessage() {
        message = headers[random.nextInt(headers.length)];
    }

    public static String getMessage() {
        return message;
    }

    public static int geCompMove() {
        return Arrays.asList(headers).indexOf(message);
    }

    private static void setKey() {
        random.nextBytes(key);
    }

    public static String getKey() {
        return Hex.encodeHexString(key);
    }
}
