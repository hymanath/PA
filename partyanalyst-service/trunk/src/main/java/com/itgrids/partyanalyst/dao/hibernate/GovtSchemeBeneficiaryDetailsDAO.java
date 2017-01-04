package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtSchemeBeneficiaryDetailsDAO;
import com.itgrids.partyanalyst.dao.IGovtSchemeBenefitTypeDAO;
import com.itgrids.partyanalyst.model.GovtSchemeBeneficiaryDetails;
import com.itgrids.partyanalyst.model.Schemephase;

public class GovtSchemeBeneficiaryDetailsDAO extends GenericDaoHibernate<GovtSchemeBeneficiaryDetails, Long> implements IGovtSchemeBeneficiaryDetailsDAO{

	public GovtSchemeBeneficiaryDetailsDAO() {
		super(GovtSchemeBeneficiaryDetails.class);
		
	}

}
