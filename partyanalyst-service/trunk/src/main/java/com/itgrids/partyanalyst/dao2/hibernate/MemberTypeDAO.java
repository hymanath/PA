package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;


import com.itgrids.partyanalyst.dao.IMemberTypeDAO;
import com.itgrids.partyanalyst.model.MemberType;

public class MemberTypeDAO extends GenericDaoHibernate<MemberType, Long> implements IMemberTypeDAO{

	public MemberTypeDAO() {
		super(MemberType.class);
		// TODO Auto-generated constructor stub
	}
	
	
	

}
