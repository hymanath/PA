package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.model.VoterCastInfo;
import com.itgrids.partyanalyst.utils.IConstants;

public class VoterCastInfoDAO extends GenericDaoHibernate<VoterCastInfo,Long> implements IVoterCastInfoDAO{

	public VoterCastInfoDAO()
	{
		super(VoterCastInfo.class);
	}
	
	
	public Integer deleteVotersCastInfoByReportLevelValue(Long reportLevelValue, Long publicationDateId,Long userId)
	{
		Query query = getSession().createQuery("delete from VoterCastInfo model where model.constituency.constituencyId=:reportLevelValue and model.publicationDateId = :publicationDateId and model.userId = :userId");
		
		query.setParameter("reportLevelValue", reportLevelValue);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("userId", userId);
		return query.executeUpdate();	
	}
	
    public List<VoterCastInfo>  getVotersCastInfo(Long levelId,Long levelValue,Long constituencyId,Long publicationId,Long userId){
		
		Query query = getSession().createQuery("from VoterCastInfo model where model.voterReportLevel.voterReportLevelId = :levelId and " +
				"model.reportLevelValue = :levelValue and model.constituency.constituencyId = :constituencyId and model.publicationDateId = :publicationId " +
				" and model.userId = :userId order by model.casteVoters desc");
		
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		
		return query.list();
	}
	
	public Long getRecordsCountToCheckDataPresent(Long constituencyId){
		Query query = getSession().createQuery("select count(*) from VoterCastInfo model where model.constituency.constituencyId = :constituencyId ");
		query.setParameter("constituencyId", constituencyId);
		return (Long)query.uniqueResult();
	}
	
    public List<VoterCastInfo> getVotersCastInfoByMultipleLevelValues(Long levelId,Set<Long> levelValues,Long constituencyId,Long publicationId,Long userId){
		
		Query query = getSession().createQuery("from VoterCastInfo model where model.voterReportLevel.voterReportLevelId = :levelId and " +
				"model.reportLevelValue in (:levelValues) and model.constituency.constituencyId = :constituencyId and model.publicationDateId = :publicationId " +
				" and model.userId = :userId ");
		
		query.setParameter("levelId", levelId);
		query.setParameterList("levelValues", levelValues);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		
		return query.list();
	}
    
    public void saveAllObjects(List<VoterCastInfo> voterCastInfos){
    	getHibernateTemplate().saveOrUpdateAll(voterCastInfos);
    }
    
     public Long  getVotersCastCount(Long levelId,Long levelValue,Long constituencyId,Long publicationId,Long userId){
		
		Query query = getSession().createQuery("select sum(model.casteVoters) from VoterCastInfo model where model.voterReportLevel.voterReportLevelId = :levelId and " +
				"model.reportLevelValue = :levelValue and model.constituency.constituencyId = :constituencyId and model.publicationDateId = :publicationId " +
				" and model.userId = :userId ");
		
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		
		return (Long)query.uniqueResult();
	}
     
     public void flushAndclearSession(){
			getSession().flush();
			getSession().clear();
		}
    
     public List<VoterCastInfo> getVotersCastInfoByMultipleLevelValuesAndCastIds(Long levelId,Set<Long> levelValues,List<Long> castStateIds,Long constituencyId,Long publicationId,Long userId){
 		
 		Query query = getSession().createQuery("from VoterCastInfo model where model.voterReportLevel.voterReportLevelId = :levelId and " +
 				"model.reportLevelValue in (:levelValues) and model.constituency.constituencyId = :constituencyId and model.publicationDateId = :publicationId " +
 				" and model.userId = :userId and model.casteState.casteStateId in(:castStateIds) ");
 		
 		query.setParameter("levelId", levelId);
 		query.setParameterList("levelValues", levelValues);
 		query.setParameter("constituencyId", constituencyId);
 		query.setParameter("publicationId", publicationId);
 		query.setParameter("userId", userId);
 		query.setParameterList("castStateIds", castStateIds);
 		
 		return query.list();
 	}
     
