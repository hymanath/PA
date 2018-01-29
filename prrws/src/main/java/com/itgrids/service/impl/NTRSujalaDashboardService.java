package com.itgrids.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.service.INTRSujalaDashboardService;

@Service
@Transactional
public class NTRSujalaDashboardService implements INTRSujalaDashboardService{

	private static final Logger LOG = Logger.getLogger(NTRSujalaDashboardService.class);
}
