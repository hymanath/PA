package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IBiometricEmployeeDetailsDAO;
import com.itgrids.model.BiometricEmployeeDetails;

@Repository
public class BiometricEmployeeDetailsDAO extends GenericDaoHibernate<BiometricEmployeeDetails, Long> implements IBiometricEmployeeDetailsDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	BiometricEmployeeDetailsDAO(){
		super(BiometricEmployeeDetails.class);
	}
}
