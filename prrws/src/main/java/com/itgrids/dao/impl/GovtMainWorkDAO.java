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
	
	public List<Object[]> getAllMainWorksForUser(Long userId,Long govtWorkTypeId){
		//0-workId,1-name,2-kms
		Query query = getSession().createQuery(" select model.govtMainWork.govtMainWorkId,model.govtMainWork.govtMainWorkName,model.govtMainWork.approvedKm "
				+ " from GovtMainWorkUser model "
				+ " where model.userId=:userId and model.govtMainWork.govtWorkTypeId=:govtWorkTypeId ");
		query.setParameter("userId", userId);
		query.setParameter("govtWorkTypeId", govtWorkTypeId);
		return query.list();
	}
}
