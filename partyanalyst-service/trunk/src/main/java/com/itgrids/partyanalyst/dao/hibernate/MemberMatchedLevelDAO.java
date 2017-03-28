package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMemberMatchedLevelDAO;
import com.itgrids.partyanalyst.model.MemberMatchedLevel;

public class MemberMatchedLevelDAO extends GenericDaoHibernate<MemberMatchedLevel, Long> implements IMemberMatchedLevelDAO {
	
	public MemberMatchedLevelDAO() {
		super(MemberMatchedLevel.class);
	}
	
}
