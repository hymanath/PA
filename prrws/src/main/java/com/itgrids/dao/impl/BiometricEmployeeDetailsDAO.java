package com.itgrids.dao.impl;



import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
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
	
	public Long getTotalEmployeeCount() {
		Query query  = getSession().createQuery("select count(distinct model.empId) from BiometricEmployeeDetails model where model.isDeleted='N' ");
		return (Long) query.uniqueResult();
	}
	public List<Object[]> getEmployeeDetails() {
		Query query  = getSession().createQuery(" select distinct model.empId,model.empName,model.empMobile from BiometricEmployeeDetails model where model.isDeleted='N' ");
		return query.list();
	}
}
