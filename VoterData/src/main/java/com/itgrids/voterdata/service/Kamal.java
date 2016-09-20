package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections.map.HashedMap;


public class Kamal {

	/*public static void main(String[] args) throws Exception
	{
		BarcodeEAN codeEAN = new BarcodeEAN();
		codeEAN.setCodeType(codeEAN.EAN13);
		codeEAN.setCode("9780201615883");
		OutputStream os = new FileOutputStream(new File("C:\\Users\\Kamalakar\\Desktop\\kamal.jpg"));
		PdfWriter pw = PdfWriter.getInstance(new PdfDocument(),os);
		Image imageEAN = codeEAN.createImageWithBarcode(new PdfContentByte(pw), null, null);
	}*/
	
	public static void main(String[] args) throws Exception
	{
		Kamal kamal = new Kamal();
		
		Map<Integer,Integer> map = new HashMap<Integer,Integer>(0);
		
		map.put(1,195);
		map.put(2,206);
		map.put(3,163);
		map.put(4,181);
		map.put(5,196);
		map.put(6,211);
		map.put(7,189);
		map.put(8,208);
		map.put(9,195);
		map.put(10,209);
		map.put(11,173);
		map.put(12,199);
		map.put(13,184);
		map.put(14,189);
		map.put(15,182);
		map.put(16,172);
		map.put(17,135);
		map.put(18,149);
		map.put(19,151);
		map.put(20,176);
		map.put(21,192);
		map.put(22,212);
		map.put(23,202);
		map.put(24,210);
		map.put(25,204);
		map.put(26,174);
		map.put(27,176);
		map.put(28,186);
		map.put(29,211);
		map.put(30,230);
		map.put(31,239);
		map.put(32,223);
		map.put(33,216);
		map.put(34,158);
		map.put(35,158);
		map.put(36,169);
		map.put(37,198);
		map.put(38,213);
		map.put(39,240);
		map.put(40,212);
		map.put(41,211);
		map.put(42,211);
		map.put(43,192);
		map.put(44,201);
		map.put(45,227);
		map.put(46,181);
		map.put(47,193);
		map.put(48,184);
		map.put(49,205);
		map.put(50,191);
		map.put(51,203);
		map.put(52,224);
		map.put(53,221);
		map.put(54,173);
		map.put(55,190);
		map.put(56,150);
		map.put(57,182);
		map.put(58,203);
		map.put(59,224);
		map.put(60,182);
		map.put(61,141);
		map.put(62,161);
		map.put(63,143);
		map.put(64,192);
		map.put(65,172);
		map.put(66,262);
		map.put(67,196);
		map.put(68,213);
		map.put(69,171);
		map.put(70,181);
		map.put(71,195);
		map.put(72,163);
		map.put(73,190);
		map.put(74,188);
		map.put(75,218);
		map.put(76,187);
		map.put(77,222);
		map.put(78,198);
		map.put(79,223);
		map.put(80,184);
		map.put(81,156);
		map.put(82,171);
		map.put(83,159);
		map.put(84,155);
		map.put(85,165);
		map.put(86,176);
		map.put(87,166);
		map.put(88,157);
		map.put(89,152);
		map.put(90,165);
		map.put(91,183);
		map.put(92,203);
		map.put(93,155);
		map.put(94,159);
		map.put(95,165);
		map.put(96,189);
		map.put(97,174);
		map.put(98,184);
		map.put(99,166);
		map.put(100,176);
		map.put(101,194);
		map.put(102,158);
		map.put(103,162);
		map.put(104,159);
		map.put(105,168);
		map.put(106,179);
		map.put(107,166);
		map.put(108,192);
		map.put(109,196);
		map.put(110,203);
		map.put(111,180);
		map.put(112,240);
		map.put(113,181);
		map.put(114,226);
		map.put(115,170);
		map.put(116,188);
		map.put(117,186);

		kamal.getPartNos(map,26);
	}
	
	public void getPartNos(Map<Integer,Integer> map,int count)
	{
		try{
			Random r = new Random();
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("C:\\Users\\Kamalakar\\Desktop\\PublicPulse\\partNos.txt")));
			for(Map.Entry<Integer,Integer> entry : map.entrySet())
			{
				int cno = entry.getKey();
				int booths = entry.getValue();
				int separator = booths/count;
				
				int partNo = (r.nextInt()%separator)+1;
				if(partNo < 0)
					partNo = 1;
				
				for(int i=1;i<=count;i++)
				{
					System.out.println("CNO - "+cno+"\t Part No - "+partNo);
					writer.write("CNO - "+cno+"\t Part No - "+partNo+"\n");
					partNo = partNo+separator;
				}
				
			}
			writer.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}	
