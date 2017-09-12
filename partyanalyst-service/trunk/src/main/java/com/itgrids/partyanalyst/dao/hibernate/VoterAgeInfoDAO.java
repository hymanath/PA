package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.model.VoterAgeInfo;
import com.itgrids.partyanalyst.utils.IConstants;

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
	
	
	public Integer deleteVoterAgeInfoByConstituencyId(Long constituencyId, Long publicationDateId)
	{
		Query query = getSession().createQuery(" delete from VoterAgeInfo model where model.constituencyId=:constituencyId and model.publicationDate.publicationDateId = :publicationDateId ");
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("constituencyId", constituencyId);
		
		return  query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getVoterAgeInfoByConstituencyId(Long constituencyId, Long publicationDateId,Long voterReportLevel)
	{
		Query query = getSession().createQuery("select distinct model.reportLevelValue from VoterAgeInfo model where model.constituencyId=:constituencyId and " +
				" model.publicationDate.publicationDateId = :publicationDateId and model.voterReportLevel.voterReportLevelId =:reportLevelId ");
		
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("reportLevelId", voterReportLevel);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterAgeDetails(Long constituencyId, Long publicationDateId,Long voterReportLevel)
	{
		Query query = getSession().createQuery("select model.reportLevelValue,count(model.voterAgeRange.voterAgeRangeId) from VoterAgeInfo model where model.constituencyId=:constituencyId and " +
				" model.publicationDate.publicationDateId = :publicationDateId and model.voterReportLevel.voterReportLevelId =:reportLevelId group by model.reportLevelValue ");
		
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("reportLevelId", voterReportLevel);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<VoterAgeInfo> getVoterAgeInfoList(Long constituencyId)
	{
		Query query = getSession().createQuery(" from VoterAgeInfo model where model.constituencyId=:constituencyId ");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getPanchayatWiseVoterDetailsForSuggestiveModel(Long constituencyId,Long publicationId,Long reportLevelId,Long ageRangeId)
	{
		Query query = getSession().createQuery("select model.reportLevelValue," +//0
				" model.totalVoters, " +//1
				" model.maleVoters , " +//2
				" model.femaleVoters ," +//3
				" model.totalVotersPercentage " +//4
				" from VoterAgeInfo model where " +
				" model.constituencyId=:constituencyId  and" +
				" model.publicationDate.publicationDateId = :publicationId " +
				" and model.voterReportLevel.voterReportLevelId = :reportLevelId and " +
				" model.voterAgeRange.voterAgeRangeId = :ageRangeId order by model.reportLevelValue");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameter("ageRangeId", ageRangeId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterAgeInfoListByconstituency(Long constituencyId,Long publicationDateId)
	{
		Long reportLevelValue = constituencyId;
		
		Query query = getSession().createQuery(" select model.voterAgeRange.voterAgeRangeId,model.voterAgeRange.ageRange, sum(model.totalVoters), " +
				" sum(model.maleVoters), sum(model.femaleVoters) from VoterAgeInfo model where model.constituencyId=:constituencyId and model.voterReportLevel = 1 and model.reportLevelValue=:reportLevelValue and model.publicationDate.publicationDateId = :publicationDateId " +
				" group by model.voterAgeRange.voterAgeRangeId order by model.voterAgeRange.voterAgeRangeId asc");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("reportLevelValue", reportLevelValue);
		query.setParameter("publicationDateId", publicationDateId);
		
		return query.list();
	}
	public List<Object[]> getTotalVotersBasedOnAConstituency(List<Long> constituencyIds,Long publicationDateId)
    {
    	Query  query = getSession().createQuery("select model1.voterAgeRange.voterAgeRangeId,model1.totalVoters,model2.name,model1.voterAgeRange.ageRange " +
    			" from VoterAgeInfo model1,Constituency model2 " +
    			" where model1.constituencyId=model2.constituencyId and" +
    			" model1.voterReportLevel.voterReportLevelId=1 and " +
    			" model1.publicationDate.publicationDateId=:publicationDateId and " +
    			" model2.constituencyId in(:constituencyIds) " +
    			" group by model2.name,model1.voterAgeRange.voterAgeRangeId " +
    			" having model1.voterAgeRange.voterAgeRangeId in (2,3,4,5,6)" +
    			" order by  model2.name");
    		query.setParameter("publicationDateId", publicationDateId);
    		query.setParameterList("constituencyIds", constituencyIds);
    	
    	 return query.list();
    }
   public List<Object[]> getGenderWiseVoterDetailsByConstituency(List<Long> constituencyIds,Long publicationDateId)
   {
	
	   Query query = getSession().createQuery("select sum(model1.maleVoters),sum(model1.femaleVoters),model2.name " +
	   		" from VoterAgeInfo model1,Constituency model2 " +
	   		" where model1.constituencyId=model2.constituencyId and " +
	   		" model1.voterReportLevel.voterReportLevelId=1 and " +
	   		" model1.publicationDate.publicationDateId=:publicationDateId and" +
	   		" model1.voterAgeRange.voterAgeRangeId!=1 and " +
	   		" model2.constituencyId in(:constituencyIds) " +
	   		" group by model1.constituencyId " +
	   		" order by model2.name");  
	   query.setParameter("publicationDateId", publicationDateId);
   	   query.setParameterList("constituencyIds", constituencyIds);
   	   return query.list();
   }
    
   public List<Object[]> getYouthVotersInfoForDistrict(List<Long> ids){
	   Query query = getSession().createQuery("select sum(model1.totalVoters),model2.district.districtId from VoterAgeInfo model1,Constituency model2 where model1.voterReportLevel.voterReportLevelId = 1 and" +
	   		" model1.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and model1.voterAgeRange.voterAgeRangeId = 7 and model1.reportLevelValue = model2.constituencyId and model2.district.districtId in(:ids) group by model2.district.districtId");
	     query.setParameterList("ids", ids);
	   return query.list();
   }
   public List<Object[]> getYouthVotersInfo(Long constiId,List<Long> ids,Long reportLevel){
	   StringBuilder queryStr = new StringBuilder();  
	   queryStr.append("select model.totalVoters,model.reportLevelValue from VoterAgeInfo model where model.voterReportLevel.voterReportLevelId = :reportLevel and" +
	   		" model.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and model.voterAgeRange.voterAgeRangeId = 7 and model.reportLevelValue in(:ids) ");
	   if(constiId != null && constiId.longValue() > 0){
		   queryStr.append(" and model.constituencyId =:constiId");
	   }
	   Query query = getSession().createQuery(queryStr.toString());
	     query.setParameterList("ids", ids);
	     query.setParameter("reportLevel", reportLevel);
	     if(constiId != null && constiId.longValue() > 0){
	    	 query.setParameter("constiId",constiId);
		   }
	   return query.list();
   }
   
   @SuppressWarnings("unchecked")
	public List<Object[]> getVoterAgeInfoListByconstituencyExceptYouth(Long constituencyId,Long publicationDateId)
	{
		Long reportLevelValue = constituencyId;
		
		Query query = getSession().createQuery(" select model.voterAgeRange.voterAgeRangeId,model.voterAgeRange.ageRange, sum(model.totalVoters), " +
				" sum(model.maleVoters), sum(model.femaleVoters) from VoterAgeInfo model where model.constituencyId=:constituencyId and model.voterReportLevel = 1 and model.reportLevelValue=:reportLevelValue and model.publicationDate.publicationDateId = :publicationDateId " +
				" and model.voterAgeRange.voterAgeRangeId != 1 group by model.voterAgeRange.voterAgeRangeId order by model.voterAgeRange.voterAgeRangeId asc");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("reportLevelValue", reportLevelValue);
		query.setParameter("publicationDateId", publicationDateId);
		
		return query.list();
	}
	
	 @SuppressWarnings("unchecked")
		public List<Object[]> getVoterAgesInfoListByconstituencyExceptYouths(List<Long> districtList,String accessType, Long publicationDateId)
		{
			
			   StringBuilder queryStr = new StringBuilder();
			   queryStr.append(" select model.voterAgeRange.voterAgeRangeId,model.voterAgeRange.ageRange, sum(model.totalVoters), ");
			   queryStr.append(" sum(model.maleVoters), sum(model.femaleVoters) from VoterAgeInfo model,Constituency model2 where ");	
			   queryStr.append("  model.constituencyId = model2.constituencyId ");
			   
			   if(districtList != null && districtList.size()>0)
			   {
				   queryStr.append("  and model2.district.districtId in (:districtList)  ");
			   }
			   else  if(accessType != null && accessType.equalsIgnoreCase("AP"))
			   {
				   queryStr.append("  and model2.district.districtId between 11 and 23  ");
			   }
			   else  if(accessType != null && accessType.equalsIgnoreCase("TS"))
			   {
				   queryStr.append("  and model2.district.districtId between 1 and 10  ");
			   }
			   
			   queryStr.append(" and model.voterReportLevel = 1  and model.publicationDate.publicationDateId = :publicationDateId  ");
			   queryStr.append(" and model.voterAgeRange.voterAgeRangeId != 1 group by model.voterAgeRange.voterAgeRangeId ");
			   queryStr.append(" order by model.voterAgeRange.voterAgeRangeId asc  ");
			   
			Query query = getSession().createQuery(queryStr.toString());
			
			 if(districtList != null && districtList.size()>0)
			   {
				 query.setParameterList("districtList", districtList);
			   }
			
			query.setParameter("publicationDateId", publicationDateId);
			
			return query.list();
		}
		
		public List<Object[]> getVotersAgeWiseCount(List<Long> constituencyIds,Long publicationDateId){
			StringBuilder sb = new StringBuilder();
			
			sb.append("select model.voterAgeRange.voterAgeRangeId,model.voterAgeRange.ageRange," +
					" model.totalVoters,model.totalVotersPercentage, " +
					" model.maleVoters,model.maleVotersPercentage, " +
					" model.femaleVoters,model.femaleVotersPercentage " +
					" from VoterAgeInfo model " +
					" where model.publicationDate.publicationDateId=:publicationDateId " +
					" and model.voterReportLevel.voterReportLevelId = :levelValue ");
			if(constituencyIds !=null && constituencyIds.size()>0){
				sb.append("and model.reportLevelValue in (:constituencyId)");
			}
			sb.append(" and model.voterAgeRange.voterAgeRangeId between 1 and 6 " +
					" order by model.voterAgeRange.voterAgeRangeId ");
			Query query = getSession().createQuery(sb.toString());
					
			query.setParameter("publicationDateId", publicationDateId);
			if(constituencyIds !=null && constituencyIds.size()>0){
				query.setParameterList("constituencyId", constituencyIds);
			}
			query.setParameter("levelValue", 1l);//constituency
			return query.list();
		}
}
