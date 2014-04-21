package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class DistrictWiseBoothLocationDataReaderFor2014 {
	
	public static void main(String[] args)
	{
		try{
			DistrictWiseBoothLocationDataReaderFor2014 readerFor2014 = new DistrictWiseBoothLocationDataReaderFor2014();
			File fk = new File(args[0]);
			List<String> flist = new ArrayList<String>(0);
			
			flist.add("Adilabad");
			/*flist.add("Chittoor");
			flist.add("Prakasam");
			flist.add("Rangareddy");
			flist.add("Srikakulam");
			flist.add("Vizianagaram");
			flist.add("Visakhapatnam");
			flist.add("Warangal");
			flist.add("WestGodavari");*/
			
			if(fk.isDirectory())
			{
				for(File fk2 : fk.listFiles())
				{
					if(flist.contains(fk2.getName()))
					{
						try{
							readerFor2014.readDistrictWiseData(fk2.getAbsolutePath());
							readerFor2014.combineData(fk2.getAbsolutePath());
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
	
	public void readDistrictWiseData(String path)
	{
		try{
			File folder = new File(path);
			
			if(folder.isDirectory())
			{
				for(File f : folder.listFiles())
				{
					try{
					if(f.isDirectory())
					{
						String[] arr = {f.getAbsolutePath()};
						BoothLocationDataReaderFor2014.main(arr);
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
	
	public void combineData(String path)
	{
		try{
			File folder = new File(path);
			
			if(folder.isDirectory())
			{
				BufferedReader br = null;
				BufferedWriter outwriter = new BufferedWriter(new FileWriter(folder.getAbsolutePath()+"\\Total_Data.txt"));
				StringBuilder sb = new StringBuilder();
				
				for(File f : folder.listFiles())
				{
					try{
					if(f.isDirectory())
					{
						for(File fl : f.listFiles())
						{
							if(fl.getName().endsWith("BoothLocationDataReader.txt"))
							{
								String line = null;
								br = new BufferedReader(new FileReader(fl));
								while((line = br.readLine()) != null)
								{
									if(line.trim().length() > 0)
									{
										sb.append(line+"\n");
									}
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
				outwriter.write(sb.toString());
				outwriter.close();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
