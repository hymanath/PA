package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

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
	
	public void testgetKeywordsCountByFileIds(){
		Set<Long> fileIds = new java.util.HashSet<Long>();
		fileIds.add(82l);
		fileIds.add(81l);
		List<Object[]> val = candidatePartyFileDAO.getKeywordsCountByFileIds(fileIds);
		for(Object[] p:val)
			System.out.println(p[0]+"-"+p[1]);
	}
}
