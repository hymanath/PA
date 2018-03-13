package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.District;
import com.itgrids.model.Division;

public interface IDivisionDAO extends GenericDao<Division,Long>{
	public List<Object[]> getDivisionIdAndNameByIds(List<Long> divisonIds);
	public List<Object[]> getDivisionsOfDistrict(Long districtId);
}
