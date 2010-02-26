package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITownshipElectionPartyResultDAO;
import com.itgrids.partyanalyst.model.TownshipElectionPartyResult;

public class TownshipElectionPartyResultDAO extends GenericDaoHibernate<TownshipElectionPartyResult, Long> 
						implements ITownshipElectionPartyResultDAO{
	public TownshipElectionPartyResultDAO(){
		super(TownshipElectionPartyResult.class);
	}
	
	public List<TownshipElectionPartyResult> findByTownshipID(Long townshipID){
		return getHibernateTemplate().find("from TownshipElectionPartyResult model where model.township.townshipId = ?", townshipID);	
	}
}
