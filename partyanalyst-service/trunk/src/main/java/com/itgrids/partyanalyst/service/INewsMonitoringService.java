package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.FileVO;

public interface INewsMonitoringService {
	public List<FileVO> getNewsForRegisterUsers(FileVO inputs);
	public List<FileVO> getAllCountDetails(Date fromDate,Date toDate,String fileType,Long regId);
	public List<FileVO> getAllSourceDetails();
	public List<FileVO> getAllCategoryDetails();
	public List<FileVO> getAllSourceLanguageDetails();
	public List<FileVO> getAllNewsImportanceDetails();
}
