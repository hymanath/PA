package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.google.gdata.util.ErrorContent.LocationType;
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
				" model.publicationDate.publicationDateId = :publicationDateId and model.constituencyId in(:constituencyIds)");
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameterList("constituencyIds",constituencyIds);
		return query.list();
	}
	public List<Object[]> getVotersCountByLocationType(Long publicationDateId,List<Long> locationIds,String locationType,Long constituencyId)
	{
		StringBuilder str = new StringBuilder();
		
		str.append("Select distinct model.reportLevelValue,model.totalVoters from VoterInfo model where  " +
				" model.publicationDate.publicationDateId = :publicationDateId and model.constituencyId = :constituencyId");
		if(locationType.equalsIgnoreCase(IConstants.MANDAL))
			   str.append(" and model.voterReportLevel.voterReportLevelId = 2");
		else if(locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			 str.append(" and model.voterReportLevel.voterReportLevelId = 5");
			 else if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
				 str.append(" and model.voterReportLevel.voterReportLevelId = 3");
			 else if(locationType.equalsIgnoreCase(IConstants.BOOTH))
				 str.append(" and model.voterReportLevel.voterReportLevelId = 4");
		str.append(" and model.reportLevelValue in(:locationIds)");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameterList("locationIds",locationIds);
		query.setParameter("constituencyId",constituencyId);
		return query.list();
	}
	
	public Long getVotersCountInADistrict(Long districtId, Long publicationDateId)
	{
		Query query = getSession().createQuery("Select sum(model.totalVoters) from VoterInfo model,Constituency model2 where model.constituencyId = model2.constituencyId and " +
				" model.voterReportLevel.voterReportLevelId = :reportLevelId and model.reportLevelValue = model.constituencyId and model.publicationDate.publicationDateId = :publicationDateId and " +
				" model2.district.districtId = :districtId");
		
		query.setParameter("reportLevelId", 1L);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("districtId", districtId);
		
		return (Long)query.uniqueResult();
    }
	
	public Long getVotersCountInAParliament(Long parliamentId, Long publicationDateId)
	{
		Query query = getSession().createQuery("Select sum(model.totalVoters) from VoterInfo model,DelimitationConstituencyAssemblyDetails model2 where model.constituencyId = model2.constituency.constituencyId and " +
				" model.voterReportLevel.voterReportLevelId = :reportLevelId and model.reportLevelValue = model.constituencyId and model.publicationDate.publicationDateId = :publicationDateId and " +
				" model2.delimitationConstituency.year = 2009 and model2.delimitationConstituency.constituency.constituencyId = :parliamentId");
		
		query.setParameter("reportLevelId", 1L);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("parliamentId", parliamentId);
		
		return (Long)query.uniqueResult();
    }
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVotersCountInADistrictsList(List<Long> districtIdsList, Long publicationDateId)
	{// 0 id,1name,2total,3male total4female total
		Query query = getSession().createQuery("Select model2.district.districtId,model2.district.districtName,sum(model.totalVoters),sum(model.maleVoters),sum(model.femaleVoters) from VoterInfo model,Constituency model2 where model.constituencyId = model2.constituencyId and " +
				" model.voterReportLevel.voterReportLevelId = :reportLevelId and model.reportLevelValue = model2.constituencyId and model.publicationDate.publicationDateId = :publicationDateId and " +
				" model2.district.districtId in (:districtIdsList) group by model2.district.districtId");
		
		query.setParameter("reportLevelId", 1L);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameterList("districtIdsList", districtIdsList);
		
		return query.list();
    }
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVotersCountInConstituenciesByDistrictsList(List<Long> districtIdsList, Long publicationDateId)
	{//0id,1 name,2 total,3districtId,4male total,5female total
		Query query = getSession().createQuery("Select model2.constituencyId,model2.name,sum(model.totalVoters),model2.district.districtId,sum(model.maleVoters),sum(model.femaleVoters) from VoterInfo model,Constituency model2 where model.constituencyId = model2.constituencyId and " +
				" model.voterReportLevel.voterReportLevelId = :reportLevelId and model.reportLevelValue = model2.constituencyId and model.publicationDate.publicationDateId = :publicationDateId and " +
				" model2.district.districtId in (:districtIdsList) group by model2.constituencyId");
		
		query.setParameter("reportLevelId", 1L);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameterList("districtIdsList", districtIdsList);
		
		return query.list();
    }
	@SuppressWarnings("unchecked")
	public List<Object[]> getVotersCountInATehsilList(List<Long> tehsilIdsList, Long publicationDateId)
	{
		Query query = getSession().createQuery("Select model.reportLevelValue,model2.tehsilName,sum(model.totalVoters),model2.district.districtId,sum(model.maleVoters),sum(model.femaleVoters),model.constituencyId from VoterInfo model,Tehsil model2 where model.reportLevelValue = model2.tehsilId and " +
				" model.voterReportLevel.voterReportLevelId = :reportLevelId and model.publicationDate.publicationDateId = :publicationDateId and " +
				" model2.tehsilId in (:tehsilIdsList) group by model2.tehsilId");
		query.setParameter("reportLevelId", 2L);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameterList("tehsilIdsList", tehsilIdsList);
		
		return query.list();
    }
	@SuppressWarnings("unchecked")
	public List<Object[]> getVotersCountInALocalBodyList(List<Long> localbodyIdsList, Long publicationDateId)
	{
		Query query = getSession().createQuery("Select model.reportLevelValue,model2.name,sum(model.totalVoters),model2.district.districtId,sum(model.maleVoters),sum(model.femaleVoters),model.constituencyId from VoterInfo model,LocalElectionBody model2 where model.reportLevelValue = model2.localElectionBodyId and " +
				" model.voterReportLevel.voterReportLevelId = :reportLevelId and model.publicationDate.publicationDateId = :publicationDateId and " +
				" model2.localElectionBodyId in (:localbodyIdsList) group by model2.localElectionBodyId");
		query.setParameter("reportLevelId", 5L);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameterList("localbodyIdsList", localbodyIdsList);
		
		return query.list();
    }
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVotersCountInPanchayatList(List<Long> panchayatIds, Long publicationDateId)
	{
		Query query = getSession().createQuery("Select model.reportLevelValue,model2.panchayatName,sum(model.totalVoters),model2.tehsil.district.districtId,sum(model.maleVoters),sum(model.femaleVoters),model.constituencyId from VoterInfo model,Panchayat model2 where model.reportLevelValue = model2.panchayatId and " +
				" model.voterReportLevel.voterReportLevelId = :reportLevelId and model.publicationDate.publicationDateId = :publicationDateId and " +
				" model2.panchayatId in (:panchayatIds) group by model2.panchayatId");
		query.setParameter("reportLevelId",3L);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameterList("panchayatIds", panchayatIds);
		
		return query.list();
    }
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVotersCountInBoothsList(List<Long> boothIds, Long publicationDateId)
	{
		Query query = getSession().createQuery("Select model.reportLevelValue,model2.partNo,sum(model.totalVoters),model2.tehsil.district.districtId,sum(model.maleVoters),sum(model.femaleVoters),model.constituencyId from VoterInfo model,Booth model2 where model.reportLevelValue = model2.boothId and " +
				" model.voterReportLevel.voterReportLevelId = :reportLevelId and model.publicationDate.publicationDateId = :publicationDateId and " +
				" model2.boothId in (:boothIds) group by model2.boothId");
		query.setParameter("reportLevelId",4L);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameterList("boothIds", boothIds);
		
		return query.list();
    }
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVotersCountInConstituencies(List<Long> constituencyIds, Long publicationDateId)
	{
		Query query = getSession().createQuery("select model.constituencyId,model2.name,sum(model.totalVoters),model2.district.districtId,sum(model.maleVoters),sum(model.femaleVoters) from VoterInfo model,Constituency model2 where model.constituencyId = model2.constituencyId and " +
				" model.voterReportLevel.voterReportLevelId = :reportLevelId and model.reportLevelValue = model2.constituencyId and model.publicationDate.publicationDateId = :publicationDateId and " +
				" model.constituencyId in (:constituencyIds) group by model.constituencyId");
		
		query.setParameter("reportLevelId", 1L);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameterList("constituencyIds", constituencyIds);
		
		return query.list();
    }
	
	public List<Object[]> getVotersCountInConstituenciesByDistrictsListAndConstituencies(List<Long> districtIdsList, Long publicationDateId, List<Long> constiIds){
		//0id,1 name,2 total,3districtId,4male total,5female total
		Query query = getSession().createQuery("Select model2.district.districtId," +
				" model2.district.districtName," +
				" sum(model.totalVoters)," +
				" sum(model.maleVoters)," +
				" sum(model.femaleVoters) " +
				" from VoterInfo model,Constituency model2 " +
				" where model.constituencyId = model2.constituencyId and " +
				" model.voterReportLevel.voterReportLevelId = :reportLevelId and " +
				" model.reportLevelValue = model2.constituencyId and " +
				" model.publicationDate.publicationDateId = :publicationDateId and " +
				" model2.district.districtId in (:districtIdsList) and " +
				" model2.constituencyId in(:constituencyIds )" +
				" group by model2.district.districtId");
		
		query.setParameter("reportLevelId", 1L);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameterList("districtIdsList", districtIdsList);
		query.setParameterList("constituencyIds", constiIds);
		
		return query.list();
    }
	
	public VoterInfo getVoterInfoOfALocation(Long constituencyId,Long publicationDateId,Long reportLevelId,Long reportLevelValue)
	{
		Query query = getSession().createQuery("select model from VoterInfo model where model.constituencyId = :constituencyId and model.publicationDate.publicationDateId = :publicationDateId and " +
				" model.voterReportLevel.voterReportLevelId = :reportLevelId and model.reportLevelValue = :reportLevelValue ");
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameter("reportLevelId",reportLevelId);
		query.setParameter("reportLevelValue",reportLevelValue);
		return (VoterInfo)query.uniqueResult();
	}
	public List<VoterInfo> getVotersCountForMultipleLocs(Long reportLevelId, Set<Long> reportLevelValues, Long publicationDateId,Long constituencyId)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" select model from VoterInfo model where ");
		stringBuilder.append(" model.voterReportLevel.voterReportLevelId =:reportLevelId and model.reportLevelValue in(:reportLevelValues) ");
		stringBuilder.append(" and model.publicationDate.publicationDateId =:publicationDateId  ");
		if(constituencyId != null){
			stringBuilder.append(" and model.constituencyId = :constituencyId");
		}
		Query queryObj = getSession().createQuery(stringBuilder.toString());
		
		queryObj.setParameter("reportLevelId", reportLevelId);
		queryObj.setParameterList("reportLevelValues", reportLevelValues);
		queryObj.setParameter("publicationDateId", publicationDateId);
		if(constituencyId != null){
		   queryObj.setParameter("constituencyId", constituencyId);
		}
		return queryObj.list();
    }
	
	public List<Object[]> getVoterCadreDetailsBySearchCriteria(Long stateId, String locationType,List<Long> locationIdsList)
	{
		if(locationType != null)
		{
			StringBuilder queryStr = new StringBuilder();
			boolean isStateWise = false;
			StringBuilder str  = new StringBuilder();
			//str.append(" select distinct model.reportLevelValue,model.totalVoters  ");
			
			if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			{
				str.append(" select distinct model2.constituencyId,model.totalVoters  ");
				str.append(" ,model2.name from VoterInfo model,Constituency model2 where model2.constituencyId = model.reportLevelValue and model.constituencyId = model2.constituencyId ");
				str.append(" and model.voterReportLevel.voterReportLevelId = 1 and model.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" ");
				if(locationIdsList != null && locationIdsList.size() > 0)
				{
					str.append(" and model2.district.districtId in (:locationIdsList) ");
				}
				str.append(" group by model2.constituencyId order by model2.name ");
			}		
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
			{
				str.append(" select distinct model2.tehsilId,model.totalVoters  ");
				str.append(" ,model2.tehsilName from VoterInfo model,Tehsil model2,Booth B where model2.tehsilId = model.reportLevelValue  and model2.tehsilId = B.tehsil.tehsilId ");
				str.append(" and B.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and B.constituency.constituencyId in (:locationIdsList) and B.localBody.localElectionBodyId is null " +
						" and  model.voterReportLevel.voterReportLevelId = 2 ");
				str.append(" group by model2.tehsilId order by model2.tehsilName ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			{
				str.append(" select distinct model2.panchayatId,model.totalVoters  ");
				str.append(" ,model2.panchayatName from VoterInfo model,Panchayat model2,Booth B where model2.panchayatId = model.reportLevelValue and model2.panchayatId = B.panchayat.panchayatId ");
				str.append(" and B.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and B.tehsil.tehsilId in (:locationIdsList) ");
				str.append("  and  model.voterReportLevel.voterReportLevelId = 3 group by model2.panchayatId order by model2.panchayatName ");
			}
			else if(locationType != null && (locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY) ))
			{
				str.append(" select distinct model2.localElectionBodyId,model.totalVoters  ");
				str.append(" ,model2.name from VoterInfo model,LocalElectionBody model2,Booth B where model2.localElectionBodyId = model.reportLevelValue and model2.localElectionBodyId = B.localBody.localElectionBodyId ");
				str.append(" and B.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and B.localBody.localElectionBodyId in (:locationIdsList) and B.localBody.localElectionBodyId is not null ");
				str.append("  and  model.voterReportLevel.voterReportLevelId = 5 group by model2.localElectionBodyId order by model2.name ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
			{
				str.append(" select distinct ward.constituencyId,model.totalVoters  ");
				str.append(" ,ward.name from VoterInfo model,Constituency ward where ward.constituencyId = model.reportLevelValue and ward.localElectionBody.localElectionBodyId is not null ");
				str.append(" and model.reportLevelValue in (:locationIdsList)  and model.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" ");			
				str.append("  and  model.voterReportLevel.voterReportLevelId = 6 order by ward.name ");
			}
			else if(stateId != null && stateId.longValue() == 0L) //AP & TS
			{
				isStateWise = true;
				str.append(" select model2.district.districtId,sum(model.totalVoters)  ");
				str.append(" ,model2.district.districtName from VoterInfo model,Constituency model2 where  model.voterReportLevel.voterReportLevelId = 1 and " +
						" model2.constituencyId = model.reportLevelValue and ( model2.district.districtId between 1 and 23)  and model.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" ");
				str.append(" group by  model2.district.districtId order by  model2.district.districtName ");
			}
			else if(stateId != null && stateId.longValue() == 1L) //AP
			{
				isStateWise = true;
				str.append(" select model2.district.districtId,sum(model.totalVoters)  ");
				str.append(" ,model2.district.districtName from VoterInfo model,Constituency model2 where  model.voterReportLevel.voterReportLevelId = 1 and " +
						" model2.constituencyId = model.reportLevelValue and ( model2.district.districtId between 11 and 23)  and model.publicationDate.publicationDateId = 11 ");
				str.append(" group by  model2.district.districtId order by  model2.district.districtName ");
			}
			else if(stateId != null && stateId.longValue() == 2L) //TS
			{
				isStateWise = true;
				str.append(" select model2.district.districtId,sum(model.totalVoters)  ");
				str.append(" ,model2.district.districtName from VoterInfo model,Constituency model2 where  model.voterReportLevel.voterReportLevelId = 1 and " +
						" model2.constituencyId = model.reportLevelValue and ( model2.district.districtId between 1 and 10)  and model.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+"  ");
				str.append(" group by  model2.district.districtId order by  model2.district.districtName ");
			}		
			
			queryStr.append(str.toString());
			
			Query query = getSession().createQuery(queryStr.toString());

			if(!isStateWise && (locationIdsList != null && locationIdsList.size() > 0))
			{
				query.setParameterList("locationIdsList", locationIdsList);
			}
					
			return query.list();
		}
		
		else
		{
			return null;
		}
	}
	
	public List<Object[]> getVotersCountByLocationValues(Long reportLevelId, Set<Long> reportLevelValues, Long publicationDateId,Long constituencyId)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" select model.reportLevelValue,model.totalVoters from VoterInfo model where ");
		stringBuilder.append(" model.voterReportLevel.voterReportLevelId =:reportLevelId and model.reportLevelValue in(:reportLevelValues) ");
		stringBuilder.append(" and model.publicationDate.publicationDateId =:publicationDateId  ");
		if(constituencyId != null){
			stringBuilder.append(" and model.constituencyId = :constituencyId");
		}
		Query queryObj = getSession().createQuery(stringBuilder.toString());
		
		queryObj.setParameter("reportLevelId", reportLevelId);
		queryObj.setParameterList("reportLevelValues", reportLevelValues);
		queryObj.setParameter("publicationDateId", publicationDateId);
		if(constituencyId != null){
		   queryObj.setParameter("constituencyId", constituencyId);
		}
		return queryObj.list();
    }
	
	
	public List<Object[]> getVotersCountForDistrict(Set<Long> districtIdsList, Long publicationDateId)
	{// 0 id,1name,2total,3male total4female total
		Query query = getSession().createQuery("Select model2.district.districtId,sum(model.totalVoters) from VoterInfo model,Constituency model2 where model.constituencyId = model2.constituencyId and " +
				" model.voterReportLevel.voterReportLevelId = :reportLevelId and model.reportLevelValue = model2.constituencyId and model.publicationDate.publicationDateId = :publicationDateId and " +
				" model2.district.districtId in (:districtIdsList) group by model2.district.districtId");
		
		query.setParameter("reportLevelId", 1L);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameterList("districtIdsList", districtIdsList);
		
		return query.list();
    }
	public Long getTotalVotersInALocationWiseCount(Long reportLevelId, Long publicationDateId,List<Long> assemblyIdsList)
	{
		Query query = getSession().createQuery("select sum(model.totalVoters) from VoterInfo model " +
				" where model.voterReportLevel.voterReportLevelId = :reportLevelId and " +
				" model.reportLevelValue in(:assemblyIdsList) and " +
				" model.publicationDate.publicationDateId = :publicationDateId ");
		
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameterList("assemblyIdsList", assemblyIdsList);
		
		return (Long)query.uniqueResult();
    }
	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalVotersForlocationWiseData(Long locationScopeId,Long locationValue,boolean isLocationBodyId)
	{
		Long reportlevelId =0l;
		if(locationScopeId != null && locationScopeId == 2l || locationScopeId == 3l ||  locationScopeId == 4l ||  locationScopeId == 10l){
			reportlevelId = 1l;
		}else if(locationScopeId != null && locationScopeId == 5l){
			reportlevelId = 2l;
		}else if(locationScopeId != null && locationScopeId == 6l){
			reportlevelId = 3l;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		if(locationScopeId != null && locationScopeId == 2l){
			sb.append("C.district_id as locationId,sum(VI.total_voters) as count from voter_info VI,constituency C,district D " +
				" where VI.report_level_value=C.constituency_id and D.district_id=C.district_id and (C.district_id between 11 and 23) AND VI.report_level_id=1 " +
				" and VI.publication_date_id=22 group by C.district_id ");
		}else if(locationScopeId != null && locationScopeId == 3l ){
			sb.append(" C.constituency_id as locationId, sum(VI.total_voters) as count from voter_info VI,constituency C" +
					" where VI.report_level_value=C.constituency_id and C.district_id =:locationValue " +
					" AND VI.report_level_id =1 and VI.publication_date_id=22 " +
					" group by C.constituency_id ");
		}else if(locationScopeId != null && locationScopeId == 4l){
			if(!isLocationBodyId){
				sb.append("  t.tehsil_id as locationId ,sum(VI.total_voters) as count " +
						" from voter_info VI,constituency C,tehsil_constituency tc,tehsil t " +
						" where VI.report_level_value=t.tehsil_id and tc.constituency_id=C.constituency_id and tc.tehsil_id=t.tehsil_id and " +
						" C.constituency_id=:locationValue " +
						" and VI.publication_date_id=22 " +
						" AND VI.report_level_id =2 " +
						" group by t.tehsil_id ");
			}else{
				sb.append(" leb.local_election_body_id as locationId,sum(VI.total_voters) as count  from voter_info VI, constituency C, tehsil_constituency tc," +
						" tehsil t,local_election_body leb where" +
						" VI.report_level_value=leb.local_election_body_id and tc.constituency_id=C.constituency_id and  tc.tehsil_id=t.tehsil_id and" +
						" leb.tehsil_id=t.tehsil_id and C.constituency_id =:locationValue  and VI.publication_date_id=22 AND  VI.report_level_id =5 group by leb.local_election_body_id ");
			}
			
		}else if(locationScopeId != null && locationScopeId == 5l){
			sb.append(" p.panchayat_id  as locationId,sum(vi.total_voters) as count" +
					" from voter_info vi, tehsil t,panchayat p where " +
					" vi.report_level_value=p.panchayat_id and p.tehsil_id=t.tehsil_id" +
					" and t.tehsil_id=:locationValue and vi.report_level_id=3 " +
					" group by p.panchayat_id ");
		}else if(locationScopeId != null && locationScopeId == 6l){
			sb.append(" p.panchayat_id  as locationId,sum(vi.total_voters) as count" +
					" from voter_info vi,panchayat p where " +
					" vi.report_level_value=p.panchayat_id " +
					" and p.panchayat_id=:locationValue and vi.report_level_id=3 " +
					" group by p.panchayat_id ");
		}else if(locationScopeId != null && locationScopeId == 10l ){
			sb.append(" C.constituency_id as locationId, sum(VI.total_voters) as count from voter_info VI,constituency C" +
					" where VI.report_level_value=C.constituency_id and C.constituency_id in  (select distinct assembly_id from parliament_assembly where parliament_id=:locationValue) " +
					" AND VI.report_level_id =1 and VI.publication_date_id=22 " +
					" group by C.constituency_id ");
		}else if(locationScopeId == 7l){
				sb.append(" leb.local_election_body_id as locationId,sum(VI.total_voters) as count " +
						" from voter_info VI, constituency C, tehsil_constituency tc," +
						" tehsil t,local_election_body leb where VI.report_level_value=leb.local_election_body_id and tc.constituency_id=C.constituency_id and " +
						" tc.tehsil_id=t.tehsil_id and leb.tehsil_id=t.tehsil_id and C.constituency_id=:locationValue " +
						" and VI.publication_date_id=22 AND " +
						" VI.report_level_id =6 " +
						" group by leb.local_election_body_id  ");
		}
				
		 Query query = getSession().createSQLQuery(sb.toString()).
				 addScalar("locationId",Hibernate.LONG)
				 .addScalar("count", Hibernate.LONG);
		 if(locationScopeId != null && locationScopeId.longValue()>0 && locationScopeId !=2l){
			 query.setParameter("locationValue",locationValue);
		  }
		return query.list();
    }
	
}
