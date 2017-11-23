package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IKaizalaInstallationTrackingDAO;
import com.itgrids.partyanalyst.model.KaizalaInstallationTracking;

public class KaizalaInstallationTrackingDAO extends GenericDaoHibernate<KaizalaInstallationTracking, Long>
implements IKaizalaInstallationTrackingDAO{

	public KaizalaInstallationTrackingDAO() {
		super(KaizalaInstallationTracking.class);
	}

}
