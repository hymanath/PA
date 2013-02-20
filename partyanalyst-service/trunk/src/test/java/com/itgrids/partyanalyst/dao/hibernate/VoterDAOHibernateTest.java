package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
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
		 List list = voterDAO.findVotersCastInfoByPollingStationAndElectionYear(201l, "2009");
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
	
	/*public void testGetVotersBasicInfoByConstituencyId()
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
	}*/
	
	/*public void testfindVotersCastInfoByUrbanAndElectionYear()
	{
		List list=voterDAO.findVotersCastInfoByUrbanAndElectionYear(83l, "2009");
		System.out.println(list.size());
	}*/
	
	/*public void testgetVotersBasicInfoByUrbanId()
	{
		List list = voterDAO.findVotersCastInfoByMandalAndElectionYear(83l,"2009");
		System.out.println(list.size());
	}
	
	public void test()
	{
		List<Voter> list =voterDAO.getVoterPersonalDetailsByVoterId(5105l);
		System.out.println(list.size());
	}*/
	
	/*public void testUpdateCasteByVoterCardId()
	{
		System.out.println(voterDAO.updateCasteByVoterCardId("RYT0097071", "Kamma"));
	}*/
	
	/*public void testUpdateVoterNameAndRelativeName()
	{
		int records = voterDAO.updateVoterNameAndRelativeName("Kamalakar Dandu", "Kamalakar Dandu", 1L);
		System.out.println(records);
	}*/
	
	/*public void testGetVoterIdsByVoterIdCardNos()
	{
		List<String> vidLiist = new ArrayList<String>(0);
		vidLiist.add("RYT0605790");
		vidLiist.add("RYT0981613");
		vidLiist.add("RYT0981621");
		vidLiist.add("RYT0981639");
		vidLiist.add("RYT0981647");
		
		List<Object[]> list = voterDAO.getVoterIdsByVoterIdCardNos(vidLiist);
		System.out.println(list.size());
	}*/
	/*public void testGetVoterByVoterCardNo()
	{
		
		
		List<Voter> list = voterDAO.getVoterByVoterCardNo("ZAF0448416");
		System.out.println(list.size());
	}*/
	
	public void testGetVoterIdsByCardNos()
	{
		List<String> voterIdCardNosList = new ArrayList<String>(0);
		voterIdCardNosList.add("1");
		voterIdCardNosList.add("2");
		voterIdCardNosList.add("3");
		voterIdCardNosList.add("4");
		voterIdCardNosList.add("5");
		voterIdCardNosList.add("6");
		
		for(String s : voterIdCardNosList.subList(1,9))
			System.out.println(s);
		
		
		//List<Object[]> list = voterDAO.getVoterIdsByCardNos(voterIdCardNosList);
		//System.out.println(list.size());
	}
//	public void testFindVoterDetailsBasedOnVoterId()
//	{
//		
//		
//		List<Voter> list = voterDAO.findVoterDetailsBasedOnVoterId(1l);
//		for (Voter voter : list) {
//			System.out.println(voter.getCastCatagery());
//		}
//	}
	/*public void testgetFamilyMemberCount()
	{
		
		
		List<Long> count = voterDAO.getFamilyMemberCount("0-2",121885l);
		System.out.println(count);
	}*/
		
}