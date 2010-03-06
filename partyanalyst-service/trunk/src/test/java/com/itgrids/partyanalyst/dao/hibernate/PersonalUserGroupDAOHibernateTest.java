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
	
	/*public void testGetAllMyGroupsByUserId()
	{
		List<PersonalUserGroup> result=personalUserGroupDAO.getAllMyGroupsByUserId(1L);
		for(PersonalUserGroup personalUserGroup:result){
			System.out.println(personalUserGroup.getPersonalUserGroupId());
			System.out.println(personalUserGroup.getGroupName());	
		}
		assertEquals(1,result.size());
	}*/
	
	/*public void testFindSubGroupsCountInSystemGroupsByUserId()
	{
		List result=personalUserGroupDAO.findSubGroupsCountInSystemGroupsByUserId(1L);
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.print(Long.parseLong(parms[0].toString()));
			System.out.print(parms[1].toString());
			System.out.print(Long.parseLong(parms[2].toString()));
		}	
		//assertEquals(1,result.size());		
	}*/
	
	@SuppressWarnings("unchecked")
	public void testGetSubGroupsCountInMyGroupsByUserId()
	{
		List result=personalUserGroupDAO.getSubGroupsCountInMyGroupsByUserId(1L,2L);
		/*for(PersonalUserGroup personalUserGroup:result){
			System.out.println(personalUserGroup.getPersonalUserGroupId());
			System.out.println(personalUserGroup.getGroupName());	
		}*/
			Object[] parms = (Object[])result.get(0);
			System.out.println(Long.parseLong(parms[0].toString()));
			System.out.println(parms[1].toString());
			System.out.print(Long.parseLong(parms[2].toString()));			
		
       
		//System.out.println(result.size());
	}

}
