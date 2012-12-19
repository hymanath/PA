package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IConstituencyLeadCandidateDAO;

public class ConstituencyLeadCandidateDAOHibernateTest  extends BaseDaoTestCase {
	private IConstituencyLeadCandidateDAO constituencyLeadCandidateDAO;

	public void setConstituencyLeadCandidateDAO(
			IConstituencyLeadCandidateDAO constituencyLeadCandidateDAO) {
		this.constituencyLeadCandidateDAO = constituencyLeadCandidateDAO;
	}
	/*public void test()
	{
		constituencyLeadCandidateDAO.getAll();
	}select model.constituencyElection.constiElecId,count(model.constituencyElection.constituency.constituencyId)," +
				" model.party.shortName ,model1.status from" +
				" Nomination model ,ConstituencyLeadCandidate model1 where model.constituencyElection.constiElecId in " +
				"(select model1.constituencyElection.constiElecId  from ConstituencyLeadCandidate model1 where " +
				" model1.constituencyElection.election.electionId = ? ) group by model.party.shortName" 
	
	public void testGetPartyLeadingOrWinningConstituencies(){
		List<Object[]> list = constituencyLeadCandidateDAO.getPartyLeadingOrWinningConstituencies(81l);
		for(Object[] params : list){
			System.out.println(params[2]);
		
		System.out.println("Party =" + params[1] +"------"+(Long)params[0]);
		}
	}*/
	/*public void testGetCountOfOldConstituenciesInAElection(){
		List result = constituencyLeadCandidateDAO.getCountOfOldConstituenciesInAElection(81l);
		System.out.println("Old constituencies     = "+result.get(0));
	}
	
	public void testGetCountOfDelimitedConstituenciesInAElection(){
		List result = constituencyLeadCandidateDAO.getCountOfDelimitedConstituenciesInAElection(81l);
		System.out.println("New Constituencies    =" +result.get(0));
	}
	
	public void testgetLeadingConstituenciesCount(){
		List result = constituencyLeadCandidateDAO.getLeadingConstituenciesCount(81l);
		System.out.println("Lead constituencies   =" + result.get(0));
	}
	
	public void testGetPartyLeadingOrWinningConstituencies()
	{
		List<Object[]> list = constituencyLeadCandidateDAO.getPartyLeadingOrWinningConstituencies(38l);
		
		System.out.println(list.size());
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
			System.out.print("\t"+obj);
			
		}
	}*/
	/*public void testgetElectionIds(){
		List list = constituencyLeadCandidateDAO.getElectionIds(81l);
		System.out.println(list.size());
	}*/
	
	/*public void test()
	{
		List<Object[]> list = constituencyLeadCandidateDAO.getPartiesLeadingInfo(38l);
		System.out.println(list.size());
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
			System.out.print("\t"+obj);
			
		}
	}*/
	
	/*public void testGetConstituencyWiseCandidatesStates()
	{
		List<Object[]> list = constituencyLeadCandidateDAO.getConstituencyWiseCandidatesStates(38l);
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
			System.out.print("\t"+obj);
			
		}
	}
	
	public void testGetResultKnownConstituenciesCountInAElection()
	{
		Object object = constituencyLeadCandidateDAO.getResultKnownConstituenciesCountInAElection(38l);
		System.out.println(object.toString());
	}*/
	
	/*public void test(){
		
	List<Object[]> list = constituencyLeadCandidateDAO.getGenderAnalysisElectionresults(182l);
	System.out.println(list.size());
	for(Object[] params :list){
		System.out.println("partyId  "+params[0]);
		System.out.println("partyName "+params[1]);
		System.out.println("Gender "+params[2]);
		System.out.println("status "+params[3] );
	}
	}*/
	
	 /* public void testgetAllParties(){
		
		List<Object[]> list = constituencyLeadCandidateDAO.getAllParties(202l);
		System.out.println(list.size());
		for(Object[] params :list){
			System.out.println("id  "+params[0]+"  name  "+params[1].toString()+" status "+params[2].toString());
			
		}
		}*/
	 public void testgetPartiesPartispatedCount(){
			
			List<Object[]> list = constituencyLeadCandidateDAO.getPartiesPartispatedCount(202l);
			System.out.println(list.size());
			for(Object[] params :list){
				System.out.println("count  "+params[0]+"  id  "+params[1]);
				
			}
			}
	}

