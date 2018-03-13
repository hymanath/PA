package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IGovtWorkDAO;
import com.itgrids.model.GovtWork;
import com.itgrids.utils.IConstants;

@Repository
public class GovtWorkDAO extends GenericDaoHibernate<GovtWork, Long> implements IGovtWorkDAO{

	public GovtWorkDAO() {
		super(GovtWork.class);
	}
	
	public List<Object[]> getWorkZoneDetailsOfMainWork(Long userId,Long mainWorkId){
		//0-workId,1-workZoneName,2-workLength,3-locationScopeId,4-locationScopeValue
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.govtWorkId,model.workZoneName,model.workLength,model.govtMainWork.locationScopeId,model.govtMainWork.locationValue "
				+ " from GovtWork model "
				+ " where model.isDeleted='N' and model.createdBy=:userId and model.govtMainWork.govtMainWorkId=:mainWorkId ");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("userId", userId);
		query.setParameter("mainWorkId", mainWorkId);
		
		return query.list();
	}
	
	public Long getWorkTypeId(Long govtWorkId){
		Query query = getSession().createQuery(" select model.govtMainWork.govtWorkTypeId "
				+ " from GovtWork model where model.govtWorkId=:govtWorkId ");
		query.setParameter("govtWorkId", govtWorkId);
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getUsersGovtMainWorks(Long userId,Long govtWorkTypeId,Long locationScopeId,List<Long> locationValues){
		//0-mainWorkId,1-mainWorkName,2-totalKms,3-locationScopeId,4-locationValue,5-locationAddressId,6-count,7-workLength
		StringBuilder sb = new StringBuilder();
		sb.append(" select gmw.govt_main_work_id as mainWorkId,gmw.govt_main_work_name as mainWorkName,"
				+ " gmw.approved_km as totalKms,gmw.location_scope_id as locationScopeId,gmw.location_value as locationValue,"
				+ " gmw.location_address_id as locationAddressId, count(distinct govt_work_id) as count,sum(gw.work_length) as workLength "
				+ " from govt_work gw, govt_main_work gmw,location_address la "
				+ " where gw.is_deleted='N' and gw.govt_main_work_id=gmw.govt_main_work_id and gmw.location_address_id=la.location_address_id "
				+ " and gmw.govt_work_type_id=:govtWorkTypeId "
				+ "and gw.created_by=:userId ");
		
		if(locationScopeId == 3l){
			sb.append(" and la.district_id in (:locationValues) ");
		}else if(locationScopeId == 4l){
			sb.append(" and la.constituency_id in (:locationValues) ");
		}else if(locationScopeId == 5l){
			sb.append(" and la.tehsil_id in (:locationValues) ");
		}else if(locationScopeId == 6l){
			sb.append(" and la.panchayat_id in (:locationValues) ");
		}else if(locationScopeId == 12l){
			sb.append(" and la.division_id in (:locationValues) ");
		}else if(locationScopeId == 13l){
			sb.append(" and la.sub_division_id in (:locationValues) ");
		}
		
		sb.append(" group by gmw.govt_main_work_id ");
		
		Query query = getSession().createSQLQuery(sb.toString());
		
		query.setParameter("userId", userId);
		query.setParameter("govtWorkTypeId", govtWorkTypeId);
		
		if(locationScopeId == 3l || locationScopeId == 4l || locationScopeId == 5l || locationScopeId == 6l || locationScopeId == 12l || locationScopeId == 13l){
			query.setParameterList("locationValues", locationValues);
		}
		return query.list();
	}
	
	public Object getOverallWork(Long workTypeId,Long locationScopeId,List<Long> locationIds){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select sum(gmw.approved_km) "
				+ " from govt_main_work gmw,location_address la "
				+ " where gmw.location_address_id=la.location_address_id and gmw.govt_work_type_id=:workTypeId ");
		
		if(locationScopeId == 3l){
			sb.append(" and la.district_id in (:locationIds) ");
		}else if(locationScopeId == 4l){
			sb.append(" and la.constituency_id in (:locationIds) ");
		}else if(locationScopeId == 5l){
			sb.append(" and la.tehsil_id in (:locationIds) ");
		}else if(locationScopeId == 6l){
			sb.append(" and la.panchayat_id in (:locationIds) ");
		}else if(locationScopeId == 12l){
			sb.append(" and la.division_id in (:locationIds) ");
		}else if(locationScopeId == 13l){
			sb.append(" and la.sub_division_id in (:locationIds) ");
		} 
		
		
		Query query = getSession().createSQLQuery(sb.toString());
		
		query.setParameter("workTypeId", workTypeId);

		if(locationScopeId == 3l || locationScopeId == 4l || locationScopeId == 5l || locationScopeId == 6l || locationScopeId == 12l || locationScopeId == 13l){
			query.setParameterList("locationIds", locationIds);
		}
			
		return (Object)query.uniqueResult();
	}
	
	public List<Object[]> getWorksCountByMainType(){
		//0-workTypeId,1-workscount
		Query query = getSession().createQuery(" select model.govtMainWork.govtWorkTypeId,count(distinct model.govtWorkId) "
				+ " from GovtWork model "
				+ " where model.isDeleted='N' "
				+ " group by model.govtMainWork.govtWorkTypeId ");
		
		return query.list();
		
	}
	
	public List<Object[]> getWorkZonesCountForDateType(Long workTypeId){
		//0-workTypeId,1-worksCount
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.govtMainWork.govtWorkTypeId,count(model.govtWorkId) "
				+ " from GovtWork model "
				+ " where model.isDeleted='N' ");
		
		if(workTypeId != null && workTypeId > 0l)
			sb.append(" and model.govtMainWork.govtWorkTypeId = :workTypeId ");
		else
			sb.append("group by model.govtMainWork.govtWorkTypeId");
		
		Query query = getSession().createQuery(sb.toString());
		
		if(workTypeId != null && workTypeId > 0l)
			query.setParameter("workTypeId", workTypeId);
			
		return query.list();
	}
	
	public List<Object[]> getAllWorkZonesOfLocations(Long locationScopeId,List<Long> locationIds){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.govtWorkId,model.workZoneName from GovtWork model where model.isDeleted='N' ");
		
		if(locationScopeId == 3l){
			sb.append(" and model.govtMainWork.locationAddress.districtId in (:locationIds) ");
		}else if(locationScopeId == 4l){
			sb.append(" and model.govtMainWork.locationAddress.constituencyId in (:locationIds) ");
		}else if(locationScopeId == 5l){
			sb.append(" and model.govtMainWork.locationAddress.tehsilId in (:locationIds) ");
		}else if(locationScopeId == 6l){
			sb.append(" and model.govtMainWork.locationAddress.panchayatId in (:locationIds) ");
		}else if(locationScopeId == 12l){
			sb.append(" and model.govtMainWork.locationAddress.divisionId in (:locationIds) ");
		}else if(locationScopeId == 13l){
			sb.append(" and model.govtMainWork.locationAddress.subDivisionId in (:locationIds) ");
		} 
		Query query = getSession().createQuery(sb.toString());
		
		if(locationScopeId == 3l || locationScopeId == 4l || locationScopeId == 5l || locationScopeId == 6l || locationScopeId == 12l || locationScopeId == 13l){
			query.setParameterList("locationIds", locationIds);
		}
		return query.list();
	}
	
	public Object getOverallLength(List<Long> workZoneIds){
		Query query = getSession().createSQLQuery(" select sum(gw.work_length) "
				+ " from govt_work gw where gw.is_deleted='N' and gw.govt_work_id in (:workZoneIds) ");
		
		query.setParameterList("workZoneIds", workZoneIds);
		
		return query.uniqueResult();
	}
	
	public List<Object[]> getCompletedWorksCount(){
		//0-workTypeId,1-workscount,2-worklength
		Query query = getSession().createSQLQuery(" select gmw.govt_work_type_id,count(distinct gw.govt_work_id),sum(gw.work_length) "
				+ " from govt_work gw,govt_main_work gmw "
				+ " where gw.is_deleted='N' and gw.govt_main_work_id=gmw.govt_main_work_id "
				+ " and gw.completed_percentage >= 99.9 "
				+ " group by gmw.govt_work_type_id ");
		
		return query.list();
	}
	
	public Object[] getOverallWorksLengthOfWorkType(Long workTypeId){
		Query query = getSession().createSQLQuery(" select count(gw.govt_work_id),sum(gw.work_length) "
				+ " from govt_work gw,govt_main_work gmw "
				+ " where gw.govt_main_id=gmw.govt_main_work_id and gw.is_deleted='N' and gmw.govt_work_type_id=:workTypeId ");
		query.setParameter("workTypeId",workTypeId);
		return (Object[])query.uniqueResult();
	}
	
	public Object[] getAllworkZonesOfLocation(Long locationScopeId,Long locationValue,Long workTypeId){
		StringBuilder sb = new StringBuilder();
		//0-workcount,1-worklength
		sb.append(" select count(distinct gw.govt_work_id),sum(gw.work_length) "
				+ " from govt_work gw,govt_main_work gmw,location_address la "
				+ " where gw.govt_main_work_id=gmw.govt_main_work_id and gmw.location_address_id=la.location_address_id and gw.is_deleted='N' "
				+ " and gmw.govt_work_type_id=:workTypeId ");
		
		if(locationScopeId == 3l){
			sb.append(" and la.district_id=:locationValue ");
		}else if(locationScopeId == 12l){
			sb.append(" and la.division_id=:locationValue ");
		}else if(locationScopeId == 13l){
			sb.append(" and la.sub_division_id=:locationValue ");
		}else if(locationScopeId == 5l){
			sb.append(" and la.tehsil_id=:locationValue ");
		}else if(locationScopeId == 6l){
			sb.append(" and la.panchayat_id=:locationValue ");
		} 
		
		Query query = getSession().createSQLQuery(sb.toString());
		
		query.setParameter("workTypeId", workTypeId);
		
		if(locationScopeId == 3l || locationScopeId == 12l || locationScopeId == 13l || locationScopeId == 5l || locationScopeId == 6l){
			query.setParameter("locationValue", locationValue);
		}
		
		return (Object[]) query.uniqueResult();
	}
	
	public Object[] getCompletedWorksDetailsOfLocation(Long locationScopeId,Long locationValue,Long workTypeId){
		StringBuilder sb = new StringBuilder();
		//0-workcount,1-worklength
		sb.append(" select count(distinct gw.govt_work_id),sum(gw.work_length) "
				+ " from govt_work gw,govt_main_work gmw,location_address la "
				+ " where gw.govt_main_work_id=gmw.govt_main_work_id and gmw.location_address_id=la.location_address_id and gw.is_deleted='N' "
				+ " and gmw.govt_work_type_id=:workTypeId and gw.completed_percentage >= 99.9 ");
		
		if(locationScopeId == 3l){
			sb.append(" and la.district_id=:locationValue ");
		}else if(locationScopeId == 12l){
			sb.append(" and la.division_id=:locationValue ");
		}else if(locationScopeId == 13l){
			sb.append(" and la.sub_division_id=:locationValue ");
		}else if(locationScopeId == 5l){
			sb.append(" and la.tehsil_id=:locationValue ");
		}else if(locationScopeId == 6l){
			sb.append(" and la.panchayat_id=:locationValue ");
		} 
		
		Query query = getSession().createSQLQuery(sb.toString());
		
		query.setParameter("workTypeId", workTypeId);
		
		if(locationScopeId == 3l || locationScopeId == 12l || locationScopeId == 13l || locationScopeId == 5l || locationScopeId == 6l){
			query.setParameter("locationValue", locationValue);
		}
		
		return (Object[]) query.uniqueResult();
	}
	public Object[] getWorkZoneMainOverview(Long govtWorkId){
		//0-worklength,estimatedCost-1
		Query query = getSession().createQuery("select model.workLength,model.govtMainWork.estimateCost "
				+ " from GovtWork model" +
				" where model.isDeleted ='N' and model.govtWorkId =:govtWorkId ");
		
		query.setParameter("govtWorkId",govtWorkId);
		return (Object[]) query.uniqueResult();
	}
}
