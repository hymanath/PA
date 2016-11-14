package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreCardPrintDAO;
import com.itgrids.partyanalyst.model.TdpCadreCardPrint;

public class TdpCadreCardPrintDAO extends GenericDaoHibernate<TdpCadreCardPrint, Long> implements ITdpCadreCardPrintDAO{

	public TdpCadreCardPrintDAO() {
		super(TdpCadreCardPrint.class);
	}
 }
