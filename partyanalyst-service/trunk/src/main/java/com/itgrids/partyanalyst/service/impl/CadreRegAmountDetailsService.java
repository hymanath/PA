package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.itgrids.partyanalyst.dto.CadreRegAmountUploadVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.CadreRegAmountFile;
import com.itgrids.partyanalyst.service.ICadreRegAmountDetailsService;

public class CadreRegAmountDetailsService implements ICadreRegAmountDetailsService{

	private static final Logger LOG = Logger.getLogger(CadreRegAmountDetailsService.class);
	
	public ResultStatus uploadCadreRegAmountDetails(CadreRegAmountUploadVO cadreRegAmountUploadVO)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			Long cadreRegAmountFileId = saveCadreRegAmountFile(cadreRegAmountUploadVO);
			return resultStatus;
		}catch(Exception e)
		{
			LOG.error("Exception Occured in uploadCadreRegAmountDetails Method - ",e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			return resultStatus;
		}
	}
	
	public List<CadreRegAmountUploadVO> readCadreRegAmountExcel(String filePath)
	{
		List<CadreRegAmountUploadVO> list = new ArrayList<CadreRegAmountUploadVO>(0);
		try{
			FileInputStream file = new FileInputStream(new File(filePath));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(0);
			int totalRows = sheet.getLastRowNum();
			CadreRegAmountUploadVO cadreRegAmountUploadVO = null;
			for(int index = 1;index<totalRows;index++)
			{
				try{
					cadreRegAmountUploadVO = new CadreRegAmountUploadVO();
					HSSFRow row = sheet.getRow(index);
					cadreRegAmountUploadVO.setBranch(row.getCell(0).toString());
					cadreRegAmountUploadVO.setAmount(Integer.valueOf(row.getCell(1).toString()));
					cadreRegAmountUploadVO.setUsername(row.getCell(2).toString());
					list.add(cadreRegAmountUploadVO);
				}catch(Exception e)
				{
					LOG.error(e);
				}
			}
			return list;
		}catch(Exception e)
		{
			LOG.error("Exception Occured in readCadreRegAmountExcel Method - ",e);
			return list;
		}
	}
	
	public Long saveCadreRegAmountFile(CadreRegAmountUploadVO cadreRegAmountUploadVO)
	{
		try{
			CadreRegAmountFile cadreRegAmountFile = new CadreRegAmountFile();
			//cadreRegAmountFile.set
			return null;
		}catch(Exception e)
		{
			LOG.error("Exception Occured in saveCadreRegAmountFile method - "+e);
			return null;
		}
	}
}
