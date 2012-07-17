package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVisibilityDAO;
import com.itgrids.partyanalyst.model.Visibility;
import com.itgrids.partyanalyst.utils.IConstants;

public class VisibilityDAOHibernateTest extends BaseDaoTestCase{

	private IVisibilityDAO visibilityDAO;

	public void setVisibilityDAO(IVisibilityDAO visibilityDAO) {
		this.visibilityDAO = visibilityDAO;
	}
	
	public void test()
	{
		visibilityDAO.getAll();
	}
	public void testgetVisibilityByVisibilityType()
	{
		List<Visibility> visibilities = visibilityDAO.getVisibilityByVisibilityType(IConstants.PUBLIC);
		if(visibilities != null && visibilities.size() > 0)
		{
			for(Visibility visibilityDetails : visibilities)
			{
				System.out.println(visibilityDetails.getType());
				System.out.println(visibilityDetails.getVisibilityId());
			}
		}
	}
}
