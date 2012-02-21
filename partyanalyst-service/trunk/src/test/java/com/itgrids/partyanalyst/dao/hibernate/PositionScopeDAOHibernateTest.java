package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPositionScopeDAO;

public class PositionScopeDAOHibernateTest  extends BaseDaoTestCase{
  private IPositionScopeDAO positionScopeDAO;

public void setPositionScopeDAO(IPositionScopeDAO positionScopeDAO) {
	this.positionScopeDAO = positionScopeDAO;
}
  
  public void test()
  {
	  positionScopeDAO.getAll();
  }
}
