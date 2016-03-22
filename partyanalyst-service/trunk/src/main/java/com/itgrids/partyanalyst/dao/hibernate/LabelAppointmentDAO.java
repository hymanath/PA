package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ILabelAppointmentDAO;
import com.itgrids.partyanalyst.model.LabelAppointment;

public class LabelAppointmentDAO extends GenericDaoHibernate<LabelAppointment, Long> implements	ILabelAppointmentDAO {

	public LabelAppointmentDAO(){
		super(LabelAppointment.class);
	}

	public List<Object[]> getLableDetailsWithStatusWiseCounts(Date labelDate,Long userId){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select model.appointmentLabel.appointmentLabelId,model.appointmentLabel.labelName," +
				" model.appointmentLabel.appointmentLabelStatus.appointmentLabelStatusId, " +
				" model.appointmentLabel.appointmentLabelStatus.status, " +
				" model.appointment.appointmentStatusId,model.appointment.appointmentStatus.status,count(model.appointment.appointmentStatusId) " +
				" from LabelAppointment model " +
				" where model.appointmentLabel.isDeleted='N' and model.appointment.isDeleted='N' " );
		if(labelDate != null)
				sb.append("and date(model.appointmentLabel.updatedTime)=:labelDate ");
		sb.append(" and model.appointmentLabel.insertedBy=:userId ");
		sb.append(" group by model.appointment.appointmentStatusId,model.appointmentLabel.appointmentLabelId ");
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("userId", userId);
		
		if(labelDate != null)
			query.setParameter("labelDate", labelDate);
		
		return query.list();
	}
}
