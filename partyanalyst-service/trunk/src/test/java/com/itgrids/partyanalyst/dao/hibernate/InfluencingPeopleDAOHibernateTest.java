package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.hibernate.Query;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IInfluencingPeopleDAO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.model.Voter;

public class InfluencingPeopleDAOHibernateTest extends BaseDaoTestCase {
	
	private IInfluencingPeopleDAO influencingPeopleDAO;

	public IInfluencingPeopleDAO getInfluencingPeopleDAO() {
		return influencingPeopleDAO;
	}

	public void setInfluencingPeopleDAO(IInfluencingPeopleDAO influencingPeopleDAO) {
		this.influencingPeopleDAO = influencingPeopleDAO;
	}
	
	/*
	@SuppressWarnings("unchecked")
	public void test(){
		List<InfluencingPeople> result = influencingPeopleDAO.findByHamletId(61l);
		System.out.println(result.size());
			for( InfluencingPeople influencingPeople: result)
			{
				System.out.println("id:"+influencingPeople.getInfluencingPeopleId());
				System.out.println("Hamlet Name:"+influencingPeople.getHamlet().getHamletName());
				System.out.println("Name:"+influencingPeople.getFirstName());
			}
				
	}*/
	
	/*public void test(){
		List list = influencingPeopleDAO.findByTehsils("836");
	}*/
	
	@SuppressWarnings("unchecked")
	@Test
/*	public void testGetInfluencingPeopleDetails(){
		List results = influencingPeopleDAO.getTotalInfluencingPeopleDetailsByInfluencingScope(385l,"constituency","347");
		System.out.println(" Results Size :" + results.size());
	}*/
	/*public void testGetTotalInfluencingPeopleDetailsInState(){
		List results = influencingPeopleDAO.getTotalInfluencingPeopleDetailsInState(1l,1l);
		System.out.println(" Results Size :" + results.size());
	}*/
	/*public void testGetinfluencingPeopleVoterId(){
		List<Long> results = influencingPeopleDAO.getinfluencingPeopleVoterId(2172883l);
		System.out.println(results);
	}
	public void testFindInfluencingPeopleDetails()
	{
		List<Long> voterIds = new ArrayList<Long>();
		voterIds.add(2172793l);
		voterIds.add(2130033l);
		voterIds.add(2152480l);
		voterIds.add(20l);
		voterIds.add(210l);
		List<Long> list = influencingPeopleDAO.findInfluencingPeopleDetails(voterIds,1l);
		System.out.println(list.size());
	}*/
	
	
public void testgetVotersMobileDetailsByConstituencyId(){
		
		List list =influencingPeopleDAO.getVotersMobileDetailsByConstituencyId(1l,"1013","MANDAL");
		System.out.println(list);
	}
}
