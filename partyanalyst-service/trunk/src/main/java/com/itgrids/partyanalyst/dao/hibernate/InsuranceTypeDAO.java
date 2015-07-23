package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IInsuranceTypeDAO;
import com.itgrids.partyanalyst.model.InsuranceType;

public class InsuranceTypeDAO extends GenericDaoHibernate<InsuranceType, Long> implements IInsuranceTypeDAO{

	public InsuranceTypeDAO() {
		super(InsuranceType.class);
		
	}

}
