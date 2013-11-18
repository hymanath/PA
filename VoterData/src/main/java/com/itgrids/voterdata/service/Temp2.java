package com.itgrids.voterdata.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Temp2 {
	
	public static void main(String[] args)
	{
		File file = new File("D:\\Server_Dump\\Temp");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String fileName = sdf.format(date);
		file.renameTo(new File("D:\\Server_Dump\\"+fileName));
		File dir = new File("D:\\Server_Dump\\Temp");
		dir.mkdir();
	}
}
