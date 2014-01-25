package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Characteristics;

public interface ICharacteristicsDAO extends GenericDao<Characteristics, Long>{

	public List<Characteristics> getCharacteristicsDetails();
}
