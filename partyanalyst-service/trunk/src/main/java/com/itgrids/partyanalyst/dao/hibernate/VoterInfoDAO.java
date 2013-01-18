package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.model.VoterInfo;

public class VoterInfoDAO extends GenericDaoHibernate<VoterInfo, Long> implements IVoterInfoDAO{

	public VoterInfoDAO()
	{
		super(VoterInfo.class);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<VoterInfo> getVotersCount(Long reportLevelId, Long reportLevelValue, Long publicationDateId)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" select model from VoterInfo model where ");
		stringBuilder.append(" model.voterReportLevel.voterReportLevelId =:reportLevelId and model.reportLevelValue =:reportLevelValue ");
		stringBuilder.append(" and model.publicationDate.publicationDateId =:publicationDateId ");
		Query queryObj = getSession().createQuery(stringBuilder.toString());
		
		queryObj.setParameter("reportLevelId", reportLevelId);
		queryObj.setParameter("reportLevelValue", reportLevelValue);
		queryObj.setParameter("publicationDateId", publicationDateId);
		return queryObj.list();
    }
}
