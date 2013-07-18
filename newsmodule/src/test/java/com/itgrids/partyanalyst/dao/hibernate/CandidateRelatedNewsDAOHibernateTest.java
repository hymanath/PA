package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidateRelatedNewsDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public class CandidateRelatedNewsDAOHibernateTest extends BaseDaoTestCase{

	ICandidateRelatedNewsDAO candidateRelatedNewsDAO;

	public void setCandidateRelatedNewsDAO(
			ICandidateRelatedNewsDAO candidateRelatedNewsDAO) {
		this.candidateRelatedNewsDAO = candidateRelatedNewsDAO;
	}
	public void testGetAllfileGallariesOfCandidate(){
		//List<Object[]> items = candidateRelatedNewsDAO.getCandidatesContainsNews();
	   // List<?> items1 =  candidateRelatedNewsDAO.getNotResponseCountBasedTotalNewsCountForCandidateParty(null, null,872l, null, null, null, null, null, null, null, null);//.getNotResponseCountBasedTotalNewsCount(null,null,872,null,null,null,null,null,null,null,null);
		//List<Object[]> items = candidateRelatedNewsDAO.getNotResponseCountPerfect(null,null,872l,null,null,null,null,"all",null,null,null);
	 //List<Object[]> items = candidateRelatedNewsDAO.getRespondNewsPartyDetailsForCandidateTable(null,null,872l,null,null,null,null,null,null,null,10534l);
		//List<Object[]> items =     candidateRelatedNewsDAO.getRespondNewsIdsForCandidateParty(null,null,872l,null,null,null,null,"all",null,null,null);
		//List<Object[]> items =  candidateRelatedNewsDAO.getNotResponseCountForCandidateParty(null,null,872l,null,null,null,null,"all",null,null,null);
	   // List<?> items =   candidateRelatedNewsDAO.getNewsCountForACandidateFromCandidateParty(null,null,null,null,null,null,"all",872L);
		//List<?> items =   candidateRelatedNewsDAO.getNewsCountForACandidate(null,null,null,null,null,null,"all",872L);
    	//List<?> items =  candidateRelatedNewsDAO.getNotResponseCountForCandidatePartyByCandidate(null,null,872l,null,null,null,null,null,null,null,10534l);
    	                          //getRespondNewsPartyDetailsForCandidateTable
		 List<Object[]> items  = candidateRelatedNewsDAO.getRespondNewsPartyDetails(null, null, 872l, null, null, null, 0L, "all", null, null, null,"candidateDetails");
		// if(items != null && items.size() > 0)
	
		//	  for(Object[] params : items)
		//	  {  ma.put((Long)params[2],params);
				   
				/*  SelectOptionVO optionVO2 = new SelectOptionVO();
				  optionVO2.setId((Long)params[2]);
				  optionVO2.setPopulateId((Long)params[0]);
				  optionVO2.setName(params[1]!=null?params[1].toString():"");
				  responseNewsCountList.add(optionVO2);
				  count+=((Long)params[0]).longValue();*/
			//  }
		//Object[] ob=	ma.get(362l);
		//ob[0]=(Long)ob[0]+2l;
				//responseNewsCountList.add(new SelectOptionVO((Long)params[0],params[1]!=null?params[1].toString():""));
			/*  optionVO.setSelectOptionsList(responseNewsCountList);*/
		 // }
		 
		System.out.println(items.size());
		long count = 0;
	/*	for (Object[] objects : items) {
			System.out.println(objects[0]+"--"+objects[1]+"--"+objects[2]+"--"+objects[3]);
			count+=((Long)objects[0]);
			
		}*/
		System.out.println(count);
		
		
		
	}
	
	/*public void testgetNewsCountForACandidate()
	{
		List<Long> categoryIdsList = new ArrayList<Long>(0);
		List<Long> galleryIdsList = new ArrayList<Long>(0);
		List<Long> locationIdsList = new ArrayList<Long>(0);
	
		List<Object[]> list = candidateRelatedNewsDAO.getNewsCountForACandidate(null, null, categoryIdsList, galleryIdsList, locationIdsList, 0L, "all", 872L);
	    System.out.println(list.size());
	}*/
	
	/*public void testgetNewsCountForACandidateFromCandidateParty()
	{ 
		List<Long> categoryIdsList = new ArrayList<Long>(0);
		List<Long> galleryIdsList = new ArrayList<Long>(0);
		List<Long> locationIdsList = new ArrayList<Long>(0);
		List<Object[]> list = candidateRelatedNewsDAO.getNewsCountForACandidateFromCandidateParty(null, null, categoryIdsList, galleryIdsList, locationIdsList, 0l, "all", 872L);
		System.out.println(list.size());
		for(Object[] params:list)
		 System.out.println(params[0]+" "+params[1]+" "+params[2]);
	}*/
	
	public void testgetCandidateNameByFileGalleryIdsList()
	{
		List<Long> fileGalleryIdsList = new ArrayList<Long>(0);
		fileGalleryIdsList.add(412L);
		List<Object[]> list = candidateRelatedNewsDAO.getCandidateNameByFileGalleryIdsList(fileGalleryIdsList);
		System.out.println(list.size());
		for(Object[] params : list)
		 System.out.println(params[0]+" "+params[1]);
		
		  }
	
	public void testgetNotResponseCountForCandidatePartyByCandidate()
	{
		List<Object[]> list = (List<Object[]>)candidateRelatedNewsDAO.getNotResponseCountForCandidatePartyByCandidate(null, null, 872L, null, null, null, 0L, null, null, null, null);
		System.out.println(list.size());
		
		
	}
}
