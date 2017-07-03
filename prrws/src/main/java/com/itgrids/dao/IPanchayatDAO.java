package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.Panchayat;

public interface IPanchayatDAO extends GenericDao<Panchayat, Long>  {

	public List<Object[]> getPanchayatIdAndNameByIds(List<Long> panchayatIds);
	public List<Object[]> getPanchayatIdAndName(Long panchayatId);
}
