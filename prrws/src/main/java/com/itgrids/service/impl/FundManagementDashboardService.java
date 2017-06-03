package com.itgrids.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IFundSanctionDAO;
import com.itgrids.service.IFundManagementDashboardService;
/*
 * Author : Swadhin K Lenka
 * Date : 03/06/2017
 */
@Service
@Transactional
public class FundManagementDashboardService implements IFundManagementDashboardService {
	private static final Logger LOG = Logger.getLogger(FundManagementDashboardService.class);
	
	@Autowired
	private IFundSanctionDAO fundSanctionDAO;
	
}
