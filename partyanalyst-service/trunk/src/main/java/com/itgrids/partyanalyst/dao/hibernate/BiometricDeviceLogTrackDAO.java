package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBiometricDeviceLogTrackDAO;
import com.itgrids.partyanalyst.model.BiometricDeviceLogTrack;

public class BiometricDeviceLogTrackDAO extends GenericDaoHibernate<BiometricDeviceLogTrack,Long> implements IBiometricDeviceLogTrackDAO{

	public BiometricDeviceLogTrackDAO()
	{
		super(BiometricDeviceLogTrack.class);
	}
}
