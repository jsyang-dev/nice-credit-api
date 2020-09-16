package link.credit.common.util;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * 암호화 모듈
 *
 * <p>Create by jsyang on 2020/09/03
 */
public class Crypto {

  private static final String TRANS = "AES/CBC/PKCS5Padding";
  private static final String ALGO = "AES";

  public static String encrypt(String plainData, String keyStr, String ivStr) throws Exception {

    SecretKeySpec key = new SecretKeySpec(keyStr.getBytes(), ALGO);
    IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes());

    Cipher cipher = Cipher.getInstance(TRANS);
    cipher.init(Cipher.ENCRYPT_MODE, key, iv);

    byte[] encrypted = cipher.doFinal(plainData.getBytes(StandardCharsets.UTF_8));
    return Base64.encodeBase64String(encrypted);
  }

  public static String decrypt(String encData, String keyStr, String ivStr) {

    SecretKeySpec key = new SecretKeySpec(keyStr.getBytes(), ALGO);
    IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes());

    try {
      Cipher cipher = Cipher.getInstance(TRANS);
      cipher.init(Cipher.DECRYPT_MODE, key, iv);
      return new String(cipher.doFinal(Base64.decodeBase64(encData)));

    } catch (Exception e) {
      System.out.println("Error while decrypting: " + e.toString());
    }

    return null;
  }
}
