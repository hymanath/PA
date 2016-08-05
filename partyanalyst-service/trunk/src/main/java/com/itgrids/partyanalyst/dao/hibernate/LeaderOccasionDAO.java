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
		queryStr.append("SELECT model.tdp_cadre_id,model2.first_name, model2.mobile_no,model2.image,model.occasion_date,model.leader_occasion_id FROM leader_occasion model, tdp_cadre model2 WHERE " +
				" model.tdp_cadre_id = model2.tdp_cadre_id and model2.is_deleted='N' and model2.enrollment_year = 2014 and model.date_no  ");
		if(betweenDatesCount != null && betweenDatesCount.longValue() ==0L){
			queryStr.append(" = CAST(DATE_FORMAT(CURDATE(),'%m%d') AS UNSIGNED) ");
		}
		else if(searchTypeStr.trim().equalsIgnoreCase("next")){
			queryStr.append(" BETWEEN CAST(DATE_FORMAT(CURDATE() + INTERVAL 1 DAY,'%m%d') AS UNSIGNED) AND " +
					" CAST(DATE_FORMAT(CURDATE() + INTERVAL "+betweenDatesCount+" DAY,'%m%d') AS UNSIGNED)");
		}
		else if(searchTypeStr.trim().equalsIgnoreCase("previous")){
			queryStr.append("BETWEEN CAST(DATE_FORMAT(CURDATE() - INTERVAL "+betweenDatesCount+" DAY,'%m%d') AS UNSIGNED) AND " +
					" CAST(DATE_FORMAT(CURDATE() - INTERVAL 1 DAY,'%m%d') AS UNSIGNED)");
		}
			
		queryStr.append(" and model.occasion_type_id=:occastionTypeId and model.is_deleted='false' ");
		
		Query query=getSession().createSQLQuery(queryStr.toString());
	    query.setParameter("occastionTypeId", occastionTypeId);
		return query.list();
	}

	public Long getLeaderOccasionCountDetailsForTaday(String searchTypeStr,Long betweenDatesCount,Long occastionTypeId) {  
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("SELECT count(model.leader_occasion_id) FROM leader_occasion model WHERE date_no ");
		if(searchTypeStr.trim().equalsIgnoreCase("next"))
			queryStr.append("BETWEEN CAST(DATE_FORMAT(CURDATE() + INTERVAL 1 DAY,'%m%d') AS UNSIGNED) AND " +
					" CAST(DATE_FORMAT(CURDATE() + INTERVAL "+betweenDatesCount+" DAY,'%m%d') AS UNSIGNED)");
			
		if(searchTypeStr.trim().equalsIgnoreCase("previous"))
			queryStr.append("BETWEEN CAST(DATE_FORMAT(CURDATE() - INTERVAL "+betweenDatesCount+" DAY,'%m%d') AS UNSIGNED) AND " +
					" CAST(DATE_FORMAT(CURDATE() - INTERVAL 1 DAY,'%m%d') AS UNSIGNED)");
		else
			queryStr.append(" = CAST(DATE_FORMAT(CURDATE(),'%m%d') AS UNSIGNED) ");
			
		queryStr.append(" and model.occasion_type_id=:occastionTypeId ");
		
		Query query=getSession().createSQLQuery(queryStr.toString());
	    query.setParameter("occastionTypeId", occastionTypeId);
		return (Long) query.uniqueResult();
	}
	public List<Object[]> getleaderOccasionWishDetails() {
		
		Query query = getSession().createQuery( "select distinct model.leaderOccasionId,model.isDeleted from  LeaderOccasion model " +
				" where model.isDeleted='false'" );
			
			return  query.list();
		}

}
