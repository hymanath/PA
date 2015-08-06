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

}
