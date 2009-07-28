package com.itgrids.partyanalyst.dao.jpa;

import java.util.Set;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.model.State;

public class CountryDAOJpaTest extends BaseDaoTestCase {
	
	private ICountryDAO countryDAO;

	public void setCountryDAO(ICountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}
	
	//@Test
	public void testFindStates() {
		Set<State> states = countryDAO.get(new Long(1)).getStates();
		Assert.assertEquals(2, states.size());
	}
	
}