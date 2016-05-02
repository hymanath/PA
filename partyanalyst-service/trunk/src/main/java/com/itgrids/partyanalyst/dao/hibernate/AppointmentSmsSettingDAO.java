package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentSmsSettingDAO;
import com.itgrids.partyanalyst.model.AppointmentSmsSetting;

public class AppointmentSmsSettingDAO extends GenericDaoHibernate<AppointmentSmsSetting,Long> implements IAppointmentSmsSettingDAO {
	
	public AppointmentSmsSettingDAO() {
		super(AppointmentSmsSetting.class);
	}
	
	public List<Object[]> getContentTypeAndSmsContent(Long appointmentStatusId)
	{
		StringBuffer str = new StringBuffer();
	    str.append("select model.contentType,model.smsContent " +
	        " from AppointmentSmsSetting model where  model.appointmentStatus.appointmentStatusId = :appointmentStatusId and model.isEnabled = 'Y' ");
	    Query query = getSession().createQuery(str.toString());
	    query.setParameter("appointmentStatusId", appointmentStatusId);
	   return query.list();
	}
	
	public String checkIsValidForSendingSMS(Long appointmentStatusId){
		
		Query query = getSession().createQuery(" select model.isEnabled from AppointmentSmsSetting model where model.appointmentStatus.appointmentStatusId = :appointmentStatusId ");
		
		query.setParameter("appointmentStatusId",appointmentStatusId);
		return (String) query.uniqueResult();
		
	}
	
	public List<Object[]> getSMSEnablingDetailsForAllStatus(){
		
		Query query = getSession().createQuery(" select model.appointmentStatus.appointmentStatusId,model.isEnabled from AppointmentSmsSetting model ");
		return  query.list();
	}
	
}
