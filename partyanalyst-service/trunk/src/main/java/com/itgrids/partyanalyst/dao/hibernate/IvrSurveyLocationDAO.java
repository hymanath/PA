package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IIvrOptionsDAO;
import com.itgrids.partyanalyst.dao.IIvrSurveyLocationDAO;
import com.itgrids.partyanalyst.model.IvrOptions;
import com.itgrids.partyanalyst.model.IvrSurveyLocation;


public class IvrSurveyLocationDAO extends GenericDaoHibernate<IvrSurveyLocation, Long> implements IIvrSurveyLocationDAO{

	public IvrSurveyLocationDAO() {
		super(IvrSurveyLocation.class);
	}

}
