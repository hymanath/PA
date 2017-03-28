package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICasteInsertTypeDAO;
import com.itgrids.partyanalyst.model.CasteInsertType;

public class CasteInsertTypeDAO extends GenericDaoHibernate<CasteInsertType, Long> implements ICasteInsertTypeDAO{

	public CasteInsertTypeDAO() {
		super(CasteInsertType.class);
		
	}

}
