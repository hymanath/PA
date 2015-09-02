package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IStudentCadreRelationDAO;
import com.itgrids.partyanalyst.model.StudentCadreRelation;

public class StudentCadreRelationDAO extends GenericDaoHibernate<StudentCadreRelation, Long> implements IStudentCadreRelationDAO{

	public StudentCadreRelationDAO() {
		super(StudentCadreRelation.class);
		
	}

}
