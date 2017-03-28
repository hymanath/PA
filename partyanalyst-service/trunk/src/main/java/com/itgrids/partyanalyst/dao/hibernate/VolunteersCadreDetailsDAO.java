package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVolunteersCadreDetailsDAO;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.VolunteersCadreDetails;

public class VolunteersCadreDetailsDAO extends GenericDaoHibernate<VolunteersCadreDetails, Long> implements IVolunteersCadreDetailsDAO{
	public VolunteersCadreDetailsDAO(){
		super(VolunteersCadreDetails.class);
	}
	
	public Long getVolunteerCountDetails(Long tdpCadreId){
		
		Query query = getSession().createQuery(" select count(model.volunteersCadreDetailsId) " +
				" from VolunteersCadreDetails model where model.tdpCadreId = :tdpCadreId");
             query.setParameter("tdpCadreId", tdpCadreId);
         return (Long) query.uniqueResult();
	}
	
public List<Object[]> getVolunteerDetailsInfo(Long tdpCadreId){
		
		Query query = getSession().createQuery(" select model.volunteersCadreDetailsId, " +
				" model.volunteerDesgination.volunteerDesignation," +
				" model.workArea," +
				" model.workDate," +
				" model.event.name " +
				" from VolunteersCadreDetails model where model.tdpCadreId = :tdpCadreId and model.isDelete='N' ");
             query.setParameter("tdpCadreId", tdpCadreId);
         return  query.list();
	}
}
