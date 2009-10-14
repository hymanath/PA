package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBoothResultDAO;
import com.itgrids.partyanalyst.model.BoothResult;

public class BoothResultDAO extends GenericDaoHibernate<BoothResult, Long> implements IBoothResultDAO{

	public BoothResultDAO(){
		super(BoothResult.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<BoothResult> findByProperty(Object value) {
		return getHibernateTemplate().find("from BoothResult model where model.nomination.nominationId = ?", value);		
	}
	
	@SuppressWarnings("unchecked")
	public BoothResult findByNominationAndBoothConstituencyElection(Long nominationId, Long boothConstituencyElectionId) {
		BoothResult boothResult = null;
		Object[] params = {nominationId, boothConstituencyElectionId};
		List<BoothResult> list = getHibernateTemplate().find("from BoothResult model where model.nomination.nominationId = ? and model.nomination.constituencyElection.constiElecId = ?", params);
		if(list != null && list.size() > 0)
			boothResult  = list.get(0);
		return boothResult;
	}
	
}
