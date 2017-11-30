package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreHealthDetailsDAO;
import com.itgrids.partyanalyst.model.TdpCadreHealthDetails;

public class TdpCadreHealthDetailsDAO extends GenericDaoHibernate<TdpCadreHealthDetails, Long> implements ITdpCadreHealthDetailsDAO{

	public TdpCadreHealthDetailsDAO() {
		super(TdpCadreHealthDetails.class);
	}

	public List<Object[]> getCadreHealthDetailsForCadre(Long cadreId){
		Query query = getSession().createQuery("select distinct model.tdpCadreId," +
											" model.age,model.gender," +
											" model.height,model.weight," +
											" model.spot," +
											" model.systolicBp,model.diastolicBp," +
											" model.heartPulse,model.spiro," +
											" model.testDate," +
											" model.tdpCadre.memberShipNo " +
											" from TdpCadreHealthDetails model" +
											" where model.tdpCadre.tdpCadreId = :cadreId" +
											" and model.isDeleted = 'N'");
		query.setParameter("cadreId", cadreId);
		return query.list();
	}
}
