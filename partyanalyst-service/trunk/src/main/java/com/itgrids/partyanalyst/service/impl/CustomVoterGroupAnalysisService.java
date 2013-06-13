package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.ICustomVoterDAO;
import com.itgrids.partyanalyst.dao.ICustomVoterGroupDAO;
import com.itgrids.partyanalyst.dao.IInfluencingPeopleDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterCategoryValueDAO;
import com.itgrids.partyanalyst.dto.ImportantFamiliesInfoVo;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterDataVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.VoterInfo;
import com.itgrids.partyanalyst.service.ICustomVoterGroupAnalysisService;
import com.itgrids.partyanalyst.service.IVoterReportService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.model.Voter;

public class CustomVoterGroupAnalysisService implements ICustomVoterGroupAnalysisService {
	
	public static Logger Log = Logger.getLogger(CustomVoterGroupAnalysisService.class);
	private ICustomVoterDAO customVoterDAO;
	private IVoterCategoryValueDAO voterCategoryValueDAO;
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IInfluencingPeopleDAO influencingPeopleDAO;
	private ICadreDAO cadreDAO;
	private ICandidateDAO candidateDAO;
	
	private IVoterReportService voterReportService;
	
	private ICustomVoterGroupDAO customVoterGroupDAO;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	
	public IVoterReportService getVoterReportService() {
		return voterReportService;
	}
	public void setVoterReportService(IVoterReportService voterReportService) {
		this.voterReportService = voterReportService;
	}
	public ICandidateDAO getCandidateDAO() {
		return candidateDAO;
	}
	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}
	public IInfluencingPeopleDAO getInfluencingPeopleDAO() {
		return influencingPeopleDAO;
	}
	public void setInfluencingPeopleDAO(IInfluencingPeopleDAO influencingPeopleDAO) {
		this.influencingPeopleDAO = influencingPeopleDAO;
	}
	public ICadreDAO getCadreDAO() {
		return cadreDAO;
	}
	public void setCadreDAO(ICadreDAO cadreDAO) {
		this.cadreDAO = cadreDAO;
	}
	public IVoterCategoryValueDAO getVoterCategoryValueDAO() {
		return voterCategoryValueDAO;
	}
	public void setVoterCategoryValueDAO(
			IVoterCategoryValueDAO voterCategoryValueDAO) {
		this.voterCategoryValueDAO = voterCategoryValueDAO;
	}
	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
		return userVoterDetailsDAO;
	}
	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}
	public ICustomVoterDAO getCustomVoterDAO() {
	  return customVoterDAO;
	}
	public void setCustomVoterDAO(ICustomVoterDAO customVoterDAO) {
	  this.customVoterDAO = customVoterDAO;
	}
	
	public ICustomVoterGroupDAO getCustomVoterGroupDAO() {
		return customVoterGroupDAO;
	}
	public void setCustomVoterGroupDAO(ICustomVoterGroupDAO customVoterGroupDAO) {
		this.customVoterGroupDAO = customVoterGroupDAO;
	}
	
	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}
	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
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
				  castInfoVO.setCasteStateId((Long)params[4]);
				  castInfoVO.setCasteId((Long)params[5]);
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
    public List<VoterCastInfoVO> getCustomGroupWiseVoterCasteDetails(Long userId,String areaType,Long locationValue)
    {
    	
    	List<VoterCastInfoVO> castInfoVOsList = null;
    	Map<String,Long> votersMap = new HashMap<String,Long>();
    	Map<Long,Long> groupMap = new HashMap<Long, Long>();
    	
    	try{
    		List<Object[]> list = customVoterDAO.getCustomGroupWiseVotersDetailsForCaste(userId,areaType,locationValue);
    		
    		if(list != null && list.size() > 0)
    		{
    			VoterCastInfoVO castInfoVO = null;
    		  castInfoVOsList = new ArrayList<VoterCastInfoVO>(0);
    		  for(Object[] params : list)
    		  {
    			 castInfoVO = checkVoterCastInfoVOExist((Long)params[1], (Long)params[3], castInfoVOsList);
    			 if(castInfoVO == null)
    			 {
    			   castInfoVO = new VoterCastInfoVO();
    			   castInfoVO.setId((Long)params[1]);
 				   castInfoVO.setName(params[2] != null ?params[2].toString():" ");
 				   castInfoVO.setCasteStateId((Long)params[3]);
 				   castInfoVO.setCasteCategoryName(params[6] != null ?params[6].toString():"");
 				   castInfoVO.setCastName(params[4] != null ?params[4].toString():"N/A");
 				   castInfoVO.setCasteId((Long)params[7]);
 				   
 				   castInfoVOsList.add(castInfoVO);
    			 }
 				 if(params[5] != null && params[5].toString().equalsIgnoreCase(IConstants.MALE))
 				  castInfoVO.setMaleVoters((Long)params[0]);
 				 else if(params[5] != null && params[5].toString().equalsIgnoreCase(IConstants.FEMALE))
    			  castInfoVO.setFemaleVoters((Long)params[0]);
    			
    			 if(votersMap.get(params[4].toString()) == null)
    				 votersMap.put(params[4].toString(), (Long)params[0]);
    			 else if(votersMap.get(params[4].toString()) != null)
    				 votersMap.put(params[4].toString(), votersMap.get(params[4].toString())+(Long)params[0]);
    			 
    			 if(groupMap.get((Long)params[1]) == null)
    				 groupMap.put((Long)params[1], (Long)params[0]);
    			 else if(groupMap.get((Long)params[1]) != null)
    				 groupMap.put((Long)params[1], groupMap.get((Long)params[1]) + (Long)params[0]);
    			 
    		  }
    		  
    		  for(VoterCastInfoVO infoVO : castInfoVOsList)
    			infoVO.setTotalVoters(infoVO.getMaleVoters()+infoVO.getFemaleVoters());
    		  
    		  for(VoterCastInfoVO infoVO : castInfoVOsList)
    		  {
    			 Double percentage = 0.00;
    			 percentage = new BigDecimal((infoVO.getTotalVoters()*100.0)/votersMap.get(infoVO.getCastName()).longValue()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
 				 infoVO.setCastePercentage(percentage);
 				 infoVO.setGroupWiseTotalVoters(groupMap.get(infoVO.getId()));
    		  }
    		  
    		      		  
    		}
    		
    		return castInfoVOsList;
    	}catch (Exception e) {
    		e.printStackTrace();
    		Log.error("Exception Occured in getCustomGroupWiseVoterCasteDetails() method, Exception - "+e);
    		return castInfoVOsList;
		}
    }
    
    
	public VoterCastInfoVO checkVoterCastInfoVOExist(Long groupId,Long casteStateId, List<VoterCastInfoVO> list)
	{
		try{
			
			if(list == null || list.size() == 0)
				return null;
			for(VoterCastInfoVO castInfoVO : list)
			  if(castInfoVO.getId().equals(groupId) && castInfoVO.getCasteStateId().equals(casteStateId))
				return castInfoVO;
			
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			Log.error("Exception Occured in checkVoterCastInfoVOExist() method, Exception - "+e);
			return null;
		}
	}
	
	public VoterCastInfoVO checkSelectOptionVOExists(Long id,List<VoterCastInfoVO> list)
	{
		try{
			if(list == null || list.size() == 0)
				return null;
			for(VoterCastInfoVO optionVO : list)
			  if(optionVO.getId().equals(id))
				return optionVO;
			
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			Log.error("Exception Occured in checkSelectOptionVOExists() method,Exception - "+e);
			return null;
		}
	}
	
	public String getGroupNameByGroupId(Long customVoterGroupId)
	{
		try{
			String name = "";
			name = customVoterGroupDAO.getCustomVoterGroupNameByGroupId(customVoterGroupId);
			return name;
			
		}catch (Exception e) {
			e.printStackTrace();
			Log.error("Exception Occured in getGroupNameByGroupId() method, Exception - "+e);
			return null;
		}
	}
	
	public List<VoterHouseInfoVO> getCustomVoterDetails(Long casteStateId, Long casteId, Long customVoterGroupId, Long userId)
	{
		List<VoterHouseInfoVO> houseInfoVOsList = new ArrayList<VoterHouseInfoVO>(0);
		try{
		
		 List<Voter> votersList = customVoterDAO.getCasteWiseCustomVoterDetails(casteStateId, casteId, customVoterGroupId, userId);
		 
		 if(votersList != null && votersList.size() > 0)
		 {
			for(Voter voter:votersList)
			{
			  VoterHouseInfoVO houseInfoVO = new VoterHouseInfoVO();
			  houseInfoVO.setVoterId(voter.getVoterId());
			  houseInfoVO.setName(voter.getName() != null ? voter.getName() :" ");
			  houseInfoVO.setAge(voter.getAge());
			  houseInfoVO.setGender(voter.getGender()!= null ? voter.getGender():" ");
			  houseInfoVO.setHouseNo(voter.getHouseNo() != null? voter.getHouseNo():" ");
			  houseInfoVO.setRelationship(voter.getRelationshipType()!= null ?voter.getRelationshipType():" ");
			  houseInfoVO.setGaurdian(voter.getRelativeName() != null ?voter.getRelativeName():" ");
			  houseInfoVO.setVoterIdCardNo(voter.getVoterIDCardNo() != null ?voter.getVoterIDCardNo():" ");
			  houseInfoVO.setMobileNo(voter.getMobileNo() != null?voter.getMobileNo():"N/A");
			  houseInfoVOsList.add(houseInfoVO);
			}
		 }
		 return houseInfoVOsList;
		}catch (Exception e) {
		 Log.error("Exception Occured in getCustomVoterDetails() method, Exception - "+e);
		 return null;
		}
	}
		    

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
 		vo.setTotalVoters(totalVotersOfGroup);
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
 	/**this method is used to Get Voter Data By CustomGroupId **/
		    public List<VoterVO> getVoterDetailsForCustomVoterGroup(Long customvoterGroupId,Integer startIndex , Integer maxRecords , String order,
		    		String columnName,Long userId,Long publicationDateId)
		    {
		    	
		    	List<VoterVO> result = new ArrayList<VoterVO>();
		    	Long totalCount =0l;
		    	List<Object[]> voterdata = new ArrayList<Object[]>();
		    	VoterVO voterVO = null;
		    	Map<Long,VoterVO> voters = new HashMap<Long, VoterVO>();
		    	List<Long> voterIds = new ArrayList<Long>();
		    	
		    	try{
		    		
		    	if(columnName != null && columnName.equalsIgnoreCase("firstName"))
		    	{
		    		columnName = "name";
		    	}
		    	if(columnName != null && columnName.equalsIgnoreCase("relativeFirstName"))
		    	{
		    		columnName = "relativeName";
		    	}
		    	List<Long> total = customVoterDAO.getCountBycustomvoterGroupId(customvoterGroupId,userId,publicationDateId);	
		    	
		    	voterdata = customVoterDAO.getVotersInfoBycustomVoterGroupId1(customvoterGroupId,userId,startIndex,maxRecords,
		    			order,columnName,publicationDateId);	
		    	result = setDataToVoterVo(voterdata, userId, null, null);
		    	if(total != null && total.size() > 0)
		    	{
		    		totalCount = new Long(total.size());
		    		result.get(0).setTotalVoters(totalCount);
		    	}
		    	
		    	
		    	}
		    	
		    	catch(Exception e)
		    	{
		    	Log.error("Exception Occured in getVoterDetailsForCustomVoterGroup() method" +e);	
		    	}
		    	
		    	
		    	return result;
		    	
		    }

		   /**this method is used to Get Voter Data By CustomGroupId **/
		    public List<VoterVO> getVoterDataForCustomGroup(VoterDataVO voterDataVO,Long userId,List<Long> categories)
		    {
		    	
		    	List<VoterVO> result = new ArrayList<VoterVO>();
		    	Long totalCount =0l;
		    	List<Object[]> voterdata = new ArrayList<Object[]>();
		    	VoterVO voterVO = null;
		    	Map<Long , VoterVO> voterMap = new HashMap<Long, VoterVO>();
		    	List<Long> voterIds = new ArrayList<Long>();
		    try{
		    	List<Long> total = customVoterDAO.getCountBycustomvoterGroupId(voterDataVO.getCustomVoterGroupId(),userId,voterDataVO.getPublicationId());	
		    	
		    	voterdata = customVoterDAO.getVotersInfoBycustomVoterGroupId1(voterDataVO.getCustomVoterGroupId(),userId,voterDataVO.getStartIndex().intValue(),voterDataVO.getMaxIndex().intValue(),
		    			 voterDataVO.getDir(),voterDataVO.getSort(),voterDataVO.getPublicationId());	
		    	result = setDataToVoterVo(voterdata, userId, voterDataVO, categories);
		    	if(total != null && total.size() > 0)
		    	{
		    		totalCount = new Long(total.size());
		    		result.get(0).setTotalVoters(totalCount);
		    	}
		    }
		    catch(Exception e)
		    {
		    	Log.error("Exception Occured in getVoterDataForCustomGroup() of CustomVoterGroupAnalysis Service Exception - ",e);
		    }
		    	return result;
		    	
		    }
		    /** this method is used get TotalVoters,Male,Female Voters By CustomGroupId **/
		    public VoterInfo getTotalVotersDetailsbyCustomVoterGroup(Long userId,Long customVoterGroupId,Long publicationId)
		    {
		    	VoterInfo voterInfo = new VoterInfo();
		    	List<Object[]> list = null;
		    	Long maleVoters = 0l;
		    	Long femaleVoters = 0l;
		    	Long total = 0l;
		    
		    try{
		    	list = customVoterDAO.getCustomVotersCount(customVoterGroupId,userId,publicationId);
		    	if(list != null && list.size() > 0)
		    	{
		    		for(Object[] params : list)
		    		{
		    			if(params[1].toString().equalsIgnoreCase("F"))
		    				femaleVoters = femaleVoters+ (Long)params[0];
		    			else
		    				maleVoters =  maleVoters + (Long)params[0];;
		    		}
		    	}
		    	total = maleVoters + femaleVoters;
		    	voterInfo.setMaleVoters(maleVoters);
		    	voterInfo.setFemaleVoters(femaleVoters);
		    	voterInfo.setTotalVoters(total);
		    
		    }
		   
		    catch (Exception e) {
				Log.error("Exception Occured in getTotalVotersDetailsbyCustomVoterGroup() of customVoterGroupAnalysis Exception - ",e);
			}
			return voterInfo;
			
			
		    }
		   /** this method is used To get Influence,Cadre,Politician People Count **/ 
		    public InfluencingPeopleBeanVO getInfluencingPeopleCount(Long userId,Long customVoterGroupId,Long publicationDateId)
		    {
		    	
		    	List<Long> influencePeople = new ArrayList<Long>();
		    	List<Long> cadrePeople = new ArrayList<Long>();
		    	List<Long> politician = new ArrayList<Long>();
		    	InfluencingPeopleBeanVO influencingPeopleBeanVO = new InfluencingPeopleBeanVO();
		    	try{
		    		influencePeople = customVoterDAO.getInfluencingPeopleCountByCustomVoter(userId, publicationDateId, customVoterGroupId);
		    		cadrePeople = customVoterDAO.getCadrePeopleCountByCustomVoter(userId, publicationDateId, customVoterGroupId);
		    		politician = customVoterDAO.getPoliticianCountByCustomVoter(userId, publicationDateId, customVoterGroupId);
		    		if(influencePeople != null && influencePeople.size() > 0)
		    		influencingPeopleBeanVO.setInfluencePeopleCount(new Long(influencePeople.get(0)));
		    		else
		    			influencingPeopleBeanVO.setInfluencePeopleCount(0l);	
		    		if(cadrePeople != null && cadrePeople.size() > 0)
			    		influencingPeopleBeanVO.setCadreCount(new Long(cadrePeople.get(0)));
			    		else
			    			influencingPeopleBeanVO.setCadreCount(0l);	
		    		if(politician != null && politician.size() > 0)
			    		influencingPeopleBeanVO.setPoliticianCount(new Long(politician.get(0)));
			    		else
			    			influencingPeopleBeanVO.setPoliticianCount(0l);	
		    	}
		    	catch(Exception e)
		    	{
		    		Log.error("Exception Occured in getInfluencingPeopleCount() method of CustomVoterGroupAnalysis Exception - ",e);
		    	}
				return influencingPeopleBeanVO;
		    }
		   /** this method is used To get influence,cadre,Politician Details By customVoterGroupId **/
		    public List<VoterVO> showVoterDetailsForSelcetedType(Long userId,Long customVoterGroupId,Long publicationDateId,String btnName,Integer startIndex,Integer maxRecords,String order,String columnName)
		    {
		    List<VoterVO> result = null;
		    List<Object[]> list  = null;
		    List<Long> influencePeople = new ArrayList<Long>();
	    	List<Long> cadrePeople = new ArrayList<Long>();
	    	List<Long> politician = new ArrayList<Long>();
		    try{
		    if(btnName.equalsIgnoreCase("InfluencePeople"))
		    {
		    	Long inftotal =0l;
		    	influencePeople = customVoterDAO.getInfluencingPeopleCountByCustomVoter(userId, publicationDateId, customVoterGroupId);
		    	list = customVoterDAO.getInfluencingPeopleDetails(userId,publicationDateId,customVoterGroupId,startIndex,maxRecords,order,columnName);
		    	if(influencePeople != null && influencePeople.size() > 0)
		    	 inftotal = influencePeople.get(0).longValue();
		    	result = setValuesToVOterVO(list,btnName,inftotal,userId);
		    }
		    else if(btnName.equalsIgnoreCase("Cadre"))
		    {
		    	Long cadreCount = 0l;
		    	list = customVoterDAO.getCadreDetails(userId,publicationDateId,customVoterGroupId,startIndex,maxRecords,order,columnName);
		    	cadrePeople = customVoterDAO.getCadrePeopleCountByCustomVoter(userId, publicationDateId, customVoterGroupId);
		    	if(cadrePeople != null && cadrePeople.size() > 0)
		        cadreCount = cadrePeople.get(0).longValue();
		    	result = setValuesToVOterVO(list,btnName,cadreCount,userId);
		    }
		    else if(btnName.equalsIgnoreCase("Politician"))
		    {
		    	Long politicainCount = 0l;
		    	list = customVoterDAO.getPoliticanDetails(userId,publicationDateId,customVoterGroupId,startIndex,maxRecords,order,columnName);
		    	politician = customVoterDAO.getPoliticianCountByCustomVoter(userId, publicationDateId, customVoterGroupId);
		    	if(politician != null && politician.size() > 0)
		        politicainCount = politician.get(0).longValue();
		    	result= setValuesToVOterVO(list,btnName,politicainCount,userId);
		    }
		    	
		    }
		    catch(Exception e)
		    {
		    	e.printStackTrace();
		    	Log.error("Exception Occured in showVoterDetailsForSelcetedType() method of customVoterGroupAnalysis Exception - " ,e);
		    }
			return result;
		    }
		    /** this method is used To get influence,cadre,Politician Details By customVoterGroupId **/
		    public List<VoterVO> showVoterDetailsForSelcetedTypeByCasteAndCategoryId(Long userId,VoterDataVO voterDataVO,String btnName)
		    {
		    List<VoterVO> result = null;
		    List<InfluencingPeople> influencingData  = null;
		    List<Cadre> cadreData = null;
		    List<Candidate> candidateData = null;
		    List<Long> influencePeople = new ArrayList<Long>();
	    	List<Long> cadrePeople = new ArrayList<Long>();
	    	List<Long> politician = new ArrayList<Long>();
	    	try{
		    if(btnName.equalsIgnoreCase("InfluencePeople"))
		    {
		    	Long inftotal =0l;
		    	influencePeople =boothPublicationVoterDAO.getInfluencingPeopleCountByCategoryAndCaste(voterDataVO.getCategoryId(), voterDataVO.getCasteId(), voterDataVO.getId(),voterDataVO.getPublicationId(),voterDataVO.getBuildType(),userId,voterDataVO.getGender(),"InfluencePeople");
		    	influencingData = boothPublicationVoterDAO.getInfluencingPeopleDataByCategoryAndCaste(voterDataVO.getCategoryId(),voterDataVO.getCasteId(),voterDataVO.getId(),voterDataVO.getPublicationId(),voterDataVO.getBuildType(),userId,voterDataVO.getGender(),"InfluencePeople",voterDataVO.getStartIndex().intValue(),voterDataVO.getMaxIndex().intValue(),voterDataVO.getDir(),voterDataVO.getSort());
		    	if(influencePeople != null && influencePeople.size() > 0)
			    	 inftotal = influencePeople.get(0).longValue();
		    	result = voterReportService.storeInfluencingPeopleDetails(influencingData,voterDataVO.getBuildType(),voterDataVO.getCategoryId(),inftotal,userId);
		    	//list = boothPublicationVoterDAO.getInfluencingPeopleDataByCategoryAndCaste(voterDataVO.getCategoryId(),voterDataVO.getCasteId(),voterDataVO.getId(),voterDataVO.getPublicationId(),voterDataVO.getBuildType(),userId,voterDataVO.getGender(),"InfluencePeople",voterDataVO.getStartIndex().intValue(),voterDataVO.getMaxIndex().intValue(),voterDataVO.getDir(),voterDataVO.getSort());
		    	
		    	//result = setValuesToVOterVOForAttribute(list,btnName,inftotal,userId);
		    }
		    else if(btnName.equalsIgnoreCase("Cadre"))
		    	 {
		    	Long cadreCount = 0l;
		    	//list = boothPublicationVoterDAO.getInfluencingPeopleDataByCategoryAndCaste(voterDataVO.getCategoryId(),voterDataVO.getCasteId(),voterDataVO.getId(),voterDataVO.getPublicationId(),voterDataVO.getBuildType(),userId,voterDataVO.getGender(),"Cadre",voterDataVO.getStartIndex().intValue(),voterDataVO.getMaxIndex().intValue(),voterDataVO.getDir(),voterDataVO.getSort());
		    	cadrePeople = boothPublicationVoterDAO.getInfluencingPeopleCountByCategoryAndCaste(voterDataVO.getCategoryId(), voterDataVO.getCasteId(), voterDataVO.getId(),voterDataVO.getPublicationId(),voterDataVO.getBuildType(),userId,voterDataVO.getGender(),"Cadre");
		    	if(cadrePeople != null && cadrePeople.size() > 0)
		        cadreCount = cadrePeople.get(0).longValue();
		    	//result = setValuesToVOterVOForAttribute(list,btnName,cadreCount,userId);
		    	cadreData = boothPublicationVoterDAO.getCadreDataByCategoryAndCaste(voterDataVO.getCategoryId(),voterDataVO.getCasteId(),voterDataVO.getId(),voterDataVO.getPublicationId(),voterDataVO.getBuildType(),userId,voterDataVO.getGender(),"Cadre",voterDataVO.getStartIndex().intValue(),voterDataVO.getMaxIndex().intValue(),voterDataVO.getDir(),voterDataVO.getSort());
		    	result = voterReportService.storeCadrePeopleDetails(cadreData,voterDataVO.getBuildType(),voterDataVO.getCategoryId(),cadreCount,userId);
		    }
		    else if(btnName.equalsIgnoreCase("Politician"))
		    {
		    	Long politicainCount = 0l;
		    	//list = boothPublicationVoterDAO.getInfluencingPeopleDataByCategoryAndCaste(voterDataVO.getCategoryId(),voterDataVO.getCasteId(),voterDataVO.getId(),voterDataVO.getPublicationId(),voterDataVO.getBuildType(),userId,voterDataVO.getGender(),"Politician",voterDataVO.getStartIndex().intValue(),voterDataVO.getMaxIndex().intValue(),voterDataVO.getDir(),voterDataVO.getSort());
		    	politician = boothPublicationVoterDAO.getInfluencingPeopleCountByCategoryAndCaste(voterDataVO.getCategoryId(), voterDataVO.getCasteId(), voterDataVO.getId(),voterDataVO.getPublicationId(),voterDataVO.getBuildType(),userId,voterDataVO.getGender(),"Politician");
		    	if(politician != null && politician.size() > 0)
		        politicainCount = politician.get(0).longValue();
		    	//result= setValuesToVOterVOForAttribute(list,btnName,politicainCount,userId);
		    	candidateData = boothPublicationVoterDAO.getPoliticianDataByCategoryAndCaste(voterDataVO.getCategoryId(),voterDataVO.getCasteId(),voterDataVO.getId(),voterDataVO.getPublicationId(),voterDataVO.getBuildType(),userId,voterDataVO.getGender(),"Politician",voterDataVO.getStartIndex().intValue(),voterDataVO.getMaxIndex().intValue(),voterDataVO.getDir(),voterDataVO.getSort());
		    	result = voterReportService.storeCandidateDetails(candidateData,voterDataVO.getBuildType(),voterDataVO.getCategoryId(),politicainCount);
		    }
		    	
		    }
		    catch(Exception e)
		    {
		    	e.printStackTrace();
		    	Log.error("Exception Occured in showVoterDetailsForSelcetedType() method of customVoterGroupAnalysis Exception - " ,e);
		    }
			return result;
		    }
		    
		    public List<VoterVO> setValuesToVOterVO(List<Object[]> list,String btnName,Long total,Long userId)
		    {
		    	
		    	List<VoterVO> result = new ArrayList<VoterVO>(0);
		    	VoterVO voterVO = null;
		    	Map<Long,VoterVO> casteMap = new HashMap<Long, VoterVO>();
		    	
		    	try{
		    	Long count = 1l;
		    	if(list != null && list.size() > 0)
		    	{
		    		
		    		for(Object[] params : list)
		    		{
		    			
		    			voterVO = new VoterVO();
						voterVO.setVoterId((Long.valueOf(count).toString()));
						voterVO.setFirstName(params[1]!= null ? params[1].toString():" ");
						voterVO.setVoterIDCardNo(params[7]!=null?params[7].toString():" ");
						voterVO.setGender(params[2]!=null ? params[2].toString():" ");
						voterVO.setAge((Long)params[3]);
						voterVO.setHouseNo(params[4]!=null?params[4].toString():" ");
						voterVO.setRelativeFirstName(params[5]!=null?params[5].toString():" ");
						voterVO.setMobileNo(params[6]!=null ?params[6].toString(): " ");
						voterVO.setLocalArea(params[8]!=null?params[8].toString() : " ");
						voterVO.setTotalVoters(total);
						casteMap.put((Long)params[0], voterVO);
						/*if(btnName.equalsIgnoreCase("Cadre"))
						{
							voterVO.setCast(params[9] != null ? params[9].toString() : " ");
						}*/
						if(btnName.equalsIgnoreCase("InfluencePeople"))
						{
						voterVO.setInfluencingRange(params[9].toString());
						voterVO.setInfluencingRegion(voterReportService.getRegionNameBasedOnScope(params[9].toString(),params[10].toString()));
						}
						++count;
						result.add(voterVO);
		    		}
		    		
		    	}
		    	List<Long> voterIds = new ArrayList<Long>(casteMap.keySet());
		    		if(voterIds != null && voterIds.size() > 0)
		    		{	
		    		List<Object[]> voterCast = userVoterDetailsDAO.getcasteForVoter(voterIds, userId);
		    		if(voterCast != null && voterCast.size() > 0)
		    		{
		    			for(Object[] params : voterCast)
		    			{
		    			voterVO = casteMap.get(params[1]);
		    			voterVO.setCast(params[0]!= null ?params[0].toString() : " ");
		    			}
		    		}
		    		}
		    	
		    	}catch(Exception e)
		    	{
		    		Log.error("Exception Occured in setValuesToVOterVO() of customVoterGroupAnalysis Exception - ",e);
		    	}
				return result;
		    }
		    public List<VoterVO> setValuesToVOterVOForAttribute(List<Object[]> list,String btnName,Long total,Long userId)
		    {
		    	
		    	List<VoterVO> result = new ArrayList<VoterVO>(0);
		    	VoterVO voterVO = null;
		    	Map<Long,VoterVO> casteMap = new HashMap<Long, VoterVO>();
		    	
		    	try{
		    	Long count = 1l;
		    	if(list != null && list.size() > 0)
		    	{
		    		for(Object[] params : list)
		    		{
		    			Voter voter = (Voter)params[0];
		    			voterVO = new VoterVO();
						voterVO.setVoterId((Long.valueOf(count).toString()));
						voterVO.setFirstName(voter.getName()!= null ? voter.getName().toString():" ");
						voterVO.setVoterIDCardNo(voter.getVoterIDCardNo()!=null?voter.getVoterIDCardNo().toString():" ");
						voterVO.setGender(voter.getGender()!=null ? voter.getGender().toString():" ");
						voterVO.setAge(voter.getAge());
						voterVO.setHouseNo(voter.getHouseNo()!=null?voter.getHouseNo().toString():" ");
						voterVO.setRelativeFirstName(voter.getRelativeName()!=null?voter.getRelativeName().toString():" ");
						voterVO.setMobileNo(voter.getMobileNo()!=null ?voter.getMobileNo().toString(): " ");
						voterVO.setLocalArea(params[1].toString()+" "+ params[2].toString());
						voterVO.setTotalVoters(total);
						casteMap.put(voter.getVoterId(), voterVO);
						
						if(btnName.equalsIgnoreCase("InfluencePeople"))
						{
						voterVO.setInfluencingRange(params[3].toString());
						voterVO.setInfluencingRegion(voterReportService.getRegionNameBasedOnScope(params[3].toString(),params[4].toString()));
						}
						++count;
						result.add(voterVO);
		    		}
		    		
		    	}
		    	List<Long> voterIds = new ArrayList<Long>(casteMap.keySet());
		    		if(voterIds != null && voterIds.size() > 0)
		    		{	
		    		List<Object[]> voterCast = userVoterDetailsDAO.getcasteForVoter(voterIds, userId);
		    		if(voterCast != null && voterCast.size() > 0)
		    		{
		    			for(Object[] params : voterCast)
		    			{
		    			voterVO = casteMap.get(params[1]);
		    			voterVO.setCast(params[0]!= null ?params[0].toString() : " ");
		    			}
		    		}
		    		}
		    	
		    	}catch(Exception e)
		    	{
		    		Log.error("Exception Occured in setValuesToVOterVO() of customVoterGroupAnalysis Exception - ",e);
		    	}
				return result;
		    }
		    
		    /**
		     * This Method is Uesd For getting Voters count For a Party  Based on CustomVoterGroupId
		     *  
		     * @param Long custGroupId
		     * @param Long  userId
		     * @return VoterCastInfoVO
		     */

		    public	VoterCastInfoVO getVotersCountForPartyByCustomGroup(Long userId,Long custGroupId){
		    	 
		    	 Log.debug("Entered into the getVotersCountForPartyByCustomGroup method");
		    	 final List<VoterCastInfoVO> resultList = new ArrayList<VoterCastInfoVO>(0);
		    	 VoterCastInfoVO listVO = null;
		    	 Map<Long,VoterCastInfoVO> VoterCastInfoDataMap = null;
		    	 VoterCastInfoVO mainVO = new VoterCastInfoVO();
		    	 Long totalVoters = 0l;
		    	 Long partyWisevotesConsidered = 0L;
		    	
		    	 try{
		    		 totalVoters = customVoterDAO.getTotalVotersByCustomGroupId(custGroupId);
		    		 final List<Object[]> list = customVoterDAO.getVotersCountForPartyByCustomGroup(userId,custGroupId);
		    		 VoterCastInfoDataMap = new HashMap<Long,VoterCastInfoVO>();
		    		 
		    		 if(list != null && !list.isEmpty() && list.get(0) != null){
		    			 VoterCastInfoVO voterCastInfoVO = null;
		    			 for(Object[] params:list){
		    				 partyWisevotesConsidered = partyWisevotesConsidered+(Long)params[0];
		    				 Long partId = (Long)params[2];
		    				 if(VoterCastInfoDataMap.isEmpty() || !VoterCastInfoDataMap.containsKey(partId)){
		    					 voterCastInfoVO = new VoterCastInfoVO();
		    					 voterCastInfoVO.setPartyId((Long)params[2]);
		    					 voterCastInfoVO.setPartyName(params[3].toString());
		    					  VoterCastInfoDataMap.put(partId,voterCastInfoVO);
		    					
		    					 
		    				 }
		    				 else if(VoterCastInfoDataMap.containsKey(partId)){
		    					 voterCastInfoVO.setPartyId((Long)params[2]);
		    					 voterCastInfoVO.setPartyName(params[3].toString());
		    					  VoterCastInfoDataMap.put(partId,voterCastInfoVO);
		    					 
		    					 
		    				 }
		    				  if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.MALE)){
		    							
		    						
		    						 voterCastInfoVO.setMaleVoters((Long)params[0]);
		    					
		    					 }
		    					 else if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.FEMALE)){
		    						 
		    						 voterCastInfoVO.setFemaleVoters((Long)params[0]);
		    					 }
		    				  
		    				  voterCastInfoVO.setTotalVoters(voterCastInfoVO.getMaleVoters()+voterCastInfoVO.getFemaleVoters());
		    				 
		    			 }
		    		 }
		    	
		    		 if(!VoterCastInfoDataMap.isEmpty()){
		    			 Set entries = VoterCastInfoDataMap.entrySet();
		    				Iterator iterator = entries.iterator();
		    				while(iterator.hasNext()){
		    				Map.Entry entry = (Map.Entry)iterator.next();
		    				Long PartyId = (Long)entry.getKey();
		    				listVO = new VoterCastInfoVO();
		    				 String votesPercentage = "0.00";
		    				  
		    				VoterCastInfoVO voterCastInfoVOList = (VoterCastInfoVO)entry.getValue();
		    				listVO.setPartyId(voterCastInfoVOList.getPartyId());
		    				listVO.setPartyName(voterCastInfoVOList.getPartyName());
		    				listVO.setTotalVoters(voterCastInfoVOList.getTotalVoters());
		    				listVO.setMaleVoters(voterCastInfoVOList.getMaleVoters());
		    				listVO.setFemaleVoters(voterCastInfoVOList.getFemaleVoters());
		    				votesPercentage = new BigDecimal((voterCastInfoVOList.getTotalVoters()*100.0)/partyWisevotesConsidered.longValue()).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
		    				listVO.setVotesPercent(votesPercentage);
		    				resultList.add(listVO);
		    				}
		    			 
		    		 }
		    		 
		    		 mainVO.setPartyWisevoterCastInfoVOList(resultList);
		    		
		    		 	mainVO.setPartyWiseAssignedVoters(partyWisevotesConsidered);
		    		 	mainVO.setPartyWiseNotAssignedVoters(totalVoters - partyWisevotesConsidered);
		    			return mainVO;
		    		 
		    	 
		    	 }catch (Exception e) {
		    			Log.error("Exception Occured in getVotersCountForPartyByCustomGroup() Method, Exception is - "+e);
		    			return mainVO;
		    		}
		    	
		    }



