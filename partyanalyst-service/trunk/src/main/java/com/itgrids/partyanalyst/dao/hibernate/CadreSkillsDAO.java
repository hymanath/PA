package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreSkillsDAO;
import com.itgrids.partyanalyst.model.CadreSkills;
import com.itgrids.partyanalyst.model.PartyCadreSkills;

public class CadreSkillsDAO  extends GenericDaoHibernate<CadreSkills ,Long> implements ICadreSkillsDAO  {

	public CadreSkillsDAO() {
		super(CadreSkills.class);		
	}

	@SuppressWarnings("unchecked")
	public List getCadreBySkillAndCadreIds(Long skillId, List<Long> cadreIds) {
		Query queryObject = getSession().createQuery("select model.cadre.cadreId from CadreSkills model where model.partyCadreSkills.partyCadreSkillId = "+skillId+" and "+
				"model.cadre.cadreId in (:cadreIds)");
		queryObject.setParameterList("cadreIds", cadreIds);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List getCadreIdsByCadreSkillAndUser(Long userId, Long skillId) {
		Object[] params = {userId,skillId};
		return getHibernateTemplate().find("select model.cadre.cadreId from CadreSkills model where model.cadre.registration.registrationId = ?"+
				" and model.partyCadreSkills.partyCadreSkillId = ?",params);
	}

}
