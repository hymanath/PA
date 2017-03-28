package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CustomReportImage;

public interface ICustomReportImageDAO extends GenericDao<CustomReportImage, Long> {
	public List<Object[]> getImageDetails(Long programId);
}
