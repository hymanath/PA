package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DelimitationConstituencyTown;

public interface IDelimitationConstituencyTownDAO extends GenericDao<DelimitationConstituencyTown, Long>{

	public List<DelimitationConstituencyTown> findByDelimitationConstituencyAndTownship(Long delimitationConstiId,Long townId);
}
