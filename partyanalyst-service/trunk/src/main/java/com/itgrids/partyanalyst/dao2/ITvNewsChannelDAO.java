package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityDocument;
import com.itgrids.partyanalyst.model.TvNewsChannel;

public interface ITvNewsChannelDAO extends GenericDao<TvNewsChannel, Long>{
	public List<Object[]> getChannelList();
}
