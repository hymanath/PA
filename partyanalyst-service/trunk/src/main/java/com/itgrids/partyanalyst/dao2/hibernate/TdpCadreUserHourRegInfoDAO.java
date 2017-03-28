/**
 * 
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreUserHourRegInfo;
import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
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
		sbS.append(" and hour(model.tdpCadre.surveyTime) <= :hour ");
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
	
	public int insertCadreDataByUserWiseHourWiseOverall(){
		
		Query query = getSession().createSQLQuery("" +
		"  INSERT INTO tdp_cadre_user_hour_reg_info( cadre_survey_user_id,tab_user_info_id,survey_date,hour,reg_count,inserted_time ) " +
	    "         SELECT TEMP.cadre_survey_user_id,TEMP.tab_user_info_id,TEMP.survey_date,TEMP.hour,TEMP.reg_count,TEMP.inserted_time" +
	    "         FROM   tdp_cadre_user_hour_reg_info_temp1 TEMP " );
		return query.executeUpdate();
	}
	public List<Object[]> getLocationWiseTabUserTrackingDetails(GISVisualizationParameterVO inputVO,String type){
		
		try {
			StringBuilder queryStr = new StringBuilder();
			
			queryStr.append(" select  model1.constituency.constituencyId,model1.constituency.name,model.tabUserInfo.tabUserInfoId,model.tabUserInfo.name,model.tabUserInfo.imgPath,model.tabUserInfo.mobileNo ");
				
			 if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE) || inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT) || inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
				 if(type.equalsIgnoreCase("LastOneHr")){
					 queryStr.append(" , model.regCount ");
				 }else{
					 queryStr.append(" , sum(model.regCount) ");
				 }
			}
			 queryStr.append(" ,model1.constituency.district.districtId,model1.constituency.district.districtName,model.cadreSurveyUserId ");
			 
			queryStr.append(" from ");
			
			queryStr.append(" TdpCadreUserHourRegInfo model,CadreSurveyUserAssignDetails model1 where model1.cadreSurveyUser.cadreSurveyUserId = model.cadreSurveyUserId ");
			
			if(!type.trim().equalsIgnoreCase("LastOneHr")){
				 if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
					queryStr.append(" and (date(model.surveyDate) between :startDate and :endDate) ");
				}else{
					queryStr.append(" and (date(model.surveyDate) between :startDate and :endDate) ");
				}
			}else{
				queryStr.append(" and model.hour = :lastOneHour and (date(model.surveyDate) between :startDate and :endDate) ");
			}
			
			if(inputVO.getChildLocationType() != null && inputVO.getChildLocationType().equalsIgnoreCase("old") &&
					 inputVO.getParentLocationType() != null &&  inputVO.getParentLocationTypeId() != null && inputVO.getParentLocationTypeId().longValue()>0L)
			{
				if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
					if(inputVO.getParentLocationTypeId().longValue() == 36L || inputVO.getParentLocationTypeId().longValue() == 2L)
						queryStr.append(" and (model1.constituency.district.districtId between 1 and 10 ) ");
					else if(inputVO.getParentLocationTypeId().longValue() == 1L)
						queryStr.append(" and ( model1.constituency.district.districtId between 11 and 23)  ");
					else
						queryStr.append(" and model1.constituency.district.state.stateId = 1 ");
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and model1.constituency.district.districtId = :parentLocationTypeId ");
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
						queryStr.append("  and model1.constituency.constituencyId = :parentLocationTypeId ");
				}
			}
			else{
				if(inputVO.getParentLocationTypeId().longValue() == 36L || inputVO.getParentLocationTypeId().longValue() == 2L)
					queryStr.append(" and (model1.constituency.district.districtId between 1 and 10 )");
				else if(inputVO.getParentLocationTypeId().longValue() == 1L)
					queryStr.append(" and (model1.constituency.district.districtId between 11 and 23) ");
				else {
					//queryStr.append(" and model1.constituency.district.state.stateId = 1 ");
					queryStr.append(" and (model1.constituency.district.districtId between 11 and 23 ) ");
				}
			}
			queryStr.append(" and model1.cadreSurveyUser.isDeleted='N' " +
					"  and model1.cadreSurveyUser.isEnabled='Y'  and model1.isDeleted='N' ");
			
				queryStr.append(" group by model.cadreSurveyUserId ");
			
			Query query = getSession().createQuery(queryStr.toString());
			if(inputVO.getChildLocationType() != null && inputVO.getChildLocationType().equalsIgnoreCase("old") && 
					inputVO.getParentLocationType() != null && !inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE) && 
					 inputVO.getParentLocationTypeId() != null && inputVO.getParentLocationTypeId().longValue()>0L)
				query.setParameter("parentLocationTypeId", inputVO.getParentLocationTypeId());
			
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			if(!type.trim().equalsIgnoreCase("LastOneHr")){
			if(type.equalsIgnoreCase("total")){
				if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
					query.setDate("startDate", format.parse(inputVO.getStartDate()));
					query.setDate("endDate", format.parse(inputVO.getEndDate()));
				}else{
					query.setDate("startDate", new DateUtilService().getCurrentDateAndTime());
					query.setDate("endDate", new DateUtilService().getCurrentDateAndTime());
				}
			}else if(type.equalsIgnoreCase("today")){
				query.setDate("startDate", new DateUtilService().getCurrentDateAndTime());
				query.setDate("endDate", new DateUtilService().getCurrentDateAndTime());
			}
			}else{
				Calendar cal = Calendar.getInstance();
				cal.setTime(new DateUtilService().getCurrentDateAndTime());
				int presentHour = cal.get(Calendar.HOUR_OF_DAY);
				Long lastOneHour = Long.valueOf(presentHour-1);
				/*Calendar cal = Calendar.getInstance();
				cal.add(Calendar.HOUR_OF_DAY, 1);// last one hour
				query.setCalendarDate("startDate",cal);*/
				if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
					query.setDate("startDate", format.parse(inputVO.getStartDate()));
					query.setDate("endDate", format.parse(inputVO.getEndDate()));
				}else{
					query.setDate("startDate", new DateUtilService().getCurrentDateAndTime());
					query.setDate("endDate", new DateUtilService().getCurrentDateAndTime());
				}
				query.setParameter("lastOneHour", lastOneHour);
			}
			
			return query.list();
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return null;
	}
	public List<Object[]> getRegDtlsHourWiseList(Long cadreSurveyUserId,Date surveyDate){
		 StringBuilder sb = new StringBuilder();
		 sb.append("select model.hour,sum(model.regCount) from TdpCadreUserHourRegInfo model where " +
		 		  " date(model.surveyDate) = :surveyDate " +  
		 		  " and model.cadreSurveyUserId = :cadreSurveyUserId" +

		 		  " group by model.hour order by model.hour");
		 Query query = getSession().createQuery(sb.toString());
		 query.setDate("surveyDate", surveyDate);  
		 query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
		 return query.list();  
	}
	public List<Object[]> getTotalRegCount(Long cadreSurveyUserId){
		StringBuilder sb = new StringBuilder();
		 sb.append("select model.cadreSurveyUserId,sum(model.regCount) from TdpCadreUserHourRegInfo model where " +
		 		  " model.cadreSurveyUserId = :cadreSurveyUserId " +
		 		  " group by model.cadreSurveyUserId ");
		 Query query = getSession().createQuery(sb.toString());
		 query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
		 return query.list(); 
	}
	public List<Object[]> getTotalRegForAHour(Long cadreSurveyUserId,Date surveyDate, Long hr){
		StringBuilder sb = new StringBuilder();
		 sb.append("select model.cadreSurveyUserId,model.hour,model.regCount from TdpCadreUserHourRegInfo model where " +
		 		  " model.cadreSurveyUserId = :cadreSurveyUserId" +
		 		  " and date(model.surveyDate) = :surveyDate " +
		 		  " and model.hour = :hr ");
		 Query query = getSession().createQuery(sb.toString());
		 query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
		 query.setParameter("hr", hr);
		 query.setDate("surveyDate", surveyDate);
		 return query.list(); 
	}
}