package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IVoterBasicInfoDAO;
import com.itgrids.partyanalyst.model.VoterBasicInfo;

public class VoterBasicInfoDAO extends GenericDaoHibernate<VoterBasicInfo,Long> implements IVoterBasicInfoDAO{
	
	public VoterBasicInfoDAO(){
		super(VoterBasicInfo.class);
	}

}
