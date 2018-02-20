package com.itgrids.partyanalyst.exceptionalReport.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dto.TrainingCampProgramVO;
import com.itgrids.partyanalyst.exceptionalReport.service.ITrainingCampExceptionalReportService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.SetterAndGetterUtilService;

public class TrainingCampExceptionalReportService implements ITrainingCampExceptionalReportService{
	private final static Logger LOG = Logger.getLogger(TrainingCampExceptionalReportService.class);
	private CommonMethodsUtilService commonMethodsUtilService;
	private SetterAndGetterUtilService setterAndGetterUtilService;
	private DateUtilService dateUtilService;
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public void setSetterAndGetterUtilService(
			SetterAndGetterUtilService setterAndGetterUtilService) {
		this.setterAndGetterUtilService = setterAndGetterUtilService;
	}
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	public TrainingCampProgramVO getOverallTrainingCampReport(String startDate,String endDate, Long stateId,int size){
		try{
			TrainingCampProgramVO campProgramVO = new TrainingCampProgramVO();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date stDate = null;
			Date ndDate = null;
			if(startDate != null && startDate.trim().length() == 10 && endDate != null && endDate.trim().length() == 10){
				stDate = sdf.parse(startDate.trim());
				ndDate = sdf.parse(endDate.trim());
			}
			campProgramVO.setTotalEligibleCount(1000L);
			campProgramVO.setTotalAttenedCount(500L);
			campProgramVO.setTotalAttenedCountPer(50.00D);
			return campProgramVO;
		}catch(Exception e){
			LOG.error("Exception raised at getListOfParliamentsWithPoorPerformance() method of TrainingCampExceptionalReportService", e);
		}
		return null;
	}
	public List<TrainingCampProgramVO> getListOfParliamentsWithPoorPerformance(String startDate,String endDate, Long stateId,int size){
		try{
			List<TrainingCampProgramVO> fianlList = new ArrayList<TrainingCampProgramVO>();
			TrainingCampProgramVO campProgramVO = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date stDate = null;
			Date ndDate = null;
			if(startDate != null && startDate.trim().length() == 10 && endDate != null && endDate.trim().length() == 10){
				stDate = sdf.parse(startDate.trim());
				ndDate = sdf.parse(endDate.trim());
			}
			TrainingCampProgramVO campProgramVO1 = new TrainingCampProgramVO();
			campProgramVO1.setId(1L);
			campProgramVO1.setName("ANANTAPUR");
			campProgramVO1.setTotalEligibleCount(100L);
			campProgramVO1.setTotalAttenedCount(50L);
			campProgramVO1.setTotalAttenedCountPer(50.00D);
			fianlList.add(campProgramVO1);
			TrainingCampProgramVO campProgramVO2 = new TrainingCampProgramVO();
			campProgramVO2.setId(1L);
			campProgramVO2.setName("ARAKU");
			campProgramVO2.setTotalEligibleCount(100L);
			campProgramVO2.setTotalAttenedCount(50L);
			campProgramVO2.setTotalAttenedCountPer(50.00D);
			fianlList.add(campProgramVO2);
			TrainingCampProgramVO campProgramVO3 = new TrainingCampProgramVO();
			campProgramVO3.setId(1L);
			campProgramVO3.setName("GUNTUR");
			campProgramVO3.setTotalEligibleCount(100L);
			campProgramVO3.setTotalAttenedCount(50L);
			campProgramVO3.setTotalAttenedCountPer(50.00D);
			fianlList.add(campProgramVO3);
			Collections.sort(fianlList, new Comparator<TrainingCampProgramVO>(){
				@Override
				public int compare(TrainingCampProgramVO obj1,TrainingCampProgramVO obj2) {
					Double value1 = obj1.getTotalAttenedCountPer();
					Double value2 = obj2.getTotalAttenedCountPer();
					return value1.compareTo(value2);
				}
			});
			
			
			
			if(fianlList.size() > size){
				fianlList = fianlList.subList(0, size);
			}
			return fianlList;
		}catch(Exception e){
			LOG.error("Exception raised at getListOfParliamentsWithPoorPerformance() method of TrainingCampExceptionalReportService", e);
		}
		return null;
	}
	public List<TrainingCampProgramVO> getListOfAssemblyWithPoorPerformance(String startDate,String endDate, Long stateId,int size){
		try{
			List<TrainingCampProgramVO> finalList = new ArrayList<TrainingCampProgramVO>();
			TrainingCampProgramVO campProgramVO = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date stDate = null;
			Date ndDate = null;
			if(startDate != null && startDate.trim().length() == 10 && endDate != null && endDate.trim().length() == 10){
				stDate = sdf.parse(startDate.trim());
				ndDate = sdf.parse(endDate.trim());
			}
			
			TrainingCampProgramVO campProgramVO1 = new TrainingCampProgramVO();
			campProgramVO1.setId(1L);
			campProgramVO1.setName("ANANTAPUR");
			campProgramVO1.setConstituency("SINGANAMALA");
			campProgramVO1.setTotalEligibleCount(100L);
			campProgramVO1.setTotalAttenedCount(50L);
			campProgramVO1.setTotalAttenedCountPer(50.00D);
			finalList.add(campProgramVO1);
			TrainingCampProgramVO campProgramVO2 = new TrainingCampProgramVO();
			campProgramVO2.setId(1L);
			campProgramVO2.setName("ANANTAPUR");
			campProgramVO2.setConstituency("TADAPTRI");
			campProgramVO2.setTotalEligibleCount(100L);
			campProgramVO2.setTotalAttenedCount(20L);
			campProgramVO2.setTotalAttenedCountPer(20.00D);
			finalList.add(campProgramVO2);
			TrainingCampProgramVO campProgramVO3 = new TrainingCampProgramVO();
			campProgramVO3.setId(1L);
			campProgramVO3.setName("ANANTAPUR");
			campProgramVO3.setConstituency("URAVAKONDA");
			campProgramVO3.setTotalEligibleCount(100L);
			campProgramVO3.setTotalAttenedCount(60L);
			campProgramVO3.setTotalAttenedCountPer(60.00D);
			finalList.add(campProgramVO3);
			Collections.sort(finalList, new Comparator<TrainingCampProgramVO>(){
				@Override
				public int compare(TrainingCampProgramVO obj1,TrainingCampProgramVO obj2) {
					Double value1 = obj1.getTotalAttenedCountPer();
					Double value2 = obj2.getTotalAttenedCountPer();
					return value1.compareTo(value2);
				}
			});
			
			if(finalList.size() > size){
				finalList = finalList.subList(0, size);
			}
			return finalList;
		}catch(Exception e){
			LOG.error("Exception raised at getListOfAssemblyWithPoorPerformance() method of TrainingCampExceptionalReportService", e);
		}
		return null;
	}
}
