package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ILightMonitoringDAO;
import com.itgrids.model.LightMonitoring;

@Repository
public class LightMonitoringDAO extends GenericDaoHibernate<LightMonitoring, Long> implements ILightMonitoringDAO {

	@Autowired
	SessionFactory sessionFactory;

	public LightMonitoringDAO() {
		super(LightMonitoring.class);
	}

	@Override
	public List<LightMonitoring> getLiveDateForCurrentDateSelection(Date date) {

		//Query query = getSession().createQuery("select distinct model from LightMonitoring model where date(model.surveyDate) = :date and model.isDeleted ='N' ");
		Query query = getSession().createQuery("update  LightMonitoring model set model.isDeleted='Y' where date(model.surveyDate) = :date and model.isDeleted ='N' ");
		query.setDate("date", date);
		return query.list();
	}
	@Override
	public Integer updateLightMoitoringData(Date date,Long lightVendorId) {
		Query query = getSession().createQuery("update  LightMonitoring model set model.isDeleted='Y' where date(model.surveyDate) = :date and model.isDeleted ='N' and model.lightsVendorId=:lightVendorId ");
		query.setDate("date", date);
		query.setParameter("lightVendorId", lightVendorId);
		return query.executeUpdate();
	}
	@Override
	public Integer updateLightWattageMoitoringData(List<Long> lightMonitoringIds) {
		Query query = getSession().createQuery("update  LightWattage model set model.isDeleted='Y' where model.lightMonitoringId in (:lightMonitoringIds) and model.isDeleted ='N' ");
		query.setParameterList("lightMonitoringIds", lightMonitoringIds);
		return query.executeUpdate();
	}
	@Override
	public List<Long> getLightMonitroingIds(Date date,Long lightVendorId) {
		Query query = getSession().createQuery("select distinct model.lightMonitoringId from  LightMonitoring model where date(model.surveyDate) = :date and model.isDeleted ='N' and model.lightsVendorId=:lightVendorId ");
		query.setDate("date", date);
		query.setParameter("lightVendorId", lightVendorId);
		return query.list();
	}

	/*
	 * Date : 02/08/2017 Author :Swapna
	 */

