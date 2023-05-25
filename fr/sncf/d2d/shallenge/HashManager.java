package fr.sncf.d2d.shallenge;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;


public class HashManager {
    public static byte[] hashPassword(byte[] salt, byte[] password)throws Exception{
        
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.reset();
        messageDigest.update(salt);
        return messageDigest.digest(password);
    }

//     public static String toHexHash(byte[] data)throws Exception{
//         return Encoder.toHex(toRawHash(data));
//     }

//     public static String toHexHashString(String data)throws Exception{
//         return toHexHash(data.getBytes(StandardCharsets.UTF_8));
//     }
}
