package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MahanaduGroup;

public interface IMahanaduGroupDAO extends GenericDao<MahanaduGroup,Long>{
	public MahanaduGroup checkIsExistGroupName(String groupName);
}
