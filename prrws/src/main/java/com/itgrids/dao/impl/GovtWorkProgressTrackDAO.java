package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IGovtWorkProgressTrackDAO;
import com.itgrids.model.GovtWorkProgressTrack;

@Repository
public class GovtWorkProgressTrackDAO extends GenericDaoHibernate<GovtWorkProgressTrack, Long> implements IGovtWorkProgressTrackDAO{

	public GovtWorkProgressTrackDAO() {
		super(GovtWorkProgressTrack.class);
	}

	public List<Object[]> getStatusWiseDayReport(Long workTypeId,Long locationScopeId,List<Long> locationIds,Date startDate,Date endDate,String reportType){
		StringBuilder sb = new StringBuilder();
		//0-statusId,1-statuName,2-date,3-length
		sb.append(" select gwpt.govt_work_status_id,gws.status_name");
		
		if(reportType != null && reportType.equalsIgnoreCase("dayWise")){
			sb.append(",date(gwpt.updated_time)");
		}else{
			sb.append(",month(gwpt.updated_time),year(gwpt.updated_time)");
		}
		
		sb.append(",sum(gwpt.work_length) "
				+ " from govt_work_progress_track gwpt ,govt_work gw ,govt_work_status gws,govt_main_work gmw,location_address la "
				+ " where gws.govt_work_status_id=gwpt.govt_work_status_id "
				+ " and gmw.location_address_id=la.location_address_id and gw.is_deleted='N' and  gwpt.govt_work_id=gw.govt_work_id "
				+ " and gmw.govt_main_work_id=gw.govt_main_work_id ");
		
		
		if(locationScopeId == 3l){
			sb.append(" and la.district_id in (:locationIds)");
		}else if(locationScopeId == 4l){
			sb.append(" and la.constituency_id in (:locationIds)");
		}else if(locationScopeId == 5l){
			sb.append(" and la.tehsil_id in (:locationIds)");
		}else if(locationScopeId == 6l){
			sb.append(" and la.panchayat_id in (:locationIds)");
		}else if(locationScopeId == 12l){
			sb.append(" and la.division_id in (:locationIds)");
		}else if(locationScopeId == 13l){
			sb.append(" and la.sub_division_id in (:locationIds)");
		}

		if(startDate != null && endDate != null){
			sb.append(" and date(gwpt.updated_time) between :startDate and :endDate ");
		}
		
		if(workTypeId != null && workTypeId > 0l){
			sb.append(" and gmw.govt_work_type_id=:workTypeId ");
		}
		
		if(reportType != null){
			if(reportType.equalsIgnoreCase("dayWise"))
				sb.append(" group by date(gwpt.updated_time),gwpt.govt_work_status_id ");
			else
				sb.append(" group by month(gwpt.updated_time),year(gwpt.updated_time),gwpt.govt_work_status_id ");
		}
		
		Query query = getSession().createSQLQuery(sb.toString());
		
		if(locationScopeId == 3l || locationScopeId== 4l || locationScopeId== 5l || locationScopeId== 6l || locationScopeId== 12l || locationScopeId== 13l){
			query.setParameterList("locationIds", locationIds);
		}
			
		if(workTypeId != null && workTypeId > 0l){
			query.setParameter("workTypeId", workTypeId);
		}
		
		
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
	}
	
	public List<Object[]> getStatusWiseDayReportForWorkZone(List<Long> workZoneIds,Date fromDate,Date toDate,String reportType){
		StringBuilder sb = new StringBuilder();
		//0-statusId,1-statuName,2-date,3-length
		sb.append(" select gwpt.govt_work_status_id,gws.status_name");
		
		if(reportType.equalsIgnoreCase("dayWise")){
			sb.append(",date(gwpt.updated_time)");
		}else{
			sb.append(",month(gwpt.updated_time),year(gwpt.updated_time)");
		}
		
		sb.append(" ,sum(gwpt.work_length) from govt_work_progress_track gwpt ,govt_work gw ,govt_work_status gws "
				+ " where gws.govt_work_status_id=gwpt.govt_work_status_id and gwpt.govt_work_id=gw.govt_work_id "
				+ " and gw.is_deleted='N' and gw.govt_work_id in (:workZoneIds) ");
		if(fromDate != null && toDate != null){
			sb.append(" and date(gwpt.updated_time) between :fromDate and :toDate ");
		}
		if(reportType.equalsIgnoreCase("dayWise")){
			sb.append(" group by date(gwpt.updated_time),gwpt.govt_work_status_id ");
		}else{
			sb.append(" group by month(gwpt.updated_time),year(gwpt.updated_time),gwpt.govt_work_status_id");
		}
		
		Query query = getSession().createSQLQuery(sb.toString());
		query.setParameterList("workZoneIds", workZoneIds);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate",toDate);
		}
		return query.list();
	}
}
