package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDepartmentBoardDAO;
import com.itgrids.partyanalyst.model.DepartmentBoard;

public class DepartmentBoardDAO extends GenericDaoHibernate<DepartmentBoard, Long> implements IDepartmentBoardDAO{

	public DepartmentBoardDAO() {
		super(DepartmentBoard.class);
		
	}

}
