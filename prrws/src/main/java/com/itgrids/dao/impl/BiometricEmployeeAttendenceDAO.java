package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IBiometricEmployeeAttendenceDAO;
import com.itgrids.model.BiometricEmployeeAttendence;

@Repository
public class BiometricEmployeeAttendenceDAO extends GenericDaoHibernate<BiometricEmployeeAttendence, Long>
		implements IBiometricEmployeeAttendenceDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	BiometricEmployeeAttendenceDAO(){
		super(BiometricEmployeeAttendence.class);
	}
}
