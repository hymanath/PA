package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.model.Hamlet;

public class HamletDAOHibernateTest extends BaseDaoTestCase{
	private IHamletDAO hamletDAO;

	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}
	
	public void testFindByTehsilTownshipAndHamletName(){
		List<Hamlet> list = hamletDAO.findByTehsilTownshipAndHamletName(new Long(844), "Isakapalle", "Juvvagulakshmipuram");
		assertEquals(2, list.size());
	}
	
	public void testFindByHamletNameAndTownshipId(){
		List list = hamletDAO.findByHamletNameAndTownshipId(21818l, "GOGULAPALLI");
		assertEquals(list.size(), 1);
	}
	
	public void testGetHamletId(){
		List  list = hamletDAO.getHamletIdBasedOnDistrictNameMandalIdAndTownship("Nellore","Allur","Singapeta","Singapeta");
		System.out.println(list.get(0).toString());
	}
	
}
