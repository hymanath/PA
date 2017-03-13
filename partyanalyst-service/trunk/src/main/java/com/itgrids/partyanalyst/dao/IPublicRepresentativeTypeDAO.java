package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PublicRepresentativeType;

public interface IPublicRepresentativeTypeDAO extends GenericDao<PublicRepresentativeType, Long>{

	public List<Object[]> getAllPublicRepresentativeList();
	public List<Long> getIds(List<Long> representativeIds);
	public List<Object[]> getPublicRepresentativeInfoList();

}
