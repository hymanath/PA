package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IAppointmentCommentDAO;
import com.itgrids.partyanalyst.model.AppointmentComment;


  public class AppointmentCommentDAO extends GenericDaoHibernate<AppointmentComment,Long> implements IAppointmentCommentDAO {
	
	public AppointmentCommentDAO() {
		super(AppointmentComment.class);
	}

}
