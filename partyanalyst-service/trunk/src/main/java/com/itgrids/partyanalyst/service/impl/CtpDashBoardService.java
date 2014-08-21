package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ISurveyCallStatusDAO;
import com.itgrids.partyanalyst.dao.ISurveyConstituencyDAO;
import com.itgrids.partyanalyst.dao.ISurveyConstituencyTempDAO;
import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.dao.ISurveyFinalDataDAO;
import com.itgrids.partyanalyst.dto.BigPictureVO;
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
			
			List<Object[]> verifierDetails = surveyCallStatusDAO.getVerifierCounts(stateId);
			if(verifierDetails != null && verifierDetails.size() > 0)
			{
				for (Object[] objects : verifierDetails)
				{
					if(objects[0] != null )
					{
						if(objects[0].toString().equalsIgnoreCase("Y"))
						{
							returnVO.setVerifierVotersCount(returnVO.getVerifierVotersCount() + Integer.valueOf(objects[1].toString()));
							returnVO.setVerifierBoothsCount(returnVO.getVerifierBoothsCount() + Integer.valueOf(objects[3].toString()));
							returnVO.setVerifierConstituencyCount(returnVO.getVerifierConstituencyCount() + Integer.valueOf(objects[2].toString()));
						}
						else if (objects[0].toString().equalsIgnoreCase("n"))
						{
							returnVO.setVerifierVotersCount(returnVO.getVerifierVotersCount() + Integer.valueOf(objects[1].toString()));
							returnVO.setVerifierBoothsCount(returnVO.getVerifierBoothsCount() + Integer.valueOf(objects[3].toString()));
							returnVO.setVerifierConstituencyCount(returnVO.getVerifierConstituencyCount() + Integer.valueOf(objects[2].toString()));
						}
					}
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
	public BigPictureVO getInternalVerificationSummary(Long stateId)
	{
		BigPictureVO returnVO = new BigPictureVO();
		try
		{
			
			List<Object[]> verifierDetails = surveyCallStatusDAO.getVerifierCounts(stateId);
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
				getRedoBooths(stateId,returnVO);
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
	public void getRedoBooths(Long stateId , BigPictureVO returnVO)
	{
		try
		{
			List<Long> redoBooths = surveyDetailsInfoDAO.getsurveyUserCollectedBooths(stateId,4l);
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
	public BigPictureVO getQcVerificationSummaryReport(Long stateId)
	{
		BigPictureVO returnVO = new BigPictureVO();
		try
		{
			returnVO.setQcVotersCount(surveyDetailsInfoDAO.getSurveyUserCollectedVoters(stateId,10l).intValue());
			List<Long> matchedIds = new ArrayList<Long>();
			matchedIds.add(1l);
			returnVO.setMatchedCount(surveyFinalDataDAO.getQcCollectedMatchedUnMatchedDetails(stateId,matchedIds).intValue());
			List<Long> unMatchedIds = new ArrayList<Long>();
			unMatchedIds.add(2l);
			unMatchedIds.add(3l);
			unMatchedIds.add(4l);
			returnVO.setUnMatchedCount(surveyFinalDataDAO.getQcCollectedMatchedUnMatchedDetails(stateId,unMatchedIds).intValue());
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
	public BigPictureVO getTodayTeamDetails(Long stateId)
	{
		BigPictureVO returnVO = new BigPictureVO();
		try 
		{
			DateUtilService dateUtilService = new DateUtilService();
			Date todateDate = dateUtilService.getCurrentDateAndTime();
			List<Object[]> teamDetailsObj = surveyDetailsInfoDAO.getTodayTeamDetails(stateId,todateDate);
			
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
	public List<BigPictureVO> getConstituencyWiseTeamDetails(Long stateId , Long surveyUserType)
	{
		List<BigPictureVO> returnList = null;
		
		try 
		{
			DateUtilService dateUtilService = new DateUtilService();
			Date todateDate = dateUtilService.getCurrentDateAndTime();
			List<Object[]> teamDetailsObj = surveyDetailsInfoDAO.getTeamDetailsInConstituencyLevel(stateId,todateDate,surveyUserType);
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
	public List<BigPictureVO> getBoothWiseTeamDetails(Long stateId , Long constituencyId , Long surveyUserTypeId)
	{
		List<BigPictureVO> returnList = null;
		try 
		{
			DateUtilService dateUtilService = new DateUtilService();
			Date todateDate = dateUtilService.getCurrentDateAndTime();
			List<Object[]> teamDetails = surveyDetailsInfoDAO.getTeamDetailsInBoothLevel(stateId,constituencyId, surveyUserTypeId, todateDate);
			if(teamDetails != null && teamDetails.size() > 0)
			{
				returnList = new ArrayList<BigPictureVO>();
				for (Object[] objects : teamDetails)
				{
					BigPictureVO bigPictureVO = new BigPictureVO();
					bigPictureVO.setDcVotersCount(objects[0] != null ?  Integer.valueOf(objects[0].toString()) : 0);// booth Id
					bigPictureVO.setQcVotersCount(objects[2] != null ?  Integer.valueOf(objects[2].toString()) : 0);//survey userid
					bigPictureVO.setDcPercentage(objects[1] != null ? objects[1].toString() : "");//partNo
					bigPictureVO.setQcPercentage(objects[3] != null ? objects[3].toString() : "");//user name
					bigPictureVO.setVerifierPercentage(objects[4] != null ? objects[4].toString() : "");// mobile no
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
	public List<BigPictureVO> getConstituencyWiseQcVerificationSummary(Long stateId , String type)
	{
		List<BigPictureVO> returnList = null;
		try
		{
			List<Object[]> constituencyWiseDetils = null;
			if(type.equalsIgnoreCase("null"))
			{
				constituencyWiseDetils = surveyDetailsInfoDAO.getConstituecySummaryForQc(stateId);
			}
			else
			{
				List<Long> statusIds = new ArrayList<Long>();
				if(type.equalsIgnoreCase("Y"))
				{
					statusIds.add(1l);
					constituencyWiseDetils = surveyFinalDataDAO.getQcDataForSelection(stateId,statusIds);
				}
				else
				{
					statusIds.add(2l);
					statusIds.add(3l);
					constituencyWiseDetils =surveyFinalDataDAO.getQcDataForSelection(stateId,statusIds);
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
	public List<BigPictureVO> getBoothWiseQcVerificationSummary(Long stateId , Long constituencyId,String type)
	{
		List<BigPictureVO> returnList = null;
		try 
		{
			List<Object[]> boothWiseQcDetails = null;
			if(type.equalsIgnoreCase("null"))
			{
				boothWiseQcDetails = surveyDetailsInfoDAO.getBoothWiseSummaryForQc(stateId,constituencyId);
			}
			else
			{
				List<Long> statusIds = new ArrayList<Long>();
				if(type.equalsIgnoreCase("Y"))
				{
					statusIds.add(1l);
					boothWiseQcDetails = surveyFinalDataDAO.getBoothWiseQcData(stateId,constituencyId,statusIds);
				}
				else
				{
					statusIds.add(2l);
					statusIds.add(3l);
					boothWiseQcDetails = surveyFinalDataDAO.getBoothWiseQcData(stateId,constituencyId,statusIds);
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
	public BigPictureVO getTodayTeamCollectedDetails(Long stateId)
	{
		BigPictureVO returnVO = new BigPictureVO();
		try 
		{
			DateUtilService dateUtilService = new DateUtilService();
			Date todateDate = dateUtilService.getCurrentDateAndTime();
			List<Object[]> teamCollectedDetails = surveyDetailsInfoDAO.getTodayTeamCollectedDetails(stateId,todateDate);
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
	public List<BigPictureVO> getConstituencyWiseTeamCollectedSummary(Long stateId , Long type)
	{
		List<BigPictureVO> returnList = null;
		try 
		{
			DateUtilService dateUtilService = new DateUtilService();
			Date todateDate = dateUtilService.getCurrentDateAndTime();
			List<Object[]> constituencyWiseDetails = surveyDetailsInfoDAO.getConstituencyWiseTeamCollectedDetails(stateId,type, todateDate);
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
	
	public List<BigPictureVO> getBoothWiseTeamCollectedDetailsSummary(Long stateId ,Long constituencyId , Long surveyUserTypeId)
	{
		List<BigPictureVO> returnList = null;
		try 
		{
			DateUtilService dateUtilService = new DateUtilService();
			Date todateDate = dateUtilService.getCurrentDateAndTime();
			List<Object[]> boothWiseDetails = surveyDetailsInfoDAO.getBoothWiseTeamCollectedDetails(stateId,surveyUserTypeId, todateDate, constituencyId);
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
	public List<SurveyDashBoardVO> getCasteCollectedDetails(Long regionId,Long userTypeId)
	{
		List<SurveyDashBoardVO> resultList = new ArrayList<SurveyDashBoardVO>();
		
		LOG.info("Entered into getCasteCollectedDetails service method");
		try
		{
			 List<Object[]> votersDtls = null;
			 List<Object[]> boothsCount = null;
			 List<Object[]> totalVotersDtls = null;
			 List<Object[]> totalBoothsDtls = null;
			 
			 
			if (userTypeId.equals(IConstants.DATA_COLLECTOR_ROLE_ID)
					|| userTypeId.equals(IConstants.THIRD_PARTY_ROLE_ID) 
					|| userTypeId.equals(IConstants.VERIFIER_ROLE_ID))			 {
			 
				 if(regionId.equals(1L))
				 {
					 votersDtls = surveyDetailsInfoDAO
							.getConstituencyWiseCasteCollectedDetailsByUserTypeId(
									userTypeId, 1L, 10L);
					
					boothsCount = surveyDetailsInfoDAO
							.getConstituencyWiseCasteCollectedBoothsDetailsByUserTypeId(
									userTypeId, 1L, 10L);
				 }
				 else
				 {
					 votersDtls = surveyDetailsInfoDAO
							.getConstituencyWiseCasteCollectedDetailsByUserTypeId(
									userTypeId, 11L, 23L);
							
					boothsCount = surveyDetailsInfoDAO
							.getConstituencyWiseCasteCollectedBoothsDetailsByUserTypeId(
									userTypeId, 11L, 23L);
				 }
			 }
			
			List<Long> casteCollectedConstnIds = new ArrayList<Long>();
			
			for(Object[] obj:votersDtls)
				casteCollectedConstnIds.add((Long)obj[1]);
			 
			 totalVotersDtls = surveyConstituencyDAO
					.getTotalVotersDetailsForSurveyConstituencies(casteCollectedConstnIds);
			 
			totalBoothsDtls = surveyConstituencyDAO
					.getTotalBoothsCountForSurveyConstituencies(casteCollectedConstnIds);			 
			 
			 Map<Long,Long> votersMap = generateMapFromList(votersDtls);
			 Map<Long,Long> booothsMap =generateMapFromList(boothsCount);
			 Map<Long,Long> totalVotersMap = generateMapFromList(totalVotersDtls);
			// Map<Long,Long> totalBoothsMap = generateMapFromList(totalBoothsDtls);
			 
	 		if (userTypeId.equals(0L))
			{	
	 			 if(regionId.equals(1L))
				 {
	 				 votersDtls = surveyCallStatusDAO.getConstituencyWiseVerifiedVoters(1L,10L);
	 				 boothsCount = surveyCallStatusDAO.getConstituencyWiseVerifiedBooths(1L,10L);
				 }
	 			 {
	 				 votersDtls = surveyCallStatusDAO.getConstituencyWiseVerifiedVoters(11L,23L);
	 				 boothsCount = surveyCallStatusDAO.getConstituencyWiseVerifiedBooths(11L,23L);
	 			 }
			}
	 
			 
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
			
			if (userTypeId.equals(IConstants.DATA_COLLECTOR_ROLE_ID)
					|| userTypeId.equals(IConstants.THIRD_PARTY_ROLE_ID) 
					|| userTypeId.equals(IConstants.VERIFIER_ROLE_ID))	{
				votersDtls = surveyDetailsInfoDAO.getBoothWiseCollectedCasteDetailsByConstituencyId(constituencyId,userTypeId);
				usersDetails = surveyDetailsInfoDAO.getBoothWiseUsersDetailsByConstituencyId(constituencyId,userTypeId);
				
			}else
			{
				votersDtls = surveyCallStatusDAO.getBoothWiseVerifiedDetailsByConstituencyId(constituencyId);
				usersDetails = surveyCallStatusDAO.getBoothWiseUsersDetailsByConstituencyId(constituencyId);
			}
			
			List<Long> boothIds = surveyDetailsInfoDAO.getSurveyStartedBoothsDetailsForConstituencyByUserTypeId(constituencyId,userTypeId);
			
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
		     List<Object[]> DVboothsList = surveyCallStatusDAO.getDVTotalBoothsByConstituencyIds(constituencyIds);
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
			e.printStackTrace();
			LOG.error("Exception raised in getAllBoothsStatusDetailsByConstituencyId service method", e);
		}
		
		return resultList;
	}
}
