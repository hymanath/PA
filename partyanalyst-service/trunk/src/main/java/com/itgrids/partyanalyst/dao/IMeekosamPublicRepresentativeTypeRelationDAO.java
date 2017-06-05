package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MeekosamPublicRepresentativeTypeRelation;

public interface IMeekosamPublicRepresentativeTypeRelationDAO extends GenericDao<MeekosamPublicRepresentativeTypeRelation, Long> {

	public List<Object[]> getReferalNamesByTypeAndDist(Long typeId,Long districtId);
	public List<Long> getMeekosamPublicRepresentativeTypeRelationId(Long typeId,Long districtId,Long nameId);
}
