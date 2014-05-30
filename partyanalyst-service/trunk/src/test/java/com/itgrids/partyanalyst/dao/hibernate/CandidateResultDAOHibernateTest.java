package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dto.GenericVO;


public class CandidateResultDAOHibernateTest extends BaseDaoTestCase {
	
	private ICandidateResultDAO candidateResultDAO;
	
	public void setCandidateResultDAO(ICandidateResultDAO candidateResultDAO) {
		this.candidateResultDAO = candidateResultDAO;
	}


/*	@Test
	public void test(){
		Assert.assertEquals(1, 1);
	}*/
	/*private ICandidateResultDAO candidateResultDAO;
	CandidateResult candRes = new CandidateResult(new Long(4),null,new Double(50000),new Long(4));
	
	public void setCandidateResultDAO(ICandidateResultDAO candidateResultDAO){
		this.candidateResultDAO = candidateResultDAO;
	}
	
	//@Test
	public void testFindByRank(){
		List<CandidateResult> candresult = candidateResultDAO.findByRank(new Long(2));
		Assert.assertEquals(2, candresult.size());
		
		CandidateResult candidateResult = candresult.get(0);
		Nomination nomination = candidateResult.getNomination();
		Assert.assertEquals(new Long(1),nomination.getNominationId());
	}
	
	//@Test
	public void testFindByVotesEarned(){
		List<CandidateResult> candresult = candidateResultDAO.findByVotesEarned(new Double(20000));
		Assert.assertEquals(1, candresult.size());
		
		CandidateResult candidateResult = candresult.get(0);
		Nomination nomination = candidateResult.getNomination();
		Assert.assertEquals(new Long(1),nomination.getNominationId());
		Assert.assertEquals(20000.0,candidateResult.getVotesEarned());
	}
	
	//@Test
	public void testAddDetails(){
		candidateResultDAO.save(candRes);
		setComplete();
	}
	
	//@Test
	public void testRemoveDetails(){
		candidateResultDAO.remove(new Long(4));
		setComplete();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetVotesPercentForACandInAnElection(){
		List votesPercent = candidateResultDAO.getVotesPercentOfACandidateInAnElection(new Long(9),new Long(232),new Long(1));
		System.out.println("Size :" + votesPercent.size());
		if(votesPercent != null && votesPercent.size() > 0){
			Object params = (Object)votesPercent.get(0);
			System.out.println("Votes Percent :" + (String)params);
		}
	}*/
	
	/*public void testGetElectionResults(){
		List results = candidateResultDAO.getElectionResultsForAllPartiesInAMandal(new Long(835),"MPTC","2006");
		
		System.out.println("Size :" + results.size());
		
		for(int i=0;i<results.size();i++){
			Object[] params = (Object[])results.get(i);
			System.out.println("Party :" + (String)params[1]);
			System.out.print("  Votes Earned :" + (Double)params[2]);
			System.out.print( "  Valid Votes :" + (Double)params[3]);
			
			System.out.println("..");
		}
	}*/
	
	/*public void testGetData1(){	
	//	int list = candidateResultDAO.updateMarginVotesAndPercentage("10.20",30.54d,"2009",IConstants.ASSEMBLY_ELECTION_TYPE,232l,15404l);
	//	System.out.println(list);
	//	setComplete();
	}*/
	/*public void testGetData2(){	
		//String marginPercentage,Double marginVotes,String electionYear,String electionType,Long constituencyId,Long candidateId)
			int result = candidateResultDAO.updateMarginVotesAndPercentage("8.09",68.0d,"2005","MUNCIPALITY",6993l, 49705l);
			System.out.println(result);
			setComplete();
		}
	*/
	
	/*public void testGetLastElectionWinner(){
		List<Object[]> list = candidateResultDAO.getPreviousElectionWinningPartyByConstituency(232l);
		if(list.size()>0){
			System.out.println(list.get(0)[0]+"--"+list.get(0)[1]+"--"+list.get(0)[2]+"--"+list.get(0)[3]);
		}
		
		List<Object[]> list = candidateResultDAO.getElectionResultsByConstituency(38l,"telangana");
		
		System.out.println(list.size());
		
		//List<Object[]> list = candidateResultDAO.getElectionResultsByParliament(17l);
		List<Long> constituencyIds = new ArrayList<Long>();
//		constituencyIds.add(467l);
//		constituencyIds.add(474l);
		
		constituencyIds.add(18l);
		constituencyIds.add(19l);
		
		
		
		
		
		
		List<Object[]> list = candidateResultDAO.getElectionResultsByMargin(38l, constituencyIds,2l);
		
		System.out.println(list.size());
	}*/
	
