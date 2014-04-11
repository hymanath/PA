package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


public class ReadLocationWiseMobileNumbers {

	public static void main(String[] args) {
		try{
			ReadLocationWiseMobileNumbers rlwmn = new ReadLocationWiseMobileNumbers();
			rlwmn.getDistrictWiseMobileNosData(args[0]);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void getDistrictWiseMobileNosData(String path)
	{
		try{
			File folder = new File(path);
			File resultFile = new File(path+"\\Total_Result.txt");
			BufferedReader br = null;
			BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultFile));
			String line = null;
			StringBuilder sb = null;
			
			if(folder.isDirectory())
			{
				String district = folder.getName();
				for(File acDir : folder.listFiles())
				{
					try{
					if(acDir.isDirectory())
					{
						String constituency = acDir.getName();
						System.out.println("Reading "+constituency+" Folder");
						sb = new StringBuilder();
						for(File acFile : acDir.listFiles())
						{
							try{
							if(!acFile.isDirectory())
							{
								String village = acFile.getName();
								if(village.contains("-r-"))
									village = village.substring(0,village.indexOf("-r-"));
								System.out.println("Reading "+constituency+" -- "+village+" File");
								br = new BufferedReader(new FileReader(acFile));
								while((line = br.readLine()) != null)
								{
									if(line.trim().length() > 0)
									{
										sb.append(district+"\t"+constituency+"\t"+village+"\t"+line.trim()+"\n");
									}
								}
								br.close();
							}
							}catch(Exception e){e.printStackTrace();}
						}//
						outwriter.write(sb.toString());
						System.gc();
					}
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}//
				outwriter.close();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
