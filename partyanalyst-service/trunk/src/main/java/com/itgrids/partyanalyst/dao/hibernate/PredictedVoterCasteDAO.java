package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPredictedVoterCasteDAO;
import com.itgrids.partyanalyst.model.PredictedVoterCaste;

public class PredictedVoterCasteDAO extends GenericDaoHibernate<PredictedVoterCaste, Long> implements IPredictedVoterCasteDAO{

	public PredictedVoterCasteDAO() {
		super(PredictedVoterCaste.class);
		
	}
	

}
