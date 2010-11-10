package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.ICadreFamilyMemberInfoDAO;
import com.itgrids.partyanalyst.model.CadreFamilyMemberInfo;

public class CadreFamilyMemberInfoDAOHibernateTest extends BaseDaoTestCase {

	ICadreFamilyMemberInfoDAO cadreFamilyMemberInfoDAO;

	public void setCadreFamilyMemberInfoDAO(
			ICadreFamilyMemberInfoDAO cadreFamilyMemberInfoDAO) {
		this.cadreFamilyMemberInfoDAO = cadreFamilyMemberInfoDAO;
	}
	
	/*
	 public void test(){
		cadreFamilyMemberInfoDAO.getAll();		
	} 
	*/
	
	public void testFindByCadreId()
	{
		List<Object[]> result = cadreFamilyMemberInfoDAO.findByCadreId(37L);
		System.out.println("-------------------"+result.size());
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.println("=====================");
			System.out.println(parms[0]);
			System.out.println(parms[1]);
			System.out.println(parms[2]);
			System.out.println(parms[3]);
			System.out.println(parms[4]);
	}
	}


}
