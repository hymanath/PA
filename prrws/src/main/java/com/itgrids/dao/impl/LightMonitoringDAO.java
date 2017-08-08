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
			sb.append(" and  model.surveyDate between :fromDate and :toDate ");
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
		sb.append(" SELECT COUNT(DISTINCT D.districtId)," + " COUNT(DISTINCT C.constituencyId),"
				+ " COUNT(DISTINCT T.tehsilId)," + " COUNT(DISTINCT P.panchayatId) " + " FROM " + " LightMonitoring LM "
				+ " LEFT OUTER JOIN Panchayat P on LM.panchayatId = P.panchayatId "
				+ " LEFT OUTER JOIN LocationAddress LA on P.locationAddressId= LA.locationAddressId "
				+ " LEFT OUTER JOIN Tehsil T on LA.tehsilId = T.tehsilId "
				+ " LEFT OUTER JOIN District D on LA.districtId = D.districtId "
				+ " LEFT OUTER JOIN Constituency C on LA.constituencyId =C.constituencyId ");

		sb.append(" where D.districtId between 11 and 23 and LM.isDeleted = 'N' ");
		if (startDate != null && toDate != null) {
			sb.append(" and LM.surveyDate between :startDate and :toDate ");
		}
		Query query = getSession().createSQLQuery(sb.toString());
		if (startDate != null && toDate != null) {
			query.setDate("startDate", startDate);
			query.setDate("toDate", toDate);
		}
		return query.list();
	}

	/*
	 * Date : 03/08/2017 Author :Swapna
	 */
	/*
	 * @Override public List<Object[]> getAllDitrictWiseSurveyDetails(Date
	 * startDate,Date endDate,Long locationId,String clickType) { StringBuilder
	 * sb = new StringBuilder();
	 * 
	 * sb.
	 * append("select distinct D.district_id,D.districtName,PA.parliamentId, C.constituency_id, T.tehsil_id,T.tehsilName"
	 * + "sum(LM.totalPoles)," + "sum(LM.totalPanels)," + "sum(LM.totalLights),"
	 * + "sum(LM.workingLights)," + "sum(LM.onLights)," + "sum(LM.offLights)," +
	 * "sum(LM.notWorkingLights) " + "FROM " + " light_monitoring LM " +
	 * " LEFT OUTER JOIN panchayat P on LM.panchayat_id = P.panchayat_id " +
	 * " LEFT OUTER JOIN location_address LA on P.location_address_id = LA.location_address_id "
	 * + " LEFT OUTER JOIN tehsil T on LA.tehsil_id = T.tehsil_id " +
	 * " LEFT OUTER JOIN district D on LA.district_id = D.district_id " +
	 * " LEFT OUTER JOIN constituency C on LA.constituency_id = C.constituency_id "
	 * +
	 * " LEFT OUTER JOIN ParliamentAssembly PA on LA.parliamentId = PA.parliamentId "
	 * ); if(startDate!= null && endDate!=null){
	 * sb.append(" date(LM.insertredTime) between :startDate and :endDate and "
	 * ); } if(clickType!=null && clickType.equalsIgnoreCase("district")) {
	 * sb.append("D.district=:locationId"); } if(clickType!=null &&
	 * clickType.equalsIgnoreCase("constituency")) {
	 * sb.append("C.constituency=:locationId"); } if(clickType!=null &&
	 * clickType.equalsIgnoreCase("tehsil")) {
	 * sb.append("T.tehsil=:locationId"); } if(locationId!=null&&
	 * locationId.longValue()>0) sb.append(" GROUP BY  LA.districtId "); Query
	 * query = getSession().createQuery(sb.toString());
	 * query.setParameter("startDate", startDate); query.setParameter("endDate",
	 * endDate); if(locationId!=null&& locationId.longValue()>0)
	 * query.setParameter("locationId", locationId);
	 * 
	 * return query.list(); }
	 */
	/*
	 * Date : 07/08/2017 Author :Swapna
	 * 
	 * @description : getDistrictLevelCount
	 */
	@Override
	public List<Object[]> getAllDitrictWiseSurveyDetails(Date fromDate, Date toDate, String year,
			List<Long> locationValues, Long locationTypeId, Long searchlevelId, List<Long> searchLevelValues) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sbm = new StringBuilder();
		StringBuilder sbe = new StringBuilder();
		StringBuilder sbg = new StringBuilder();
		
		sb.append("SELECT  sum(LM.totalPoles),sum(LM.totalPanels),sum(LM.totalLights),sum(LM.workingLights),sum(LM.onLights),sum(LM.offLights) ");
		sbm.append(" FROM  LightMonitoring  LM, Panchayat P, LocationAddress  LA  ");
		sbe.append(" WHERE  LM.panchayatId = P.panchayatId  AND  P.locationAddressId  = LA.locationAddressId ");

		if (year != null && !year.trim().isEmpty()) 
		{
			sbe.append(" and year(LM.surveyDate) =:year  ");
		} 
		else if (fromDate != null && toDate != null) {
			sb.append(" and date(LM.surveyDate) between :fromDate and :toDate ");
		}
		
		sbg.append(" GROUP BY ");
		
		if (locationTypeId != null && locationTypeId.longValue() > 0l) 
		{
			if (locationTypeId == 2l) 
			{
				sb.append(" ,LA.district.state.stateId , LA.district.state.stateName  ");
				sbg.append(" LA.stateId ");
				if (locationValues != null && locationValues.size() > 0) {
					sbe.append(" and  LA.district.state.stateId in (:locationValues) ");
				}
			}
			else if (locationTypeId == 3l) {
				sb.append("  LA.district.districtId ,LA.district.districtName, COUNT(DISTINCT LA.tehsil.tehsilId) ");
				sbg.append(" LA.districtId ");
				
				if (locationValues != null && locationValues.size() > 0) {
					sbe.append(" AND LA.district.districtId IN (:locationValues)  ");
				}
			} 
			else if (locationTypeId == 4l) {
				sb.append(" LA.constituency.constituencyId ");
				sbg.append(" LA.constituencyId ");

				if (locationValues != null && locationValues.size() > 0) {
					sbe.append(" and LA.constituency.constituencyId in (:locationValues) ");
				}
			}
			else if (locationTypeId == 5l) {
				sb.append("  LA.tehsil.tehsilId,LA.tehsil.tehsilName ");
				sbg.append(" LA.tehsil.tehsilId ");
				if (locationValues != null && locationValues.size() > 0) {
					sbe.append(" AND LA.tehsil.tehsilId  IN (:locationValues) ");
				}
			} 
			else if (locationTypeId == 10l) 
			{
				sb.append(" LA.parliament.constituencyId, LA.parliament.name ");
				sbg.append(" LA.parliament.constituencyId ");
				if (locationValues != null && locationValues.size() > 0) {
					sbe.append(" AND PA.parliament.constituencyId IN (:locationValues) ");
				}
			}
		}

		if (searchlevelId != null && searchlevelId.longValue() > 0l && searchLevelValues != null
				&& searchLevelValues.size() > 0) {
			if (searchlevelId == 2l) {
				sbe.append("  and LA.stateId in (:searchLevelValues)  ");
			} else if (searchlevelId == 3l) {
				sbe.append("  and LA.districtId in (:searchLevelValues)  ");
			} else if (searchlevelId == 4l) {
				sbe.append(" and LA.constituencyId in (:searchLevelValues) ");
			} else if (searchlevelId == 10l) {
				sbe.append(" and LA.parliamentId  in (:searchLevelValues) ");
			} else if (searchlevelId == 5l) {
				sbe.append(" and LA.tehsilId  in (:searchLevelValues) ");
			}
		}
		
		
		
		sb.append(sbm.toString()).append(sbe.toString()).append(sbg.toString());
		System.out.println(sb.toString());

		Query query = getSession().createQuery(sb.toString());
		if (fromDate != null && toDate != null) {
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}

		if (year != null && !year.trim().isEmpty()) {
			query.setParameter("year", Integer.parseInt(year));
		}

		if (locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null
				&& locationValues.size() > 0) {
			query.setParameterList("locationValues", locationValues);
		}
		if (searchlevelId != null && searchlevelId.longValue() > 0l && searchLevelValues != null
				&& searchLevelValues.size() > 0) {
			query.setParameterList("searchLevelValues", searchLevelValues);
		}

		return query.list();
	}

	
	@Override
	public List<Object[]> getConstituencyLevelWiseSurveyDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> getMandalLevelWiseSurveyDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> getParlaimentWiseSurveyDetails() {

		return null;
	}
}
