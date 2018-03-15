package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmBriefLead;

public interface IPmBriefLeadDAO extends GenericDao<PmBriefLead, Long> {
	public List<Object[]> gePmBriefLeadDetailsList(Long deptDesignationId);
	public List<Object[]> getAllPmBriefLeadDetailsList();
	public List<Object[]> getAllPmBriefLeadDetailsDeptWiseList(List<Long> deptIdsList,List<Long> statusIds,Date fromDate,Date toDate);

}
