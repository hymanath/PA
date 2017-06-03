package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtProposalPropertyCategory;

public interface IGovtProposalPropertyCategoryDAO extends GenericDao<GovtProposalPropertyCategory, Long>{
	
	public GovtProposalPropertyCategory getExistingStatusByAlertId(Long alertId);
	public List<Object[]> getStatusFrALert(Long alertId);
	public List<Object[]> getProposalStatusFrAlert(Long alertId);

}
