/**
 * 
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreUserHourRegInfo;
import com.itgrids.partyanalyst.model.TdpCadreUserHourRegInfo;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

/**
 * @author sys
 *
 */
public class TdpCadreUserHourRegInfoDAO  extends GenericDaoHibernate<TdpCadreUserHourRegInfo, Long> implements ITdpCadreUserHourRegInfo{

	public TdpCadreUserHourRegInfoDAO() {
		super(TdpCadreUserHourRegInfo.class);
	}
	
public List<Object[]> getTabUserLastOneHourData(Long lstHour,List<Long> tabUserInfoIds){
	StringBuilder queryStr = new StringBuilder();
	
	queryStr.append(" select model.regCount ,model.hour ,model.cadreSurveyUserId,model.tabUserInfoId from " +
			" TdpCadreUserHourRegInfo model where model.tabUserInfoId in (:tabUserInfoIds) and date(model.surveyDate) = :today and model.hour = :lstHour " );
	
	Query query = getSession().createQuery(queryStr.toString());
	query.setParameterList("tabUserInfoIds", tabUserInfoIds);   
	query.setDate("today",new DateUtilService().getCurrentDateAndTime());
	query.setParameter("lstHour", lstHour);   
	
	return query.list();
}

public List<Object[]> getTdpCadreDataHourWiseForTabUsers(Date date,Integer hour){
	
	StringBuilder sbS = new StringBuilder();
	
	sbS.append(" select date(model.tdpCadre.surveyTime),hour(model.tdpCadre.surveyTime)," +
			"           model.tdpCadre.insertedUserId,model.tdpCadre.tabUserInfoId,count(model.tdpCadre.tdpCadreId) " +
			  "  from   TdpCadreEnrollmentYear model " +
			  "  where  model.tdpCadre.isDeleted = 'N' and model.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 and model.enrollmentYearId = :enrollmentYearId ");
	if(date != null){
		sbS.append(" and date(model.tdpCadre.surveyTime) =:date ");
	}
	if(hour != null){
		sbS.append(" and hour(model.tdpCadre.surveyTime) < :hour ");
	}
	sbS.append(" group by hour(model.tdpCadre.surveyTime),model.tdpCadre.insertedUserId,model.tdpCadre.tabUserInfoId  ");
	
	
	Query query = getSession().createQuery(sbS.toString());
	query.setParameter("enrollmentYearId",IConstants.PRESENT_CADRE_ENROLLMENT_YEAR);
	if(date != null){
		query.setDate("date",date);
	}
	if(hour != null){
		query.setParameter("hour",hour);
	}
	return query.list();
}

public int deleteAllRecords(Date fromDate){
    
	 StringBuilder sb = new StringBuilder();
	 
	 sb.append(" delete from tdp_cadre_user_hour_reg_info  ");
	 if(fromDate != null){
		sb.append(" where survey_date = :fromDate "); 
	 }
	   Query query = getSession().createSQLQuery(sb.toString());
	   if(fromDate != null){
		  query.setDate("fromDate",fromDate );
	   }
	   return query.executeUpdate();
}

public List<Object[]> getTdpCadreDataHourWiseForTabUsersOverall(){
	
	StringBuilder sbS = new StringBuilder();
	
	sbS.append(" select date(model.tdpCadre.surveyTime),hour(model.tdpCadre.surveyTime)," +
			"           model.tdpCadre.insertedUserId,model.tdpCadre.tabUserInfoId,count(model.tdpCadre.tdpCadreId) " +
			  "  from   TdpCadreEnrollmentYear model " +
			  "  where  model.tdpCadre.isDeleted = 'N' and model.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 and model.enrollmentYearId = :enrollmentYearId ");
	
	sbS.append(" group by date(model.tdpCadre.surveyTime),hour(model.tdpCadre.surveyTime),model.tdpCadre.insertedUserId,model.tdpCadre.tabUserInfoId  ");
	
	
	Query query = getSession().createQuery(sbS.toString());
	query.setParameter("enrollmentYearId",IConstants.PRESENT_CADRE_ENROLLMENT_YEAR);
	return query.list();
}

	public int insertCadreDataByUserWiseHourWise(){
		
		Query query = getSession().createSQLQuery("" +
		"  INSERT INTO tdp_cadre_user_hour_reg_info( cadre_survey_user_id,tab_user_info_id,survey_date,hour,reg_count,inserted_time ) " +
	    "         SELECT TEMP.cadre_survey_user_id,TEMP.tab_user_info_id,TEMP.survey_date,TEMP.hour,TEMP.reg_count,TEMP.inserted_time" +
	    "         FROM   tdp_cadre_user_hour_reg_info_temp TEMP " );
		return query.executeUpdate();
	}
	
}
