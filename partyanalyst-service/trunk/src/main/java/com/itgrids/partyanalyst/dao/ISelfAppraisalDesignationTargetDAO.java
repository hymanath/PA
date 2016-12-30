package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalDesignationTarget;

public interface ISelfAppraisalDesignationTargetDAO extends GenericDao<SelfAppraisalDesignationTarget, Long> {
	public List<Object[]> getDesignationWiseTargetCnt(Date fromDate,Date toDate,String type);
    public List<Object[]> getTourCategoryWiseTargetCnt(Date fromDate,Date toDate,String type);
    public List<Object[]> getCandiateWiseTargetCnt(Date fromDate,Date toDate,String type);
    public List<Object[]> getCandiateAndCategoryWiseTargetCnt(Date fromDate,Date toDate,String type,Long selfAppraisalCandidateId);
    public List<Object[]> getTotalTargetOfDesignation(Date fromDate,Date toDate,List<Long> designationsList,String type);
    public List<Object[]> getDesignationAndCategoryWiseCandidatesTarget(Date fromDate,Date toDate,String type,List<Long> designationIds,Long candidateId);
    public List<Object[]> getTourCategoryWiseTargetCntDesignation(Date fromDate,Date toDate,String type,List<Long> designationIds);
    public List<Object[]> getCategoryWiseTargetCnt(Date fromDate,Date toDate,String type,List<Long> desinationIds);
}
