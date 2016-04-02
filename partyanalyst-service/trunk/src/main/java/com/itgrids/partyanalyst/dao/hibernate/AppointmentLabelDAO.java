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

	/*@Override
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
	}*/
	@Override
	public Integer deleteAppointmentLabel(Long appointmentLabelId,String remarks) {
		Query query=getSession().createQuery("update AppointmentLabel model set model.isDeleted='Y',model.deletedRemarks=:remarks where model.appointmentLabelId=:appointmentLabelId");
	    query.setParameter("appointmentLabelId", appointmentLabelId);
	    query.setParameter("remarks", remarks);
	 	return  query.executeUpdate();
	 }
	
	public List<Object[]> getAllLabels(Date date,Long userID,Long statusId){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.appointmentLabelId,model.labelName,model.appointmentLabelStatusId,model.appointmentLabelStatus.status,model.updatedTime " +
				" from AppointmentLabel model " +
				" where model.appointmentUserId=:userID and model.isDeleted='N' ");
		if(date != null)
			sb.append(" and date(model.updatedTime=:date) ");
		
		if(statusId !=null && statusId>0){
			sb.append(" and model.appointmentLabelStatusId = :statusId");	
		}
		
		
		sb.append(" order by model.updatedTime desc ");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("userID", userID);
		if(date != null)
			query.setParameter("date", date);
		if(statusId !=null && statusId>0){
			query.setParameter("statusId", statusId);
		}
		return query.list();
	}
	public List<Object[]> getAppointmentLabels(Long aptUserId){
		
		StringBuilder str = new StringBuilder();		
			str.append(" select model.appointmentLabelId,model.labelName from AppointmentLabel model where model.appointmentLabelStatusId=1 and model.isDeleted='N' ");
		if(aptUserId !=null && aptUserId>0){
			str.append(" and model.appointmentUserId = :aptUserId ");
		}
		
		Query query = getSession().createQuery(str.toString());		
		
		if(aptUserId !=null && aptUserId>0){
			query.setParameter("aptUserId",aptUserId);
		}
		
		return query.list();
	}
	
	public Integer updateAppointmentsLabelStatus(Long labelId,Long labelstatusId){
		Query query=getSession().createQuery("update AppointmentLabel model set model.appointmentLabelStatusId=:labelstatusId where model.appointmentLabelId=:labelId");
	    query.setParameter("labelId", labelId);
	    query.setParameter("labelstatusId", labelstatusId);
	 	return  query.executeUpdate();
	 }
	
	public Integer updateMemberAppointmentsStatus(Long memberAppntId,Long updateAppntStatusId){
		Query query=getSession().createQuery("update Appointment model set model.appointmentStatusId=:updateAppntStatusId where model.appointmentId=:memberAppntId");
	    query.setParameter("memberAppntId", memberAppntId);
	    query.setParameter("updateAppntStatusId", updateAppntStatusId);
	 	return  query.executeUpdate();
	 }
}