	/*public void test(Long electionId){
		
		List<Object[]> list = candidateResultDAO.getElectionResultsByConstituency(38l);
		
		System.out.println(list.size());
	}*/
	
	/*public void testgetElectionResultsForCBNORMODIEffect()
	{
		List<Long> parties = new ArrayList<Long>();
		parties.add(872l);
		parties.add(362l);
		parties.add(163l);
		parties.add(72l);
		parties.add(886l);
		parties.add(662l);
		parties.add(1117l);
		System.out.println(candidateResultDAO.getElectionResultsForCBNORMODIEffect(17l,1l,parties,1l).size());;
	}*/
	
	
	
	public void testCbnEffect()
	{
		cbnEffectCalucation();
	}
	public List<GenericVO> cbnEffectCalucation()
	{
		List<GenericVO> returnList = null;
		
		try
		{
			
			Map<Long,GenericVO> mapFor2009 = null;
			Map<Long,GenericVO> mapFor2009Win = null;
			Map<Long,GenericVO> mapFor2009Loss = null;
			
			Map<Long,GenericVO> mapFor2012 = null;
			Map<Long,GenericVO> mapFor2012Win = null;
			Map<Long,GenericVO> mapFor2012Loss = null;
			List<Object[]> resultFor2009Election = candidateResultDAO.getElectionResultForCbnEffect(38l, 872l);
			System.out.println(resultFor2009Election.size());
			if(resultFor2009Election != null && resultFor2009Election.size() > 0 )
			{
				 mapFor2009 = new HashMap<Long, GenericVO>();
				 mapFor2009Win = new HashMap<Long, GenericVO>();
				 mapFor2009Loss = new HashMap<Long, GenericVO>();
				for (Object[] objects : resultFor2009Election)
				{
					GenericVO genericVO = new GenericVO();
					genericVO.setId((Long)objects[0]);
					genericVO.setPerc(Double.valueOf(objects[1].toString()));
					genericVO.setRank((Long)objects[2]);
					if((Long)objects[2] == 1l)
					{
						mapFor2009Win.put((Long)objects[0], genericVO);
					}
					else
					{
						mapFor2009Loss.put((Long)objects[0], genericVO);
					}
					mapFor2009.put((Long)objects[0], genericVO);
				}
			}
			List<Object[]> resultFor2012Election = candidateResultDAO.getElectionResultForCbnEffect(258l, 872l);
			System.out.println(resultFor2012Election.size());
			if(resultFor2012Election != null && resultFor2012Election.size() > 0 )
			{
				 mapFor2012 = new HashMap<Long, GenericVO>();
				 mapFor2012Win = new HashMap<Long, GenericVO>();
				 mapFor2012Loss = new HashMap<Long, GenericVO>();
				for (Object[] objects : resultFor2012Election)
				{
					GenericVO genericVO = new GenericVO();
					genericVO.setId((Long)objects[0]);
					genericVO.setPerc(Double.valueOf(objects[1].toString()));
					genericVO.setRank((Long)objects[2]);
					if((Long)objects[2] == 1l)
					{
						mapFor2012Win.put((Long)objects[0], genericVO);
					}
					else
					{
						mapFor2012Loss.put((Long)objects[0], genericVO);
					}
					mapFor2012.put((Long)objects[0], genericVO);
				}
			}
			Map<Long,String> constiMap = new HashMap<Long, String>();
		
			
			//1) 2009 LOST 			2014 LOST
			
			List<Long> firstRuleConstituencyes = new ArrayList<Long>(mapFor2009Loss.keySet());
			firstRuleConstituencyes.addAll(new ArrayList<Long>(mapFor2012Loss.keySet()));
			
			Set<Long> firstIds = findDuplicates(firstRuleConstituencyes);
			if(firstIds != null && firstIds.size() > 0)
			{
				for (Long constituencyId : firstIds)
				{
					Double avilPerc = mapFor2012Loss.get(constituencyId).getPerc().doubleValue() - mapFor2009Loss.get(constituencyId).getPerc().doubleValue();
					if(avilPerc > 0.0)
					{
						constiMap.put(constituencyId, "CBN EFFECT");
					}
					else if (avilPerc == 0)
					{
						constiMap.put(constituencyId, "NEUTRAL");
					}
					else
					{
						constiMap.put(constituencyId, "NO CBN EFFECT");
					}
				}
			}
			
			// 2) 2009 LOST 				2014 WON
			List<Long> secondRuleConstituencyes = new ArrayList<Long>(mapFor2009Loss.keySet());
			secondRuleConstituencyes.addAll(new ArrayList<Long>(mapFor2012Win.keySet()));
			
			Set<Long> secondIds = findDuplicates(secondRuleConstituencyes);
			if(secondIds != null && secondIds.size() > 0)
			{
				for (Long constituencyId : secondIds)
				{
					Double avilPerc = mapFor2012Win.get(constituencyId).getPerc().doubleValue() - mapFor2009Loss.get(constituencyId).getPerc().doubleValue();
					if(avilPerc > 0.0)
					{
						constiMap.put(constituencyId, "CBN EFFECT");
					}
					else
					{
						constiMap.put(constituencyId, "NEUTRAL");
					}
				}
			}
			
			//3)2009 WON 				2014 LOST
			List<Long> thirdRuleConstituencyes = new ArrayList<Long>(mapFor2009Win.keySet());
			thirdRuleConstituencyes.addAll(new ArrayList<Long>(mapFor2012Loss.keySet()));
			Set<Long> thirdIds = findDuplicates(thirdRuleConstituencyes);
			if(thirdIds != null && thirdIds.size() > 0)
			{
				for (Long constituencyId : thirdIds)
				{
					Double avilPerc = mapFor2012Loss.get(constituencyId).getPerc().doubleValue() - mapFor2009Win.get(constituencyId).getPerc().doubleValue();
					if(avilPerc > 0.0)
					{
						constiMap.put(constituencyId, "NEUTRAL");
					}
					else if (avilPerc == 0)
					{
						constiMap.put(constituencyId, "NEUTRAL");
					}
					else
					{
						constiMap.put(constituencyId, "NO CBN EFFECT");
					}
				}
			}
			
			//2009 WON 				2014 WON
			List<Long> fourthRuleConstituencyes = new ArrayList<Long>(mapFor2009Win.keySet());
			fourthRuleConstituencyes.addAll(new ArrayList<Long>(mapFor2012Win.keySet()));
			Set<Long> fourthIds = findDuplicates(fourthRuleConstituencyes);	
			if(fourthIds != null && fourthIds.size() > 0)
			{
				for (Long constituencyId : fourthIds)
				{
					Double avilPerc = mapFor2012Win.get(constituencyId).getPerc().doubleValue() - mapFor2009Win.get(constituencyId).getPerc().doubleValue();
					if(avilPerc > 0.0)
					{
						constiMap.put(constituencyId, "CBN EFFECT");
					}
					else 
					{
						constiMap.put(constituencyId, "NEUTRAL");
					}
					
				}
			}
			
			List<Long> constituencyes = new ArrayList<Long>(constiMap.keySet());
			for (Long long1 : constituencyes) {
				System.out.println(long1 +":"+ constiMap.get(long1));
				//System.out.println(constiMap.get(long1));
				//System.out.println("*********************");
			}
		}
		
		
		catch (Exception e)
		{
			e.printStackTrace();
			//LOG.error("Exception Raised In cbnEffectCalucation", e);
		}
		return returnList;
	}
	
	
	public Set<Long> findDuplicates(List<Long> listContainingDuplicates)
	{ 
	  final Set<Long> setToReturn = new HashSet<Long>(); 
	  final Set<Long> set1 = new HashSet<Long>(); 

	  for (Long yourInt : listContainingDuplicates)
	  {
	   if (!set1.add(yourInt))
	   {
	    setToReturn.add(yourInt);
	   }
	  }
	  return setToReturn;
	}
	
}
