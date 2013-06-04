package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IFileTypeDAO;
import com.itgrids.partyanalyst.model.FileType;

public class FileTypeDAO extends GenericDaoHibernate<FileType, Long> implements
		IFileTypeDAO {
	public FileTypeDAO() {
		super(FileType.class);
	}

	@SuppressWarnings("unchecked")
	public List<FileType> getFileType(String type) {
		return getHibernateTemplate().find(
				"from FileType model where model.type= ? ", type);
	}

}
