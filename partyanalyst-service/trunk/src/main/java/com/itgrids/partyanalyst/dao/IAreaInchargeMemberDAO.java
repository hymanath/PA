package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AreaInchargeMember;

public interface IAreaInchargeMemberDAO extends GenericDao<AreaInchargeMember, Long>{
	public String getActiveOrInActiveInchageDetails(Long cadreId);
	public List<Object[]> getAssignedCadreList(String status);
	public List<Long> getAssignedInchargeBooths(Long cadreId);
}
