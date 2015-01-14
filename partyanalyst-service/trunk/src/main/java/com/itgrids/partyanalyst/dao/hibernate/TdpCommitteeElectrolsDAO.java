package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCommitteeElectrolsDAO;
import com.itgrids.partyanalyst.model.TdpCommitteeElectrols;

public class TdpCommitteeElectrolsDAO extends GenericDaoHibernate<TdpCommitteeElectrols, Long>  implements ITdpCommitteeElectrolsDAO {
	public TdpCommitteeElectrolsDAO() {
		super(TdpCommitteeElectrols.class);
	}

}
