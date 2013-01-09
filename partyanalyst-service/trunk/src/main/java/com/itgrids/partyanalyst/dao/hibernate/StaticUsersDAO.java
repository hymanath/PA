package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IStaticUsersDAO;
import com.itgrids.partyanalyst.model.StaticUsers;

public class StaticUsersDAO extends GenericDaoHibernate<StaticUsers, Long> implements
		IStaticUsersDAO {

	public StaticUsersDAO()
	{
		super(StaticUsers.class);
	}
	@SuppressWarnings("unchecked")
	public List<StaticUsers> findByMobileNo(String mobileNumber) {
		
		return getHibernateTemplate().find("from StaticUsers model where model.mobileNumber= ?",mobileNumber);
	}
	@SuppressWarnings("unchecked")
	public List findGroupMembersMobileNosByMemberIds(List<Long> memberIds) {
		Query queryObject = getSession().createQuery("select model.mobileNumber from StaticUsers model where model.staticUserId in (:memberIds)");
		queryObject.setParameterList("memberIds", memberIds);
		return queryObject.list();
	}
	@SuppressWarnings("unchecked")
	public List findGroupMembersMobileNosByMemberIds(String memberIds) {
		return getHibernateTemplate().find("select model.mobileNumber from StaticUsers model where model.staticUserId in ("+memberIds+")");
	}
	@SuppressWarnings("unchecked")
	public List findGroupMembersNameAndMobileNosByMemberIds(List<Long> memberIds) {
		Query queryObject = getSession().createQuery("select model.name,model.mobileNumber from StaticUsers model where model.staticUserId in (:memberIds)");
		queryObject.setParameterList("memberIds", memberIds);
		return queryObject.list();
	}
	@SuppressWarnings("unchecked")
	public List findGroupMembersNameAndMobileNosByMemberIds(String memberIds) {
		return getHibernateTemplate().find("select model.name,model.mobileNumber from StaticUsers model where model.staticUserId in ("+memberIds+")");
	}
	
	public Integer deleteStaticUsersByStaticUserIds(List<Long> staticUserIds) {
		
		Query queryObject = getSession().createQuery("delete from StaticUsers model where model.staticUserId in (:staticUserIds)");
		queryObject.setParameterList("staticUserIds", staticUserIds);
		return queryObject.executeUpdate();
	}

	public List<Long> checkForUserGroupMembers(Long id)
	{
		return getHibernateTemplate().find("select count(*) from StaticUsers model where model.staticUserId = ?",id);
		
	}
	
	public Integer deleteUserMembers(Long id){
		
		Query query = getSession().createQuery("delete from StaticUserGroup model where model.staticUser.staticUserId =:staticUserIds");
		query.setParameter("staticUserIds", id);
		query.executeUpdate();
		Query queryObject = getSession().createQuery("delete from StaticUsers model where model.staticUserId = :id");
		queryObject.setParameter("id", id);
		return queryObject.executeUpdate();
		
		
		
	}

}
