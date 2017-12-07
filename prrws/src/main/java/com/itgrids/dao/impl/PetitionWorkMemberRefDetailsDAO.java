package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionWorkMemberRefDetailsDAO;
import com.itgrids.model.PetitionWorkMemberRefDetails;

@Repository
public class PetitionWorkMemberRefDetailsDAO extends GenericDaoHibernate<PetitionWorkMemberRefDetails, Long>
		implements IPetitionWorkMemberRefDetailsDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	PetitionWorkMemberRefDetailsDAO(){
		super(PetitionWorkMemberRefDetails.class);
	}
}
