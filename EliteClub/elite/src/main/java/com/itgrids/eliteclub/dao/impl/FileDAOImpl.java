package com.itgrids.eliteclub.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.eliteclub.dao.FileDAO;
import com.itgrids.eliteclub.model.File;

@Component("fileDAO")

public class FileDAOImpl extends AbstractDaoImpl<File, Integer> implements FileDAO {

	protected FileDAOImpl(Class<File> entityClass) {
		super(entityClass);
		
	}
	public  FileDAOImpl() {
		super(File.class);
		
	}
	//@Override
	
	public List<?> loadFilesByCategory() {
	
	Query  query=	 getCurrentSession().createQuery("select category  from  Category  category join  category.files file  where category.isDeleted='N'  and  file.isDeleted='N' group by  category ");
		
		return query.list();
	}
	
	public List<?> getVoiceIdsForFileIds(Set<Integer> fileIds) {
		
	Query  query=	 getCurrentSession().createQuery("select distinct file.voiceId  from  File file     where  file.fileId in(:fileIds) and  file.isDeleted='N' and file.contentType.contenttypeId=1 group by file.voiceId ");
	query.setParameterList("fileIds", fileIds);
		return query.list();
	}
	
	
	public List<?> getSmsTextForFileIds(Set<Integer> fileIds) {
		
		Query  query=	 getCurrentSession().createQuery("select distinct file.description  from  File file     where  file.fileId in(:fileIds) and  file.isDeleted='N' and file.contentType.contenttypeId=2 group by file.description ");
		query.setParameterList("fileIds", fileIds);
			return query.list();
		}

}
