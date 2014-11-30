package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ReadMobileDataK {
	
	public static void main(String[] args) {
		try{
			ReadMobileDataK tempK2 = new ReadMobileDataK();

			File districtFolder = new File(args[0]);
			{
				if(districtFolder.isDirectory())
				{
					tempK2.readDataWithoutNames(districtFolder.getAbsolutePath());
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
				BufferedWriter outwriter = new BufferedWriter(new FileWriter(file2.getAbsolutePath()+"_Mobiles.csv"));
				StringBuilder sb = new StringBuilder();
				
				for(File file : file2.listFiles())
				{
					System.out.println("Reading File -- "+file.getAbsolutePath());
					
					if(!file.isDirectory())
					{
						BufferedReader br =  new BufferedReader(new FileReader(file));
						String line = null;
						
						while((line = br.readLine()) != null)
						{
							int index = line.indexOf("|");
							if(index != -1)
							{
								String mobileNo = line.substring(0,index).trim();
								sb.append(mobileNo+"\n");
							}
						}
					br.close();
					}
					else{
						for(File file4 : file.listFiles())
						{
							System.out.println("Reading File -- "+file4.getAbsolutePath());
							if(!file4.isDirectory())
							{
								BufferedReader br =  new BufferedReader(new FileReader(file4));
								String line = null;
								
								while((line = br.readLine()) != null)
								{
									int index = line.indexOf("|");
									if(index != -1)
									{
										String mobileNo = line.substring(0,index).trim();
										sb.append(mobileNo+"\n");
									}
								}
								br.close();
							}
							else
							{
								for(File file5 : file4.listFiles())
								{
									System.out.println("Reading File -- "+file5.getAbsolutePath());
									if(!file5.isDirectory())
									{
										BufferedReader br =  new BufferedReader(new FileReader(file5));
										String line = null;
										
										while((line = br.readLine()) != null)
										{
											int index = line.indexOf("|");
											if(index != -1)
											{
												String mobileNo = line.substring(0,index).trim();
												sb.append(mobileNo+"\n");
											}
										}
										br.close();
									}
								}
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
	
	public int readDataWithoutNames(String filePath)
	{
		int count = 0;
		try{
			File file2 = new File(filePath);
			
			if(file2.isDirectory())
			{
				BufferedWriter outwriter = new BufferedWriter(new FileWriter(file2.getAbsolutePath()+"_Mobiles.csv"));
				StringBuilder sb = new StringBuilder();
				
				for(File file : file2.listFiles())
				{
					System.out.println("Reading File -- "+file.getAbsolutePath());
					
					if(!file.isDirectory())
					{
						BufferedReader br =  new BufferedReader(new FileReader(file));
						String line = null;
						
						while((line = br.readLine()) != null)
								sb.append(line.trim()+"\n");
						br.close();
					}
					else{
						for(File file4 : file.listFiles())
						{
							System.out.println("Reading File -- "+file4.getAbsolutePath());
							if(!file4.isDirectory())
							{
								BufferedReader br =  new BufferedReader(new FileReader(file4));
								String line = null;
								while((line = br.readLine()) != null)
								{
									while((line = br.readLine()) != null)
										sb.append(line.trim()+"\n");
								}
								br.close();
							}
							else
							{
								for(File file5 : file4.listFiles())
								{
									System.out.println("Reading File -- "+file5.getAbsolutePath());
									if(!file5.isDirectory())
									{
										BufferedReader br =  new BufferedReader(new FileReader(file5));
										String line = null;
										
										while((line = br.readLine()) != null)
											sb.append(line.trim()+"\n");
										br.close();
									}
								}
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
	
}
