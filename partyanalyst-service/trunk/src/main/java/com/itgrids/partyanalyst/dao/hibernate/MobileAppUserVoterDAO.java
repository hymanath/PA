package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMobileAppUserVoterDAO;
import com.itgrids.partyanalyst.model.MobileAppUserVoter;
import com.itgrids.partyanalyst.utils.IConstants;

public class MobileAppUserVoterDAO extends GenericDaoHibernate<MobileAppUserVoter, Long> implements IMobileAppUserVoterDAO{

	public MobileAppUserVoterDAO() {
		super(MobileAppUserVoter.class);
	}
	
	public List<Object[]> getUserStartEndTime(Long locationId, String locationType, Date fromDate, Date toDate,List<String> userType){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.mobileAppUser.mobileAppUserId," +
				" model.mobileAppUser.userName," +
				" model.mobileAppUser.mobileNo," +
				" model.mobileAppUser.email," +
				" model.mobileAppUser.uniqueCode," +
				" date(model.surveyTime)," +
				" min(model.surveyTime)," +
				" max(model.surveyTime), " +
				" model.mobileAppUser.name" +
				" from MobileAppUserVoter model" +
				" where" +
				" model.mobileAppUser.isDeleted='N'" +
				" and model.mobileAppUser.isEnabled='Y' ");
		if(fromDate != null && toDate != null)
			sb.append("  and ( date(model.surveyTime) between :fromDate and :toDate ) ");
		
		
		if(locationType.equalsIgnoreCase("Ward")){
			sb.append(" and model.wardId=:locationId");
		}
		if(!(userType.contains("All")))
			sb.append(" and model.mobileAppUser.type in (:userType) ");
		sb.append(" group by model.mobileAppUser.mobileAppUserId," +
				" date(model.surveyTime)");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("locationId", locationId);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(!(userType.contains("All")))
			query.setParameterList("userType", userType);
		return query.list();
		
	}
	
	public List<Object[]> getUserCollectedDetails(Long locationId, String locationType, Date fromDate, Date toDate,List<String> userType){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.mobileAppUser.mobileAppUserId," +
				" date(model.surveyTime)," +
				" count(distinct model.mobileNo)," +
				" count(model.voterId)," +
				" count(distinct model.voterId)" +
				" from MobileAppUserVoter model" +
				" where" +
				" model.mobileAppUser.isDeleted='N'" +
				" and model.mobileAppUser.isEnabled='Y'");
		if(fromDate != null && toDate != null)
			sb.append(" and ( date(model.surveyTime) between :fromDate and :toDate ) ");
		
		if(locationType.equalsIgnoreCase("Ward")){
			sb.append(" and model.wardId=:locationId");
		}
		if(!(userType.contains("All")))
			sb.append(" and model.mobileAppUser.type in (:userType) ");
		sb.append(" group by model.mobileAppUser.mobileAppUserId," +
				" date(model.surveyTime)");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("locationId", locationId);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(!(userType.contains("All")))
			query.setParameterList("userType", userType);
		return query.list();
	}
	
	public List<Object[]> getUserCollectedRatingDetails(Long locationId, String locationType, Date fromDate, Date toDate,List<String> userType){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.mobileAppUser.mobileAppUserId," +
				" date(model.surveyTime)," +
				" model.rating," +
				" count(model.rating)" +
				" from MobileAppUserVoter model" +
				" where" +
				" model.mobileAppUser.isDeleted='N'" +
				" and model.mobileAppUser.isEnabled='Y' " );
		if(fromDate != null && toDate != null)
			sb.append(" and (date(model.surveyTime) between :fromDate and :toDate) ");
		
		if(locationType.equalsIgnoreCase("Ward")){
			sb.append(" and model.wardId=:locationId");
		}
		if(!(userType.contains("All")))
			sb.append(" and model.mobileAppUser.type in (:userType) ");
		sb.append(" group by model.mobileAppUser.mobileAppUserId," +
				" date(model.surveyTime)," +
				" model.rating");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("locationId", locationId);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(!(userType.contains("All")))
		query.setParameterList("userType", userType);
		return query.list();
	}
	public List<Object[]> locationWiseOverView(Date StartDate,Date endDate,List<Long> locationIds,String locationType,List<String> userTypes){
	
		
		StringBuilder sb=new StringBuilder();
		
		if(locationType.equalsIgnoreCase("ward")){
			
			sb.append(" select  uv.wardId,c.name,gmc.divisionName," +
					"           date(uv.surveyTime)," +
					"           count(distinct uv.mobileAppUser.mobileAppUserId),count(distinct uv.voterId),count(distinct uv.mobileNo),count(distinct uv.tdpCadreId)" +
					"   from    MobileAppUserVoter uv,Constituency c,GreaterMuncipalWard gmc " +
					"   where   uv.wardId=c.constituencyId and uv.wardId=gmc.wardId " +
					"           and uv.wardId in (:locationIds) and uv.mobileAppUser.type is not null ");	
			if(StartDate!=null){
				sb.append(" and ( date(uv.surveyTime) >=:StartDate ");
			}
			if(endDate!=null){
				sb.append(" and date(uv.surveyTime) <=:endDate ) ");
			}
			if(!userTypes.contains("All")){
				sb.append(" and uv.mobileAppUser.type in (:userTypes) ");
			}
			sb.append(" group by uv.wardId,date(uv.surveyTime)");
			sb.append(" order by gmc.divisionName,date(uv.surveyTime)");
		}
		
		Query query=getSession().createQuery(sb.toString());
		
		if(locationType.equalsIgnoreCase("ward")){
			query.setParameterList("locationIds",locationIds);
		}
		if(StartDate!=null){
			query.setParameter("StartDate",StartDate);
		}
		if(endDate!=null){
			query.setParameter("endDate",endDate);
		}
		if(!userTypes.contains("All")){
			query.setParameterList("userTypes",userTypes);
		}
		return query.list();
	}
	
	public List<Object[]> voterRatings(Date startDate,Date endDate,List<Long> locationIds,List<String> userTypes){
		
		StringBuilder sb=new StringBuilder();
		sb.append(" select   wardId as wardId,surveyDate as surveyDate,rating as rating ,count(rating) as count from " +
				  "          (select   distinct uv.ward_id as wardId,date(uv.survey_time) as surveyDate,uv.voter_id as voterId,uv.rating as rating" +
				  "           from    mobile_app_user_voter uv join mobile_app_user user on uv.mobile_app_user_id=user.mobile_app_user_id " +
				  "           where   uv.ward_id in (:locationIds)  and user.type is not null ");
			  if(!userTypes.contains("All")){
				sb.append(" and user.type in (:userTypes) ");
			  }
		     if(startDate!=null){
			   sb.append(" and date(uv.survey_time) >=:startDate ");
		     }
		    if(endDate!=null){
		 	  sb.append(" and date(uv.survey_time) <=:endDate ");
		    }
		    
		    sb.append(" ) AS SUBQUERY ");
	   sb.append(" group by wardId,surveyDate,rating");
	   
	   Query query=getSession().createSQLQuery(sb.toString())
			   .addScalar("wardId", Hibernate.LONG)
			   .addScalar("surveyDate", Hibernate.DATE)
			   .addScalar("rating", Hibernate.LONG)
			   .addScalar("count", Hibernate.LONG);
	   
	   query.setParameterList("locationIds",locationIds);
	   if(!userTypes.contains("All")){
			query.setParameterList("userTypes",userTypes);
		}
	   if(startDate!=null){
			query.setParameter("startDate",startDate);
		}
		if(endDate!=null){
			query.setParameter("endDate",endDate);
		}
		return query.list();
	}
	
    public List<Object[]> overAllDivisionsSummary(Date startDate,Date endDate,List<Long> locationIds,List<String> userTypes){
		
		StringBuilder sb=new StringBuilder();
		sb.append(" select count(distinct uv.wardId), count(distinct uv.mobileAppUserId),count(distinct uv.voterId),count(distinct uv.mobileNo) " +
		" from   MobileAppUserVoter uv ");
		sb.append(" where uv.mobileAppUser.type is not null  ");
        if(locationIds!=null && locationIds.size()>0){
        	sb.append(" and uv.wardId in (:locationIds)  ");
		}
        if(!userTypes.contains("All")){
        	sb.append(" and uv.mobileAppUser.type in (:userTypes)");
        }
		if(startDate!=null){
			sb.append(" and  date(uv.surveyTime) >=:startDate ");
		}
		if(endDate!=null){
			sb.append(" and date(uv.surveyTime) <=:endDate ");
		}
		
		Query query=getSession().createQuery(sb.toString());
		if(locationIds!=null && locationIds.size()>0){
			query.setParameterList("locationIds",locationIds);
		}
		if(!userTypes.contains("All")){
			query.setParameterList("userTypes",userTypes);
        }
		if(startDate!=null){
			query.setParameter("startDate",startDate);
		}
		if(endDate!=null){
			query.setParameter("endDate",endDate);
		}
		return query.list();
	}

    public List<Object[]> overallVoterRatings(Date startDate,Date endDate,List<Long> locationIds,List<String> userTypes){
    	
    
		StringBuilder sb=new StringBuilder();
		sb.append(" select    inRating as outRating,count(inRating) as count from " +
				  "          (select   distinct uv.voter_id as inVoterId,uv.rating as inRating" +
				  "           from     mobile_app_user_voter uv join mobile_app_user user on uv.mobile_app_user_id=user.mobile_app_user_id " +
				  "           where    user.type is not null " );
		      if(locationIds!=null && locationIds.size()>0){
		    	  sb.append(" and uv.ward_id in (:locationIds)");
		      }
		      if(!userTypes.contains("All")){
		        	sb.append(" and user.type in (:userTypes)");
		      }
		     if(startDate!=null){
			   sb.append(" and date(uv.survey_time) >=:startDate ");
		     }
		    if(endDate!=null){
		 	  sb.append(" and date(uv.survey_time) <=:endDate ");
		    }
		    sb.append(" ) AS SUBQUERY ");
	  sb.append(" group by outRating");
	   
	   Query query=getSession().createSQLQuery(sb.toString())
			   .addScalar("outRating", Hibernate.LONG)
			   .addScalar("count", Hibernate.LONG);
	     if(locationIds!=null && locationIds.size()>0){
	    	 query.setParameterList("locationIds",locationIds);
	     }
	     if(!userTypes.contains("All")){
	    	query.setParameterList("userTypes",userTypes);
         }
	   if(startDate!=null){
			query.setParameter("startDate",startDate);
		}
		if(endDate!=null){
			query.setParameter("endDate",endDate);
		}
		return query.list();
	}
	
	
	public List<MobileAppUserVoter> getVoterDataForBooth(List<Long> voterIds,Long boothId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model from MobileAppUserVoter model where model.boothId = :boothId ");
		if(voterIds != null && voterIds.size() > 0)
		str.append(" and model.voterId not in(:voterIds)");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("boothId", boothId);
		if(voterIds != null && voterIds.size() > 0)
		query.setParameterList("voterIds", voterIds);
		return query.list();
	}
	
	public List<Object[]> getLatiLongi(Long userId,Long divisonId,List<Date> datesList){
		Query query = getSession().createQuery(" select model.latitude,model.longitude,model.surveyTime,const.name,gmw.divisionName," +
				" model.voter.name,model.voter.relativeName,model.voter.voterIDCardNo,model.mobileNo,model.rating,model.booth.partNo " +
				" from MobileAppUserVoter model,Constituency const,GreaterMuncipalWard gmw " +
				" where model.mobileAppUserId=:userId and " +
				" model.wardId=:divisonId and " +
				" date(model.surveyTime) in (:datesList) " +
				" and model.wardId=const.constituencyId " +
				" and model.wardId=gmw.wardId ");
		
		query.setParameter("userId", userId);
		query.setParameter("divisonId", divisonId);
		query.setParameterList("datesList", datesList);
	
		return query.list();
	}
	
	public List<Object> getAllAvailableForUser(Long userId,Long divisonId){
		Query query = getSession().createQuery(" select distinct date(model.surveyTime) " +
				" from MobileAppUserVoter model " +
				" where model.mobileAppUserId=:userId and " +
				" model.wardId=:divisonId ");
		
		query.setParameter("userId", userId);
		query.setParameter("divisonId", divisonId);
		
		return query.list();
	}
	
	public Long getNumberOfNumsCollected(Long userId,Long divisonId,List<Date> datesList){
		Query query = getSession().createQuery("select count(distinct model.mobileNo) " +
				" from MobileAppUserVoter model " +
				" where model.mobileAppUserId=:userId and " +
				" model.wardId=:divisonId and " +
				" date(model.surveyTime) in (:datesList) ");
		query.setParameter("userId", userId);
		query.setParameter("divisonId", divisonId);
		query.setParameterList("datesList", datesList);
		return (Long)query.uniqueResult();
		
	}
	
	public List<Long> getTrackingDivisionIds(List<Long> locationIds){
		
		Query query=getSession().createQuery(" " +
		" select distinct uv.wardId" +
		" from MobileAppUserVoter uv where uv.wardId in (:locationIds) and uv.isTracked='Y' ");
		query.setParameterList("locationIds",locationIds);
		
		return query.list();
	}
	
	public Object[] getTrackingDivisionSummaryCounts(List<Long> locationIds){
		Query query=getSession().createQuery(" " +
		" select count(distinct uv.voterId),count(distinct uv.tdpCadreId) " +
		" from MobileAppUserVoter uv where uv.wardId in (:locationIds) and uv.isTracked='Y' ");
		query.setParameterList("locationIds",locationIds);
		return (Object[])query.uniqueResult();
	}

	public List<Object[]> getCapturedVoterRatings(List<Long> locationIds){
		Query query=getSession().createQuery(" " +
		" select uv.rating,count(uv.rating) " +
		" from MobileAppUserVoter uv where uv.wardId in (:locationIds) and uv.isTracked='Y' " +
		" group by uv.rating");
		query.setParameterList("locationIds",locationIds);
		return query.list();
	}
	
	public List<Object[]> mobileAppUserVoterId(List<Long> voterIds)
	{
		Query query = getSession().createQuery(" select mobileAppUserVoterId,model.voterId " +
				" from MobileAppUserVoter model " +
				" where model.voterId in(:voterIds)");
				query.setParameterList("voterIds", voterIds);
		return query.list();
	}
	
	
	public List<Object[]> divisionWiseTotalVotersAndCapturedCadre(List<Long> locationIds){
		
	   Query query=getSession().createQuery(" " + 
	    " select   uv.wardId,gmw.divisionName,gmw.totalVoters as totalVoters,count(distinct uv.voterId) as capturedvoters,count(distinct uv.tdpCadreId) as capturedCadre" +
	    " from     MobileAppUserVoter uv,GreaterMuncipalWard gmw " +
	    " where    uv.wardId=gmw.wardId and uv.wardId in (:locationIds) and uv.isTracked='Y' " +
	    " group by uv.wardId " +
	    " order by gmw.divisionName");
	   query.setParameterList("locationIds",locationIds);
	   return query.list();
	}
	public List<Object[]> getPolledVotersAndPolledCadre(List<Long> divisionIds){
	
		 Query query=getSession().createQuery(" " + 
		    " select   uv.wardId,count(distinct uv.voterId) as polledvoters,count(distinct uv.tdpCadreId) as polledCadre" +
		    " from     MobileAppUserVoter uv " +
		    " where    uv.wardId in (:divisionIds) and uv.isVoted='Y' " +
		    " group by uv.wardId " );
		   query.setParameterList("divisionIds",divisionIds);
		   return query.list();
	}
	public List<Object[]> getCapturedCadrePolled(List<Long> divisionIds){
		
	    Query query=getSession().createQuery(" " + 
	    " select   uv.wardId,count(distinct uv.tdpCadreId) as capturedCadrePolled" +
	    " from     MobileAppUserVoter uv " +
	    " where    uv.wardId in (:divisionIds) and uv.isVoted='Y' and uv.isTracked='Y' " +
	    " group by uv.wardId " );
	   query.setParameterList("divisionIds",divisionIds);
	   return query.list();
		
	}
	public List<Object[]> getNonCapturedVotersPolled(List<Long> divisionIds){
		
	    Query query=getSession().createQuery(" " + 
	    " select   uv.wardId,count(distinct uv.voterId) as nonCapturedVoterpolled " +
	    " from     MobileAppUserVoter uv " +
	    " where    uv.wardId in (:divisionIds) and uv.isVoted='Y' and uv.isTracked='N' " +
	    " group by uv.wardId " );
	   query.setParameterList("divisionIds",divisionIds);
	   return query.list();
		
	}
	public List<Object[]> gettrackedAndPolledratingVoters(List<Long> divisionIds,String type){
		
		StringBuilder sb=new StringBuilder();
		sb.append(" select  uv.wardId,uv.rating,count(uv.rating) from MobileAppUserVoter uv where uv.wardId in (:divisionIds) and uv.isTracked='Y'");
		if(type.equalsIgnoreCase("polled")){
			sb.append(" and uv.isVoted='Y' ");
		}
		sb.append(" group by uv.wardId,uv.rating");
		Query query=getSession().createQuery(sb.toString());
	    query.setParameterList("divisionIds",divisionIds);
		return query.list();
	}
	
	
	public List<Object[]> getNotYetPolledMembers(String resultType,Long locationId){//BoothId
		
		StringBuilder str = new StringBuilder();
		
		str.append("select distinct model.voter.voterId,model.voter.voterIDCardNo,model.voter.name,model.mobileNo," +
				" model.smsStatus,model.isCalled " +
				" from MobileAppUserVoter model" +
				" where model.isVoted is null or model.isVoted ='N'" +
				" and model.boothId =:locationId  ");
		if(resultType !=null && resultType.equalsIgnoreCase("totalVoters"))
		{	
			
		}else if(resultType !=null && resultType.equalsIgnoreCase("totalCadres")){
			str.append(" and model.tdpCadreId is not null ");
		}else if(resultType !=null && resultType.equalsIgnoreCase("totalCapturedCadres")){
			str.append(" and model.tdpCadreId is not null and model.isTracked = 'Y' ");
		}else if(resultType !=null && resultType.equalsIgnoreCase("inclinedVoters")){
			str.append(" and model.rating in (:inclinedVoters)");
			
		}else if(resultType !=null && resultType.equalsIgnoreCase("undecidedVoters")){
			str.append(" and model.rating in (:undecidedVoters)");
		}else if(resultType !=null && resultType.equalsIgnoreCase("otherPartyVoters")){
			str.append(" and model.rating in (:otherPartyVoters)");
		}else if(resultType !=null && resultType.equalsIgnoreCase("nonOptedVoters")){
			str.append(" and model.rating in (:nonOptedVoters)");
		}
		
		Query  query = getSession().createQuery(str.toString());
		
		query.setParameter("locationId", locationId);
		
		if(resultType !=null && resultType.equalsIgnoreCase("inclinedVoters")){
			query.setParameterList("inclinedVoters", IConstants.GHMC_INCLINED_VOTERS);
		}
		if(resultType !=null && resultType.equalsIgnoreCase("undecidedVoters")){
			query.setParameterList("undecidedVoters", IConstants.GHMC_UNDECIDED_VOTERS);
		}
		if(resultType !=null && resultType.equalsIgnoreCase("otherPartyVoters")){
			query.setParameterList("otherPartyVoters", IConstants.GHMC_OTHER_VOTERS);
		}
		if(resultType !=null && resultType.equalsIgnoreCase("nonOptedVoters")){
			query.setParameterList("nonOptedVoters", IConstants.GHMC_NONOPTED_VOTERS);
		}
		
		
		return query.list();
	}
	
}
