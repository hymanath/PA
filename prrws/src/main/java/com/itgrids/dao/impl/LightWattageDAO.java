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
	public List<Object[]> getTotalWattege(Date fromDate,Date toDate, String locationType,Long locationValue) {
		StringBuilder sb = new StringBuilder();
		 sb.append("select model.wattage ,sum(model.lightCount) "
				 +" from "
				+ " LightWattage model  "
		 		+ " where model.isDeleted = 'N' and model.lightMonitoring.isDeleted ='N' ");
		 if( locationType != null && locationType.trim().length() > 0 && locationValue != null && locationValue.longValue() > 0){
				if(locationType.equalsIgnoreCase("district")){
					sb.append(" AND model.lightMonitoring.panchayat.locationAddress.district.districtId = :locationValue ");
				}else if(locationType.equalsIgnoreCase("parliament")){
					sb.append(" AND model.lightMonitoring.panchayat.locationAddress.parliament.constituencyId = :locationValue ");
				}else if(locationType.equalsIgnoreCase("constituency")){
					sb.append(" AND model.lightMonitoring.panchayat.locationAddress.constituency.constituencyId = :locationValue ");
				}else if(locationType.equalsIgnoreCase("mandal")){
					sb.append(" AND model.lightMonitoring.panchayat.locationAddress.tehsil.tehsilId = :locationValue ");
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
		 if(locationType != null && locationType.trim().length() > 0 && locationValue != null && locationValue.longValue() > 0){
				query.setParameter("locationValue",locationValue);
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
	
	
	
}
