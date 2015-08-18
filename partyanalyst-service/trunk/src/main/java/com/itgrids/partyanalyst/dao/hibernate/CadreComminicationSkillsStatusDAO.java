package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreComminicationSkillsStatusDAO;
import com.itgrids.partyanalyst.model.CadreComminicationSkillsStatus;

public class CadreComminicationSkillsStatusDAO extends GenericDaoHibernate<CadreComminicationSkillsStatus, Long> implements ICadreComminicationSkillsStatusDAO{

	public CadreComminicationSkillsStatusDAO() {
		super(CadreComminicationSkillsStatus.class);
	}
    public List<Object[]> getAllCadreComminicationSkills(){
		
		Query query=getSession().createQuery(" select model.cadreComminicationSkillsStatusId,model.status " +
			" from  CadreComminicationSkillsStatus model ");
	    return query.list();
	}

}
