package com.itgrids.dao.impl;



import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ILightWattageDAO;
import com.itgrids.model.LightWattage;
@Repository
public class LightWattageDAO extends GenericDaoHibernate<LightWattage ,Long> implements ILightWattageDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	public LightWattageDAO()
	{
	super(LightWattage.class);

}
	@Override
	public List<Object[]> getTotalWattege(Date fromDate,Date toDate, String locationType,List<Long> locationValues) {
		StringBuilder sb = new StringBuilder();
		 sb.append("select model.wattage ,sum(model.lightCount) "
				 +" from "
				+ " LightWattage model  "
		 		+ " where model.isDeleted = 'N' and model.lightMonitoring.isDeleted ='N' ");
		 if( locationType != null && locationType.trim().length() > 0 && locationValues != null && locationValues.size() > 0){
				if(locationType.equalsIgnoreCase("district")){
					sb.append(" AND model.lightMonitoring.panchayat.locationAddress.district.districtId in(:locationValues) ");
				}else if(locationType.equalsIgnoreCase("parliament")){
					sb.append(" AND model.lightMonitoring.panchayat.locationAddress.parliament.constituencyId in(:locationValues) ");
				}else if(locationType.equalsIgnoreCase("constituency")){
					sb.append(" AND model.lightMonitoring.panchayat.locationAddress.constituency.constituencyId in(:locationValues) ");
				}else if(locationType.equalsIgnoreCase("mandal")){
					sb.append(" AND model.lightMonitoring.panchayat.locationAddress.tehsil.tehsilId in(:locationValues) ");
				}
			}
		 if(fromDate != null && toDate != null){
			 sb.append(" and date(model.lightMonitoring.surveyDate) between :fromDate and :toDate ");
		 }
		 sb.append(" group by model.wattage ");
		 
		 Query query = getSession().createQuery(sb.toString());
		 if(fromDate != null && toDate != null){
			 query.setDate("fromDate", fromDate);
			 query.setDate("toDate", toDate);
		 }
		 if(locationType != null && locationType.trim().length() > 0 && locationValues != null && locationValues.size() > 0){
				query.setParameterList("locationValues",locationValues);
		 }
		return query.list();
	}
	@Override
	public int deleteAllLightWattageDetails(Date surveyDate) {
		Query query =getSession().createQuery("delete from LightWattage where lightMonitoringId in"
				+ "(select lightMonitoringId from LightMonitoring  where surveyDate =:surveyDate)");
		query.setParameter("surveyDate", surveyDate);
		return query.executeUpdate();	
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getLocationWiseLightWattageDtls(String locationType,String filterType,Long filterValue,Date fromDate,Date toDate)
	{
		StringBuilder sb = new StringBuilder();
		StringBuilder sbc = new StringBuilder();
		StringBuilder sbg = new StringBuilder();
	
		sb.append(" SELECT  LM.wattage,sum(LM.lightCount) ");
		if(locationType.equalsIgnoreCase("district"))
		{	
			sb.append(",LM.lightMonitoring.panchayat.locationAddress.district.districtId ");
			sbg.append(" GROUP BY LM.lightMonitoring.panchayat.locationAddress.district.districtId ");
		}
		else if(locationType.equalsIgnoreCase("parliament"))
		{
			sb.append(",LM.lightMonitoring.panchayat.locationAddress.parliament.constituencyId ");
			sbg.append(" GROUP BY LM.lightMonitoring.panchayat.locationAddress.parliament.constituencyId ");
		}
		else if(locationType.equalsIgnoreCase("constituency"))
		{
			sb.append(",LM.lightMonitoring.panchayat.locationAddress.constituency.constituencyId ");
			sbg.append(" GROUP BY LM.lightMonitoring.panchayat.locationAddress.constituency.constituencyId ");
		}
		else if(locationType.equalsIgnoreCase("mandal"))
		{
			sb.append(",LM.lightMonitoring.panchayat.locationAddress.tehsil.tehsilId ");
			sbg.append(" GROUP BY LM.lightMonitoring.panchayat.locationAddress.tehsil.tehsilId ");
		}
		sbg.append(",LM.wattage");
		
		sb.append(" FROM LightWattage LM  WHERE LM.lightMonitoring.panchayat.locationAddress.state.stateId = 1 and LM.isDeleted='N' and LM.lightMonitoring.isDeleted='N' ");
		
		if(filterType != null && filterType.trim().length() > 0 && filterValue != null && filterValue.longValue() > 0)
		{
			if(filterType.equalsIgnoreCase("district")){
				sbc.append(" AND LM.lightMonitoring.panchayat.locationAddress.district.districtId = :filterValue ");
			}else if(filterType.equalsIgnoreCase("parliament")){
				sbc.append(" AND LM.lightMonitoring.panchayat.locationAddress.parliament.constituencyId = :filterValue ");
			} else if(filterType.equalsIgnoreCase("constituency")) {
				sbc.append(" AND LM.lightMonitoring.panchayat.locationAddress.constituency.constituencyId = :filterValue ");
			}else if(filterType.equalsIgnoreCase("mandal")) {
				sbc.append(" AND LM.lightMonitoring.panchayat.locationAddress.tehsil.tehsilId = :filterValue ");
			}
		}
		if (fromDate != null && toDate != null) {
			 sbc.append(" and  date(LM.lightMonitoring.surveyDate) between :fromDate and :toDate ");
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
