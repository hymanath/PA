package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMediaOptionsDAO;
import com.itgrids.partyanalyst.model.MediaOptions;

public class MediaOptionsDAO extends GenericDaoHibernate<MediaOptions,Long> implements IMediaOptionsDAO{

	public MediaOptionsDAO()
	{
		super(MediaOptions.class);
	}

}
