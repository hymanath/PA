package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalDesignation;

public interface ISelfAppraisalDesignationDAO extends GenericDao<SelfAppraisalDesignation, Long> {
   public List<Object[]> getDesiganationList();
   public List<Object[]> getDesignationDtls(List<Long> desigList);
}
