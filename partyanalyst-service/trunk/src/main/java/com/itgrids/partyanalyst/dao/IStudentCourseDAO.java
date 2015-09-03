package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.StudentCourse;

public interface IStudentCourseDAO extends GenericDao<StudentCourse, Long>{

	public List<Object[]> getAcademicYearDetailsOfStudent(Long studentId);
}
