package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IIvrOptionsDAO;
import com.itgrids.partyanalyst.dao.IIvrSurveyScopeDAO;
import com.itgrids.partyanalyst.model.IvrOptions;
import com.itgrids.partyanalyst.model.IvrSurveyScope;


public class IvrSurveyScopeDAO extends GenericDaoHibernate<IvrSurveyScope, Long> implements IIvrSurveyScopeDAO{

	public IvrSurveyScopeDAO() {
		super(IvrSurveyScope.class);
	}

}
