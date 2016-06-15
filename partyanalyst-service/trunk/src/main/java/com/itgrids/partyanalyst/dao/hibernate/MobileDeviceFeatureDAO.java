package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMobileDeviceFeatureDAO;
import com.itgrids.partyanalyst.model.MobileDeviceFeature;

public class MobileDeviceFeatureDAO extends GenericDaoHibernate<MobileDeviceFeature, Long> implements IMobileDeviceFeatureDAO{

	public MobileDeviceFeatureDAO() {
		super(MobileDeviceFeature.class);
		
	}
}
