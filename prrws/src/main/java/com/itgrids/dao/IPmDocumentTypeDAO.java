package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmDocumentType;

public interface IPmDocumentTypeDAO extends GenericDao<PmDocumentType, Long> {
	public List<Object[]> getPmDocumentTypeList();

}
