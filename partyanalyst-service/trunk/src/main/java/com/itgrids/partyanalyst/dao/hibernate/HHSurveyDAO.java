package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IHHSurveyDAO;
import com.itgrids.partyanalyst.model.HHSurvey;


public class HHSurveyDAO extends GenericDaoHibernate<HHSurvey,Long> implements IHHSurveyDAO {
	
	public HHSurveyDAO() {
		super(HHSurvey.class);
	}
	
	
}
