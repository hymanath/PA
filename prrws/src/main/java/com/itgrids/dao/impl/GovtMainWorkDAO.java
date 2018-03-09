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
}
