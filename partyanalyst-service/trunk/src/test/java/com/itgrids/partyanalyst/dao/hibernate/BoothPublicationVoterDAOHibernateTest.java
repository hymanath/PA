package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.utils.IConstants;

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
	
	public void testGetVotersCountForPanchayatByPublicationId(){
		 boothPublicationVoterDAO.getPublicationUserCount(1l,8l,29l,IConstants.HAMLET);
		//stem.out.println(count.size());
		//System.out.println(count.get(0)+" ");
		//System.out.println(count);
	}
	*/
	
	/*public void testGetVotersDetailsForPanchayatByPublicationId(){
		
		List l = boothPublicationVoterDAO.getVotersDetailsForPanchayatByPublicationId(444L,1L,0,15,"asc","voterId");
		
		System.out.println(l.size());
	}*/
	
/*	public void testGetVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(){
		
		List l = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(11L,1L,18L,25L);
		
	
		System.out.println(((Object[])l.get(0))[0].toString());
		System.out.println(((Object[])l.get(0))[1]);
		
	}
		}*/
	
	/*public void testGetAgewiseVoterDetailsInSpecifiedRangeByGenderAndConstituncyId(){
		
		List<Object[]> l = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForHamletByPublicationId(50819L,7L,18L,25L);

      for(Object[] obj:l){
    	  System.out.println(obj[0].toString()+"-"+obj[1].toString());
    	  
      }		

}*/
	
	//public void testGetLocationNameByLocationValue()
	//{
	/*	List name = boothPublicationVoterDAO.getLocalitiesForBooth(122094l, 1l);
		
		System.out.println(name.size());
		if(name != null && name.size() > 0)
		  System.out.println(name.get(0));*/
	//	List l=new ArrayList<Long>();
	//	l.add(42l);
		
	//	List<Object[]> name = boothPublicationVoterDAO.getVoterDetailsByPanchayatIds(4l,8l,1l);
	 // System.out.println(name);
	//  for(Object[] obj:name){
    //	  System.out.println(obj[0].toString()+"-"+obj[1].toString());
	//}
	
	//}
	
		public void testGetVotersCasteDetailsForAgeRange(){
			Long starttime = System.currentTimeMillis();
			List<Long> list = new ArrayList<Long>();
			list.add(122978l);list.add(
					122979l);list.add(
					122980l);list.add(
					122981l);list.add(
					122982l);list.add(
					122983l);list.add(
					122984l);list.add(
					122985l);list.add(
					122986l);list.add(
					122987l);list.add(
					122988l);list.add(
					122989l);list.add(
					122990l);list.add(
					122991l);list.add(
					122992l);list.add(
					122993l);list.add(
					122994l);list.add(
					122995l);
					
					
			List<Object[]> castInfo = boothPublicationVoterDAO.getVotersCasteDetailsForAgeRange(23l, 30l, list);
			Long endtime = System.currentTimeMillis();
			System.out.println((endtime-starttime)/1000);
			for(Object[] caste : castInfo){
				for(Object value : caste){
					System.out.print(value+"  ");
				}
				System.out.println("");
			}
		}
	}
