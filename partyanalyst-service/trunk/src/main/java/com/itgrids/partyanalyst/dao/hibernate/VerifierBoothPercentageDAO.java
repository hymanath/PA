package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVerifierBoothPercentageDAO;
import com.itgrids.partyanalyst.model.VerifierBoothPercentage;

public class VerifierBoothPercentageDAO extends GenericDaoHibernate<VerifierBoothPercentage, Long> implements IVerifierBoothPercentageDAO {
	
	public VerifierBoothPercentageDAO() {
		super(VerifierBoothPercentage.class);
	}
	
}
