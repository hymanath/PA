package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ILeaderOccasionDAO;
import com.itgrids.partyanalyst.model.LeaderOccasion;
import com.itgrids.partyanalyst.model.LeaderOccasionWishDetails;

public class LeaderOccasionDAO extends GenericDaoHibernate<LeaderOccasion, Long> implements ILeaderOccasionDAO{

	public LeaderOccasionDAO() {
		super(LeaderOccasion.class);
		
	}

	public List<Object[]> getLeaderOccasionDetailsForTaday(String searchTypeStr,Long betweenDatesCount,Long occastionTypeId) {  
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("SELECT * FROM leader_occasion WHERE date_no ");
		if(searchTypeStr.trim().equalsIgnoreCase("next"))
			queryStr.append("BETWEEN CAST(DATE_FORMAT(CURDATE() + INTERVAL 1 DAY,'%m%d') AS UNSIGNED) AND " +
					" CAST(DATE_FORMAT(CURDATE() + INTERVAL "+betweenDatesCount+" DAY,'%m%d') AS UNSIGNED)");
			
		if(searchTypeStr.trim().equalsIgnoreCase("previous"))
			queryStr.append("BETWEEN CAST(DATE_FORMAT(CURDATE() - INTERVAL "+betweenDatesCount+" DAY,'%m%d') AS UNSIGNED) AND " +
					" CAST(DATE_FORMAT(CURDATE() - INTERVAL 1 DAY,'%m%d') AS UNSIGNED)");
		else
			queryStr.append(" = CAST(DATE_FORMAT(CURDATE(),'%m%d') AS UNSIGNED) ");
			
		Query query=getSession().createSQLQuery(queryStr.toString());
	    query.setParameter("occastionTypeId", occastionTypeId);
		return query.list();
	}

	public Long getTotalBirthDays( Date fromDate, Date toDate) {
		Query query = getSession()
				.createQuery(
						"select count(model.leaderOccasionId) from LeaderOccasion model "
								+ "where model.isdeleted='false'  " +
								"(  DATE_FORMAT(date(model.occasionDate),'%m-%d') = DATE_FORMAT(:fromDate,'%m-%d') and " +
				" DATE_FORMAT(date(model.occasionDate),'%m-%d') = DATE_FORMAT(:toDate,'%m-%d') ) ");
								//"and month(model.occasionDate) in (:months) and day(model.occasionDate) in (:days)");
		
		/*Query query=getSession().createQuery("select count(model.leaderOccasionId) from LeaderOccasion model where model.isdeleted='false' " +
				"and month(model.occasionDate) in (:months) and day(model.occasionDate) in (:days)");*/
		/*select count(leader_occasion_id) from leader_occasion where month(occasion_date)='7' and day(occasion_date) 
		between '25' and '31';*/
		
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return (Long) query.uniqueResult();
	}

	
	public Long getTotalWishedBirthDays(Date fromDate,Date toDate) {
		
		Query query=getSession().createQuery("select count(model.leaderOccasionWishDetailsId) from LeaderOccasionWishDetails model "
								+ "where model.leaderOccasion.leaderOccasionId=model.leaderOccasionId " +
								 " ( DATE_FORMAT(date(model.leaderOccasion.occasionDate),'%m-%d') = DATE_FORMAT(:fromDate,'%m-%d') and " +
								" DATE_FORMAT(date(model.leaderOccasion.occasionDate),'%m-%d') = DATE_FORMAT(:toDate,'%m-%d') ) ");
								//"and month(model.leaderOccasion.occasionDate) in (:months) and day(model.leaderOccasion.occasionDate) in (:days)");
		
		
		
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return (Long) query.uniqueResult();
	}
	
	/*public List<Long> getLeaderBirthDayWishingDetails(Date fromDate, Date toDate){
		
		Query query=getSession().createQuery("select model.leaderOccasion.tdpCadreId from LeaderOccasionWishDetails model "
				+ "where model.leaderOccasionId=model.leaderOccasion.leaderOccasionId and date(model.leaderOccasion.occasionDate) between :fromDate and :toDate");
		
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return (List<Long>) query.list();
		
	}
*/
	/*public List<LeaderOccasionWishDetails> getWishingDetails(Long searchId,String year) {  
	   Query query=getSession().createQuery("select model from LeaderOccasionWishDetails model "  +
				" where model.leaderOccasionWishDetailsId =:searchId" +
				" AND model.year = :year ");
	   query.setParameter("searchId", searchId);
	   query.setParameter("year", year);
		return query.list();
	}*/
	
	/*public List<Object[]> getLeaderOccasionDetailsForTaday1(List<Integer> months,List<Integer> days,Long occastionTypeId){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select model.tdpCadre.image,model.tdpCadre.firstname,model.occasionDate," +
				"  model.tdpCadre.mobileNo, model.leaderOccasionId from LeaderOccasion model where " +
				" model.tdpCadreId=model.tdpCadre.tdpCadreId and model.occastionTypeId =:occastionTypeId ");
		sb.append(" and month(model.occasionDate) in (:months) and day(model.occasionDate) in (:days) ");
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameterList("months", months); 
		query.setParameterList("days", days);
		query.setParameter("occastionTypeId", occastionTypeId);
		
		return query.list();
		  
	}*/

}
