package com.itgrids.partyanalyst.util;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.CadreLevel;

public class DummyCadreData {
	@SuppressWarnings("unchecked")
	public static List getStates(){
		List states = new ArrayList();
		Object[] obj1 = {new Long(1), "AP"};
		Object[] obj2 = {new Long(2), "UP"};
		Object[] obj3 = {new Long(3), "MP"};
		states.add(obj1); states.add(obj2); states.add(obj3);
		return states;
	}
	
	@SuppressWarnings("unchecked")
	public static List getDistricts(){
		List districts = new ArrayList();
		Object[] obj10 = {new Long(10), "AP_Dist1"};
		Object[] obj11 = {new Long(11), "AP_Dist2"};
		Object[] obj12 = {new Long(12), "AP_Dist3"};
		Object[] obj13 = {new Long(13), "AP_Dist4"};
		Object[] obj14 = {new Long(14), "AP_Dist5"};
		Object[] obj21 = {new Long(21), "UP_Dist1"};
		Object[] obj22 = {new Long(22), "UP_Dist2"};
		Object[] obj23 = {new Long(23), "UP_Dist3"};
		Object[] obj24 = {new Long(24), "UP_Dist4"};
		Object[] obj25 = {new Long(25), "UP_Dist5"};
		Object[] obj31 = {new Long(31), "MP_Dist1"};
		Object[] obj32 = {new Long(32), "MP_Dist2"};
		Object[] obj33 = {new Long(33), "MP_Dist3"};
		districts.add(obj10); districts.add(obj11); districts.add(obj12); districts.add(obj13);  districts.add(obj14); 
		districts.add(obj21); districts.add(obj22); districts.add(obj23); districts.add(obj24);  districts.add(obj25); 
		districts.add(obj31); districts.add(obj32); districts.add(obj33);
		return districts;
	}
	
	@SuppressWarnings("unchecked")
	public static List getMandals(){
		List mandals = new ArrayList();
		Object[] mandal1 = {new Long(1), "AP_Dist1_m1"};
		Object[] mandal2 = {new Long(2), "AP_Dist1_m2"};
		Object[] mandal3 = {new Long(3), "AP_Dist1_m3"};
		Object[] mandal4 = {new Long(4), "AP_Dist2_m4"};
		Object[] mandal5 = {new Long(5), "AP_Dist3_m5"};
		Object[] mandal6 = {new Long(6), "AP_Dist4_m6"};
		Object[] mandal7 = {new Long(7), "AP_Dist5_m7"};
		Object[] mandal8 = {new Long(8), "UP_Dist1_m8"};
		Object[] mandal9 = {new Long(9), "UP_Dist2_m9"};
		Object[] mandal10 = {new Long(10), "UP_Dist3_m1"};
		Object[] mandal11= {new Long(11), "UP_Dist4_m2"};
		Object[] mandal12 = {new Long(12), "UP_Dist5_m3"};
		Object[] mandal13 = {new Long(13), "MP_Dist1_m4"};
		Object[] mandal14 = {new Long(14), "MP_Dist2_m5"};
		Object[] mandal15 = {new Long(15), "MP_Dist3_m6"}; 
		mandals.add(mandal1); mandals.add(mandal2); mandals.add(mandal3); mandals.add(mandal4); mandals.add(mandal5); 
		mandals.add(mandal6); mandals.add(mandal7); mandals.add(mandal8); mandals.add(mandal9); mandals.add(mandal10); 
		mandals.add(mandal11); mandals.add(mandal12); mandals.add(mandal13); mandals.add(mandal14); mandals.add(mandal15); 
		return mandals;
	}
	
