package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IFileDAO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.utils.IConstants;

public class FileDAO extends GenericDaoHibernate<File, Long> implements
		IFileDAO {
	public FileDAO() {
		super(File.class);
	}
	public List<Object[]> getFileByFileId(Long fileId)
	{
		return getHibernateTemplate().find("select model.fileId,model.fileName,model.filePath,model.fileTitle,model.fileDescription , " +
				" model.source ,model.fileDate from File model where  model.fileId=?",fileId); 
		
		
	}
}
