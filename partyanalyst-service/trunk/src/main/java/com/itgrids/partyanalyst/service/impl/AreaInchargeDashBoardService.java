package com.itgrids.partyanalyst.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAreaInchargeLocationDAO;
import com.itgrids.partyanalyst.dao.IAreaInchargeMemberDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.AreaInchargeVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.AreaInchargeLocation;
import com.itgrids.partyanalyst.model.AreaInchargeMember;
import com.itgrids.partyanalyst.service.IAreaInchargeDashBoardService;
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

	public AreaInchargeVO getAreaInchargeDetails(String voterIdNO,String mobileNo,String memberShipId){
		AreaInchargeVO inchargeVo = new AreaInchargeVO();
		try{
			//1-first name,2-relativeName,3-age,4-gender,5-houseNo,6-tehsilId,7-tehsilName,8-casteName,9-image
			List<Object[]> inchargeDetails = tdpCadreDAO.getAreaInchargeDetails(voterIdNO,mobileNo,memberShipId);
			
			if(inchargeDetails != null && inchargeDetails.size()>0){
				for(Object[] param: inchargeDetails){
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
				}
			}
			String isActive = areaInchargeMemberDAO.getActiveOrInActiveInchageDetails(inchargeVo.getId());
			if(isActive != null && isActive.equalsIgnoreCase("Y")){
				inchargeVo.setIsActive(isActive);
			}else{
				inchargeVo.setIsActive("N");
			}
			
		}catch(Exception e){
			LOG.error("Exception occurred at getAreaInchargeDetails() of AreaInchargeDashBoardService class ",e);
		}
		
		return inchargeVo;
		
	}
	
	public ResultStatus savingAssigningBooths(final Long cadreId, final List<Long> boothIds){
		ResultStatus status = new ResultStatus();
		try{
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					//getLocationIdsOfBooths(boothIds);
					for(Long param : boothIds){
						//Long inchargeLocationId = areaInchargeLocationDAO.getLocationIdsOfBooths(param);
						AreaInchargeLocation modal = areaInchargeLocationDAO.get(param);
						modal.setIsAssinged("Y");
						modal.setIsDeleted("N");
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

			});
			status.setMessage("SUCCESS");
			return status;
		}catch(Exception e){
			status.setMessage("fail");
			LOG.error("Exception occurred at savingAssigningBooths() of AreaInchargeDashBoardService class ",e);
		}
		
		return status;
		
		
	}

	public AreaInchargeVO getAssignedAndUnAssignedBooths(){
		AreaInchargeVO finalVO = new AreaInchargeVO();
		try{
			Long total = 0l,count =0l;
			//0-inchargeLocationId,1-isAssigned,2-panchayatId,3-panchaytName,4-tehsilId,5-tehsilName
			List<Object[]> unAssignedList = areaInchargeLocationDAO.getAssignedAndUnAssignedBooths("N");
			if(unAssignedList != null && unAssignedList.size()>0){
				for(Object[] param : unAssignedList ){
						settingAssignedAndUnAssignedList(param,finalVO,"unAssigned",count,total);
				}
			}	
			List<Object[]> assignedList = areaInchargeLocationDAO.getAssignedAndUnAssignedBooths("Y");
			if(assignedList != null && assignedList.size()>0){
				for(Object[] param : assignedList ){
					settingAssignedAndUnAssignedList(param,finalVO,"assigned",count,total);
					
				}
			}
			
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
	public void settingAssignedAndUnAssignedList(Object[] param,AreaInchargeVO finalVO,String status,Long count,Long total){
		try{
			AreaInchargeVO assignVO = new AreaInchargeVO();
			assignVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
			assignVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(param[4]));
			assignVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[5]));
			assignVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(param[2]));
			assignVO.setPanchayatName(commonMethodsUtilService.getStringValueForObject(param[3]));
			if(status != null && status.equalsIgnoreCase("unAssigned ")){
				finalVO.getUnAssignBoothList().add(assignVO);
				finalVO.setUnAssignedCount(count+1);
			}else if(status != null && status.equalsIgnoreCase("assigned")){
				finalVO.getAssignBoothList().add(assignVO);
				finalVO.setAssignedCount(count+1);
			}
			finalVO.setTotal(total+1);
			
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
	public AreaInchargeVO editAssignedInchargeDetails(Long tdpCadreId,List<Long> boothIds){
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
				Long count=0l,total=0l;
				List<Object[]> unAssignedList = areaInchargeLocationDAO.getAssignedAndUnAssignedBooths("N");
				if(unAssignedList != null && unAssignedList.size()>0){
					for(Object[] param : unAssignedList ){
						settingAssignedAndUnAssignedList(param,inchargeVo,"unAssigned",count,total);
					}
				}
				if(boothIds != null && boothIds.size()>0){
					assignBooths.addAll(boothIds);
					inchargeVo.setAssignBoothIds(assignBooths);
				}
		}catch(Exception e){
			LOG.error("Exception occurred at editAssignedInchargeDetails() of AreaInchargeDashBoardService class ",e);
		}
		return inchargeVo;
	}
	
}
