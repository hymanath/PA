package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterCastBasicInfoDAO;
import com.itgrids.partyanalyst.model.VoterCastBasicInfo;

public class VoterCastBasicInfoDAO extends GenericDaoHibernate<VoterCastBasicInfo, Long> implements IVoterCastBasicInfoDAO{

	public VoterCastBasicInfoDAO() {
		super(VoterCastBasicInfo.class);
	}
	public Integer deleteVotersCastInfoByReportLevelValue(Long reportLevelId, Long reportLevelValue, Long publicationDateId)
	{
		Query query = getSession().createQuery("delete from VoterCastBasicInfo model where model.constituency.constituencyId=:reportLevelValue and model.voterReportLevel.voterReportLevelId = :reportLevelId " +
				" and model.publicationDateId = :publicationDateId ");
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameter("reportLevelValue", reportLevelValue);
		query.setParameter("publicationDateId", publicationDateId);
		return query.executeUpdate();	
	}
	
	
}
