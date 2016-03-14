package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentLableDAO;
import com.itgrids.partyanalyst.model.AppointmentLable;

public class AppointmentLableDAO extends GenericDaoHibernate<AppointmentLable, Long> implements IAppointmentLableDAO {

	public AppointmentLableDAO() {
		super(AppointmentLable.class);
	}

	@Override
	public List<Object[]> getLabelDtslByDate(Date date) {
		StringBuilder builder=new StringBuilder();
		builder.append(" select model.lableName,model.date  from AppointmentLable model where model.isDeleted='N' ");
		if(date!=null){
		  builder.append(" and model.date=:date");	
		}
		Query query=getSession().createQuery(builder.toString());
		if(date!=null){
		  query.setParameter("date",date);	
		}
		return query.list();
	}
}
