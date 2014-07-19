package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISurveyConstituencyDAO;
import com.itgrids.partyanalyst.model.SurveyConstituency;

public class SurveyConstituencyDAO extends GenericDaoHibernate<SurveyConstituency, Long> implements ISurveyConstituencyDAO{

	public SurveyConstituencyDAO() {
		super(SurveyConstituency.class);
		
	}
	
	public List<Object[]> getSurveyConstituencies()
	{
		return getHibernateTemplate().find("select distinct model.constituency.constituencyId,model.constituency.name,model.constituency.district.districtId from SurveyConstituency model");
	}

}
