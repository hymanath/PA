package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreReportStatusDAO;
import com.itgrids.partyanalyst.model.TdpCadreReportStatus;

public class TdpCadreReportStatusDAO extends GenericDaoHibernate<TdpCadreReportStatus, Long> implements ITdpCadreReportStatusDAO{

	public TdpCadreReportStatusDAO() {
		super(TdpCadreReportStatus.class);
		// TODO Auto-generated constructor stub
	}

}
