package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCommitteeElectrolsDAO;
import com.itgrids.partyanalyst.model.TdpCommitteeElectrols;

public class TdpCommitteeElectrolsDAO extends GenericDaoHibernate<TdpCommitteeElectrols, Long>  implements ITdpCommitteeElectrolsDAO {
	public TdpCommitteeElectrolsDAO() {
		super(TdpCommitteeElectrols.class);
	}
	public List<Object[]> getTdpCommitteeElectrolsForTdpCadreIdList(List<Long> tdpCadreIdsList)
	{
		String queryStr = " select distinct model.tdpCadre.tdpCadreId ,model.tdpCommittee.tdpBasicCommittee.name," +
				" '', model.tdpCommittee.tdpCommitteeLevelId, " +
				" model.tdpCommittee.tdpCommitteeLevelValue " +
				" from TdpCommitteeElectrols model where model.tdpCadre.tdpCadreId in (:tdpCadreIdsList)  and model.isDeleted = 'N'";
		Query query = getSession().createQuery(queryStr);
		query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
		return query.list();
	}
}
