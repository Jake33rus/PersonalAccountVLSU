package com.example.jake.university.API;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jake.university.API.MD5;
import com.example.jake.university.API.TripleDES;
import com.example.jake.university.API.postReq;
import com.example.jake.university.MainActivity;
import com.example.jake.university.R;
import com.example.jake.university.adapter.FingerprintHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class Login extends AppCompatActivity {

    private static final String KEY_NAME = "yourKey";
    private Cipher cipher;
    private KeyStore keyStore;
    private KeyGenerator keyGenerator;
    private FingerprintManager.CryptoObject cryptoObject;
    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView fp = (ImageView)findViewById(R.id.fingerprint);
        ImageView ad = (ImageView)findViewById(R.id.arrowDown);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {

            keyguardManager =
                    (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
            fingerprintManager =
                    (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

            if (!fingerprintManager.isHardwareDetected())
            {

            }
            //Check whether the user has granted your app the USE_FINGERPRINT permission//
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED)
            {

            }

            //Check that the user has registered at least one fingerprint//
            if (!fingerprintManager.hasEnrolledFingerprints())
            {

            }

            //Check that the lockscreen is secured//
            if (!keyguardManager.isKeyguardSecure())
            {

            }
            else {

                fp.setVisibility(View.VISIBLE);
                ad.setVisibility(View.VISIBLE);

                try {
                    generateKey();
                } catch (FingerprintException e) {
                    e.printStackTrace();
                }

                if (initCipher()) {
                    //If the cipher is initialized successfully, then create a CryptoObject instance//
                    cryptoObject = new FingerprintManager.CryptoObject(cipher);

                    FingerprintHandler helper = new FingerprintHandler(this);
                    helper.startAuth(fingerprintManager, cryptoObject);

                }
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void generateKey() throws FingerprintException {
        try {
            // Obtain a reference to the Keystore using the standard Android keystore container identifier (“AndroidKeystore”)//
            keyStore = KeyStore.getInstance("AndroidKeyStore");

            //Generate the key//
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");

            //Initialize an empty KeyStore//
            keyStore.load(null);

            //Initialize the KeyGenerator//
            keyGenerator.init(new

                    //Specify the operation(s) this key can be used for//
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)

                    //Configure this key so that the user has to confirm their identity with a fingerprint each time they want to use it//
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());

            //Generate the key//
            keyGenerator.generateKey();

        } catch (KeyStoreException
                | NoSuchAlgorithmException
                | NoSuchProviderException
                | InvalidAlgorithmParameterException
                | CertificateException
                | IOException exc) {
            exc.printStackTrace();
            throw new FingerprintException(exc);
        }
    }

    //Create a new method that we’ll use to initialize our cipher//
    @TargetApi(Build.VERSION_CODES.M)
    public boolean initCipher() {

        try {
            //Obtain a cipher instance and configure it with the properties required for fingerprint authentication//
            cipher = Cipher.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES + "/"
                            + KeyProperties.BLOCK_MODE_CBC + "/"
                            + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }

        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                    null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //Return true if the cipher has been initialized successfully//
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {

            //Return false if cipher initialization failed//
            return false;
        } catch (KeyStoreException | CertificateException
                | UnrecoverableKeyException | IOException
                | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }

    private class FingerprintException extends Exception {
        public FingerprintException(Exception e) {
            super(e);
        }
    }

    public void onButtonClick (View v) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
          TextView login = (TextView) findViewById(R.id.loginField);
        TextView password = (TextView) findViewById(R.id.passField);

        String logStr, passStr;
        TripleDES tde = new TripleDES();

        logStr = login.getText().toString();
        passStr = password.getText().toString();

        JSONArray arr;
        JSONObject obj = new JSONObject();
        postReq comand = new postReq();

        //Первый этап инициализации(Получаем CPerson)
        try {
            comand.execute("20","AuthData_GetData",
                    "0, 0,'"+logStr+"', '"+MD5.hash(tde.Encr(passStr))+"','','', 0,'','', 0").get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        arr = comand.getjARRAY();
        if (arr.length()!=0)
        {
            try {
                obj = arr.getJSONObject(0);
            } catch (Exception e) {
               e.printStackTrace();
            }

            String CPerson = new String();
            try {
                CPerson =  obj.getString("CPerson");
            } catch (Exception e) {
                e.printStackTrace();
            }
//Этап 2. Получаем PerID

            postReq comm2 =new postReq();

            try {
                comm2.execute("20","dbo.UserProfiles_GetData","0,'',0,"+CPerson+",0,0,0,0").get();
            } catch (Exception e) {
                e.printStackTrace();
            }

            JSONArray arr2 = comm2.getjARRAY();
            JSONObject obj2 = new JSONObject();

            try {
                obj2 = arr2.getJSONObject(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String PerID = new String();
            try {
                PerID =  obj2.getString("PerID");
            } catch (Exception e) {
                e.printStackTrace();
            }



            Intent toMain = new Intent(this, MainActivity.class);
            startActivity(toMain);
            finish();
        }
        else
            {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Неверный логин или пароль!", Toast.LENGTH_SHORT);
                toast.show();
                password.setText("");

            }
    }
}
