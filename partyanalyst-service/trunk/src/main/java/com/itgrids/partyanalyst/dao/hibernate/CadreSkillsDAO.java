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
		Query queryObject = getSession().createQuery("select model.cadre.cadreId from CadreSkills model where model.partyCadreSkills.partyCadreSkillId = ? and "+
				"model.cadre.cadreId in (:cadreIds)");
		queryObject.setParameter(0, skillId);
		queryObject.setParameterList("cadreIds", cadreIds);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List getCadreIdsByCadreSkillAndUser(Long userId, Long skillId) {
		Object[] params = {userId,skillId};
		return getHibernateTemplate().find("select model.cadre.cadreId from CadreSkills model where model.cadre.user.userId = ?"+
				" and model.partyCadreSkills.partyCadreSkillId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getCadreBySkillListAndCadreIds(List<Long> skillId,
			List<Long> cadreIds) {
		Query queryObject = getSession().createQuery("select model.cadre.cadreId from CadreSkills model where model.partyCadreSkills.partyCadreSkillId in (:skillId) and "+
		       "model.cadre.cadreId in (:cadreIds)");
		queryObject.setParameterList("skillId", skillId);
		queryObject.setParameterList("cadreIds", cadreIds);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List getCadreIdsByCadreSkillListAndUser(Long userId,
			List<Long> skillIds) {
		Query queryObject = getSession().createQuery("select model.cadre.cadreId from CadreSkills model where model.cadre.user.userId = ? and "+
		      "model.partyCadreSkills.partyCadreSkillId in (:skillIds)");
		queryObject.setParameter(0, userId);
		queryObject.setParameterList("skillIds", skillIds);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List<CadreSkills> findByCadreId(Long cadreId) {
		
		return getHibernateTemplate().find("from CadreSkills model where model.cadre.cadreId = ?", cadreId);
		
	}

	public Integer deleteSkillsByCadreId(Long cadreId) {
		
		Query queryObject = getSession().createQuery("delete from CadreSkills model where model.cadre.cadreId = ?");
		queryObject.setParameter(0, cadreId);
		return queryObject.executeUpdate();
	}

}
