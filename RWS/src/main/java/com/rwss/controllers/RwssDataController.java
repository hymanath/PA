package com.rwss.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rwss.dto.BasicVO;
import com.rwss.dto.HabitationDetailsVO;
import com.rwss.dto.InputVO;
import com.rwss.dto.LocationVO;
import com.rwss.model.RwsMinAssetView;
import com.rwss.model.RwsMinConstituencyView;
import com.rwss.model.RwsMinHabView;
import com.rwss.model.RwsMinWorksAdminView;
import com.rwss.model.RwsMinWorkscompView;
import com.rwss.service.IHabitationDetailsOnClickService;
import com.rwss.service.IHabitationDetailsService;
import com.rwss.utils.IConstants;


@EnableAutoConfiguration
@RestController
@RequestMapping("/cd")
public class RwssDataController {

	private static final Logger LOG = LoggerFactory.getLogger(RwssDataController.class);

	@Autowired
	private IHabitationDetailsService habitationDetailsService;
	
	@Autowired
	private IHabitationDetailsOnClickService habitationDetailsOnClickService;
	
	@Autowired
	private Environment environment;
	
	private JSONObject jobj;
	
	@RequestMapping(value = "/getHabitationCoverageByStatusByLocationType", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON)
	public @ResponseBody List<LocationVO> getHabitationCoverageByStatusByLocationType(@RequestBody InputVO inputVo) {
		List<LocationVO> habitationDtslLst = new ArrayList<LocationVO>();
		try {
				habitationDtslLst = habitationDetailsService.getHabitationCoverageByStatusByLocationType(inputVo);
			
			return habitationDtslLst;
		} catch (Exception e) {
			LOG.error("Error occured at getHabitationCoverageByStatusByLocationType() in RwssDataController class",e);
			LocationVO locationVO = new LocationVO();
			locationVO.setStatus(IConstants.RESULT_FAILURE);
			locationVO.setExceptionMessage(e.getLocalizedMessage());
			habitationDtslLst.add(locationVO);
			return habitationDtslLst;
		}

	}

	@RequestMapping(value = "/getLocationBasedOnSelection", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON)
	public @ResponseBody List<LocationVO> getLocationBasedOnSelection(@RequestBody InputVO inputVo) {
		List<LocationVO> locationList = new ArrayList<LocationVO>();
		try {
				locationList = habitationDetailsService.getLocationBasedOnSelection(inputVo);
				return locationList;
		} catch (Exception e) {
			LOG.error("Error occured at getLocationBasedOnSelection() in RwssDataController class",e);
			LocationVO locationVO = new LocationVO();
			locationVO.setStatus(IConstants.RESULT_FAILURE);
			locationVO.setExceptionMessage(e.getLocalizedMessage());
			locationList.add(locationVO);
			return locationList;
		}
	}

	@RequestMapping(value = "/getAssetsinfo", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON)
	public @ResponseBody String getAssetInfo(@RequestBody InputVO inputVo) {
		
		try {
			return habitationDetailsService.getAssetInfo(inputVo);
			
		} catch (Exception e) {
			jobj = new JSONObject();

			LOG.error("Error occured at getHabitationCoverageByStatusByLocationType() in RwssDataController class",e);
			jobj.put(IConstants.STATUS, IConstants.RESULT_FAILURE);
			jobj.put("exceptionMessage", e.getLocalizedMessage());
			return jobj.toString();
		}

	}

	@RequestMapping(value = "/gethabitationWatersupplyDetails", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON)
	public @ResponseBody String gethabitationWatersupplyDetails(@RequestBody InputVO inputVo) {
		try {
			return habitationDetailsService.gethabitationWatersupplyDetails(inputVo);
		} catch (Exception e) {
			jobj = new JSONObject();
			LOG.error("Error occured at gethabitationsupplyDetails() in RwssDataController class",e);
			jobj.put(IConstants.STATUS, IConstants.RESULT_FAILURE);
			jobj.put("exceptionMessage", e.getLocalizedMessage());
			return jobj.toString();
		}

	}

