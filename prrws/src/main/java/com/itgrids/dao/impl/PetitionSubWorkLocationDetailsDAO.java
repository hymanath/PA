package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionSubWorkLocationDetailsDAO;
import com.itgrids.model.PetitionSubWorkLocationDetails;

@Repository
public class PetitionSubWorkLocationDetailsDAO extends GenericDaoHibernate<PetitionSubWorkLocationDetails, Long>
		implements IPetitionSubWorkLocationDetailsDAO {

	@Autowired
	SessionFactory sessionFactory;

	public PetitionSubWorkLocationDetailsDAO() {
		super(PetitionSubWorkLocationDetails.class);
	}

	public List<PetitionSubWorkLocationDetails> getSubWorkDetailsByWorkDetailsIdsList(List<Long> workDetailsIdsList){
		StringBuilder str = new StringBuilder();
		str.append("select model from PetitionSubWorkLocationDetails model where model.petitionWorkDetailsId in (:workDetailsIdsList) and " +
				" model.isDeleted='N' ");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("workDetailsIdsList", workDetailsIdsList);
		return query.list();
	}
	
}
