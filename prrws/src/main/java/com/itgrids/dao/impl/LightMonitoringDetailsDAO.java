package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ILightMonitoringDetailsDAO;
import com.itgrids.model.LightMonitoringDetails;

@Repository
public class LightMonitoringDetailsDAO extends GenericDaoHibernate<LightMonitoringDetails, Long> implements ILightMonitoringDetailsDAO{

	public LightMonitoringDetailsDAO() {
		super(LightMonitoringDetails.class);
	}

	public List<Object[]> getTodayVendorDetails(Date toDate,Long vendorId){
		StringBuilder sb = new StringBuilder();
		sb.append("select  model.panchayat.locationAddress.district.districtId,"
				+ "model.panchayat.locationAddress.district.districtName,"
				+ "model.panchayat.panchayatId,"
				+ "model.panchayat.panchayatName,"
				+ "sum(model.lightsFitted),"
				+ "sum(model.teamWorked)"
				+ " from LightMonitoringDetails model"
				+ " where ");
		if(vendorId != null && vendorId.longValue() > 0L){
			sb.append(" model.lightsVendor.lightsVendorId = :vendorId");
		}
		if(toDate != null){
			sb.append(" and date(model.workDate) = :toDate");
		}
		sb.append(" group by model.panchayat.locationAddress.district.districtId,model.panchayat.panchayatId");
		
		Query query = getSession().createQuery(sb.toString());
		if(toDate != null){
			query.setDate("toDate", toDate);
		}
		if(vendorId != null && vendorId.longValue() > 0L)
			query.setParameter("vendorId", vendorId);
		
		return query.list();
	}
	
	public List<Object[]> getLightsFittedCountBetweenDates(Date fromDate,Date toDate,Long vendorId){
		StringBuilder sb = new StringBuilder();
		sb.append("select count(distinct model.panchayat.panchayatId),sum(model.lightsFitted),sum(model.teamWorked)"
				+ " from LightMonitoringDetails model"
				+ " where");
		if(vendorId != null && vendorId.longValue() > 0L){
			sb.append(" model.lightsVendor.lightsVendorId = :vendorId");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.workDate) between :fromDate and :toDate");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(vendorId != null && vendorId.longValue() > 0L)
			query.setParameter("vendorId", vendorId);
		
		return query.list();
	}
	
	public List<Object[]> getLightVendorDetailsByLevelType(String levelType,Long vendorId,Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append("select date(model.workDate),");
		if(levelType != null && levelType.trim().equalsIgnoreCase("district")){
			sb.append(" model.panchayat.locationAddress.district.districtId,model.panchayat.locationAddress.district.districtName,'','',");
		}else if(levelType != null && levelType.trim().equalsIgnoreCase("panchayat")){
			sb.append(" model.panchayat.locationAddress.district.districtId,model.panchayat.locationAddress.district.districtName,model.panchayat.panchayatId,model.panchayat.panchayatName,");
		}
			sb.append("sum(model.lightsFitted),sum(model.teamWorked)"
					+ " from LightMonitoringDetails model");
			
		if(vendorId != null && vendorId.longValue() > 0L){
			sb.append(" where model.lightsVendor.lightsVendorId = :vendorId");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.workDate) between :fromDate and :toDate");
		}
		if(levelType != null && levelType.trim().equalsIgnoreCase("district")){
			sb.append(" group by model.panchayat.locationAddress.district.districtId");
		}else if(levelType != null && levelType.trim().equalsIgnoreCase("panchayat")){
			sb.append(" group by model.panchayat.panchayatId");
		}
		sb.append(",date(model.workDate)");
		
		Query query = getSession().createQuery(sb.toString());
		if(vendorId != null && vendorId.longValue() > 0L)
			query.setParameter("vendorId", vendorId);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
	}
	
	public List<Object[]> getVendorDetails(Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.lightsVendor.lightsVendorId,"
				+ "sum(model.lightsFitted),sum(model.teamWorked)"
				+ " from LightMonitoringDetails model");
		if(fromDate != null && toDate != null){
			sb.append(" where date(model.workDate) between :fromDate and :toDate");
		}
		sb.append(" group by model.lightsVendor.lightsVendorId");
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		return query.list();
	}
	
	public List<Object[]> getAllVendorDetailsByLevelType(String levelType,Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.lightsVendor.lightsVendorId,model.lightsVendor.vendorName,");
		if(levelType != null && levelType.trim().equalsIgnoreCase("district")){
			sb.append(" model.panchayat.locationAddress.district.districtId,model.panchayat.locationAddress.district.districtName,'','',");
		}else if(levelType != null && levelType.trim().equalsIgnoreCase("panchayat")){
			sb.append(" model.panchayat.locationAddress.district.districtId,model.panchayat.locationAddress.district.districtName,model.panchayat.panchayatId,model.panchayat.panchayatName,");
		}
			sb.append("sum(model.lightsFitted),sum(model.teamWorked)"
					+ " from LightMonitoringDetails model");
			
		if(fromDate != null && toDate != null){
			sb.append(" where date(model.workDate) between :fromDate and :toDate");
		}
		if(levelType != null && levelType.trim().equalsIgnoreCase("district")){
			sb.append(" group by model.panchayat.locationAddress.district.districtId");
		}else if(levelType != null && levelType.trim().equalsIgnoreCase("panchayat")){
			sb.append(" group by model.panchayat.panchayatId");
		}
		sb.append(",model.lightsVendor.lightsVendorId");
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
	}
	
	public List<Object[]> getVendorDetailsForLocation(String levelType,Date fromDate,Date toDate,Long locationValue,Long vendorId){
		StringBuilder sb = new StringBuilder();
		sb.append("select date(model.workDate),sum(model.lightsFitted),sum(model.teamWorked)"
					+ " from LightMonitoringDetails model");
		
		if(fromDate != null && toDate != null){
			sb.append(" where date(model.workDate) between :fromDate and :toDate");
		}
		if(levelType != null && levelType.trim().length() > 0 && locationValue != null && locationValue.longValue() > 0L){
			if(levelType != null && levelType.trim().equalsIgnoreCase("district")){
				sb.append(" and model.panchayat.locationAddress.district.districtId = :locationValue");
			}else if(levelType != null && levelType.trim().equalsIgnoreCase("panchayat")){
				sb.append(" and model.panchayat.panchayatId = :locationValue");
			}
		}
		
		if(vendorId != null && vendorId.longValue() > 0L){
			sb.append(" and model.lightsVendor.lightsVendorId = :vendorId");
		}
		sb.append(" group by date(model.workDate)");
		
		Query query = getSession().createQuery(sb.toString());
		if(levelType != null && levelType.trim().length() > 0 && locationValue != null && locationValue.longValue() > 0L)
			query.setParameter("locationValue", locationValue);
		if(vendorId != null && vendorId.longValue() > 0L)
			query.setParameter("vendorId", vendorId);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
	}
	
}
