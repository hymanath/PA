package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UnionTypeDesignation;

public interface IUnionTypeDesignationDAO extends GenericDao<UnionTypeDesignation, Long>{
	public List<Object[]> getDesignationsOfUnionType(Long uniontypeId);
	public List<Object[]> getDesignationsOfTdpMemberTypeIdsList(List<Long> tdpMemberTypeIdsList);
	public List<Object[]> getDesignationsOfTdpMemberTypeId(Long tdpMemberTypeId);
}
