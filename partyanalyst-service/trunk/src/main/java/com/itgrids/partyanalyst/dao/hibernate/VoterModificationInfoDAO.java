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
				" and model.publicationDate.publicationDateId in(:publicationIdsList) group by model.voterStatus.status");
		query.setParameter("locationLvl", locationLvl);
		query.setParameter("locationValue", locationValue);
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("publicationIdsList", publicationIdsList);
		return query.list();
	}
	
	public List<Object[]> getGenderWiseVoterModificationsForEachPublication(Long locationLvl,Long locationValue,Long constituencyId,List<Long> publicationIdsList){
		Query query = getSession().createQuery("select  sum(model.maleVoters), sum(model.femaleVoters),model.voterStatus.status,model.publicationDate.publicationDateId,model.publicationDate.name from " +
				" VoterModificationInfo model where model.voterReportLevel.voterReportLevelId = :locationLvl and model.reportLevelValue = :locationValue and model.constituencyId = :constituencyId " +
				" and model.publicationDate.publicationDateId in(:publicationIdsList) group by model.publicationDate.publicationDateId,model.voterStatus.status");
		query.setParameter("locationLvl", locationLvl);
		query.setParameter("locationValue", locationValue);
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("publicationIdsList", publicationIdsList);
		return query.list();
	}
}
