package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;

public class BoothConstituencyElectionDAO extends GenericDaoHibernate<BoothConstituencyElection, Long> implements IBoothConstituencyElectionDAO{

	public BoothConstituencyElectionDAO(){
		super(BoothConstituencyElection.class);
	}
	
	@SuppressWarnings("unchecked")
	public BoothConstituencyElection findByBoothAndConstiuencyElection(Long partNo, Long constituencyElectionId) {
		
		BoothConstituencyElection boothConstituencyElection = null;
		Object[] params = {partNo, constituencyElectionId};
		List<BoothConstituencyElection> boothConstituencyElections = getHibernateTemplate().find("from BoothConstituencyElection model where model.booth.partNo=? and model.constituencyElection.constiElecId = ?", params);
		if(boothConstituencyElections != null && boothConstituencyElections.size() > 0){
			boothConstituencyElection = boothConstituencyElections.get(0);
		}
		return boothConstituencyElection;
	}
}
