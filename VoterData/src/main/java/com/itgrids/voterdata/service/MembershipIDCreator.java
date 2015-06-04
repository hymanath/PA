package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MembershipIDCreator {

	public static void main(String[] args)
	{
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File(args[0]+"/data.txt")));
			String line = null;
			int index = 0;
			while((line = br.readLine()) != null)
			{
				try{
					index++;
					BufferedWriter outwriter = new BufferedWriter(new FileWriter(new File(args[0]+"/"+index+".html")));
					outwriter.write(line);
					outwriter.close();
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
}
