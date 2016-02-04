package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpMemberTypeDAO;
import com.itgrids.partyanalyst.model.TdpMemberType;

public class TdpMemberTypeDAO extends GenericDaoHibernate<TdpMemberType, Long> implements ITdpMemberTypeDAO{

	public TdpMemberTypeDAO() {
		super(TdpMemberType.class);
		
	}

}
