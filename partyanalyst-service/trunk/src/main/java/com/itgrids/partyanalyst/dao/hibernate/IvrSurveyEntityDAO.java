package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IIvrOptionsDAO;
import com.itgrids.partyanalyst.dao.IIvrSurveyEntityDAO;
import com.itgrids.partyanalyst.model.IvrOptions;
import com.itgrids.partyanalyst.model.IvrSurveyEntity;


public class IvrSurveyEntityDAO extends GenericDaoHibernate<IvrSurveyEntity, Long> implements IIvrSurveyEntityDAO{

	public IvrSurveyEntityDAO() {
		super(IvrSurveyEntity.class);
	}

}
