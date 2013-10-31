package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INewsReportDAO;
import com.itgrids.partyanalyst.model.NewsReport;

public class NewsReportDAO extends GenericDaoHibernate<NewsReport, Long> implements INewsReportDAO{

	public NewsReportDAO() {
		super(NewsReport.class);
	
	}

}
