package com.itgrids.partyanalyst.dao.hibernate;
 
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IStaticGroupDAO;
import com.itgrids.partyanalyst.model.StaticGroup;

public class StaticGroupDAOHibernateTest extends BaseDaoTestCase {

	private IStaticGroupDAO staticGroupDAO;

	public void setStaticGroupDao(IStaticGroupDAO staticGroupDAO) {
		this.staticGroupDAO = staticGroupDAO;
	}
	
	/*public void testGetAll()
	{
		List<StaticGroup>staticGroup=staticGroupDao.getAll();
		
	}*/
	
	
	public void testfindByGroupDescription()
	{
		List<StaticGroup> result=staticGroupDAO.findByGroupDescription("toi");
		for(StaticGroup staticGroup:result)
		{
			System.out.println("Static group name"+staticGroup.getGroupName().toString());
			System.out.println("static group id" +staticGroup.getStaticGroupId().toString());
		}
		assertEquals(1, result.size());
	}
}
