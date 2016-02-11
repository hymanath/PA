package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IIvrOptionsDAO;
import com.itgrids.partyanalyst.dao.IIvrSurveyEntityTypeDAO;
import com.itgrids.partyanalyst.model.IvrOptions;
import com.itgrids.partyanalyst.model.IvrSurveyEntityType;


public class IvrSurveyEntityTypeDAO extends GenericDaoHibernate<IvrSurveyEntityType, Long> implements IIvrSurveyEntityTypeDAO{

	public IvrSurveyEntityTypeDAO() {
		super(IvrSurveyEntityType.class);
	}

}
