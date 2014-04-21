package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class CombineMobileNosDataForDB {
	
	public static void main(String[] args) {
		try{
			CombineMobileNosDataForDB combineMobileNosDataForDB = new CombineMobileNosDataForDB();

			File districtFolder = new File(args[0]);
			{
				if(districtFolder.isDirectory())
				{
					for(File file : districtFolder.listFiles())
					if(file.isDirectory())
					{
						try{
							combineMobileNosDataForDB.readAndCombineMobileData(file.getAbsolutePath(),districtFolder.getName());
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
	
	public int readAndCombineMobileData(String filePath,String district)
	{
		int count = 0;
		try{
			File file = new File(filePath);
			
			if(file.isDirectory())
			{
				System.out.println("Reading File -- "+filePath);
				String constituency = file.getName();
				BufferedReader br = null;
				BufferedWriter outwriter = new BufferedWriter(new FileWriter(file.getAbsolutePath()+"_FULL"));
				
				String line = null;
				StringBuilder sb = new StringBuilder();
				
				for(File data : file.listFiles())
				{
					try{
					if(data.isDirectory())
					{
						String mandal = data.getName();
						for(File data2 : data.listFiles())
						{
							try{
							String village = data2.getName().substring(0,data2.getName().indexOf("-"));
							//System.out.println("Reading --> "+data2.getAbsolutePath());
							br = new BufferedReader(new FileReader(data2));
							while((line = br.readLine()) != null)
							{
								int index = line.indexOf("|");
								if(index != -1)
								{
									String[] d = line.split("\\|");
									sb.append(district+"\t"+constituency+"\t"+mandal+"\t"+village+"\t"+line.substring(0,index).trim());
									if(d.length >= 2)
										sb.append("\t"+d[1]);
									if(d.length >= 3)
										sb.append("\t"+d[2]);
									sb.append("\n");
								}
								/*if(index != -1)
								{
									String[] d = line.split("\\|");
									if(d.length >= 3)
										sb.append(line.substring(0,index).trim()+"\t"+d[1]+"\t"+d[2]+"\t"+constituency+"\t"+mandal+"\t"+village+"\n");
								}*/
							}
							br.close();
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
