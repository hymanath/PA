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
	
	public List<Object[]> getTotalVoters(Long type)
	{
		StringBuffer queryString = new StringBuffer();
		queryString.append("select sum(model.totalVoters) ,sum(model.totalBooths) , count(model.constituency.constituencyId)  from SurveyConstituencyTemp model where ");
		if(type != null)
		{
			if(type.longValue() == 1l)
			{
				queryString.append("  model.constituency.district.districtId > 10 and ");
			}
			if(type.longValue() == 2l)
			{
				queryString.append("  model.constituency.district.districtId <= 10  and ");
			}
		}
		queryString.append(" model.surveyType = 'CTP' ");

		Query query = getSession().createQuery(queryString.toString());
		return query.list();
	}

	
	
	public List<Object[]> getTotalVotersAndBooths(List<Long> constituencyIds)
	{
		Query query = getSession().createQuery("select model.constituency.constituencyId,model.totalVoters,model.totalBooths,model.constituency.name from SurveyConstituencyTemp model where model.constituency.constituencyId in(:constituencyIds)" +
				" group by model.constituency.constituencyId");
		query.setParameterList("constituencyIds", constituencyIds);
		return query.list();
	}
	
	public List<Object[]> getTotalVotersForConstituencies()
	{
		Query query = getSession().createQuery("select SCT.totalVoters,SCT.constituency.constituencyId from SurveyConstituencyTemp SCT ");
		
		return query.list();
		
	}
	
	public List<Object[]> getTotalVotersAndBoothsByConstituencyes(List<Long> constituencyIds)
	{
		Query query = getSession().createQuery("select model.constituency.constituencyId,model.constituency.name ,model.totalVoters,model.totalBooths,model.boothsTodo from SurveyConstituencyTemp model where model.constituency.constituencyId in(:constituencyIds)" +
				" group by model.constituency.constituencyId");
		query.setParameterList("constituencyIds", constituencyIds);
		return query.list();
	}
	
}
