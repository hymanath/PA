package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ReadMunicipalMobileNumbers {
	
	public static void main(String[] args) {
		try{
			ReadMunicipalMobileNumbers readMunicipalMobileNumbers = new ReadMunicipalMobileNumbers();

			File districtFolder = new File(args[0]);
			{
				if(districtFolder.isDirectory())
				{
					readMunicipalMobileNumbers.readData(districtFolder.getAbsolutePath());
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public int readData(String filePath)
	{
		int count = 0;
		try{
			File file2 = new File(filePath);
			
			if(file2.isDirectory())
			{
				BufferedWriter outwriter = new BufferedWriter(new FileWriter(file2.getAbsolutePath()+"_Mobiles.txt"));
				StringBuilder sb = new StringBuilder();

				for(File file : file2.listFiles())
				{
					System.out.println("Reading File -- "+file.getAbsolutePath());
					String municipality = file.getName();
					
					if(file.isDirectory())
					{
						for(File file3 : file.listFiles())
						{
							if(!file3.isDirectory())
							{
								BufferedReader br =  new BufferedReader(new FileReader(file3));
								String line = null;
							
								while((line = br.readLine()) != null)
								{
									if(line.trim().length() > 0)
									{
										sb.append(municipality+"\t"+line.trim()+"\n");
									}
								}
								br.close();
							}
						}
					}
				}
				outwriter.write(sb.toString());
				outwriter.close();
			}
			return count;
		}catch(Exception e)
		{
			e.printStackTrace();
			return count;
		}
	}
	
	/*public int readData(String filePath)
	{
		int count = 0;
		try{
			File file2 = new File(filePath);
			
			if(file2.isDirectory())
			{
				for(File file : file2.listFiles())
				{
					System.out.println("Reading File -- "+file.getAbsolutePath());
					BufferedWriter outwriter = new BufferedWriter(new FileWriter(file.getAbsolutePath()+".txt"));
					StringBuilder sb = new StringBuilder();
					
					if(file.isDirectory())
					{
						for(File file3 : file.listFiles())
						{
							if(!file3.isDirectory())
							{
								BufferedReader br =  new BufferedReader(new FileReader(file3));
								String line = null;
								while((line = br.readLine()) != null)
								{
									if(line.trim().length() > 0)
									{
										sb.append(line.trim()+"\n");
									}
								}
								br.close();
							}
						}
					}
					outwriter.write(sb.toString());
					outwriter.close();
				}
			}
			return count;
		}catch(Exception e)
		{
			e.printStackTrace();
			return count;
		}
	}*/
	
}
