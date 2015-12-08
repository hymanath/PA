package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityAttributeQuestionnaireInfoDAO;
import com.itgrids.partyanalyst.dao.IActivityLocationInfoDAO;
import com.itgrids.partyanalyst.dto.ActivityVO;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.service.IActivityService;

public class ActivityService implements IActivityService{

	private static final Logger   LOG = Logger.getLogger(ActivityService.class);
	
	private IActivityAttributeQuestionnaireInfoDAO activityAttributeQuestionnaireInfoDAO;
	private IActivityLocationInfoDAO activityLocationInfoDAO;
	
	
	public IActivityLocationInfoDAO getActivityLocationInfoDAO() {
		return activityLocationInfoDAO;
	}
	public void setActivityLocationInfoDAO(
			IActivityLocationInfoDAO activityLocationInfoDAO) {
		this.activityLocationInfoDAO = activityLocationInfoDAO;
	}
	public IActivityAttributeQuestionnaireInfoDAO getActivityAttributeQuestionnaireInfoDAO() {
		return activityAttributeQuestionnaireInfoDAO;
	}
	public void setActivityAttributeQuestionnaireInfoDAO(
			IActivityAttributeQuestionnaireInfoDAO activityAttributeQuestionnaireInfoDAO) {
		this.activityAttributeQuestionnaireInfoDAO = activityAttributeQuestionnaireInfoDAO;
	}


	public Map<Long,ActivityVO> getAttributeListByQuestionnaireList(List<Long> questionnairesList){
		
		Map<Long,ActivityVO> finalMap = new LinkedHashMap<Long, ActivityVO>();
		
		try {
			
			//0.questionnaireId,1.attributeId,2.attributeName
			List<Object[]> list = activityAttributeQuestionnaireInfoDAO.getAttributeListByQuestionnaires(questionnairesList);
			
			if(list != null && list.size() > 0){
				for (Object[] objects : list) {
					ActivityVO vo = new ActivityVO();
					
					Long questionnaireId = (Long) (objects[0] != null ? objects[0]:0l);
					vo.setId((Long) (objects[1] != null ? objects[1]:0l));
					vo.setName(objects[2] != null ? objects[2].toString():"");
					
					finalMap.put(questionnaireId, vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getAttributeListByQuestionnaireList in ActivityService service", e);
		}
		return finalMap;
	}
	
	public List<ActivityVO> getActivityDayWiseCountsByLocation(SearchAttributeVO searchAttributeVO){
		
		List<ActivityVO> finalList = new ArrayList<ActivityVO>();
		
		try {
			Map<String,ActivityVO> datesMap = new LinkedHashMap<String, ActivityVO>();
			
			datesMap = getDatesWiseCounts(searchAttributeVO.getStartDate(), searchAttributeVO.getEndDate(), "Day");
			
			//0.locationId,1.locationName,2.locationLevelId,3.date,4.count
			List<Object[]> dayWiseList = activityLocationInfoDAO.getActivityDayWiseCountsByLocation(searchAttributeVO);
			
			if(dayWiseList != null && dayWiseList.size() > 0){
				for (Object[] objects : dayWiseList) {
					ActivityVO mapvo = new ActivityVO();
					
					String date = objects[3] != null ? objects[3].toString():"";
					if(date != null && date.length() > 0){
						mapvo = datesMap.get(date);
						if(mapvo != null){
							mapvo.setId((Long) (objects[0] != null ? objects[0]:0l));
							mapvo.setAttendedCount((Long) (objects[4] != null ? objects[4]:0l));
						}
					}
										
					finalList.add(mapvo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getActivityDayWiseCountsByLocation in ActivityService service", e);
		}
		return finalList;
	}
	
	public Map<String,ActivityVO> getDatesWiseCounts(Date startDate,Date endDate,String name){
		
		Map<String,ActivityVO> returnMap = new LinkedHashMap<String,ActivityVO>();
		try {
			
			List<String> dates=getBetweenDatesInString(startDate,endDate);
			
			if(name != null && name.length() > 0){
				if(dates != null && dates.size() > 0){
					for (int i = 0; i < dates.size(); i++) {
						ActivityVO vo = new ActivityVO();
						
						vo.setName(dates.get(i)+" ( "+name+"-"+i+" ) ");
						
						returnMap.put(dates.get(i), vo);
					}
				}
			}else{
				if(dates != null && dates.size() > 0){
					for (int i = 0; i < dates.size(); i++) {
						ActivityVO vo = new ActivityVO();
						
						vo.setName(dates.get(i));
						
						returnMap.put(dates.get(i), vo);
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getDatesWiseCounts in ActivityService service", e);
		}
		return returnMap;
	}
	
	public List<String> getBetweenDatesInString(Date fromDate,Date toDate){
		
		List<String> dateStr = new ArrayList<String>(0);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(fromDate);
		cal.add(Calendar.DATE, -1);
		
		while (cal.getTime().before(toDate)) {
		    cal.add(Calendar.DATE, 1);
		    dateStr.add(sdf.format(cal.getTime()));
		    
		}
		return dateStr;
	}
}
