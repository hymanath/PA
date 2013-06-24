package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NewsDetails;

public interface INewsDetailsDAO extends GenericDao<NewsDetails,Long> {
	
	public List<Object[]> getEditionAndPageNoByFileSourceId(Long fileSourceLanguageId);

}
