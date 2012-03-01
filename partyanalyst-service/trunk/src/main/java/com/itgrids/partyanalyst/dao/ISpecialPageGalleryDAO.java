package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.SpecialPage;
import com.itgrids.partyanalyst.model.SpecialPageGallery;

public interface ISpecialPageGalleryDAO extends GenericDao<SpecialPageGallery, Long> {
	
	public List<Object[]> getGallariesBySpecialPageId(Long specialPageId, String contentType);
	
	public List<Object[]> getSpecialPageGallaryDetails(Long specialPageId,String contentType);

	public List<Object[]> getSpecialPageGallaryDetail(Long specialPageId,
			int firstRecord, int maxRecord, String type);
	
	public List<File> getGalleryBasedOnSpecialPageId(Long specialPageId,int firstRecord, int maxRecord, String contentType);
	
	public List<SpecialPage> getSpecialPageByGalleryId(Long gallaryId);

}
