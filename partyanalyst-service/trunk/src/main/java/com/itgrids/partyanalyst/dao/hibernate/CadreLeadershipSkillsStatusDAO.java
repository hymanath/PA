package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreLeadershipSkillsStatusDAO;
import com.itgrids.partyanalyst.model.CadreLeadershipSkillsStatus;

public class CadreLeadershipSkillsStatusDAO extends GenericDaoHibernate<CadreLeadershipSkillsStatus, Long> implements ICadreLeadershipSkillsStatusDAO{

	public CadreLeadershipSkillsStatusDAO() {
		super(CadreLeadershipSkillsStatus.class);
	}
    
    public List<Object[]> getAllCadreLeadershipSkills(){
	
		Query query=getSession().createQuery(" select model.cadreLeadershipSkillsStatusId,model.status " +
				"from  CadreLeadershipSkillsStatus model ");
	    return query.list();
	}
} 
