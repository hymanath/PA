package com.itgrids.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IErrorLogDAO;
import com.itgrids.dto.ErrorLogVO;
import com.itgrids.model.ErrorLog;
import com.itgrids.service.ILoggerService;
import com.itgrids.utils.DateUtilService;

@Service
@Transactional
public class LoggerService implements ILoggerService{

	private static Logger LOG = Logger.getLogger(LoggerService.class);
	
	@Autowired
	private IErrorLogDAO errorLogDAO;
	
	@Autowired
	private DateUtilService dateUtilService;
	
	public void saveErrorLog(ErrorLogVO errorLogVO)
	{
		try{
			ErrorLog errorLog = new ErrorLog();
			errorLog.setInputUrl(errorLogVO.getInputUrl());
			errorLog.setInputJson(errorLogVO.getInputJson());
			errorLog.setStatusCode(errorLogVO.getStatusCode());
			errorLog.setResponse(errorLog.getResponse());
			errorLog.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			errorLog.setExceptionMsg(errorLogVO.getExceptionMsg());
			errorLog.setExceptionStack(errorLogVO.getExceptionStack());
			
			errorLogDAO.save(errorLog);
			
		}catch(Exception e)
		{
			LOG.error(e);
		}
	}
}
