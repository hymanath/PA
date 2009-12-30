package com.itgrids.partyanalyst.service;

import java.io.File;

import com.itgrids.partyanalyst.excel.upload.vo.UploadFormVo;

public interface IExcelToDBService {

	public void readCSVFileAndStoreIntoDB(UploadFormVo uploadFormValueObject,String fileName,File fileToUpload) throws Exception;
}