public ImportantFamiliesInfoVo getCustomVoterImpFamilyDetails(Long customVoterGroupId,Long publicationDateId,Long userId){
	ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
	String query = "";
	Long maleVotersCount = 0l;
	Long femaleVotersCount = 0l;
	Long unknownsCount = 0l;
	
	List<Object[]> votersCountList = customVoterDAO.getCustomVoterCount(customVoterGroupId, publicationDateId,userId);
	 for(Object[] votersCount : votersCountList){
	    	if(votersCount[1] != null && votersCount[1].toString().trim().equalsIgnoreCase("M")){
	    		maleVotersCount = (Long)votersCount[0];
	    	}else if(votersCount[1] != null && votersCount[1].toString().trim().equalsIgnoreCase("F")){
	    		femaleVotersCount = (Long)votersCount[0];
	    	}else if(votersCount[1] != null && votersCount[1].toString().trim().equalsIgnoreCase("")){
	    		unknownsCount = (Long)votersCount[0];
	    	}
	    }
	importantFamiliesInfoVo.setTotalFemaleVoters(femaleVotersCount.toString());
	importantFamiliesInfoVo.setTotalMaleVoters(maleVotersCount.toString());
	importantFamiliesInfoVo.setTotalVoters(maleVotersCount.longValue()+femaleVotersCount.longValue()+unknownsCount.longValue());
	
	List<Long> totalFamilyList = getImpFamilyInfo(customVoterGroupId, publicationDateId,query);
	Long totalVoters = (long)totalFamilyList.size();
	importantFamiliesInfoVo.setTotalFamalies(totalVoters);
	
	query = " having count(model.voter.voterId) <= 3";
	List<Long> familyList = getImpFamilyInfo(customVoterGroupId, publicationDateId,query);
	importantFamiliesInfoVo.setBelow3((long)familyList.size());
	Double val=0.0;
	if(totalVoters>0)
	val = Double.valueOf((long)familyList.size()*100/totalVoters);
	importantFamiliesInfoVo.setBelow3perc(val);	
	
	query = " having count(model.voter.voterId) >= 4 and count(model.voter.voterId) <= 6";
	List<Long> familyList1 = getImpFamilyInfo(customVoterGroupId, publicationDateId,query);
	importantFamiliesInfoVo.setBetwn4to6((long)familyList1.size());
	Double val1=0.0;
	if(totalVoters>0)
	val1 = Double.valueOf((long)familyList1.size()*100/totalVoters);
	importantFamiliesInfoVo.setBetwn4to6perc(val1);
	
	query = " having count(model.voter.voterId) >= 7 and count(model.voter.voterId) <= 10";
	List<Long> familyList2 = getImpFamilyInfo(customVoterGroupId, publicationDateId,query);
	importantFamiliesInfoVo.setBetwn7to10((long)familyList2.size());
	Double val2=0.0;
	if(totalVoters>0)
	val2 = Double.valueOf((long)familyList2.size()*100/totalVoters);
	importantFamiliesInfoVo.setBetwn7to10perc(val2);	
	
	query = " having count(model.voter.voterId) > 10";
	List<Long> familyList3 = getImpFamilyInfo(customVoterGroupId, publicationDateId,query);
	importantFamiliesInfoVo.setAbove10((long)familyList3.size());
	Double val3=0.0;
	if(totalVoters>0)
	val3 = Double.valueOf((long)familyList3.size()*100/totalVoters);
	importantFamiliesInfoVo.setAbove10perc(val3);	
	return importantFamiliesInfoVo;
}

