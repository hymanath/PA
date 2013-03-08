package com.itgrids.partyanalyst.dao.hibernate;



import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationAgeInfoDAO;
import com.itgrids.partyanalyst.model.VoterModificationAgeInfo;

public class VoterModificationAgeInfoDAO extends GenericDaoHibernate<VoterModificationAgeInfo,Long> implements IVoterModificationAgeInfoDAO
{

	public VoterModificationAgeInfoDAO() {
		super(VoterModificationAgeInfo.class);
		
	}

	
	public Integer deletevotermodificationInfoByConstituencyId(Long voterModificationInfoId)
	{
		Query query =getSession().createQuery("delete from VoterModificationAgeInfo model where model.voterModificationInfo.voterModificationInfoId=:voterModificationInfoId ");
		query.setParameter("voterModificationInfoId", voterModificationInfoId);
		return query.executeUpdate();
	}
	
	public Integer deleteVoterModicationAgeInfoById(List<Long> voterModificationInfoIds)
	{
		Query query =getSession().createQuery("delete from VoterModificationAgeInfo model where model.voterModificationInfo.voterModificationInfoId in (:voterModificationInfoId)");
		query.setParameterList("voterModificationInfoId", voterModificationInfoIds);
		
		return query.executeUpdate();
	}
}
