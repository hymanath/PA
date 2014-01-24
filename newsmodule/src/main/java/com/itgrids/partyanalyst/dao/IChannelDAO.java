package com.itgrids.partyanalyst.dao;

import java.util.List;
import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Channel;

public interface IChannelDAO extends GenericDao<Channel, Long>{

	public List<Channel> getChannelDetails();
}
