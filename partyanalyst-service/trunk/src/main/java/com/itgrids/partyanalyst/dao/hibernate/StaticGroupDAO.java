package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IStaticGroupDAO;
import com.itgrids.partyanalyst.model.StaticGroup;

public class StaticGroupDAO extends GenericDaoHibernate<StaticGroup, Long> implements
		IStaticGroupDAO {

	public StaticGroupDAO() {
		super(StaticGroup.class);
	}

	@SuppressWarnings("unchecked")
	public List<StaticGroup> findByGroupDescription(String groupDescription) {
		List<StaticGroup>staticGroup= getHibernateTemplate().find("from  StaticGroup model where model.groupDescription = ?",groupDescription);
	return staticGroup;
	}

}
