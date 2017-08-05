 package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ILightMonitoringDAO;
import com.itgrids.model.LightMonitoring;


@Repository
public class LightMonitoringDAO extends GenericDaoHibernate<LightMonitoring ,Long> implements ILightMonitoringDAO{

	@Autowired
	SessionFactory sessionFactory; 

	public LightMonitoringDAO() {
		super(LightMonitoring.class);
	}
	
	@Override
	public List<LightMonitoring> getLiveDateForCurrentDateSelection(Date date) {
		Query query = getSession().createQuery(" select model from LightMonitoring model where date(model.surveyDate) = :date and model.isDeleted ='N' ");
		
	      query.setDate("date", date);
	return  query.list();
	}

	/*
 	 * Date : 02/08/2017
 	 * Author :Swapna
 	  	 */
     	
	@Override
	public List<Object[]> getTotalVillagesDetails(Date fromDate,Date toDate) {
		 StringBuilder sb = new StringBuilder();
		 sb.append("select sum(model.totalLights),sum(model.totalPanels),sum(model.totalPoles),"
		 		+ "sum(model.workingLights),sum(model.onLights),sum(model.offLights),sum(model.notWorkingLights) "
		 		+ " from  LightMonitoring model where model.isDeleted ='N' ");
		 
		 if(fromDate != null && toDate != null){
			 sb.append(" and  model.surveyDate between :fromDate and :toDate ");
		 }
		 Query query = getSession().createQuery(sb.toString());
		 if(fromDate != null && toDate != null){
			 query.setDate("fromDate", fromDate);
			 query.setDate("toDate", toDate);
		 }
		 
		 return query.list();
	}
	/*
 	 * Date : 02/08/2017
 	 * Author :Swapna
 	  */
     
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getTotalSurveyDetails(Date startDate,Date toDate) {
		 StringBuilder sb = new StringBuilder();
		 sb.append(" SELECT COUNT(DISTINCT D.district_id),"
		 		+ " COUNT(DISTINCT C.constituency_id),"
		 		+ " COUNT(DISTINCT T.tehsil_id),"
		 		+ " COUNT(DISTINCT P.panchayat_id) "
		 		+ " FROM "
		 		+ " light_monitoring LM "
		 		+ " LEFT OUTER JOIN panchayat P on LM.panchayat_id = P.panchayat_id "
		 		+ " LEFT OUTER JOIN location_address LA on P.location_address_id = LA.location_address_id "
		 		+ " LEFT OUTER JOIN tehsil T on LA.tehsil_id = T.tehsil_id "
		 		+ " LEFT OUTER JOIN district D on LA.district_id = D.district_id "
		 		+ " LEFT OUTER JOIN constituency C on LA.constituency_id =C.constituency_id ");
		 
		 sb.append(" where D.district_id between 11 and 23 and LM.is_deleted = 'N' ");
		 if(startDate != null && toDate != null){
			 sb.append(" and LM.survey_date between :startDate and :toDate ");
		 }
	 Query query = getSession().createSQLQuery(sb.toString());
		 if(startDate != null && toDate != null){
			 query.setDate("startDate", startDate);
			 query.setDate("toDate", toDate);
		 }
	 return query.list();
	}
	/*
 	 * Date : 03/08/2017
 	 * Author :Swapna
 	 */
     
	@Override
	public List<Object[]> getAllDitrictWiseSurveyDetails(Date startDate,Date endDate) {
		 StringBuilder sb = new StringBuilder();
		 
		 sb.append("select distinct d.districtId,d.districtName,sum( distinct LA.tehsil_id),sum(LM.totalPoles),sum(LM.totalPanels),sum(LM.totalLights),"
		 		+ " sum(LM.workingLights),sum(LM.onLights),sum(LM.offLights),sum(LM.notWorkingLights) FROM "
		 		+ " LightMonitoring LM,Panchayat P,LocationAddress LA,District d WHERE   LM.panchayatId = P.panchayatId AND "
		 		+ " P.locationAddressId = LA.locationAddressId AND  "
		 		+ " LA.districtId=D.districtId ");
		 
		 		if(startDate!= null && endDate!=null){
		 			sb.append(" date(LM.insertredTime) between :startDate and :endDate and ");
		 		}
		 		sb.append(" GROUP BY  LA.districtId ");
        
	 Query query = getSession().createQuery(sb.toString());
	 	if(startDate!= null && endDate!=null){
	 		 query.setDate("startDate", startDate);
			 query.setDate("endDate", endDate);
		}
	 return query.list();
	}
	/*
 	 * Date : 03/08/2017
 	 * Author :Swapna
 	 */
     
