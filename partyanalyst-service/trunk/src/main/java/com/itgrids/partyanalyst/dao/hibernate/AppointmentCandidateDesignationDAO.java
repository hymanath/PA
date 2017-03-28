package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentCandidateDesignationDAO;
import com.itgrids.partyanalyst.model.AppointmentCandidateDesignation;

public class AppointmentCandidateDesignationDAO extends GenericDaoHibernate<AppointmentCandidateDesignation, Long> implements IAppointmentCandidateDesignationDAO {

	public AppointmentCandidateDesignationDAO( ) {
		super(AppointmentCandidateDesignation.class);
	}
	public List<Object[]> getAppCandidateDesigList(){
		Query query=getSession().createQuery("select model.appointmentCandidateDesignationId, model.designation,model.appointmentCandidateTypeId from AppointmentCandidateDesignation model"
				+ " order by model.appointmentCandidateTypeId ");
		return query.list();
	}
	public List<Object[]> getAppCandidateDesigListByType(Long typeId){
		Query query=getSession().createQuery("select model.appointmentCandidateDesignationId, model.designation,model.appointmentCandidateTypeId from AppointmentCandidateDesignation model"
				+ " where model.appointmentCandidateTypeId = :typeId"
				+ " order by model.appointmentCandidateTypeId ");
		query.setParameter("typeId", typeId);
		return query.list();
	}
   public String checkDesignationExistOrNot(Long appointmentCandidateTypeId,String designation){
	   Query query=getSession().createQuery("select model.designation from AppointmentCandidateDesignation model where model.appointmentCandidateTypeId=:appointmentCandidateTypeId and model.designation=:designation");
	        query.setParameter("appointmentCandidateTypeId", appointmentCandidateTypeId);
	        query.setParameter("designation", designation);
	        return (String) query.uniqueResult();
   }
}
