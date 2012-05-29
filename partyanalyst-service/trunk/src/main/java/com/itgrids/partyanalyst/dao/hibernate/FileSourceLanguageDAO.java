package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IFileSourceLanguageDAO;
import com.itgrids.partyanalyst.model.FileSourceLanguage;

public class FileSourceLanguageDAO extends GenericDaoHibernate<FileSourceLanguage,Long> implements IFileSourceLanguageDAO {

	 public FileSourceLanguageDAO(){
		 super(FileSourceLanguage.class);
	 }

	 @SuppressWarnings("unchecked")
	public List<FileSourceLanguage> getFileSourceLanguageObject(Long fileId , Long sourceId ,Long languageId)
	 {
		 Object[] params = {fileId,sourceId , languageId};
		return getHibernateTemplate().find("select model from FileSourceLanguage model where model.file.fileId = ? and model.source.sourceId = ? " +
		 		" and model.language.languageId =?",params );
	 }
}
