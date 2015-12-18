package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.EventDocumentVO;
import com.itgrids.partyanalyst.model.ActivityInfoDocument;

public interface IActivityInfoDocumentDAO extends GenericDao<ActivityInfoDocument, Long>{
	public List<Object[]> getEventDocuments(EventDocumentVO inputVO,Date startDate,Date endDate);
	public List<Object[]> getLocations(EventDocumentVO inputVO,Date startDate,Date endDate,String type);
	
	
	public Integer deleteEventUploadFilebyActivityInfoDocId(Long acitivityInfoDocId);
	public List<Object[]> getAvailableDates(EventDocumentVO inputVO,Date startDate,Date endDate,String type);
	public Long getEventDocumentsCount(EventDocumentVO inputVO,Date startDate,Date endDate);
	public List<Object[]> getLocationWiseImageCount(EventDocumentVO inputVO,Date startDate,Date endDate,String type);
	
}
