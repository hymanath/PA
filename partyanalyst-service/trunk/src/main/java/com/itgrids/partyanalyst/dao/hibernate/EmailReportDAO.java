package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEmailReportDAO;
import com.itgrids.partyanalyst.model.EmailReport;

public class EmailReportDAO extends GenericDaoHibernate<EmailReport, Long> implements IEmailReportDAO {
	public EmailReportDAO(){
		super(EmailReport.class);
	}
}
