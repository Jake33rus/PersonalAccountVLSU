package com.example.jake.university.API;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class TripleDES {

    //Метод для шифрования
    public String Encrypter(String skey,String sdata, String siv) {
        /*
        * date - Данные, которые необходимо зашифровать;
        * key - ключ шифрования;
        * iv - вектор инициализации для симетричного алгоритма;
        */
        byte[] key = skey.getBytes();
        byte[] data = sdata.getBytes();
        byte[] iv = siv.getBytes();
        IvParameterSpec ivParam = new IvParameterSpec(iv);
        Key deskey = null;
        StringBuffer hexCiphertext = null;
        DESedeKeySpec spec;
        try {
            spec = new DESedeKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
            deskey = keyFactory.generateSecret(spec);

            Cipher cipher = Cipher.getInstance("desede"+"/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, deskey, ivParam);

            byte[] CipherText = cipher.doFinal(data);
            hexCiphertext = new StringBuffer();
            for (int i=0;i<CipherText.length; i++)
                hexCiphertext.append(Integer.toString((CipherText[i]&0xff)+0x100,16).substring(1));

        }
        catch(InvalidKeyException ex) {
            Logger.getLogger(TripleDES.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(NoSuchAlgorithmException ex) {
            Logger.getLogger(TripleDES.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InvalidKeySpecException ex){
            Logger.getLogger(TripleDES.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchPaddingException ex){
            Logger.getLogger(TripleDES.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalBlockSizeException ex){
            Logger.getLogger(TripleDES.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (BadPaddingException ex){
            Logger.getLogger(TripleDES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(TripleDES.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String(hexCiphertext);
    }

    //метод для дешифровки
    public String Dencrypt(String sdata)
    {
        byte[] key = "".getBytes();
        byte[] data = sdata.getBytes();
        byte[] iv = "".getBytes();
        IvParameterSpec ivParam = new IvParameterSpec(iv);
        Key deskey = null;
        StringBuffer hexCiphertext = null;
        DESedeKeySpec spec;
        byte[] res = null;
        try {
            spec = new DESedeKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
            deskey = keyFactory.generateSecret(spec);

            Cipher cipher = Cipher.getInstance("desede"+"/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, deskey, ivParam);

            res = cipher.doFinal(data);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return new String(res);
    }
    public String Encr(String data)
    {
        String skey = "";
        String siv = "";
        return Encrypter(skey,data,siv);
    }
}
