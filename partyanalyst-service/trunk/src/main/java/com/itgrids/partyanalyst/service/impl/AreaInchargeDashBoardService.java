package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IAreaInchargeLocationDAO;
import com.itgrids.partyanalyst.dao.IAreaInchargeMemberDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.IUserConstituencyAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserDistrictAccessInfoDAO;
import com.itgrids.partyanalyst.dto.AreaInchargeVO;
import com.itgrids.partyanalyst.dto.InchargeMemberVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.AreaInchargeLocation;
import com.itgrids.partyanalyst.model.AreaInchargeMember;
import com.itgrids.partyanalyst.service.IAreaInchargeDashBoardService;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class AreaInchargeDashBoardService implements IAreaInchargeDashBoardService{

	private static final Logger   LOG = Logger.getLogger(AreaInchargeDashBoardService.class);
	
	
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
    private IAreaInchargeMemberDAO areaInchargeMemberDAO;
    private ITdpCadreDAO tdpCadreDAO;
    private ResultStatus status;
	private TransactionTemplate     transactionTemplate;
	private IAreaInchargeLocationDAO areaInchargeLocationDAO;
	private ICadreDetailsService cadreDetailsService;
	private IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private IUserDistrictAccessInfoDAO userDistrictAccessInfoDAO;
	private IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO;
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	
	public IAreaInchargeMemberDAO getAreaInchargeMemberDAO() {
		return areaInchargeMemberDAO;
	}

	public void setAreaInchargeMemberDAO(
			IAreaInchargeMemberDAO areaInchargeMemberDAO) {
		this.areaInchargeMemberDAO = areaInchargeMemberDAO;
	}
	
	
	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}

	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}

	public ResultStatus getStatus() {
		return status;
	}

	public void setStatus(ResultStatus status) {
		this.status = status;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	

	public IAreaInchargeLocationDAO getAreaInchargeLocationDAO() {
		return areaInchargeLocationDAO;
	}

	public void setAreaInchargeLocationDAO(
			IAreaInchargeLocationDAO areaInchargeLocationDAO) {
		this.areaInchargeLocationDAO = areaInchargeLocationDAO;
	}

	public ICadreDetailsService getCadreDetailsService() {
		return cadreDetailsService;
	}

	public void setCadreDetailsService(ICadreDetailsService cadreDetailsService) {
		this.cadreDetailsService = cadreDetailsService;
	}

	
	public IActivityMemberAccessLevelDAO getActivityMemberAccessLevelDAO() {
		return activityMemberAccessLevelDAO;
	}

	public void setActivityMemberAccessLevelDAO(
			IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO) {
		this.activityMemberAccessLevelDAO = activityMemberAccessLevelDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	
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

	public List<AreaInchargeVO> getAreaInchargeDetails(Long voterId,String mobileNo,String memberShipId){
		List<AreaInchargeVO> finalList = new ArrayList<AreaInchargeVO>();
		try{
			//1-first name,2-relativeName,3-age,4-gender,5-houseNo,6-tehsilId,7-tehsilName,8-casteName,9-image
			List<Object[]> inchargeDetails = tdpCadreDAO.getAreaInchargeDetails(voterId,mobileNo,memberShipId);
			
			if(inchargeDetails != null && inchargeDetails.size()>0){
				for(Object[] param: inchargeDetails){
					AreaInchargeVO inchargeVo = new AreaInchargeVO();
					inchargeVo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					inchargeVo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					inchargeVo.setRelativeName(commonMethodsUtilService.getStringValueForObject(param[2]));
					inchargeVo.setAge(commonMethodsUtilService.getLongValueForObject(param[3]));
					inchargeVo.setGender(commonMethodsUtilService.getStringValueForObject(param[4]));
					inchargeVo.setHouseNo(commonMethodsUtilService.getStringValueForObject(param[5]));
					inchargeVo.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[7]));
					inchargeVo.setAddress(inchargeVo.getHouseNo()+" "+inchargeVo.getTehsilName());
					inchargeVo.setCaste(commonMethodsUtilService.getStringValueForObject(param[8]));
					inchargeVo.setImage(commonMethodsUtilService.getStringValueForObject(param[9]));
					List<String> isActive = areaInchargeMemberDAO.getActiveOrInActiveInchageDetails(inchargeVo.getId());
					if(isActive.contains("Y")){
						inchargeVo.setIsActive("Y");
					}else{
						inchargeVo.setIsActive("N");
					}
					finalList.add(inchargeVo);
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception occurred at getAreaInchargeDetails() of AreaInchargeDashBoardService class ",e);
		}
		
		return finalList;
		
	}
	
	public ResultStatus savingAssigningBooths(final Long cadreId, final List<String> portNos,final Long levelId,final Long levelValue){
		ResultStatus status = new ResultStatus();
		try{
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					List<Long> boothIds = areaInchargeLocationDAO.getBoothPortInchageLocationIds(portNos,levelId,levelValue);
					if(boothIds != null && boothIds.size()>0){
					for(Long param : boothIds){
						//Long inchargeLocationId = areaInchargeLocationDAO.getLocationIdsOfBooths(param);
						AreaInchargeLocation modal = areaInchargeLocationDAO.get(param);
						modal.setIsAssinged("Y");
						modal.setIsDeleted("N");
						modal.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
						AreaInchargeMember AIM = new AreaInchargeMember();
						AIM.setAreaInchargeLocationId(param);
						AIM.setIsActive("Y");
						AIM.setIsDeleted("N");
						AIM.setTdpCadreId(cadreId);
						AIM.setFromDate(new DateUtilService().getCurrentDateAndTime());
						AIM.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
						AreaInchargeMember inchargeMember = areaInchargeMemberDAO.save(AIM);
						
					}
					}
				}

			});
			status.setMessage("SUCCESS");
			return status;
		}catch(Exception e){
			status.setMessage("fail");
			LOG.error("Exception occurred at savingAssigningBooths() of AreaInchargeDashBoardService class ",e);
		}
		
		return status;
		
		
	}

	public AreaInchargeVO getAssignedAndUnAssignedBooths(Long levelId,Long levelValue){
		AreaInchargeVO finalVO = new AreaInchargeVO();
		try{
			//0-inchargeLocationId,1-isAssigned,2-panchayatId,3-panchaytName,4-tehsilId,5-tehsilName	
			List<Object[]> totalList = areaInchargeLocationDAO.getAssignedAndUnAssignedBooths(levelId,levelValue);
				settingAssignedAndUnAssignedList(totalList,finalVO);	
				
			//0-inchargeLocationId,1-cadreId,2-cadreName,3-houseNo
			List<Object[]> assignedCadreList = areaInchargeMemberDAO.getAssignedCadreList("Y");
			if(assignedCadreList != null && assignedCadreList.size()>0){
				for(Object[] param : assignedCadreList){
					AreaInchargeVO inchargeVO = getMatchedVO(finalVO.getAssignBoothList(),commonMethodsUtilService.getLongValueForObject(param[0]));
					if(inchargeVO != null){
						inchargeVO.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
						inchargeVO.setHouseNo(commonMethodsUtilService.getStringValueForObject(param[3]));
						inchargeVO.setAddress(inchargeVO.getName()+"-"+inchargeVO.getHouseNo());
					}
					
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception occurred at getAssignedAndUnAssignedBooths() of AreaInchargeDashBoardService class ",e);
		}
		return finalVO;
	}
	public void settingAssignedAndUnAssignedList(List<Object[]> unAssignedList,AreaInchargeVO finalVO){
		try{
			Long total = 0l,assignCount =0l,unAssignedCount =0l;
			//0-inchargeLocationId,1-isAssigned,2-panchayatId,3-panchaytName,4-tehsilId,5-tehsilName
		if(unAssignedList != null && unAssignedList.size()>0){
		 for(Object[] param : unAssignedList){
			 String status = commonMethodsUtilService.getStringValueForObject(param[1]);
			AreaInchargeVO assignVO = new AreaInchargeVO();
			assignVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
			assignVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(param[4]));
			assignVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[5])+""+"(M)");
			assignVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(param[2]));
			assignVO.setPanchayatName(commonMethodsUtilService.getStringValueForObject(param[3])+""+"(P)");
			if(status != null && status.equalsIgnoreCase("N")){
				finalVO.getUnAssignBoothList().add(assignVO);
				unAssignedCount = unAssignedCount+1l;
				finalVO.setUnAssignedCount(unAssignedCount);
			}else if(status != null && status.equalsIgnoreCase("Y")){
				finalVO.getAssignBoothList().add(assignVO);
				assignCount = assignCount+1l;
				finalVO.setAssignedCount(assignCount);
			}
			total = total+1l;
			finalVO.setTotal(total);
			
			}
		}
			
		}catch(Exception e){
			LOG.error("Exception occurred at settingAssignedAndUnAssignedList() of AreaInchargeDashBoardService class ",e);
		}
		
	}
	public AreaInchargeVO getMatchedVO(List<AreaInchargeVO> returnList,Long id)
	{
		try{
			if(returnList == null || returnList.size() == 0)
				return null;
			for(AreaInchargeVO vo : returnList)
			{
				if(vo.getId().longValue()== id.longValue())
					return vo;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public AreaInchargeVO editAssignedInchargeDetails(Long tdpCadreId,Long levelId,Long levelValue){
		AreaInchargeVO inchargeVo = new AreaInchargeVO();
		try{
			//1-first name,2-relativeName,3-age,4-gender,5-houseNo,6-tehsilId,7-tehsilName,8-casteName,9-image
			Object[] inchargeDetails = tdpCadreDAO.editAssignedInchargeDetails(tdpCadreId);
			if(inchargeDetails != null){
				inchargeVo.setId(commonMethodsUtilService.getLongValueForObject(inchargeDetails[0]));
				inchargeVo.setName(commonMethodsUtilService.getStringValueForObject(inchargeDetails[1]));
				inchargeVo.setRelativeName(commonMethodsUtilService.getStringValueForObject(inchargeDetails[2]));
				inchargeVo.setAge(commonMethodsUtilService.getLongValueForObject(inchargeDetails[3]));
				inchargeVo.setGender(commonMethodsUtilService.getStringValueForObject(inchargeDetails[4]));
				inchargeVo.setHouseNo(commonMethodsUtilService.getStringValueForObject(inchargeDetails[5]));
				inchargeVo.setTehsilName(commonMethodsUtilService.getStringValueForObject(inchargeDetails[7]));
				inchargeVo.setAddress(inchargeVo.getHouseNo()+" "+inchargeVo.getTehsilName());
				inchargeVo.setCaste(commonMethodsUtilService.getStringValueForObject(inchargeDetails[8]));
				inchargeVo.setImage(commonMethodsUtilService.getStringValueForObject(inchargeDetails[9]));
			 }
			  List<Long> assignBooths = null;
			     assignBooths = areaInchargeMemberDAO.getAssignedInchargeBooths(tdpCadreId);
				if(assignBooths != null && assignBooths.size()>0){
					inchargeVo.setAssignBoothIds(assignBooths);
				}
				List<Object[]> totalList = areaInchargeLocationDAO.getAssignedAndUnAssignedBooths(levelId,levelValue);
					settingAssignedAndUnAssignedList(totalList,inchargeVo);	
		}catch(Exception e){
			LOG.error("Exception occurred at editAssignedInchargeDetails() of AreaInchargeDashBoardService class ",e);
		}
		return inchargeVo;
	}
	public String deleteAreaInchargeAssignBooths(Long candidateId,String portNo,Long levelId,Long levelValue){
		String result = null;
		try{
			Long boothId =0l;
			if((portNo != null) || !(portNo.equalsIgnoreCase("0"))){
				 boothId = areaInchargeLocationDAO.getBoothPortInchageLocationId(portNo,levelId,levelValue);
			}
			if((portNo  == null) || (portNo.equalsIgnoreCase("0"))){
				List<Long> inchargeLocationIds =areaInchargeMemberDAO.getdeletedBoothIds(candidateId);
				if(inchargeLocationIds != null && inchargeLocationIds.size()>0){
				for(Long param : inchargeLocationIds){
					AreaInchargeLocation inchargeLocation = areaInchargeLocationDAO.get(param);
					if(inchargeLocation != null){
						inchargeLocation.setIsAssinged("N");
						inchargeLocation.setIsDeleted("Y");
						inchargeLocation = areaInchargeLocationDAO.save(inchargeLocation);
					}
				}
				}
			}else{
				//Long getBoothPortInchageLocationIds(Long portNo)
				AreaInchargeLocation inchargeLocation = areaInchargeLocationDAO.get(boothId);
				if(inchargeLocation != null){
					inchargeLocation.setIsAssinged("N");
					inchargeLocation.setIsDeleted("Y");
					inchargeLocation = areaInchargeLocationDAO.save(inchargeLocation);
				}
			}
		int status=areaInchargeMemberDAO.deleteAreaInchargeAssignBooths(candidateId,boothId);
		
		if(status >0){
			result = "success";
		  }
		}catch(Exception e){
			e.printStackTrace();
			result = "failure";
		}
		return result;
		
	}
	public List<AreaInchargeVO> getAreaInchargeAssignedBoothDetails(Long levelId,Long levelValue){
		List<AreaInchargeVO> finalList = new ArrayList<AreaInchargeVO>();
		try{
			Map<Long,AreaInchargeVO> inchargeMap= new HashMap<Long,AreaInchargeVO>();
			Set<Long> assignedBooths = null;
			//0-tdpCadreId,1-name,2-relativename,3-age,4-gender,5-memberShipNo,6-mobileNo,7-locationId,8-image
			List<Object[]> inchargeDetails= areaInchargeMemberDAO.getAreaInchargeAssignedBoothDetails(levelId,levelValue);
			if(inchargeDetails != null && inchargeDetails.size()>0){
				for(Object[] param : inchargeDetails){
					AreaInchargeVO inchargeVo = inchargeMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(inchargeVo != null){
						  assignedBooths.add(commonMethodsUtilService.getLongValueForObject(param[7]));
						  inchargeVo.setAssignIds(assignedBooths);
					}else{
					  inchargeVo = new AreaInchargeVO();
					  inchargeVo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					  inchargeVo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					  inchargeVo.setRelativeName(commonMethodsUtilService.getStringValueForObject(param[2]));
					  inchargeVo.setAge(commonMethodsUtilService.getLongValueForObject(param[3]));
					  inchargeVo.setGender(commonMethodsUtilService.getStringValueForObject(param[4]));
					  inchargeVo.setMemberShipNo(commonMethodsUtilService.getStringValueForObject(param[5]));
					  inchargeVo.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[6]));
					  assignedBooths = new HashSet<Long>();
					  assignedBooths.add(commonMethodsUtilService.getLongValueForObject(param[7]));
					  inchargeVo.setAssignIds(assignedBooths);
					  inchargeVo.setImage(commonMethodsUtilService.getStringValueForObject(param[8]));
					  inchargeMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), inchargeVo);
					}
				}
			}
			if(inchargeMap != null && inchargeMap.size()>0){
				finalList.addAll(inchargeMap.values());
			}
			
			
		}catch(Exception e){
			LOG.error("Exception occurred at getAreaInchargeDetails() of AreaInchargeDashBoardService class ",e);
		}
		return finalList;
		
	}
	
	public AreaInchargeVO getAreaInchargesStatusWiseCount(Long levelId,Long levelValue){
		AreaInchargeVO finalVo =new AreaInchargeVO();
		try{
			//0-locationId,1-inActive,2-locationName
			List<Object[]> totalList = areaInchargeLocationDAO.getAreaInchargesStatusWiseCount(levelId,levelValue);
			  finalVo = settingAssignedAndUnAssignedCount(totalList,finalVo);
			if(finalVo != null){
				finalVo.setAssignPer(Double.parseDouble(cadreDetailsService.calculatePercentage(finalVo.getTotal(), finalVo.getAssignedCount())));
				finalVo.setPendingPers(Double.parseDouble(cadreDetailsService.calculatePercentage(finalVo.getTotal(), finalVo.getUnAssignedCount())));
			}
		}catch(Exception e){
			LOG.error("Exception occurred at getAreaInchargesStatusWiseCount() of AreaInchargeDashBoardService class ",e);
		}
		return finalVo;
		
	}
	public AreaInchargeVO settingAssignedAndUnAssignedCount(List<Object[]> totalList,AreaInchargeVO finalVo){
		try{
			Long total=0l,assignCount=0l,unAssignCount =0l;
			Set<String> assignIds = new HashSet<String>();
			if(totalList != null && totalList.size()>0){
				for(Object[] param : totalList){
					String status=commonMethodsUtilService.getStringValueForObject(param[1]);
					if(status != null && status.equalsIgnoreCase("Y")){
						assignCount=assignCount+1l;
						finalVo.setAssignedCount(assignCount);
						assignIds.add(commonMethodsUtilService.getStringValueForObject(param[0]));
					}else if(status != null && status.equalsIgnoreCase("N")){
						unAssignCount=unAssignCount+1l;
						finalVo.setUnAssignedCount(unAssignCount);
					}
					finalVo.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
					total=total+1l;
					finalVo.setTotal(total);
				}
				
			}
		if(assignIds != null && assignIds.size()>0){
			 Long inchargeCount = areaInchargeMemberDAO.getInchargeMembers(assignIds);
			 if(inchargeCount != null && inchargeCount.longValue()>0l){
				 finalVo.setInchargeCount(inchargeCount);
			 }
		  }
			
		}catch(Exception e){
			LOG.error("Exception occurred at settingAssignedAndUnAssignedCount() of AreaInchargeDashBoardService class ",e);
		}
		return finalVo;
	}
	public List<InchargeMemberVO> getBoothAssignedpercentageBaseConstituencies(String fromDateStr,String toDateStr,Long activityMemberId){
		List<AreaInchargeVO> finalList =new ArrayList<AreaInchargeVO>();
		List<InchargeMemberVO> returnList = null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<Long,Set<Long>> locationAccessLevelMap = new HashMap<Long, Set<Long>>(0);
			 Long userAccessLevelId=0l;
			 Set<Long> locationValuesSet= null;
			 List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
				   userAccessLevelId = commonMethodsUtilService.getLongValueForObject(rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0]);
				   for (Object[] param : rtrnUsrAccssLvlIdAndVlusObjLst) {
				  locationValuesSet= locationAccessLevelMap.get((Long)param[0]);
					 if(locationValuesSet == null){
						 locationValuesSet = new java.util.HashSet<Long>();
						 locationAccessLevelMap.put((Long)param[0],locationValuesSet);
					 }
					 locationValuesSet.add(param[1] != null ? (Long)param[1]:0l);
				}
			   }
			Date startDate = null;
			Date endDate = null;
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && toDateStr != null && !toDateStr.isEmpty() && toDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			Map<Long,AreaInchargeVO> inchargeConstituencyMap =new HashMap<Long,AreaInchargeVO>();
			Long assignedCount =0l,unAssignedCount=0l,total=0l;
			////0-locationId,1-isAssigned,2-constituencyId,3-name
			 List<Object[]> constituencyList = areaInchargeLocationDAO.getConstituenciesBaseBoothLocationCount(userAccessLevelId,locationValuesSet);
			 if(constituencyList != null && constituencyList.size()>0l){
				 for(Object[] param: constituencyList){
					String assignedStatus =commonMethodsUtilService.getStringValueForObject(param[1]);
					AreaInchargeVO inchargeVo = inchargeConstituencyMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
					if(inchargeVo == null){
						inchargeVo = new AreaInchargeVO();
						inchargeVo.setId(commonMethodsUtilService.getLongValueForObject(param[2]));
						inchargeVo.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
						if(assignedStatus != null && assignedStatus.equalsIgnoreCase("N")){
							assignedCount =assignedCount+1;
							inchargeVo.setUnAssignedCount(assignedCount);
						}else if(assignedStatus != null && assignedStatus.equalsIgnoreCase("Y")){
							assignedCount =assignedCount+1;
							inchargeVo.setAssignedCount(assignedCount);
						}
						total =total+1;
						inchargeVo.setTotal(total);
						inchargeConstituencyMap.put(commonMethodsUtilService.getLongValueForObject(param[2]),inchargeVo);
					}else{
						if(assignedStatus != null && assignedStatus.equalsIgnoreCase("N")){
							assignedCount =assignedCount+1;
							inchargeVo.setUnAssignedCount(assignedCount);
						}else if(assignedStatus != null && assignedStatus.equalsIgnoreCase("Y")){
							assignedCount =assignedCount+1;
							inchargeVo.setAssignedCount(assignedCount);
						}
						total = total+1;
						inchargeVo.setTotal(total);
					}
				 }
			 }
			if(inchargeConstituencyMap != null && inchargeConstituencyMap.size()>0){
				finalList.addAll(inchargeConstituencyMap.values());
			}
			Map<String,InchargeMemberVO> areaPerMAp = new HashMap<String,InchargeMemberVO>();
			   returnList = setAreaInchargeAssignedTemplete(areaPerMAp);
			   returnList = setAreaInchargeAssignedPer(finalList,returnList,areaPerMAp);
		
			
		}catch(Exception e){
			e.printStackTrace();
			Log.error("Exception raised in getLevelWisePostsOverView method of LocationDashboardService"+e);
		}
		return returnList;
	}
	public List<InchargeMemberVO> setAreaInchargeAssignedTemplete(Map<String,InchargeMemberVO> areaPerMAp){
		List<InchargeMemberVO> returnList = new ArrayList<InchargeMemberVO>();
		try{
			String[] perArr = {"0","1-10","10-20","20-30","30-40","40-50","50-60","60-70","70-80","80-90","90"};
			for(String param : perArr){
				InchargeMemberVO perVo =areaPerMAp.get(param);
				if(perVo == null ){
					perVo = new InchargeMemberVO();
					perVo.setName(param);
					areaPerMAp.put(param, perVo);
				}
			}
			if(areaPerMAp != null && areaPerMAp.size()>0){
				returnList.addAll(areaPerMAp.values());
			}
		}catch(Exception e){
			e.printStackTrace();
			Log.error("Exception raised in setAreaInchargeAssignedTemplete method of LocationDashboardService"+e);
		}
		
		return returnList;
		
	}
	public List<InchargeMemberVO> setAreaInchargeAssignedPer(List<AreaInchargeVO> finalList,List<InchargeMemberVO> returnList,Map<String,InchargeMemberVO> areaPerMAp){
		
		//List<InchargeMemberVO> returnList = new ArrayList<InchargeMemberVO>();
		try{
			Long total =0l;
			if(finalList != null && finalList.size()>0){
				for(AreaInchargeVO inchargeVO : finalList){
					inchargeVO.setAssignPer(Double.parseDouble(cadreDetailsService.calculatePercentage(inchargeVO.getTotal(),inchargeVO.getAssignedCount())));
					if(Double.valueOf(inchargeVO.getAssignPer()) >=1 && Double.valueOf(inchargeVO.getAssignPer()) <10){
						InchargeMemberVO  memberVo = areaPerMAp.get(1-10);
						memberVo.setOneToTenCount(memberVo.getOneToTenCount()+1);
					}else if(Double.valueOf(inchargeVO.getAssignPer()) >=10 && Double.valueOf(inchargeVO.getAssignPer()) <20){
						InchargeMemberVO  memberVo = areaPerMAp.get(10-20);
						memberVo.setTenToTwentyCount(memberVo.getTenToTwentyCount()+1);
					}else if(Double.valueOf(inchargeVO.getAssignPer()) >=20 && Double.valueOf(inchargeVO.getAssignPer()) <30){
						InchargeMemberVO  memberVo = areaPerMAp.get(20-30);
						memberVo.setTwentyToThirCount(memberVo.getTwentyToThirCount()+1);
					}else if(Double.valueOf(inchargeVO.getAssignPer()) >=30 && Double.valueOf(inchargeVO.getAssignPer()) <40){
						InchargeMemberVO  memberVo = areaPerMAp.get(30-40);
						memberVo.setThirToFrtyCount(memberVo.getThirToFrtyCount()+1);
					}else if(Double.valueOf(inchargeVO.getAssignPer()) >=40 && Double.valueOf(inchargeVO.getAssignPer()) <50){
						InchargeMemberVO  memberVo = areaPerMAp.get(40-50);
						memberVo.setFrtyToFivtyCount(memberVo.getFrtyToFivtyCount()+1);
					}else if(Double.valueOf(inchargeVO.getAssignPer()) >=50 && Double.valueOf(inchargeVO.getAssignPer()) <60){
						InchargeMemberVO  memberVo = areaPerMAp.get(50-60);
						memberVo.setFivtyToSixtyCount(memberVo.getFivtyToSixtyCount()+1);
					}else if(Double.valueOf(inchargeVO.getAssignPer()) >=60 && Double.valueOf(inchargeVO.getAssignPer()) <70){
						InchargeMemberVO  memberVo = areaPerMAp.get(60-70);
						memberVo.setSixtyToSeventyCount(memberVo.getSixtyToSeventyCount()+1);
					}else if(Double.valueOf(inchargeVO.getAssignPer()) >=80 && Double.valueOf(inchargeVO.getAssignPer()) <90){
						InchargeMemberVO  memberVo = areaPerMAp.get(80-90);
						memberVo.setEightyToNightyCount(memberVo.getEightyToNightyCount()+1);
					}else if(Double.valueOf(inchargeVO.getAssignPer()) >=90){
						InchargeMemberVO  memberVo = areaPerMAp.get(90);
						memberVo.setAboveNighty(memberVo.getAboveNighty()+1);
					}else if(Double.valueOf(inchargeVO.getAssignPer()) ==0){
						//perVO.setZeroPerCount(perVO.getZeroPerCount()+1);
					}
					total = total+1;
					//returnList.add(perVO);
				}
				returnList.get(0).setCount(total);
			}
			if(returnList != null && returnList.size()>0){
				for(InchargeMemberVO inchargeVO : returnList){
					if(inchargeVO.getOneToTenCount()>0l){
					 inchargeVO.setAssignPer(Double.parseDouble(cadreDetailsService.calculatePercentage(total,inchargeVO.getOneToTenCount())));
					}else if(inchargeVO.getTenToTwentyCount() >0l){
					 inchargeVO.setAssignPer(Double.parseDouble(cadreDetailsService.calculatePercentage(total,inchargeVO.getTenToTwentyCount())));
					}else if(inchargeVO.getTwentyToThirCount() >0l){
					 inchargeVO.setAssignPer(Double.parseDouble(cadreDetailsService.calculatePercentage(total,inchargeVO.getTwentyToThirCount())));
					}else if(inchargeVO.getThirToFrtyCount() >0l){
					 inchargeVO.setAssignPer(Double.parseDouble(cadreDetailsService.calculatePercentage(total,inchargeVO.getThirToFrtyCount())));
					}else if(inchargeVO.getFrtyToFivtyCount() >0l){
					 inchargeVO.setAssignPer(Double.parseDouble(cadreDetailsService.calculatePercentage(total,inchargeVO.getFrtyToFivtyCount())));
					}else if(inchargeVO.getFivtyToSixtyCount() >0l){
					 inchargeVO.setAssignPer(Double.parseDouble(cadreDetailsService.calculatePercentage(total,inchargeVO.getFivtyToSixtyCount())));
					}else if(inchargeVO.getSixtyToSeventyCount() >0l){
					 inchargeVO.setAssignPer(Double.parseDouble(cadreDetailsService.calculatePercentage(total,inchargeVO.getSixtyToSeventyCount())));
					}else if(inchargeVO.getEightyToNightyCount() >0l){
					 inchargeVO.setAssignPer(Double.parseDouble(cadreDetailsService.calculatePercentage(total,inchargeVO.getEightyToNightyCount() )));
					}else if(inchargeVO.getAboveNighty() >0l){
					 inchargeVO.setAssignPer(Double.parseDouble(cadreDetailsService.calculatePercentage(total,inchargeVO.getAboveNighty())));
					}else if(inchargeVO.getZeroPerCount() >0l){
					 inchargeVO.setAssignPer(Double.parseDouble(cadreDetailsService.calculatePercentage(total,inchargeVO.getZeroPerCount())));
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			Log.error("Exception raised in setAreaInchargeAssignedPer method of LocationDashboardService"+e);
		}
		return returnList;
	}
}
