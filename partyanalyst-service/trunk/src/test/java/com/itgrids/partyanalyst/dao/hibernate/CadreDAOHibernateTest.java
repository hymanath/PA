package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class CadreDAOHibernateTest extends BaseDaoTestCase {
	ICadreDAO cadreDAO;
	
		
	public ICadreDAO getCadreDAO() {
		return cadreDAO;
	}

	public void setCadreDAO(ICadreDAO cadreDAO) {
		this.cadreDAO = cadreDAO;
	}

	public void testFindCadresByLevels()
	{
		List result = cadreDAO.findCadresByLevels(7l, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.print(parms[0].toString());
			System.out.println(parms[1].toString());
			
		}
		
	}
}
