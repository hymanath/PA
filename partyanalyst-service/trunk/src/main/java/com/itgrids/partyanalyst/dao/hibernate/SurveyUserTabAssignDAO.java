package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISurveyUserTabAssignDAO;
import com.itgrids.partyanalyst.model.SurveyUserTabAssign;

public class SurveyUserTabAssignDAO extends GenericDaoHibernate<SurveyUserTabAssign, Long>  implements ISurveyUserTabAssignDAO{

	public SurveyUserTabAssignDAO()
	{
		super(SurveyUserTabAssign.class);
	}

}
