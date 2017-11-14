package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.BiometricEmployeeDetails;

public interface IBiometricEmployeeDetailsDAO extends GenericDao<BiometricEmployeeDetails, Long> {

	public Long getTotalEmployeeCount();
	public List<Object[]> getEmployeeDetails();
}
