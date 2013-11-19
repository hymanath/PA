package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INewsResponseDAO;
import com.itgrids.partyanalyst.model.NewsResponse;


public class NewsResponseDAO extends GenericDaoHibernate<NewsResponse,Long> implements INewsResponseDAO {

	public NewsResponseDAO(){
		super(NewsResponse.class);
	}
	
	public List<Long> getCandidateNewsResponseFileIds(Long fileId)
	{
		
		return getHibernateTemplate().find("select distinct(model.candidatePartyFile.file.fileId) from NewsResponse model where model.file.fileId = ?",fileId);
	}
}
