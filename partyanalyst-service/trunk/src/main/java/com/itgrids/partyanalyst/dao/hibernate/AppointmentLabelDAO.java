package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentLabelDAO;
import com.itgrids.partyanalyst.model.AppointmentLabel;

public class AppointmentLabelDAO extends GenericDaoHibernate<AppointmentLabel, Long> implements IAppointmentLabelDAO {

	public AppointmentLabelDAO() {
		super(AppointmentLabel.class);
	}

	@Override
	public List<Object[]> getLabelDtslByDate(Date date,Long appntmentUsrId) {
		StringBuilder builder=new StringBuilder();
		builder.append(" select model.appointmentLabelId, model.labelName,model.date  from AppointmentLabel model where model.isDeleted='N' ");
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
		Query query=getSession().createQuery("update AppointmentLabel model set model.isDeleted='Y',model.deletedRemarks=:remarks where model.appointmentLabelId=:appointmentLabelId");
	    query.setParameter("appointmentLabelId", appointmentLabelId);
	    query.setParameter("remarks", remarks);
	 	return  query.executeUpdate();
	 }
}
