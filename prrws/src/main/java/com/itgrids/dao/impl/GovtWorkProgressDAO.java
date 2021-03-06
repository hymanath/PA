package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.solr.search.QueryUtils;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IGovtWorkProgressDAO;
import com.itgrids.model.GovtWorkProgress;
import com.itgrids.utils.IConstants;

@Repository
public class GovtWorkProgressDAO extends GenericDaoHibernate<GovtWorkProgress, Long> implements IGovtWorkProgressDAO{

	public GovtWorkProgressDAO() {
		super(GovtWorkProgress.class);
	}

	public List<Object[]> getLatestStatusOfWork(List<Long> workIds){
		Query query = getSession().createQuery(" select model.govtWorkId,max(model.govtWorkStatusId) "
				+ " from GovtWorkProgress model "
				+ " where model.govtWorkId in (:workIds) group by model.govtWorkId ");
		query.setParameterList("workIds", workIds);
		return query.list();
	}
	
	public GovtWorkProgress getWorkProgressId(Long govtWorkId,Long govtWorkStatusId){
		Query query = getSession().createQuery(" select model "
				+ " from GovtWorkProgress model "
				+ " where model.govtWorkId=:govtWorkId and model.govtWorkStatusId=:govtWorkStatusId ");
		query.setParameter("govtWorkId", govtWorkId);
		query.setParameter("govtWorkStatusId", govtWorkStatusId);
		return (GovtWorkProgress)query.uniqueResult();
	}
	
	public List<Object[]> getAllstatusInfoOfGovtWork(Long govtWorkId){
		//0-statusTypeId,1-statusType,2-govtWorkStatusId,3-statusName,4-workLength,5-completedPercentage,6-isCompleted,7-updateById,8-updatedByName,9-time,10-govtWorkProgressId
		Query query = getSession().createQuery(" select model.govtWorkStatus.statusType.statusTypeId,model.govtWorkStatus.statusType.typeName,"
				+ " model.govtWorkStatusId,model.govtWorkStatus.statusName,"
				+ " model.workLength,model.completedPercentage,model.isCompleted,model.updatedBy,model.updatedByUser.username,date(model.updatedTime),model.govtWorkProgressId "
				+ " from GovtWorkProgress model "
				+ " where model.govtWorkId=:govtWorkId ");
		query.setParameter("govtWorkId", govtWorkId);
		return query.list();
	}
	
	public List<Object[]> getCompletedMianWorkPercentage(Long userId,Long workTypeId,Long locationScopeId,List<Long> locationValues){
		//0-mainWorkId,1-totalKms,2-completedKms
		StringBuilder sb = new StringBuilder();
		sb.append("select gmw.govt_main_work_id as mainWorkId,gmw.approved_km as totalKms,sum(gwp.work_length) as completedKms "
				+ " from govt_work_progress gwp,govt_main_work gmw,govt_work gw,location_address la "
				+ " where gw.is_deleted='N' "
				+ " and gwp.govt_work_id=gw.govt_work_id "
				+ " and gw.govt_main_work_id=gmw.govt_main_work_id "
				+ " and gmw.location_address_id=la.location_address_id "
				+ " and gw.created_by=:userId and gmw.govt_work_type_id=:workTypeId and gwp.govt_work_status_id=:statusId  ");
		
			if(locationScopeId == 3l){
				sb.append(" and la.district_id in (:locationValues) ");
			}else if(locationScopeId == 12l){
				sb.append(" and la.division_id in (:locationValues) ");
			}else if(locationScopeId == 13l){
				sb.append(" and la.sub_division_id in (:locationValues) ");
			}else if(locationScopeId == 5l){
				sb.append(" and la.tehsil_id in (:locationValues) ");
			}else if(locationScopeId == 6l){
				sb.append(" and la.panchayat_id in (:locationValues) ");
			}
				
			sb.append(" group by gmw.govt_main_work_id");
		
		Query query = getSession().createSQLQuery(sb.toString());
		
		query.setParameter("userId", userId);
		query.setParameter("workTypeId", workTypeId);
		if(locationScopeId == 3l || locationScopeId == 12l || locationScopeId == 13l || locationScopeId == 5l || locationScopeId == 6l){
			query.setParameterList("locationValues", locationValues);
		}
		
		if(workTypeId == 1l){
			query.setParameter("statusId", IConstants.UGDFINISHSTATUSID);
		}
		
		return query.list();
	}
	
