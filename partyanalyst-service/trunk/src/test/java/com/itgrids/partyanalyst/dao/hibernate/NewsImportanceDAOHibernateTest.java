package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.INewsImportanceDAO;

public class NewsImportanceDAOHibernateTest  extends BaseDaoTestCase{
   private INewsImportanceDAO newsImportanceDAO;

   public INewsImportanceDAO getNewsImportanceDAO() {
	  return newsImportanceDAO;
   }

   public void setNewsImportanceDAO(INewsImportanceDAO newsImportanceDAO) {
	  this.newsImportanceDAO = newsImportanceDAO;
   }
   public void testGetAll(){
	   newsImportanceDAO.getAll();
   }
}
