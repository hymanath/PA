package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDashboardCommentDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreHealthReportDAO;
import com.itgrids.partyanalyst.model.DashboardComment;
import com.itgrids.partyanalyst.model.TdpCadreHealthReport;

public class TdpCadreHealthReportDAO extends GenericDaoHibernate<TdpCadreHealthReport, Long> implements ITdpCadreHealthReportDAO{

	public TdpCadreHealthReportDAO() {
		super(TdpCadreHealthReport.class);
		
	}

}
