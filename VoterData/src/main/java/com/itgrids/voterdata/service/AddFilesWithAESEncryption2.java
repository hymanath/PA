package com.itgrids.voterdata.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class AddFilesWithAESEncryption2 {
	
	public void makeZipWithAESEncryption(String sourceDirPath,String targetZipFilePath) {
		
		try {
			
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); 
			parameters.setEncryptFiles(true);
			parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
			parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
			parameters.setPassword("Kamal@karD");
			
			ZipFile zipFile = new ZipFile(targetZipFilePath);
			
			ArrayList<File> filesToAdd = new ArrayList<File>();
			
			File sourceDir = new File(sourceDirPath);
			
			if(sourceDir.isDirectory())
			{
				for(File f1 : sourceDir.listFiles())
				{
					if(f1.isDirectory())
					{
						zipFile.addFolder(f1, parameters);
						System.out.println(f1.getName()+" Completed");
					}
					else
					filesToAdd.add(f1);
				}
			}
			
			if(filesToAdd.size() > 0)
			zipFile.addFiles(filesToAdd, parameters);
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		AddFilesWithAESEncryption2 addFilesWithAESEncryption = new AddFilesWithAESEncryption2();
		File dir = new File("E:/kamal/232");
		
		for(File file : dir.listFiles())
		{
			System.out.println(file.getName()+" Started - "+new Date());
			addFilesWithAESEncryption.makeZipWithAESEncryption(file.getAbsolutePath(),"E:/kamal/232E/"+file.getName()+".zip");
		}
	}
	
}
