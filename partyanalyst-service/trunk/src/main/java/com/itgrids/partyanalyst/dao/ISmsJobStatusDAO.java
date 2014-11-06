package com.itgrids.partyanalyst.dao;

import java.util.Date;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SmsJobStatus;

public interface ISmsJobStatusDAO extends GenericDao<SmsJobStatus, Long>{
	
	//public Integer updateSmsJobCode(Long tdpCadreId,String jobCode);
	public Integer updateSmsSentStatus(String mobile,String jobCode,Date doneTime, String status);
}
