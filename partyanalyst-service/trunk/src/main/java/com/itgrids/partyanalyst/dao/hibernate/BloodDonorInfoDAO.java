package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBloodDonorInfoDAO;
import com.itgrids.partyanalyst.model.BloodDonorInfo;

public class BloodDonorInfoDAO extends GenericDaoHibernate<BloodDonorInfo, Long> implements IBloodDonorInfoDAO {
 
	public BloodDonorInfoDAO(){
		super(BloodDonorInfo.class);
	}
}
