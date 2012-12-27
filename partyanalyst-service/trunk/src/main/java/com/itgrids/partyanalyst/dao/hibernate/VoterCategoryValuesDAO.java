package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IVoterCategoryValuesDAO;
import com.itgrids.partyanalyst.model.VoterCategoryValues;

public class VoterCategoryValuesDAO extends GenericDaoHibernate<VoterCategoryValues, Long> implements IVoterCategoryValuesDAO{

	public VoterCategoryValuesDAO() {
		super(VoterCategoryValues.class);
		// TODO Auto-generated constructor stub
	}

}
