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
	

}
