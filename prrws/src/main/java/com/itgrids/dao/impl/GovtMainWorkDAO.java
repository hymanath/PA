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

	public List<Object[]> getPraposalWorksCount(){
		//0-workTypeId,1-count
		Query query = getSession().createQuery("select model.govtWorkTypeId,count(model.govtMainWorkId) "
				+ " from GovtMainWork model "
				+ " group by model.govtWorkTypeId ");
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
}
