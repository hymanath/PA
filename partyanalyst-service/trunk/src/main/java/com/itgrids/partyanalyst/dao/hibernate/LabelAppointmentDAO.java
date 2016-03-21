package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ILabelAppointmentDAO;
import com.itgrids.partyanalyst.model.LabelAppointment;

public class LabelAppointmentDAO extends
		GenericDaoHibernate<LabelAppointment, Long> implements
		ILabelAppointmentDAO {

	public LabelAppointmentDAO(){
		super(LabelAppointment.class);
	}

}
