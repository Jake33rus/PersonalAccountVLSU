package com.example.jake.university.API;

import android.util.Xml;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class Encryption {

    public static String Decr(String word) throws UnsupportedEncodingException {
        String skey = "";
        byte[] bkey = skey.getBytes("ISO-8859-1");
        String siv = "";
        byte[] biv = siv.getBytes("ISO-8859-1");

        // что такое windows 1251 не знаю, чем заменить я ХЗ!!!
        Encoding win1251 = Encoding.GetEncoding("windows-1251");

        byte[] enc = win1251.GetBytes(word);
        return Encryption.Decrypt(enc, bkey, biv);
    }

    public  static  byte[] Encrypt(String date, byte[] keyshifr, byte[] iv)
    {
        try
        {
            MemoryStream mStream = new MemoryStream();
            TripleDES triple = TripleDES.Create();
            CryptoStream cStream = new CryptoStream(mStream, triple.CreateEncryptor(keyshifr, iv),
                    CryptoStreamMode.Write);
            byte[] toEncrypt = new UTF8Encoding().GetBytes(date);
            cStream.Write(toEncrypt, 0, toEncrypt.Length);
            cStream.FlushFinalBlock();
            byte[] ret = mStream.ToArray();
            cStream.Close();
            mStream.Close();
            return ret;
        }
        catch (CryptographicException e)
        {
            //вывод сообщения об ошибке, хз нужен или нет, возможно только для отладки
            Console.WriteLine("A Cryptographic error occurred: {0}", e.Message);
            return null;
        }
    }

    public static String EncryptString(byte[] date)
    {
        try
        {
            String val = new String(date, "UTF-8");
            return val;
        }
        catch(Exception e)
        {
            return "Проблемы кодировки";
        }
    }
    public static String Decrypt(byte[] date, byte[] keyshifr, byte[] iv) // Дешифровка данных
    {
        try
        {
            // Потоки и тройничек какой то искал - аналогов нет
            MemoryStream msDecrypt = new MemoryStream(date);
            TripleDES triple = TripleDES.Create();
            CryptoStream csDecrypt = new CryptoStream(msDecrypt, triple.CreateDecryptor(keyshifr, iv), CryptoStreamMode.Read);

            //Создаю буфер для хранения расшифрованных данных.
            byte[] fromEncrypt = new byte[date.length];

            //Читаю расшифрованные данные из крипто-потока и помещаю его во временный буфер.
            csDecrypt.Read(fromEncrypt, 0, fromEncrypt.length);
            return new UTF8Encoding().GetString(fromEncrypt).Replace("\0", "");
        }
        catch (Exception e)
        {
            return "Ошибка дешифрования";
        }
    }

    public static String RandomString(int size) // Создание рандомной строки определенной длины
    {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        char ch;
        for (int i = 0; i < size; i++)
        {
            //Генерируем число являющееся латинским символом в юникоде
            ch = Convert.ToChar(Convert.ToInt32(Math.Floor(26 * random.NextDouble() + 65)));
            //Конструируем строку со случайно сгенерированными символами
            builder.Append(ch);
        }
        return builder.toString();
    }

    public static String Encr(String query) throws UnsupportedEncodingException {
        String skey = ""; // Надо обязательно 24 символа
        byte[] bkey = skey.getBytes("ISO-8859-1");
        String siv = ""; // Надо обязательно 8 символа
        byte[] biv = siv.getBytes("ISO-8859-1");
        byte[] enc = Encryption.Encrypt(query, bkey, biv /*triple.Key, triple.IV*/);
        //Что за класс Encoding я хз!
        Encoding win1251 = Encoding.GetEncoding("windows-1251");
        String encrypted = win1251.GetString(enc);
        return encrypted;
    }
}
