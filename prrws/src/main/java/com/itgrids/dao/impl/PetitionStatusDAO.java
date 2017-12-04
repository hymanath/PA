package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionStatusDAO;
import com.itgrids.model.PetitionStatus;
@Repository
public class PetitionStatusDAO extends GenericDaoHibernate<PetitionStatus,Long> implements IPetitionStatusDAO {

	@Autowired
	SessionFactory sessionFactory; 
	
	PetitionStatusDAO(){
		super(PetitionStatus.class);
	}
	
	public List<Object[]> getPetitionStatusList(){
		Query qry = getSession().createQuery(" select model.petitionStatusId,model.description from PetitionStatus model ");
		return qry.list();
	}
}
