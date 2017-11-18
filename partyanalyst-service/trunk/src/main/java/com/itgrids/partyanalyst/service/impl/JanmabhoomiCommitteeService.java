package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.itgrids.partyanalyst.dao.ICasteCategoryDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IJbCommitteeDAO;
import com.itgrids.partyanalyst.dao.IJbCommitteeLevelDAO;
import com.itgrids.partyanalyst.dao.IJbCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.IJbCommitteeRoleDAO;
import com.itgrids.partyanalyst.dao.IUserConstituencyAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserDistrictAccessInfoDAO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.JanmabhoomiCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.JanmabhoomiCommitteeVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.VoterSearchVO;
import com.itgrids.partyanalyst.model.JbCommittee;
import com.itgrids.partyanalyst.model.JbCommitteeLevel;
import com.itgrids.partyanalyst.model.JbCommitteeMember;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.IJanmabhoomiCommitteeService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class JanmabhoomiCommitteeService implements IJanmabhoomiCommitteeService{

	
	private final static Logger LOG = Logger.getLogger(JanmabhoomiCommitteeService.class);
	private IJbCommitteeDAO jbCommitteeDAO;
	private CommonMethodsUtilService commonMethodsUtilService ;
	private IJbCommitteeLevelDAO jbCommitteeLevelDAO ;
	private IJbCommitteeRoleDAO jbCommitteeRoleDAO;
	private IJbCommitteeMemberDAO jbCommitteeMemberDAO;
	private DateUtilService dateUtilService;
	private ICadreCommitteeService cadreCommitteeService;
	private ICadreRegistrationService cadreRegistrationService;
	private List<VoterSearchVO> voterVoList = new ArrayList<VoterSearchVO>();
	private ICasteStateDAO casteStateDAO;
	private ICasteCategoryDAO casteCategoryDAO;
	private IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO;
	private IUserDistrictAccessInfoDAO userDistrictAccessInfoDAO;
	
	
	public IUserDistrictAccessInfoDAO getUserDistrictAccessInfoDAO() {
		return userDistrictAccessInfoDAO;
	}

	public void setUserDistrictAccessInfoDAO(
			IUserDistrictAccessInfoDAO userDistrictAccessInfoDAO) {
		this.userDistrictAccessInfoDAO = userDistrictAccessInfoDAO;
	}

	public IUserConstituencyAccessInfoDAO getUserConstituencyAccessInfoDAO() {
		return userConstituencyAccessInfoDAO;
	}

	public void setUserConstituencyAccessInfoDAO(
			IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO) {
		this.userConstituencyAccessInfoDAO = userConstituencyAccessInfoDAO;
	}

	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}

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
	
	public IJbCommitteeLevelDAO getJbCommitteeLevelDAO() {
		return jbCommitteeLevelDAO;
	}
	public void setJbCommitteeLevelDAO(IJbCommitteeLevelDAO jbCommitteeLevelDAO) {
		this.jbCommitteeLevelDAO = jbCommitteeLevelDAO;
	}
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public IJbCommitteeDAO getJbCommitteeDAO() {
		return jbCommitteeDAO;
	}
	public void setJbCommitteeDAO(IJbCommitteeDAO jbCommitteeDAO) {
		this.jbCommitteeDAO = jbCommitteeDAO;
	}

	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}

	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}
   
	public List<VoterSearchVO> getVoterVoList() {
		return voterVoList;
	}
	public void setVoterVoList(List<VoterSearchVO> voterVoList) {
		this.voterVoList = voterVoList;
	}
	
	
	public ICasteStateDAO getCasteStateDAO() {
		return casteStateDAO;
	}

	public void setCasteStateDAO(ICasteStateDAO casteStateDAO) {
		this.casteStateDAO = casteStateDAO;
	}

	public ICasteCategoryDAO getCasteCategoryDAO() {
		return casteCategoryDAO;
	}

	public void setCasteCategoryDAO(ICasteCategoryDAO casteCategoryDAO) {
		this.casteCategoryDAO = casteCategoryDAO;
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
						Long designationId = commonMethodsUtilService.getLongValueForObject(param[0]);
						JanmabhoomiCommitteeMemberVO designationVO = designationVOMap.get(designationId);
						if(designationVO == null){
							designationVO = new JanmabhoomiCommitteeMemberVO();
							designationVO.setDesignationId(designationId);
							designationVO.setRoleMemberCount(commonMethodsUtilService.getLongValueForObject(param[3]));
							designationVO.setDesignationName(commonMethodsUtilService.getStringValueForObject(param[2]));
							committeeVO.setRoleMemberCount(designationVO.getRoleMemberCount()+committeeVO.getRoleMemberCount());
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
							//committeeVO.setTotalMemberCount(committeeVO.getTotalMemberCount()+1L);
						}
					}
				}
				
				committeeVO.getDesinationVOList().addAll(designationVOMap.values());
				if(committeeVO.getDesinationVOList() !=null && committeeVO.getDesinationVOList().size() >0){
					for(JanmabhoomiCommitteeMemberVO designationVO:committeeVO.getDesinationVOList()){
						Long designationId = designationVO.getDesignationId();
						Long roleCount = designationVO.getRoleMemberCount();
						if(roleCount >Long.valueOf(designationVO.getDesinationMebersVOList().size())){
							Long memebersNotAddedCount = roleCount.longValue()-designationVO.getDesinationMebersVOList().size();
							for(int i=0;i<memebersNotAddedCount;i++){
								JanmabhoomiCommitteeMemberVO notAddedMemeberVO=new JanmabhoomiCommitteeMemberVO();
								designationVO.getDesinationMebersVOList().add(notAddedMemeberVO);
							}
						}
					}
				}
			} catch (Exception e) {
				LOG.error("Entered into getJanmabhoomiCommitteeOverview method in JanmabhoomiCommitteeService ",e);
			}
			return committeeVO;
		}

		public ICadreRegistrationService getCadreRegistrationService() {
		return cadreRegistrationService;
	}

	public void setCadreRegistrationService(
			ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
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
		       
		     Map<Long,JanmabhoomiCommitteeVO> locationDtlsMap =new HashMap<Long, JanmabhoomiCommitteeVO>(0);   
		     List<Object[]> committeeList = jbCommitteeDAO.getJbCommitteeStatusCount();
		     List<JbCommitteeLevel> committeeLevels= jbCommitteeLevelDAO.getAll();
		    /* if(commonMethodsUtilService.isListOrSetValid(committeeLevels)){
		       for (JbCommitteeLevel jbCommitteeLevel : committeeLevels) {
		          JanmabhoomiCommitteeVO vo=new JanmabhoomiCommitteeVO();
		           vo.setId(jbCommitteeLevel.getJbCommitteeLevelId());
		           vo.setName(jbCommitteeLevel.getName());
		           locationDtlsMap.put(vo.getId(),vo);
		          }
		     }      */ 
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
		                String confirmed     =    commonMethodsUtilService.getStringValueForObject(objects[1]);
		                String startdate     =    commonMethodsUtilService.getStringValueForObject(objects[2]);
		                String completedDate =    commonMethodsUtilService.getStringValueForObject(objects[3]);
		               
		               /* if(confirmed=="N" && startdate!=null){
		                  vo.setInprogressCommitteeCnt(vo.getInprogressCommitteeCnt()+1);
		                }*/
		                if(confirmed.equalsIgnoreCase("N") && startdate==""){
		                  vo.setNotStartedCommitteeCnt(vo.getNotStartedCommitteeCnt().longValue()+1l);
		                  vo.setTotalCommitteeCnt(vo.getTotalCommitteeCnt().longValue()+1l);
		                }
		                if(confirmed.equalsIgnoreCase("Y") && completedDate!=""){
		                  vo.setTotalApprovedCommitteeCnt(vo.getTotalApprovedCommitteeCnt().longValue()+1l);
		                  vo.setTotalCommitteeCnt(vo.getTotalCommitteeCnt().longValue()+1l);
		                }    
		                 
		                }
		              
		            }
		     Map<Long, JanmabhoomiCommitteeMemberVO> levelWiseStatusMap=getLevelWiseCommiteeStatusCounts("");
		     if(commonMethodsUtilService.isMapValid(locationDtlsMap)){
	                for(Entry<Long,JanmabhoomiCommitteeVO> entry : locationDtlsMap.entrySet()){ 
	                  JanmabhoomiCommitteeVO returnVo=entry.getValue(); 
	                  JanmabhoomiCommitteeMemberVO levelVO = levelWiseStatusMap.get(returnVo.getId());
	                  returnVo.setInprogressCommitteeCnt(returnVo.getInprogressCommitteeCnt().longValue()+levelVO.getAddedMemberCount().longValue());
	                  returnVo.setReadyForApprovelCommitteeCnt(returnVo.getReadyForApprovelCommitteeCnt().longValue()+levelVO.getRoleMemberCount().longValue());
	                  returnVo.setTotalCommitteeCnt(returnVo.getTotalCommitteeCnt().longValue()+returnVo.getInprogressCommitteeCnt().longValue()+returnVo.getReadyForApprovelCommitteeCnt().longValue());
	                  mainVO.setTotalApprovedCommitteeCnt(mainVO.getTotalApprovedCommitteeCnt().longValue()+returnVo.getTotalApprovedCommitteeCnt().longValue());
	                  mainVO.setInprogressCommitteeCnt(mainVO.getInprogressCommitteeCnt().longValue()+returnVo.getInprogressCommitteeCnt().longValue());
	                  mainVO.setReadyForApprovelCommitteeCnt(mainVO.getReadyForApprovelCommitteeCnt().longValue()+returnVo.getReadyForApprovelCommitteeCnt().longValue());
	                  mainVO.setNotStartedCommitteeCnt(mainVO.getNotStartedCommitteeCnt().longValue()+returnVo.getNotStartedCommitteeCnt().longValue());
	                  mainVO.setTotalCommitteeCnt(mainVO.getTotalCommitteeCnt().longValue()+returnVo.getTotalCommitteeCnt().longValue());

	                  returnVo.setInprogressCommitteePerc(calculatePercentage(returnVo.getTotalCommitteeCnt(),returnVo.getInprogressCommitteeCnt() ));
	                  returnVo.setNotStartedCommitteePerc(calculatePercentage(returnVo.getTotalCommitteeCnt(), returnVo.getNotStartedCommitteeCnt()));
	                  returnVo.setReadyForApprovelCommitteeperc(calculatePercentage(returnVo.getTotalCommitteeCnt(),returnVo.getReadyForApprovelCommitteeCnt()));
	                  returnVo.setTotalApprovedCommitteeperc(calculatePercentage(returnVo.getTotalCommitteeCnt(), returnVo.getTotalApprovedCommitteeCnt()));
	                  returnVo.setSubmitedCommitteesperc(calculatePercentage(returnVo.getTotalCommitteeCnt(),returnVo.getSubmitedCommittees()));
	                  mainVO.getPositinsList().add(returnVo);
	                }        
	           }
		        mainVO.setInprogressCommitteePerc(calculatePercentage(mainVO.getTotalCommitteeCnt(),mainVO.getInprogressCommitteeCnt() ));
		        mainVO.setNotStartedCommitteePerc(calculatePercentage(mainVO.getTotalCommitteeCnt(), mainVO.getNotStartedCommitteeCnt()));
		        mainVO.setReadyForApprovelCommitteeperc(calculatePercentage(mainVO.getTotalCommitteeCnt(),mainVO.getReadyForApprovelCommitteeCnt()));
		        mainVO.setTotalApprovedCommitteeperc(calculatePercentage(mainVO.getTotalCommitteeCnt(), mainVO.getTotalApprovedCommitteeCnt()));
		        mainVO.setSubmitedCommitteesperc(calculatePercentage(mainVO.getTotalCommitteeCnt(),mainVO.getSubmitedCommittees()));
		     }catch(Exception e){
		        Log.error("Exception raised in JanmabhoomiCommitteeService method of JanmabhoomiCommitteeService"+e);
		    }
		    return mainVO;
		    }

	public String calculatePercentage(Long totalVoters,Long count)
	{
		try{
			if(totalVoters != null && totalVoters.longValue() > 0l && count != null && count.longValue()>0L)
			  return (new BigDecimal((count * 100.0)/totalVoters.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
			else{
				return "0";
			}
			
		}catch (Exception e) {
			LOG.error("Exception Occured in calculatePercentage() method, Exception - ",e);
		}
		return null;
	}
	
		
		public JanmabhoomiCommitteeMemberVO createMemberVO(Object[] param,JanmabhoomiCommitteeMemberVO committeeVO){
					JanmabhoomiCommitteeMemberVO memeberVO = new JanmabhoomiCommitteeMemberVO();
					memeberVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					memeberVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(param[1]));
					memeberVO.setMemeberName(commonMethodsUtilService.getStringValueForObject(param[2]));
					memeberVO.setMobileNumber(commonMethodsUtilService.getStringValueForObject(param[3]));
					memeberVO.setVoterCardNo(commonMethodsUtilService.getStringValueForObject(param[6]));
					memeberVO.setCategoryId(commonMethodsUtilService.getLongValueForObject(param[7]));
					memeberVO.setCategoryName(commonMethodsUtilService.getStringValueForObject(param[8]));
					memeberVO.setCasteId(commonMethodsUtilService.getLongValueForObject(param[9]));
					memeberVO.setCasteName(commonMethodsUtilService.getStringValueForObject(param[10]));
					memeberVO.setPartyId(commonMethodsUtilService.getLongValueForObject(param[11]));
					memeberVO.setPartyName(commonMethodsUtilService.getStringValueForObject(param[12]));
					memeberVO.setMemberShipCardId(commonMethodsUtilService.getStringValueForObject(param[13]));
					memeberVO.setVoterId(commonMethodsUtilService.getLongValueForObject(param[14]));
					if(commonMethodsUtilService.getStringValueForObject(param[4]).equalsIgnoreCase("Y") && commonMethodsUtilService.getStringValueForObject(param[5]).equalsIgnoreCase("F")){
						memeberVO.setStatus("Approved");
						committeeVO.setAddedMemberCount(committeeVO.getAddedMemberCount()+1);
						committeeVO.setNotAddedMemberCount(committeeVO.getRoleMemberCount()-committeeVO.getAddedMemberCount());
					}
					else if(commonMethodsUtilService.getStringValueForObject(param[4]).equalsIgnoreCase("Y") && commonMethodsUtilService.getStringValueForObject(param[5]).equalsIgnoreCase("P")){
						memeberVO.setStatus("Inprogress");
						committeeVO.setAddedMemberCount(committeeVO.getAddedMemberCount()+1);
						committeeVO.setNotAddedMemberCount(committeeVO.getRoleMemberCount()-committeeVO.getAddedMemberCount());
						//committeeVO.setNotAddedMemberCount(committeeVO.getNotAddedMemberCount()+1);
					}else{
						memeberVO.setStatus("Rejected");
						committeeVO.setRejectedMemberCount(committeeVO.getRejectedMemberCount()+1);
					}
					return memeberVO;
		}

		public Map<Long, JanmabhoomiCommitteeMemberVO> getLevelWiseCommiteeStatusCounts(String type) {
			Map<Long,Map<Long,JanmabhoomiCommitteeMemberVO>> levelCommiteeCounts = new HashMap<Long, Map<Long,JanmabhoomiCommitteeMemberVO>>();
			Map<Long,JanmabhoomiCommitteeMemberVO> returnMap = new HashMap<Long,JanmabhoomiCommitteeMemberVO>();
			List<Object[]> committeeWiseTotalMemeberCountObjList = jbCommitteeRoleDAO.getCommitteeWiseTotalMemberCount(type,null);
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
			
			List<Object[]> committeeWiseTotalMemeberAddedCountObjList = jbCommitteeMemberDAO.getCommitteeWiseTotalMemberAddedCount(type,null);
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
						if(committeeVO.getStatus() != null && committeeVO.getStatus().equalsIgnoreCase("Ready for apply")){
							levelVO.setRoleMemberCount(levelVO.getRoleMemberCount()+1l);
						}else if(committeeVO.getStatus() != null &&  committeeVO.getStatus().equalsIgnoreCase("InProgress")){
							levelVO.setAddedMemberCount(levelVO.getAddedMemberCount()+1l);
						}
					}
					levelVO.setTotalMemberCount(Long.valueOf(committeeMap.size()));
					
				}
			}
			return returnMap;
		}

	public List<JanmabhoomiCommitteeVO> getDistrictWiseCommitteeDetails(String fromDate,String endDate,String type,Long userId){
		List<JanmabhoomiCommitteeVO> returnList = new ArrayList<JanmabhoomiCommitteeVO>(); 
		Map<Long,JanmabhoomiCommitteeVO> locationMapsWithLevel = new HashMap<Long,JanmabhoomiCommitteeVO>(); 
		try{ 
			
			
			List<JbCommitteeLevel> committeeLvls = jbCommitteeLevelDAO.getAll();
			List<Object[]> userLocaVals = null;
			Set<Long> userAccessLocVals = null;
			if(type != null && type.equalsIgnoreCase("district")){
				userLocaVals =userDistrictAccessInfoDAO.getLocationIdList(userId);
				 getUserAccessVals(userLocaVals,userAccessLocVals);
			}else if(type != null && type.equalsIgnoreCase("constituency")){
				userLocaVals =userConstituencyAccessInfoDAO.findByUser(userId);
				getUserAccessVals(userLocaVals,userAccessLocVals);
			}/*else if(type.equalsIgnoreCase("parliament")){
				
			}*/
			List<Object[]> list = jbCommitteeDAO.getDistrictWiseCommitteeDetails(null,null,type,userAccessLocVals);
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for(Object[] param :list){
				JanmabhoomiCommitteeVO locVO = locationMapsWithLevel.get(commonMethodsUtilService.getLongValueForObject(param[6]));
					if(locVO == null){
						 locVO = new JanmabhoomiCommitteeVO(commonMethodsUtilService.getLongValueForObject(param[6]),commonMethodsUtilService.getStringValueForObject(param[7]));
						 locVO.setList(setCommitteeLevels(committeeLvls));
						locationMapsWithLevel.put(commonMethodsUtilService.getLongValueForObject(param[6]), locVO);
					}
					Long committeeLvlId = commonMethodsUtilService.getLongValueForObject(param[0]);
					String confirmedStatus = commonMethodsUtilService.getStringValueForObject(param[2]);
					String startDate = commonMethodsUtilService.getStringValueForObject(param[3]);
					String completedDate = commonMethodsUtilService.getStringValueForObject(param[4]);
					String status = "";
					
					/*if(confirmedStatus == "N" && startDate != null){
						status = "InProgress";
					}else*/ if(confirmedStatus.equalsIgnoreCase("N") && startDate == ""){
						status = "Not Started";
					}else if(confirmedStatus == "Y" && completedDate != ""){
						status = "Approved";
					}
					if(committeeLvlId.longValue()==1l || commonMethodsUtilService.getLongValueForObject(param[6]) == 517l){
						locVO.setStatusType(status);
						locVO.setCommitteeId(commonMethodsUtilService.getLongValueForObject(param[5]));
					}
					for(JanmabhoomiCommitteeVO levelVO :locVO.getList()){
						if(levelVO.equals(new JanmabhoomiCommitteeVO(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1])))){
							if(status.equalsIgnoreCase("Not Started")){
								levelVO.setNotStartedCommitteeCnt(levelVO.getNotStartedCommitteeCnt()+1l);
							}else if(status.equalsIgnoreCase("Approved")){
								levelVO.setTotalApprovedCommitteeCnt(levelVO.getTotalApprovedCommitteeCnt()+1l);
							}
							levelVO.setTotalCommitteeCnt(levelVO.getTotalCommitteeCnt()+1l);
						}
					}
					
				}
			}
			
			Map<Long, Map<Long ,JanmabhoomiCommitteeMemberVO>> locLevelMap =  getLocationLevelWiseCommiteeStatusCounts( type,userAccessLocVals);
			
			if(commonMethodsUtilService.isMapValid(locationMapsWithLevel)){
				for (Entry<Long, JanmabhoomiCommitteeVO> entry:locationMapsWithLevel.entrySet()) {
					Map<Long ,JanmabhoomiCommitteeMemberVO> levelMap = locLevelMap.get(entry.getKey());
					JanmabhoomiCommitteeVO locVO = entry.getValue();
					JanmabhoomiCommitteeMemberVO distLevlVO = levelMap.get(1l);
					if(distLevlVO != null){
						if(distLevlVO.getAddedMemberCount() != null && distLevlVO.getAddedMemberCount().longValue() >0l)
							locVO.setStatusType("InProgress");
						else if(distLevlVO.getRoleMemberCount() != null && distLevlVO.getRoleMemberCount().longValue() >0l)
							locVO.setStatusType("Ready For Approval");
					}
					returnList.add(locVO);
					for(JanmabhoomiCommitteeVO levelVO :locVO.getList()){
						JanmabhoomiCommitteeMemberVO loclevelVO = levelMap.get(levelVO.getId());
						if(loclevelVO != null){
						levelVO.setInprogressCommitteeCnt(levelVO.getInprogressCommitteeCnt()+loclevelVO.getAddedMemberCount());
						levelVO.setReadyForApprovelCommitteeCnt(levelVO.getReadyForApprovelCommitteeCnt()+loclevelVO.getRoleMemberCount());
						}
					}
				}
				
			}
		}catch (Exception e) {
			LOG.error("Excepting Occured in getDistrictWiseCommitteeDetails() of JanmabhoomiCommitteeService ", e);
		}
		return returnList;
	}
	public void getUserAccessVals(List<Object[]> userLocaVals,Set<Long> userAccessLocVals){
		try {
			if(commonMethodsUtilService.isListOrSetValid(userLocaVals)){
				userAccessLocVals = new TreeSet<Long>();
				for (Object[] userAccessVal : userLocaVals) {
					userAccessLocVals.add(commonMethodsUtilService.getLongValueForObject(userAccessVal[0]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Excepting Occured in getUserAccessVals() of JanmabhoomiCommitteeService ", e);
		}
	}
	public List<JanmabhoomiCommitteeVO> setCommitteeLevels(List<JbCommitteeLevel> committeeLvls){
		List<JanmabhoomiCommitteeVO> returnList = new ArrayList<JanmabhoomiCommitteeVO>();
		try{
			if(commonMethodsUtilService.isListOrSetValid(committeeLvls)){
				for(JbCommitteeLevel committeeLvl :committeeLvls){
					if(committeeLvl.getJbCommitteeLevelId() != 1l && !returnList.contains(new JanmabhoomiCommitteeVO(committeeLvl.getJbCommitteeLevelId(),committeeLvl.getName()))){
						returnList.add(new JanmabhoomiCommitteeVO(committeeLvl.getJbCommitteeLevelId(),committeeLvl.getName()));
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Excepting Occured in setCommitteeLevels() of JanmabhoomiCommitteeService ", e);
		}
		return returnList;
	}
	public Map<Long, Map<Long ,JanmabhoomiCommitteeMemberVO>> getLocationLevelWiseCommiteeStatusCounts(String type,Set<Long> userAccessLocVals) {
		Map<Long,Map<Long,Map<Long,JanmabhoomiCommitteeMemberVO>>> levelCommiteeCounts = new HashMap<Long,Map<Long, Map<Long,JanmabhoomiCommitteeMemberVO>>>();
		Map<Long, Map<Long ,JanmabhoomiCommitteeMemberVO>> returnMap = new HashMap<Long, Map<Long ,JanmabhoomiCommitteeMemberVO>>();
		List<Object[]> committeeWiseTotalMemeberCountObjList = jbCommitteeRoleDAO.getCommitteeWiseTotalMemberCount(type,userAccessLocVals);
		if (committeeWiseTotalMemeberCountObjList != null && committeeWiseTotalMemeberCountObjList.size() > 0) {
			for (Object[] param : committeeWiseTotalMemeberCountObjList) {
				Long locId = commonMethodsUtilService.getLongValueForObject(param[4]);
				Long levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				Long commiteeId = commonMethodsUtilService.getLongValueForObject(param[2]);
				
				Map<Long,Map<Long,JanmabhoomiCommitteeMemberVO>> locationMap= levelCommiteeCounts.get(locId);
				if(locationMap == null){
					locationMap = new HashMap<Long,Map<Long, JanmabhoomiCommitteeMemberVO>>();
					levelCommiteeCounts.put(locId, locationMap);
				}
				Map<Long,JanmabhoomiCommitteeMemberVO> commiteeMap= locationMap.get(levelId);
				if(commiteeMap == null){
					 commiteeMap = new HashMap<Long, JanmabhoomiCommitteeMemberVO>();
					 locationMap.put(levelId, commiteeMap);
				}
				JanmabhoomiCommitteeMemberVO levelVO =commiteeMap.get(commiteeId);
				if(levelVO == null){
					 levelVO = new JanmabhoomiCommitteeMemberVO();
					levelVO.setId(levelId);
					levelVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					commiteeMap.put(commiteeId, levelVO);
				}
				levelVO.setTotalMemberCount(commonMethodsUtilService.getLongValueForObject(param[3]));
				
			}
		}
		
		List<Object[]> committeeWiseTotalMemeberAddedCountObjList = jbCommitteeMemberDAO.getCommitteeWiseTotalMemberAddedCount(type,userAccessLocVals);
		if (committeeWiseTotalMemeberAddedCountObjList != null && committeeWiseTotalMemeberAddedCountObjList.size() > 0) {
			for (Object[] param : committeeWiseTotalMemeberAddedCountObjList) {
				Long locId = commonMethodsUtilService.getLongValueForObject(param[6]);
				Long levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				Long commiteeId = commonMethodsUtilService.getLongValueForObject(param[1]);
				Map<Long,Map<Long,JanmabhoomiCommitteeMemberVO>> locationMap= levelCommiteeCounts.get(locId);
				if(locationMap != null){
				Map<Long,JanmabhoomiCommitteeMemberVO> commiteeMap= locationMap.get(levelId);
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
				for (Entry<Long, Map<Long,Map<Long, JanmabhoomiCommitteeMemberVO>>> entry : levelCommiteeCounts.entrySet()) {
					Map<Long, JanmabhoomiCommitteeMemberVO> levelMap = new HashMap<Long, JanmabhoomiCommitteeMemberVO>();
					returnMap.put(entry.getKey(), levelMap);
					Map<Long,Map<Long, JanmabhoomiCommitteeMemberVO>> levelcommitteeMap = entry.getValue();
					
						for (Entry<Long, Map<Long,JanmabhoomiCommitteeMemberVO>> committee : levelcommitteeMap.entrySet()) {
							JanmabhoomiCommitteeMemberVO  levelVO = new JanmabhoomiCommitteeMemberVO();
							Map<Long, JanmabhoomiCommitteeMemberVO> committeeMap = committee.getValue();
							levelMap.put(committee.getKey(), levelVO);
							for (Entry<Long, JanmabhoomiCommitteeMemberVO> committeesVO : committeeMap.entrySet()) {
								JanmabhoomiCommitteeMemberVO committeeVO = committeesVO.getValue();
								levelVO.setId(committeeVO.getId());
								levelVO.setName(committeeVO.getName());
								//levelVO.getCommitteeIds().add(committeeVO.getId());
								if(committeeVO.getStatus() != null && committeeVO.getStatus().equalsIgnoreCase("Ready for apply")){
									levelVO.setRoleMemberCount(levelVO.getRoleMemberCount()+1l);
								}else if(committeeVO.getStatus() != null && committeeVO.getStatus().equalsIgnoreCase("InProgress")){
									levelVO.setAddedMemberCount(levelVO.getAddedMemberCount()+1l);
								}
							}
						//levelVO.setTotalMemberCount(Long.valueOf(committeeMap.size()));
						
					}
				}
			}
		}
		return returnMap;
	}
	
	public ResultStatus saveJanmabhoomiCommitteeMember(final JanmabhoomiCommitteeMemberVO janmabhoomiCommitteeMemberVO){
		ResultStatus resultStatus  = new ResultStatus();
		try {
			
			if(janmabhoomiCommitteeMemberVO != null){
				JbCommitteeMember jbCommitteeMember = null;
				if(janmabhoomiCommitteeMemberVO.getId() != null){
				 jbCommitteeMember = jbCommitteeMemberDAO.get(janmabhoomiCommitteeMemberVO.getId());
				}
				if(jbCommitteeMember == null){
					jbCommitteeMember = new JbCommitteeMember();
				}
				String status =janmabhoomiCommitteeMemberVO.getStatus() ;
				String statusType ="";
				if(status.equalsIgnoreCase("approval")){
					statusType = "F";
				}else if(status.equalsIgnoreCase("reject")){
					statusType = "R";
				}else if(status.equalsIgnoreCase("proposal")){
					statusType = "P";
				}
				if(status != null && (statusType.equalsIgnoreCase("F") || statusType.equalsIgnoreCase("R"))){
					jbCommitteeMember.setStatus(statusType);
					jbCommitteeMember.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					jbCommitteeMember.setUpdatedUserId(janmabhoomiCommitteeMemberVO.getUserId());
					jbCommitteeMember.setEndDate(dateUtilService.getCurrentDateAndTime());
				}else if(statusType.equalsIgnoreCase("P")){
					JbCommittee jbCommittee = jbCommitteeDAO.get(janmabhoomiCommitteeMemberVO.getCommitteeId());
					if(jbCommittee != null){
						if(jbCommittee.getStartDate() == null){
							jbCommittee.setStartDate(dateUtilService.getCurrentDateAndTime());
							jbCommitteeDAO.save(jbCommittee);
						}
					}
					String saveStatus = avoidingDuplicateMemberAdding(janmabhoomiCommitteeMemberVO);
					if(saveStatus.equalsIgnoreCase("duplicate")){
						resultStatus.setExceptionMsg("DUPLICATE");
						return resultStatus;
					}
					jbCommitteeMember.setJbCommitteeRoleId(janmabhoomiCommitteeMemberVO.getDesignationId());
					jbCommitteeMember.setMemberName(janmabhoomiCommitteeMemberVO.getName());
					//jbCommitteeMember.setAge(null);
					jbCommitteeMember.setCasteStateId(janmabhoomiCommitteeMemberVO.getCasteId());
					jbCommitteeMember.setCasteCategoryId(janmabhoomiCommitteeMemberVO.getCategoryId());
					jbCommitteeMember.setMobileNo(janmabhoomiCommitteeMemberVO.getMobileNumber());
					//jbCommitteeMember.setDescripation(null);
					jbCommitteeMember.setTdpCadreId(janmabhoomiCommitteeMemberVO.getTdpCadreId());
					jbCommitteeMember.setVoterId(janmabhoomiCommitteeMemberVO.getVoterId());
					jbCommitteeMember.setPartyId(janmabhoomiCommitteeMemberVO.getPartyId());
					jbCommitteeMember.setStartDate(dateUtilService.getCurrentDateAndTime());
					//jbCommitteeMember.setEndDate(dateUtilService.getCurrentDateAndTime());
					jbCommitteeMember.setIsActive("Y");
					jbCommitteeMember.setJbCommitteeEnrollmentId(janmabhoomiCommitteeMemberVO.getEnrollmentYrId());
					jbCommitteeMember.setInsertedUserId(janmabhoomiCommitteeMemberVO.getUserId());
					jbCommitteeMember.setUpdatedUserId(janmabhoomiCommitteeMemberVO.getUserId());
					jbCommitteeMember.setStatus(statusType);
					jbCommitteeMember.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					jbCommitteeMember.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					
				}
				
				jbCommitteeMemberDAO.save(jbCommitteeMember);
				resultStatus.setExceptionMsg("SUCCESS");
			}
		} catch (Exception e) {
			resultStatus.setExceptionMsg("FAIL");
			e.printStackTrace();
			LOG.error("Excepting Occured in saveJanmabhoomiCommitteeMember() of JanmabhoomiCommitteeService ", e);
		}
		return resultStatus;
		
	}
	
	public String avoidingDuplicateMemberAdding(JanmabhoomiCommitteeMemberVO janmabhoomiCommitteeMemberVO){
		String status = "";
		try{
			Long commiteeId = janmabhoomiCommitteeMemberVO.getCommitteeId();
			List<Long> tdpCadreIdsList = new ArrayList<Long>();
			List<String> voterIdCardsList = new ArrayList<String>();
			List<Object[]> commiteesMembersList = jbCommitteeMemberDAO.getCommitteeMembersByCommiteeId(commiteeId);
			////0 jbCommitteeMemberId,1 jbCommitteeRoleId,2 memberName,3 voterIDCardNo,4 tdpCadreId
			if(commiteesMembersList !=null && commiteesMembersList.size() >0) {
				for(Object[] param: commiteesMembersList){
					if(commonMethodsUtilService.getStringValueForObject(param[3]) !=null)
					voterIdCardsList.add(commonMethodsUtilService.getStringValueForObject(param[3]));
					if(commonMethodsUtilService.getStringValueForObject(param[4]) !=null)
						tdpCadreIdsList.add(commonMethodsUtilService.getLongValueForObject(param[4]));
				}
			}
			if(janmabhoomiCommitteeMemberVO.getTdpCadreId() !=null){
				if(tdpCadreIdsList.contains(janmabhoomiCommitteeMemberVO.getTdpCadreId())){
					status = "duplicate";
				}
			}else if(janmabhoomiCommitteeMemberVO.getVoterCardNo() !=null){
				if(voterIdCardsList.contains(janmabhoomiCommitteeMemberVO.getVoterCardNo())){
					status = "duplicate";
				}
			}
			
		}catch (Exception e) {
			LOG.error("Excepting Occured in avoidingDuplicateMemberAdding() of JanmabhoomiCommitteeService ", e);
		}
		return status;
	}
	public List<JanmabhoomiCommitteeVO> getJanmabhoomiCommitteesByLocIdAndCommLvlId(String fromDate,String endDate,Long locationId,Long locLvlId,Long committeeLvlId,String status){
		 List<JanmabhoomiCommitteeVO> returnList = new ArrayList<JanmabhoomiCommitteeVO>();
	try {
		Map<Long,JanmabhoomiCommitteeVO> committeeMaps = null;
		if(status != null && (status.equalsIgnoreCase("Not Started") || status.equalsIgnoreCase("Approved") || status.equalsIgnoreCase("total"))){
			List<Object[]> list = jbCommitteeDAO.getLocationWiseCommitteeDetailsForCommitteeLvl(null,null , locLvlId, locationId, committeeLvlId,status);
			committeeMaps = new HashMap<Long,JanmabhoomiCommitteeVO>();
			setCommitteesData(committeeMaps,list,status,"");
			
		}else if(status != null && (status.equalsIgnoreCase("Inprogress") || status.equalsIgnoreCase("readyforapproval"))){
			List<Object[]> maxMemberCnt = jbCommitteeRoleDAO.getCommitteeLvlWiseTotalMemberCountInLocation(null,null,locLvlId,locationId,committeeLvlId);
			List<Object[]> addedMemberCnt =  jbCommitteeMemberDAO.getCommitteeWiseTotalMemberAddedCount(null,null,locLvlId,locationId,committeeLvlId);
			committeeMaps = new HashMap<Long,JanmabhoomiCommitteeVO>();
			setCommitteesData(committeeMaps,maxMemberCnt,status,"maxMember");
			setCommitteesData(committeeMaps,addedMemberCnt,status,"addedMember");
		}
		if(commonMethodsUtilService.isMapValid(committeeMaps)){
			for (Entry<Long,JanmabhoomiCommitteeVO> entry : committeeMaps.entrySet()) {
				JanmabhoomiCommitteeVO committeeVO = entry.getValue();
				if(status != null && status.equalsIgnoreCase("readyforapproval") && committeeVO.getTotalCommitteeCnt().longValue() == committeeVO.getNotStartedCommitteeCnt() && committeeVO.getInprogressCommitteePerc().equalsIgnoreCase("N") && committeeVO.getNotStartedCommitteePerc().equalsIgnoreCase("")){
					returnList.add(committeeVO);
				}else if(status != null && status.equalsIgnoreCase("Inprogress") && committeeVO.getTotalCommitteeCnt().longValue() > committeeVO.getNotStartedCommitteeCnt().longValue()){
					returnList.add(committeeVO);
				}else{
					returnList.add(committeeVO);
				}
			}
		}
		
	} catch (Exception e) {
		e.printStackTrace();
		LOG.error("Excepting Occured in saveJanmabhoomiCommitteeMember() of JanmabhoomiCommitteeService ", e);
	}
	return returnList;
}
	
	public void setCommitteesData(Map<Long,JanmabhoomiCommitteeVO> committeeMaps,List<Object[]> list,String status,String type){
		
		try {
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (Object[] param : list) {
					JanmabhoomiCommitteeVO committeeVO = committeeMaps.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(committeeVO == null){
						committeeVO = new JanmabhoomiCommitteeVO();
						committeeMaps.put(commonMethodsUtilService.getLongValueForObject(param[0]), committeeVO);
					}
						committeeVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						committeeVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						if(type != null && type.equalsIgnoreCase("maxMember")){
							committeeVO.setTotalCommitteeCnt(commonMethodsUtilService.getLongValueForObject(param[2]));//maxMemCount
						}
						if(type != null && type.equalsIgnoreCase("addedMember")){
							Long addedMem = commonMethodsUtilService.getLongValueForObject(param[1]);
							String isConfirmed = commonMethodsUtilService.getStringValueForObject(param[2]);
							String completedDate = commonMethodsUtilService.getStringValueForObject(param[4]);
							committeeVO.setInprogressCommitteePerc(completedDate);
							committeeVO.setNotStartedCommitteePerc(isConfirmed);
							committeeVO.setNotStartedCommitteeCnt(addedMem);
							/*if(committeeVO.getTotalCommitteeCnt().longValue() == addedMem && isConfirmed.equalsIgnoreCase("N") && completedDate=="" ){
								
							}*/
							//committeeVO.setTotalCommitteeCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
						}
						
					
					
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Excepting Occured in setCommitteesData() of JanmabhoomiCommitteeService ", e);
		}
	}
	
	public JanmabhoomiCommitteeMemberVO searchByMemberIdOrVoterId(Long locationLevel,Long locationValue,String memberShipCardNo,String voterCardNo){
		JanmabhoomiCommitteeMemberVO mainVO=new JanmabhoomiCommitteeMemberVO();
		try {
			//searchTdpCadreDetailsBySearchCriteriaForCadreCommitte(Long locationLevel,Long locationId, String searchName,String memberShipCardNo,
			//String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategory,Long fromAge,Long toAge,String houseNo,String gender,int startIndex,int maxIndex,boolean isRemoved,Long enrollmentId,String searchType)
			CadreCommitteeVO cadreCommitteeVO = cadreCommitteeService.searchTdpCadreDetailsBySearchCriteriaForCadreCommitte(locationLevel,locationValue, "",memberShipCardNo, 
					voterCardNo, "", "",0L,"",0L,0L,"","",0,50,false,0L,"NominatedPostSearch");
		    if(cadreCommitteeVO !=null && cadreCommitteeVO.getPreviousElections() !=null && cadreCommitteeVO.getPreviousElections().size() ==0 && cadreCommitteeVO.getPreviousRoles() == null){
				
		    	//getOnliCadRegistrSearchVoteDetails(constencyId,mandalId,villageId,boothId,type,typeVal)
		    	if(voterCardNo !=null && voterCardNo.length() >1){
			    	voterVoList=cadreRegistrationService.getOnliCadRegistrSearchVoteDetails(null,null,null,null,"voterId",voterCardNo);
			    	if(voterVoList !=null && voterVoList.size() >0){
			    		for(VoterSearchVO voterDetailsVO:voterVoList){
			    			if(voterDetailsVO !=null){
			    				mainVO.setName(voterDetailsVO.getName());
			    				mainVO.setRelationshipType(voterDetailsVO.getRelationshipType() != null ? voterDetailsVO.getRelationshipType():null);
			    				mainVO.setRelativeName(voterDetailsVO.getRelativeName() != null ? voterDetailsVO.getRelativeName():null);
			    				mainVO.setGender(voterDetailsVO.getGender() != null ? voterDetailsVO.getGender():null);
			    				mainVO.setAge(voterDetailsVO.getAge() != null ? voterDetailsVO.getAge():null);
			    				mainVO.setHouseNo(voterDetailsVO.getHouseNo() != null ? voterDetailsVO.getHouseNo():null);
								mainVO.setMobileNumber(voterDetailsVO.getActualMobiNumber() != null?voterDetailsVO.getActualMobiNumber():null);
								mainVO.setVoterCardNo(voterDetailsVO.getVoterIDCardNo()!= null ? voterDetailsVO.getVoterIDCardNo():null);
								if(voterDetailsVO.getVoterId()!= null)
								mainVO.setVoterId(Long.valueOf(voterDetailsVO.getVoterId().toString()));
								mainVO.setMemberShipCardId(voterDetailsVO.getMemberShipNo()!= null ? voterDetailsVO.getMemberShipNo():null);
								mainVO.setTdpCadreId(voterDetailsVO.getTdpCadreId() == null?null:voterDetailsVO.getTdpCadreId());
								if(mainVO.getMemberShipCardId() !=null){ //from tdpcadre table
									if(voterDetailsVO.getTotalImagePathStr() !=null)
									   mainVO.setImageURL("https://www.mytdp.com/images/cadre_images/"+voterDetailsVO.getTotalImagePathStr());
								}else{ // from voter table
									if(voterDetailsVO.getTotalImagePathStr() !=null)
										   mainVO.setImageURL("https://mytdp.com/voter_images/"+voterDetailsVO.getTotalImagePathStr());
								}
								
			    			}
			    		}
			    	}
		    	}
			}else {
				for(CadreCommitteeVO candidateDetailsVO:cadreCommitteeVO.getPreviousRoles()){
					mainVO.setName(candidateDetailsVO.getCadreName());
					mainVO.setMemberShipCardId(candidateDetailsVO.getMemberShipCardId() == null?null:candidateDetailsVO.getMemberShipCardId()); // memberShipCardId
					mainVO.setTdpCadreId(candidateDetailsVO.getTdpCadreId() == null?null:candidateDetailsVO.getTdpCadreId());
					if(candidateDetailsVO.getImageURL() !=null){
						mainVO.setImageURL("https://www.mytdp.com/images/cadre_images/"+candidateDetailsVO.getImageURL());
					}else{
						mainVO.setImageURL(candidateDetailsVO.getImageURL() == null?null:candidateDetailsVO.getImageURL());
					}
					mainVO.setMobileNumber(candidateDetailsVO.getMobileNo() == null?null:candidateDetailsVO.getMobileNo());
					mainVO.setVoterCardNo(candidateDetailsVO.getVoterCardNo() == null?null:candidateDetailsVO.getVoterCardNo());
					if(candidateDetailsVO.getCadreVoterId() !=null)
					mainVO.setVoterId(candidateDetailsVO.getCadreVoterId());
				}
			}
	   } catch (Exception e) {
			LOG.error("Excepting Occured in searchByMemberIdOrVoterId() of JanmabhoomiCommitteeService ", e);
		}
		return mainVO;
	}

	@Override
	public List<JanmabhoomiCommitteeVO> getStatewiseCastNamesByCasteCategoryGroupId(List<Long> categoryGrouId) {
		List<JanmabhoomiCommitteeVO> casteVOList =new ArrayList<JanmabhoomiCommitteeVO>();
		List<Object[]> casteWiseObjList = casteStateDAO.getStatewiseCastNamesByCasteCategoryGroupId(categoryGrouId,1L);//state 1 as static
		try{
			if(casteWiseObjList !=null && casteWiseObjList.size() >0){
				for(Object[] param:casteWiseObjList){
					JanmabhoomiCommitteeVO casteVO = new JanmabhoomiCommitteeVO();
					casteVO.setId(commonMethodsUtilService.getLongValueForObject(param[2]));
					casteVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					casteVOList.add(casteVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Excepting Occured in getStatewiseCastNamesByCasteCategoryGroupId() of JanmabhoomiCommitteeService ", e);
		}
		return casteVOList;
	}

	@Override
	public List<JanmabhoomiCommitteeVO> getCategories() {
		List<JanmabhoomiCommitteeVO> categotiesVOList =new ArrayList<JanmabhoomiCommitteeVO>();
		List<Object[]> categories = casteCategoryDAO.getAllCasteCategoryDetails();
		try{
			if(categories !=null && categories.size() >0){
				for(Object[] param:categories){
					JanmabhoomiCommitteeVO categotiesVO = new JanmabhoomiCommitteeVO();
					categotiesVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					categotiesVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					categotiesVOList.add(categotiesVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Excepting Occured in getCategories() of JanmabhoomiCommitteeService ", e);
		}
		return categotiesVOList;
	}
	
	public ResultStatus updateCommitteStatusByCommiteeId(Long committeeId){
		ResultStatus resultStatus  = new ResultStatus();
		try {
			Long totalRoleCount = jbCommitteeRoleDAO.getTotalRoleMemberCountByCommitteId(committeeId);
			Long totalApprovedRoleCount = jbCommitteeMemberDAO.getMemberApprovedCountByCommitteId(committeeId);
			if(totalRoleCount !=null && totalApprovedRoleCount !=null && totalRoleCount.longValue() == totalApprovedRoleCount.longValue()){
				JbCommittee jbCommittee = jbCommitteeDAO.get(committeeId);
				if(jbCommittee !=null){
					if(jbCommittee.getIsDeleted().equalsIgnoreCase("N")){
						jbCommittee.setIsCommitteeConfirmed("Y");
						jbCommittee.setCompletedDate(dateUtilService.getCurrentDateAndTime());
						
						JbCommittee updatedJbCommittee = jbCommitteeDAO.save(jbCommittee);
						if(updatedJbCommittee !=null){
							resultStatus.setExceptionMsg("SUCCESS");
						}
					}else{
						resultStatus.setExceptionMsg("FAIL");
					}
				}
			}else{
				resultStatus.setExceptionMsg("NotFilled");
			}
		}catch (Exception e) {
			resultStatus.setExceptionMsg("FAIL");
			e.printStackTrace();
			LOG.error("Excepting Occured in updateCommitteStatusByCommiteeId() of JanmabhoomiCommitteeService ", e);
		}
		return resultStatus;
	}
}



