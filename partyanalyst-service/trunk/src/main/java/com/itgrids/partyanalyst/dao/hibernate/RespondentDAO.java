package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IRespondentDAO;
import com.itgrids.partyanalyst.model.Respondent;

public class RespondentDAO extends GenericDaoHibernate<Respondent, Long> implements IRespondentDAO{

	public RespondentDAO() {
		super(Respondent.class);
		// TODO Auto-generated constructor stub
	}

}
