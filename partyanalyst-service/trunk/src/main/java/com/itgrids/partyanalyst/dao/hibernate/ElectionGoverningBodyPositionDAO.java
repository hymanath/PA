package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IElectionGoverningBodyPositionDAO;
import com.itgrids.partyanalyst.model.ElectionGoverningBodyPosition;

public class ElectionGoverningBodyPositionDAO extends GenericDaoHibernate<ElectionGoverningBodyPosition, Long> 
					implements IElectionGoverningBodyPositionDAO{

	public ElectionGoverningBodyPositionDAO(){
		super(ElectionGoverningBodyPosition.class);
	}

	@SuppressWarnings("unchecked")
	public List<ElectionGoverningBodyPosition> findByPosition(String position) {
		return getHibernateTemplate().find("from ElectionGoverningBodyPosition model where model.governingBodyPosition = ?", position);
	}
	
	public List<Object[]> getAllPositions() {
		return getHibernateTemplate().find("select model.governingBodyPositionId ,model.governingBodyPosition from ElectionGoverningBodyPosition model");
	}
	public List<ElectionGoverningBodyPosition> getPositionsByValue(String position) {
		Object[] data = {position};
		return getHibernateTemplate().find("select model from ElectionGoverningBodyPosition model where model.governingBodyPosition = ?  ",data);
	}
}
