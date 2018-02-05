package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmDeptDesignationPrePostStatusDetails;

public interface IPmDeptDesignationPrePostStatusDetailsDAO extends GenericDao<PmDeptDesignationPrePostStatusDetails, Long> {
	public List<Long> getItSelfandPoststatusDetail(Long statusId);
}
