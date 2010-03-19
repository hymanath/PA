package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.model.HamletBoothElection;

public class HamletBoothElectionDAO extends GenericDaoHibernate<HamletBoothElection, Long> implements IHamletBoothElectionDAO{

	public HamletBoothElectionDAO(){
		super(HamletBoothElection.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> findByHamletAndBoothConstituencyElection(Long hamletId,
			Long boothConstituencyElectionId) {
		Object[] params = {hamletId, boothConstituencyElectionId};	
		return getHibernateTemplate().find("select model.hamletBoothElectionId from HamletBoothElection model where " +
				"model.hamlet.hamletId = ? and model.boothConstituencyElection.boothConstituencyElectionId = ?",params);
	}
	
}
