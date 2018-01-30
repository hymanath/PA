package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IMeesevaKpiCentersDAO;
import com.itgrids.model.MeesevaKpiCenters;
@Repository
public class MeesevaKpiCentersDAO extends GenericDaoHibernate<MeesevaKpiCenters, Long> implements IMeesevaKpiCentersDAO{

	public MeesevaKpiCentersDAO() {
		super(MeesevaKpiCenters.class);
	}
	
	public int deleteRecrdsFrmTable(String districtIdStr){
		Query query = getSession().createQuery("delete from MeesevaKpiCenters where districtId = :districtIdStr");
		query.setParameter("districtIdStr", districtIdStr);
		return  query.executeUpdate();
	}
	
	public List<Object[]> getStateWiseTotalMeesevaCentersCunts(){
		Query query = getSession().createQuery("select model.centerType,count(*)"
				+ " from MeesevaKpiCenters model"
				+ " group by model.centerType");
			
		return query.list();
	}
	
	public List<Date> getEstablisedDatesFrAllRecords(){
		Query query = getSession().createQuery("select model.estdate"
				+ " from MeesevaKpiCenters model");
			
		return query.list();
	}
	
	public List<Object[]> getMeesevaKPIDetails(){
		Query query = getSession().createQuery("select model.districtId,model.districtName,model.mandalId,model.mandalName,model.villageId,model.villageName,"//5
				+ "model.agentId,model.agentName,model.mobileNo,model.caste,model.identityNo,model.address,model.centerType,date(model.estdate)"//13
				+ " from MeesevaKpiCenters model");
			
		return query.list();
	}
	
	public List<Object[]> getMeesevaKPICentresEstFrom2014(){
		Query query = getSession().createQuery("select model.centerType,count(*)"
				+ " from MeesevaKpiCenters model"
				+ " where date(model.estdate) >='2014-01-01'"
				+ " group by model.centerType");
			
		return  query.list();
	}
	
	public List<Object[]> getMeesevaKPICentresEstLast(Date fromDate,Date endDate){
		Query query = getSession().createQuery("select model.centerType,count(*)"
				+ " from MeesevaKpiCenters model"
				+ " where date(model.estdate) between :fromDate and :endDate"
				+ " group by model.centerType");
		query.setDate("fromDate", fromDate);
		query.setDate("endDate", endDate);
		return query.list();
	}
	
	public List<Object[]> getMeesevaKPICentresByType(String levelType){
		StringBuilder sb  = new StringBuilder();
		sb.append("select model.centerType");
		if(levelType  != null && levelType.trim().equalsIgnoreCase("district")){
			sb.append(",model.districtId,model.districtName,'','','',''");
		}else if(levelType  != null && levelType.trim().equalsIgnoreCase("mandal")){
			sb.append(",model.districtId,model.districtName,model.mandalId,model.mandalName,'',''");
		}else if(levelType  != null && levelType.trim().equalsIgnoreCase("panchayat")){
			sb.append(",model.districtId,model.districtName,model.mandalId,model.mandalName,model.villageId,model.villageName");
		}
		
		sb.append(",count(*)"
				+ " from MeesevaKpiCenters model");
		
		if(levelType  != null && levelType.trim().equalsIgnoreCase("district"))
			sb.append(" group by model.districtId");
		else if(levelType  != null && levelType.trim().equalsIgnoreCase("mandal"))
			sb.append(" group by model.mandalId");
		else if(levelType  != null && levelType.trim().equalsIgnoreCase("panchayat"))
			sb.append(" group by model.villageId");
		 
		sb.append(",model.centerType");
		Query query = getSession().createQuery(sb.toString());
		
		return query.list();
	}
	
	public List<Object[]> getMeesevaKPICentresFrom2014ByType(String levelType){
		StringBuilder sb  = new StringBuilder();
		sb.append("select model.centerType");
		if(levelType  != null && levelType.trim().equalsIgnoreCase("district")){
			sb.append(",model.districtId,model.districtName,'','','',''");
		}else if(levelType  != null && levelType.trim().equalsIgnoreCase("mandal")){
			sb.append(",model.districtId,model.districtName,model.mandalId,model.mandalName,'',''");
		}else if(levelType  != null && levelType.trim().equalsIgnoreCase("panchayat")){
			sb.append(",model.districtId,model.districtName,model.mandalId,model.mandalName,model.villageId,model.villageName");
		}
		sb.append(",count(*)"
				+ " from MeesevaKpiCenters model"
				+ " where date(model.estdate) >='2014-01-01'");
		
		if(levelType  != null && levelType.trim().equalsIgnoreCase("district"))
			sb.append(" group by model.districtId");
		else if(levelType  != null && levelType.trim().equalsIgnoreCase("mandal"))
			sb.append(" group by model.mandalId");
		else if(levelType  != null && levelType.trim().equalsIgnoreCase("panchayat"))
			sb.append(" group by model.villageId");
		 
		sb.append(",model.centerType");
		Query query = getSession().createQuery(sb.toString());
		
		return query.list();
	}
	
	public List<Object[]> getMeesevaKPICentresLastYearByType(Date startDate,Date endDate,String levelType){
		StringBuilder sb  = new StringBuilder();
		sb.append("select model.centerType");
		if(levelType  != null && levelType.trim().equalsIgnoreCase("district")){
			sb.append(",model.districtId,model.districtName,'','','',''");
		}else if(levelType  != null && levelType.trim().equalsIgnoreCase("mandal")){
			sb.append(",model.districtId,model.districtName,model.mandalId,model.mandalName,'',''");
		}else if(levelType  != null && levelType.trim().equalsIgnoreCase("panchayat")){
			sb.append(",model.districtId,model.districtName,model.mandalId,model.mandalName,model.villageId,model.villageName");
		}
		
		sb.append(",count(*)"
				+ " from MeesevaKpiCenters model"
				+ " where date(model.estdate) between :startDate and :endDate");
		
		if(levelType  != null && levelType.trim().equalsIgnoreCase("district"))
			sb.append(" group by model.districtId");
		else if(levelType  != null && levelType.trim().equalsIgnoreCase("mandal"))
			sb.append(" group by model.mandalId");
		else if(levelType  != null && levelType.trim().equalsIgnoreCase("panchayat"))
			sb.append(" group by model.villageId");
		 
		sb.append(",model.centerType");
		Query query = getSession().createQuery(sb.toString());
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		return query.list();
	}
	
	public List<Object[]> getMeesevaDetailsByDistrictId(String districtId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.mandalName,model.villageName,model.agentId,"
				+ "model.agentName,model.mobileNo,model.address,model.centerType,date(model.estdate)"
				+ " from MeesevaKpiCenters model");
		if(districtId != null && !districtId.isEmpty()){
			sb.append(" where model.districtId = :districtId");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(districtId != null && !districtId.isEmpty()){
			query.setParameter("districtId", districtId);
		}
		
		return query.list();
	}
	
	public List<Object[]> getMeesevaKPICentresForFromAndToDate(Date startDate,Date endDate){
		Query query = getSession().createQuery("select model.centerType,count(*)"
				+ " from MeesevaKpiCenters model"
				+ " where date(model.estdate) between :startDate and :endDate"
				+ " group by model.centerType");
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		return  query.list();
	}
}
