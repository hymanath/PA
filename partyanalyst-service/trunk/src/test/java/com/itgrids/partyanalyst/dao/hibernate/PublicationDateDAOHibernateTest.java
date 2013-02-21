package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.model.PublicationDate;

public class PublicationDateDAOHibernateTest extends BaseDaoTestCase  {
	private IPublicationDateDAO publicationDateDAO;
	
	public void setPublicationDateDAO(IPublicationDateDAO publicationDateDAO) {
		this.publicationDateDAO = publicationDateDAO;
	}

	/*public void testGetAllData(){
		List<PublicationDate> result =  publicationDateDAO.getAll();
		System.out.println(result.size());
	}
	*/
	/*public void testgetAllPublicationDates()
	{
		List<Object[]> list = publicationDateDAO.getAllPublicationDates();
		System.out.println(list.size());
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
			{
				System.out.println(params[0]+" "+params[1]);
			}
		}
	}*/
	
	public void testGetNamePublicationDateId()
	{
		System.out.println(publicationDateDAO.getNamePublicationDateId(8L));
	}
}
