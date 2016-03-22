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
	public List<Object[]> getLabelDtslByDate(Date date,Long appntmentUsrId) {
		StringBuilder builder=new StringBuilder();
		builder.append(" select model.appointmentLableId, model.lableName,model.date  from AppointmentLable model where model.isDeleted='N' ");
		if(date!=null){
		  builder.append(" and model.date=:date ");	
		}
		if(appntmentUsrId!=null && appntmentUsrId>0l){
		  builder.append(" and model.updatedBy=:updatedBy");	
		}
		Query query=getSession().createQuery(builder.toString());
		if(date!=null){
		  query.setParameter("date",date);	
		}
		if(appntmentUsrId!=null && appntmentUsrId>0l){
			  query.setParameter("updatedBy",appntmentUsrId);	
			}
		return query.list();
	}
	@Override
	public Integer deleteAppointmentLabel(Long appointmentLabelId,String remarks) {
		Query query=getSession().createQuery("update AppointmentLable model set model.isDeleted='Y',model.deletedRemarks=:remarks where model.appointmentLableId=:appointmentLableId");
		query.setParameter("remarks", remarks);  
		query.setParameter("appointmentLableId", appointmentLabelId);  
	    return  query.executeUpdate();
	 }
}
