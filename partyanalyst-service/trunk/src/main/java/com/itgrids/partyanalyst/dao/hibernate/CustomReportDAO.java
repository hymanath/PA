package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICustomReportDAO;
import com.itgrids.partyanalyst.model.CustomReport;

public class CustomReportDAO extends GenericDaoHibernate<CustomReport, Long> implements ICustomReportDAO {

	public CustomReportDAO() {
		super(CustomReport.class);
	}
}
