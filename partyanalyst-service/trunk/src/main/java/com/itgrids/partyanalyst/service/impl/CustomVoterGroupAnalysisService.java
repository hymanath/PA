package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICustomVoterGroupDAO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.dao.ICustomVoterDAO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.service.ICustomVoterGroupAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CustomVoterGroupAnalysisService implements ICustomVoterGroupAnalysisService {
	
	public static Logger Log = Logger.getLogger(CustomVoterGroupAnalysisService.class);
	private ICustomVoterDAO customVoterDAO;
	public ICustomVoterGroupDAO customVoterGroupDAO;


	public ICustomVoterGroupDAO getCustomVoterGroupDAO() {
		return customVoterGroupDAO;
	}
	public void setCustomVoterGroupDAO(ICustomVoterGroupDAO customVoterGroupDAO) {
		this.customVoterGroupDAO = customVoterGroupDAO;
	}
	
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

public static final String AGE1="18to25";
public static final String AGE2="26to35";
public static final String AGE3="36to45";
public static final String AGE4="46to60";
public static final String AGE5="60Above";

 public VotersDetailsVO getAgeWiseCustomVotersInGroup(Long userId,Long customGroupId,Long publicationDateId){
	
	 VotersDetailsVO votersDetailsVO=new VotersDetailsVO();
	 List<Object[]> votersCount1 =customVoterGroupDAO.getVotersCountBasedOnAgeInGroup(userId, customGroupId, AGE1, publicationDateId);
	 setAgewiseVotersFromGroup(votersCount1,AGE1,votersDetailsVO);
	 	  
	 List<Object[]> votersCount2 =customVoterGroupDAO.getVotersCountBasedOnAgeInGroup(userId, customGroupId, AGE2, publicationDateId);
	 setAgewiseVotersFromGroup(votersCount2,AGE2,votersDetailsVO);
	 	  
	 List<Object[]> votersCount3 =customVoterGroupDAO.getVotersCountBasedOnAgeInGroup(userId, customGroupId, AGE3, publicationDateId);
	 setAgewiseVotersFromGroup(votersCount3,AGE3,votersDetailsVO);
	 
	 List<Object[]> votersCount4 =customVoterGroupDAO.getVotersCountBasedOnAgeInGroup(userId, customGroupId, AGE4, publicationDateId);
	 setAgewiseVotersFromGroup(votersCount4,AGE4,votersDetailsVO);
	 
	 List<Object[]> votersCount5 =customVoterGroupDAO.getVotersCountBasedOnAgeInGroup(userId, customGroupId, AGE5, publicationDateId);
	 setAgewiseVotersFromGroup(votersCount5,AGE5,votersDetailsVO);  
	 
	 countPercentages(votersDetailsVO);
	 
		return votersDetailsVO;
	}
 
 
 public void setAgewiseVotersFromGroup(List<Object[]> votersCounts,String age,VotersDetailsVO votersDetailsVO){
	 Long ttlVtrs=0l;
	 
	  for(Object[] obj:votersCounts){
		  if(obj[1].toString().equalsIgnoreCase("F")){
			  if(age.equalsIgnoreCase(AGE1)){
			  votersDetailsVO.setFemaleVotersCountBetween18To25((Long)obj[0]);
			  }
			  if(age.equalsIgnoreCase(AGE2)){
				  votersDetailsVO.setFemaleVotersCountBetween26To35((Long)obj[0]);
			  }
				
			  if(age.equalsIgnoreCase(AGE3)){
				  votersDetailsVO.setFemaleVotersCountBetween36To45((Long)obj[0]);
			  }
				
			  if(age.equalsIgnoreCase(AGE4)){
				  votersDetailsVO.setFemaleVotersCountBetween46To60((Long)obj[0]);
			  }
				
			  if(age.equalsIgnoreCase(AGE5)){
				  votersDetailsVO.setFemaleVotersCountAbove60((Long)obj[0]);
			  }
			  
			  ttlVtrs+=(Long)obj[0];
		  }
		  else if(obj[1].toString().equalsIgnoreCase("M")){
			  if(age.equalsIgnoreCase(AGE1)){
			  votersDetailsVO.setMaleVotersCountBetween18To25((Long)obj[0]);
			  }
			  if(age.equalsIgnoreCase(AGE2)){
				  votersDetailsVO.setMaleVotersCountBetween26To35((Long)obj[0]);
			  }
			  if(age.equalsIgnoreCase(AGE3)){
				  votersDetailsVO.setMaleVotersCountBetween36To45((Long)obj[0]);
			  }
			  if(age.equalsIgnoreCase(AGE4)){
				  votersDetailsVO.setMaleVotersCountBetween46To60((Long)obj[0]);
			  }
				
			  if(age.equalsIgnoreCase(AGE5)){
				  votersDetailsVO.setMaleVotersCountAbove60((Long)obj[0]);
			  }
			  ttlVtrs+=(Long)obj[0];
		  }
	  }
	  	  if(age.equalsIgnoreCase(AGE1)){
	  		  votersDetailsVO.setTotalVotersFor18To25(ttlVtrs!=null ? ttlVtrs :0l);
	  	  }
		  if(age.equalsIgnoreCase(AGE2)){
			  votersDetailsVO.setTotalVotersFor26To35(ttlVtrs!=null ? ttlVtrs :0l);
		  }
		  if(age.equalsIgnoreCase(AGE3)){
			  votersDetailsVO.setTotalVotersFor36To45(ttlVtrs!=null ? ttlVtrs :0l);
		  }
		  if(age.equalsIgnoreCase(AGE4)){
			  votersDetailsVO.setTotalVotersFor46To60(ttlVtrs!=null ? ttlVtrs :0l);
		  }
		  if(age.equalsIgnoreCase(AGE5)){
			  votersDetailsVO.setTotalVotersForAbove60(ttlVtrs!=null ? ttlVtrs :0l);
		  }
		  
		 
 }
 	public void countPercentages(VotersDetailsVO vo){
 		Long totalVotersOfGroup=0l;
 		if(vo.getTotalVotersFor18To25()!=null){
 			totalVotersOfGroup+=vo.getTotalVotersFor18To25();
 		}
 		if(vo.getTotalVotersFor26To35()!=null){
 			totalVotersOfGroup+=vo.getTotalVotersFor26To35();
 		}
 		if(vo.getTotalVotersFor36To45()!=null){
 			totalVotersOfGroup+=vo.getTotalVotersFor36To45();
 		}
 		if(vo.getTotalVotersFor46To60()!=null){
 			totalVotersOfGroup+=vo.getTotalVotersFor46To60();
 		}
 		if(vo.getTotalVotersForAbove60()!=null){
 			totalVotersOfGroup+=vo.getTotalVotersForAbove60();
 		}
 		
 			vo.setVotersPercentFor18To25(changeToPercentageString(vo.getTotalVotersFor18To25(),totalVotersOfGroup));
 		vo.setFemaleVotersPercentFor18To25(changeToPercentageString(vo.getFemaleVotersCountBetween18To25(),vo.getTotalVotersFor18To25()));
 		vo.setMaleVotersPercentFor18To25(changeToPercentageString(vo.getMaleVotersCountBetween18To25(),vo.getTotalVotersFor18To25()));
 		
 		vo.setVotersPercentFor26To35(changeToPercentageString(vo.getTotalVotersFor26To35(),totalVotersOfGroup));
 		vo.setFemaleVotersPercentFor26To35(changeToPercentageString(vo.getFemaleVotersCountBetween26To35(),vo.getTotalVotersFor26To35()));
 		vo.setMaleVotersPercentFor26To35(changeToPercentageString(vo.getMaleVotersCountBetween26To35(),vo.getTotalVotersFor26To35()));
 		
 		vo.setVotersPercentFor36To45(changeToPercentageString(vo.getTotalVotersFor36To45(),totalVotersOfGroup));
 		vo.setFemaleVotersPercentFor36To45(changeToPercentageString(vo.getFemaleVotersCountBetween36To45(),vo.getTotalVotersFor36To45()));
 		vo.setMaleVotersPercentFor36To45(changeToPercentageString(vo.getMaleVotersCountBetween36To45(),vo.getTotalVotersFor36To45()));
 		
 		vo.setVotersPercentFor46To60(changeToPercentageString(vo.getTotalVotersFor46To60(),totalVotersOfGroup));
 		vo.setFemaleVotersPercentFor46To60(changeToPercentageString(vo.getFemaleVotersCountBetween46To60(),vo.getTotalVotersFor46To60()));
 		vo.setMaleVotersPercentFor46To60(changeToPercentageString(vo.getMaleVotersCountBetween46To60(),vo.getTotalVotersFor46To60()));
 		
 		vo.setVotersPercentForAbove60(changeToPercentageString(vo.getTotalVotersForAbove60(),totalVotersOfGroup));
 		vo.setFemaleVotersPercentForAbove60(changeToPercentageString(vo.getFemaleVotersCountAbove60(),vo.getTotalVotersForAbove60()));
 		vo.setMaleVotersPercentForAbove60(changeToPercentageString(vo.getMaleVotersCountAbove60(),vo.getTotalVotersForAbove60()));
 	
}
 	
 	public String changeToPercentageString(Long numerator,Long denominator){
 		String result="";
 		Long nmrtor=0l;
 		nmrtor= numerator!=null?numerator:0l;
 		
 		if(denominator!=0){
 		result=new BigDecimal((new Double(nmrtor)*100)/denominator).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
 		}
 		else{
 			result="0.00";
 		}
 		return result;
 	}
}





 
