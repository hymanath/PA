package com.itgrids.eliteclub.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Component;

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
	@Override
	public List<?> loadFilesByCategory() {
	
	Query  query=	 getCurrentSession().createQuery("select category  from  Category  category join  category.files file  where category.isDeleted='N'  and  file.isDeleted='N' group by  category ");
		
		return query.list();
	}



}
