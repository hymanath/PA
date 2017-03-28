package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IPartyUpdatesEmailDAO;
import com.itgrids.partyanalyst.model.PartyUpdatesEmail;
import com.itgrids.partyanalyst.utils.IConstants;

public class PartyUpdatesEmailDAO  extends GenericDaoHibernate<PartyUpdatesEmail,Long> implements IPartyUpdatesEmailDAO{

public PartyUpdatesEmailDAO()
{
	super(PartyUpdatesEmail.class);
}

@SuppressWarnings("unchecked")
public List<Object> getPartyUpdatesEmail(String emailId, Long partyId) 
{
	Object[] params = {partyId,emailId};
	return getHibernateTemplate().find("select model from PartyUpdatesEmail model where model.party.partyId = ? and model.email = ?",params);
}

@SuppressWarnings("unchecked")
public List<PartyUpdatesEmail> getAllSubscriberDetails() 
{

	return getHibernateTemplate().find("select model from PartyUpdatesEmail model where model.unsubscribed = ?",IConstants.FALSE);
}

}
