package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAttendanceTabUserDAO;
import com.itgrids.partyanalyst.model.AttendanceTabUser;

public class AttendanceTabUserDAO extends GenericDaoHibernate<AttendanceTabUser,Long> implements IAttendanceTabUserDAO{

	public AttendanceTabUserDAO()
	{
		super(AttendanceTabUser.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAttendanceTabUserByUsernameAndPassword(String username,String password)
	{
		Query query = getSession().createQuery("select model.attendanceTabUserId,model.firstname,model.lastname,model.username,model.password " +
				" from AttendanceTabUser model where model.username = :username and model.password = :password and model.isEnabled = 'Y'");
		query.setParameter("username",username);
		query.setParameter("password",password);
		return query.list();
		
	}

	public List<Object[]> getAttendanceTabUserDetailes(){
		StringBuilder sb=new StringBuilder();
		sb.append("select model.attendanceTabUserId,model.firstname,model.lastname,model.mobile,model.username " +
				"from AttendanceTabUser model where model.isEnabled='Y' " );
		Query query = getSession().createQuery(sb.toString());
		
		return query.list();
	}
}
