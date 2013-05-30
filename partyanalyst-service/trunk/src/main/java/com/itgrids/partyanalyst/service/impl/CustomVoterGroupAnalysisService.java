package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICustomVoterDAO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.service.ICustomVoterGroupAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CustomVoterGroupAnalysisService implements ICustomVoterGroupAnalysisService {
	
	public static Logger Log = Logger.getLogger(CustomVoterGroupAnalysisService.class);
	private ICustomVoterDAO customVoterDAO;
	
	public ICustomVoterDAO getCustomVoterDAO() {
	  return customVoterDAO;
	}
	public void setCustomVoterDAO(ICustomVoterDAO customVoterDAO) {
	  this.customVoterDAO = customVoterDAO;
	}
	
	public List<VoterCastInfoVO> getCasteWiseCustomVotersCount(Long customVoterGroupId,Long userId)
	{
		List<VoterCastInfoVO> castInfoVOsList = null;
		try{
		
		Long totalVoters = 0L;
		 List<Object[]> list = customVoterDAO.getCasteWiseCustomVotersCount(customVoterGroupId, userId);
		 VoterCastInfoVO castInfoVO = null;
		 if(list != null && list.size() > 0)
		 {
			 castInfoVOsList = new ArrayList<VoterCastInfoVO>(0);
			for(Object[] params : list)
			{
				totalVoters = totalVoters+(Long)params[0];
			  String casteName = "";
			  if(params[2] != null)
				  casteName = params[2].toString();
			  else
				  casteName = "N/A";
			  castInfoVO = checkVoterCasteInfoVOExist(casteName,castInfoVOsList);
			  if(castInfoVO == null)
			  {
				  castInfoVO = new VoterCastInfoVO();
				  castInfoVO.setCastName(casteName);
				  castInfoVO.setCasteCategoryName(params[3] != null ?params[3].toString():"");
				  castInfoVOsList.add(castInfoVO);
			  }
			  
			  if(params[1].toString().equalsIgnoreCase(IConstants.MALE))
				  castInfoVO.setMaleVoters((Long)params[0]);
			  else if(params[1].toString().equalsIgnoreCase(IConstants.FEMALE))
				  castInfoVO.setFemaleVoters((Long)params[0]);
			  castInfoVO.setTotalVoters(castInfoVO.getMaleVoters()+castInfoVO.getFemaleVoters());
			  
			}
		 }
		 
		 if(totalVoters > 0 && castInfoVOsList != null && castInfoVOsList.size() > 0)
		 {
			for(VoterCastInfoVO infoVO : castInfoVOsList)
			{
				Double castePercentage = 0.00;
				castePercentage = new BigDecimal((infoVO.getTotalVoters()*100.0)/totalVoters.longValue()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				infoVO.setCastePercentage(castePercentage);
			}
		 }
		 
		 if(castInfoVOsList != null && castInfoVOsList.size() > 0)
		 {
			  Collections.sort(castInfoVOsList,sortCaste);
			  Collections.reverse(castInfoVOsList);
		 }
		 
		 
		 return castInfoVOsList;
		}catch (Exception e) {
			Log.error("Exception Occured in getCasteWiseCustomVotersCount() method, Exception - "+e);
			return castInfoVOsList;
		}
	}
	
	public VoterCastInfoVO checkVoterCasteInfoVOExist(String casteName, List<VoterCastInfoVO> list)
	{
		try{
		 if(list == null || list.size() == 0)
		   return null;
		 for(VoterCastInfoVO castInfoVO : list)
		  if(castInfoVO.getCastName().equalsIgnoreCase(casteName))
			  return castInfoVO;
		  return null;
		}catch (Exception e) {
			e.printStackTrace();
			Log.error("Exception Occured in checkVoterCasteInfoVOExist() method, Exception - "+e);
			return null;
		}
	}
	
	public static Comparator<VoterCastInfoVO> sortCaste = new Comparator<VoterCastInfoVO>()
		    {
		        public int compare(VoterCastInfoVO castVo1, VoterCastInfoVO castVo2)
		        {
		        	 return castVo1.getTotalVoters().intValue()- castVo2.getTotalVoters().intValue();
		        }
		    };
}
