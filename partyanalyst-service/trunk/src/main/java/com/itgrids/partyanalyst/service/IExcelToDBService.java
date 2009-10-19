package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.excel.upload.vo.UploadFormVo;

public interface IExcelToDBService {

	public void readCSVFileAndStoreIntoDB(UploadFormVo uploadFormValueObject) throws Exception;
}