public List<Long> getImpFamilyInfo(Long customVoterGroupId,Long publicationDateId,String query){
	List<Long> familyList = customVoterDAO.getImpFamiles(customVoterGroupId, publicationDateId,query);
	
	return familyList;
}

public List<VotersDetailsVO> getCustomVotersAgeDetails(Long constituencyId, Long locationId, Long publicationDateId,String areaType, Long userId)
{
	List<VotersDetailsVO> votersDetailsVOsList = new ArrayList<VotersDetailsVO>(0);
	try{
		List<Object[]> list = null;
		 list = boothPublicationVoterDAO.getAgeWiseCustomVoterDetails(constituencyId, locationId, publicationDateId, areaType, userId,AGE1);
		 setCustomVoterAgeDetails(AGE1,list,votersDetailsVOsList);
		
		 list = boothPublicationVoterDAO.getAgeWiseCustomVoterDetails(constituencyId, locationId, publicationDateId, areaType, userId,AGE2);
		 setCustomVoterAgeDetails(AGE2,list,votersDetailsVOsList);
		
		 list = boothPublicationVoterDAO.getAgeWiseCustomVoterDetails(constituencyId, locationId, publicationDateId, areaType, userId,AGE3);
		 setCustomVoterAgeDetails(AGE3,list,votersDetailsVOsList);
		
		 list = boothPublicationVoterDAO.getAgeWiseCustomVoterDetails(constituencyId, locationId, publicationDateId, areaType, userId,AGE4);
		 setCustomVoterAgeDetails(AGE4,list,votersDetailsVOsList);
		
		 list = boothPublicationVoterDAO.getAgeWiseCustomVoterDetails(constituencyId, locationId, publicationDateId, areaType, userId,AGE5);
		 setCustomVoterAgeDetails(AGE5,list,votersDetailsVOsList);
		 
		 if(votersDetailsVOsList != null && votersDetailsVOsList.size() > 0)
		 {
			for(VotersDetailsVO vo:votersDetailsVOsList)
			{
			  vo.setTotalVotersFor18To25(vo.getFemaleVotersCountBetween18To25() + vo.getMaleVotersCountBetween18To25());
			  vo.setTotalVotersFor26To35(vo.getFemaleVotersCountBetween26To35()+ vo.getMaleVotersCountBetween26To35());
			  vo.setTotalVotersFor36To45(vo.getFemaleVotersCountBetween36To45() + vo.getMaleVotersCountBetween36To45());
			  vo.setTotalVotersFor46To60(vo.getFemaleVotersCountBetween46To60() + vo.getMaleVotersCountBetween46To60());
			  vo.setTotalVotersForAbove60(vo.getFemaleVotersCountAbove60() + vo.getMaleVotersCountAbove60());
			  
			  countPercentages(vo);
			}
		 }
		
		return votersDetailsVOsList;
	}catch (Exception e) {
		e.printStackTrace();
		Log.error("Exception Occured in getCustomVotersAgeDetails() method, Exception - "+e);
		return votersDetailsVOsList;
	}
}

