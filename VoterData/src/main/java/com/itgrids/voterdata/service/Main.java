package com.itgrids.voterdata.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
 
// usage: java Main [filename] [password]
public class Main {
    public static void main(String[] args) throws IOException {
        // password-protected zip file I need to read
        FileInputStream fis = new FileInputStream("E:/kamal/232_p2.zip");
        // wrap it in the decrypt stream
        ZipDecryptInputStream zdis = new ZipDecryptInputStream(fis,"kamal");
        
        // wrap the decrypt stream by the ZIP input stream
        ZipInputStream zis = new ZipInputStream(zdis);
 
        // read all the zip entries and save them as files
        ZipEntry ze;
        while ((ze = zis.getNextEntry()) != null) {
            FileOutputStream fos = new FileOutputStream(ze.getName());
            int b;
            while ((b = zis.read()) != -1) {
                fos.write(b);
            }
            fos.close();
            zis.closeEntry();
        }
        zis.close();
    }
}