package com.itgrids.voterdata.service;

import java.io.File;
import java.util.ArrayList;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class AddFilesWithAESEncryption {
	
	public void makeZipWithAESEncryption(String sourceDirPath,String targetZipFilePath) {
		
		try {
			ZipFile zipFile = new ZipFile(targetZipFilePath);
			
			ArrayList<File> filesToAdd = new ArrayList<File>();
			
			File sourceDir = new File(sourceDirPath);
			
			if(sourceDir.isDirectory())
			{
				for(File f1 : sourceDir.listFiles())
				{
					if(f1.isDirectory())
						for(File f2 : f1.listFiles())
							filesToAdd.add(f2);
					else
					filesToAdd.add(f1);
				}
			}
			
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); 
			parameters.setEncryptFiles(true);
			parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
			parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
			parameters.setPassword("test123!");
			zipFile.addFiles(filesToAdd, parameters);
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		AddFilesWithAESEncryption addFilesWithAESEncryption = new AddFilesWithAESEncryption();
		addFilesWithAESEncryption.makeZipWithAESEncryption("E:/kamal/232","E:/kamal/232_1.zip");
	}
	
}
