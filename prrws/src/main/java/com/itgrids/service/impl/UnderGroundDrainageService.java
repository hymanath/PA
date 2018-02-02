package com.itgrids.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.service.IUnderGroundDrainageService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.DateUtilService;

@Service
@Transactional
public class UnderGroundDrainageService implements IUnderGroundDrainageService{
	private static final Logger LOG = Logger.getLogger(UnderGroundDrainageService.class);
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	@Autowired
	private DateUtilService dateUtilService;
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	
	
}
