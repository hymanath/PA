package com.itgrids.eliteclub.dao;

import java.util.List;

import com.itgrids.eliteclub.model.File;

public interface FileDAO extends AbstractDao<File,Integer> {
	
	public List<?> loadFilesByCategory();

}
