package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterPartyInfoDAO;
import com.itgrids.partyanalyst.model.VoterPartyInfo;

public class VoterPartyInfoDAO extends GenericDaoHibernate<VoterPartyInfo, Long>implements IVoterPartyInfoDAO{

	public VoterPartyInfoDAO() {
		super(VoterPartyInfo.class);
		
	}
	public Integer deleteVotersPartyInfoByConstituencyId(Long reportLevelId, Long constituencyId, Long publicationDateId)
	{
		Query query = getSession().createQuery("delete from VoterPartyInfo model where model.reportLevelValue=:constituencyId and model.reportLevelId = :reportLevelId " +
				" and model.publicationDateId = :publicationDateId ");
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		return query.executeUpdate();	
	}
	
	
}
