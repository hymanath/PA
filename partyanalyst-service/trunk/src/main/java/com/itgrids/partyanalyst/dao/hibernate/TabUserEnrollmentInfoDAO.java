package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITabUserEnrollmentInfoDAO;
import com.itgrids.partyanalyst.model.TabUserEnrollmentInfo;
import com.itgrids.partyanalyst.model.VoterFamilyCount;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

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
		queryStr.append("select count(distinct model.tabUserInfoId) from TabUserEnrollmentInfo model where model.endTime >= (:lastOneHourTime) and " +
						" model.stateId = :stateId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("lastOneHourTime", lastOneHourTime);
		query.setParameter("stateId", stateId);
		return (Long)query.uniqueResult();
	}
	public Long getTodayPresentList(Long stateId,Date surveyTime){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select count(distinct model.tabUserInfoId) from TabUserEnrollmentInfo model where date(model.surveyTime) = :surveyTime " +
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

	public List<Object[]> getTabUserFirstLastRecordNew(Long cadreRegUserId,Long constituencyId,Long userId,Long districtId,Long stateId,Date startDate,Date endDate){
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select model.startTime ,model.endTime ,model.cadreSurveyUserId,model.tabUserInfoId,sum(model.totalRecords),model.tabUserName,model.tabUserInfo.mobileNo from " +
				" TabUserEnrollmentInfo model,CadreRegUserTabUser model1 where model.cadreSurveyUserId = model1.cadreSurveyUserId" +
				" and model1.cadreRegUser.userType = 'FM' and model.enrollmentYearId = 4" );
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
		if(startDate !=null && endDate != null){
			queryStr.append(" and date(model.surveyTime) between :startDate and :endDate ");
		}
		
		queryStr.append(" group by model.tabUserInfoId order by model.endTime");
		
		Query query = getSession().createQuery(queryStr.toString());
		
		//query.setDate("today",new DateUtilService().getCurrentDateAndTime());
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			query.setParameter("cadreRegUserId", cadreRegUserId);
		if(districtId != null && districtId.longValue() > 0l)
			query.setParameter("districtId", districtId);
		if(constituencyId != null && constituencyId.longValue() > 0l)
			query.setParameter("constituencyId", constituencyId);
		if(userId != null && userId.longValue() > 0l)
			query.setParameter("cadreSurveyUserId", userId);
		if(startDate !=null && endDate != null)	{
			query.setDate("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		return query.list();
		
	}
	public List<Object[]> getTabUserWiseTotalRegistrationDetails(Long stateId,Date date){
	     StringBuilder queryStr = new StringBuilder();
	     queryStr.append(" select distinct " +
	     		         " model.tabUserInfoId," +//0
	     		         " model.tabUserName," +//1
	     		         " model.mobileNo," +//2
	     		         " model.imagePath," +//3
	     		         " sum(model.totalRecords) " + //4
	     		         " from TabUserEnrollmentInfo model " +
	     		         " where model.enrollmentYearId=4 " +
	     		         " and model.stateId=:stateId " +
	     		         " and date(model.surveyTime)=:date " +
	      		         " group by model.tabUserInfoId order by sum(model.totalRecords) desc ");
	      Query query = getSession().createQuery(queryStr.toString());
	       query.setParameter("stateId", stateId);
	       query.setParameter("date", date);
	      return query.list();
   }
   public List<Object[]> getActiveTabUserDtls(Long stateId,Date lastOneHourTime){
	 		StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select distinct model.tabUserInfoId,sum(model.totalRecords) from TabUserEnrollmentInfo model where model.endTime > (:lastOneHourTime) and " +
							" model.stateId = :stateId group by model.tabUserInfoId ");
			Query query = getSession().createQuery(queryStr.toString());
			query.setParameter("lastOneHourTime", lastOneHourTime);
			query.setParameter("stateId", stateId);
			return query.list();
	
    }
   	public List<Object[]> getTabUserDtlsList(Long constituencyId,Date fromDate,Date toDate){
   		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model.tabUserInfoId, " +//0
						" model.tabUserName," +//1
						" model.imagePath," +//2
						" model.mobileNo," +//3
						" sum(model.totalRecords), " +//4
						" min(model.startTime), " +//5
						" max(model.endTime) " +//6
						" from TabUserEnrollmentInfo model where " +
						" date(model.surveyTime) between :fromDate and :toDate " +
						" and model.constituencyId = :constituencyId " +
						" group by model.tabUserInfoId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
   		return query.list();    
   	}
   	
   	public List<Object[]> getActualCountOfCadreSurveyUserWiseCount(Set<Long> cadreSurveyUsers,Date fromDate,Date toDate){
		
		StringBuilder str = new StringBuilder();				
		str.append(" select model.cadreSurveyUserId,model.totalRecords,date(model.startTime) " +
				" from TabUserEnrollmentInfo model  " +
				" where " +
				"  date(model.surveyTime) between :fromDate and :toDate ");					
		
		if(cadreSurveyUsers !=null && cadreSurveyUsers.size()>0){
			str.append( " and model.cadreSurveyUserId in (:cadreSurveyUsers)  " );
		}
		if(fromDate != null && toDate != null){
			str.append(" and date(model.surveyTime) between :fromDate and  :toDate ");
		}
		
		str.append(" group by date(model.surveyTime) ");
		
		Query query = getSession().createQuery(str.toString());
		
		
		//CADRE_ENROLLMENT_NUMBER
		if(cadreSurveyUsers !=null && cadreSurveyUsers.size()>0){
			query.setParameterList("cadreSurveyUsers", cadreSurveyUsers);
		}
		
		if(fromDate !=null && toDate !=null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
		
	}
   	public List<Object[]> getActualCountOfCadreSurveyUser(Set<Long> cadreSurveyUsers,Date fromDate,Date toDate){
		
		StringBuilder str = new StringBuilder();				
		str.append(" select model.cadreSurveyUserId,sum(model.totalRecords) " +
				" from TabUserEnrollmentInfo model  " +
				" where ");					
		
		if(cadreSurveyUsers !=null && cadreSurveyUsers.size()>0){
			str.append( "  model.cadreSurveyUserId in (:cadreSurveyUsers) and " );
		}
		if(fromDate != null && toDate != null){
			str.append("  date(model.surveyTime) between :fromDate and  :toDate ");
		}
		str.append(" group by model.cadreSurveyUserId ");
		
		Query query = getSession().createQuery(str.toString());
		
		
		//CADRE_ENROLLMENT_NUMBER
		if(fromDate !=null && toDate !=null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(cadreSurveyUsers !=null && cadreSurveyUsers.size()>0){
			query.setParameterList("cadreSurveyUsers", cadreSurveyUsers);
		}
		
		return query.list();
		
	}
   	
   	public List<Object[]> getActualCountOfCadreSurveyUser(Long districtId,Long stateId,Date startDate,Date endDate,Long constituencyId,Set<Long> cadreSurveyUsers){
 		
 		StringBuilder queryStr = new StringBuilder();
 		queryStr.append(" select ");
 		
 		if(districtId != null && districtId.longValue()>0l){
 			queryStr.append(" distinct c.constituencyId,c.name ");
 		}else{
 			queryStr.append(" distinct d.districtId,d.districtName ");
 		}
 		
 		queryStr.append(" ,sum(TUEI.totalRecords) " +
 				" from TabUserEnrollmentInfo TUEI "); 
 		if(constituencyId != null && constituencyId.longValue()>0l){
 			queryStr.append(" ,Constituency c  where c.constituencyId = TUEI.constituency.constituencyId " );
 		}else if(districtId != null && districtId.longValue()>0l){
 			queryStr.append(" ,Constituency c where c.district.districtId = :districtId  " );
 		}else{
 			queryStr.append(" ,District d where d.districtId = TUEI.districtId " );
 		}
 		
 		if(constituencyId != null && constituencyId.longValue()>0l){
 			queryStr.append(" and TUEI.constituency.constituencyId = :constituencyId ");
 		}else if(districtId != null && districtId.longValue()>0l){
 			queryStr.append(" and  c.constituencyId = TUEI.constituency.constituencyId");
 		}else{
 			if(stateId != null && stateId.longValue() == 1l){
 				queryStr.append(" and  (TUEI.districtId between 11 and 23) ");
 			}else if(stateId != null && stateId.longValue() == 36l && stateId.longValue() == 2l ){
				queryStr.append(" and  (TUEI.districtId between  1 and 10 ) ");
			}
 		}
 		 if(cadreSurveyUsers !=null && cadreSurveyUsers.size()>0){
 			queryStr.append("  and TUEI.cadreSurveyUserId in (:cadreSurveyUsers) " );
 		 }
 		if(startDate != null && endDate != null){
 			queryStr.append(" and (date(TUEI.surveyTime) between :startDate and :endDate) ");
 		 }
 		if(constituencyId != null && constituencyId.longValue()>0l){
 			queryStr.append(" group by c.constituencyId ");
 		}else if(districtId != null && districtId.longValue()>0l){   
 			queryStr.append(" group by c.constituencyId ");
 		}else {
 			queryStr.append(" group by d.districtId ");
 		}
 		
 		Query query = getSession().createQuery(queryStr.toString());
 		
 		
 	    if(constituencyId != null && constituencyId.longValue()>0l){
 			query.setParameter("constituencyId", constituencyId);	
 		}else if(districtId != null && districtId.longValue()>0l){
 			query.setParameter("districtId", districtId);
 		}
 		/*if(stateId != null && stateId.longValue() > 0l){
 			query.setParameter("stateId", stateId);
 		}*/
 	   if(cadreSurveyUsers !=null && cadreSurveyUsers.size()>0){
			query.setParameterList("cadreSurveyUsers", cadreSurveyUsers);
		}
 		if(startDate != null && endDate != null){
 			query.setDate("startDate", startDate);
 			query.setDate("endDate", endDate);
 		}
 		return query.list();
 		
 	}

}


