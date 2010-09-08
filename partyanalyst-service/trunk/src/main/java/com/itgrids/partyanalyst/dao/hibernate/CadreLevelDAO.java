package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreLevelDAO;
import com.itgrids.partyanalyst.model.CadreLevel;


public class CadreLevelDAO extends GenericDaoHibernate<CadreLevel , Long> implements ICadreLevelDAO  {

	public CadreLevelDAO() {
		super(CadreLevel.class);		 
	}

}
