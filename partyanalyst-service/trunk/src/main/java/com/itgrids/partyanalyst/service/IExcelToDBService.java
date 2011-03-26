package com.itgrids.partyanalyst.service;

import java.io.File;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.excel.upload.vo.UploadFormVo;

public interface IExcelToDBService {

	public ResultStatus readCSVFileAndStoreIntoDB(UploadFormVo uploadFormValueObject,String fileName,File fileToUpload) throws Exception;
}
