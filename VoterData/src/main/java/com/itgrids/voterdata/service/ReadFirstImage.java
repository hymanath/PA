package com.itgrids.voterdata.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ReadFirstImage {
	
	public static void main(String[] args)
	{
		File mainDir = new File("D:\\Voter_Images_2016\\TS\\CID\\");
		
		/*if(mainDir.isDirectory())
		{
			for(File file : mainDir.listFiles())
				System.out.println(file.getName()+"\t"+file.listFiles()[0].listFiles()[0].getName());
		}*/
		
		Map<String,String> map = new HashMap<>(0);
		
		map.put("10","6-MUDHOLE");
		map.put("100","362-PALAKURTHI");
		map.put("101","86-DORNAKAL");
		map.put("102","91-MAHBUBABAD");
		map.put("103","93-NARSAMPET");
		map.put("104","94-PARKAL");
		map.put("105","363-WARANGAL WEST");
		map.put("106","364-WARANGAL EAST");
		map.put("107","97-WARDHANNAPET");
		map.put("108","365-BHUPALPALLE");
		map.put("109","92-MULUG");
		map.put("11","10-ARMOOR");
		map.put("110","324-PINAPAKA");
		map.put("112","101-KHAMMAM");
		map.put("113","104-PALAIR");
		map.put("114","103-MADHIRA");
		map.put("115","325-WYRA");
		map.put("116","105-SATHUPALLI");
		map.put("117","102-KOTHAGUDEM");
		map.put("118","326-ASWARAOPET");
		map.put("119","100-BHADRACHALAM");
		map.put("12","13-BODHAN");
		map.put("13","15-JUKKAL");
		map.put("14","12-BANSWADA");
		map.put("15","18-YELLAREDDY");
		map.put("16","16-KAMAREDDY");
		map.put("17","342-NIZAMABAD URBAN");
		map.put("18","343-NIZAMABAD RURAL");
		map.put("19","11-BALKONDA");
		map.put("20","321-KORATLA");
		map.put("21","23-JAGTIAL");
		map.put("22","322-DHARMAPURI");
		map.put("23","318-RAMAGUNDAM");
		map.put("24","26-MANTHANI");
		map.put("25","30-PEDDAPALLI");
		map.put("26","24-KARIMNAGAR");
		map.put("27","20-CHOPPADANDI");
		map.put("28","323-VEMULAWADA");
		map.put("29","31-SIRCILLA");
		map.put("30","319-MANAKONDUR");
		map.put("31","21-HUZURABAD");
		map.put("32","320-HUSNABAD");
		map.put("33","40-SIDDIPET");
		map.put("34","35-MEDAK");
		map.put("35","36-NARAYANKHED");
		map.put("36","32-ANDOLE");
		map.put("37","37-NARSAPUR");
		map.put("38","41-ZAHIRABAD");
		map.put("39","39-SANGAREDDY");
		map.put("40","337-PATANCHERU");
		map.put("41","336-DUBBAK");
		map.put("42","34-GAJWEL");
		map.put("43","57-MEDCHAL");
		map.put("44","367-MALKAJGIRI");
		map.put("45","345-QUTHBULLAPUR");
		map.put("46","346-KUKATPALLY");
		map.put("47","347-UPPAL");
		map.put("48","56-IBRAHIMPATNAM");
		map.put("49","348-LAL BAHADUR NAGAR");
		map.put("50","349-MAHESWARAM");
		map.put("51","351-RAJENDRANAGAR");
		map.put("52","350-SERLINGAMPALLY");
		map.put("53","55-CHEVELLA");
		map.put("54","58-PARGI");
		map.put("55","60-VIKARABAD");
		map.put("56","59-TANDUR");
		map.put("57","50-MUSHEERABAD");
		map.put("58","49-MALAKPET");
		map.put("59","315-AMBERPET");
		map.put("6","5-KHANAPUR");
		map.put("60","47-KHAIRATABAD");
		map.put("61","314-JUBILEE HILLS");
		map.put("62","51-SANATHNAGAR");
		map.put("63","316-NAMPALLY");
		map.put("64","46-KARWAN");
		map.put("65","317-GOSHAMAHAL");
		map.put("66","44-CHARMINAR");
		map.put("67","43-CHANDRAYANGUTTA");
		map.put("68","54-YAKUTPURA");
		map.put("69","313-BAHADURPURA");
		map.put("7","1-ADILABAD");
		map.put("70","52-SECUNDERABAD");
		map.put("71","53-SECUNDERABAD CANTONMENT");
		map.put("72","66-KODANGAL");
		map.put("73","335-NARAYANPET");
		map.put("74","68-MAHBUBNAGAR");
		map.put("75","64-JADCHERLA");
		map.put("76","369-DEVARKADRA");
		map.put("77","69-MAKTHAL");
		map.put("78","73-WANAPARTHY");
		map.put("79","63-GADWAL");
		map.put("8","3-BOATH");
		map.put("80","62-ALAMPUR");
		map.put("81","70-NAGARKURNOOL");
		map.put("82","61-ACHAMPET");
		map.put("83","65-KALWAKURTHI");
		map.put("84","71-SHADNAGAR");
		map.put("85","67-KOLLAPUR");
		map.put("86","77-DEVERKONDA");
		map.put("87","338-NAGARJUNA SAGAR");
		map.put("88","79-MIRYALGUDA");
		map.put("89","339-HUZURNAGAR");
		map.put("9","7-NIRMAL");
		map.put("90","78-KODAD");
		map.put("91","84-SURYAPET");
		map.put("92","82-NALGONDA");
		map.put("93","80-MUNGODE");
		map.put("94","75-BHONGIR");
		map.put("95","81-NAKREKAL");
		map.put("96","85-TUNGATURTHI");
		map.put("97","74-ALAIR");
		map.put("98","89-JANGAON");
		map.put("99","87-GHANPUR");
		
		if(mainDir.isDirectory())
		{
			/*for(File file : mainDir.listFiles())
				if(file.isDirectory())
				{	if(map.get(file.getName()) != null)
					{
						File nd = new File("D:\\Voter_Images_2016\\TS\\CID\\"+map.get(file.getName()));
						System.out.println(nd.getAbsolutePath());
						file.renameTo(nd);
					}
				}*/
			
			for(File file : mainDir.listFiles())
			if(file.isDirectory())
			{	File nd = new File("D:\\Voter_Images_2016\\TS\\CID\\"+file.getName().split("-")[0]);
				System.out.println(nd.getAbsolutePath());
				file.renameTo(nd);
			}
		}
	}

}
