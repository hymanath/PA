package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MeekosamOccupation;

public interface IMeekosamOccupationDAO  extends GenericDao<MeekosamOccupation, Long>{
	public List<Object[]> getMeekosamOccupationList();
}
