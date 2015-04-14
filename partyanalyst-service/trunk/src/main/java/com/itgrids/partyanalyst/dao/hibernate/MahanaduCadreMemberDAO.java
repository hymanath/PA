package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMahanaduCadreMemberDAO;
import com.itgrids.partyanalyst.model.MahanaduCadreMember;

public class MahanaduCadreMemberDAO extends GenericDaoHibernate<MahanaduCadreMember, Long> implements IMahanaduCadreMemberDAO{

	public MahanaduCadreMemberDAO() {
		super(MahanaduCadreMember.class);
	}

}