public void setCustomVoterAgeDetails(String age ,List<Object[]> list,List<VotersDetailsVO> votersDetailsVOsList)
{
	try{
		if(list != null && list.size() > 0)
		{
			VotersDetailsVO votersDetailsVO = null;
			for(Object[] params :list)
			{
			  votersDetailsVO = checkVotersDetailsVOExists((Long)params[2],votersDetailsVOsList);
			  if(votersDetailsVO == null)
			  {
				  votersDetailsVO = new VotersDetailsVO();
				  votersDetailsVO.setId((Long)params[2]);
				  votersDetailsVO.setName(params[3] != null ?params[3].toString():" ");
				  votersDetailsVOsList.add(votersDetailsVO);
			  }
			  if(params[1].toString().equalsIgnoreCase(IConstants.MALE))
			  {
				  if(age.equalsIgnoreCase(AGE1))
				   votersDetailsVO.setMaleVotersCountBetween18To25((Long)params[0]);
				  else if(age.equalsIgnoreCase(AGE2))
				   votersDetailsVO.setMaleVotersCountBetween26To35((Long)params[0]);
				  else if(age.equalsIgnoreCase(AGE3))
				   votersDetailsVO.setMaleVotersCountBetween36To45((Long)params[0]);
				  else if(age.equalsIgnoreCase(AGE4))
				   votersDetailsVO.setMaleVotersCountBetween46To60((Long)params[0]);
				  else if(age.equalsIgnoreCase(AGE5))
				   votersDetailsVO.setMaleVotersCountAbove60((Long)params[0]);
			  }
			  else if(params[1].toString().equalsIgnoreCase(IConstants.FEMALE))
			  {
				if(age.equalsIgnoreCase(AGE1))
				  votersDetailsVO.setFemaleVotersCountBetween18To25((Long)params[0]);
				else if(age.equalsIgnoreCase(AGE2))
				  votersDetailsVO.setFemaleVotersCountBetween26To35((Long)params[0]);
				else if(age.equalsIgnoreCase(AGE3))
				  votersDetailsVO.setFemaleVotersCountBetween36To45((Long)params[0]);
				else if(age.equalsIgnoreCase(AGE4))
				  votersDetailsVO.setFemaleVotersCountBetween46To60((Long)params[0]);
				else if(age.equalsIgnoreCase(AGE5))
				  votersDetailsVO.setFemaleVotersCountAbove60((Long)params[0]);  
			  }
			  
			}
		}
		
	}catch (Exception e) {
		e.printStackTrace();
		Log.error("Exception Occured in setCustomVoterAgeDetails() method, Exception - "+e);
	}
}

  public VotersDetailsVO checkVotersDetailsVOExists(Long groupId, List<VotersDetailsVO> list)
  {
	try{
		
		if(list == null || list.size() == 0)
			return null;
		for(VotersDetailsVO votersDetailsVO : list)
		  if(votersDetailsVO.getId().equals(groupId))
			  return votersDetailsVO;
					  
		return null;
	}catch (Exception e) {
		e.printStackTrace();
		Log.error("Exception Occured in checkVotersDetailsVOExists() method, Exception - "+e);
		return null;
	}
  }
  public List<VoterVO> getVoterDetailsForAttribute(Long userVoterCategoryValueId,Long casteId,String gender,Integer startIndex,Integer maxIndex,String order,String columnName,Long userId,String locationType,Long locationId,Long publicationId)
  {
	  List<VoterVO> result = new ArrayList<VoterVO>();
	  List<Object[]> voterData = null;
	  List<Long> total = boothPublicationVoterDAO.getVotersCountForAttribute(userId,userVoterCategoryValueId,casteId,gender,locationType,locationId,publicationId);
	  Long totalCount = 0l;
	try{
		if(columnName != null && columnName.equalsIgnoreCase("firstName"))
    	{
    		columnName = "name";
    	}
    	if(columnName != null && columnName.equalsIgnoreCase("relativeFirstName"))
    	{
    		columnName = "relativeName";
    	}
    	
    	voterData = boothPublicationVoterDAO.getVoterDetailsByAttributeIdAndCasteId(userId,userVoterCategoryValueId,casteId,
    			gender,startIndex,maxIndex,order,columnName,locationType,locationId,publicationId);
    	result = setDataToVoterVo(voterData,userId,null,null);
	}
	
	catch (Exception e) {
		Log.error("Exception Occured in getVoterDetailsForAttribute() method, Exception - "+e);
	}
	if(total != null && total.size() > 0)
	{
		totalCount = new Long(total.size());
		result.get(0).setTotalVoters(totalCount);
	}
	return result;
  }
  /** here voterData params[0]-voter model and params[1]-partNo and params[2]-serialNo**/
