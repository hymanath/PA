package com.itgrids.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.service.IRUrbanDashboardService;

@Transactional
@Service
public class RUrbanDashboardService implements IRUrbanDashboardService{

	private static final Logger LOG = Logger.getLogger(RUrbanDashboardService.class);
}
