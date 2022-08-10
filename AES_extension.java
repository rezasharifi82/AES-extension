package com.maresha.encrypt;


// In The Name Of God 
// The most merciful




//  Info:
//      Developer     :     (Mohammadreza & Alireza) Sharifi
//      License       :     MIT License
//      Spcial Thanks :     GFG website and Google :)
//      *
//      *
//      *
//      *
//      *
//      *
//       .........   Hope you Enjoy :)   .........






import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.common.ComponentCategory;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;



@DesignerComponent(
        version = 1,
        description = "To Encrypt strings with AES",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,
        iconName = "https://api3.iloveimg.com/v1/download/g27d4mrsg3ztmnzAgm5d3njAg2y9l6k6wp550m4jbw392p3jj3dckyb9y595s2zAl1j5xghA7szbgrk5q4xnbf0hhsjsAcdlnznzvm9pm3md29c9g61w5bdgbbcyv8x0hdl1sygd4dhcA2f6swpvkm0w2ntv7n4nsy9tq8jqrd517Asdqlk1")

@SimpleObject(external = true)


public class MareshaAES extends AndroidNonvisibleComponent implements Component {
public static final int VERSION = 1;
private ComponentContainer container;
private Context context;
private static final String LOG_TAG = "";

 public MareshaAES(ComponentContainer container) {
    super(container.$form());
    this.container = container;
    context = (Context) container.$context();
    Log.d(LOG_TAG, "Maresha" );
}
@SimpleFunction(description = "Encrypt arbitrary string via AES")
public String AESEncrypt(String text,String salt,String secret){
    return AES.encrypt(text,salt, secret);

   }
@SimpleFunction(description = "Decrypt arbitrary string via AES")
public String AESDecrypt(String text,String salt,String secret){
    return AES.decrypt(text,salt, secret);
    }
@SimpleFunction(description = "Generate SALT")
public String Salt(String secret){
    return AES.encrypt("Default text hust in case ","Non default salt forexample",secret);
    }


  
public static final class AES {
    

    // Encrypt method




    public static String encrypt(String strToEncrypt,final String SALT,final String SECRET_KEY)
    {
        try {
  
            // Create default byte array
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0,
                          0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec
                = new IvParameterSpec(iv);
  
            // Create SecretKeyFactory object
            SecretKeyFactory factory
                = SecretKeyFactory.getInstance(
                    "PBKDF2WithHmacSHA256");
            
            // Create KeySpec object and assign with
            // constructor
            KeySpec spec = new PBEKeySpec(
                SECRET_KEY.toCharArray(), SALT.getBytes(),
                65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(
                tmp.getEncoded(), "AES");
  
            Cipher cipher = Cipher.getInstance(
                "AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey,
                        ivspec);
            // Return encrypted string
            return Base64.getEncoder().encodeToString(
                cipher.doFinal(strToEncrypt.getBytes(
                    StandardCharsets.UTF_8)));
        }
        catch (Exception e) {
            System.out.println("Error while encrypting: "
                               + e.toString());
        }
        return "Error";
    }
  
    // This method use to decrypt to string
    public static String decrypt(String strToDecrypt,final String SALT,final String SECRET_KEY)
    {
        try {
  
            // Default byte array
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0,
                          0, 0, 0, 0, 0, 0, 0, 0 };
            // Create IvParameterSpec object and assign with
            // constructor
            IvParameterSpec ivspec
                = new IvParameterSpec(iv);
  
            // Create SecretKeyFactory Object
            SecretKeyFactory factory
                = SecretKeyFactory.getInstance(
                    "PBKDF2WithHmacSHA256");
  
            // Create KeySpec object and assign with
            // constructor
            KeySpec spec = new PBEKeySpec(
                SECRET_KEY.toCharArray(), SALT.getBytes(),
                65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(
                tmp.getEncoded(), "AES");
  
            Cipher cipher = Cipher.getInstance(
                "AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey,
                        ivspec);
            // Return decrypted string
            return new String(cipher.doFinal(
                Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e) {
            System.out.println("Error while decrypting: "
                               + e.toString());
        }
        return "Error";
    }
}
}


// Thanks


/// Please change the file name if you wanna compile






