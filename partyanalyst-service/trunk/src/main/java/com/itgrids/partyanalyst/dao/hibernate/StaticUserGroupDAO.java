package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IStaticUserGroupDAO;
import com.itgrids.partyanalyst.model.StaticUserGroup;

public class StaticUserGroupDAO extends GenericDaoHibernate<StaticUserGroup, Long> implements IStaticUserGroupDAO {

	public StaticUserGroupDAO() {
		super(StaticUserGroup.class);
	}

	@SuppressWarnings("unchecked")
	public List getGroupMembersCountForAGroup(Long groupId){
		//Object[] params = {groupId,userId};
		return getHibernateTemplate().find("select count(model.staticUserGroupId) from StaticUserGroup model where model.personalUserGroup.personalUserGroupId = ? ",groupId);
	}
	

	@SuppressWarnings("unchecked")
	public List findMembersByUserId(Long registrationId, Long groupId){
		Object [] params = {registrationId, groupId};
		return getHibernateTemplate().find("select model.staticUser.name,model.staticUser.mobileNumber,model.staticUser.emailId," +
				" model.staticUser.address,model.staticUser.location,model.staticUser.designation,model.staticUser.phoneNumber" +
				" from StaticUserGroup model where model.personalUserGroup.createdUserId.registrationId= ? " +
				" and model.personalUserGroup.personalUserGroupId = ?",params);
		}
	

	@SuppressWarnings("unchecked")
	public List getGroupMembersMobileNoForAGroup(Long groupId,Long userId){
		Object[] params = {groupId,userId};
		return getHibernateTemplate().find("select model.staticUser.mobileNumber from StaticUserGroup model where model.personalUserGroup.personalUserGroupId = ? and model.staticUser.staticUserId = ?",params);
	}


