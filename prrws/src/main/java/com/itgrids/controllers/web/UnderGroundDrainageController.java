package com.itgrids.controllers.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.DocumentVO;
import com.itgrids.dto.GovtMainWorkVO;
import com.itgrids.dto.GovtWorksVO;
import com.itgrids.dto.LocationAddressVO;
import com.itgrids.dto.MobileAppInputVO;
import com.itgrids.dto.MobileAppLoginVO;
import com.itgrids.dto.ResultStatus;
import com.itgrids.dto.SmallVO;
import com.itgrids.dto.WorkStatusVO;
import com.itgrids.service.IUnderGroundDrainageService;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class UnderGroundDrainageController {
	private static final Logger LOG = Logger.getLogger(UnderGroundDrainageController.class);
	
	@Autowired
	private IUnderGroundDrainageService underGroundDrainageService;
	
	@RequestMapping(value = "/mobileAppLogin", method = RequestMethod.POST)
	public @ResponseBody  MobileAppLoginVO   saveRealtimeStatusByVillages(@RequestBody MobileAppInputVO inputVO){
		MobileAppLoginVO VO = new MobileAppLoginVO();
		try {
			if(inputVO != null){
				if(inputVO.getUserName() != null && inputVO.getPassword() != null){
					VO = underGroundDrainageService.checkLogin(inputVO); 
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised while checking the login", e);
			VO.setStatus("failure");
		}
		return VO;
	}
	
	/*@RequestMapping(value = "/getUserWorkTypesWork", method = RequestMethod.POST)
	public @ResponseBody  List<GovtWorksVO>   getUserWorkTypesWork(@RequestBody MobileAppInputVO inputVO){
		try {
			if(inputVO != null){
				if(inputVO.getUserId() != null && inputVO.getWorkTypeId() != null){
					return underGroundDrainageService.getWorkDetailsOfMobileAppUser(inputVO.getUserId(),inputVO.getWorkTypeId()); 
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised while checking the login", e);
		}
		return null;
	}*/
	
	@RequestMapping(value = "/getUserWorkTypesMainWorks", method = RequestMethod.POST)
	public @ResponseBody  List<GovtMainWorkVO>   getUserWorkTypesMainWorks(@RequestBody MobileAppInputVO inputVO){
		try {
			if(inputVO != null){
				if(inputVO.getUserId() != null && inputVO.getWorkTypeId() != null){
					return underGroundDrainageService.getUsersGovtMainWorks(inputVO.getUserId(),inputVO.getWorkTypeId()); 
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised while checking the login", e);
		}
		return null;
	}
	
	
	
	@RequestMapping(value="/saveWorkDetails", method=RequestMethod.POST)
	public @ResponseBody ResultStatus saveWorkDetails(@RequestBody GovtWorksVO govtWorksVO){
		try {
			return underGroundDrainageService.saveWorkDetails(govtWorksVO);
		} catch (Exception e) {
			LOG.error("Exception raised while saving the govt works details", e);
		}
		return null;
	} 
	
	@RequestMapping(value="/getAllGovtWorksOfGovtMainWork", method=RequestMethod.POST)
	public @ResponseBody List<GovtWorksVO> getAllGovtWorksOfGovtMainWork(@RequestBody MobileAppInputVO inputVO){
		try {
			return underGroundDrainageService.getAllGovtWorksOfGovtMainWork(inputVO.getUserId(),inputVO.getMainWorkId());
		} catch (Exception e) {
			LOG.error("Exception raised while fetcheg the all govt works of govt main work", e);
		}
		return null;
	} 
	
	@RequestMapping(value="/getAllTheStatusOfGovtWork", method=RequestMethod.POST)
	public @ResponseBody List<WorkStatusVO> getAllTheStatusOfGovtWork(@RequestBody MobileAppInputVO inputVO){
		try {
			return underGroundDrainageService.getAllTheStatusOfGovtWork(inputVO.getWorkId());
		} catch (Exception e) {
			LOG.error("Exception raised while fetcheg the all status of govtwork", e);
		}
		return null;
	} 
	
	@RequestMapping(value="/updateWorkStatus", method=RequestMethod.POST)
	public @ResponseBody ResultStatus updateWorkStatus(@RequestBody List<WorkStatusVO> WorkStatusVOList){
		try {
			return underGroundDrainageService.updateWorkStatus(WorkStatusVOList);
		} catch (Exception e) {
			LOG.error("Exception raised while updating the govt works status details", e);
		}
		return null;
	} 
	
	@RequestMapping(value="/updateWorkStatusDocuments", method=RequestMethod.POST)
	public @ResponseBody List<DocumentVO> updateWorkStatusDocuments(@RequestBody List<WorkStatusVO> WorkStatusVOList){
		try {
			return underGroundDrainageService.updateWorkStatusDocuments(WorkStatusVOList);
		} catch (Exception e) {
			LOG.error("Exception raised while updating the govt works status updateWorkStatusDocuments", e);
		}
		return null;
	} 
	
	//userTypeId,userIds
	@RequestMapping(value="/getSubUsers", method=RequestMethod.POST)
	public @ResponseBody Object getSubUsers(@RequestBody MobileAppInputVO inputVO){
		try {
			return underGroundDrainageService.getSubUsers(inputVO.getUserTypeId(),inputVO.getUserIds(),inputVO.getWorkTypeId());
		} catch (Exception e) {
			LOG.error("Exception raised while getting the sub users ", e);
		}
		return null;
	}
	
	@RequestMapping(value="/getUsersAssignedLocations", method=RequestMethod.POST)
	public @ResponseBody Object getUsersAssignedLocations(@RequestBody MobileAppInputVO inputVO){
		try {
			return underGroundDrainageService.getUsersAssignedLocations(inputVO.getUserIds());
		} catch (Exception e) {
			LOG.error("Exception raised at getUsersAssignedLocations", e);
		}
		return null;
	}
	
	@RequestMapping(value="/getAllWorkZonesOfLocations", method=RequestMethod.POST)
	public @ResponseBody Object getAllWorkZonesOfLocations(@RequestBody MobileAppInputVO inputVO){
		try {
			return underGroundDrainageService.getAllWorkZonesOfLocations(inputVO.getLocationScopeId(),inputVO.getLocationIds());
		} catch (Exception e) {
			LOG.error("Exception raised at getAllWorkZonesOfLocations ", e);
		}
		return null;
	}
	
	@RequestMapping(value="/getStatusWiseDayReport", method=RequestMethod.POST)
	public @ResponseBody List<WorkStatusVO> getStatusWiseDayReport(@RequestBody MobileAppInputVO inputVO){
		try {
			return underGroundDrainageService.getStatusWiseDayReport(inputVO);
		} catch (Exception e) {
			LOG.error("Exception raised while getting the status wise daily kilometers ", e);
		}
		return null;
	}
	
	@RequestMapping(value="/getImgesForMobAppDashboard", method=RequestMethod.POST)
	public @ResponseBody List<SmallVO> getImgesForMobAppDashboard(@RequestBody MobileAppInputVO inputVO){
		try {
			return underGroundDrainageService.getImgesForMobAppDashboard(inputVO);
		} catch (Exception e) {
			LOG.error("Exception raised while getting the status wise images for mobile app dashboard ", e);
		}
		return null;
	}
	
	//web dashboard services -- start
	@RequestMapping(value="/getWorkTypeWiseCompletedDetails", method=RequestMethod.POST)
	public @ResponseBody List<GovtMainWorkVO> getWorkTypeWiseCompletedDetails(@RequestBody MobileAppInputVO inputVO){
		try {
			return underGroundDrainageService.getWorkTypeWiseCompletedDetails();
		} catch (Exception e) {
			LOG.error("Exception raised while getting the status wise daily kilometers ", e);
		}
		return null;
	}
	
	//TIME LINE vs LOCATION
	@RequestMapping(value="/getLocationStatusDayWiseKms", method=RequestMethod.POST)
	public @ResponseBody List<DocumentVO> getLocationStatusDayWiseKms(@RequestBody MobileAppInputVO inputVO){
		try {
			//startDate,endDate,StatusId,WorkTypeId,DistrictId,DivisonId,SubDivisonId,MandalId
			return underGroundDrainageService.getLocationStatusDayWiseKms(inputVO);
		} catch (Exception e) {
			LOG.error("Exception raised while getting the getLocationStatusDayWise kilometers ", e);
		}
		return null;
	}
	
	@RequestMapping(value="/getLocationLevelStatusDayWiseKms", method=RequestMethod.POST)
	public @ResponseBody List<DocumentVO> getLocationLevelStatusDayWiseKms(@RequestBody MobileAppInputVO inputVO){
		try {
			//startDate,endDate,StatusId,WorkTypeId,DistrictId,DivisonId,SubDivisonId,MandalId,locationLevelId
			return underGroundDrainageService.getLocationLevelStatusDayWiseKms(inputVO);
		} catch (Exception e) {
			LOG.error("Exception raised while getting the getLocationStatusDayWise kilometers ", e);
		}
		return null;
	}
	
	@RequestMapping(value="/getLocationLevelSubDayWiseKms", method=RequestMethod.POST)
	public @ResponseBody List<DocumentVO> getLocationLevelSubDayWiseKms(@RequestBody MobileAppInputVO inputVO){
		try {
			//inputs - startDate,endDate,WorkTypeId,locationscopeId,locationLevelId
			return underGroundDrainageService.getLocationLevelSubDayWiseKms(inputVO);
		} catch (Exception e) {
			LOG.error("Exception raised while getting the getLocationStatusDayWise kilometers ", e);
		}
		return null;
	}
	//TIME LINE vs LOCATION
	
	//STATE LEVEL OVERVIEW
	public @ResponseBody GovtMainWorkVO getStateLevelOverAllDetails(@RequestBody MobileAppInputVO inputVO){
		try {
			return underGroundDrainageService.getStateLevelOverAllDetails(inputVO.getWorkTypeId());
		} catch (Exception e) {
			LOG.error("Exception raised while getting getStateLevelOverAllDetails ", e);
		}
		return null;
	}
	
	public @ResponseBody List<DocumentVO> getRecentWorkDocuments(@RequestBody MobileAppInputVO inputVO){
		try {
			return underGroundDrainageService.getRecentWorkDocuments(inputVO.getWorkTypeId());
		} catch (Exception e) {
			LOG.error("Exception raised while getting getStateLevelOverAllDetails ", e);
		}
		return null;
	}
	
	public @ResponseBody List<GovtWorksVO> getStatusWiseWorksAndKms(@RequestBody MobileAppInputVO inputVO){
		try {
			return underGroundDrainageService.getStatusWiseWorksAndKms(inputVO.getWorkTypeId());
		} catch (Exception e) {
			LOG.error("Exception raised while getting getStateLevelOverAllDetails ", e);
		}
		return null;
	}
	
	//STATE LEVEL OVERVIEW
	
	//LOCATION WISE OVERVIEW
	public @ResponseBody List<GovtWorksVO> getLOCATIONWISEOVERVIEW(@RequestBody MobileAppInputVO inputVO){
		try {
			return underGroundDrainageService.getLOCATIONWISEOVERVIEW(inputVO);
		} catch (Exception e) {
			LOG.error("Exception raised while getting getStateLevelOverAllDetails ", e);
		}
		return null;
	}
	//LOCATION WISE OVERVIEW
	
	//get location level wise overview details - screen 3
	public @ResponseBody GovtMainWorkVO getLocationLevelWiseOverviewDetails(@RequestBody MobileAppInputVO inputVO){
		try {//inputs-locationScopeId,locationValue,workTypeId
			return underGroundDrainageService.getLocationLevelWiseOverviewDetails(inputVO.getLocationScopeId(),inputVO.getLocationValue(),inputVO.getWorkTypeId());
		} catch (Exception e) {
			LOG.error("Exception raised at getStateLevelOverAllDetails ", e);
		}
		return null;
	}
	
	public @ResponseBody List<WorkStatusVO> getLocationLevelStatusWiseOverviewDetails(@RequestBody MobileAppInputVO inputVO){
		try {//inputs-locationScopeId,locationValue,workTypeId
			return underGroundDrainageService.getLocationLevelStatusWiseOverviewDetails(inputVO.getLocationScopeId(),inputVO.getLocationValue(),inputVO.getWorkTypeId());
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationLevelStatusWiseOverviewDetails ", e);
		}
		return null;
	}
	
	public @ResponseBody List<WorkStatusVO> getWorkZoneStatusWiseKms(@RequestBody MobileAppInputVO inputVO){
		try {//inputs-locationScopeId,locationValue,workTypeId
			return underGroundDrainageService.getWorkZoneStatusWiseKms(inputVO.getLocationScopeId(),inputVO.getLocationValue(),inputVO.getWorkTypeId());
		} catch (Exception e) {
			LOG.error("Exception raised at getWorkZoneStatusWiseKms ", e);
		}
		return null;
	}
	//get location level wise overview details - screen 3
	
	//web dashboard services -- end	
	
	public @ResponseBody GovtWorksVO getWorkZoneMainOverview(@RequestBody MobileAppInputVO inputVO){
		try {//inputs-GovtWorkId
			return underGroundDrainageService.getWorkZoneMainOverview(inputVO.getWorkId());
		} catch (Exception e) {
			LOG.error("Exception raised while getting getWorkZoneMainOverview ", e);
		}
		return null;
	}
	public @ResponseBody List<WorkStatusVO> getWorkZoneStatusDetailsInfo(@RequestBody MobileAppInputVO inputVO){
		try {//inputs-GovtWorkId,worktypeId
			return underGroundDrainageService.getWorkZoneStatusDetailsInfo(inputVO.getWorkId(),inputVO.getWorkTypeId());
		} catch (Exception e) {
			LOG.error("Exception raised while getting getWorkZoneStatusDetailsInfo ", e);
		}
		return null;
	}	
	public @ResponseBody List<DocumentVO> getWorkZoneDocumentDetailsInfo(@RequestBody MobileAppInputVO inputVO){
		try {//inputs-GovtWorkId
			return underGroundDrainageService.getWorkZoneDocumentDetailsInfo(inputVO.getWorkId());
		} catch (Exception e) {
			LOG.error("Exception raised while getting getWorkZoneDocumentDetailsInfo ", e);
		}
		return null;
	}
	public @ResponseBody List<GovtWorksVO> getWorkZoneWorkStategsDetailsInfo(@RequestBody MobileAppInputVO inputVO){
		try {//inputs-dates,GovtWorkId,statusId
			return underGroundDrainageService.getWorkZoneWorkStategsDetailsInfo(inputVO.getFromDate(),inputVO.getToDate(),inputVO.getWorkId(),inputVO.getStatusId());
		} catch (Exception e) {
			LOG.error("Exception raised while getting getWorkZoneWorkStategsDetailsInfo ", e);
		}
		return null;
	}
}
