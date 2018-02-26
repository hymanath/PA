package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityInfoDocumentDAO;
import com.itgrids.partyanalyst.dto.DalithaTejamInputVo;
import com.itgrids.partyanalyst.dto.DalithaTejamVO;
import com.itgrids.partyanalyst.service.IDalithaTejamDashBoardService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class DalithaTejamDashBoardService implements IDalithaTejamDashBoardService {
	
	public static Logger Log = Logger.getLogger(CustomVoterGroupAnalysisService.class);
	
	private IActivityInfoDocumentDAO activityInfoDocumentDAO;

	private CommonMethodsUtilService commonMethodsUtilService;
	

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
	

	public List<DalithaTejamVO> getLatestImages(DalithaTejamInputVo inputVo){
		List<DalithaTejamVO> finalList = new ArrayList<DalithaTejamVO>();
		try{
			Date fromDate=null, toDate=null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if(inputVo.getFromDate()!= null && inputVo.getToDate() !=null && inputVo.getFromDate().length()>0 && inputVo.getToDate().length() >0){
				fromDate = sdf.parse(inputVo.getFromDate());
				toDate = sdf.parse(inputVo.getToDate());
			}
			List<Object[]> imagesList= activityInfoDocumentDAO.getlatestimages(fromDate,toDate,inputVo.getLocationScopeId(),inputVo.getLocationValue(),inputVo.getActivityId());
			
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

}
