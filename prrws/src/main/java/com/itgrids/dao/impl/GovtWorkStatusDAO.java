package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IGovtWorkStatusDAO;
import com.itgrids.model.GovtWorkStatus;

@Repository
public class GovtWorkStatusDAO extends GenericDaoHibernate<GovtWorkStatus, Long> implements IGovtWorkStatusDAO{

	public GovtWorkStatusDAO() {
		super(GovtWorkStatus.class);
	}

	public List<Object[]> getAllStatusOfWorkType(Long workTypeId){
		//0-statusTypeId,1-statusType,2-govtWorkStatusId,3-govtWorkStatus
		Query query = getSession().createQuery(" select model.statusType.statusTypeId,model.statusType.typeName,model.govtWorkStatusId,model.statusName "
				+ " from GovtWorkStatus model "
				+ " where model.govtWorkTypeId=:workTypeId ");
		query.setParameter("workTypeId", workTypeId);
		return query.list();
	}
}
