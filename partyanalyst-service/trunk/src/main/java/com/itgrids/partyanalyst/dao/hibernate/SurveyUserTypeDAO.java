package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISurveyUserTypeDAO;
import com.itgrids.partyanalyst.model.SurveyUserType;

public class SurveyUserTypeDAO extends GenericDaoHibernate<SurveyUserType, Long>  implements ISurveyUserTypeDAO{

	public SurveyUserTypeDAO() 
	{
		super(SurveyUserType.class);
	}

}