public List<VoterVO> setDataToVoterVo(List<Object[]> voterData,Long userId,VoterDataVO voterDataVO,List<Long> categories)
{
	List<VoterVO> result = new ArrayList<VoterVO>();
	VoterVO voterVO = null;
	Map<Long,VoterVO> voterMap = new HashMap<Long, VoterVO>();
	List<Long> voterIds = new ArrayList<Long>();
	try{
		if(voterData != null && voterData.size() > 0)
	    	for(Object[] params : voterData)
	    	{
	    	Voter voter = (Voter) params[0];
	    	voterIds.add(voter.getVoterId());
	    	}
		if(voterData != null && voterData.size() > 0)
    	{
    		for(Object[] params : voterData)
    		{
    		Voter voter = (Voter) params[0];
    		voterVO = new VoterVO();
    		voterVO.setVoterId(voter.getVoterId().toString());
    		voterVO.setName(voter.getName());
    		voterVO.setVoterIds((Long)voter.getVoterId());
    		voterVO.setGender(voter.getGender());
    		voterVO.setAge((Long)voter.getAge());
    		voterVO.setVoterIDCardNo(voter.getVoterIDCardNo());
    		voterVO.setHouseNo(voter.getHouseNo().toString());
    		voterVO.setRelativeFirstName(voter.getRelativeName());
    		voterVO.setSerialNo((Long)params[2]);
    		voterVO.setPartNo(new Long(params[1].toString()));
    		voterVO.setMobileNo(voter.getMobileNo()!= null?voter.getMobileNo() : " ");
    		voterMap.put((Long)voter.getVoterId(), voterVO);
    		result.add(voterVO);
    		}
    		
    	}
    	List<Long> influencingPeopleList = influencingPeopleDAO.findInfluencingPeopleDetails(voterIds,userId);
		if(influencingPeopleList != null && influencingPeopleList.size() > 0)
		{
			
			for (Long influencingPeople : influencingPeopleList) {
				if(influencingPeople != null)
				{
					voterVO = voterMap.get(influencingPeople);
					voterVO.setInfluencePerson(true);
				}
			}
		}
		
		List<Long> cadrePeopleList = cadreDAO.findCadrePeopleDetails(voterIds,userId);
		if(cadrePeopleList != null && cadrePeopleList.size() > 0)
		{
			for (Long cadrePeople : cadrePeopleList) {
				if(cadrePeople != null)
				{
					voterVO = voterMap.get(cadrePeople);
					voterVO.setIsCadrePerson(true);
				}
			}
		}
		
		List<Long> candidatePeopleList = candidateDAO.findCandidatePeopleDetails(voterIds);
		if(candidatePeopleList != null && candidatePeopleList.size() > 0)
		{
			for (Long candidatePeople : candidatePeopleList) {
				if(candidatePeople != null)
				{
					voterVO = voterMap.get(candidatePeople);
					voterVO.setIsPoliticion(true);
				}
			}
		}	
		if(voterDataVO != null)
		{
		if(voterDataVO.getPartyPresent() || voterDataVO.getCastePresent())
		{
			List<UserVoterDetails> votersPartyCastList = userVoterDetailsDAO.getAllUserVoterDetails(voterIds,userId);
			if(votersPartyCastList != null && votersPartyCastList.size() > 0)
			{
				for (UserVoterDetails voterPartyAndCasteDetails : votersPartyCastList) {
					voterVO = voterMap.get(voterPartyAndCasteDetails.getVoter().getVoterId());
					if(voterPartyAndCasteDetails != null)
					{
						if(voterPartyAndCasteDetails.getParty()!=null)
						voterVO.setPartyName(voterPartyAndCasteDetails.getParty()!=null ? voterPartyAndCasteDetails.getParty().getShortName():"");
						if(voterPartyAndCasteDetails.getCasteState() != null && voterPartyAndCasteDetails.getCasteState().getCaste() != null)
						voterVO.setCasteName(voterPartyAndCasteDetails.getCasteState().getCaste() != null ? voterPartyAndCasteDetails.getCasteState().getCaste().getCasteName():"");
					}
					else
					{
						voterVO.setPartyName("");
						voterVO.setCasteName("");
					}
				}
			}
		}
		}
		if(categories != null && categories.size() > 0){
			VoterVO category = null;
			 List<Object[]> votersPartyCastList = voterCategoryValueDAO.getVoterCategoryValuesForVoters(userId,voterIds);
		     for(Object[] voterDetails:votersPartyCastList){
		    	 VoterVO voterObj = voterMap.get((Long)voterDetails[0]);
		    	 if(voterObj != null){
		    		 List<VoterVO> categoriesList = voterObj.getCategoriesList();
		    		 if(categoriesList == null){
		    			 categoriesList = new ArrayList<VoterVO>();
		    			 voterObj.setCategoriesList(categoriesList);
		    		 }
		    		  category = new VoterVO();
		    		  categoriesList.add(category);
		    		  category.setCategoryValuesId((Long)voterDetails[1]);
		    		  category.setName(voterDetails[2]!=null?voterDetails[2].toString():"");
		    	 }
		     }
		 }
	}
	catch (Exception e) {
	Log.error("Exception Occured in setDataToVoterVo() method, Exception - "+e);
	}
	return result;
}

