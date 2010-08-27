package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreSkillsDAO;
import com.itgrids.partyanalyst.model.CadreSkills;
import com.itgrids.partyanalyst.model.PartyCadreSkills;

public class CadreSkillsDAO  extends GenericDaoHibernate<CadreSkills ,Long> implements ICadreSkillsDAO  {

	public CadreSkillsDAO() {
		super(CadreSkills.class);		
	}

}
