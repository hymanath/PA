package com.itgrids.partyanalyst.dao.hibernate;
 
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IStaticGroupDAO;
import com.itgrids.partyanalyst.model.InformationSource;
import com.itgrids.partyanalyst.model.StaticGroup;

public class StaticGroupDAOHibernateTest extends BaseDaoTestCase {

	private IStaticGroupDAO staticGroupDAO;

	
	
	/*public void testGetAll()
	{
		List<StaticGroup>staticGroup=staticGroupDao.getAll();
		
	}*/
	
	public IStaticGroupDAO getStaticGroupDAO() {
		return staticGroupDAO;
	}



	public void setStaticGroupDAO(IStaticGroupDAO staticGroupDAO) {
		this.staticGroupDAO = staticGroupDAO;
	}



	/*
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
	public void testGetAll(){
		List<StaticGroup> list = staticGroupDAO.getAll();
		System.out.println(list.size());
		for(StaticGroup staticGroup:list)
		{
			System.out.println("Static group name"+staticGroup.getGroupName().toString());
			System.out.println("static group id" +staticGroup.getStaticGroupId().toString());
		}
	//	assertEquals(1, list.size());
	}*/
	
	public void testFindAllStaticGroups(){
		List<StaticGroup> staticGroups = staticGroupDAO.findAllStaticGroups();
		if(staticGroups != null && staticGroups.size() > 0){
			for(StaticGroup result:staticGroups){
			System.out.println("Static Groups :" + result.getGroupName());
			}
		}
	}
}
