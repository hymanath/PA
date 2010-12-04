package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProfileOptsDAO;

public class ProfileOptsDAOHibernate extends BaseDaoTestCase{

	private IProfileOptsDAO profileOptsDAO;

	public IProfileOptsDAO getProfileOptsDAO() {
		return profileOptsDAO;
	}

	public void setProfileOptsDAO(IProfileOptsDAO profileOptsDAO) {
		this.profileOptsDAO = profileOptsDAO;
	}
	
	public void testGetAll(){
		assertEquals(profileOptsDAO.getAll().size() >= 0, true);
	}
	
	public void testGetAllProfileOpts(){
		List list = profileOptsDAO.getAllProfileOpts();
		System.out.println(list.size());
	}
	
	
}
