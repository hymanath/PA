package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterModificationInfoDAO;
import com.itgrids.partyanalyst.model.VoterModificationInfo;

public class VoterModificationInfoDAO extends GenericDaoHibernate<VoterModificationInfo, Long> implements IVoterModificationInfoDAO{

	public VoterModificationInfoDAO()
	{
		super(VoterModificationInfo.class);
	}
	
	public Integer  deletevotermodificationInfoByConstituencyId(Long constituencyId,Long publicationId)
	{
		Query query =getSession().createQuery("delete from VoterModificationInfo model where model.constituencyId=:constituencyId and model.publicationDate.publicationDateId=:publicationId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		return query.executeUpdate();
	}
	
	public List<Long> getVoterModificationInfoIds(Long constituencyId,Long publicationId)
	{
		Query query =getSession().createQuery("select model.voterModificationInfoId from  VoterModificationInfo model where model.constituencyId=:constituencyId and model.publicationDate.publicationDateId=:publicationId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		return query.list();
	}
	public List<Object[]> getVoterModificationReportDetailsByReportLevelId(
			Long reportLevelId, List<Long> reportLevelValues,
			Long constituencyId ,List<Long> publicationIdsList)	{
		
		
		Query query = getSession()
				.createQuery(
						"select model.totalVoters , model.maleVoters , model.femaleVoters , " +
						"model.reportLevelValue ,model.voterStatus.status " +
						"from VoterModificationInfo model where model.voterReportLevel.voterReportLevelId = :reportLevelId and " +
						"model.reportLevelValue in(:reportLevelValues) and " +
						"model.constituencyId = :constituencyId");
		
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameterList("reportLevelValues", reportLevelValues);
		query.setParameter("constituencyId", constituencyId);
		
		
		return query.list();
		
	}
	
	public List<Object[]> getGenderWiseVoterModificationsBetweenPublications(Long locationLvl,Long locationValue,Long constituencyId,List<Long> publicationIdsList){
		Query query = getSession().createQuery("select  sum(model.maleVoters), sum(model.femaleVoters),model.voterStatus.status from VoterModificationInfo model where " +
				" model.voterReportLevel.voterReportLevelId = :locationLvl and model.reportLevelValue = :locationValue and model.constituencyId = :constituencyId " +
				" and model.publicationDate.publicationDateId in(:publicationIdsList) group by model.voterStatus.voterStatusId");
		query.setParameter("locationLvl", locationLvl);
		query.setParameter("locationValue", locationValue);
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("publicationIdsList", publicationIdsList);
		return query.list();
	}
	
	public List<Object[]> getGenderWiseVoterModificationsForEachPublication(Long locationLvl,Long locationValue,Long constituencyId,List<Long> publicationIdsList){
		Query query = getSession().createQuery("select  sum(model.maleVoters), sum(model.femaleVoters),model.voterStatus.status,model.publicationDate.publicationDateId,model.publicationDate.name from " +
				" VoterModificationInfo model where model.voterReportLevel.voterReportLevelId = :locationLvl and model.reportLevelValue = :locationValue and model.constituencyId = :constituencyId " +
				" and model.publicationDate.publicationDateId in(:publicationIdsList) group by model.publicationDate.publicationDateId,model.voterStatus.voterStatusId");
		query.setParameter("locationLvl", locationLvl);
		query.setParameter("locationValue", locationValue);
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("publicationIdsList", publicationIdsList);
		return query.list();
	}
	
	public Long getVoterModificationInfoIdByReportLevelValue(Long reportLevelId, Long reportLevelValue, Long publicationDateId,Long voterStatusId, Long constituencyId)
	{
		Query queryObj = getSession().createQuery("select model.voterModificationInfoId from VoterModificationInfo model where " +
				" model.voterReportLevel.voterReportLevelId = :reportLevelId and model.reportLevelValue = :reportLevelValue " +
				" and model.publicationDate.publicationDateId = :publicationDateId and model.voterStatus.voterStatusId = :voterStatusId and model.constituencyId = :constituencyId ");
		
		queryObj.setParameter("reportLevelId", reportLevelId);
		queryObj.setParameter("reportLevelValue", reportLevelValue);
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("voterStatusId", voterStatusId);
		queryObj.setParameter("constituencyId", constituencyId);
		
		return (Long) queryObj.uniqueResult();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterModificationGenderDetailsByLocationValuesList(List<Long> locationValuesList, List<Long> publicationIdsList, Long constituencyId, Long reportLevelId)
	{
		Query queryObj = getSession().createQuery(" select model.totalVoters, model.maleVoters, model.femaleVoters, model.voterStatus.status,model.reportLevelValue from " +
				" VoterModificationInfo model where model.reportLevelValue in (:reportLevelValue) and model.voterReportLevel.voterReportLevelId = :voterReportLevelId " +
				" and model.constituencyId =:constituencyId and model.publicationDate.publicationDateId in(:publicationDateId) " +
				" ");
		
		queryObj.setParameterList("reportLevelValue", locationValuesList);
		queryObj.setParameterList("publicationDateId", publicationIdsList);
		queryObj.setParameter("constituencyId", constituencyId);
		queryObj.setParameter("voterReportLevelId", reportLevelId);
		
		return queryObj.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getReportLevelValueByReportLevelId(Long constituencyId,Long publicationDateId,Long reportLevelId)
	{
		Query query = getSession().createQuery(" select distinct model.reportLevelValue from VoterModificationInfo model where " +
				" model.voterReportLevel.voterReportLevelId = :voterReportLevelId and model.constituencyId =:constituencyId " +
				" and model.publicationDate.publicationDateId =:publicationDateId ");
		
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("voterReportLevelId", reportLevelId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getModificationDetailsByConstituencyId(Long constituencyId,Long publicationDateId,Long reportLevelId)
	{
		Query query = getSession().createQuery(" select model.reportLevelValue,count(model.voterStatus.voterStatusId) from VoterModificationInfo model where " +
				" model.voterReportLevel.voterReportLevelId = :voterReportLevelId and model.constituencyId =:constituencyId " +
				" and model.publicationDate.publicationDateId =:publicationDateId group by model.reportLevelValue ");
		
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("voterReportLevelId", reportLevelId);
		return query.list();
	}
	
		
	@SuppressWarnings("unchecked")
	public List<Long> getVoterModificationIdsByReportLevelValue(Long constituencyId,Long publicationDateId,Long reportLevelId,List<Long> reportLevelValueList)
	{
		Query query = getSession().createQuery(" select model.voterModificationInfoId from VoterModificationInfo model where " +
				" model.voterReportLevel.voterReportLevelId =:reportLevelId and model.constituencyId =:constituencyId  " +
				" and model.publicationDate.publicationDateId =:publicationDateId and model.reportLevelValue in (:reportLevelValueList) ");
		
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameterList("reportLevelValueList", reportLevelValueList);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterModificationDetailsByModificationIdsList(List<Long> modificationIdsList)
	{
		Query query = getSession().createQuery("select distinct model.reportLevelValue, model.voterReportLevel.voterReportLevelId from VoterModificationInfo model " +
				" where model.voterModificationInfoId in (:modificationIdsList) order by model.voterReportLevel.voterReportLevelId ");
		query.setParameterList("modificationIdsList", modificationIdsList);
		return query.list();
	}
	
}
