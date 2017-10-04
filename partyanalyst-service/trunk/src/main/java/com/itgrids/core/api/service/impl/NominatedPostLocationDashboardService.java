package com.itgrids.core.api.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.itgrids.core.api.service.INominatedPostLocationDashboardService;
import com.itgrids.partyanalyst.dao.INominatedPostDAO;
import com.itgrids.partyanalyst.dto.NominatedPostCandidateDtlsVO;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class NominatedPostLocationDashboardService implements INominatedPostLocationDashboardService{

	private static Logger LOG = Logger.getLogger(NominatedPostLocationDashboardService.class);
	private CommonMethodsUtilService commonMethodsUtilService;
	private INominatedPostDAO nominatedPostDAO;
	
	
	
	public INominatedPostDAO getNominatedPostDAO() {
		return nominatedPostDAO;
	}
	public void setNominatedPostDAO(INominatedPostDAO nominatedPostDAO) {
		this.nominatedPostDAO = nominatedPostDAO;
	}
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	
	public List<NominatedPostCandidateDtlsVO> getNominatedPositionWiseCandidates(List<Long> locationValues,String fromDateStr, String toDateStr,Long locationTypeId,String year,Long boardLvlId
			,Long startIndex,Long endIndex){
		List<NominatedPostCandidateDtlsVO> finalList = new CopyOnWriteArrayList<NominatedPostCandidateDtlsVO>();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date startDate = null;
			Date endDate = null;
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
		
			List<Object[]> candList = nominatedPostDAO.getNominatedPositionWiseCandidates( locationValues, startDate,  endDate, locationTypeId, year, boardLvlId, startIndex, endIndex);
			if(commonMethodsUtilService.isListOrSetValid(candList)){
				for (Object[] param : candList) {
					NominatedPostCandidateDtlsVO vo = new NominatedPostCandidateDtlsVO();
					vo.setNominatedPostCandiateId(commonMethodsUtilService.getLongValueForObject(param[0]));
					vo.setCandidateName(commonMethodsUtilService.getStringValueForObject(param[1]));
					vo.setImage(commonMethodsUtilService.getStringValueForObject(param[2]));
					vo.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(param[3]));
					vo.setMemberShipId(commonMethodsUtilService.getStringValueForObject(param[4]));
					vo.setDepartmentId(commonMethodsUtilService.getLongValueForObject(param[5]));
					vo.setDepartment(commonMethodsUtilService.getStringValueForObject(param[6]));
					vo.setBoardId(commonMethodsUtilService.getLongValueForObject(param[7]));
					vo.setBoard(commonMethodsUtilService.getStringValueForObject(param[8]));
					vo.setPositionId(commonMethodsUtilService.getLongValueForObject(param[9]));
					vo.setPosition(commonMethodsUtilService.getStringValueForObject(param[10]));
					vo.setStatus(commonMethodsUtilService.getStringValueForObject(param[12]));
					vo.setLevelValue(commonMethodsUtilService.getLongValueForObject(param[13]));
					vo.setLocationName(commonMethodsUtilService.getStringValueForObject(param[14]));
					finalList.add(vo);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			Log.error("Exception raised in getNominatedPositionWiseCandidates method of LocationDashboardService"+e);
		}
		return finalList;
	}
	
	
}
