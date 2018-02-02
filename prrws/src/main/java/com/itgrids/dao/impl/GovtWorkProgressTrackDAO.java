package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IGovtWorkProgressTrackDAO;
import com.itgrids.model.GovtWorkProgressTrack;

@Repository
public class GovtWorkProgressTrackDAO extends GenericDaoHibernate<GovtWorkProgressTrack, Long> implements IGovtWorkProgressTrackDAO{

	public GovtWorkProgressTrackDAO() {
		super(GovtWorkProgressTrack.class);
	}

}
