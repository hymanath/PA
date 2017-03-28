package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCommitteeLevelDAO;
import com.itgrids.partyanalyst.model.TdpCommitteeLevel;
import com.itgrids.partyanalyst.utils.IConstants;

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
	public List<Object[]> getCommitteeInfoList(){
		Query query = getSession().createQuery(" select model.tdpCommitteeLevelId,model.tdpCommitteeLevel from TdpCommitteeLevel model " +
				" where model.tdpCommitteeLevelId in ("+IConstants.REQUIRED_COMMITTEE_LEVEL_IDS+")  order by model.tdpCommitteeLevel ");
		return query.list();
	}
	
}
