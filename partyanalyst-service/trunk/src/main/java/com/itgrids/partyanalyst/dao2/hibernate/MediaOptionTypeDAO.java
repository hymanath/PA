package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMediaOptionTypeDAO;
import com.itgrids.partyanalyst.model.MediaOptionType;

public class MediaOptionTypeDAO extends GenericDaoHibernate<MediaOptionType,Long> implements IMediaOptionTypeDAO{

	public MediaOptionTypeDAO()
	{
		super(MediaOptionType.class);
	}

}
