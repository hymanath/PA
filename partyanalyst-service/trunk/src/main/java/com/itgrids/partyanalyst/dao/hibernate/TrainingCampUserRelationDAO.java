package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampUserRelationDAO;
import com.itgrids.partyanalyst.model.TrainingCampUserRelation;

public class TrainingCampUserRelationDAO extends GenericDaoHibernate<TrainingCampUserRelation, Long> implements ITrainingCampUserRelationDAO{

	public TrainingCampUserRelationDAO() {
		super(TrainingCampUserRelation.class);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAgentsByCampCallerAdminId(Long campCallerAdminId,boolean isAdmin)
	{
		StringBuilder str = new StringBuilder();
		
		str.append("select distinct model.trainingCampCaller.userId,model.trainingCampCaller.middleName,model.trainingCampCaller.lastName" +
				" from TrainingCampUserRelation model where model.isDeleted = 'N' ");
		
		if(!isAdmin){
			str.append(" and model.trainingCampCallAdmin.userId =:campCallerAdminId ");
		}
		str.append(" order by " +
				" model.trainingCampCaller.firstName ");
		
		
		Query query = getSession().createQuery(str.toString());
		
		if(!isAdmin){
			query.setParameter("campCallerAdminId", campCallerAdminId);
		}
		return query.list();
	}

}
