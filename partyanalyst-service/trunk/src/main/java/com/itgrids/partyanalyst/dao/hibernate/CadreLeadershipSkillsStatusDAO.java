package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreLeadershipSkillsStatusDAO;
import com.itgrids.partyanalyst.model.CadreLeadershipSkillsStatus;

public class CadreLeadershipSkillsStatusDAO extends GenericDaoHibernate<CadreLeadershipSkillsStatus, Long> implements ICadreLeadershipSkillsStatusDAO{

	public CadreLeadershipSkillsStatusDAO() {
		super(CadreLeadershipSkillsStatus.class);
		
	}

}
