package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class CheckImagesInAFolder {

	public static void main(String[] args)
	{
		CheckImagesInAFolder mover = new CheckImagesInAFolder();
		mover.checkImages(args[0],args[1],args[2]);
	}
	public void checkImages(String srcDirPath,String dbSrcPath,String resultFilePath)
	{
		try{
			File srcDir = new File(srcDirPath);
			File db = new File(dbSrcPath);
			File result = new File(resultFilePath);
			
			BufferedReader br = new BufferedReader(new FileReader(db));
			BufferedWriter outwriter = new BufferedWriter(new FileWriter(result));
			
			StringBuilder sb = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();
			
			String line = null;
			List<String> dbList = new ArrayList<String>(0);
			
			while((line = br.readLine()) != null)
				dbList.add(line);
			
			br.close();
			
			int index = 0;
			int nfi = 0;
			if(srcDir.isDirectory())
			{
				List<String> filesList = new ArrayList<String>(0);
				for(File fname : srcDir.listFiles())
				{
					filesList.add(fname.getName());
				}
				
				int showIndex = 0;
				for(String fileName : dbList)
				{
					showIndex++;
					if(showIndex%1000 == 0)
						System.out.println("Now At "+showIndex);
					try{
						if(filesList.contains(fileName))
						{
							index++;
							//System.out.println(index+" --> "+fileName+" is found");
							sb.append(index+" --> "+fileName+" is found"+"\n");
						}
						else
						{
							nfi++;
							//System.out.println(nfi+" --> "+fileName+" is not found");
							sb2.append(nfi+" --> "+fileName+" is not found"+"\n");
						}
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			sb.append("------------------------------------------------------------");
			sb.append(sb2.toString());
			
			System.out.println("Total "+index+" Images are found & "+nfi+" Images are Not found");
			sb.append("Total "+index+" Images are found & "+nfi+" Images are Not found"+"\n");
			outwriter.write(sb.toString());
			outwriter.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
