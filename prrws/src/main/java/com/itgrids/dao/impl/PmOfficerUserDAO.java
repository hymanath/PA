package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmOfficerUserDAO;
import com.itgrids.model.PmOfficerUser;

@Repository
public class PmOfficerUserDAO extends GenericDaoHibernate<PmOfficerUser, Long> implements IPmOfficerUserDAO {

	@Autowired
	SessionFactory sessionFactory;

	PmOfficerUserDAO(){
		super(PmOfficerUser.class);
	}
	
	public Object[] getPmOffceUserDetails(Long userId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.pmDepartmentDesignation.pmDepartment.pmDepartmentId,");// 0-deptId
		sb.append(" model.pmDepartmentDesignation.pmDepartment.department,");//1-deptName
		sb.append( "model.pmDepartmentDesignation.pmOfficerDesignation.pmOfficerDesignationId,");//2-designtionId
		sb.append("model.pmDepartmentDesignation.pmOfficerDesignation.designation,");//3-designtionName
		sb.append("model2.pmOfficer.pmOfficerId,model2.pmOfficer.name, model2.pmOfficer.mobileNo ");//4-officerId,5-name,6-mobileNo
		sb.append("from PmOfficerUser model,PmDepartmentDesignationOfficer model2 ");
		sb.append( "where model.pmDepartmentDesignationId=model2.pmDepartmentDesignationId and ");
		sb.append( "model.isActive ='Y' and model2.pmOfficer.isActive='Y' and model2.isActive='Y' and "
				+ " model.userId=:userId " );
		Query query = getSession().createQuery(sb.toString());//
		query.setParameter("userId", userId);
		return (Object[]) query.uniqueResult();
	}
}
