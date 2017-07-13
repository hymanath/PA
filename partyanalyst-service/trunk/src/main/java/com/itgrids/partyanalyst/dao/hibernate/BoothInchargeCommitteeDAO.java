package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBoothInchargeCommitteeDAO;
import com.itgrids.partyanalyst.model.BoothInchargeCommittee;

public class BoothInchargeCommitteeDAO extends GenericDaoHibernate<BoothInchargeCommittee,Long> implements IBoothInchargeCommitteeDAO {

	public BoothInchargeCommitteeDAO() {
		super(BoothInchargeCommittee.class);

	}

}
