package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMemberMatchedTypeDAO;
import com.itgrids.partyanalyst.model.MemberMatchedType;

public class MemberMatchedTypeDAO extends GenericDaoHibernate<MemberMatchedType, Long>  implements IMemberMatchedTypeDAO {
	
	public MemberMatchedTypeDAO() {
		super(MemberMatchedType.class);
	}
}
