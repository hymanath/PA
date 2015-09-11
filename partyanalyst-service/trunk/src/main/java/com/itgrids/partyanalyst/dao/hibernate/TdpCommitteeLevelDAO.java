package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCommitteeLevelDAO;
import com.itgrids.partyanalyst.model.TdpCommitteeLevel;

public class TdpCommitteeLevelDAO extends GenericDaoHibernate<TdpCommitteeLevel, Long>  implements ITdpCommitteeLevelDAO {
	public TdpCommitteeLevelDAO() {
		super(TdpCommitteeLevel.class);
	}
	
	public List<Object[]> getAllLevels(){
		Query query = getSession().createQuery(" select model.tdpCommitteeLevelId," +
				" model.tdpCommitteeLevel " +
				" from TdpCommitteeLevel model ");
		
		return query.list();
	}
	public List<Object[]> getLevels(){
		Query query = getSession().createQuery(" select model.tdpCommitteeLevelId," +
				" model.tdpCommitteeLevel " +
				" from TdpCommitteeLevel model " +
				" order by model.tdpCommitteeLevel");
		
		return query.list();
	}
	
}
