package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidatePartyFileDAO;

public class CandidatePartyFileDAOHibernateTest extends BaseDaoTestCase{

	private ICandidatePartyFileDAO candidatePartyFileDAO;

	public void setCandidatePartyFileDAO(
			ICandidatePartyFileDAO candidatePartyFileDAO) {
		this.candidatePartyFileDAO = candidatePartyFileDAO;
	}
	
	/*public void test()
	{
	  candidatePartyFileDAO.getAll();
	}*/
	
/*	public void testgetSourceCandidates()
	{
		List<Object[]> values = candidatePartyFileDAO.getSourceCandidates();
		System.out.println("source");
		for (Object[] objects : values) {
			System.out.println(objects[0] +":"+ objects[1] +":"+objects[2]);
		}
	}*/
	
/*	public void testgetDestinationCandidates()
	{
		List<Object[]> values = candidatePartyFileDAO.getDestinationCandidates();
		System.out.println("destination");
		for (Object[] objects : values) {
			System.out.println(objects[0] +":"+ objects[1] +":"+objects[2]);
		}
	}*/
	
	/*public void testgetCandidateNamesByFileIds(){
		Set<Long> fileIds = new java.util.HashSet<Long>();
		//fileIds.add(41l);
		fileIds.add(42l);
		List<Object[]> file = candidatePartyFileDAO.getCandidateNamesByFileIds(fileIds);
		for(Object[] param:file){
			System.out.println(param[0]+","+param[1]);
		}
	}*/
	
	/*public void testgetKeywordsCountByFileIds(){
		Set<Long> fileIds = new java.util.HashSet<Long>();
		fileIds.add(82l);
		fileIds.add(81l);
		List<Object[]> val = candidatePartyFileDAO.getKeywordsCountByFileIds(fileIds);
		for(Object[] p:val)
			System.out.println(p[0]+"-"+p[1]);
	}*/
	
	/*public void testgetSourcePartyCommentsOnly()
	{
		List<Object[]> values = candidatePartyFileDAO.getSourcePartyCommentsOnly(null,3424l);
		for (Object[] p : values) {
			System.out.println(p[0]+"-"+p[1]+"-"+p[2]+"-"+p[3]);
		}
	}
	*/
	
	public void testGetNews(){
		Calendar c= Calendar.getInstance();
		c.set(2014,0, 3);
		Date date1 = new Date();
		List<Long> categoryIds = new ArrayList<Long>();
		categoryIds.add(3991l);
		List<Long> districtIds = new ArrayList<Long>();
		districtIds.add(1l);
		districtIds.add(22l);
		districtIds.add(23l);
		districtIds.add(20l);
		districtIds.add(14l);
		districtIds.add(17l);
		districtIds.add(5l);
		districtIds.add(3l);
		districtIds.add(10l);
		districtIds.add(16l);
		districtIds.add(21l);
		districtIds.add(7l);
		districtIds.add(4l);
		districtIds.add(8l);
		districtIds.add(19l);
		districtIds.add(2l);
		districtIds.add(18l);
		districtIds.add(6l);
		districtIds.add(11l);
		districtIds.add(13l);
		districtIds.add(12l);
		districtIds.add(9l);
		districtIds.add(15l);
		/*List<Object[]> result = candidatePartyFileDAO.getPoliticalActivitiesNews(c.getTime(), date1,categoryIds,districtIds,0,10000);
		Long result1 = candidatePartyFileDAO.getPoliticalActivitiesNewsCount(c.getTime(), date1,categoryIds,districtIds);
		System.out.println(result.size());
		System.out.println(result1);
		for(Object[] res:result1){
			System.out.println(res[0].toString());
			
		}
		for(Long res:result1){
			System.out.println(res);
			
		}*/
		List<Long> partyIds = new ArrayList<Long>();
		partyIds.add(872l);
		List<Object[]> result1 = candidatePartyFileDAO.getAllPoliticalActivitiesCount(c.getTime(), date1,districtIds,1l,partyIds,3991l);
		for(Object[] res:result1){
			System.out.println(res[0].toString()+" : "+res[1].toString()+" : "+res[2].toString()+" : "+res[3].toString()+" : "+res[4].toString()+" : "+res[5].toString());
			
		}
	}
}
