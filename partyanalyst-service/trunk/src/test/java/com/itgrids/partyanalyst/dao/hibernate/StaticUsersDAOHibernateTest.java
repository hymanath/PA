package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IStaticUsersDAO;
import com.itgrids.partyanalyst.model.StaticUsers;

public class StaticUsersDAOHibernateTest extends BaseDaoTestCase {
private IStaticUsersDAO staticUsersDAO;

public IStaticUsersDAO getStaticUsersDAO() {
return staticUsersDAO;
}

public void setStaticUsersDAO(IStaticUsersDAO staticUsersDAO) {
	this.staticUsersDAO = staticUsersDAO;
}

	

public void testfindByMobileNo()
{
	
	//try {
		//List<StaticUsers> result=staticUsersDAO.findByMobileNo(new Long(9848498484L));
	
	List<StaticUsers> result=staticUsersDAO.findByMobileNo("9848498484");
	/*System.out.println(result.size());
	for(StaticUsers users:result)
	{
		System.out.println("Static users address"+users.getAddress().toString() );
		System.out.println("Static users Email id" +users.getEmailId().toString() );
		System.out.println("Static users name" +users.getName().toString());
		System.out.println(""+users.getStaticUserId().toString());
	}
	
	assertEquals(1,result.size());
	System.out.println("last");
	} catch (Error e) {
		System.out.println("Entered into exception");
		e.printStackTrace();
	}*/
}

}