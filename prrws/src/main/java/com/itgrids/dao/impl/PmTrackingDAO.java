package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmTrackingDAO;
import com.itgrids.model.PmTracking;
import com.sun.org.apache.bcel.internal.generic.Select;

@Repository
public class PmTrackingDAO extends GenericDaoHibernate<PmTracking, Long> implements IPmTrackingDAO {

	@Autowired
	SessionFactory sessionFactory;
	PmTrackingDAO(){
		super(PmTracking.class);
	}
	
	public List<Object[]> getPetitionTrackingHistoryDetails(Long petitionId,List<Long> subWorksList){
		return null;
	}
	
	public List<Object[]> getLatestTrackingDetails(Long petitionId,List<Long> subWorksList){
		StringBuilder str = new StringBuilder();
		str.append(" select model.petitionId, model.pmSubWorkDetailsId, pmTrackingAction.actionName, document.path, model.remarks, model.insertedTime," +
				"  insertedUser.username,pmStatus.status, pmOfficer.name, pmOfficer.mobileNo, pmOfficerDesignation.designation " +
				" from PmTracking model  " +
				" left join  model.pmTrackingAction pmTrackingAction " +
				" left join  model.document document " +
				" left join  model.insertedUser insertedUser " +
				" left join model.pmStatus pmStatus" +
				" left join model.pmDepartmentDesignationOfficer pmDepartmentDesignationOfficer " +
				" left join pmDepartmentDesignationOfficer.pmOfficer pmOfficer" +
				" left join pmDepartmentDesignationOfficer.pmDepartmentDesignation pmDepartmentDesignation" +
				" left join pmDepartmentDesignation.pmOfficerDesignation pmOfficerDesignation" +
				"  where model.petitionId =:petitionId ");
		if(subWorksList != null && subWorksList.size()>0)
			str.append(" and model.pmSubWorkDetailsId in (:subWorksList) ");
		str.append(" order by model.insertedTime desc ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("petitionId", petitionId);
		if(subWorksList != null && subWorksList.size()>0)
			query.setParameterList("subWorksList", subWorksList);
		
		return query.list();
	}
}
