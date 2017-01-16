package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalDesignationTarget;

public interface ISelfAppraisalDesignationTargetDAO extends GenericDao<SelfAppraisalDesignationTarget, Long> {
    public List<Object[]> getTourCategoryWiseTargetCnt(List<Long> monthYearIds,String type);
    public List<Object[]> getCandiateAndCategoryWiseTargetCnt(List<Long> monthYearIds,String type,Long selfAppraisalCandidateId);
    public List<Object[]> getTotalTargetOfDesignation(Date fromDate,Date toDate,List<Long> designationsList,String type);
    public List<Object[]> getDesignationAndCategoryWiseCandidatesTarget(Date fromDate,Date toDate,String type,List<Long> designationIds,Long candidateId,List<Long> monthyearIds);
    public List<Object[]> getCategoryWiseTargetCnt(List<Long> monthYearIds,String type,List<Long> desinationIds);
    public List<Object[]> getToursDetailsforDesignation(List<String> monthYearStrList, Long designationId);
}
