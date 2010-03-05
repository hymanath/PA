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
	
	public List findByTownshipIDElectionID(Long townshipID,Long electionID){
		return getHibernateTemplate().find("select model.nomination.party.partyId, model.nomination.party.shortName," +
				"sum(model.votesEarned) from TownshipElectionPartyResult model where model.township.townshipId = ? group by" +
				" model.nomination.party.partyId", townshipID);	
	}
}
