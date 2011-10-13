package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.FileGallary;

public interface IFileGallaryDAO extends GenericDao<FileGallary, Long>{
	
	public List<Object[]> getStartingRecordInGallary(Long gallaryId);
	
	public List<Object[]> getAllRecordInGallary(Long gallaryId);

}
