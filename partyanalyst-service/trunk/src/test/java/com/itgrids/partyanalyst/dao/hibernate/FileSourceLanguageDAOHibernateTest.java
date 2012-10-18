package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFileSourceLanguageDAO;
import com.itgrids.partyanalyst.model.FileSourceLanguage;

public class FileSourceLanguageDAOHibernateTest extends BaseDaoTestCase {
  
	 private IFileSourceLanguageDAO fileSourceLanguageDAO;

	public void setFileSourceLanguageDAO(
			IFileSourceLanguageDAO fileSourceLanguageDAO) {
		this.fileSourceLanguageDAO = fileSourceLanguageDAO;
	}
	 
	 /*public void test(){
		 
		 fileSourceLanguageDAO.getAll();
	 }*/
	
	/*public void testGetFileSourceLanguageObject()
	{
		List<FileSourceLanguage> list = fileSourceLanguageDAO.getFileSourceLanguageObject(8547L,1L, 1l);
		for(FileSourceLanguage filesource: list)
		System.out.println(filesource.getFile().getFileId());
	}*/
	
	public void testGetFileLanguage()
	{
		System.out.println(fileSourceLanguageDAO.getFileLanguage(14l));
	}
}
