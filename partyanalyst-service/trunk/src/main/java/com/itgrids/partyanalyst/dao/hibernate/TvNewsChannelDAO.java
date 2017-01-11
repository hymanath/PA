package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityDocumentDAO;
import com.itgrids.partyanalyst.dao.ITvNewsChannelDAO;
import com.itgrids.partyanalyst.model.ActivityDocument;
import com.itgrids.partyanalyst.model.TvNewsChannel;

public class TvNewsChannelDAO extends GenericDaoHibernate<TvNewsChannel, Long> implements ITvNewsChannelDAO{

	public TvNewsChannelDAO() {
		super(TvNewsChannel.class);
		
	}

}
