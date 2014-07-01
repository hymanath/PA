package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISurveyUserBoothAssignDAO;
import com.itgrids.partyanalyst.model.SurveyUserBoothAssign;

public class SurveyUserBoothAssignDAO extends GenericDaoHibernate<SurveyUserBoothAssign, Long>  implements ISurveyUserBoothAssignDAO{

	public SurveyUserBoothAssignDAO()
	{
		super(SurveyUserBoothAssign.class);
	}

}
