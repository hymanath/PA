package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyConstituencyDAO;
import com.itgrids.partyanalyst.model.SurveyConstituency;
import com.itgrids.partyanalyst.utils.IConstants;

public class SurveyConstituencyDAO extends GenericDaoHibernate<SurveyConstituency, Long> implements ISurveyConstituencyDAO{

	public SurveyConstituencyDAO() {
		super(SurveyConstituency.class);
		
	}
	
	public List<Object[]> getSurveyConstituencies()
	{
		return getHibernateTemplate().find("select distinct model.constituency.constituencyId,model.constituency.name,model.constituency.district.districtId from SurveyConstituency model");
	}
	
	public List<Object[]> getDistrictWiseSurveyConstituenciesCount()
	{
		Query query = getSession().createQuery("select count(SC.constituency.constituencyId),SC.constituency.district.districtId " +
				"from SurveyConstituency SC group by SC.constituency.district.districtId");
		
		return query.list();
		
	}
	
	public List<Object[]> getTotalVotersDetailsForSurveyConstituencies(List<Long> constituencyIds)
	{
		Query query = getSession().createQuery("select count(BPV.voter.voterId),BPV.booth.constituency.constituencyId" +
				"  from " +
				"BoothPublicationVoter BPV , SurveyConstituency SC " +
				"where " +
				"BPV.booth.constituency.constituencyId = SC.constituency.constituencyId and " +
				"BPV.booth.publicationDate.publicationDateId = :publicationDateId and " +
				"SC.constituency.constituencyId in(:constituencyIds) group by " +
				"BPV.booth.constituency.constituencyId");
		
		query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID);
		query.setParameterList("constituencyIds", constituencyIds);
		
		return query.list();
		
	}
	
	public List<Object[]> getTotalBoothsCountForSurveyConstituencies(List<Long> constituencyIds)
	{
		Query query = getSession().createQuery("select count(B.boothId), " +
				"SC.constituency.constituencyId,SC.constituency.name " +
				" from Booth B ,SurveyConstituency SC " +
				"where " +
				"B.constituency.constituencyId = SC.constituency.constituencyId and " +
				"B.publicationDate.publicationDateId = :publicationDateId and " +
				"SC.constituency.constituencyId in(:constituencyIds) " +
				"group by SC.constituency.constituencyId  ");
		
		query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID);
		query.setParameterList("constituencyIds", constituencyIds);
		
		return query.list();
		
	}

}
