package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IKaizalaEventsResponseDAO;
import com.itgrids.partyanalyst.model.KaizalaEventsResponse;

public class KaizalaEventsResponseDAO extends GenericDaoHibernate<KaizalaEventsResponse, Long> implements IKaizalaEventsResponseDAO {

	public KaizalaEventsResponseDAO(){
		super(KaizalaEventsResponse.class);
	}
}
