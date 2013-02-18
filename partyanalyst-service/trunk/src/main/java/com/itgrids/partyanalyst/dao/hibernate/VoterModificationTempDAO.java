package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVoterModificationTempDAO;
import com.itgrids.partyanalyst.model.VoterModificationTemp;

public class VoterModificationTempDAO extends GenericDaoHibernate<VoterModificationTemp,Long> implements IVoterModificationTempDAO{
	
	public VoterModificationTempDAO()
	{
		super(VoterModificationTemp.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterIDAndStatusFromVoterModificationTempByConstituencyId(Long constituencyId)
	{
		return getHibernateTemplate().find("select model.voterId,model.status from VoterModificationTemp model where model.constituencyId = ?",constituencyId);
	}

}
