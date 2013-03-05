package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.model.VoterAgeInfo;

public class VoterAgeInfoDAO extends GenericDaoHibernate<VoterAgeInfo, Long> implements IVoterAgeInfoDAO{

	public VoterAgeInfoDAO(){
		super(VoterAgeInfo.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<VoterAgeInfo> getVoterAgeInfoByPublicationDateAndReportLevelId(Long reportLevelId, Long reportLevelValue, Long publicationDateId,Long constituencyId)
	{
		Query query = getSession().createQuery(" select model from VoterAgeInfo model where model.voterReportLevel.voterReportLevelId = :reportLevelId " +
				" and model.reportLevelValue = :reportLevelValue and model.publicationDate.publicationDateId = :publicationDateId and model.constituencyId = :constituencyId  " +
				" order by model.voterAgeRange.voterAgeRangeId ");
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameter("reportLevelValue", reportLevelValue);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<VoterAgeInfo> getAgewiseVoterDetailsInSpecifiedRangeByAgeRangeId(Long reportLevelId, Long reportLevelValue, Long publicationDateId, Long ageRangeId)
	{
		Query query = getSession().createQuery(" select model from VoterAgeInfo model where model.voterReportLevel.voterReportLevelId = :reportLevelId " +
				" and model.reportLevelValue = :reportLevelValue and model.publicationDate.publicationDateId = :publicationDateId and model.voterAgeRange.voterAgeRangeId = :ageRangeId ");
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameter("reportLevelValue", reportLevelValue);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("ageRangeId", ageRangeId);
		return query.list();
	}
	
	public Long getVoterInfoIdByReportLevelValueAndReportLevelId(Long reportLevelId, Long reportLevelValue, Long ageRangeId)
	{
		Query query = getSession().createQuery("select model.voterAgeInfoId from VoterAgeInfo model where model.voterReportLevel.voterReportLevelId =:reportLevelId " +
				" and model.reportLevelValue =:reportLevelValue and model.voterAgeRange.voterAgeRangeId =:ageRangeId ");
		
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameter("reportLevelValue", reportLevelValue);
		query.setParameter("ageRangeId", ageRangeId);
		return (Long) query.uniqueResult();
	}
	
	public Integer deleteVoterAgeInfoByReportLevelIdAndReportLevelValue(Long reportLevelId, List<Long> reportLevelValue, Long publicationDateId)
	{
		Query query = getSession().createQuery(" delete from VoterAgeInfo model where model.voterReportLevel.voterReportLevelId =:reportLevelId " +
				" and model.reportLevelValue in (:reportLevelValue) and model.publicationDate.publicationDateId = :publicationDateId ");
		
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameterList("reportLevelValue", reportLevelValue);
		
		return  query.executeUpdate();
	}
	
	public List<VoterAgeInfo> getAgewiseVoterDetailsInAllRange(Long reportLevelId, Set<Long> reportLevelValues, Long publicationDateId,Long constituencyId)
	{
		Query query = getSession().createQuery(" select model from VoterAgeInfo model where model.voterReportLevel.voterReportLevelId = :reportLevelId " +
				" and model.reportLevelValue in(:reportLevelValues) and model.publicationDate.publicationDateId = :publicationDateId  and model.constituencyId = :constituencyId ");
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameterList("reportLevelValues", reportLevelValues);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public Integer deleteVoterAgeInfoByConstituencyIdAndReportLevelValue(Long reportLevelId,Long constituencyId, Long publicationDateId)
	{
		Query query = getSession().createQuery(" delete from VoterAgeInfo model where model.voterReportLevel.voterReportLevelId =:reportLevelId " +
				" and model.constituencyId=:constituencyId and model.publicationDate.publicationDateId = :publicationDateId ");
		
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("constituencyId", constituencyId);
		
		return  query.executeUpdate();
	}
	
}
