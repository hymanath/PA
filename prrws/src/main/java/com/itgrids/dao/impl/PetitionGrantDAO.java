package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionGrantDAO;
import com.itgrids.model.PetitionGrant;
@Repository
public class PetitionGrantDAO extends GenericDaoHibernate<PetitionGrant, Long> implements IPetitionGrantDAO {

	@Autowired
	SessionFactory sessionFactory;

	PetitionGrantDAO(){
		super(PetitionGrant.class);
	}
public List<Object[]> getPetitionGrantList(){
		
		Query qry = getSession().createQuery(" select model.petitionGrantId,model.grantUnder from  PetitionGrant model ");
		return qry.list();
	}
	
}
