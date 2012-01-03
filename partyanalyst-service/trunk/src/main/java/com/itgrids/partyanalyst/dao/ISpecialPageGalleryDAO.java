package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SpecialPageGallery;

public interface ISpecialPageGalleryDAO extends GenericDao<SpecialPageGallery, Long> {
	public List<Object[]> getGallariesBySpecialPageId(Long specialPageId, String contentType);

}
