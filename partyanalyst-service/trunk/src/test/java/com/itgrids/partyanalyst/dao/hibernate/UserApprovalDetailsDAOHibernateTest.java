package com.itgrids.partyanalyst.dao.hibernate;

import java.text.DateFormat;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IUserApprovalDetailsDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserApprovalDetailsDAOHibernateTest extends BaseDaoTestCase {
	
	private IUserApprovalDetailsDAO UserApprovalDetailsDAO;

	public IUserApprovalDetailsDAO getUserApprovalDetailsDAO() {
		
		return UserApprovalDetailsDAO;
	}

	public void setUserApprovalDetailsDAO(
			IUserApprovalDetailsDAO UserApprovalDetailsDAO) {
		
		
		this.UserApprovalDetailsDAO = UserApprovalDetailsDAO;
	}	
		
	@SuppressWarnings("unchecked")
	public void testfindUserApprovalStatusbetweendates()throws Exception
	{  
		SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
					
		System.out.println("details");
		List<Object[]> list = UserApprovalDetailsDAO. findUserApprovalStatusbetweendates(sdf.parse("2011/01/13"),
				sdf.parse("2011/01/13"));				
			System.out.println("size"+list.size());
		for(Object[] obj : list)
		{
			System.out.println("1"+obj[0]);
			System.out.println("2"+obj[1]);
			System.out.println("3"+obj[2]);
			System.out.println("4"+obj[3]);
			System.out.println("5"+obj[4]);		
			System.out.println("6"+obj[5]);	
		}
	}

	
}
	
