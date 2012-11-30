package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.model.BoothPublicationVoter;

public class BoothPublicationVoterDAO extends
		GenericDaoHibernate<BoothPublicationVoter, Long> implements
		IBoothPublicationVoterDAO {
	
	
	public BoothPublicationVoterDAO(){
		super(BoothPublicationVoter.class);
		
	}
	
}