public List<VoterVO> getVoterDataForAttribute(VoterDataVO voterDataVO,Long userId,List<Long> categories)
{

	  List<VoterVO> result = new ArrayList<VoterVO>();
	  List<Long> voterIds = new ArrayList<Long>();
	  List<Object[]> voterData = null;
	 VoterVO voterVO =new VoterVO();
  		Map<Long , VoterVO> voterMap = new HashMap<Long, VoterVO>();
	 // List<Long> total = boothPublicationVoterDAO.getVotersCountForAttribute(userId,voterDataVO.getCategoryId(),voterDataVO.getCasteId(),voterDataVO.getGender(),voterDataVO.getBuildType(),voterDataVO.getLocationId(),voterDataVO.getPublicationId());
  		 List<Long> total = boothPublicationVoterDAO.getVotersCountForAttribute(userId,voterDataVO.getCategoryId(),voterDataVO.getCasteId(),voterDataVO.getGender(),voterDataVO.getBuildType(),voterDataVO.getId(),voterDataVO.getPublicationId());
	  Long totalCount = 0l;
	try{
		
  	
		voterData = boothPublicationVoterDAO.getVoterDetailsByAttributeIdAndCasteId(userId,voterDataVO.getCategoryId(),voterDataVO.getCasteId(),
  			voterDataVO.getGender(),voterDataVO.getStartIndex().intValue(),voterDataVO.getMaxIndex().intValue(),voterDataVO.getDir(),voterDataVO.getSort(),voterDataVO.getBuildType(),voterDataVO.getId(),voterDataVO.getPublicationId());
  	result = setDataToVoterVo(voterData,userId,voterDataVO,categories);
  	
  	
	}
	
	catch (Exception e) {
		Log.error("Exception Occured in getVoterDataForAttribute() method, Exception - "+e);
	}
	if(total != null && total.size() > 0)
	{
		totalCount = new Long(total.size());
		result.get(0).setTotalVoters(totalCount);
	}
	return result;	
}

