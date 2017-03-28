package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAdminHouseMemberDAO;
import com.itgrids.partyanalyst.model.AdminHouseMember;

public class AdminHouseMemberDAO extends GenericDaoHibernate<AdminHouseMember, Long> implements IAdminHouseMemberDAO{

	public AdminHouseMemberDAO() {
		super(AdminHouseMember.class);
		
	}
}
