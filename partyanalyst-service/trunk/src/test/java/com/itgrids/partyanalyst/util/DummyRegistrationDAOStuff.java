package com.itgrids.partyanalyst.util;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.model.Registration;

public class DummyRegistrationDAOStuff {
	private static List<Registration> registrations = null;

	public static List<Registration> getRegistrations(){
			registrations = new ArrayList<Registration>();
			Registration reg1 = new Registration("aaa","middleName","lastName","itgrids1","password",java.sql.Date.valueOf("2009-08-06"),"email@gmail.com","99655652","5846526","address","gender","India","517325","","");
			Registration reg2 = new Registration("aaa1","middleName","lastName1","itgrids2","password",java.sql.Date.valueOf("2009-08-06"),"email@gmail.com","99655652","5846526","address","gender","India","517325","","");
			Registration reg3 = new Registration("aaa2","middleName","lastName2","itgrids3","password",java.sql.Date.valueOf("2009-08-06"),"email@gmail.com","99655652","5846526","address","gender","India","517325","","");
			Registration reg4 = new Registration("aaa3","middleName","lastName3","itgrids4","password",java.sql.Date.valueOf("2009-08-06"),"email@gmail.com","99655652","5846526","address","gender","India","517325","","");
			
			registrations.add(reg1);
			registrations.add(reg2);
			registrations.add(reg3);
			registrations.add(reg4);
					
			return registrations;
		}
	public static List<Registration> getEmptyList(){
		registrations = new ArrayList<Registration>();
		return registrations;
	}
		
	}

	

