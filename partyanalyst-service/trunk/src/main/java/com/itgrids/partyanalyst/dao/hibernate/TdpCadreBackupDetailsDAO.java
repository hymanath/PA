package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreBackupDetailsDAO;
import com.itgrids.partyanalyst.model.TdpCadreBackupDetails;

public class TdpCadreBackupDetailsDAO extends GenericDaoHibernate<TdpCadreBackupDetails, Long>implements ITdpCadreBackupDetailsDAO{

	public TdpCadreBackupDetailsDAO() {
		super(TdpCadreBackupDetails.class);
	}
}
