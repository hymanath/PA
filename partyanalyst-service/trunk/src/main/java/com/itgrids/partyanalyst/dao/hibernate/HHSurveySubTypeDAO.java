package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IHHSurveySubTypeDAO;
import com.itgrids.partyanalyst.model.HHSurveySubType;

public class HHSurveySubTypeDAO extends
		GenericDaoHibernate<HHSurveySubType, Long> implements
		IHHSurveySubTypeDAO {
	
	public HHSurveySubTypeDAO() {
		super(HHSurveySubType.class);
	}
	
	public List<Object[]> getSubSurveyTypesByMainTypeId(Long mainTypeId)
	{
		Query query = getSession().createQuery("select model.HHSurveySubTypeId , model.name from " +
				"HHSurveySubType model where model.parentId =:mainTypeId");
		
		query.setParameter("mainTypeId", mainTypeId);
		
		return query.list();
		
	}
	
	public List<Object[]> getMainSurveyTypes()
	{
		Query query = getSession().createQuery("select model.HHSurveySubTypeId , model.name from " +
				"HHSurveySubType model where model.parentId is null");
		
		return query.list();
		
	}
	
	public List<Object[]> getSubSurveyTypes()
	{
		Query query = getSession().createQuery("select model.HHSurveySubTypeId , model.name from " +
				"HHSurveySubType model where model.parentId is not null");
		
		return query.list();
		
	}
	
}
