package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.model.SubProgram;

public interface ISubProgramDAO extends GenericDao<SubProgram, Long> {

	public List<Object[]> getGovtSubProgramsDetails(Long govtSchemesId);;
}
