package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IStateDAO;
public class StateDAOHibernateTest extends BaseDaoTestCase{
  private  IStateDAO stateDAO;

public void setStateDAO(IStateDAO stateDAO) {
	this.stateDAO = stateDAO;
}
public void test()
{
	List list = stateDAO.findStatesByCountryIdAndCountryAccess(1L, 1L);
	System.out.println(list.size());
}


public void testFindStatesByCountryIdAndCountryAccessAndStateAccess()
{
	List list = stateDAO.findStatesByCountryIdAndCountryAccessAndStateAccess(1L, 1L);
	System.out.println(list.size());
}

}
