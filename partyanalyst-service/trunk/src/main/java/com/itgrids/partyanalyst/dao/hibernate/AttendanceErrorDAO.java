package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IAttendanceErrorDAO;
import com.itgrids.partyanalyst.model.AttendanceError;

public class AttendanceErrorDAO extends GenericDaoHibernate<AttendanceError,Long> implements IAttendanceErrorDAO{

	public AttendanceErrorDAO()
	{
		super(AttendanceError.class);
	}
}
