package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreHourRegInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreHourRegInfo;
import com.itgrids.partyanalyst.utils.IConstants;

public class TdpCadreHourRegInfoDAO extends GenericDaoHibernate<TdpCadreHourRegInfo,Long> implements ITdpCadreHourRegInfoDAO {
	
	public TdpCadreHourRegInfoDAO() {
		super(TdpCadreHourRegInfo.class);
	}
	
	public List<Object[]> getDateHourWiseTdpCadreCount(Date date , Long stateId){
		
		StringBuilder sbS = new StringBuilder();
		
		sbS.append(" select date(model.tdpCadre.surveyTime),hour(model.tdpCadre.surveyTime),count(model.tdpCadre.tdpCadreId),count(distinct model.tdpCadre.insertedUserId ) " +
				  "  from   TdpCadreEnrollmentYear model " +
				  "  where  model.tdpCadre.isDeleted = 'N' and model.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 and model.enrollmentYearId = :enrollmentYearId ");
		if(date != null){
			sbS.append(" and date(model.tdpCadre.surveyTime) =:date ");
		}
		if(stateId == 1l){
			sbS.append(" and model.tdpCadre.userAddress.district.districtId between 11 and 23 ");
		}else if(stateId == 36l){
			sbS.append(" and model.tdpCadre.userAddress.district.districtId between 1 and 10 ");
		}
		if(date != null){
			sbS.append(" group by hour(model.tdpCadre.surveyTime)");
		}else{
			sbS.append(" group by date(model.tdpCadre.surveyTime),hour(model.tdpCadre.surveyTime)");
		}
		
		
		Query query = getSession().createQuery(sbS.toString());
		
		query.setParameter("enrollmentYearId",IConstants.PRESENT_CADRE_ENROLLMENT_YEAR);
		if(date != null){
			query.setDate("date",date);
		}
		return query.list();
	}
	
	public int deleteAllRecords(Date fromDate){
	    
		 StringBuilder sb = new StringBuilder();
		 
		 sb.append(" delete from tdp_cadre_hour_reg_info  ");
		 if(fromDate != null){
			sb.append(" where survey_date = :fromDate "); 
		 }
 	   Query query = getSession().createSQLQuery(sb.toString());
 	   if(fromDate != null){
 		  query.setDate("fromDate",fromDate );
 	   }
 	   return query.executeUpdate();
  }
	
	public int insertCadreDataHourWiseToday(){
		
		Query query = getSession().createSQLQuery("" +
		"  INSERT INTO tdp_cadre_hour_reg_info( state_id,survey_date,hour,total_registrations,cadre_survey_users,inserted_time ) " +
	    "         SELECT TEMP.state_id,TEMP.survey_date,TEMP.hour,TEMP.total_registrations,TEMP.cadre_survey_users,TEMP.inserted_time" +
	    "         FROM   tdp_cadre_hour_reg_info_temp TEMP " );
		return query.executeUpdate();
	}

	public int insertCadreDataHourWiseOverall(){
		
		Query query = getSession().createSQLQuery("" +
		"  INSERT INTO tdp_cadre_hour_reg_info( state_id,survey_date,hour,total_registrations,cadre_survey_users,inserted_time ) " +
	    "         SELECT TEMP.state_id,TEMP.survey_date,TEMP.hour,TEMP.total_registrations,TEMP.cadre_survey_users,TEMP.inserted_time" +
	    "         FROM   tdp_cadre_hour_reg_info_temp1 TEMP " );
		return query.executeUpdate();
	}
	public List<Object[]> getHourWiseReport(Long stateId, Date surveyDate){
		 StringBuilder sb = new StringBuilder();
		 sb.append("select model.hour,model.totalRegistrations,model.cadreSurveyUsers from TdpCadreHourRegInfo model where " +
		 		  " model.stateId = :stateId and date(model.surveyDate) = :surveyDate order by model.hour ");
		 Query query = getSession().createQuery(sb.toString());  
		 query.setParameter("stateId", stateId);
		 query.setDate("surveyDate", surveyDate);
		 return query.list();
	}
	public List<Object[]> getHourWiseCombineReport(Date surveyDate){
		 StringBuilder sb = new StringBuilder();
		 sb.append("select model.hour,sum(model.totalRegistrations),sum(model.cadreSurveyUsers) from TdpCadreHourRegInfo model where " +
		 		  " date(model.surveyDate) = :surveyDate group by model.hour order by model.hour");
		 Query query = getSession().createQuery(sb.toString());
		 query.setDate("surveyDate", surveyDate);
		 return query.list();
	}
	
}//SELECT hour,sum(total_registrations),sum(cadre_survey_users) FROM tdp_cadre_hour_reg_info WHERE survey_date = '2016-11-06' group by hour;
