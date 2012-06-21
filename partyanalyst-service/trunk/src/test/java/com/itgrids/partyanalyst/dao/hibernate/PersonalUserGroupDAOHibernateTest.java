package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IPersonalUserGroupDAO;
import com.itgrids.partyanalyst.model.PersonalUserGroup;

public class PersonalUserGroupDAOHibernateTest extends BaseDaoTestCase {
	private IPersonalUserGroupDAO  personalUserGroupDAO;

	public IPersonalUserGroupDAO getPersonalUserGroupDAO() {
		return personalUserGroupDAO;
	}

	public void setPersonalUserGroupDAO(IPersonalUserGroupDAO personalUserGroupDAO) {
		this.personalUserGroupDAO = personalUserGroupDAO;
	}
	/*
	@SuppressWarnings("unchecked")
	@Test
	public void testGetCompleteUserGroupMemberDetailsForAGroup(){
		List resultsList = personalUserGroupDAO.getCompleteUserGroupDetailsForAGroup(10L);
		if(resultsList != null)
			System.out.println(" Results List Size :" + resultsList.size());
	}*/
	
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
	/*
	@SuppressWarnings("unchecked")
	public void testGetSubGroupsCountInMyGroupsByUserId()
	{
		List result=personalUserGroupDAO.getSubGroupsCountInMyGroupsByUserId(1L,2L);
		for(PersonalUserGroup personalUserGroup:result){
			System.out.println(personalUserGroup.getPersonalUserGroupId());
			System.out.println(personalUserGroup.getGroupName());	
		}
			Object[] parms = (Object[])result.get(0);
			System.out.println(Long.parseLong(parms[0].toString()));
			System.out.println(parms[1].toString());
			System.out.print(Long.parseLong(parms[2].toString()));			
		
       
		//System.out.println(result.size());
	}*/

	/*public void testGetSubGroupsCompleteDetailsForASystemGroup()
	{
		List result=personalUserGroupDAO.getSubGroupsCompleteDetailsForASystemGroup(1L, 1L);
		System.out.println(result.size());
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.print(Long.parseLong(parms[0].toString()));
			System.out.println(parms[1].toString());
			
		}	
		
	}*/
	public void testGetGroupsByName()
	{
		List result = personalUserGroupDAO.getGroupsByName(1L, "aaa");
		if(Long.parseLong(result.get(0).toString())==0L){
			System.out.println("fale");
		}else{
			System.out.println("true");
		}
		System.out.println(result.get(0).toString());
		System.out.println(result.size());			
		
		
	}
	/*
	public void testFindAllGroupCategoriesInfoAndCountsOfLocationsByLocation(){
		List list = personalUserGroupDAO.findAllGroupCategoriesInfoAndCountsOfLocationsByLocation("model.localGroupRegion.district.districtId", 
				1l, 19l, "model.localGroupRegion.district.districtId");
		System.out.println(list.size());
	}*/
	
	/*public void testFindGroupsInfoByCategoryAndUserId(){
		//rawData = personalUserGroupDAO.findAllGroupCategoriesInfoAndCountsOfLocationsByLocation("model.localGroupRegion.district.districtId", userId, 
		//Long.parseLong(accessValue), compareLocationInfo)
		List list = personalUserGroupDAO.findGroupsInfoByCategoryAndUserIdByRegion(1l, 1l, "model.localGroupRegion.state.stateId");
		System.out.println(list.size());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetLocalUserGroupsOverview(){
		
		List results = personalUserGroupDAO.getLocalGroupDetailsInConstituency(1L,232L,1L);
		if(results != null){
			System.out.println(" Results Size :" + results.size());
			
		}
	}*/
	
	/*@SuppressWarnings("unchecked")
	@Test
	public void testGetLocalUserGroupDetailsById(){
		List<Object[]>results = personalUserGroupDAO.getLocalUserGroupDetailsById(15L);
		
		System.out.println(" Results Size :" + results.size());
		for(int i=0;i<results.size();i++){
			Object[] parms = (Object[])results.get(i);
			System.out.println(parms[0].toString());
			System.out.println(parms[1].toString());
			System.out.println(parms[2].toString());
			System.out.println(parms[3].toString());
			System.out.println(parms[4].toString());
		}
	}*/
	
	/*public void testGetTotalCountOfLocalGroupsInAssemblyConstituencysGroupByConstituency()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(223l);
		ids.add(241l);
		ids.add(232l);
		ids.add(233l);
		ids.add(340l);
		ids.add(341l);
		ids.add(238l);
		
		List result = personalUserGroupDAO.getTotalCountOfLocalGroupsInAssemblyConstituencysGroupByConstituency(6l, ids, 1l);
		System.out.println(" Results Size :" + result.size());
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.println(parms[0].toString());
			System.out.println(parms[1].toString());
			
		}
	}*/
	
	/*public void testGetTotalCountOfLocalGroupsInAssemblyConstituencies()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(223l);
		ids.add(241l);
		ids.add(232l);
		ids.add(233l);
		ids.add(340l);
		ids.add(341l);
		ids.add(238l);
		
		List result = personalUserGroupDAO.getTotalCountOfLocalGroupsInAssemblyConstituencies(6l, ids, 1l);
		System.out.println(" Results Size :" + result.size());
		Long count  = Long.parseLong(result.get(0).toString());
	}*/
}
