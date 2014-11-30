package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class TempK3 {
	
	public static void main(String[] args) {
		try{
			TempK3 tempK3 = new TempK3();

			File districtFolder = new File(args[0]);
			{
				if(districtFolder.isDirectory())
				{
					tempK3.readData(districtFolder.getAbsolutePath());
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void readData(String filePath)
	{
		try{
			File file2 = new File(filePath);
			
			if(file2.isDirectory())
			{
				BufferedWriter outwriter = new BufferedWriter(new FileWriter(file2.getAbsolutePath()+"_Count.txt"));
				StringBuilder sb = new StringBuilder();
				
				for(File file : file2.listFiles())
				{
					System.out.println("Reading File -- "+file.getAbsolutePath());
					
					if(file.isDirectory())
					{
						for(File consFolder : file.listFiles())
						{
							System.out.println("Reading File -- "+consFolder.getAbsolutePath());
							int count = 0;
							String constituency = consFolder.getName();
							
							if(consFolder.isDirectory())
							{
								for(File mandalFile : consFolder.listFiles())
								{
									if(mandalFile.isDirectory())
									{
										for(File villageFile : mandalFile.listFiles())
										{
											if(!villageFile.isDirectory())
											{
												BufferedReader br =  new BufferedReader(new FileReader(villageFile));
												String line = null;
												while((line = br.readLine()) != null)
												{
													if(line.trim().length() > 0)
														count++;
												}
												br.close();
											}
										}
									}
									else
									{
										BufferedReader br =  new BufferedReader(new FileReader(mandalFile));
										String line = null;
										while((line = br.readLine()) != null)
										{
											if(line.trim().length() > 0)
												count++;
										}
										br.close();
									}
								}
							}
							sb.append(constituency+"\t"+count+"\n");
						}
					}
				}
				outwriter.write(sb.toString());
				outwriter.close();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
