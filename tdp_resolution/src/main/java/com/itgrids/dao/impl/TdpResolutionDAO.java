package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ITdpResolutionDAO;
import com.itgrids.model.TdpResolution;

@Repository
public class TdpResolutionDAO extends GenericDaoHibernate<TdpResolution,Long> implements ITdpResolutionDAO{

	public TdpResolutionDAO() {
		super(TdpResolution.class);
	}

}
