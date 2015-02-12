package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ErrorStatusSms;


public interface IErrorStatusSmsDAO extends GenericDao<ErrorStatusSms, Long>{
	public List<ErrorStatusSms> getErrorSmsInfo();

}
