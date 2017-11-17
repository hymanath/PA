package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.AssemblyMla;

public interface IAssemblyMlaDAO extends GenericDao<AssemblyMla, Long> {

	public List<Object[]> getAllConstituencyDetails(Long constituencyId);

}
