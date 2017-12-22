package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.Document;

public interface IDocumentDAO extends GenericDao<Document, Long> {
	public List<Long> getdocumentByFilePath(String path);
}
