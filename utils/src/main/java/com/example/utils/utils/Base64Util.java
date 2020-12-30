package com.example.utils.utils;

import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * @author: programmingroad
 * @create: 2020/01/04 16:33
 * @description:
 **/
public class Base64Util {

    public static String encoder(byte[] body) {
        return Base64.getEncoder().encodeToString(body);
    }

    public static byte[] decoder(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    public static String fileToBase64(File file) throws IOException {
        return encoder(fileToBody(file));
    }

    public static byte[] fileToBody(File file) throws IOException {
        try (InputStream is = new FileInputStream(file)) {
            return StreamUtils.copyToByteArray(is);
        }
    }
}
