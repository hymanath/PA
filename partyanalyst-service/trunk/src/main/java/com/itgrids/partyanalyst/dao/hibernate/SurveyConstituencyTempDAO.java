package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyConstituencyTempDAO;
import com.itgrids.partyanalyst.model.SurveyConstituencyTemp;

public class SurveyConstituencyTempDAO extends GenericDaoHibernate<SurveyConstituencyTemp, Long> implements ISurveyConstituencyTempDAO{

	public SurveyConstituencyTempDAO() {
		super(SurveyConstituencyTemp.class);
		
	}

	
	
	public List<Object[]> getTotalVotersAndBooths(List<Long> constituencyIds)
	{
		Query query = getSession().createQuery("select model.constituency.constituencyId,model.totalVoters,model.totalBooths from SurveyConstituencyTemp model where model.constituency.constituencyId in(:constituencyIds)" +
				" group by model.constituency.constituencyId");
		query.setParameterList("constituencyIds", constituencyIds);
		return query.list();
	}
}
