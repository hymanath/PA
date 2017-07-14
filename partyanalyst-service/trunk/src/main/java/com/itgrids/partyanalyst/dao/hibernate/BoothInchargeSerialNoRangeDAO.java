package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBoothInchargeSerialNoRangeDAO;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerTracking;
import com.itgrids.partyanalyst.model.BoothInchargeSerialNoRange;

public class BoothInchargeSerialNoRangeDAO extends GenericDaoHibernate<BoothInchargeSerialNoRange, Long> implements IBoothInchargeSerialNoRangeDAO {

	public BoothInchargeSerialNoRangeDAO() {
		super(BoothInchargeSerialNoRange.class);
		
	}



}
