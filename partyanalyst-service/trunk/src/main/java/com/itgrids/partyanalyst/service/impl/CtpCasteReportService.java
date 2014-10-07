package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import sun.security.action.GetLongAction;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.model.UserVoterDetails;
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
}
