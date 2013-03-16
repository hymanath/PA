package com.itgrids.voterdata.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.apache.commons.io.FileUtils;

public class ChangeDBName {

	public void changeDBName(String sourceFolderPath, String targetFolderPath )
	{
		try{
			File sourceFolder = new File(sourceFolderPath);
			
			if(sourceFolder.isDirectory())
			{
				int index = 0;
				for(File sqlFile : sourceFolder.listFiles())
				{
					try{
						System.out.println(++index+") Now Processing File - "+sqlFile.getName());
						String content = FileUtils.readFileToString(sqlFile);
						File targetFile = new File(targetFolderPath+"/"+sqlFile.getName());
						FileWriter fileWriter = new FileWriter(targetFile);
						BufferedWriter bw = new BufferedWriter(fileWriter);
						bw.write(content.replaceAll("dakavara_pa", "dakava2_dakavara_pa"));
						bw.close();
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		ChangeDBName changeDBName = new ChangeDBName();
		changeDBName.changeDBName("C:\\Dump20130315", "C:\\dakava2");
	}
}
