package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreComminicationSkillsStatusDAO;
import com.itgrids.partyanalyst.model.CadreComminicationSkillsStatus;

public class CadreComminicationSkillsStatusDAO extends GenericDaoHibernate<CadreComminicationSkillsStatus, Long> implements ICadreComminicationSkillsStatusDAO{

	public CadreComminicationSkillsStatusDAO() {
		super(CadreComminicationSkillsStatus.class);
		
	}

}
