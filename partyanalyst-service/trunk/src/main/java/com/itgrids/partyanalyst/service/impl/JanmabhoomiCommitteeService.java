package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.itgrids.partyanalyst.dao.IJbCommitteeDAO;
import com.itgrids.partyanalyst.dao.IJbCommitteeLevelDAO;
import com.itgrids.partyanalyst.dao.IJbCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.IJbCommitteeRoleDAO;
import com.itgrids.partyanalyst.dto.JanmabhoomiCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.JanmabhoomiCommitteeVO;
import com.itgrids.partyanalyst.model.JbCommitteeLevel;
import com.itgrids.partyanalyst.service.IJanmabhoomiCommitteeService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class JanmabhoomiCommitteeService implements IJanmabhoomiCommitteeService{

	
	private final static Logger LOG = Logger.getLogger(JanmabhoomiCommitteeService.class);
	private CommonMethodsUtilService commonMethodsUtilService;
	private IJbCommitteeRoleDAO jbCommitteeRoleDAO;
	private IJbCommitteeMemberDAO jbCommitteeMemberDAO;
	
	public IJbCommitteeRoleDAO getJbCommitteeRoleDAO() {
		return jbCommitteeRoleDAO;
	}

	public void setJbCommitteeRoleDAO(IJbCommitteeRoleDAO jbCommitteeRoleDAO) {
		this.jbCommitteeRoleDAO = jbCommitteeRoleDAO;
	}
 
	
	public IJbCommitteeMemberDAO getJbCommitteeMemberDAO() {
		return jbCommitteeMemberDAO;
	}

	public void setJbCommitteeMemberDAO(IJbCommitteeMemberDAO jbCommitteeMemberDAO) {
		this.jbCommitteeMemberDAO = jbCommitteeMemberDAO;
	}
	 private IJbCommitteeDAO  jbCommitteeDAO ;
	    private IJbCommitteeLevelDAO jbCommitteeLevelDAO; 
	    
		public IJbCommitteeDAO getJbCommitteeDAO() {
			return jbCommitteeDAO;
		}
		public void setJbCommitteeDAO(IJbCommitteeDAO jbCommitteeDAO) {
			this.jbCommitteeDAO = jbCommitteeDAO;
		}
		
		public CommonMethodsUtilService getCommonMethodsUtilService() {
			return commonMethodsUtilService;
		}
		public void setCommonMethodsUtilService(
				CommonMethodsUtilService commonMethodsUtilService) {
			this.commonMethodsUtilService = commonMethodsUtilService;
		}
		
		public IJbCommitteeLevelDAO getJbCommitteeLevelDAO() {
			return jbCommitteeLevelDAO;
		}
		public void setJbCommitteeLevelDAO(IJbCommitteeLevelDAO jbCommitteeLevelDAO) {
			this.jbCommitteeLevelDAO = jbCommitteeLevelDAO;
		}
		public static Logger getLog() {
			return LOG;
		}
		public List<JanmabhoomiCommitteeVO> getDistrictWiseCommitteeDetails(String fromDate,String endDate,String type){
			List<JanmabhoomiCommitteeVO> returnList = new ArrayList<JanmabhoomiCommitteeVO>(); 
			try{
				
				//List<Object[]> list = jbCommitteeDAO.
			}catch (Exception e) {
				LOG.error("Excepting Occured in getDistrictWiseCommitteeDetails() of JanmabhoomiCommitteeService ", e);
			}
			return returnList;
		}
		
		// * @author Swapna
		@Override
		public JanmabhoomiCommitteeVO getJbCommitteeStatusCount(String fromDateStr, String toDateStr) {
			JanmabhoomiCommitteeVO  mainVO = new JanmabhoomiCommitteeVO();
		 try{		 
			    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

					Date startDate = null;
					Date endDate = null;
					if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && toDateStr != null && !toDateStr.isEmpty() && toDateStr.trim().length() > 0){
						startDate = sdf.parse(fromDateStr);
						endDate = sdf.parse(toDateStr);
					} 
			 
		 Map<Long,JanmabhoomiCommitteeVO> locationDtlsMap =new HashMap<Long, JanmabhoomiCommitteeVO>();	 
		 List<Object[]> committeeList = jbCommitteeDAO.getJbCommitteeStatusCount();
		 List<JbCommitteeLevel> committeeLevels= jbCommitteeLevelDAO.getAll();
		 if(commonMethodsUtilService.isListOrSetValid(committeeLevels)){
			 for (JbCommitteeLevel jbCommitteeLevel : committeeLevels) {
				 JanmabhoomiCommitteeVO vo=new JanmabhoomiCommitteeVO();
					 vo.setId(jbCommitteeLevel.getJbCommitteeLevelId());
					 vo.setName(jbCommitteeLevel.getName());
					 locationDtlsMap.put(vo.getId(),vo);
				  }
		 }			 
		 if (committeeList!=null && committeeList.size()>0){
			  for (Object[] objects : committeeList) {
				 JanmabhoomiCommitteeVO vo=null;
				     vo = locationDtlsMap.get((Long)objects[4]);
		    		  if(vo == null){
		    			  vo=new JanmabhoomiCommitteeVO();
		    			  vo.setId(commonMethodsUtilService.getLongValueForObject(objects[4]));
		    			  vo.setName(commonMethodsUtilService.getStringValueForObject(objects[5]).toUpperCase());
		    			  locationDtlsMap.put((Long)objects[4], vo);	
		    		      }
		    			  String confirmed     =    commonMethodsUtilService.getStringValueForObject((String)objects[1]);
		    			  String startdate     =    commonMethodsUtilService.getStringValueForObject((String)objects[2]);
		    			  String completedDate =    commonMethodsUtilService.getStringValueForObject((String)objects[3]);
		    			 
		    			 /* if(confirmed=="N" && startdate!=null){
		    				  vo.setInprogressCommitteeCnt(vo.getInprogressCommitteeCnt()+1);
		    			  }*/
		    			  if(confirmed.equalsIgnoreCase("N") && startdate==""){
		    				  vo.setNotStartedCommitteeCnt(vo.getNotStartedCommitteeCnt()+1);
		    			  }
		    			  if(confirmed.equalsIgnoreCase("Y") && completedDate!=""){
		    				  vo.setTotalApprovedCommitteeCnt(vo.getTotalApprovedCommitteeCnt()+1);
		    			  }	 			  
		    			  		  
		    		      }
		    		  if(commonMethodsUtilService.isMapValid(locationDtlsMap)){
		    			  for(Entry<Long,JanmabhoomiCommitteeVO> entry : locationDtlsMap.entrySet()){ 
		    				  JanmabhoomiCommitteeVO returnVo=entry.getValue(); 
		    				  mainVO.setTotalApprovedCommitteeCnt(mainVO.getTotalApprovedCommitteeCnt()+returnVo.getTotalApprovedCommitteeCnt());
		    				  mainVO.setInprogressCommitteeCnt(mainVO.getInprogressCommitteeCnt()+returnVo.getInprogressCommitteeCnt());
		    				  mainVO.setNotStartedCommitteeCnt(mainVO.getNotStartedCommitteeCnt()+returnVo.getNotStartedCommitteeCnt());
		    				  mainVO.getPositinsList().add(returnVo);
		    			  }			  
		    	   	      }
		                  }
		 
		
		 }catch(Exception e){
				Log.error("Exception raised in JanmabhoomiCommitteeService method of JanmabhoomiCommitteeService"+e);
			}
			return mainVO;
		 }
	
	@SuppressWarnings("unused")
	@Override
	public JanmabhoomiCommitteeMemberVO getJanmabhoomiCommitteeOverview(Long committeId, String fromDateStr, String toDateStr) {
		JanmabhoomiCommitteeMemberVO committeeVO = new JanmabhoomiCommitteeMemberVO();
		Map<Long,JanmabhoomiCommitteeMemberVO> designationVOMap=new HashMap<Long, JanmabhoomiCommitteeMemberVO>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date startDate = null;
			Date endDate = null;
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && toDateStr != null && !toDateStr.isEmpty() && toDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			
			
			List<Object[]> designationsobjList = jbCommitteeRoleDAO.getDesignationsIdsByCommitteeId(committeId);
			 //0 jbCommitteeRoleId,1 jbMemberTypeId,2 memberType, 3 maxMembers
			
            List<Long> committeeRoleIdsList=new ArrayList<Long>();
			if(designationsobjList!=null && designationsobjList.size() >0){
				for(Object[] param:designationsobjList){
					committeeRoleIdsList.add(commonMethodsUtilService.getLongValueForObject(param[0]));
					Long designationId = commonMethodsUtilService.getLongValueForObject(param[1]);
					JanmabhoomiCommitteeMemberVO designationVO = designationVOMap.get(designationId);
					if(designationVO == null){
						designationVO = new JanmabhoomiCommitteeMemberVO();
						designationVO.setDesignationId(designationId);
						designationVO.setRoleMemberCount(commonMethodsUtilService.getLongValueForObject(param[3]));
						designationVO.setDesignationName(commonMethodsUtilService.getStringValueForObject(param[2]));
						designationVOMap.put(designationId, designationVO);
					}
				}
			}
			
			List<Object[]> objList = jbCommitteeMemberDAO.getCommitteMemebersByRoleIds(committeeRoleIdsList, startDate, endDate);
			//0 jbCommitteeMemberId,1 jbMemberTypeId,2 memberName, 3 mobileNo,4 isActive,5 status,6 voterIDCardNo
			if(objList!=null && objList.size() >0){
				for(Object[] param:objList){
					Long designationId = commonMethodsUtilService.getLongValueForObject(param[1]);
					JanmabhoomiCommitteeMemberVO designationVO = designationVOMap.get(designationId);
					if(designationVO !=null){
						designationVO.getDesinationMebersVOList().add(createMemberVO(param,committeeVO));
						committeeVO.setTotalMemberCount(committeeVO.getTotalMemberCount()+1L);
					}
				}
				committeeVO.getDesinationVOList().addAll(designationVOMap.values());
			}
		} catch (Exception e) {
			LOG.error("Entered into getJanmabhoomiCommitteeOverview method in JanmabhoomiCommitteeService ",e);
		}
		return committeeVO;
	}
	
	
	public JanmabhoomiCommitteeMemberVO createMemberVO(Object[] param,JanmabhoomiCommitteeMemberVO committeeVO){
		JanmabhoomiCommitteeMemberVO memeberVO = new JanmabhoomiCommitteeMemberVO();
		memeberVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
		memeberVO.setMemeberName(commonMethodsUtilService.getStringValueForObject(param[2]));
		memeberVO.setMobileNumber(commonMethodsUtilService.getLongValueForObject(param[3]));
		memeberVO.setVoterId(commonMethodsUtilService.getStringValueForObject(param[6]));
		//memeberVO.setPartyId(commonMethodsUtilService.getLongValueForObject(param[3]));
		//memeberVO.setPartyName(commonMethodsUtilService.getStringValueForObject(param[0]));
		if(commonMethodsUtilService.getStringValueForObject(param[4]).equalsIgnoreCase("Y") && commonMethodsUtilService.getStringValueForObject(param[5]).equalsIgnoreCase("F")){
			memeberVO.setStatus("Approved");
			committeeVO.setAddedMemberCount(committeeVO.getAddedMemberCount()+1);
		}
		else if(commonMethodsUtilService.getStringValueForObject(param[4]).equalsIgnoreCase("Y") && commonMethodsUtilService.getStringValueForObject(param[5]).equalsIgnoreCase("P")){
			memeberVO.setStatus("Inprogress");
			committeeVO.setNotAddedMemberCount(committeeVO.getNotAddedMemberCount()+1);
		}else{
			memeberVO.setStatus("Rejected");
			committeeVO.setRejectedMemberCount(committeeVO.getRejectedMemberCount()+1);
		}
		return memeberVO;
	}

	@Override
	public Map<Long, JanmabhoomiCommitteeMemberVO> getLevelWiseCommiteeStatusCounts() {
		Map<Long,Map<Long,JanmabhoomiCommitteeMemberVO>> levelCommiteeCounts = new HashMap<Long, Map<Long,JanmabhoomiCommitteeMemberVO>>();
		Map<Long,JanmabhoomiCommitteeMemberVO> returnMap = new HashMap<Long,JanmabhoomiCommitteeMemberVO>();
		List<Object[]> committeeWiseTotalMemeberCountObjList = jbCommitteeRoleDAO.getCommitteeWiseTotalMemberCount();
		if (committeeWiseTotalMemeberCountObjList != null && committeeWiseTotalMemeberCountObjList.size() > 0) {
			for (Object[] param : committeeWiseTotalMemeberCountObjList) {
				Long levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				Long commiteeId = commonMethodsUtilService.getLongValueForObject(param[2]);
				
				Map<Long,JanmabhoomiCommitteeMemberVO> commiteeMap= levelCommiteeCounts.get(levelId);
				if(commiteeMap == null){
					 commiteeMap = new HashMap<Long, JanmabhoomiCommitteeMemberVO>();
					levelCommiteeCounts.put(levelId, commiteeMap);
				}
				JanmabhoomiCommitteeMemberVO levelVO =commiteeMap.get(commiteeId);
				if(levelVO == null){
					 levelVO = new JanmabhoomiCommitteeMemberVO();
					levelVO.setId(levelId);
					levelVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					commiteeMap.put(levelId, levelVO);
				}
				levelVO.setTotalMemberCount(commonMethodsUtilService.getLongValueForObject(param[3]));
				
			}
		}
		
		List<Object[]> committeeWiseTotalMemeberAddedCountObjList = jbCommitteeMemberDAO.getCommitteeWiseTotalMemberAddedCount();
		if (committeeWiseTotalMemeberAddedCountObjList != null && committeeWiseTotalMemeberAddedCountObjList.size() > 0) {
			for (Object[] param : committeeWiseTotalMemeberAddedCountObjList) {
				Long levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				Long commiteeId = commonMethodsUtilService.getLongValueForObject(param[1]);
				Map<Long,JanmabhoomiCommitteeMemberVO> commiteeMap= levelCommiteeCounts.get(levelId);
				if(commiteeMap != null){
					JanmabhoomiCommitteeMemberVO committeeVO =commiteeMap.get(commiteeId);
					if(committeeVO != null){
						 Long addedMems = commonMethodsUtilService.getLongValueForObject(param[2]);
						 Long maxMems = committeeVO.getTotalMemberCount();
						 if(maxMems.longValue() == addedMems.longValue() && commonMethodsUtilService.getStringValueForObject(param[3]).equalsIgnoreCase("N") && commonMethodsUtilService.getStringValueForObject(param[5]) == null ){
							 committeeVO.setStatus("Ready for apply");
						 }else{
							 committeeVO.setStatus("InProgress");
						 }
					}
				}
			}
		}
		
		if(commonMethodsUtilService.isMapValid(levelCommiteeCounts)){
			for (Entry<Long, Map<Long, JanmabhoomiCommitteeMemberVO>> entry : levelCommiteeCounts.entrySet()) {
				JanmabhoomiCommitteeMemberVO  levelVO = new JanmabhoomiCommitteeMemberVO();
				returnMap.put(entry.getKey(), levelVO);
				Map<Long, JanmabhoomiCommitteeMemberVO> committeeMap = entry.getValue();
				for (Entry<Long, JanmabhoomiCommitteeMemberVO> committee : committeeMap.entrySet()) {
					JanmabhoomiCommitteeMemberVO committeeVO = committee.getValue();
					levelVO.setId(committeeVO.getId());
					levelVO.setName(committeeVO.getName());
					if(committeeVO.getStatus().equalsIgnoreCase("Ready for apply")){
						levelVO.setRoleMemberCount(levelVO.getRoleMemberCount()+1l);
					}else if(committeeVO.getStatus().equalsIgnoreCase("InProgress")){
						levelVO.setAddedMemberCount(levelVO.getAddedMemberCount()+1l);
					}
				}
				levelVO.setTotalMemberCount(Long.valueOf(committeeMap.size()));
				
			}
		}
		return returnMap;
	}

	
}

