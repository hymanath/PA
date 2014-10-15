package com.itgrids.partyanalyst.service.impl;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ISurveyCompletedLocationsDAO;
import com.itgrids.partyanalyst.dao.ISurveyConstituencyTempDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dto.SurveyCompletionDetailsVO;
import com.itgrids.partyanalyst.dto.SurveyStatusVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.service.ICtpCasteReportService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CtpCasteReportService implements ICtpCasteReportService{
	private static final Logger LOG = Logger.getLogger(CtpCasteReportService.class); 
	
	@Autowired
	private IBoothPublicationVoterDAO boothPublicationVoterDAO ;
	@Autowired
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	@Autowired
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	@Autowired
	private IVoterInfoDAO voterInfoDAO;
	@Autowired
	private IConstituencyDAO constituencyDAO;
	@Autowired
	private ISurveyCompletedLocationsDAO surveyCompletedLocationsDAO ;
	@Autowired
	private ISurveyConstituencyTempDAO surveyConstituencyTempDAO;
	
	public VoterHouseInfoVO getVoterDetailsForSearch(VoterHouseInfoVO inputVo ,String locationType,Long id)
	{
		VoterHouseInfoVO result = new VoterHouseInfoVO();
		try{
		
			StringBuilder query = new StringBuilder();
			
			if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
				query.append(" and model.booth.constituency.constituencyId = :id");
			else if(locationType.equalsIgnoreCase(IConstants.MANDAL))
			{
					if(id.toString().substring(0,1).trim().equalsIgnoreCase("1"))
					{
						List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().substring(1).trim()));
					query.append(" and model.booth.localBody.localElectionBodyId = :id ");
					id = (Long)list.get(0);
				 }else if(id.toString().substring(0,1).trim().equalsIgnoreCase("2")){
					 query.append(" and model.booth.tehsil.tehsilId = :id and model.booth.localBody is null ");
					 id = new Long(id.toString().substring(1).trim());
				 }
			}
			else if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			{
				query.append(" and model.booth.panchayat.panchayatId = :id ");
			}
			else if(locationType.equalsIgnoreCase("booth")){
				query.append(" and model.booth.boothId = :id ");
			}
			if(!inputVo.getVoterIdCardNo().isEmpty())
			{
				query.append(" and model.voter.voterIDCardNo = '"+inputVo.getVoterIdCardNo()+"'");	
			}
			
		   if(!inputVo.getName().isEmpty())
		   {
			   if(inputVo.getSetValue().equalsIgnoreCase("start"))
				   query.append(" and model.voter.name like '"+inputVo.getName()+"%'");
			   else
		    		query.append(" and model.voter.name like '%"+inputVo.getName()+"%'");
			   
		   }
		   if(!inputVo.getGaurdian().isEmpty()){
	    		query.append(" and model.voter.relativeName like '%"+inputVo.getGaurdian()+"%'");
	    	}
	    	if(!inputVo.getGender().isEmpty()){
	    		 query.append(" and model.voter.gender = '"+inputVo.getGender()+"'");
	    	}
	    	if(inputVo.getAge() != null && inputVo.getAge() > 0){
	    		 query.append(" and model.voter.age >= "+inputVo.getAge());
	    	}
	    	if(inputVo.getToAge() != null && inputVo.getToAge() > 0){
	    		 query.append(" and model.voter.age <= "+inputVo.getToAge());
	    	}
	    	if(inputVo.getHouseNo() != null && !inputVo.getHouseNo().isEmpty()){
	    		 query.append(" and model.voter.houseNo = '"+inputVo.getHouseNo()+"'");
	    	}
	    	if(inputVo.getFromSno() != null && inputVo.getFromSno() > 0){
	    		 query.append(" and model.serialNo >= "+inputVo.getFromSno());
	    	}
	    	if(inputVo.getToSno() != null && inputVo.getToSno() > 0){
	    		 query.append(" and model.serialNo <= "+inputVo.getToSno());
	    	}
	    	
	    	  List<Object[]> votersData = boothPublicationVoterDAO.getVotersDetailsByCTPSearchCriteria(inputVo.getPublicationId(),id,query.toString(),inputVo.getUserId());
	    	  List<VoterHouseInfoVO> votersList = new ArrayList<VoterHouseInfoVO>();
	    	 
	    	  if(votersData != null && votersData.size() > 0)
	    	  {
	    		  populateVotersDataToVo(votersData,votersList,inputVo.getUserId());  
	    	  }
	    	  result.setVotersList(votersList);
		}
		catch (Exception e) {
		LOG.error("Exception Occured in getVoterDetailsForSearch()", e);
		}
		return result;
	}
	
	 public void populateVotersDataToVo(List<Object[]> votersData,List<VoterHouseInfoVO> votersList,Long userId){
		 VoterHouseInfoVO voterHouseInfoVO = null;
		 Map<Long,VoterHouseInfoVO> votersMap = new HashMap<Long,VoterHouseInfoVO>();
		 List<Long> voterIds = new ArrayList<Long>();
		 Map<Long,String> mobileNosMap = new HashMap<Long, String>(0);
		 if(votersData != null && votersData.size() > 0)
		 {
		  for(Object[] params:votersData)
		  {
			Voter voter = (Voter)params[0];
			 voterIds.add(voter.getVoterId());
		  }
		  
		  List<Object[]> list = userVoterDetailsDAO.getVoterIdAndMobileNoByVoterIdsList(voterIds, userId);
		  if(list != null && list.size() > 0)
		   for(Object[] params:list)
			 mobileNosMap.put((Long)params[0], params[1]!=null?params[1].toString():"N/A");
		  
		 }
		 for(Object[] voters : votersData){
			    Voter voter = (Voter)voters[0];
		    	voterHouseInfoVO = new VoterHouseInfoVO();
		    	//voterHouseInfoVO.setName(voter.getFirstName()+" "+voter.getLastName());
		    	voterHouseInfoVO.setName(voter.getName());
		    	voterHouseInfoVO.setGender(voter.getGender());
		    	voterHouseInfoVO.setAge(voter.getAge());
		    	voterHouseInfoVO.setHouseNo(voter.getHouseNo());
		    	//voterHouseInfoVO.setGaurdian(voter.getRelativeFirstName()+" "+voter.getRelativeLastName());
		    	voterHouseInfoVO.setGaurdian(voter.getRelativeName());
		    	voterHouseInfoVO.setRelationship(voter.getRelationshipType());
		    	if(mobileNosMap.get(voter.getVoterId())!= null)
		    	 voterHouseInfoVO.setMobileNo(mobileNosMap.get(voter.getVoterId()));
		    	else
		    	 voterHouseInfoVO.setMobileNo("N/A");
		    	voterHouseInfoVO.setVoterId(voter.getVoterId());
		    	voterHouseInfoVO.setBoothId((Long)voters[1]);
		    	voterHouseInfoVO.setBoothName(voters[2]!=null?voters[2].toString():"");
		    	voterHouseInfoVO.setVoterIdCardNo(voter.getVoterIDCardNo());
		    	voterHouseInfoVO.setFromSno((Long)voters[3]);
		    	voterHouseInfoVO.setCasteName(voters[4] !=null ? voters[4].toString() : "");
		    	votersList.add(voterHouseInfoVO);
		 }
		   
	 }
	 public VoterHouseInfoVO getCTPVoterCount(Long userId)
	 {
		 VoterHouseInfoVO result = new VoterHouseInfoVO();
		 List<VoterHouseInfoVO> resultList = new ArrayList<VoterHouseInfoVO>();
		 List<Long> constituneycIds = new ArrayList<Long>();
		 result.setVotersList(resultList);
		 try{
			
			List<Object[]> list = userVoterDetailsDAO.getCasteCountByConstituencyIds(IConstants.VOTER_DATA_PUBLICATION_ID,userId);
			if(list != null && list.size() > 0)
			{
				for(Object[] params :list)
				{
					VoterHouseInfoVO vo = new VoterHouseInfoVO();
					vo.setId((Long)params[0]);
					vo.setName(params[1] != null ? params[1].toString() : "");
					vo.setDistrictName(params[4] != null ? params[4].toString() : "");
					vo.setDistrictId((Long)params[3]);
					vo.setCasteCount((Long)params[2]);
					resultList.add(vo);
					constituneycIds.add((Long)params[0]);
				}
				List<Object[]> votersCount = voterInfoDAO.getVotersCountForAllConstituencies(IConstants.VOTER_DATA_PUBLICATION_ID,constituneycIds);
				if(votersCount != null && votersCount.size() > 0)
				{
					for(Object[] params : votersCount)
					{
						VoterHouseInfoVO constInfoVO = getMatchedVo(resultList, (Long)params[0]);
						constInfoVO.setCount((Long)params[1]);
					}
					
				}
			}
			
			if(resultList != null && resultList.size() > 0)
			for(VoterHouseInfoVO vo1 : resultList)
			{
				vo1.setPercentage(new BigDecimal(vo1.getCasteCount()*(100.0)/vo1.getCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			}
			 
		 }
		 catch (Exception e) {
			 LOG.error("Exception Occured in getCTPVoterCount()", e);
		}
		return result;
	 }
	 
	 public VoterHouseInfoVO getMatchedVo(List<VoterHouseInfoVO> list ,Long id)
	 {
		 try{
			 if(list == null || list.size() == 0)
				 return null;
			 for(VoterHouseInfoVO vo : list)
			 {
				 if(vo.getId().longValue() == id.longValue())
					 return vo;
			 }
		 }
		 catch (Exception e) {
			 LOG.error("Exception Occured in getMatchedVo()", e);
		}
		return null;
	 }
	
	 
	 public VoterHouseInfoVO getVotersCountInRegion(Long constituencyId,String locationType,Long userId)
	 {
		 VoterHouseInfoVO result = new VoterHouseInfoVO();
		 List<VoterHouseInfoVO> resultList = new ArrayList<VoterHouseInfoVO>();

		 List<VoterHouseInfoVO> localbodyList = new ArrayList<VoterHouseInfoVO>();
		 result.setVotersList(resultList);
		 try{
			if(locationType.equalsIgnoreCase(IConstants.MANDAL))
			{
			List<Object[]> list1 = userVoterDetailsDAO.getCasteCountBylocationType(IConstants.VOTER_DATA_PUBLICATION_ID,userId,constituencyId,IConstants.LOCAL_ELECTION_BODY);
			result.setLocalbodyList(localbodyList);
			setDataToList(list1,localbodyList,IConstants.LOCAL_ELECTION_BODY,constituencyId);
			}
			List<Object[]> list = userVoterDetailsDAO.getCasteCountBylocationType(IConstants.VOTER_DATA_PUBLICATION_ID,userId,constituencyId,locationType);
			setDataToList(list,resultList,locationType,constituencyId);
			
			
			if(resultList != null && resultList.size() > 0)
			for(VoterHouseInfoVO vo1 : resultList)
			{
				if(vo1.getCount() == 0)
					vo1.setPercentage("-");
				else
				vo1.setPercentage(new BigDecimal(vo1.getCasteCount()*(100.0)/vo1.getCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			}
			if(localbodyList != null && localbodyList.size() > 0)
				for(VoterHouseInfoVO vo2 : localbodyList)
				{
					if(vo2.getCount() == 0)
						vo2.setPercentage("-");
					else
					vo2.setPercentage(new BigDecimal(vo2.getCasteCount()*(100.0)/vo2.getCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
			 
		 }
		 catch (Exception e) {
			 LOG.error("Exception Occured in getCTPVoterCount()", e);
		}
		return result;
	 }
	
	 public void setDataToList(List<Object[]> list,List<VoterHouseInfoVO> resultList,String locationType,Long constituencyId)
	 {
		 List<Long> locationIds = new ArrayList<Long>();
		 if(list != null && list.size() > 0)
			{
				for(Object[] params :list)
				{
					if(!locationIds.contains((Long)params[0]))
					{
					VoterHouseInfoVO vo = new VoterHouseInfoVO();
					vo.setId((Long)params[0]);
					if(locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
					vo.setName(params[1] != null ? params[1].toString() + " Muncipality" : "");
					else
						vo.setName(params[1] != null ? params[1].toString() : "");	
					vo.setSelType(locationType);
					vo.setCasteCount(0l);
					resultList.add(vo);
					locationIds.add((Long)params[0]);
					}
				}
				
				for(Object[] params :list)
				{
					VoterHouseInfoVO vo = getMatchedVo(resultList, (Long)params[0]);
					if(vo != null)
					{
						if(params[3] != null && params[3].toString().equalsIgnoreCase("M"))
							vo.setMaleCnt((Long)params[2]);
						else if(params[3] != null && params[3].toString().equalsIgnoreCase("F"))
							vo.setFemaleCnt((Long)params[2]);
						vo.setCasteCount(vo.getMaleCnt() + vo.getFemaleCnt());
					}
				}
				List<Object[]> votersCount = voterInfoDAO.getVotersCountByLocationType(IConstants.VOTER_DATA_PUBLICATION_ID,locationIds,locationType,constituencyId);
				if(votersCount != null && votersCount.size() > 0)
				{
					for(Object[] params : votersCount)
					{
						VoterHouseInfoVO constInfoVO = getMatchedVo(resultList, (Long)params[0]);
						constInfoVO.setCount((Long)params[1]);
					}
					
				}
			}
	 }
	 
	 public VoterHouseInfoVO getCatseVotersCountInRegion(Long constituencyId,String locationType,Long userId)
	 {
		 VoterHouseInfoVO result = new VoterHouseInfoVO();
		 List<VoterHouseInfoVO> resultList = new ArrayList<VoterHouseInfoVO>();

		 List<VoterHouseInfoVO> localbodyList = new ArrayList<VoterHouseInfoVO>();
		 result.setVotersList(resultList);
		 try{
			if(locationType.equalsIgnoreCase(IConstants.MANDAL))
			{
			List<Object[]> list1 = userVoterDetailsDAO.getCasteVotersCountBylocationTypeInConstituency(IConstants.VOTER_DATA_PUBLICATION_ID,userId,constituencyId,IConstants.LOCAL_ELECTION_BODY);
			result.setLocalbodyList(localbodyList);
			
			setCasteDataToList(list1,localbodyList,IConstants.LOCAL_ELECTION_BODY,constituencyId);
			}
			List<Object[]> list = userVoterDetailsDAO.getCasteVotersCountBylocationTypeInConstituency(IConstants.VOTER_DATA_PUBLICATION_ID,userId,constituencyId,locationType);
			setCasteDataToList(list,resultList,locationType,constituencyId);
			if(resultList != null && resultList.size() > 0)
			for(VoterHouseInfoVO vo1 : resultList)
			{
				if( vo1.getCasteList() != null &&  vo1.getCasteList().size() > 0)
				for(VoterHouseInfoVO castVo : vo1.getCasteList())
				{
				if(vo1.getTotalCasteVoters() == 0)
					castVo.setPercentage("-");
				else
					castVo.setPercentage(new BigDecimal(castVo.getCasteCount()*(100.0)/vo1.getTotalCasteVoters()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
			}
			if(localbodyList != null && localbodyList.size() > 0)
				for(VoterHouseInfoVO vo2 : localbodyList)
				{
					if( vo2.getCasteList() != null &&  vo2.getCasteList().size() > 0)
					for(VoterHouseInfoVO castVo1 : vo2.getCasteList())
					{
					if(vo2.getTotalCasteVoters() == 0)
						castVo1.setPercentage("-");
					else
						castVo1.setPercentage(new BigDecimal(castVo1.getCasteCount()*(100.0)/vo2.getTotalCasteVoters()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}
				}
			 
		 }
		 catch (Exception e) {
			 LOG.error("Exception Occured in getCTPVoterCount()", e);
		}
		return result;
	 } 
	 public void setCasteDataToList(List<Object[]> list,List<VoterHouseInfoVO> resultList,String locationType,Long constituencyId)
	 {
		Map<Long,Map<Long,VoterHouseInfoVO>> resultMap = new HashMap<Long, Map<Long,VoterHouseInfoVO>>();
		Map<Long,String> locationName = new HashMap<Long, String>();
		 	if(list != null && list.size() > 0)
			{
		 		for(Object[] params :list)
				{
		 			Map<Long,VoterHouseInfoVO> casteMap = resultMap.get((Long)params[0]);
		 			if(casteMap == null)
		 			{
		 				casteMap = new HashMap<Long, VoterHouseInfoVO>();
		 				if(locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
		 				locationName.put((Long)params[0], params[1].toString() + " Muncipality");
		 				else
		 					locationName.put((Long)params[0], params[1].toString());
		 				resultMap.put((Long)params[0], casteMap);
		 			}
		 			VoterHouseInfoVO vo = casteMap.get((Long)params[4]);
		 			if(vo == null)
		 			{
		 				if(params[4] != null)
		 				{
		 					vo = new VoterHouseInfoVO();
		 				vo.setCasteStateId((Long)params[4]);
		 				vo.setCast(params[5].toString());
		 				vo.setCasteCount(0l);
		 				vo.setCategoryName(params[6].toString());
		 				casteMap.put((Long)params[4],vo);
		 				}
		 			}
		 			
				}
		 		
		 		for(Long location : resultMap.keySet())
		 		{
		 			
		 			VoterHouseInfoVO vo = new VoterHouseInfoVO();
		 			vo.setId(location);
		 			vo.setName(locationName.get(location));
		 			vo.setSelType(locationType);
		 			Map<Long,VoterHouseInfoVO> casteMap = resultMap.get(location);
		 			if(casteMap != null)
		 			vo.setCasteList(new ArrayList<VoterHouseInfoVO>(casteMap.values()));
		 			vo.setTotalCasteVoters(0l);
		 			resultList.add(vo);
		 		}
		 		for(Object[] params :list)
				{
		 			VoterHouseInfoVO vo = getMatchedVo(resultList, (Long)params[0]);
					if(vo != null)
					{
						VoterHouseInfoVO casteVo = getMatchedCasteVo(vo.getCasteList(), (Long)params[4]);
						if(casteVo != null)
						{
						if(params[3] != null && params[3].toString().equalsIgnoreCase("M"))
							casteVo.setMaleCnt((Long)params[2]);
						else if(params[3] != null && params[3].toString().equalsIgnoreCase("F"))
							casteVo.setFemaleCnt((Long)params[2]);
							casteVo.setCasteCount(casteVo.getMaleCnt() + casteVo.getFemaleCnt());
						}
						vo.setTotalCasteVoters(vo.getTotalCasteVoters() + (Long)params[2]);
					}	
				}
		 		
			}
	 }
	 public VoterHouseInfoVO getMatchedCasteVo(List<VoterHouseInfoVO> castelist ,Long id)
	 {
		 try{
			 if(castelist == null || castelist.size() == 0)
				 return null;
			 for(VoterHouseInfoVO vo : castelist)
			 {
				 if(vo.getCasteStateId().longValue() == id.longValue())
					 return vo;
			 }
		 }
		 catch (Exception e) {
			 LOG.error("Exception Occured in getMatchedVo()", e);
		}
		return null;
	 }
	 public SurveyStatusVO getSurveyStatusDetailsInfo()
	 {
		 SurveyStatusVO resultVo = new SurveyStatusVO();
		 List<Long> constituencyIds = new ArrayList<Long>();
		 List<Long> surveyCompletedStatusList = new ArrayList<Long>();
		 surveyCompletedStatusList.add(6l); surveyCompletedStatusList.add(7l);
		 surveyCompletedStatusList.add(8l); surveyCompletedStatusList.add(9l);
		 surveyCompletedStatusList.add(10l); surveyCompletedStatusList.add(11l);
		 List<SurveyStatusVO> resultList = new ArrayList<SurveyStatusVO>();
		 resultVo.setSubList(resultList);
		 try{
				List<Object[]>  list = surveyCompletedLocationsDAO.getSurveyConstituenciesStatus();
				if(list != null && list.size() > 0)
				{
					
					for(Object[] params : list)
					{
					  	if(!constituencyIds.contains((Long)params[0]))
					  	{
					  		SurveyStatusVO vo = new SurveyStatusVO();
					  		vo.setId((Long)params[0]);
					  		vo.setName(params[1] != null ? params[1].toString() : "");
					  		resultList.add(vo);
					  		constituencyIds.add((Long)params[0]);
					  	}
					}
					
					for(Object[] params : list)
					{
						SurveyStatusVO constituencyVo = getMatchedStatusVO (resultList, (Long)params[0]);
						if(constituencyVo != null)
						{
							if((Long)params[2] == 1 || (Long)params[2] == 2 || (Long)params[2] == 3)
								constituencyVo.setSurveyprocessTotal(constituencyVo.getSurveyprocessTotal() + (Long)params[3]);
							if( (Long)params[2] == 3)
								constituencyVo.setSurveyprocessCompleted(constituencyVo.getSurveyprocessCompleted() + (Long)params[3]);
							if((Long)params[2] == 4 || (Long)params[2] == 5)
								constituencyVo.setRedoBoothsTotal(constituencyVo.getRedoBoothsTotal() + ((Long)params[3]));
							if((Long)params[2] == 5)
								constituencyVo.setRedoBoothsCompleted(constituencyVo.getRedoBoothsCompleted() + ((Long)params[3]));
							if(surveyCompletedStatusList.contains((Long)params[2]))
								constituencyVo.setSurveyCompletedBooths(constituencyVo.getSurveyCompletedBooths() + (Long)params[3]);
							}
					}
					List<Object[]> totalVotersAndBooths = surveyConstituencyTempDAO.getTotalVotersAndBoothsByConstituencyes(constituencyIds);
					if(totalVotersAndBooths != null && totalVotersAndBooths.size() > 0)
					{
						for(Object[] params : totalVotersAndBooths)
						{
							SurveyStatusVO vo1 = getMatchedStatusVO (resultList, (Long)params[0]);
							if(vo1 != null)
							{
								vo1.setTotalBooths((Long)params[4]);
								
							}
						}
					}
				}
			 
		 }
		 catch (Exception e) {
			LOG.error("Exception Occured in getSurveyStatusDetailsInfo() method", e);
		}
		return resultVo;
	 }
	 public SurveyStatusVO getMatchedStatusVO(List<SurveyStatusVO> list ,Long id)
	 {
		 try{
			 if(list == null || list.size() == 0)
				 return null;
			 for(SurveyStatusVO vo : list)
			 {
				 if(vo.getId().longValue() == id.longValue())
					 return vo;
			 }
		 }
		 catch (Exception e) {
			 LOG.error("Exception Occured in getMatchedVo()", e);
		}
		return null;
	 }
	 
	 public VoterHouseInfoVO getVotersDetailsInCaste(Long id,String type,Long casteId,Long userId,Long constituencyId,String gender)
	 {
		 VoterHouseInfoVO resultVo = new VoterHouseInfoVO();
		
		 try{
			  List<Object[]> votersData = userVoterDetailsDAO.getCasteVotersDetailsBylocationTypeInConstituency(IConstants.VOTER_DATA_PUBLICATION_ID,userId,constituencyId,type,casteId,gender,id);
	    	  List<VoterHouseInfoVO> votersList = new ArrayList<VoterHouseInfoVO>();
	    	 
	    	  if(votersData != null && votersData.size() > 0)
	    	  {
	    		  populateVotersDataToVo(votersData,votersList,userId);  
	    	  }

	 		 resultVo.setVotersList(votersList);
		 }
		 catch (Exception e) {
			 LOG.error("Exception Occured in getVotersDetailsInCaste()", e);
		}
		return resultVo;
	 }
	 
}
