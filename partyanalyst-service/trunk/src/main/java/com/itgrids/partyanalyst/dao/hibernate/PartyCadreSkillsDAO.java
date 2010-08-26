package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyCadreSkillsDAO;
import com.itgrids.partyanalyst.model.PartyCadreSkills;

public class PartyCadreSkillsDAO extends GenericDaoHibernate<PartyCadreSkills ,Long> implements IPartyCadreSkillsDAO {

	public PartyCadreSkillsDAO() {
		super(PartyCadreSkills.class);
		
	}

	@SuppressWarnings("unchecked")
	public List<PartyCadreSkills> getCadreSkillsPartywise(Long partyId) {
		
		return getHibernateTemplate().find("from PartyCadreSkills model where model.party.partyId = ?", partyId);
	}

}
