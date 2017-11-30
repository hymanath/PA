package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionMemberDAO;
import com.itgrids.model.PetitionMember;

@Repository
public class PetitionMemberDAO extends GenericDaoHibernate<PetitionMember, Long> implements IPetitionMemberDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	PetitionMemberDAO(){
	super(PetitionMember.class);
	} 
	
}
