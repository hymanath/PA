package com.itgrids.eliteclub.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import com.itgrids.eliteclub.dao.FileDAO;
import com.itgrids.eliteclub.model.File;

public class FileDAOImpl extends AbstractDaoImpl<File, Integer> implements FileDAO {

	protected FileDAOImpl(Class<File> entityClass) {
		super(entityClass);
		
	}
	public  FileDAOImpl() {
		super(File.class);
		
	}



}
