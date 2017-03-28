package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Option;

	public interface IOptionDAO extends GenericDao<Option, Long> {
		
		public List<Option> getSubOptionsByParentOptionId(Long parentOptionsId);
		
		public Long getSubOptionsCountParentOptionId(Long parentOptionsId);
	}
