package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.IvrCampaign;


public interface IIvrCampaignDAO extends GenericDao<IvrCampaign, Long>{
	public List<Object[]> getAllCampaigns();
}
