package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DelimitationWard;

public interface IDelimitationWardDAO extends GenericDao<DelimitationWard, Long>{

	public List<DelimitationWard> findByDelimitationConstituenyTownAndWard(Long delimconstiTownId,Long wardId);
}
