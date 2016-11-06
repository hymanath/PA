package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITabUserEnrollmentInfoDAO;
import com.itgrids.partyanalyst.model.TabUserEnrollmentInfo;
import com.itgrids.partyanalyst.model.VoterFamilyCount;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class TabUserEnrollmentInfoDAO extends GenericDaoHibernate<TabUserEnrollmentInfo, Long> implements ITabUserEnrollmentInfoDAO {
	public TabUserEnrollmentInfoDAO() {
		super(TabUserEnrollmentInfo.class);
	}
	public Long getTotalRecordSubmitedByTabUser(Date surveyTime, Long stateId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select sum(TUEI.totalRecords) from TabUserEnrollmentInfo TUEI where " +
						" date(TUEI.surveyTime) = (:surveyTime) and " +
						" TUEI.stateId = :stateId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setDate("surveyTime", surveyTime);
		query.setParameter("stateId", stateId);
		return (Long) query.uniqueResult(); 
	}//SELECT sum(TUEI.total_records) from tab_user_enrollment_info TUEI where date(TUEI.survey_time) = '2016-10-13' and TUEI.state_id = 1;
	public Long getTotalTabUserWorkingInField(Long accessLvlId,List<Long> accessLvlValue,Long stateId,Date today){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(distinct model.tabUserInfoId) from TabUserEnrollmentInfo model where model.stateId =:stateId and " +
				" date(model.startTime) = :today ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("stateId", stateId);   
		query.setDate("today",today);
		return (Long) query.uniqueResult();
	}
	public Long getTodayInFieldList(Long stateId,Date lastOneHourTime){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select count(distinct model.tabUserInfoId) from TabUserEnrollmentInfo model where model.endTime > (:lastOneHourTime) and " +
						" model.stateId = :stateId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("lastOneHourTime", lastOneHourTime);
		query.setParameter("stateId", stateId);
		return (Long)query.uniqueResult();
	}
	public Long getTodayPresentList(Long stateId,Date surveyTime){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select count(distinct model.tabUserInfoId) from TabUserEnrollmentInfo model where date(model.surveyTime) = (:surveyTime) " +
						" and  model.stateId = :stateId ");
		Query query = getSession().createQuery(queryStr.toString());  
		query.setDate("surveyTime", surveyTime);
		query.setParameter("stateId", stateId);  
		return (Long)query.uniqueResult();            
	}
	public int pushTabUserInfoIntoIntermediateTable(){
		Query query = getSession().createSQLQuery("CALL cadre_core_dashboard2();");   
		return query.executeUpdate();  
	}
	
	public List<Object[]> getTabUserFirstLastRecord(List<Long> tabUserInfoIds){
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select model.startTime ,model.endTime ,model.cadreSurveyUserId,model.tabUserInfoId,model.totalRecords from " +
				" TabUserEnrollmentInfo model where model.tabUserInfoId in (:tabUserInfoIds) and date(model.surveyTime) = :today " );
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("tabUserInfoIds", tabUserInfoIds);   
		query.setDate("today",new DateUtilService().getCurrentDateAndTime());
		
		return query.list();
		
	}

	public List<Object[]> getTabUserFirstLastRecordNew(Long cadreRegUserId,Long constituencyId,Long userId,Long districtId,Long stateId){
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select model.startTime ,model.endTime ,model.cadreSurveyUserId,model.tabUserInfoId,model.totalRecords,model.tabUserName from " +
				" TabUserEnrollmentInfo model,CadreRegUserTabUser model1 where model.cadreSurveyUserId = model1.cadreSurveyUserId" +
				" and date(model.surveyTime) = :today and model1.cadreRegUser.userType = 'FM' and model.enrollmentYearId = 4" );
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			queryStr.append(" and model1.cadreRegUser.cadreRegUserId = :cadreRegUserId");
		if(districtId != null && districtId.longValue() > 0l){
			queryStr.append(" and model.districtId = :districtId");
		}
		else{
			if(stateId != null && stateId.longValue() == 1l){
				queryStr.append("  and  model.stateId = 1 ");
			}else if(stateId != null && stateId.longValue() == 36l){
				queryStr.append(" and  model.stateId = 36 ");
			}
			else if(stateId != null && stateId.longValue() == 0l){
				queryStr.append(" and  model.stateId in (1,36) ");
			}
		}
		if(constituencyId != null && constituencyId.longValue() > 0l)
			queryStr.append(" and model.constituencyId = :constituencyId");
		if(userId != null && userId.longValue() > 0l)
			queryStr.append(" and model.cadreSurveyUserId = :cadreSurveyUserId");
		
		Query query = getSession().createQuery(queryStr.toString());
		
		query.setDate("today",new DateUtilService().getCurrentDateAndTime());
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			query.setParameter("cadreRegUserId", cadreRegUserId);
		if(districtId != null && districtId.longValue() > 0l)
			query.setParameter("districtId", districtId);
		if(constituencyId != null && constituencyId.longValue() > 0l)
			query.setParameter("constituencyId", constituencyId);
		if(userId != null && userId.longValue() > 0l)
			query.setParameter("cadreSurveyUserId", userId);
			
		return query.list();
		
	}
}

