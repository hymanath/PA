package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ISurveyCallStatusDAO;
import com.itgrids.partyanalyst.dao.ISurveyConstituencyDAO;
import com.itgrids.partyanalyst.dao.ISurveyConstituencyTempDAO;
import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.dao.ISurveyFinalDataDAO;
import com.itgrids.partyanalyst.dto.BigPictureVO;
import com.itgrids.partyanalyst.dto.BoothWiseSurveyStatusDetailsVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.SurveyDashBoardVO;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;
import com.itgrids.partyanalyst.service.ICtpDashBoardService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CtpDashBoardService implements ICtpDashBoardService
{

	private static final Logger LOG = Logger.getLogger(CtpDashBoardService.class);
	@Autowired
	private ISurveyDetailsInfoDAO surveyDetailsInfoDAO;
	
	@Autowired
	private ISurveyCallStatusDAO surveyCallStatusDAO;
	
	@Autowired
	private ISurveyFinalDataDAO surveyFinalDataDAO;
	
	@Autowired
	private IBoothPublicationVoterDAO  boothPublicationVoterDAO;
	
	@Autowired
	private ISurveyConstituencyDAO surveyConstituencyDAO;
	
	@Autowired
	private ISurveyConstituencyTempDAO surveyConstituencyTempDAO;
	@Autowired
	private IConstituencyDAO constituencyDAO;
	
	
	@Autowired
	private IBoothDAO boothDAO;
	
	/**
	 * @author Prasad Thiragabathina
	 * This Serivice is used for setting over all big picture on CTP project
	 * @return returnVO
	 */
	public BigPictureVO getBigPictureDetails(Long stateId)
	{
		BigPictureVO returnVO = new BigPictureVO();
		try
		{
			List<Object[]> dcAndQcDetails = surveyDetailsInfoDAO.getTotalVotersAndBoothsAndConstituencyes(stateId);
			if(dcAndQcDetails != null && dcAndQcDetails.size() > 0)
			{
				for (Object[] objects : dcAndQcDetails)
				{
					if(objects[0] != null )
					{
						if((Long)objects[0] == 1l)
						{
							returnVO.setDcVotersCount(objects[1] != null ? Integer.valueOf(objects[1].toString()) : 0);
							returnVO.setDcBoothsCount(objects[3] != null ? Integer.valueOf(objects[3].toString()) : 0);
							returnVO.setDcConstituencysCount(objects[2] != null ? Integer.valueOf(objects[2].toString()) : 0);
						}
						else
						{
							returnVO.setQcVotersCount(objects[1] != null ? Integer.valueOf(objects[1].toString()) : 0);
							returnVO.setQcBoothsCount(objects[3] != null ? Integer.valueOf(objects[3].toString()) : 0);
							returnVO.setQcConstituencyesCount(objects[2] != null ? Integer.valueOf(objects[2].toString()) : 0);
						}
					}
				}
			}
			
			List<Object[]> verifierDetails = surveyCallStatusDAO.getVerifiesCountDetails(stateId);
			if(verifierDetails != null && verifierDetails.size() > 0)
			{
				for (Object[] objects : verifierDetails)
				{
					returnVO.setVerifierVotersCount( Integer.valueOf(objects[0].toString()));
					returnVO.setVerifierBoothsCount( Integer.valueOf(objects[1].toString()));
					returnVO.setVerifierConstituencyCount(Integer.valueOf(objects[2].toString()));
				}
			}
			
			List<Object[]> totalCountsList = surveyConstituencyTempDAO.getTotalVoters(stateId);
			if(totalCountsList != null && totalCountsList.size() > 0)
			{
				for (Object[] objects : totalCountsList) 
				{
					returnVO.setTotalVoters(Integer.valueOf(objects[0].toString()));
					returnVO.setTotalBooths(Integer.valueOf(objects[1].toString()));
					returnVO.setTotalConstituencyes(Integer.valueOf(objects[2].toString()));
				}
				
				
				if(returnVO.getTotalVoters() != null && returnVO.getTotalVoters() > 0)
				{
					if(returnVO.getDcVotersCount() != null  && returnVO.getDcVotersCount() > 0)
					{
						returnVO.setDcPercentage(new BigDecimal(returnVO.getDcVotersCount()*(100.0)/returnVO.getTotalVoters()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}
					if(returnVO.getQcVotersCount() != null && returnVO.getQcVotersCount() > 0)
					{
						returnVO.setQcPercentage(new BigDecimal(returnVO.getQcVotersCount()*(100.0)/returnVO.getTotalVoters()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}
					if(returnVO.getVerifierVotersCount() != null && returnVO.getVerifierVotersCount() > 0)
					{
						returnVO.setVerifierPercentage(new BigDecimal(returnVO.getVerifierVotersCount()*(100.0)/returnVO.getTotalVoters()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}
					
				}
			}
			
			
			
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getBigPictureDetails", e);
		}
		return returnVO;
	}
	
	/**
	 * This Service is used for providing internal Verification Summary details
	 * @return returnVO
	 */
	public BigPictureVO getInternalVerificationSummary(Long stateId,String fromDate,String toDate)
	{
		BigPictureVO returnVO = new BigPictureVO();
		try
		{
			List<Object[]> verifierDetails = null;
			if(fromDate.toString().equalsIgnoreCase("null") && toDate.toString().equalsIgnoreCase("null"))
			{
				verifierDetails = surveyCallStatusDAO.getVerifierCounts(stateId,null,null);
			}
			else
			{
				SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
				verifierDetails = surveyCallStatusDAO.getVerifierCounts(stateId,originalFormat.parse(fromDate),originalFormat.parse(toDate));
			}
			if(verifierDetails != null && verifierDetails.size() > 0)
			{
				for (Object[] objects : verifierDetails)
				{
					if(objects[0] != null )
					{
						if(objects[0].toString().equalsIgnoreCase("Y"))
						{
							returnVO.setCorrectDetails(returnVO.getVerifierVotersCount() + Integer.valueOf(objects[1].toString()));
							
						}
						else if (objects[0].toString().equalsIgnoreCase("N"))
						{
							returnVO.setWrongDetails(returnVO.getVerifierVotersCount() + Integer.valueOf(objects[1].toString()));
						}
					}
					
			}
			if(returnVO.getCorrectDetails() != null && returnVO.getCorrectDetails() > 0  && returnVO.getWrongDetails() != null && returnVO.getWrongDetails() > 0)
			{
				returnVO.setVerifierVotersCount(returnVO.getCorrectDetails() + returnVO.getWrongDetails())	;
				if(returnVO.getVerifierVotersCount() != null && returnVO.getVerifierVotersCount() > 0)
				{
					if(returnVO.getCorrectDetails() != null && returnVO.getCorrectDetails() > 0)
					{
						returnVO.setCorrectPerc(new BigDecimal(returnVO.getCorrectDetails()*(100.0)/returnVO.getVerifierVotersCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}
					if(returnVO.getWrongDetails() != null && returnVO.getWrongDetails() > 0)
					{
						returnVO.setWrongPerc(new BigDecimal(returnVO.getWrongDetails()*(100.0)/returnVO.getVerifierVotersCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}
					
				}
				getRedoBooths(stateId,returnVO,fromDate,toDate);
			}
			
			
			}
		}
		catch (Exception e) 
		{
			LOG.error("Exception raised in getInternalVerificationSummary", e);
		}
		return returnVO;
	}
	
	/**
	 * This Service is used for getting redo booths
	 * @param returnVO
	 */
	public void getRedoBooths(Long stateId , BigPictureVO returnVO,String fromDate , String toDate)
	{
		try
		{
			List<Long> redoBooths = null;
			if(fromDate.toString().equalsIgnoreCase("null") && toDate.toString().equalsIgnoreCase("null") )
			{
				redoBooths = surveyDetailsInfoDAO.getsurveyUserCollectedBooths(stateId,4l,null,null);
			}
			else
			{
				SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
				 redoBooths = surveyDetailsInfoDAO.getsurveyUserCollectedBooths(stateId,4l,originalFormat.parse(fromDate),originalFormat.parse(toDate));
			}
			
			if(redoBooths != null && redoBooths.size() > 0)
			{
				returnVO.setRedoBooths(redoBooths.size());
				Long total = getRedoTotalVoters(redoBooths,returnVO);
				if(total != null && total > 0)
				{
					returnVO.setRedoVoters(Integer.valueOf(total.toString()));
				}
				
			}
		} 
		catch (Exception e) 
		{
			LOG.error("Exception raised in getRedoBooths", e);
		}
	}
	
	/**
	 * This Service is used for getting redo booths total voters
	 * @param redoBooths
	 * @param returnVO
	 * @return total
	 */
	public Long getRedoTotalVoters(List<Long> redoBooths,BigPictureVO returnVO )
	{
		Long total = 0l;
		try
		{
			returnVO.setRedoBooths(redoBooths.size());
			 int fromIndex = 0;
			 int toIndex = 1000;
			 
			 if(redoBooths.size() > 1000)
			  {
				  while(fromIndex <= toIndex)
				  {
					Long count = boothPublicationVoterDAO.getTotalVoterByBooths(redoBooths.subList(fromIndex, toIndex));
					 if(count != null)
					 {
						 total = total +  count;
					 }
					 fromIndex += 1000;
					  toIndex += 1000;
					  if(toIndex >= redoBooths.size())
						toIndex = redoBooths.size();
				  }
			  }
			  else
			  {
				  total = boothPublicationVoterDAO.getTotalVoterByBooths(redoBooths);
			  }
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getRedoTotalVoters", e);
		}
		return total;
	}
	
	/**
	 * This Service is used for providing QC Verification Summary Details
	 * @return returnVO
	 */
	public BigPictureVO getQcVerificationSummaryReport(Long stateId,String fromDate , String toDate)
	{
		BigPictureVO returnVO = new BigPictureVO();
		try
		{
			if(fromDate.toString().equalsIgnoreCase("null") && toDate.toString().equalsIgnoreCase("null"))
			{
				returnVO.setQcVotersCount(surveyDetailsInfoDAO.getSurveyUserCollectedVoters(stateId,10l,null,null).intValue());
				List<Long> matchedIds = new ArrayList<Long>();
				matchedIds.add(1l);
				returnVO.setMatchedCount(surveyFinalDataDAO.getQcCollectedMatchedUnMatchedDetails(stateId,matchedIds,null,null).intValue());
				List<Long> unMatchedIds = new ArrayList<Long>();
				unMatchedIds.add(2l);
				unMatchedIds.add(3l);
				unMatchedIds.add(4l);
				returnVO.setUnMatchedCount(surveyFinalDataDAO.getQcCollectedMatchedUnMatchedDetails(stateId,unMatchedIds,null,null).intValue());
			}
			else
			{
				SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
				returnVO.setQcVotersCount(surveyDetailsInfoDAO.getSurveyUserCollectedVoters(stateId,10l,originalFormat.parse(fromDate),originalFormat.parse(toDate)).intValue());
				List<Long> matchedIds = new ArrayList<Long>();
				matchedIds.add(1l);
				returnVO.setMatchedCount(surveyFinalDataDAO.getQcCollectedMatchedUnMatchedDetails(stateId,matchedIds,originalFormat.parse(fromDate),originalFormat.parse(toDate)).intValue());
				List<Long> unMatchedIds = new ArrayList<Long>();
				unMatchedIds.add(2l);
				unMatchedIds.add(3l);
				unMatchedIds.add(4l);
				returnVO.setUnMatchedCount(surveyFinalDataDAO.getQcCollectedMatchedUnMatchedDetails(stateId,unMatchedIds,originalFormat.parse(fromDate),originalFormat.parse(toDate)).intValue());
			}
			if(returnVO.getQcVotersCount() != null && returnVO.getQcVotersCount() > 0)
			{
				if(returnVO.getMatchedCount() != null && returnVO.getMatchedCount() > 0)
				{
					returnVO.setCorrectPerc(new BigDecimal(returnVO.getMatchedCount()*(100.0)/returnVO.getQcVotersCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
				if(returnVO.getUnMatchedCount() != null && returnVO.getUnMatchedCount() > 0)
				{
					returnVO.setWrongPerc(new BigDecimal(returnVO.getUnMatchedCount()*(100.0)/returnVO.getQcVotersCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
			}
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getQcVerificationSummaryReport", e);
		}
		return returnVO;
	}
	
	/**
	 * This Service is used for getting team details 
	 * @return returnVO
	 */
	public BigPictureVO getTodayTeamDetails(Long stateId,String fromdate,String todate)
	{
		BigPictureVO returnVO = new BigPictureVO();
		try 
		{
			List<Object[]> teamDetailsObj = null;
			if(fromdate.toString().equalsIgnoreCase("null") && todate.toString().equalsIgnoreCase("null"))
			{
				DateUtilService dateUtilService = new DateUtilService();
				Date todateDate = dateUtilService.getCurrentDateAndTime();
				 teamDetailsObj = surveyDetailsInfoDAO.getTodayTeamDetails(stateId,todateDate,null,null);
			}
			else
			{
				SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
				teamDetailsObj = surveyDetailsInfoDAO.getTodayTeamDetails(stateId,null,originalFormat.parse(fromdate),originalFormat.parse(todate));
			}
			
			if(teamDetailsObj != null && teamDetailsObj.size() > 0)
			{
				for (Object[] objects : teamDetailsObj) 
				{
					if((Long)objects[0] == 1l)
					{
						returnVO.setDcVotersCount(Integer.valueOf(objects[1].toString()));
					}
					else if((Long)objects[0] == 4l)
					{
						returnVO.setVerifierVotersCount(Integer.valueOf(objects[1].toString()));
					}
					else
					{
						returnVO.setQcVotersCount(Integer.valueOf(objects[1].toString()));
					}
				}
			}
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getTodayTeamDetails", e);
		}
		return returnVO;
	}
	
	/**
	 * This Service is used for getting constituency wise team details
	 * @return returnList
	 */
	public List<BigPictureVO> getConstituencyWiseTeamDetails(Long stateId , Long surveyUserType,String fromdate , String toDate)
	{
		List<BigPictureVO> returnList = null;
		
		try 
		{
			List<Object[]> teamDetailsObj = null;
			if(fromdate.toString().equalsIgnoreCase("null") && toDate.toString().equalsIgnoreCase("null"))
			{
				DateUtilService dateUtilService = new DateUtilService();
				Date todateDate = dateUtilService.getCurrentDateAndTime();
				teamDetailsObj = surveyDetailsInfoDAO.getTeamDetailsInConstituencyLevel(stateId,todateDate,surveyUserType,null,null);
			}
			else
			{
				SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
				teamDetailsObj = surveyDetailsInfoDAO.getTeamDetailsInConstituencyLevel(stateId,null,surveyUserType,originalFormat.parse(fromdate),originalFormat.parse(toDate));
			}
			
			if(teamDetailsObj != null && teamDetailsObj.size() > 0)
			{
				returnList = new ArrayList<BigPictureVO>();
				for (Object[] objects : teamDetailsObj) 
				{
					BigPictureVO bigPictureVO = new BigPictureVO();
					bigPictureVO.setDcVotersCount(objects[0] != null ? Integer.valueOf(objects[0].toString()) : 0);// surveyUserType
					bigPictureVO.setQcVotersCount(objects[1] != null ?  Integer.valueOf(objects[1].toString()) : 0);//constituencyId
					bigPictureVO.setDcPercentage(objects[2] != null ?  objects[2].toString() : "");//constituency
					bigPictureVO.setDcBoothsCount(objects[3] != null ?  Integer.valueOf(objects[3].toString()): 0);// total booths
					bigPictureVO.setDcConstituencysCount(objects[4] != null ?  Integer.valueOf(objects[4].toString()) : 0);//total survey users
					returnList.add(bigPictureVO);
				}
			}
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in getConstituencyWiseTeamDetails", e);
		}
		return returnList;
	}
	
	/** 
	 * This Service is used for getting booth wise survey user details
	 * @param constituencyId
	 * @param surveyUserTypeId
	 * @return returnList
	 */
	public List<BigPictureVO> getBoothWiseTeamDetails(Long stateId , Long constituencyId , Long surveyUserTypeId,String fromDate , String toDate)
	{
		List<BigPictureVO> returnList = null;
		try 
		{
			List<Object[]> teamDetails = null;
			if(fromDate.toString().equalsIgnoreCase("null") && toDate.toString().equalsIgnoreCase("null"))
			{
				DateUtilService dateUtilService = new DateUtilService();
				Date todateDate = dateUtilService.getCurrentDateAndTime();
				teamDetails = surveyDetailsInfoDAO.getTeamDetailsInBoothLevel(stateId,constituencyId, surveyUserTypeId, todateDate,null,null);
			}
			else
			{
				SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
				teamDetails = surveyDetailsInfoDAO.getTeamDetailsInBoothLevel(stateId,constituencyId, surveyUserTypeId, null,originalFormat.parse(fromDate),originalFormat.parse(toDate));
			}
			if(teamDetails != null && teamDetails.size() > 0)
			{
				returnList = new ArrayList<BigPictureVO>();
				for (Object[] objects : teamDetails)
				{
					BigPictureVO bigPictureVO = new BigPictureVO();
					bigPictureVO.setDcVotersCount(objects[0] != null ?  Integer.valueOf(objects[0].toString()) : 0);// booth Id
					bigPictureVO.setVerifierVotersCount(objects[2] != null ?  Integer.valueOf(objects[2].toString()) : 0);//survey userid
					bigPictureVO.setDcPercentage(objects[1] != null ? objects[1].toString() : "");//partNo
					bigPictureVO.setQcPercentage(objects[3] != null ? objects[3].toString() : "");//user name
					bigPictureVO.setVerifierPercentage(objects[4] != null ? objects[4].toString() : "");// mobile no
					bigPictureVO.setQcVotersCount(objects[5] != null ?  Integer.valueOf(objects[5].toString()) : 0);//total Voters
					returnList.add(bigPictureVO);
				}
			}
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in getBoothWiseTeamDetails", e);
		}
		return returnList;
	}
	
	/**
	 * This Service is used for getting constituency wise Qc Summary
	 * @return returnList
	 */
	public List<BigPictureVO> getConstituencyWiseQcVerificationSummary(Long stateId , String type,String fromDate , String toDate)
	{
		List<BigPictureVO> returnList = null;
		try
		{
			List<Object[]> constituencyWiseDetils = null;
			if(type.equalsIgnoreCase("null"))
			{
				if(fromDate.toString().equalsIgnoreCase("null") && toDate.toString().equalsIgnoreCase("null"))
				{
					constituencyWiseDetils = surveyDetailsInfoDAO.getConstituecySummaryForQc(stateId,null,null);
				}
				else
				{
					SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					constituencyWiseDetils = surveyDetailsInfoDAO.getConstituecySummaryForQc(stateId,originalFormat.parse(fromDate),originalFormat.parse(toDate));
				}
				
			}
			else
			{
				List<Long> statusIds = new ArrayList<Long>();
				if(type.equalsIgnoreCase("Y"))
				{
					statusIds.add(1l);
					if(fromDate.toString().equalsIgnoreCase("null") && toDate.toString().equalsIgnoreCase("null"))
					{
						constituencyWiseDetils = surveyFinalDataDAO.getQcDataForSelection(stateId,statusIds,null,null);
					}
					else
					{
						SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
						constituencyWiseDetils = surveyFinalDataDAO.getQcDataForSelection(stateId,statusIds,originalFormat.parse(fromDate),originalFormat.parse(toDate));
					}
					
				}
				else
				{
					statusIds.add(2l);
					statusIds.add(3l);
					if(fromDate.toString().equalsIgnoreCase("null") && toDate.toString().equalsIgnoreCase("null"))
					{
						constituencyWiseDetils =surveyFinalDataDAO.getQcDataForSelection(stateId,statusIds,null,null);
					}
					else
					{
						SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
						constituencyWiseDetils = surveyFinalDataDAO.getQcDataForSelection(stateId,statusIds,originalFormat.parse(fromDate),originalFormat.parse(toDate));
					}
					
				}
				
			}
			
			if(constituencyWiseDetils != null && constituencyWiseDetils.size() > 0)
			{
				returnList = new ArrayList<BigPictureVO>();
				for (Object[] objects : constituencyWiseDetils)
				{
					BigPictureVO bigPictureVO = new BigPictureVO();
					bigPictureVO.setDcVotersCount(objects[0] != null ?  Integer.valueOf(objects[0].toString()) : 0);// constituency Id
					bigPictureVO.setQcVotersCount(objects[2] != null ?  Integer.valueOf(objects[2].toString()) : 0);//booths count
					bigPictureVO.setDcPercentage(objects[1] != null ?objects[1].toString() : "");// constituency Name
					bigPictureVO.setVerifierVotersCount(objects[3] != null ?  Integer.valueOf(objects[3].toString()) : 0); // voters count
					returnList.add(bigPictureVO);
				}
			}
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getConstituencyWiseQcVerificationSummary", e);
		}
		return returnList;
	}
	
	/**
	 * This Service is used for providing booth wise qc verification report
	 * @param constituencyId
	 * @return returnList
	 */
	public List<BigPictureVO> getBoothWiseQcVerificationSummary(Long stateId , Long constituencyId,String type,String fromDate , String toDate)
	{
		List<BigPictureVO> returnList = null;
		try 
		{
			List<Object[]> boothWiseQcDetails = null;
			if(type.equalsIgnoreCase("null"))
			{
				if(fromDate.toString().equalsIgnoreCase("null") && toDate.toString().equalsIgnoreCase("null"))
				{
					boothWiseQcDetails = surveyDetailsInfoDAO.getBoothWiseSummaryForQc(stateId,constituencyId,null,null);
				}
				else
				{
					SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					boothWiseQcDetails = surveyDetailsInfoDAO.getBoothWiseSummaryForQc(stateId,constituencyId,originalFormat.parse(fromDate),originalFormat.parse(toDate));
				}
				
			}
			else
			{
				List<Long> statusIds = new ArrayList<Long>();
				if(type.equalsIgnoreCase("Y"))
				{
					statusIds.add(1l);
					if(fromDate.toString().equalsIgnoreCase("null") && toDate.toString().equalsIgnoreCase("null"))
					{
						boothWiseQcDetails = surveyFinalDataDAO.getBoothWiseQcData(stateId,constituencyId,statusIds,null,null);
					}
					else
					{
						SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
						boothWiseQcDetails = surveyFinalDataDAO.getBoothWiseQcData(stateId,constituencyId,statusIds,originalFormat.parse(fromDate),originalFormat.parse(toDate));
					}
					
				}
				else
				{
					statusIds.add(2l);
					statusIds.add(3l);
					if(fromDate.toString().equalsIgnoreCase("null") && toDate.toString().equalsIgnoreCase("null"))
					{
						boothWiseQcDetails = surveyFinalDataDAO.getBoothWiseQcData(stateId,constituencyId,statusIds,null,null);
					}
					else
					{
						SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
						boothWiseQcDetails = surveyFinalDataDAO.getBoothWiseQcData(stateId,constituencyId,statusIds,originalFormat.parse(fromDate),originalFormat.parse(toDate));
					}
					
				}
				
			}
			
			if(boothWiseQcDetails != null && boothWiseQcDetails.size() > 0)
			{
				returnList = new ArrayList<BigPictureVO>();
				for (Object[] objects : boothWiseQcDetails)
				{
					BigPictureVO bigPictureVO = new BigPictureVO();
					bigPictureVO.setDcVotersCount(objects[0] != null ? Integer.valueOf(objects[0].toString()) : 0);// booth id
					bigPictureVO.setQcVotersCount(objects[2] != null ? Integer.valueOf(objects[2].toString()) : 0);// survey user id
					bigPictureVO.setDcPercentage(objects[1] != null ?objects[1].toString() : "");// part no
					bigPictureVO.setVerifierPercentage(objects[3] != null ?objects[3].toString() : "");// survey user name
					bigPictureVO.setQcPercentage(objects[4] != null ? objects[4].toString() : ""); // MObile Number
					bigPictureVO.setVerifierVotersCount(objects[5] != null ?  Integer.valueOf(objects[5].toString()) : 0); // voters count
					returnList.add(bigPictureVO);
				}
			}
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in getBoothWiseQcVerificationSummary", e);
		}
		return returnList;
	}
	
	/**
	 * This Service is used for getting all team collected details
	 * @return returnList
	 */
	public BigPictureVO getTodayTeamCollectedDetails(Long stateId,String fromDate , String toDate)
	{
		BigPictureVO returnVO = new BigPictureVO();
		try 
		{
			List<Object[]> teamCollectedDetails = null;
			if(fromDate.toString().equalsIgnoreCase("null") && toDate.toString().equalsIgnoreCase("null"))
			{
				DateUtilService dateUtilService = new DateUtilService();
				Date todateDate = dateUtilService.getCurrentDateAndTime();
				teamCollectedDetails = surveyDetailsInfoDAO.getTodayTeamCollectedDetails(stateId,todateDate,null,null);
			}
			else
			{
				SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
				teamCollectedDetails = surveyDetailsInfoDAO.getTodayTeamCollectedDetails(stateId,null,originalFormat.parse(fromDate),originalFormat.parse(toDate));
			}
			if(teamCollectedDetails != null && teamCollectedDetails.size() > 0)
			{
				for (Object[] objects : teamCollectedDetails)
				{
					if(objects[0] != null)
					{
						if((Long)objects[0] == 1)
						{
							returnVO.setDcVotersCount(Integer.valueOf(objects[1].toString()));
						}
						else if((Long)objects[0] == 4)
						{
							returnVO.setVerifierVotersCount(Integer.valueOf(objects[1].toString()));
						}
						else
						{
							returnVO.setQcVotersCount(Integer.valueOf(objects[1].toString()));
						}
					}
				}
			}
		} 
		catch (Exception e) 
		{
			LOG.error("Exception raised in getTodayTeamCollectedDetails", e);
		}
		return returnVO;
	}
	
	/**
	 * This Service is used for getting constituency wise team collected details summary
	 * @param type
	 * @return returnList
	 */
	public List<BigPictureVO> getConstituencyWiseTeamCollectedSummary(Long stateId , Long type,String fromdate,String toDate)
	{
		List<BigPictureVO> returnList = null;
		try 
		{
			List<Object[]> constituencyWiseDetails = null;
			if(fromdate.toString().equalsIgnoreCase("null") && toDate.toString().equalsIgnoreCase("null")  )
			{
				DateUtilService dateUtilService = new DateUtilService();
				Date todateDate = dateUtilService.getCurrentDateAndTime();
				constituencyWiseDetails = surveyDetailsInfoDAO.getConstituencyWiseTeamCollectedDetails(stateId,type, todateDate,null,null);
			}
			else
			{
				SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
				constituencyWiseDetails = surveyDetailsInfoDAO.getConstituencyWiseTeamCollectedDetails(stateId,type, null,originalFormat.parse(fromdate),originalFormat.parse(toDate));
			}
			
			if(constituencyWiseDetails != null && constituencyWiseDetails.size() > 0)
			{
				returnList = new ArrayList<BigPictureVO>();
				for (Object[] objects : constituencyWiseDetails)
				{
					BigPictureVO bigPictureVO = new BigPictureVO();
					bigPictureVO.setDcVotersCount(objects[0] != null ?  Integer.valueOf(objects[0].toString()) : 0);// constituency Id
					bigPictureVO.setQcVotersCount(objects[2] != null ?  Integer.valueOf(objects[2].toString()) : 0);//booths count
					bigPictureVO.setDcPercentage(objects[1] != null ?objects[1].toString() : "");// constituency Name
					bigPictureVO.setVerifierVotersCount(objects[3] != null ?  Integer.valueOf(objects[3].toString()) : 0); // voters count
					returnList.add(bigPictureVO);
				}
			}
		}
		catch (Exception e) 
		{
			LOG.error("Exception raised in getConstituencyWiseTeamCollectedSummary", e);
		}
		return returnList;
	}
	
	public List<BigPictureVO> getBoothWiseTeamCollectedDetailsSummary(Long stateId ,Long constituencyId , Long surveyUserTypeId,String fromDate, String toDate)
	{
		List<BigPictureVO> returnList = null;
		try 
		{
			List<Object[]> boothWiseDetails = null;
			if(fromDate.toString().equalsIgnoreCase("null") && toDate.toString().equalsIgnoreCase("null"))
			{
				DateUtilService dateUtilService = new DateUtilService();
				Date todateDate = dateUtilService.getCurrentDateAndTime();
				boothWiseDetails = surveyDetailsInfoDAO.getBoothWiseTeamCollectedDetails(stateId,surveyUserTypeId, todateDate, constituencyId,null,null);
			}
			else
			{
				SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
				boothWiseDetails = surveyDetailsInfoDAO.getBoothWiseTeamCollectedDetails(stateId,surveyUserTypeId, null, constituencyId,originalFormat.parse(fromDate),originalFormat.parse(toDate));
			}
			if(boothWiseDetails != null && boothWiseDetails.size() > 0)
			{
				returnList = new ArrayList<BigPictureVO>();
				for (Object[] objects : boothWiseDetails) 
				{
					BigPictureVO bigPictureVO = new BigPictureVO();
					bigPictureVO.setDcVotersCount(objects[0] != null ? Integer.valueOf(objects[0].toString()) : 0);// booth id
					bigPictureVO.setQcVotersCount(objects[2] != null ? Integer.valueOf(objects[2].toString()) : 0);// survey user id
					bigPictureVO.setDcPercentage(objects[1] != null ?objects[1].toString() : "");// part no
					bigPictureVO.setVerifierPercentage(objects[3] != null ?objects[3].toString() : "");// survey user name
					bigPictureVO.setQcPercentage(objects[4] != null ? objects[4].toString() : ""); // MObile Number
					bigPictureVO.setVerifierVotersCount(objects[5] != null ?  Integer.valueOf(objects[5].toString()) : 0); // voters count
					returnList.add(bigPictureVO);
				}
			}
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in getBoothWiseTeamCollectedDetailsSummary", e);
		}
		return returnList;
	}
	public List<SurveyDashBoardVO> getCasteCollectedDetails(Long regionId,Long userTypeId,String startDate,String endDate)
	{
		List<SurveyDashBoardVO> resultList = new ArrayList<SurveyDashBoardVO>();
		
		LOG.info("Entered into getCasteCollectedDetails service method");
		try
		{
			 List<Object[]> votersDtls = null;
			 List<Object[]> boothsCount = null;
			 List<Object[]> totalVotersDtls = null;
			 List<Object[]> totalBoothsDtls = null;
			 
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			
			 Date strtDt = null;
			 Date endDt = null;
			
			if(!startDate.trim().equalsIgnoreCase("") && !endDate.trim().equalsIgnoreCase(""))
			{
				  strtDt = formatter.parse(startDate);
				  endDt = formatter.parse(endDate);
				
			}
			 
			
			if (userTypeId.equals(IConstants.DATA_COLLECTOR_ROLE_ID)
					|| userTypeId.equals(IConstants.THIRD_PARTY_ROLE_ID) 
					|| userTypeId.equals(IConstants.VERIFIER_ROLE_ID))			 {
			 
				 if(regionId.equals(1L))
				 {
					 votersDtls = surveyDetailsInfoDAO
							.getConstituencyWiseCasteCollectedDetailsByUserTypeId(
									userTypeId, 1L, 10L,strtDt,endDt);
					
					boothsCount = surveyDetailsInfoDAO
							.getConstituencyWiseCasteCollectedBoothsDetailsByUserTypeId(
									userTypeId, 1L, 10L,strtDt,endDt);
				 }
				 else
				 {
					 votersDtls = surveyDetailsInfoDAO
							.getConstituencyWiseCasteCollectedDetailsByUserTypeId(
									userTypeId, 11L, 23L,strtDt,endDt);
							
					boothsCount = surveyDetailsInfoDAO
							.getConstituencyWiseCasteCollectedBoothsDetailsByUserTypeId(
									userTypeId, 11L, 23L,strtDt,endDt);
				 }
			 }else if (userTypeId.equals(0L))
			{	
	 			 if(regionId.equals(1L))
				 {
	 				 votersDtls = surveyCallStatusDAO.getConstituencyWiseVerifiedVoters(1L,10L,strtDt,endDt);
	 				 boothsCount = surveyCallStatusDAO.getConstituencyWiseVerifiedBooths(1L,10L,strtDt,endDt);
	 				 
				 }else 	if(regionId.equals(2L))
	 			 {
	 				 votersDtls = surveyCallStatusDAO.getConstituencyWiseVerifiedVoters(11L,23L,strtDt,endDt);
	 				 boothsCount = surveyCallStatusDAO.getConstituencyWiseVerifiedBooths(11L,23L,strtDt,endDt);
	 			 }else 
	 			 {
	 				 votersDtls = surveyCallStatusDAO.getConstituencyWiseVerifiedVoters(1L,23L,strtDt,endDt);
	 				 boothsCount = surveyCallStatusDAO.getConstituencyWiseVerifiedBooths(1L,23L,strtDt,endDt);
	 			 }
	 			 
			}
			
			List<Long> casteCollectedConstnIds = new ArrayList<Long>();
			
			for(Object[] obj:votersDtls)
				casteCollectedConstnIds.add((Long)obj[1]);
			 
			/* totalVotersDtls = surveyConstituencyDAO
					.getTotalVotersDetailsForSurveyConstituencies(casteCollectedConstnIds);*/
			
			totalVotersDtls = surveyConstituencyTempDAO.getTotalVotersForConstituencies();
			 
			totalBoothsDtls = surveyConstituencyDAO
					.getTotalBoothsCountForSurveyConstituencies(casteCollectedConstnIds);			 
			 
			 Map<Long,Long> votersMap = generateMapFromList(votersDtls);
			 Map<Long,Long> booothsMap =generateMapFromList(boothsCount);
			 Map<Long,Long> totalVotersMap = generateMapFromList(totalVotersDtls);
			// Map<Long,Long> totalBoothsMap = generateMapFromList(totalBoothsDtls);
			 
	 		
	 
			 
			 for(Object[] obj:totalBoothsDtls)
			 {
				 SurveyDashBoardVO constituency = new SurveyDashBoardVO();
				 
				 constituency.setLocationName(obj[2].toString());
				 constituency.setLocationId((Long)obj[1]);

				 constituency.setTotalBooths((Long)obj[0]);
				 constituency.setTotalVoters(totalVotersMap.get((Long)obj[1]));
				 
				 constituency.setCollectedBoothsCount(booothsMap.get((Long)obj[1]));
				 constituency.setCollectedVotersCount(votersMap.get((Long)obj[1]));
				 
				 resultList.add(constituency);
			 }
			 
			 
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getCasteCollectedDetails", e);
		}
		
		return resultList;
	}
	
	public Map<Long,Long> generateMapFromList(List<Object[]> countList)
	{
		LOG.info("Entered into the  generateMapFromList service method");
		Map<Long,Long> resultMap = new HashMap<Long,Long>();
		try
		{
			for(Object[] obj:countList)
				resultMap.put((Long)obj[1], (Long)obj[0]);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in generateMapFromList service method", e);
		}
		return resultMap;
		
	}
	
	public List<SurveyDashBoardVO> getSurveyDetailsByConstituencyId(
			Long constituencyId, Long userTypeId)	{
		
		LOG.info("Entered into the  getSurveyConstituencyDetailsByConstituencyId service method");
		List<SurveyDashBoardVO>  resultList = new ArrayList<SurveyDashBoardVO>();
		try
		{
			List<Object[]> votersDtls = null;
			List<Object[]> totalVoters  = null;
			List<Object[]> usersDetails  = null;
			List<Long> boothIds = null;

			
			if (userTypeId.equals(IConstants.DATA_COLLECTOR_ROLE_ID)
					|| userTypeId.equals(IConstants.THIRD_PARTY_ROLE_ID) 
					|| userTypeId.equals(IConstants.VERIFIER_ROLE_ID))	{
				votersDtls = surveyDetailsInfoDAO.getBoothWiseCollectedCasteDetailsByConstituencyId(constituencyId,userTypeId);
				usersDetails = surveyDetailsInfoDAO.getBoothWiseUsersDetailsByConstituencyId(constituencyId,userTypeId);
				
				boothIds = surveyDetailsInfoDAO.getSurveyStartedBoothsDetailsForConstituencyByUserTypeId(constituencyId,userTypeId);
				
			}else
			{
				votersDtls = surveyCallStatusDAO.getBoothWiseVerifiedDetailsByConstituencyId(constituencyId);
				usersDetails = surveyCallStatusDAO.getBoothWiseUsersDetailsByConstituencyId(constituencyId);
				
				boothIds = surveyCallStatusDAO.getVerifiedBoothIdsByConstituencyId(constituencyId);
			}
			
			
			totalVoters = boothPublicationVoterDAO.getBoothWiseTotalVotersByConstituencyId(constituencyId);

			Map<Long,String> usersMap = new HashMap<Long, String>();
			
			for(Object[] obj:usersDetails)
				usersMap.put(Long.parseLong(obj[0].toString()), obj[1].toString());
			
			Map<Long,Long> votersMap = generateMapFromList(votersDtls);
			
			for(Object[] obj:totalVoters)
			{
				
				if(boothIds.contains((Long)obj[1]))
				{
					SurveyDashBoardVO boothVO = new SurveyDashBoardVO();
					
					boothVO.setLocationName(obj[2].toString());
					boothVO.setLocationId((Long)obj[1]);
					boothVO.setCollectedVotersCount(votersMap.get((Long)obj[1]));
					boothVO.setName(usersMap.get((Long)obj[1]));
					boothVO.setTotalVoters((Long)obj[0]);
					boothVO.setPartNo(Long.parseLong(obj[2].toString()));
					
					resultList.add(boothVO);
				}
				
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getSurveyConstituencyDetailsByConstituencyId service method", e);
		}
		
		return resultList;
	}
	
	
	public List<SurveyResponceVO> getBoothWiseCollectedcasteDetails(Long boothId,Long userTypeId)
	{
		LOG.info("Entered into the  getBoothWiseCollectedcasteDetails service method");
		
		List<SurveyResponceVO> resultList = new ArrayList<SurveyResponceVO>();

		try
		{
			List<Object[]> votersDetails = null;
			
			if (userTypeId.equals(IConstants.DATA_COLLECTOR_ROLE_ID)
					|| userTypeId.equals(IConstants.THIRD_PARTY_ROLE_ID)
					|| userTypeId.equals(IConstants.VERIFIER_ROLE_ID))	{
				 votersDetails = surveyDetailsInfoDAO
						.getBoothWiseCollectedcasteDetails(boothId, userTypeId);
				
				for(Object[] obj:votersDetails)
				{
					SurveyResponceVO voterVO = new SurveyResponceVO();
					
					voterVO.setVoterName(obj[0].toString());
					voterVO.setRelativeName(obj[1].toString());
					voterVO.setHouseNo(obj[2].toString());
					voterVO.setAge((Long)obj[3]);
					voterVO.setGender(obj[4].toString());
					voterVO.setCasteName(obj[5].toString());
					resultList.add(voterVO);
				}
			}else
			{
				
				votersDetails = surveyCallStatusDAO.getVotersDetailsByBoothId(boothId);
				
				List<Long> dcVoterIds = new ArrayList<Long>();
				//List<Long> dvVoterIds = new ArrayList<Long>();
				
				
				for(Object[] obj:votersDetails)
				{
					SurveyResponceVO voterVO = new SurveyResponceVO();
					
					voterVO.setVoterName(obj[0].toString());
					voterVO.setRelativeName(obj[1].toString());
					voterVO.setHouseNo(obj[2].toString());
					voterVO.setAge((Long)obj[3]);
					voterVO.setGender(obj[4].toString());
					voterVO.setVoterId((Long)obj[5]);
					voterVO.setDcCorrectedCasteName(obj[6].toString());
					dcVoterIds.add((Long)obj[5]);
						
					resultList.add(voterVO);
				}
				
				
				
				if(dcVoterIds.size()== 0)
					dcVoterIds.add(0L);
				
					List<Object[]> dcCasteDtls = surveyDetailsInfoDAO
							.getCollectedCasteDetailsForVoterIdsByUserTypeId(
									dcVoterIds, IConstants.DATA_COLLECTOR_ROLE_ID,boothId);
					
					
					for(Object[] obj:dcCasteDtls)
					{
						SurveyResponceVO voterVO = new SurveyResponceVO();
						
						voterVO.setVoterId((Long)obj[0]);
						voterVO.setVoterName(obj[2].toString());
						voterVO.setRelativeName(obj[3].toString());
						voterVO.setHouseNo(obj[4].toString());
						voterVO.setAge((Long)obj[5]);
						voterVO.setGender(obj[6].toString());
						voterVO.setDcCasteName(obj[1].toString());
						resultList.add(voterVO);
					}
	
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getBoothWiseCollectedcasteDetails service method", e);
		}
		
		return resultList;
	}
	
	public SurveyResponceVO getMatchedVoterVO(List<SurveyResponceVO> resultList,Long voterId)
	{
		for(SurveyResponceVO resultVO:resultList)
			if(resultVO.getVoterId().equals(voterId))
				return resultVO;
		return null;
	
	}
	
	public List<SurveyDashBoardVO> getCountsForDC()
	{
		List<SurveyDashBoardVO> resultList = new ArrayList<SurveyDashBoardVO>();
	try{
		List<Long> constituencyIds = surveyDetailsInfoDAO.getConstituencyIds();
		if(constituencyIds != null && constituencyIds.size() > 0)
		{
			List<Object[]> list = surveyConstituencyTempDAO.getTotalVotersAndBooths(constituencyIds);
			if(list != null && list.size() > 0)
			 for(Object[] params : list)
			 {
				 SurveyDashBoardVO vo = new SurveyDashBoardVO();
				 vo.setConstituencyId((Long)params[0]);
				 vo.setName(constituencyDAO.get((Long)params[0]).getName());
				 vo.setTotalVoters((Long)params[1]);
				 vo.setTotalBooths((Long)params[2]);
				 resultList.add(vo); 
			 }
			
			 List<Object[]> votersList = surveyCallStatusDAO.getTotalVotersByConstituencyIds(constituencyIds);
			if(votersList != null && votersList.size() > 0)
				for(Object[] params : votersList)
				{
					SurveyDashBoardVO constituencyVo = getMatchedConstituencyVo(resultList,(Long)params[0]);
					if(constituencyVo != null)
						constituencyVo.setDcTotalVoters((Long)params[1]);
				}
		     List<Object[]> boothsList = surveyCallStatusDAO.getTotalBoothsByConstituencyIds(constituencyIds);
		     if(boothsList != null && boothsList.size() > 0)
					for(Object[] params : boothsList)
					{
						SurveyDashBoardVO constituencyVo = getMatchedConstituencyVo(resultList,(Long)params[0]);
						if(constituencyVo != null)
							constituencyVo.setDcVerifiedBooths((Long)params[1]);
					}
		     List<Object[]> DVboothsList = surveyDetailsInfoDAO.getDVTotalBoothsByConstituencyIds(constituencyIds);
		     if(DVboothsList != null && DVboothsList.size() > 0)
					for(Object[] params : DVboothsList)
					{
						SurveyDashBoardVO constituencyVo = getMatchedConstituencyVo(resultList,(Long)params[0]);
						if(constituencyVo != null)
							constituencyVo.setDvTotalBooths((Long)params[1]);
					}

		     
			
		}
		
	}
	catch (Exception e) {
		e.printStackTrace();
		LOG.error("Exception raised in getCountsForDC service method", e);
	}
	return resultList;
	}
	
	public SurveyDashBoardVO getMatchedConstituencyVo(List<SurveyDashBoardVO> resultList,Long constituencyId)
	{
		try{
	if(resultList == null)
		return null;
	for(SurveyDashBoardVO vo : resultList)
	{
		if(vo.getConstituencyId().longValue() == constituencyId.longValue())
			return vo;
	}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	return null;
	}
	
	
	public List<BoothWiseSurveyStatusDetailsVO> getAllBoothsStatusDetailsByConstituencyId(Long constituencyId)
	{
		LOG.info("Entered into the getAllBoothsStatusDetailsByConstituencyId service method");
		List<BoothWiseSurveyStatusDetailsVO> resultList = new ArrayList<BoothWiseSurveyStatusDetailsVO>();
		try
		{
			
			List<Long> dcBoothsDetails = surveyDetailsInfoDAO.getBoothDetailsForConstituencyByUserTypeId(constituencyId,IConstants.DATA_COLLECTOR_ROLE_ID);
			List<Long> dvBoothsDetails = surveyDetailsInfoDAO.getBoothDetailsForConstituencyByUserTypeId(constituencyId,IConstants.VERIFIER_ROLE_ID);
			List<Long> qcBoothsDetails = surveyDetailsInfoDAO.getBoothDetailsForConstituencyByUserTypeId(constituencyId,IConstants.THIRD_PARTY_ROLE_ID);
			
			List<Long> dcWmBoothsDetails = surveyCallStatusDAO.getDataCollectorWebMonitorDetailsForConstituency(constituencyId);
			List<Long> dvWmBoothsDetails = surveyCallStatusDAO.getDataVerifierWebMonitorDetailsForConstituency(constituencyId);
			
			List<Object[]> votersDetails = boothPublicationVoterDAO.getBoothWiseTotalVotersByConstituencyId(constituencyId);
			
			Map<Long,Long> voterCountMap = new HashMap<Long, Long>();
			
			for(Object[] obj:votersDetails)
				voterCountMap.put((Long)obj[1], (Long)obj[0]);
				
			
			List<Object[]> boothDtls = boothDAO.getTotalBoothsDetailsByConstituencyId(constituencyId);
			
			for(Object[] obj:boothDtls)
			{
				BoothWiseSurveyStatusDetailsVO booth = new BoothWiseSurveyStatusDetailsVO();
				
				booth.setBoothId((Long)obj[0]);
				booth.setPartNo(obj[1].toString());
				booth.setTotalVoters(voterCountMap.get((Long)obj[0]));
				
				if(dcBoothsDetails.contains((Long)obj[0]))
				{
					booth.setDcCompleted("Y");
					
					if(dvBoothsDetails.contains((Long)obj[0]))
					{
						booth.setDvCompleted("Y");
						
						if(dvWmBoothsDetails.contains((Long)obj[0]))
						{
							booth.setWmDvCompleted("Y");
						}
					}
					
					if(qcBoothsDetails.contains((Long)obj[0]))
					{
						booth.setQcCompleted("Y");
					}
					
					if(dcWmBoothsDetails.contains((Long)obj[0]))
					{
						booth.setWmDcCompleted("Y");
					}
				}
				resultList.add(booth);
			}
	
			
		}catch(Exception e)
		{
			LOG.error("Exception raised in getAllBoothsStatusDetailsByConstituencyId service method", e);
		}
		
		return resultList;
	}
	
	/**
	 * This Service is used for getting constituency wise summary 
	 * @return returnList
	 */
	public List<BigPictureVO> buildConstituencyWiseSummaryReport()
	{
		List<BigPictureVO> returnList = null;
		try 
		{
			Map<Long,BigPictureVO> resultMap = null;
			Map<Long,GenericVO> comstiDetailsMap = null;
			Map<Long,GenericVO> wmDcDetailsMap = null;
			Map<Long,GenericVO> wmDvDetailsMap = null;
			Map<Long,Long> qcMatchedMap = null;
			Map<Long,Long> qcUnMatchedMap = null;
			List<Object[]> constituencyWiseList = surveyDetailsInfoDAO.getConstituencyWiseSummary();
			if(constituencyWiseList != null && constituencyWiseList.size() > 0)
			{
				resultMap = new HashMap<Long, BigPictureVO>();
				fillMainCasteCollectionDetails(constituencyWiseList,resultMap);
			}
			
			List<Long> constituencyIds = new ArrayList<Long>(resultMap.keySet());
			if(constituencyIds != null && constituencyIds.size() > 0)
			{
				List<Object[]> constituencyDetails = surveyConstituencyTempDAO.getTotalVotersAndBooths(constituencyIds);
				if(constituencyDetails != null && constituencyDetails.size() > 0)
				{
					comstiDetailsMap = new HashMap<Long, GenericVO>();
					fillConstituencyMapDetails(constituencyDetails,comstiDetailsMap);
				}
				
				List<Object[]> wmDcDetails = surveyCallStatusDAO.getConstituencyWiseSummaryForWmDc();
				if(wmDcDetails != null && wmDcDetails.size() > 0)
				{
					wmDcDetailsMap = new HashMap<Long, GenericVO>();
					fillWmDetailsMap(wmDcDetails,wmDcDetailsMap);
				}
				
				List<Object[]> wmDvDetails = surveyCallStatusDAO.getConstituencyWiseSummaryForWmDV();
				if(wmDvDetails != null && wmDvDetails.size() > 0)
				{
					wmDvDetailsMap = new HashMap<Long, GenericVO>();
					fillWmDetailsMap(wmDvDetails,wmDvDetailsMap);
				}
				List<Long> statusIds = new ArrayList<Long>();
				statusIds.add(1l);
				List<Object[]> qcMatched = surveyFinalDataDAO.getQcDataForSelection(0l, statusIds, null, null);
				if(qcMatched != null && qcMatched.size() > 0)
				{
					qcMatchedMap = new HashMap<Long, Long>();
					for (Object[] objects : qcMatched) {
						qcMatchedMap.put((Long)objects[0], (Long)objects[3]);
					}
					
				}
				List<Long> unstatusIds = new ArrayList<Long>();
				unstatusIds.add(2l);
				unstatusIds.add(3l);
				List<Object[]> qcUnMatched = 	surveyFinalDataDAO.getQcDataForSelection(0l, unstatusIds, null, null);
				if(qcUnMatched != null && qcUnMatched.size() > 0 )
				{
					qcUnMatchedMap = new HashMap<Long, Long>();
					for (Object[] objects : qcUnMatched)
					{
						qcUnMatchedMap.put((Long)objects[0], (Long)objects[3]);
					}
					
				}
			}
			
			if(comstiDetailsMap != null && comstiDetailsMap.size() > 0)
			{
				returnList = new ArrayList<BigPictureVO>();
				fillResultList(comstiDetailsMap,resultMap, wmDcDetailsMap , wmDvDetailsMap, returnList,qcMatchedMap ,qcUnMatchedMap);
			}
			
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in buildConstituencyWiseSummaryReport service method", e);
		}
		return returnList;
	}
	
	public void fillMainCasteCollectionDetails(List<Object[]> processList, Map<Long,BigPictureVO> resultMap)
	{
		try
		{
			for (Object[] objects : processList)
			{
				BigPictureVO bigPictureVO = resultMap.get((Long)objects[0]);
				if(bigPictureVO == null)
				{
					bigPictureVO = new BigPictureVO();
					resultMap.put((Long)objects[0], bigPictureVO);
				}
				if((Long)objects[1] == 1)
				{
					bigPictureVO.setDcVotersCount(Integer.valueOf(objects[2].toString()));
					bigPictureVO.setDcBoothsCount(Integer.valueOf(objects[3].toString()));
				}
				else if((Long)objects[1] == 4)
				{
					bigPictureVO.setVerifierVotersCount(Integer.valueOf(objects[2].toString()));
					bigPictureVO.setVerifierBoothsCount(Integer.valueOf(objects[3].toString()));
				}
				else
				{
					bigPictureVO.setQcVotersCount(Integer.valueOf(objects[2].toString()));
					bigPictureVO.setQcBoothsCount(Integer.valueOf(objects[3].toString()));
				}
			}
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in fillMainCasteCollectionDetails service method", e);
		}
		
	}
	
	public void fillConstituencyMapDetails(List<Object[]> processList , Map<Long,GenericVO> resultMap)
	{
		try
		{
			for (Object[] objects : processList)
			{
				GenericVO genericVO = new GenericVO();
				genericVO.setCount((Long)objects[1]);// total voters
				genericVO.setId((Long)objects[2]); // total booths
				genericVO.setName(objects[3].toString()); // constituency name
				resultMap.put((Long)objects[0], genericVO);
			}
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in fillConstituencyMapDetails service method", e);
		}
		
	}
	
	public void fillWmDetailsMap(List<Object[]> processList , Map<Long,GenericVO> resultMap)
	{
		try 
		{
			for (Object[] objects : processList) {
				GenericVO genericVO = new GenericVO();
				genericVO.setCount((Long)objects[1]);// booths count
				genericVO.setId((Long)objects[2]); // voters count
				resultMap.put((Long)objects[0], genericVO);
			}
		} 
		catch (Exception e) 
		{
			LOG.error("Exception raised in fillWmDetailsMap service method", e);
		}
		
	}
	
	
	public void fillResultList(Map<Long,GenericVO> comstiDetailsMap,Map<Long,BigPictureVO> resultMap,Map<Long,GenericVO> wmDcDetailsMap , Map<Long,GenericVO> wmDvDetailsMap,List<BigPictureVO> returnList,Map<Long,Long> matchedMap ,Map<Long,Long> unMatchedMap )
	{
		try
		{
			for(Long constituencyId : comstiDetailsMap.keySet())
			{
				BigPictureVO bigPictureVO = new BigPictureVO();
				BigPictureVO mainVO = resultMap.get(constituencyId);
				bigPictureVO.setId(constituencyId);
				
				GenericVO constiVO = comstiDetailsMap.get(constituencyId);
				if(constiVO != null)
				{
					bigPictureVO.setTotalVoters(Integer.valueOf(constiVO.getCount().toString()));
					bigPictureVO.setTotalConstituencyes(Integer.valueOf(constiVO.getId().toString()));
					bigPictureVO.setName(constiVO.getName());
					
					if(mainVO != null)
					{
						bigPictureVO.setDcVotersCount(mainVO.getDcVotersCount());
						bigPictureVO.setDcBoothsCount(mainVO.getDcBoothsCount());
						bigPictureVO.setDcPercentage(new BigDecimal(bigPictureVO.getDcBoothsCount()*(100.0)/bigPictureVO.getTotalConstituencyes()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
						bigPictureVO.setQcPercentage(new BigDecimal(bigPictureVO.getDcVotersCount()*(100.0)/bigPictureVO.getTotalVoters()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
						bigPictureVO.setVerifierVotersCount(mainVO.getVerifierVotersCount());
						bigPictureVO.setVerifierBoothsCount(mainVO.getVerifierBoothsCount());
						bigPictureVO.setQcVotersCount(mainVO.getQcVotersCount());
						bigPictureVO.setQcBoothsCount(mainVO.getQcBoothsCount());
					}
					
					GenericVO wmDcVO = wmDcDetailsMap.get(constituencyId);
					if(wmDcVO != null)
					{
						bigPictureVO.setRedoBooths(Integer.valueOf(wmDcVO.getCount().toString())); // wm DC Booths count
						bigPictureVO.setRedoVoters(Integer.valueOf(wmDcVO.getId().toString()));// wm Dc Voters Count
					}
					
					GenericVO wmDvVO = wmDvDetailsMap.get(constituencyId);
					if(wmDvVO != null )
					{
						bigPictureVO.setDcConstituencysCount(Integer.valueOf(wmDvVO.getCount().toString()) );// wm dv booth count
						bigPictureVO.setQcConstituencyesCount(Integer.valueOf(wmDvVO.getCount().toString())); // wm dv voters count
					}
					Long matchedCount = matchedMap.get(constituencyId);
					if(matchedCount != null)
					{
						bigPictureVO.setMatchedCount(Integer.valueOf(matchedCount.toString()));
						bigPictureVO.setMatchedPerc(new BigDecimal(bigPictureVO.getMatchedCount()*(100.0)/bigPictureVO.getQcVotersCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}
					Long unMatchedCount = unMatchedMap.get(constituencyId);
					if(unMatchedCount != null)
					{
						bigPictureVO.setUnMatchedCount(Integer.valueOf(unMatchedCount.toString()));
						bigPictureVO.setUnMatchedPerc(new BigDecimal(bigPictureVO.getUnMatchedCount()*(100.0)/bigPictureVO.getQcVotersCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}
				}
				
				returnList.add(bigPictureVO);
			}
		} 
		catch (Exception e) 
		{
			LOG.error("Exception raised in fillResultList service method", e);
		}
		
	}
	
	/**
	 * This Servic is used for getting booth wise user location tracking
	 * @param boothId
	 * @param surveyUserUd
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public List<GenericVO> getUserWiseCollecetionDetails(Long boothId , Long surveyUserUd , String fromDate , String toDate)
	{
		List<GenericVO> returnList = null;
		try
		{
			List<Object[]> boothWiseUserDetails = null;
			if(fromDate.toString().equalsIgnoreCase("null") && toDate.toString().equalsIgnoreCase("null"))
			{
				DateUtilService dateUtilService = new DateUtilService();
				Date todateDate = dateUtilService.getCurrentDateAndTime();
				boothWiseUserDetails = surveyDetailsInfoDAO.getBoothWiseUserCollectedLocations(boothId,surveyUserUd,todateDate,null,null);
			}
			else
			{
				SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
				boothWiseUserDetails = surveyDetailsInfoDAO.getBoothWiseUserCollectedLocations(boothId,surveyUserUd,null,originalFormat.parse(fromDate),originalFormat.parse(toDate));
			}
			
			if(boothWiseUserDetails != null && boothWiseUserDetails.size() > 0)
			{
				returnList = new ArrayList<GenericVO>();
				for (Object[] objects : boothWiseUserDetails)
				{
					GenericVO genericVO = new GenericVO();
					genericVO.setName(objects[0] != null ? objects[0].toString() : ""); //longitude
					genericVO.setDesc(objects[1] != null ? objects[1].toString() : ""); //latitude
					returnList.add(genericVO);
				}
			}
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in getUserWiseCollecetionDetails service method", e);
		}
		return returnList;
	}
}
