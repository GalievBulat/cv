package Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TextEncoding {
    public String encodeText(String text){
        try {
            byte[] bytes = text.getBytes();
            byte[] res= MessageDigest.getInstance("SHA").digest(bytes);
            return new String(res);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
