package com.itgrids.voterdata.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class ImageComparator {


public void compareImages(String srcPath,String destPath,String copyPath)
{
	try{
	File sourceFolder = new File(srcPath);
	File destFolder = new File(destPath);
	List<String> duplicates = new ArrayList<String>(0);
	ImageStringConvert imageStringConvert = new ImageStringConvert();
	
	for(File file : sourceFolder.listFiles())
	{
		System.out.println("Reading From Source Folder --> "+file.getName());
		if(!duplicates.contains(file.getName()))
		{
			String srcStr = imageStringConvert.convertImageToBase64String(file.getAbsolutePath());
			for(File file2 : destFolder.listFiles())
			{
				System.out.println("Reading From Dest Folder --> "+file2.getName());
				if(!duplicates.contains(file2.getName()) && !file.getName().endsWith(file2.getName()))
				{
					String destStr = imageStringConvert.convertImageToBase64String(file2.getAbsolutePath());
					
					if(srcStr.equals(destStr))
					{
						System.out.println("Images --> "+file.getName()+" and "+file2.getName()+" are Same");
						duplicates.add(file.getName());
						duplicates.add(file2.getName());
					}
				}
			}
		}
	}
	
	if(duplicates.size() > 0)
	{
		System.out.println("--------------------------------------------------");
		for(String dup : duplicates)
		{	
			System.out.println(dup);
			FileUtils.copyFile(new File(srcPath+"\\"+dup), new File(copyPath+"\\"+dup));
		}
		System.out.println("--------------------------------------------------");
		
		
	}
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	
	public static void main(String[] args) {
		ImageComparator imageComparator = new ImageComparator();
		//imageComparator.compareImages("C:\\Users\\Kamalakar\\Desktop\\img\\img", "C:\\Users\\Kamalakar\\Desktop\\img\\img2",
			//	"C:\\Users\\Kamalakar\\Desktop\\img\\img3");
		imageComparator.rename();
	}
	
	public void rename()
	{
		try{
			Map<String,String> dupMap = new HashMap<String, String>(0);
			
			dupMap.put("AP21142156387.jpg","AP1419649367.jpg");
			dupMap.put("AP21142522543.jpg","AP1439493258.jpg");
			dupMap.put("UXN0096115.jpg","AP1481120105.jpg");
			dupMap.put("AP21142510768.jpg","AP1498628305.jpg");
			dupMap.put("AP21142156076.jpg","AP1415088195.jpg");
			dupMap.put("AP21142156202.jpg","AP1492919461.jpg");
			dupMap.put("UXN0108621.jpg","AP1447485735.jpg");
			dupMap.put("UXN0135863.jpg","AP1454374838.jpg");
			dupMap.put("UXN0718314.jpg","AP1482198575.jpg");
			dupMap.put("AP21142351338.jpg","AP1484992087.jpg");
			dupMap.put("UXN0974700.jpg","AP1438474716.jpg");
			dupMap.put("AP21142156493.jpg","AP1458980629.jpg");
			dupMap.put("UXN0248773.jpg","AP1438753426.jpg");
			dupMap.put("AP21142420023.jpg","AP1427534106.jpg");
			dupMap.put("UXN0955544.jpg","AP1411633129.jpg");
			dupMap.put("CWB2011765.jpg","AP1476093011.jpg");
			dupMap.put("UXN0732280.jpg","AP1451904613.jpg");
			dupMap.put("CWB2275089.jpg","AP1495087974.jpg");
			dupMap.put("UXN0119909.jpg","AP1472555337.jpg");
			dupMap.put("CWB2464600.jpg","AP1429683735.jpg");
			dupMap.put("CWB2638211.jpg","AP1438330042.jpg");
			dupMap.put("AP21142522296.jpg","AP1416351084.jpg");
			dupMap.put("AP21142522008.jpg","AP1479498646.jpg");
			dupMap.put("AP21142522658.jpg","AP1476350935.jpg");
			dupMap.put("AP21142111389.jpg","AP1452302332.jpg");
			dupMap.put("AP21142111484.jpg","AP1411771524.jpg");
			dupMap.put("AP21142111509.jpg","AP1494357974.jpg");
			dupMap.put("CWB2489151.jpg","AP1479396990.jpg");
			dupMap.put("CWB1920859.jpg","AP1415543622.jpg");
			dupMap.put("CWB2904837.jpg","AP1410708217.jpg");
			dupMap.put("AP21142105376.jpg","AP1440663037.jpg");
			dupMap.put("UXN0418822.jpg","AP1470217005.jpg");
			dupMap.put("AP21142105211.jpg","AP1487212446.jpg");
			dupMap.put("UXN0933029.jpg","AP1470790432.jpg");
			dupMap.put("AP21142105175.jpg","AP1430937363.jpg");
			dupMap.put("UXN0246785.jpg","AP1427804961.jpg");
			dupMap.put("AP21142522350.jpg","AP1436480645.jpg");
			dupMap.put("UXN0717894.jpg","AP1446312341.jpg");
			dupMap.put("AP211420090052.jpg","AP1425288514.jpg");
			dupMap.put("UXN0871039.jpg","AP1440620153.jpg");
			dupMap.put("AP211420522796.jpg","AP1444285933.jpg");
			dupMap.put("AP21142522630.jpg","AP1410221296.jpg");
			dupMap.put("CWB1371244.jpg","AP1460949199.jpg");
			dupMap.put("UXN0562314.jpg","AP1415512276.jpg");
			dupMap.put("UXN0368035.jpg","AP1494664979.jpg");
			dupMap.put("AP21142522320.jpg","AP1438135505.jpg");
			dupMap.put("CWB1370998.jpg","AP1424636936.jpg");
			dupMap.put("CWB1370824.jpg","AP1461124427.jpg");
			dupMap.put("AP21142522052.jpg","AP1453529501.jpg");
			dupMap.put("UXN0871187.jpg","AP1423152610.jpg");
			dupMap.put("AP21142522484.jpg","AP1468362851.jpg");
			dupMap.put("AP21142522321.jpg","AP1466393022.jpg");
			dupMap.put("AP211420522729.jpg","AP1421240269.jpg");
			dupMap.put("UXN0619387.jpg","AP1423573642.jpg");
			dupMap.put("AP21142351341.jpg","AP1425886324.jpg");
			dupMap.put("AP21142351112.jpg","AP1464418408.jpg");
			dupMap.put("AP21142351443.jpg","AP1413120412.jpg");
			dupMap.put("AP21142351289.jpg","AP1462391435.jpg");
			dupMap.put("UXN0789018.jpg","AP1458017607.jpg");
			dupMap.put("CWB1142702.jpg","AP1421401027.jpg");
			dupMap.put("UXN0025031.jpg","AP1489729119.jpg");
			dupMap.put("CWB1143494.jpg","AP1467497375.jpg");
			dupMap.put("AP21142396682.jpg","AP1419205795.jpg");
			dupMap.put("AP211420153011.jpg","AP1446520761.jpg");
			dupMap.put("UXN0181081.jpg","AP1482120474.jpg");
			dupMap.put("CWB1432418.jpg","AP1469890042.jpg");
			dupMap.put("AP21142549635.jpg","AP1424964338.jpg");
			dupMap.put("CWB2273753.jpg","AP1474829282.jpg");
			dupMap.put("UXN0499616.jpg","AP1486335944.jpg");
			dupMap.put("AP21142549443.jpg","AP1431023523.jpg");
			dupMap.put("UXN0051201.jpg","AP1480000806.jpg");
			dupMap.put("AP21142549265.jpg","AP1484449074.jpg");
			dupMap.put("AP211420549266.jpg","AP1452736751.jpg");
			dupMap.put("UXN0806085.jpg","AP1436762299.jpg");
			dupMap.put("AP21142549465.jpg","AP1494113420.jpg");
			dupMap.put("CWB1290683.jpg","AP1435301147.jpg");
			dupMap.put("UXN0881251.jpg","AP1462982458.jpg");
			dupMap.put("AP21142471486.jpg","AP1441886323.jpg");
			dupMap.put("CWB1291673.jpg","AP1417331371.jpg");
			dupMap.put("AP21142474150.jpg","AP1412167492.jpg");
			dupMap.put("AP21142411636.jpg","AP1414680531.jpg");
			dupMap.put("CWB1141076.jpg","AP1477802098.jpg");
			dupMap.put("UXN0415182.jpg","AP1444213439.jpg");
			dupMap.put("UXN0767642.jpg","AP1423532205.jpg");
			dupMap.put("UXN0767659.jpg","AP1428393455.jpg");
			dupMap.put("AP211420342425.jpg","AP1456583166.jpg");
			dupMap.put("AP211420342077.jpg","AP1435190678.jpg");
			dupMap.put("UXN0415356.jpg","AP1496672352.jpg");
			dupMap.put("UXN0414532.jpg","AP1457423405.jpg");
			dupMap.put("UXN0427336.jpg","AP1414983123.jpg");
			dupMap.put("UXN0807737.jpg","AP1460900640.jpg");
			dupMap.put("AP21142336200.jpg","AP1420383013.jpg");
			dupMap.put("CWB1981836.jpg","AP1458044295.jpg");
			dupMap.put("CWB2471043.jpg","AP1465099457.jpg");
			dupMap.put("AP21142336240.jpg","AP1443415212.jpg");
			dupMap.put("AP211420342009.jpg","AP1460317083.jpg");
			dupMap.put("UXN0168823.jpg","AP1410728991.jpg");
			dupMap.put("AP211420522810.jpg","AP1420126308.jpg");
			dupMap.put("AP21142105473.jpg","AP1494119958.jpg");
			dupMap.put("CWB1902220.jpg","AP1482544108.jpg");
			dupMap.put("AP21142105073.jpg","AP1457864170.jpg");
			dupMap.put("AP21142105204.jpg","AP1417533018.jpg");
			dupMap.put("UXN0169003.jpg","AP1491741664.jpg");
			dupMap.put("UXN0096610.jpg","AP1440482473.jpg");
			dupMap.put("AP21142549638.jpg","AP1423036464.jpg");
			dupMap.put("UXN0116343.jpg","AP1490445071.jpg");
			dupMap.put("AP21142510640.jpg","AP1418616364.jpg");
			dupMap.put("AP21142513662.jpg","AP1464700595.jpg");
			dupMap.put("AP21142510053.jpg","AP1473521535.jpg");
			dupMap.put("AP21142513024.jpg","AP1498535953.jpg");
			dupMap.put("UXN0167767.jpg","AP1413025764.jpg");
			dupMap.put("UXN0116277.jpg","AP1453854066.jpg");
			dupMap.put("AP21142513144.jpg","AP1439722766.jpg");
			dupMap.put("AP21142513092.jpg","AP1480449150.jpg");
			dupMap.put("AP21142513047.jpg","AP1430548096.jpg");
			dupMap.put("CWB2430395.jpg","AP1484539541.jpg");
			dupMap.put("CWB1353556.jpg","AP1459878643.jpg");
			dupMap.put("AP21142510778.jpg","AP1435858426.jpg");
			dupMap.put("AP21142513046.jpg","AP1448027080.jpg");
			dupMap.put("CWB2426146.jpg","AP1465095237.jpg");
			dupMap.put("CWB1353309.jpg","AP1432274693.jpg");
			dupMap.put("UXN0471136.jpg","AP1449984336.jpg");
			dupMap.put("AP21142510065.jpg","AP1475686686.jpg");
			dupMap.put("UXN0090068.jpg","AP1443180554.jpg");
			dupMap.put("AP211420159530.jpg","AP1442361780.jpg");
			dupMap.put("CWB1352764.jpg","AP1492672081.jpg");
			dupMap.put("AP21142513063.jpg","AP1417284896.jpg");
			dupMap.put("AP21142513747.jpg","AP1433680849.jpg");
			dupMap.put("UXN0025353.jpg","AP1498646243.jpg");
			dupMap.put("AP21142396412.jpg","AP1481173353.jpg");
			dupMap.put("UXN0856262.jpg","AP1439979013.jpg");
			dupMap.put("AP21142396034.jpg","AP1429701793.jpg");
			dupMap.put("AP21142522388.jpg","AP1480926671.jpg");
			dupMap.put("AP21142336356.jpg","AP1431891792.jpg");
			dupMap.put("UXN0047498.jpg","AP1446432877.jpg");
			dupMap.put("AP211420225302.jpg","AP1421095431.jpg");
			dupMap.put("AP211420225401.jpg","AP1442634053.jpg");
			dupMap.put("AP211420225331.jpg","AP1435227552.jpg");
			dupMap.put("UXN0142455.jpg","AP1410212909.jpg");
			dupMap.put("AP211420225363.jpg","AP1446419693.jpg");
			dupMap.put("CWB2456135.jpg","AP1419511852.jpg");
			dupMap.put("AP211420225391.jpg","AP1413311679.jpg");
			dupMap.put("AP211420225216.jpg","AP1444468184.jpg");
			dupMap.put("AP21142396477.jpg","AP1452197618.jpg");
			dupMap.put("AP21142339034.jpg","AP1434160260.jpg");
			dupMap.put("AP211420153176.jpg","AP1493637262.jpg");
			dupMap.put("AP211420153013.jpg","AP1470900939.jpg");
			dupMap.put("CWB1751833.jpg","AP1487485733.jpg");
			dupMap.put("AP21142345503.jpg","AP1416238035.jpg");
			dupMap.put("UXN0464677.jpg","AP1455580220.jpg");
			dupMap.put("AP21142345503.jpg","AP1494774987.jpg");
			dupMap.put("UXN0367946.jpg","AP1420672570.jpg");
			dupMap.put("AP21142345503.jpg","AP1482582602.jpg");
			dupMap.put("UXN0265413.jpg","AP1452956556.jpg");
			dupMap.put("UXN0140251.jpg","AP1491336940.jpg");
			dupMap.put("UXN0766180.jpg","AP1483220431.jpg");
			dupMap.put("AP211420153010.jpg","AP1466305324.jpg");
			dupMap.put("AP21142510761.jpg","AP1450499018.jpg");
			dupMap.put("UXN0138081.jpg","AP1474750994.jpg");
			dupMap.put("UXN0414680.jpg","AP1454368600.jpg");
			dupMap.put("UXN0656322.jpg","AP1498515087.jpg");
			dupMap.put("UXN0413575.jpg","AP1468253861.jpg");
			dupMap.put("AP211420168240.jpg","AP1441331761.jpg");
			dupMap.put("AP21142408579.jpg","AP1428210368.jpg");
			dupMap.put("UXN0282780.jpg","AP1463302412.jpg");
			dupMap.put("AP21142219426.jpg","AP1485460457.jpg");
			dupMap.put("AP211420219590.jpg","AP1476850527.jpg");
			dupMap.put("UXN0400044.jpg","AP1490987104.jpg");
			dupMap.put("AP211420192198.jpg","AP1483676950.jpg");
			dupMap.put("UXN0141168.jpg","AP1489491633.jpg");
			dupMap.put("UXN0175273.jpg","AP1466361326.jpg");
			dupMap.put("AP211420192550.jpg","AP1496093122.jpg");
			dupMap.put("UXN0692962.jpg","AP1454893942.jpg");
			dupMap.put("UXN0126284.jpg","AP1464895737.jpg");
			dupMap.put("UXN0222448.jpg","AP1445080329.jpg");
			dupMap.put("CWB1611094.jpg","AP1448512605.jpg");
			dupMap.put("UXN0951088.jpg","AP1414112858.jpg");
			dupMap.put("CWB1680677.jpg","AP1484201526.jpg");
			dupMap.put("AP211420411251.jpg","AP1465710630.jpg");
			dupMap.put("UXN0868622.jpg","AP1480980773.jpg");
			dupMap.put("UXN0049734.jpg","AP1417430691.jpg");
			dupMap.put("UXN0380246.jpg","AP1475980451.jpg");
			dupMap.put("AP211420414284.jpg","AP1496236870.jpg");
			dupMap.put("UXN0674820.jpg","AP1459029492.jpg");
			dupMap.put("UXN0068908.jpg","AP1467256568.jpg");
			dupMap.put("AP21142402373.jpg","AP1466231816.jpg");
			dupMap.put("AP21142558175.jpg","AP1433187277.jpg");
			dupMap.put("AP21142558585.jpg","AP1467404199.jpg");
			
			File dir = new File("C:\\Users\\Kamalakar\\Desktop\\img\\VoterImages");
			
			for(File file : dir.listFiles())
			{
				FileUtils.moveFile(file, new File("C:\\Users\\Kamalakar\\Desktop\\img\\NewImages\\"+dupMap.get(file.getName())));
			}
			
			
		}catch(Exception e)
		{
			
		}
	}

}