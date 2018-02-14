package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmDeptDesignationPrePostStatusDetails;

public interface IPmDeptDesignationPrePostStatusDetailsDAO extends GenericDao<PmDeptDesignationPrePostStatusDetails, Long> {
	public List<Long> getItSelfandPoststatusDetail(List<Long> statusIdsList,Long pmOfficerDesignationId);
	public List<Long> getAssignedDesignationsForStatusIdsList(List<Long> pmOfficerDesignationIdsList);
	public List<Object[]> getMyPreStatusDetail(List<Long>  pmOfficerDesignationIdsList);
}
