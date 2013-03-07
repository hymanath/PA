package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.model.VoterCastInfo;

public class VoterCastInfoDAO extends GenericDaoHibernate<VoterCastInfo,Long> implements IVoterCastInfoDAO{

	public VoterCastInfoDAO()
	{
		super(VoterCastInfo.class);
	}
	
	
	public Integer deleteVotersCastInfoByReportLevelValue(Long reportLevelValue, Long publicationDateId)
	{
		Query query = getSession().createQuery("delete from VoterCastInfo model where model.constituency.constituencyId=:reportLevelValue and model.publicationDateId = :publicationDateId ");
		
		query.setParameter("reportLevelValue", reportLevelValue);
		query.setParameter("publicationDateId", publicationDateId);
		return query.executeUpdate();	
	}
}
