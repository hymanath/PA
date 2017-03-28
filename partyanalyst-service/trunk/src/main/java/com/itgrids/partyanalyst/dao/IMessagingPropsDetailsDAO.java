package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MessagingPropsDetails;

public interface IMessagingPropsDetailsDAO extends GenericDao<MessagingPropsDetails, Long>{
	
	public List<MessagingPropsDetails> getMessagingPropsDetails(String appName);
}
