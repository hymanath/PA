package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMobileAppUserVoterDAO;
import com.itgrids.partyanalyst.model.MobileAppUserVoter;

public class MobileAppUserVoterDAO extends GenericDaoHibernate<MobileAppUserVoter, Long> implements IMobileAppUserVoterDAO{

	public MobileAppUserVoterDAO() {
		super(MobileAppUserVoter.class);
	}
	
	
	public List<Object[]> getUserStartEndTime(Long locationId, String locationType, Date fromDate, Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.mobileAppUser.mobileAppUserId," +
				" model.mobileAppUser.userName," +
				" model.mobileAppUser.mobileNo," +
				" model.mobileAppUser.email," +
				" model.mobileAppUser.uniqueCode," +
				" date(model.surveyTime)," +
				" min(model.surveyTime)," +
				" max(model.surveyTime)" +
				" from MobileAppUserVoter model" +
				" where" +
				" model.mobileAppUser.isDeleted='N'" +
				" and model.mobileAppUser.isEnabled='Y'" +
				" and date(model.surveyTime) between :fromDate and :toDate");
		
		if(locationType.equalsIgnoreCase("Ward")){
			sb.append(" and model.wardId=:locationId");
		}
		
		sb.append(" group by model.mobileAppUser.mobileAppUserId," +
				" date(model.surveyTime)");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("locationId", locationId);
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
		
	}
	
	public List<Object[]> getUserCollectedDetails(Long locationId, String locationType, Date fromDate, Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.mobileAppUser.mobileAppUserId," +
				" date(model.surveyTime)," +
				" count(distinct model.mobileNo)," +
				" count(model.voterId)," +
				" count(distinct model.voterId)" +
				" from MobileAppUserVoter model" +
				" where" +
				" model.mobileAppUser.isDeleted='N'" +
				" and model.mobileAppUser.isEnabled='Y'" +
				" and date(model.surveyTime) between :fromDate and :toDate");
		
		if(locationType.equalsIgnoreCase("Ward")){
			sb.append(" and model.wardId=:locationId");
		}
		
		sb.append(" group by model.mobileAppUser.mobileAppUserId," +
				" date(model.surveyTime)");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("locationId", locationId);
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
	}
	
	public List<Object[]> getUserCollectedRatingDetails(Long locationId, String locationType, Date fromDate, Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.mobileAppUser.mobileAppUserId," +
				" date(model.surveyTime)," +
				" model.rating," +
				" count(model.rating)" +
				" from MobileAppUserVoter model" +
				" where" +
				" model.mobileAppUser.isDeleted='N'" +
				" and model.mobileAppUser.isEnabled='Y'" +
				" and date(model.surveyTime) between :fromDate and :toDate");
		
		if(locationType.equalsIgnoreCase("Ward")){
			sb.append(" and model.wardId=:locationId");
		}
		
		sb.append(" group by model.mobileAppUser.mobileAppUserId," +
				" date(model.surveyTime)," +
				" model.rating");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("locationId", locationId);
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
	}
	public List<Object[]> locationWiseOverView(Date StartDate,Date endDate,List<Long> locationIds,String locationType){
	
		
		StringBuilder sb=new StringBuilder();
		
		if(locationType.equalsIgnoreCase("ward")){
			
			sb.append(" select  uv.wardId,c.name,gmc.divisionName," +
					"           date(uv.surveyTime)," +
					"           count(distinct uv.mobileAppUserId),count(distinct uv.voterId),count(distinct uv.mobileNo)" +
					"   from    MobileAppUserVoter uv,Constituency c,GreaterMuncipalWard gmc " +
					"   where   uv.wardId=c.constituencyId and uv.wardId=gmc.wardId " +
					"           and uv.wardId in (:locationIds) ");	
			if(StartDate!=null){
				sb.append(" and date(uv.surveyTime) >=:StartDate ");
			}
			if(endDate!=null){
				sb.append(" and date(uv.surveyTime) <=:endDate ");
			}
			sb.append(" group by uv.wardId,date(uv.surveyTime)");
			sb.append(" order by c.name,date(uv.surveyTime)");
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
		return query.list();
	}
	
	public List<Object[]> voterRatings(Date startDate,Date endDate,List<Long> locationIds){
		
		StringBuilder sb=new StringBuilder();
		sb.append(" select   wardId as wardId,surveyDate as surveyDate,rating as rating ,count(rating) as count from " +
				  "          (select   distinct uv.ward_id as wardId,date(uv.survey_time) as surveyDate,uv.voter_id as voterId,uv.rating as rating" +
				  "           from    mobile_app_user_voter uv " +
				  "           where   uv.ward_id in (:locationIds)  ");
		
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
	   if(startDate!=null){
			query.setParameter("startDate",startDate);
		}
		if(endDate!=null){
			query.setParameter("endDate",endDate);
		}
		return query.list();
	}
}
