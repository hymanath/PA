package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterModificationInfoDAO;
import com.itgrids.partyanalyst.model.VoterModificationInfo;

public class VoterModificationInfoDAO extends GenericDaoHibernate<VoterModificationInfo, Long> implements IVoterModificationInfoDAO{

	public VoterModificationInfoDAO()
	{
		super(VoterModificationInfo.class);
	}
	
	public Integer  deletevotermodificationInfoByConstituencyId(Long constituencyId,Long publicationId)
	{
		Query query =getSession().createQuery("delete from VoterModificationInfo model where model.constituencyId=:constituencyId and model.publicationDate.publicationDateId=:publicationId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		return query.executeUpdate();
	}
	
	public List<Long> getVoterModificationInfoIds(Long constituencyId,Long publicationId)
	{
		Query query =getSession().createQuery("select model.voterModificationInfoId from  VoterModificationInfo model where model.constituencyId=:constituencyId and model.publicationDate.publicationDateId=:publicationId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		return query.list();
	}
}