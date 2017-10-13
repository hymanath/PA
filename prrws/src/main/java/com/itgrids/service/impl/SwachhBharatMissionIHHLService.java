package com.itgrids.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.SwachhBharatMissionIHHLDtlsVO;
import com.itgrids.service.ISwachhBharatMissionIHHLService;

@Service
@Transactional
public class SwachhBharatMissionIHHLService implements ISwachhBharatMissionIHHLService{

	private static final Logger LOG = Logger.getLogger(SwachhBharatMissionIHHLService.class);
	
	
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO 
	 * @description {This service is used to get Swachh Bharat Mission overview details.}
	 * @return SwachhBharatMissionIHHLDtlsVO
	 * @Date 14-10-2017
	 */
	public SwachhBharatMissionIHHLDtlsVO getSwachhBharatMissionOverviewDtls(InputVO inputVO) {
		SwachhBharatMissionIHHLDtlsVO resultVO = new SwachhBharatMissionIHHLDtlsVO();
		try {
			resultVO.setTarget(10000l);
			resultVO.setAchivement(8000l);
		} catch (Exception e) {
			LOG.error("Exception occured at getSwachhBharatMissionOverviewDtls() in SwachhBharatMissionIHHLService class",e);
		}
		return resultVO;
	}
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to get Swachh Bharat Mission Status details.}
	 * @return SwachhBharatMissionIHHLDtlsVO
	 * @Date 14-10-2017
	 */
	public SwachhBharatMissionIHHLDtlsVO getSwachhBharatMissionStatusOverviewDtls(InputVO inputVO) {
		SwachhBharatMissionIHHLDtlsVO resultVO = new SwachhBharatMissionIHHLDtlsVO();
		try {
			resultVO.setTarget(10000l);
			resultVO.setGrounded(2000l);
			resultVO.setNoTGrounded(8000l);
			resultVO.setInProgress(1000l);
			resultVO.setCompleted(1000l);
		} catch (Exception e) {
			LOG.error("Exception occured at getSwachhBharatMissionStatusDtls() in SwachhBharatMissionIHHLService class",e);
		}
		return resultVO;
	}
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to get Swachh Bharat Mission category wise analysis details.}
	 * @return List<SwachhBharatMissionIHHLDtlsVO>
	 * @Date 14-10-2017
	 */
	public List<SwachhBharatMissionIHHLDtlsVO> getIHHLCategoryWiseAnalysis(InputVO inputVO) {
		List<SwachhBharatMissionIHHLDtlsVO> resultList = new ArrayList<SwachhBharatMissionIHHLDtlsVO>(0);
		try {
			SwachhBharatMissionIHHLDtlsVO vo = new SwachhBharatMissionIHHLDtlsVO();
			vo.setRange("100% TO 76%");
			vo.setDistrictCount(10l);
			vo.setConstituencyCount(5l);
			vo.setMandalCount(2l);
			SwachhBharatMissionIHHLDtlsVO vo1 = new SwachhBharatMissionIHHLDtlsVO();
			vo1.setRange("75% TO 51%");
			vo1.setDistrictCount(10l);
			vo1.setConstituencyCount(5l);
			vo1.setMandalCount(2l);
			resultList.add(vo);
			resultList.add(vo1);
		} catch (Exception e) {
			LOG.error("Exception occured at getIHHLCategoryWiseAnalysis() in SwachhBharatMissionIHHLService class",e);
		}
		return resultList;
	}
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to get  Swachh Bharat Mission IHHL Achivement progress details.}
	 * @return List<SwachhBharatMissionIHHLDtlsVO>
	 * @Date 14-10-2017
	 */
	public List<SwachhBharatMissionIHHLDtlsVO> getIHHLAchivementProgressDtls(InputVO inputVO) {
		List<SwachhBharatMissionIHHLDtlsVO> resultList = new ArrayList<SwachhBharatMissionIHHLDtlsVO>(0);
		try {
			SwachhBharatMissionIHHLDtlsVO timeRangeVO = new SwachhBharatMissionIHHLDtlsVO();
			timeRangeVO.setRange("01-08-2017");
			timeRangeVO.setTarget(10000l);
			timeRangeVO.setCompleted(1000l);
			SwachhBharatMissionIHHLDtlsVO timeRangeVO1 = new SwachhBharatMissionIHHLDtlsVO();
			timeRangeVO1.setRange("02-08-2017");
			timeRangeVO1.setTarget(10000l);
			timeRangeVO1.setCompleted(2000l);
			SwachhBharatMissionIHHLDtlsVO timeRangeVO2 = new SwachhBharatMissionIHHLDtlsVO();
			timeRangeVO2.setRange("03-08-2017");
			timeRangeVO2.setTarget(10000l);
			timeRangeVO2.setCompleted(3000l);
			resultList.add(timeRangeVO);
			resultList.add(timeRangeVO1);
			resultList.add(timeRangeVO2);
		} catch (Exception e) {
			LOG.error("Exception occured at getIHHLAchivementProgressDtls() in SwachhBharatMissionIHHLService class",e);
		}
		return resultList;
	}
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to get Swachh Bharat Mission details location wise.}
	 * @return List<SwachhBharatMissionIHHLDtlsVO>
	 * @Date 14-10-2017
	 */
	public List<SwachhBharatMissionIHHLDtlsVO> getSwachhBharatMissionLocationWiseDetails(InputVO inputVO) {
		List<SwachhBharatMissionIHHLDtlsVO> resultList = new ArrayList<SwachhBharatMissionIHHLDtlsVO>(0);
		try {
			SwachhBharatMissionIHHLDtlsVO locationVO = new SwachhBharatMissionIHHLDtlsVO();
			locationVO.setLocationId(1l);
			locationVO.setName("Andhra Pradesh");
			locationVO.setTarget(10000l);
			locationVO.setGrounded(2000l);
			locationVO.setNoTGrounded(8000l);
			locationVO.setInProgress(1000l);
			locationVO.setCompleted(1000l);
			locationVO.setPercentage(50d);
			resultList.add(locationVO);
		} catch (Exception e) {
			LOG.error("Exception occured at getSwachhBharatMissionLocationWiseDetails() in SwachhBharatMissionIHHLService class",e);
		}
		return resultList;
	}
}
