package com.itgrids.partyanalyst.dao.hibernate;

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
	// List<?> items =  candidateRelatedNewsDAO.getNotResponseCountBasedTotalNewsCount(null, null,872l, null, null, null, null, null, null, null, null);//.getNotResponseCountBasedTotalNewsCount(null,null,872,null,null,null,null,null,null,null,null);
		//List<Object[]> items = candidateRelatedNewsDAO.getNotResponseCountPerfect(null,null,872l,null,null,null,null,"all",null,null,null);
		List<Object[]> items = candidateRelatedNewsDAO.getRespondNewsPartyDetailsForCandidateTable(null,null,872l,null,null,null,null,"all",null,null,null);
		List<Object[]> items1 =     candidateRelatedNewsDAO.getRespondNewsPartyDetails(null,null,872l,null,null,null,null,"all",null,null,null);
		 Map<Long , Object[]> ma  = new HashMap<Long,Object[]>();
		 if(items != null && items.size() > 0)
		  {  
			  for(Object[] params : items)
			  {  ma.put((Long)params[2],params);
				   
				/*  SelectOptionVO optionVO2 = new SelectOptionVO();
				  optionVO2.setId((Long)params[2]);
				  optionVO2.setPopulateId((Long)params[0]);
				  optionVO2.setName(params[1]!=null?params[1].toString():"");
				  responseNewsCountList.add(optionVO2);
				  count+=((Long)params[0]).longValue();*/
			  }
		Object[] ob=	ma.get(362l);
		ob[0]=(Long)ob[0]+2l;
				//responseNewsCountList.add(new SelectOptionVO((Long)params[0],params[1]!=null?params[1].toString():""));
			/*  optionVO.setSelectOptionsList(responseNewsCountList);*/
		  }
		 
		System.out.println(items.size());
		long count = 0;
	/*	for (Object[] objects : items) {
			System.out.println(objects[0]+"--"+objects[1]+"--"+objects[2]+"--"+objects[3]);
			count+=((Long)objects[0]);
			
		}*/
		System.out.println(count);
		
		
		
	}
}
