package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICasteGroupDAO;
import com.itgrids.partyanalyst.model.CasteGroup;

public class CasteGroupDAO extends GenericDaoHibernate<CasteGroup, Long> implements ICasteGroupDAO{

	public CasteGroupDAO() {
		super(CasteGroup.class);
		// TODO Auto-generated constructor stub
	}

}
