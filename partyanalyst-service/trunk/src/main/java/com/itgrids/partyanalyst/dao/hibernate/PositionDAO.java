package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;


import com.itgrids.partyanalyst.dao.IPositionDAO;
import com.itgrids.partyanalyst.model.Position;

public class PositionDAO extends GenericDaoHibernate<Position, Long> implements IPositionDAO{

	public PositionDAO() {
		super(Position.class);
		
	}

	public List<Object[]> getAllPositions(){
		Query query = getSession().createQuery("select model.positionId,model.positionName from Position model order by model.orderNo asc ");
		return query.list();
	}
}
