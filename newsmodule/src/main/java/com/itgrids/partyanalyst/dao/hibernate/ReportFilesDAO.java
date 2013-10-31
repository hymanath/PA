package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IReportFilesDAO;
import com.itgrids.partyanalyst.model.ReportFiles;

public class ReportFilesDAO extends GenericDaoHibernate<ReportFiles, Long> implements IReportFilesDAO{

	public ReportFilesDAO() {
		super(ReportFiles.class);
	
	}

}
