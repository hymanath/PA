package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentCommentDAO;
import com.itgrids.partyanalyst.model.AppointmentComment;


  public class AppointmentCommentDAO extends GenericDaoHibernate<AppointmentComment,Long> implements IAppointmentCommentDAO {
	
	public AppointmentCommentDAO() {
		super(AppointmentComment.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAppointmentCommentsForViewHistory(List<Long> appointmentIds)
	{
		
		Query query = getSession().createQuery(" " +
				"select model.appointment.appointmentId, model.appointment.reason, model.appointment.appointmentUniqueId," +
	    		          " model.appointmentStatusId, model.appointmentStatus.status, model.comment, model.insertedTime,model.insertedUser.firstName, " +
	    		          " model.insertedUser.lastName "+
			        " from AppointmentComment model " +
			        " where model.appointment.isDeleted='N' and model.appointment.appointmentId in(:appointmentIds) order by model.insertedTime desc");
	  
	    query.setParameterList("appointmentIds", appointmentIds);
	    return query.list();
	}

}
