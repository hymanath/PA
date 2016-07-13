package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDepartmentBoardPositionDAO;
import com.itgrids.partyanalyst.model.DepartmentBoardPosition;

public class DepartmentBoardPositionDAO extends GenericDaoHibernate<DepartmentBoardPosition, Long> implements IDepartmentBoardPositionDAO{

	public DepartmentBoardPositionDAO() {
		super(DepartmentBoardPosition.class);
		
	}

}
