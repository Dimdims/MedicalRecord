package com.rekam.medis.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * provides utilities for text encoding.
 * e.g. password encryption
 */
public final class HashUtils {

    public static String MD5(String plainText) {
        
        try {
            
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(plainText.getBytes());
            
            return bytesToHex(messageDigest.digest());
            
        } catch (NoSuchAlgorithmException ex) {
            
            ex.printStackTrace();
        }
        
        return null;
    }

    public static String SHA1(String plainText) {
        
        try {
            
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(plainText.getBytes());
            
            return bytesToHex(messageDigest.digest());
            
        } catch (NoSuchAlgorithmException ex) {
            
            ex.printStackTrace();
        }
        
        return null;
    }

    public static String SHA256(String plainText) {
        
        try {
            
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(plainText.getBytes());
            
            return bytesToHex(messageDigest.digest());
            
        } catch (NoSuchAlgorithmException ex) {
            
            ex.printStackTrace();
        }
        
        return null;
    }
    
    public static String bytesToHex(byte[] b) {
        
        char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        StringBuilder buf = new StringBuilder();
        
        for (int j = 0; j < b.length; j++) {
            
            buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
            buf.append(hexDigit[b[j] & 0x0f]);
        }
        
        return buf.toString();
    }
}
