package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadreChildrenInfoDAO;
import com.itgrids.partyanalyst.model.CadreChildrenInfo;
import com.itgrids.partyanalyst.model.CadreLanguageEfficiency;

public class CadreChildrenInfoDAOHibernateTest extends BaseDaoTestCase {
	
	ICadreChildrenInfoDAO cadreChildrenInfoDAO;
	
	public void setCadreChildrenInfoDAO(ICadreChildrenInfoDAO cadreChildrenInfoDAO) {
		this.cadreChildrenInfoDAO = cadreChildrenInfoDAO;
	}
	
	/*public void test(){
		cadreChildrenInfoDAO.getAll();		
	}
	*/
	public void testFindByCadreId()
	{
		List<Object[]> result = cadreChildrenInfoDAO.findByCadreId(25L);
		System.out.println("-------------------"+result.size());
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.println("=====================");
			System.out.println(parms[0]);
			System.out.println(parms[1]);
			System.out.println(parms[2]);
	}
	}

}
