package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyUpdatesEmailDAO;
import com.itgrids.partyanalyst.model.PartyUpdatesEmail;

public class PartyUpdatesEmailDAO  extends GenericDaoHibernate<PartyUpdatesEmail,Long> implements IPartyUpdatesEmailDAO{
public PartyUpdatesEmailDAO()
{
	super(PartyUpdatesEmail.class);
}
}
