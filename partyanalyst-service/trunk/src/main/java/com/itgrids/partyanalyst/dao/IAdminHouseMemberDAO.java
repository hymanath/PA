package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AdminHouseMember;

public interface IAdminHouseMemberDAO extends GenericDao<AdminHouseMember, Long>{
	
	public List<Object[]> getcandateNameForPartyId(Long partyId);
	public List<Object[]> getAllPartyNames();
	
}
