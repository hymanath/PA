package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IElectionGoverningBodyDAO;
import com.itgrids.partyanalyst.model.ElectionGoverningBody;

public class ElectionGoverningBodyDAOHibernateTest extends BaseDaoTestCase{

	private IElectionGoverningBodyDAO electionGoverningBodyDAO;

	
	public IElectionGoverningBodyDAO getElectionGoverningBodyDAO() {
		return electionGoverningBodyDAO;
	}


	public void setElectionGoverningBodyDAO(
			IElectionGoverningBodyDAO electionGoverningBodyDAO) {
		this.electionGoverningBodyDAO = electionGoverningBodyDAO;
	}


	/*public void testGetAll(){
		List<ElectionGoverningBody> list = electionGoverningBodyDAO.getAll();
		assertEquals(list.size() >= 0, true);
	}*/
	
	/*public void testGetAllStatesForMinisters(){
		List<Object[]> list = electionGoverningBodyDAO.getAllStatesForMinisters();
		for(Object[] states:list)
		{
			System.out.println("id: "+states[0].toString()+"  name: "+states[1].toString());
		}
	}*/
/*
	public void testGetAllYearsAndElecIds(){
		List<Object[]> list = electionGoverningBodyDAO.getAllYearsAndElecIdsForAssembly(27l);
		for(Object[] years:list)
		{
			System.out.println("id: "+years[0].toString()+"  name: "+years[1].toString());
		}
	}*/
	/*public void testGetAllYearsAndElecIdsForParliament(){
		List<Object[]> list = electionGoverningBodyDAO.getAllYearsAndElecIdsForParliament();
		for(Object[] years:list)
		{
			System.out.println("id: "+years[0].toString()+"  name: "+years[1].toString());
		}
	}*/
	
	/*public void testgetAllCandidateDetails()
	{
		
		List<ElectionGoverningBody> list = electionGoverningBodyDAO.getAllCandidateDetails(245443l);
		
		for(ElectionGoverningBody params : list)
		{
			System.out.println(params.getStatus());
			System.out.println(params.getFromDate());
			System.out.println(params.getCandidate().getLastname());
			System.out.println(params.getPositionScope().getMinisterType().getMinisterType());
			System.out.println(params.getElection().getElectionYear());
			System.out.println(params.getElection().getElectionScope().getState().getStateName());
			System.out.println(params.getCandidate().getLastname());
			System.out.println(params.getCandidate().getCandidateId());
			System.out.println(params.getParty().getShortName());
			System.out.println(params.getParty().getPartyFlag());
			System.out.println(params.getParty().getPartyId());
			
		}
		
		System.out.println(list.size());
	}*/
	
	
	
	/*public void testgetAllMinistersDetails()
	{
		List<ElectionGoverningBody> list = electionGoverningBodyDAO.getAllMinistersDetails(158l);
		for(ElectionGoverningBody params: list)
		{
			System.out.println(params.getCandidate().getCandidateId());
			System.out.println(params.getCandidate().getLastname().toString());
			System.out.println(params.getParty().getPartyId());
			System.out.println(params.getParty().getShortName());
			System.out.println(params.getPositionScope().getElectionGoverningBodyPosition().getGoverningBodyPosition());
			System.out.println(params.getFromDate());
			System.out.println(params.getToDate());
			
		}
		System.out.println(list.size());
	}
	
	public void testGetMinistryYearsForParliament()
	{
		List<Object[]> list = electionGoverningBodyDAO.getMinistryYearsForParliament();
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println(params[0]+"---"+params[1]);
		}
		
	}
	
	public void testGetMinistryYearsForAssembly()
	{
		List<Object[]> list = electionGoverningBodyDAO.getMinistryYearsForAssembly(27l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println(params[0]+"---"+params[1]);
		}
		
	}*/
	
	public void testGetChiefMinisters(){
		List<Object[]> list = electionGoverningBodyDAO.getChiefMinisters(1l);
		System.out.println(list.size());
		for(Object[] params : list){
			System.out.println(params[0]);
			
		}
		
	}
}
