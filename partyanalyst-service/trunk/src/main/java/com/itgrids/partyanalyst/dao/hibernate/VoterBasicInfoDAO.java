package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterBasicInfoDAO;
import com.itgrids.partyanalyst.model.VoterBasicInfo;

public class VoterBasicInfoDAO extends GenericDaoHibernate<VoterBasicInfo,Long> implements IVoterBasicInfoDAO{
	
	public VoterBasicInfoDAO(){
		super(VoterBasicInfo.class);
	}

	public Integer deleteVoterBasicInfoByConstituencyId(Long constituencyId)
	{
	
	Query query = getSession().createQuery("delete from VoterBasicInfo model where model.constituency.constituencyId =:constituencyId");
	query.setParameter("constituencyId", constituencyId);
	return query.executeUpdate();
	
	}
	
	@SuppressWarnings("unchecked")
	public List<VoterBasicInfo> getVoterBasicInfoList(Long constituencyId)
	{
		Query query = getSession().createQuery(" from VoterBasicInfo model where model.constituency.constituencyId =:constituencyId ");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
}
