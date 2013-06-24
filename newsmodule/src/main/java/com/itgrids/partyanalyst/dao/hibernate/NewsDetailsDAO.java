package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INewsDetailsDAO;
import com.itgrids.partyanalyst.model.NewsDetails;

public class NewsDetailsDAO  extends GenericDaoHibernate<NewsDetails,Long> implements INewsDetailsDAO {
	public NewsDetailsDAO() {
		super(NewsDetails.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getEditionAndPageNoByFileSourceId(Long fileSourceLanguageId)
	{
		Query query = getSession().createQuery(" select model.pageNo, model.edition from NewsDetails model where " +
				" model.fileSourceLanguage.fileSourceLanguageId =:fileSourceLanguageId ");
		query.setParameter("fileSourceLanguageId", fileSourceLanguageId);
		return query.list();
		
	}
}
