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

	public List<Object[]> getStatusWiseDayReport(Long workTypeId,Long workId,Date startDate,Date endDate,String reportType){
		StringBuilder sb = new StringBuilder();
		//0-statusId,1-statuName,2-date,3-length
		sb.append(" select gwpt.govt_work_status_id,gws.status_name");
		
		if(reportType.equalsIgnoreCase("dayWise")){
			sb.append(",date(gwpt.updated_time)");
		}else{
			sb.append(",month(gwpt.updated_time),year(gwpt.updated_time)");
		}
		
		sb.append(",gwpt.work_length "
				+ " from govt_work_progress_track gwpt ,govt_work gw ,govt_work_status gws,govt_main_work gmw "
				+ " where gws.govt_work_status_id=gwpt.govt_work_status_id "
				+ " and gw.is_deleted='N' and  gwpt.govt_work_id=gw.govt_work_id and gmw.govt_main_work_id=gw.govt_main_work_id ");
		
		if(workId != null && workId > 0l){
			sb.append(" and gw.govt_work_id=:workId ");
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
		
		if(workTypeId != null && workTypeId > 0l){
			query.setParameter("workTypeId", workTypeId);
		}
		
		if(workId != null && workId > 0l){
			query.setParameter("workId", workId);	
		}
		
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
	}
}
