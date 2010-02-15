package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionVoterDAO;
import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.model.BoothConstituencyElectionVoter;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.service.impl.ConstituencyManagementService;

public class BoothConstituencyElectionVoterDAOHibernateTest extends BaseDaoTestCase{

	private IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO;
	private ConstituencyManagementService constituencyManagementService;

	public IBoothConstituencyElectionVoterDAO getBoothConstituencyElectionVoterDAO() {
		return boothConstituencyElectionVoterDAO;
	}

	public void setBoothConstituencyElectionVoterDAO(
			IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO) {
		this.boothConstituencyElectionVoterDAO = boothConstituencyElectionVoterDAO;
	}
	
	/*	public void testGetAll(){
		List<BoothConstituencyElectionVoter> list = boothConstituencyElectionVoterDAO.getAll();
		assertEquals(1, list.size());
	}
	
	public void testSave(){
		BoothConstituencyElection boothConstituencyElection = new BoothConstituencyElection(new Long(2124));
		Voter voter = new Voter(new Long(2));
		BoothConstituencyElectionVoter boothConstituencyElectionVoter = new BoothConstituencyElectionVoter(new Long(1));
		boothConstituencyElectionVoter.setBoothConstituencyElection(boothConstituencyElection);
		boothConstituencyElectionVoter.setVoter(voter);
		boothConstituencyElectionVoter = boothConstituencyElectionVoterDAO.save(boothConstituencyElectionVoter);
		setComplete();
		Long id = boothConstituencyElectionVoter.getBoothConstituencyElectionVoterId();
		assertEquals(new Long(1), id);
	}
	
	public void testDelete(){
		boothConstituencyElectionVoterDAO.remove(new Long(3));
		setComplete();
	}*/
	
	/*public void testFindByBoothConstituencyElectionAndVoter(){
		List<BoothConstituencyElectionVoter> list = boothConstituencyElectionVoterDAO.findByBoothConstituencyElectionAndVoter(new Long(2346), new Long(1));
		assertEquals(1, list.size());	
	}
		
	public void testFindByBoothConstituencyElection(){
		List<BoothConstituencyElectionVoter> list = boothConstituencyElectionVoterDAO.findByBoothConstituencyElection(new Long(2346));
		assertEquals(1, list.size());	
	}*/
	
	/*public void testFindVotersByHamletAndElectionYear(){
		constituencyManagementService = new ConstituencyManagementService();
		List<Voter> list = boothConstituencyElectionVoterDAO.findVotersCastInfoByHamletAndElectionYear(new Long(6), "2009");
		VoterCastInfoVO voterCastInfoVO = constituencyManagementService.caluculatePercentage(list);
		System.out.println("TOTAL HAMLETS::"+list.size());
		System.out.println(" Total Voters:"+voterCastInfoVO.getTotalVoters());
		System.out.println(" Female "+voterCastInfoVO.getFemaleVoters());
		System.out.println(" Male "+voterCastInfoVO.getMaleVoters());
		System.out.println(" Casts "+voterCastInfoVO.getCastVOs().size());
		System.out.println("---------------------------------------------");
		for(CastVO castVO:voterCastInfoVO.getCastVOs()){
			System.out.print(castVO.getCastName()+" ");
			System.out.print(castVO.getCastPercentage()+" ");
			System.out.println(castVO.getCastCount());
		}
		System.out.println("---------------------------------------------");
		assertEquals(1, list.size());
	}*/
	
	/*public void testFindVotersGroupByHouseNoForHamlet(){
		List<Voter> list = boothConstituencyElectionVoterDAO.findVotersGroupByHouseNoForHamlet(new Long(6), "2009");
		System.out.println("TOTAL HAMLETS::"+list.size());
	}*/
	
	/*public void testFindVotersHouseDetails(){
		List<String> list = boothConstituencyElectionVoterDAO.findVoterHouseNosInHamlet(new Long(6), "2009");
		System.out.print(list.size());
		System.out.print(list);
	}*/
	
	/*public void testFindVotersGroupByHouseNoAndAgeForHamletAndYear(){
		List<Voter> list = boothConstituencyElectionVoterDAO.findVotersGroupByHouseNoAndAgeForHamletAndYear(new Long(6), "2009");
		System.out.print(list.size());
		//System.out.print(list);
	}*/
	
	public void testFindTownshipWiseBoothDetailsForTehsil(){
		long beginTimeMillis = System.currentTimeMillis();
		List list = boothConstituencyElectionVoterDAO.findTownshipWiseBoothDetailsForTehsil(new Long(844));
		long endTimeMillis = System.currentTimeMillis();
		for(int i=0; i<list.size(); i++){
			System.out.print(((Object[])list.get(i))[0]+"--");
			System.out.print(((Object[])list.get(i))[1]+"--");
			System.out.print(((Object[])list.get(i))[2]+"--");
			//System.out.print(((Object[])list.get(i))[3]+"--");
			System.out.print(((Object[])list.get(i))[4]+"--");
			System.out.println(((Object[])list.get(i))[5]);
		}
		System.out.println("beginTimeMillis:"+beginTimeMillis);
		System.out.println("endTimeMillis:"+endTimeMillis);
		System.out.println("Total time taken:" + (endTimeMillis-beginTimeMillis)/1000);
		System.out.println(list.size());
	}
}
