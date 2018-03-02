package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityInfoDocumentDAO;
import com.itgrids.partyanalyst.dao.IAffiliatedMemberDAO;
import com.itgrids.partyanalyst.dto.DalithaTejamInputVo;
import com.itgrids.partyanalyst.dto.DalithaTejamVO;
import com.itgrids.partyanalyst.service.IDalithaTejamDashBoardService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class DalithaTejamDashBoardService implements IDalithaTejamDashBoardService {
	
	public static Logger Log = Logger.getLogger(CustomVoterGroupAnalysisService.class);
	
	private IActivityInfoDocumentDAO activityInfoDocumentDAO;

	private CommonMethodsUtilService commonMethodsUtilService;

	private IAffiliatedMemberDAO affiliatedMemberDAO;
	

	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	public IActivityInfoDocumentDAO getActivityInfoDocumentDAO() {
		return activityInfoDocumentDAO;
	}

	public void setActivityInfoDocumentDAO(
			IActivityInfoDocumentDAO activityInfoDocumentDAO) {
		this.activityInfoDocumentDAO = activityInfoDocumentDAO;
	}
	
	public IAffiliatedMemberDAO getAffiliatedMemberDAO() {
		return affiliatedMemberDAO;
	}

	public void setAffiliatedMemberDAO(IAffiliatedMemberDAO affiliatedMemberDAO) {
		this.affiliatedMemberDAO = affiliatedMemberDAO;
	}

	public List<DalithaTejamVO> getLatestImages(DalithaTejamInputVo inputVo){
		List<DalithaTejamVO> finalList = new ArrayList<DalithaTejamVO>();
		try{
			Date fromDate=null, toDate=null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if(inputVo.getFromDate()!= null && inputVo.getToDate() !=null && inputVo.getFromDate().length()>0 && inputVo.getToDate().length() >0){
				fromDate = sdf.parse(inputVo.getFromDate());
				toDate = sdf.parse(inputVo.getToDate());
			}
			List<Object[]> imagesList= activityInfoDocumentDAO.getlatestimages(fromDate,toDate,inputVo.getLocationScopeIds(),inputVo.getLocationValues(),inputVo.getActivityId());
			
			for (Object[] objects : imagesList) {
				DalithaTejamVO dalithaTejamVO=new DalithaTejamVO();
				
				dalithaTejamVO.setImageId(commonMethodsUtilService.getLongValueForObject(objects[0]));
				dalithaTejamVO.setImagePath(commonMethodsUtilService.getStringValueForObject(objects[1]));
				dalithaTejamVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(objects[2]));
				dalithaTejamVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(objects[3]));
				dalithaTejamVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(objects[4]));
				dalithaTejamVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(objects[5]));
				dalithaTejamVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(objects[6]));
				dalithaTejamVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(objects[7]));
				dalithaTejamVO.setVillageId(commonMethodsUtilService.getLongValueForObject(objects[8]));
				dalithaTejamVO.setVillageName(commonMethodsUtilService.getStringValueForObject(objects[9]));
				
				finalList.add(dalithaTejamVO);
			}
		}catch(Exception e){
			Log.error("Error Occured in getLatestImages of DalithaTejamDashBoardService "+e);
		}
		return finalList;
	}
	
	@Override
	public List<DalithaTejamVO> getDateWiseCount(DalithaTejamInputVo inputVo){
		List<DalithaTejamVO> finalList = new ArrayList<DalithaTejamVO>();
		try{
			Date fromDate=null, toDate=null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if(inputVo.getFromDate()!= null && inputVo.getToDate() !=null && inputVo.getFromDate().length()>0 && inputVo.getToDate().length() >0){
				fromDate = sdf.parse(inputVo.getFromDate());
				toDate = sdf.parse(inputVo.getToDate());
			}
			List<Object[]> registeredCount =new ArrayList<Object[]>();
			registeredCount = affiliatedMemberDAO.getDayWisrRegisteredCount(fromDate,toDate,inputVo.getLocationScopeId(),inputVo.getLocationValue(),"register");
			List<Object[]> loanCount = affiliatedMemberDAO.getDayWisrRegisteredCount(fromDate,toDate,inputVo.getLocationScopeId(),inputVo.getLocationValue(),"loan");
			List<Object[]> visitedViilages = affiliatedMemberDAO.getDayWisrVisitedCount(fromDate,toDate,inputVo.getLocationScopeId(),inputVo.getLocationValue());
			
			if(commonMethodsUtilService.isListOrSetValid(registeredCount)){
				registeredCount.addAll(loanCount);
			}else{
				registeredCount.addAll(loanCount);
			}
			DalithaTejamVO vo = new DalithaTejamVO();
			for (Object[] objects : registeredCount) {
				vo.setTodayRegistred(commonMethodsUtilService.getLongValueForObject(objects[0])+vo.getTodayRegistred());
				vo.setTodayLoanApplied(commonMethodsUtilService.getLongValueForObject(objects[1])+vo.getTodayLoanApplied());
				vo.setDistrictId(commonMethodsUtilService.getLongValueForObject(objects[2]));
			}
			finalList.add(vo);
			for (Object[] objects : visitedViilages) {
				for (DalithaTejamVO finalVo : finalList) {
					if(commonMethodsUtilService.getLongValueForObject(objects[1])==finalVo.getDistrictId()){
						finalVo.setTodayvisted(commonMethodsUtilService.getLongValueForObject(objects[0])+finalVo.getTodayvisted());
					}
				}
				
			}
			
		}catch(Exception e){
			Log.error("Error Occured in getDateWiseCount of DalithaTejamDashBoardService "+e);
		}
		return finalList;
		
	}
}
