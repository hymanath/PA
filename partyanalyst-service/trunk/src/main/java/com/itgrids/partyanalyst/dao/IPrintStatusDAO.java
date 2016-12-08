package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PrintStatus;

public interface IPrintStatusDAO extends GenericDao<PrintStatus, Long> {
	
	public List<Object[]> getAllPrintStatus();
}
