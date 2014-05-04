package com.itgrids.eliteclub.dao;

import java.util.List;
import java.util.Set;

import com.itgrids.eliteclub.model.File;

public interface FileDAO extends AbstractDao<File,Integer> {
	
	public List<?> loadFilesByCategory();
	public List<?> getVoiceIdsForFileIds(Set<Integer> fileIds);
	public List<?> getSmsTextForFileIds(Set<Integer> fileIds) ;


}
