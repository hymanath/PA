package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.NregaFAVacantPanchayat;

public interface INregaFAVacantPanchayatDAO extends GenericDao<NregaFAVacantPanchayat, Long>{

	public List<Object[]> getLocationWiseEmptyVacencies(String locationType,Long locationId,String subLocationType);
	public Long getLocationWiseEmptyVacencyCount(String locationType,Long locationId);
}
