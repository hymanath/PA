package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.model.VoterInfo;
import com.itgrids.partyanalyst.utils.IConstants;

public class VoterInfoDAO extends GenericDaoHibernate<VoterInfo, Long> implements IVoterInfoDAO{

	public VoterInfoDAO()
	{
		super(VoterInfo.class);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<VoterInfo> getVotersCount(Long reportLevelId, Long reportLevelValue, Long publicationDateId,Long constituencyId)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" select model from VoterInfo model where ");
		stringBuilder.append(" model.voterReportLevel.voterReportLevelId =:reportLevelId and model.reportLevelValue =:reportLevelValue ");
		stringBuilder.append(" and model.publicationDate.publicationDateId =:publicationDateId and model.constituencyId = :constituencyId ");
		Query queryObj = getSession().createQuery(stringBuilder.toString());
		
		queryObj.setParameter("reportLevelId", reportLevelId);
		queryObj.setParameter("reportLevelValue", reportLevelValue);
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("constituencyId", constituencyId);
		return queryObj.list();
    }
	
	public Long getVotersCountInALocation(Long reportLevelId, Long reportLevelValue, Long publicationDateId,Long constituencyId)
	{
		Query query = getSession().createQuery("select model.totalVoters from VoterInfo model where model.voterReportLevel.voterReportLevelId = :reportLevelId and " +
				" model.reportLevelValue = :reportLevelValue and model.publicationDate.publicationDateId = :publicationDateId and model.constituencyId = :constituencyId");
		
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameter("reportLevelValue", reportLevelValue);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("constituencyId", constituencyId);
		
		return (Long)query.uniqueResult();
    }
	
	public List<VoterInfo> getVotersMultipleCount(Long reportLevelId, Set<Long> reportLevelValues, Long publicationDateId,Long constituencyId)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" select model from VoterInfo model where ");
		stringBuilder.append(" model.voterReportLevel.voterReportLevelId =:reportLevelId and model.reportLevelValue in(:reportLevelValues) ");
		stringBuilder.append(" and model.publicationDate.publicationDateId =:publicationDateId and model.constituencyId = :constituencyId ");
		Query queryObj = getSession().createQuery(stringBuilder.toString());
		
		queryObj.setParameter("reportLevelId", reportLevelId);
		queryObj.setParameterList("reportLevelValues", reportLevelValues);
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("constituencyId", constituencyId);
		return queryObj.list();
    }
	
	public Integer deleteVotersInfoByReportLevelValue(Long reportLevelId, List<Long> reportLevelValue, Long publicationDateId)
	{
		Query query = getSession().createQuery("delete from VoterInfo model where model.reportLevelValue in (:reportLevelValue) and model.voterReportLevel.voterReportLevelId = :reportLevelId " +
				" and model.publicationDate.publicationDateId = :publicationDateId ");
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameterList("reportLevelValue", reportLevelValue);
		query.setParameter("publicationDateId", publicationDateId);
		return query.executeUpdate();
	}
	
	
	public Long getTotalVotersByReportLevelValue(Long reportLevelId, Long reportLevelValue, Long publicationDateId,Long constituencyId)
	{
		Query queryObj = getSession().createQuery("select model.totalVoters from VoterInfo model where model.voterReportLevel.voterReportLevelId = :reportLevelId " +
				" and model.reportLevelValue =:reportLevelValue and model.publicationDate.publicationDateId =:publicationDateId  and model.constituencyId = :constituencyId ");
		
		queryObj.setParameter("reportLevelId", reportLevelId);
		queryObj.setParameter("reportLevelValue", reportLevelValue);
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("constituencyId", constituencyId);
		return (Long) queryObj.uniqueResult();
	}
	
	public Long getFamiliesCountInALocation(Long reportLevelId, Long reportLevelValue, Long publicationDateId,Long constituencyId)
	{
		Query query = getSession().createQuery("select model.totalFamilies from VoterInfo model where model.voterReportLevel.voterReportLevelId = :reportLevelId and " +
				" model.reportLevelValue = :reportLevelValue and model.publicationDate.publicationDateId = :publicationDateId and model.constituencyId = :constituencyId");
		
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameter("reportLevelValue", reportLevelValue);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("constituencyId", constituencyId);
		return (Long)query.uniqueResult();
    }
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterInfoByPublicationDateIds(Long reportLevelId, Long reportLevelValue, List<Long> publicationDateIds)
	{
		Query query = getSession().createQuery("select model.totalVoters, model.maleVoters, model.femaleVoters,model.publicationDate.name " +
				"  from VoterInfo model where model.voterReportLevel.voterReportLevelId = :reportLevelId " +
				" and model.reportLevelValue = :reportLevelValue and model.publicationDate.publicationDateId in (:publicationDateId) ");
		
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameter("reportLevelValue", reportLevelValue);
		query.setParameterList("publicationDateId", publicationDateIds);
		return query.list();
	}
	
	public Integer deleteVotersInfoByConstituencyId(Long constituencyId, Long publicationDateId)
	{
		Query query = getSession().createQuery("delete from VoterInfo model where model.constituencyId=:constituencyId and model.publicationDate.publicationDateId = :publicationDateId ");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		return query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalVotersByPublicationDateIdsList(List<Long> publicationDateIdsList, Long reportLevelId, Long locationValue, Long constituencyId)
	{
		Query queryObj = getSession().createQuery("select model.totalVoters,model.publicationDate.name from VoterInfo model where model.voterReportLevel.voterReportLevelId =:reportLevelId " +
				" and model.reportLevelValue =:locationValue and model.constituencyId =:constituencyId and model.publicationDate.publicationDateId in(:publicationDateIdsList) order by model.publicationDate.name desc ");
		
		queryObj.setParameter("reportLevelId", reportLevelId);
		queryObj.setParameter("locationValue", locationValue);
		queryObj.setParameter("constituencyId", constituencyId);
		queryObj.setParameterList("publicationDateIdsList", publicationDateIdsList);
		
		return queryObj.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPublicationDetailsBasedOnConstituencyId(Long constituencyId)
	{
		Query queryObj = getSession().createQuery("select distinct model.publicationDate.publicationDateId,model.publicationDate.date from VoterInfo model where " +
				" model.constituencyId =:constituencyId and model.voterReportLevel.reportLevel =:reportLevel order by model.publicationDate.date desc ");
		
		queryObj.setParameter("constituencyId", constituencyId);
		queryObj.setParameter("reportLevel", IConstants.CONSTITUENCY);
		return queryObj.list();
	}
	@SuppressWarnings("unchecked")
	public List<Long> getConstituencyIds()
	{
		Query query = getSession().createQuery("select distinct model.constituencyId from VoterInfo model where model.voterReportLevel.reportLevel =:reportLevel ");
		query.setParameter("reportLevel", IConstants.CONSTITUENCY);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getVoterPublicationIdsBetweenTwoPublications(Long fromPublicationDateId, Long toPublicationDateId)
	{
		Query queryObj = getSession().createQuery("select distinct model.publicationDate.publicationDateId from VoterInfo model where " +
				" model.publicationDate.date between (select model2.date from PublicationDate model2 where model2.publicationDateId = :fromPublicationDateId) " +
				" and (select model3.date from PublicationDate model3 where model3.publicationDateId = :toPublicationDateId ) order by model.publicationDate.date desc ");
		
		queryObj.setParameter("fromPublicationDateId", fromPublicationDateId);
		queryObj.setParameter("toPublicationDateId", toPublicationDateId);
		return queryObj.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Long> getPreviousPublicationIds(Long publicationDateId)
	{
		Query query = getSession().createQuery("select distinct model.publicationDate.publicationDateId from VoterInfo model " +
				" where model.publicationDate.date < (select model2.date from PublicationDate model2 where model2.publicationDateId = :publicationDateId) " +
				" order by model.publicationDate.date desc ");
		query.setParameter("publicationDateId",publicationDateId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPanchayatWiseVotersCount(Long constituencyId, Long publicationDateId)
	{
		Query query = getSession().createQuery("select P.tehsil.tehsilId,P.tehsil.tehsilName,P.panchayatId,P.panchayatName,VI.totalVoters from " +
				" VoterInfo VI, Panchayat P where P.panchayatId = VI.reportLevelValue and VI.constituencyId = :constituencyId and " +
				" VI.publicationDate.publicationDateId = :publicationDateId and VI.voterReportLevel.reportLevel = :reportLevel order by P.tehsil.tehsilName,P.panchayatName ");
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameter("reportLevel","Panchayat");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getReportLevelValueByConstituencyId(Long constituencyId,Long publicationDateId,Long reportLevelValue)
	{
		Query query = getSession().createQuery("select model.reportLevelValue from VoterInfo model where model.constituencyId =:constituencyId " +
				" and model.publicationDate.publicationDateId =:publicationDateId and model.voterReportLevel.voterReportLevelId =:voterReportLevelId ");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("voterReportLevelId", reportLevelValue);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterDetailedCountByLocation(Long reportLevelId,Long reportLevelValue,Long publicationDateId,Long constituencyId)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" select model.totalVoters,model.maleVoters,model.femaleVoters from VoterInfo model where ");
		stringBuilder.append(" model.voterReportLevel.voterReportLevelId =:reportLevelId and model.reportLevelValue =:reportLevelValue ");
		stringBuilder.append(" and model.publicationDate.publicationDateId =:publicationDateId and model.constituencyId = :constituencyId ");
		Query queryObj = getSession().createQuery(stringBuilder.toString());
		
		queryObj.setParameter("reportLevelId", reportLevelId);
		queryObj.setParameter("reportLevelValue", reportLevelValue);
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("constituencyId", constituencyId);
		return queryObj.list();
	}

}
