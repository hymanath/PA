package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MuslimDataUpadte {
	
	public static void main(String[] args) {
		try{
			MuslimDataUpadte mdu = new MuslimDataUpadte();

			File constituencyFolder = new File(args[0]);
			{
				if(constituencyFolder.isDirectory())
				{
					mdu.readMuslimData(constituencyFolder.getAbsolutePath());
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public int readMuslimData(String filePath)
	{
		int count = 0;
		try{
			File file2 = new File(filePath);
			
			if(file2.isDirectory())
			{
				BufferedWriter outwriter = new BufferedWriter(new FileWriter(file2.getAbsolutePath()+"_Muslim_Mobiles.txt"));
				StringBuilder sb = new StringBuilder();
				List<String> mobileNosList = new ArrayList<String>(0);
				
				for(File file3 : file2.listFiles())
				{
					System.out.println("Reading Mandal File -- "+file3.getAbsolutePath());
					
					if(file3.isDirectory())
					{
						for(File file4 : file3.listFiles())
						{
							System.out.println("Reading Village Folder - "+file4.getAbsolutePath());
							if(file4.isDirectory())
							{
								for(File file5 : file4.listFiles())
								{
									if(file5.isDirectory())
									{
										for(File file6 : file5.listFiles())
										{
											if(!file6.isDirectory())
											{
												System.out.println("Reading File -->"+file6.getAbsolutePath());
												BufferedReader br =  new BufferedReader(new FileReader(file6));
												String line = null;
												
												while((line = br.readLine()) != null)
												{
													if(!mobileNosList.contains(line.trim()))
														mobileNosList.add(line.trim());
												}
												br.close();
											}
										}
									}
								}
							}
						}
					}
					
				}
				
				if(mobileNosList != null && mobileNosList.size() > 0)
				{
					for(String mobile : mobileNosList)
					{
						sb.append(mobile+"\n");
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
	
	public int getConstituencyId(String cname)
	{
		try{
			Map<String,Integer> map = new HashMap<String, Integer>(0);
			map.put("ADILABAD",1);
			map.put("ASIFABAD",2);
			map.put("BOATH",3);
			map.put("CHENNUR",4);
			map.put("KHANAPUR",5);
			map.put("MUDHOLE",6);
			map.put("NIRMAL",7);
			map.put("SIRPUR",8);
			map.put("ARMOOR",10);
			map.put("BALKONDA",11);
			map.put("BANSWADA",12);
			map.put("BODHAN",13);
			map.put("JUKKAL",15);
			map.put("KAMAREDDY",16);
			map.put("YELLAREDDY",18);
			map.put("CHOPPADANDI",20);
			map.put("HUZURABAD",21);
			map.put("JAGTIAL",23);
			map.put("KARIMNAGAR",24);
			map.put("MANTHANI",26);
			map.put("PEDDAPALLI",30);
			map.put("SIRCILLA",31);
			map.put("ANDOLE",32);
			map.put("GAJWEL",34);
			map.put("MEDAK",35);
			map.put("NARAYANKHED",36);
			map.put("NARSAPUR",37);
			map.put("SANGAREDDY",39);
			map.put("SIDDIPET",40);
			map.put("ZAHIRABAD",41);
			map.put("CHANDRAYANGUTTA",43);
			map.put("CHARMINAR",44);
			map.put("KARWAN",46);
			map.put("KHAIRATABAD",47);
			map.put("MALAKPET",49);
			map.put("MUSHEERABAD",50);
			map.put("SANATHNAGAR",51);
			map.put("SECUNDERABAD",52);
			map.put("SECUNDERABAD CANTONMENT",53);
			map.put("YAKUTPURA",54);
			map.put("CHEVELLA",55);
			map.put("IBRAHIMPATNAM",56);
			map.put("MEDCHAL",57);
			map.put("PARGI",58);
			map.put("TANDUR",59);
			map.put("VIKARABAD",60);
			map.put("ACHAMPET",61);
			map.put("ALAMPUR",62);
			map.put("GADWAL",63);
			map.put("JADCHERLA",64);
			map.put("KALWAKURTHI",65);
			map.put("KODANGAL",66);
			map.put("KOLLAPUR",67);
			map.put("MAHBUBNAGAR",68);
			map.put("MAKTHAL",69);
			map.put("NAGARKURNOOL",70);
			map.put("SHADNAGAR",71);
			map.put("WANAPARTHY",73);
			map.put("ALAIR",74);
			map.put("BHONGIR",75);
			map.put("DEVERKONDA",77);
			map.put("KODAD",78);
			map.put("MIRYALGUDA",79);
			map.put("MUNGODE",80);
			map.put("NAKREKAL",81);
			map.put("NALGONDA",82);
			map.put("SURYAPET",84);
			map.put("TUNGATURTHI",85);
			map.put("DORNAKAL",86);
			map.put("GHANPUR",87);
			map.put("JANGAON",89);
			map.put("MAHBUBABAD",91);
			map.put("MULUG",92);
			map.put("NARSAMPET",93);
			map.put("PARKAL",94);
			map.put("WARDHANNAPET",97);
			map.put("BHADRACHALAM",100);
			map.put("KHAMMAM",101);
			map.put("KOTHAGUDEM",102);
			map.put("MADHIRA",103);
			map.put("PALAIR",104);
			map.put("SATHUPALLI",105);
			map.put("YELLANDU",107);
			map.put("AMADALAVALASA",108);
			map.put("ETCHERLA",109);
			map.put("ICHAPURAM",111);
			map.put("NARASANNAPETA",112);
			map.put("PALAKONDA",113);
			map.put("PATHAPATNAM",114);
			map.put("SRIKAKULAM",116);
			map.put("TEKKALI",117);
			map.put("CHEEPURUPALLI",120);
			map.put("GAJAPATHINAGARAM",121);
			map.put("BOBBILI",122);
			map.put("PARVATHIPURAM",124);
			map.put("SALUR",125);
			map.put("SRUNGAVARAPUKOTA",127);
			map.put("VIZIANAGARAM",129);
			map.put("ANAKAPALLI",133);
			map.put("CHODAVARAM",134);
			map.put("ELAMANCHILI",135);
			map.put("MADUGULA",136);
			map.put("NARSIPATNAM",137);
			map.put("PADERU",138);
			map.put("PAYAKARAOPETA",140);
			map.put("PENDURTHI",141);
			map.put("Amalapuram",146);
			map.put("ANAPARTHY",147);
			map.put("JAGGAMPETA",149);
			map.put("KOTHAPETA",152);
			map.put("MUMMIDIVARAM",153);
			map.put("PEDDAPURAM",155);
			map.put("PITHAPURAM",156);
			map.put("PRATHIPAD",157);
			map.put("RAMACHANDRAPURAM",159);
			map.put("RAZOLE",160);
			map.put("TUNI",163);
			map.put("BHIMAVARAM",167);
			map.put("CHINTALAPUDI",168);
			map.put("DENDULUR",169);
			map.put("ELURU",170);
			map.put("GOPALPURAM",171);
			map.put("KOVVUR",172);
			map.put("NARASAPUR",173);
			map.put("PALACOLE",174);
			map.put("POLAVARAM",176);
			map.put("TADEPALLIGUDEM",177);
			map.put("TANUKU",178);
			map.put("UNDI",179);
			map.put("UNGUTUR",180);
			map.put("ACHANTA",181);
			map.put("AVANIGADDA",182);
			map.put("GANNAVARAM",184);
			map.put("GUDIVADA",185);
			map.put("JAGGAYYAPET",186);
			map.put("KAIKALUR",187);
			map.put("MYLAVARAM",191);
			map.put("NANDIGAMA",192);
			map.put("NUZVID",193);
			map.put("TIRUVURU",194);
			map.put("VIJAYAWADA EAST",195);
			map.put("VIJAYAWADA WEST",196);
			map.put("CHILAKALURIPET",199);
			map.put("GURZALA",203);
			map.put("MACHERLA",205);
			map.put("MANGALAGIRI",206);
			map.put("VINUKONDA",207);
			map.put("NARASARAOPET",208);
			map.put("BAPATLA",209);
			map.put("PEDDAKURAPADU",210);
			map.put("PONNUR",211);
			map.put("PRATHIPADU",212);
			map.put("REPALLE",213);
			map.put("SATTENAPALLI",214);
			map.put("TADIKONDA",215);
			map.put("TENALI",216);
			map.put("VEMURU",217);
			map.put("ADDANKI",218);
			map.put("CHIRALA",219);
			map.put("DARSI",221);
			map.put("GIDDALUR",222);
			map.put("KANDUKUR",223);
			map.put("KANIGIRI",224);
			map.put("KONDEPI",225);
			map.put("MARKAPUR",226);
			map.put("ONGOLE",227);
			map.put("PARCHUR",228);
			map.put("SANTHANUTHALAPADU",229);
			map.put("GUDUR",231);
			map.put("KAVALI",232);
			map.put("KOVUR",233);
			map.put("SARVEPALLI",236);
			map.put("SULLURPET",237);
			map.put("UDAYAGIRI",238);
			map.put("VENKATAGIRI",239);
			map.put("ATMAKUR",241);
			map.put("BADVEL",242);
			map.put("KADAPA",243);
			map.put("JAMMALAMADUGU",244);
			map.put("KAMALAPURAM",245);
			map.put("KODUR",246);
			map.put("RAYACHOTY",248);
			map.put("MYDUKUR",249);
			map.put("PRODDATUR",250);
			map.put("PULIVENDLA",251);
			map.put("RAJAMPET",252);
			map.put("ADONI",253);
			map.put("ALLAGADDA",254);
			map.put("ALUR",255);
			map.put("DHONE",257);
			map.put("KODUMUR",258);
			map.put("KURNOOL",260);
			map.put("NANDIKOTKUR",261);
			map.put("NANDYAL",262);
			map.put("PANYAM",263);
			map.put("PATTIKONDA",264);
			map.put("YEMMIGANUR",265);
			map.put("DHARMAVARAM",267);
			map.put("HINDUPUR",270);
			map.put("KADIRI",271);
			map.put("KALYANDURG",272);
			map.put("MADAKASIRA",273);
			map.put("PENUKONDA",275);
			map.put("RAYADURG",276);
			map.put("SINGANAMALA",277);
			map.put("TADPATRI",278);
			map.put("URAVAKONDA",279);
			map.put("CHANDRAGIRI",280);
			map.put("CHITTOOR",281);
			map.put("KUPPAM",282);
			map.put("NAGARI",283);
			map.put("Palamaner",284);
			map.put("PILERU",285);
			map.put("PUNGANUR",286);
			map.put("SATYAVEDU",288);
			map.put("SRIKALAHASTI",289);
			map.put("THAMBALLAPALLE",290);
			map.put("TIRUPATI",291);
			map.put("MADANPALLE",294);
			map.put("BELLAMPALLI",295);
			map.put("MANCHERIAL",296);
			map.put("GUNTAKAL",297);
			map.put("ANANTAPUR URBAN",298);
			map.put("RAPTADU",299);
			map.put("PUTTAPARTHI",300);
			map.put("GANGADHARA NELLORE",301);
			map.put("PUTHALAPATTU",302);
			map.put("RAJANAGARAM",303);
			map.put("RAJAHMUNDRY CITY",304);
			map.put("RAJAHMUNDRY RURAL",305);
			map.put("RAMPACHODAVARAM",306);
			map.put("KAKINADA RURAL",307);
			map.put("KAKINADA CITY",308);
			map.put("MANDAPETA",309);
			map.put("GANNAVARAM",310);
			map.put("GUNTUR EAST",311);
			map.put("GUNTUR WEST",312);
			map.put("BAHADURPURA",313);
			map.put("JUBILEE HILLS",314);
			map.put("AMBERPET",315);
			map.put("NAMPALLY",316);
			map.put("GOSHAMAHAL",317);
			map.put("RAMAGUNDAM",318);
			map.put("MANAKONDUR",319);
			map.put("HUSNABAD",320);
			map.put("KORATLA",321);
			map.put("DHARMAPURI",322);
			map.put("VEMULAWADA",323);
			map.put("PINAPAKA",324);
			map.put("WYRA",325);
			map.put("ASWARAOPET",326);
			map.put("PEDANA",327);
			map.put("MACHILIPATNAM",328);
			map.put("PAMARRU",329);
			map.put("PENAMALURU",330);
			map.put("VIJAYAWADA CENTRAL",331);
			map.put("SRISAILAM",332);
			map.put("BANAGANAPALLE",333);
			map.put("MANTRALAYAM",334);
			map.put("NARAYANPET",335);
			map.put("DUBBAK",336);
			map.put("PATANCHERU",337);
			map.put("NAGARJUNA SAGAR",338);
			map.put("HUZURNAGAR",339);
			map.put("NELLORE CITY",340);
			map.put("NELLORE RURAL",341);
			map.put("NIZAMABAD URBAN",342);
			map.put("NIZAMABAD RURAL",343);
			map.put("YERRAGONDAPALEM",344);
			map.put("QUTHBULLAPUR",345);
			map.put("KUKATPALLY",346);
			map.put("UPPAL",347);
			map.put("LAL BAHADUR NAGAR",348);
			map.put("MAHESWARAM",349);
			map.put("SERLINGAMPALLY",350);
			map.put("RAJENDRANAGAR",351);
			map.put("PALASA",352);
			map.put("RAJAM",353);
			map.put("VISAKHAPATNAM EAST",354);
			map.put("VISAKHAPATNAM SOUTH",355);
			map.put("VISAKHAPATNAM NORTH",356);
			map.put("VISAKHAPATNAM WEST",357);
			map.put("GAJUWAKA",358);
			map.put("ARAKU VALLEY",359);
			map.put("KURUPAM",360);
			map.put("NELLIMARLA",361);
			map.put("PALAKURTHI",362);
			map.put("WARANGAL WEST",363);
			map.put("WARANGAL EAST",364);
			map.put("BHUPALPALLE",365);
			map.put("NIDADAVOLE",366);
			map.put("MALKAJGIRI",367);
			map.put("BHIMILI",368);
			map.put("DEVARKADRA",369);
			return map.get(cname);
		}catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

}
