package com.itgrids.cardprint.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.cardprint.model.PrintStatus;

public interface IPrintStatusDAO extends GenericDao<PrintStatus, Long> {
	
	public List<Object[]> getAllPrintStatus();
}
