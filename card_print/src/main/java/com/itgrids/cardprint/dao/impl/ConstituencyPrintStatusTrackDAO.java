package com.itgrids.cardprint.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.cardprint.dao.IConstituencyPrintStatusTrackDAO;
import com.itgrids.cardprint.model.ConstituencyPrintStatusTrack;

public class ConstituencyPrintStatusTrackDAO extends GenericDaoHibernate<ConstituencyPrintStatusTrack, Long> implements IConstituencyPrintStatusTrackDAO {

	public ConstituencyPrintStatusTrackDAO(){
		super(ConstituencyPrintStatusTrack.class);
	}

}
