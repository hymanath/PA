package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class CadreImagesMover {

	public static void main(String[] args)
	{
		CadreImagesMover mover = new CadreImagesMover();
		mover.moveFiles(args[0],args[1],args[2],args[3]);
	}
	public void moveFiles(String srcDirPath,String destDirPath,String dbSrcPath,String resultFilePath)
	{
		try{
			File srcDir = new File(srcDirPath);
			File db = new File(dbSrcPath);
			File result = new File(resultFilePath);
			
			BufferedReader br = new BufferedReader(new FileReader(db));
			BufferedWriter outwriter = new BufferedWriter(new FileWriter(result));
			
			StringBuilder sb = new StringBuilder();
			
			String line = null;
			List<String> dbList = new ArrayList<String>(0);
			
			while((line = br.readLine()) != null)
				dbList.add(line);
			
			br.close();
			
			int index = 0;
			int nfi = 0;
			if(srcDir.isDirectory())
			{
				for(File f : srcDir.listFiles())
				{
					try{
						if(!f.isDirectory())
						{
							if(dbList.contains(f.getName()))
							{
								index++;
								if(f.renameTo(new File(destDirPath+"/"+f.getName())))
								{
									System.out.println(index+" --> "+f.getName()+" is Moved");
									System.out.println(srcDir.getAbsolutePath()+"/"+f.getName());
									sb.append(index+" --> "+f.getName()+" is Moved"+"\n");
								}
								else
								{
									System.out.println(index+" --> "+f.getName()+" is Not Moved");
								}
							}
							else
							{
								nfi++;
								System.out.println(nfi+" --> "+f.getName()+" is not found");
								sb.append(nfi+" --> "+f.getName()+" is not found"+"\n");
							}
						}
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			System.out.println("Total "+index+" Images are Moved & "+nfi+" Images are Not Moved");
			sb.append("Total "+index+" Images are Moved & "+nfi+" Images are Not Moved"+"\n");
			outwriter.write(sb.toString());
			outwriter.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
