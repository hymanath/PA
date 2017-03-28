package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.StudentContact;

public interface IStudentContactDAO extends GenericDao<StudentContact, Long>{
	public List<Object[]> getContactDetailsOfStudent(Long studentId);
}
