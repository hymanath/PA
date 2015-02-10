package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TirupatiByelection;

public interface ITirupatiByelectionDAO extends GenericDao<TirupatiByelection, Long>{
	public List<TirupatiByelection> getModelByType(Long typeId,String type);
	public List<String> getDivisionNames();
	public List<String> getClusterNames();
	public List<TirupatiByelection> getModelByboothIds(List<Long> boothIds);
}
