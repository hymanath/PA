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
	public List<Object[]> getTotalWattege() {
		StringBuilder sb = new StringBuilder();
		 sb.append("select LWT.wattage,LWT.lightCount "
				 +"from "
				+ "LightWattage LWT, LightMonitoring LM  "
		 		+ " where LM.lightMonitoringId = LWT.lightMonitoringId "
		 		+ " group by  LWT.wattage" );
		 Query query = getSession().createQuery(sb.toString());
		 
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
