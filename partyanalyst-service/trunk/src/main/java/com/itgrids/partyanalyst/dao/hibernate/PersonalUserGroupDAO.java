/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February, 2010
 * Author Saikrishna.g
 */

package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPersonalUserGroupDAO;
import com.itgrids.partyanalyst.model.MyGroup;
import com.itgrids.partyanalyst.model.PersonalUserGroup;

public class PersonalUserGroupDAO extends GenericDaoHibernate<PersonalUserGroup, Long> implements
		IPersonalUserGroupDAO {

	public PersonalUserGroupDAO()
	{
		super(PersonalUserGroup.class);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PersonalUserGroup> getAllMyGroupsByUserId(Long userId){		
		return getHibernateTemplate().find("from PersonalUserGroup model where model.createdUserId.registrationId = ? and model.staticGroup.staticGroupId = null and model.parentGroupId = null"  ,userId);
	}
	
	@SuppressWarnings("unchecked")
	public List findSubGroupsCountInSystemGroupsByUserId(Long userId)
	{
		return getHibernateTemplate().find("select model.staticGroup.staticGroupId, model.staticGroup.groupName, count(model.personalUserGroupId) from PersonalUserGroup model where model.createdUserId.registrationId = ? and model.staticGroup.staticGroupId != null and model.myGroup.myGroupId is null and model.parentGroupId.personalUserGroupId is null group by model.staticGroup.staticGroupId", userId);
	}
	
	@SuppressWarnings("unchecked")
	public List getSubGroupsCountInMyGroupsByUserId(Long userId, Long myGroupId)
	{
		Object[] params = {userId, myGroupId};	
		return getHibernateTemplate().find("select model.parentGroupId.personalUserGroupId, model.parentGroupName, count(model.personalUserGroupId) from PersonalUserGroup model where model.createdUserId.registrationId = ? and model.myGroup.myGroupId is not null and model.staticGroup.staticGroupId is null and model.parentGroupId.personalUserGroupId = ? group by model.parentGroupId.personalUserGroupId", params);
	}


	@SuppressWarnings("unchecked")
	public List<MyGroup> getMyGroupObjFromPersonalUserGroup(Long personalUserGroupId) {
		
		return getHibernateTemplate().find("select model.myGroup from PersonalUserGroup model where model.personalUserGroupId = ?", personalUserGroupId );
		
	}
	
	@SuppressWarnings("unchecked")
	public List getSubGroupsCountForASystemGroup(Long groupId,Long userId){
        System.out.println("Inside getSubGroupsCountForASystemGroup DAO ..");
		Object[] params = {groupId,userId};
		return getHibernateTemplate().find("select count(model.personalUserGroupId) from PersonalUserGroup model where model.staticGroup is not null and model.myGroup is null and model.parentGroupId is null and model.staticGroup.staticGroupId = ? and model.createdUserId.registrationId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getSubGroupsCompleteDetailsForASystemGroup(Long groupId,Long userId){
		System.out.println("Inside getSubGroupsCompleteDetailsForASystemGroup DAO ..");
		Object[] params = {groupId,userId};
		return getHibernateTemplate().find("select model.personalUserGroupId,model.groupName,model.description,model.createdDate from PersonalUserGroup model where model.staticGroup is not null and model.myGroup is null and model.parentGroupId is null and model.staticGroup.staticGroupId = ? and model.createdUserId.registrationId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getSubGroupsCountForASystemGroupFromPersonalUserGroup(Long groupId,Long userId){
		Object[] params = {groupId,userId};
		return getHibernateTemplate().find("select count(model.personalUserGroupId) from PersonalUserGroup model where model.staticGroup is not null and model.myGroup is null and model.parentGroupId.personalUserGroupId = ? and model.createdUserId.registrationId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getSubGroupsCompleteDetailsForASystemGroupFromPersonalUserGroup(Long groupId,Long userId){
		Object[] params = {groupId,userId};
		return getHibernateTemplate().find("select model.personalUserGroupId,model.groupName,model.description,model.createdDate from PersonalUserGroup model where model.staticGroup is not null and model.myGroup is null and model.parentGroupId.personalUserGroupId = ? and model.createdUserId.registrationId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getSubGroupsDetailsForMyGroupFromPersonalUserGroup(Long groupId,Long userId){
		Object[] params = {groupId,userId};
		return getHibernateTemplate().find("select model.personalUserGroupId,model.groupName,model.description,model.createdDate from PersonalUserGroup model where model.staticGroup is null and model.myGroup is not null and model.personalUserGroupId = ? and model.createdUserId.registrationId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getSubGroupsCountForMyGroupFromPersonalUserGroup(Long groupId,Long userId){
		Object[] params = {groupId,userId};
		return getHibernateTemplate().find("select count(model.personalUserGroupId) from PersonalUserGroup model where model.staticGroup is null and model.myGroup is not null and model.parentGroupId.personalUserGroupId = ? and model.createdUserId.registrationId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getCompleteUserGroupDetailsForAGroup(Long groupId) {
		return getHibernateTemplate().find("select model.personalUserGroupId,model.groupName,model.description,model.createdDate,"+
				"model.staticLocalGroup.staticLocalGroupId,model.staticLocalGroup.groupType,model.staticLocalGroup.description,"+
				"model.localGroupRegion.groupRegionScope,model.localGroupRegion.state,model.localGroupRegion.district,"+
				"model.localGroupRegion.constituency,model.localGroupRegion.tehsil,model.localGroupRegion.township,"+
				"model.localGroupRegion.localBody,model.localGroupRegion.ward,model.localGroupRegion.hamlet "+
				"from PersonalUserGroup model where model.staticGroup is null and model.myGroup is null and "+
				"model.personalUserGroupId = ?",groupId);
	}
	
	@SuppressWarnings("unchecked")
	public List getSubGroupsCompleteDetailsForMyGroup(Long groupId,Long userId){
		Object[] params = {groupId,userId};
		return getHibernateTemplate().find("select model.personalUserGroupId,model.groupName,model.description,model.createdDate from PersonalUserGroup model where model.staticGroup is null and model.myGroup is not null and model.parentGroupId.personalUserGroupId = ? and model.createdUserId.registrationId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getGroupsByName(Long userId, String groupName) {
		Object[] params = {userId,groupName};
		return getHibernateTemplate().find("select count(model.personalUserGroupId) from PersonalUserGroup model where model.createdUserId.registrationId = ? and  model.groupName = ?",params);
	}
    
    public List findAllGroupCategoriesInfoAndCountsOfLocationsByLocation(String countLocationInfo, Long userId, Long locationId, String compareLocationInfo ){
		Object[] params = {userId, locationId};
		return getHibernateTemplate().find("select model.staticLocalGroup.staticLocalGroupId, model.staticLocalGroup.groupType, " +
				"count(distinct "+countLocationInfo+"), count(model.personalUserGroupId) from PersonalUserGroup model " +
				"where model.createdUserId.registrationId = ? and "+compareLocationInfo+" = ? " +
						"group by model.staticLocalGroup.staticLocalGroupId", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<PersonalUserGroup> findGroupsInfoByCategoryAndUserIdByRegion(Long userId, Long categoryId, String regionInfo){
		Object[] params = {userId, categoryId};
		return getHibernateTemplate().find("from PersonalUserGroup model where model.createdUserId.registrationId = ? and " +
				"model.staticLocalGroup.staticLocalGroupId = ? group by "+regionInfo,params);
	}
	
	@SuppressWarnings("unchecked")
	public List<PersonalUserGroup> findGroupsInfoByCategoryAndUserId(Long userId, Long categoryId){
		Object[] params = {userId, categoryId};
		return getHibernateTemplate().find("from PersonalUserGroup model where model.createdUserId.registrationId = ? and " +
				"model.staticLocalGroup.staticLocalGroupId = ? ",params);
	}


	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInConstituency(Long userId,
			Long constituencyId,Long categoryId) {
		Object[] params = {userId, constituencyId,categoryId};
		return getHibernateTemplate().find("select count(model.personalUserGroupId) from PersonalUserGroup model where "+
				"model.createdUserId.registrationId = ? and model.staticLocalGroup is not null and model.localGroupRegion "+
				"is not null and model.localGroupRegion.constituency is not null and model.localGroupRegion.constituency.constituencyId = ? "+
				"and model.staticLocalGroup.staticLocalGroupId = ?",params);
	}


	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInConstituencysByDistrict(
			Long userId, Long districtId,Long categoryId) {
		Object[] params = {userId, districtId,categoryId};
		return getHibernateTemplate().find("select count(model.personalUserGroupId),model.localGroupRegion.constituency.constituencyId "+
				"from PersonalUserGroup model where "+
				"model.createdUserId.registrationId = ? and model.staticLocalGroup is not null and model.localGroupRegion "+
				"is not null and model.localGroupRegion.district is not null and model.localGroupRegion.district.districtId = ? "+
				"and model.staticLocalGroup.staticLocalGroupId = ? and model.localGroupRegion.constituency is not null "+
				"group by model.localGroupRegion.constituency.constituencyId",params);
	}


	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInDistrict(Long userId,
			Long districtId,Long categoryId) {
		Object[] params = {userId, districtId,categoryId};
		return getHibernateTemplate().find("select count(model.personalUserGroupId) from PersonalUserGroup model where "+
				"model.createdUserId.registrationId = ? and model.staticLocalGroup is not null and model.localGroupRegion "+
				"is not null and model.localGroupRegion.district is not null and model.localGroupRegion.district.districtId = ? "+
				"and model.staticLocalGroup.staticLocalGroupId = ?",params);
	}


	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInDistrictsByState(Long userId,
			Long stateId,Long categoryId) {
		Object[] params = {userId, stateId,categoryId};
		return getHibernateTemplate().find("select count(model.personalUserGroupId),model.localGroupRegion.district.districtId "+
				"from PersonalUserGroup model where "+
				"model.createdUserId.registrationId = ? and model.staticLocalGroup is not null and model.localGroupRegion "+
				"is not null and model.localGroupRegion.state is not null and model.localGroupRegion.state.stateId = ? "+
				"and model.staticLocalGroup.staticLocalGroupId = ? and model.localGroupRegion.district is not null "+
				"group by model.localGroupRegion.district.districtId",params);
	}


	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInLocalBodys(Long userId,
			Long localBodyId,Long categoryId) {
		Object[] params = {userId, localBodyId,categoryId};
		return getHibernateTemplate().find("select count(model.personalUserGroupId) from PersonalUserGroup model where "+
				"model.createdUserId.registrationId = ? and model.staticLocalGroup is not null and model.localGroupRegion "+
				"is not null and model.localGroupRegion.localBody is not null and model.localGroupRegion.localBody.localElectionBodyId = ? "+
				"and model.staticLocalGroup.staticLocalGroupId = ?",params);
	}


	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInLocalBodysByConstituency(
			Long userId, Long constituencyId,Long categoryId) {
		Object[] params = {userId, constituencyId,categoryId};
		return getHibernateTemplate().find("select count(model.personalUserGroupId),model.localGroupRegion.localBody.localElectionBodyId "+
				"from PersonalUserGroup model where "+
				"model.createdUserId.registrationId = ? and model.staticLocalGroup is not null and model.localGroupRegion "+
				"is not null and model.localGroupRegion.constituency is not null and model.localGroupRegion.constituency.constituencyId = ? "+
				"and model.staticLocalGroup.staticLocalGroupId = ? and model.localGroupRegion.localBody is not null "+
				"group by model.localGroupRegion.localBody.localElectionBodyId",params);
	}


	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInState(Long userId, Long stateId,Long categoryId) {
		
		Object[] params = {userId, stateId,categoryId};
		return getHibernateTemplate().find("select count(model.personalUserGroupId) from PersonalUserGroup model where "+
				"model.createdUserId.registrationId = ? and model.staticLocalGroup is not null and model.localGroupRegion "+
				"is not null and model.localGroupRegion.state is not null and model.localGroupRegion.state.stateId = ? "+
				"and model.staticLocalGroup.staticLocalGroupId = ?",params);
	}


	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInTehsil(Long userId, Long tehsilId,Long categoryId) {
		
		Object[] params = {userId, tehsilId,categoryId};
		return getHibernateTemplate().find("select count(model.personalUserGroupId) from PersonalUserGroup model where "+
				"model.createdUserId.registrationId = ? and model.staticLocalGroup is not null and model.localGroupRegion "+
				"is not null and model.localGroupRegion.tehsil is not null and model.localGroupRegion.tehsil.tehsilId = ? "+
				"and model.staticLocalGroup.staticLocalGroupId = ?",params);
	}


	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInTehsilsByConstituency(Long userId,
			Long constituencyId,Long categoryId) {

		Object[] params = {userId, constituencyId,categoryId};
		return getHibernateTemplate().find("select count(model.personalUserGroupId),model.localGroupRegion.tehsil.tehsilId "+
				"from PersonalUserGroup model where "+
				"model.createdUserId.registrationId = ? and model.staticLocalGroup is not null and model.localGroupRegion "+
				"is not null and model.localGroupRegion.constituency is not null and model.localGroupRegion.constituency.constituencyId = ? "+
				"and model.staticLocalGroup.staticLocalGroupId = ? and model.localGroupRegion.tehsil is not null "+
				"group by model.localGroupRegion.tehsil.tehsilId",params);
	}


	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInVillage(Long userId, Long villageId,Long categoryId) {
		
		Object[] params = {userId, villageId,categoryId};
		return getHibernateTemplate().find("select count(model.personalUserGroupId) from PersonalUserGroup model where "+
				"model.createdUserId.registrationId = ? and model.staticLocalGroup is not null and model.localGroupRegion "+
				"is not null and model.localGroupRegion.hamlet is not null and model.localGroupRegion.hamlet.hamletId = ? "+
				"and model.staticLocalGroup.staticLocalGroupId = ?",params);
	}


	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInVillagesByTehsil(Long userId,
			Long tehsilId,Long categoryId) {

		Object[] params = {userId, tehsilId,categoryId};
		return getHibernateTemplate().find("select count(model.personalUserGroupId),model.localGroupRegion.hamlet.hamletId "+
				"from PersonalUserGroup model where "+
				"model.createdUserId.registrationId = ? and model.staticLocalGroup is not null and model.localGroupRegion "+
				"is not null and model.localGroupRegion.tehsil is not null and model.localGroupRegion.tehsil.tehsilId = ? "+
				"and model.staticLocalGroup.staticLocalGroupId = ? and model.localGroupRegion.hamlet is not null "+
				"group by model.localGroupRegion.hamlet.hamletId",params);
	}


	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInWard(Long userId, Long wardId,Long categoryId) {
		
		Object[] params = {userId, wardId,categoryId};
		return getHibernateTemplate().find("select count(model.personalUserGroupId) from PersonalUserGroup model where "+
				"model.createdUserId.registrationId = ? and model.staticLocalGroup is not null and model.localGroupRegion "+
				"is not null and model.localGroupRegion.ward is not null and model.localGroupRegion.ward.constituencyId = ? "+
				"and model.staticLocalGroup.staticLocalGroupId = ?",params);
	}


	@SuppressWarnings("unchecked")
	public List getTotalCountOfLocalGroupsInWardsByLocalBodys(Long userId,
			Long localBodyId,Long categoryId) {
		
		Object[] params = {userId, localBodyId,categoryId};
		return getHibernateTemplate().find("select count(model.personalUserGroupId),model.localGroupRegion.ward.constituencyId "+
				"from PersonalUserGroup model where "+
				"model.createdUserId.registrationId = ? and model.staticLocalGroup is not null and model.localGroupRegion "+
				"is not null and model.localGroupRegion.localBody is not null and model.localGroupRegion.localBody.localElectionBodyId = ? "+
				"and model.staticLocalGroup.staticLocalGroupId = ? and model.localGroupRegion.ward is not null "+
				"group by model.localGroupRegion.ward.constituencyId",params);
	}


	@SuppressWarnings("unchecked")
	public List getTotalLocalUserGroupsByScope(Long userId) {
		
		return getHibernateTemplate().find("select count(model.personalUserGroupId) from PersonalUserGroup model where "+
				"model.createdUserId.registrationId = ? and model.localGroupRegion is not null group by model.localGroupRegion."+
				"groupRegionScope order by model.localGroupRegion.groupRegionScope",userId);
	}


	@SuppressWarnings("unchecked")
	public List getTotalLocalUserGroupsByScope(Long userId, String scope) {
		
		Object[] params = {userId,scope};
		return getHibernateTemplate().find("select count(model.personalUserGroupId) from PersonalUserGroup model where "+
				"model.createdUserId.registrationId = ? and model.localGroupRegion is not null group by model.localGroupRegion."+
				"groupRegionScope = ?",params);
	}


	@SuppressWarnings("unchecked")
	public List getDistinctLocalGroupCategorysForAUser(Long userId) {
		
		return getHibernateTemplate().find("select distinct model.staticLocalGroup.staticLocalGroupId,model.staticLocalGroup.groupType "+
				"from PersonalUserGroup model where model.createdUserId.registrationId = ? and model.staticLocalGroup is not null order by "+
				"model.staticLocalGroup.groupType",userId);
	}
	
}
