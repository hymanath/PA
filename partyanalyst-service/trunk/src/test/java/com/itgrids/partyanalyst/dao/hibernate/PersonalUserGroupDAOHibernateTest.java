package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IPersonalUserGroupDAO;
import com.itgrids.partyanalyst.model.PersonalUserGroup;

public class PersonalUserGroupDAOHibernateTest extends BaseDaoTestCase {
	IPersonalUserGroupDAO  personalUserGroupDAO;

	public IPersonalUserGroupDAO getPersonalUserGroupDAO() {
		return personalUserGroupDAO;
	}

	public void setPersonalUserGroupDAO(IPersonalUserGroupDAO personalUserGroupDAO) {
		this.personalUserGroupDAO = personalUserGroupDAO;
	}
	
	public void testFindByGroupName()
	{
		List<PersonalUserGroup> result=personalUserGroupDAO.findByGroupName("media");
		for(PersonalUserGroup personalUserGroup:result)
		{
			System.out.println("personal user group id" +personalUserGroup.getPersonalUserGroupId().toString());
			System.out.println("personal user group description" +personalUserGroup.getDescription().toString());
			
		}
		assertEquals(1,result.size());
	}

}
