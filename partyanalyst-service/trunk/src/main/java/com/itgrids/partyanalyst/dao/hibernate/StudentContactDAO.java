package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IStudentContactDAO;
import com.itgrids.partyanalyst.model.StudentContact;

public class StudentContactDAO extends GenericDaoHibernate<StudentContact, Long> implements IStudentContactDAO{

	public StudentContactDAO() {
		super(StudentContact.class);
	}
	public List<Object[]> getContactDetailsOfStudent(Long studentId){
		
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.phoneNo,model.phoneType from StudentContact model " +
				" where model.student.studentId = :studentId " +
				" order by model.orderNo ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("studentId", studentId);
		
		return query.list();
	}
	
}
