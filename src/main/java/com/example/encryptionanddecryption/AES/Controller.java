package com.example.encryptionanddecryption.AES;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class Controller{

    private final LicenseService licenseService;


    @PostMapping("/encrypt")
        public String encrypt(@RequestBody LicenseRequest request) throws Exception{
        return licenseService.generateLicense(request);
        }


    @PostMapping("/decrypt")
    public String decrypt(@RequestParam(name="Encrypted Code") String encryptedMessage) throws Exception{
        return licenseService.decrypt(encryptedMessage);
    }

    }


