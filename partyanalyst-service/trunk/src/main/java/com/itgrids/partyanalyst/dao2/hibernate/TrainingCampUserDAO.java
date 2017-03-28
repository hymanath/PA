package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampUserDAO;
import com.itgrids.partyanalyst.model.TrainingCampUser;

public class TrainingCampUserDAO extends GenericDaoHibernate<TrainingCampUser, Long> implements ITrainingCampUserDAO{

	public TrainingCampUserDAO() {
		super(TrainingCampUser.class);
	}
	
	public List<Long> getTrainingCampUserTypeIds(Long typeId){
		
		Query query=getSession().createQuery("select model.userId from TrainingCampUser model " +
				" where model.trainingCampUserType.trainingCampUserTypeId =:typeId ");
		query.setParameter("typeId", typeId);
		return query.list();
	}
	public List<Object[]> getUserIdsByType(Long typeId){
		
		Query query=getSession().createQuery("select model.user.userId,model.user.lastName from TrainingCampUser model " +
				" where model.trainingCampUserType.trainingCampUserTypeId =:typeId ");
		query.setParameter("typeId", typeId);
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getuserDetailsByUserId(Long userId)
	{
		Query query=getSession().createQuery("select distinct model.user.userId,model.user.lastName from TrainingCampUser model " +
				" where model.user.userId =:userId ");
		query.setParameter("userId", userId);
		return query.list();
	}
	
	
}
