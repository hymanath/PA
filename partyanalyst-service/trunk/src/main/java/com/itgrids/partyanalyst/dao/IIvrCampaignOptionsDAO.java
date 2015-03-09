package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.IvrCampaignOptions;

public interface IIvrCampaignOptionsDAO extends GenericDao<IvrCampaignOptions, Long>{
	public List<Object[]> getAllIVROptions(Long campaignId);
}
