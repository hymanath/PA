package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmSubject;

public interface IPmSubjectDAO extends GenericDao<PmSubject, Long> {
	public List<Object[]> getPmSubjectList();
	public List<Object[]> getPmSubSubjectList(Long subjectId);

}
