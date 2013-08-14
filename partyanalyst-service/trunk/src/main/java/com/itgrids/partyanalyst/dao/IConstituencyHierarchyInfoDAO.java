package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ConstituencyHierarchyInfo;

public interface IConstituencyHierarchyInfoDAO extends GenericDao<ConstituencyHierarchyInfo, Long>{
	
	public Integer deleteConstituencyBasicInfo(Long constituencyId,Long publicationId,Long userId);
	
	public List<ConstituencyHierarchyInfo> getConstituencyHierarchyInfoList(Long constituencyId,Long userId);
	

}
