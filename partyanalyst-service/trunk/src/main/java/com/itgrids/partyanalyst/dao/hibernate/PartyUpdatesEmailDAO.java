package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IPartyUpdatesEmailDAO;
import com.itgrids.partyanalyst.model.PartyUpdatesEmail;

public class PartyUpdatesEmailDAO  extends GenericDaoHibernate<PartyUpdatesEmail,Long> implements IPartyUpdatesEmailDAO{

public PartyUpdatesEmailDAO()
{
	super(PartyUpdatesEmail.class);
}

public List<Object> getPartyUpdatesEmail(String emailId, Long partyId) 
{
	Object[] params = {partyId,emailId};
	return getHibernateTemplate().find("select model from PartyUpdatesEmail model where model.party.partyId = ? and model.email = ?",params);
}

}
