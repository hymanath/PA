package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.itgrids.partyanalyst.dao.ICadreRegAmountDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreRegAmountFileDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dto.CadreRegAmountUploadVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.CadreRegAmountDetails;
import com.itgrids.partyanalyst.model.CadreRegAmountFile;
import com.itgrids.partyanalyst.service.ICadreRegAmountDetailsService;

public class CadreRegAmountDetailsService implements ICadreRegAmountDetailsService{

	private static final Logger LOG = Logger.getLogger(CadreRegAmountDetailsService.class);
	private IUserDAO userDAO;
	private ICadreRegAmountFileDAO cadreRegAmountFileDAO;
	private IVoterDAO voterDAO;
	private ICadreRegAmountDetailsDAO cadreRegAmountDetailsDAO;
	private ICadreSurveyUserDAO cadreSurveyUserDAO;
	
	public ICadreSurveyUserDAO getCadreSurveyUserDAO() {
		return cadreSurveyUserDAO;
	}

	public void setCadreSurveyUserDAO(ICadreSurveyUserDAO cadreSurveyUserDAO) {
		this.cadreSurveyUserDAO = cadreSurveyUserDAO;
	}

	public ICadreRegAmountDetailsDAO getCadreRegAmountDetailsDAO() {
		return cadreRegAmountDetailsDAO;
	}

	public void setCadreRegAmountDetailsDAO(
			ICadreRegAmountDetailsDAO cadreRegAmountDetailsDAO) {
		this.cadreRegAmountDetailsDAO = cadreRegAmountDetailsDAO;
	}

	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}

	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}

	public ICadreRegAmountFileDAO getCadreRegAmountFileDAO() {
		return cadreRegAmountFileDAO;
	}

	public void setCadreRegAmountFileDAO(
			ICadreRegAmountFileDAO cadreRegAmountFileDAO) {
		this.cadreRegAmountFileDAO = cadreRegAmountFileDAO;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public ResultStatus uploadCadreRegAmountDetails(CadreRegAmountUploadVO cadreRegAmountUploadVO)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			List<CadreRegAmountUploadVO> list = readCadreRegAmountExcel(cadreRegAmountUploadVO.getPath()) ;
			
			CadreRegAmountFile cadreRegAmountFile = new CadreRegAmountFile();
			cadreRegAmountFile.setFileName(cadreRegAmountUploadVO.getFileName());
			cadreRegAmountFile.setPath(cadreRegAmountUploadVO.getPath());
			cadreRegAmountFile.setUploadedby(userDAO.get(cadreRegAmountUploadVO.getUserId()));
			cadreRegAmountFile.setDate(cadreRegAmountUploadVO.getUploadedDate());
			cadreRegAmountFile.setUploadedTime(cadreRegAmountUploadVO.getUploadedTime());
			cadreRegAmountFile = cadreRegAmountFileDAO.save(cadreRegAmountFile);
			
			if(list != null && list.size() > 0)
			{
				for(CadreRegAmountUploadVO uploadVO : list)
				{
					try{
					CadreRegAmountDetails cadreRegAmountDetails = new CadreRegAmountDetails();
					cadreRegAmountDetails.setCadreRegAmountFile(cadreRegAmountFile);
					cadreRegAmountDetails.setCadreSurveyUserId(cadreSurveyUserDAO.getCadreSurveyUserByUsername(uploadVO.getUsername()).getCadreSurveyUserId());
					cadreRegAmountDetails.setAmount(Long.valueOf(uploadVO.getAmount().toString()));
					cadreRegAmountDetails.setBranch(uploadVO.getBranch());
					cadreRegAmountDetailsDAO.save(cadreRegAmountDetails);
					}catch(Exception e)
					{
						LOG.error(e);
					}
				}
			}
			voterDAO.flushAndclearSession();
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
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
	
}