	public List<Object[]> getAllFininsedKmsOfWorks(List<Long> workIds){
		//0-workId,1-statusCount,2-lengthSum
		Query query = getSession().createSQLQuery(" select model.govt_work_id,model.work_length "
				+ " from govt_work_progress model "
				+ " where model.govt_work_id in (:workIds) and model.govt_work_status_id=:finishStatusId ");
		query.setParameterList("workIds", workIds);
		query.setParameter("finishStatusId", IConstants.UGDFINISHSTATUSID);
		return query.list();
	}
	
	public Object getWorkOverallWorkCompletedPercentage(Long govtWorkId){
		Query query = getSession().createSQLQuery(" select sum(completed_percentage)/6 "
				+ " from govt_work_progress "
				+ " where govt_work_id=:govtWorkId ");
		query.setParameter("govtWorkId", govtWorkId);
		return query.uniqueResult();
	}
	
	public Object getWorkOverallWorkCompletedKms(Long workId){
		Query query = getSession().createSQLQuery(" select sum(work_length)/6 "
				+ " from govt_work_progress "
				+ " where govt_work_id=:workId ");
		query.setParameter("workId", workId);
		return query.uniqueResult();
	}
	
	public Object[] getWorkCompletedKms(Long workTypeId){
		
		Query query = getSession().createSQLQuery(" select count(gw.govt_work_id),sum(gw.work_length) "
				+ " from govt_work gw,govt_main_work gmw "
				+ " where gw.is_deleted='N' "
				+ " and gw.govt_main_work_id=gmw.govt_main_work_id and gmw.govt_work_type_id=:workTypeId "
				+ " and gw.completed_percentage >= 99.9 ");
		
		query.setParameter("workTypeId", workTypeId);
		return (Object[]) query.uniqueResult();
	}
	
	public List<Object[]> getStatusWiseAllWorksAndKms(Long workTypeId){
		//0-statusId,1-works count,2-kms
		Query query = getSession().createSQLQuery(" select gwp.govt_work_status_id,count(distinct gwp.govt_work_id),sum(gwp.work_length) "
				+ " from govt_work_progress gwp,govt_work gw,govt_main_work gmw "
				+ " where gwp.govt_work_id=gw.govt_work_id and gw.is_deleted='N' and gmw.govt_work_type_id=:workTypeId and gw.govt_main_work_id=gmw.govt_main_work_id "
				+ " group by gwp.govt_work_status_id");
		query.setParameter("workTypeId", workTypeId);
		return query.list();
	}
	
	public List<Object[]> getLocationWiseOverview(Long workTypeId,Long locationScopeId,Long districtId,Long divisonId,Long subDivisonId,Long mandalId,String workZone){
		//0-statusId,1-statusName,2-kms,3-locationId(,4-workZoneId,5-workzonename)
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select gws.govt_work_status_id,gws.status_name,sum(gwp.work_length) ");
		if(locationScopeId == 3l){
			sb.append(" ,la.district_id ");
		}else if(locationScopeId == 12l){
			sb.append(" ,la.division_id ");
		}else if(locationScopeId == 13l){
			sb.append(" ,la.sub_divison_id ");
		}else if(locationScopeId == 5l){
			sb.append(" ,la.tehsil_id ");
		}else if(locationScopeId == 6l){
			sb.append(" ,la.panchayat_id ");
		}
		if(workZone != null && workZone.equalsIgnoreCase("Y")){
			sb.append(",gw.govt_work_id,gw.work_zone_name");
		}
		sb.append(" from govt_work_progress gwp,govt_work gw, govt_main_work gmw,location_address la,govt_work_status gws "
				+ " where gwp.govt_work_id=gw.govt_work_id and gw.govt_main_work_id=gmw.govt_main_work_id and gmw.location_address_id=la.location_address_id "
				+ " and gw.is_deleted='N' and gmw.govt_work_type_id=:workTypeId and gwp.govt_work_status_id=gws.govt_work_status_id and gws.govt_work_type_id=:workTypeId ");
		
		if(districtId != null && districtId > 0l){
			sb.append(" and la.district_id=:districtId ");
		}
		if(divisonId != null && divisonId > 0l){
			sb.append(" and la.division_id=:divisonId ");
		}
		if(subDivisonId != null && subDivisonId > 0l){
			sb.append(" and la.sub_divison_id=:subDivisonId ");
		}
		if(mandalId != null && mandalId > 0l){
			sb.append(" and la.tehsil_id=:mandalId ");
		}
		
		sb.append(" group by gws.govt_work_status_id ");
		
		if(locationScopeId == 3l){
			sb.append(" ,la.district_id ");
		}else if(locationScopeId == 12l){
			sb.append(" ,la.division_id ");
		}else if(locationScopeId == 13l){
			sb.append(" ,la.sub_divison_id ");
		}else if(locationScopeId == 5l){
			sb.append(" ,la.tehsil_id ");
		}else if(locationScopeId == 6l){
			sb.append(" ,la.panchayat_id ");
		}
		
		if(workZone != null && workZone.equalsIgnoreCase("Y")){
			sb.append(",gw.govt_work_id");
		}
		
		Query query = getSession().createSQLQuery(sb.toString());
		
		query.setParameter("workTypeId", workTypeId);
		
		if(districtId != null && districtId > 0l){
			query.setParameter("districtId", districtId);
		}
		if(divisonId != null && divisonId > 0l){
			query.setParameter("divisonId", divisonId);
		}
		if(subDivisonId != null && subDivisonId > 0l){
			query.setParameter("subDivisonId", subDivisonId);
		}
		if(mandalId != null && mandalId > 0l){
			query.setParameter("mandalId", mandalId);
		}
		
		return query.list();
	}
		public List<Object[]> getLocationLevelStatusWiseOverviewDetails(Long locationScopeId,Long locationLevelId,Long workTypeId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select gwp.govt_work_status_id,sum(gwp.work_length) "
				+ " from govt_work_progress gwp,govt_work gw,govt_main_work gmw,location_address la "
				+ " where gwp.govt_work_id=gw.govt_work_id and gw.govt_main_work_id=gmw.govt_main_work_id and gmw.location_address_id=la.location_address_id "
				+ " and gmw.govt_work_type_id=:workTypeId and gw.is_deleted='N' ");
		
