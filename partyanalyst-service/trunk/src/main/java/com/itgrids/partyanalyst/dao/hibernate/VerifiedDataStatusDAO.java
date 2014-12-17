package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVerifiedDataStatusDAO;
import com.itgrids.partyanalyst.model.VerifiedDataStatus;

public class VerifiedDataStatusDAO  extends GenericDaoHibernate<VerifiedDataStatus, Long> implements IVerifiedDataStatusDAO {

	public VerifiedDataStatusDAO() {
		super(VerifiedDataStatus.class);
	}


	public List<Object[]> getExistingFamilyRecordStatus(List<String> uniqueKeys,List<Long> userIds){
		//0 uniqueKey,1 status
		Query query = getSession().createQuery("select model.uniqueKey,model.status from VerifiedDataStatus model where " +
				"model.uniqueKey in (:uniqueKeys) and model.userId in (:userIds) ");
		
		query.setParameterList("uniqueKeys", uniqueKeys);
		query.setParameterList("userIds", userIds);
		return query.list();
	}
	
	public void updateStatus(List<String> uniqueKeys,List<Long> userIds,Date currentTime,String status){
		Query query = getSession().createQuery("update VerifiedDataStatus model set model.status=:status,model.updatedTime = :currentTime " +
				" where model.uniqueKey in (:uniqueKeys) and model.userId in (:userIds) ");
		query.setParameterList("uniqueKeys", uniqueKeys);
		query.setParameterList("userIds", userIds);
		query.setParameter("currentTime", currentTime);
		query.setParameter("status", status);
		query.executeUpdate();
	}
}
