package com.itgrids.voterdata.service;

import java.io.File;

public class BoothDataReaderForAP2014MAYState {

	public static void main(String[] args)
	{
		try{
			File state = new File(args[0]);
			
			if(state.isDirectory())
			{
				for(File district : state.listFiles())
				{
					try{
						if(district.isDirectory())
						{
							String districtStr = district.getName();
							for(File constituency : district.listFiles())
							{
								try{
								if(constituency.isDirectory())
								{
									String[] arr = new String[2];
									arr[0] = constituency.getAbsolutePath();
									arr[1] = districtStr;
									BoothDataReaderForAP2014MAY.main(arr);
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
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
