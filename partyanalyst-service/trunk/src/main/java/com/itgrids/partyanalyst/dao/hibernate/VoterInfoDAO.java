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
		stringBuilder.append(" select model.totalVoters,model.maleVoters,model.femaleVoters,model.maleVotersPercentage,model.femaleVotersPercentage from VoterInfo model where ");
		stringBuilder.append(" model.voterReportLevel.voterReportLevelId =:reportLevelId and model.reportLevelValue =:reportLevelValue ");
		stringBuilder.append(" and model.publicationDate.publicationDateId =:publicationDateId and model.constituencyId = :constituencyId ");
		Query queryObj = getSession().createQuery(stringBuilder.toString());
		
		queryObj.setParameter("reportLevelId", reportLevelId);
		queryObj.setParameter("reportLevelValue", reportLevelValue);
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("constituencyId", constituencyId);
		return queryObj.list();
	}

	@SuppressWarnings("unchecked")
	public List<Long> getNONURBANConstituencyIds(Long electionTypeId,Long electionYear,Long countryId){
		Query query = getSession().createQuery("select distinct model1.constituencyId from VoterInfo model1 where model1.voterReportLevel.reportLevel =:reportLevel " +
				" and model1.constituencyId in ( Select model.constituency.constituencyId from DelimitationConstituency model where " +
				" model.constituency.electionScope.electionType.electionTypeId = :electionTypeId and model.constituency.state.country.countryId = :countryId and model.year = :electionYear)");
		query.setParameter("reportLevel", IConstants.CONSTITUENCY);
		query.setParameter("electionTypeId", electionTypeId);
		query.setParameter("electionYear", electionYear);
		query.setParameter("countryId", countryId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<VoterInfo> getVoterInfoList(Long constituencyId,Long publicationDateId)
	{
		Query query = getSession().createQuery(" from VoterInfo model where model.constituencyId =:constituencyId and model.publicationDate.publicationDateId = :publicationDateId ");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId",publicationDateId);
		return query.list();
	}
	
	public Long getTotalVotersForSelectdLevel(Long levelId,Long levelValue,Long publicationDateId,Long constituencyId)
	{
			Query query = getSession().createQuery("select sum(model.totalVoters) from VoterInfo model where " +
					" model.constituencyId =:constituencyId and model.voterReportLevel.voterReportLevelId = :levelId " +
					" and model.reportLevelValue = :levelValue and model.publicationDate.publicationDateId = :publicationDateId");
			query.setParameter("levelId", levelId);
			query.setParameter("levelValue", levelValue);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("constituencyId", constituencyId);
			return (Long) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalVotersForHamletBooth(Long constituencyId,Long publicationDateId,Long reportLevelId)
	{
		Query query = getSession().createQuery(" select model.reportLevelValue,model.totalVoters from VoterInfo model where model.constituencyId =:constituencyId " +
				" and model.publicationDate.publicationDateId =:publicationDateId and model.voterReportLevel.voterReportLevelId =:voterReportLevelId ");
		
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("voterReportLevelId", reportLevelId);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterCountInPanchayatLevel(Long constituencyId,Long publicationDateId,Long reportLevelId)
	{
		Query query = getSession().createQuery("select model.reportLevelValue ,model.totalVoters from VoterInfo model " +
				" where model.constituencyId = :constituencyId and " +
				" model.voterReportLevel.voterReportLevelId = :reportLevelId and " +
				" model.publicationDate.publicationDateId = :publicationDateId");
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Long> getCountForSelectdCountRange(Long constituencyId,Long publicationId,Long minValue,Long maxValue,Long reportLevelId)
	{
		Query query = getSession().createQuery("select model.reportLevelValue  from VoterInfo model " +
				" where model.constituencyId = :constituencyId and " +
				" model.voterReportLevel.voterReportLevelId = :reportLevelId and " +
				" model.publicationDate.publicationDateId = :publicationId and " +
				" model.totalVoters >= :minValue and " +
				" model.totalVoters <= :maxValue");
		query.setParameter("publicationId", publicationId);
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("minValue", minValue);
		query.setParameter("maxValue", maxValue);
		return query.list();
	}
	
	public Long getLatestPublicationDate(Long constituencyId)
	{
		Query query = getSession().createQuery("select max(model.publicationDate.publicationDateId) " +
				" from VoterInfo model  where model.constituencyId = :constituencyId ");
		query.setParameter("constituencyId", constituencyId);
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getTotalVotersInAPanchayat(Long constituencyId,Long publicationId,Long levelId,List<Long> panchayatIds)
	{
		Query query = getSession().createQuery("select model.reportLevelValue ," +
				" model.totalVoters from VoterInfo model" +
				" where model.constituencyId = :constituencyId and " +
				" model.voterReportLevel.voterReportLevelId = :levelId and " +
				" model.publicationDate.publicationDateId = :publicationId and " +
				" model.reportLevelValue in (:panchayatIds) " +
				" ");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("levelId", levelId);
		query.setParameterList("panchayatIds", panchayatIds);
		return query.list();		
	}
	public List<Object[]> getVotersCountInPunchayatAndLocalElecBody(Long constituencyId,Long publicationId){
		Query query = getSession().createQuery("select model.totalVoters,model.reportLevelValue,model.voterReportLevel.voterReportLevelId from VoterInfo model " +
		" where model.constituencyId =:constituencyId and model.publicationDate.publicationDateId =:publicationId and " +
		" model.voterReportLevel.voterReportLevelId in(3,5)");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		return query.list();
		}

	public List<Object[]> getVoterInfoByPublicationDateIdsNew(Long reportLevelId, Long reportLevelValue, List<Long> publicationDateIds)
	{
		/*Query query = getSession().createQuery("select model.totalVoters, model.maleVoters, model.femaleVoters,model.publicationDate.name " +
				"  from VoterInfo model where model.voterReportLevel.voterReportLevelId = :reportLevelId " +
				" and model.reportLevelValue = :reportLevelValue and model.publicationDate.publicationDateId in (:publicationDateId) ");
		*/
		Query query = getSession().createQuery("select count(distinct model.voter.voterId) ,model.booth.publicationDate.publicationDateId, model.booth.publicationDate.name " +
				"  from BoothPublicationVoter model where model.booth.constituency.constituencyId = :reportLevelValue " +
				"   and model.booth.publicationDate.publicationDateId in (:publicationDateId) group by model.booth.publicationDate.publicationDateId ");
		
		query.setParameter("reportLevelValue", reportLevelValue);
		query.setParameterList("publicationDateId", publicationDateIds);
		return query.list();
	}
	public List<Object[]> getVoterInfoByPublicationDateIdsNewMaleCount(Long reportLevelId, Long reportLevelValue, List<Long> publicationDateIds)
	{
		
		Query query = getSession().createQuery("select count(distinct model.voter.voterId) ,model.booth.publicationDate.publicationDateId, model.booth.publicationDate.name " +
				"  from BoothPublicationVoter model where model.booth.constituency.constituencyId = :reportLevelValue and model.voter.gender = 'M'" +
				"   and model.booth.publicationDate.publicationDateId in (:publicationDateId) group by model.booth.publicationDate.publicationDateId ");
		
		query.setParameter("reportLevelValue", reportLevelValue);
		query.setParameterList("publicationDateId", publicationDateIds);
		return query.list();
	}
	public List<Object[]> getVoterInfoByPublicationDateIdsNewFemaleCount(Long reportLevelId, Long reportLevelValue, List<Long> publicationDateIds)
	{
		
		Query query = getSession().createQuery("select count(distinct model.voter.voterId) ,model.booth.publicationDate.publicationDateId, model.booth.publicationDate.name " +
				"  from BoothPublicationVoter model where model.booth.constituency.constituencyId = :reportLevelValue and model.voter.gender = 'F'" +
				"   and model.booth.publicationDate.publicationDateId in (:publicationDateId) group by model.booth.publicationDate.publicationDateId ");
		
		query.setParameter("reportLevelValue", reportLevelValue);
		query.setParameterList("publicationDateId", publicationDateIds);
		return query.list();
	}
	
	public List<Object[]> getVotersCountInCustomWards(Long constituencyId,Long publicationId,Long voterReportLevelId){
		Query query = getSession().createQuery("select model.reportLevelValue,model.totalVoters from VoterInfo model " +
				" where model.constituencyId =:constituencyId and model.publicationDate.publicationDateId =:publicationId and " +
				" model.voterReportLevel.voterReportLevelId =:voterReportLevelId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("voterReportLevelId", voterReportLevelId);
		return query.list();
	}
	
	public List<Object[]> getPanchayatDetailsForConstituency(Long constituencyId,Long publicationId,Long mandalId)
	{
		Query query = getSession().createQuery("select model.panchayatId, model.panchayatName from Panchayat model ,VoterInfo model1 " +
				" where model.panchayatId = model1.reportLevelValue and model1.constituencyId =:constituencyId and model1.publicationDate.publicationDateId =:publicationId " +
				" and model1.voterReportLevel.voterReportLevelId = 3 and  model.tehsil.tehsilId = :mandalId");
		
		query.setParameter("mandalId", mandalId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		return query.list();
	}
	
	public List<Object[]> getVoterCountByLevels(Long constituencyId,Long publicationDateId,List<Long> reportLevelIds,Set<Long> locationIds)
	{
		Query query = getSession().createQuery("select model.reportLevelValue ,model.totalVoters,model.voterReportLevel.voterReportLevelId from VoterInfo model " +
				" where model.constituencyId = :constituencyId and " +
				" model.voterReportLevel.voterReportLevelId in(:reportLevelIds) and " +
				" model.publicationDate.publicationDateId = :publicationDateId and model.reportLevelValue in(:locationIds)");
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameterList("reportLevelIds", reportLevelIds);
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("locationIds",locationIds);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getFamiliesCountInAPanchayats(List<Long> panchayatIds,Long publicationDateId)
	{
		Query query = getSession().createQuery("Select DISTINCT model.reportLevelValue,model.totalFamilies from VoterInfo model where model.voterReportLevel.voterReportLevelId = 3 and " +
				" model.reportLevelValue in (:panchayatIds) and model.publicationDate.publicationDateId = :publicationDateId ");
		query.setParameterList("panchayatIds",panchayatIds);
		query.setParameter("publicationDateId",publicationDateId);
		return query.list();
	}
	
	public List<Object[]> getVotersCountForAllConstituencies(Long publicationDateId,List<Long> constituencyIds)
	{
		Query query = getSession().createQuery("Select distinct model.reportLevelValue,model.totalVoters from VoterInfo model where model.voterReportLevel.voterReportLevelId = 1 and " +
				" model.publicationDate.publicationDateId = :publicationDateId and model.reportLevelValue in(:constituencyIds)");
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameterList("constituencyIds",constituencyIds);
		return query.list();
	}
}
