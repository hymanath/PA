package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAdminHouseTermMemberDAO;
import com.itgrids.partyanalyst.model.AdminHouseTermMember;

public class AdminHouseTermMemberDAO extends GenericDaoHibernate<AdminHouseTermMember, Long> implements IAdminHouseTermMemberDAO{

	public AdminHouseTermMemberDAO() {
		super(AdminHouseTermMember.class);
		
	}

}
