package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDebateParticipantCharcsDAO;
import com.itgrids.partyanalyst.model.DebateParticipantCharcs;

public class DebateParticipantCharcsDAO extends GenericDaoHibernate<DebateParticipantCharcs, Long> implements IDebateParticipantCharcsDAO{

	public DebateParticipantCharcsDAO() {
		super(DebateParticipantCharcs.class);
		// TODO Auto-generated constructor stub
	}
	

}
