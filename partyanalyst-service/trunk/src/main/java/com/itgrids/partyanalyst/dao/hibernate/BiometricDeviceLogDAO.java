package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBiometricDeviceLogDAO;
import com.itgrids.partyanalyst.model.BiometricDeviceLog;

public class BiometricDeviceLogDAO extends GenericDaoHibernate<BiometricDeviceLog,Long> implements IBiometricDeviceLogDAO{

	public BiometricDeviceLogDAO()
	{
		super(BiometricDeviceLog.class);
	}
	
	public Integer getMaxLogIdForAMonth(Integer year,Integer month)
	{
		Query query = getSession().createSQLQuery(" SELECT MAX(device_log_id) FROM biometric_device_log " +
				" WHERE YEAR(log_time) = :year AND MONTH(log_time) = :month ");
		query.setParameter("year",year);
		query.setParameter("month",month);
		return (Integer) query.uniqueResult();
	}
}
