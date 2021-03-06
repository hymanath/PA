package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.model.AllianceGroup;

public class AllianceGroupDAO extends GenericDaoHibernate<AllianceGroup, Long> implements IAllianceGroupDAO {

	public AllianceGroupDAO() {
		super(AllianceGroup.class);
	}

	@SuppressWarnings("unchecked")
	public List<AllianceGroup> findByGroupId(final Long groupId) {
		return getHibernateTemplate().find("from AllianceGroup as model where model.group.groupId=?",groupId);
	}

}
