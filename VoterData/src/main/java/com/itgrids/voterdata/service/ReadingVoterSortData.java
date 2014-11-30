package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ReadingVoterSortData {

	public static void main(String[] args) {
		try{
			ReadingVoterSortData readingVoterSortData = new ReadingVoterSortData();
			readingVoterSortData.readAndCombineMobileData(args[0]);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void readAndCombineMobileData(String filePath)
	{
		try{
			File folder = new File(filePath);
			BufferedWriter outwriter = new BufferedWriter(new FileWriter(folder.getAbsolutePath()+"_Hamlet_Mobiles.txt"));
			StringBuilder sb = new StringBuilder();
			
			if(folder.isDirectory())
			{
				for(File file1 : folder.listFiles())
				{
					System.out.println("reading --> "+file1.getAbsolutePath());
					if(file1.isDirectory())
					{
						String mandal = file1.getName();
						for(File file2 : file1.listFiles())
						{
							System.out.println("reading --> "+file2.getAbsolutePath());
							String hamletName = file2.getName().substring(0,file2.getName().indexOf("-"));
							BufferedReader br =  new BufferedReader(new FileReader(file2));
							String line = null;
							while((line = br.readLine()) != null)
							{
								String[] a = line.trim().split("\\|");
								sb.append(mandal+"\t"+hamletName+"\t"+a[0]+"\n");
							}
							br.close();
						}
					}
					else
					{
						BufferedReader br =  new BufferedReader(new FileReader(file1));
						String hamletName = file1.getName().substring(0,file1.getName().indexOf("-"));
						String line = null;
						while((line = br.readLine()) != null)
						{
							String[] a = line.trim().split("\\|");
							sb.append(hamletName+"\t"+a[0]+"\n");
						}
						br.close();
					}
				}
			}
			outwriter.write(sb.toString());
			outwriter.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void combine(String filePath)
	{
		try{
			File folder = new File(filePath);
			
			BufferedWriter outwriter = new BufferedWriter(new FileWriter(folder.getAbsolutePath()+"_Kamal.txt"));
			StringBuilder sb = new StringBuilder();
			List<String> nlist = new ArrayList<String>(0);
			for(File file : folder.listFiles())
			{
				BufferedReader br =  new BufferedReader(new FileReader(file));
				String line = null;
				while((line = br.readLine()) != null)
				{
					if(!nlist.contains(line.trim()))
					{
						nlist.add(line.trim());
						sb.append(line.trim()+"\n");
					}
				}
				br.close();
			}
			outwriter.write(sb.toString());
			outwriter.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
