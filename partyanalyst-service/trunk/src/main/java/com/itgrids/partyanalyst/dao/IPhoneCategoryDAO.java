package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PhoneCategory;

public interface IPhoneCategoryDAO extends GenericDao<PhoneCategory, Long>{

	public Object getPhoneCategoryNameByPhoneCategoryId(Long phoneCategoryId);
	public List<Object[]> getAllPhoneCategorys();
}
