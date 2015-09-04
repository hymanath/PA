package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.StudentRecomendation;

public interface IStudentRecomendationDAO extends GenericDao<StudentRecomendation, Long>{

	public List<Object[]> getRecomendationDetailsOfStudent(Long studentId);
}
