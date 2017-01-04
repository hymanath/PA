package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtSchemeBenefitTypeDAO;
import com.itgrids.partyanalyst.model.GovtSchemeBenefitType;
import com.itgrids.partyanalyst.model.SchemeBenefitType;

public class GovtSchemeBenefitTypeDAO extends GenericDaoHibernate<GovtSchemeBenefitType, Long> implements IGovtSchemeBenefitTypeDAO {

	public GovtSchemeBenefitTypeDAO() {
		super(GovtSchemeBenefitType.class);

	}

}
