package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAcceptanceStatusDAO;
import com.itgrids.partyanalyst.model.AcceptanceStatus;
import com.itgrids.partyanalyst.model.AppointmentCandidate;

public class AcceptanceStatusDAO extends GenericDaoHibernate<AcceptanceStatus, Long> implements IAcceptanceStatusDAO {

	public AcceptanceStatusDAO() {
		super(AcceptanceStatus.class);
	}
}
