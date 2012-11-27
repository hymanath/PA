package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.model.Voter;

public class VoterDAOHibernateTest extends BaseDaoTestCase{

	private IVoterDAO voterDAO;

	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}

	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}
	
	/*	public void testGetAll(){
		List<Voter> voters = voterDAO.getAll();
		assertEquals(1, voters.size());
	}
	
	public void testSave(){
		Voter voter = new Voter(new Long(1));
		voter.setFirstName("Thaniga1");
		voter.setLastName("Mohan1");
		voter.setInsertionDate(null);
		voter = voterDAO.save(voter);
		//setComplete();
		Long id = voter.getVoterId();
		assertEquals(new Long(1), id);
	}
	
	public void testFindByVoterFirstNameLastNameAndVoterIdCardNo(){
		List<Voter> voters = voterDAO.findByVoterFirstNameLastNameRelativeFirstNameLastNameAndVoterIdNo("LAKSHMI", "TAMBI", "ENKATESHWARLU", "", "AP191260270275");
		assertEquals(1, voters.size());
	}*/
	
	/*public void test(){
		List voters = voterDAO.getCastCatageory();
		System.out.println(voters.size());
	}*/
	
	/*public void test()
	{
		List<Object[]> list = voterDAO.getVotersInfoForPollingStationAndElectionYear(211l,
				"2009");
		System.out.println(list.size());
	}*/
	
	/*public void test()
	{
		 List list = voterDAO.findVotersCastInfoByPollingStationAndElectionYear(211l, "2009");
		 System.out.println(list.size());
	}*/
	/*public void testfindVotersInfoForPollingStationAndElectionYear()
	{
	 List list=voterDAO.findVotersInfoForPollingStationAndElectionYear(211l,"2009");
	 System.out.println(list.size());
	 
}*/
	
	/*public void testgetVotersCastInfoForAssembly()
	{
		List list =voterDAO.findVotersCastInfoByMandalAndElectionYear(836l,"2009");
		System.out.println(list.size());
	}*/
	
	/*public void testGetVotersBasicInfoByManadalId()
	{
		List<Object[]> list = voterDAO.getVotersBasicInfoByManadalId(2844l, "2009");
		System.out.println(list.size());
		for(Object[] params : list)
		{
			
			if(params[0].toString() != null && params[1].toString() != null)
			{
				if(params[1].toString().equalsIgnoreCase("M"))
					System.out.println("Male:  "+params[0].toString());
				if(params[1].toString().equalsIgnoreCase("F"))
					System.out.println("FeMale:  "+params[0].toString());
				if(params[1].toString().equalsIgnoreCase(""))
					System.out.println("UnKnow:  "+ params[0].toString());
			}
		}
	}*/
	
	/*public void testGetVotersBasicInfoByPollingStationId()
	{
		List<Object[]> list = voterDAO.getVotersBasicInfoByPollingStationId(2378l, "2009");
		System.out.println(list.size());
		for(Object[] params : list)
		{
			
			if(params[0].toString() != null && params[1].toString() != null)
			{
				if(params[1].toString().equalsIgnoreCase("M"))
					System.out.println("Male:  "+params[0].toString());
				if(params[1].toString().equalsIgnoreCase("F"))
					System.out.println("FeMale:  "+params[0].toString());
				if(params[1].toString().equalsIgnoreCase(""))
					System.out.println("UnKnow:  "+ params[0].toString());
			}
		}
	}*/
	
	public void testGetVotersBasicInfoByConstituencyId()
	{
		List<Object[]> list = voterDAO.getVotersBasicInfoByConstituencyId(232l, "2009");
		System.out.println(list.size());
		for(Object[] params : list)
		{
			
			if(params[0].toString() != null && params[1].toString() != null)
			{
				if(params[1].toString().equalsIgnoreCase("M"))
					System.out.println("Male:  "+params[0].toString());
				if(params[1].toString().equalsIgnoreCase("F"))
					System.out.println("FeMale:  "+params[0].toString());
				if(params[1].toString().equalsIgnoreCase(""))
					System.out.println("UnKnow:  "+ params[0].toString());
			}
		}
	}
}