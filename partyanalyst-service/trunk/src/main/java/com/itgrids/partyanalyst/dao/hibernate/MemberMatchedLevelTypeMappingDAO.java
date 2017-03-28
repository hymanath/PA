package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMemberMatchedLevelTypeMappingDAO;
import com.itgrids.partyanalyst.model.MemberMatchedLevelTypeMapping;

public class MemberMatchedLevelTypeMappingDAO extends GenericDaoHibernate<MemberMatchedLevelTypeMapping, Long> implements
		IMemberMatchedLevelTypeMappingDAO {

	public MemberMatchedLevelTypeMappingDAO() {
		super(MemberMatchedLevelTypeMapping.class);
	}
	
	
}