	@RequestMapping(value = "/getStressedHabitationInfoInALocation", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON)
	public @ResponseBody LocationVO getStressedHabitationInfoInALocation(@RequestBody InputVO inputVo) {
		LocationVO locationHabitationDtlsVO = new LocationVO();
		try {
			locationHabitationDtlsVO = habitationDetailsService.getStressedHabitationInfoInALocation(inputVo);
			return locationHabitationDtlsVO;
		} catch (Exception e) {
			LOG.error("Error occured at getStressedHabitationInfoInALocation() in RwssDataController class",e);
			locationHabitationDtlsVO.setStatus(IConstants.RESULT_FAILURE);
			locationHabitationDtlsVO.setExceptionMessage(e.getLocalizedMessage());
			return locationHabitationDtlsVO;
		}
	}

	@RequestMapping(value = "/getSchemesDetails", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON)
	public @ResponseBody List<BasicVO> getSchemesDataByDate(@RequestBody InputVO inputVo) {
		BasicVO basicVO = new BasicVO();
		List<BasicVO> schemesDataList = new ArrayList<BasicVO>();
		try {
				schemesDataList = habitationDetailsService.getSchemesDataByDate(inputVo);
				return schemesDataList;
		} catch (Exception e) {
			LOG.error("Error occured at getSchemesDataByDate() in RwssDataController class",e);
			basicVO.setStatus(IConstants.RESULT_FAILURE);
			
			schemesDataList.add(basicVO);
			return schemesDataList;
		}
	}

	@RequestMapping(value = "/getLabTestDetails", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON)
	public @ResponseBody BasicVO getLabTestDetails(@RequestBody InputVO inputVo, HttpServletResponse httpResponse) {
		BasicVO labTestDetails = new BasicVO();
		try {
				labTestDetails = habitationDetailsService.getLabTestDetails(inputVo);
				return labTestDetails;
		} catch (Exception e) {
			LOG.error("Error occured at getLabTestDetails() in RwssDataController class",e);
			labTestDetails.setStatus(IConstants.RESULT_FAILURE);
			labTestDetails.setExceptionMessage(e.getLocalizedMessage());
			return labTestDetails;
		}

	}

	@RequestMapping(value = "/getSchemeWiseWorkDetails", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON)
	public @ResponseBody List<LocationVO> getSchemeWiseWorkDetails(@RequestBody InputVO inputVo) {
		List<LocationVO> schemeWiseWorkList = new ArrayList<LocationVO>();
		LocationVO basicVO = new LocationVO();
		try {
			schemeWiseWorkList = habitationDetailsService.getSchemeWiseWorkDetails(inputVo);
			return schemeWiseWorkList;
		} catch (Exception e) {
			LOG.error("Error occured at getSchemeWiseWorkDetails() in RwssDataController class",e);
			basicVO.setStatus(IConstants.RESULT_FAILURE);
			basicVO.setExceptionMessage(e.getLocalizedMessage());
			schemeWiseWorkList.add(basicVO);
			return schemeWiseWorkList;
		}

	}

