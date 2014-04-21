package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ReadAndCombineMobileData {
	
	public static void main(String[] args) {
		try{
			ReadAndCombineMobileData readAndCombineMobileData = new ReadAndCombineMobileData();

			File districtFolder = new File(args[0]);
			{
				if(districtFolder.isDirectory())
				{
					for(File file : districtFolder.listFiles())
					if(file.isDirectory())
					{
						try{
							readAndCombineMobileData.readAndCombineMobileData(file.getAbsolutePath());
						}catch(Exception e)
						{
							e.printStackTrace();
						}
					}
						
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public int readAndCombineMobileData(String filePath)
	{
		int count = 0;
		try{
			File file = new File(filePath);
			
			if(file.isDirectory())
			{
				System.out.println("Reading File -- "+filePath);
				BufferedReader br = null;
				BufferedWriter outwriter = new BufferedWriter(new FileWriter(file.getAbsolutePath()+"_FULL"));
				
				String line = null;
				StringBuilder sb = new StringBuilder();
				
				for(File data : file.listFiles())
				{
					try{
					if(!data.isDirectory())
					{
						br = new BufferedReader(new FileReader(data));
						while((line = br.readLine()) != null)
						{
							int index = line.indexOf("|");
							if(index != -1)
								sb.append(line.substring(0,index).trim()+"\n");
						}
						br.close();
					}
					else
					{
						for(File data2 : data.listFiles())
						{
							try{
							if(!data2.isDirectory())
							{
								br = new BufferedReader(new FileReader(data2));
								while((line = br.readLine()) != null)
								{
									int index = line.indexOf("|");
									if(index != -1)
										sb.append(line.substring(0,index).trim()+"\n");
								}
								br.close();
							}
							else
							{
								for(File data3 : data2.listFiles())
								{
									if(!data3.isDirectory())
									{
										br = new BufferedReader(new FileReader(data3));
										while((line = br.readLine()) != null)
										{
											int index = line.indexOf("|");
											if(index != -1)
												sb.append(line.substring(0,index).trim()+"\n");
										}
										br.close();
									}
									else{
										for(File data4 : data3.listFiles())
										{	
											br = new BufferedReader(new FileReader(data4));
											while((line = br.readLine()) != null)
											{
												int index = line.indexOf("|");
												if(index != -1)
													sb.append(line.substring(0,index).trim()+"\n");
											}
											br.close();
										}
									}
								}
							}
							}catch(Exception e)
							{
								e.printStackTrace();
							}
							
						}
					}
				}catch(Exception e)
				{
					e.printStackTrace();
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
	
	

}
