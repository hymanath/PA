package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IRegionWiseSurveysDAO;
import com.itgrids.partyanalyst.model.RegionWiseSurveys;

public class RegionWiseSurveysDAO extends GenericDaoHibernate<RegionWiseSurveys,Long> implements IRegionWiseSurveysDAO{

	public RegionWiseSurveysDAO() {
		super(RegionWiseSurveys.class);
	}
	
	public List<Object[]> getSurveyDetailsByRegion(Long regionId)
	{
		Query query = getSession().createQuery("select RWS.surveyId,RWS.surveyName from RegionWiseSurveys RWS " +
				"where " +
				"RWS.regionId = :regionId ");
		
		query.setParameter("regionId", regionId);
		
		return query.list();
	}
}
