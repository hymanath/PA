package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IInfluencingPeopleDAO;

public class InfluencingPeopleDAOHibernateTest extends BaseDaoTestCase {
	
	private IInfluencingPeopleDAO influencingPeopleDAO;

	public IInfluencingPeopleDAO getInfluencingPeopleDAO() {
		return influencingPeopleDAO;
	}

	public void setInfluencingPeopleDAO(IInfluencingPeopleDAO influencingPeopleDAO) {
		this.influencingPeopleDAO = influencingPeopleDAO;
	}
	
	public void test(){
		List result = influencingPeopleDAO.findByHamletId(12l);
		System.out.println(result.size());
	}
}
