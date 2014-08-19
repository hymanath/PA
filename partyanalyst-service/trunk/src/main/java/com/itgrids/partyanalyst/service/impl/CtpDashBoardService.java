package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ISurveyCallStatusDAO;
import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.dao.ISurveyFinalDataDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dto.BigPictureVO;
import com.itgrids.partyanalyst.service.ICtpDashBoardService;
import com.itgrids.partyanalyst.utils.DateUtilService;

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
	
	/**
	 * @author Prasad Thiragabathina
	 * This Serivice is used for setting over all big picture on CTP project
	 * @return returnVO
	 */
	public BigPictureVO getBigPictureDetails()
	{
		BigPictureVO returnVO = new BigPictureVO();
		try
		{
			returnVO.setDcVotersCount(surveyDetailsInfoDAO.getSurveyUserCollectedVoters(1l));
			returnVO.setDcBoothsCount(surveyDetailsInfoDAO.getSurveyUserCollectedVoters(1l));
			returnVO.setDcConstituencysCount(surveyDetailsInfoDAO.getSurveyUserCompeletedConstituencyes(1l));
			
			returnVO.setQcVotersCount(surveyDetailsInfoDAO.getSurveyUserCollectedVoters(10l));
			returnVO.setQcBoothsCount(surveyDetailsInfoDAO.getSurveyUserCompeletedBooths(10l));
			returnVO.setQcConstituencyesCount(surveyDetailsInfoDAO.getSurveyUserCompeletedConstituencyes(10l));
			
			returnVO.setVerifierVotersCount(surveyCallStatusDAO.getWmVerifiedVoters());
			returnVO.setVerifierBoothsCount(surveyCallStatusDAO.getWmVerifiedBooths());
			returnVO.setVerifierConstituencyCount(surveyCallStatusDAO.getWmVerifiedConstituencyes());
			
			returnVO.setTotalVoters(37696573);
			returnVO.setTotalBooths(44688);
			returnVO.setTotalConstituencyes(216);
			
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
	public BigPictureVO getInternalVerificationSummary()
	{
		BigPictureVO returnVO = new BigPictureVO();
		try
		{
			returnVO.setVerifierVotersCount(surveyCallStatusDAO.getWmVerifiedVoters());
			returnVO.setCorrectDetails(surveyCallStatusDAO.getWmVerifiedRecordsCount("Y"));
			returnVO.setWrongDetails(surveyCallStatusDAO.getWmVerifiedRecordsCount("N"));
			
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
			getRedoBooths(returnVO);
			
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
	public void getRedoBooths(BigPictureVO returnVO)
	{
		try
		{
			List<Long> redoBooths = surveyDetailsInfoDAO.getsurveyUserCollectedBooths(4l);
			if(redoBooths != null && redoBooths.size() > 0)
			{
				returnVO.setRedoBooths(redoBooths.size());
				returnVO.setRedoVoters(getRedoTotalVoters(redoBooths,returnVO));
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
	public Integer getRedoTotalVoters(List<Long> redoBooths,BigPictureVO returnVO )
	{
		Integer total = 0;
		try
		{
			returnVO.setRedoBooths(redoBooths.size());
			 int fromIndex = 0;
			 int toIndex = 1000;
			 
			 if(redoBooths.size() > 1000)
			  {
				  while(fromIndex <= toIndex)
				  {
					Integer count = boothPublicationVoterDAO.getTotalVoterByBooths(redoBooths.subList(fromIndex, toIndex));
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
	public BigPictureVO getQcVerificationSummaryReport()
	{
		BigPictureVO returnVO = new BigPictureVO();
		try
		{
			returnVO.setQcVotersCount(surveyDetailsInfoDAO.getSurveyUserCollectedVoters(10l));
			List<Long> matchedIds = new ArrayList<Long>();
			matchedIds.add(1l);
			returnVO.setMatchedCount(surveyFinalDataDAO.getQcCollectedMatchedUnMatchedDetails(matchedIds));
			List<Long> unMatchedIds = new ArrayList<Long>();
			unMatchedIds.add(2l);
			unMatchedIds.add(3l);
			unMatchedIds.add(4l);
			returnVO.setUnMatchedCount(surveyFinalDataDAO.getQcCollectedMatchedUnMatchedDetails(unMatchedIds));
			
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
	public BigPictureVO getTodayTeamDetails()
	{
		BigPictureVO returnVO = new BigPictureVO();
		try 
		{
			DateUtilService dateUtilService = new DateUtilService();
			Date todateDate = dateUtilService.getCurrentDateAndTime();
			List<Object[]> teamDetailsObj = surveyDetailsInfoDAO.getTodayTeamDetails(todateDate);
			
			if(teamDetailsObj != null && teamDetailsObj.size() > 0)
			{
				for (Object[] objects : teamDetailsObj) 
				{
					if((Integer)objects[0] == 1)
					{
						returnVO.setDcVotersCount((Integer)objects[0]);
					}
					else if((Integer)objects[0] == 4)
					{
						returnVO.setVerifierVotersCount((Integer)objects[0]);
					}
					else
					{
						returnVO.setQcVotersCount((Integer)objects[0]);
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
	public List<BigPictureVO> getConstituencyWiseTeamDetails()
	{
		List<BigPictureVO> returnList = null;
		
		try 
		{
			DateUtilService dateUtilService = new DateUtilService();
			Date todateDate = dateUtilService.getCurrentDateAndTime();
			List<Object[]> teamDetailsObj = surveyDetailsInfoDAO.getTeamDetailsInConstituencyLevel(todateDate);
			if(teamDetailsObj != null && teamDetailsObj.size() > 0)
			{
				returnList = new ArrayList<BigPictureVO>();
				for (Object[] objects : teamDetailsObj) 
				{
					BigPictureVO bigPictureVO = new BigPictureVO();
					bigPictureVO.setDcVotersCount(objects[0] != null ? (Integer)objects[0] : 0);// surveyUserType
					bigPictureVO.setQcVotersCount(objects[1] != null ? (Integer)objects[1] : 0);//constituencyId
					bigPictureVO.setDcPercentage(objects[2] != null ?objects[2].toString() : "");//constituency
					bigPictureVO.setDcBoothsCount(objects[3] != null ? (Integer)objects[3] : 0);// total booths
					bigPictureVO.setDcConstituencysCount(objects[4] != null ? (Integer)objects[4] : 0);//total survey users
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
	public List<BigPictureVO> getBoothWiseTeamDetails(Long constituencyId , Long surveyUserTypeId)
	{
		List<BigPictureVO> returnList = null;
		try 
		{
			DateUtilService dateUtilService = new DateUtilService();
			Date todateDate = dateUtilService.getCurrentDateAndTime();
			List<Object[]> teamDetails = surveyDetailsInfoDAO.getTeamDetailsInBoothLevel(constituencyId, surveyUserTypeId, todateDate);
			if(teamDetails != null && teamDetails.size() > 0)
			{
				returnList = new ArrayList<BigPictureVO>();
				for (Object[] objects : teamDetails)
				{
					BigPictureVO bigPictureVO = new BigPictureVO();
					bigPictureVO.setDcVotersCount(objects[0] != null ? (Integer)objects[0] : 0);// booth Id
					bigPictureVO.setQcVotersCount(objects[2] != null ? (Integer)objects[2] : 0);//survey userid
					bigPictureVO.setDcPercentage(objects[1] != null ?objects[1].toString() : "");//partNo
					bigPictureVO.setQcPercentage(objects[3] != null ?objects[3].toString() : "");//user name
					bigPictureVO.setVerifierPercentage(objects[4] != null ?objects[4].toString() : "");// mobile no
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
	public List<BigPictureVO> getConstituencyWiseQcVerificationSummary()
	{
		List<BigPictureVO> returnList = null;
		try
		{
			List<Object[]> constituencyWiseDetils = surveyDetailsInfoDAO.getConstituecySummaryForQc();
			if(constituencyWiseDetils != null && constituencyWiseDetils.size() > 0)
			{
				returnList = new ArrayList<BigPictureVO>();
				for (Object[] objects : constituencyWiseDetils)
				{
					BigPictureVO bigPictureVO = new BigPictureVO();
					bigPictureVO.setDcVotersCount(objects[0] != null ? (Integer)objects[0] : 0);// constituency Id
					bigPictureVO.setQcVotersCount(objects[2] != null ? (Integer)objects[2] : 0);//booths count
					bigPictureVO.setDcPercentage(objects[1] != null ?objects[1].toString() : "");// constituency Name
					bigPictureVO.setVerifierVotersCount(objects[3] != null ? (Integer)objects[3] : 0);
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
}
