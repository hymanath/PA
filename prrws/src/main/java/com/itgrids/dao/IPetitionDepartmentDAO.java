package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PetitionDepartment;

public interface IPetitionDepartmentDAO extends GenericDao<PetitionDepartment, Long> {
	public List<Object[]> getAllPetitionList();

}
