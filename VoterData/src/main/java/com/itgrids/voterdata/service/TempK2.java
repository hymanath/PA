package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class TempK2 {
	
	public static void main(String[] args) {
		try{
			TempK2 tempK2 = new TempK2();

			File districtFolder = new File(args[0]);
			{
				if(districtFolder.isDirectory())
				{
					tempK2.readData(districtFolder.getAbsolutePath());
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
					String constituency = file.getName();
					
					int k = 0;
					
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
								//sb.append(mobileNo+"\n");
							}
						}
					br.close();
					}
					else{
						for(File file4 : file.listFiles())
						{
							System.out.println("Reading File -- "+file4.getAbsolutePath());
							String tehsil = file4.getName();
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
										//sb.append(mobileNo+"\n");
									}
								}
								br.close();
							}
							else
							{
								for(File file5 : file4.listFiles())
								{
									System.out.println("Reading File -- "+file5.getAbsolutePath());
									String hamlet = file5.getName();
									if(hamlet.indexOf("-r") != -1)
										hamlet = hamlet.substring(0,hamlet.indexOf("-r"));
									if(!file5.isDirectory())
									{
										try{
										BufferedReader br =  new BufferedReader(new FileReader(file5));
										String line = null;
										
										while((line = br.readLine()) != null)
										{
											try{
											int index = line.indexOf("|");
											if(index != -1)
											{
												String mobileNo = line.substring(0,index).trim();
												String [] arr = line.split("\\|");
												if(arr.length >= 2)
												{
													if(k++ < 2000)
													{
														sb.append(mobileNo+"\n");
														//sb.append(constituency+"\t"+tehsil+"\t"+hamlet+"\t"+arr[1]+"\t"+mobileNo+"\n");
													}
												}
											}
											}catch(Exception e)
											{
												e.printStackTrace();
											}
										}
										br.close();
										}catch(Exception e)
										{
											e.printStackTrace();
										}
									}
									else
									{
										/*for(File file6 : file5.listFiles())
										{
											System.out.println("Reading File -- "+file6.getAbsolutePath());
											if(!file6.isDirectory())
											{
												BufferedReader br =  new BufferedReader(new FileReader(file6));
												String line = null;
												String hamlet2 = file6.getName();
												hamlet = hamlet.substring(0,hamlet.indexOf("-r"));
												int k = 0;
												while((line = br.readLine()) != null)
												{
													int index = line.indexOf("|");
													if(index != -1)
													{
														String mobileNo = line.substring(0,index).trim();
														if(k++ <= 5)
															sb.append(constituency+"\t"+tehsil+"\t"+hamlet+"\t"+mobileNo+"\n");
													}
												}
												br.close();
											}
										}*/
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
	
	public void createScript(String path)
	{
		try{
			File file2 = new File(path);
			
			if(file2.isDirectory())
			{
				BufferedWriter outwriter = new BufferedWriter(new FileWriter(file2.getAbsolutePath()+"_Scripts.txt"));
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
							
						}
					br.close();
					}
				}
					
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
