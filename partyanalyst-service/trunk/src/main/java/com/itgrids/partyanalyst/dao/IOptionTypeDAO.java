package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.OptionType;

public interface IOptionTypeDAO extends GenericDao<OptionType, Long> {

	public List<Object[]> getOptionTypes();
}
