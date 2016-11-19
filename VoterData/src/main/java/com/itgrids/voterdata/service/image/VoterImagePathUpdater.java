package com.itgrids.voterdata.service.image;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class VoterImagePathUpdater {

	private static String mainDir = "/mnt/tdp-img/voter_images/";
	public static void main(String[] args) throws Exception
	{
		List<Integer> list = new ArrayList<Integer>(0);
		
		list.add(318);
		list.add(229);
		list.add(39);
		list.add(16);
		list.add(296);
		list.add(343);
		list.add(10);
		list.add(12);
		list.add(4);
		list.add(100);
		
		BufferedWriter outwriter = new BufferedWriter(new FileWriter(mainDir+"US/1_Image_Update.txt"));
		for(Integer cid :list)
			updateImagePath(cid,outwriter);
		outwriter.close();
	}
	
	public static void updateImagePath(Integer cid,BufferedWriter outwriter)
	{
		try{
			File conDir = new File(mainDir+cid);
			
			if(conDir.isDirectory())
			{
				
				StringBuilder sb = new StringBuilder();
				
				for(File boothDir : conDir.listFiles())
				{
					try{
						if(boothDir.isDirectory())
						{
							for(File image : boothDir.listFiles())
							{
								try{
									if(!image.isDirectory())
									{
										String path = image.getAbsolutePath().replace("/mnt/tdp-img/voter_images/","");
										String voterId = image.getName().replace(".jpg","");
										//System.out.println(path+"\t"+voterId);
										sb.append("UPDATE voter SET image_path = '"+path+"' WHERE voter_id_card_no = '"+voterId+"' AND image_path != '"+path+"';\n");
									}
								}catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
				outwriter.write(sb.toString());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
