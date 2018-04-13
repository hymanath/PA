package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IGovtMainWorkDAO;
import com.itgrids.model.GovtMainWork;
import com.itgrids.model.GovtWork;

@Repository
public class GovtMainWorkDAO extends GenericDaoHibernate<GovtMainWork, Long> implements IGovtMainWorkDAO{

	public GovtMainWorkDAO() {
		super(GovtMainWork.class);
	}

	public List<Object[]> getPraposalWorksCount(Long workTypeId){
		//0-workTypeId,1-count,2-kms
		StringBuilder sb = new StringBuilder();
		sb.append("select gmw.govt_work_type_id,count(gmw.govt_main_work_id),sum(gmw.approved_km),sum(gmw.estimate_cost)  "
				+ " from govt_main_work gmw ");
		
		if(workTypeId != null && workTypeId > 0l){
			sb.append(" where gmw.govt_work_type_id=:workTypeId ");
		}else{
			sb.append(" group by gmw.govt_work_type_id ");
		}
		
		Query query = getSession().createSQLQuery(sb.toString());
		
		if(workTypeId != null && workTypeId > 0l){
			query.setParameter("workTypeId", workTypeId);
		}
		
		return query.list();
	}
	
	public List<Object[]> getAllMainWorksForUser(Long govtWorkTypeId,Long locationScopeId,List<Long> locationValues){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.govtMainWorkId,model.govtMainWorkName,model.approvedKm "
				+ " from GovtMainWork model where model.govtWorkTypeId=:govtWorkTypeId ");
		
		
		if(locationScopeId == 3l){
			sb.append(" and model.locationAddress.districtId in (:locationValues) ");
		}else if(locationScopeId == 4l){
			sb.append(" and model.locationAddress.constituencyId in (:locationValues) ");
		}else if(locationScopeId == 5l){
			sb.append(" and model.locationAddress.tehsilId in (:locationValues) ");
		}else if(locationScopeId == 6l){
			sb.append(" and model.locationAddress.panchayatId in (:locationValues) ");
		}else if(locationScopeId == 12l){
			sb.append(" and model.locationAddress.divisionId in (:locationValues) ");
		}else if(locationScopeId == 13l){
			sb.append(" and model.locationAddress.subDivisionId in (:locationValues) ");
		}
		
		//0-workId,1-name,2-kms
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("govtWorkTypeId", govtWorkTypeId);
		
		if(locationScopeId == 3l || locationScopeId == 4l || locationScopeId == 5l || locationScopeId == 6l || locationScopeId == 12l || locationScopeId == 13l ){
			query.setParameterList("locationValues", locationValues);
		}
		
		return query.list();
	}
	
	public Object getEstimationCosrOfLocationBasedMainWorks(Long locationScopeId,Long locationValue,Long workTypeId){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select sum(gmw.estimate_cost) "
				+ " from govt_main_work gmw,location_address la "
				+ " where gmw.location_address_id=la.location_address_id and gmw.govt_work_type_id=:workTypeId ");
		
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
		
		return query.uniqueResult();
	}
	
	public List<Object[]> getLocationWiseWorksTotalKms(List<Long> locationIds,Long locationLevelId,String workZoneReq){
		//0-locationId,1-kms(,2-wokrZoneId)
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		if(locationLevelId == 3l){
			sb.append(" la.district_id ");
		}else if(locationLevelId == 12l){
			sb.append(" la.division_id ");
		}else if(locationLevelId == 13l){
			sb.append(" la.sub_division_id ");
		}else if(locationLevelId == 5l){
			sb.append(" la.tehsil_id ");
		}else if(locationLevelId == 6l){
			sb.append(" la.panchayat_id ");
		} 
		
		
		if(workZoneReq != null && workZoneReq.equalsIgnoreCase("Y")){
			sb.append(",gw.work_length,gw.govt_work_id  ");
		}else{
			sb.append(" ,sum(gmw.approved_km) ");
		}
		
		sb.append("from govt_main_work gmw,location_address la ");
		if(workZoneReq != null && workZoneReq.equalsIgnoreCase("Y")){
			sb.append(",govt_work gw ");
		}
		sb.append(" where gmw.location_address_id=la.location_address_id ");
		if(workZoneReq != null && workZoneReq.equalsIgnoreCase("Y")){
			sb.append(" and gw.govt_main_work_id=gmw.govt_main_work_id ");
		}
		
		if(locationLevelId == 3l){
			sb.append(" and la.district_id in (:locationIds) ");
		}else if(locationLevelId == 12l){
			sb.append(" and la.division_id in (:locationIds) ");
		}else if(locationLevelId == 13l){
			sb.append(" and la.sub_division_id in (:locationIds) ");
		}else if(locationLevelId == 5l){
			sb.append(" and la.tehsil_id in (:locationIds) ");
		}else if(locationLevelId == 6l){
			sb.append(" and la.panchayat_id in (:locationIds) ");
		} 
		
		sb.append(" group by ");
		
		if(locationLevelId == 3l){
			sb.append(" la.district_id ");
		}else if(locationLevelId == 12l){
			sb.append(" la.division_id ");
		}else if(locationLevelId == 13l){
			sb.append(" la.sub_division_id ");
		}else if(locationLevelId == 5l){
			sb.append(" la.tehsil_id ");
		}else if(locationLevelId == 6l){
			sb.append(" la.panchayat_id ");
		} 
		if(workZoneReq != null && workZoneReq.equalsIgnoreCase("Y")){
			sb.append(",gw.govt_work_id ");
		}
		
		Query query = getSession().createSQLQuery(sb.toString());
		
		if(locationLevelId == 3l || locationLevelId == 12l || locationLevelId == 13l || locationLevelId == 5l || locationLevelId == 6l){
			query.setParameterList("locationIds", locationIds);
		}
		
		return query.list();
	}
	
	public List<Object[]> getLocationLevelWiseLocationDetails(Long locationScopeId,Long districtId,Long divisionId,Long subDivisionId,Long mandalId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		
		if(locationScopeId == 3l){
			sb.append(" la.district_id,dist.district_name, ");
		}else if(locationScopeId == 4l){
			sb.append(" la.constituency_id,const.name, ");
		}else if(locationScopeId == 5l){
			sb.append(" la.tehsil_id,teh.tehsil_name, ");
		}else if(locationScopeId == 6l){
			sb.append(" la.panchayat_id,panch.panchayat_name, ");
		}else if(locationScopeId == 12l){
			sb.append(" la.division_id,divi.division_name, ");
		}else if(locationScopeId == 13l){
			sb.append(" la.sub_division_id,subDivi.division_name, ");
		} 
		
		sb.append("sum(gmw.approved_km) ");
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
		
		sb.append(" govt_main_work gmw,location_address la "
				+ " where gmw.location_address_id=la.location_address_id ");
		
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
		
		return query.list();
				
	}
}
