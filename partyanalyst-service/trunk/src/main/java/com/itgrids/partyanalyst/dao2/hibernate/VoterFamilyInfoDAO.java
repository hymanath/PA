package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterFamilyInfoDAO;
import com.itgrids.partyanalyst.model.VoterFamilyInfo;

public class VoterFamilyInfoDAO extends GenericDaoHibernate<VoterFamilyInfo, Long> implements IVoterFamilyInfoDAO{

	public VoterFamilyInfoDAO()
	{
		super(VoterFamilyInfo.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<VoterFamilyInfo> getVoterFamilyDetails(Long reportLevelId, Long reportLevelValue, Long publicationDateId,Long constituencyId)
	{
		Query queryObj = getSession().createQuery("select model from VoterFamilyInfo model where model.voterReportLevel.voterReportLevelId = :reportLevelId " +
				" and model.reportLevelValue = :reportLevelValue and model.publicationDate.publicationDateId = :publicationDateId and model.constituencyId = :constituencyId ");
		
		queryObj.setParameter("reportLevelId", reportLevelId);
		queryObj.setParameter("reportLevelValue", reportLevelValue);
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("constituencyId", constituencyId);
		return queryObj.list();
	}
	
	public Integer deleteVoterFamilyDetByReportLevelValAndVoterAgeRange(Long reportLevelId, List<Long> reportLevelValue, Long publicationDateId)
	{
		Query query = getSession().createQuery("delete from VoterFamilyInfo model where model.voterReportLevel.voterReportLevelId = :reportLevelId " +
				" and model.reportLevelValue in (:reportLevelValue) and model.publicationDate.publicationDateId = :publicationDateId ");
		
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameterList("reportLevelValue", reportLevelValue);
		query.setParameter("publicationDateId", publicationDateId);
		
		return  query.executeUpdate();
	}
	
	
	public List getTotalFamiliesCountByReportLevelValue(Long reportLevelId, Long reportLevelValue, Long publicationDateId)
	{
		Query query = getSession().createQuery("select sum(model.totalFamilies) from VoterFamilyInfo model where model.voterReportLevel.voterReportLevelId =:reportLevelId " +
				" and model.reportLevelValue = :reportLevelValue and model.publicationDate.publicationDateId =:publicationDateId ");
		
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameter("reportLevelValue", reportLevelValue);
		query.setParameter("publicationDateId", publicationDateId);
		return  query.list();
	}
	public List<VoterFamilyInfo> getMultipleVoterFamilyDetails(Long reportLevelId, Set<Long> reportLevelValues, Long publicationDateId,Long constituencyId)
	{
		Query queryObj = getSession().createQuery("select model from VoterFamilyInfo model where model.voterReportLevel.voterReportLevelId = :reportLevelId " +
				" and model.reportLevelValue in (:reportLevelValues) and model.publicationDate.publicationDateId = :publicationDateId  and model.constituencyId = :constituencyId ");
		
		queryObj.setParameter("reportLevelId", reportLevelId);
		queryObj.setParameterList("reportLevelValues", reportLevelValues);
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("constituencyId", constituencyId);
		return queryObj.list();
	}
	
	public Integer deleteVoterFamilyDetByConstituencyIdAndVoterAgeRange(Long reportLevelId,Long constituencyId, Long publicationDateId)
	{
		Query query = getSession().createQuery("delete from VoterFamilyInfo model where model.voterReportLevel.voterReportLevelId = :reportLevelId " +
				" and model.constituencyId =:constituencyId and model.publicationDate.publicationDateId = :publicationDateId ");
		
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		
		return  query.executeUpdate();
	}
	
	
	public Integer deleteVoterFamilyDetByConstituencyId(Long constituencyId, Long publicationDateId)
	{
		Query query = getSession().createQuery("delete from VoterFamilyInfo model where model.constituencyId =:constituencyId and model.publicationDate.publicationDateId = :publicationDateId ");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		
		return  query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getVoterReportLevelValueByReportLevelId(Long constituencyId, Long publicationDateId,Long voterReportLevel)
	{
		Query query = getSession().createQuery("select distinct model.reportLevelValue from VoterFamilyInfo model where model.constituencyId =:constituencyId and " +
				" model.publicationDate.publicationDateId = :publicationDateId and model.voterReportLevel.voterReportLevelId =:voterReportLevelId ");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("voterReportLevelId", voterReportLevel);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterReportLevelValueByConstituencyId(Long constituencyId, Long publicationDateId,Long voterReportLevelId)
	{
		Query query = getSession().createQuery("select model.reportLevelValue,count(model.voterFamilyRange.voterFamilyRangeId) from VoterFamilyInfo model where model.constituencyId =:constituencyId and " +
				" model.publicationDate.publicationDateId = :publicationDateId and model.voterReportLevel.voterReportLevelId =:voterReportLevelId group by model.reportLevelValue ");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("voterReportLevelId", voterReportLevelId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<VoterFamilyInfo> getVoterFamilyInfoList(Long constituencyId)
	{
		Query query = getSession().createQuery(" from VoterFamilyInfo model where model.constituencyId =:constituencyId ");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public Long getTotalFamiliesCount(Long constituencyId,Long publicationDateId,Long voterReportLevelId,Long reportLevelValue){
		Query query = getSession().createQuery("select sum(model.totalFamilies) from VoterFamilyInfo model where model.constituencyId =:constituencyId and model.publicationDate.publicationDateId =:publicationDateId " +
				" and model.voterReportLevel.voterReportLevelId =:voterReportLevelId and model.reportLevelValue =:reportLevelValue and model.totalFamilies is not null");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("voterReportLevelId", voterReportLevelId);
		query.setParameter("reportLevelValue", reportLevelValue);
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getTotalFamiliesByCosntituency(Long constituencyId,Long publicationDateId,Long reportLevelValue){
		Query query = getSession().createQuery("select  model.voterFamilyRange.voterFamilyRangeId,model.voterFamilyRange.familyRange," +
				" model.totalFamilies, model.familiesPercentage from VoterFamilyInfo model where model.constituencyId =:constituencyId and model.publicationDate.publicationDateId =:publicationDateId " +
				" and model.voterReportLevel.voterReportLevelId =1 and model.reportLevelValue =:reportLevelValue and model.totalFamilies is not null order by model.voterFamilyRange.voterFamilyRangeId asc ");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("reportLevelValue", reportLevelValue);
		return query.list();
	}
	
	public List<Object[]> getTotalFamilies(Long constituencyId,Long publicationDateId,Long reportLevelValue,Long reportLvlId){
		Query query = getSession().createQuery("select  model.voterFamilyRange.voterFamilyRangeId,model.voterFamilyRange.familyRange," +
				" model.totalFamilies, model.familiesPercentage from VoterFamilyInfo model where model.constituencyId =:constituencyId and model.publicationDate.publicationDateId =:publicationDateId " +
				" and model.voterReportLevel.voterReportLevelId =:reportLvlId and model.reportLevelValue =:reportLevelValue and model.totalFamilies is not null order by model.voterFamilyRange.voterFamilyRangeId asc ");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("reportLevelValue", reportLevelValue);
		query.setParameter("reportLvlId", reportLvlId);
		return query.list();
	}
	
}
