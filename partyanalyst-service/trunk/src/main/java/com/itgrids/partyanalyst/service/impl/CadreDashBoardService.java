package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import com.itgrids.partyanalyst.dao.IAppDbUpdateDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICadreGhmcDriveUsersDAO;
import com.itgrids.partyanalyst.dao.ICadreRegAmountDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserAssignDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserAssigneeDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictConstituenciesDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITabLogInAuthDAO;
import com.itgrids.partyanalyst.dao.ITabUserEnrollmentInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDataSourceTypeInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreLocationInfoDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserConstituencyAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserDistrictAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dto.AppDbDataVO;
import com.itgrids.partyanalyst.dto.CadreBasicInformationVO;
import com.itgrids.partyanalyst.dto.CadreDashboardVO;
import com.itgrids.partyanalyst.dto.CadreDataSourceTypeVO;
import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.RegistrationCountVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyTransactionVO;
import com.itgrids.partyanalyst.dto.WSResultVO;
import com.itgrids.partyanalyst.model.CadreSurveyUser;
import com.itgrids.partyanalyst.model.CadreSurveyUserAssignee;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.DelimitationConstituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.service.ICadreDashBoardService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IRegistrationService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CadreDashBoardService implements ICadreDashBoardService {

	private static Logger LOG = Logger.getLogger(CadreDashBoardService.class);
	
	private ITdpCadreDAO tdpCadreDAO;
	private IConstituencyDAO constituencyDAO;
	private IBoothDAO boothDAO;
	private IDistrictDAO districtDAO;
	private IPanchayatDAO panchayatDAO;
	private ITehsilDAO tehsilDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private ICadreSurveyUserDAO cadreSurveyUserDAO;
	private DateUtilService dateService = new DateUtilService();
	private IAppDbUpdateDAO appDbUpdateDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private ICadreSurveyUserAssignDetailsDAO cadreSurveyUserAssignDetailsDAO;
	private ICadreSurveyUserAssigneeDAO cadreSurveyUserAssigneeDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IVoterInfoDAO voterInfoDAO;
	private ICadreRegAmountDetailsDAO cadreRegAmountDetailsDAO;
	private ITabLogInAuthDAO tabLogInAuthDAO;
	private IRegistrationService registrationService;

	private IRegionServiceData regionServiceDataImp;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private ICadreGhmcDriveUsersDAO cadreGhmcDriveUsersDAO;
	private ITdpCadreLocationInfoDAO tdpCadreLocationInfoDAO;
	private IStateDAO stateDAO;
	private IUserDistrictAccessInfoDAO userDistrictAccessInfoDAO;
    private IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO;
    private ITabUserEnrollmentInfoDAO tabUserEnrollmentInfoDAO;
    private ITdpCadreDataSourceTypeInfoDAO tdpCadreDataSourceTypeInfoDAO;
    private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	private IDistrictConstituenciesDAO districtConstituenciesDAO;
    
	
	public IDistrictConstituenciesDAO getDistrictConstituenciesDAO() {
		return districtConstituenciesDAO;
	}

	public void setDistrictConstituenciesDAO(
			IDistrictConstituenciesDAO districtConstituenciesDAO) {
		this.districtConstituenciesDAO = districtConstituenciesDAO;
	}

	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	public ITdpCadreDataSourceTypeInfoDAO getTdpCadreDataSourceTypeInfoDAO() {
		return tdpCadreDataSourceTypeInfoDAO;
	}

	public void setTdpCadreDataSourceTypeInfoDAO(
			ITdpCadreDataSourceTypeInfoDAO tdpCadreDataSourceTypeInfoDAO) {
		this.tdpCadreDataSourceTypeInfoDAO = tdpCadreDataSourceTypeInfoDAO;
	}

	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public ITdpCadreLocationInfoDAO getTdpCadreLocationInfoDAO() {
		return tdpCadreLocationInfoDAO;
	}

	public void setTdpCadreLocationInfoDAO(
			ITdpCadreLocationInfoDAO tdpCadreLocationInfoDAO) {
		this.tdpCadreLocationInfoDAO = tdpCadreLocationInfoDAO;
	}

	public ICadreGhmcDriveUsersDAO getCadreGhmcDriveUsersDAO() {
		return cadreGhmcDriveUsersDAO;
	}

	public void setCadreGhmcDriveUsersDAO(
			ICadreGhmcDriveUsersDAO cadreGhmcDriveUsersDAO) {
		this.cadreGhmcDriveUsersDAO = cadreGhmcDriveUsersDAO;
	}

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}

	public void setCadreRegAmountDetailsDAO(
			ICadreRegAmountDetailsDAO cadreRegAmountDetailsDAO) {
		this.cadreRegAmountDetailsDAO = cadreRegAmountDetailsDAO;
	}

	public IRegistrationService getRegistrationService() {
		return registrationService;
	}

	public void setRegistrationService(IRegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	public IVoterInfoDAO getVoterInfoDAO() {
		return voterInfoDAO;
	}

	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}

	public ICadreSurveyUserAssigneeDAO getCadreSurveyUserAssigneeDAO() {
		return cadreSurveyUserAssigneeDAO;
	}

	public void setCadreSurveyUserAssigneeDAO(
			ICadreSurveyUserAssigneeDAO cadreSurveyUserAssigneeDAO) {
		this.cadreSurveyUserAssigneeDAO = cadreSurveyUserAssigneeDAO;
	}

	

	public void setCadreSurveyUserAssignDetailsDAO(
			ICadreSurveyUserAssignDetailsDAO cadreSurveyUserAssignDetailsDAO) {
		this.cadreSurveyUserAssignDetailsDAO = cadreSurveyUserAssignDetailsDAO;
	}

	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}

	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}

	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}
	
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public ICadreSurveyUserDAO getCadreSurveyUserDAO() {
		return cadreSurveyUserDAO;
	}

	public void setCadreSurveyUserDAO(ICadreSurveyUserDAO cadreSurveyUserDAO) {
		this.cadreSurveyUserDAO = cadreSurveyUserDAO;
	}

	public IAppDbUpdateDAO getAppDbUpdateDAO() {
		return appDbUpdateDAO;
	}

	public void setAppDbUpdateDAO(IAppDbUpdateDAO appDbUpdateDAO) {
		this.appDbUpdateDAO = appDbUpdateDAO;
	}

	public ITabLogInAuthDAO getTabLogInAuthDAO() {
		return tabLogInAuthDAO;
	}

	public void setTabLogInAuthDAO(ITabLogInAuthDAO tabLogInAuthDAO) {
		this.tabLogInAuthDAO = tabLogInAuthDAO;
	}
	
	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}
	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	public void setUserDistrictAccessInfoDAO(
			IUserDistrictAccessInfoDAO userDistrictAccessInfoDAO) {
		this.userDistrictAccessInfoDAO = userDistrictAccessInfoDAO;
	}

	public void setUserConstituencyAccessInfoDAO(
			IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO) {
		this.userConstituencyAccessInfoDAO = userConstituencyAccessInfoDAO;
	}
	public void setTabUserEnrollmentInfoDAO(
			ITabUserEnrollmentInfoDAO tabUserEnrollmentInfoDAO) {
		this.tabUserEnrollmentInfoDAO = tabUserEnrollmentInfoDAO;
	}

	public List<CadreRegisterInfo> getDashBoardBasicInfo(String accessType,Long accessValue){
		
		List<CadreRegisterInfo> returnResult = new ArrayList<CadreRegisterInfo>();
		List<Long> constituencyIds = new ArrayList<Long>();
		List<Long> districtIds = new ArrayList<Long>();
			if(accessType.equalsIgnoreCase("MLA"))
			{
				constituencyIds.add(accessValue);
				districtIds = constituencyDAO.getDistrictIdsByConstituency(constituencyIds);
			}
			else if(accessType.equalsIgnoreCase("MP"))
			{
				constituencyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliament(accessValue);
				districtIds = constituencyDAO.getDistrictIdsByConstituency(constituencyIds);
			}
			else if(accessType.equalsIgnoreCase("DISTRICT"))
			{
				districtIds.add(accessValue);
			}
		
		Date currentDate = dateService.getCurrentDateAndTime();
		CadreRegisterInfo todayInfo = getRegisterCount(currentDate,currentDate,constituencyIds,districtIds);
		
		Calendar fromCalendar = Calendar.getInstance();
		fromCalendar.setTime(currentDate);
		fromCalendar.set(Calendar.DAY_OF_MONTH,  fromCalendar.get(Calendar.DAY_OF_MONTH)-6);
		//fromCalendar.add(Calendar.DAY_OF_WEEK, fromCalendar.getFirstDayOfWeek() - fromCalendar.get(Calendar.DAY_OF_WEEK));
	   
		Calendar toCalendar = Calendar.getInstance();
		toCalendar.setTime(currentDate);
		//toCalendar.setTime(fromCalendar.getTime());
		//toCalendar.add(Calendar.DAY_OF_YEAR, 6);
		
		//CadreRegisterInfo thisWeekInfo = getRegisterCount(fromCalendar.getTime(),toCalendar.getTime(),constituencyIds,districtIds);
		
		fromCalendar.setTime(currentDate);
		fromCalendar.set(Calendar.DAY_OF_MONTH,  fromCalendar.get(Calendar.DAY_OF_MONTH)-29);
		//fromCalendar.set(Calendar.DAY_OF_MONTH, 1);
	    //toCalendar.set(Calendar.DAY_OF_MONTH,toCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		CadreRegisterInfo info = new CadreRegisterInfo();
		info.setApCount(0l);
		info.setApWebCount(0l);
		info.setApTabCount(0l);
		info.setApOnlineCount(0l);
		info.setTgCount(0l);
		info.setTgWebCount(0l);
		info.setTgTabCount(0l);
		info.setTgOnlineCount(0l);
		info.setTotalCount(0l);
		info.setApPartyWebCount(0l);
		info.setTgPartyWebCount(0l);
		info.setVzwPartyCount(0l);
	     
	     //CadreRegisterInfo thisMonthInfo = getRegisterCount(fromCalendar.getTime(),toCalendar.getTime(),constituencyIds,districtIds);
	     
	     CadreRegisterInfo totalCadreInfo = getRegisterCount(null,null,constituencyIds,districtIds);
	     
	     //CadreRegisterInfo newlyRegisterCadreInfo = getNewlyRegisterCount(constituencyIds,districtIds);
	     
	     returnResult.add(todayInfo);
	     returnResult.add(info);
	     returnResult.add(info);
	     returnResult.add(totalCadreInfo);
	     returnResult.add(info);
	     
	     return returnResult;
	}
	
	
	 public CadreRegisterInfo getRegisterCount(Date fromDate,Date toDate,List<Long> constituencyIds,List<Long> districtIds){
		CadreRegisterInfo info = new CadreRegisterInfo();
		Long apCount = 0l;
		Long tgCount = 0l;
		Long apWebCount = 0l;
		Long tgWebCount = 0l;
		Long apPartyWebCount = 0l;
		Long tgPartyWebCount = 0l;
		Long apTabCount = 0l;
		Long tgTabCount = 0l;
		Long apOnlineCount = 0l;
		Long tgOnlineCount = 0l;
		Long vzwPartyCount = 0l;
		List<Object[]> districtWiseCount = null;
		List<Object[]> districtWiseCount1 = null;
		try{
			districtWiseCount = tdpCadreDAO.getRegisterCadreInfoForUserBetweenDates(fromDate, toDate,constituencyIds,districtIds);
			districtWiseCount1 = tdpCadreDAO.getRegisterCadreInfoForUserBetweenDates1(fromDate, toDate,constituencyIds,districtIds);
			if(districtWiseCount != null && districtWiseCount.size() > 0)
		    for(Object[] districtCount:districtWiseCount){
			if(((Long)districtCount[1]).longValue() > 10l){
				apCount = apCount+(Long)districtCount[0];
				if(districtCount[2] != null && districtCount[2].toString().trim().equalsIgnoreCase("WEB"))
				apWebCount = apWebCount +  (Long)districtCount[0];
				else if(districtCount[2] != null && districtCount[2].toString().trim().equalsIgnoreCase("TAB"))
				apTabCount = apTabCount + 	 (Long)districtCount[0];
				else if(districtCount[2] != null && districtCount[2].toString().trim().equalsIgnoreCase("ONLINE"))
					apOnlineCount = apOnlineCount + 	 (Long)districtCount[0];	
				
			}else{
				tgCount = tgCount+(Long)districtCount[0];	
				if(districtCount[2] != null && districtCount[2].toString().trim().equalsIgnoreCase("WEB"))
					tgWebCount = tgWebCount +  (Long)districtCount[0];
					else if(districtCount[2] != null && districtCount[2].toString().trim().equalsIgnoreCase("TAB"))
					tgTabCount = tgTabCount + 	 (Long)districtCount[0];
					else if(districtCount[2] != null && districtCount[2].toString().trim().equalsIgnoreCase("ONLINE"))
						tgOnlineCount = tgOnlineCount + (Long)districtCount[0];	
			}
		  }
			if(districtWiseCount1 != null && districtWiseCount1.size() > 0)
			    for(Object[] districtCnt:districtWiseCount1){
				if(((Long)districtCnt[1]).longValue() > 10l){
					apCount = apCount+(Long)districtCnt[0];
					if(districtCnt[2] != null && districtCnt[2].toString().trim().equalsIgnoreCase("WEB") && (Long)districtCnt[3] == 3930)
						apPartyWebCount = apPartyWebCount +  (Long)districtCnt[0];
					else if(districtCnt[2] != null && districtCnt[2].toString().trim().equalsIgnoreCase("WEB") && (Long)districtCnt[3] == 7394)
						vzwPartyCount = vzwPartyCount +  (Long)districtCnt[0];
					/*else if(districtCnt[2] != null && districtCnt[2].toString().trim().equalsIgnoreCase("TAB"))
					apTabCount = apTabCount + 	 (Long)districtCnt[0];
					else if(districtCnt[2] != null && districtCnt[2].toString().trim().equalsIgnoreCase("ONLINE"))
						apOnlineCount = apOnlineCount + 	 (Long)districtCnt[0];	*/
					
				}else{
					tgCount = tgCount+(Long)districtCnt[0];	
					if(districtCnt[2] != null && districtCnt[2].toString().trim().equalsIgnoreCase("WEB") && (Long)districtCnt[3] == 3930)
						tgPartyWebCount = tgPartyWebCount +  (Long)districtCnt[0];
					else if(districtCnt[2] != null && districtCnt[2].toString().trim().equalsIgnoreCase("WEB") && (Long)districtCnt[3] == 7394)
						vzwPartyCount = vzwPartyCount +  (Long)districtCnt[0];
						/*else if(districtCnt[2] != null && districtCnt[2].toString().trim().equalsIgnoreCase("TAB"))
						tgTabCount = tgTabCount + 	 (Long)districtCnt[0];
						else if(districtCnt[2] != null && districtCnt[2].toString().trim().equalsIgnoreCase("ONLINE"))
							tgOnlineCount = tgOnlineCount + (Long)districtCnt[0];	*/
				}
			  }
		}catch(Exception e){
			LOG.error("Exception rised in getRegisterCount",e);
		}
		info.setApCount(apCount);
		info.setApWebCount(apWebCount);
		info.setApTabCount(apTabCount);
		info.setApOnlineCount(apOnlineCount);
		info.setTgCount(tgCount);
		info.setTgWebCount(tgWebCount);
		info.setTgTabCount(tgTabCount);
		info.setTgOnlineCount(tgOnlineCount);
		info.setTotalCount(apCount+tgCount);
		info.setApPartyWebCount(apPartyWebCount);
		info.setTgPartyWebCount(tgPartyWebCount);
		info.setVzwPartyCount(vzwPartyCount);
		return info;
	}
	 
	 public CadreRegisterInfo getNewlyRegisterCount(List<Long> constituencyIds,List<Long> districtIds){
			CadreRegisterInfo info = new CadreRegisterInfo();
			Long apCount = 0l;
			Long tgCount = 0l;
			
			try{
	          List<Object[]> districtWiseCount = tdpCadreDAO.getNewlyRegisterCadreInfo1(constituencyIds,districtIds);
			  for(Object[] districtCount:districtWiseCount){
				if(((Long)districtCount[1]).longValue() > 10l){
					apCount = apCount+(Long)districtCount[0];
				}else{
					tgCount = tgCount+(Long)districtCount[0];	
				}
			  }
			}catch(Exception e){
				LOG.error("Exception rised in getNewlyRegisterCount",e);
			}
			info.setApCount(apCount);
			info.setTgCount(tgCount);
			info.setTotalCount(apCount+tgCount);
			
			return info;
		}
	 
	public  List<CadreRegisterInfo> getRecentlyRegisteredCadresInfo(){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		try{
		   CadreRegisterInfo info = null;
		 //0 first name ,1 lastname,2 constituency ,3 localArea, 4 image
		   List<Object[]> cadreDetails = tdpCadreDAO.getRecentlyRegisteredCadres();
		   for(Object[] cadre:cadreDetails){
			   StringBuilder name = new StringBuilder("");
			   if(cadre[0] != null){
				   name.append(cadre[0].toString());
			   }
			   if(cadre[1] != null){
				   name.append(" "+cadre[1].toString());
			   }
				info = new CadreRegisterInfo();
				info.setName(name.toString());
				if(cadre[3] != null){
				    info.setArea(cadre[3].toString());
				}else{
					info.setArea("");
				}
				info.setLocation(cadre[2].toString());
				if(cadre[4] != null){
				info.setDate("images/cadre_images/"+cadre[4].toString());
				}
				returnList.add(info);
			}
		}catch(Exception e){
        	LOG.error("Exception rised in getRecentlyRegisteredCadresInfo",e);
		}
		return returnList;
	}
	
	
	public  List<CadreRegisterInfo> getRecentlyRegisteredCadresInfo(Integer startIndex,Integer maxIndex,String accessType,Long accessValue){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		try{
		   CadreRegisterInfo info = null;
		   List<Long> constiIds = new ArrayList<Long>();
		   List<Object[]> cadreDetails = null;
		   if(accessType.equalsIgnoreCase("STATE")){
		   cadreDetails = tdpCadreDAO.getRecentlyRegisteredCadres(startIndex,maxIndex);
		   }
		   else if(accessType.equalsIgnoreCase("MLA")){
		   constiIds.add(accessValue);
		   cadreDetails = tdpCadreDAO.getRecentlyRegisteredCadresByConstituencies(startIndex,maxIndex,constiIds);
		   }
		   else if(accessType.equalsIgnoreCase("MP")){
		   constiIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliament(accessValue);
		   cadreDetails = tdpCadreDAO.getRecentlyRegisteredCadresByConstituencies(startIndex,maxIndex,constiIds);
		   }

		   else if(accessType.equalsIgnoreCase("DISTRICT")){
		   constiIds = constituencyDAO.getConstituenciesInADistrict(accessValue);
		   cadreDetails = tdpCadreDAO.getRecentlyRegisteredCadresByConstituencies(startIndex,maxIndex,constiIds);
		   }
		   if(cadreDetails != null && cadreDetails.size() >0){
		   
		   //cadreDetails = tdpCadreDAO.getRecentlyRegisteredCadres(startIndex,maxIndex);
		   for(Object[] cadre:cadreDetails){
			   StringBuilder name = new StringBuilder("");
			   if(cadre[0] != null){
				   name.append(cadre[0].toString());
			   }
			   if(cadre[1] != null){
				   name.append(" "+cadre[1].toString());
			   }
				info = new CadreRegisterInfo();
				info.setName(name.toString());
				if(cadre[3] != null){
				    info.setArea(cadre[3].toString());
				}else{
					info.setArea("");
				}
				info.setLocation(cadre[2].toString());
				if(cadre[4] != null){
				info.setDate("images/cadre_images/"+cadre[4].toString());
				}
				returnList.add(info);
		   }
		   }
		}catch(Exception e){
        	LOG.error("Exception rised in getRecentlyRegisteredCadresInfo",e);
		}
		return returnList;
	}
	public List<CadreRegisterInfo> getConstituencyWiseRegistrationInfo(){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		CadreRegisterInfo info = null;
		try{
		    List<Object[]> constituencyWiseCount = tdpCadreDAO.getRegisterCadreInfoConstituencyWise();
			for(Object[] constituency:constituencyWiseCount){
				info = new CadreRegisterInfo();
				info.setName(constituency[1].toString());
				info.setTotalCount((Long)constituency[0]);
				returnList.add(info);
			}
        }catch(Exception e){
        	LOG.error("Exception rised in getConstituencyWiseRegistrationInfo",e);
		}
		return returnList;
	}
	
	public List<CadreRegisterInfo> getDistrictWiseRegistrationInfo(){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		try{
			CadreRegisterInfo info = null;
			List<Object[]> districtWiseCount = tdpCadreDAO.getRegisterCadreInfoDistrictWise();
			
			for(Object[] district:districtWiseCount){
				info = new CadreRegisterInfo();
				info.setName(district[1].toString());
				info.setTotalCount((Long)district[0]);
				returnList.add(info);
			}
		}catch(Exception e){
			LOG.error("Exception rised in getDistrictWiseRegistrationInfo",e);
		}
		return returnList;
	}

	public List<CadreRegisterInfo> getWorkStartedConstituencyCount(String accessType,Long accessValue){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		 Long tsCount = 0l;
		 Long count_2012TS =0l;
		 Long count_2014TS =0l;
		 Long apCount = 0l;
		 Long count_2012AP = 0l;
		 Long count_2014AP = 0l;
		try{
				List<Long> constituencyIds = new ArrayList<Long>();
				
				if(accessType.equalsIgnoreCase("MLA"))
				{
					constituencyIds.add(accessValue);
				}
				else if(accessType.equalsIgnoreCase("MP"))
				{
					constituencyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliament(accessValue);
					
				}
				else if(accessType.equalsIgnoreCase("DISTRICT"))
				{
					constituencyIds = constituencyDAO.getConstituenciesInADistrict(accessValue);

				}
			 //tsCount = tdpCadreDAO.getWorkStartedConstituencyCount1("TS",constituencyIds);
			 tsCount = 119l;
			 //count_2012TS = tdpCadreDAO.getWorkStartedConstituencyYearCount1(2012L,"TS",null,null,constituencyIds);
			 count_2012TS = 482566l;
			 count_2014TS = tdpCadreDAO.getWorkStartedConstituencyYearCount1(2014L,"TS",null,null,constituencyIds);
			 
			 //apCount = tdpCadreDAO.getWorkStartedConstituencyCount1("AP",constituencyIds);
			 apCount = 175l;
			 
			 //count_2012AP = tdpCadreDAO.getWorkStartedConstituencyYearCount1(2012L,"AP",null,null,constituencyIds);
			 count_2012AP = 914359l;
			 count_2014AP = tdpCadreDAO.getWorkStartedConstituencyYearCount1(2014L,"AP",null,null,constituencyIds);

			 CadreRegisterInfo apVo = new CadreRegisterInfo();
			 CadreRegisterInfo tsVo = new CadreRegisterInfo();
			 
			 apVo.setTotalCount(apCount != null ? apCount : 0l);
			 tsVo.setTotalCount(tsCount != null ? tsCount : 0l);
			 
			 apVo.setApCount(count_2012AP != null ? count_2012AP : 0l);
			 tsVo.setApCount(count_2012TS != null ? count_2012TS : 0l);
			 
			 apVo.setTgCount(count_2014AP != null ? count_2014AP : 0l);
			 tsVo.setTgCount(count_2014TS != null ? count_2014TS : 0l);
			 
			 apVo.setPercentage(0l);
			 tsVo.setPercentage(0l);
			 
			 if(apVo.getApCount().longValue() > 0l && apVo.getTgCount().longValue() > 0l){
				 apVo.setPercentage((apVo.getTgCount().longValue()*100)/apVo.getApCount().longValue());
			 }
			 
             if(tsVo.getApCount().longValue() > 0l && tsVo.getTgCount().longValue() > 0l){
            	 tsVo.setPercentage((tsVo.getTgCount().longValue()*100)/tsVo.getApCount().longValue());
			 }
			 
			 returnList.add(apVo);
			 returnList.add(tsVo);
			
		}catch(Exception e){
			LOG.error("Exception rised in getWorkStartedConstituencyCount",e);
		}
		return returnList;
	}
			
	public List<CadreRegisterInfo> getAssemblyWiseCompletedPercentage(Long assemblyId,Long stateId, String accessType,String accessValue,String percType){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		
		try{
			List<Long> assemblyIds = new ArrayList<Long>();
			CadreRegisterInfo infoVo = null;
			if(assemblyId == null || assemblyId.longValue() == 0){
				if(accessType.equalsIgnoreCase("STATE")){
					assemblyIds = tdpCadreDAO.getCadreAvailableConstituencies(stateId);
				}
				if(accessType.equalsIgnoreCase("MLA")){
					assemblyIds.add(Long.valueOf(accessValue));
				}
				if(accessType.equalsIgnoreCase("MP")){
					List<Long> parlis = new ArrayList<Long>();
					parlis.add(Long.valueOf(accessValue));
					List<Object[]> list = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesListForAListOfParliamentConstituency(parlis);
					if(list!=null && list.size()>0){
						for(Object[] obj:list){
							assemblyIds.add(Long.valueOf(obj[0].toString()));
						}
					}
				}
				if(accessType.equalsIgnoreCase("DISTRICT")){
					List<Long> dists = new ArrayList<Long>();
					dists.add(Long.valueOf(accessValue));
					List<Long> list = constituencyDAO.getAllConstituencysByDistrictIds(dists, "Assembly");
					assemblyIds = list;
					
				}
				
			}else{
				assemblyIds = new ArrayList<Long>();
				assemblyIds.add(assemblyId);
			}
			
			int ap_perc = 0;
			int tg_perc = 0;
			Float ap_percentage=0f;
			Float tg_percentage=0f;
			if(percType.equalsIgnoreCase("target")){
				ap_perc = IConstants.TARGET_CADRE_AP*100/IConstants.AP_VOTERS_2014;
				tg_perc = IConstants.TARGET_CADRE_TG*100/IConstants.TG_VOTERS_2014;
				
				ap_percentage=(float)IConstants.TARGET_CADRE_AP/IConstants.AP_VOTERS_2014;
				tg_percentage=(float)IConstants.TARGET_CADRE_TG*100/IConstants.TG_VOTERS_2014;
			}
			
			
			
			Map<Long,Map<Long,Long>> locationMap = new HashMap<Long,Map<Long,Long>>();//Map<locationId,Map<year,count>>
			Map<Long,String> locationNames = new HashMap<Long,String>();
			Map<Long,Long> yearMap = null;
			
			if(percType.equalsIgnoreCase("target")){
				if(assemblyIds.size() > 0){
					List<Object[]>  constituencyInfoList = tdpCadreDAO.getCadreInfoConstituencytWise(assemblyIds,null,null,2014l);
					List<Object[]> vtrsCounts = voterInfoDAO.getVotersCountForAllConstituencies(IConstants.VOTER_DATA_PUBLICATION_ID, assemblyIds);
					
					Map<Long,Long> vtrsMap = new HashMap<Long, Long>();
					
					if(vtrsCounts!=null && vtrsCounts.size()>0){
						for(Object[] obj:vtrsCounts){
							Long vtrs = vtrsMap.get(obj[0]);
							if(vtrs==null){
								vtrsMap.put(Long.valueOf(obj[0].toString()), Long.valueOf(obj[1].toString()));
							}
						}
					}
					
					if(constituencyInfoList!=null && constituencyInfoList.size()>0){
						for(Object[] obj:constituencyInfoList){
							infoVo = new CadreRegisterInfo();
							infoVo.setLocation(obj[2].toString());
							infoVo.setTotalCount(Long.valueOf(obj[0].toString()));
							Long targetCount = 0l;
							Long votersCount = 0l;
							if(stateId.equals(1l)){
								votersCount = vtrsMap.get(obj[1]!=null ? Long.valueOf(obj[1].toString()) : 0l);
								if(votersCount==null){
									votersCount = 0l;
								}
								
								Float ap_temp=((ap_percentage)*100)*votersCount/100;
								targetCount =ap_temp.longValue() ;
								//targetCount = ap_perc*votersCount/100;
							}else{
								votersCount = vtrsMap.get(obj[1]!=null ? Long.valueOf(obj[1].toString()) : 0l);
								if(votersCount==null){
									votersCount = 0l;
								}
								
								
								Float tg_temp=((tg_percentage)*100)*votersCount/100;
								targetCount = tg_temp.longValue();
								//targetCount = tg_perc*votersCount/100;
							}
							infoVo.setVotersCount(votersCount);
							infoVo.setTargetCount(targetCount);
							if(targetCount!=0){
								infoVo.setApCount((Long.valueOf(obj[0].toString())*100)/targetCount);
								infoVo.setDate(new BigDecimal(Long.valueOf(obj[0].toString())*(100.0)/targetCount).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
							}else{
								infoVo.setApCount(0l);
								infoVo.setDate(" - ");
							}
							
							
							infoVo.setTgCount(Long.valueOf(obj[1].toString()));
							returnList.add(infoVo);
						}
					}
				}
			}else{
				if(assemblyIds.size() > 0){
					//0 count,1 id,2 name ,3 year
					List<Object[]>  constituencyInfoList = tdpCadreDAO.getCadreInfoConstituencytWise(assemblyIds,null,null,2014l);
					constituencyInfoList.addAll( tdpCadreDAO.getCadreInfoConstituencytWise(assemblyIds,null,null,2012l));
					for(Object[] info:constituencyInfoList){
						 yearMap = locationMap.get((Long)info[1]);
						 if(yearMap == null){
							 yearMap = new HashMap<Long,Long>();
							 locationMap.put((Long)info[1],yearMap);
							 locationNames.put((Long)info[1],info[2].toString());
						 }
						 yearMap.put((Long)info[3], (Long)info[0]);
					}
				}
				for(Long locationId:locationMap.keySet()){
					yearMap = locationMap.get(locationId);
					if(yearMap.size() >= 2){
						Long previousCount = yearMap.get(2012l);
						Long currentCount = yearMap.get(2014l);
						if(previousCount != null && previousCount.longValue() > 0 && currentCount != null && currentCount.longValue() > 0){
							infoVo = new CadreRegisterInfo();
							infoVo.setLocation(locationNames.get(locationId));
							infoVo.setTotalCount(currentCount);
							infoVo.setApCount((currentCount*100)/previousCount);
							infoVo.setDate(new BigDecimal(currentCount*(100.0)/previousCount).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
							infoVo.setTgCount(locationId);
							returnList.add(infoVo);
						}
					}
				}
			}
		}catch(Exception e){
			LOG.error("Exception rised in getAssemblyWiseCompletedPercentage",e);
		}
		
		return returnList;
	}
	
	public List<CadreRegisterInfo> getDistrictWiseCompletedPercentage(Long districtId,Long stateId, String accessType, String accessValue, String percType){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		try{
			List<Long> districtIds = null;
			CadreRegisterInfo infoVo = null;
			List<Long> assemblyIds = null;
			if(districtId == null || districtId.longValue() == 0){
				assemblyIds = new ArrayList<Long>();
				districtIds = new ArrayList<Long>();
				
				if(accessType.equalsIgnoreCase("STATE")){
					districtIds = tdpCadreDAO.getCadreAvailableDistricts(stateId);
				}
				if(accessType.equalsIgnoreCase("MLA")){
					List<Long> consti = new ArrayList<Long>();
					consti.add(Long.valueOf(accessValue));
					assemblyIds.add(Long.valueOf(accessValue));
					districtIds = constituencyDAO.getDistrictIdByConstituencyIds(consti);
				}
				if(accessType.equalsIgnoreCase("MP")){
					List<Long> parlis = new ArrayList<Long>();
					parlis.add(Long.valueOf(accessValue));
					List<Long> list = delimitationConstituencyAssemblyDetailsDAO.findDistrictsOfParliamentConstituencies(parlis);
					
					List<Object[]> asslyList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesListForAListOfParliamentConstituency(parlis);
					if(asslyList!=null && asslyList.size()>0){
						for(Object[] obj:asslyList){
							assemblyIds.add(Long.valueOf(obj[0].toString()));
						}
					}
					
					districtIds = list;
				}
				if(accessType.equalsIgnoreCase("DISTRICT")){
					districtIds.add(Long.valueOf(accessValue));
					
				}
				
			}else{
				assemblyIds = new ArrayList<Long>();
				if(accessType.equalsIgnoreCase("MLA")){
					List<Long> consti = new ArrayList<Long>();
					consti.add(Long.valueOf(accessValue));
					assemblyIds.add(Long.valueOf(accessValue));
				}
				if(accessType.equalsIgnoreCase("MP")){
					List<Long> parlis = new ArrayList<Long>();
					parlis.add(Long.valueOf(accessValue));
					List<Object[]> asslyList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesListForAListOfParliamentConstituency(parlis);
					if(asslyList!=null && asslyList.size()>0){
						for(Object[] obj:asslyList){
							assemblyIds.add(Long.valueOf(obj[0].toString()));
						}
					}
				}
				
				districtIds = new ArrayList<Long>();
				districtIds.add(districtId);
			}
			
			int ap_perc = 0;
			int tg_perc = 0;
			Float ap_percentage=0f;
			Float tg_percentage=0f;
			if(percType.equalsIgnoreCase("target")){
				ap_perc = IConstants.TARGET_CADRE_AP*100/IConstants.AP_VOTERS_2014;
				tg_perc = IConstants.TARGET_CADRE_TG*100/IConstants.TG_VOTERS_2014;
				
				ap_percentage=(float)IConstants.TARGET_CADRE_AP*100/IConstants.AP_VOTERS_2014;
				tg_percentage=(float)IConstants.TARGET_CADRE_TG*100/IConstants.TG_VOTERS_2014;
			}
			
			Map<Long,Map<Long,Long>> locationMap = new HashMap<Long,Map<Long,Long>>();//Map<locationId,Map<year,count>>
			Map<Long,String> locationNames = new HashMap<Long,String>();
			Map<Long,Long> yearMap = null;
			
			if(percType.equalsIgnoreCase("target")){
				if(districtIds.size() > 0){
					List<Object[]>  constituencyInfoList = new ArrayList<Object[]>();
					
					if(accessType.equalsIgnoreCase("STATE") || accessType.equalsIgnoreCase("DISTRICT")){
						constituencyInfoList = tdpCadreDAO.getCadreInfoDistrictWise(districtIds,null,null,2014l);
						//constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoDistrictWise(districtIds,null,null,2012l));
					}
					if(accessType.equalsIgnoreCase("MLA") || accessType.equalsIgnoreCase("MP")){
						constituencyInfoList = tdpCadreDAO.getCadreInfoDistrictConstiWise(districtIds,null,null,2014l,assemblyIds);
						//constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoDistrictConstiWise(districtIds,null,null,2012l,assemblyIds));
					}
					
					List<Object[]> vtrsCounts = voterInfoDAO.getVotersCountInADistrictsList(districtIds, IConstants.VOTER_DATA_PUBLICATION_ID);
					
					Map<Long,Long> vtrsMap = new HashMap<Long, Long>();
					
					if(vtrsCounts!=null && vtrsCounts.size()>0){
						for(Object[] obj:vtrsCounts){
							Long vtrs = vtrsMap.get(obj[0]);
							if(vtrs==null){
								vtrsMap.put(Long.valueOf(obj[0].toString()), Long.valueOf(obj[2].toString()));
							}
						}
					}
					
					if(constituencyInfoList!=null && constituencyInfoList.size()>0){
						for(Object[] obj:constituencyInfoList){
							infoVo = new CadreRegisterInfo();
							infoVo.setLocation(obj[2].toString());
							infoVo.setTotalCount(Long.valueOf(obj[0].toString()));
							Long targetCount = 0l;
							Long votersCount = 0l;
							if(stateId.equals(1l)){
								votersCount = vtrsMap.get(obj[1] != null ? Long.valueOf(obj[1].toString()) : 0l);
								if(votersCount==null){
									votersCount = 0l;
								}
								
								Float ap_temp = ap_percentage*votersCount/100;
								targetCount =ap_temp.longValue() ;
								//targetCount = ap_percentage*votersCount/100;
							}else{
								votersCount = vtrsMap.get(obj[1] != null ? Long.valueOf(obj[1].toString()) : 0l);
								if(votersCount==null){
									votersCount = 0l;
								}
								
								Float tg_temp=tg_percentage*votersCount/100;
								targetCount = tg_temp.longValue();
								//targetCount = tg_perc*votersCount/100;
							}
							infoVo.setVotersCount(votersCount);
							infoVo.setTargetCount(targetCount);
							
							if(targetCount!=0){
								infoVo.setApCount((Long.valueOf(obj[0].toString())*100)/targetCount);
								infoVo.setDate(new BigDecimal(Long.valueOf(obj[0].toString())*(100.0)/targetCount).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
							}else{
								infoVo.setApCount(0l);
								infoVo.setDate(" - ");
							}
							infoVo.setTgCount(Long.valueOf(obj[1].toString()));
							returnList.add(infoVo);
						}
					}
				}
			}
			else{
				if(districtIds.size() > 0){
					//0 count,1 id,2 name ,3 year
					List<Object[]>  constituencyInfoList = new ArrayList<Object[]>();
					
					if(accessType.equalsIgnoreCase("STATE") || accessType.equalsIgnoreCase("DISTRICT")){
						constituencyInfoList = tdpCadreDAO.getCadreInfoDistrictWise(districtIds,null,null,2014l);
						constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoDistrictWise(districtIds,null,null,2012l));
					}
					if(accessType.equalsIgnoreCase("MLA") || accessType.equalsIgnoreCase("MP")){
						constituencyInfoList = tdpCadreDAO.getCadreInfoDistrictConstiWise(districtIds,null,null,2014l,assemblyIds);
						constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoDistrictConstiWise(districtIds,null,null,2012l,assemblyIds));
					}
					
					
					for(Object[] info:constituencyInfoList){
						 yearMap = locationMap.get((Long)info[1]);
						 if(yearMap == null){
							 yearMap = new HashMap<Long,Long>();
							 locationMap.put((Long)info[1],yearMap);
							 locationNames.put((Long)info[1],info[2].toString());
						 }
						 yearMap.put((Long)info[3], (Long)info[0]);
					}
				}
				for(Long locationId:locationMap.keySet()){
					yearMap = locationMap.get(locationId);
					if(yearMap.size() >= 2){
						Long previousCount = yearMap.get(2012l);
						Long currentCount = yearMap.get(2014l);
						if(previousCount != null && previousCount.longValue() > 0 && currentCount != null && currentCount.longValue() > 0){
							infoVo = new CadreRegisterInfo();
							infoVo.setLocation(locationNames.get(locationId));
							infoVo.setTotalCount(currentCount);
							infoVo.setApCount((currentCount*100)/previousCount);
							infoVo.setDate(new BigDecimal(currentCount*(100.0)/previousCount).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
							infoVo.setTgCount(locationId);
							returnList.add(infoVo);
						}
					}
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception rised in getDistrictWiseCompletedPercentage",e);
		}
		
		return returnList;
	}

	/*public CadreRegisterInfo getWorkingMembersInfo(){
		CadreRegisterInfo info = new CadreRegisterInfo();
		try{
			Date date = dateService.getCurrentDateAndTime();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			
			cal.add(Calendar.HOUR, -1);
			Date oneHourBack = cal.getTime();
			
			cal.add(Calendar.HOUR, -2);
			Date towHourBack = cal.getTime();
			
			Long count = tdpCadreDAO.getWorkingMembersCount(date);
			info.setTotalCount(count);
			
			Long count1 = tdpCadreDAO.getLastHoursWorkingMemberCount(date, oneHourBack); // field members in the last 1 hr			
			info.setApCount(count1);
			
			Long count2 = tdpCadreDAO.getLastHoursWorkingMemberCount(date, towHourBack); // field members in the last 2 hrs
			info.setTgCount(count2);
			
		}catch(Exception e){
			LOG.error("Exception rised in getWorkingMembersInfo",e);
		}
		
		return info;
	}*/
	
	public CadreRegisterInfo getWorkingMembersInfo(String hours,String accessType,Long accessValue)
	{
		CadreRegisterInfo info = new CadreRegisterInfo();
		try{
			Date date = dateService.getCurrentDateAndTime();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			
			Date hourBack = null;
			Long count  = 0L;
			Long webCount  = 0L;
			List<Long> constiIds = new ArrayList<Long>();
			
			if(accessType.equalsIgnoreCase("MLA")){
			   constiIds.add(accessValue);
			}
			else if(accessType.equalsIgnoreCase("MP")){
			   constiIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliament(accessValue);
			}
			 else if(accessType.equalsIgnoreCase("DISTRICT")){
			   constiIds = constituencyDAO.getConstituenciesInADistrict(accessValue);
			 }
			 else if(accessType.equalsIgnoreCase("STATE")){
				 //constiIds = tdpCadreDAO.getCadreAvailableConstituencies(0L); // AP & TS constituencies list
				 constiIds = constituencyDAO.getConstituencyIdsByStateIdForAElectionType(1l,2l);
				}
			if(hours != null && hours.trim().length()>0 && !hours.equalsIgnoreCase("0"))
			{
				int hourCount = Integer.valueOf(hours);						
				cal.add(Calendar.HOUR, -hourCount);
				hourBack = cal.getTime();
				
				if(!accessType.equalsIgnoreCase("STATE")){
					count = tdpCadreDAO.getLastHoursWorkingMemberCountOfAccessLevel(date, hourBack, constiIds);
					webCount = tdpCadreDAO.getLastHoursWorkingMemberCountOfAccessLevelForWeb(date, hourBack, constiIds);
				}else{
					count = tdpCadreDAO.getLastHoursWorkingMemberCount(date,hourBack);
					webCount = tdpCadreDAO.getLastHoursWorkingMemberCountForWeb(date,hourBack);
				}
				
			}
			else
			{
				if(!accessType.equalsIgnoreCase("STATE")){
					count = tdpCadreDAO.getWorkingMembersCountOfAccessLevel(date, constiIds);
					webCount = tdpCadreDAO.getWorkingMembersCountOfAccessLevelForWeb(date, constiIds);
				}else{
					count = tdpCadreDAO.getWorkingMembersCount(date);
					webCount = tdpCadreDAO.getWorkingMembersForWebCount(date);
				}
			}
			
			info.setTotalCount(count);
			info.setApCount(webCount);
			//For Inactive users count.
			Long inActiveUsersCount=(Long)getInActiveUsers(hours,"count",constiIds);
			info.setVotersCount(inActiveUsersCount);
			
		
		}catch(Exception e){
			LOG.error("Exception rised in getWorkingMembersInfo",e);
		}
		
		return info;
	}
	
	public List<CadreRegisterInfo> getCandidateDataCollectionInfo(Long locationType,List<Long> locationIds,Date fromDate,Date toDate){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		CadreRegisterInfo vo = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat timeFormate = new SimpleDateFormat("HH:mm");
		try{
			List<Long> surveyUserIds = new ArrayList<Long>();
			List<Date> datesList = new ArrayList<Date>();
			Map<Long,String> nameMap = new HashMap<Long, String>();
			Map<Long,String> tabMap = new HashMap<Long, String>();
			Map<Long,Map<Date,CadreRegisterInfo>> userMap = new HashMap<Long,Map<Date,CadreRegisterInfo>>();//Map<userId,Map<Date,info>>
			Map<Date,CadreRegisterInfo> dateMap = new HashMap<Date,CadreRegisterInfo>();//Map<Date,info>
			Map<Long,String> userNames = new HashMap<Long,String>();
			Map<Long,CadreRegisterInfo> mobileNos = new HashMap<Long,CadreRegisterInfo>();
			//0 count,1 name,2 min,3 max,4 date,5 id,6 name
			String parlimentName = null;
			
			Map<String,String> parliamentForAssemblyMap = new TreeMap<String, String>();
			List<Object[]> locationsList =  delimitationConstituencyAssemblyDetailsDAO.getPcListByRegion(0L);							
				
			List<SurveyTransactionVO> locationIdsList = new ArrayList<SurveyTransactionVO>();
			if(locationsList != null && locationsList.size()>0)
			{
				for (Object[] param : locationsList)
				{
					SurveyTransactionVO surveyTransactionVO = new SurveyTransactionVO();
					surveyTransactionVO.setId(param[0] != null ? Long.valueOf(param[0].toString()) :0L);
					surveyTransactionVO.setName(param[1] != null ? param[1].toString() :"");
					
					locationIdsList.add(surveyTransactionVO);
				}
			}
			List<Long> assemblyIds = new ArrayList<Long>(0);
			
			if(locationIdsList != null && locationIdsList.size()>0)
			{
				for (SurveyTransactionVO surveyTransctionVO : locationIdsList) 
				{
					List<Long> locationIdList = new ArrayList<Long>();
					locationIdList.add(surveyTransctionVO.getId());
					
					locationsList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesListForAListOfParliamentConstituency(locationIdList);
											
					if(locationsList != null && locationsList.size()>0)
					{
						for (Object[] param : locationsList)
						{
							assemblyIds.add(param[0] != null ? Long.valueOf(param[0].toString().trim()):0L );
							parliamentForAssemblyMap.put(param[1] != null ? param[1].toString().trim() :"", surveyTransctionVO.getName());
						}
					}	
					
				}
			}
			
			List<Object[]> dataCollectedInfo = null;
			List<Long> assemblyIdsList = new ArrayList<Long>(0);
			 if(locationType.longValue() == 4L )
			 {
				 Long parliamentID = locationIds.get(0) != null? locationIds.get(0):0L;
				 try {
						 if(parliamentID.longValue() >0 && locationIds.size() == 1 )
						 {
							 List<DelimitationConstituency> delimitationConstituencyList = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyID(parliamentID);
								DelimitationConstituency parliamentConstituency = delimitationConstituencyList.get(0);
								parlimentName = parliamentConstituency.getConstituency().getName();
								assemblyIdsList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliament(parliamentID);
							
								
						 }
						 else
						 {
							// assemblyIdsList.addAll(assemblyIds);
							 if(locationIds != null && locationIds.size()>0)
							 {
								 for (Long parliamentId : locationIds) 
								 {
										assemblyIdsList.addAll(delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliament(parliamentId));
								 }
							 }
						 }
						 
					} catch (Exception e) {}
				 
				 locationIds.clear();
				 locationIds.addAll(assemblyIdsList);
					
				 dataCollectedInfo = tdpCadreDAO.getCandidateDataCollectionInfo(3L,assemblyIdsList,fromDate, toDate);
			 }
			 else
			 {
				 dataCollectedInfo = tdpCadreDAO.getCandidateDataCollectionInfo(locationType,locationIds,fromDate, toDate);
			 }
			 
			for(Object[] data:dataCollectedInfo){
				if(!datesList.contains((Date)data[4])){
					datesList.add((Date)data[4]);
				}
				userNames.put((Long)data[5], data[1].toString());
				dateMap = userMap.get((Long)data[5]);
				if(dateMap == null){
					dateMap = new HashMap<Date,CadreRegisterInfo>();
					userMap.put((Long)data[5],dateMap);
					nameMap.put((Long)data[5], data[6] != null ? data[6].toString() : "");
					if(!surveyUserIds.contains((Long)data[5]))
					surveyUserIds.add((Long)data[5]);
				}
				vo = new CadreRegisterInfo() ;
				vo.setArea(convertTimeTo12HrsFormat(timeFormate.format((Date)data[2])));
				vo.setLocation(convertTimeTo12HrsFormat(timeFormate.format((Date)data[3])));
				vo.setTotalCount((Long)data[0]);
				vo.setAmount(vo.getTotalCount() * 100);
				
				dateMap.put((Date)data[4], vo);
			}
			if(surveyUserIds != null && surveyUserIds.size() > 0)
			{
			List<Object[]> tabNos = cadreSurveyUserAssignDetailsDAO.getTabNos(surveyUserIds);
			if(tabNos != null && tabNos.size() > 0)
				for(Object[] params : tabNos)
				{
					tabMap.put((Long)params[0], params[1] != null ?  params[1].toString() : "");
				}
				
			}
			
			if(fromDate.equals(toDate))
			{
				int count = 0;
				if(userNames.size() > 0){
					//0 userId,1mobile,2constituencyId,3constiname,4distiictId,5districtName
					List<Object[]> mobileNosList = cadreSurveyUserDAO.getAllUserMobileNos(locationType,locationIds);
					for(Object[] mobileNo:mobileNosList){
						CadreRegisterInfo userData = new CadreRegisterInfo();
					 if(mobileNo[1] != null){
						userData.setArea(mobileNo[1].toString());//mobileNo
					 }
					 if(((Long)mobileNo[4]).longValue() < 11){
					     userData.setLocation("TG");//state
					 }else{
						 userData.setLocation("AP");//state
					 }
					 userData.setNumber(mobileNo[5].toString());//district
					 userData.setMemberShipNo(mobileNo[3].toString());//constituency
					 userData.setPercentStr(parliamentForAssemblyMap.get(mobileNo[3].toString()));//parliament
					 userData.setUname(mobileNo[6] != null ?  mobileNo[6].toString() :"");
					 userData.setName(mobileNo[7] != null ? mobileNo[7].toString():"");
					 userData.setTabNo(mobileNo[8] != null ?  mobileNo[8].toString() :"");
					 //
					 if(locationType.longValue() == 2L && locationIds.contains(Long.valueOf(mobileNo[4].toString())))
					 {
						 mobileNos.put((Long)mobileNo[0], userData);
					 }
					 else if(locationType.longValue() == 3L && locationIds.contains(Long.valueOf(mobileNo[2].toString())))
					 {
						 mobileNos.put((Long)mobileNo[0], userData);
					 }
					 else if(locationType.longValue() == 4L )
					 {
						 mobileNos.put((Long)mobileNo[0], userData);
					 }
					 else if(locationType.longValue() == 1L || locationType.longValue() == 0L)
					 {
						 mobileNos.put((Long)mobileNo[0], userData);
					 }
					 
					}
				}
				
				for(Long key:mobileNos.keySet()){
					dateMap = userMap.get(key);
					vo = new CadreRegisterInfo();
					vo.setName(userNames.get(key));
					if(vo.getName() != null)
					{
							//vo.setUname(unameMap.get(key) != null ? unameMap.get(key).toString() : "");
							CadreRegisterInfo userData = mobileNos.get(key);
							if(userData != null){
							    vo.setArea(userData.getArea());//mobileNo
							    vo.setLocation(userData.getLocation());//state
							    vo.setNumber(userData.getNumber());//district
							    vo.setMemberShipNo(userData.getMemberShipNo());//constituency
							    vo.setPercentStr(userData.getPercentStr());
							    vo.setUname(nameMap.get(key));
							    vo.setTabNo(tabMap.get(key));
							}
							List<CadreRegisterInfo> daysList = new ArrayList<CadreRegisterInfo>();
							for(Date date:datesList){
								if(dateMap.get(date) != null){
									CadreRegisterInfo day = dateMap.get(date);
								    if(count == 0){
									  day.setDate(sdf.format(date));
								    }
									daysList.add(day);
								}else{
									CadreRegisterInfo day = new CadreRegisterInfo();
									if(count == 0){
									   day.setDate(sdf.format(date));
									 }
									daysList.add(day);
								}
							}
							vo.setInfoList(daysList);
							if(vo.getInfoList() != null && vo.getInfoList().size() > 0)
							{
								Long count1 = 0l;
								for(CadreRegisterInfo vo3 : vo.getInfoList())
								{
									if(vo3.getTotalCount() != null)
									 count1 = count1 + vo3.getTotalCount();
								}
								vo.setTotalCount(count1);
								vo.setTotalAmount(vo.getTotalCount() > 0 ?  vo.getTotalCount()* 100 : 0);
							}
							returnList.add(0,vo);
							count++;
					}
					else
					{
						CadreRegisterInfo userData = mobileNos.get(key);
						
						if(userData != null)
						{
						    vo.setArea(userData.getArea());//mobileNo
						    vo.setLocation(userData.getLocation());//state
						    vo.setNumber(userData.getNumber());//district
						    vo.setMemberShipNo(userData.getMemberShipNo());//constituency
						    vo.setPercentStr(userData.getPercentStr());//parliament
						    vo.setUname(userData.getName());
						    vo.setName(userData.getUname());
						    vo.setTabNo(userData.getTabNo());
						}
						List<CadreRegisterInfo> daysList = new ArrayList<CadreRegisterInfo>();
						CadreRegisterInfo day = new CadreRegisterInfo();
						day.setDate(sdf.format(fromDate));
						daysList.add(day);							
						vo.setInfoList(daysList);
						vo.setTotalCount(0L);
						vo.setTotalAmount(0L);					
						returnList.add(vo);
					}
				}
					
			}
			else
			{
				int count = 0;
				if(userNames.size() > 0){
					//0 userId,1mobile,2constituencyId,3constiname,4distiictId,5districtName
					List<Object[]> mobileNosList = cadreSurveyUserDAO.getUserMobileNos(userNames.keySet());
					for(Object[] mobileNo:mobileNosList){
						CadreRegisterInfo userData = new CadreRegisterInfo();
					 if(mobileNo[1] != null){
						userData.setArea(mobileNo[1].toString());//mobileNo
					 }
					 if(((Long)mobileNo[4]).longValue() < 11){
					     userData.setLocation("TG");//state
					 }else{
						 userData.setLocation("AP");//state
					 }
					 userData.setNumber(mobileNo[5].toString());//district
					 userData.setMemberShipNo(mobileNo[3].toString());//constituency
					 userData.setPercentStr(parliamentForAssemblyMap.get(mobileNo[3].toString()));
					 mobileNos.put((Long)mobileNo[0], userData);
					}
				}
				for(Long key:userMap.keySet()){
					dateMap = userMap.get(key);
					vo = new CadreRegisterInfo();
					vo.setName(userNames.get(key));
					
					//vo.setUname(unameMap.get(key) != null ? unameMap.get(key).toString() : "");
					CadreRegisterInfo userData = mobileNos.get(key);
					if(userData != null){
					    vo.setArea(userData.getArea());//mobileNo
					    vo.setLocation(userData.getLocation());//state
					    vo.setNumber(userData.getNumber());//district
					    vo.setMemberShipNo(userData.getMemberShipNo());//constituency
					    vo.setPercentStr(userData.getPercentStr());
					    vo.setUname(nameMap.get(key));
					    vo.setTabNo(tabMap.get(key));
					}
					List<CadreRegisterInfo> daysList = new ArrayList<CadreRegisterInfo>();
					for(Date date:datesList){
						if(dateMap.get(date) != null){
							CadreRegisterInfo day = dateMap.get(date);
						    if(count == 0){
							  day.setDate(sdf.format(date));
						    }
							daysList.add(day);
						}else{
							CadreRegisterInfo day = new CadreRegisterInfo();
							if(count == 0){
							   day.setDate(sdf.format(date));
							 }
							daysList.add(day);
						}
					}
					vo.setInfoList(daysList);
					if(vo.getInfoList() != null && vo.getInfoList().size() > 0)
					{
						Long count1 = 0l;
						for(CadreRegisterInfo vo3 : vo.getInfoList())
						{
							if(vo3.getTotalCount() != null)
							 count1 = count1 + vo3.getTotalCount();
						}
						vo.setTotalCount(count1);
						vo.setTotalAmount(vo.getTotalCount() > 0 ?  vo.getTotalCount()* 100 : 0);
					}
					returnList.add(vo);
					count++;
				}
			}
			
			
		}catch(Exception e){
			LOG.error("Exception rised in getCandidateDataCollectionInfo",e);
		}
		return returnList;
	}
	
	
	private String convertTimeTo12HrsFormat(String time){
		String[] timeArray = time.split(":");
		int hours = Integer.parseInt(timeArray[0].trim());
		if(hours > 11){
			if(hours != 12){
			   hours = hours-12;
			}
			return hours+":"+timeArray[1]+" PM";
		}
		if(hours == 0){
			return "12:"+timeArray[1]+" AM";
		}
		
		return time+" AM";
	}
	
	public List<CadreRegisterInfo> getDetailsForConstituency(Long constituencyId)
	{
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		try {
			//Long totalConstituencyCount2012 = tdpCadreDAO.getGenderTotalCount(constituencyId, 2012L,"constituency");
			//Long totalConstituencyCount2014 = tdpCadreDAO.getGenderTotalCount(constituencyId, 2014L,"constituency");
			List<CadreRegisterInfo> ageWiseInfo = getConstituencyWiseAgeRangeCount(constituencyId);
			
			if(ageWiseInfo != null && ageWiseInfo.size()>0)
			{
				CadreRegisterInfo mainVO = new CadreRegisterInfo();
				mainVO.setName("agewise");
				mainVO.setAllDetailsList(ageWiseInfo);
				returnList.add(mainVO);
			}
			
			List<CadreRegisterInfo> genderWiseInfo = getConstituencyWiseGenderCadreCount(constituencyId);
			if(genderWiseInfo != null && genderWiseInfo.size()>0)
			{
				CadreRegisterInfo mainVO = new CadreRegisterInfo();
				mainVO.setName("genderwise");
				mainVO.setInfoList(genderWiseInfo);
				returnList.add(mainVO);
			}
			
			List<CadreRegisterInfo> casteWiseInfo = getConstituencyWiseCastCadreCount(constituencyId);
			
			if(casteWiseInfo != null && casteWiseInfo.size()>0)
			{
				CadreRegisterInfo mainVO = new CadreRegisterInfo();
				mainVO.setName("castewise");
				mainVO.setCadreRegisterInfoList(casteWiseInfo);
				returnList.add(mainVO);
			}
			
		} catch (Exception e) {
			LOG.error("Exception rised in getDetailsForState",e);
		}
		return returnList;
	}
	
	public List<CadreRegisterInfo> getDetailsForDistricts(Long districtId)
	{
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		try {
			//Long totalDistrictCount2012 = tdpCadreDAO.getAgeRangeTotalCount(districtId, 2012L,"district");
			//Long totalDistrictCount2014 = tdpCadreDAO.getAgeRangeTotalCount(districtId, 2014L,"district");
			List<CadreRegisterInfo> ageWiseInfo = getDistrictWiseAgeRangeCount(districtId);
			
			if(ageWiseInfo != null && ageWiseInfo.size()>0)
			{
				CadreRegisterInfo mainVO = new CadreRegisterInfo();
				mainVO.setName("agewise");
				mainVO.setAllDetailsList(ageWiseInfo);
				returnList.add(mainVO);
			}
			
			List<CadreRegisterInfo> genderWiseInfo = getDistrictWiseGenderCadreCount(districtId);
			
			if(genderWiseInfo != null && genderWiseInfo.size()>0)
			{
				CadreRegisterInfo mainVO = new CadreRegisterInfo();
				mainVO.setName("genderwise");
				mainVO.setInfoList(genderWiseInfo);
				returnList.add(mainVO);
			}
			
			List<CadreRegisterInfo> casteWiseInfo = getDistrictWiseCastCadreCount(districtId);
			
			if(casteWiseInfo != null && casteWiseInfo.size()>0)
			{
				CadreRegisterInfo mainVO = new CadreRegisterInfo();
				mainVO.setName("castewise");
				mainVO.setCadreRegisterInfoList(casteWiseInfo);
				returnList.add(mainVO);
			}
			
		} catch (Exception e) {
			LOG.error("Exception rised in getDetailsForDistricts",e);
		}
		return returnList;
	}
	
	public List<CadreRegisterInfo> getConstituencyWiseAgeRangeCount(Long constituencyId) {
		List<CadreRegisterInfo> ageWiseTotalList=new ArrayList<CadreRegisterInfo>();
		try {
			List<Object[]> countsList = tdpCadreDAO.getAgeTotalCount(constituencyId, "constituency");
			Long totalConstituencyCount2014 = null;
			Long totalConstituencyCount2012 = null;
			for(Object[] counts:countsList){
				if(counts[1] != null){
					if(((Long)counts[1]).longValue() == 2012l){
						totalConstituencyCount2012 = (Long)counts[0];
					}else if(((Long)counts[1]).longValue() == 2014l){
						totalConstituencyCount2014 = (Long)counts[0];
					}
				}
			}
			
			List<Object[]> cadreBelow18 = tdpCadreDAO.getAgeRangeCadreCount(constituencyId,"below 18","constituency");
			setAgeWiseRangeCount(cadreBelow18,"below 18", totalConstituencyCount2012, totalConstituencyCount2014, ageWiseTotalList);
			
			List<Object[]> cadre18to25info = tdpCadreDAO.getAgeRangeCadreCount(constituencyId,"18-25","constituency");
			setAgeWiseRangeCount(cadre18to25info,"18-25", totalConstituencyCount2012, totalConstituencyCount2014, ageWiseTotalList);
			
			List<Object[]> cadre26to35info = tdpCadreDAO.getAgeRangeCadreCount(constituencyId,"26-35","constituency");
			setAgeWiseRangeCount(cadre26to35info,"26-35", totalConstituencyCount2012, totalConstituencyCount2014, ageWiseTotalList);
			
			List<Object[]> cadre36to45info = tdpCadreDAO.getAgeRangeCadreCount(constituencyId,"36-45","constituency");
			setAgeWiseRangeCount(cadre36to45info,"36-45", totalConstituencyCount2012, totalConstituencyCount2014, ageWiseTotalList);
			
			List<Object[]> cadre46to60info = tdpCadreDAO.getAgeRangeCadreCount(constituencyId,"46-60","constituency");
			setAgeWiseRangeCount(cadre46to60info,"46-60", totalConstituencyCount2012, totalConstituencyCount2014, ageWiseTotalList);
			
			List<Object[]> cadreabove60info = tdpCadreDAO.getAgeRangeCadreCount(constituencyId,"above 60","constituency");
			setAgeWiseRangeCount(cadreabove60info,"above 60", totalConstituencyCount2012, totalConstituencyCount2014, ageWiseTotalList);
			
            
		} catch (Exception e) {
			LOG.error("Exception rised in getConstituencyWiseAgeRangeCount", e);
		}
		return ageWiseTotalList;
	}

	public List<CadreRegisterInfo> getDistrictWiseAgeRangeCount(Long districtId) {
		List<CadreRegisterInfo> ageWiseTotalList=new ArrayList<CadreRegisterInfo>();
		
		try {	
			
			 List<Object[]> countsList = tdpCadreDAO.getAgeTotalCount(districtId, "district");
				Long totalDistrictCount2014 = null;
				Long totalDistrictCount2012 = null;
				for(Object[] counts:countsList){
					if(counts[1] != null){
						if(((Long)counts[1]).longValue() == 2012l){
							totalDistrictCount2012 = (Long)counts[0];
						}else if(((Long)counts[1]).longValue() == 2014l){
							totalDistrictCount2014 = (Long)counts[0];
						}
					}
				}
			
			List<Object[]> cadreBelow18 = tdpCadreDAO.getAgeRangeCadreCount(districtId, "below 18","district");			
			setAgeWiseRangeCount(cadreBelow18,"below 18", totalDistrictCount2012, totalDistrictCount2014, ageWiseTotalList);
				
			List<Object[]> cadre18to25info = tdpCadreDAO.getAgeRangeCadreCount(districtId, "18-25","district");			
			setAgeWiseRangeCount(cadre18to25info,"18-25", totalDistrictCount2012, totalDistrictCount2014, ageWiseTotalList);
			
			List<Object[]> cadre26to35info = tdpCadreDAO.getAgeRangeCadreCount(districtId, "26-35","district");
			setAgeWiseRangeCount(cadre26to35info,"26-35", totalDistrictCount2012, totalDistrictCount2014, ageWiseTotalList);
			
			List<Object[]> cadre36to45info = tdpCadreDAO.getAgeRangeCadreCount(districtId,"36-45","district");
			setAgeWiseRangeCount(cadre36to45info,"36-45", totalDistrictCount2012, totalDistrictCount2014, ageWiseTotalList);
			
			List<Object[]> cadre46to60info = tdpCadreDAO.getAgeRangeCadreCount(districtId,"46-60","district");
			setAgeWiseRangeCount(cadre46to60info,"46-60", totalDistrictCount2012, totalDistrictCount2014, ageWiseTotalList);
			
			List<Object[]> cadreabove60info = tdpCadreDAO.getAgeRangeCadreCount(districtId,"above 60","district");
			setAgeWiseRangeCount(cadreabove60info,"above 60", totalDistrictCount2012, totalDistrictCount2014, ageWiseTotalList);
			
            
		} catch (Exception e) {
			LOG.error("Exception rised in getDistrictWiseAgeRangeCount", e);
		}
		return ageWiseTotalList;
	}	
	
	public void setAgeWiseRangeCount(List<Object[]> cadreinfo,String ageRange,Long totalCount2012, Long totalCount2014,List<CadreRegisterInfo> ageWiseTotalList) {

		CadreRegisterInfo ageVo = new CadreRegisterInfo();
		ageVo.setName(ageRange);
		ageVo.setApCount(0l);//2014 total count
		ageVo.setTgCount(0l);//2012 total count
		ageVo.setPercentStr("0");//2014 perc
		ageVo.setArea("0");//2012 perc
		if (cadreinfo!=null && cadreinfo.size() > 0)
		{						
			for (Object[] cadreinfo1 : cadreinfo) 
			{
				if (((Long)cadreinfo1[1]).longValue() == 2014l){
					 ageVo.setApCount((Long) cadreinfo1[0]);
						if(totalCount2014 != null && totalCount2014.longValue() > 0 ){
							ageVo.setPercentStr(new BigDecimal((ageVo.getApCount().doubleValue() / totalCount2014.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
						} 
			   }				
			   if (((Long)cadreinfo1[1]).longValue() == 2012l){ 
				   
					ageVo.setTgCount((Long)cadreinfo1[0]);
					if(totalCount2012 != null && totalCount2012.longValue() > 0 ){
						ageVo.setArea(new BigDecimal((ageVo.getTgCount().doubleValue() / totalCount2012.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					} 
			   }   
			}
		}  
		ageWiseTotalList.add(ageVo);
	} 
	
	public List<CadreRegisterInfo> getConstituencyWiseGenderCadreCount(Long constituencyId){
		List<CadreRegisterInfo> genderList = new ArrayList<CadreRegisterInfo>();
		try{
			 List<Object[]> countsList = tdpCadreDAO.getGenderTotalCount(constituencyId, "constituency");
				Long totalConstituencyCount2014 = null;
				Long totalConstituencyCount2012 = null;
				for(Object[] counts:countsList){
					if(counts[1] != null){
						if(((Long)counts[1]).longValue() == 2012l){
							totalConstituencyCount2012 = (Long)counts[0];
						}else if(((Long)counts[1]).longValue() == 2014l){
							totalConstituencyCount2014 = (Long)counts[0];
						}
					}
				}
			List<Object[]> genderInfoList=tdpCadreDAO.getGenderWiseCadreCount(constituencyId,"constituency");
			setGenderWiseCount(genderInfoList,genderList,totalConstituencyCount2012,totalConstituencyCount2014);							
						
		} catch (Exception e) {
			LOG.error("Exception rised in getConstituencyWiseGenderCadreCount", e);
		}
		return genderList;
	}
	
	public List<CadreRegisterInfo> getDistrictWiseGenderCadreCount(Long districtId){
		List<CadreRegisterInfo> genderList = new ArrayList<CadreRegisterInfo>();
		try{
			 List<Object[]> countsList = tdpCadreDAO.getGenderTotalCount(districtId, "district");
				Long totalCount2014 = null;
				Long totalCount2012 = null;
				for(Object[] counts:countsList){
					if(counts[1] != null){
						if(((Long)counts[1]).longValue() == 2012l){
							totalCount2012 = (Long)counts[0];
						}else if(((Long)counts[1]).longValue() == 2014l){
							totalCount2014 = (Long)counts[0];
						}
					}
				}
			List<Object[]> genderInfoList=tdpCadreDAO.getGenderWiseCadreCount(districtId,"district");
			setGenderWiseCount(genderInfoList,genderList,totalCount2012,totalCount2014);
			
			} catch (Exception e) {
				LOG.error("Exception rised in getDistrictWiseGenderCadreCount", e);
			}
		return genderList;
		
	}
	
	public void setGenderWiseCount(List<Object[]> genderInfoList,List<CadreRegisterInfo> genderList,Long totalCount2012,Long totalCount2014){
		CadreRegisterInfo maleVo = new CadreRegisterInfo();
			maleVo.setName("Male");
			maleVo.setApCount(0l);//2014 total count
			maleVo.setTgCount(0l);//2012 total count
			maleVo.setPercentStr("0");//2014 perc
			maleVo.setArea("0");//2012 perc
			
		CadreRegisterInfo femaleVo = new CadreRegisterInfo();
		   femaleVo.setName("Female");
		   femaleVo.setApCount(0l);//2014 total count
		   femaleVo.setTgCount(0l);//2012 total count
		   femaleVo.setPercentStr("0");//2014 perc
		   femaleVo.setArea("0");//2012 perc
		   
		genderList.add(maleVo);
		genderList.add(femaleVo);
		
		for(Object[] gender:genderInfoList)
		{	
			if(((Long)gender[1]).longValue() == 2014l){
				if( gender[2] != null && (gender[2].toString().equals("Male") || gender[2].toString().equals("M"))){
					maleVo.setApCount(maleVo.getApCount()+(Long)gender[0]);
				}else if( gender[2] != null && (gender[2].toString().equals("Female") || gender[2].toString().equals("F"))){
					femaleVo.setApCount(femaleVo.getApCount()+(Long)gender[0]);
				}	
			}else if(((Long)gender[1]).longValue() == 2012l){
				if( gender[2] != null && (gender[2].toString().equals("Male") || gender[2].toString().equals("M"))){
					maleVo.setTgCount(maleVo.getTgCount()+(Long)gender[0]);
				}else if( gender[2] != null && (gender[2].toString().equals("Female") || gender[2].toString().equals("F"))){
					femaleVo.setTgCount(femaleVo.getTgCount()+(Long)gender[0]);
				}	
			}
			
		}
	
		if(totalCount2012 != null && totalCount2012.longValue() > 0l){
			maleVo.setArea(new BigDecimal((maleVo.getTgCount().doubleValue() / totalCount2012.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			femaleVo.setArea(new BigDecimal((femaleVo.getTgCount().doubleValue() / totalCount2012.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		}
		if(totalCount2014 != null && totalCount2014.longValue() > 0l){
			maleVo.setPercentStr(new BigDecimal((maleVo.getApCount().doubleValue() / totalCount2014.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			femaleVo.setPercentStr(new BigDecimal((femaleVo.getApCount().doubleValue() / totalCount2014.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		}
			
	}

	public List<CadreRegisterInfo> getConstituencyWiseCastCadreCount(Long constituencyId){
		List<CadreRegisterInfo> casteList = new ArrayList<CadreRegisterInfo>();
		try{
			List<Object[]> countsList = tdpCadreDAO.getCasteGroupTotalCount(constituencyId, "constituency");
			Long totalCount2014 = null;
			Long totalCount2012 = null;
			for(Object[] counts:countsList){
				if(counts[1] != null){
					if(((Long)counts[1]).longValue() == 2012l){
						totalCount2012 = (Long)counts[0];
					}else if(((Long)counts[1]).longValue() == 2014l){
						totalCount2014 = (Long)counts[0];
					}
				}
			}
			//0 count,1 year,2 casteState ,3 casteName
			List<Object[]> casteInfoList = tdpCadreDAO.getCastWiseCadreCount(constituencyId,"constituency");
			setCasteWiseCount(casteInfoList, casteList,totalCount2012,totalCount2014);				
		}catch (Exception e) {
			//LOG.error("Exception rised in getConstituencyWiseCastCadreCount", e);
		}			
		return casteList;
		
	}
	
	public List<CadreRegisterInfo> getDistrictWiseCastCadreCount(Long districtId){
		List<CadreRegisterInfo> casteList = new ArrayList<CadreRegisterInfo>();
		try{
			List<Object[]> countsList = tdpCadreDAO.getCasteGroupTotalCount(districtId, "district");
			Long totalCount2014 = null;
			Long totalCount2012 = null;
			for(Object[] counts:countsList){
				if(counts[1] != null){
					if(((Long)counts[1]).longValue() == 2012l){
						totalCount2012 = (Long)counts[0];
					}else if(((Long)counts[1]).longValue() == 2014l){
						totalCount2014 = (Long)counts[0];
					}
				}
			}
			List<Object[]> casteInfoList = tdpCadreDAO.getCastWiseCadreCount(districtId,"district");
			setCasteWiseCount(casteInfoList, casteList,totalCount2012,totalCount2014);
		}catch (Exception e) {
			//LOG.error("Exception rised in getDistrictWiseCastCadreCount", e);
		}		
		return casteList;		
	}
	
	public void setCasteWiseCount(List<Object[]> casteInfoList,List<CadreRegisterInfo> casteList,Long totalCount2012,Long totalCount2014){
		
		LinkedHashMap<Long,CadreRegisterInfo> casteMap = new LinkedHashMap<Long,CadreRegisterInfo>();
		//0 count,1 year,2 casteStateId ,3 casteName,4casteGroup
		for(Object[] caste:casteInfoList){
			
			CadreRegisterInfo casteVo = casteMap.get((Long)caste[2]);
			if(casteVo == null){
				casteVo = new CadreRegisterInfo();
				casteVo.setName(caste[3].toString());
				casteVo.setApCount(0l);//2014 total count
				casteVo.setTgCount(0l);//2012 total count
				casteVo.setPercentStr("0");//2014 perc
				casteVo.setArea("0");//2012 perc
				casteVo.setDate(caste[4].toString());//casteGroup
				casteMap.put((Long)caste[2],casteVo);
			}
			if(((Long)caste[1]).longValue() == 2014l){
				casteVo.setApCount((Long)caste[0]);
				if(totalCount2014 != null && totalCount2014.longValue() > 0){
					casteVo.setPercentStr(new BigDecimal((casteVo.getApCount().doubleValue() / totalCount2014.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
			}else if(((Long)caste[1]).longValue() == 2012l){
				casteVo.setTgCount((Long)caste[0]);
				if(totalCount2012 != null && totalCount2012.longValue() > 0){
					casteVo.setArea(new BigDecimal((casteVo.getTgCount().doubleValue() / totalCount2012.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
			}
			
		}
		casteList.addAll(new ArrayList<CadreRegisterInfo>(casteMap.values()));
	}
	
	public List<CadreRegisterInfo> getAssemblyConstituencies(String type){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		try{
			CadreRegisterInfo vo = null;
			List<Object[]> constituenciesList = constituencyDAO.getAssemblyConstituenciesInAP(type);
			for(Object[] constituency:constituenciesList){
				vo = new CadreRegisterInfo();
				vo.setId((Long)constituency[0]);
				vo.setName(constituency[1].toString());
				returnList.add(vo);
			}
		}catch(Exception e){
			LOG.error("Exception rised in getAssemblyConstituencies",e);
		}
		return returnList;
	}
	
	public List<CadreRegisterInfo> getPanchayatsInConstituencies(Long constituencyId){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		try{
			/*CadreRegisterInfo vo = null;
			List<Long> constiIds = new ArrayList<Long>();
			constiIds.add(constituencyId);
			List<Long> mandalIds = boothDAO.getTehsilsByConstituencyIds(constiIds, 11l);
			if(mandalIds.size() > 0){
				List<Object[]> constituenciesList = panchayatDAO.getAllPanchaytesInAConstituency(mandalIds);
				for(Object[] constituency:constituenciesList){
					vo = new CadreRegisterInfo();
					vo.setId((Long)constituency[0]);
					vo.setName(constituency[1].toString());
					returnList.add(vo);
				}
			}*/
			CadreRegisterInfo vo = null;
			List<Object[]> constituenciesList = boothDAO.getPanchayatsByConstituencyAndPublication(constituencyId,11l);
			for(Object[] constituency:constituenciesList){
				vo = new CadreRegisterInfo();
				vo.setId((Long)constituency[0]);
				vo.setName(constituency[1].toString());
				returnList.add(vo);
			}
		}catch(Exception e){
			LOG.error("Exception rised in getPanchayatsInConstituencies",e);
		}
		return returnList;
	}
	
	public List<CadreRegisterInfo> getBoothsInConstituencies(Long constituencyId){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		try{
			CadreRegisterInfo vo = null;
			List<Object[]> constituenciesList = boothDAO.getBoothsInAConstituency(constituencyId,11l);
			for(Object[] constituency:constituenciesList){
				vo = new CadreRegisterInfo();
				vo.setId((Long)constituency[0]);
				vo.setName(constituency[1].toString());
				returnList.add(vo);
			}
		}catch(Exception e){
			LOG.error("Exception rised in getBoothsInConstituencies",e);
		}
		return returnList;
	}
	
	public List<CadreRegisterInfo> getStateWiseRegistrationInfo(List<Long> stateIds,String fromDateStr, String toDateStr,Long userCountValue){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try{

		Date fromDate = null;
		Date toDate = null;

		if(fromDateStr != null && fromDateStr.trim().length()>0)
		{
		fromDate = format.parse(fromDateStr);
		}
		if(toDateStr != null && toDateStr.trim().length()>0)
		{
		toDate = format.parse(toDateStr);
		}


		if(stateIds.contains(1l)){
		Long count_2012AP = tdpCadreDAO.getWorkStartedConstituencyYearCount(2012L,"AP",null,null);
		Long count_2014AP = tdpCadreDAO.getWorkStartedConstituencyYearCount(2014L,"AP",fromDate,toDate);
		CadreRegisterInfo apVo = new CadreRegisterInfo();
		apVo.setLocation("Andhra Pradesh");
		apVo.setId(1l);
		apVo.setApCount(count_2014AP);
		apVo.setTgCount(count_2012AP);
		if(userCountValue!=null){
		if(count_2014AP < userCountValue){
		returnList.add(apVo);
		}
		}
		else
		returnList.add(apVo);
		}
		if(stateIds.contains(36l)){
		Long count_2012TS = tdpCadreDAO.getWorkStartedConstituencyYearCount(2012L,"TS",null,null);
		Long count_2014TS = tdpCadreDAO.getWorkStartedConstituencyYearCount(2014L,"TS",fromDate,toDate);
		CadreRegisterInfo tgVo = new CadreRegisterInfo();
		tgVo.setLocation("Telangana");
		tgVo.setId(36l);
		tgVo.setApCount(count_2014TS);
		tgVo.setTgCount(count_2012TS);
		if(userCountValue!=null){
		if(count_2014TS < userCountValue){
		returnList.add(tgVo);
		}
		}
		else
		returnList.add(tgVo);

		}


		}catch(Exception e){
		LOG.error("Exception rised in getStateWiseRegistrationInfo",e);
		}
		return returnList;
		}
	public List<CadreRegisterInfo> getLocationWiseRegistrationInfo1(List<Long> ids,String type,String fromDateStr, String toDateStr,boolean reqOthers,Long userCountValue){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		List<CadreRegisterInfo> returnList1= new ArrayList<CadreRegisterInfo>();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try{
		CadreRegisterInfo infoVo = null;
		Map<Long,Map<Long,Long>> locationMap = new HashMap<Long,Map<Long,Long>>();//Map<locationId,Map<year,count>>
		
		
		Map<Long,Long> vtrMap = new HashMap<Long, Long>();
		Map<Long,Long> cdrCountMap = new HashMap<Long, Long>();
		
		Map<Long,String> locationType = new HashMap<Long,String>();
		Map<Long,Long> yearMap = null;
		List<Object[]> namesList = new ArrayList<Object[]>();
		//0 count,1 id,2 name ,3 year
		List<Object[]> constituencyInfoList = new ArrayList<Object[]>();

		Date fromDate = null;
		Date toDate = null;

		if(fromDateStr != null && fromDateStr.trim().length()>0)
		{
		fromDate = format.parse(fromDateStr);
		}
		if(toDateStr != null && toDateStr.trim().length()>0)
		{
		toDate = format.parse(toDateStr);
		}

		Long constituencyId = 0L;
		
		Long noOfDays = dateService.noOfDayBetweenDates(IConstants.CADRE_2014_START_DATE, IConstants.CADRE_2014_LAST_DATE);
		Long districtId = null;

		if(ids.size() > 0){
			if(type.equals("assembly")){
			constituencyInfoList = tdpCadreDAO.getCadreInfoConstituencytWise(ids,fromDate,toDate,2014l);
			constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoConstituencytWise(ids,null,null,2012l));
			namesList = constituencyDAO.getConstituencyNameByConstituencyIdsList(ids);
			}else if(type.equals("district")){
			constituencyInfoList = tdpCadreDAO.getCadreInfoDistrictWise(ids,fromDate,toDate,2014l);
			constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoDistrictWise(ids,null,null,2012l));
			namesList = districtDAO.getDistrictDetailsByDistrictIds(ids);
			}else if(type.equals("panchayat")){
				constituencyId = boothDAO.getConstituencyIdByLocationIdAndType(ids.get(0), type);
				constituencyInfoList = tdpCadreDAO.getCadreInfoPanchayatWise(ids,fromDate,toDate,2014l);
				constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoPanchayatWise(ids,null,null,2012l));
				
				List<Object[]> totRegList = tdpCadreDAO.getCadreInfoPanchayatWise(ids,null,null,2014l);
				
				List<Object[]> list = voterInfoDAO.getVotersCountInPanchayatList(ids, IConstants.VOTER_DATA_PUBLICATION_ID);
				
				if(list!=null && list.size()>0){
					districtId =  Long.valueOf(list.get(0)[3].toString());
					for(Object[] obj:list){
						vtrMap.put(Long.valueOf(obj[0].toString()), Long.valueOf(obj[2].toString()));
					}
				}
				
				if(totRegList!=null && totRegList.size()>0){
					for(Object[] obj:totRegList){
						cdrCountMap.put(Long.valueOf(obj[1].toString()), Long.valueOf(obj[0].toString()));
					}
				}
				
				namesList = panchayatDAO.getPanchayatNamesByIds(ids);
			}else if(type.equals("booth")){
				constituencyId = boothDAO.getConstituencyIdByLocationIdAndType(ids.get(0), type);
				constituencyInfoList = tdpCadreDAO.getCadreInfoBoothWise(ids,fromDate,toDate,2014l);
				constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoBoothWise(ids,null,null,2012l));
				
				List<Object[]> list = voterInfoDAO.getVotersCountInBoothsList(ids, IConstants.VOTER_DATA_PUBLICATION_ID);
				
				if(list!=null && list.size()>0){
					districtId =  Long.valueOf(list.get(0)[3].toString());
					for(Object[] obj:list){
						vtrMap.put(Long.valueOf(obj[0].toString()), Long.valueOf(obj[2].toString()));
					}
				}
				
				List<Object[]> totRegList = tdpCadreDAO.getCadreInfoBoothWise(ids,null,null,2014l);
				if(totRegList!=null && totRegList.size()>0){
					for(Object[] obj:totRegList){
						cdrCountMap.put(Long.valueOf(obj[1].toString()), Long.valueOf(obj[0].toString()));
					}
				}
				
				namesList = boothDAO.getBoothNamesByIds(ids);
			}else if(type.equals("mandal")){
				List<Long> mandalIds = new ArrayList<Long>();
				List<Long> localBodyIds = new ArrayList<Long>();
				for(Long id:ids){
				if(id.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				localBodyIds.add(new Long(id.toString().substring(1)));
				}else{
				mandalIds.add(new Long(id.toString().substring(1)));
				}
				}
				if(mandalIds.size() > 0){
				constituencyId = boothDAO.getConstituencyIdByLocationIdAndType(mandalIds.get(0), type);
				}else if(localBodyIds.size() > 0){
				constituencyId = boothDAO.getConstituencyIdByLocationIdAndType(localBodyIds.get(0), "localBody");
				}
				if(mandalIds.size() > 0){
				constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoMandalWise(mandalIds,fromDate,toDate,2014l));
				constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoMandalWise(mandalIds,null,null,2012l));
				
				List<Object[]> list = voterInfoDAO.getVotersCountInATehsilList(mandalIds, IConstants.VOTER_DATA_PUBLICATION_ID);
				
				if(list!=null && list.size()>0){
					districtId =  Long.valueOf(list.get(0)[3].toString());
					for(Object[] obj:list){
						vtrMap.put(Long.valueOf(obj[0].toString()), Long.valueOf(obj[2].toString()));
					}
				}
				
				List<Object[]> totRegList = tdpCadreDAO.getCadreInfoMandalWise(mandalIds,null,null,2014l);
				if(totRegList!=null && totRegList.size()>0){
					for(Object[] obj:totRegList){
						cdrCountMap.put(Long.valueOf(obj[1].toString()), Long.valueOf(obj[0].toString()));
					}
				}
				
				
				namesList.addAll(tehsilDAO.getTehsilNameByTehsilIdsList(mandalIds));
				}
				if(localBodyIds.size() > 0){
					/*List<Long> assmblyLclIds = new ArrayList<Long>();
					assmblyLclIds = assemblyLocalElectionBodyDAO.getLEBIdsByALEBIds(localBodyIds);*/
					
						constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoLocalBodyWise(localBodyIds,fromDate,toDate,2014l));
						constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoLocalBodyWise(localBodyIds,null,null,2012l));
						
						List<Object[]> list = voterInfoDAO.getVotersCountInALocalBodyList(localBodyIds, IConstants.VOTER_DATA_PUBLICATION_ID);
						if(list!=null && list.size()>0){
							districtId =  Long.valueOf(list.get(0)[3].toString());
							for(Object[] obj:list){
								vtrMap.put(Long.valueOf(obj[0].toString()), Long.valueOf(obj[2].toString()));
							}
						}
						
						List<Object[]> totRegList = tdpCadreDAO.getCadreInfoLocalBodyWise(localBodyIds,null,null,2014l);
						if(totRegList!=null && totRegList.size()>0){
							for(Object[] obj:totRegList){
								cdrCountMap.put(Long.valueOf(obj[1].toString()), Long.valueOf(obj[0].toString()));
							}
						}
						
						namesList.addAll(localElectionBodyDAO.getLocalElectionBodyNames(localBodyIds));
					
				
				}
			}
			for(Object[] info:constituencyInfoList){
				yearMap = locationMap.get((Long)info[1]);
				if(yearMap == null){
					yearMap = new HashMap<Long,Long>();
					locationMap.put((Long)info[1],yearMap);
					if(type.equals("assembly") || type.equals("mandal")){
						locationType.put((Long)info[1], info[4].toString());
					}
				}
				yearMap.put((Long)info[3], (Long)info[0]);
			}
		}

		Map<Long,Long> yearWiseTotalCadreMap = new HashMap<Long, Long>();
		if(namesList != null && namesList.size()>0 ){
			for(Object[] name:namesList){
				Long cadreCount2014 = 0L;
				Long cadreCount2012 = 0L;
		
				try{
					if(yearWiseTotalCadreMap.get(2014L) != null){
						cadreCount2014 = yearWiseTotalCadreMap.get(2014L);
					}
					if(yearWiseTotalCadreMap.get(2012L) != null){
						cadreCount2012 = yearWiseTotalCadreMap.get(2012L);
					}
			
					yearMap = locationMap.get((Long)name[0]);
					infoVo = new CadreRegisterInfo();
					infoVo.setId((Long)name[0]);
					infoVo.setLocation(name[1].toString());
					
					Long count_2014 = 0l;
					if(yearMap!=null){
					 count_2014 = Long.valueOf(yearMap.get(2014L) != null ? yearMap.get(2014L).toString().trim():"0");
					}
					if(type.equalsIgnoreCase("panchayat") || type.equalsIgnoreCase("booth") || type.equalsIgnoreCase("mandal")){
						
						String currentDate = dateService.getCurrentDateInStringFormatYYYYMMDD();
						Long tillDays = dateService.noOfDayBetweenDates(IConstants.CADRE_2014_START_DATE, IConstants.CADRE_2014_LAST_DATE);
						Long stateId = 2l;
						if(districtId>10){
							stateId = 1l;
						}
						
						Long registerdCount = cdrCountMap.get((Long)name[0]); //OVERALL REGISTERED COUNT
						Long votersCount = vtrMap.get((Long)name[0]);
						
						infoVo.setOverAllRegCount(registerdCount);
						
						if(votersCount!=null){
							Long targetCadre = 0l;
							if(stateId == 1){
								if(((votersCount * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014)<noOfDays){
									targetCadre = ((votersCount * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014);
								}else{
									targetCadre =  (((votersCount * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014)/noOfDays)*tillDays;
								}
							}else{
								if(((votersCount * IConstants.TARGET_CADRE_TG) / IConstants.TG_VOTERS_2014)<noOfDays){
									targetCadre = ((votersCount * IConstants.TARGET_CADRE_TG) / IConstants.TG_VOTERS_2014);
								}else{
									targetCadre =  (((votersCount * IConstants.TARGET_CADRE_TG) / IConstants.TG_VOTERS_2014)/noOfDays)*tillDays;
								}
							}
							
							infoVo.setTargetCadre(targetCadre);
							infoVo.setTotalVoters(votersCount);
						}
						
						  String percentage ="0.0";
					
						  /*if(infoVo.getTargetCadre()!=null && infoVo.getTargetCadre()>0){
							  percentage = (new BigDecimal(count_2014*(100.0)/infoVo.getTargetCadre().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						  }*/
						  if(infoVo.getTargetCadre()!=null && infoVo.getTargetCadre()>0 && infoVo.getOverAllRegCount()!=null){
							  percentage = (new BigDecimal(infoVo.getOverAllRegCount()*(100.0)/infoVo.getTargetCadre().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						  }
						  float fl_perc = Float.parseFloat(percentage);
						  if(fl_perc>=120f){
							  infoVo.setColorStatus("Best");
						  }else if(fl_perc>=100f && fl_perc<120f){
							  infoVo.setColorStatus("Good");
						  }else if(fl_perc>=75f && fl_perc<100f){
							  infoVo.setColorStatus("Ok");
						  }else if(fl_perc>=50f && fl_perc<75f){
							  infoVo.setColorStatus("Poor");
						  }else {
							  infoVo.setColorStatus("Worst");
						  }
						  
						  infoVo.setRegPercent(percentage);
					}
					
					if(yearMap != null)	{
						
						
						
						
						cadreCount2012 = cadreCount2012 + Long.valueOf(yearMap.get(2012L) != null ? yearMap.get(2012L).toString().trim():"0");
						cadreCount2014 = cadreCount2014 + Long.valueOf(yearMap.get(2014L) != null ? yearMap.get(2014L).toString().trim():"0");
				
						yearWiseTotalCadreMap.put(2014L,cadreCount2014);
						yearWiseTotalCadreMap.put(2012L,cadreCount2012);
				
						infoVo.setApCount(yearMap.get(2014l));
						infoVo.setTgCount(yearMap.get(2012l));
						if(type.equals("assembly")){
							String reqType = locationType.get((Long)name[0]);
							if(reqType != null && !reqType.equalsIgnoreCase("URBAN") ){
								infoVo.setName("True");
								infoVo.setArea("True");
							}
						}else if(type.equals("mandal")){
							String reqType = locationType.get((Long)name[0]);
							if(reqType != null && reqType.equalsIgnoreCase("mandal") ){
								infoVo.setArea("True");
							}
						}
						if((type.equals("assembly") || type.equals("district")) && infoVo.getApCount() != null && infoVo.getApCount().longValue() > 0 && infoVo.getTgCount() != null && infoVo.getTgCount().longValue() > 0 ){
							infoVo.setName(new BigDecimal(infoVo.getApCount()*(100.0)/infoVo.getTgCount().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
						}
			
					}
					returnList.add(infoVo);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}

		if(reqOthers && constituencyId != null && constituencyId != 0L && ids.size() > 1)
		{

		List<Object[]> enrollmentYearWiseCadreCount = tdpCadreDAO.getEnrollmentYearWiseDetails(constituencyId,fromDate,toDate,2014L);
		enrollmentYearWiseCadreCount.addAll( tdpCadreDAO.getEnrollmentYearWiseDetails(constituencyId,null,null,2012L));

		if(enrollmentYearWiseCadreCount != null && enrollmentYearWiseCadreCount.size()>0 && yearWiseTotalCadreMap != null && yearWiseTotalCadreMap.size()>0)
		{
		Long totalCount2012 = 0L;
		Long actualCount2012 = 0L;

		Long totalCount2014 = 0L;
		Long actualCount2014 = 0L;

		for (Object[] cadre : enrollmentYearWiseCadreCount)
		{
		if(cadre[0] != null)
		{
		Long enrollmentYear = Long.valueOf(cadre[0].toString());

		if(enrollmentYear.longValue() == 2014L)
		{
		totalCount2014 = cadre[1] != null ? Long.valueOf(cadre[1].toString()):0L;
		actualCount2014 = yearWiseTotalCadreMap.get(2014L) != null ? yearWiseTotalCadreMap.get(2014L):0L;
		}

		if(enrollmentYear.longValue() == 2012L)
		{
		totalCount2012 = cadre[1] != null ? Long.valueOf(cadre[1].toString()):0L;
		actualCount2012 = yearWiseTotalCadreMap.get(2012L) != null ? yearWiseTotalCadreMap.get(2012L):0L;
		}

		}
		}

		infoVo = new CadreRegisterInfo();
		infoVo.setLocation(" Others ");
		Long count2014 = totalCount2014 - actualCount2014;
		Long count2012 = totalCount2012 - actualCount2012;
		if(count2014<0){
			infoVo.setApCount(0l);
		}else{
			infoVo.setApCount( count2014 );
		}
		infoVo.setTgCount( count2012 );
		infoVo.setArea(" - ");
		if((count2014 != null && count2014 != 0L) || (count2012 != null && count2012 != 0L))
		{
		// infoVo.setName(new BigDecimal((double)count2014*(100.0)/(double)count2012).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		returnList.add(0,infoVo);
		}
		}
		}


		}catch(Exception e){
		LOG.error("Exception rised in getLocationWiseRegistrationInfo",e);
		}
		Collections.sort(returnList,locationSort);
		if(userCountValue!=null){
			if(returnList!=null && returnList.size()>0)
			{
				for (CadreRegisterInfo cadreRegisterInfo : returnList)
				{
					if(cadreRegisterInfo != null && cadreRegisterInfo.getApCount() != null)
					{
						if(cadreRegisterInfo.getApCount() < userCountValue)
						{
							returnList1.add(cadreRegisterInfo);
						}
					}
					
				}
				return returnList1;
			}
		}

		return returnList;
		}


	
	
	public List<CadreRegisterInfo> getLocationWiseRegistrationInfo(List<Long> ids,String type,String fromDateStr, String toDateStr,boolean reqOthers,Long userCountValue){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		List<CadreRegisterInfo> returnList1= new ArrayList<CadreRegisterInfo>();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try{
		CadreRegisterInfo infoVo = null;
		Map<Long,Map<Long,Long>> locationMap = new HashMap<Long,Map<Long,Long>>();//Map<locationId,Map<year,count>>
		
		
		Map<Long,Long> vtrMap = new HashMap<Long, Long>();
		Map<Long,Long> cdrCountMap = new HashMap<Long, Long>();
		
		Map<Long,String> locationType = new HashMap<Long,String>();
		Map<Long,Long> yearMap = null;
		List<Object[]> namesList = new ArrayList<Object[]>();
		//0 count,1 id,2 name ,3 year
		List<Object[]> constituencyInfoList = new ArrayList<Object[]>();

		Date fromDate = null;
		Date toDate = null;

		if(fromDateStr != null && fromDateStr.trim().length()>0)
		{
		fromDate = format.parse(fromDateStr);
		}
		if(toDateStr != null && toDateStr.trim().length()>0)
		{
		toDate = format.parse(toDateStr);
		}

		Long constituencyId = 0L;
		
		Long noOfDays = dateService.noOfDayBetweenDates(IConstants.CADRE_2014_START_DATE, IConstants.CADRE_2014_LAST_DATE);
		Long districtId = null;

		if(ids.size() > 0){
			if(type.equals("assembly")){
			constituencyInfoList = tdpCadreDAO.getCadreInfoConstituencytWise(ids,fromDate,toDate,2014l);
			constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoConstituencytWise(ids,null,null,2012l));
			namesList = constituencyDAO.getConstituencyNameByConstituencyIdsList(ids);
			}else if(type.equals("district")){
			constituencyInfoList = tdpCadreDAO.getCadreInfoDistrictWise(ids,fromDate,toDate,2014l);
			constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoDistrictWise(ids,null,null,2012l));
			namesList = districtDAO.getDistrictDetailsByDistrictIds(ids);
			}else if(type.equals("panchayat")){
				constituencyId = boothDAO.getConstituencyIdByLocationIdAndType(ids.get(0), type);
				constituencyInfoList = tdpCadreDAO.getCadreInfoPanchayatWise(ids,fromDate,toDate,2014l);
				constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoPanchayatWise(ids,null,null,2012l));
				
				List<Object[]> totRegList = tdpCadreDAO.getCadreInfoPanchayatWise(ids,null,null,2014l);
				
				List<Object[]> list = voterInfoDAO.getVotersCountInPanchayatList(ids, IConstants.VOTER_DATA_PUBLICATION_ID);
				
				if(list!=null && list.size()>0){
					districtId =  Long.valueOf(list.get(0)[3].toString());
					for(Object[] obj:list){
						vtrMap.put(Long.valueOf(obj[0].toString()), Long.valueOf(obj[2].toString()));
					}
				}
				if(districtId == null && constituencyId != null){
					districtId = constituencyDAO.get(constituencyId).getDistrict().getDistrictId();
				}
				if(totRegList!=null && totRegList.size()>0){
					for(Object[] obj:totRegList){
						cdrCountMap.put(Long.valueOf(obj[1].toString()), Long.valueOf(obj[0].toString()));
					}
				}
				
				namesList = panchayatDAO.getPanchayatNamesByIds(ids);
			}else if(type.equals("booth")){
				constituencyId = boothDAO.getConstituencyIdByLocationIdAndType(ids.get(0), type);
				constituencyInfoList = tdpCadreDAO.getCadreInfoBoothWise(ids,fromDate,toDate,2014l);
				constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoBoothWise(ids,null,null,2012l));
				
				List<Object[]> list = voterInfoDAO.getVotersCountInBoothsList(ids, IConstants.VOTER_DATA_PUBLICATION_ID);
				
				if(list!=null && list.size()>0){
					districtId =  Long.valueOf(list.get(0)[3].toString());
					for(Object[] obj:list){
						vtrMap.put(Long.valueOf(obj[0].toString()), Long.valueOf(obj[2].toString()));
					}
				}
				if(districtId == null && constituencyId != null){
					districtId = constituencyDAO.get(constituencyId).getDistrict().getDistrictId();
				}
				List<Object[]> totRegList = tdpCadreDAO.getCadreInfoBoothWise(ids,null,null,2014l);
				if(totRegList!=null && totRegList.size()>0){
					for(Object[] obj:totRegList){
						cdrCountMap.put(Long.valueOf(obj[1].toString()), Long.valueOf(obj[0].toString()));
					}
				}
				
				namesList = boothDAO.getBoothNames(ids);
			}else if(type.equals("mandal")){
				List<Long> mandalIds = new ArrayList<Long>();
				List<Long> localBodyIds = new ArrayList<Long>();
				for(Long id:ids){
				if(id.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				localBodyIds.add(new Long(id.toString().substring(1)));
				}else{
				mandalIds.add(new Long(id.toString().substring(1)));
				}
				}
				if(mandalIds.size() > 0){
				constituencyId = boothDAO.getConstituencyIdByLocationIdAndType(mandalIds.get(0), type);
				}else if(localBodyIds.size() > 0){
				constituencyId = boothDAO.getConstituencyIdByLocationIdAndType(localBodyIds.get(0), "localBody");
				}
				if(mandalIds.size() > 0){
				constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoMandalWise(mandalIds,fromDate,toDate,2014l));
				constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoMandalWise(mandalIds,null,null,2012l));
				
				List<Object[]> list = voterInfoDAO.getVotersCountInATehsilList(mandalIds, IConstants.VOTER_DATA_PUBLICATION_ID);
				
				if(list!=null && list.size()>0){
					districtId =  Long.valueOf(list.get(0)[3].toString());
					for(Object[] obj:list){
						vtrMap.put(Long.valueOf(obj[0].toString()), Long.valueOf(obj[2].toString()));
					}
				}
				if(districtId == null && constituencyId != null){
					districtId = constituencyDAO.get(constituencyId).getDistrict().getDistrictId();
				}
				List<Object[]> totRegList = tdpCadreDAO.getCadreInfoMandalWise(mandalIds,null,null,2014l);
				if(totRegList!=null && totRegList.size()>0){
					for(Object[] obj:totRegList){
						cdrCountMap.put(Long.valueOf(obj[1].toString()), Long.valueOf(obj[0].toString()));
					}
				}
				
				
				namesList.addAll(tehsilDAO.getTehsilNameByTehsilIdsList(mandalIds));
				}
				if(localBodyIds.size() > 0){
					List<Long> assmblyLclIds = new ArrayList<Long>();
					assmblyLclIds = assemblyLocalElectionBodyDAO.getLEBIdsByALEBIds(localBodyIds);
					if(assmblyLclIds!=null && assmblyLclIds.size()>0){
						constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoLocalBodyWise(assmblyLclIds,fromDate,toDate,2014l));
						constituencyInfoList.addAll(tdpCadreDAO.getCadreInfoLocalBodyWise(assmblyLclIds,null,null,2012l));
						
						List<Object[]> list = voterInfoDAO.getVotersCountInALocalBodyList(assmblyLclIds, IConstants.VOTER_DATA_PUBLICATION_ID);
						if(list!=null && list.size()>0){
							districtId =  Long.valueOf(list.get(0)[3].toString());
							for(Object[] obj:list){
								vtrMap.put(Long.valueOf(obj[0].toString()), Long.valueOf(obj[2].toString()));
							}
						}
						if(districtId == null && constituencyId != null){
							districtId = constituencyDAO.get(constituencyId).getDistrict().getDistrictId();
						}
						List<Object[]> totRegList = tdpCadreDAO.getCadreInfoLocalBodyWise(assmblyLclIds,null,null,2014l);
						if(totRegList!=null && totRegList.size()>0){
							for(Object[] obj:totRegList){
								cdrCountMap.put(Long.valueOf(obj[1].toString()), Long.valueOf(obj[0].toString()));
							}
						}
						
						namesList.addAll(localElectionBodyDAO.getLocalElectionBodyNames(assmblyLclIds));
					}
				
				}
			}
			for(Object[] info:constituencyInfoList){
				yearMap = locationMap.get((Long)info[1]);
				if(yearMap == null){
					yearMap = new HashMap<Long,Long>();
					locationMap.put((Long)info[1],yearMap);
					if(type.equals("assembly") || type.equals("mandal")){
						locationType.put((Long)info[1], info[4].toString());
					}
				}
				yearMap.put((Long)info[3], (Long)info[0]);
			}
		}

		Map<Long,Long> yearWiseTotalCadreMap = new HashMap<Long, Long>();
		if(namesList != null && namesList.size()>0 ){
			for(Object[] name:namesList){
				Long cadreCount2014 = 0L;
				Long cadreCount2012 = 0L;
		
				try{
					if(yearWiseTotalCadreMap.get(2014L) != null){
						cadreCount2014 = yearWiseTotalCadreMap.get(2014L);
					}
					if(yearWiseTotalCadreMap.get(2012L) != null){
						cadreCount2012 = yearWiseTotalCadreMap.get(2012L);
					}
			
					yearMap = locationMap.get((Long)name[0]);
					infoVo = new CadreRegisterInfo();
					infoVo.setId((Long)name[0]);
					infoVo.setLocation(name[1].toString());
					
					Long count_2014 = 0l;
					if(yearMap!=null){
					 count_2014 = Long.valueOf(yearMap.get(2014L) != null ? yearMap.get(2014L).toString().trim():"0");
					}
					if(type.equalsIgnoreCase("panchayat") || type.equalsIgnoreCase("booth") || type.equalsIgnoreCase("mandal")){
						
						String currentDate = dateService.getCurrentDateInStringFormatYYYYMMDD();
						Long tillDays = dateService.noOfDayBetweenDates(IConstants.CADRE_2014_START_DATE, IConstants.CADRE_2014_LAST_DATE);
						Long stateId = 2l;
						if(districtId>10){
							stateId = 1l;
						}
						
						Long registerdCount = cdrCountMap.get((Long)name[0]); //OVERALL REGISTERED COUNT
						Long votersCount = vtrMap.get((Long)name[0]);
						
						infoVo.setOverAllRegCount(registerdCount);
						
						if(votersCount!=null){
							Long targetCadre = 0l;
							if(stateId == 1){
								if(((votersCount * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014)<noOfDays){
									targetCadre = ((votersCount * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014);
								}else{
									targetCadre =  (((votersCount * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014)/noOfDays)*tillDays;
								}
							}else{
								if(((votersCount * IConstants.TARGET_CADRE_TG) / IConstants.TG_VOTERS_2014)<noOfDays){
									targetCadre = ((votersCount * IConstants.TARGET_CADRE_TG) / IConstants.TG_VOTERS_2014);
								}else{
									targetCadre =  (((votersCount * IConstants.TARGET_CADRE_TG) / IConstants.TG_VOTERS_2014)/noOfDays)*tillDays;
								}
							}
							
							infoVo.setTargetCadre(targetCadre);
							infoVo.setTotalVoters(votersCount);
						}
						
						  String percentage ="0.0";
					
						  /*if(infoVo.getTargetCadre()!=null && infoVo.getTargetCadre()>0){
							  percentage = (new BigDecimal(count_2014*(100.0)/infoVo.getTargetCadre().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						  }*/
						  if(infoVo.getTargetCadre()!=null && infoVo.getTargetCadre()>0 && infoVo.getOverAllRegCount()!=null){
							  percentage = (new BigDecimal(infoVo.getOverAllRegCount()*(100.0)/infoVo.getTargetCadre().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						  }
						  float fl_perc = Float.parseFloat(percentage);
						  if(fl_perc>=120f){
							  infoVo.setColorStatus("Best");
						  }else if(fl_perc>=100f && fl_perc<120f){
							  infoVo.setColorStatus("Good");
						  }else if(fl_perc>=75f && fl_perc<100f){
							  infoVo.setColorStatus("Ok");
						  }else if(fl_perc>=50f && fl_perc<75f){
							  infoVo.setColorStatus("Poor");
						  }else {
							  infoVo.setColorStatus("Worst");
						  }
						  
						  infoVo.setRegPercent(percentage);
						  if(type.equalsIgnoreCase("booth")){
							  if(name[2] != null){
							    infoVo.setParliament(name[2].toString());
							  }else{
								infoVo.setParliament("");
							  }
							  if(name[3] != null){
							    infoVo.setState(name[3].toString());
							  }else{
								infoVo.setState("");
							  }
							  if(name[4] != null){
							    infoVo.setFromDate(name[4].toString());
							  }else{
								infoVo.setFromDate("");
							  }
						  }
					}
					
					if(yearMap != null)	{
						
						
						
						
						cadreCount2012 = cadreCount2012 + Long.valueOf(yearMap.get(2012L) != null ? yearMap.get(2012L).toString().trim():"0");
						cadreCount2014 = cadreCount2014 + Long.valueOf(yearMap.get(2014L) != null ? yearMap.get(2014L).toString().trim():"0");
				
						yearWiseTotalCadreMap.put(2014L,cadreCount2014);
						yearWiseTotalCadreMap.put(2012L,cadreCount2012);
				
						infoVo.setApCount(yearMap.get(2014l));
						infoVo.setTgCount(yearMap.get(2012l));
						if(type.equals("assembly")){
							String reqType = locationType.get((Long)name[0]);
							if(reqType != null && !reqType.equalsIgnoreCase("URBAN") ){
								infoVo.setName("True");
								infoVo.setArea("True");
							}
						}else if(type.equals("mandal")){
							String reqType = locationType.get((Long)name[0]);
							if(reqType != null && reqType.equalsIgnoreCase("mandal") ){
								infoVo.setArea("True");
							}
						}
						if((type.equals("assembly") || type.equals("district")) && infoVo.getApCount() != null && infoVo.getApCount().longValue() > 0 && infoVo.getTgCount() != null && infoVo.getTgCount().longValue() > 0 ){
							infoVo.setName(new BigDecimal(infoVo.getApCount()*(100.0)/infoVo.getTgCount().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
						}
			
					}
					returnList.add(infoVo);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}

		if(reqOthers && constituencyId != null && constituencyId != 0L && ids.size() > 1)
		{

		List<Object[]> enrollmentYearWiseCadreCount = tdpCadreDAO.getEnrollmentYearWiseDetails(constituencyId,fromDate,toDate,2014L);
		enrollmentYearWiseCadreCount.addAll( tdpCadreDAO.getEnrollmentYearWiseDetails(constituencyId,null,null,2012L));

		if(enrollmentYearWiseCadreCount != null && enrollmentYearWiseCadreCount.size()>0 && yearWiseTotalCadreMap != null && yearWiseTotalCadreMap.size()>0)
		{
		Long totalCount2012 = 0L;
		Long actualCount2012 = 0L;

		Long totalCount2014 = 0L;
		Long actualCount2014 = 0L;

		for (Object[] cadre : enrollmentYearWiseCadreCount)
		{
		if(cadre[0] != null)
		{
		Long enrollmentYear = Long.valueOf(cadre[0].toString());

		if(enrollmentYear.longValue() == 2014L)
		{
		totalCount2014 = cadre[1] != null ? Long.valueOf(cadre[1].toString()):0L;
		actualCount2014 = yearWiseTotalCadreMap.get(2014L) != null ? yearWiseTotalCadreMap.get(2014L):0L;
		}

		if(enrollmentYear.longValue() == 2012L)
		{
		totalCount2012 = cadre[1] != null ? Long.valueOf(cadre[1].toString()):0L;
		actualCount2012 = yearWiseTotalCadreMap.get(2012L) != null ? yearWiseTotalCadreMap.get(2012L):0L;
		}

		}
		}

		infoVo = new CadreRegisterInfo();
		 
		infoVo.setParliament("");
		infoVo.setState("");
		infoVo.setFromDate("");
			  
		infoVo.setLocation(" Others ");
		Long count2014 = totalCount2014 - actualCount2014;
		Long count2012 = totalCount2012 - actualCount2012;
		if(count2014<0){
			infoVo.setApCount(0l);
		}else{
			infoVo.setApCount( count2014 );
		}
		infoVo.setTgCount( count2012 );
		infoVo.setArea(" - ");
		if((count2014 != null && count2014 != 0L) || (count2012 != null && count2012 != 0L))
		{
		// infoVo.setName(new BigDecimal((double)count2014*(100.0)/(double)count2012).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		returnList.add(0,infoVo);
		}
		}
		}


		}catch(Exception e){
		LOG.error("Exception rised in getLocationWiseRegistrationInfo",e);
		}
		Collections.sort(returnList,locationSort);
		if(userCountValue!=null){
			if(returnList!=null && returnList.size()>0)
			{
				for (CadreRegisterInfo cadreRegisterInfo : returnList)
				{
					if(cadreRegisterInfo != null && cadreRegisterInfo.getApCount() != null)
					{
						if(cadreRegisterInfo.getApCount() < userCountValue)
						{
							returnList1.add(cadreRegisterInfo);
						}
					}
					
				}
				return returnList1;
			}
		}

		return returnList;
		}


	public  Comparator<CadreRegisterInfo> locationSort = new Comparator<CadreRegisterInfo>(){
				  
	  public int compare(CadreRegisterInfo vo1, CadreRegisterInfo vo2)
		{
		   return vo1.getLocation().compareTo(vo2.getLocation());
		}
	};
	
	public List<CadreRegisterInfo> getCastGroupWiseCadreCount(Long constituencyId,String type){
		LinkedHashMap<Long,CadreRegisterInfo> casteMap = new LinkedHashMap<Long,CadreRegisterInfo>();
	 try{
		List<Object[]> countsList = tdpCadreDAO.getCasteGroupTotalCount(constituencyId, type);
		Long totalCount2014 = null;
		Long totalCount2012 = null;
		for(Object[] counts:countsList){
			if(counts[1] != null){
				if(((Long)counts[1]).longValue() == 2012l){
					totalCount2012 = (Long)counts[0];
				}else if(((Long)counts[1]).longValue() == 2014l){
					totalCount2014 = (Long)counts[0];
				}
			}
		}
		List<Object[]> casteInfoList = tdpCadreDAO.getCasteGroupWiseCadreCountExcludingMinorities(constituencyId,type);
		//0 count,1 year,2 casteGroupId ,3 casteGroup
		
		List<Object[]> minList = tdpCadreDAO.getCadreCountInMinorities(constituencyId,type);
		
		if(minList != null && minList.size() > 0)
		{
			for(Object[] params : minList)
			{
				Object[] objArr = {params[0],params[1],Long.parseLong("99"),"Minority"};
				casteInfoList.add(objArr);
			}
		}
		
		for(Object[] caste:casteInfoList){
			
			CadreRegisterInfo casteVo = casteMap.get((Long)caste[2]);
			if(casteVo == null){
				casteVo = new CadreRegisterInfo();
				casteVo.setName(caste[3].toString());
				casteVo.setApCount(0l);//2014 total count
				casteVo.setTgCount(0l);//2012 total count
				casteVo.setPercentStr("0");//2014 perc
				casteVo.setArea("0");//2012 perc
				//casteVo.setDate(caste[4].toString());//casteGroup
				casteMap.put((Long)caste[2],casteVo);
			}
			if(((Long)caste[1]).longValue() == 2014l){
				casteVo.setApCount((Long)caste[0]);
				if(totalCount2014 != null && totalCount2014.longValue() > 0){
					casteVo.setPercentStr(new BigDecimal((casteVo.getApCount().doubleValue() / totalCount2014.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
			}else if(((Long)caste[1]).longValue() == 2012l){
				casteVo.setTgCount((Long)caste[0]);
				if(totalCount2012 != null && totalCount2012.longValue() > 0){
					casteVo.setArea(new BigDecimal((casteVo.getTgCount().doubleValue() / totalCount2012.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
			}
			
		}
	 }catch(Exception e){
		 LOG.error("Exception rised in getCastGroupWiseCadreCount",e);
	 }
		return new ArrayList<CadreRegisterInfo>(casteMap.values());
	}
	public CadreRegisterInfo getRegisteredDetailsByLocation(String locationType,List<Long> locationIds,int startIndex,int maxIndex,String orderBy,String orderType){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		CadreRegisterInfo returnVO = new CadreRegisterInfo();
		
	  try{
		//0 id,1 image,2name,3relative,4mobile,5partNo,6panchayat
		List<Object[]> cadres = null;
		CadreRegisterInfo cadreVo = null;
		Long totalCount = null;
		if(locationType != null && locationType.equalsIgnoreCase("booth")){
			cadres = tdpCadreDAO.getBoothWiseCadreInfo(locationIds,startIndex,maxIndex,orderBy,orderType);
			totalCount = tdpCadreDAO.getBoothWiseCadreInfoCount(locationIds);
		}else if(locationType != null && locationType.equalsIgnoreCase("panchayat")){
			List<Long> boothIds = new ArrayList<Long>();
			List<Long> panchayatIds = new ArrayList<Long>();
			List<Long> localBodyIds = new ArrayList<Long>();
			List<Long> constiIds = new ArrayList<Long>();
			for(Long id:locationIds){
				if(id != null && id.longValue() > 0){
					if(id.toString().substring(0,1).trim().equalsIgnoreCase("1")){
						panchayatIds.add(Long.valueOf(id.toString().substring(1)));
					}else if(id.toString().substring(0,1).trim().equalsIgnoreCase("2")){
						localBodyIds.add(Long.valueOf(id.toString().substring(1)));
					}else if(id.toString().substring(0,1).trim().equalsIgnoreCase("3")){
						constiIds.add(Long.valueOf(id.toString().substring(1)));
					}
				}
			}
			List<Long> boothsList = new ArrayList<Long>();
			if(panchayatIds.size() > 0){
				boothIds.addAll(boothDAO.getAllBoothIdsInPanchayat(panchayatIds, IConstants.VOTER_DATA_PUBLICATION_ID));
			}
			if(localBodyIds.size() > 0){
				boothIds.addAll(boothDAO.getAllBoothIdsInLocalBodies(localBodyIds,IConstants.VOTER_DATA_PUBLICATION_ID));
			}
            if(constiIds.size() > 0){
            	boothIds.addAll(boothDAO.getAllBoothIdsByConsti(constiIds.get(0),IConstants.VOTER_DATA_PUBLICATION_ID));
			}
			/*cadres = tdpCadreDAO.getPanchayatWiseCadreInfo(locationIds,startIndex,maxIndex,orderBy,orderType);
			totalCount = tdpCadreDAO.getPanchayatWiseCadreInfoCount(locationIds);*/
            cadres = tdpCadreDAO.getBoothWiseCadreInfo(boothIds,startIndex,maxIndex,orderBy,orderType);
			totalCount = tdpCadreDAO.getBoothWiseCadreInfoCount(boothIds);
		}
		if(cadres != null && cadres.size() > 0){
			for(Object[] cadre:cadres){
				cadreVo = new CadreRegisterInfo();
				cadreVo.setId((Long)cadre[0]);//id
				if(cadre[1] != null){
				   cadreVo.setDate(cadre[1].toString());//image
				}
				if(cadre[2] != null){
				  cadreVo.setName(cadre[2].toString());//name
				}else{
					cadreVo.setName("");
				}
				if(cadre[3] != null){
					   cadreVo.setPercentStr(cadre[3].toString());//relative
				}else{
				   cadreVo.setPercentStr("");
				}
				if(cadre[4] != null){
				   cadreVo.setNumber(cadre[4].toString());//mobile
				}else{
				   cadreVo.setNumber("");
				}
				if(cadre[5] != null){
				   cadreVo.setLocation(cadre[5].toString());//booth
				}else{
				   cadreVo.setLocation("");
				}
				if(cadre[6] != null){
				   cadreVo.setArea(cadre[6].toString());//panchayat
				}else{
				   cadreVo.setArea("");
				}
				
				if(cadre[7] != null){
					if(cadre[7].toString().trim().length() > 8){
						cadreVo.setMemberShipNo(cadre[7].toString().trim().substring(cadre[7].toString().trim().length()-8));
					}else{
						cadreVo.setMemberShipNo(cadre[7].toString());
					}
					//cadreVo.setMemberShipNo(cadre[7].toString());//Membership No
				}else{
					cadreVo.setMemberShipNo("");
				}
				
				returnList.add(cadreVo);
			}
		}
		
		returnVO.setCadreRegisterInfoList(returnList);
		returnVO.setTotalCount(totalCount);
	  }catch(Exception e){
		  LOG.error("Exception rised in getRegisteredDetailsByLocation",e);
	  }
		return returnVO;
	}
	
	public List<CadreRegisterInfo> getDataForSubLocations(String fromLocation,Long fromLocationId,String toLocation,String fromDateStr, String toDateStr,Long constituencyId){
		List<Long> ids = new ArrayList<Long>();
		String type = "";
		try{
			if(fromLocation.equalsIgnoreCase("state")){
				if(toLocation.equalsIgnoreCase("district")){
					type ="district";
					ids = districtDAO.getDistrictsInAState(fromLocationId);
				}else if(toLocation.equalsIgnoreCase("constituency")){
					type ="assembly";
					ids = constituencyDAO.getConstituenciesInAState(fromLocationId);
				}
			}else if(fromLocation.equalsIgnoreCase("district")){
	            if(toLocation.equalsIgnoreCase("constituency")){
	            	type ="assembly";
	            	ids = constituencyDAO.getConstituenciesInADistrict(fromLocationId);
				}
			}else if(fromLocation.equalsIgnoreCase("constituency")){
	            if(toLocation.equalsIgnoreCase("mandal")){
	            	type = "mandal";
	            	List<Long> mandalIds = boothDAO.getAllTehsilsInAConstituency(fromLocationId, IConstants.VOTER_DATA_PUBLICATION_ID);
	            	List<Long> lblIds = boothDAO.getLBSInAConstituency(fromLocationId, IConstants.VOTER_DATA_PUBLICATION_ID);
	            	for(Long id:lblIds){
	            		ids.add(Long.valueOf("1"+id));
	            	}
	            	for(Long id:mandalIds){
	            		ids.add(Long.valueOf("2"+id));
	            	}
				}else if(toLocation.equalsIgnoreCase("panchayat")){
					type = "panchayat";
					ids = boothDAO.getAllPanchayatsInAConstituency(fromLocationId, IConstants.VOTER_DATA_PUBLICATION_ID);
				}else if(toLocation.equalsIgnoreCase("booth")){
					type = "booth";
					ids = boothDAO.getAllBoothsInAConstituency(fromLocationId, IConstants.VOTER_DATA_PUBLICATION_ID);
				}
			}else if(fromLocation.equalsIgnoreCase("mandal")){
				if(toLocation.equalsIgnoreCase("panchayat")){
					type = "panchayat";
					ids = boothDAO.getAllPanchayatsInAMandal(fromLocationId,IConstants.VOTER_DATA_PUBLICATION_ID,constituencyId);
				}else if(toLocation.equalsIgnoreCase("booth")){
					type = "booth";
					ids = boothDAO.getAllBoothsInAMandal(fromLocationId,IConstants.VOTER_DATA_PUBLICATION_ID,constituencyId);
				}
			}else if(fromLocation.equalsIgnoreCase("municipality")){
				if(toLocation.equalsIgnoreCase("booth")){
					type = "booth";
					ids = boothDAO.getAllBoothIdsInALocalBody(fromLocationId,IConstants.VOTER_DATA_PUBLICATION_ID,constituencyId);
				}
			}else if(fromLocation.equalsIgnoreCase("panchayat")){
				if(toLocation.equalsIgnoreCase("booth")){
					type = "booth";
					List<Long> panchayatIds = new ArrayList<Long>();
					panchayatIds.add(fromLocationId);
					ids = boothDAO.getAllBoothIdsInPanchayat(panchayatIds,IConstants.VOTER_DATA_PUBLICATION_ID);
				}
			}
			if(type.trim().length() == 0 || ids.size() == 0){
				return new ArrayList<CadreRegisterInfo>();
			}
		}catch(Exception e){
			LOG.error("Exception rised in getDataForSubLocations",e);
		}
		return getLocationWiseRegistrationInfo1(ids,type,fromDateStr,toDateStr,false,null);
	}
	
	public AppDbDataVO getAllVersionsOfAnApp(String appName,Double currentVerson,boolean includeTest){
		AppDbDataVO returnVo = new AppDbDataVO();
		try{
			List<Double> versions = appDbUpdateDAO.getAllVersionsOfAnApp(appName, currentVerson, includeTest);
			if(versions.size() > 0){
				returnVo.setStatus("Updates Available");
				returnVo.setVersions(versions);
			}else{
				returnVo.setStatus("No Updates Available");
				returnVo.setVersions(new ArrayList<Double>());
			}
		}catch(Exception e){
			LOG.error("Exception rised in getAllVersionsOfAnApp",e);
			returnVo.setStatus("Try Again Later");
		}
		return returnVo;
	}
	
	public AppDbDataVO getAllUpdatesByVersion(String appName,Double version){
		AppDbDataVO returnVo = new AppDbDataVO();
		try{
			List<Object[]> scriptList = appDbUpdateDAO.getAllUpdatesByVersion(appName, version);
			if(scriptList.size() > 0){
				returnVo.setStatus("Data Available");
				List<AppDbDataVO> dataList = new ArrayList<AppDbDataVO>();
				for(Object[] script:scriptList){
					 AppDbDataVO data = new AppDbDataVO();
					 data.setId((Long)script[0]);
					 data.setOrder((Long)script[1]);
					 data.setScript(script[2].toString());
					 dataList.add(data);
				}
				returnVo.setDataList(dataList);
			}else{
				returnVo.setStatus("Data Not Available");
				returnVo.setDataList(new ArrayList<AppDbDataVO>());
			}
		}catch(Exception e){
			LOG.error("Exception rised in getAllVersionsOfAnApp",e);
			returnVo.setStatus("Try Again Later");
		}
		return returnVo;
	}
	
	/**
	 * Service Method will call DAO to fetch Survey Working Member details
	 * @param hours
	 * @return
	 */
	public CadreRegisterInfo getWorkingMembersDetails(String hours)
	{
		CadreRegisterInfo info = new CadreRegisterInfo();
		List<CadreRegisterInfo> infoLst = new ArrayList<CadreRegisterInfo>();
		List<Object[]> resultsLst = null;
		try{
			Date date = dateService.getCurrentDateAndTime();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			
			Date hourBack = null;
			Long count  = 0L;
			
			if(hours != null && hours.trim().length()>0 && !hours.equalsIgnoreCase("0"))
			{
				int hourCount = Integer.valueOf(hours);						
				cal.add(Calendar.HOUR, -hourCount);
				hourBack = cal.getTime();
				
				//count = tdpCadreDAO.getLastHoursWorkingMemberCount(date,hourBack);
				resultsLst = tdpCadreDAO.getLastHoursWorkingMembersDetails(date, hourBack);
			}
			else
			{
				//count = tdpCadreDAO.getWorkingMembersCount(date);
				resultsLst = tdpCadreDAO.getWorkingMembersDetails(date);
			}
			
			if(resultsLst != null && resultsLst.size() > 0){
				count = new Long(resultsLst.size());
				
				for(Object[] res:resultsLst){
					
					CadreRegisterInfo sInfo = new CadreRegisterInfo();
					sInfo.setId((Long)res[0]);
					sInfo.setName(res[2].toString());
					//latitude
					sInfo.setArea(res[3].toString());
					//longitude
					sInfo.setLocation(res[4].toString());					
					infoLst.add(sInfo);
				}
			}
			
			info.setTotalCount(count);
			info.setAllDetailsList(infoLst);
			

		}catch(Exception e){
			LOG.error("Exception rised in getWorkingMembersInfo",e);
		}
		
		return info;
	}

		
	public CadreRegisterInfo getRegisteredInfo(Long locationId,String locationType,int startIndex,int maxIndex){
		CadreRegisterInfo returnVo = new CadreRegisterInfo();
		List<CadreRegisterInfo> resultList = new ArrayList<CadreRegisterInfo>();
		CadreRegisterInfo vo = null;
		try{
			//0image,1name,2relative,3constituency,4age,5dataSourceType
		    List<Object[]> cadreList = tdpCadreDAO.getCadreInfoDetails(locationId,locationType,startIndex,maxIndex);
		    Long count = tdpCadreDAO.getCadreInfoDetailsCount(locationId,locationType);
		    returnVo.setTotalCount(count);
		    for(Object[] cadre:cadreList){
		    	vo = new CadreRegisterInfo(); 
		    	if(cadre[1] != null){
		    	   vo.setName(cadre[1].toString());
		    	}else{
		    		 vo.setName("-");
		    	}
		    	if(cadre[4] != null){
		    	  vo.setNumber(cadre[4].toString());//age
		    	}else{
		    		 vo.setNumber("-");//age
		    	}
		    	if(cadre[2] != null){
		    	  vo.setArea(cadre[2].toString());//2relative
		    	}else{
	    		  vo.setArea("-");//2relative
		    	}
		    	if(cadre[3] != null){
			      vo.setLocation(cadre[3].toString());//3constituency
			    }else{
	    		  vo.setLocation("-");//3constituency
		    	}
		    	if(cadre[0] != null){
		    	 vo.setMemberShipNo("images/cadre_images/"+cadre[0].toString());//image
		    	}
		    	if(cadre[5] != null){
			    	 vo.setDate(cadre[5].toString());//5datasource
			    }else{
			    	 vo.setDate("-");//5datasource
			    }
		    	resultList.add(vo);
		    }
		}catch(Exception e){
			LOG.error("Exception rised in getRegisteredInfo",e);
		}
		returnVo.setInfoList(resultList);
		return returnVo;
	}
	
	public String getStateBasedOnLocation(String AccessType,String accessValue){
		
		String state = "";
		
		if(AccessType.equalsIgnoreCase("STATE")){
			state = "both";
		}else{
			Long dist = null;
			if(AccessType.equalsIgnoreCase("MLA")){
				List<Long> distIds = constituencyDAO.getDistrictIdByConstituencyId(Long.valueOf(accessValue));
				if(distIds!=null){
					dist = distIds.get(0);
				}
				
			}
			if(AccessType.equalsIgnoreCase("MP")){
				List<Long> distIds = delimitationConstituencyAssemblyDetailsDAO.findDistrictsBYParliament(Long.valueOf(accessValue));
				if(distIds!=null){
					dist = distIds.get(0);
				}
				
			}
			if(AccessType.equalsIgnoreCase("DISTRICT")){
				dist = Long.valueOf(accessValue);
			}
			
			if(dist!=null && dist>10){
				state=  "AP";
			}else{
				state = "TS";
			}
			
			
		}
		
		return state;
	}
	public List<CadreBasicInformationVO> getConstituencySurveyUsers(Long constituencyId)
	{
		List<CadreBasicInformationVO> resultlist = new ArrayList<CadreBasicInformationVO>();
		try{
			List<Object[]> list = cadreSurveyUserAssignDetailsDAO.getCadreSurveyUsers(constituencyId);
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					CadreBasicInformationVO vo = new CadreBasicInformationVO();
					vo.setUserId((Long)params[0]);
					vo.setName(params[1].toString());
					resultlist.add(vo);
				}
			}
		}
		catch (Exception e) {
			LOG.error("Exception Occured in getCadreSurveyUsers() method", e); 
		}
		return resultlist;
	}
	
	public List<CadreRegisterInfo> getAssignedUsersForCadresurveyUser(Long constituencyId,Long userId)
	{
		List<CadreRegisterInfo>  returnList = new ArrayList<CadreRegisterInfo>();
	try{
		List<Object[]> list = cadreSurveyUserAssignDetailsDAO.getUsersByConstituencyAndUserId(constituencyId,userId);
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
			{
				CadreRegisterInfo vo = new CadreRegisterInfo();
				vo.setId((Long)params[0]);
				vo.setName(params[1].toString());
				vo.setNumber(params[2] != null? params[2].toString() : "");
				vo.setFromDate(params[3] != null ? params[3].toString() : "");
				vo.setDate(params[4] != null ? params[4].toString() : "");
				returnList.add(vo);
			}
		}
		
	}
	catch (Exception e) {
		LOG.error("Exception Occured in getUsersList() method", e);
	}
	return returnList;
	}
	public ResultStatus saveCadreSurveyUserAssignInfo(CadreRegisterInfo vo)
	{
		ResultStatus result = new ResultStatus();
	
		DateUtilService dateUtilService = new DateUtilService();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		try{
			Date fromDate = sdf.parse(vo.getFromDate());
			List avilability = cadreSurveyUserAssigneeDAO.checkUserExists(vo.getId(),fromDate);
			if(avilability != null && avilability.size() > 0)
			   result.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			else
			{
				// update last user endTime 
				Long previousUser = cadreSurveyUserAssigneeDAO.getLatestUserByCadreSurveyUser(vo.getId());
				if(previousUser != null)
				{
				CadreSurveyUserAssignee updateUser = cadreSurveyUserAssigneeDAO.get(previousUser);
				Calendar fromCalendar = Calendar.getInstance();
				fromCalendar.setTime(fromDate);
				fromCalendar.set(Calendar.DAY_OF_MONTH,  fromCalendar.get(Calendar.DAY_OF_MONTH)-1);
				updateUser.setToDate(fromCalendar.getTime());
				updateUser.setIsDeleted("Y");
				cadreSurveyUserAssigneeDAO.save(updateUser);
				}
				
				// add user to cadreSurveyUser
				CadreSurveyUserAssignee cadreSurveyUserAssignee = new CadreSurveyUserAssignee();
				cadreSurveyUserAssignee.setName(vo.getName());
				cadreSurveyUserAssignee.setMobileNo(vo.getNumber());
				cadreSurveyUserAssignee.setCadreSurveyUser(cadreSurveyUserDAO.get(vo.getId()));
				cadreSurveyUserAssignee.setIsDeleted("N");
				cadreSurveyUserAssignee.setFromDate(fromDate);
				cadreSurveyUserAssigneeDAO.save(cadreSurveyUserAssignee);
				result.setResultCode(ResultCodeMapper.SUCCESS);
				// update CadreSurvey User
				CadreSurveyUser user = cadreSurveyUserDAO.get(vo.getId());
				user.setName(vo.getName());
				user.setMobileNo(vo.getNumber());
				user.setUpdatedTime(fromDate);
				cadreSurveyUserDAO.save(user);
			}
		}
		catch (Exception e) {
			LOG.error("Exception Occured in getCadreSurveyUsers() method", e);
			result.setResultCode(ResultCodeMapper.FAILURE);
		}
		return result;
	}

	
	public List<CadreRegisterInfo> getSlowUserDetails(Long locationType,List<Long> locationIds,Date fromDate,Date toDate,Long recordsCount){
		List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
		CadreRegisterInfo vo = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat timeFormate1 = new SimpleDateFormat("HH:mm");
		SimpleDateFormat timeFormate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		try{
			List<Long> surveyUserIds = new ArrayList<Long>();
			List<Date> datesList = new ArrayList<Date>();
			Map<Long,String> nameMap = new HashMap<Long, String>();
			Map<Long,String> tabMap = new HashMap<Long, String>();
			Map<Long,Map<Date,CadreRegisterInfo>> userMap = new HashMap<Long,Map<Date,CadreRegisterInfo>>();//Map<userId,Map<Date,info>>
			Map<Date,CadreRegisterInfo> dateMap = new HashMap<Date,CadreRegisterInfo>();//Map<Date,info>
			Map<Long,String> userNames = new HashMap<Long,String>();
			Map<Long,CadreRegisterInfo> mobileNos = new HashMap<Long,CadreRegisterInfo>();
			//0 count,1 name,2 min,3 max,4 date,5 id,6 name
			//List<Object[]> dataCollectedInfo = tdpCadreDAO.getCandidateDataCollectionInfo(locationType,locationIds,fromDate, toDate);
			String parlimentName = null;			
			Map<String,String> parliamentForAssemblyMap = new TreeMap<String, String>();
			List<Object[]> locationsList =  delimitationConstituencyAssemblyDetailsDAO.getPcListByRegion(0L);							
				
			List<SurveyTransactionVO> locationIdsList = new ArrayList<SurveyTransactionVO>();
			if(locationsList != null && locationsList.size()>0)
			{
				for (Object[] param : locationsList)
				{
					SurveyTransactionVO surveyTransactionVO = new SurveyTransactionVO();
					surveyTransactionVO.setId(param[0] != null ? Long.valueOf(param[0].toString()) :0L);
					surveyTransactionVO.setName(param[1] != null ? param[1].toString() :"");
					
					locationIdsList.add(surveyTransactionVO);
				}
			}
			List<Long> assemblyIds = new ArrayList<Long>(0);			
			if(locationIdsList != null && locationIdsList.size()>0)
			{
				for (SurveyTransactionVO surveyTransctionVO : locationIdsList) 
				{
					List<Long> locationIdList = new ArrayList<Long>();
					locationIdList.add(surveyTransctionVO.getId());
					
					locationsList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesListForAListOfParliamentConstituency(locationIdList);
											
					if(locationsList != null && locationsList.size()>0)
					{
						for (Object[] param : locationsList)
						{
							assemblyIds.add(param[0] != null ? Long.valueOf(param[0].toString().trim()):0L );
							parliamentForAssemblyMap.put(param[1] != null ? param[1].toString().trim() :"", surveyTransctionVO.getName());
						}
					}	
					
				}
			}			
			List<Object[]> dataCollectedInfo = null;
			List<Long> assemblyIdsList = new ArrayList<Long>(0);
			 if(locationType.longValue() == 4L )
			 {
				 Long parliamentID = locationIds.get(0) != null? locationIds.get(0):0L;
				 try {
						 if(parliamentID.longValue() >0 && locationIds.size() == 1 )
						 {
							 List<DelimitationConstituency> delimitationConstituencyList = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyID(parliamentID);
								DelimitationConstituency parliamentConstituency = delimitationConstituencyList.get(0);
								parlimentName = parliamentConstituency.getConstituency().getName();
								assemblyIdsList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliament(parliamentID);
							
								
						 }
						 else
						 {
							// assemblyIdsList.addAll(assemblyIds);
							 if(locationIds != null && locationIds.size()>0)
							 {
								 for (Long parliamentId : locationIds) 
								 {
										assemblyIdsList.addAll(delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliament(parliamentId));
								 }
							 }
						 }
						 
					} catch (Exception e) {}
				 
				 locationIds.clear();
				 locationIds.addAll(assemblyIdsList);
					
				 dataCollectedInfo = tdpCadreDAO.getCandidateDataCollectionInfo(3L,assemblyIdsList,fromDate, toDate);
			 }
			 else
			 {
				 dataCollectedInfo = tdpCadreDAO.getCandidateDataCollectionInfo(locationType,locationIds,fromDate, toDate);
			 }
			for(Object[] data:dataCollectedInfo){
				if(!datesList.contains((Date)data[4])){
					datesList.add((Date)data[4]);
				}
				userNames.put((Long)data[5], data[1].toString());
				dateMap = userMap.get((Long)data[5]);
				if(dateMap == null){
					dateMap = new HashMap<Date,CadreRegisterInfo>();
					userMap.put((Long)data[5],dateMap);
					nameMap.put((Long)data[5], data[6] != null ? data[6].toString() : "");
					if(!surveyUserIds.contains((Long)data[5]))
					surveyUserIds.add((Long)data[5]);
				}
				vo = new CadreRegisterInfo() ;
			
				vo.setArea(convertTimeTo12HrsFormat(timeFormate1.format((Date)data[2])));
				vo.setLocation(convertTimeTo12HrsFormat(timeFormate1.format((Date)data[3])));
				//vo.setTotalCount((Long)data[0]);
				//vo.setAmount(vo.getTotalCount() * 100);
				
				vo.setTotalCount((Long)data[0]);			
				vo.setAmount(convertTimeToMinutesFormat(timeFormate.parse(data[2].toString()),timeFormate.parse(data[3].toString())));
				vo.setTgCount(vo.getAmount() / recordsCount);
				vo.setAvgTime(vo.getAmount()/ vo.getTotalCount());
					dateMap.put((Date)data[4], vo);
			}
			if(surveyUserIds != null && surveyUserIds.size() > 0)
			{
			List<Object[]> tabNos = cadreSurveyUserAssignDetailsDAO.getTabNos(surveyUserIds);
			if(tabNos != null && tabNos.size() > 0)
				for(Object[] params : tabNos)
				{
					tabMap.put((Long)params[0], params[1] != null ?  params[1].toString() : "");
				}
				
			}
			int count = 0;
			if(userNames.size() > 0){
				//0 userId,1mobile,2constituencyId,3constiname,4distiictId,5districtName
				List<Object[]> mobileNosList = cadreSurveyUserDAO.getUserMobileNos(userNames.keySet());
				for(Object[] mobileNo:mobileNosList){
					CadreRegisterInfo userData = new CadreRegisterInfo();
				 if(mobileNo[1] != null){
					userData.setArea(mobileNo[1].toString());//mobileNo
				 }
				 if(((Long)mobileNo[4]).longValue() < 11){
				     userData.setLocation("Telangana");//state
				 }else{
					 userData.setLocation("AndhraPradesh");//state
				 }
				 userData.setNumber(mobileNo[5].toString());//district
				 userData.setMemberShipNo(mobileNo[3].toString());//constituency
				 userData.setPercentStr(parliamentForAssemblyMap.get(mobileNo[3].toString()));//parliament
				 mobileNos.put((Long)mobileNo[0], userData);
				}
			}
			for(Long key:userMap.keySet()){
				dateMap = userMap.get(key);
				vo = new CadreRegisterInfo();
				vo.setName(userNames.get(key));
				
				//vo.setUname(unameMap.get(key) != null ? unameMap.get(key).toString() : "");
				CadreRegisterInfo userData = mobileNos.get(key);
				if(userData != null){
				    vo.setArea(userData.getArea());//mobileNo
				    vo.setLocation(userData.getLocation());//state
				    vo.setNumber(userData.getNumber());//district
				    vo.setMemberShipNo(userData.getMemberShipNo());//constituency
				    vo.setPercentStr(userData.getPercentStr());
				    vo.setUname(nameMap.get(key));
				    vo.setTabNo(tabMap.get(key));
				}
				List<CadreRegisterInfo> daysList = new ArrayList<CadreRegisterInfo>();
				for(Date date:datesList){
					if(dateMap.get(date) != null){
						CadreRegisterInfo day = dateMap.get(date);
					    if(count == 0){
						  day.setDate(sdf.format(date));
					    }
						daysList.add(day);
					}else{
						CadreRegisterInfo day = new CadreRegisterInfo();
						if(count == 0){
						   day.setDate(sdf.format(date));
						 }
						daysList.add(day);
					}
				}
				vo.setInfoList(daysList);
				if(vo.getInfoList() != null && vo.getInfoList().size() > 0)
				{
					Long count1 = 0l;
					
					for(CadreRegisterInfo vo3 : vo.getInfoList())
					{
						if(vo3.getTotalCount() != null)
						 count1 = count1 + vo3.getTotalCount();
					}
					vo.setTotalCount(count1);
					Long count2 = 0l;
					
					for(CadreRegisterInfo vo3 : vo.getInfoList())
					{
						if(vo3.getTgCount() != null)
						 count2 = count2 + vo3.getTgCount();
					}
					vo.setTotalAmount(count2);
				}
			
				returnList.add(vo);
				count++;
			}
			if(returnList!=null && returnList.size()>0){
				for(CadreRegisterInfo cd:returnList){
					List<CadreRegisterInfo> list  = cd.getInfoList();
					if(list!=null && list.size()>0){
						for(CadreRegisterInfo temp:list){
							if(!cd.isSlowUser()){
								if(temp.getTgCount()>temp.getTotalCount()){
									cd.setSlowUser(true);
								}
							}
						}
					}
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception rised in getCandidateDataCollectionInfo",e);
		}
		
		return returnList;
	}
	
	private Long convertTimeToMinutesFormat(Date startTime,Date endTime){
		
		Long totalmins = 0L;
		long diff = endTime.getTime() - startTime.getTime();

		//long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		
		if(diffHours >= 1){
			totalmins = (diffHours * 60) + diffMinutes;
		}
		else
			totalmins = diffMinutes;
	
		return totalmins;
	}
	
	public List<SurveyTransactionVO> getUserTrackingDetails(List<Object[]> userTrackList, List<SurveyTransactionVO> returnList,Map<Long,Long> assignedUsersMap,String type,Long stateTypeId,List<Long> areaIdsList,String FdateStr)
	{
		 List<SurveyTransactionVO> surveyTransactionVOList = null;
		final SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Map<String,List<SurveyTransactionVO>> userCountByLocationMap = new TreeMap<String, List<SurveyTransactionVO>>();
			Map<String,Long> assignCountByLocationMap = new TreeMap<String, Long>();
			List<String> datesSet = new ArrayList<String>();
			
			Map<String,String> parliamentForAssemblyMap = new TreeMap<String, String>();
			Map<String,Long> assemblyForAssemblyIdMap = new TreeMap<String, Long>();
			Map<String,Long> parliamentForParliamentMap = new TreeMap<String, Long>();
			List<Object[]> locationsList =  delimitationConstituencyAssemblyDetailsDAO.getPcListByRegion(stateTypeId);							
				
			List<SurveyTransactionVO> locationIdsList = new ArrayList<SurveyTransactionVO>();
			if(locationsList != null && locationsList.size()>0)
			{
				for (Object[] param : locationsList)
				{
					SurveyTransactionVO surveyTransactionVO = new SurveyTransactionVO();
					surveyTransactionVO.setId(param[0] != null ? Long.valueOf(param[0].toString()) :0L);
					surveyTransactionVO.setName(param[1] != null ? param[1].toString() :"");
					
					locationIdsList.add(surveyTransactionVO);
					parliamentForParliamentMap.put(surveyTransactionVO.getName(), surveyTransactionVO.getId());
				}
			}
			
			if(locationIdsList != null && locationIdsList.size()>0)
			{
				for (SurveyTransactionVO surveyTransctionVO : locationIdsList) 
				{
					List<Long> locationIdList = new ArrayList<Long>();
					locationIdList.add(surveyTransctionVO.getId());
					
					locationsList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesListForAListOfParliamentConstituency(locationIdList);
											
					if(locationsList != null && locationsList.size()>0)
					{
						for (Object[] param : locationsList)
						{
							assemblyForAssemblyIdMap.put(param[1] != null ? param[1].toString().trim() :"",param[0] != null ? Long.valueOf(param[0].toString().trim()):0L);
							parliamentForAssemblyMap.put(param[1] != null ? param[1].toString().trim() :"", surveyTransctionVO.getName());
						
						}
					}	
					
				}
			}
			
			List<Long> workingConstituencyList = new ArrayList<Long>();
			if(userTrackList != null && userTrackList.size()>0)
			{
				for (Object[] location : userTrackList) 
				{
					String date = format.format(format1.parse(location[2].toString().trim()));
					surveyTransactionVOList = new ArrayList<SurveyTransactionVO>();
					if(userCountByLocationMap.get(location[1].toString().trim()) != null)
					{
						surveyTransactionVOList = userCountByLocationMap.get(location[1].toString().trim());
					}
					
					SurveyTransactionVO surveyTransactionVO = new SurveyTransactionVO();
					
					surveyTransactionVO.setId(Long.valueOf(location[0].toString().trim()));
					surveyTransactionVO.setName(location[1].toString().trim());
					surveyTransactionVO.setLocationName(parliamentForAssemblyMap.get(surveyTransactionVO.getName()));
					Long totalAssignUsers = assignedUsersMap.get(Long.valueOf(location[0].toString().trim())) != null ? assignedUsersMap.get(Long.valueOf(location[0].toString().trim())):0L;
					Long workedUsers = location[3] != null ? Long.valueOf(location[3].toString().trim()):0L;
					Long notWorkedUsers = totalAssignUsers - workedUsers;
					
					surveyTransactionVO.setTeamSize(workedUsers);
					surveyTransactionVO.setIdleTeamSize(notWorkedUsers);
					surveyTransactionVO.setSurveyDate(date);
					surveyTransactionVOList.add(surveyTransactionVO);
					
					if(!datesSet.contains(date))
					{
						datesSet.add(date);
					}
					
					userCountByLocationMap.put(location[1].toString().trim(), surveyTransactionVOList);
					assignCountByLocationMap.put(location[1].toString().trim(), totalAssignUsers);
					if(!workingConstituencyList.contains(surveyTransactionVO.getId()))
					workingConstituencyList.add(surveyTransactionVO.getId());
				}
			}
			
			Collections.sort(datesSet, new Comparator<String>() {
				public int compare(String m1, String m2) {
			    	int value = 0;
			    	try {
			    		value =  format.parse(m2).compareTo(format.parse(m1));
					} catch (ParseException e) {}
			    	
			    	return value;
			    }
			});	
			
			
			if(areaIdsList != null && areaIdsList.size()>0)
			{
				List<Object[]> constituencyInfo = constituencyDAO.getConstituencyInfoByNotConstituencyIdList(areaIdsList,type);
				
				if(datesSet != null && datesSet.size() == 0)
				{
					surveyTransactionVOList = new ArrayList<SurveyTransactionVO>();
					datesSet.add(FdateStr);
				}
				
				if(constituencyInfo != null && constituencyInfo.size()>0)
				{
					for (Object[] constituency : constituencyInfo) 
					{
						if(datesSet != null && datesSet.size()>0)
						{
							for (String date : datesSet) {

								if(userCountByLocationMap.get(constituency[1].toString()) != null)
								{
									surveyTransactionVOList = userCountByLocationMap.get(constituency[1].toString().trim());
								}
								
								SurveyTransactionVO surveyTransactionVO = new SurveyTransactionVO();
								
								surveyTransactionVO.setId(Long.valueOf(constituency[0].toString().trim()));
								surveyTransactionVO.setName(constituency[1].toString().trim());
								surveyTransactionVO.setLocationName(parliamentForAssemblyMap.get(surveyTransactionVO.getName()));
								Long totalAssignUsers = assignedUsersMap.get(surveyTransactionVO.getId()) != null ?assignedUsersMap.get(surveyTransactionVO.getId()):0L ;
								Long workedUsers = 0L;
								Long notWorkedUsers = totalAssignUsers - workedUsers;
								
								surveyTransactionVO.setTeamSize(workedUsers);
								surveyTransactionVO.setIdleTeamSize(notWorkedUsers);
								surveyTransactionVO.setSurveyDate(date);
								surveyTransactionVOList.add(surveyTransactionVO);
								
								
								userCountByLocationMap.put(constituency[1].toString().trim(), surveyTransactionVOList);
								assignCountByLocationMap.put(constituency[1].toString().trim(), totalAssignUsers);
							}
						}
						
					}
				}
			}
			
			if(userCountByLocationMap != null && userCountByLocationMap.size()>0)
			{
				returnList = (returnList != null ? returnList :new ArrayList<SurveyTransactionVO>()) ;
								
				for (String constiteuncy : userCountByLocationMap.keySet()) 
				{
					List<SurveyTransactionVO> list = userCountByLocationMap.get(constiteuncy);
					List<SurveyTransactionVO> constituencyWiseList = new ArrayList<SurveyTransactionVO>();
					
					SurveyTransactionVO surveyTransactionVO = new SurveyTransactionVO();	
					surveyTransactionVO.setName(constiteuncy);
					surveyTransactionVO.setLocationName(parliamentForAssemblyMap.get(surveyTransactionVO.getName()));
					if(datesSet != null && datesSet.size()>0)
					{
						for (String date : datesSet)
						{
							SurveyTransactionVO survyTransactionVO = getMatchedVOForDate(list,date,constiteuncy);
							if(survyTransactionVO != null)
							{
								constituencyWiseList.add(survyTransactionVO);
							}
							else
							{
								survyTransactionVO = new SurveyTransactionVO();
								survyTransactionVO.setName(constiteuncy);
								if(type.equalsIgnoreCase("Parliament"))
								{
									survyTransactionVO.setId(parliamentForParliamentMap.get(constiteuncy));
								}
								else
								{
									survyTransactionVO.setId(assemblyForAssemblyIdMap.get(constiteuncy));
								}
								surveyTransactionVO.setLocationName(parliamentForAssemblyMap.get(surveyTransactionVO.getName()));
								survyTransactionVO.setSurveyDate(date);
								survyTransactionVO.setTeamSize(0L);
								survyTransactionVO.setIdleTeamSize(assignCountByLocationMap.get(constiteuncy));
								constituencyWiseList.add(survyTransactionVO);
							}
						}
					}
					
					surveyTransactionVO.setSurveyTransactionVOList(constituencyWiseList);
					returnList.add(surveyTransactionVO);
					
				}
			}
			
			
		} catch (Exception e) {
			LOG.error("Exception rised in getUserTrackingDetails() ",e);
		}
		return returnList;
	}
	
	public SurveyTransactionVO getMatchedVOForDate(List<SurveyTransactionVO> datesWiseReportList,String date,String locationName)
	{
		SurveyTransactionVO vo = null;
		try {
			
			if(datesWiseReportList != null && datesWiseReportList.size()>0)
			{
				for (SurveyTransactionVO surveyTransactionVO : datesWiseReportList) 
				{
					if(surveyTransactionVO.getSurveyDate().trim().equalsIgnoreCase(date.trim()) && surveyTransactionVO.getName().trim().equalsIgnoreCase(locationName))
					{
						return surveyTransactionVO;
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception rised in getMatchedVOForDate() ",e);
		}
		
		return vo;
	}
	
	//99999
	public List<SurveyTransactionVO> getLocationswiseUsersList(String areaId,String areaType, Long stateTypeId,String FdateStr, String TdateStr)
	{
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

		List<SurveyTransactionVO> surveyTransactionVOList = null;
		try {
			
			Date fromDate = format.parse(FdateStr);
			Date toDate = format.parse(TdateStr);
			
			String[] areaidsArr = areaId.split(",");
			List<Long> areaIdsList = new ArrayList<Long>(0);
			
			
				if(areaidsArr != null && areaidsArr.length > 0 )
				{
					for (String locationId : areaidsArr)
					{
						if(Long.valueOf(locationId) != 0L)
						{
							areaIdsList.add(Long.valueOf(locationId));
						}
					}
				}
			
			if(areaIdsList != null && areaIdsList.size() == 0)
			{
				List<Object[]> locationsList = constituencyDAO.getAllAssemblyConstituenciesByStateTypeId(stateTypeId,1L,null);
				
				if(locationsList != null && locationsList.size()>0)
				{
					for (Object[] locationId : locationsList) {
						areaIdsList.add(Long.valueOf(locationId[0] != null ? locationId[0].toString():"0"));
					}
				}
				
			}
			StringBuilder queryStr = new StringBuilder();
			StringBuilder assignUsersQuery = new StringBuilder();
			
			if(areaType.equalsIgnoreCase("district"))
			{
				
				queryStr.append(" select distinct model2.constituency.district.districtId, model2.constituency.district.districtName , date(model.surveyTime) , count( distinct model.insertedBy.cadreSurveyUserId) " );
				queryStr.append(" from TdpCadre model,CadreSurveyUserAssignDetails model2 where model2.constituency.district.districtId in (:locationIdsList) and ( date(model.surveyTime) >=:fromDate  and date(model.surveyTime) <=:toDate ) ");
				queryStr.append(" and model2.cadreSurveyUser.cadreSurveyUserId = model.insertedBy.cadreSurveyUserId ");
				queryStr.append(" group by  model2.constituency.district.districtId,date(model.surveyTime) order by date(model.insertedTime) desc ");

				assignUsersQuery.append(" select distinct model.constituency.district.districtId, count( distinct model.cadreSurveyUserId ) from CadreSurveyUserAssignDetails model where " +
						" model.isDeleted = 'N' and  model.constituency.district.districtId in (:locationIdsList) group by model.constituency.district.districtId  ");				
			}
			
			else if(areaType.equalsIgnoreCase("assembly"))
			{
								
				queryStr.append(" select model2.constituency.constituencyId , model2.constituency.name,  date(model.surveyTime) , count( distinct model.insertedBy.cadreSurveyUserId) " );
				queryStr.append(" from TdpCadre model , CadreSurveyUserAssignDetails model2 where model2.constituency.constituencyId in (:locationIdsList) and ( date(model.surveyTime) >=:fromDate  and date(model.surveyTime) <=:toDate ) ");
				queryStr.append(" and model2.cadreSurveyUser.cadreSurveyUserId = model.insertedBy.cadreSurveyUserId ");
				queryStr.append(" group by model2.constituency.constituencyId, date(model.surveyTime) order by date(model.insertedTime) desc ");
				
				assignUsersQuery.append(" select distinct model.constituency.constituencyId, count( distinct model.cadreSurveyUserId ) from CadreSurveyUserAssignDetails model where " +
						" model.isDeleted = 'N' and model.constituency.constituencyId in (:locationIdsList)  group by model.constituency.constituencyId ");	
				
			}
			
			else if(areaType.equalsIgnoreCase("Parliament"))
			{
				assignUsersQuery.append(" select distinct model2.delimitationConstituency.constituency.constituencyId, count( distinct model.cadreSurveyUserId ) " +
						" from CadreSurveyUserAssignDetails model, DelimitationConstituencyAssemblyDetails model2  where " +
						" model.isDeleted = 'N' and model.constituency.constituencyId = model2.constituency.constituencyId and model2.delimitationConstituency.year = 2009  " +
						"  and  model2.delimitationConstituency.constituency.constituencyId in (:locationIdsList) group by model2.delimitationConstituency.constituency.constituencyId , model2.delimitationConstituency.constituency.constituencyId ");	
			
			}
			
			List<Object[]> userTrackList = null; 
			Map<Long,Long> assignedUsersMap = new TreeMap<Long, Long>();
			List<Object[]> assignedCandidates = cadreSurveyUserAssignDetailsDAO.getCandidatesInfo(assignUsersQuery.toString(),areaIdsList);
			
			if(assignedCandidates != null && assignedCandidates.size()>0)
			{
				for (Object[] param : assignedCandidates) 
				{
					assignedUsersMap.put(Long.valueOf(param[0].toString().trim()), Long.valueOf(param[1].toString().trim()));
				}
			}
			
			if(areaType.equalsIgnoreCase("Parliament"))
			{						
						queryStr.append(" select model2.delimitationConstituency.constituency.constituencyId,model2.delimitationConstituency.constituency.name,date(model.surveyTime),count( distinct model.insertedBy.cadreSurveyUserId) " );
						queryStr.append(" from TdpCadre model,DelimitationConstituencyAssemblyDetails model2 where model.userAddress.constituency.constituencyId = model2.constituency.constituencyId and " );
						queryStr.append(" model2.delimitationConstituency.year = 2009 and model2.delimitationConstituency.constituency.constituencyId in (:locationIdsList) and ( date(model.surveyTime) >=:fromDate  and date(model.surveyTime) <=:toDate ) ");
						queryStr.append(" group by model2.delimitationConstituency.constituency.constituencyId,date(model.surveyTime) order by date(model.insertedTime) desc ");
						
						userTrackList = tdpCadreDAO.getLocationWiseUsersDetails(areaIdsList, fromDate, toDate, queryStr.toString());
					
					surveyTransactionVOList = getUserTrackingDetails(userTrackList,surveyTransactionVOList,assignedUsersMap,areaType,stateTypeId,areaIdsList,FdateStr);
				
			}
			else
			{
				userTrackList = tdpCadreDAO.getLocationWiseUsersDetails(areaIdsList, fromDate, toDate, queryStr.toString());
				surveyTransactionVOList = getUserTrackingDetails(userTrackList,surveyTransactionVOList,assignedUsersMap, areaType,stateTypeId,areaIdsList,FdateStr);
			}

			
			
		} catch (Exception e) {
			LOG.error("Exception rised in getLocationswiseUsersList() ",e);
		}
		
		return surveyTransactionVOList;
	}

	public WSResultVO gettingUserDetailsByLocation(String location,Long locationId,String type,Date date)
	{
		 WSResultVO  finalVO=new WSResultVO();
		try{
			
			Map<String,String> parliamentForAssemblyMap = new TreeMap<String, String>();
			List<Object[]> locationsList =  delimitationConstituencyAssemblyDetailsDAO.getPcListByRegion(0L);							
				
			List<SurveyTransactionVO> locationIdsList = new ArrayList<SurveyTransactionVO>();
			if(locationsList != null && locationsList.size()>0)
			{
				for (Object[] param : locationsList)
				{
					SurveyTransactionVO surveyTransactionVO = new SurveyTransactionVO();
					surveyTransactionVO.setId(param[0] != null ? Long.valueOf(param[0].toString()) :0L);
					surveyTransactionVO.setName(param[1] != null ? param[1].toString() :"");
					
					locationIdsList.add(surveyTransactionVO);
				}
			}
			
			if(locationIdsList != null && locationIdsList.size()>0)
			{
				for (SurveyTransactionVO surveyTransctionVO : locationIdsList) 
				{
					List<Long> locationIdList = new ArrayList<Long>();
					locationIdList.add(surveyTransctionVO.getId());
					
					locationsList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesListForAListOfParliamentConstituency(locationIdList);
											
					if(locationsList != null && locationsList.size()>0)
					{
						for (Object[] param : locationsList)
						{
							parliamentForAssemblyMap.put(param[1] != null ? param[1].toString().trim() :"", surveyTransctionVO.getName());
						}
					}	
					
				}
			}
						
			if(location.equalsIgnoreCase("constituency"))
			{
				List<Long> assignedUsersList=cadreSurveyUserAssignDetailsDAO.getCadreSurveyUserIdsByLocation(location,locationId,null);
				
				if(type.equalsIgnoreCase("submit")){
				List<Long> startedList=null;
				if(assignedUsersList!=null && assignedUsersList.size()>0){
				   startedList=tdpCadreDAO.getCadreSurveyUsersStartedByLocation(assignedUsersList,date);
				}
				
				if(startedList != null && startedList.size()>0)
				{
					List<Object[]> startedUsersDetailsList=cadreSurveyUserAssignDetailsDAO.getUsersDetails(startedList);
					List<WSResultVO> startedUsersList=null;
					if(startedUsersDetailsList!=null && startedUsersDetailsList.size()>0){
						startedUsersList=new ArrayList<WSResultVO>();
						for (Object[] objects : startedUsersDetailsList)//uname,name,nobileno,tabno
						{
							WSResultVO wsResultVO=new WSResultVO();
							wsResultVO.setUserName(objects[0]!=null?objects[0].toString():"");
							wsResultVO.setName(objects[1]!=null?objects[1].toString():"");
							wsResultVO.setDescription(objects[2]!=null?objects[2].toString():"");//mobileNO.
							wsResultVO.setPwd(objects[3]!=null?objects[3].toString():"");//tabno
							wsResultVO.setLocation(objects[4]!=null?objects[4].toString():""); // constituency
							wsResultVO.setUniqueCode(parliamentForAssemblyMap.get(wsResultVO.getLocation()));//parliament
							startedUsersList.add(wsResultVO);
						}
						finalVO.setStartVO(startedUsersList);
					  }
				}
				
				}
				else if(type.equalsIgnoreCase("notSubmit"))
				{
					
						List<Long> startedList=null;
						if(assignedUsersList!=null && assignedUsersList.size()>0){
						   startedList=tdpCadreDAO.getCadreSurveyUsersStartedByLocation(assignedUsersList,date);
						}
						if(startedList != null && startedList.size()>0)
						{
							List<Object[]> startedUsersDetailsList=cadreSurveyUserAssignDetailsDAO.getUsersDetails(startedList);
							List<WSResultVO> startedUsersList=null;
							if(startedUsersDetailsList!=null && startedUsersDetailsList.size()>0){
								startedUsersList=new ArrayList<WSResultVO>();
								for (Object[] objects : startedUsersDetailsList)//uname,name,nobileno,tabno
								{
									WSResultVO wsResultVO=new WSResultVO();
									wsResultVO.setUserName(objects[0]!=null?objects[0].toString():"");
									wsResultVO.setName(objects[1]!=null?objects[1].toString():"");
									wsResultVO.setDescription(objects[2]!=null?objects[2].toString():"");//mobileNO.
									wsResultVO.setPwd(objects[3]!=null?objects[3].toString():"");//tabno
									wsResultVO.setLocation(objects[4]!=null?objects[4].toString():"");
									wsResultVO.setUniqueCode(parliamentForAssemblyMap.get(wsResultVO.getLocation()));
									startedUsersList.add(wsResultVO);
								}
								finalVO.setStartVO(startedUsersList);
							  }
						}
						
				List<Long> notStartedList=new ArrayList<Long>();
				if(assignedUsersList!=null && assignedUsersList.size()>0)
				{
					for (Long assignedUser : assignedUsersList)
					{
						if(assignedUser!=null){
						  if(startedList!=null && startedList.size()>0){
							if(!startedList.contains(assignedUser)){
								notStartedList.add(assignedUser);
							 }
						   }
						  else
						  {
							  notStartedList.add(assignedUser);
						  }
						 }
					 }
				 }
				
				List<Object[]> nonStartedUsersDetailsList=cadreSurveyUserAssignDetailsDAO.getUsersDetails(notStartedList);
				List<WSResultVO> nonStartedUsersList=null;
			    if(nonStartedUsersDetailsList!=null && nonStartedUsersDetailsList.size()>0){
					 nonStartedUsersList=new ArrayList<WSResultVO>();
						for (Object[] objects : nonStartedUsersDetailsList)//uname,name,nobileno,tabno
						{
							WSResultVO wsResultVO=new WSResultVO();
							wsResultVO.setUserName(objects[0]!=null?objects[0].toString():"");
							wsResultVO.setName(objects[1]!=null?objects[1].toString():"");
							wsResultVO.setDescription(objects[2]!=null?objects[2].toString():"");//mobileNO.
							wsResultVO.setPwd(objects[3]!=null?objects[3].toString():"");//tabno
							wsResultVO.setLocation(objects[4]!=null?objects[4].toString():"");
							wsResultVO.setUniqueCode(parliamentForAssemblyMap.get(wsResultVO.getLocation()));
							nonStartedUsersList.add(wsResultVO);
						}
						finalVO.setNotStartVO(nonStartedUsersList);
					  }
				}	
			}
			
		    else if(location.equalsIgnoreCase("district"))
			{
		    	List<Long> assignedUsersList=cadreSurveyUserAssignDetailsDAO.getCadreSurveyUserIdsByLocation(location,locationId,null);
		    	List<Long> startedList=null;
				List<Long> notStartedList=new ArrayList<Long>();
				if(assignedUsersList!=null && assignedUsersList.size()>0){
				   startedList=tdpCadreDAO.getCadreSurveyUsersStartedByLocation(assignedUsersList,date);
				}
				if(assignedUsersList!=null && assignedUsersList.size()>0)
				{
					for (Long assignedUser : assignedUsersList)
					{
						if(assignedUser!=null){
						  if(startedList!=null && startedList.size()>0){
							if(!startedList.contains(assignedUser)){
								notStartedList.add(assignedUser);
							 }
						   }
						  else
						  {
							  notStartedList.add(assignedUser);
						  }
						 }
					 }
				 }
				
				if(startedList != null && startedList.size()>0)
				{
					 List<Object[]> startedUsersDetailsList=cadreSurveyUserAssignDetailsDAO.getUsersDetails(startedList);
					 List<WSResultVO> startedUsersList=null;
					 
					 if(startedUsersDetailsList!=null && startedUsersDetailsList.size()>0){
						startedUsersList=new ArrayList<WSResultVO>();
						for (Object[] objects : startedUsersDetailsList)//uname,name,nobileno,tabno
						{
							WSResultVO wsResultVO=new WSResultVO();
							wsResultVO.setUserName(objects[0]!=null?objects[0].toString():"");
							wsResultVO.setName(objects[1]!=null?objects[1].toString():"");
							wsResultVO.setDescription(objects[2]!=null?objects[2].toString():"");//mobileNO.
							wsResultVO.setPwd(objects[3]!=null?objects[3].toString():"");//tabno
							wsResultVO.setLocation(objects[4]!=null?objects[4].toString():"");
							wsResultVO.setUniqueCode(parliamentForAssemblyMap.get(wsResultVO.getLocation()));
							startedUsersList.add(wsResultVO);
						}
						finalVO.setStartVO(startedUsersList);
					  }
				}
								 
				 List<Object[]> nonStartedUsersDetailsList=cadreSurveyUserAssignDetailsDAO.getUsersDetails(notStartedList);
				 List<WSResultVO> nonStartedUsersList=null;
				 if(nonStartedUsersDetailsList!=null && nonStartedUsersDetailsList.size()>0){
					 nonStartedUsersList=new ArrayList<WSResultVO>();
						for (Object[] objects : nonStartedUsersDetailsList)//uname,name,nobileno,tabno
						{
							WSResultVO wsResultVO=new WSResultVO();
							wsResultVO.setUserName(objects[0]!=null?objects[0].toString():"");
							wsResultVO.setName(objects[1]!=null?objects[1].toString():"");
							wsResultVO.setDescription(objects[2]!=null?objects[2].toString():"");//mobileNO.
							wsResultVO.setPwd(objects[3]!=null?objects[3].toString():"");//tabno
							wsResultVO.setLocation(objects[4]!=null?objects[4].toString():"");
							wsResultVO.setUniqueCode(parliamentForAssemblyMap.get(wsResultVO.getLocation()));
							nonStartedUsersList.add(wsResultVO);
						}
						finalVO.setNotStartVO(nonStartedUsersList);
					  }
			}				 
		    else if(location.equalsIgnoreCase("parliament"))
			{
		    	StringBuilder assignUsersQuery = new StringBuilder();
		    	
		    	assignUsersQuery.append(" select distinct model.cadreSurveyUserId  " +
						" from CadreSurveyUserAssignDetails model, DelimitationConstituencyAssemblyDetails model2  where " +
						" model.isDeleted = 'N' and model.constituency.constituencyId = model2.constituency.constituencyId and model2.delimitationConstituency.year = 2009   ");	
		    	
							
		    	List<Long> assignedUsersList=cadreSurveyUserAssignDetailsDAO.getCadreSurveyUserIdsByLocation(location,locationId,assignUsersQuery.toString());
		    	List<Long> startedList=null;
				List<Long> notStartedList=new ArrayList<Long>();
				if(assignedUsersList!=null && assignedUsersList.size()>0){
				   startedList=tdpCadreDAO.getCadreSurveyUsersStartedByLocation(assignedUsersList,date);
				}
				if(assignedUsersList!=null && assignedUsersList.size()>0)
				{
					for (Long assignedUser : assignedUsersList)
					{
						if(assignedUser!=null){
						  if(startedList!=null && startedList.size()>0){
							if(!startedList.contains(assignedUser)){
								notStartedList.add(assignedUser);
							 }
						   }
						  else
						  {
							  notStartedList.add(assignedUser);
						  }
						 }
					 }
				 }
				
				if(startedList != null && startedList.size()>0)
				{
					 List<Object[]> startedUsersDetailsList=cadreSurveyUserAssignDetailsDAO.getUsersDetails(startedList);
					 List<WSResultVO> startedUsersList=null;
					 
					 if(startedUsersDetailsList!=null && startedUsersDetailsList.size()>0){
						startedUsersList=new ArrayList<WSResultVO>();
						for (Object[] objects : startedUsersDetailsList)//uname,name,nobileno,tabno
						{
							WSResultVO wsResultVO=new WSResultVO();
							wsResultVO.setUserName(objects[0]!=null?objects[0].toString():"");
							wsResultVO.setName(objects[1]!=null?objects[1].toString():"");
							wsResultVO.setDescription(objects[2]!=null?objects[2].toString():"");//mobileNO.
							wsResultVO.setPwd(objects[3]!=null?objects[3].toString():"");//tabno
							wsResultVO.setLocation(objects[4]!=null?objects[4].toString():"");
							wsResultVO.setUniqueCode(parliamentForAssemblyMap.get(wsResultVO.getLocation()));
							startedUsersList.add(wsResultVO);
						}
						finalVO.setStartVO(startedUsersList);
					  }
				}
								 
				 List<Object[]> nonStartedUsersDetailsList=cadreSurveyUserAssignDetailsDAO.getUsersDetails(notStartedList);
				 List<WSResultVO> nonStartedUsersList=null;
				 if(nonStartedUsersDetailsList!=null && nonStartedUsersDetailsList.size()>0){
					 nonStartedUsersList=new ArrayList<WSResultVO>();
						for (Object[] objects : nonStartedUsersDetailsList)//uname,name,nobileno,tabno
						{
							WSResultVO wsResultVO=new WSResultVO();
							wsResultVO.setUserName(objects[0]!=null?objects[0].toString():"");
							wsResultVO.setName(objects[1]!=null?objects[1].toString():"");
							wsResultVO.setDescription(objects[2]!=null?objects[2].toString():"");//mobileNO.
							wsResultVO.setPwd(objects[3]!=null?objects[3].toString():"");//tabno
							wsResultVO.setLocation(objects[4]!=null?objects[4].toString():"");
							wsResultVO.setUniqueCode(parliamentForAssemblyMap.get(wsResultVO.getLocation()));
							nonStartedUsersList.add(wsResultVO);
						}
						finalVO.setNotStartVO(nonStartedUsersList);
					  }
			}
					
		   }catch (Exception e)
		   {
			LOG.error("Exception rised in gettingUserDetailsByLocation() ",e);
			
		   }
		 return  finalVO;
		
	}

	public WSResultVO getAllParliamentsForAssembly()
	{
		return null;
	}
	
	public void getAnalysisData(){
		String reqDate ="2014-11-12";
      //LinkedHashMap<constiId,LinkedHashMap<userId,List<Date>>>
		LinkedHashMap<Long,LinkedHashMap<Long,List<Date>>> resultMap = new LinkedHashMap<Long,LinkedHashMap<Long,List<Date>>>();
		LinkedHashMap<Long,List<Date>> constiMap = null;
		Map<Long,String> constituencyMap = new HashMap<Long,String>();
		Map<Long,String> nameMap = new HashMap<Long,String>();
		Map<Long,String> userNameMap = new HashMap<Long,String>();
		Map<Long,String> tabMap = new HashMap<Long,String>();
		Map<Long,String> mobileMap = new HashMap<Long,String>();
		
		//0 constiId,1constiName,2userId,3name,4userName,5 tab,6 mobile
		List<Object[]> userDataList = tdpCadreDAO.getUserData();
		for(Object[] userData:userDataList){
			if(userData[1] != null){
			   constituencyMap.put((Long)userData[0], userData[1].toString());
			}
			if(userData[3] != null){
				nameMap.put((Long)userData[2], userData[3].toString());
			}
			if(userData[4] != null){
				userNameMap.put((Long)userData[2], userData[4].toString());
			}
			if(userData[5] != null){
				tabMap.put((Long)userData[2], userData[5].toString());
			}
			if(userData[6] != null){
				mobileMap.put((Long)userData[2], userData[6].toString());
			}
		}
		//0 userId,constiId,time
		List<Object[]> dataList = tdpCadreDAO.getAnalysisData(reqDate);	
		for(Object[] result:dataList){
			constiMap = resultMap.get((Long)result[1]);
			if(constiMap == null){
				constiMap = new LinkedHashMap<Long,List<Date>>();
				resultMap.put((Long)result[1], constiMap);
			}
			List<Date> dates = constiMap.get((Long)result[0]);
			if(dates == null){
				dates = new ArrayList<Date>();
				constiMap.put((Long)result[0],dates);
			}
			dates.add((Date)result[2]);
		}
		   try{
		         FileOutputStream fileOut =
		         new FileOutputStream("D:/tmp/obj.ser");
		         ObjectOutputStream out = new ObjectOutputStream(fileOut);
		         out.writeObject(resultMap);
		         out.close();
		         fileOut.close();
	       }catch(Exception e){
					e.printStackTrace();
				}
		   
		/*try
	      {
	         FileInputStream fileIn = new FileInputStream("D:/tmp/obj.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         resultMap = (LinkedHashMap<Long,LinkedHashMap<Long,List<Date>>>) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(Exception e){
				e.printStackTrace();
			}*/
		try{
			int row = 0;
		      HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("Users");
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("CONSTITUENCY");
			    header.createCell(1).setCellValue("USER1");
			    //header.createCell(2).setCellValue("NAME");
			    header.createCell(2).setCellValue("START TIME");
			    header.createCell(3).setCellValue("END TIME");
			    header.createCell(4).setCellValue("MOBILE");
			    header.createCell(5).setCellValue("TABNO");
				header.createCell(6).setCellValue("USER2");
				//header.createCell(8).setCellValue("NAME");
				header.createCell(7).setCellValue("STATE TIME");
				header.createCell(8).setCellValue("END TIME");
				header.createCell(9).setCellValue("MOBILE");
				header.createCell(10).setCellValue("TABNO");
				//header.createCell(7).setCellValue("USER1 ALL RECORDS");
				//header.createCell(8).setCellValue("USER2 ALL RECORDS");
		   for(Long constiId:resultMap.keySet()){
			   constiMap = resultMap.get(constiId);
			   if(constiMap.size()== 1){
				   continue;
			   }else{
				   List<Long> users = new ArrayList<Long>(constiMap.keySet());
				   for(int i=0;i<users.size();i++){
					  
					   List<Date> user1Dates = constiMap.get(users.get(i));
					   if(user1Dates.size() > 1){
						 Date user1MinDate = constiMap.get(users.get(i)).get(0);
						 Date user1MaxDate = constiMap.get(users.get(i)).get(constiMap.get(users.get(i)).size()-1);  
					    for(int j=i+1;j<users.size();j++){
					    if(constiMap.get(users.get(j)).size()>1){	
						   boolean isExchange = false;
						  
						   Date user2MinDate = constiMap.get(users.get(j)).get(0);
						   Date user2MaxDate = constiMap.get(users.get(j)).get(constiMap.get(users.get(j)).size()-1);
						   /*for(Date ser1Date:user1Dates){
							   if(!isExchange){
								   continue;
							   }*/
							   if((user1MaxDate.compareTo(user2MinDate) <= 0) || user2MaxDate.compareTo(user1MinDate) <=0){
								   isExchange = true;
							   }
							  
						   //}
						   if(isExchange){
							   row=row+1;
							   Row dataRow = sheet.createRow(row);
							   if(constituencyMap.get(constiId) != null){
							      dataRow.createCell(0).setCellValue(constituencyMap.get(constiId));
							   }else{
								   dataRow.createCell(0).setCellValue("");
							   }
							   String str1 ="";
							   String str2 ="";
							   /*for(Date ser1Date:user1Dates){
								   str1=str1+ser1Date.toString().replace(reqDate, "")+",";
							   }
							   for(Date ser1Date:constiMap.get(users.get(j))){
								   str2=str2+ser1Date.toString().replace(reqDate, "")+",";
							   }*/
							   dataRow.createCell(1).setCellValue(userNameMap.get(users.get(i)));
							   //dataRow.createCell(2).setCellValue(nameMap.get(users.get(i)));
							   dataRow.createCell(2).setCellValue(user1Dates.get(0).toString().replace(reqDate, ""));
							   dataRow.createCell(3).setCellValue(user1Dates.get(user1Dates.size()-1).toString().replace(reqDate, ""));
							   dataRow.createCell(4).setCellValue(mobileMap.get(users.get(i)));
							   dataRow.createCell(5).setCellValue(tabMap.get(users.get(i)));
							   dataRow.createCell(6).setCellValue(userNameMap.get(users.get(j)));
							   //dataRow.createCell(8).setCellValue(nameMap.get(users.get(j)));
							   dataRow.createCell(7).setCellValue(constiMap.get(users.get(j)).get(0).toString().replace(reqDate, ""));
							   dataRow.createCell(8).setCellValue(constiMap.get(users.get(j)).get(constiMap.get(users.get(j)).size()-1).toString().replace(reqDate, ""));
							   dataRow.createCell(9).setCellValue(mobileMap.get(users.get(j)));
							   dataRow.createCell(10).setCellValue(tabMap.get(users.get(j)));
							   //dataRow.createCell(7).setCellValue(str1);
							   //dataRow.createCell(8).setCellValue(str2);
							   System.out.println("Constituency:"+constiId+" Exchange"+users.get(i)+"("+str1+"),"+users.get(j)+"("+str2+")");
						   }
					   }
					   }
				    }
				   }
				  
			   }
		   }
		   FileOutputStream out =
	                new FileOutputStream(new File("D:\\tmp\\abc.xls"));
	        workbook.write(out);
	        out.close();
	}catch(Exception e){
		   e.printStackTrace();
	   }
	}
	
	public List<CadreRegisterInfo> getCandidateDataCollectionInfo(Long locationType,List<Long> locationIds,Date fromDate,Date toDate,String sourceType){
			List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
			CadreRegisterInfo vo = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat timeFormate = new SimpleDateFormat("HH:mm");
			try{
				List<Long> surveyUserIds = new ArrayList<Long>();
				List<Date> datesList = new ArrayList<Date>();
				Map<Long,String> nameMap = new HashMap<Long, String>();
				Map<Long,String> webUserAccessMap = new HashMap<Long, String>();
				Map<Long,String> tabMap = new HashMap<Long, String>();
				Map<Long,Map<Date,CadreRegisterInfo>> userMap = new HashMap<Long,Map<Date,CadreRegisterInfo>>();//Map<userId,Map<Date,info>>
				Map<Date,CadreRegisterInfo> dateMap = new HashMap<Date,CadreRegisterInfo>();//Map<Date,info>
				Map<Long,String> userNames = new HashMap<Long,String>();
				Map<Long,CadreRegisterInfo> mobileNos = new HashMap<Long,CadreRegisterInfo>();
				//0 count,1 name,2 min,3 max,4 date,5 id,6 name
				String parlimentName = null;
				Map<String,String> parliamentForAssemblyMap = new TreeMap<String, String>();
				List<Object[]> locationsList =  delimitationConstituencyAssemblyDetailsDAO.getPcListByRegion(0L);							
					
				List<SurveyTransactionVO> locationIdsList = new ArrayList<SurveyTransactionVO>();
				if(locationsList != null && locationsList.size()>0)
				{
					for (Object[] param : locationsList)
					{
						SurveyTransactionVO surveyTransactionVO = new SurveyTransactionVO();
						surveyTransactionVO.setId(param[0] != null ? Long.valueOf(param[0].toString()) :0L);
						surveyTransactionVO.setName(param[1] != null ? param[1].toString() :"");
						locationIdsList.add(surveyTransactionVO);
					}
				}
				List<Long> assemblyIds = new ArrayList<Long>(0);
				
				if(locationIdsList != null && locationIdsList.size()>0)
				{
					for (SurveyTransactionVO surveyTransctionVO : locationIdsList) 
					{
						List<Long> locationIdList = new ArrayList<Long>();
						locationIdList.add(surveyTransctionVO.getId());
						
						locationsList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesListForAListOfParliamentConstituency(locationIdList);
												
						if(locationsList != null && locationsList.size()>0)
						{
							for (Object[] param : locationsList)
							{
								assemblyIds.add(param[0] != null ? Long.valueOf(param[0].toString().trim()):0L );
								parliamentForAssemblyMap.put(param[1] != null ? param[1].toString().trim() :"", surveyTransctionVO.getName());
							}
						}	
						
					}
				}
				
				List<Object[]> dataCollectedInfo = null;
				List<Long> assemblyIdsList = new ArrayList<Long>(0);
				 if(locationType.longValue() == 4L )
				 {
					 Long parliamentID = locationIds.get(0) != null? locationIds.get(0):0L;
					 try {
							 if(parliamentID.longValue() >0 && locationIds.size() == 1 )
							 {
								 List<DelimitationConstituency> delimitationConstituencyList = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyID(parliamentID);
									DelimitationConstituency parliamentConstituency = delimitationConstituencyList.get(0);
									parlimentName = parliamentConstituency.getConstituency().getName();
									assemblyIdsList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliament(parliamentID);
							 }
							 else
							 {
								// assemblyIdsList.addAll(assemblyIds);
								 if(locationIds != null && locationIds.size()>0)
								 {
									 for (Long parliamentId : locationIds) 
									 {
											assemblyIdsList.addAll(delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliament(parliamentId));
									 }
								 }
							 }
							 
						} catch (Exception e) {}
					 
					 locationIds.clear();
					 locationIds.addAll(assemblyIdsList);
						
					 dataCollectedInfo = tdpCadreDAO.getCandidateDataCollectionInfo1(3L,assemblyIdsList,fromDate, toDate,sourceType);
				 }
				 else
				 {
					 dataCollectedInfo = tdpCadreDAO.getCandidateDataCollectionInfo1(locationType,locationIds,fromDate, toDate,sourceType);
				 }
				 
				 Map<String, String> districtMap = new TreeMap<String, String>();
				 Map<String, String> stateMap = new TreeMap<String, String>();
				 
				for(Object[] data:dataCollectedInfo){
					if(!datesList.contains((Date)data[3])){
						datesList.add((Date)data[3]);
					}
					if(sourceType.equalsIgnoreCase("TAB"))
					userNames.put((Long)data[4], data[5].toString());
					else
					{
						String fname = data[5] != null ? data[5].toString() : "" ;
						String lname = data[6] != null ? data[6].toString() : "";
						String name = fname+ " " +lname;
						userNames.put((Long)data[4],name);
						if(data[7] != null && data[7].toString().equalsIgnoreCase("MLA") )
						{
							Constituency constituency = constituencyDAO.get(new Long(data[8].toString()));
							String constiName = constituency.getName();
							webUserAccessMap.put((Long)data[4],constiName);
							districtMap.put(constiName, constituency.getDistrict().getDistrictName());
							
							if(constituency.getDistrict() != null && constituency.getDistrict().getDistrictId().longValue() < 11)
							{
								stateMap.put(constiName, "TG");
							}
							if(constituency.getDistrict() != null && ( constituency.getDistrict().getDistrictId().longValue() > 10 && constituency.getDistrict().getDistrictId().longValue() < 24 ) )
							{
								stateMap.put(constiName, "AP");
							}
						}
					}
					dateMap = userMap.get((Long)data[4]);
					if(dateMap == null){
						dateMap = new HashMap<Date,CadreRegisterInfo>();
						userMap.put((Long)data[4],dateMap);
						if(sourceType.equalsIgnoreCase("TAB"))
						nameMap.put((Long)data[4], data[6] != null ? data[6].toString() : "");
						if(!surveyUserIds.contains((Long)data[4]))
						surveyUserIds.add((Long)data[4]);
					}
					vo = new CadreRegisterInfo() ;
					vo.setArea(convertTimeTo12HrsFormat(timeFormate.format((Date)data[1])));
					vo.setLocation(convertTimeTo12HrsFormat(timeFormate.format((Date)data[2])));
					vo.setTotalCount((Long)data[0]);
					vo.setAmount(vo.getTotalCount() * 100);
					dateMap.put((Date)data[3], vo);
				}
				if(surveyUserIds != null && surveyUserIds.size() > 0 && sourceType.equalsIgnoreCase("TAB"))
				{
				List<Object[]> tabNos = cadreSurveyUserAssignDetailsDAO.getTabNos(surveyUserIds);
				if(tabNos != null && tabNos.size() > 0)
					for(Object[] params : tabNos)
					{
						tabMap.put((Long)params[0], params[1] != null ?  params[1].toString() : "");
					}
				}
				
				if(fromDate.equals(toDate) && sourceType.equalsIgnoreCase("TAB"))
				{
					int count = 0;
					if(userNames.size() > 0 ){
						if(sourceType.equalsIgnoreCase("TAB"))
						{
							//0 userId,1mobile,2constituencyId,3constiname,4distiictId,5districtName
							List<Object[]> mobileNosList = cadreSurveyUserDAO.getAllUserMobileNos(locationType,locationIds);
							for(Object[] mobileNo:mobileNosList){
								CadreRegisterInfo userData = new CadreRegisterInfo();
							 if(mobileNo[1] != null){
								userData.setArea(mobileNo[1].toString());//mobileNo
							 }
							 if(((Long)mobileNo[4]).longValue() < 11){
							     userData.setLocation("TG");//state
							 }else{
								 userData.setLocation("AP");//state
							 }
							 userData.setNumber(mobileNo[5].toString());//district
							 userData.setMemberShipNo(mobileNo[3].toString());//constituency
							 userData.setPercentStr(parliamentForAssemblyMap.get(mobileNo[3].toString()));//parliament
							
							 userData.setName(mobileNo[7] != null ? mobileNo[7].toString():"");
							 if(sourceType.equalsIgnoreCase("TAB"))
							 {
								 userData.setTabNo(mobileNo[8] != null ?  mobileNo[8].toString() :"");
								 userData.setUname(mobileNo[6] != null ?  mobileNo[6].toString() :"");
							 }
								
							 //
							 if(locationType.longValue() == 2L && locationIds.contains(Long.valueOf(mobileNo[4].toString())))
							 {
								 mobileNos.put((Long)mobileNo[0], userData);
							 }
							 else if(locationType.longValue() == 3L && locationIds.contains(Long.valueOf(mobileNo[2].toString())))
							 {
								 mobileNos.put((Long)mobileNo[0], userData);
							 }
							 else if(locationType.longValue() == 4L )
							 {
								 mobileNos.put((Long)mobileNo[0], userData);
							 }
							 else if(locationType.longValue() == 1L || locationType.longValue() == 0L)
							 {
								 mobileNos.put((Long)mobileNo[0], userData);
							 }
							 
						}
						}
					}
					
					for(Long key:mobileNos.keySet()){
						dateMap = userMap.get(key);
						vo = new CadreRegisterInfo();
						if(sourceType.equalsIgnoreCase("WEB"))
						vo.setMemberShipNo(webUserAccessMap.get(key));
						vo.setName(userNames.get(key));
						if(vo.getName() != null)
						{
								
								CadreRegisterInfo userData = mobileNos.get(key);
								if(userData != null && sourceType.equalsIgnoreCase("TAB")){
								    vo.setArea(userData.getArea());//mobileNo
								    vo.setLocation(userData.getLocation());//state
								    vo.setNumber(userData.getNumber());//district
								    vo.setMemberShipNo(userData.getMemberShipNo());//constituency
								    vo.setPercentStr(userData.getPercentStr());
								    vo.setUname(nameMap.get(key));
								    
								    vo.setTabNo(tabMap.get(key));
								}
								List<CadreRegisterInfo> daysList = new ArrayList<CadreRegisterInfo>();
								for(Date date:datesList){
									if(dateMap.get(date) != null){
										CadreRegisterInfo day = dateMap.get(date);
									    if(count == 0){
										  day.setDate(sdf.format(date));
									    }
										daysList.add(day);
									}else{
										CadreRegisterInfo day = new CadreRegisterInfo();
										if(count == 0){
										   day.setDate(sdf.format(date));
										 }
										daysList.add(day);
									}
								}
								vo.setInfoList(daysList);
								if(vo.getInfoList() != null && vo.getInfoList().size() > 0)
								{
									Long count1 = 0l;
									for(CadreRegisterInfo vo3 : vo.getInfoList())
									{
										if(vo3.getTotalCount() != null)
										 count1 = count1 + vo3.getTotalCount();
									}
									vo.setTotalCount(count1);
									vo.setTotalAmount(vo.getTotalCount() > 0 ?  vo.getTotalCount()* 100 : 0);
								}
								returnList.add(0,vo);
								count++;
						}
						else
						{
							CadreRegisterInfo userData = mobileNos.get(key);
							
							if(userData != null && sourceType.equalsIgnoreCase("TAB"))
							{
							    vo.setArea(userData.getArea());//mobileNo
							    vo.setLocation(userData.getLocation());//state
							    vo.setNumber(userData.getNumber());//district
							    vo.setMemberShipNo(userData.getMemberShipNo());//constituency
							    vo.setPercentStr(userData.getPercentStr());//parliament
							    vo.setUname(userData.getName());
							    vo.setName(userData.getUname());
							    vo.setTabNo(userData.getTabNo());
							}
							List<CadreRegisterInfo> daysList = new ArrayList<CadreRegisterInfo>();
							CadreRegisterInfo day = new CadreRegisterInfo();
							day.setDate(sdf.format(fromDate));
							daysList.add(day);							
							vo.setInfoList(daysList);
							vo.setTotalCount(0L);
							vo.setTotalAmount(0L);					
							returnList.add(vo);
						}
					}
						
				}
				else
				{
					int count = 0;
					if(userNames.size() > 0 && sourceType.equalsIgnoreCase("TAB")){
						//0 userId,1mobile,2constituencyId,3constiname,4distiictId,5districtName
						List<Object[]> mobileNosList = cadreSurveyUserDAO.getUserMobileNos(userNames.keySet());
						for(Object[] mobileNo:mobileNosList){
							CadreRegisterInfo userData = new CadreRegisterInfo();
						 if(mobileNo[1] != null){
							userData.setArea(mobileNo[1].toString());//mobileNo
						 }
						 if(((Long)mobileNo[4]).longValue() < 11){
						     userData.setLocation("TG");//state
						 }else{
							 userData.setLocation("AP");//state
						 }
						 userData.setNumber(mobileNo[5].toString());//district
						 userData.setMemberShipNo(mobileNo[3].toString());//constituency
						 userData.setPercentStr(parliamentForAssemblyMap.get(mobileNo[3].toString()));
						 mobileNos.put((Long)mobileNo[0], userData);
						}
					}
					for(Long key:userMap.keySet()){
						dateMap = userMap.get(key);
						vo = new CadreRegisterInfo();
						vo.setName(userNames.get(key));
						if(sourceType.equalsIgnoreCase("WEB"))
						{
							vo.setMemberShipNo(webUserAccessMap.get(key));
							vo.setParliament(parliamentForAssemblyMap.get(vo.getMemberShipNo() != null?vo.getMemberShipNo():"") != null ? parliamentForAssemblyMap.get(vo.getMemberShipNo() != null?vo.getMemberShipNo():"") :" -- ");
							vo.setDistrict(districtMap.get(vo.getMemberShipNo() != null?vo.getMemberShipNo():"") != null ? districtMap.get(vo.getMemberShipNo() != null?vo.getMemberShipNo():"") :" -- ");
							vo.setState(stateMap.get(vo.getMemberShipNo() != null?vo.getMemberShipNo():"") != null ? stateMap.get(vo.getMemberShipNo() != null?vo.getMemberShipNo():"") :" -- ");
						}
						//vo.setUname(unameMap.get(key) != null ? unameMap.get(key).toString() : "");
						CadreRegisterInfo userData = mobileNos.get(key);
						if(userData != null && sourceType.equalsIgnoreCase("TAB")){
						    vo.setArea(userData.getArea());//mobileNo
						    vo.setLocation(userData.getLocation());//state
						    vo.setNumber(userData.getNumber());//district
						    vo.setMemberShipNo(userData.getMemberShipNo());//constituency
						    vo.setPercentStr(userData.getPercentStr());
						    vo.setUname(nameMap.get(key));
						    vo.setTabNo(tabMap.get(key));
						}
						List<CadreRegisterInfo> daysList = new ArrayList<CadreRegisterInfo>();
						for(Date date:datesList){
							if(dateMap.get(date) != null){
								CadreRegisterInfo day = dateMap.get(date);
							    if(count == 0){
								  day.setDate(sdf.format(date));
							    }
								daysList.add(day);
							}else{
								CadreRegisterInfo day = new CadreRegisterInfo();
								if(count == 0){
								   day.setDate(sdf.format(date));
								 }
								daysList.add(day);
							}
						}
						vo.setInfoList(daysList);
						if(vo.getInfoList() != null && vo.getInfoList().size() > 0)
						{
							Long count1 = 0l;
							for(CadreRegisterInfo vo3 : vo.getInfoList())
							{
								if(vo3.getTotalCount() != null)
								 count1 = count1 + vo3.getTotalCount();
							}
							vo.setTotalCount(count1);
							vo.setTotalAmount(vo.getTotalCount() > 0 ?  vo.getTotalCount()* 100 : 0);
						}
						returnList.add(vo);
						count++;
					}
				}
				
				
			}catch(Exception e){
				LOG.error("Exception rised in getCandidateDataCollectionInfo",e);
			}
			return returnList;
		}
		

		/*public List<CadreRegisterInfo> getCandidateDataCollectionInfoForOnlineUsers(Long locationType,List<Long> locationIds,Date fromDate,Date toDate,String sourceType,Long stateTypeId){
			List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
			CadreRegisterInfo returnVo = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat timeFormate = new SimpleDateFormat("HH:mm");
			try{
				
				List<Date> datesList = new ArrayList<Date>();
				//Map<Long,Map<Date,CadreRegisterInfo>> userMap = new HashMap<Long,Map<Date,CadreRegisterInfo>>();//Map<userId,Map<Date,info>>
				Map<Date,CadreRegisterInfo> dateMap = new HashMap<Date,CadreRegisterInfo>();//Map<Date,info>
				
				//0 count,1 name,2 min,3 max,4 date,5 id,6 name
				String parlimentName = null;
				Map<String,String> parliamentForAssemblyMap = new TreeMap<String, String>();
				List<Object[]> locationsList =  delimitationConstituencyAssemblyDetailsDAO.getPcListByRegion(0L);							
					
				List<SurveyTransactionVO> locationIdsList = new ArrayList<SurveyTransactionVO>();
				if(locationsList != null && locationsList.size()>0)
				{
					for (Object[] param : locationsList)
					{
						SurveyTransactionVO surveyTransactionVO = new SurveyTransactionVO();
						surveyTransactionVO.setId(param[0] != null ? Long.valueOf(param[0].toString()) :0L);
						surveyTransactionVO.setName(param[1] != null ? param[1].toString() :"");
						locationIdsList.add(surveyTransactionVO);
					}
				}
				List<Long> assemblyIds = new ArrayList<Long>(0);
				
				if(locationIdsList != null && locationIdsList.size()>0)
				{
					for (SurveyTransactionVO surveyTransctionVO : locationIdsList) 
					{
						List<Long> locationIdList = new ArrayList<Long>();
						locationIdList.add(surveyTransctionVO.getId());
						
						locationsList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesListForAListOfParliamentConstituency(locationIdList);
												
						if(locationsList != null && locationsList.size()>0)
						{
							for (Object[] param : locationsList)
							{
								assemblyIds.add(param[0] != null ? Long.valueOf(param[0].toString().trim()):0L );
								parliamentForAssemblyMap.put(param[1] != null ? param[1].toString().trim() :"", surveyTransctionVO.getName());
							}
						}	
						
					}
				}
				
				List<Object[]> dataCollectedInfo = null;
				List<Long> assemblyIdsList = new ArrayList<Long>(0);
				 if(locationType.longValue() == 4L )
				 {
					 Long parliamentID = locationIds.get(0) != null? locationIds.get(0):0L;
					 try {
							 if(parliamentID.longValue() >0 && locationIds.size() == 1 )
							 {
								 List<DelimitationConstituency> delimitationConstituencyList = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyID(parliamentID);
									DelimitationConstituency parliamentConstituency = delimitationConstituencyList.get(0);
									parlimentName = parliamentConstituency.getConstituency().getName();
									assemblyIdsList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliament(parliamentID);
							 }
							 else
							 {
								// assemblyIdsList.addAll(assemblyIds);
								 if(locationIds != null && locationIds.size()>0)
								 {
									 for (Long parliamentId : locationIds) 
									 {
											assemblyIdsList.addAll(delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliament(parliamentId));
									 }
								 }
							 }
							 
						} catch (Exception e) {}
					 
					 locationIds.clear();
					 locationIds.addAll(assemblyIdsList);
						
					 dataCollectedInfo = tdpCadreDAO.getCandidateDataCollectionInfo1(3L,assemblyIdsList,fromDate, toDate,sourceType);
				 }
				 else
				 {
					 dataCollectedInfo = tdpCadreDAO.getCandidateDataCollectionInfo1(locationType,locationIds,fromDate, toDate,sourceType);
				 }
				 
				 
				for(Object[] data:dataCollectedInfo){
					if(!datesList.contains((Date)data[3])){
						datesList.add((Date)data[3]);
					}
					CadreRegisterInfo vo = dateMap.get((Date)data[3]);
					if(vo == null){
						vo = new CadreRegisterInfo() ;
						vo.setArea(convertTimeTo12HrsFormat(timeFormate.format((Date)data[1])));
						vo.setLocation(convertTimeTo12HrsFormat(timeFormate.format((Date)data[2])));
						vo.setTotalCount((Long)data[0]);
						vo.setAmount(vo.getTotalCount() * 100);
						dateMap.put((Date)data[3], vo);
					}
					
				}
				
				
					int count = 0;
					returnVo = new CadreRegisterInfo();
					List<CadreRegisterInfo> daysList = new ArrayList<CadreRegisterInfo>();
						for(Date date:datesList){
							if(dateMap.get(date) != null){
								CadreRegisterInfo day = dateMap.get(date);
							    if(count == 0){
								  day.setDate(sdf.format(date));
							    }
								daysList.add(day);
							}else{
								CadreRegisterInfo day = new CadreRegisterInfo();
								if(count == 0){
								   day.setDate(sdf.format(date));
								 }
								daysList.add(day);
							}
						}
						returnVo.setInfoList(daysList);
						if(returnVo.getInfoList() != null && returnVo.getInfoList().size() > 0)
						{
							Long count1 = 0l;
							for(CadreRegisterInfo vo3 : returnVo.getInfoList())
							{
								if(vo3.getTotalCount() != null)
								 count1 = count1 + vo3.getTotalCount();
							}
							returnVo.setTotalCount(count1);
							returnVo.setTotalAmount(returnVo.getTotalCount() > 0 ?  returnVo.getTotalCount()* 100 : 0);
						}
						returnList.add(returnVo);
						count++;
					
			}catch(Exception e){
				LOG.error("Exception rised in getCandidateDataCollectionInfo",e);
			}
			return returnList;
		}*/
		public void setDataForOnlineUsers(List<Object[]> dataCollectedInfo,List<CadreRegisterInfo> returnList)
		{
			CadreRegisterInfo returnVo = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat timeFormate = new SimpleDateFormat("HH:mm");
			try{
				List<Long> surveyUserIds = new ArrayList<Long>();
				List<Date> datesList = new ArrayList<Date>();
				//Map<Long,Map<Date,CadreRegisterInfo>> userMap = new HashMap<Long,Map<Date,CadreRegisterInfo>>();//Map<userId,Map<Date,info>>
				Map<Date,CadreRegisterInfo> dateMap = new HashMap<Date,CadreRegisterInfo>();//Map<Date,info>
				for(Object[] data:dataCollectedInfo){
					if(!datesList.contains((Date)data[3])){
						datesList.add((Date)data[3]);
					}
					CadreRegisterInfo vo = dateMap.get((Date)data[3]);
					if(vo == null){
						vo = new CadreRegisterInfo() ;
						vo.setArea(convertTimeTo12HrsFormat(timeFormate.format((Date)data[1])));
						vo.setLocation(convertTimeTo12HrsFormat(timeFormate.format((Date)data[2])));
						vo.setTotalCount((Long)data[0]);
						vo.setAmount(vo.getTotalCount() * 100);
						dateMap.put((Date)data[3], vo);
					}
				}
				int count = 0;
				returnVo = new CadreRegisterInfo();
				List<CadreRegisterInfo> daysList = new ArrayList<CadreRegisterInfo>();
					for(Date date:datesList){
						if(dateMap.get(date) != null){
							CadreRegisterInfo day = dateMap.get(date);
						    if(count == 0){
							  day.setDate(sdf.format(date));
						    }
							daysList.add(day);
						}else{
							CadreRegisterInfo day = new CadreRegisterInfo();
							if(count == 0){
							   day.setDate(sdf.format(date));
							 }
							daysList.add(day);
						}
					}
					returnVo.setInfoList(daysList);
					if(returnVo.getInfoList() != null && returnVo.getInfoList().size() > 0)
					{
						Long count1 = 0l;
						for(CadreRegisterInfo vo3 : returnVo.getInfoList())
						{
							if(vo3.getTotalCount() != null)
							 count1 = count1 + vo3.getTotalCount();
						}
						returnVo.setTotalCount(count1);
						returnVo.setTotalAmount(returnVo.getTotalCount() > 0 ?  returnVo.getTotalCount()* 100 : 0);
					}
					returnList.add(returnVo);
					count++;
				
			}
			catch (Exception e) {
				LOG.error("Exception rised in setDataForOnlineUsers",e);
			}
		}

		@SuppressWarnings("unchecked")
		public List<CadreRegisterInfo> getCandidateDataCollectionInfoForOnlineUsers(Long locationType,List<Long> locationIds,Date fromDate,Date toDate,String sourceType,Long stateTypeId){
			List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();
			CadreRegisterInfo returnVo = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat timeFormate = new SimpleDateFormat("HH:mm");
			try{
				List<Date> datesList = new ArrayList<Date>();
				//Map<Long,Map<Date,CadreRegisterInfo>> userMap = new HashMap<Long,Map<Date,CadreRegisterInfo>>();//Map<userId,Map<Date,info>>
				Map<Date,CadreRegisterInfo> dateMap = new HashMap<Date,CadreRegisterInfo>();//Map<Date,info>
				Map<Long,Map<Date,CadreRegisterInfo>> userMap = new HashMap<Long,Map<Date,CadreRegisterInfo>>();//Map<userId,Map<Date,info>>
				//0 count,1 name,2 min,3 max,4 date,5 id,6 name
				Map<String,String> parliamentForAssemblyMap = new TreeMap<String, String>();
				List<Object[]> locationsList =  delimitationConstituencyAssemblyDetailsDAO.getPcListByRegion(0L);							
					
				List<SurveyTransactionVO> locationIdsList = new ArrayList<SurveyTransactionVO>();
				if(locationsList != null && locationsList.size()>0)
				{
					for (Object[] param : locationsList)
					{
						SurveyTransactionVO surveyTransactionVO = new SurveyTransactionVO();
						surveyTransactionVO.setId(param[0] != null ? Long.valueOf(param[0].toString()) :0L);
						surveyTransactionVO.setName(param[1] != null ? param[1].toString() :"");
						locationIdsList.add(surveyTransactionVO);
					}
				}
				List<Long> assemblyIds = new ArrayList<Long>(0);
				
				if(locationIdsList != null && locationIdsList.size()>0)
				{
					for (SurveyTransactionVO surveyTransctionVO : locationIdsList) 
					{
						List<Long> locationIdList = new ArrayList<Long>();
						locationIdList.add(surveyTransctionVO.getId());
						
						locationsList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesListForAListOfParliamentConstituency(locationIdList);
												
						if(locationsList != null && locationsList.size()>0)
						{
							for (Object[] param : locationsList)
							{
								assemblyIds.add(param[0] != null ? Long.valueOf(param[0].toString().trim()):0L );
								parliamentForAssemblyMap.put(param[1] != null ? param[1].toString().trim() :"", surveyTransctionVO.getName());
							}
						}	
						
					}
				}
				locationsList.clear();
				StringBuilder queryStr = new StringBuilder();
				
				if(locationType.longValue() == 0L)
				{
					locationsList = constituencyDAO.getAllAssemblyConstituenciesByStateTypeId(stateTypeId,1L,null);
					
					queryStr.append(" select  count(model.tdpCadreId),min(model.surveyTime),max(model.surveyTime),date(model.surveyTime),model2.constituency.constituencyId ,model2.constituency.name ");		
					queryStr.append(" from TdpCadre model,UserAddress model2 where model.enrollmentYear = 2014  " );
					queryStr.append(" and model.isDeleted = 'N' and ( date(model.surveyTime) >=:fromDate and date(model.surveyTime) <=:toDate  ) and ");
					queryStr.append(" model.userAddress.userAddressId = model2.userAddressId ");
					queryStr.append(" and model.dataSourceType = :sourceType and model2.constituency.constituencyId in (:locationIds)  ");
					queryStr.append(" group by date(model.surveyTime), model2.constituency.constituencyId order by date(model.surveyTime) "); 
					
				}
				else if(locationType.longValue() == 1L)
				{
					if(stateTypeId == 1L)
					{
						locationsList = constituencyDAO.getAllAssemblyConstituenciesByStateTypeId(locationIds.get(0),1L,null);
					}
					else if(stateTypeId == 2L)
					{
						locationsList = constituencyDAO.getAllAssemblyConstituenciesByStateTypeId(stateTypeId,1L,null);
					}
					else
					{
						locationsList = constituencyDAO.getAllAssemblyConstituenciesByStateTypeId(stateTypeId,1L,null);
					}
					
					queryStr.append(" select  count(model.tdpCadreId),min(model.surveyTime),max(model.surveyTime),date(model.surveyTime),model2.constituency.constituencyId,model2.constituency.name ");		
					queryStr.append(" from TdpCadre model,UserAddress model2 where model.enrollmentYear = 2014  " );
					queryStr.append(" and model.isDeleted = 'N' and ( date(model.surveyTime) >=:fromDate and date(model.surveyTime) <=:toDate  ) and ");
					queryStr.append(" model.userAddress.userAddressId = model2.userAddressId ");
					queryStr.append(" and model.dataSourceType = :sourceType and model2.constituency.constituencyId in (:locationIds)  ");
					queryStr.append(" group by date(model.surveyTime), model2.constituency.constituencyId order by date(model.surveyTime) "); 
					
				}
				else if(locationType.longValue() == 2L)
				{
						locationsList = districtDAO.getDistrictDetailsByDistrictIds(locationIds);
						
						queryStr.append(" select  count(model.tdpCadreId),min(model.surveyTime),max(model.surveyTime),date(model.surveyTime),model2.district.districtId,model2.district.districtName, model2.constituency.constituencyId,model2.constituency.name ");		
						queryStr.append(" from TdpCadre model,UserAddress model2 where model.enrollmentYear = 2014  " );
						queryStr.append(" and model.isDeleted = 'N' and ( date(model.surveyTime) >=:fromDate and date(model.surveyTime) <=:toDate  ) and ");
						queryStr.append(" model.userAddress.userAddressId = model2.userAddressId ");
						queryStr.append(" and model.dataSourceType = :sourceType and model2.district.districtId in (:locationIds)  ");
						queryStr.append(" group by date(model.surveyTime), model2.district.districtId order by date(model.surveyTime) "); 
						
				}
				else if(locationType.longValue() == 3L)
				{
						locationsList = constituencyDAO.getConstituencyInfoByConstituencyIdList(locationIds);
						
						queryStr.append(" select  count(model.tdpCadreId),min(model.surveyTime),max(model.surveyTime),date(model.surveyTime),model2.constituency.constituencyId,model2.constituency.name ");		
						queryStr.append(" from TdpCadre model,UserAddress model2 where model.enrollmentYear = 2014  " );
						queryStr.append(" and model.isDeleted = 'N' and ( date(model.surveyTime) >=:fromDate and date(model.surveyTime) <=:toDate  ) and ");
						queryStr.append(" model.userAddress.userAddressId = model2.userAddressId ");
						queryStr.append(" and model.dataSourceType = :sourceType and model2.constituency.constituencyId in (:locationIds)  ");
						queryStr.append(" group by date(model.surveyTime), model2.constituency.constituencyId order by date(model.surveyTime) "); 
				}
				
				if(locationsList != null && locationsList.size()>0)
				{
					locationIds.clear();
					for (Object[] param : locationsList)
					{
						locationIds.add(param[0] != null ? Long.valueOf(param[0].toString().trim()):0L );
					}
				}	
				
				 if(locationType.longValue() == 4L )
				 {
					 	queryStr.append(" select  count(model.tdpCadreId), min(model.surveyTime), max(model.surveyTime), date(model.surveyTime), " );
					 	queryStr.append(" model.userAddress.constituency.constituencyId, model.userAddress.constituency.name ");		
						queryStr.append(" from TdpCadre model,DelimitationConstituencyAssemblyDetails model3 where model.enrollmentYear = 2014  " );
						queryStr.append(" and model3.constituency.constituencyId = model.userAddress.constituency.constituencyId ");
						queryStr.append(" and model.isDeleted = 'N' and ( date(model.surveyTime) >=:fromDate and date(model.surveyTime) <=:toDate  ) ");						
						queryStr.append(" and model.dataSourceType = :sourceType and model3.delimitationConstituency.constituency.constituencyId in (:locationIds) ");
						queryStr.append(" and model3.delimitationConstituency.year = 2009 ");						
						queryStr.append(" group by date(model.surveyTime), model3.delimitationConstituency.constituency.constituencyId order by date(model.surveyTime) desc "); 
						
				 }
				List<Object[]>  dataCollectedInfo = tdpCadreDAO.getCandidateDataCollectionInfoForOnline(locationType,locationIds,fromDate, toDate,sourceType,queryStr.toString());
				Map<Long,String> constiNameMap = new HashMap<Long, String>();
				Map<Long,String> districtNameMap = new HashMap<Long, String>();
				for(Object[] data:dataCollectedInfo){
					if(!datesList.contains((Date)data[3])){
						datesList.add((Date)data[3]);
						
					}
					dateMap = userMap.get((Long)data[4]);
						if(dateMap == null)
						{
							dateMap = new HashMap<Date,CadreRegisterInfo>();
							userMap.put((Long)data[4],dateMap);
							constiNameMap.put((Long)data[4], data[5].toString());
							if(locationType == 2L)
								districtNameMap.put((Long)data[4], data[7].toString());
						}
						
						CadreRegisterInfo vo = dateMap.get((Date)data[3]);
						if(vo == null){
							vo = new CadreRegisterInfo() ;
							vo.setArea(convertTimeTo12HrsFormat(timeFormate.format((Date)data[1])));
							vo.setLocation(convertTimeTo12HrsFormat(timeFormate.format((Date)data[2])));
							vo.setTotalCount((Long)data[0]);
							vo.setAmount(vo.getTotalCount() * 100);
							dateMap.put((Date)data[3], vo);
						}
						
					}
					
					
				
				int count = 0;
				for(Long key:userMap.keySet()){
					dateMap = userMap.get(key);
					returnVo = new CadreRegisterInfo();
					returnVo.setId(key);
					returnVo.setName(constiNameMap.get(key));
					if(locationType != 2L)
					{
						returnVo.setParliament(parliamentForAssemblyMap.get(returnVo.getName()));
						
						Constituency constituency = constituencyDAO.get(key);
						if(constituency != null)
						{
							if(constituency.getDistrict() != null)
							{
								returnVo.setDistrict(constituency.getDistrict().getDistrictName());
								
								if(constituency.getDistrict().getDistrictId()<11)
								{
									returnVo.setState("TG");
								}
								if(constituency.getDistrict().getDistrictId()>10 && constituency.getDistrict().getDistrictId() < 24)
								{
									returnVo.setState("AP");
								}
							}
						}
						
					}
					else
					{
						returnVo.setParliament(parliamentForAssemblyMap.get(districtNameMap.get(key)));
						returnVo.setMemberShipNo(districtNameMap.get(key));
						
						District district = districtDAO.get(key);
						if(district != null)
						{							
								returnVo.setDistrict(district.getDistrictName());
								
								if(district.getDistrictId()<11)
								{
									returnVo.setState("TG");
								}
								if(district.getDistrictId()>10 && district.getDistrictId() < 24)
								{
									returnVo.setState("AP");
								}
							
						}
						
					}
					
					
					List<CadreRegisterInfo> daysList = new ArrayList<CadreRegisterInfo>();
						for(Date date:datesList){
							if(dateMap.get(date) != null){
								CadreRegisterInfo day = dateMap.get(date);
							    if(count == 0){
								  day.setDate(sdf.format(date));
							    }
								daysList.add(day);
							}else{
								CadreRegisterInfo day = new CadreRegisterInfo();
								if(count == 0){
								   day.setDate(sdf.format(date));
								 }
								daysList.add(day);
							}
						}
						returnVo.setInfoList(daysList);
						if(returnVo.getInfoList() != null && returnVo.getInfoList().size() > 0)
						{
							Long count1 = 0l;
							for(CadreRegisterInfo vo3 : returnVo.getInfoList())
							{
								if(vo3.getTotalCount() != null)
								 count1 = count1 + vo3.getTotalCount();
							}
							returnVo.setTotalCount(count1);
							returnVo.setTotalAmount(returnVo.getTotalCount() > 0 ?  returnVo.getTotalCount()* 100 : 0);
						}
						returnList.add(returnVo);
						count++;
						
			}
					
						
			}catch(Exception e){
				LOG.error("Exception rised in getCandidateDataCollectionInfo",e);
			}
			return returnList;
		}
		public List<GenericVO> getInactiveUsersListDetails(String hours, String accessType, String accessValue)
		{
			 List<GenericVO> totalDetails=null;
			try
			{
				List<Long> assemblyIds = null;
				assemblyIds = new ArrayList<Long>();
				
					assemblyIds = new ArrayList<Long>();
					if(accessType.equalsIgnoreCase("MLA"))
					{
						List<Long> consti = new ArrayList<Long>();
						consti.add(Long.valueOf(accessValue));
						assemblyIds.add(Long.valueOf(accessValue));
					}
					else if(accessType.equalsIgnoreCase("MP"))
					{
						List<Long> parlis = new ArrayList<Long>();
						parlis.add(Long.valueOf(accessValue));
						List<Object[]> asslyList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesListForAListOfParliamentConstituency(parlis);
						if(asslyList!=null && asslyList.size()>0){
							for(Object[] obj:asslyList){
								assemblyIds.add(Long.valueOf(obj[0].toString()));
							}
						}
					}
					else if(accessType.equalsIgnoreCase("DISTRICT"))
					{
						List<Object[]> asslyList = constituencyDAO.getConstituenciesByDistrictId(Long.valueOf(accessValue));
						if(asslyList!=null && asslyList.size()>0){
							for(Object[] obj:asslyList){
								assemblyIds.add(Long.valueOf(obj[0].toString()));
							}
						}
					}
					else if(accessType.equalsIgnoreCase("STATE"))
					{						
						List<Object[]> asslyList = constituencyDAO.getConstituencyByState(Long.valueOf(accessValue));
						if(asslyList!=null && asslyList.size()>0){
							for(Object[] obj:asslyList){
								assemblyIds.add(Long.valueOf(obj[0].toString()));
							}
						}
					}
				totalDetails=(List<GenericVO>)getInActiveUsers(hours,"total",assemblyIds);
				return totalDetails;
				
			} catch (Exception e) {
				LOG.error("Exception rised in getInactiveUsersListDetails",e);
				return null;
			}
			
		}
		public SurveyTransactionVO getDaywiseWebUserDetails(Long userId,String FdateStr, String TdateStr,Long memberTypeId)
		{
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			//String FdateStr ="01-11-2014";
			//String TdateStr = "15-11-2014";
			SurveyTransactionVO returnVO = new SurveyTransactionVO();		
			
			try {
				List<SurveyTransactionVO> finalList = new ArrayList<SurveyTransactionVO>();
				List<SurveyTransactionVO> returnList = new ArrayList<SurveyTransactionVO>();
				Date fromDate = format.parse(FdateStr);
				Date toDate = format.parse(TdateStr);
				Map<String,Long> dateWiseAmountMap = new LinkedHashMap<String, Long>();
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(fromDate);
				dateWiseAmountMap.put(format.format(cal.getTime()), 0L);
				while (cal.getTime().before(toDate)) {
				    cal.add(Calendar.DATE, 1);
				    dateWiseAmountMap.put(format.format(cal.getTime()), 0L);
				}
				
				
				List<Object[]> acountInfo = cadreRegAmountDetailsDAO.getPaidAmountDetailsOfWebUserByDateANDType(userId, fromDate, toDate,"WEB");
				
				
				if(acountInfo != null && acountInfo.size()>0)
				{
					for (Object[] amount : acountInfo) 
					{
						String Date = amount[0] != null ? format.format(format1.parse(amount[0].toString().trim())):"";					
						
							dateWiseAmountMap.put(Date, amount[1] != null ? Long.valueOf(amount[1].toString().trim()):0L);
					
					}
				}
				
				List<Object[]> webUserRecordsList = tdpCadreDAO.getDaywiseWebuserDetailsByUserANDType(userId, fromDate, toDate,"WEB",memberTypeId);
				
				Long recordsCount = 0L;
				Long actualAmount = 0L;
				Long depositedAmount = 0L;
				Long remainingAmount = 0L;
						
				if(webUserRecordsList != null && webUserRecordsList.size()>0)
				{
					
					for (Object[] param : webUserRecordsList)
					{
						String Date = param[0] != null ? format.format(format1.parse(param[0].toString().trim())):"";
						SurveyTransactionVO vo = new SurveyTransactionVO();
						
						vo.setSurveyDate(Date);
						vo.setRecordsCount(param[1] != null ? Long.valueOf(param[1].toString()):0L);
						vo.setActualAmount(vo.getRecordsCount() * 100);					
						vo.setDepositedAmount(dateWiseAmountMap.get(Date) != null ? dateWiseAmountMap.get(Date) :0L);
						vo.setRemainingAmount(vo.getActualAmount() - vo.getDepositedAmount());
						
						returnList.add(vo);
						
						recordsCount = recordsCount + vo.getRecordsCount();
						actualAmount = actualAmount + vo.getActualAmount();
						depositedAmount = depositedAmount + vo.getDepositedAmount();
						remainingAmount = remainingAmount + vo.getRemainingAmount();
						
					}
				}
							
				if(dateWiseAmountMap != null && dateWiseAmountMap.size()>0)
				{
					for (String date : dateWiseAmountMap.keySet()) 
					{
						SurveyTransactionVO vo = getMatchedVOForDate(returnList,date);
						
						if(vo == null)
						{
							vo = new SurveyTransactionVO();							
							vo.setSurveyDate(date);
							vo.setRecordsCount(0L);
							vo.setActualAmount(0L);					
							vo.setDepositedAmount(0L);
							vo.setRemainingAmount(0L);
						}
						
						finalList.add(vo);
					}
				}
				
				returnVO.setRecordsCount(recordsCount);
				returnVO.setActualAmount(actualAmount);
				returnVO.setDepositedAmount(depositedAmount);
				returnVO.setRemainingAmount(remainingAmount);
				
				returnVO.setSurveyTransactionVOList(finalList);
				
				
			} catch (Exception e) {
				LOG.error(" exception occured at getDaywiseWebUserDetails() in CadreSurveyTransactionService service class. ", e);
			}
			return returnVO;
		}
		
		private SurveyTransactionVO getMatchedVOForDate(List<SurveyTransactionVO> list, String date)
		{
			SurveyTransactionVO returnVO = null;
			
			try {
				if(list != null && list.size()>0)
				{
					for (SurveyTransactionVO vo : list) 
					{
						if(vo.getSurveyDate().trim().equalsIgnoreCase(date.trim()))
						{
							return vo;
						}
					}
				}
			} catch (Exception e) {
				LOG.error(" exception occured at getMatchedVOForDate() in CadreSurveyTransactionService service class. ", e);
			}
			
			return returnVO;
		}
		
		public Object getInActiveUsers(String hours,String value,List<Long> constiIds)
		{
			 List<Long> inActiveUsersCount=null;
			 List<GenericVO> inActiveUsersDetails=null;
		  try
		  {
			  if(hours != null && hours.trim().length()>0 && !hours.equalsIgnoreCase("0")){
				  int hourCount = Integer.valueOf(hours);
				  
				  
				       //Last hours active list.
					    DateUtilService dateService1 = new DateUtilService();
						Date date1 = dateService1.getCurrentDateAndTime();
						Calendar cal1 = Calendar.getInstance();
						cal1.setTime(date1);
						cal1.add(Calendar.HOUR, -hourCount);
						Date hourBack = cal1.getTime();
					    List<Long>	lastHoursList=tdpCadreDAO.lastHoursActiveUsers(date1,hourBack,constiIds);
					    
						
					
				//total inactive users last one hour....started with 6am..and their details
					   //settingDate to today 6AM. 
						DateUtilService dateService = new DateUtilService();
						Date date = dateService.getCurrentDateAndTime();
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.set(Calendar.HOUR_OF_DAY,6);
						cal.set(Calendar.MINUTE, 0);
						cal.set(Calendar.SECOND,0);
						
						if(value.equalsIgnoreCase("count")){
						  if(lastHoursList!=null && lastHoursList.size()>0)
						     inActiveUsersCount=tdpCadreDAO.inActiveUsersCountInLastHours(cal.getTime(),lastHoursList,constiIds);
						   
						}
						else if(value.equalsIgnoreCase("total")){
							
							  Map<String,String> parliamentForAssemblyMap = new TreeMap<String, String>();
							  List<Long> parliamentIds = new ArrayList<Long>();
							  
								List<Object[]> locationsList =  delimitationConstituencyAssemblyDetailsDAO.getPcListByRegion(0L);							
									
								if(locationsList != null && locationsList.size()>0)
								{
									for (Object[] param : locationsList)
									{
										parliamentIds.add(param[0] != null ? Long.valueOf(param[0].toString()) :0L);
									}
								}
								
								if(parliamentIds != null && parliamentIds.size()>0)
								{
									locationsList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesListForAListOfParliamentConstituency(parliamentIds);
															
									if(locationsList != null && locationsList.size()>0)
									{
										for (Object[] param : locationsList)
										{
											parliamentForAssemblyMap.put(param[1] != null ? param[1].toString().trim() :"", param[2] != null ? param[2].toString().trim() :"");
										}
									}	
								}
								
						  List<Object[]> objList=tdpCadreDAO.inActiveUsersInLastHours(cal.getTime(),lastHoursList,constiIds);
						  if(objList!=null && objList.size()>0)
							{
							  inActiveUsersDetails=new ArrayList<GenericVO>();
								for (Object[] objects : objList)
								{
									GenericVO genericVO=new GenericVO();
									genericVO.setDesc(objects[0]!=null?objects[0].toString():"");//username
									genericVO.setName(objects[1]!=null?objects[1].toString():"");//name
									genericVO.setMobileNo(objects[2]!=null?objects[2].toString():"");//mobileno
									genericVO.setCaste(objects[3]!=null?objects[3].toString():"");//tabno									
									
									genericVO.setWorkedTime(objects[5]!=null?objects[5].toString():"");//constituencyName
									genericVO.setStartTime(objects[6]!=null?objects[6].toString():""); //district Name
									genericVO.setEndTime(parliamentForAssemblyMap.get(genericVO.getWorkedTime()));// parliament
									
									inActiveUsersDetails.add(genericVO);
									
								}
							 }
						 }
			
			  }
			 if(value.equalsIgnoreCase("count")){
				  
			      if(inActiveUsersCount!=null && inActiveUsersCount.size()>0)
				  {
					 return inActiveUsersCount.get(0);
				  }
			      else 
				   return null;
			 }
			 else if(value.equalsIgnoreCase("total")) {
			   return inActiveUsersDetails; 
			 }
			 else 
				return null;
		  }catch (Exception e){
			  LOG.error("Exception rised in getInActiveUsersCount",e);
			  return null;
		  }	
		  
			
		}
				
		public String updateTabAllocationDetails(Long authId,String cause,Long userId){
			try{
				tabLogInAuthDAO.updateStatus(authId, cause, userId);
				return "success";
			}catch(Exception e){
				LOG.error("Exception rised in updateTabAllocationDetails",e);
				return "error";
			}
		}
		
		public List<CadreRegisterInfo> getAuthDetails(Long id,String variable){
			List<CadreRegisterInfo> authDetails = new ArrayList<CadreRegisterInfo>();
			try{
				List<Object[]> usersList = null;
				CadreRegisterInfo info = null;
				if(id.longValue() == 1l){//for userName
					usersList = tabLogInAuthDAO.getAuthDetailsByUserId(variable);
				}else{//for imei
					//0authId,1userName,2name,3mobileNo,4imei
					usersList = tabLogInAuthDAO.getAuthDetailsByImei(variable);
				}
				if(usersList != null && usersList.size() > 0){
					for(Object[] user:usersList){
						info = new CadreRegisterInfo();
						info.setId((Long)user[0]);
						if(user[2] != null){
						   info.setName(user[2].toString());
						}else{
						  info.setName("");
						}
						info.setUname(user[1].toString());
						if(user[3] != null){
						  info.setNumber(user[3].toString());
						}else{
						  info.setNumber("");
						}
						if(user[4] != null){
						  info.setTabNo(user[4].toString());
						}
						authDetails.add(info);
					}
				}
			}catch(Exception e){
				LOG.error("Exception rised in getAuthDetails",e);
			}
			return authDetails;
		}
		public String registerAllUsers(RegistrationVO user){
			return registrationService.registerAllUsers(user);
			//registrationService.changepassword();
			//return "success";
		}

		
		public List<CadreRegisterInfo> getLocationWiseAgeRangeAndGenderCount(String type,Long stateId,Long constituencyId){
			List<CadreRegisterInfo> resultList = new ArrayList<CadreRegisterInfo>();
			try{
				List<Object[]> votersCountList = null;
				List<Object[]> totalRecords = null;
				List<Object[]> genderWiseCount = null;
				List<Long> districtIds = new ArrayList<Long>();
				List<Long> constituencyIds = new ArrayList<Long>();
				List<Long> tehsilIds = new ArrayList<Long>();
				List<Long> localbodyIds = new ArrayList<Long>();
				List<Long> panchayatIds = new ArrayList<Long>();
				List<Long> boothIds = new ArrayList<Long>();
		
				if(stateId == 2){
					for(int i=1;i<=10;i++)
						districtIds.add(new Long(i));
				}
				else if(stateId == 1){
				    for(int i=11;i<=23;i++)
						districtIds.add(new Long(i));
				}	
				if(type.equalsIgnoreCase(IConstants.DISTRICT))
				{					
					votersCountList = voterInfoDAO.getVotersCountInADistrictsList(districtIds,IConstants.VOTER_DATA_PUBLICATION_ID);
					totalRecords = tdpCadreDAO.getLocationWiseTotalRecords(districtIds,type);
					genderWiseCount = tdpCadreDAO.getLocationWiseGenderCadreCount(districtIds,type);					
					setLocationWiseGenderData(votersCountList,totalRecords,genderWiseCount,type,resultList,districtIds,constituencyIds);
					setLocationWiseAgeData(type,resultList,districtIds);
					
				}else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY)){
					votersCountList = voterInfoDAO.getVotersCountInConstituenciesByDistrictsList(districtIds,IConstants.VOTER_DATA_PUBLICATION_ID);
					totalRecords = tdpCadreDAO.getLocationWiseTotalRecords(districtIds,type);
					genderWiseCount = tdpCadreDAO.getLocationWiseGenderCadreCount(districtIds,type);
					setLocationWiseGenderData(votersCountList,totalRecords,genderWiseCount,type,resultList,districtIds,constituencyIds);	
					setLocationWiseAgeData(type,resultList,districtIds);
					
				}else if(type.equalsIgnoreCase(IConstants.TEHSIL) || type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY)){
					
					Constituency constituency = constituencyDAO.get(constituencyId);
					constituencyIds.add(constituencyId);
					if(constituency.getAreaType().equalsIgnoreCase(IConstants.RURAL)|| constituency.getAreaType().equalsIgnoreCase(IConstants.RURALURBAN))
					{
						 List<SelectOptionVO> list = regionServiceDataImp.getSubRegionsInConstituency(constituencyId,  IConstants.PRESENT_YEAR, constituency.getAreaType());
						 if(list != null && list.size() > 0)
							 for(SelectOptionVO vo : list)
							 {
								 if(vo.getId().toString().substring(0,1).trim().equalsIgnoreCase("2"))
									 tehsilIds.add(new Long(vo.getId().toString().substring(1)));
								 else
						    	  	localbodyIds.add(new Long(vo.getId().toString().substring(1)));
							 }
					}
					if(tehsilIds != null && tehsilIds.size() > 0)
					{					
					    votersCountList = voterInfoDAO.getVotersCountInATehsilList(tehsilIds,IConstants.VOTER_DATA_PUBLICATION_ID);
					    totalRecords = tdpCadreDAO.getLocationWiseTotalRecords(tehsilIds,IConstants.TEHSIL);
					    genderWiseCount = tdpCadreDAO.getLocationWiseGenderCadreCount(tehsilIds,IConstants.TEHSIL);
					    setLocationWiseGenderData(votersCountList,totalRecords,genderWiseCount,IConstants.TEHSIL,resultList,tehsilIds,constituencyIds);
					    setLocationWiseAgeData(type,resultList,tehsilIds);
					}
					if(localbodyIds != null && localbodyIds.size() > 0)
					{
						votersCountList = voterInfoDAO.getVotersCountInALocalBodyList(localbodyIds,IConstants.VOTER_DATA_PUBLICATION_ID);
						totalRecords = tdpCadreDAO.getLocationWiseTotalRecords(localbodyIds,IConstants.LOCAL_ELECTION_BODY);
						genderWiseCount = tdpCadreDAO.getLocationWiseGenderCadreCount(localbodyIds,IConstants.LOCAL_ELECTION_BODY);
						setLocationWiseGenderData(votersCountList,totalRecords,genderWiseCount,IConstants.LOCAL_ELECTION_BODY,resultList,localbodyIds,constituencyIds);
						setLocationWiseAgeData(type,resultList,localbodyIds);
					}
				}else if(type.equalsIgnoreCase(IConstants.PANCHAYAT)){
						Constituency constituency = constituencyDAO.get(constituencyId);
						constituencyIds.add(constituencyId);
						List<Object[]> panchayatsList = boothDAO.getPanchayatsByConstituencyAndPublication(constituencyId,IConstants.VOTER_DATA_PUBLICATION_ID);
						for(Object[] obj:panchayatsList){
								panchayatIds.add((Long)obj[0]);								
						}
						votersCountList = voterInfoDAO.getVotersCountInPanchayatList(panchayatIds,IConstants.VOTER_DATA_PUBLICATION_ID);
						totalRecords = tdpCadreDAO.getTotalRecords1(panchayatIds,IConstants.PANCHAYAT);
						genderWiseCount = tdpCadreDAO.getLocationWiseGenderCadreCount(panchayatIds,IConstants.PANCHAYAT);
						setLocationWiseGenderData(votersCountList,totalRecords,genderWiseCount,type,resultList,panchayatIds,constituencyIds);	
						setLocationWiseAgeData(type,resultList,panchayatIds);
				
				}else if(type.equalsIgnoreCase(IConstants.BOOTH)){
						Constituency constituency = constituencyDAO.get(constituencyId);
						constituencyIds.add(constituencyId);
						List<Object[]> boothsList = boothDAO.getBoothsInAConstituency(constituencyId,IConstants.VOTER_DATA_PUBLICATION_ID);
						for(Object[] obj:boothsList){
							boothIds.add((Long)obj[0]);								
					    }
						votersCountList = voterInfoDAO.getVotersCountInBoothsList(boothIds,IConstants.VOTER_DATA_PUBLICATION_ID);
						totalRecords = tdpCadreDAO.getTotalRecords1(boothIds,IConstants.BOOTH);
						genderWiseCount = tdpCadreDAO.getLocationWiseGenderCadreCount(boothIds,IConstants.BOOTH);
						setLocationWiseGenderData(votersCountList,totalRecords,genderWiseCount,type,resultList,boothIds,constituencyIds);
						setLocationWiseAgeData(type,resultList,boothIds);
				}
							
			}catch(Exception e){
				LOG.error("Exception rised in getLocationWiseAgeRangeAndGenderCount",e);
			}
			return resultList;
		}
		
		
		public CadreRegisterInfo getMatchedVO(List<CadreRegisterInfo> list,Long id)
		{
			try{
				if(list == null || list.size() == 0)
					return null;
				for(CadreRegisterInfo vo :list)
				{
					if(vo.getId().longValue() == id)
						return vo;
				}
			}
			catch (Exception e) {
				return null;
			}
			return null;
		}	
		
		
		public void setLocationWiseGenderData(List<Object[]> votersCountList,List<Object[]> totalRecords,List<Object[]> genderWiseCount,String type,List<CadreRegisterInfo> resultList,List<Long> ids,List<Long> constituencyIds)
		{
			if(votersCountList != null && votersCountList.size() > 0){
				for(Object[] params :votersCountList)
				{
					CadreRegisterInfo vo = new CadreRegisterInfo();
					vo.setId((Long)params[0]);						
					vo.setName(params[1] != null ? params[1].toString() : "");	
					vo.setVotersCount((Long)params[2]);
					
					if(type.equalsIgnoreCase(IConstants.DISTRICT)){
						vo.setMaleVotersCount((Long)params[3]);
						vo.setFemaleVotersCount((Long)params[4]);
					}
					else{
						vo.setMaleVotersCount((Long)params[4]);
						vo.setFemaleVotersCount((Long)params[5]);
					}
					if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
						constituencyIds.add((Long)params[0]);
					
					resultList.add(vo);
				}				
			}
			if(totalRecords != null && totalRecords.size() > 0){
				for(Object[] params : totalRecords)
				{
					CadreRegisterInfo vo = getMatchedVO(resultList,(Long)params[1]);
					  if(vo != null)
					  {
						  vo.setRegisteredCount(params[0] != null ? (Long)params[0] : 0);
						  vo.setFemaleCadreCount(0L);
						  vo.setMaleCadreCount(0L);
					  }
				}					
			}
			if(genderWiseCount != null && genderWiseCount.size() > 0){
				for(Object[] params : genderWiseCount)
				{
					CadreRegisterInfo vo = getMatchedVO(resultList,(Long)params[2]);
					  if(vo != null && params[1] != null)
					  {
						  if(params[1].toString().equalsIgnoreCase("M") || params[1].toString().equalsIgnoreCase("Male"))
						    vo.setMaleCadreCount(params[0] != null ? (Long)params[0]+vo.getMaleCadreCount() : vo.getMaleCadreCount());
						  else if(params[1].toString().equalsIgnoreCase("F") || params[1].toString().equalsIgnoreCase("Female"))
							vo.setFemaleCadreCount(params[0] != null ? (Long)params[0]+vo.getFemaleCadreCount() : vo.getFemaleCadreCount());
						  
					  }
				}					
			}
			
			
			if(constituencyIds != null && constituencyIds.size() > 0)
			{
				List<Object[]> list = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentsAndDistrictForAssembly(constituencyIds);
				if(list != null && list.size() > 0)
				{
					for(Object[] params : list)
					{
						CadreRegisterInfo vo1 = getMatchedVO(resultList,(Long)params[0]);
						if(vo1 != null){
							vo1.setParliamentId((Long)params[1]);
							vo1.setParliament(params[2] != null ? params[2].toString() : "");
							vo1.setDistrictId((Long)params[3]);
							vo1.setDistrict(params[4] != null ? params[4].toString() : "");
							vo1.setConstituency(params[5] != null ? params[5].toString() : "");													
						}
					}
				}
			}
		
		}

		public void setLocationWiseAgeData(String type,List<CadreRegisterInfo> resultList,List<Long> ids) {
			
			try {	
				
				List<Object[]>  voterAgeRangeCount = boothPublicationVoterDAO.getLocationWiseVoterAgeRangeCount(ids,type,IConstants.VOTER_DATA_PUBLICATION_ID);
			
				for(Object[] obj: voterAgeRangeCount){					
					
					CadreRegisterInfo vo = getMatchedVO(resultList,(Long)obj[3]);
					if(vo != null){						
							CadreRegisterInfo ageRangeVo = new CadreRegisterInfo();
							ageRangeVo.setName(obj[2].toString());
							ageRangeVo.setId((Long)obj[1]);
							ageRangeVo.setAgeRangeVoterValues(obj[0] != null ? (Long)obj[0] :0);	
							vo.getAllDetailsList().add(ageRangeVo);
							if(vo.getRegisteredCount() >0 ){
							vo.setPercentStr(new BigDecimal((vo.getFemaleCadreCount().doubleValue() / vo.getRegisteredCount().doubleValue()) * 100.0).setScale(2, BigDecimal.ROUND_HALF_UP).toString());							
							vo.setStatus(new BigDecimal((vo.getMaleCadreCount().doubleValue() / vo.getRegisteredCount().doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
							}
							if(vo.getVotersCount()>0){
							vo.setNumber(new BigDecimal((vo.getFemaleVotersCount().doubleValue() / vo.getVotersCount().doubleValue()) * 100.0).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
							vo.setUname(new BigDecimal((vo.getMaleVotersCount().doubleValue() / vo.getVotersCount().doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
							}
					}					
				}
											
				List<Object[]> ageRange18 = tdpCadreDAO.getLocationWiseAgeRangeCount(ids,"below 18",type);				
				setAgeRanges(ageRange18,"below 18",resultList);
				List<Object[]> ageRange18to25 = tdpCadreDAO.getLocationWiseAgeRangeCount(ids,"18-25",type);
				setAgeRanges(ageRange18to25,"18-25",resultList);
				List<Object[]> ageRange26to35 = tdpCadreDAO.getLocationWiseAgeRangeCount(ids,"26-35",type);
				setAgeRanges(ageRange26to35,"26-35",resultList);
				List<Object[]> ageRange36to45 = tdpCadreDAO.getLocationWiseAgeRangeCount(ids,"36-45",type);
				setAgeRanges(ageRange36to45,"36-45",resultList);
				List<Object[]> ageRange46to60 = tdpCadreDAO.getLocationWiseAgeRangeCount(ids,"46-60",type);
				setAgeRanges(ageRange46to60,"46-60",resultList);
				List<Object[]> ageRange60 = tdpCadreDAO.getLocationWiseAgeRangeCount(ids,"above 60",type);
				setAgeRanges(ageRange60,"above 60",resultList);
				
			} catch (Exception e) {
				LOG.error("Exception rised in getLocationsWiseAgeRangeCount", e);
			}
			
		}
		
		public void setAgeRanges(List<Object[]> result,String ageRange,List<CadreRegisterInfo> resultList) {
			
			if (result!=null && result.size() > 0){		
				
				for(Object[] obj : result){
					CadreRegisterInfo vo = getMatchedVO(resultList,(Long)obj[1]);
									
					if(vo != null){	
						List<CadreRegisterInfo> subList = vo.getAllDetailsList();
					
						for(CadreRegisterInfo info :subList){							
								
								 if(ageRange.equals("18-25") && info.getId()== 2 ){
									
									info.setAgeRangeCadreValues(obj[0] != null ? (Long)obj[0] : 0);	
									if(vo.getRegisteredCount() > 0 )
									info.setPercentStr( new BigDecimal((info.getAgeRangeCadreValues().doubleValue() / vo.getRegisteredCount().doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
									if( vo.getVotersCount() > 0)
									info.setNumber(new BigDecimal((info.getAgeRangeVoterValues().doubleValue() / vo.getVotersCount().doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
									
								}
								else if(ageRange.equals("26-35") && info.getId()== 3 ){
									info.setAgeRangeCadreValues(obj[0] != null ? (Long)obj[0] :0);
									if(vo.getRegisteredCount() > 0 )
									info.setPercentStr(new BigDecimal((info.getAgeRangeCadreValues().doubleValue() / vo.getRegisteredCount().doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
									if( vo.getVotersCount() > 0)
									info.setNumber(new BigDecimal((info.getAgeRangeVoterValues().doubleValue() / vo.getVotersCount().doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
									
								}
								else if(ageRange.equals("36-45") && info.getId()== 4 ){
									info.setAgeRangeCadreValues(obj[0] != null ? (Long)obj[0] : 0);
									if(vo.getRegisteredCount() > 0)
									info.setPercentStr(new BigDecimal((info.getAgeRangeCadreValues().doubleValue() / vo.getRegisteredCount().doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
									if( vo.getVotersCount() > 0)
									info.setNumber(new BigDecimal((info.getAgeRangeVoterValues().doubleValue() / vo.getVotersCount().doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
									
								}
								else if(ageRange.equals("46-60") && info.getId()== 5){
									info.setAgeRangeCadreValues(obj[0] != null ? (Long)obj[0] :0);
									if(vo.getRegisteredCount() > 0)
									info.setPercentStr(new BigDecimal((info.getAgeRangeCadreValues().doubleValue() / vo.getRegisteredCount().doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
									if( vo.getVotersCount() > 0)
									info.setNumber(new BigDecimal((info.getAgeRangeVoterValues().doubleValue() / vo.getVotersCount().doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
								
								}
								else if(ageRange.equals("above 60") && info.getId()== 6 ){
									info.setAgeRangeCadreValues(obj[0] != null ? (Long)obj[0] :0);
									if(vo.getRegisteredCount() > 0 )
									info.setPercentStr(new BigDecimal((info.getAgeRangeCadreValues().doubleValue() / vo.getRegisteredCount().doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
									if( vo.getVotersCount() > 0)
									info.setNumber(new BigDecimal((info.getAgeRangeVoterValues().doubleValue() / vo.getVotersCount().doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
									
								}
								
						}
										
					}
							
				}
			}  			
		} 
		
		public List<CadreRegisterInfo> getRegisteredCountByUserForHourWise(Date fromDate,Date toDate)
		{
			List<CadreRegisterInfo> returnList = new ArrayList<CadreRegisterInfo>();			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			try{
				
				List<Date> datesList = new ArrayList<Date>();
				List<Long> userIds = new ArrayList<Long>();
				Map<Long,Map<Date,Map<Long,Long>>> userMap = new HashMap<Long, Map<Date,Map<Long,Long>>>();
				Map<Date,Map<Long,Long>> dateMap = null;
				Map<Long,CadreRegisterInfo> userNames = new HashMap<Long,CadreRegisterInfo>();
			
				//0 count,1 userId,2 hour,3 date,4 name
				List<Object[]> dataCollectedInfo = tdpCadreDAO.getRegisteredCountByHourWise(fromDate, toDate);
				
				if(dataCollectedInfo != null && dataCollectedInfo.size() > 0){
				for(Object[] data:dataCollectedInfo){
					if(!datesList.contains((Date)data[3])){
						datesList.add((Date)data[3]);
					}
					if(!userIds.contains((Long)data[1])){
							userIds.add((Long)data[1]);	
					}
				
				
					dateMap = userMap.get((Long)data[1]);
					if(dateMap == null){
						dateMap = new HashMap<Date, Map<Long,Long>>();
						userMap.put((Long)data[1],dateMap);
						
					}
					Map<Long,Long> hourMap = dateMap.get((Date)data[3]);
					if(hourMap == null){
						hourMap = new HashMap<Long, Long>();
						dateMap.put((Date)data[3], hourMap);
					}					
					hourMap.put(Long.parseLong(String.valueOf(data[2])),(Long)data[0]);
					
				}
				}
				if(userIds.size() > 0){
					List<Object[]> userDetails = cadreSurveyUserDAO.getCadreSurveyUserDetails(userIds);				
					for(Object[] obj : userDetails){
						CadreRegisterInfo userData = new CadreRegisterInfo();
						userData.setId((Long)obj[0]);
						userData.setName(obj[1].toString());
						userData.setUname(obj[2] != null ? obj[2].toString() : "");
						userData.setConstituency(obj[5] != null ? obj[5].toString() : "");
						userData.setDistrict(obj[7] != null ? obj[7].toString() : "");
						userNames.put((Long)obj[0], userData);
					}	
				}				
				CadreRegisterInfo userVo = null;
				for(Long key:userMap.keySet()){
					dateMap = userMap.get(key);
					userVo = userNames.get(key);
					//userVo = new CadreRegisterInfo();
					userVo.setId(key);
					
					List<CadreRegisterInfo> daysList = new ArrayList<CadreRegisterInfo>();
					for(Date date:datesList){
						CadreRegisterInfo day = new CadreRegisterInfo();
						day.setDate(date.toString());					
						if(dateMap.get(date) != null){							
							Map<Long, Long> hours =  dateMap.get(date);								
							for(int i=0;i<=23;i++){
								Long count = hours.get(new Long(i)); 
								if(count == null)
									day.getHours().add(0l);	
								else
									day.getHours().add(count);
							}									
						}					
						daysList.add(day);						
					}
					userVo.setInfoList(daysList);			
					returnList.add(userVo);					
				}				
			}catch(Exception e){
				LOG.error("Exception rised in getRegisteredCountByUserForHourWise",e);
			}
			
			return returnList;
		}
		
		public List<CadreRegisterInfo> getHours()
		{
			List<CadreRegisterInfo>  resultList = new ArrayList<CadreRegisterInfo>();
			try{			
				for(int i=0;i<=23;i++){
					CadreRegisterInfo vo = new CadreRegisterInfo();
					vo.setId(new Long(i));
					if(i<12){
						if(i==0)
							vo.setName(12 +"AM");
						else
							vo.setName(i +"AM");					
					}else if(i >= 12){
						if(i==12)
							vo.setName(12 +"PM");
						else
							vo.setName(i - 12 +"PM");
					}
					resultList.add(vo);
			   }
			}catch (Exception e) {
		
			}
			return resultList;
		}
		
		public List<CadreRegisterInfo> getDistrictWiseAgeRangeCountByAccess(Long districtId,String accessType,String accessValue) {
			List<CadreRegisterInfo> ageWiseTotalList=new ArrayList<CadreRegisterInfo>();
			
			try {
				List<Long> constiIds = new ArrayList<Long>();
				
				if(accessType.equalsIgnoreCase("MLA")){
					constiIds.add(Long.valueOf(accessValue));
				
				}
				else if(accessType.equalsIgnoreCase("MP")){
					List<Long> parliamentConstiId = new ArrayList<Long>();
					parliamentConstiId.add(Long.valueOf(accessValue));
					List<Object[]> list = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesListForAListOfParliamentConstituency(parliamentConstiId);
									
					if(list!=null && list.size()>0){
						for(Object[] obj:list){
							constiIds.add(Long.valueOf(obj[0].toString()));
						}
					}
				}
				 List<Object[]> countsList = tdpCadreDAO.getAgeTotalCountByAccessType(districtId,constiIds);
					Long totalDistrictCount2014 = null;
					Long totalDistrictCount2012 = null;
					for(Object[] counts:countsList){
						if(counts[1] != null){
							if(((Long)counts[1]).longValue() == 2012l){
								totalDistrictCount2012 = (Long)counts[0];
							}else if(((Long)counts[1]).longValue() == 2014l){
								totalDistrictCount2014 = (Long)counts[0];
							}
						}
					}
				
				List<Object[]> cadreBelow18 = tdpCadreDAO.getAgeRangeCadreCountByAccessType(districtId, "below 18",constiIds);			
				setAgeWiseRangeCount(cadreBelow18,"below 18", totalDistrictCount2012, totalDistrictCount2014, ageWiseTotalList);
					
				List<Object[]> cadre18to25info = tdpCadreDAO.getAgeRangeCadreCountByAccessType(districtId, "18-25",constiIds);			
				setAgeWiseRangeCount(cadre18to25info,"18-25", totalDistrictCount2012, totalDistrictCount2014, ageWiseTotalList);
				
				List<Object[]> cadre26to35info = tdpCadreDAO.getAgeRangeCadreCountByAccessType(districtId, "26-35",constiIds);
				setAgeWiseRangeCount(cadre26to35info,"26-35", totalDistrictCount2012, totalDistrictCount2014, ageWiseTotalList);
				
				List<Object[]> cadre36to45info = tdpCadreDAO.getAgeRangeCadreCountByAccessType(districtId,"36-45",constiIds);
				setAgeWiseRangeCount(cadre36to45info,"36-45", totalDistrictCount2012, totalDistrictCount2014, ageWiseTotalList);
				
				List<Object[]> cadre46to60info = tdpCadreDAO.getAgeRangeCadreCountByAccessType(districtId,"46-60",constiIds);
				setAgeWiseRangeCount(cadre46to60info,"46-60", totalDistrictCount2012, totalDistrictCount2014, ageWiseTotalList);
				
				List<Object[]> cadreabove60info = tdpCadreDAO.getAgeRangeCadreCountByAccessType(districtId,"above 60",constiIds);
				setAgeWiseRangeCount(cadreabove60info,"above 60", totalDistrictCount2012, totalDistrictCount2014, ageWiseTotalList);
				
	            
			} catch (Exception e) {
				LOG.error("Exception rised in getDistrictWiseAgeRangeCountByAccess", e);
			}
			return ageWiseTotalList;
		}	
		
		
		public List<CadreRegisterInfo> getCastGroupWiseCadreCountByAccess(Long districtId,String accessType,String accessValue){
		
			LinkedHashMap<Long,CadreRegisterInfo> casteMap = new LinkedHashMap<Long,CadreRegisterInfo>();
		 try{
			 
			 List<Long> constiIds = new ArrayList<Long>();
				
				if(accessType.equalsIgnoreCase("MLA")){
					constiIds.add(Long.valueOf(accessValue));
				
				}
				else if(accessType.equalsIgnoreCase("MP")){
					List<Long> parliamentConstiId = new ArrayList<Long>();
					parliamentConstiId.add(Long.valueOf(accessValue));
					List<Object[]> list = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesListForAListOfParliamentConstituency(parliamentConstiId);
									
					if(list!=null && list.size()>0){
						for(Object[] obj:list){
							constiIds.add(Long.valueOf(obj[0].toString()));
						}
					}
				}
			List<Object[]> countsList = tdpCadreDAO.getCasteGroupTotalCountByAccessType(districtId, constiIds);
			Long totalCount2014 = null;
			Long totalCount2012 = null;
			for(Object[] counts:countsList){
				if(counts[1] != null){
					if(((Long)counts[1]).longValue() == 2012l){
						totalCount2012 = (Long)counts[0];
					}else if(((Long)counts[1]).longValue() == 2014l){
						totalCount2014 = (Long)counts[0];
					}
				}
			}
			List<Object[]> casteInfoList = tdpCadreDAO.getCasteGroupWiseCadreCountExcludingMinoritiesByAccessType(districtId,constiIds);
			//0 count,1 year,2 casteGroupId ,3 casteGroup
			
			List<Object[]> minList = tdpCadreDAO.getCadreCountInMinoritiesByAccessType(districtId,constiIds);
			
			if(minList != null && minList.size() > 0)
			{
				for(Object[] params : minList)
				{
					Object[] objArr = {params[0],params[1],Long.parseLong("99"),"Minority"};
					casteInfoList.add(objArr);
				}
			}
			
			for(Object[] caste:casteInfoList){
				
				CadreRegisterInfo casteVo = casteMap.get((Long)caste[2]);
				if(casteVo == null){
					casteVo = new CadreRegisterInfo();
					casteVo.setName(caste[3].toString());
					casteVo.setApCount(0l);//2014 total count
					casteVo.setTgCount(0l);//2012 total count
					casteVo.setPercentStr("0");//2014 perc
					casteVo.setArea("0");//2012 perc
					//casteVo.setDate(caste[4].toString());//casteGroup
					casteMap.put((Long)caste[2],casteVo);
				}
				if(((Long)caste[1]).longValue() == 2014l){
					casteVo.setApCount((Long)caste[0]);
					if(totalCount2014 != null && totalCount2014.longValue() > 0){
						casteVo.setPercentStr(new BigDecimal((casteVo.getApCount().doubleValue() / totalCount2014.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}
				}else if(((Long)caste[1]).longValue() == 2012l){
					casteVo.setTgCount((Long)caste[0]);
					if(totalCount2012 != null && totalCount2012.longValue() > 0){
						casteVo.setArea(new BigDecimal((casteVo.getTgCount().doubleValue() / totalCount2012.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}
				}
				
			}
		 }catch(Exception e){
			 LOG.error("Exception rised in getCastGroupWiseCadreCountByAccess",e);
		 }
			return new ArrayList<CadreRegisterInfo>(casteMap.values());
		}
		
		public List<CadreRegisterInfo> getDistrictWiseGenderCadreCountByAccess(Long districtId,String accessType,String accessValue){
			List<CadreRegisterInfo> genderList = new ArrayList<CadreRegisterInfo>();
			try{
				
				List<Long> constiIds = new ArrayList<Long>();					
				if(accessType.equalsIgnoreCase("MLA")){
					constiIds.add(Long.valueOf(accessValue));
				
				}
				else if(accessType.equalsIgnoreCase("MP")){
					List<Long> parliamentConstiId = new ArrayList<Long>();
					parliamentConstiId.add(Long.valueOf(accessValue));
					List<Object[]> list = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesListForAListOfParliamentConstituency(parliamentConstiId);
									
					if(list!=null && list.size()>0){
						for(Object[] obj:list){
							constiIds.add(Long.valueOf(obj[0].toString()));
						}
					}
				}

				List<Object[]>	countsList = tdpCadreDAO.getGenderTotalCountByAccessType(districtId,constiIds);
								 
				Long totalCount2014 = null;
				Long totalCount2012 = null;
				for(Object[] counts:countsList){
					if(counts[1] != null){
						if(((Long)counts[1]).longValue() == 2012l){
							totalCount2012 = (Long)counts[0];
						}else if(((Long)counts[1]).longValue() == 2014l){
							totalCount2014 = (Long)counts[0];
						}
					}
				}
				List<Object[]>	genderInfoList=tdpCadreDAO.getGenderWiseCadreCountByAccessType(districtId,constiIds);
				setGenderWiseCount(genderInfoList,genderList,totalCount2012,totalCount2014);
				
				} catch (Exception e) {
					LOG.error("Exception rised in getDistrictWiseGenderCadreCountByAccess", e);
				}
			return genderList;
			
		}
		
		public List<CadreRegisterInfo> getDistrictWiseCastCadreCountByAccess(Long districtId,String accessType,String accessValue){
			List<CadreRegisterInfo> casteList = new ArrayList<CadreRegisterInfo>();
			try{
				List<Long> constiIds = new ArrayList<Long>();					
				if(accessType.equalsIgnoreCase("MLA")){
					constiIds.add(Long.valueOf(accessValue));
				
				}
				else if(accessType.equalsIgnoreCase("MP")){
					List<Long> parliamentConstiId = new ArrayList<Long>();
					parliamentConstiId.add(Long.valueOf(accessValue));
					List<Object[]> list = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesListForAListOfParliamentConstituency(parliamentConstiId);
									
					if(list!=null && list.size()>0){
						for(Object[] obj:list){
							constiIds.add(Long.valueOf(obj[0].toString()));
						}
					}
				}
				List<Object[]>	countsList = tdpCadreDAO.getCasteGroupTotalCountByAccessType(districtId,constiIds);
				
				Long totalCount2014 = null;
				Long totalCount2012 = null;
				for(Object[] counts:countsList){
					if(counts[1] != null){
						if(((Long)counts[1]).longValue() == 2012l){
							totalCount2012 = (Long)counts[0];
						}else if(((Long)counts[1]).longValue() == 2014l){
							totalCount2014 = (Long)counts[0];
						}
					}
				}
				List<Object[]> casteInfoList = tdpCadreDAO.getCastWiseCadreCountByAccessType(districtId,constiIds);
				setCasteWiseCount(casteInfoList, casteList,totalCount2012,totalCount2014);
			}catch (Exception e) {
				LOG.error("Exception rised in getDistrictWiseCastCadreCountByAccess", e);
			}		
			return casteList;		
		}
		
		public CadreRegisterInfo getTotalRegisterCadreInfo(){
			CadreRegisterInfo info = new CadreRegisterInfo();
			Long tgCount = 0l;
			Long apCount = 0l;
			try{
				List<Object[]> districtWiseCount = tdpCadreDAO.getTotalRegisterCadreInfo();
				for(Object[] districtCount:districtWiseCount){
					if(((Long)districtCount[1]).longValue() > 10l){
						apCount = apCount+(Long)districtCount[0];
					}else{
						tgCount = tgCount+(Long)districtCount[0];
					}
				}
			}catch(Exception e){
				LOG.error("Exception rised in getTotalRegisterCadreInfo", e);
			}
			info.setApCount(apCount);
			info.setTgCount(tgCount);
			return info;
		}

		public List<CadreRegisterInfo> getGHMCRegisteredCountDetails(String type){
			
			List<CadreRegisterInfo> returnResult = new ArrayList<CadreRegisterInfo>();
			
			Date currentDate = dateService.getCurrentDateAndTime();
			CadreRegisterInfo todayInfo = new CadreRegisterInfo();
			
			if(type.equalsIgnoreCase("today"))
				todayInfo = getGHMCRegisterCount(currentDate,currentDate);
			else if(type.equalsIgnoreCase("total"))
				todayInfo = getGHMCRegisterCount(null,null);	
		     
		    returnResult.add(todayInfo);
		
		  

		    return returnResult;
		}
		
		
		 public CadreRegisterInfo getGHMCRegisterCount(Date fromDate,Date toDate){
			CadreRegisterInfo info = new CadreRegisterInfo();
		
			Long ghmcTabCount = 0l;
			Long ghmcWebCount = 0l;
		
			List<Object[]> districtWiseGHMCCount = null;
			try{
				
				List<Long> tabInsertedUsers = cadreSurveyUserDAO.getCadreSurveyUserDetailsByType();
				List<Long> webInsertedUsers = cadreGhmcDriveUsersDAO.getGHMCDriveUsers();
				//List<Long> tabInsertedUsers = new ArrayList<Long>();
				//tabInsertedUsers.add(3l);
				//List<Long> webInsertedUsers = new ArrayList<Long>();
				//webInsertedUsers.add(1l);
				//webInsertedUsers.add(2l);
			
				if(tabInsertedUsers.size() > 0 || webInsertedUsers.size() > 0)
					districtWiseGHMCCount = tdpCadreDAO.getRegisterCadreInfoForVolunteerUserBetweenDates(fromDate, toDate,tabInsertedUsers,webInsertedUsers);

				if(districtWiseGHMCCount != null && districtWiseGHMCCount.size() > 0){
				    for(Object[] districtGHMCCnt:districtWiseGHMCCount){			    	
							if(districtGHMCCnt[1] != null && districtGHMCCnt[1].toString().trim().equalsIgnoreCase("TAB"))
								ghmcTabCount = ghmcTabCount +  (Long)districtGHMCCnt[0];
							else if(districtGHMCCnt[1] != null && districtGHMCCnt[1].toString().trim().equalsIgnoreCase("WEB"))
								ghmcWebCount = ghmcWebCount + 	 (Long)districtGHMCCnt[0];					
					
				   }
				}
				
			}catch(Exception e){
				LOG.error("Exception rised in getRegisterCount",e);
			}
			info.setGhmcTabCount(ghmcTabCount);
			info.setGhmcWebCount(ghmcWebCount);
			return info;
		}
		 public CadreRegisterInfo getDashBoardBasicRegistrationInfo(String accessType,Long accessValue,Long stateId)
		 {
			 List list = null;
			 Long totalRegistration = 0l;
			 Long regConstiCnt = 0l;
			 CadreRegisterInfo returnVo = new CadreRegisterInfo();
			 try{
				 Long totalConsti = 0l;
				 Date currentDate = dateService.getCurrentDateAndTime();
				 List<Long> constituencyIds = new ArrayList<Long>();
					List<Long> districtIds = new ArrayList<Long>();
					 if(accessType.equalsIgnoreCase(IConstants.STATE))
					 {
					 list = constituencyDAO.getLatestConstituenciesByStateId(
							 IConstants.ASSEMBLY_ELECTION_TYPE, stateId);
					 totalRegistration = tdpCadreDAO.getRegisterCadreInfoForState(stateId,2014l);
					 regConstiCnt = tdpCadreDAO.getRegisterConstituenciesForState(stateId,2014l,null,IConstants.STATE);
					 if(list != null && list.size() > 0)
					 totalConsti = new Long(list.size());
					 }
						if(accessType.equalsIgnoreCase("MLA"))
						{
							constituencyIds.add(accessValue);
							districtIds = constituencyDAO.getDistrictIdsByConstituency(constituencyIds);
							totalRegistration = tdpCadreDAO.getRegisterCadreInfoForUserBetweenDatesByIds(null,null,constituencyIds,districtIds);
							regConstiCnt = tdpCadreDAO. getRegisterConstituenciesForState(stateId,2014l,constituencyIds,IConstants.CONSTITUENCY);
							totalConsti = new Long(constituencyIds.size());
						}
						else if(accessType.equalsIgnoreCase("MP"))
						{
							constituencyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliament(accessValue);
							districtIds = constituencyDAO.getDistrictIdsByConstituency(constituencyIds);
							totalRegistration = tdpCadreDAO.getRegisterCadreInfoForUserBetweenDatesByIds(null,null,constituencyIds,districtIds);
							totalConsti = new Long(constituencyIds.size());
							regConstiCnt = tdpCadreDAO. getRegisterConstituenciesForState(stateId,2014l,constituencyIds,IConstants.CONSTITUENCY);
						}
						else if(accessType.equalsIgnoreCase("DISTRICT"))
						{
							districtIds.add(accessValue);
							totalRegistration = tdpCadreDAO.getRegisterCadreInfoForUserBetweenDatesByIds(null,null,null,districtIds);
							regConstiCnt = tdpCadreDAO. getRegisterConstituenciesForState(stateId,2014l,districtIds,IConstants.DISTRICT);
							List<Object[]> constis = constituencyDAO.getConstituencyListByDistrictIdsList(districtIds);
							totalConsti = new Long(constis.size());
						}
				
				
					 
				 	 returnVo.setTotalCount(totalConsti != null ? totalConsti : 0l);
				 	 returnVo.setApCount(totalRegistration != null ?  totalRegistration : 0l);
				 	 returnVo.setVotersCount(regConstiCnt != null ? regConstiCnt : 0l);
				 
			 }
			 catch(Exception e)
			 {
				 LOG.error(e);
			 }
			return returnVo;
		 }
		 
		 
	public List<CadreDashboardVO> get2016LocationWiseRegisteredCountsForConstitunecy(String type,Long locationScopeId,String locationType,Long districId,Long constType){
		 List<CadreDashboardVO> returnList = new ArrayList<CadreDashboardVO>();
		 try {
			 
			 Long vizagRuralId = 0l;
			 if(districId.longValue() == 517){
				 vizagRuralId = districId.longValue() ;
				 districId = 13l;
			}else if(districId.longValue() == 13){
					vizagRuralId = districId.longValue() ;
			}
		 	List<Object[]> list = tdpCadreLocationInfoDAO.get2016LocationWiseRegisteredCountsForConstituency(type,locationScopeId,locationType, null,districId);
		 	List<Object[]> totalList = null;
		 //	if(type != null && type.trim().equalsIgnoreCase("Today")){
		 		totalList = tdpCadreLocationInfoDAO.get2016LocationWiseRegisteredCountsForConstituency("Total",locationScopeId,locationType, null,districId);
		 //	}
		 	List<Object[]> list2 = null;
		 	if(type.equalsIgnoreCase("total")){
		 		list2 = tdpCadreLocationInfoDAO.get2016LocationWiseRegisteredCountsForConstituency("Today",locationScopeId,locationType, null,districId);            
		 	}
		 	List<Object[]> list3 = null;
		 	List<Object[]> list4 = null;
		 	if(locationScopeId.longValue() != 2l){
		 		list3 = cadreSurveyUserAssignDetailsDAO.getMapPowerLocationWise(locationScopeId,locationType); 
		 	}
		 	if(locationScopeId.longValue() == 4l){
		 		list4 = delimitationConstituencyDAO.getConstituencyNo(locationType);
		 	}
		 	prepairReturnList(returnList,list,list2,list3,list4,type,locationScopeId,totalList,vizagRuralId);
		 	
		 	
		 	if(vizagRuralId.longValue() == 517l || vizagRuralId.longValue() == 13l && constType == null){
		 		List<CadreDashboardVO> vizagRuralConsts = removeVisakapatnamUrbanData(returnList,vizagRuralId);
		 		returnList.removeAll(returnList);
		 		returnList.addAll(vizagRuralConsts);
		 	}
		 	
		 	 if(locationType.equalsIgnoreCase("AP") && locationType != null &&  districId.longValue() == 0L && locationScopeId.longValue() == 3l){
		 		 List<Long> constitencyIds  = new ArrayList<Long>(0);
		 		 
		         List<Object[]> constitencis = constituencyDAO.getDistrictConstituencies(13l);
		         if(commonMethodsUtilService.isListOrSetValid(constitencis)){
		        	 for (Object[] param : constitencis) {
		        		 constitencyIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
		        	}
		         }
		         
		         List<CadreDashboardVO>  assemblyList = get2016LocationWiseRegisteredCountsForConstitunecy(type,4L,locationType,13L,13l);
		         List<Object[]> vishakapatnamRuralAssemblyList = districtConstituenciesDAO.getDistrictByConstituenciesIds(new HashSet<Long>(constitencyIds));
		         List<Long> vizagRuralConstIds = districtConstituenciesDAO.getConstituenciesOfDistrictById(517l);
		         
		         setVisakhapatnamDetails(assemblyList,returnList,vizagRuralConstIds);
		       }
		} catch (Exception e) {
			LOG.error("Exception Occured in get2016StateWiseRegisteredCounts() method in CadreDashBoardService().",e);
		}
		 return returnList;    
	}
	
	public List<CadreDashboardVO> removeVisakapatnamUrbanData(List<CadreDashboardVO> returnList,Long vizagRuralId){
		List<CadreDashboardVO> vizagRuralConsts = new ArrayList<CadreDashboardVO>();
		List<CadreDashboardVO> vizagUrbanConsts = new ArrayList<CadreDashboardVO>();
		try{
			
			List<Long> vizagRuralConstIds = districtConstituenciesDAO.getConstituenciesOfDistrictById(517l);
			if(returnList != null && returnList.size() >0){
				for(CadreDashboardVO vo :returnList){
					
					if(vizagRuralConstIds.contains(vo.getId())){
						vizagRuralConsts.add(vo);
					}else{
						vizagUrbanConsts.add(vo);
					}
				}
			}
			
		}catch (Exception e) {
			LOG.error("Exception Occured in removeVisakapatnamUrbanData() method in CadreDashBoardService().",e);
		}
		if(vizagRuralId.longValue() == 13l)
			return vizagUrbanConsts;
		else
			return vizagRuralConsts;
	}
	
	public void setVisakhapatnamDetails(List<CadreDashboardVO> assemblyList,List<CadreDashboardVO> returnList,List<Long> vizagRuralConstIds){
		
		try{
			CadreDashboardVO vizagRural = new CadreDashboardVO();
			CadreDashboardVO vizagUrban = new CadreDashboardVO();
			if(assemblyList != null && assemblyList.size() >0){
				for(CadreDashboardVO vo :assemblyList){
					if(vizagRuralConstIds.contains(vo.getId())){
						vizagRural.setId(517l);
						
						Long l1 = vo.getCount2014() != null? vo.getCount2014():0l;
						Long v1 = vizagRural.getCount2014() != null? vizagRural.getCount2014():0l;
						vizagRural.setCount2014(v1+vo.getCount2014());
						
						Long l2 = vo.getTotalRenewal() != null?vo.getTotalRenewal():0l;
						Long v2 = vizagRural.getTotalRenewal() != null?vizagRural.getTotalRenewal():0l;
						vizagRural.setTotalRenewal(v2+l2);
						
						if(vo.getTotalRenPerc() != null)
						vizagRural.setTotalRenPerc(vizagRural.getTotalRenPerc() != null?vizagRural.getTotalRenPerc():0l+vo.getTotalRenPerc());
						
						if(vo.getPerc2014() != null)
						vizagRural.setPerc2014(vizagRural.getPerc2014()!=null?vizagRural.getPerc2014():0l+vo.getPerc2014());
						
						Long l3 = vo.getCount2016() != null?vo.getCount2016():0l;
						Long v3 = vizagRural.getCount2016() != null?vizagRural.getCount2016():0l;
						vizagRural.setCount2016(v3+l3);
						
						//if(vo.getPerc2016() != null)
						//vizagRural.setPerc2016(vizagRural.getPerc2016() != null ?vizagRural.getPerc2016():0l+vo.getPerc2016());
						
						Long l4 = vo.getNewCount() != null ?vo.getNewCount():0l;
						Long v4 = vizagRural.getNewCount() != null ?vizagRural.getNewCount():0l;
						vizagRural.setNewCount(v4+l4);
						//vizagRural.setNewPerc(vizagRural.getNewPerc() != null ?vizagRural.getNewPerc():0l+vo.getNewPerc());
						Long l5 = vo.getRenewalCount()!=null?vo.getRenewalCount():0l;
						Long v5 = vizagRural.getRenewalCount()!=null?vizagRural.getRenewalCount():0l;
						vizagRural.setRenewalCount(v5+l5);
						//vizagRural.setRenewalPerc(vizagRural.getRenewalPerc()!= null?vizagRural.getRenewalPerc():0l+vo.getRenewalPerc());
						if(vo.getLocationScopeId() != null)
						vizagRural.setLocationScopeId(vo.getLocationScopeId());
						vizagRural.setType(vo.getType());
						Long l6 = vo.getCount2016Today() != null?vo.getCount2016Today():0l;
						Long v6 = vizagRural.getCount2016Today() != null?vizagRural.getCount2016Today():0l;
						vizagRural.setCount2016Today(v6+l6);
						
						Long l7 = vo.getMapPowerCount() !=null?vo.getMapPowerCount():0l;
						Long v7 = vizagRural.getMapPowerCount() !=null?vizagRural.getMapPowerCount():0l;
						vizagRural.setMapPowerCount(v7+l7);
						vizagRural.setNo(vo.getNo());
						vizagRural.setDistrictname(vo.getDistrictname());
						vizagRural.setName("Visakhapatnam Rural");
						vizagRural.setValue(vo.getValue());
						
						Long l8 = vo.getTargetCount() != null?vo.getTargetCount():0l;
						Long v8 = vizagRural.getTargetCount() != null?vizagRural.getTargetCount():0l;
						vizagRural.setTargetCount(v8+l8);
						
					}else{
						vizagUrban.setId(13l);
						Long l1 = vo.getCount2014() != null? vo.getCount2014():0l;
						Long v1 = vizagUrban.getCount2014() != null? vizagUrban.getCount2014():0l;
						vizagUrban.setCount2014(v1+vo.getCount2014());
						
						Long l2 = vo.getTotalRenewal() != null?vo.getTotalRenewal():0l;
						Long v2 = vizagUrban.getTotalRenewal() != null?vizagUrban.getTotalRenewal():0l;
						vizagUrban.setTotalRenewal(v2+l2);
						
						if(vo.getTotalRenPerc() != null)
							vizagUrban.setTotalRenPerc(vizagUrban.getTotalRenPerc() != null?vizagUrban.getTotalRenPerc():0l+vo.getTotalRenPerc());
						
						if(vo.getPerc2014() != null)
							vizagUrban.setPerc2014(vizagUrban.getPerc2014()!=null?vizagUrban.getPerc2014():0l+vo.getPerc2014());
						
						Long l3 = vo.getCount2016() != null?vo.getCount2016():0l;
						Long v3 = vizagUrban.getCount2016() != null?vizagUrban.getCount2016():0l;
						vizagUrban.setCount2016(v3+l3);
						
						//if(vo.getPerc2016() != null)
						//vizagRural.setPerc2016(vizagRural.getPerc2016() != null ?vizagRural.getPerc2016():0l+vo.getPerc2016());
						
						Long l4 = vo.getNewCount() != null ?vo.getNewCount():0l;
						Long v4 = vizagUrban.getNewCount() != null ?vizagUrban.getNewCount():0l;
						vizagUrban.setNewCount(v4+l4);
						//vizagRural.setNewPerc(vizagRural.getNewPerc() != null ?vizagRural.getNewPerc():0l+vo.getNewPerc());
						Long l5 = vo.getRenewalCount()!=null?vo.getRenewalCount():0l;
						Long v5 = vizagUrban.getRenewalCount()!=null?vizagUrban.getRenewalCount():0l;
						vizagUrban.setRenewalCount(v5+l5);
						//vizagRural.setRenewalPerc(vizagRural.getRenewalPerc()!= null?vizagRural.getRenewalPerc():0l+vo.getRenewalPerc());
						if(vo.getLocationScopeId() != null)
						vizagUrban.setLocationScopeId(vo.getLocationScopeId());
						vizagUrban.setType(vo.getType());
						Long l6 = vo.getCount2016Today() != null?vo.getCount2016Today():0l;
						Long v6 = vizagUrban.getCount2016Today() != null?vizagUrban.getCount2016Today():0l;
						vizagUrban.setCount2016Today(v6+l6);
						
						Long l7 = vo.getMapPowerCount() !=null?vo.getMapPowerCount():0l;
						Long v7 = vizagUrban.getMapPowerCount() !=null?vizagUrban.getMapPowerCount():0l;
						vizagUrban.setMapPowerCount(v7+l7);
						vizagUrban.setNo(vo.getNo());
						vizagUrban.setDistrictname(vo.getDistrictname());
						vizagUrban.setName("Visakhapatnam");
						vizagUrban.setValue(vo.getValue());
						
						Long l8 = vo.getTargetCount() != null?vo.getTargetCount():0l;
						Long v8 = vizagUrban.getTargetCount() != null?vizagUrban.getTargetCount():0l;
						vizagUrban.setTargetCount(v8+l8);
					}
				}
			}
			setPercentage(vizagUrban,returnList);
			setPercentage(vizagRural,returnList);
			//returnList.add(vizagUrban);
			//returnList.add(vizagRural);
		}catch (Exception e) {
			LOG.error("Exception Occured in setVisakhapatnamDetails() method in CadreDashBoardService().",e);
		}
	}
	
	public void setPercentage(CadreDashboardVO vo,List<CadreDashboardVO> returnList){
		
		try{
			Long l1 =vo.getTotalRenewal() != null ?vo.getTotalRenewal():0l;
			Long v1 =vo.getCount2014() != null ?vo.getCount2014():0l;
			String perc = "";
			if(v1.longValue() != 0l)
			perc = commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(l1*100.00/v1);
			
			Long veryGood =0l ;
			Long good = 0l ;
			Long ok = 0l ;
			Long poor = 0l ;
			Long veryPoor = 0l ;
			
			Long renVeryGood =0l ;
			Long renGood = 0l ;
			Long renOk = 0l ;
			Long renPoor = 0l ;
			Long renVeryPoor = 0l ;
			
			vo.setTotalRenPerc(perc);
			Long l2 =vo.getCount2016() != null ?vo.getCount2016():0l;
			Long v2 =vo.getTargetCount() != null ?vo.getTargetCount():0l;
			
			if(v2.longValue() != 0l){
			vo.setPerc2016(new BigDecimal(l2*(100.0)/v2).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			
			Long l3 =vo.getCount2014() != null ?vo.getCount2014():0l;
			vo.setPerc2014(new BigDecimal(l3*(100.0)/v2).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			
			Long l4 =vo.getNewCount() != null ?vo.getNewCount():0l;
			vo.setNewPerc(new BigDecimal(l4*(100.0)/v2).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			
			Long l5 =vo.getRenewalCount() != null ?vo.getRenewalCount():0l;
			vo.setRenewalPerc(new BigDecimal(l5*(100.0)/v2).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			}
			
			Double perCount2016 = 0.0;
			if(vo.getPerc2016() != null)
			perCount2016=Double.parseDouble(vo.getPerc2016());
			
			if(perCount2016 > 100){
				vo.setLevelPerformanceType("VeryGood");
				veryGood++;
			}else if(perCount2016 > 90 && perCount2016 <= 100){
				vo.setLevelPerformanceType("Good");
				good ++;
			}else if(perCount2016 > 80 && perCount2016 <= 90){
				vo.setLevelPerformanceType("Ok");
				ok++;
			}else if(perCount2016 > 60 && perCount2016 <= 80){
				vo.setLevelPerformanceType("Poor");  
				poor++;
			}else{
				vo.setLevelPerformanceType("VeryPoor");
				veryPoor++;
			}
			
			if(vo.getTotalRenPerc() != null && vo.getTotalRenPerc() != ""){
				Double totalRenPerc=Double.parseDouble(vo.getTotalRenPerc());					
					if(totalRenPerc > 100){
						vo.setRenewalPerformanceStatus("VeryGood");
						renVeryGood++;
					}else if(totalRenPerc > 90 && totalRenPerc <= 100){
						vo.setRenewalPerformanceStatus("Good");
						renGood++;
					}else if(totalRenPerc > 80 && totalRenPerc <= 90){
						vo.setRenewalPerformanceStatus("Ok");
						renOk++;
					}else if(totalRenPerc > 60 && totalRenPerc <= 80){
						vo.setRenewalPerformanceStatus("Poor"); 
						renPoor++;
					}else{
						vo.setRenewalPerformanceStatus("VeryPoor");
						renVeryPoor++;
					}
				}
			
			if(v1.longValue() != 0l)
			vo.setPercentage(new BigDecimal(l2*(100.0)/v1).toString());
			
			returnList.add(vo);
			
			if(returnList !=null && !returnList.isEmpty()){
				CadreDashboardVO countList=returnList.get(0);
				countList.setVeryGood(countList.getVeryGood()+veryGood);
				countList.setGood(countList.getGood()+good);
				countList.setOk(countList.getOk()+ok);
				countList.setPoor(countList.getPoor()+poor);
				countList.setVeryPoor(countList.getVeryPoor()+veryPoor);
				
				countList.setRenVeryGood(countList.getRenVeryGood()+renVeryGood);
				countList.setRenGood(countList.getRenGood()+renGood);
				countList.setRenOk(countList.getRenOk()+renOk);
				countList.setRenPoor(countList.getRenPoor()+renPoor);
				countList.setRenVeryPoor(countList.getRenVeryPoor()+renVeryPoor);
				
			}
		}catch (Exception e) {
			LOG.error("Exception Occured in setPercentage() method in CadreDashBoardService().",e);
		}
	}
	public List<CadreDashboardVO> get2016LocationWiseRegisteredCounts(String type,Long locationScopeId,String locationType){
		 List<CadreDashboardVO> returnList = new ArrayList<CadreDashboardVO>();
		 try {
		 	List<Object[]> list = tdpCadreLocationInfoDAO.get2016LocationWiseRegisteredCounts(type,locationScopeId,locationType, null);
		 	List<Object[]> totalList = null;
		 	//if(type != null && type.trim().equalsIgnoreCase("Today")){
		 		totalList = tdpCadreLocationInfoDAO.get2016LocationWiseRegisteredCounts("Total",locationScopeId,locationType, null);
		 	//}
		 	List<Object[]> list2 = null;
		 	if(type.equalsIgnoreCase("total")){
		 		list2 = tdpCadreLocationInfoDAO.get2016LocationWiseRegisteredCounts("Today",locationScopeId,locationType, null);            
		 	}
		 	List<Object[]> list3 = null;
		 	List<Object[]> list4 = null;
		 	if(locationScopeId.longValue() != 2l){
		 		list3 = cadreSurveyUserAssignDetailsDAO.getMapPowerLocationWise(locationScopeId,locationType); 
		 	}
		 	if(locationScopeId.longValue() == 4l){
		 		list4 = delimitationConstituencyDAO.getConstituencyNo(locationType);
		 	}
		 	prepairReturnList(returnList,list,list2,list3,list4,type,locationScopeId,totalList,0l);
		} catch (Exception e) {
			LOG.error("Exception Occured in get2016StateWiseRegisteredCounts() method in CadreDashBoardService().",e);
		}
		 return returnList;    
	}
	public void prepairReturnList(List<CadreDashboardVO> returnList, List<Object[]> list, List<Object[]> list2, List<Object[]> list3, List<Object[]> list4,
			 String type,Long locationScopeId,List<Object[]> totalList,Long districtId){
		Map<Long,Long> targetMap = new LinkedHashMap<Long, Long>();
		Map<Long,String> locationNameMap = new LinkedHashMap<Long, String>();
		Map<Long,Long> locationIdAndTodayCountMap = new LinkedHashMap<Long,Long>();
		Map<Long,Long> locationIdAndManPowerCountMap = new LinkedHashMap<Long,Long>();
		Map<Long,Long> locIdAndLocNoMap = new LinkedHashMap<Long,Long>();
		Map<Long,CadreDashboardVO> locationWise2014CountMap = new LinkedHashMap<Long,CadreDashboardVO>(0);
		List<Long> locationIds = new ArrayList<Long>();
		try{
		Long veryGood =0l ;
		Long good = 0l ;
		Long ok = 0l ;
		Long poor = 0l ;
		Long veryPoor = 0l ;
		
		Long renVeryGood =0l ;
		Long renGood = 0l ;
		Long renOk = 0l ;
		Long renPoor = 0l ;
		Long renVeryPoor = 0l ;
		
		
		//collect locaionId and today count
		if(list2 != null && list2.size() > 0){
			for(Object[] param : list2){
				
				if(locationScopeId.longValue() == 3l){//district
					if(param[0] != null && (Long)param[0] != 13l){
						locationIdAndTodayCountMap.put(Long.valueOf(param[0] != null ? param[0].toString():"0"),Long.valueOf(param[3] != null ? param[3].toString():"0"));
					}
				}else{
					if(param[0] != null){
						locationIdAndTodayCountMap.put(Long.valueOf(param[0] != null ? param[0].toString():"0"),Long.valueOf(param[3] != null ? param[3].toString():"0"));
					}
				}
			}
		}
		//create a map for locationId and man power count
		if(list3 != null && list3.size() > 0){
			for(Object[] param : list3){
				if(locationScopeId.longValue() == 3l){//district
					if(param[0] != null && (Long)param[0] != 13l){
						locationIdAndManPowerCountMap.put(Long.valueOf(param[0] != null ? param[0].toString():"0"),Long.valueOf(param[1] != null ? param[1].toString():"0"));	
					}
				}else{
					if(param[0] != null ){
						locationIdAndManPowerCountMap.put(Long.valueOf(param[0] != null ? param[0].toString():"0"),Long.valueOf(param[1] != null ? param[1].toString():"0"));	
					}
				}
			}
		}
		//create a map for constid and const name
		if(list4 != null  && list.size() > 0){
			for(Object[] param : list4){
				if(locationScopeId.longValue() == 3l){//district
					if(param[0] != null && (Long)param[0] != 13l){
						locIdAndLocNoMap.put(Long.valueOf(param[0] != null ? param[0].toString():"0"),Long.valueOf(param[1] != null ? param[1].toString():"0"));
					}
				}else{
					if(param[0] != null){
						locIdAndLocNoMap.put(Long.valueOf(param[0] != null ? param[0].toString():"0"),Long.valueOf(param[1] != null ? param[1].toString():"0"));
					}
				}
			}
		}
		
		if(commonMethodsUtilService.isListOrSetValid(totalList)){
			for(Object[] param : totalList){
				CadreDashboardVO vo = new CadreDashboardVO();
				Long id = Long.valueOf(commonMethodsUtilService.getLongValueForObject(param[0]));
				if(locationScopeId.longValue() == 3l){//district
					if(id.longValue() != 13l){
						vo.setId(id);
						vo.setCount2014(commonMethodsUtilService.getLongValueForObject(param[1]));
						vo.setTotalRenewal(commonMethodsUtilService.getLongValueForObject(param[7]));
						vo.setTotalRenPerc(commonMethodsUtilService.getStringValueForObject(param[8]));
						locationWise2014CountMap.put(id, vo);
				    }
				}else{
						vo.setId(id);
						vo.setCount2014(commonMethodsUtilService.getLongValueForObject(param[1]));
						vo.setTotalRenewal(commonMethodsUtilService.getLongValueForObject(param[7]));
						vo.setTotalRenPerc(commonMethodsUtilService.getStringValueForObject(param[8]));
						locationWise2014CountMap.put(id, vo);
				}
			}
		}
		
		if(list != null && !list.isEmpty()){
	 		for (Object[] obj : list) {
	 			Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
	 			boolean stopflag = false;
	 			if(locationScopeId.longValue() == 3l && id.longValue() == 13l){
	 				stopflag = true;
	 			}
	 			if(!stopflag){
	 				
	 				CadreDashboardVO vo = new CadreDashboardVO();
					vo.setId(id);
					if(locationWise2014CountMap.get(id) != null){
						CadreDashboardVO vo1 = locationWise2014CountMap.get(id);
						if(vo1 != null){
							vo.setCount2014(vo1.getCount2014());
							vo.setTotalRenewal(vo1.getTotalRenewal());
							String perc = commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(vo1.getTotalRenewal()*100.00/vo1.getCount2014());
							vo.setTotalRenPerc(perc);
						}
					}
					else					
						vo.setCount2014(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
					
					vo.setPerc2014(obj[2] != null ? obj[2].toString():"");
					vo.setCount2016(Long.valueOf(obj[3] != null ? obj[3].toString():"0"));
					vo.setPerc2016(obj[4] != null ? obj[4].toString():"0.00");
					vo.setNewCount(Long.valueOf(obj[5] != null ? obj[5].toString():"0"));
					vo.setNewPerc(obj[6] != null ? obj[6].toString():"");
					vo.setRenewalCount(Long.valueOf(obj[7] != null ? obj[7].toString():"0"));
					vo.setRenewalPerc(obj[8] != null ? obj[8].toString():"");
					vo.setLocationScopeId(locationScopeId);
					vo.setType(type);
					locationIds.add(id);
					returnList.add(vo);
	 			}
			}
	 	}
		
		
		//push today count to vo
		if(list2 != null && list2.size() > 0 && returnList != null && returnList.size() > 0){
			for(CadreDashboardVO param : returnList){
				Long locId = param.getId();
				if(locationScopeId.longValue() == 3l){
					if(locationIdAndTodayCountMap.get(locId) != null  && locId.longValue() != 13l){
						param.setCount2016Today(locationIdAndTodayCountMap.get(locId));
					}
				}else{
					if(locationIdAndTodayCountMap.get(locId) != null){
						param.setCount2016Today(locationIdAndTodayCountMap.get(locId));
					}
				}
			}
		}
		
	 	//push manpower count
		if(list3 != null && list3.size() > 0 && returnList != null && returnList.size() > 0){
			for(CadreDashboardVO param : returnList){
				Long locId = param.getId();
				if(locationIdAndManPowerCountMap.get(locId) != null){
					param.setMapPowerCount(locationIdAndManPowerCountMap.get(locId));
				}  
			}
			      
		}
		//push constituency no into vo
		if(locationScopeId != null && locationScopeId.longValue() == 4l){
			if(returnList != null && returnList.size() > 0){
				for(CadreDashboardVO param : returnList){
					Long locId = param.getId();
					if(locIdAndLocNoMap.get(locId) != null){
						param.setNo(locIdAndLocNoMap.get(locId));        
					}  
				}
			}
		}
	 	List<Object[]> list1 = null;
	 	if(locationIds != null && locationIds.size() > 0){
	 		if(locationScopeId != null && locationScopeId.longValue() == 2l){
		 		list1 = stateDAO.getStatesForList(locationIds);
		 	}
		 	else if(locationScopeId != null && locationScopeId.longValue() == 3l ){
		 		list1 = districtDAO.getDistrictDetailsByDistrictIds(locationIds);
		 	}
		 	else if(locationScopeId != null && locationScopeId.longValue() == 4l){
		 		list1 = constituencyDAO.getConstituenctNamesByIds(locationIds);
		 	}
	 	}
	 		
	 	
	 	if(list1 != null && !list1.isEmpty()){
	 		for (Object[] obj : list1) {
	 			
				Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
				boolean stopflag = false;
				if(locationScopeId.longValue() == 3l && id.longValue() == 13l){
					stopflag = true;
				}
				if(!stopflag){
					String name = null;
					 if(locationScopeId != null && locationScopeId.longValue() == 3l || locationScopeId != null && locationScopeId.longValue() == 2l){
						 name = obj[1] != null ? obj[1].toString():"";
					}else if(locationScopeId != null && locationScopeId.longValue() == 4l){
							String t = obj[1] != null ? obj[1].toString():""; //consName
							String t1 = obj[2] != null ? obj[2].toString():"";//distName
							Long t3 = Long.valueOf(obj[3] != null ? obj[3].toString():"0");//districtId
							name = t+"-"+t1+"-"+t3;
					}
					 
					locationNameMap.put(id, name);
				}
			}
	 	}
	 	
	 	if(returnList != null && !returnList.isEmpty()){
	 		for (CadreDashboardVO vo : returnList) {
	 			
	 			if(locationScopeId != null && locationScopeId.longValue() == 3l || locationScopeId != null && locationScopeId.longValue() == 2l){
	 				vo.setName(locationNameMap.get(vo.getId()).split("-")[0]);
				}else if(locationScopeId != null && locationScopeId.longValue() == 4l){
					vo.setName(locationNameMap.get(vo.getId()).split("-")[0]);
					vo.setDistrictname(locationNameMap.get(vo.getId()).split("-")[1]);
					
					if(districtId.longValue() == 517l)
						vo.setValue("517");
					else
						vo.setValue(locationNameMap.get(vo.getId()).split("-")[2]);
						
				}
	 		}
	 	}
	 	
	 	List<Object[]> targrtLst = tdpCadreLocationInfoDAO.getLocationWiseTargets(locationScopeId);
		if(targrtLst != null && !targrtLst.isEmpty()){
			for (Object[] obj : targrtLst) {
				Long locaId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
				Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
				if(locationScopeId.longValue() == 3l){
					if(locaId.longValue() != 13l){
						targetMap.put(locaId, count);	
					}
				}else{
					targetMap.put(locaId, count);
				}
			}
		}
	 		
		if(returnList != null && !returnList.isEmpty()){
			for (CadreDashboardVO vo : returnList) {
				vo.setTargetCount(targetMap.get(vo.getId()));
			}
		}
	 		
		if(type.trim().equalsIgnoreCase("today") &&  (locationScopeId == 3l || locationScopeId == 4l)){
			if(returnList != null && !returnList.isEmpty()){
				for (CadreDashboardVO vo : returnList) {
					vo.setTargetCount(vo.getTargetCount()/IConstants.CADRE_REGISTRATION_2016_DAYS);
				}
			}
		}
	   
		if(type.trim().equalsIgnoreCase("today") &&  (locationScopeId == 3l || locationScopeId == 4l)){
			if(returnList != null && !returnList.isEmpty()){
				for (CadreDashboardVO vo : returnList) {
					vo.setPerc2016(new BigDecimal(vo.getCount2016()*(100.0)/vo.getTargetCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
			}
		}
		if(returnList != null && !returnList.isEmpty()){
			for(CadreDashboardVO vo : returnList){
				Double perCount2016=Double.parseDouble(vo.getPerc2016());
				if(perCount2016 > 100){
					vo.setLevelPerformanceType("VeryGood");
					veryGood++;
				}else if(perCount2016 > 90 && perCount2016 <= 100){
					vo.setLevelPerformanceType("Good");
					good ++;
				}else if(perCount2016 > 80 && perCount2016 <= 90){
					vo.setLevelPerformanceType("Ok");
					ok++;
				}else if(perCount2016 > 60 && perCount2016 <= 80){
					vo.setLevelPerformanceType("Poor");  
					poor++;
				}else{
					vo.setLevelPerformanceType("VeryPoor");
					veryPoor++;
				}
			}
		}
		
		if(returnList != null && !returnList.isEmpty()){
			for(CadreDashboardVO vo : returnList){
				if(vo.getTotalRenPerc() != null ){
				Double totalRenPerc=Double.parseDouble(vo.getTotalRenPerc());					
					if(totalRenPerc > 100){
						vo.setRenewalPerformanceStatus("VeryGood");
						renVeryGood++;
					}else if(totalRenPerc > 90 && totalRenPerc <= 100){
						vo.setRenewalPerformanceStatus("Good");
						renGood++;
					}else if(totalRenPerc > 80 && totalRenPerc <= 90){
						vo.setRenewalPerformanceStatus("Ok");
						renOk++;
					}else if(totalRenPerc > 60 && totalRenPerc <= 80){
						vo.setRenewalPerformanceStatus("Poor"); 
						renPoor++;
					}else{
						vo.setRenewalPerformanceStatus("VeryPoor");
						renVeryPoor++;
					}
				}
			}
		}
		
		if(locationScopeId == 2l && type.trim().equalsIgnoreCase("Total")){
			if(returnList != null && !returnList.isEmpty()){
				for (CadreDashboardVO vo : returnList) {
					vo.setPercentage(new BigDecimal(vo.getCount2016()*(100.0)/vo.getCount2014()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
			}
		}
		
		if(returnList !=null && !returnList.isEmpty()){
			CadreDashboardVO countList=returnList.get(0);
			countList.setVeryGood(veryGood);
			countList.setGood(good);
			countList.setOk(ok);
			countList.setPoor(poor);
			countList.setVeryPoor(veryPoor);
			
			countList.setRenVeryGood(renVeryGood);
			countList.setRenGood(renGood);
			countList.setRenOk(renOk);
			countList.setRenPoor(renPoor);
			countList.setRenVeryPoor(renVeryPoor);
			
		}
		}catch(Exception e){
			LOG.error("Error Occured while setting the coutn details in CadreDashBoardService  ",e);
		}
	}
	
	public List<CadreDataSourceTypeVO> getDataSourceTypeWiseRegisteredDetails(){
		List<CadreDataSourceTypeVO> returnList = new ArrayList<CadreDataSourceTypeVO>();
		try {
			Date currentDate = dateService.getCurrentDateAndTime();
			CadreDataSourceTypeVO todayVO = new CadreDataSourceTypeVO();
			CadreDataSourceTypeVO totalVO = new CadreDataSourceTypeVO();
			
			List<Object[]> list = tdpCadreLocationInfoDAO.getTdpCadreRecordsCountLocWise(currentDate,currentDate);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					String sourceType = obj[0] != null ? obj[0].toString():"";
					Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					
					if(sourceType.trim().equalsIgnoreCase("WEB")){
						todayVO.setWebTotal(count);
					}
					else if(sourceType.trim().equalsIgnoreCase("TAB")){
						todayVO.setTabTotal(count);
					}
					else if(sourceType.trim().equalsIgnoreCase("ONLINE")){
						todayVO.setOnlineTotal(count);
					}
				}
			}
			
			List<Object[]> otherList = tdpCadreLocationInfoDAO.getTdpCadreRecordsCount(currentDate,currentDate);
			if(otherList != null && !otherList.isEmpty()){
				for (Object[] obj : otherList) {
					Long userId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					
					if(userId == 3930l){					//Hyd PartyOfc
						todayVO.setPartyOfcHydTotal(count);
					}
					else if(userId == 7394l){				//Vij PartyOfc
						todayVO.setPartyOfcVijTotal(count);
					}
				}
			}
			
			List<Object[]> reneList = tdpCadreLocationInfoDAO.getRenewalTdpCadreRecordsCountLocWise(currentDate, currentDate);
			if(reneList != null && !reneList.isEmpty()){
				for (Object[] obj : reneList) {
					String sourceType = obj[0] != null ? obj[0].toString():"";
					Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					
					if(sourceType.trim().equalsIgnoreCase("WEB")){
						todayVO.setWebRenewal(count);
					}
					else if(sourceType.trim().equalsIgnoreCase("TAB")){
						todayVO.setTabRenewal(count);
					}
					else if(sourceType.trim().equalsIgnoreCase("ONLINE")){
						todayVO.setOnlineRenewal(count);
					}
				}
			}
			
			List<Object[]> renOtrList = tdpCadreLocationInfoDAO.getRenewalTdpCadreRecordsCount(currentDate, currentDate);
			if(renOtrList != null && !renOtrList.isEmpty()){
				for (Object[] obj : renOtrList) {
					Long userId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					
					if(userId == 3930l){					//Hyd PartyOfc
						todayVO.setPartyOfcHydRenewal(count);
					}
					else if(userId == 7394l){				//Vij PartyOfc
						todayVO.setPartyOfcVijRenewal(count);
					}
				}
			}
			
			todayVO.setWebNew(todayVO.getWebTotal() - todayVO.getWebRenewal());
			todayVO.setTabNew(todayVO.getTabTotal() - todayVO.getTabRenewal());
			todayVO.setOnlineNew(todayVO.getOnlineTotal() - todayVO.getOnlineRenewal());
			todayVO.setPartyOfcHydNew(todayVO.getPartyOfcHydTotal() - todayVO.getPartyOfcHydRenewal());
			todayVO.setPartyOfcVijNew(todayVO.getPartyOfcVijTotal() - todayVO.getPartyOfcVijRenewal());
			
			/*List<Object[]> list1 = tdpCadreLocationInfoDAO.getDataSourceTypeWiseRegisteredDetails1(currentDate, currentDate);
			if(list1 != null && !list1.isEmpty()){
				for (Object[] obj : list1) {
					String sourceType = obj[0] != null ? obj[0].toString():"";
					Long userId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					Long enrolYrId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
					
					if(userId == 3930l){					//Hyd PartyOfc
						if(enrolYrId == 3l){
							todayVO.setPartyOfcHydRenewal(count);
							todayVO.setPartyOfcHydTotal(todayVO.getPartyOfcHydTotal()+count);
						}
						else if(enrolYrId == 4l){
							todayVO.setPartyOfcHydNew(count);
							todayVO.setPartyOfcHydTotal(todayVO.getPartyOfcHydTotal()+count);
						}
					}
					else if(userId == 7394l){				//Vij PartyOfc
						if(enrolYrId == 3l){
							todayVO.setPartyOfcVijRenewal(count);
							todayVO.setPartyOfcVijTotal(todayVO.getPartyOfcVijTotal()+count);
						}
						else if(enrolYrId == 4l){
							todayVO.setPartyOfcVijNew(count);
							todayVO.setPartyOfcVijTotal(todayVO.getPartyOfcVijTotal()+count);
						}
					}
					
				}
			}
			
			/*List<Object[]> stateList = tdpCadreLocationInfoDAO.get2016LocationWiseRegisteredCounts("Today", 2l);
			if(stateList != null && !stateList.isEmpty()){
				for (Object[] obj : stateList) {
					Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long newCount = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					if(id == 1l){
						todayVO.setApNew(Long.valueOf(s))
					}
				}
			}*/
			
			List<Object[]> totalList = tdpCadreLocationInfoDAO.getTdpCadreRecordsCountLocWise(null,null);
			if(totalList != null && !totalList.isEmpty()){
				for (Object[] obj : totalList) {
					String sourceType = obj[0] != null ? obj[0].toString():"";
					Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					
					if(sourceType.trim().equalsIgnoreCase("WEB")){
						totalVO.setWebTotal(count);
					}
					else if(sourceType.trim().equalsIgnoreCase("TAB")){
						totalVO.setTabTotal(count);
					}
					else if(sourceType.trim().equalsIgnoreCase("ONLINE")){
						totalVO.setOnlineTotal(count);
					}
				}
			}
			
			List<Object[]> othertotalList = tdpCadreLocationInfoDAO.getTdpCadreRecordsCount(null,null);
			if(othertotalList != null && !othertotalList.isEmpty()){
				for (Object[] obj : othertotalList) {
					Long userId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					
					if(userId == 3930l){					//Hyd PartyOfc
						totalVO.setPartyOfcHydTotal(count);
					}
					else if(userId == 7394l){				//Vij PartyOfc
						totalVO.setPartyOfcVijTotal(count);
					}
				}
			}
			
			List<Object[]> renetotalList = tdpCadreLocationInfoDAO.getRenewalTdpCadreRecordsCountLocWise(null, null);
			if(renetotalList != null && !renetotalList.isEmpty()){
				for (Object[] obj : renetotalList) {
					String sourceType = obj[0] != null ? obj[0].toString():"";
					Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					
					if(sourceType.trim().equalsIgnoreCase("WEB")){
						totalVO.setWebRenewal(count);
					}
					else if(sourceType.trim().equalsIgnoreCase("TAB")){
						totalVO.setTabRenewal(count);
					}
					else if(sourceType.trim().equalsIgnoreCase("ONLINE")){
						totalVO.setOnlineRenewal(count);
					}
				}
			}
			
			List<Object[]> renOtrtotalList = tdpCadreLocationInfoDAO.getRenewalTdpCadreRecordsCount(null, null);
			if(renOtrtotalList != null && !renOtrtotalList.isEmpty()){
				for (Object[] obj : renOtrtotalList) {
					Long userId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					
					if(userId == 3930l){					//Hyd PartyOfc
						totalVO.setPartyOfcHydRenewal(count);
					}
					else if(userId == 7394l){				//Vij PartyOfc
						totalVO.setPartyOfcVijRenewal(count);
					}
				}
			}
			
			totalVO.setWebNew(totalVO.getWebTotal() - totalVO.getWebRenewal());
			totalVO.setTabNew(totalVO.getTabTotal() - totalVO.getTabRenewal());
			totalVO.setOnlineNew(totalVO.getOnlineTotal() - totalVO.getOnlineRenewal());
			totalVO.setPartyOfcHydNew(totalVO.getPartyOfcHydTotal() - totalVO.getPartyOfcHydRenewal());
			totalVO.setPartyOfcVijNew(totalVO.getPartyOfcVijTotal() - totalVO.getPartyOfcVijRenewal());
			
			
			/*List<Object[]> list2 = tdpCadreLocationInfoDAO.getDataSourceTypeWiseRegisteredDetails(null, null);
			if(list2 != null && !list2.isEmpty()){
				for (Object[] obj : list2) {
					String sourceType = obj[0] != null ? obj[0].toString():"";
					Long enrolYrId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					Long count = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					
					if(sourceType.trim().equalsIgnoreCase("WEB")){
						if(enrolYrId == 3l){
							totalVO.setWebRenewal(count);
							totalVO.setWebTotal(totalVO.getWebTotal()+count);
						}
						else if(enrolYrId == 4l){
							totalVO.setWebNew(count);
							totalVO.setWebTotal(totalVO.getWebTotal()+count);
						}
					}
					else if(sourceType.trim().equalsIgnoreCase("TAB")){
						if(enrolYrId == 3l){
							totalVO.setTabRenewal(count);
							totalVO.setTabTotal(totalVO.getTabTotal()+count);
						}
						else if(enrolYrId == 4l){
							totalVO.setTabNew(count);
							totalVO.setTabTotal(totalVO.getTabTotal()+count);
						}
					}
					else if(sourceType.trim().equalsIgnoreCase("ONLINE")){
						if(enrolYrId == 3l){
							totalVO.setOnlineRenewal(count);
							totalVO.setOnlineTotal(totalVO.getOnlineTotal()+count);
						}
						else if(enrolYrId == 4l){
							totalVO.setOnlineNew(count);
							totalVO.setOnlineTotal(totalVO.getOnlineTotal()+count);
						}
					}
				}
			}
			
			List<Object[]> list3 = tdpCadreLocationInfoDAO.getDataSourceTypeWiseRegisteredDetails1(null, null);
			if(list3 != null && !list3.isEmpty()){
				for (Object[] obj : list3) {
					String sourceType = obj[0] != null ? obj[0].toString():"";
					Long userId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					Long enrolYrId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
					
					if(userId == 3930l){					//Hyd PartyOfc
						if(enrolYrId == 3l){
							totalVO.setPartyOfcHydRenewal(count);
							totalVO.setPartyOfcHydTotal(totalVO.getPartyOfcHydTotal()+count);
						}
						else if(enrolYrId == 4l){
							totalVO.setPartyOfcHydNew(count);
							totalVO.setPartyOfcHydTotal(totalVO.getPartyOfcHydTotal()+count);
						}
					}
					else if(userId == 7394l){				//Vij PartyOfc
						if(enrolYrId == 3l){
							totalVO.setPartyOfcVijRenewal(count);
							totalVO.setPartyOfcVijTotal(totalVO.getPartyOfcVijTotal()+count);
						}
						else if(enrolYrId == 4l){
							totalVO.setPartyOfcVijNew(count);
							totalVO.setPartyOfcVijTotal(totalVO.getPartyOfcVijTotal()+count);
						}
					}
					
				}
			}*/
			
			returnList.add(todayVO);
			returnList.add(totalVO);
		} catch (Exception e) {
			LOG.error("Exception Occured in getDataSourceTypeWiseRegisteredDetails() method in CadreDashBoardService().",e);
		}
		return returnList;
	}
	public List<List<CadreDashboardVO>> get2016LocationWiseRegisteredCountsForPreviligedUser(Long userId, String locationType, String type){
		 LOG.info("Entered into get2016LocationWiseRegisteredCounts() in CoreDashboardCadreRegistrationService service");
		 try{
			 //for dist
			 List<CadreDashboardVO> cadreDashboardVOList1 = new ArrayList<CadreDashboardVO>();
			 //for const
			 List<CadreDashboardVO> cadreDashboardVOList2 = new ArrayList<CadreDashboardVO>();
			 //for all
			 List<List<CadreDashboardVO>> returnList = new ArrayList<List<CadreDashboardVO>>();
			 CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
			 Map<Long,String> distIdAndNameMap = new HashMap<Long,String>();
			 Set<Long> distIdList = new HashSet<Long>();
			 
			 Map<Long,String> constIdAndNameMap = new HashMap<Long,String>(); 
			 Set<Long> constIdList = new HashSet<Long>();
			 List<Object[]> distList = userDistrictAccessInfoDAO.getLocationIdList(userId);
			 Long locationId = null;
			 if(distList != null && distList.size() > 0){
				 locationId = (Long)distList.get(0)[0];
				 if(locationId > 11L && locationId <= 517L){
					 locationType = "AP";
				 }else{
					 locationType = "TS";  
				 }
			 }
			 //get location info
			 if(distList != null && distList.size() > 0){
				 for(Object[] param : distList){
					 distIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1]));
					 distIdList.add(commonMethodsUtilService.getLongValueForObject(param[0])); 
				 }
			 }else{ 
				 List<Object[]> constList = userConstituencyAccessInfoDAO.getLocationIdList(userId);  
				 locationId = (Long)constList.get(0)[0];
				 Long dstId = constituencyDAO.getDistId(locationId);
				 if(dstId > 11L && dstId <= 517L){
					 locationType = "AP";
				 }else{
					 locationType = "TS";          
				 }  
				 for(Object[] param : constList){
					 constIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1]));
					 constIdList.add(commonMethodsUtilService.getLongValueForObject(param[0]));  
				 }   
			 }
			 
			 //get data
			 if(distIdList.size() > 0){
				 List<Object[]> distResultList = tdpCadreLocationInfoDAO.get2016LocationWiseRegisteredCounts(type,3l,null,new ArrayList<Long>(distIdList));
				 List<Object[]> totalList = null;
				 //if(type != null && type.trim().equalsIgnoreCase("Today")){
				 		totalList = tdpCadreLocationInfoDAO.get2016LocationWiseRegisteredCounts("Total",3l,null,new ArrayList<Long>(distIdList));
				 //	}
				 List<Object[]> list2 = null;
			 	 if(type.equalsIgnoreCase("total")){
			 		list2 = tdpCadreLocationInfoDAO.get2016LocationWiseRegisteredCounts("Today",3l,null, new ArrayList<Long>(distIdList));            
			 	 } 
			 	
			 	 List<Object[]> list3 = cadreSurveyUserAssignDetailsDAO.getMapPowerLocationWise(3l,locationType);   
			 
			 	
			 	 List<Object[]> list4 = delimitationConstituencyDAO.getConstituencyNo(locationType);
			 
				 //  
				 prepairReturnList(cadreDashboardVOList1,distResultList,list2,list3,null,type,3l,totalList,0l);
				 returnList.add(cadreDashboardVOList1);
				 //get all the const belongs to above dist  
				 List<Object[]> constList = constituencyDAO.getConstituenciesList(new ArrayList<Long>(distIdList));
				
				 List<Object[]> constList2 = userConstituencyAccessInfoDAO.getLocationIdList(userId);
				
				 for(Object[] param : constList){
					 constIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[2]),commonMethodsUtilService.getStringValueForObject(param[3]));
					 constIdList.add(commonMethodsUtilService.getLongValueForObject(param[2]));
				 }
				 if(constList2 != null && constList2.size() > 0){
					 for(Object[] param : constList2){
						 constIdList.add(commonMethodsUtilService.getLongValueForObject(param[0]));     
					 }   
				 }  
				 
				 List<Object[]> constResultList = tdpCadreLocationInfoDAO.get2016LocationWiseRegisteredCounts(type,4l,null,new ArrayList<Long>(constIdList));
				 if(type.equalsIgnoreCase("total")){
				 		list2 = tdpCadreLocationInfoDAO.get2016LocationWiseRegisteredCounts("Today",4l,null, new ArrayList<Long>(constIdList));            
				 }
				 list3 = cadreSurveyUserAssignDetailsDAO.getMapPowerLocationWise(4l,locationType);
				 list4 = delimitationConstituencyDAO.getConstituencyNo(locationType);
				 prepairReturnList(cadreDashboardVOList2,constResultList,list2,list3,list4,type,4l,totalList,0l);
				 returnList.add(cadreDashboardVOList2);    
				 return returnList;  
			 } 
			 if(constIdList.size() > 0){                
				 List<Object[]> constResultList = tdpCadreLocationInfoDAO.get2016LocationWiseRegisteredCounts(type,4l,null,new ArrayList<Long>(constIdList));
				 List<Object[]> totalList = null;
				// if(type != null && type.trim().equalsIgnoreCase("Today")){
				 		totalList = tdpCadreLocationInfoDAO.get2016LocationWiseRegisteredCounts("Total",4l,null,new ArrayList<Long>(constIdList));
				 //	}
				 List<Object[]> list2 = null; 
				 if(type.equalsIgnoreCase("total")){
				 	list2 = tdpCadreLocationInfoDAO.get2016LocationWiseRegisteredCounts("Today",4l,null, new ArrayList<Long>(constIdList));            
				 }
				 List<Object[]> list3 = cadreSurveyUserAssignDetailsDAO.getMapPowerLocationWise(4l,locationType); 
				 List<Object[]> list4 = delimitationConstituencyDAO.getConstituencyNo(locationType);    
				 prepairReturnList(cadreDashboardVOList1,constResultList,list2,list3,list4,type,4l,totalList,0l);     
				 returnList.add(cadreDashboardVOList1);      
				 return returnList;      
			 }    
		 }catch(Exception e){
			 e.printStackTrace();
			 LOG.error("Exception raised in get2016LocationWiseRegisteredCounts() in CoreDashboardCadreRegistrationService service", e); 
		 }
		 return null;  
	}
	public List<RegistrationCountVO> getRegistrationCountDtls(String location, Long constId, String scope){  
		LOG.info("entered into getRegistrationCountDtls of WebServiceHandlerService");  
		try{
			CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
			Map<Long,RegistrationCountVO> locationIdAndLocationDtlsMap = new HashMap<Long,RegistrationCountVO>();
			Map<Long,RegistrationCountVO> locationIdAndLocationDtlsMap2 = new HashMap<Long,RegistrationCountVO>();
			Map<Long,Long> locationIdAndCdrCount2014Map = new HashMap<Long,Long>();
			Map<Long,Long> locationIdAndCdrCount2014Map2 = new HashMap<Long,Long>();
			Map<Long,Long> locationIdAndCdrCount2016Map = new HashMap<Long,Long>();
			Map<Long,Long> locationIdAndCdrCount2016Map2 = new HashMap<Long,Long>();
			RegistrationCountVO registrationCountVO = null;
			
			List<Long> locationIdList = new ArrayList<Long>();  
			List<Long> locationIdList2 = new ArrayList<Long>();
			Long locationScopeId = 0l;
			Long locationScopeId2 = 0l;
			List<Object[]> getTotalVtrList = null;
			List<Object[]> getTotalVtrListUrb = null;
			if(location.equalsIgnoreCase("mandal")){
				getTotalVtrList = boothPublicationVoterDAO.getTotalVoterGroupByLocation(location, constId);
				getTotalVtrListUrb = boothPublicationVoterDAO.getTotalVoterGroupByLocationUrb(location, constId);
			}else if(location.equalsIgnoreCase("panchayat")){
				getTotalVtrList = boothPublicationVoterDAO.getTotalVoterGroupByLocation(location, constId);
				getTotalVtrListUrb = boothPublicationVoterDAO.getTotalVoterGroupByLocationUrbWard(location, constId);
			}else{
				getTotalVtrList = boothPublicationVoterDAO.getTotalVoterGroupByLocation(location, constId);
			}       
			
			/*List<Object[]> getTotalVtrForEleBdyList = null;
			if(location.equalsIgnoreCase("booth")){
				getTotalVtrForEleBdyList = boothPublicationVoterDAO.getTotalVoterGroupByLocation("localElectionBody", constId);
			}*/           
			
			if(location.equalsIgnoreCase("mandal")){
				if(getTotalVtrList != null && getTotalVtrList.size() > 0){
					locationScopeId = 5l;//for Tehsil  
					for(Object[] param : getTotalVtrList){
						locationIdList.add(commonMethodsUtilService.getLongValueForObject(param[0]));
					}
				}
				if(getTotalVtrListUrb != null && getTotalVtrListUrb.size() > 0){
					locationScopeId2 = 7l;//for MUNICIPAL-CORP-GMC
					for(Object[] param : getTotalVtrListUrb){
						locationIdList2.add(commonMethodsUtilService.getLongValueForObject(param[0]));
					}
				}  
			}else if(location.equalsIgnoreCase("panchayat")){
				if(getTotalVtrList != null && getTotalVtrList.size() > 0){
					locationScopeId = 6l;//for village  
					for(Object[] param : getTotalVtrList){
						locationIdList.add(commonMethodsUtilService.getLongValueForObject(param[2]));
					}
				}
				if(getTotalVtrListUrb != null && getTotalVtrListUrb.size() > 0){
					locationScopeId2 = 8l;//for ward
					for(Object[] param : getTotalVtrListUrb){
						locationIdList2.add(commonMethodsUtilService.getLongValueForObject(param[2]));    
					}
				}
			}else{
				locationScopeId = 9l;//for booth
				for(Object[] param : getTotalVtrList){
					locationIdList.add(commonMethodsUtilService.getLongValueForObject(param[6]));
				}
			}
			List<Object[]> getTotalEnrledCadreCnt2014 = null; 
			List<Object[]> getTotalEnrledCadreCnt20142 = null; 
			Long year = 3l;//for 2014
			/*if(location.equalsIgnoreCase("booth")){
				getTotalEnrledCadreCnt2014 =  boothPublicationVoterDAO.getTotalCadreLocationWiseForBooth2014(location, constId, year,"all");
			}else{
				getTotalEnrledCadreCnt2014 =  boothPublicationVoterDAO.getTotalCadreLocationWise(location, constId, year,"all");
			}*/
			
			if(location.equalsIgnoreCase("mandal")){
				if(locationIdList.size() > 0){
					getTotalEnrledCadreCnt2014 = tdpCadreLocationInfoDAO.get2014CadreBasedOnLocationIds(locationScopeId,locationIdList);
				}
				if(locationIdList2.size() > 0){
					getTotalEnrledCadreCnt20142 = tdpCadreLocationInfoDAO.get2014CadreBasedOnLocationIds(locationScopeId2,locationIdList2);
				}
			}else if(location.equalsIgnoreCase("panchayat")){
				if(locationIdList.size() > 0){
					getTotalEnrledCadreCnt2014 = tdpCadreLocationInfoDAO.get2014CadreBasedOnLocationIds(locationScopeId,locationIdList);
				}
				if(locationIdList2.size() > 0){
					getTotalEnrledCadreCnt20142 = tdpCadreLocationInfoDAO.get2014CadreBasedOnLocationIds(locationScopeId2,locationIdList2);
				}
			}else{
				getTotalEnrledCadreCnt2014 = tdpCadreLocationInfoDAO.get2014CadreBasedOnLocationIds(locationScopeId,locationIdList);
			}
			    
			
			year = 4l;//for 2016
			List<Object[]> getTotalEnrledCadreCnt2016 = null;
			List<Object[]> getTotalEnrledCadreCnt20162 = null;
			//List<Object[]> getTotalEnrledCadreCnt2016 =  boothPublicationVoterDAO.getTotalCadreLocationWise(location, constId, year,"all");
			
			if(location.equalsIgnoreCase("mandal")){
				if(locationIdList.size() > 0){
					getTotalEnrledCadreCnt2016 =  tdpCadreLocationInfoDAO.getTotalCadreLocationWise(locationScopeId, locationIdList,scope);
				}
				if(locationIdList2.size() > 0){
					getTotalEnrledCadreCnt20162 =  tdpCadreLocationInfoDAO.getTotalCadreLocationWise(locationScopeId2, locationIdList2,scope);
				}
			}else if(location.equalsIgnoreCase("panchayat")){
				if(locationIdList.size() > 0){
					getTotalEnrledCadreCnt2016 =  tdpCadreLocationInfoDAO.getTotalCadreLocationWise(locationScopeId, locationIdList,scope);
				}
				if(locationIdList2.size() > 0){
					getTotalEnrledCadreCnt20162 =  tdpCadreLocationInfoDAO.getTotalCadreLocationWise(locationScopeId2, locationIdList2,scope);
				}
			}else{
				getTotalEnrledCadreCnt2016 =  tdpCadreLocationInfoDAO.getTotalCadreLocationWise(locationScopeId, locationIdList,scope);
			}
			if(location.equalsIgnoreCase("mandal")){
				if(getTotalVtrList != null && getTotalVtrList.size() > 0){
					for(Object[] param : getTotalVtrList){
						if(param[0] != null){
							registrationCountVO = new RegistrationCountVO();
							registrationCountVO.setMandalId(param[0] != null ? (Long)param[0] : 0l);
							registrationCountVO.setMandalName(param[1] != null ? param[1].toString() : "");
							registrationCountVO.setTotalVoter(param[2] != null ? (Long)param[2] : 0l);
							locationIdAndLocationDtlsMap.put(param[0] != null ? (Long)param[0] : 0l, registrationCountVO);
						}
					}
				}
				if(getTotalVtrListUrb != null && getTotalVtrListUrb.size() > 0){
					for(Object[] param : getTotalVtrListUrb){
						if(param[0] != null){
							registrationCountVO = new RegistrationCountVO();
							registrationCountVO.setMandalId(param[0] != null ? (Long)param[0] : 0l);
							registrationCountVO.setMandalName(param[1] != null ? param[1].toString().split(" ")[0]+" Town" : "");
							registrationCountVO.setTotalVoter(param[2] != null ? (Long)param[2] : 0l);
							locationIdAndLocationDtlsMap2.put(param[0] != null ? (Long)param[0] : 0l, registrationCountVO);
						}
					}
				}
			}    
			//for panchayat
			if(location.equalsIgnoreCase("panchayat")){
				if(getTotalVtrList != null && getTotalVtrList.size() > 0){  
					for(Object[] param : getTotalVtrList){
						if(param[0] != null){
							registrationCountVO = new RegistrationCountVO();    
							registrationCountVO.setMandalId(param[0] != null ? (Long)param[0] : 0l);
							registrationCountVO.setMandalName(param[1] != null ? param[1].toString() : "");
							registrationCountVO.setPanchayatId(param[2] != null ? (Long)param[2] : 0l);
							registrationCountVO.setPanchayatName(param[3] != null ? param[3].toString() : "");  
							registrationCountVO.setTotalVoter(param[4] != null ? (Long)param[4] : 0l);
							locationIdAndLocationDtlsMap.put(param[2] != null ? (Long)param[2] : 0l, registrationCountVO);
						}
					}
				}
				if(getTotalVtrListUrb != null && getTotalVtrListUrb.size() > 0){  
					for(Object[] param : getTotalVtrListUrb){
						if(param[0] != null){
							registrationCountVO = new RegistrationCountVO();
							registrationCountVO.setMandalId(param[0] != null ? (Long)param[0] : 0l);
							registrationCountVO.setMandalName(param[1] != null ? param[1].toString().split(" ")[0]+" Town" : "");   
							registrationCountVO.setPanchayatId(param[2] != null ? (Long)param[2] : 0l);
							registrationCountVO.setPanchayatName(param[3] != null ? param[3].toString() : "");  
							registrationCountVO.setTotalVoter(param[4] != null ? (Long)param[4] : 0l);
							locationIdAndLocationDtlsMap2.put(param[2] != null ? (Long)param[2] : 0l, registrationCountVO);        
						}
					}
				}
			}
			//for booth
			if(location.equalsIgnoreCase("booth")){
				if(getTotalVtrList != null && getTotalVtrList.size() > 0){
					for(Object[] param : getTotalVtrList){  
						registrationCountVO = new RegistrationCountVO();
						if(((Long)param[4]) != null){
							registrationCountVO.setLocalElectionBodyId(param[4] != null ? (Long)param[4] : 0l);
							registrationCountVO.setLocalElectionBody(param[5] != null ? param[5].toString() : "");
						}else{
							registrationCountVO.setMandalId(param[0] != null ? (Long)param[0] : 0l);
							registrationCountVO.setMandalName(param[1] != null ? param[1].toString() : "");
							registrationCountVO.setPanchayatId(param[2] != null ? (Long)param[2] : 0l);
							registrationCountVO.setPanchayatName(param[3] != null ? param[3].toString() : "");
						}
						
						registrationCountVO.setBoothId(param[6] != null ? (Long)param[6] : 0l);
						registrationCountVO.setBoothName(param[7] != null ? param[7].toString() : "");
						registrationCountVO.setTotalVoter(param[8] != null ? (Long)param[8] : 0l);
						locationIdAndLocationDtlsMap.put(param[6] != null ? (Long)param[6] : 0l, registrationCountVO);  
					}
				}
			}
			//push cadre count  2014
			if(getTotalEnrledCadreCnt2014 != null && getTotalEnrledCadreCnt2014.size() > 0){
				for(Object[] param : getTotalEnrledCadreCnt2014){
					locationIdAndCdrCount2014Map.put(param[0] != null ? (Long)param[0] : 0l, param[1] != null ? (Long)param[1] : 0l);
				}
				for(Entry<Long,Long> entry : locationIdAndCdrCount2014Map.entrySet()){
					registrationCountVO = locationIdAndLocationDtlsMap.get(entry.getKey());
					if(registrationCountVO != null){
						registrationCountVO.setCadreCount2014(entry.getValue());
					}
				}
			}
			
			if(getTotalEnrledCadreCnt20142 != null && getTotalEnrledCadreCnt20142.size() > 0){
				for(Object[] param : getTotalEnrledCadreCnt20142){
					locationIdAndCdrCount2014Map2.put(param[0] != null ? (Long)param[0] : 0l, param[1] != null ? (Long)param[1] : 0l);
				}
				for(Entry<Long,Long> entry : locationIdAndCdrCount2014Map2.entrySet()){
					registrationCountVO = locationIdAndLocationDtlsMap2.get(entry.getKey());
					if(registrationCountVO != null){
						registrationCountVO.setCadreCount2014(entry.getValue());  
					}
				}
			}
			
			
			//push cadre count  2016
			if(getTotalEnrledCadreCnt2016 != null && getTotalEnrledCadreCnt2016.size() > 0){
				for(Object[] param : getTotalEnrledCadreCnt2016){
					locationIdAndCdrCount2016Map.put(param[0] != null ? (Long)param[0] : 0l, param[1] != null ? (Long)param[1] : 0l);
				}
				for(Entry<Long,Long> entry : locationIdAndCdrCount2016Map.entrySet()){
					registrationCountVO = locationIdAndLocationDtlsMap.get(entry.getKey());
					if(registrationCountVO != null){
						registrationCountVO.setCadreCount2016OverAll(entry.getValue());
					}
				}
			}  
			
			if(getTotalEnrledCadreCnt20162 != null && getTotalEnrledCadreCnt20162.size() > 0){
				for(Object[] param : getTotalEnrledCadreCnt20162){
					locationIdAndCdrCount2016Map2.put(param[0] != null ? (Long)param[0] : 0l, param[1] != null ? (Long)param[1] : 0l);
				}
				for(Entry<Long,Long> entry : locationIdAndCdrCount2016Map2.entrySet()){
					registrationCountVO = locationIdAndLocationDtlsMap2.get(entry.getKey());
					if(registrationCountVO != null){
						registrationCountVO.setCadreCount2016OverAll(entry.getValue());
					}
				}
			}
			
			
			Long renewalCount = 0l;
			Long newCount = 0l;
			Map<Long,Long> locationIdAndRenewalCountMap = new HashMap<Long,Long>();
			Map<Long,Long> locationIdAndRenewalCountMap2 = new HashMap<Long,Long>();
			
			//List<Object[]> locationWiseRenewalCdr = tdpCadreEnrollmentYearDAO.getTotalRenewlCadreLocationWise(location, constId);
			List<Object[]> locationWiseRenewalCdr = null;//tdpCadreLocationInfoDAO.getTotalRenewlCadreLocationWiseCount(locationScopeId, locationIdList,scope);
			List<Object[]> locationWiseRenewalCdr2 = null;
			if(location.equalsIgnoreCase("mandal")){
				if(locationIdList.size() > 0){
					locationWiseRenewalCdr = tdpCadreLocationInfoDAO.getTotalRenewlCadreLocationWiseCount(locationScopeId, locationIdList,scope);
				}
				if(locationIdList2.size() > 0){
					locationWiseRenewalCdr2 = tdpCadreLocationInfoDAO.getTotalRenewlCadreLocationWiseCount(locationScopeId2, locationIdList2,scope);
				}
			}else if(location.equalsIgnoreCase("panchayat")){
				if(locationIdList.size() > 0){
					locationWiseRenewalCdr = tdpCadreLocationInfoDAO.getTotalRenewlCadreLocationWiseCount(locationScopeId, locationIdList,scope);
				}
				if(locationIdList2.size() > 0){
					locationWiseRenewalCdr2 = tdpCadreLocationInfoDAO.getTotalRenewlCadreLocationWiseCount(locationScopeId2, locationIdList2,scope);
				}
			}else{
				locationWiseRenewalCdr = tdpCadreLocationInfoDAO.getTotalRenewlCadreLocationWiseCount(locationScopeId, locationIdList,scope);
			}
			//push renewal cadre count  2016
			if(locationWiseRenewalCdr != null && locationWiseRenewalCdr.size() > 0){   
				for(Object[] param : locationWiseRenewalCdr){
					locationIdAndRenewalCountMap.put(param[0] != null ? (Long)param[0] : 0l, param[1] != null ? (Long)param[1] : 0l);
				}
				for(Entry<Long,Long> entry : locationIdAndRenewalCountMap.entrySet()){
					registrationCountVO = locationIdAndLocationDtlsMap.get(entry.getKey());
					if(registrationCountVO != null){
						registrationCountVO.setRenewalCount(entry.getValue());
						renewalCount = entry.getValue();
						newCount = registrationCountVO.getCadreCount2016OverAll()-renewalCount;
						registrationCountVO.setNewCount(newCount);
					}  
				}
			}
			
			if(locationWiseRenewalCdr2 != null && locationWiseRenewalCdr2.size() > 0){   
				for(Object[] param : locationWiseRenewalCdr2){
					locationIdAndRenewalCountMap2.put(param[0] != null ? (Long)param[0] : 0l, param[1] != null ? (Long)param[1] : 0l);
				}
				for(Entry<Long,Long> entry : locationIdAndRenewalCountMap2.entrySet()){
					registrationCountVO = locationIdAndLocationDtlsMap2.get(entry.getKey());
					if(registrationCountVO != null){
						registrationCountVO.setRenewalCount(entry.getValue());
						renewalCount = entry.getValue();
						newCount = registrationCountVO.getCadreCount2016OverAll()-renewalCount;
						registrationCountVO.setNewCount(newCount);
					}
				}
			}
			
			
			if(locationIdAndLocationDtlsMap.size() > 0){
				for(Entry<Long,RegistrationCountVO> entry : locationIdAndLocationDtlsMap.entrySet()){
					registrationCountVO = locationIdAndLocationDtlsMap.get(entry.getKey());
					if(registrationCountVO.getRenewalCount() == 0l){
						registrationCountVO.setNewCount(registrationCountVO.getCadreCount2016OverAll());
					}
					
				}
			}
			
			if(locationIdAndLocationDtlsMap2.size() > 0){
				for(Entry<Long,RegistrationCountVO> entry : locationIdAndLocationDtlsMap2.entrySet()){
					registrationCountVO = locationIdAndLocationDtlsMap2.get(entry.getKey());
					if(registrationCountVO.getRenewalCount() == 0l){
						registrationCountVO.setNewCount(registrationCountVO.getCadreCount2016OverAll());
					}
					
				}
			}
			List<RegistrationCountVO> countVOs = new ArrayList<RegistrationCountVO>(locationIdAndLocationDtlsMap.values());
			countVOs.addAll(new ArrayList<RegistrationCountVO>(locationIdAndLocationDtlsMap2.values()));    
			return countVOs;  
				
		}catch(Exception e){    
			e.printStackTrace();
			LOG.error("exception occured in getRegistrationCountDtls of WebServiceHandlerService", e);
		}
		return null;  
	}	
	public List<IdAndNameVO> getCadreRegistrationCountByConstituency(Long constituencyId,String fromDateStr,String toDateStr){
		try{
			CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
			List<IdAndNameVO> idAndNameVOs = new ArrayList<IdAndNameVO>();
			IdAndNameVO idAndNameVO = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
			Date fromDate = null;
			Date toDate = null;
			if(fromDateStr != null && fromDateStr.length() > 0 && toDateStr != null && toDateStr.length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<Object[]> tabUserDtlsList = tabUserEnrollmentInfoDAO.getTabUserDtlsList(constituencyId,fromDate,toDate);
			if(tabUserDtlsList != null && tabUserDtlsList.size() > 0){
				for(Object[] param : tabUserDtlsList){
					idAndNameVO = new IdAndNameVO();
					idAndNameVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					idAndNameVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					idAndNameVO.setImagePathStr(commonMethodsUtilService.getStringValueForObject(param[2]));
					idAndNameVO.setMobileNumber(commonMethodsUtilService.getStringValueForObject(param[3]));
					idAndNameVO.setApTotal(commonMethodsUtilService.getLongValueForObject(param[4]));
					idAndNameVO.setStartTime(commonMethodsUtilService.getStringValueForObject(param[5]));
					idAndNameVO.setEndTime(commonMethodsUtilService.getStringValueForObject(param[6]));
					idAndNameVOs.add(idAndNameVO);
				}
			}
			return idAndNameVOs;
			
		}catch(Exception e){
			e.printStackTrace();     
		}
		return null;  
		
	}
	
	public List<IdAndNameVO> getDataSourceTypeWiseCountsByType(String type){
		List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
		try {
			IdAndNameVO tabvo = new IdAndNameVO();
			tabvo.setName(IConstants.CADRE_DATA_SOURCE_TYPE_TAB);
			returnList.add(tabvo);
			IdAndNameVO webvo = new IdAndNameVO();
			webvo.setName(IConstants.CADRE_DATA_SOURCE_TYPE_WEB);
			returnList.add(webvo);
			IdAndNameVO onlinevo = new IdAndNameVO();
			onlinevo.setName(IConstants.CADRE_DATA_SOURCE_TYPE_ONLINE);
			returnList.add(onlinevo);
			IdAndNameVO hydPOvo = new IdAndNameVO();
			hydPOvo.setName(IConstants.HYDERABAD_PARY_OFFICE);
			returnList.add(hydPOvo);
			IdAndNameVO vijPOvo = new IdAndNameVO();
			vijPOvo.setName(IConstants.VIJAYAWADA_PARY_OFFICE);
			returnList.add(vijPOvo);
			
			List<Object[]> list = tdpCadreDataSourceTypeInfoDAO.getDataSourceTypeWiseCountsByType(type);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					String name = obj[0] != null ? obj[0].toString():"";
					IdAndNameVO vo = getMatchedVOForName(returnList, name);
					if(vo != null){
						//vo.setName(obj[0] != null ? obj[0].toString():"");
						vo.setInviteeCount(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));    // 2016Count
						vo.setAttenteeCount(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));   // NewCount
						vo.setMobileNumber(obj[3] != null ? obj[3].toString():"");   				 //New Percent
						vo.setInviteeAttendeeCnt(Long.valueOf(obj[4] != null ? obj[4].toString():"0"));//RenewalCount
						vo.setActualMobNumber(obj[5] != null ? obj[5].toString():""); 					//RenewalPer
					}
					else{
						vo = new IdAndNameVO();
						vo.setName(obj[0] != null ? obj[0].toString():"");
						vo.setInviteeCount(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));    // 2016Count
						vo.setAttenteeCount(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));   // NewCount
						vo.setMobileNumber(obj[3] != null ? obj[3].toString():"");   				 //New Percent
						vo.setInviteeAttendeeCnt(Long.valueOf(obj[4] != null ? obj[4].toString():"0"));//RenewalCount
						vo.setActualMobNumber(obj[5] != null ? obj[5].toString():""); 					//RenewalPer
						returnList.add(vo);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getDataSourceTypeWiseCountsByType() method in CadreDashBoardService().",e);
		}
		return returnList;
	}
	
	private IdAndNameVO getMatchedVOForName(List<IdAndNameVO> list, String name)
	{
		IdAndNameVO returnVO = null;
		try {
			if(list != null && list.size()>0){
				for (IdAndNameVO vo : list){
					if(vo.getName().trim().equalsIgnoreCase(name.trim())){
						return vo;
					}
				}
			}
		} catch (Exception e) {
			LOG.error(" exception occured at getMatchedVOForName() in CadreSurveyTransactionService service class. ", e);
		}
		return returnVO;
	}
}
