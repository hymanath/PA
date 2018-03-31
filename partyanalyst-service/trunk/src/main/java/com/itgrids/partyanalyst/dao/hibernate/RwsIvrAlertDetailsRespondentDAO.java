package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IRwsIvrAlertDetailsRespondentDAO;
import com.itgrids.partyanalyst.model.RwsIvrAlertDetailsRespondent;

public class RwsIvrAlertDetailsRespondentDAO extends GenericDaoHibernate<RwsIvrAlertDetailsRespondent, Long> implements
IRwsIvrAlertDetailsRespondentDAO
{

	public RwsIvrAlertDetailsRespondentDAO() {
		super(RwsIvrAlertDetailsRespondent.class);
	}

}
