package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IStudentParentDetailsDAO;
import com.itgrids.partyanalyst.model.StudentParentDetails;

public class StudentParentDetailsDAO extends GenericDaoHibernate<StudentParentDetails, Long> implements IStudentParentDetailsDAO{

	public StudentParentDetailsDAO() {
		super(StudentParentDetails.class);
	}
	public List<Object[]> getParentDetails(Long studentId){
		
		StringBuilder str = new StringBuilder();
		
		str.append("select model.parentName,model.relation from StudentParentDetails model ");
		
		if(studentId !=null && studentId>0){
			str.append(" where model.student.studentId = :studentId ");
		}
		Query query = getSession().createQuery(str.toString());
		
		if(studentId !=null && studentId>0){
			query.setParameter("studentId", studentId);
		}
		return  query.list();
	}

}
