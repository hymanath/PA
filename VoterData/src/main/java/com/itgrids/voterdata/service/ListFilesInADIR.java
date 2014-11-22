package com.itgrids.voterdata.service;

import java.io.File;

public class ListFilesInADIR {

	public static void main(String[] args)
	{
		//ListFilesInADIR listFilesInADIR = new ListFilesInADIR();
		//listFilesInADIR.listFiles("E:\\Photos\\");
		File file = new File("D:\\Photos\\CD_IMG\\255\\");
		
		for(File f : file.listFiles())
			System.out.println(f.getName());
	}
	public void listFiles(String dirPath)
	{
		try{
			File dir = new File(dirPath);
			
			for(File inDir : dir.listFiles())
			{
				try{
					if(inDir.isDirectory())
					{
						int index = inDir.listFiles().length;
						System.out.println("Constituency "+inDir.getName()+" Contains "+index+" Cadre Images");
					}
					
				}catch(Exception e)
				{
					
				}
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
