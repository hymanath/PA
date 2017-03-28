package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpMemberType;

public interface ITdpMemberTypeDAO extends GenericDao<TdpMemberType, Long>{
	public List<Object[]> getCadreMemberTypeListByYear();
}
