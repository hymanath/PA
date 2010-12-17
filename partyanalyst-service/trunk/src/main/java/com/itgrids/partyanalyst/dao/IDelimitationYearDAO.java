package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DelimitationYear;

public interface IDelimitationYearDAO extends GenericDao<DelimitationYear, Long>{

	public List<DelimitationYear> findByDelimitationYear(String year);
}
