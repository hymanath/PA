package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PetitionSubject;

public interface IPetitionSubjectDAO extends GenericDao<PetitionSubject, Long>{
	public List<Object[]> getPetitionSubjectList();
	public List<Object[]> getPetitionSubSubjectList(Long subjectId);

}
