package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.ICustomVoterDAO;
import com.itgrids.partyanalyst.dao.ICustomVoterGroupDAO;
import com.itgrids.partyanalyst.dao.IInfluencingPeopleDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterCategoryValueDAO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterDataVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.VoterInfo;
import com.itgrids.partyanalyst.service.ICustomVoterGroupAnalysisService;
import com.itgrids.partyanalyst.service.IVoterReportService;
import com.itgrids.partyanalyst.utils.IConstants;

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
		    	List<Object[]> serialNosList = new ArrayList<Object[]>(0);
		    	
		    	try{
		    		
		    	if(columnName != null && columnName.equalsIgnoreCase("firstName"))
		    	{
		    		columnName = "name";
		    	}
		    	if(columnName != null && columnName.equalsIgnoreCase("relativeFirstName"))
		    	{
		    		columnName = "relativeName";
		    	}
		    	List<Long> total = customVoterDAO.getCountBycustomvoterGroupId(customvoterGroupId,userId,8l);	
		    	
		    	voterdata = customVoterDAO.getVotersInfoBycustomVoterGroupId(customvoterGroupId,userId,startIndex,maxRecords,
		    			order,columnName,8l);	
		    	if(voterdata != null && voterdata.size() > 0)
		    	for(Object[] params : voterdata)
		    	{
		    	voterIds.add((Long)params[0]);
		    	}
		    	
		    	
		    	if(voterdata != null && voterdata.size() > 0)
		    	{
		    		for(Object[] params : voterdata)
		    		{
		    		voterVO = new VoterVO();
		    		voterVO.setVoterId(params[0].toString());
		    		voterVO.setFirstName(params[1].toString());
		    		voterVO.setGender(params[2].toString());
		    		voterVO.setAge((Long)params[3]);
		    		voterVO.setVoterIDCardNo(params[4].toString());
		    		voterVO.setHouseNo(params[5].toString());
		    		voterVO.setRelativeFirstName(params[6].toString());
		    		voterVO.setSerialNo((Long)params[7]);
		    	    voterVO.setPartNo(new Long(params[8].toString()));
		    	    voterVO.setMobileNo(params[9]!= null?params[9].toString() : " ");
		    		voterVO.setVoterIds((Long)params[0]);
		    		voters.put((Long)params[0], voterVO);
		    		result.add(voterVO);
		    		}
		    		
		    	}
		    	List<Long> influencingPeopleList = influencingPeopleDAO.findInfluencingPeopleDetails(voterIds,userId);
				if(influencingPeopleList != null && influencingPeopleList.size() > 0)
				{
					for (Long influencingPeople : influencingPeopleList) {
					   voterVO = voters.get(influencingPeople);
						if(voterVO != null)
						{
							voterVO.setInfluencePerson(true);
						}
					}
				}
				List<Long> cadrePeopleList = cadreDAO.findCadrePeopleDetails(voterIds,userId);
				if(cadrePeopleList != null && cadrePeopleList.size() > 0)
				{
					for (Long cadrePeople : cadrePeopleList) {
						voterVO = voters.get(cadrePeople);
						if(voterVO != null)
						{
							voterVO.setIsCadrePerson(true);
						}
					}
				}
				List<Long> candidatePeopleList = candidateDAO.findCandidatePeopleDetails(voterIds);
				if(candidatePeopleList != null && candidatePeopleList.size() > 0)
				{
					for (Long candidatePeople : candidatePeopleList) {
						voterVO = voters.get(candidatePeople);
						if(voterVO != null)
						{
							voterVO.setIsPoliticion(true);
						}
					}
				}
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
		    	List<Object[]> serialNosList = new ArrayList<Object[]>(0);
		    	List<Long> total = customVoterDAO.getCountBycustomvoterGroupId(voterDataVO.getCustomVoterGroupId(),userId,8l);	
		    	
		    	voterdata = customVoterDAO.getVotersInfoBycustomVoterGroupId(voterDataVO.getCustomVoterGroupId(),userId,voterDataVO.getStartIndex().intValue(),voterDataVO.getMaxIndex().intValue(),
		    			 voterDataVO.getDir(),voterDataVO.getSort(),voterDataVO.getPublicationId());	
		    	if(voterdata != null && voterdata.size() > 0)
		    		for(Object[] params : voterdata)
		    		{
		    		voterIds.add((Long)params[0]);
		    		}
		    		
		    	if(voterdata != null && voterdata.size() > 0)
		    	{
		    		for(Object[] params : voterdata)
		    		{
		    		voterVO = new VoterVO();
		    		voterVO.setVoterId(params[0].toString());
		    		
		    		voterVO.setName(params[1].toString());
		    		voterVO.setVoterIds((Long)params[0]);
		    		voterVO.setGender(params[2].toString());
		    		voterVO.setAge((Long)params[3]);
		    		voterVO.setVoterIDCardNo(params[4].toString());
		    		voterVO.setHouseNo(params[5].toString());
		    		voterVO.setRelativeFirstName(params[6].toString());
		    		voterVO.setSerialNo((Long)params[7]);
		    		voterVO.setPartNo(new Long(params[8].toString()));
		    		voterVO.setMobileNo(params[9]!= null?params[9].toString() : " ");
		    		voterMap.put((Long)params[0], voterVO);
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
		    	if(voterDataVO.getPartyPresent() || voterDataVO.getCastePresent())
		    	{
		    		List<UserVoterDetails> votersPartyCastList = userVoterDetailsDAO.getAllUserVoterDetails(voterIds,userId);
		    		if(votersPartyCastList != null && votersPartyCastList.size() > 0)
		    		{
		    			for (UserVoterDetails voterPartyAndCasteDetails : votersPartyCastList) {
		    				voterVO = voterMap.get(voterPartyAndCasteDetails.getVoter().getVoterId());
		    				if(voterPartyAndCasteDetails != null)
		    				{
		    					
		    					voterVO.setPartyName(voterPartyAndCasteDetails.getParty()!=null ? voterPartyAndCasteDetails.getParty().getShortName():"");
		    					if(voterPartyAndCasteDetails.getCasteState() != null)
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
		    	if(total != null && total.size() > 0)
		    	{
		    		totalCount = new Long(total.size());
		    		result.get(0).setTotalVoters(totalCount);
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
				Log.error("Exception Occured in getTotalVotersDetailsbyCustomVoterGroup() of customVoterGroupAnalysis");
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
		    		Log.error("Exception Occured in getInfluencingPeopleCount() method of CustomVoterGroupAnalysis");
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
		    	result = setValuesToVOterVO(list,btnName,inftotal);
		    }
		    else if(btnName.equalsIgnoreCase("Cadre"))
		    {
		    	Long cadreCount = 0l;
		    	list = customVoterDAO.getCadreDetails(userId,publicationDateId,customVoterGroupId,startIndex,maxRecords,order,columnName);
		    	cadrePeople = customVoterDAO.getCadrePeopleCountByCustomVoter(userId, publicationDateId, customVoterGroupId);
		    	if(cadrePeople != null && cadrePeople.size() > 0)
		        cadreCount = cadrePeople.get(0).longValue();
		    	result = setValuesToVOterVO(list,btnName,cadreCount);
		    }
		    else if(btnName.equalsIgnoreCase("Politician"))
		    {
		    	Long politicainCount = 0l;
		    	list = customVoterDAO.getPoliticanDetails(userId,publicationDateId,customVoterGroupId,startIndex,maxRecords,order,columnName);
		    	politician = customVoterDAO.getPoliticianCountByCustomVoter(userId, publicationDateId, customVoterGroupId);
		    	if(politician != null && politician.size() > 0)
		        politicainCount = politician.get(0).longValue();
		    	result= setValuesToVOterVO(list,btnName,politicainCount);
		    }
		    	
		    }
		    catch(Exception e)
		    {
		    	e.printStackTrace();
		    	Log.error("Exception Occured in showVoterDetailsForSelcetedType() method of customVoterGroupAnalysis" ,e);
		    }
			return result;
		    }
		    
		    public List<VoterVO> setValuesToVOterVO(List<Object[]> list,String btnName,Long total)
		    {
		    	
		    	List<VoterVO> result = new ArrayList<VoterVO>(0);
		    	VoterVO voterVO = null;
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
						voterVO.setTotalVoters(total);
						if(btnName.equalsIgnoreCase("InfluencePeople"))
						{
						voterVO.setInfluencingRange(params[8].toString());
						voterVO.setInfluencingRegion(voterReportService.getRegionNameBasedOnScope(params[8].toString(),params[9].toString()));
						}
						++count;
						result.add(voterVO);
		    		}
		    	}
		    	}catch(Exception e)
		    	{
		    		Log.error("Exception Occured in setValuesToVOterVO() of customVoterGroupAnalysis");
		    	}
				return result;
		    }


}
