package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IKaizalaGroupsDAO;
import com.itgrids.partyanalyst.model.KaizalaGroups;

public class KaizalaGroupsDAO extends GenericDaoHibernate<KaizalaGroups, Long> implements IKaizalaGroupsDAO{
	
	public KaizalaGroupsDAO() {
		super(KaizalaGroups.class);
	}
	
	public List<Long> checkGroupExistence(String groupId){
		Query query = getSession().createQuery(" select model.kaizalaGroupsId "
				+ " from KaizalaGroups model "
				+ " where model.groupId=:groupId and model.isDeleted='N' ");
		query.setParameter("groupId", groupId);
		return query.list();
	}

	public Integer removeParentGroup(Long kaizalaGroupId){
		Query query = getSession().createQuery(" update KaizalaGroups model set model.parentKaizalaGroupsId = :value " +
				" where model.kaizalaGroupsId = :kaizalaGroupId ");
		query.setParameter("value", null);
		query.setParameter("kaizalaGroupId", kaizalaGroupId);
		return query.executeUpdate();
	}
}
