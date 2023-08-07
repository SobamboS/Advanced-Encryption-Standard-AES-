package com.example.encryptionanddecryption.AES;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.crypto.*;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
@Service
@RequiredArgsConstructor
public class LicenseService {

    private static final int KEY_LENGTH = 256;
    private static SecretKey secretKey;

    private final LicenseRepository licenseRepository;

    /*
    * Generates Secret key used for encryption and Decryption*/
    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        if (secretKey == null) {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(KEY_LENGTH);
            secretKey = keyGenerator.generateKey();
        }
        return secretKey;
    }


    public String generateLicense(LicenseRequest request) throws GeneralSecurityException{
        License license = License.builder()
                .license(request.getLicense())
                .build();
        licenseRepository.save(license);
        return encrypt(license);
    }


    public String encrypt(License license) throws GeneralSecurityException{

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, generateKey());
        byte[] licenseBytes = license.getLicense().getBytes(StandardCharsets.UTF_8);
        byte[] cipherText = cipher.doFinal(licenseBytes);
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }


    public  String decrypt(String encryptedMessage) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, generateKey());
        byte[] encryptedData = Base64.getDecoder().decode(encryptedMessage);
        byte[] decryptedBytes = cipher.doFinal(encryptedData);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

}
