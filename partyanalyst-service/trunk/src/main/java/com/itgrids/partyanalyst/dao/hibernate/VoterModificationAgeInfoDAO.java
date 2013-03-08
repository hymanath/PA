package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterModificationAgeInfoDAO;
import com.itgrids.partyanalyst.model.VoterModificationAgeInfo;

public class VoterModificationAgeInfoDAO extends GenericDaoHibernate<VoterModificationAgeInfo,Long> implements IVoterModificationAgeInfoDAO
{

	public VoterModificationAgeInfoDAO() {
		super(VoterModificationAgeInfo.class);
		
	}

	
	public Integer deletevotermodificationInfoByConstituencyId(Long voterModificationInfoId)
	{
		Query query =getSession().createQuery("delete from VoterModificationAgeInfo model where model.voterModificationInfo.voterModificationInfoId=:voterModificationInfoId ");
		query.setParameter("voterModificationInfoId", voterModificationInfoId);
		return query.executeUpdate();
	}
	
	public Integer deleteVoterModicationAgeInfoById(List<Long> voterModificationInfoIds)
	{
		Query query =getSession().createQuery("delete from VoterModificationAgeInfo model where model.voterModificationInfo.voterModificationInfoId in (:voterModificationInfoId)");
		query.setParameterList("voterModificationInfoId", voterModificationInfoIds);
		
		return query.executeUpdate();
	}
	
	public List<Object[]> getGenderWiseVoterModificationsBetweenPublications(Long locationLvl,Long locationValue,Long constituencyId,List<Long> publicationIdsList,Set<Long> ageRangeIds){
		Query query = getSession().createQuery("select  sum(model.totalVoters),model.voterAgeRangeId,model.voterModificationInfo.voterStatus.status from VoterModificationAgeInfo model where " +
				" model.voterModificationInfo.voterReportLevel.voterReportLevelId = :locationLvl and model.voterModificationInfo.reportLevelValue = :locationValue and model.voterModificationInfo.constituencyId = :constituencyId " +
				" and model.voterModificationInfo.publicationDate.publicationDateId in(:publicationIdsList) and model.voterAgeRangeId in(:ageRangeIds) group by model.voterAgeRangeId,model.voterModificationInfo.voterStatus.status");
		query.setParameter("locationLvl", locationLvl);
		query.setParameter("locationValue", locationValue);
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("publicationIdsList", publicationIdsList);
		query.setParameterList("ageRangeIds", ageRangeIds);
		return query.list();
	}
}
