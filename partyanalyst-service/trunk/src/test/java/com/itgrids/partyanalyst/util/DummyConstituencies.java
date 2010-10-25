package com.itgrids.partyanalyst.util;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.model.Constituency;

public class DummyConstituencies {

	public static List<Constituency> getConstituencies(){
		
		List<Constituency> constituencies = new ArrayList<Constituency>();
		
		Constituency c1 = new Constituency(new Long(1), null,null, null , "Madanapalli",null ,null ,new Long(1),null,null,null,null);
		Constituency c2 = new Constituency(new Long(2), null,null, null , "Nellore",null ,null ,new Long(1),null,null,null,null);
		Constituency c3 = new Constituency(new Long(3), null,null, null , "Punganoor",null ,null ,new Long(1),null,null,null,null);
		Constituency c4 = new Constituency(new Long(4), null,null, null , "Palamaner",null ,null ,new Long(1),null,null,null,null);
		Constituency c5 = new Constituency(new Long(5), null,null, null , "Chittoor",null ,null ,new Long(1),null,null,null,null);
		
		constituencies.add(c1);
		constituencies.add(c2);
		constituencies.add(c3);
		constituencies.add(c4);
		constituencies.add(c5);
		
		return constituencies;	
	}
	

	
}
