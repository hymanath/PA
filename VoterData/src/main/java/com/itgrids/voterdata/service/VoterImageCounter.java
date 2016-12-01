package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class VoterImageCounter {

	public static void main(String[] args)
	{
		countImages("D:\\Voter_Images_2016\\TS\\CID\\");
	}
	
	public static void countImages(String mainDirPath)
	{
		try{
			File mainDir = new File(mainDirPath);
			BufferedWriter outwriter = new BufferedWriter(new FileWriter(mainDirPath+"\\Images_count.txt"));
			StringBuilder sb = new StringBuilder();
			
			if(mainDir.isDirectory())
			{
				for(File conDir : mainDir.listFiles())
				{
					if(conDir.isDirectory())
					{
						System.out.println("Calculating --> "+conDir.getName());
						int boothsCount = 0;
						int imagesCount = 0;
						
						for(File boothDir : conDir.listFiles())
						{
							if(boothDir.isDirectory())
							{
								boothsCount++;
								for(File image : boothDir.listFiles())
								{
									if(!image.isDirectory())
										imagesCount++;
								}
							}
						}
						System.out.println("Constituency - "+conDir.getName()+"\tBooths - "+boothsCount+"\tImages - "+imagesCount);
						sb.append("Constituency - "+conDir.getName()+"\tBooths - "+boothsCount+"\tImages - "+imagesCount+"\n");
					}
				}
			}
			outwriter.write(sb.toString());
			outwriter.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
