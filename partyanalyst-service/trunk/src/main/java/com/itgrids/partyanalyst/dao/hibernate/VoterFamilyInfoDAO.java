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
}
