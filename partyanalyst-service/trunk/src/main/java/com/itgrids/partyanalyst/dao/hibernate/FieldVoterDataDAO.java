package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IFieldVoterDataDAO;
import com.itgrids.partyanalyst.model.FieldVoterData;

public class FieldVoterDataDAO extends GenericDaoHibernate<FieldVoterData, Long> implements IFieldVoterDataDAO{

	public FieldVoterDataDAO() {
		super(FieldVoterData.class);
		
	}

}
