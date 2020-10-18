package com.clbee.appmaker.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAPasswordEncoder implements PasswordEncoder {

    public static String crypt(String str){
        String encoded = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes());
            byte byteData[] = md.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0 ; i < byteData.length ; i++){
                sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
            }
            encoded = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            encoded = null;
        }
        return encoded;
    }

    @Override public String encode(CharSequence raw) {
        return crypt(raw.toString());
    }
    @Override public boolean matches(CharSequence raw, String encoded) {
        return crypt(raw.toString()).equals(encoded);
    }
}