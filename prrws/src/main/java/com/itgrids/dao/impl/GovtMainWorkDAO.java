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
		Query query = getSession().createQuery("select model.govtWorkTypeId,count(distinct model.locationValue) "
				+ " from GovtMainWork model "
				+ " group by model.govtWorkTypeId ");
		return query.list();
	}
	
	public List<Object[]> getAllMainWorksForUser(Long govtWorkTypeId,Long locationScopeId,List<Long> locationValues){
		//0-workId,1-name,2-kms
		Query query = getSession().createQuery(" select model.govtMainWorkId,model.govtMainWorkName,model.approvedKm "
				+ " from GovtMainWork model "
				+ " where model.govtWorkTypeId=:govtWorkTypeId "
				+ " and model.locationScopeId=:locationScopeId and model.locationValue in (:locationValues) ");
		
		query.setParameter("govtWorkTypeId", govtWorkTypeId);
		query.setParameter("locationScopeId", locationScopeId);
		query.setParameterList("locationValues", locationValues);
		return query.list();
	}
}