		if(locationScopeId == 3l){
			sb.append(" and la.district_id=:locationLevelId ");
		}else if(locationScopeId == 5l){
			sb.append(" and la.tehsil_id=:locationLevelId ");
		}else if(locationScopeId == 6l){
			sb.append(" and la.panchayat_id=:locationLevelId ");
		}else if(locationScopeId == 12l){
			sb.append(" and la.division_id=:locationLevelId ");
		}else if(locationScopeId == 13l){
			sb.append(" and la.sub_division_id=:locationLevelId ");
		}
		
		sb.append(" group by gwp.govt_work_status_id ");
		
		Query query = getSession().createSQLQuery(sb.toString());
		
		query.setParameter("workTypeId", workTypeId);
		
		if(locationScopeId == 3l || locationScopeId == 5l || locationScopeId == 6l || locationScopeId == 12l || locationScopeId == 13l){
			query.setParameter("locationLevelId", locationLevelId);
		}
		
		return query.list();
	}
	
	public List<Object[]> getWorkZoneStatusWiseKms(Long locationScopeId,Long locationLevelId,Long workTypeId){
		//0-workZoneId,1-zonename,2-statusId,3-length
		StringBuilder sb = new StringBuilder();
		sb.append(" select gw.govt_work_id,gw.work_zone_name,gwp.govt_work_status_id,gwp.work_length "
				+ " from govt_work_progress gwp,govt_work gw,govt_main_work gmw,location_address la "
				+ " where gwp.govt_work_id=gw.govt_work_id and gw.govt_main_work_id=gmw.govt_main_work_id and gmw.location_address_id=la.location_address_id "
				+ " and gmw.govt_work_type_id=:workTypeId and gw.is_deleted='N' ");
		
		if(locationScopeId == 3l){
			sb.append(" and la.district_id=:locationLevelId ");
		}else if(locationScopeId == 5l){
			sb.append(" and la.tehsil_id=:locationLevelId ");
		}else if(locationScopeId == 6l){
			sb.append(" and la.panchayat_id=:locationLevelId ");
		}else if(locationScopeId == 12l){
			sb.append(" and la.division_id=:locationLevelId ");
		}else if(locationScopeId == 13l){
			sb.append(" and la.sub_division_id=:locationLevelId ");
		}
		
		sb.append(" group by gw.govt_work_id,gwp.govt_work_status_id ");
		
		Query query = getSession().createSQLQuery(sb.toString());
		
		query.setParameter("workTypeId", workTypeId);
		
		if(locationScopeId == 3l || locationScopeId == 5l || locationScopeId == 6l || locationScopeId == 12l || locationScopeId == 13l){
			query.setParameter("locationLevelId", locationLevelId);
		}
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getWorkZoneStatusDetailsInfo(Long workId){
		//0-statusId,1-workLength,2-Perc,3-date
		Query query = getSession().createQuery(" select model.govtWorkStatusId,model.workLength,model.completedPercentage,date(updatedTime) "
				+ " from GovtWorkProgress model "
				+ " where model.govtWorkId=:workId ");
		
		query.setParameter("workId",workId);
		return query.list();
	}
	
	public List<Object[]> getlocationLevelWiseCompletedKms(Long locationScopeId,Long districtId,Long divisionId,Long subDivisionId,Long mandalId,Long statusId,Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		
		if(locationScopeId == 3l){
			sb.append(" la.district_id ");
		}else if(locationScopeId == 4l){
			sb.append(" la.constituency_id ");
		}else if(locationScopeId == 5l){
			sb.append(" la.tehsil_id ");
		}else if(locationScopeId == 6l){
			sb.append(" la.panchayat_id ");
		}else if(locationScopeId == 12l){
			sb.append(" la.division_id ");
		}else if(locationScopeId == 13l){
			sb.append(" la.sub_division_id ");
		} 
		
		sb.append(",sum(gwp.work_length) ");
		sb.append(" from ");
		
		if(locationScopeId == 3l){
			sb.append(" district dist, ");
		}else if(locationScopeId == 4l){
			sb.append(" constituency const, ");
		}else if(locationScopeId == 5l){
			sb.append(" tehsil teh, ");
		}else if(locationScopeId == 6l){
			sb.append(" panchayat panch, ");
		}else if(locationScopeId == 12l){
			sb.append(" division divi, ");
		}else if(locationScopeId == 13l){
			sb.append(" sub_division subDivi, ");
		} 
		
		sb.append(" govt_work_progress gwp,govt_work gw, govt_main_work gmw,location_address la "
				+ " where gwp.govt_work_id=gw.govt_work_id and gw.govt_main_work_id=gmw.govt_main_work_id and gmw.location_address_id=la.location_address_id and gw.is_deleted='N' ");
		
		if(locationScopeId == 3l){
			sb.append(" and la.district_id=dist.district_id ");
		}else if(locationScopeId == 4l){
			sb.append(" and la.constituency_id=const.constituency_id ");
		}else if(locationScopeId == 5l){
			sb.append(" and la.tehsil_id=teh.tehsil_id ");
		}else if(locationScopeId == 6l){
			sb.append(" and la.panchayat_id=panch.panchayat_id ");
		}else if(locationScopeId == 12l){
			sb.append(" and la.division_id=divi.division_id ");
		}else if(locationScopeId == 13l){
			sb.append(" and la.sub_division_id=subDivi.sub_division_id, ");
		} 
		
		if(statusId != null && statusId > 0l){
			sb.append(" and gwp.govt_work_status_id=:statusId ");
		}
		
		if(districtId != null && districtId > 0l){
			sb.append(" and la.district_id=:districtId ");
		}
		
		if(divisionId != null && divisionId > 0l){
			sb.append(" and la.division_id=:divisionId ");
		}
		
		if(subDivisionId != null && subDivisionId > 0l){
			sb.append(" and la.sub_division_id=:subDivisionId ");
		}
		
		if(mandalId != null && mandalId > 0l){
			sb.append(" and la.tehsil_id=:mandalId ");
		}
		
		if(fromDate != null && toDate != null){
			sb.append(" and date(gwp.updated_time) between :fromDate and :toDate ");
		}
		
		if(locationScopeId == 3l){
			sb.append(" group by la.district_id ");
		}else if(locationScopeId == 4l){
			sb.append(" group by la.constituency_id ");
		}else if(locationScopeId == 5l){
			sb.append(" group by la.tehsil_id ");
		}else if(locationScopeId == 6l){
			sb.append(" group by la.panchayat_id ");
		}else if(locationScopeId == 12l){
			sb.append(" group by la.division_id ");
		}else if(locationScopeId == 13l){
			sb.append(" group by la.sub_division_id ");
		} 
		
		
		Query query = getSession().createSQLQuery(sb.toString());
		
		if(districtId != null && districtId > 0l){
			query.setParameter("districtId", districtId);
		}
		
		if(divisionId != null && divisionId > 0l){
			query.setParameter("divisionId", divisionId);
		}
		
		if(subDivisionId != null && subDivisionId > 0l){
			query.setParameter("subDivisionId", subDivisionId);
		}
		
		if(mandalId != null && mandalId > 0l){
			query.setParameter("mandalId", mandalId);
		}
		
		if(statusId != null && statusId > 0l){
			query.setParameter("statusId", statusId);
		}
		
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
				
	}
}
