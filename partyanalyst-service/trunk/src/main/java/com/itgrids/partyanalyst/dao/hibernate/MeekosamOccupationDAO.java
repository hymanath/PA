package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMeekosamOccupationDAO;
import com.itgrids.partyanalyst.model.MeekosamOccupation;

public class MeekosamOccupationDAO extends GenericDaoHibernate<MeekosamOccupation, Long> implements IMeekosamOccupationDAO {

	public MeekosamOccupationDAO(){
		super(MeekosamOccupation.class);
	}
	public List<Object[]> getMeekosamOccupationList(){
		Query query = getSession().createQuery(" select model.meekosamOccupationId, model.meekosamOccupation from MeekosamOccupation model where model.isActive = 'Y'");
		return query.list();
	}
	
}
