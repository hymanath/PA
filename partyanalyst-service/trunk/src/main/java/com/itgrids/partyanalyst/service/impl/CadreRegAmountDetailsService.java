package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.ICadreRegAmountDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreRegAmountFileDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserAssignDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dto.CadreAmountDetailsVO;
import com.itgrids.partyanalyst.dto.CadreRegAmountUploadVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.CadreRegAmountDetails;
import com.itgrids.partyanalyst.model.CadreRegAmountFile;
import com.itgrids.partyanalyst.model.CadreSurveyUser;
import com.itgrids.partyanalyst.service.ICadreRegAmountDetailsService;

public class CadreRegAmountDetailsService implements ICadreRegAmountDetailsService{

	private static final Logger LOG = Logger.getLogger(CadreRegAmountDetailsService.class);
	private IUserDAO userDAO;
	private ICadreRegAmountFileDAO cadreRegAmountFileDAO;
	private IVoterDAO voterDAO;
	private ICadreSurveyUserDAO cadreSurveyUserDAO;
	private ITdpCadreDAO tdpCadreDAO;
	private ICadreRegAmountDetailsDAO cadreRegAmountDetailsDAO;
	
	@Autowired
	private ICadreSurveyUserAssignDetailsDAO cadreSurveyUserAssignDetailsDAO;
	
	
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
					if(uploadVO.getUsername().contains("w"))
					{
						cadreRegAmountDetails.setCadreSurveyUserId(userDAO.getUserByUser(uploadVO.getUsername()).getUserId());
						cadreRegAmountDetails.setRegistrationType("WEB");
						
					}
					else  
					{
						List<CadreSurveyUser> list1 = cadreSurveyUserDAO.getCadreSurveyUserByUsername(uploadVO.getUsername());
						if(list1 != null && list1.size()>0){
							CadreSurveyUser cadreSurveyUser = list1.get(0);
							cadreRegAmountDetails.setCadreSurveyUserId(cadreSurveyUser.getCadreSurveyUserId());
							cadreRegAmountDetails.setRegistrationType("TAB");
						}
					
					}
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
			Map<Long, CadreAmountDetailsVO> webFinalMap = new HashMap<Long, CadreAmountDetailsVO>();
			Map<Long, CadreAmountDetailsVO> webUsersMap = null;
			Map<Long,String> constiMap = null;
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
							cd.setUserType("TAB");
							cd.setPaidAmount(obj[4]!=null?Long.valueOf(obj[4].toString()):0l);
							finalMap.put(cd.getUserId(), cd);
						}
						
					}
					
				}
				
				Set<Long> userIds = new java.util.HashSet<Long>();
				Set<Long> webUserIds = new java.util.HashSet<Long>();
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
								cd.setUserType("TAB");
								finalMap.put(cd.getUserId(), cd);
							}
							
							usersMap.put(Long.valueOf(obj[0].toString()), cd);
							
						}
					}
				}	
				
				List<Object[]> webAmtList = cadreRegAmountDetailsDAO.getAmountDetailsOfWebUserByDate(fromDate,toDate);
				if(webAmtList!=null && webAmtList.size()>0)
				{
					for(Object[] obj:webAmtList)
					{
						if(obj[5] != null)
						{
							webFinalMap = datesMap.get(obj[5].toString());
							if(webFinalMap == null)
							{
								webFinalMap = new HashMap<Long, CadreAmountDetailsVO>();
								datesMap.put(obj[5].toString(), finalMap);
							}
							CadreAmountDetailsVO cd = new CadreAmountDetailsVO();
							cd.setUserId(Long.valueOf(obj[0].toString()));
							cd.setUserName(obj[1]!=null?obj[1].toString():"-");
							cd.setName(obj[2]!=null?obj[2].toString():"-");
							cd.setMobileNo(obj[3]!=null?obj[3].toString():"-");
							cd.setUserType("WEB");
							cd.setPaidAmount(obj[4]!=null?Long.valueOf(obj[4].toString()):0l);
							webFinalMap.put(cd.getUserId(), cd);
						}
						
					}
					
				}
					
					List<Object[]> webUserWiseDetails =  tdpCadreDAO.getUserBetweenDatesForWeb(fromDate, toDate);
					if(webUserWiseDetails != null && webUserWiseDetails.size() > 0)
					{
						webUsersMap = new HashMap<Long, CadreAmountDetailsVO>();
						for(String dateStr : datesMap.keySet())
						{
							webFinalMap = datesMap.get(dateStr);
							for (Object[] obj : webUserWiseDetails)
							{
								webUserIds.add(Long.valueOf(obj[0].toString()));
								CadreAmountDetailsVO cd = webFinalMap.get(Long.valueOf(obj[0].toString()));
								
								if(cd == null)
								{
									cd = new CadreAmountDetailsVO();
									cd.setUserId(Long.valueOf(obj[0].toString()));
									cd.setUserName(obj[1]!=null?obj[1].toString():"-");
									cd.setName(obj[2]!=null?obj[2].toString():"-");
									cd.setMobileNo(obj[3]!=null?obj[3].toString():"-");
									cd.setUserType("WEB");
									webFinalMap.put(cd.getUserId(), cd);
								}
								
								webUsersMap.put(Long.valueOf(obj[0].toString()), cd);
								
							}
						}
					}
					
					
					if(userIds!=null && userIds.size()>0)
					{
						
							List<Object[]> constiDetails = cadreSurveyUserAssignDetailsDAO.getUserConstituencyDetails(new ArrayList<Long>(userIds));
							if(constiDetails != null)
							{
								constiMap = new HashMap<Long, String>();
								for (Object[] objects : constiDetails) 
								{
									if(objects[0] != null && objects[1] != null)
									{
										constiMap.put(Long.valueOf(objects[0].toString()),objects[1].toString());
									}
								}
							}
						
						
					}
					List<Object[]> list2 = tdpCadreDAO.getCandidateDataCollectedByDate(fromDate, toDate, new ArrayList<Long>(userIds));
					if(list2!=null && list2.size()>0)
					{
						for(Object[] obj:list2)
						{		
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
									
									CadreAmountDetailsVO cd1 = finalMap.get(Long.valueOf(obj[0].toString()));
									if(cd1 != null)
									{
										cd.setDifference(cd.getTotalAmount()-cd1.getPaidAmount());
									}
									else
									{
										cd.setDifference(cd.getTotalAmount()-0l);
									}
									finalMap.put(Long.valueOf(obj[0].toString()), cd);
								}
								else
								{
									cd = new CadreAmountDetailsVO();
									cd.setTotalCount(obj[1]!=null?Long.valueOf(obj[1].toString()):0l);
									cd.setConstituencyId(Long.valueOf(obj[2].toString()));
									cd.setConstituency(obj[3].toString());
									cd.setTotalAmount(cd.getTotalCount()*100);
									
									CadreAmountDetailsVO cd1 = finalMap.get(Long.valueOf(obj[0].toString()));
									if(cd1 != null)
									{
										cd.setDifference(cd.getTotalAmount()-cd1.getPaidAmount());
									}
									else
									{
										cd.setDifference(cd.getTotalAmount()-0l);
									}
									finalMap.put(Long.valueOf(obj[0].toString()), cd);
									
								}
							}
						}
					}
					
					List<Object[]> list3 = tdpCadreDAO.getCandidateDataCollectedByDateWeb(fromDate, toDate, new ArrayList<Long>(webUserIds));
					if(list3!=null && list3.size()>0)
					{
						for(Object[] obj:list3)
						{		
							if(obj[4].toString() != null)
							{
								webFinalMap = datesMap.get(obj[4].toString());
								if(webFinalMap == null)
								{
									webFinalMap = new HashMap<Long, CadreAmountDetailsVO>();
									datesMap.put(obj[4].toString(), webFinalMap);
								}
								CadreAmountDetailsVO cd = webFinalMap.get(Long.valueOf(obj[0].toString()));
								if(cd != null)
								{
									cd.setTotalCount(obj[1]!=null?Long.valueOf(obj[1].toString()):0l);
									cd.setConstituencyId(Long.valueOf(obj[2].toString()));
									cd.setConstituency(obj[3].toString());
									cd.setTotalAmount(cd.getTotalCount()*100);
									
									CadreAmountDetailsVO cd1 = webFinalMap.get(Long.valueOf(obj[0].toString()));
									if(cd1 != null)
									{
										cd.setDifference(cd.getTotalAmount()-cd1.getPaidAmount());
									}
									else
									{
										cd.setDifference(cd.getTotalAmount()-0l);
									}
									webFinalMap.put(Long.valueOf(obj[0].toString()), cd);
								}
								else
								{
									cd = new CadreAmountDetailsVO();
									cd.setTotalCount(obj[1]!=null?Long.valueOf(obj[1].toString()):0l);
									cd.setConstituencyId(Long.valueOf(obj[2].toString()));
									cd.setConstituency(obj[3].toString());
									cd.setTotalAmount(cd.getTotalCount()*100);
									
									CadreAmountDetailsVO cd1 = webFinalMap.get(Long.valueOf(obj[0].toString()));
									if(cd1 != null)
									{
										cd.setDifference(cd.getTotalAmount()-cd1.getPaidAmount());
									}
									else
									{
										cd.setDifference(cd.getTotalAmount()-0l);
									}
									webFinalMap.put(Long.valueOf(obj[0].toString()), cd);
									
								}
							}
						}
					}
					
					List<Object[]> list4 = tdpCadreDAO.getCandidateDataCollectedByDateWebParty(fromDate, toDate, 3930l);
					if(list4!=null && list4.size()>0)
					{
						for(Object[] obj:list4)
						{		
							if(obj[4].toString() != null)
							{
								webFinalMap = datesMap.get(obj[4].toString());
								if(webFinalMap == null)
								{
									webFinalMap = new HashMap<Long, CadreAmountDetailsVO>();
									datesMap.put(obj[4].toString(), webFinalMap);
								}
								CadreAmountDetailsVO cd = webFinalMap.get(Long.valueOf(obj[0].toString()));
								if(cd != null)
								{
									cd.setUserId(3930l);
									cd.setUserName("tdp_cadre");
									cd.setName("CADRE");
									cd.setMobileNo("9999999999");
									cd.setUserType("WEB-PARTY");
									cd.setTotalCount(obj[1]!=null?Long.valueOf(obj[1].toString()):0l);
									cd.setConstituencyId(Long.valueOf(obj[2].toString()));
									cd.setConstituency("STATE");
									cd.setTotalAmount(cd.getTotalCount()*100);
									CadreAmountDetailsVO cd1 = webFinalMap.get(Long.valueOf(obj[0].toString()));
									if(cd1 != null)
									{
										cd.setDifference(cd.getTotalAmount()-cd1.getPaidAmount());
									}
									else
									{
										cd.setDifference(cd.getTotalAmount()-0l);
									}
									webFinalMap.put(Long.valueOf(obj[0].toString()), cd);
								}
								else
								{
									cd = new CadreAmountDetailsVO();
									cd.setUserId(3930l);
									cd.setUserName("tdp_cadre");
									cd.setName("CADRE");
									cd.setMobileNo("9999999999");
									cd.setUserType("WEB-PARTY");
									cd.setTotalCount(obj[1]!=null?Long.valueOf(obj[1].toString()):0l);
									cd.setConstituencyId(Long.valueOf(obj[2].toString()));
									cd.setConstituency("STATE");
									cd.setTotalAmount(cd.getTotalCount()*100);
									CadreAmountDetailsVO cd1 = webFinalMap.get(Long.valueOf(obj[0].toString()));
									if(cd1 != null)
									{
										cd.setDifference(cd.getTotalAmount()-cd1.getPaidAmount());
									}
									else
									{
										cd.setDifference(cd.getTotalAmount()-0l);
									}
									webFinalMap.put(Long.valueOf(obj[0].toString()), cd);
								}
								
							}
						}
					}
					if(usersMap != null && usersMap.size() > 0)
					{
						finalList = new ArrayList<CadreAmountDetailsVO>();
						for (Long userId : usersMap.keySet())
						{
							if(constiMap.get(userId) != null)
							{
								CadreAmountDetailsVO cd = usersMap.get(userId);
								cd.setConstituency(constiMap.get(userId));
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
					
					
					List<Object[]> webUserConstiDetails = tdpCadreDAO.getWebUserConstituecny(new ArrayList<Long>(webUserIds));
					Map<Long,String> webConstiUserMap = new HashMap<Long, String>(); 
					if(webUserConstiDetails != null && webUserConstiDetails.size() > 0)
					{
						for (Object[] objects : webUserConstiDetails) 
						{
							webConstiUserMap.put(Long.valueOf(objects[0].toString()), objects[1].toString());
						}
					}
					if(webUsersMap != null && webUsersMap.size() > 0)
					{
						for (Long userId : webUsersMap.keySet())
						{
							
								if(webConstiUserMap.get(userId) != null)
								{
									CadreAmountDetailsVO cd = webUsersMap.get(userId);
									cd.setConstituency(webConstiUserMap.get(userId));
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
					for(CadreAmountDetailsVO finalVO : finalList)
					{
						if(finalVO != null)
						{
							CadreAmountDetailsVO VO = new CadreAmountDetailsVO();
							VO.setDate("Grand Total");
							for(CadreAmountDetailsVO subVO : finalVO.getInfoList())
							{
								VO.setTotalAmount(VO.getTotalAmount() + subVO.getTotalAmount());
								VO.setPaidAmount(VO.getPaidAmount() + subVO.getPaidAmount());
								VO.setTotalCount(VO.getTotalCount() + subVO.getTotalCount() );
								VO.setDifference(VO.getDifference() + subVO.getDifference());
							}
							finalVO.getInfoList().add(VO);
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
	
	/**
	 * This Service is used for getting all details for reconsication process for Cumilative report
	 * @param fromDt
	 * @param toDt
	 * @return finalList
	 */
	public List<CadreAmountDetailsVO> getCadreRegAmountDetailsByCumilativeReport(String fromDt,String toDt)
	{
		List<CadreAmountDetailsVO> finalList = new ArrayList<CadreAmountDetailsVO>();
		Map<Long, CadreAmountDetailsVO> finalMap = new HashMap<Long, CadreAmountDetailsVO>();
		Map<Long, CadreAmountDetailsVO> webAmtMap = new HashMap<Long, CadreAmountDetailsVO>();
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date fromDate = format.parse(fromDt);
			Date toDate = format.parse(toDt);
			
			List<Object[]> list1 = cadreRegAmountDetailsDAO.getAmountDetailsOfUser(fromDate,toDate);
			if(list1!=null && list1.size()>0)
			{
				for(Object[] obj:list1){
					CadreAmountDetailsVO cd = new CadreAmountDetailsVO();
					//userIds.add(Long.valueOf(obj[0].toString()));
					
					cd.setUserId(Long.valueOf(obj[0].toString()));
					cd.setUserName(obj[1]!=null?obj[1].toString():"-");
					cd.setName(obj[2]!=null?obj[2].toString():"-");
					cd.setMobileNo(obj[3]!=null?obj[3].toString():"-");
					cd.setPaidAmount(obj[4]!=null?Long.valueOf(obj[4].toString()):0l);
					cd.setUserType("TAB");
					finalMap.put(cd.getUserId(), cd);
					//finalList.add(cd);
				}
				
			}
			
			List<Object[]> webAmtList = cadreRegAmountDetailsDAO.getAmountDetailsOfWebUser(fromDate,toDate);
			if(webAmtList!=null && webAmtList.size()>0)
			{
				for(Object[] obj:webAmtList){
					CadreAmountDetailsVO cd = new CadreAmountDetailsVO();
					//userIds.add(Long.valueOf(obj[0].toString()));
					
					cd.setUserId(Long.valueOf(obj[0].toString()));
					cd.setUserName(obj[1]!=null?obj[1].toString():"-");
					cd.setName(obj[2]!=null?obj[2].toString():"-");
					cd.setMobileNo(obj[3]!=null?obj[3].toString():"-");
					cd.setPaidAmount(obj[4]!=null?Long.valueOf(obj[4].toString()):0l);
					cd.setUserType("WEB");
					webAmtMap.put(cd.getUserId(), cd);
					//finalList.add(cd);
				}
				
			}
			
			
			
			List<Long> userIds = new ArrayList<Long>();
			List<Long> webUserIds = new ArrayList<Long>();
			
			// HERE WE ARE GETTING ALL TAB USER BASED ON SELETED DATES AND PLACING ALL TAB USERS WITH THEIR DETAILS IN SINGLE MAP
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
						cd.setUserType("TAB");
						cd.setPaidAmount(0l);
						finalMap.put(cd.getUserId(), cd);
					}
					
				}
				
				// HERE WE ARE GETTING ALL WEB USER BASED ON SELETED DATES AND PLACING ALL TAB USERS WITH THEIR DETAILS IN SINGLE MAP
				List<Object[]> webUsers = tdpCadreDAO.getUserBetweenDatesForWeb(fromDate, toDate);
				if(webUsers != null && webUsers.size() > 0)
				{
					for (Object[] obj : webUsers)
					{
						webUserIds.add(Long.valueOf(obj[0].toString()));
						CadreAmountDetailsVO cd = webAmtMap.get(Long.valueOf(obj[0].toString()));
						
						if(cd == null)
						{
							cd = new CadreAmountDetailsVO();
							cd.setUserId(Long.valueOf(obj[0].toString()));
							cd.setUserName(obj[1]!=null?obj[1].toString():"-");
							cd.setName(obj[2]!=null?obj[2].toString():"-");
							cd.setMobileNo(obj[3]!=null?obj[3].toString():"-");
							cd.setUserType("WEB");
							cd.setPaidAmount(0l);
							webAmtMap.put(cd.getUserId(), cd);
						}
					}
					
				}
				
				// HERE WE ARE GETTING ALL TAB USER COLLECTED RECORDS AND CONSTITUENCY DETAILS 
				if(userIds!=null && userIds.size()>0)
				{
					List<Object[]> list2 = tdpCadreDAO.getCandidateDataCollected(fromDate, toDate, userIds);
					 if(list2!=null && list2.size()>0){
							for(Object[] obj:list2){
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
				
				// HERE WE ARE GETTING ALL WEB USER COLLECTED RECORDS AND CONSTITUENCY DETAILS 
				if(webUserIds != null && webUserIds.size() > 0)
				{
					List<Object[]> list2 = tdpCadreDAO.getCandidateDataCollectedWeb(fromDate, toDate, webUserIds);
					 if(list2!=null && list2.size()>0){
							for(Object[] obj:list2){
								CadreAmountDetailsVO cd = webAmtMap.get( Long.valueOf(obj[0].toString()));
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
				
				// HERE WE ARE GETTING ALL WEB USER COLLECTED RECORDS AND CONSTITUENCY DETAILS 
				if(webUserIds != null && webUserIds.size() > 0)
				{
					List<Object[]> list2 = tdpCadreDAO.getCandidateDataCollectedWebParty(fromDate, toDate, 3930l);
					 if(list2!=null && list2.size()>0){
							for(Object[] obj:list2){
								CadreAmountDetailsVO cd = new CadreAmountDetailsVO();
									cd.setUserId(3930l);
									cd.setUserName("tdp_cadre");
									cd.setName("Cadre");
									cd.setMobileNo("9999999999");
									cd.setUserType("WEB-PARTY");
									cd.setTotalCount(obj[1]!=null?Long.valueOf(obj[1].toString()):0l);
									//cd.setConstituencyId(Long.valueOf(obj[2].toString()));
									cd.setConstituency("STATE");
									cd.setTotalAmount(cd.getTotalCount()*100);
									cd.setDifference(cd.getTotalAmount()-cd.getPaidAmount());
									finalList.add(cd);
							}
						}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Raised in getCadreRegAmountDetailsByCumilativeReport()",e);
		}
		
		
		return finalList;
		
	}
	
	public List<CadreAmountDetailsVO> getCadreRegAmountDetails(String fromDt,String toDt,String reportValue){
		List<CadreAmountDetailsVO> finalList = new ArrayList<CadreAmountDetailsVO>();
		
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
	
	public List<CadreAmountDetailsVO> getCadreSummaryAmountDayWise(String sourceType){
		LOG.debug(" Entered into getCadreSummaryAmountDayWise()");
		List<CadreAmountDetailsVO> finalList = new ArrayList<CadreAmountDetailsVO>();
		try{
			
			
			List<String> sourcesList = new ArrayList<String>();
			if(sourceType.equalsIgnoreCase("ALL")){
				sourcesList.add("TAB");
				sourcesList.add("WEB");
			}else if(sourceType.equalsIgnoreCase("TAB")){
				sourcesList.add("TAB");
			}else if(sourceType.equalsIgnoreCase("WEB")){
				sourcesList.add("WEB");
			}
			
			List<Object[]> list = tdpCadreDAO.getTotalRecordsDayWise(sourcesList);
			if(list!=null && list.size()>0){
				for(Object[] obj:list){
					CadreAmountDetailsVO cd = new CadreAmountDetailsVO();
					cd.setDate(obj[1].toString());
					cd.setTotalCount(Long.valueOf(obj[0].toString()));
					cd.setTotalAmount(cd.getTotalCount()*100);
					cd.setPaidAmount(0l);
					cd.setDifference(0l);
					finalList.add(cd);
				}
			}
			
			List<Object[]> list1 = cadreRegAmountDetailsDAO.getAmountDetailsDateWise(sourceType);
			if(list1!=null && list1.size()>0){
				for(Object[] obj:list1){
					CadreAmountDetailsVO cd = getMatchedDate(finalList,obj[1].toString());
					if(cd!=null){
						cd.setPaidAmount(Long.valueOf(obj[0].toString()));
					}
				}
			}
			
			if(finalList.size()>0){
				Long ttlRec = 0l;
				Long ttlAmt = 0l;
				Long ttlPaid = 0l;
				Long ttlDiff = 0l;
				
				for(int i=0;i<finalList.size();i++){
					if(i==0){
						CadreAmountDetailsVO cd0 = finalList.get(0);
						ttlRec =cd0.getTotalCount();
						ttlPaid = cd0.getPaidAmount();
						cd0.setDifference(cd0.getTotalAmount());
					}
					if(i>0){
						
						CadreAmountDetailsVO cd1 = finalList.get(i);
						CadreAmountDetailsVO cd0 = finalList.get(i-1);
						
						ttlRec = ttlRec + cd1.getTotalCount();
						ttlPaid = ttlPaid + cd1.getPaidAmount();
						
						
						cd1.setDifference((cd1.getTotalAmount()+cd0.getDifference())-cd1.getPaidAmount());
					}
					
				}
				
				ttlAmt = ttlRec * 100;
				ttlDiff = ttlAmt - ttlPaid;
				
				CadreAmountDetailsVO cd = new CadreAmountDetailsVO();
				cd.setTotalAmount(ttlAmt);
				cd.setTotalCount(ttlRec);
				cd.setPaidAmount(ttlPaid);
				cd.setTotalDifference(ttlDiff);
				
				//finalList.add(cd);
			}
			
			
		}catch (Exception e) {
			LOG.error("Exception Raised in getCadreSummaryAmountDayWise()"+e); 
		}
		
		return finalList;
	}
	
	public CadreAmountDetailsVO getMatchedDate(List<CadreAmountDetailsVO> list,String date){
		if(list!=null && list.size()>0){
			for(CadreAmountDetailsVO cd:list){
				if(cd.getDate().equalsIgnoreCase(date)){
					return cd;
				}
			}
		}
		return null;
	}
	
	
}
