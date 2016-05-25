package com.itgrids.partyanalyst.dao.hibernate;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;

import com.itextpdf.text.DocumentException;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IEventAttendeeDAO;
import com.itgrids.partyanalyst.dao.IEventAttendeeErrorDAO;
import com.itgrids.partyanalyst.dao.IEventDAO;
import com.itgrids.partyanalyst.dao.IEventInviteeDAO;
import com.itgrids.partyanalyst.dto.MahanaduEventVO;
import com.itgrids.partyanalyst.service.IMahaNaduService;
import com.itgrids.partyanalyst.service.IMahanaduDashBoardService;
import com.itgrids.partyanalyst.service.IMahanaduDashBoardService1;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class EventAttendeeDAOHibernateTest extends BaseDaoTestCase{
	
	private IEventAttendeeDAO eventAttendeeDAO;
	private IEventInviteeDAO eventInviteeDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IEventAttendeeErrorDAO eventAttendeeErrorDAO;
	private IEventDAO eventDAO;
	private IMahaNaduService mahaNaduService;
	private IMahanaduDashBoardService1 mahanaduDashBoardService1;
	private IMahanaduDashBoardService mahanaduDashBoardService;
	/*private CommonMethodsUtilService commonMethodUtilService;*/
	
	DateUtilService dateUtilService = new DateUtilService();
	
	SimpleDateFormat format = new SimpleDateFormat("MMM dd yyyy hh:mm a");
	
	public IEventAttendeeDAO getEventAttendeeDAO() {
		return eventAttendeeDAO;
	}
	public void setEventAttendeeDAO(IEventAttendeeDAO eventAttendeeDAO) {
		this.eventAttendeeDAO = eventAttendeeDAO;
	}
	public IEventInviteeDAO getEventInviteeDAO() {
		return eventInviteeDAO;
	}
	public void setEventInviteeDAO(IEventInviteeDAO eventInviteeDAO){
		this.eventInviteeDAO = eventInviteeDAO;
	}
	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO(){
		return delimitationConstituencyDAO;
	}
	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO){
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}
	
	public IEventAttendeeErrorDAO getEventAttendeeErrorDAO() {
		return eventAttendeeErrorDAO;
	}
	public void setEventAttendeeErrorDAO(
			IEventAttendeeErrorDAO eventAttendeeErrorDAO) {
		this.eventAttendeeErrorDAO = eventAttendeeErrorDAO;
	}
	
	public IEventDAO getEventDAO() {
		return eventDAO;
	}
	public void setEventDAO(IEventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}
	public IMahaNaduService getMahaNaduService() {
		return mahaNaduService;
	}
	public void setMahaNaduService(IMahaNaduService mahaNaduService) {
		this.mahaNaduService = mahaNaduService;
	}

	public IMahanaduDashBoardService getMahanaduDashBoardService() {
		return mahanaduDashBoardService;
	}
	public void setMahanaduDashBoardService(
			IMahanaduDashBoardService mahanaduDashBoardService) {
		this.mahanaduDashBoardService = mahanaduDashBoardService;
	}
	public IMahanaduDashBoardService1 getMahanaduDashBoardService1() {
		return mahanaduDashBoardService1;
	}
	public void setMahanaduDashBoardService1(
			IMahanaduDashBoardService1 mahanaduDashBoardService1) {
		this.mahanaduDashBoardService1 = mahanaduDashBoardService1;
	}
	
	/*public CommonMethodsUtilService getCommonMethodUtilService() {
		return commonMethodUtilService;
	}
	public void setCommonMethodUtilService(
			CommonMethodsUtilService commonMethodUtilService) {
		this.commonMethodUtilService = commonMethodUtilService;
	}*/
	
	//TEST CASE.
	
	/*public void sreeTest() throws DocumentException, IOException{
		
		Long parentId = 30l;
		
		List<Long> subEventIds = new ArrayList<Long>(0);
		subEventIds.add(31l);
		subEventIds.add(35l);
		subEventIds.add(36l);
		subEventIds.add(37l);
		
		String startDate="24/05/2016";
		String endDate = "26/05/2016";
		
		List<Long> stateIds = new ArrayList<Long>();
		stateIds.add(1l);
		stateIds.add(36l);
		stateIds.add(0l);
		
		Long parentId = 7l;
		
		List<Long> subEventIds = new ArrayList<Long>(0);
		subEventIds.add(8l);
		subEventIds.add(12l);
		subEventIds.add(13l);
		subEventIds.add(16l);
		
		String startDate="27/05/2015";
		String endDate = "29/05/2015";
		
		List<Long> stateIds = new ArrayList<Long>();
		stateIds.add(1l);
		stateIds.add(36l);
		
		//getAllImages(parentId,subEventIds,startDate,endDate,stateIds);
		
	}*/
	
	public  void test(){
		
		List<Long> subEventIds = new ArrayList<Long>(0);
		subEventIds.add(31l);
		subEventIds.add(35l);
		subEventIds.add(36l);
		subEventIds.add(37l);
		
		getPublicrepresentatives("24/05/2016","26/05/2016",30l,subEventIds);
		
	}
	
	  public List<MahanaduEventVO> getPublicrepresentatives(String startDateStr,String endDateStr,Long eventId,List<Long> subEventIds){
		  
		  List<MahanaduEventVO> finallist = new ArrayList<MahanaduEventVO>();
		  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		  try{
			  
			  //Dates
			  Date startDate = null;
			  Date endDate = null;
			  if( startDateStr != null && !startDateStr.isEmpty()){
				  startDate = sdf.parse(startDateStr);
			  }
			  if( endDateStr != null && !endDateStr.isEmpty()){
				  endDate = sdf.parse(endDateStr);
			  }
			  List<Date>  betweenDates=new CommonMethodsUtilService().getBetweenDates(startDate,endDate);
			  
			  //designations
			  List<Long> designationIds =Arrays.asList(IConstants.PUBLIC_REPR_DESIGNATION_IDS);
			  
			  
			  Map<Long,MahanaduEventVO> designationsMap = new LinkedHashMap<Long, MahanaduEventVO>();
			  
			  //TOTAL INVITEES
			  List<Object[]> inviteesList = eventInviteeDAO.getPublicRepresentiveInvitessForEvent(eventId,designationIds);
			  if( inviteesList != null && inviteesList.size() > 0){
				  
				  for(Object[] obj : inviteesList){
					  
					  MahanaduEventVO desgVO = new MahanaduEventVO();
					  desgVO.setId( obj[0]!= null ? (Long)obj[0] :0l);
					  desgVO.setName(obj[1]!= null ? obj[1].toString() :"");
					  desgVO.setInvitees(obj[2]!= null ? (Long)obj[2] :0l);
					  
					  //false
					  if(betweenDates != null && betweenDates .size() > 0){
						  for( int i=0;i<betweenDates.size();i++){
							  MahanaduEventVO dayVO = new MahanaduEventVO();
							  dayVO.setName("Day"+(i+1));
							  dayVO.setDateStr(format.format(betweenDates.get(i)));
							  dayVO.setNotAttended( desgVO.getInvitees() );
							  dayVO.setTotalDaydataExist(false);
							  if(desgVO.getSubMap() == null){
								  desgVO.setSubMap(new LinkedHashMap<String, MahanaduEventVO>(0));  
							  }
							  desgVO.getSubMap().put(dayVO.getDateStr(),dayVO);
						  }
					  }
					  designationsMap.put( desgVO.getId() , desgVO);
				  }
			  }
			  
			  //Day Wise Attended.
			  List<Object[]> dayWiseList = eventInviteeDAO.dayWisePublicRepInviteesAttendedForEvent(startDate,endDate,subEventIds,designationIds);
			  if( dayWiseList != null && dayWiseList.size() > 0){
				  
				  for( Object[] obj : dayWiseList){
					  
					  MahanaduEventVO designationVO = designationsMap.get((Long)obj[0]);
					  if( designationVO != null ){
						  MahanaduEventVO dayVO = designationVO.getSubMap().get(obj[2].toString());
						  
						  if( dayVO != null){
							  
							  dayVO.setAttended(obj[3]!=null ? (Long)obj[3]:0l);
							  dayVO.setNotAttended( designationVO.getInvitees() - dayVO.getAttended());
							  
							  designationsMap.entrySet().iterator().next().getValue().getSubMap().get(obj[2].toString()).setTotalDaydataExist(true);
							  
						  }
					  }
				  }
			  }
			  
			  
			  if( designationsMap!= null && designationsMap.size() > 0){
					 for (Map.Entry<Long, MahanaduEventVO> entry : designationsMap.entrySet()) {
						 if (entry.getValue().getSubMap() != null){
							 entry.getValue().getSubList().addAll(entry.getValue().getSubMap().values());
							 entry.getValue().getSubMap().clear();
						 } 
					 }
					 finallist.addAll(designationsMap.values());
				 }
			  
		  }catch (Exception e) {
			e.printStackTrace();
		  }
		  return finallist;
	  }
	
	
}