     public List<Object[]> getCastAndPartyForSelectedLevel(Long userId,Long reportId ,List<Long> ids,Long publicationId)
     {
    	 Query query = getSession().createQuery("select distinct(model.casteState.casteStateId) , model.casteState.caste.casteName " +
    	 		" from VoterCastInfo model where model.userId = :userId and model.voterReportLevel.voterReportLevelId = :reportId " +
    	 		" and model.reportLevelValue in (:ids) and model.publicationDateId = :publicationId order by model.casteState.caste.casteName " );
    	 query.setParameter("userId", userId);
    	 query.setParameter("reportId", reportId);
    	 query.setParameterList("ids", ids);
    	 query.setParameter("publicationId", publicationId);
    	 return query.list();
     }
     
     @SuppressWarnings("unchecked")
	public List<Object[]> getAllCasteInfoByUserId(Long userId)
     {
    	 Query query =getSession().createQuery("select distinct(model.casteState.casteStateId) , model.casteState.caste.casteName " +
    	 		" from VoterCastInfo model where model.userId = :userId  " );
    	 query.setParameter("userId", userId);
    	 return query.list();
     }
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTopThreeCasteFoeSelctedLevel(Long id,Long reportId,Long publicationId,Long userId)
	{
		Query query = getSession().createQuery("select model.reportLevelValue, model.casteState.caste.casteName,model.casteVoters,model.castePercentage ,model.casteState.casteStateId" +
    	 		" from VoterCastInfo model where model.userId = :userId and model.voterReportLevel.voterReportLevelId = :reportId " +
    	 		" and model.reportLevelValue = :id and model.publicationDateId = :publicationId " +
    	 		" order by model.reportLevelValue,model.casteVoters desc  ");
		query.setParameter("reportId", reportId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		query.setParameter("id", id);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTopCasteFoeSelctedLevel(List<Long> ids,Long reportId,Long publicationId,Long userId,Set<Long> casteIds)
	{
		Query query = getSession().createQuery("select  model.casteState.casteStateId,model.casteState.caste.casteName,model.casteVoters,model.reportLevelValue" +
    	 		" from VoterCastInfo model where model.userId = :userId and model.voterReportLevel.voterReportLevelId = :reportId " +
    	 		" and model.reportLevelValue in (:ids) and model.publicationDateId = :publicationId and " +
    	 		" model.casteState.casteStateId in (:casteIds) group by  model.casteState.casteStateId,model.reportLevelValue" +
    	 		" order by model.reportLevelValue,model.casteVoters desc  ");
		query.setParameter("reportId", reportId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		query.setParameterList("ids", ids);
		query.setParameterList("casteIds", casteIds);
		return query.list();
	}
	
	public List<Object[]> getTopThreeCasteForPanchayat(Long panchayatId,Long reportId,Long publicationId,Long userId){
		Query query = getSession().createQuery("select model.casteState.caste.casteName,model.casteVoters,model.castePercentage,model.casteState.casteStateId from VoterCastInfo model where model.voterReportLevel.voterReportLevelId = :reportId "+
				"and model.userId = :userId and model.reportLevelValue = :panchayatId and model.publicationDateId = :publicationId " +
    	 		" order by model.reportLevelValue,model.casteVoters desc ");
		query.setParameter("reportId", reportId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("panchayatId",panchayatId);
		query.setParameter("userId", userId);

		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<VoterCastInfo> getVoterCasteInfoList(Long constituencyId,Long publicationDateId,Long userId)
	{
		Query query = getSession().createQuery(" from VoterCastInfo model where model.constituency.constituencyId =:constituencyId and model.publicationDateId = :publicationDateId and model.userId =:userId ");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<Object[]> getCasteWiseCountDetails(Long levelId,Long levelValue,Long publicationDateId,Long constituencyId,List<Long> casteIds,Long userId)
	{
		Query query = getSession().createQuery("select model.casteState.casteStateId,model.casteVoters from VoterCastInfo model " +
				" where model.voterReportLevel.voterReportLevelId = :levelId and " +
				" model.reportLevelValue = :levelValue and model.publicationDateId = :publicationDateId and " +
				" model.constituency.constituencyId = :constituencyId  and model.userId = :userId and " +
				" model.casteState.casteStateId in (:casteIds)");
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("casteIds", casteIds);
		query.setParameter("userId", userId);
		return query.list();
				
	}
	
	public List<Object[]> getCasteAvaliableConstituencyes(List<Long> constituencyIds,Long userId)
	{
		Query query = getSession().createQuery("select distinct model.constituency.constituencyId,model.constituency.name " +
				" from  VoterCastInfo model where model.userId = :userId and " +
				" model.constituency.constituencyId in (:constituencyIds)");
		query.setParameter("userId", userId);
		query.setParameterList("constituencyIds", constituencyIds);
		
		return query.list();
	}
	
	/*public List<Object[]> getPanchayatWiseCasteForSuggestiveModel(Long constituencyId,Long publicationId,Long levelId,Long userId)
	{
		Query query = getSession().createQuery("select model.reportLevelValue , " +//0
				" model.casteState.casteStateId , " +//1
				" model.casteState.caste.casteName," +//2
				" model.casteVoters  " +//3
				" from VoterCastInfo model where " +
				" model.constituency.constituencyId = :constituencyId and " +
				" model.publicationDateId = :publicationId and " +
				" model.userId = :userId and " +
				" model.voterReportLevel.voterReportLevelId = :levelId " +
				" order by model.reportLevelValue ");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("levelId", levelId);
		query.setParameter("userId", userId);
		return query.list();
	}*/
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterCasteInfoListByConstituency(Long constituencyId,Long publicationDateId,Long userId)
	{
		Long reportLevelValue = constituencyId;
		Query query = getSession().createQuery(" select model.casteState.caste.casteId, model.casteState.caste.casteName, model.casteState.casteCategoryGroup.casteCategory.casteCategoryId, " +
				" model.casteState.casteCategoryGroup.casteCategory.categoryName,model.casteVoters,model.casteMaleVoters, " +
				"model.casteFemaleVoters,model.castePercentage,model.casteState.casteStateId from VoterCastInfo model where model.voterReportLevel = 1 and model.reportLevelValue =:reportLevelValue and model.constituency.constituencyId =:constituencyId and model.publicationDateId = :publicationDateId and model.userId =:userId order by  model.casteVoters desc, model.casteState.caste.casteName asc");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("reportLevelValue", reportLevelValue);
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameter("userId", userId);
		
		return query.list();
	}
		
  public List<Object[]>  getVotersCastInfoByCasteIds(List<Long> levelIds,Long constituencyId,Long publicationId,Long userId,List<Long> casteStateIds,Set<Long> locationIds){
		
		Query query = getSession().createQuery("select model.reportLevelValue,model.casteState.casteStateId,model.casteVoters from VoterCastInfo model where model.voterReportLevel.voterReportLevelId in( :levelIds) and " +
				" model.constituency.constituencyId = :constituencyId and model.publicationDateId = :publicationId and model.casteState.casteStateId in(:casteStateIds)  and model.reportLevelValue in(:locationIds) " +
				" and model.userId = :userId ");
		
		query.setParameterList("levelIds", levelIds);
		query.setParameterList("casteStateIds", casteStateIds);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		query.setParameterList("locationIds",locationIds);
		return query.list();
	}
  
  public List<Object[]> getVoterCadreDetailsBySearchCriteria(Long stateId, String locationType,List<Long> locationIdsList,Long casteStateId)
	{
		  if(locationType != null)
		  {
			  StringBuilder queryStr = new StringBuilder();
				boolean isStateWise = false;
				StringBuilder str  = new StringBuilder();
				//str.append(" select distinct model.reportLevelValue,model.totalVoters  ");
				
				if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
				{
					str.append(" select distinct model2.constituencyId,model.casteVoters  ");
					str.append(" ,model2.name from VoterCastInfo model,Constituency model2 where model2.constituencyId = model.reportLevelValue and model.constituency.constituencyId = model2.constituencyId ");
					str.append(" and model.voterReportLevel.voterReportLevelId = 1 and model.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" ");
					if(locationIdsList != null && locationIdsList.size() > 0)
					{
						str.append(" and model2.district.districtId in (:locationIdsList) ");
					}
					if(casteStateId != null && casteStateId.longValue() >0)
					{
						str.append(" and model.casteState.casteStateId = :casteStateId ");
					}
					str.append(" group by model2.constituencyId order by model2.name ");
				}		
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
				{
					str.append(" select distinct model2.tehsilId,model.casteVoters  ");
					str.append(" ,model2.tehsilName from VoterCastInfo model,Tehsil model2,Booth B where model2.tehsilId = model.reportLevelValue  and model2.tehsilId = B.tehsil.tehsilId ");
					str.append(" and B.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and B.constituency.constituencyId in (:locationIdsList) and B.localBody.localElectionBodyId is null " +
							" and  model.voterReportLevel.voterReportLevelId = 2 ");
					if(casteStateId != null && casteStateId.longValue() >0)
					{
						str.append(" and model.casteState.casteStateId = :casteStateId ");
					}
					str.append(" group by model2.tehsilId order by model2.tehsilName ");
				}
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
				{
					str.append(" select distinct model2.panchayatId,model.casteVoters  ");
					str.append(" ,model2.panchayatName from VoterCastInfo model,Panchayat model2,Booth B where model2.panchayatId = model.reportLevelValue and model2.panchayatId = B.panchayat.panchayatId ");
					str.append(" and B.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and B.tehsil.tehsilId in (:locationIdsList) ");
					if(casteStateId != null && casteStateId.longValue() >0)
					{
						str.append(" and model.casteState.casteStateId = :casteStateId ");
					}
					str.append("  and  model.voterReportLevel.voterReportLevelId = 3 group by model2.panchayatId order by model2.panchayatName ");
				}
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
				{
					str.append(" select distinct model2.localElectionBodyId,model.casteVoters  ");
					str.append(" ,model2.name from VoterCastInfo model,LocalElectionBody model2,Booth B where model2.localElectionBodyId = model.reportLevelValue and model2.localElectionBodyId = B.localBody.localElectionBodyId ");
					str.append(" and B.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and B.localBody.localElectionBodyId in (:locationIdsList) and B.localBody.localElectionBodyId is not null ");
					if(casteStateId != null && casteStateId.longValue() >0)
					{
						str.append(" and model.casteState.casteStateId = :casteStateId ");
					}
					str.append("  and  model.voterReportLevel.voterReportLevelId = 5 group by model2.localElectionBodyId order by model2.name ");
				}
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
				{
					str.append(" select distinct model2.constituencyId,model.casteVoters  ");
					str.append(" ,model2.name from VoterCastInfo model,Constituency model2,Booth B where model2.constituencyId = B.constituency.constituencyId and model2.constituencyId = model.reportLevelValue and model2.localElectionBody.localElectionBodyId is not null ");
					str.append(" and model.reportLevelValue in (:locationIdsList)  and model.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" ");			
					str.append("  and  model.voterReportLevel.voterReportLevelId = 6 group by model2.constituencyId order by model2.name ");
				}
				else if(stateId != null && stateId.longValue() == 0L) //AP & TS
				{
					isStateWise = true;
					str.append(" select model2.district.districtId,sum(model.casteVoters)  ");
					str.append(" ,model2.district.districtName from VoterCastInfo model,Constituency model2 where  model.voterReportLevel.voterReportLevelId = 1 and " +
							" model2.constituencyId = model.reportLevelValue and ( model2.district.districtId between 1 and 23)  and model.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" ");
					if(casteStateId != null && casteStateId.longValue() >0)
					{
						str.append(" and model.casteState.casteStateId = :casteStateId ");
					}
					str.append(" group by  model2.district.districtId order by  model2.district.districtName ");
				}
				else if(stateId != null && stateId.longValue() == 1L) //AP
				{
					isStateWise = true;
					str.append(" select model2.district.districtId,sum(model.casteVoters)  ");
					str.append(" ,model2.district.districtName from VoterCastInfo model,Constituency model2 where  model.voterReportLevel.voterReportLevelId = 1 and " +
							" model2.constituencyId = model.reportLevelValue and ( model2.district.districtId between 11 and 23)  and model.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" ");
					if(casteStateId != null && casteStateId.longValue() >0)
					{
						str.append(" and model.casteState.casteStateId = :casteStateId ");
					}
					str.append(" group by  model2.district.districtId order by  model2.district.districtName ");
				}
				else if(stateId != null && stateId.longValue() == 2L) //TS
				{
					isStateWise = true;
					str.append(" select model2.district.districtId,sum(model.casteVoters)  ");
					str.append(" ,model2.district.districtName from VoterCastInfo model,Constituency model2 where  model.voterReportLevel.voterReportLevelId = 1 and " +
							" model2.constituencyId = model.reportLevelValue and ( model2.district.districtId between 1 and 10)  and model.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+"  ");
					if(casteStateId != null && casteStateId.longValue() >0)
					{
						str.append(" and model.casteState.casteStateId = :casteStateId ");
					}
					str.append(" group by  model2.district.districtId order by  model2.district.districtName ");
				}		
				
				queryStr.append(str.toString());
				
				Query query = getSession().createQuery(queryStr.toString());
				query.setParameter("casteStateId", casteStateId);
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
  
  public List<Object[]> getCasteWiseVoterDetailsBySearchCriteria(Long stateId, String locationType,List<Long> locationIdsList,Long casteStateId)
  {
	  if(locationType != null)
	  {
		  StringBuilder queryStr = new StringBuilder();
			boolean isStateWise = false;
			StringBuilder str  = new StringBuilder();
			str.append(" select distinct model.casteState.caste.casteName, model.casteState.casteStateId, sum(model.casteVoters)  ");
			if(locationIdsList != null && locationIdsList.size() > 0 && locationIdsList.get(0) != 0L && locationType != null && locationType.equalsIgnoreCase(IConstants.STATE))
			{
				str.append(" from VoterCastInfo model,Constituency model2 where model2.constituencyId = model.reportLevelValue and model.constituency.constituencyId = model2.constituencyId ");
				str.append(" and model.voterReportLevel.voterReportLevelId = 1 and model.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" ");
				if(locationIdsList != null && locationIdsList.size() > 0)
				{
					str.append(" and model2.district.districtId in (:locationIdsList) ");
				}
				if(casteStateId != null && casteStateId.longValue() >0)
				{
					str.append(" and model.casteState.casteStateId = :casteStateId ");
				}
				str.append(" group by model.casteState.casteStateId order by sum(model.casteVoters) desc ");
			}		
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			{
				str.append(" from VoterCastInfo model,Constituency model2 where model2.constituencyId = model.reportLevelValue and model.constituency.constituencyId = model2.constituencyId ");
				str.append(" and model.voterReportLevel.voterReportLevelId = 1 and model.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" ");
				if(locationIdsList != null && locationIdsList.size() > 0)
				{
					str.append(" and model2.constituencyId in (:locationIdsList) ");
				}
				if(casteStateId != null && casteStateId.longValue() >0)
				{
					str.append(" and model.casteState.casteStateId = :casteStateId ");
				}
				str.append(" group by model.casteState.casteStateId order by sum(model.casteVoters) desc ");
			}		
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
			{
				str.append(" from VoterCastInfo model,Tehsil model2 where model.reportLevelValue = model2.tehsilId  ");
				str.append(" and model.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and model.reportLevelValue in (:locationIdsList) " +
						" and  model.voterReportLevel.voterReportLevelId = 2 ");
				if(casteStateId != null && casteStateId.longValue() >0)
				{
					str.append(" and model.casteState.casteStateId = :casteStateId ");
				}
				str.append(" group by model.casteState.casteStateId order by sum(model.casteVoters) desc ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			{
				str.append(" from VoterCastInfo model,Panchayat model2 where model.reportLevelValue = model2.panchayatId  ");
				str.append(" and model.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and model.reportLevelValue in (:locationIdsList) ");
				if(casteStateId != null && casteStateId.longValue() >0)
				{
					str.append(" and model.casteState.casteStateId = :casteStateId ");
				}
				str.append(" and model.voterReportLevel.voterReportLevelId = 3  ");
				str.append(" group by model.casteState.casteStateId order by sum(model.casteVoters) desc ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			{
				str.append("  from VoterCastInfo model,LocalElectionBody model2 where model2.localElectionBodyId = model.reportLevelValue  ");
				str.append(" and model.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and model.reportLevelValue in (:locationIdsList) " );
				if(casteStateId != null && casteStateId.longValue() >0)
				{
					str.append(" and model.casteState.casteStateId = :casteStateId ");
				}
				str.append("  and  model.voterReportLevel.voterReportLevelId = 5  ");
				str.append(" group by model.casteState.casteStateId order by sum(model.casteVoters) desc ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
			{
				str.append("  from VoterCastInfo model,LocalElectionBody model2 where model2.localElectionBodyId = model.reportLevelValue  ");
				str.append(" and model.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and model.reportLevelValue in (:locationIdsList) " );
				if(casteStateId != null && casteStateId.longValue() >0)
				{
					str.append(" and model.casteState.casteStateId = :casteStateId ");
				}
				str.append("  and  model.voterReportLevel.voterReportLevelId = 5  ");
				str.append(" group by model.casteState.casteStateId order by sum(model.casteVoters) desc ");
			}
			else if(stateId != null && stateId.longValue() == 0L) //AP & TS
			{
				isStateWise = true;
				str.append(" from VoterCastInfo model,Constituency model2 where  model.voterReportLevel.voterReportLevelId = 1 and " +
						" model2.constituencyId = model.reportLevelValue and ( model2.district.districtId between 1 and 23)  and model.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" ");
				if(casteStateId != null && casteStateId.longValue() >0)
				{
					str.append(" and model.casteState.casteStateId = :casteStateId ");
				}
				str.append(" group by model.casteState.casteStateId order by sum(model.casteVoters) desc ");
			}
			else if(stateId != null && stateId.longValue() == 1L) //AP
			{
				isStateWise = true;
				str.append("  from VoterCastInfo model,Constituency model2 where  model.voterReportLevel.voterReportLevelId = 1 and " +
						" model2.constituencyId = model.reportLevelValue and ( model2.district.districtId between 11 and 23)  and model.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" ");
				if(casteStateId != null && casteStateId.longValue() >0)
				{
					str.append(" and model.casteState.casteStateId = :casteStateId ");
				}
				str.append(" group by model.casteState.casteStateId order by sum(model.casteVoters) desc ");
			}
			else if(stateId != null && stateId.longValue() == 2L) //TS
			{
				isStateWise = true;
				str.append("  from VoterCastInfo model,Constituency model2 where  model.voterReportLevel.voterReportLevelId = 1 and " +
						" model2.constituencyId = model.reportLevelValue and ( model2.district.districtId between 1 and 10)  and model.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+"  ");
				if(casteStateId != null && casteStateId.longValue() >0)
				{
					str.append(" and model.casteState.casteStateId = :casteStateId ");
				}
				str.append(" group by model.casteState.casteStateId order by sum(model.casteVoters) desc ");
			}		
			
			queryStr.append(str.toString());
			
			Query query = getSession().createQuery(queryStr.toString());
			
			if(casteStateId != null && casteStateId.longValue() >0)
			{
				query.setParameter("casteStateId", casteStateId);
			}
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
  
  @SuppressWarnings("unchecked")
  public List<Object[]> getVotersCastInfoForParliament(Long levelId,List<Long> assemblyIdsList,Long publicationId,Long userId)
  {
		
	  Query query = getSession().createQuery("SELECT model.casteState.casteStateId," +
		" model.casteState.caste.casteName," +
		" model.casteState.casteCategoryGroup.casteCategory.categoryName," +
		" sum(model.casteVoters) ," +
		" sum(casteMaleVoters)," +
		" sum(casteFemaleVoters)," +
		" sum(casteMaleVoters) " +
		" from VoterCastInfo model where model.voterReportLevel.voterReportLevelId = :levelId and " +
		" model.reportLevelValue in(:assemblyIdsList) and model.publicationDateId = :publicationId " +
		" and model.userId = :userId group by model.casteState.caste.casteId order by sum(model.casteVoters) desc ");
	  
		query.setParameter("levelId", levelId);
		query.setParameterList("assemblyIdsList", assemblyIdsList);
		query.setParameter("publicationId", publicationId);
		query.setParameter("userId", userId);
		
		return query.list();
	}
  
  public List<Object[]> getVotersCasteWiseCount(List<Long> constituencyIds,Long publicationDateId){
	  Query query = getSession().createQuery(" select model.casteState.casteCategoryGroup.casteCategory.casteCategoryId,model.casteState.casteCategoryGroup.casteCategory.categoryName, " +
	  		" model.casteState.caste.casteId,model.casteState.caste.casteName,model.casteVoters,model.castePercentage,model.casteMaleVoters,model.casteFemaleVoters " +
	  		" from VoterCastInfo model " +
	  		" where model.voterReportLevel.voterReportLevelId = :levelValue " +
	  		" and model.reportLevelValue in (:constituencyId) " +
	  		" and model.publicationDateId = :publicationDateId " +
	  		" and model.casteState.state.stateId = :stateId " +
	  		" order by model.casteState.caste.casteId ");
	  
	  query.setParameterList("constituencyId", constituencyIds);
	  query.setParameter("publicationDateId", publicationDateId);
	  query.setParameter("levelValue", 1l);
	  query.setParameter("stateId", 1l);
	  
	  return query.list();
  }
  
  public List<Object[]> getVotersCastGroupWiseCount(List<Long> constituencyIds, Long PublicationDateId){
	  
	  StringBuilder sb = new StringBuilder();
	  sb.append("select cc.caste_category_id as castCategoryId,cc.category_name as castName,sum(vci.caste_voters) as totalVoters from voter_cast_info vci join caste_state " +
	  		" cs on vci.caste_state_id=cs.caste_state_id join caste_category_group ccg on " +
	  		" cs.caste_category_group_id =ccg.caste_category_group_id join caste_category cc on " +
	  		" cc.caste_category_id=ccg.caste_category_id where " +
	  		" vci.report_level_id = 1 and vci.report_level_value in (:constituencyIds)  and publication_date_id =:publicationDateId" +
	  		" group by cc.caste_category_id ");
	  Query query = getSession().createSQLQuery(sb.toString())
			  .addScalar("castCategoryId",Hibernate.LONG).addScalar("castName",Hibernate.STRING).addScalar("totalVoters",Hibernate.LONG);
	  
	  query.setParameterList("constituencyIds", constituencyIds);
	  query.setParameter("publicationDateId", PublicationDateId);
	  return query.list();
	  
  }
  
}
