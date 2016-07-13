package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;


import com.itgrids.partyanalyst.dao.IPositionDAO;
import com.itgrids.partyanalyst.model.Position;

public class PositionDAO extends GenericDaoHibernate<Position, Long> implements IPositionDAO{

	public PositionDAO() {
		super(Position.class);
		
	}

}
