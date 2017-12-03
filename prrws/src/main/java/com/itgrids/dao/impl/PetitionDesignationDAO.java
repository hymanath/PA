package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionDesignationDAO;
import com.itgrids.model.PetitionDesignation;

@Repository
public class PetitionDesignationDAO extends GenericDaoHibernate<PetitionDesignation, Long> implements IPetitionDesignationDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	public PetitionDesignationDAO() {
		super(PetitionDesignation.class);
		
	}
	
	public List<Object[]> getpetitionDesignationList(){
		
		StringBuilder sb = new StringBuilder();
		sb.append("select model.petitionDesignationId,model.designationName from PetitionDesignation model order by model.designationName asc ");
		Query qry = getSession().createQuery(sb.toString());
		return qry.list();
	}

	

}
