package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CommitteIvrDistrictDetail;

public interface ICommitteIvrDistrictDetailDAO extends GenericDao<CommitteIvrDistrictDetail, Long>{
	
	public List<Object[]> getDistrictWiseIvrDetails(Long campainId);

}
