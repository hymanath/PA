package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ProblemBackup;

public interface IProblemBackupDAO extends GenericDao<ProblemBackup, Long>{
	
	public List<String> getProblemReferenceNo(String refNo);

}
