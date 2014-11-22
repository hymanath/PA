package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ImageDataVerifier {

	public static void main(String[] args)
	{
		ImageDataVerifier verifier = new ImageDataVerifier();
		verifier.verifyData(args[0]);
	}
	
	public void verifyData(String dirPath)
	{
		try{
			File mainDir = new File(dirPath);
			BufferedWriter outwriter = new BufferedWriter(new FileWriter(dirPath+"_Kamal.txt"));
			StringBuilder sb = new StringBuilder();
			if(mainDir.isDirectory())
			{
				for(File conDir : mainDir.listFiles())
				{
					if(conDir.isDirectory())
					{
						int total = conDir.list().length;
						System.out.println("Constituency - "+conDir.getName()+" ,Parts --> "+total);
						sb.append("Constituency - "+conDir.getName()+" ,Parts --> "+total+"\n");
					}
				}
			}
			
			outwriter.write(sb.toString());
			outwriter.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
