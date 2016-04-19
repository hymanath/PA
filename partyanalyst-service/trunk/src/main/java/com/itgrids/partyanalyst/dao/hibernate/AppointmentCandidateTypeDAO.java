package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentCandidateTypeDAO;
import com.itgrids.partyanalyst.model.AppointmentCandidateType;

public class AppointmentCandidateTypeDAO extends GenericDaoHibernate<AppointmentCandidateType, Long> implements IAppointmentCandidateTypeDAO{

	public AppointmentCandidateTypeDAO() {
		super(AppointmentCandidateType.class);
	}
	
	public List<Object[]> getAllCandidateTypes(){
		Query query = getSession().createQuery("select model.appointmentCandidateTypeId,model.candidateType " +
				" from " +
				" AppointmentCandidateType model " );
		
		return query.list();
	}
	public List<Object[]> getCandidateTypesByIds(List<Long> ids){
		Query query = getSession().createQuery("select model.appointmentCandidateTypeId,model.candidateType " +
				" from AppointmentCandidateType model where model.appointmentCandidateTypeId in (:ids)"); 
				
		query.setParameterList("ids",ids);
		return query.list();
	}
}
