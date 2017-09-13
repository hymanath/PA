package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtSchemeBenefitsInfoDAO;
import com.itgrids.partyanalyst.model.GovtSchemeBenefitsInfo;

public class GovtSchemeBenefitsInfoDAO  extends GenericDaoHibernate<GovtSchemeBenefitsInfo, Long> implements IGovtSchemeBenefitsInfoDAO{

	public GovtSchemeBenefitsInfoDAO() {
		super(GovtSchemeBenefitsInfo.class);
		
	}

	
}
