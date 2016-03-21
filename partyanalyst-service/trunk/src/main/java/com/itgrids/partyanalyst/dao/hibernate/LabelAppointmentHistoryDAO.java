package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ILabelAppointmentHistoryDAO;
import com.itgrids.partyanalyst.model.LabelAppointmentHistory;

public class LabelAppointmentHistoryDAO extends
		GenericDaoHibernate<LabelAppointmentHistory, Long> implements
		ILabelAppointmentHistoryDAO {

	public LabelAppointmentHistoryDAO(){
		super(LabelAppointmentHistory.class);
	}

}
