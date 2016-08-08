package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IPartyEventTypeDAO;
import com.itgrids.partyanalyst.model.PartyEventType;

public class PartyEventTypeDAO extends GenericDaoHibernate<PartyEventType, Long> implements IPartyEventTypeDAO {
	public PartyEventTypeDAO(){
		super(PartyEventType.class);
	}

}
