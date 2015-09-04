package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IStudentRecomendationDAO;
import com.itgrids.partyanalyst.model.StudentRecomendation;

public class StudentRecomendationDAO extends GenericDaoHibernate<StudentRecomendation, Long> implements IStudentRecomendationDAO{

	public StudentRecomendationDAO() {
		super(StudentRecomendation.class);
	}
	
	public List<Object[]> getRecomendationDetailsOfStudent(Long studentId){
		
		Query query=getSession().createQuery("select model.studentRecomendationId," +
				" model.personName,model.referalDesignation.referalDesignationId,model.referalDesignation.designation," +
				" model.contactNo,model.tdpCadreId,model.membershipNo " +
				" from StudentRecomendation model " +
				" where model.student.studentId = :studentId ");
		
		query.setParameter("studentId", studentId);
		
		return query.list();
	}

}
