package com.itgrids.partyanalyst.exceptionalReport.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.dto.PartyMeetingExceptionalReportVO;
import com.itgrids.partyanalyst.exceptionalReport.service.IPartyMeetingExceptionalReportService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class PartyMeetingExceptionalReportService implements IPartyMeetingExceptionalReportService {

	private final static Logger LOG = Logger.getLogger(PartyMeetingExceptionalReportService.class);
	
	private CommonMethodsUtilService commonMethodsUtilService;
	
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	/**
	   * @param InputVO inputVO
	   * @return PartyMeetingExceptionalReportVO 
	   * @author Santosh Kumar Verma 
	   * @Description :This Service Method is used to get party meeting location level wise report. 
	   *  @since 19-FEB-2018
	   */
		public PartyMeetingExceptionalReportVO getPartyMeetingExceptionReportMeetingLevelWise(InputVO inputVO) {
			PartyMeetingExceptionalReportVO resultVO = new PartyMeetingExceptionalReportVO();
			try {
				List<Long> partyMeetingLevelIds = getPartyMeetingLevelIds(inputVO);
				List<Date> dateList = getDates(inputVO.getFromDateStr(), inputVO.getToDateStr(), new SimpleDateFormat("dd/MM/yyyy"));
				inputVO.setPartyMeetingLevelIds(partyMeetingLevelIds);
				inputVO.setFromDate(dateList.get(0));
				inputVO.setToDate(dateList.get(1));
				
				resultVO.setTotalCount(100l);
				resultVO.setConductedCount(80l);
				resultVO.setNotConductedCount(20l);
				resultVO.setPercentage(20d);
				resultVO.setSubList(new ArrayList<PartyMeetingExceptionalReportVO>());
			} catch (Exception e) {
				LOG.error("Exception occurred  at getPartyMeetingExceptionReportMeetingLevelWise() in TrainingCampExceptionalReportService class",e);
			}
			return resultVO;
		}

	public List<Long> getPartyMeetingLevelIds(InputVO inputVO) {
		List<Long> partyMeetingLevelIds = new ArrayList<Long>();
		try {
			if (inputVO.getLocationLevel() != null) {
				if (inputVO.getLocationLevel().equalsIgnoreCase("Constituency")) {
					partyMeetingLevelIds.add(3l);
				} else if (inputVO.getLocationLevel().equalsIgnoreCase("mandalTownDivision")) {
					partyMeetingLevelIds.add(4l);
					partyMeetingLevelIds.add(5l);
					partyMeetingLevelIds.add(6l);
				} else if (inputVO.getLocationLevel().equalsIgnoreCase("villageWard")) {
					partyMeetingLevelIds.add(7l);
					partyMeetingLevelIds.add(8l);
				}
			}

		} catch (Exception e) {
			LOG.error("Exception occurred  at getPartyMeetingLevelIds() in TrainingCampExceptionalReportService class",e);
		}
		return partyMeetingLevelIds;
	}

	public List<Date> getDates(String startDateString, String endDateString,SimpleDateFormat sdf) {
		List<Date> datesList = new ArrayList<Date>();
		Date startDate = null;
		Date endDate = null;
		try {
			if (startDateString != null && !startDateString.isEmpty()) {
				startDate = sdf.parse(startDateString);
			}
			if (endDateString != null && !endDateString.isEmpty()) {
				endDate = sdf.parse(endDateString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		datesList.add(0, startDate);
		datesList.add(1, endDate);
		return datesList;
	}
}
