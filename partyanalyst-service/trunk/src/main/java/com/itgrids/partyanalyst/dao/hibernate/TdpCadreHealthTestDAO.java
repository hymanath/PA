package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreHealthTestDAO;
import com.itgrids.partyanalyst.model.TdpCadreHealthTest;

public class TdpCadreHealthTestDAO extends GenericDaoHibernate<TdpCadreHealthTest,Long> implements ITdpCadreHealthTestDAO {
	
	public TdpCadreHealthTestDAO() {
		super(TdpCadreHealthTest.class);
	}	

	public List<Object[]> getCadreHealthTestsForCadre(Long tdpCadreId){
		Query query = getSession().createQuery("select distinct model.healthTestId," +
											" model.reportPath," +
											" model.testDate," +
											" model.tdpCadreId," +
											" model.tdpCadre.memberShipNo " +
											" from TdpCadreHealthTest model" +
											" where model.tdpCadre.tdpCadreId = :tdpCadreId" +
											" and model.isDeleted = 'N'");
		query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
	}
}