	@RequestMapping(value = "/getAllHabitationDetails", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON)
	public @ResponseBody List<RwsMinHabView> getAllHabitationDetails(@RequestHeader(value = "Authorization") String credentials, @RequestBody InputVO inputVo,
			HttpServletResponse httpResponse) {
		List<RwsMinHabView> allHabDataList = new ArrayList<RwsMinHabView>();
		RwsMinHabView rwsMinHabView = new RwsMinHabView();
		try {
			if (!credentials.isEmpty() && environment.getRequiredProperty("user3").equalsIgnoreCase((credentials.split(" ")[1]))) {
				allHabDataList = habitationDetailsService.getAllHabitationDetails(inputVo);
			} else {
				rwsMinHabView.setCoverageStatus("UnAuthorized Access, Please Check Credantials");
				allHabDataList.add(rwsMinHabView);
				httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
			return allHabDataList;
		} catch (Exception e) {
			LOG.error("Error occured at getAllHabitationDetails() in RwssDataController class",e);
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getAllHabitationConstitencyData", method = RequestMethod.POST)
	public @ResponseBody List<RwsMinConstituencyView> getAllHabitationConstitencyData(@RequestHeader(value = "Authorization") String credentials, 
			HttpServletResponse httpResponse) {
		List<RwsMinConstituencyView> allHabConstituencyList = new ArrayList<RwsMinConstituencyView>();
		try {
			if (!credentials.isEmpty() && environment.getRequiredProperty("user3").equalsIgnoreCase((credentials.split(" ")[1]))) {
				allHabConstituencyList = habitationDetailsService.getAllHabitationConstitencyData();
			} else {
				httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
			return allHabConstituencyList;
		} catch (Exception e) {
			LOG.error("Error occured at getAllHabitationConstitencyData() in RwssDataController class",e);
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getAllAssestDetails", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON)
	public @ResponseBody List<RwsMinAssetView> getAllAssestDetails(@RequestHeader(value = "Authorization") String credentials,HttpServletResponse httpResponse) {
		List<RwsMinAssetView> allHabConstituencyList = new ArrayList<RwsMinAssetView>();
		try {
			if (!credentials.isEmpty() && environment.getRequiredProperty("user3").equalsIgnoreCase((credentials.split(" ")[1]))) {
				allHabConstituencyList = habitationDetailsService.getAllAssestDetails();
			} else {
				httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
			return allHabConstituencyList;
		} catch (Exception e) {
			LOG.error("Error occured at getAllAssestDetails() in RwssDataController class",e);
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getAllWorkAdminDetails", method = RequestMethod.POST)
	public @ResponseBody List<RwsMinWorksAdminView> getAllWorkAdminDetails(@RequestHeader(value = "Authorization") String credentials,HttpServletResponse httpResponse) {
		List<RwsMinWorksAdminView> allWorksDetailList = new ArrayList<RwsMinWorksAdminView>();
		try {
			if (!credentials.isEmpty() && environment.getRequiredProperty("user3").equalsIgnoreCase((credentials.split(" ")[1]))) {
				allWorksDetailList = habitationDetailsService.getAllWorkAdminDetails();
			} else {
				httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
			return allWorksDetailList;
		} catch (Exception e) {
			LOG.error("Error occured at getAllWorkAdminDetails() in RwssDataController class",e);
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getAllWorkComplitionDetails", method = RequestMethod.POST)
	public @ResponseBody List<RwsMinWorkscompView> getAllWorkComplitionDetails(@RequestHeader(value = "Authorization") String credentials, HttpServletResponse httpResponse) {
		List<RwsMinWorkscompView> allWorksCompleteList = new ArrayList<RwsMinWorkscompView>();
		try {
			if (!credentials.isEmpty() && environment.getRequiredProperty("user3").equalsIgnoreCase((credentials.split(" ")[1]))) {
				allWorksCompleteList = habitationDetailsService.getAllWorkComplitionDetails();
			} else {
				httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
			return allWorksCompleteList;
		} catch (Exception e) {
			LOG.error("Error occured at getAllWorkComplitionDetails() in RwssDataController class",e);
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/getWaterSourceDeatils", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON, consumes=MediaType.APPLICATION_JSON)
	public @ResponseBody String getWaterSourceDeatils(@RequestBody InputVO inputVo) {
		jobj = new JSONObject();
		try {
			String result = habitationDetailsService.getWaterSourceDeatils(inputVo);
			return result;
		} catch (Exception e) {
			LOG.error("Error occured at getSchemeWiseWorkDetails() in RwssDataController class",e);
			jobj.put("status", IConstants.RESULT_FAILURE);
			jobj.put("exceptionMessage",e.getMessage());
			
			return jobj.toString();
		}

	}
	@RequestMapping(value = "/getWaterSourceDeatils2", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON, consumes=MediaType.APPLICATION_JSON)
	public @ResponseBody List<LocationVO> getWaterSourceDeatils2(@RequestBody InputVO inputVo) {
		List<LocationVO> result = new ArrayList<LocationVO>();
		try {
			result = habitationDetailsService.getWaterSourceDeatils2(inputVo);
			return result;
		} catch (Exception e) {
			LOG.error("Error occured at getSchemeWiseWorkDetails() in RwssDataController class",e);
			LocationVO basicVO = new LocationVO();
			basicVO.setStatus(IConstants.RESULT_FAILURE);
			basicVO.setExceptionMessage(e.getLocalizedMessage());
			result.add(basicVO);
			return result;
		}

	}
		
	@RequestMapping(value = "/getKpiDeatils", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON,consumes=MediaType.APPLICATION_JSON)
	public @ResponseBody String getKPIDeatils(@RequestBody InputVO inputVo) {
		
		try {
			String result = habitationDetailsService.getKPIDeatils(inputVo);
			return result;
		} catch (Exception e) {
			jobj = new JSONObject();
			LOG.error("Error occured at getSchemeWiseWorkDetails() in RwssDataController class",e);
			jobj.put("status", IConstants.RESULT_FAILURE);
			jobj.put("exceptionMessage",e.getMessage());
			
			return jobj.toString();
		}

	}
	
	@RequestMapping(value = "/getStressedKPIDeatils", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON,consumes=MediaType.APPLICATION_JSON)
	public @ResponseBody String getStressedKPIDeatils(@RequestBody InputVO inputVo) {
		
		jobj = new JSONObject();
		try {
			String result = habitationDetailsService.getStressedKPIDeatils(inputVo);
			return result;
		} catch (Exception e) {
			jobj = new JSONObject();
			LOG.error("Error occured at getSchemeWiseWorkDetails() in RwssDataController class",e);
			jobj.put("status", IConstants.RESULT_FAILURE);
			jobj.put("exceptionMessage",e.getMessage());
			
			return jobj.toString();
		}

	}
	
	@RequestMapping(value="/getOnclickWorkDetails", method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON,produces=MediaType.APPLICATION_JSON)
	public @ResponseBody String getOnclickWorkDetails(@RequestBody InputVO inputVO) {
	
		try{
			String result= habitationDetailsOnClickService.getOnclickWorkDetails(inputVO);
			return result;	
		}catch (Exception e) {
			jobj = new JSONObject();
			LOG.error("Error occured at getSchemeWiseWorkDetails() in RwssDataController class",e);
			jobj.put("status", IConstants.RESULT_FAILURE);
			jobj.put("exceptionMessage",e.getMessage());
			
			return jobj.toString();
		}
		
		
	}
	
	@RequestMapping(value="/getOnclickTargetsAcheievementsDetails", method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON,produces=MediaType.APPLICATION_JSON)
	public @ResponseBody String getOnclickTargetsAcheievementsDetails(@RequestBody InputVO inputVO) {
	
		try{
			String result= habitationDetailsOnClickService.getOnclickTargetsAcheievementsDetails(inputVO);
			return result;	
		}catch (Exception e) {
			jobj = new JSONObject();
			LOG.error("Error occured at getSchemeWiseWorkDetails() in RwssDataController class",e);
			jobj.put("status", IConstants.RESULT_FAILURE);
			jobj.put("exceptionMessage",e.getMessage());
			
			return jobj.toString();
		}
		
	}

	@RequestMapping(value="/getOnclickStressedTargetsAcheievementsDetails", method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON,produces=MediaType.APPLICATION_JSON)
	public @ResponseBody String getOnclickStrssedTargetsAcheievementsDetails(@RequestBody InputVO inputVO) {
	
		try{
			String result= habitationDetailsOnClickService.getOnclickStrssedTargetsAcheievementsDetails(inputVO);
			return result;	
		}catch (Exception e) {
			jobj = new JSONObject();
			LOG.error("Error occured at getSchemeWiseWorkDetails() in RwssDataController class",e);
			jobj.put("status", IConstants.RESULT_FAILURE);
			jobj.put("exceptionMessage",e.getMessage());
			
			return jobj.toString();
		}
		
	}
	@RequestMapping(value = "/getHabitationDetailsByStatusByLocationType", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON)
	public @ResponseBody List<HabitationDetailsVO> getHabitationDetailsByStatusByLocationType(@RequestBody InputVO inputVo) {
		List<HabitationDetailsVO> habitationDtlsList = new ArrayList<HabitationDetailsVO>();
		try {
			habitationDtlsList = habitationDetailsOnClickService.getHabitationDetailsByStatusByLocationType(inputVo);
			return habitationDtlsList;
		} catch (Exception e) {
			LOG.error("Error occured at getHabitationDetailsByStatusByLocationType() in RwssDataController class",e);
			HabitationDetailsVO statusVO = new HabitationDetailsVO();
			statusVO.setStatus(IConstants.RESULT_FAILURE);
			statusVO.setExceptionMessage(e.getLocalizedMessage());
			habitationDtlsList.add(statusVO);
			return habitationDtlsList;
		}
	}
	
	@RequestMapping(value = "/getAssetDetailsByAssetType", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON)
	public @ResponseBody List<HabitationDetailsVO> getAssetDetailsByAssetType(@RequestBody InputVO inputVo) {
		List<HabitationDetailsVO> assestDtsLst = new ArrayList<HabitationDetailsVO>();
		try {
			assestDtsLst = habitationDetailsOnClickService.getAssetDetailsByAssetType(inputVo);
			return assestDtsLst;
		} catch (Exception e) {
			LOG.error("Error occured at getAssetDetailsByAssetType() in RwssDataController class",e);
			HabitationDetailsVO statusVO = new HabitationDetailsVO();
			statusVO.setStatus(IConstants.RESULT_FAILURE);
			statusVO.setExceptionMessage(e.getLocalizedMessage());
			assestDtsLst.add(statusVO);
			return assestDtsLst;
		}

	}
	
	@RequestMapping(value="/getOnclickHabitationsupplyDetails", method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON,produces=MediaType.APPLICATION_JSON)
	public @ResponseBody String getOnclickHabitationsupplyDetails(@RequestBody InputVO inputVO) {
	
		try{
			String result= habitationDetailsOnClickService.getOnclickHabitationsupplyDetails(inputVO);
			return result;	
		}catch (Exception e) {
			jobj = new JSONObject();
			LOG.error("Error occured at getSchemeWiseWorkDetails() in RwssDataController class",e);
			jobj.put("status", IConstants.RESULT_FAILURE);
			jobj.put("exceptionMessage",e.getMessage());
			
			return jobj.toString();
		}
		
	}
	@RequestMapping(value = "/getSchemeDetailsByTypeOfAssestName", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON)
	public @ResponseBody List<HabitationDetailsVO> getSchemeDetailsByTypeOfAssestName(@RequestBody InputVO inputVo) {
		List<HabitationDetailsVO> schemDtslList = new ArrayList<HabitationDetailsVO>();
		try {
			schemDtslList = habitationDetailsOnClickService.getSchemeDetailsByTypeOfAssestName(inputVo);
			return schemDtslList;
		} catch (Exception e) {
			LOG.error("Error occured at getSchemeDetailsByTypeOfAssestName() in RwssDataController class",e);
			HabitationDetailsVO statusVO = new HabitationDetailsVO();
			statusVO.setStatus(IConstants.RESULT_FAILURE);
			statusVO.setExceptionMessage(e.getLocalizedMessage());
			schemDtslList.add(statusVO);
			return schemDtslList;
		}

	}
	
	@RequestMapping(value = "/getWaterSourceDeatilsLocationWise", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON)
	public @ResponseBody List<HabitationDetailsVO> getWaterSourceDeatilsLocationWise(@RequestBody InputVO inputVo) {
		List<HabitationDetailsVO> result = new ArrayList<HabitationDetailsVO>();
		try {
			result = habitationDetailsOnClickService.getWaterSourceDeatilsLocationWise(inputVo);
			return result;
		} catch (Exception e) {
			LOG.error("Error occured at getWaterSourceDeatilsLocationWise() in RwssDataController class",e);
			HabitationDetailsVO statusVO = new HabitationDetailsVO();
			statusVO.setStatus(IConstants.RESULT_FAILURE);
			statusVO.setExceptionMessage(e.getLocalizedMessage());
			result.add(statusVO);
			return result;
		}

	}
	
	@RequestMapping(value = "/getStressedHabitationDetailsByStatusByLocationType", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON)
	public @ResponseBody List<HabitationDetailsVO> getStressedHabitationDetailsByStatusByLocationType(@RequestBody InputVO inputVo) {
		List<HabitationDetailsVO> stressedHabitationDtlsList = new ArrayList<HabitationDetailsVO>();
		try {
			stressedHabitationDtlsList = habitationDetailsOnClickService.getStressedHabitationDetailsByStatusByLocationType(inputVo);
			return stressedHabitationDtlsList;
		} catch (Exception e) {
			LOG.error("Error occured at getStressedHabitationDetailsByStatusByLocationType() in RwssDataController class",e);
			HabitationDetailsVO statusVO = new HabitationDetailsVO();
			statusVO.setStatus(IConstants.RESULT_FAILURE);
			statusVO.setExceptionMessage(e.getLocalizedMessage());
			stressedHabitationDtlsList.add(statusVO);
			return stressedHabitationDtlsList;
		}
	}
	
	
}
