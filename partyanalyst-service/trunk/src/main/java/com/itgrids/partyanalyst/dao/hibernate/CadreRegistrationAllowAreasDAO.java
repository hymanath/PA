package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.CadreRegistrationAllowAreas;

public class CadreRegistrationAllowAreasDAO extends GenericDaoHibernate<CadreRegistrationAllowAreas, Long>{

	public CadreRegistrationAllowAreasDAO(){
		super(CadreRegistrationAllowAreas.class);
	}


}
