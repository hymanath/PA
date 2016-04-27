package com.itgrids.paymentgateway.soundex;



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
			File file = new File("C:\\Users\\Client19\\Desktop\\names.txt"); 
			File result = new File("C:\\Users\\Client19\\Desktop\\result.txt"); 
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedWriter outWriter = new BufferedWriter(new FileWriter(result));
			List<String> namesList = new ArrayList<String>();
			List<String> resultList = new ArrayList<String>();
			String line = null; 
			RefinedSoundex soundex = new RefinedSoundex();
			
			while((line = br.readLine()) != null)
            {
				namesList.add(line.toString());
            }
			
			System.out.println("1");
			for(String name : namesList)
			{
				try{
					boolean flag = false;
					String nameSound = soundex.soundex(name.trim());
					System.out.println(nameSound);
					for(String resName:resultList)
					{
						String resSound = soundex.soundex(resName.trim());
						System.out.println(resSound);
						if(nameSound.equalsIgnoreCase(resSound))
						{
							System.out.println(name.trim()+" Equals ==> "+resName.trim());
							flag = true;
						}
					}
					if(!flag)
					{
						resultList.add(name.trim());
						System.out.println(name.trim());
						outWriter.write(name.trim());
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			br.close();
			outWriter.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
