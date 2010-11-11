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


}