	@SuppressWarnings("unchecked")
	public static List getVillages(){
		List villages = new ArrayList();
		Object[] village1 = {new Long(1), "AP_Dist1_m1_v1"};
		Object[] village2 = {new Long(2), "AP_Dist1_m1_v2"};
		Object[] village3 = {new Long(3), "AP_Dist1_m2_v1"};
		Object[] village4 = {new Long(4), "AP_Dist1_m2_v2"};
		Object[] village5 = {new Long(5), "AP_Dist1_m3_v1"};
		Object[] village6 = {new Long(6), "AP_Dist1_m3_v2"};
		Object[] village7 = {new Long(7), "AP_Dist2_m4_v1"};
		Object[] village8 = {new Long(8), "AP_Dist2_m4_v2"};
		Object[] village9 = {new Long(9), "AP_Dist3_m5_v1"};
		Object[] village10 = {new Long(10), "AP_Dist3_m5_v2"};
		Object[] village11 = {new Long(11), "AP_Dist4_m6_v1"};
		Object[] village12 = {new Long(12), "AP_Dist4_m6_v2"};
		Object[] village13 = {new Long(13), "AP_Dist5_m7_v1"};
		Object[] village14 = {new Long(14), "AP_Dist5_m7_v2"};
		Object[] village15 = {new Long(15), "UP_Dist1_m8_v1"};
		Object[] village16 = {new Long(16), "UP_Dist1_m8_v2"};
		Object[] village17 = {new Long(17), "UP_Dist2_m9_v1"};
		Object[] village18 = {new Long(18), "UP_Dist2_m9_v2"};
		Object[] village19 = {new Long(19), "UP_Dist3_m1_v1"};
		Object[] village20 = {new Long(20), "UP_Dist3_m1_v2"};
		Object[] village21 = {new Long(21), "UP_Dist4_m2_v1"};
		Object[] village22 = {new Long(22), "UP_Dist4_m2_v2"};
		Object[] village23 = {new Long(23), "UP_Dist5_m3_v1"};
		Object[] village24 = {new Long(24), "UP_Dist5_m3_v2"};
		Object[] village25 = {new Long(25), "MP_Dist1_m4_v1"};
		Object[] village26 = {new Long(26), "MP_Dist1_m4_v2"};
		Object[] village27 = {new Long(27), "MP_Dist2_m5_v1"};
		Object[] village28 = {new Long(28), "MP_Dist2_m5_v2"};
		Object[] village29 = {new Long(29), "MP_Dist3_m6_v1"}; 
		Object[] village30 = {new Long(30), "MP_Dist3_m6_v2"}; 
		villages.add(village1); villages.add(village2); villages.add(village3); villages.add(village4); 
		villages.add(village5); villages.add(village6); villages.add(village7); villages.add(village8); 
		villages.add(village9); villages.add(village10);villages.add(village11); villages.add(village12);
		villages.add(village13); villages.add(village14); villages.add(village15); villages.add(village16); 
		villages.add(village17); villages.add(village18); villages.add(village19); villages.add(village20);
		villages.add(village21); villages.add(village22); villages.add(village23); villages.add(village24); 
		villages.add(village25); villages.add(village26); villages.add(village27); villages.add(village28); 
		villages.add(village29); villages.add(village30);
		
		return villages;
	}
	
	public static List<Cadre> getCadres(){
		List<Cadre> list = new ArrayList<Cadre>();

		Cadre c1=new Cadre();
		c1.setCadreId(1L);
		c1.setFirstName("first");
		c1.setMiddleName("middle");
		c1.setLastName("last");
		c1.setGender("Male");
		c1.setMobile("9959988988");
		c1.setEmail("email1@email.com");
		c1.setCadreLevel(new CadreLevel(1L, "COUNTRY"));
		c1.setCadreLevelValue(1L);
		
		Cadre c2=new Cadre();
		c2.setCadreId(2L);
		c2.setFirstName("aaa");
		c2.setMiddleName("bbb");
		c2.setLastName("ccc");
		c2.setGender("Male");
		c2.setMobile("9959988977");
		c2.setEmail("email2@gmail.com");
		c2.setCadreLevel(new CadreLevel(2L, "MANDAL"));
		c2.setCadreLevelValue(1L);

		list.add(c1);		list.add(c2);
		return list;
		
	}
	
	public static List<Long> getCadreIdsList(){
		
		List<Long> cadreIds = new ArrayList<Long>();
		cadreIds.add(new Long(1));
		cadreIds.add(new Long(2));
		cadreIds.add(new Long(3));
		cadreIds.add(new Long(4));
		
	 return cadreIds;
	}

}
