package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IStaticGroupDAO;
import com.itgrids.partyanalyst.model.StaticGroup;

public class StaticGroupDAO extends GenericDaoHibernate<StaticGroup, Long> implements
		IStaticGroupDAO {

	public StaticGroupDAO() {
		super(StaticGroup.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<StaticGroup> findByGroupDescription(String groupDescription) {
		List<StaticGroup>staticGroup= getHibernateTemplate().find("from  StaticGroup model where model.groupDescription = ?",groupDescription);
	return staticGroup;
	}

	@Override
	public boolean exists(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public StaticGroup get(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StaticGroup> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Long arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public StaticGroup save(StaticGroup arg0) {
		// TODO Auto-generated method stub
		return null;
	}



}
