package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalTourCategory;

public interface ISelfAppraisalTourCategoryDAO extends GenericDao<SelfAppraisalTourCategory, Long> {

	public List<Object[]> getAllTourCategorys(Long cadreId,Long designationId);
}
