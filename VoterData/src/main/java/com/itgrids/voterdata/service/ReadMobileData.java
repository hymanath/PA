package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ReadMobileData {
	
	public static void main(String[] args) {
		try{
			ReadMobileData readMobileData = new ReadMobileData();
			File districtFolder = new File(args[0]);
			{
				if(districtFolder.isDirectory())
				{
					for(File file : districtFolder.listFiles())
					if(file.isDirectory())
					{
						try{
							readMobileData.readAndUpdateMobileDataInAFolder(file.getAbsolutePath());
						}catch(Exception e)
						{
							e.printStackTrace();
						}
					}
						
				}
			}
			readMobileData.readAndUpdateMobileDataInAFolder(args[0]);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void readAndUpdateMobileDataInAFolder(String folderPath)
	{
		try{
			File folder = new File(folderPath);
			
			if(folder.isDirectory())
			{
				System.out.println("Reading Folder -- "+folderPath);
				BufferedReader br = null;
				BufferedWriter outwriter = null;
				List<String> oldFilesList = new ArrayList<String>();
				
				for(File file : folder.listFiles())
				{
					if(!file.isDirectory())
					{
						oldFilesList.add(file.getAbsolutePath());
						br = new BufferedReader(new FileReader(file));
						outwriter = new BufferedWriter(new FileWriter(file.getAbsolutePath()+"_alternative"));
						String line = null;
						StringBuilder sb = new StringBuilder();
						int index = 0;
						while((line = br.readLine()) != null)
						{
							if(line.trim().length() > 0)
							{
								if(index %2 == 0)
									sb.append(line+"\n");
								index++;	
							}
						}
						br.close();
						outwriter.write(sb.toString());
						outwriter.close();
					}
				}
				
				for(String delfilePath : oldFilesList)
				{
					try{
						File file = new File(delfilePath);
						file.deleteOnExit();
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

}
