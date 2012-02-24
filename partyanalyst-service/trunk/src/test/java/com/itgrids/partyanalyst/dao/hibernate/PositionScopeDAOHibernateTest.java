package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

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
  public void testGetStateDetails()
  {
	  
	  List<Object[]> data = positionScopeDAO.getStateDetails(5l,"Main");
	  for(Object[] result: data)
	  {
		  System.out.println("id:  "+result[0].toString()+"name "+result[1].toString());
	  }
  }
  /*public void testGetPositionTypeDetails()
  {
	  
	  List<Object> data = positionScopeDAO.getPositionTypeDetails(5l);
	  for(Object result: data)
	  {
		  System.out.println("name "+result.toString());
	  }
  }*/
}
