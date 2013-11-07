package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.model.FileKeyword;
import com.itgrids.partyanalyst.dao.IFileKeywordDAO;


public class FileKeywordDAO extends GenericDaoHibernate<FileKeyword, Long> implements IFileKeywordDAO{

	public FileKeywordDAO() {
		super(FileKeyword.class);
		
	}
	

	
	public List<Long> getFilesForEachKeyWord(List<Long> keyWords)
	{
		Query query =getSession().createQuery("select distinct model.file.fileId from FileKeyword model where model.keyword.keywordId in(:keyWords)");
		query.setParameterList("keyWords", keyWords);
		return query.list();
		
	}
}

