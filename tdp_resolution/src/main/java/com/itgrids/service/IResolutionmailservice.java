package com.itgrids.service;

import org.appfuse.service.GenericManager;

import com.itgrids.model.TdpEmailmodel;

public interface IResolutionmailservice extends GenericManager<TdpEmailmodel, Integer> {

	String sentmail(String json);

}
