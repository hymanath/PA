package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreLanguageEfficiencyDAO;
import com.itgrids.partyanalyst.model.CadreLanguageEfficiency;

public class CadreLanguageEfficiencyDAO extends GenericDaoHibernate<CadreLanguageEfficiency, Long> implements ICadreLanguageEfficiencyDAO {

	public CadreLanguageEfficiencyDAO() {
		super(CadreLanguageEfficiency.class);
		 
	}

}
