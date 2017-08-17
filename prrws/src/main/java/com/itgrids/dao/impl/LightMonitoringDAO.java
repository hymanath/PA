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
	public Integer updateLightMoitoringData(Date date) {
		Query query = getSession().createQuery("update  LightMonitoring model set model.isDeleted='Y' where date(model.surveyDate) = :date and model.isDeleted ='N' ");
		query.setDate("date", date);
		return query.executeUpdate();
	}
	@Override
	public Integer updateLightWattageMoitoringData(List<Long> lightMonitoringIds) {
		Query query = getSession().createQuery("update  LightWattage model set model.isDeleted='Y' where model.lightMonitoringId in (:lightMonitoringIds) and model.isDeleted ='N' ");
		query.setParameterList("lightMonitoringIds", lightMonitoringIds);
		return query.executeUpdate();
	}
	@Override
	public List<Long> getLightMonitroingIds(Date date) {
		Query query = getSession().createQuery("select distinct model.lightMonitoringId from  LightMonitoring model where date(model.surveyDate) = :date and model.isDeleted ='N' ");
		query.setDate("date", date);
		return query.list();
	}

	/*
	 * Date : 02/08/2017 Author :Swapna
	 */

	@Override
	public List<Object[]> getTotalVillagesDetails(Date fromDate, Date toDate, String locationType,Long locationValues) {
		StringBuilder sb = new StringBuilder();
		sb.append("select sum(model.totalLights),sum(model.totalPanels),sum(model.totalPoles),"
				+ "sum(model.workingLights),sum(model.onLights),sum(model.offLights),sum(model.notWorkingLights) "
				+ " from  LightMonitoring model where model.isDeleted ='N' and model.panchayat.locationAddress.state.stateId = 1 ");

		if( locationType != null && locationType.trim().length() > 0 && locationValues != null && locationValues.longValue() > 0){
			if(locationType.equalsIgnoreCase("district")){
				sb.append(" AND model.panchayat.locationAddress.district.districtId = :locationValue ");
			}else if(locationType.equalsIgnoreCase("parliament")){
				sb.append(" AND model.panchayat.locationAddress.parliament.constituencyId = :locationValue ");
			}else if(locationType.equalsIgnoreCase("constituency")){
				sb.append(" AND model.panchayat.locationAddress.constituency.constituencyId = :locationValue ");
			}else if(locationType.equalsIgnoreCase("mandal")){
				sb.append(" AND model.panchayat.locationAddress.tehsil.tehsilId = :locationValue ");
			}
		}
		if (fromDate != null && toDate != null) {
			sb.append(" and  date(model.surveyDate) between :fromDate and :toDate ");
		}
		Query query = getSession().createQuery(sb.toString());
		if (fromDate != null && toDate != null) {
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(locationType != null && locationType.trim().length() > 0 && locationValues != null && locationValues.longValue() > 0){
			query.setParameter("locationValue",locationValues);
		}	
		return query.list();
	}
	/*
	 * Date : 02/08/2017 Author :Swapna
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getTotalSurveyDetails(Date startDate, Date toDate, String locationType,Long locationValues) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sbc = new StringBuilder();
		StringBuilder sbg = new StringBuilder();
		
		sb.append("SELECT COUNT(DISTINCT LM.panchayat.locationAddress.district.districtId),"
				 + "COUNT(DISTINCT LM.panchayat.locationAddress.constituency.constituencyId),"
				+ " COUNT(DISTINCT LM.panchayat.locationAddress.tehsil.tehsilId), "
				+ "COUNT(DISTINCT LM.panchayat.panchayatId)  ");
		sb.append("  FROM LightMonitoring LM  WHERE LM.isDeleted ='N' and LM.panchayat.locationAddress.state.stateId = 1  ");
		
		if( locationType != null && locationType.trim().length() > 0 && locationValues != null && locationValues.longValue() > 0){
			if(locationType.equalsIgnoreCase("district")){
				sbc.append(" AND LM.panchayat.locationAddress.district.districtId = :locationValue ");
			}else if(locationType.equalsIgnoreCase("parliament")){
				sbc.append(" AND LM.panchayat.locationAddress.parliament.constituencyId = :locationValue ");
			}else if(locationType.equalsIgnoreCase("constituency")){
				sbc.append(" AND LM.panchayat.locationAddress.constituency.constituencyId = :locationValue ");
			}else if(locationType.equalsIgnoreCase("mandal")){
				sbc.append(" AND LM.panchayat.locationAddress.tehsil.tehsilId = :locationValue ");
			}
		}
		if (startDate != null && toDate != null) {
			sbc.append(" and date(LM.surveyDate) between :startDate and :toDate ");
		}
		String queryStr = sb.toString() + sbc.toString()+sbg.toString();
		Query query = getSession().createQuery(queryStr);
		if(locationType != null && locationType.trim().length() > 0 && locationValues != null && locationValues.longValue() > 0){
			query.setParameter("locationValue",locationValues);
		}		
		if (startDate != null && toDate != null) {
			query.setDate("startDate", startDate);
			query.setDate("toDate", toDate);
		}
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getLocationsForLEDDashboard(String locationType,String filterType,Long locationId)
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
		}
		sb.append(", state.stateId, state.stateName,district.districtId,district.districtName," +
				 " constituency.constituencyId,constituency.name,tehsil.tehsilId,tehsil.tehsilName, "
				 +"panchayat.panchayatId,panchayat.panchayatName,parliament.constituencyId,parliament.name ");
		
		sb.append("  FROM Panchayat model  ");
		sb.append("  left join model.locationAddress locationAddress "
				 + " left join locationAddress.district district "
				 + " left join locationAddress.state state "
				 + " left join locationAddress.constituency constituency "
				 + " left join locationAddress.parliament parliament "
				 + " left join locationAddress.tehsil  tehsil "
				 + " left join locationAddress.panchayat panchayat ");
		sb.append(" where state.stateId =1 ");
		if(filterType != null && filterType.trim().length() > 0 && locationId != null && locationId.longValue() > 0)
		{
			if(filterType.equalsIgnoreCase("district")){
				sbc.append(" AND district.districtId = :locationId ");
			}else if(filterType.equalsIgnoreCase("parliament")){
				sbc.append(" AND parliament.constituencyId = :locationId ");
			}else if(filterType.equalsIgnoreCase("constituency")){
				sbc.append(" AND constituency.constituencyId = :locationId ");
			}else if(filterType.equalsIgnoreCase("mandal")) {
				sbc.append(" AND tehsil.tehsilId = :locationId ");
			}
		}
		
		String queryStr = sb.toString() + sbc.toString()+sbg.toString();
		Query query = getSession().createQuery(queryStr);
		
		if(filterType != null && filterType.trim().length() > 0 && locationId != null && locationId.longValue() > 0)
		{
			query.setParameter("locationId",locationId);
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLocationWiseDataForLEDDashboard(String locationType,String filterType,Long filterValue,Date fromDate,Date toDate)
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
		
		sb.append(" FROM LightMonitoring LM  WHERE LM.panchayat.locationAddress.state.stateId = 1 and LM.isDeleted='N' ");
		
		if(filterType != null && filterType.trim().length() > 0 && filterValue != null && filterValue.longValue() > 0)
		{
			if(filterType.equalsIgnoreCase("district")){
				sbc.append(" AND LM.panchayat.locationAddress.district.districtId = :filterValue ");
			}else if(filterType.equalsIgnoreCase("parliament")){
				sbc.append(" AND LM.panchayat.locationAddress.parliament.constituencyId = :filterValue ");
			} else if(filterType.equalsIgnoreCase("constituency")) {
				sbc.append(" AND LM.panchayat.locationAddress.constituency.constituencyId = :filterValue ");
			}else if(filterType.equalsIgnoreCase("mandal")) {
				sbc.append(" AND LM.panchayat.locationAddress.tehsil.tehsilId = :filterValue ");
			}
		}
		if (fromDate != null && toDate != null) {
			 sbc.append(" and  date(LM.surveyDate) between :fromDate and :toDate ");
		}
		
		String queryStr = sb.toString() + sbc.toString()+sbg.toString();
		Query query = getSession().createQuery(queryStr);
		
		if(filterType != null && filterType.trim().length() > 0 && filterValue != null && filterValue.longValue() > 0)
		{
			query.setParameter("filterValue",filterValue);
		}
		if (fromDate != null && toDate != null) {
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
	
		
		return query.list();
	}
}
