package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPrintRejectReasonDAO;
import com.itgrids.partyanalyst.model.PrintRejectReason;

public class PrintRejectReasonDAO extends GenericDaoHibernate<PrintRejectReason, Long> implements IPrintRejectReasonDAO{

	public PrintRejectReasonDAO() {
		super(PrintRejectReason.class);
		
	}
 }