	@SuppressWarnings("unchecked")
	public List getGroupMembersByName(Long groupId,
			String memberName) {
		Object[] params = {groupId, memberName};
		return getHibernateTemplate().find("select count(model.staticUser.staticUserId) from StaticUserGroup model where model.personalUserGroup.personalUserGroupId = ? and model.staticUser.name = ?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List getGroupMembersMobileNumbers(Long groupId) {
		return getHibernateTemplate().find("select model.staticUser.mobileNumber from StaticUserGroup model where model.personalUserGroup.personalUserGroupId = ? ",groupId);
	}
	
	@SuppressWarnings("unchecked")
	public List getCompleteUserGroupMemberDetailsForAGroup(Long groupId){
		return getHibernateTemplate().find("select model.staticUser.staticUserId,model.staticUser.name,model.staticUser.mobileNumber,model.staticUser.emailId,"+
				"model.staticUser.address,model.staticUser.staticUserDesignation.designationType,model.staticUser.staticUserDesignation.description,"+
				"model.personalUserGroup.personalUserGroupId,model.personalUserGroup.groupName,model.personalUserGroup.description,"+
				"model.personalUserGroup.createdDate,model.personalUserGroup.staticLocalGroup.staticLocalGroupId,"+
				"model.personalUserGroup.staticLocalGroup.description,model.personalUserGroup.staticLocalGroup.groupType,"+
				"model.personalUserGroup.localGroupRegion.groupRegionScope,model.personalUserGroup.localGroupRegion.state,"+
				"model.personalUserGroup.localGroupRegion.district,model.personalUserGroup.localGroupRegion.constituency,"+
				"model.personalUserGroup.localGroupRegion.tehsil,model.personalUserGroup.localGroupRegion.township,"+
				"model.personalUserGroup.localGroupRegion.localBody,model.personalUserGroup.localGroupRegion.ward,"+
				"model.personalUserGroup.localGroupRegion.hamlet from StaticUserGroup model where model.personalUserGroup.staticGroup is null "+
				"and model.personalUserGroup.myGroup is null and model.personalUserGroup.staticLocalGroup is not null and "+
				"model.personalUserGroup.personalUserGroupId = ?",groupId);
	}
	
	@SuppressWarnings("unchecked")
	public List getCompleteUserGroupMemberDetailsForAGroupCatgory(Long groupCategoryId,Long userId){
		Object[] params = {groupCategoryId,userId};
		return getHibernateTemplate().find("select model.staticUser.staticUserId,model.staticUser.name,model.staticUser.mobileNumber,model.staticUser.emailId,"+
				"model.staticUser.address,model.staticUser.staticUserDesignation.designationType,model.staticUser.staticUserDesignation.description,"+
				"model.personalUserGroup.personalUserGroupId,model.personalUserGroup.groupName,model.personalUserGroup.description,"+
				"model.personalUserGroup.createdDate,model.personalUserGroup.staticLocalGroup.staticLocalGroupId,"+
				"model.personalUserGroup.staticLocalGroup.description,model.personalUserGroup.staticLocalGroup.groupType,"+
				"model.personalUserGroup.localGroupRegion.groupRegionScope,model.personalUserGroup.localGroupRegion.state,"+
				"model.personalUserGroup.localGroupRegion.district,model.personalUserGroup.localGroupRegion.constituency,"+
				"model.personalUserGroup.localGroupRegion.tehsil,model.personalUserGroup.localGroupRegion.township,"+
				"model.personalUserGroup.localGroupRegion.localBody,model.personalUserGroup.localGroupRegion.ward,"+
				"model.personalUserGroup.localGroupRegion.hamlet from StaticUserGroup model where model.personalUserGroup.staticGroup is null "+
				"and model.personalUserGroup.myGroup is null and model.personalUserGroup.staticLocalGroup is not null and "+
				"model.personalUserGroup.staticLocalGroup.staticLocalGroupId = ? and model.personalUserGroup.createdUserId.registrationId = ?",params);
				
	}
	
	@SuppressWarnings("unchecked")
	public List getCompleteUserGroupMemberDetailsForAUserOfSpecificDesignation(Long designationId, Long userId){
		Object[] params = {designationId,userId};
		return getHibernateTemplate().find("select model.staticUser.staticUserId,model.staticUser.name,model.staticUser.mobileNumber,model.staticUser.emailId,"+
				"model.staticUser.address,model.staticUser.staticUserDesignation.designationType,model.staticUser.staticUserDesignation.description,"+
				"model.personalUserGroup.personalUserGroupId,model.personalUserGroup.groupName,model.personalUserGroup.description,"+
				"model.personalUserGroup.createdDate,model.personalUserGroup.staticLocalGroup.staticLocalGroupId,"+
				"model.personalUserGroup.staticLocalGroup.description,model.personalUserGroup.staticLocalGroup.groupType,"+
				"model.personalUserGroup.localGroupRegion.groupRegionScope,model.personalUserGroup.localGroupRegion.state,"+
				"model.personalUserGroup.localGroupRegion.district,model.personalUserGroup.localGroupRegion.constituency,"+
				"model.personalUserGroup.localGroupRegion.tehsil,model.personalUserGroup.localGroupRegion.township,"+
				"model.personalUserGroup.localGroupRegion.localBody,model.personalUserGroup.localGroupRegion.ward,"+
				"model.personalUserGroup.localGroupRegion.hamlet from StaticUserGroup model where model.personalUserGroup.staticGroup is null "+
				"and model.personalUserGroup.myGroup is null and model.personalUserGroup.staticLocalGroup is not null and "+
				"model.staticUser.staticUserDesignation.staticUserDesignationId = ? and model.personalUserGroup.createdUserId.registrationId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getCompleteUserGroupMemberDetailsForAUserOfSpecificDesignation(Long designationId, Long userId, Long groupCategoryId){
		Object[] params = {designationId,userId,groupCategoryId};
		return getHibernateTemplate().find("select model.staticUser.staticUserId,model.staticUser.name,model.staticUser.mobileNumber,model.staticUser.emailId,"+
				"model.staticUser.address,model.staticUser.staticUserDesignation.designationType,model.staticUser.staticUserDesignation.description,"+
				"model.personalUserGroup.personalUserGroupId,model.personalUserGroup.groupName,model.personalUserGroup.description,"+
				"model.personalUserGroup.createdDate,model.personalUserGroup.staticLocalGroup.staticLocalGroupId,"+
				"model.personalUserGroup.staticLocalGroup.description,model.personalUserGroup.staticLocalGroup.groupType,"+
				"model.personalUserGroup.localGroupRegion.groupRegionScope,model.personalUserGroup.localGroupRegion.state,"+
				"model.personalUserGroup.localGroupRegion.district,model.personalUserGroup.localGroupRegion.constituency,"+
				"model.personalUserGroup.localGroupRegion.tehsil,model.personalUserGroup.localGroupRegion.township,"+
				"model.personalUserGroup.localGroupRegion.localBody,model.personalUserGroup.localGroupRegion.ward,"+
				"model.personalUserGroup.localGroupRegion.hamlet from StaticUserGroup model where model.personalUserGroup.staticGroup is null "+
				"and model.personalUserGroup.myGroup is null and model.personalUserGroup.staticLocalGroup is not null and "+
				"model.staticUser.staticUserDesignation.staticUserDesignationId = ? and model.personalUserGroup.createdUserId.registrationId = ? "+
				"and model.personalUserGroup.staticLocalGroup.staticLocalGroupId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getMemberDetailsOfLocalUserGroup(Long localGroupId) {
		
		return getHibernateTemplate().find("select model.staticUser.staticUserId,model.staticUser.name,model.staticUser.mobileNumber,"+
				"model.staticUser.emailId,model.staticUser.address,model.staticUser.location,model.staticUser.staticUserDesignation "+
				"from StaticUserGroup model where model.personalUserGroup.personalUserGroupId = ?",localGroupId);
	}

}
