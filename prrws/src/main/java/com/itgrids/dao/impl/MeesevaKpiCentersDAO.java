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
	
	public Long getStateWiseTotalMeesevaCentersCunts(){
		Query query = getSession().createQuery("select count(*)"
				+ " from MeesevaKpiCenters model");
			
		return (Long) query.uniqueResult();
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
	
	public Long getMeesevaKPICentresEstFrom2014(){
		Query query = getSession().createQuery("select count(*)"
				+ " from MeesevaKpiCenters model"
				+ " where date(model.estdate) >='2014-01-01'");
			
		return (Long) query.uniqueResult();
	}
	
	public Long getMeesevaKPICentresEstLast(Date fromDate,Date endDate){
		Query query = getSession().createQuery("select count(*)"
				+ " from MeesevaKpiCenters model"
				+ " where date(model.estdate) between :fromDate and :endDate");
		query.setDate("fromDate", fromDate);
		query.setDate("endDate", endDate);
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getMeesevaKPICentresForDoistrict(){
		Query query = getSession().createQuery("select model.districtId,model.districtName,count(*)"
				+ " from MeesevaKpiCenters model"
				+ " group by model.districtId");
		
		return query.list();
	}
	
	public List<Object[]> getMeesevaKPICentresFrom2014ForDistricts(){
		Query query = getSession().createQuery("select model.districtId,model.districtName,count(*)"
				+ " from MeesevaKpiCenters model"
				+ " where date(model.estdate) >='2014-01-01'"
				+ " group by model.districtId");
		
		return query.list();
	}
	
	public List<Object[]> getMeesevaKPICentresLastYearForDistricts(Date startDate,Date endDate ){
		Query query = getSession().createQuery("select model.districtId,model.districtName,count(*)"
				+ " from MeesevaKpiCenters model"
				+ " where date(model.estdate) between :startDate and :endDate"
				+ " group by model.districtId");
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
	
	public Long getMeesevaKPICentresForFromAndToDate(Date startDate,Date endDate){
		Query query = getSession().createQuery("select count(*)"
				+ " from MeesevaKpiCenters model"
				+ " where date(model.estdate) between :startDate and :endDate"
				+ " group by model.districtId");
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		return (Long) query.uniqueResult();
	}
}