	@Override
	public List<Object[]> getConstituencyLevelWiseSurveyDetails() {
		StringBuilder sb = new StringBuilder();
		 sb.append("select distinct LA.districtId,d.districtName,C.constituencyId,C.name as constituencyName, sum(LM.totalPoles),sum(LM.totalPanels), sum(LM.totalLights),"
		 		+ "sum(LM.workingLights),sum(LM.onLights),sum(LM.offLights),sum(LM.notWorkingLights) FROM "
		 		+ "LightMonitoring LM,Panchayat P,LocationAddress LA,District d,Constituency C WHERE  and LM.panchayatId = P.panchayatId AND "
		 		+ "P.locationAddressId = LA.locationAddressId AND  LA.districtId=D.districtId and LA.constituencyId=C.constituencyId  LM.insertredTime = LM.insertredTime GROUP BY "
		 		+ "LA.districtId,LA.constituencyId");
       
	 Query query = getSession().createQuery(sb.toString());
	 return query.list();
	}
	/*
 	 * Date : 03/08/2017
 	 * Author :Swapna
 	 */
     
	@Override
	public List<Object[]> getMandalLevelWiseSurveyDetails() {
		StringBuilder sb = new StringBuilder();
		 sb.append("select distinct LA.districtId,d.districtName,C.constituencyId,C.name as constituencyName,T.tehsilId,T.tehsilName,sum(LM.totalPoles),sum(LM.totalPanels), sum(LM.totalLights),"
		 		+ "sum(LM.workingLights),sum(LM.onLights),sum(LM.offLights),sum(LM.notWorkingLights) FROM "
		 		+ "LightMonitoring LM,Panchayat P,,LocationAddress LA,District d,Constituency C,Tehsil T WHERE PR.panchayatId=P.panchayatId and LM.panchayatId = P.panchayatId AND "
		 		+ "P.locationAddressId = LA.locationAddressId AND  LA.districtId=D.districtId and LA.constituencyId=C.constituencyId and LA.tehsilId=T.tehsilId and LM.insertredTime = LM.insertredTime GROUP BY "
		 		+ "LA.districtId,LA.constituencyId,LA.tehsilId");
       
	 Query query = getSession().createQuery(sb.toString());
	 return query.list();
	}
	
	@Override
	public List<Object[]> getParlaimentWiseSurveyDetails() {
		StringBuilder sb = new StringBuilder();
		 sb.append("select distinct LA.districtId,d.districtName,C.constituencyId,C.name as constituencyName,T.tehsilId,T.tehsilName,sum(LM.totalPoles),sum(LM.totalPanels), sum(LM.totalLights),"
		 		+ "sum(LM.workingLights),sum(LM.onLights),sum(LM.offLights),sum(LM.notWorkingLights) FROM "
		 		+ "LightMonitoring LM,Panchayat P,,LocationAddress LA,District d,Constituency C,Tehsil T WHERE PR.panchayatId=P.panchayatId and LM.panchayatId = P.panchayatId AND "
		 		+ "P.locationAddressId = LA.locationAddressId AND  LA.districtId=D.districtId and LA.constituencyId=C.constituencyId and LA.tehsilId=T.tehsilId and LM.insertredTime = LM.insertredTime GROUP BY "
		 		+ "LA.districtId,LA.constituencyId,LA.tehsilId");
       
	 Query query = getSession().createQuery(sb.toString());
	 return query.list();
	}

}



			