package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IConstituencyPrintStatusTrackDAO;
import com.itgrids.partyanalyst.model.ConstituencyPrintStatusTrack;

public class ConstituencyPrintStatusTrackDAO extends GenericDaoHibernate<ConstituencyPrintStatusTrack, Long> implements IConstituencyPrintStatusTrackDAO {

	public ConstituencyPrintStatusTrackDAO(){
		super(ConstituencyPrintStatusTrack.class);
	}

}
