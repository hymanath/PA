package com.itgrids.partyanalyst.dao.hibernate;


import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IJbCommitteeLevelDAO;
import com.itgrids.partyanalyst.model.JbCommitteeLevel;

public class JbCommitteeLevelDAO extends GenericDaoHibernate<JbCommitteeLevel, Long> implements IJbCommitteeLevelDAO {

	public JbCommitteeLevelDAO() {
		super(JbCommitteeLevel.class);
		
	}

	

}
