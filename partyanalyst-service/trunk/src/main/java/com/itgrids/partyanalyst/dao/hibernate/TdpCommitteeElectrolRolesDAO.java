package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCommitteeElectrolRolesDAO;
import com.itgrids.partyanalyst.model.TdpCommitteeElectrolRoles;

public class TdpCommitteeElectrolRolesDAO extends GenericDaoHibernate<TdpCommitteeElectrolRoles, Long>  implements
		ITdpCommitteeElectrolRolesDAO {
	public TdpCommitteeElectrolRolesDAO() {
		super(TdpCommitteeElectrolRoles.class);
	}
	
	public List<Object[]> getAllRolesForACadre(Long cadreId){
		//0 id,1 name,2 startDate,3endDate
		Query query = getSession().createQuery("select model.tdpCommitteeDesignation.tdpCommitteeDesignationId,model.tdpCommitteeDesignation.designation,model.startDate" +
				",model.endDate from TdpCommitteeElectrolRoles model where model.isDeleted ='N' and model.tdpCommitteeElectrols.tdpCadre.tdpCadreId =:cadreId ");
		query.setParameter("cadreId", cadreId);
		
		return query.list();
	}
	
	
	
}
