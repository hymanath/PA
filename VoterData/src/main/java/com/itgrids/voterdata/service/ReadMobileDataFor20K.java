package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ReadMobileDataFor20K {
	
	public static void main(String[] args) {
		try{
			ReadMobileDataFor20K readMobileData = new ReadMobileDataFor20K();

			File districtFolder = new File(args[0]);
			{
				if(districtFolder.isDirectory())
				{
					for(File file : districtFolder.listFiles())
					if(!file.isDirectory())
					{
						try{
							int totalCount = readMobileData.getMobileNosCount(file.getAbsolutePath());
							if(totalCount > 20000)
							{
								int count = readMobileData.readAndUpdateMobileData(file.getAbsolutePath(),totalCount/20000);
								count = readMobileData.readAndRemoveMobileData(file.getAbsolutePath()+"_1",(count/(count-20000))+1,false);
								count = readMobileData.readAndRemoveMobileData(file.getAbsolutePath()+"_1_2",(count/(count-20000))+1,true);
							}
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
	
	public int readAndUpdateMobileData(String filePath,int divider)
	{
		int count = 0;
		try{
			File file = new File(filePath);
			
			if(!file.isDirectory())
			{
				System.out.println("Reading File -- "+filePath);
				BufferedReader br = new BufferedReader(new FileReader(file));
				BufferedWriter outwriter = new BufferedWriter(new FileWriter(file.getAbsolutePath()+"_1"));
				List<String> oldFilesList = new ArrayList<String>();
				oldFilesList.add(file.getAbsolutePath());
				
				String line = null;
				StringBuilder sb = new StringBuilder();
				int index = 0;
				
				while((line = br.readLine()) != null)
				{
					if(line.trim().length() > 0)
					{
						if(++index % divider == 0)
						{
							sb.append(line+"\n");
							count++;
						}
					}
				}
				br.close();
				outwriter.write(sb.toString());
				outwriter.close();
				
				for(String delfilePath : oldFilesList)
				{
					try{
						File file2 = new File(delfilePath);
						file2.deleteOnExit();
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				
			}
			return count;
		}catch(Exception e)
		{
			e.printStackTrace();
			return count;
		}
	}
	
	public int readAndRemoveMobileData(String filePath,int divider,boolean flag)
	{
		int count = 0;
		try{
			File file = new File(filePath);
			
			if(!file.isDirectory())
			{
				System.out.println("Reading File -- "+filePath);
				BufferedReader br = new BufferedReader(new FileReader(file));
				BufferedWriter outwriter = new BufferedWriter(new FileWriter(file.getAbsolutePath()+"_2"));
				List<String> oldFilesList = new ArrayList<String>();
				oldFilesList.add(file.getAbsolutePath());
				
				String line = null;
				StringBuilder sb = new StringBuilder();
				int index = 0;
				while((line = br.readLine()) != null)
				{
					if(line.trim().length() > 0)
					{
						if(!(++index % divider == 0))
						{
							if(flag)
							{
								if(count < 20000)
									sb.append(line+"\n");
							}
							else
							{
								sb.append(line+"\n");
							}
							count++;
						}
					}
				}
				br.close();
				outwriter.write(sb.toString());
				outwriter.close();
				
				for(String delfilePath : oldFilesList)
				{
					try{
						File file2 = new File(delfilePath);
						file2.deleteOnExit();
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				
			}
			return count;
		}catch(Exception e)
		{
			e.printStackTrace();
			return count;
		}
	}
	
	public int getMobileNosCount(String filepath)
	{
		int count = 0;
		try{
			File file = new File(filepath);
			
			if(!file.isDirectory())
			{
				System.out.println("Reading File For Counting -- "+filepath);
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line = null;
				
				while((line = br.readLine()) != null)
				{
					if(line.trim().length() > 0)
						count++;
				}
				br.close();
			}
			return count;
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return count;
		}
	}
	
	public void removeExistanceData(String filePath)
	{
		try{
			File file = new File(filePath);
			
			if(!file.isDirectory())
			{
				List<String> data = readData("D"+filePath.substring(1));
				
				System.out.println("Reading File -- "+filePath);
				BufferedReader br = new BufferedReader(new FileReader(file));
				BufferedWriter outwriter = new BufferedWriter(new FileWriter(file.getAbsolutePath()+"_1"));
				List<String> oldFilesList = new ArrayList<String>();
				oldFilesList.add(file.getAbsolutePath());
				
				String line = null;
				StringBuilder sb = new StringBuilder();
				
				while((line = br.readLine()) != null)
				{
					if(line.trim().length() > 0)
					{
						if(!data.contains(line.trim()))
						{
							sb.append(line+"\n");
						}
					}
				}
				br.close();
				outwriter.write(sb.toString());
				outwriter.close();
				
				for(String delfilePath : oldFilesList)
				{
					try{
						File file2 = new File(delfilePath);
						file2.deleteOnExit();
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
	
	public List<String> readData(String filePath)
	{
		List<String> data = new ArrayList<String>(0);
		try{
			File file = new File(filePath);
			
			if(!file.isDirectory())
			{
				System.out.println("Reading File -- "+filePath);
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line = null;
				
				while((line = br.readLine()) != null)
				{
					if(line.trim().length() > 0)
					{
						data.add(line);
					}
				}
				br.close();
				
			}
			return data;
		}catch(Exception e)
		{
			e.printStackTrace();
			return data;
		}
	}

}
