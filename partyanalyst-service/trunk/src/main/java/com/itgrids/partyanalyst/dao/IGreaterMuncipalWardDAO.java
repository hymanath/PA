package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GreaterMuncipalWard;

public interface IGreaterMuncipalWardDAO extends GenericDao<GreaterMuncipalWard,Long>{
	public List<Object[]> getWardsByWardsIdsLsit(Long publicationDateId,List<Long> getWardsByWardsIdsLsit);
	public List<Object[]> getDivisionWiseVoters(List<Long> divisionIds);
	public Object getTotalVotersByDivisionIds(List<Long> divisionIds);
}
