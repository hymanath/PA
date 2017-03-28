package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISchemeBenefitTypeDAO;
import com.itgrids.partyanalyst.model.LeaderOccasion;
import com.itgrids.partyanalyst.model.SchemeBenefitType;

public class SchemeBenefitTypeDAO extends GenericDaoHibernate<SchemeBenefitType, Long> implements ISchemeBenefitTypeDAO{

	public SchemeBenefitTypeDAO() {
		super(SchemeBenefitType.class);
		
	}

}
