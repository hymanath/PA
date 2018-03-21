package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NominatedPostPosition;

public interface INominatedPostPositionDAO extends GenericDao<NominatedPostPosition, Long>{
	public List<Object[]> getDepartmentWisePositionList(Long boardId);

}
