package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.language.RefinedSoundex;

public class SoundexAlg {

	public static void main(String[] args)
	{
		try{
			System.out.println("kamal");
			File file = new File("C:\\Users\\Kamalakar\\Desktop\\names.txt"); 
			File result = new File("C:\\Users\\Kamalakar\\Desktop\\result.txt"); 
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedWriter outWriter = new BufferedWriter(new FileWriter(result));
			List<String> namesList = new ArrayList<String>();
			String line = null; 
			StringBuilder sb = new StringBuilder();
			RefinedSoundex soundex = new RefinedSoundex();
			
			while((line = br.readLine()) != null)
            {
				namesList.add(line.toString());
            }
			
			for(String name : namesList)
			{
				try{
					String nameSound = soundex.soundex(name.trim());
					System.out.println(name.trim()+"\t"+nameSound+"\t"+nameSound.replaceAll("0",""));
					sb.append(name.trim()+"\t"+nameSound+"\t"+nameSound.replaceAll("0","")+"\n");
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			br.close();
			outWriter.write(sb.toString());
			outWriter.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
