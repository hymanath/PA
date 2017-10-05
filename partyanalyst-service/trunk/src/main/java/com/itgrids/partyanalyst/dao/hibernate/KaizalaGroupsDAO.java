package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.partyanalyst.dao.IKaizalaGroupsDAO;
import com.itgrids.partyanalyst.model.KaizalaGroups;

public class KaizalaGroupsDAO extends GenericDaoHibernate<KaizalaGroups, Long> implements IKaizalaGroupsDAO{
	
	public KaizalaGroupsDAO() {
		super(KaizalaGroups.class);
	}
	
	public Long checkGroupExistence(String groupId){
		Query query = getSession().createQuery(" select model.kaizalaGroupsId "
				+ " from KaizalaGroups model "
				+ " where model.groupId=:groupId ");
		query.setParameter("groupId", groupId);
		return (Long) query.uniqueResult();
	}

	public Integer removeParentGroup(Long kaizalaGroupId){
		Query query = getSession().createQuery(" update KaizalaGroups model set model.parentKaizalaGroupsId = :value " +
				" where model.kaizalaGroupsId = :kaizalaGroupId ");
		query.setParameter("value", null);
		return query.executeUpdate();
	}
}
