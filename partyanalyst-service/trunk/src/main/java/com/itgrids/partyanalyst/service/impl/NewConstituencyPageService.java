package com.itgrids.partyanalyst.service.impl;

import org.apache.log4j.Logger;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.service.INewConstituencyPageService;

public class NewConstituencyPageService implements INewConstituencyPageService{

	private static final Logger LOG = Logger.getLogger(NewConstituencyPageService.class);
	private TransactionTemplate transactionTemplate = null;
}