public VoterInfo getTotalVotersDetailsbyCategoryAndCaste(Long categoryId,Long casteId,Long locationId,Long publicationId,String areaType,Long userId)
{
VoterInfo voterInfo = new VoterInfo();
List<Object[]> list = null;
Long totalVoters = 0l;
Long malevoters= 0l;
Long femaleVoters = 0l;
try{
	list = boothPublicationVoterDAO.getTotalVotersCountbyCategoryAndCaste(categoryId,casteId,locationId,publicationId,areaType,userId);
	if(list != null && list.size() > 0)
	{
		for(Object[] params : list)
		{
			if(params[1].toString().equalsIgnoreCase("F"))
			femaleVoters +=(Long)params[0];
			else if(params[1].toString().equalsIgnoreCase("M"))
			malevoters +=(Long)params[0];
		}
	}
	totalVoters = malevoters + femaleVoters;
	voterInfo.setMaleVoters(malevoters);
	voterInfo.setFemaleVoters(femaleVoters);
	voterInfo.setTotalVoters(totalVoters);
	return voterInfo;
}
catch (Exception e) {
	Log.error("Exception Occured in getTotalVotersDetailsbyCategoryAndCaste() method, Exception - "+e);
	return voterInfo;
}
	}

/** this method is used To get Influence,Cadre,Politician People Count For Caste and Category**/ 
public InfluencingPeopleBeanVO getInfluencingPeopleCountByCategoryAndCaste(Long categoryId,Long casteId,Long locationId,Long publicationId,
		String areaType,String gender,Long userId)
{
	List<Long> influencePeople = new ArrayList<Long>();
	List<Long> cadrePeople = new ArrayList<Long>();
	List<Long> politician = new ArrayList<Long>();
	InfluencingPeopleBeanVO influencingPeopleBeanVO = new InfluencingPeopleBeanVO();
	try{
		influencePeople = boothPublicationVoterDAO.getInfluencingPeopleCountByCategoryAndCaste(categoryId, casteId, locationId,publicationId,areaType,userId,gender,"InfluencePeople");
		cadrePeople = boothPublicationVoterDAO.getInfluencingPeopleCountByCategoryAndCaste(categoryId, casteId, locationId,publicationId,areaType,userId,gender,"Cadre");
		politician = boothPublicationVoterDAO.getInfluencingPeopleCountByCategoryAndCaste(categoryId, casteId, locationId,publicationId,areaType,userId,gender,"Politician");
		if(influencePeople != null && influencePeople.size() > 0)
		influencingPeopleBeanVO.setInfluencePeopleCount(new Long(influencePeople.get(0)));
		else
			influencingPeopleBeanVO.setInfluencePeopleCount(0l);	
		if(cadrePeople != null && cadrePeople.size() > 0)
    		influencingPeopleBeanVO.setCadreCount(new Long(cadrePeople.get(0)));
    		else
    			influencingPeopleBeanVO.setCadreCount(0l);	
		if(politician != null && politician.size() > 0)
    		influencingPeopleBeanVO.setPoliticianCount(new Long(politician.get(0)));
    		else
    			influencingPeopleBeanVO.setPoliticianCount(0l);	
	}
	catch(Exception e)
	{
		Log.error("Exception Occured in getInfluencingPeopleCount() method of CustomVoterGroupAnalysis Exception - ",e);
	}
	return influencingPeopleBeanVO;
}
}
