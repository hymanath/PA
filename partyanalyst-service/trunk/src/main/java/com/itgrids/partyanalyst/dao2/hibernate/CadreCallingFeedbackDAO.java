package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreCallingFeedbackDAO;
import com.itgrids.partyanalyst.model.CadreCallingFeedback;

public class CadreCallingFeedbackDAO extends GenericDaoHibernate<CadreCallingFeedback, Long> implements ICadreCallingFeedbackDAO {
	public CadreCallingFeedbackDAO() {
		super(CadreCallingFeedback.class);

	}

}
