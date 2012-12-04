package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;

	public class BoothPublicationVoterDAOHibernateTest  extends BaseDaoTestCase{
	  private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	
	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}
	
	/*public void test()
	{
		boothPublicationVoterDAO.getAll();
	}*/
	
	/*public void testGetVotersCountByPublicationId(){
		List<Object[]> count = boothPublicationVoterDAO.getVotersCountByPublicationId("constituency", 12l, 1l);
		System.out.println(count.size());
	}
	*/
/*	public void testGetVotersCountForPanchayatByPublicationId(){
		List<Object[]> count = boothPublicationVoterDAO.getVotersCountForPanchayatByPublicationId(10l,2l);
		System.out.println(count.size());
		//System.out.println(count.get(0)[0]+" "+count.get(0)[1]);
	}
	*/
	
	public void testGetVotersDetailsForPanchayatByPublicationId(){
		
		List l = boothPublicationVoterDAO.getVotersDetailsForPanchayatByPublicationId(444L,1L,0,15,"asc","voterId");
		
		System.out.println(l.size());
	}
}
