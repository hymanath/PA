package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IGallaryDAO;
public class GallaryDAOHibernateTest extends BaseDaoTestCase{
	
	private IGallaryDAO gallaryDAO;

	public void setGallaryDAO(IGallaryDAO gallaryDAO) {
		this.gallaryDAO = gallaryDAO;
	}
	
	/*public void test()
	{
		gallaryDAO.getAll();
	}*/
	
	/*public void testGetCandidateGallaryDetail()
	{
		gallaryDAO.getCandidateGallaryDetail(2L,"","");
	}*/
	
	public void testGetGallariesByCandidateId()
	{
		List<Object[]> list = gallaryDAO.getGallariesByCandidateId(3424l);
		
		System.out.println(list.size());
		
		for(Object[] params :list)
		{
			System.out.println(params[0].toString()+"---"+params[1].toString());
		}
	}


}