	@Override
	public List<Object[]> getTotalVillagesDetails(Date fromDate, Date toDate, String locationType,List<Long> locationValues,List<Long> lightsVendorIds) {
		StringBuilder sb = new StringBuilder();
		sb.append("select model.lightsVendor.lightsVendorId,model.lightsVendor.vendorName," +
				" sum(model.totalLights),sum(model.totalPanels),sum(model.totalPoles),"
				+ "sum(model.workingLights),sum(model.onLights),sum(model.offLights),sum(model.notWorkingLights) "
				+ " from  LightMonitoring model where model.isDeleted ='N' and model.panchayat.locationAddress.state.stateId = 1 ");

		if( locationType != null && locationType.trim().length() > 0 && locationValues != null && locationValues.size() > 0){
			if(locationType.equalsIgnoreCase("district")){
				sb.append(" AND model.panchayat.locationAddress.district.districtId in(:locationValues) ");
			}else if(locationType.equalsIgnoreCase("parliament")){
				sb.append(" AND model.panchayat.locationAddress.parliament.constituencyId in(:locationValues) ");
			}else if(locationType.equalsIgnoreCase("constituency")){
				sb.append(" AND model.panchayat.locationAddress.constituency.constituencyId in(:locationValues) ");
			}else if(locationType.equalsIgnoreCase("mandal")){
				sb.append(" AND model.panchayat.locationAddress.tehsil.tehsilId in(:locationValues) ");
			}
		}
		if (lightsVendorIds != null && lightsVendorIds.size() > 0) {
			sb.append(" AND model.lightsVendor.lightsVendorId in (:lightsVendorIds)");
		}
		if (fromDate != null && toDate != null) {
			sb.append(" and  date(model.surveyDate) between :fromDate and :toDate ");
		}
		sb.append(" group by model.lightsVendor.lightsVendorId");
		Query query = getSession().createQuery(sb.toString());
		if (fromDate != null && toDate != null) {
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(locationType != null && locationType.trim().length() > 0 && locationValues != null && locationValues.size() > 0){
			query.setParameterList("locationValues",locationValues);
		}	
		if(lightsVendorIds != null && lightsVendorIds.size() > 0){
			query.setParameterList("lightsVendorIds",lightsVendorIds);
		}	
		return query.list();
	}
	/*
	 * Date : 02/08/2017 Author :Swapna
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getTotalSurveyDetails(Date startDate, Date toDate, String locationType,List<Long> locationValues,List<Long> lightsVendorIds,String isGroupRequired) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sbc = new StringBuilder();
		StringBuilder sbg = new StringBuilder();
		
		sb.append(" SELECT " 
				 + " LM.lightsVendor.lightsVendorId,LM.lightsVendor.vendorName," 
				 + " COUNT(DISTINCT LM.panchayat.locationAddress.district.districtId),"
				 + "COUNT(DISTINCT LM.panchayat.locationAddress.constituency.constituencyId),"
				 + " COUNT(DISTINCT LM.panchayat.locationAddress.tehsil.tehsilId), "
				 + "COUNT(DISTINCT LM.panchayat.panchayatId)  ");
		sb.append("  FROM LightMonitoring LM  WHERE LM.isDeleted ='N' and LM.panchayat.locationAddress.state.stateId = 1  ");
		
		if( locationType != null && locationType.trim().length() > 0 && locationValues != null && locationValues.size() > 0){
			if(locationType.equalsIgnoreCase("district")){
				sbc.append(" AND LM.panchayat.locationAddress.district.districtId in(:locationValues) ");
			}else if(locationType.equalsIgnoreCase("parliament")){
				sbc.append(" AND LM.panchayat.locationAddress.parliament.constituencyId in(:locationValues) ");
			}else if(locationType.equalsIgnoreCase("constituency")){
				sbc.append(" AND LM.panchayat.locationAddress.constituency.constituencyId in(:locationValues) ");
			}else if(locationType.equalsIgnoreCase("mandal")){
				sbc.append(" AND LM.panchayat.locationAddress.tehsil.tehsilId in(:locationValues) ");
			}
		}
		if (lightsVendorIds != null && lightsVendorIds.size() > 0) {
			sb.append(" AND LM.lightsVendor.lightsVendorId in (:lightsVendorIds)");
		}
		if (startDate != null && toDate != null) {
			sbc.append(" and date(LM.surveyDate) between :startDate and :toDate ");
		}
		if (isGroupRequired.equalsIgnoreCase("Yes")) {
			sbg.append(" group by LM.lightsVendor.lightsVendorId ");	
		}
		
		String queryStr = sb.toString() + sbc.toString()+sbg.toString();
		Query query = getSession().createQuery(queryStr);
		if(locationType != null && locationType.trim().length() > 0 && locationValues != null && locationValues.size() > 0){
			query.setParameterList("locationValues",locationValues);
		}	
		if(lightsVendorIds != null && lightsVendorIds.size() > 0){
			query.setParameterList("lightsVendorIds",lightsVendorIds);
		}
		if (startDate != null && toDate != null) {
			query.setDate("startDate", startDate);
			query.setDate("toDate", toDate);
		}
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getLocationsForLEDDashboard(String locationType,String filterType,List<Long> locationIds,String type,Date fromDate,Date toDate,List<Long> lightsVendorIds)
	{
		StringBuilder sb = new StringBuilder();
		StringBuilder sbc = new StringBuilder();
		StringBuilder sbg = new StringBuilder();
		
		sb.append("SELECT COUNT(DISTINCT tehsil.tehsilId), COUNT(DISTINCT model.panchayatId) ");
		
		if(locationType.equalsIgnoreCase("district"))
		{
			sb.append(",district.districtId, district.districtName ");
			sbg.append(" GROUP BY district.districtId ORDER BY district.districtId ");
		}
		else if(locationType.equalsIgnoreCase("parliament"))
		{
			sb.append(",parliament.constituencyId, parliament.name ");
			sbg.append(" GROUP BY parliament.constituencyId ORDER BY parliament.name ");
		}
		else if(locationType.equalsIgnoreCase("constituency"))
		{
			sb.append(",constituency.constituencyId, constituency.name ");
			sbg.append(" GROUP BY constituency.constituencyId ORDER BY constituency.name ");
		}
		else if(locationType.equalsIgnoreCase("mandal"))
		{
			sb.append(",tehsil.tehsilId,tehsil.tehsilName ");
			sbg.append(" GROUP BY tehsil.tehsilId ORDER BY tehsil.tehsilName ");
			
		} else if(locationType.equalsIgnoreCase("panchayat")) {
			sb.append(",model.panchayatId,model.panchayatName ");
			sbg.append(" GROUP BY model.panchayatId ORDER BY model.panchayatName ");
		}
		sb.append(", state.stateId, state.stateName,district.districtId,district.districtName," +
				 "  constituency.constituencyId,constituency.name,tehsil.tehsilId,tehsil.tehsilName, "
				 +" parliament.constituencyId,parliament.name ");
		
		if(locationType.equalsIgnoreCase("panchayat")) {
				sb.append(",model.panchayatId,model.panchayatName ");
		}
		
		
		sb.append("  FROM Panchayat model  ");
		sb.append("  left join model.locationAddress locationAddress "
				 + " left join locationAddress.district district "
				 + " left join locationAddress.state state "
				 + " left join locationAddress.constituency constituency "
				 + " left join locationAddress.parliament parliament "
				 + " left join locationAddress.tehsil  tehsil ");
		if (type.equalsIgnoreCase("filter")){
			sb.append(",LightMonitoring lm where lm.isDeleted='N' and  lm.panchayatId=model.panchayatId and state.stateId =1");
		}else{
			sb.append(" where state.stateId = 1 ");	
		}
		
		if(filterType != null && filterType.trim().length() > 0 && locationIds != null && locationIds.size() > 0)
		{
			if(filterType.equalsIgnoreCase("district")){
				sbc.append(" AND district.districtId in(:locationIds) ");
			}else if(filterType.equalsIgnoreCase("parliament")){
				sbc.append(" AND parliament.constituencyId in(:locationIds) ");
			}else if(filterType.equalsIgnoreCase("constituency")){
				sbc.append(" AND constituency.constituencyId in(:locationIds) ");
			}else if(filterType.equalsIgnoreCase("mandal")) {
				sbc.append(" AND tehsil.tehsilId in(:locationIds) ");
			}else if(filterType.equalsIgnoreCase("panchayat")) {
				sbc.append(" AND model.panchayatId in(:locationIds) ");
			}
		}
		if (fromDate != null && toDate != null) {
			 sbc.append(" and  date(lm.surveyDate) between :fromDate and :toDate ");
		}
		if (lightsVendorIds != null && lightsVendorIds.size() > 0) {
			sb.append(" AND lm.lightsVendor.lightsVendorId in (:lightsVendorIds)");
		}
		
		String queryStr = sb.toString() + sbc.toString()+sbg.toString();
		Query query = getSession().createQuery(queryStr);
		
		if(filterType != null && filterType.trim().length() > 0 && locationIds != null && locationIds.size() > 0)
		{
			query.setParameterList("locationIds",locationIds);
		}
		if (fromDate != null && toDate != null) {
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(lightsVendorIds != null && lightsVendorIds.size() > 0){
			query.setParameterList("lightsVendorIds",lightsVendorIds);
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLocationWiseDataForLEDDashboard(String locationType,String filterType,List<Long> filterValues,Date fromDate,Date toDate,List<Long> lightsVendorIds)
	{
		StringBuilder sb = new StringBuilder();
		StringBuilder sbc = new StringBuilder();
		StringBuilder sbg = new StringBuilder();
	
		sb.append(" SELECT COUNT(DISTINCT LM.panchayat.locationAddress.tehsil.tehsilId), COUNT(DISTINCT LM.panchayat.panchayatId) ");
		sb.append(",SUM(LM.totalPoles),SUM(LM.totalPanels),SUM(LM.totalLights),SUM(LM.workingLights),SUM(LM.onLights),sum(LM.offLights),sum(LM.notWorkingLights) ");	
		
		
		if(locationType.equalsIgnoreCase("district"))
		{	
			sb.append(",LM.panchayat.locationAddress.district.districtId ");
			sbg.append(" GROUP BY LM.panchayat.locationAddress.district.districtId ");
		}
		else if(locationType.equalsIgnoreCase("parliament"))
		{
			sb.append(",LM.panchayat.locationAddress.parliament.constituencyId ");
			sbg.append(" GROUP BY LM.panchayat.locationAddress.parliament.constituencyId ");
		}
		else if(locationType.equalsIgnoreCase("constituency"))
		{
			sb.append(",LM.panchayat.locationAddress.constituency.constituencyId ");
			sbg.append(" GROUP BY LM.panchayat.locationAddress.constituency.constituencyId ");
		}
		else if(locationType.equalsIgnoreCase("mandal"))
		{
			sb.append(",LM.panchayat.locationAddress.tehsil.tehsilId ");
			sbg.append(" GROUP BY LM.panchayat.locationAddress.tehsil.tehsilId ");
		}
		else if(locationType.equalsIgnoreCase("panchayat")) {
			sb.append(",LM.panchayat.panchayatId ");
			sbg.append(" GROUP BY LM.panchayat.panchayatId ");
		}
		sbg.append(",LM.lightsVendor.lightsVendorId ");
		
		sb.append(" ,LM.lightsVendor.lightsVendorId,LM.lightsVendor.vendorName FROM LightMonitoring LM  WHERE LM.panchayat.locationAddress.state.stateId = 1 and LM.isDeleted='N' ");
		
		if(filterType != null && filterType.trim().length() > 0 && filterValues != null && filterValues.size() > 0)
		{
			if(filterType.equalsIgnoreCase("district")){
				sbc.append(" AND LM.panchayat.locationAddress.district.districtId in(:filterValues) ");
			}else if(filterType.equalsIgnoreCase("parliament")){
				sbc.append(" AND LM.panchayat.locationAddress.parliament.constituencyId in(:filterValues) ");
			} else if(filterType.equalsIgnoreCase("constituency")) {
				sbc.append(" AND LM.panchayat.locationAddress.constituency.constituencyId in(:filterValues) ");
			}else if(filterType.equalsIgnoreCase("mandal")) {
				sbc.append(" AND LM.panchayat.locationAddress.tehsil.tehsilId in(:filterValues) ");
			}else if(filterType.equalsIgnoreCase("panchayat")) {
				sbc.append(" AND LM.panchayat.panchayatId in(:filterValues) ");
			}
		}
		if (fromDate != null && toDate != null) {
			 sbc.append(" and  date(LM.surveyDate) between :fromDate and :toDate ");
		}
		if (lightsVendorIds != null && lightsVendorIds.size() > 0) {
			sb.append(" AND LM.lightsVendor.lightsVendorId in (:lightsVendorIds)");
		}
		
		String queryStr = sb.toString() + sbc.toString()+sbg.toString();
		Query query = getSession().createQuery(queryStr);
		
		if(filterType != null && filterType.trim().length() > 0 && filterValues != null && filterValues.size() > 0)
		{
			query.setParameterList("filterValues",filterValues);
		}
		if (fromDate != null && toDate != null) {
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(lightsVendorIds != null && lightsVendorIds.size() > 0){
			query.setParameterList("lightsVendorIds",lightsVendorIds);
		}
		
		return query.list();
	}
	@Override
	public List<Long> isDataExist(Date fromDate, Date toDate) {
		StringBuilder sb = new StringBuilder();
		sb.append("select model.lightMonitoringId from  LightMonitoring model where model.isDeleted ='N' and model.panchayat.locationAddress.state.stateId = 1 ");
		if (fromDate != null && toDate != null) {
			sb.append(" and  date(model.surveyDate) between :fromDate and :toDate ");
		}
		Query query = getSession().createQuery(sb.toString());
		if (fromDate != null && toDate != null) {
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
			
		return query.list();
	}
	@Override
	public List<String> getMonthAndYear(Date fromDate,Date toDate){
        StringBuilder queryStr = new StringBuilder();
        
        queryStr.append(" select CONCAT(DATE_FORMAT(m1, '%M'),'-',year(m1)) as monthYear " +
                           "    from ( " +
                               "    select " +
                               "    (:fromDate - INTERVAL DAYOFMONTH(:fromDate)-1 DAY)  " +
                               "        +INTERVAL m MONTH as m1 " +
                               "    from ( " +
                                   "    select @rownum /*'*/:=/*'*/ @rownum+1 as m from " +
                                   "    (select 1 union select 2 union select 3 union select 4 union select 5) t1, " +
                                   "     (select 1 union select 2 union select 3 union select 4 union select 5) t2, " +
                                   "     (select 1 union select 2 union select 3 union select 4 union select 5) t3,  " +
                                   "     (select 1 union select 2 union select 3 union select 4 union select 5) t4, " +
                                   "     (select @rownum /*'*/:=/*'*/ -1) t0  " +
                                   "      ) d1 " +
                               " ) d2 " +
                               "  where m1<=:toDate " +
                               " order by m1");
        
          Session session = getSession();
          SQLQuery sqlQuery = session.createSQLQuery(queryStr.toString());
          sqlQuery.addScalar("monthYear",StandardBasicTypes.STRING);
          sqlQuery.setDate("fromDate", fromDate);
          sqlQuery.setDate("toDate", toDate);
          return sqlQuery.list();
   }
}
