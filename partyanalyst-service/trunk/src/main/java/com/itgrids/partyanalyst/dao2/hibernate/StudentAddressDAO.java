package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IStudentAddressDAO;
import com.itgrids.partyanalyst.model.AddressForStudent;
import com.itgrids.partyanalyst.model.StudentAddress;

public class StudentAddressDAO extends GenericDaoHibernate<StudentAddress, Long> implements IStudentAddressDAO{

	public StudentAddressDAO() {
		super(StudentAddress.class);
	}
	public List<StudentAddress> getStudentSpecificAddressDetails(Long studentId){
		
		Query query = getSession().createQuery(" select model from StudentAddress model " +
				" where model.student.studentId = :studentId ");
	
		query.setParameter("studentId", studentId);
		
		return query.list();
	}

}
