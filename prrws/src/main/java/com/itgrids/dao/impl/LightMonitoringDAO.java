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
		Query query = getSession().createQuery(
				" select model from LightMonitoring model where date(model.surveyDate) = :date and model.isDeleted ='N' ");

		query.setDate("date", date);
		return query.list();
	}

	/*
	 * Date : 02/08/2017 Author :Swapna
	 */

	@Override
	public List<Object[]> getTotalVillagesDetails(Date fromDate, Date toDate) {
		StringBuilder sb = new StringBuilder();
		sb.append("select sum(model.totalLights),sum(model.totalPanels),sum(model.totalPoles),"
				+ "sum(model.workingLights),sum(model.onLights),sum(model.offLights),sum(model.notWorkingLights) "
				+ " from  LightMonitoring model where model.isDeleted ='N' ");

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
	/*
	 * Date : 02/08/2017 Author :Swapna
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getTotalSurveyDetails(Date startDate, Date toDate) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT COUNT(DISTINCT LM.panchayat.locationAddress.district.districtId),"
				 + "COUNT(DISTINCT LM.panchayat.locationAddress.constituency.constituencyId),"
				+ " COUNT(DISTINCT LM.panchayat.locationAddress.tehsil.tehsilId), "
				+ "COUNT(DISTINCT LM.panchayat.panchayatId)  from  LightMonitoring  LM  ");

		sb.append(" where LM.panchayat.locationAddress.district.districtId  between 11 and 23 and LM.isDeleted = 'N' and LM.surveyDate is not null ");
		if (startDate != null && toDate != null) {
			sb.append(" and LM.surveyDate between :startDate and :toDate ");
		}
		Query query = getSession().createQuery(sb.toString());
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
		
		sb.append("SELECT COUNT(DISTINCT P.locationAddress.tehsil.tehsilId), COUNT(DISTINCT P.panchayatId) ");
		
		if(locationType.equalsIgnoreCase("district"))
		{
			sb.append(",P.locationAddress.district.districtId, P.locationAddress.district.districtName ");
			sbg.append(" GROUP BY P.locationAddress.district.districtId ORDER BY P.locationAddress.district.districtId ");
		}
		else if(locationType.equalsIgnoreCase("parliament"))
		{
			sb.append(",P.locationAddress.parliament.constituencyId, P.locationAddress.parliament.name ");
			sbg.append(" GROUP BY P.locationAddress.parliament.constituencyId ORDER BY P.locationAddress.parliament.name ");
		}
		else if(locationType.equalsIgnoreCase("constituency"))
		{
			sb.append(",P.locationAddress.constituency.constituencyId, P.locationAddress.constituency.name ");
			sbg.append(" GROUP BY P.locationAddress.constituency.constituencyId ORDER BY P.locationAddress.district.districtId,P.locationAddress.constituency.name ");
		}
		
		else if(locationType.equalsIgnoreCase("mandal"))
		{
			sb.append(",P.locationAddress.tehsil.tehsilId, P.locationAddress.tehsil.tehsilName ");
			sbg.append(" GROUP BY P.locationAddress.tehsil.tehsilId ORDER BY P.locationAddress.district.districtId,P.locationAddress.constituency.name,P.locationAddress.tehsil.tehsilName ");
		}
			
		sb.append(" FROM Panchayat P WHERE P.locationAddress.state.stateId = 1 ");
		
		if(filterType != null && filterType.trim().length() > 0 && locationId != null && locationId.longValue() > 0)
		{
			if(filterType.equalsIgnoreCase("district")){
				sbc.append(" AND P.locationAddress.district.districtId = :locationId ");
			}else if(filterType.equalsIgnoreCase("parliament")){
				sbc.append(" AND P.locationAddress.parliament.constituencyId = :locationId ");
			}else if(filterType.equalsIgnoreCase("constituency")){
				sbc.append(" AND P.locationAddress.constituency.constituencyId = :locationId ");
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
		sb.append(",SUM(LM.totalPoles),SUM(LM.totalPanels),SUM(LM.totalLights),SUM(LM.workingLights),SUM(LM.onLights),sum(LM.offLights) ");	
		
		
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
