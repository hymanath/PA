package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.model.TdpCommittee;

public class TdpCommitteeDAO extends GenericDaoHibernate<TdpCommittee, Long>  implements ITdpCommitteeDAO {

	public TdpCommitteeDAO() {
		super(TdpCommittee.class);
	}

	public List<Object[]> getAllAffiliatedCommittiesInALocation(Long levelId,Long levelValue){
		Query query = getSession().createQuery("select model.tdpCommitteeId,model.tdpBasicCommittee.name from TdpCommittee model where " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId and model.tdpCommitteeLevelValue =:levelValue and " +
				" model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId = 2");
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		return query.list();
	}
	
	public List<Long> getMainCommittiesInALocation(Long levelId,Long levelValue){
		Query query = getSession().createQuery("select model.tdpCommitteeId from TdpCommittee model where " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId and model.tdpCommitteeLevelValue =:levelValue and " +
				" model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId = 1");
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		return query.list();
	}
	
	public List<Long> getTdpCommittee(Long tdpBasicCommitteeId,Long tdpCommitteeLevelId,Long tdpCommitteeLevelValue){
		Query query = getSession().createQuery("select model.tdpCommitteeId from TdpCommittee model where " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId =:tdpCommitteeLevelId and  " +
				" model.tdpCommitteeLevelValue =:tdpCommitteeLevelValue and model.tdpBasicCommittee.tdpBasicCommitteeId = :tdpBasicCommitteeId");
		query.setParameter("tdpBasicCommitteeId", tdpBasicCommitteeId);
		query.setParameter("tdpCommitteeLevelId", tdpCommitteeLevelValue);
		query.setParameter("tdpCommitteeLevelValue", tdpCommitteeLevelValue);
		return query.list();
	}
}
