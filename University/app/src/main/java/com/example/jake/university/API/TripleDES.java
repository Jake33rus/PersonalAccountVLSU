package com.example.jake.university.API;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class TripleDES {

    //Метод для шифрования
    public String Encrypter(String skey,String sdata, String siv) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        //MessageDigest md = MessageDigest.getInstance("md5");
        byte[] keyBytes= skey.getBytes("utf-8");
        //byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

        SecretKey key = new SecretKeySpec(keyBytes, "DESede");
        IvParameterSpec iv = new IvParameterSpec(siv.getBytes("utf-8"));
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        byte[] dataBytes = sdata.getBytes("utf-8");
        byte[] cipherText = cipher.doFinal(dataBytes);
        String result = new String(cipherText, "windows-1251");
        return result;
    }

    //метод для дешифровки
    public String Dencrypt(String sdata)
    {
        byte[] key = "11YDon1l{Yvz4#Qu|981nzb8".getBytes();
        byte[] data = sdata.getBytes();
        byte[] iv = "rV1Gb?yk".getBytes();
        IvParameterSpec ivParam = new IvParameterSpec(iv);
        Key deskey = null;
        StringBuffer hexCiphertext = null;
        DESedeKeySpec spec;
        byte[] res = null;
        try {
            spec = new DESedeKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
            deskey = keyFactory.generateSecret(spec);

            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, deskey, ivParam);

            res = cipher.doFinal(data);

        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchPaddingException | BadPaddingException | InvalidKeySpecException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return new String(res);
    }
    public String Encr(String data) throws UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        String skey = "11YDon1l{Yvz4#Qu|981nzb8";
        String siv = "rV1Gb?yk";
        return Encrypter(skey,data,siv);
    }
}