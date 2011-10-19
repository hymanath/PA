package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IInfluencingPeopleDAO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.model.InfluencingPeople;

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
	public void testGetInfluencingPeopleDetails(){
		List results = influencingPeopleDAO.getTotalInfluencingPeopleDetailsByInfluencingScope(385l,"constituency","347");
		System.out.println(" Results Size :" + results.size());
	}
}
