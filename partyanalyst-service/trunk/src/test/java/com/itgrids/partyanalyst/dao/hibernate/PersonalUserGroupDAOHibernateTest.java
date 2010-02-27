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
	/*
	public void testFindByGroupName()
	{
		List<PersonalUserGroup> result=personalUserGroupDAO.findByGroupName("media");
		for(PersonalUserGroup personalUserGroup:result)
		{
			System.out.println("personal user group id" +personalUserGroup.getPersonalUserGroupId().toString());
			System.out.println("personal user group description" +personalUserGroup.getDescription().toString());
			
		}
		assertEquals(1,result.size());
	}*/
	
	public void testFindMyGroupsByUserId()
	{
		List<PersonalUserGroup> result=personalUserGroupDAO.findMyGroupsByUserId(1L);
		for(PersonalUserGroup personalUserGroup:result){
			System.out.println(personalUserGroup.getPersonalUserGroupId());
			System.out.println(personalUserGroup.getGroupName());
			System.out.println(personalUserGroup.getParentGroupId());
		}
		assertEquals(1,result.size());
	}
	
	public void testFindSubGroupsInSystemGroupsByUserId()
	{
		List result=personalUserGroupDAO.findSubGroupsInSystemGroupsByUserId(1L);
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.println(Long.parseLong(parms[0].toString()));
			System.out.println(parms[1].toString());
			System.out.println(Long.parseLong(parms[2].toString()));
		}	
		//assertEquals(1,result.size());
		System.out.println(result.size());
	}

}
