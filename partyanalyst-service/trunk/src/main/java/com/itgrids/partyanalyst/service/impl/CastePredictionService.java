package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICasteInsertTypeDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IPredictedVoterCasteDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.CasteInsertType;
import com.itgrids.partyanalyst.model.CasteState;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.service.ICastePredictionService;

public class CastePredictionService implements ICastePredictionService {
	private static final Logger LOG = Logger.getLogger(CastePredictionService.class);
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IPredictedVoterCasteDAO predictedVoterCasteDAO;
	private IVoterDAO voterDAO;
	private ICasteInsertTypeDAO casteInsertTypeDAO;
	private IUserDAO userDAO;
	private ICasteStateDAO casteStateDAO;
	
	public ICasteStateDAO getCasteStateDAO() {
		return casteStateDAO;
	}
	public void setCasteStateDAO(ICasteStateDAO casteStateDAO) {
		this.casteStateDAO = casteStateDAO;
	}
	public IUserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	public ICasteInsertTypeDAO getCasteInsertTypeDAO() {
		return casteInsertTypeDAO;
	}
	public void setCasteInsertTypeDAO(ICasteInsertTypeDAO casteInsertTypeDAO) {
		this.casteInsertTypeDAO = casteInsertTypeDAO;
	}
	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}
	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}
	public IPredictedVoterCasteDAO getPredictedVoterCasteDAO() {
		return predictedVoterCasteDAO;
	}
	public void setPredictedVoterCasteDAO(
			IPredictedVoterCasteDAO predictedVoterCasteDAO) {
		this.predictedVoterCasteDAO = predictedVoterCasteDAO;
	}
	
	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
		return userVoterDetailsDAO;
	}
	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}
	
	
	public List<VoterVO> getCountForCaste(Long userId)
	{
		
		List<VoterVO>  result = new ArrayList<VoterVO>();
		try{
			List<Object[]> list = userVoterDetailsDAO.getCountForCasteType(userId);
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					VoterVO voterVO = new VoterVO();
					voterVO.setTotalVoters((Long)params[0]);
					voterVO.setType(params[1].toString());
					result.add(voterVO);
				}
			}
			return result;
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getCountForCaste () method in CastePredictionService",e) ;
			return result;
		}
	}
	
	public List<SelectOptionVO> getPredictedCasteList()
	{
		List<SelectOptionVO> predictedCasteList = new ArrayList<SelectOptionVO>(0);
		try{
			List<Object[]> list = predictedVoterCasteDAO.getPredictedCasteList();
			
			if(list != null && list.size() > 0)
				for(Object[] params : list)
					predictedCasteList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
			
			return predictedCasteList;
		}catch(Exception e)
		{
			LOG.error("Exception Occred getPredictedCasteList Method, Exception is ",e);
			return predictedCasteList;
		}
	}
	
	public ResultStatus insertPredictedCasteIntoMainCasteTables(Long userId,Long casteStateId,Integer firstRecord,Integer maxRecords)
	{
		LOG.info("Entered into insertPredictedCasteIntoMainCasteTables() Method ");
		ResultStatus resultStatus = new ResultStatus();
		try{
			List<Long> voterIdsList = new ArrayList<Long>(0);
			/*List<Integer> voterIdsResultList = predictedVoterCasteDAO.getVoterIdsFromCastePrediction(userId,casteStateId,firstRecord,maxRecords);
			
			if(voterIdsResultList != null && voterIdsResultList.size() > 0)
			{
				for(Integer resultId : voterIdsResultList)
					voterIdsList.add(resultId.longValue());
			}*/
			
			List<Long> voterIdsResultList = predictedVoterCasteDAO.getVoterIdsFromCastePredictionForACaste(casteStateId,firstRecord,maxRecords);
			
			if(voterIdsResultList != null && voterIdsResultList.size() > 0)
			{
				for(Long resultId : voterIdsResultList)
					voterIdsList.add(resultId);
			}
			
			if(voterIdsList != null && voterIdsList.size() > 0)
			{
				int fromIndex = 0;
				int toIndex = 1000;
				
				for(;;)
				{
					if(toIndex > voterIdsList.size())
						toIndex = voterIdsList.size();
					LOG.info("From Index --> "+fromIndex+"\tTo Index --> "+toIndex);
					
					List<Long> voterIdsListTemp = voterIdsList.subList(fromIndex, toIndex);
							
					List<Long> matchedVotersList = userVoterDetailsDAO.getMatchtedRecordsForACaste(userId, casteStateId, voterIdsListTemp);
					if(matchedVotersList != null && matchedVotersList.size() > 0)
					{
						Integer updated = userVoterDetailsDAO.updateCasteInsertType(matchedVotersList,3l);
						LOG.info("Matched Voters --> "+updated);
					}
					
					List<Long> unmatchedVotersList = userVoterDetailsDAO.getUnmatchtedRecordsForACaste(userId, casteStateId, voterIdsListTemp);
					if(unmatchedVotersList != null && unmatchedVotersList.size() > 0)
					{
						Integer updated = userVoterDetailsDAO.updateCasteInsertType(unmatchedVotersList,4l);
						LOG.info("Unmatched Voters --> "+updated);
					}
					
					int updatedCount = userVoterDetailsDAO.updateVoterCasteByPrediction(userId, casteStateId,2l,voterIdsListTemp);
					LOG.info("Updated Votrs - "+updatedCount);
					
					List<Long> availableVotersList = userVoterDetailsDAO.getAvailableVoterIdsList(userId, casteStateId, voterIdsListTemp);
					final List<Long> insertVoterIdsList = new ArrayList<Long>(0);
					
					if(availableVotersList != null && availableVotersList.size() > 0)
					{
						for(Long voterId : voterIdsListTemp)
							if(!availableVotersList.contains(voterId))
								insertVoterIdsList.add(voterId);
					}
					
					if(insertVoterIdsList.size() > 0)
					{
						CasteInsertType casteInsertType = casteInsertTypeDAO.get(2l);
						User user = userDAO.get(userId);
						CasteState casteState = casteStateDAO.get(casteStateId);
						
						for(Long voterId : insertVoterIdsList)
						{
							try{
							
							UserVoterDetails userVoterDetails = null;
							userVoterDetails = userVoterDetailsDAO.getUserVoterDetailsByUserIdAndVoterId(userId,voterId); 
							
							if(userVoterDetails == null)
							{
								userVoterDetails = new UserVoterDetails();
								userVoterDetails.setCasteInsertType(casteInsertType);
								userVoterDetails.setUser(user);
								userVoterDetails.setCasteState(casteState);
								userVoterDetails.setVoter(voterDAO.get(voterId));
								if(userVoterDetails.getVoter() != null)
									userVoterDetailsDAO.save(userVoterDetails);
							}
							else if(userVoterDetails.getCasteState() == null)
							{
								userVoterDetails.setCasteState(casteState);
								userVoterDetails.setCasteInsertType(casteInsertType);
								userVoterDetailsDAO.save(userVoterDetails);
							}
							}catch(Exception e)
							{
								LOG.error(e);
							}
						}
						voterDAO.flushAndclearSession();	
					}
					
					fromIndex = fromIndex + 1000;
					toIndex = toIndex + 1000;
					
					if(fromIndex >= voterIdsList.size())
						break;
				}

			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		}catch(Exception e)
		{
			LOG.error("Exception Occred in insertPredictedCasteIntoMainCasteTables() Method");
			LOG.error("Exception is - ",e);
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
	
	public UserVoterDetails insertCasteAndPhoneNumberFromAndroid(Long voterId,Long casteStateId,String phoneNumber){
		User user = userDAO.get(1l);
		CasteInsertType casteInsertType=null;
		CasteState casteState=null;
		if(casteStateId!=null && casteStateId!=0l){
			casteInsertType = casteInsertTypeDAO.get(1l);
			casteState = casteStateDAO.get(casteStateId);
		}
		UserVoterDetails userVoterDetails=null;
		
		try{
			userVoterDetails = new UserVoterDetails();
			userVoterDetails.setUser(user);
			if(casteStateId!=null && casteStateId!=0l){
				userVoterDetails.setCasteInsertType(casteInsertType);
				userVoterDetails.setCasteState(casteState);
			}
			if(phoneNumber.trim()!="" && !phoneNumber.equalsIgnoreCase("null")){
				userVoterDetails.setMobileNo(phoneNumber);
			}
			userVoterDetails.setVoter(voterDAO.get(voterId));
			if(userVoterDetails.getVoter() != null)
				userVoterDetailsDAO.save(userVoterDetails);
			}catch(Exception e)
			{
				System.out.println(e);
			}
		return userVoterDetails;
	}

}
