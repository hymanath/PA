package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.itgrids.partyanalyst.dao.ICadreRegAmountDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.CadreAmountDetailsVO;
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
	private ICadreSurveyUserDAO cadreSurveyUserDAO;
	private ITdpCadreDAO tdpCadreDAO;
	private ICadreRegAmountDetailsDAO cadreRegAmountDetailsDAO;
	
	
	
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public ICadreRegAmountFileDAO getCadreRegAmountFileDAO() {
		return cadreRegAmountFileDAO;
	}

	public void setCadreRegAmountFileDAO(
			ICadreRegAmountFileDAO cadreRegAmountFileDAO) {
		this.cadreRegAmountFileDAO = cadreRegAmountFileDAO;
	}

	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}

	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}

	public ICadreSurveyUserDAO getCadreSurveyUserDAO() {
		return cadreSurveyUserDAO;
	}

	public void setCadreSurveyUserDAO(ICadreSurveyUserDAO cadreSurveyUserDAO) {
		this.cadreSurveyUserDAO = cadreSurveyUserDAO;
	}

	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}

	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}
	
	

	public ICadreRegAmountDetailsDAO getCadreRegAmountDetailsDAO() {
		return cadreRegAmountDetailsDAO;
	}

	public void setCadreRegAmountDetailsDAO(
			ICadreRegAmountDetailsDAO cadreRegAmountDetailsDAO) {
		this.cadreRegAmountDetailsDAO = cadreRegAmountDetailsDAO;
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
			for(int index = 1;index<=totalRows;index++)
			{
				try{
					cadreRegAmountUploadVO = new CadreRegAmountUploadVO();
					HSSFRow row = sheet.getRow(index);
					cadreRegAmountUploadVO.setBranch(row.getCell(0).toString());
					String amountStr = row.getCell(1).toString();
					amountStr = amountStr.substring(0,amountStr.length()-2);
					cadreRegAmountUploadVO.setAmount(Integer.valueOf(amountStr));
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
	
	
	public List<CadreAmountDetailsVO> getCadreRegAmountDetailsByDateWise(String fromDt,String toDt)
	{
		List<CadreAmountDetailsVO> finalList = null;
		try 
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date fromDate = format.parse(fromDt);
			Date toDate = format.parse(toDt);
			
			Map<String,Map<Long, CadreAmountDetailsVO>> datesMap = new HashMap<String, Map<Long,CadreAmountDetailsVO>>();
			Map<Long, CadreAmountDetailsVO> finalMap = null;
			Map<Long, CadreAmountDetailsVO> usersMap = null;
			List<Object[]> list1 = cadreRegAmountDetailsDAO.getAmountDetailsOfUserByDate(fromDate,toDate);
			if(list1 != null && list1.size() > 0)
			{
				if(list1!=null && list1.size()>0)
				{
					for(Object[] obj:list1)
					{
						if(obj[5] != null)
						{
							finalMap = datesMap.get(obj[5].toString());
							if(finalMap == null)
							{
								finalMap = new HashMap<Long, CadreAmountDetailsVO>();
								datesMap.put(obj[5].toString(), finalMap);
							}
							CadreAmountDetailsVO cd = new CadreAmountDetailsVO();
							cd.setUserId(Long.valueOf(obj[0].toString()));
							cd.setUserName(obj[1]!=null?obj[1].toString():"-");
							cd.setName(obj[2]!=null?obj[2].toString():"-");
							cd.setMobileNo(obj[3]!=null?obj[3].toString():"-");
							cd.setPaidAmount(obj[4]!=null?Long.valueOf(obj[4].toString()):0l);
							finalMap.put(cd.getUserId(), cd);
						}
						
					}
					
				}
				
				List<Long> userIds = new ArrayList<Long>();
				List<Object[]> userWiseDetails = tdpCadreDAO.getUserBetweenDates(fromDate, toDate);
				if(userWiseDetails != null && userWiseDetails.size() > 0)
				{
					usersMap = new HashMap<Long, CadreAmountDetailsVO>();
					for(String dateStr : datesMap.keySet())
					{
						finalMap = datesMap.get(dateStr);
						for (Object[] obj : userWiseDetails)
						{
							userIds.add(Long.valueOf(obj[0].toString()));
							CadreAmountDetailsVO cd = finalMap.get(Long.valueOf(obj[0].toString()));
							
							if(cd == null)
							{
								cd = new CadreAmountDetailsVO();
								cd.setUserId(Long.valueOf(obj[0].toString()));
								cd.setUserName(obj[1]!=null?obj[1].toString():"-");
								cd.setName(obj[2]!=null?obj[2].toString():"-");
								cd.setMobileNo(obj[3]!=null?obj[3].toString():"-");
								finalMap.put(cd.getUserId(), cd);
							}
							
							usersMap.put(Long.valueOf(obj[0].toString()), cd);
							
						}
					}
					
					//SETTING CONSTITUENCY AND TOTAL COUNTS FOR USER
					List<Object[]> list2 = new ArrayList<Object[]>();
					if(userIds!=null && userIds.size()>0){
						 list2 = tdpCadreDAO.getCandidateDataCollectedByDate(fromDate, toDate, userIds);
						
					}
					if(list2!=null && list2.size()>0)
					{
						for(Object[] obj:list2){
							//CadreAmountDetailsVO cd = getMatchedCadreCollector(finalList, Long.valueOf(obj[0].toString()));
							if(obj[4].toString() != null)
							{
								finalMap = datesMap.get(obj[4].toString());
								if(finalMap == null)
								{
									finalMap = new HashMap<Long, CadreAmountDetailsVO>();
									datesMap.put(obj[4].toString(), finalMap);
								}
								CadreAmountDetailsVO cd = finalMap.get(Long.valueOf(obj[0].toString()));
								if(cd != null)
								{
									cd.setTotalCount(obj[1]!=null?Long.valueOf(obj[1].toString()):0l);
									cd.setConstituencyId(Long.valueOf(obj[2].toString()));
									cd.setConstituency(obj[3].toString());
									cd.setTotalAmount(cd.getTotalCount()*100);
									
									
								}
								
								CadreAmountDetailsVO cd1 = finalMap.get(Long.valueOf(obj[0].toString()));
								if(cd1 != null)
								{
									cd.setDifference(cd.getTotalAmount()-cd1.getPaidAmount());
								}
								else
								{
									cd.setDifference(cd.getTotalAmount()-0l);
								}
								finalMap.put(Long.valueOf(obj[1].toString()), cd);
							}
							
							
							
							
							
						}
						
						if(usersMap != null && usersMap.size() > 0)
						{
							finalList = new ArrayList<CadreAmountDetailsVO>();
							for (Long userId : usersMap.keySet())
							{
								CadreAmountDetailsVO cd = usersMap.get(userId);
								List<CadreAmountDetailsVO> subList = new ArrayList<CadreAmountDetailsVO>();
								if(datesMap != null && datesMap.size() > 0)
								{
									for (String dateStr : datesMap.keySet()) 
									{
										CadreAmountDetailsVO subVO = new CadreAmountDetailsVO();
										subVO.setDate(dateStr);
										Map<Long, CadreAmountDetailsVO> dataMap = datesMap.get(dateStr);
										if(dataMap != null && dataMap.size() > 0 )
										{
											CadreAmountDetailsVO vo = dataMap.get(userId);
											if(vo != null)
											{
												subVO.setTotalCount(vo.getTotalCount());
												subVO.setPaidAmount(vo.getPaidAmount());
												subVO.setTotalAmount(vo.getTotalCount()*100);
												subVO.setDifference(vo.getTotalAmount()-vo.getPaidAmount());
											}
											else
											{
												subVO.setTotalCount(0l);
												subVO.setPaidAmount(0l);
												subVO.setTotalAmount(0l);
												subVO.setDifference(0l);
											}
										}
										subList.add(subVO);
									}
								}
								cd.setInfoList(subList);
								finalList.add(cd);
							}
						}
						
					}
				}
			}
		} 
		catch (Exception e)
		{
			finalList = null;
			LOG.error("Exception Occured in getCadreRegAmountDetailsByDateWise Method - ",e);
		}
		return finalList;
	}
	
	
	public List<CadreAmountDetailsVO> getCadreRegAmountDetailsByCumilativeReport(String fromDt,String toDt)
	{
		List<CadreAmountDetailsVO> finalList = new ArrayList<CadreAmountDetailsVO>();
		Map<Long, CadreAmountDetailsVO> finalMap = new HashMap<Long, CadreAmountDetailsVO>();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date fromDate = format.parse(fromDt);
			Date toDate = format.parse(toDt);
			
			List<Object[]> list1 = cadreRegAmountDetailsDAO.getAmountDetailsOfUser(fromDate,toDate);
			//List<Long> userIds = new ArrayList<Long>();
			if(list1!=null && list1.size()>0){
				for(Object[] obj:list1){
					CadreAmountDetailsVO cd = new CadreAmountDetailsVO();
					//userIds.add(Long.valueOf(obj[0].toString()));
					
					cd.setUserId(Long.valueOf(obj[0].toString()));
					cd.setUserName(obj[1]!=null?obj[1].toString():"-");
					cd.setName(obj[2]!=null?obj[2].toString():"-");
					cd.setMobileNo(obj[3]!=null?obj[3].toString():"-");
					cd.setPaidAmount(obj[4]!=null?Long.valueOf(obj[4].toString()):0l);
					finalMap.put(cd.getUserId(), cd);
					//finalList.add(cd);
				}
				
			}
			
			List<Long> userIds = new ArrayList<Long>();
			List<Object[]> userWiseDetails = tdpCadreDAO.getUserBetweenDates(fromDate, toDate);
			if(userWiseDetails != null && userWiseDetails.size() > 0)
			{
				for (Object[] obj : userWiseDetails)
				{
					userIds.add(Long.valueOf(obj[0].toString()));
					CadreAmountDetailsVO cd = finalMap.get(Long.valueOf(obj[0].toString()));
					
					if(cd == null)
					{
						cd = new CadreAmountDetailsVO();
						cd.setUserId(Long.valueOf(obj[0].toString()));
						cd.setUserName(obj[1]!=null?obj[1].toString():"-");
						cd.setName(obj[2]!=null?obj[2].toString():"-");
						cd.setMobileNo(obj[3]!=null?obj[3].toString():"-");
						cd.setPaidAmount(0l);
						finalMap.put(cd.getUserId(), cd);
					}
					
				}
				//SETTING CONSTITUENCY AND TOTAL COUNTS FOR USER
				List<Object[]> list2 = new ArrayList<Object[]>();
				if(userIds!=null && userIds.size()>0){
					 list2 = tdpCadreDAO.getCandidateDataCollected(fromDate, toDate, userIds);
					
				}
				if(list2!=null && list2.size()>0){
					for(Object[] obj:list2){
						//CadreAmountDetailsVO cd = getMatchedCadreCollector(finalList, Long.valueOf(obj[0].toString()));
						CadreAmountDetailsVO cd = finalMap.get( Long.valueOf(obj[0].toString()));
						if(cd != null)
						{
							cd.setTotalCount(obj[1]!=null?Long.valueOf(obj[1].toString()):0l);
							cd.setConstituencyId(Long.valueOf(obj[2].toString()));
							cd.setConstituency(obj[3].toString());
							cd.setTotalAmount(cd.getTotalCount()*100);
							cd.setDifference(cd.getTotalAmount()-cd.getPaidAmount());
							finalList.add(cd);
						}
						
						
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return finalList;
		
	}
	
	public List<CadreAmountDetailsVO> getCadreRegAmountDetails(String fromDt,String toDt,String reportValue){
		List<CadreAmountDetailsVO> finalList = null;
		
		try{
			LOG.debug("Entered Into getCadreRegAmountDetails()");
			
			if(reportValue.equalsIgnoreCase("CumilativeReport"))
			{
				finalList = getCadreRegAmountDetailsByCumilativeReport(fromDt,toDt);
			}
			else
			{
				finalList = getCadreRegAmountDetailsByDateWise(fromDt,toDt);
			}
			
		}catch (Exception e) {
			LOG.error("Exception Raised in getCadreRegAmountDetails()",e);
		}
		
		return finalList;
	}
	
	public CadreAmountDetailsVO getMatchedCadreCollector(List<CadreAmountDetailsVO> list,Long userId){
		if(list!=null && list.size()>0 && userId!=null){
			Boolean flag = true;
			for(CadreAmountDetailsVO cd:list)
			{
				if(cd.getUserId().equals(userId))
				{
					flag = false;
					return cd;
				}
			}
			if(flag)
			{
				CadreAmountDetailsVO cd = new CadreAmountDetailsVO();
				return cd;
			}
		}
		return null;
	}
}
