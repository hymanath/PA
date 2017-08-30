package com.itgrids.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.service.IPRExpenditureService;
@Service
@Transactional
public class PRExpenditureService implements IPRExpenditureService {
	private static final Logger LOG = Logger.getLogger(PRExpenditureService.class);
}
