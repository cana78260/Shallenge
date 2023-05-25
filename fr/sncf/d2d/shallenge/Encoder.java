package fr.sncf.d2d.shallenge;

import java.util.Formatter;

public class Encoder {
    public static String toHex(byte[] data){
StringBuilder sb = new StringBuilder(data.length * 2);
try (Formatter formatter = new Formatter(sb)) {
    for (byte b : data){
        formatter.format("%02x", b);
    }
}
return sb.toString();
    }
}
