package com.itgrids.voterdata.service;

import java.io.File;

public class RenameFilesInDir {

	public static void main(String[] args)
	{
		RenameFilesInDir renameFilesInDir = new RenameFilesInDir();
		renameFilesInDir.rename(args[0]);
	}
	public void rename(String dirPath)
	{
		try{
			File dir = new File(dirPath);
			if(dir.isDirectory())
			{
				for(File file : dir.listFiles())
				{
					if(!file.isDirectory()){
						try{
							String fileName = file.getName();
							fileName = fileName.substring(0,fileName.length()-1);
							if(file.renameTo(new File(dirPath+"/"+fileName)))
								System.out.println("File "+fileName+" is Renamed");
							else
								System.out.println("Error in renaming the file "+fileName);
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
