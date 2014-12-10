package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;

import com.itgrids.partyanalyst.dao.IAppDbUpdateDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserAssignDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserAssigneeDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserDAO;
import com.itgrids.partyanalyst.dao.ICasteCategoryDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.IZebraPrintDetailsDAO;

import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.hibernate.CasteCategoryDAO;
import com.itgrids.partyanalyst.dto.CadreAmountDetailsVO;
import com.itgrids.partyanalyst.dto.CadreDataAnalysisVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TabRecordsStatusVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.service.ILeaderCadreDashBoardService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class LeaderCadreDashBoardService implements ILeaderCadreDashBoardService {

	private static Logger LOG = Logger.getLogger(LeaderCadreDashBoardService.class);
	
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
	private IRegionServiceData regionServiceDataImp;
	private ICasteCategoryDAO casteCategoryDAO;
	private IVoterAgeInfoDAO voterAgeInfoDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IZebraPrintDetailsDAO zebraPrintDetailsDAO;
	
	
	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}

	public ICasteCategoryDAO getCasteCategoryDAO() {
		return casteCategoryDAO;
	}

	public void setCasteCategoryDAO(ICasteCategoryDAO casteCategoryDAO) {
		this.casteCategoryDAO = casteCategoryDAO;
	}

	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
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

	public void testMethod(){
		LOG.info("Enterd into testMethod() in LeaderCaderDashBoardService");
	}
	
	public IVoterAgeInfoDAO getVoterAgeInfoDAO() {
		return voterAgeInfoDAO;
	}

	public void setVoterAgeInfoDAO(IVoterAgeInfoDAO voterAgeInfoDAO) {
		this.voterAgeInfoDAO = voterAgeInfoDAO;
	}

	public IZebraPrintDetailsDAO getZebraPrintDetailsDAO() {
		return zebraPrintDetailsDAO;
	}

	public void setZebraPrintDetailsDAO(IZebraPrintDetailsDAO zebraPrintDetailsDAO) {
		this.zebraPrintDetailsDAO = zebraPrintDetailsDAO;
	}

	public List<CadreAmountDetailsVO> getLoationWiseLeaderCadreDetails(String locationtype,Long stateId,String accessType,String accessValue,Date fromDate,Date toDate)
	{
		List<CadreAmountDetailsVO> resultList = new ArrayList<CadreAmountDetailsVO>(); 
		List<Long> constituenycIds = new ArrayList<Long>();
		List<Long> districtIds = new ArrayList<Long>();
		List<Object[]> voterCountList = null;
		try{
				if(stateId == 2)
				{
				for(int i=1;i<=10;i++)
					districtIds.add(new Long(i));
				}
				else if(stateId == 1)
				{
			    for(int i=11;i<=23;i++)
					districtIds.add(new Long(i));
			    }
				if(stateId == 0)
				{
				    for(int i=1;i<=23;i++)
						districtIds.add(new Long(i));
				}
				
				if(accessType.trim().equalsIgnoreCase(IConstants.DISTRICT))
				{
					if(!districtIds.contains(Long.valueOf(accessValue.trim()).longValue()))
					{
						districtIds.clear();
					}
					else
					{
						districtIds.clear();
						districtIds.add(Long.valueOf(accessValue.trim()).longValue());
					}
				}
				else if(accessType.trim().equalsIgnoreCase(IConstants.MLA))
				{
					Constituency constituency = constituencyDAO.get(Long.valueOf(accessValue.trim()));
					
					if(!districtIds.contains(constituency.getDistrict().getDistrictId().longValue()))
					{
						districtIds.clear();
					}
					else
					{
						districtIds.clear();
						districtIds.add(constituency.getDistrict().getDistrictId());
					}

				}
				
				if(districtIds != null && districtIds.size()>0)
				{
					if(locationtype.equalsIgnoreCase(IConstants.DISTRICT))
						voterCountList = voterInfoDAO.getVotersCountInADistrictsList(districtIds,IConstants.VOTER_DATA_PUBLICATION_ID);
					else if(locationtype.equalsIgnoreCase(IConstants.CONSTITUENCY))
						voterCountList = voterInfoDAO.getVotersCountInConstituenciesByDistrictsList(districtIds,IConstants.VOTER_DATA_PUBLICATION_ID);
					if(voterCountList != null && voterCountList.size() > 0)
						for(Object[] params :voterCountList)
						{
							CadreAmountDetailsVO basicVo = new CadreAmountDetailsVO();
							basicVo.setId((Long)params[0]);
							basicVo.setName(params[1] != null ? params[1].toString() : "");
							basicVo.setTotalVoters((Long)params[2]);
							if(stateId == 1)
							basicVo.setTargetCadres((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014);
							else
								basicVo.setTargetCadres((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_TG) / IConstants.TG_VOTERS_2014);
							if(locationtype.equalsIgnoreCase(IConstants.CONSTITUENCY))
								constituenycIds.add((Long)params[0]);
							
							if(accessType.equalsIgnoreCase(IConstants.MLA) && basicVo.getId().longValue() == Long.valueOf(accessValue.trim()).longValue())
								resultList.add(basicVo);
							else if(!accessType.equalsIgnoreCase(IConstants.MLA))
								resultList.add(basicVo);
						}
					
					List<Object[]> receivedAmountDetails = cadreSurveyUserAssignDetailsDAO.getTDPCadreAmountDetails(districtIds,locationtype,fromDate,toDate);
					if(receivedAmountDetails != null && receivedAmountDetails.size() > 0)
					{
						for(Object[] params : receivedAmountDetails)
						{
							  CadreAmountDetailsVO vo = getMatchedVO(resultList,(Long)params[1]);
							  if(vo != null)
							  vo.setPaidAmount((Long)params[0]);
							 
						}
					}
					List<Object[]> totalRecords = tdpCadreDAO.getTotalRecords(districtIds,locationtype,fromDate,toDate);
					
					if(totalRecords != null && totalRecords.size() > 0)
					{
						for(Object[] params : totalRecords)
						{
							  CadreAmountDetailsVO vo = getMatchedVO(resultList,(Long)params[1]);
							  if(vo != null)
							  {
								  vo.setTotalRecords(params[0] != null ? (Long)params[0] : 0);
								  vo.setTotalAmount(vo.getTotalRecords() * 100);
								  vo.setDifference(vo.getTotalAmount() - vo.getPaidAmount());
								  String percentage ="";
								  if(vo.getTargetCadres() > 0)
								  percentage = (new BigDecimal(vo.getTotalRecords()*(100.0)/vo.getTargetCadres().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
								  vo.setPercentage(percentage);
							  }
						}
					}	
					
					if(constituenycIds != null && constituenycIds.size() > 0)
					{
						List<Long> parliamentIds = new ArrayList<Long>();
						List<Object[]> list = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentsAndDistrictForAssembly(constituenycIds);
						if(list != null && list.size() > 0)
						{
							for(Object[] params : list)
							{
								CadreAmountDetailsVO vo1 = getMatchedVO(resultList,(Long)params[0]);
								if(vo1 != null)
								{
									vo1.setParliamentId((Long)params[1]);
									vo1.setParliament(params[2] != null ? params[2].toString() : "");
									vo1.setDistrictId((Long)params[3]);
									vo1.setDistrictName(params[4] != null ? params[4].toString() : "");
								}
								if(!parliamentIds.add((Long)params[1]))
								parliamentIds.add((Long)params[1]);
							}
						}
						
						List<Object[]> pcList = delimitationConstituencyDAO.getConstituencyNosByConstituency(parliamentIds,2009l,1l);
						Map<Long,String> pcConstiNoMap = new HashMap<Long, String>();
						if(pcList != null && pcList.size() > 0)
						{
							for(Object[] params : pcList)
							{
								String constiNo = params[1] != null ? params[1].toString() : "";
								pcConstiNoMap.put((Long)params[0], constiNo);
							}
						}
						List<Object[]> list1 = delimitationConstituencyDAO.getConstituencyNosByConstituency(constituenycIds,2009l,2l);
						if(list1 != null && list1.size() > 0)
						{
							for(Object[] params : list1)
							{
								CadreAmountDetailsVO vo1 = getMatchedVO(resultList,(Long)params[0]);
									if(vo1 != null)
									{
										String constiNo = params[1] != null ? params[1].toString() : "";
										vo1.setConstiNo(constiNo);
										vo1.setPcConstiNo(pcConstiNoMap.get(vo1.getParliamentId()));
									}
							 	}
							}
						}
					}
		}
		catch (Exception e) {
			LOG.info("Enterd into getLoationWiseLeaderCadreDetails() in LeaderCaderDashBoardService");
		}
		return resultList;
	}
	
	
	public List<CadreAmountDetailsVO> getSubLevelLoationWiseLeaderCadreDetails(String type,Long id,String accessType,String accessValue,Date fromDate,Date toDate)
	{
		List<CadreAmountDetailsVO> resultList = new ArrayList<CadreAmountDetailsVO>(); 
		
		List<Object[]> voterCountList = null;
		String locationtype = "";
		List<Long> districtIds = new ArrayList<Long>();
		List<Long> tehsilIds = new ArrayList<Long>();
		List<Long> constituencyIds = new ArrayList<Long>();
		List<Long> localbodyIds = new ArrayList<Long>();
		try{
				if(type.equalsIgnoreCase(IConstants.DISTRICT))
				{
							locationtype = IConstants.CONSTITUENCY;
							districtIds.add(id);
							voterCountList = voterInfoDAO.getVotersCountInConstituenciesByDistrictsList(districtIds,IConstants.VOTER_DATA_PUBLICATION_ID);
							setLocationWiseCadreData(locationtype,districtIds,voterCountList,constituencyIds,resultList,fromDate,toDate);
				}
				
				if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
				{
							
							Constituency constituency = constituencyDAO.get(id);
							constituencyIds.add(id);
							if(constituency.getAreaType().equalsIgnoreCase(IConstants.RURAL)|| constituency.getAreaType().equalsIgnoreCase(IConstants.RURALURBAN))
							{
								 List<SelectOptionVO> list = regionServiceDataImp.getSubRegionsInConstituency(id,  IConstants.PRESENT_YEAR, constituency.getAreaType());
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
								locationtype = IConstants.TEHSIL;
							    voterCountList = voterInfoDAO.getVotersCountInATehsilList(tehsilIds,IConstants.VOTER_DATA_PUBLICATION_ID);
							    setLocationWiseCadreData(locationtype,tehsilIds,voterCountList,constituencyIds,resultList,fromDate,toDate);
							}
							if(localbodyIds != null && localbodyIds.size() > 0)
							{
								locationtype = IConstants.LOCAL_BODY_ELECTION;
								List<Long> assmblyLclIds = new ArrayList<Long>();
								assmblyLclIds = assemblyLocalElectionBodyDAO.getLEBIdsByALEBIds(localbodyIds);
								if(assmblyLclIds!=null && assmblyLclIds.size()>0){
									voterCountList = voterInfoDAO.getVotersCountInALocalBodyList(assmblyLclIds,IConstants.VOTER_DATA_PUBLICATION_ID);
								}
							    setLocationWiseCadreData(locationtype,assmblyLclIds,voterCountList,constituencyIds,resultList,fromDate,toDate);
							}
				}
				
				
			
			
		}
		catch (Exception e) {
			LOG.info("Enterd into getLoationWiseLeaderCadreDetails() in LeaderCaderDashBoardService");
		}
		return resultList;
	}
	public CadreAmountDetailsVO getCasteWiseDetails(Long stateId,String locationtype)
	{
		
		List<Long> districtIds = new ArrayList<Long>();
		CadreAmountDetailsVO returnVo = new CadreAmountDetailsVO();
		List<Object[]> voterCountList = null;
		List<Long> constituencyIds = new ArrayList<Long>();
		List<CadreAmountDetailsVO> resultList = new ArrayList<CadreAmountDetailsVO>(); 
		try{
		if(stateId == 1){
			for(int i=11;i<=23;i++){
				districtIds.add(new Long(i));
			}
		}else{
			for(int i=1;i<=10;i++){
				districtIds.add(new Long(i));
			}
		}
			
			if(districtIds != null && districtIds.size()>0)
			{
				if(locationtype.equalsIgnoreCase(IConstants.DISTRICT))
				voterCountList = voterInfoDAO.getVotersCountInADistrictsList(districtIds,IConstants.VOTER_DATA_PUBLICATION_ID);
				setLocationWiseCadreCasteData(locationtype,districtIds,voterCountList,resultList);
			}
			if(resultList!= null && resultList.size()>0)
			{
				for(CadreAmountDetailsVO vo : resultList)
				{
					if(vo.getInfoList() != null && vo.getInfoList().size()> 0)
					for(CadreAmountDetailsVO castVo : vo.getInfoList())
					{
						 String percentage ="";
						 if(vo.getDifference() > 0)
						  percentage = (new BigDecimal(castVo.getTotalCount()*(100.0)/vo.getDifference().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						  castVo.setPercentage(percentage);
					}
				}
			}
			returnVo.setInfoList(resultList);
		}
		catch(Exception e)
		{
			LOG.error("Exception rised in  getCasteWiseDetails() in LeaderCaderDashBoardService",e);	
		}
		return returnVo;
	}
	
	
	public CadreAmountDetailsVO getSubLoctaionCasteWiseDetails(Long stateId,String locationtype,Long Id)
	{
		
		List<Long> districtIds = new ArrayList<Long>();
		CadreAmountDetailsVO returnVo = new CadreAmountDetailsVO();
		List<Object[]> voterCountList = null;
		List<Long> tehsilIds = new ArrayList<Long>();
		List<Long> constituencyIds = new ArrayList<Long>();
		List<Long> localbodyIds = new ArrayList<Long>();
		List<CadreAmountDetailsVO> resultList = new ArrayList<CadreAmountDetailsVO>(); 
		try{
			returnVo.setInfoList(resultList);
			String subType = "";
		if(locationtype.equalsIgnoreCase(IConstants.DISTRICT))
		{
					subType = IConstants.CONSTITUENCY;
					 constituencyIds = constituencyDAO.getConstituenciesInADistrict(Id);
					districtIds.add(Id);
					voterCountList = voterInfoDAO.getVotersCountInConstituenciesByDistrictsList(districtIds,IConstants.VOTER_DATA_PUBLICATION_ID);
					setLocationWiseCadreCasteData(subType,constituencyIds,voterCountList,resultList);
		}
		
		if(locationtype.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{
					
					Constituency constituency = constituencyDAO.get(Id);
					constituencyIds.add(Id);
					if(constituency.getAreaType().equalsIgnoreCase(IConstants.RURAL)|| constituency.getAreaType().equalsIgnoreCase(IConstants.RURALURBAN))
					{
						 List<SelectOptionVO> list = regionServiceDataImp.getSubRegionsInConstituency(Id,  IConstants.PRESENT_YEAR, constituency.getAreaType());
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
						subType = IConstants.TEHSIL;
					    voterCountList = voterInfoDAO.getVotersCountInATehsilList(tehsilIds,IConstants.VOTER_DATA_PUBLICATION_ID);
					    setLocationWiseCadreCasteData(subType,tehsilIds,voterCountList,resultList);
					}
					if(localbodyIds != null && localbodyIds.size() > 0)
					{
						subType = IConstants.LOCAL_ELECTION_BODY;
						voterCountList = voterInfoDAO.getVotersCountInALocalBodyList(localbodyIds,IConstants.VOTER_DATA_PUBLICATION_ID);
						setLocationWiseCadreCasteData(subType,localbodyIds,voterCountList,resultList);
					}
		}
		if(resultList!= null && resultList.size()>0)
		{
			for(CadreAmountDetailsVO vo : resultList)
			{
				if(vo.getInfoList() != null && vo.getInfoList().size()> 0)
				for(CadreAmountDetailsVO castVo : vo.getInfoList())
				{
					 String percentage ="";
					 if(vo.getDifference() > 0 )
					  percentage = (new BigDecimal(castVo.getTotalCount()*(100.0)/vo.getDifference().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					  castVo.setPercentage(percentage);
				}
			}
		}
			
		}
		catch(Exception e)
		{
			LOG.error("Exception rised in  getCasteWiseDetails() in LeaderCaderDashBoardService",e);	
		}
		return returnVo;
	}
		public void setLocationWiseCadreCasteData(String locationtype,List<Long> Ids,List<Object[]> voterCountList,List<CadreAmountDetailsVO> resultList)
		{
			try{
				
			if(voterCountList != null && voterCountList.size() > 0)
				for(Object[] params :voterCountList)
				{
					CadreAmountDetailsVO basicVo = new CadreAmountDetailsVO();
					basicVo.setId((Long)params[0]);
					if(locationtype.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION))
					basicVo.setName(params[1] != null ? params[1].toString()+ "Muncipality" : "");
					else
					basicVo.setName(params[1] != null ? params[1].toString() : "");	
					basicVo.setTotalVoters((Long)params[2]);
					basicVo.setUserType(locationtype);
					basicVo.setInfoList(getCategories());
					resultList.add(basicVo);
				}
			List<Object[]> totalRecords = tdpCadreDAO.getTotalRecordsByIds(Ids,locationtype,null,null);
			if(totalRecords != null && totalRecords.size() > 0)
			{
				for(Object[] params : totalRecords)
				{
					  CadreAmountDetailsVO vo = getMatchedVO1(resultList,(Long)params[1],locationtype);
					  if(vo != null)
					  {
						  
						  vo.setTotalRecords(params[0] != null ? (Long)params[0] : 0);
						 
					  }
				}
			}	
			List<Object[]> casteData = tdpCadreDAO.getCastGroupWiseCadreCountExcludeminority(Ids,locationtype);
			if(casteData != null && casteData.size() > 0)
			{
				 for(Object[] params : casteData)
				 {
					 CadreAmountDetailsVO vo = getMatchedVO1(resultList,(Long)params[3],locationtype);
					  if(vo != null)
					  {
						  CadreAmountDetailsVO casteVo = getMatchedVO(vo.getInfoList(),(Long)params[1]);
						  if(casteVo != null)
						  {
							  
						  casteVo.setTotalCount((Long)params[0]);
						  vo.setDifference(casteVo.getTotalCount() + vo.getDifference());
						  }
					  } 
				 }
			}
			List<Object[]> casteData1 = tdpCadreDAO.getCastGroupWiseCadreCountMinority(Ids,locationtype);
			if(casteData1 != null && casteData1.size() > 0)
			{
				 for(Object[] params : casteData1)
				 {
					 CadreAmountDetailsVO vo = getMatchedVO1(resultList,(Long)params[1],locationtype);
					  if(vo != null)
					  {
						  CadreAmountDetailsVO casteVo1 = getMatchedVO(vo.getInfoList(),0l);
						  if(casteVo1 != null)
						  {
						  casteVo1.setTotalCount((Long)params[0]);
						  vo.setDifference(casteVo1.getTotalCount() + vo.getDifference());
						  }
					  } 
				 }
			}
			
			if((Ids != null && Ids.size() > 0) && locationtype.equalsIgnoreCase(IConstants.CONSTITUENCY))
			{
				List<Object[]> list = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentsAndDistrictForAssembly(Ids);
				if(list != null && list.size() > 0)
				{
					for(Object[] params : list)
					{
						CadreAmountDetailsVO vo1 = getMatchedVO(resultList,(Long)params[0]);
						if(vo1 != null)
						{
							vo1.setParliamentId((Long)params[1]);
							vo1.setParliament(params[2] != null ? params[2].toString() : "");
							vo1.setDistrictId((Long)params[3]);
							vo1.setDistrictName(params[4] != null ? params[4].toString() : "");
							vo1.setConstituency(params[5] != null ? params[5].toString() : "");
							}
						
					}
				}
			}
			}
			catch(Exception e){
					LOG.error("Exception Raised in setLocationWiseCadreCasteData()" + e);
			}
		}
		
		public List<CadreAmountDetailsVO> getCategories()
		{
			List<CadreAmountDetailsVO> casteCategories = new ArrayList<CadreAmountDetailsVO>();
			List<Object[]> categories = casteCategoryDAO.getAllCasteCategoryDetails();
			if(categories != null && categories.size()> 0)
			{
				for(Object[] params : categories)
				{
					CadreAmountDetailsVO categoryVo = new CadreAmountDetailsVO();
					categoryVo.setId((Long)params[0]);
					categoryVo.setCasteCategory(params[1].toString());
					casteCategories.add(categoryVo);
				}
				CadreAmountDetailsVO categoryVo1 = new CadreAmountDetailsVO();
				categoryVo1.setId(0l);
				categoryVo1.setCasteCategory("minority");
				casteCategories.add(categoryVo1);
			}
			return casteCategories;
		}
	public void setLocationWiseCadreData(String locationtype,List<Long> Ids,List<Object[]> voterCountList,List<Long> constituencyIds,List<CadreAmountDetailsVO> resultList,Date fromDate,Date toDate)
	{
		
		List<Long> districtIds = new ArrayList<Long>();
		
		try{
		if(voterCountList != null && voterCountList.size() > 0)
			for(Object[] params :voterCountList)
			{
				String state = "";
				CadreAmountDetailsVO basicVo = new CadreAmountDetailsVO();
				basicVo.setId((Long)params[0]);
				basicVo.setUserType(locationtype);
				if(locationtype.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION))
				basicVo.setName(params[1] != null ? params[1].toString()+ " Muncipality" : "");
				else
				basicVo.setName(params[1] != null ? params[1].toString() : "");	
				basicVo.setTotalVoters((Long)params[2]);
				if(locationtype.equalsIgnoreCase(IConstants.CONSTITUENCY))
				 state = getStateByDistrictId(Ids.get(0));//DistrictId
				else if(locationtype.equalsIgnoreCase(IConstants.TEHSIL) || locationtype.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION))
					 state = getStateByDistrictId((Long)params[3]);//DistrictId
				if(state.equalsIgnoreCase("AP"))
				basicVo.setTargetCadres((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014);
				else
				basicVo.setTargetCadres((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_TG) / IConstants.TG_VOTERS_2014);
				if(locationtype.equalsIgnoreCase(IConstants.CONSTITUENCY))
				constituencyIds.add((Long)params[0]);
				resultList.add(basicVo);
			}
		if(locationtype.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{
			List<Object[]> receivedAmountDetails = cadreSurveyUserAssignDetailsDAO.getTDPCadreAmountDetails(Ids,locationtype,fromDate,toDate);
			if(receivedAmountDetails != null && receivedAmountDetails.size() > 0)
			{
				for(Object[] params : receivedAmountDetails)
				{
					  CadreAmountDetailsVO vo = getMatchedVO(resultList,(Long)params[1]);
					  if(vo != null)
					  vo.setPaidAmount((Long)params[0]);
					 
				}
			}
		}	
		List<Object[]> totalRecords = tdpCadreDAO.getTotalRecords(Ids,locationtype,fromDate,toDate);
		
		if(totalRecords != null && totalRecords.size() > 0)
		{
			for(Object[] params : totalRecords)
			{
				  CadreAmountDetailsVO vo = getMatchedVO1(resultList,(Long)params[1],locationtype);
				  if(vo != null)
				  {
					  vo.setTotalRecords(params[0] != null ? (Long)params[0] : 0);
					  vo.setTotalAmount(vo.getTotalRecords() * 100);
					  if(locationtype.equalsIgnoreCase(IConstants.CONSTITUENCY))
					  vo.setDifference(vo.getTotalAmount() - vo.getPaidAmount());
					  String percentage ="";
					  if(vo.getTargetCadres() > 0 )
					  percentage = (new BigDecimal(vo.getTotalRecords()*(100.0)/vo.getTargetCadres().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					  vo.setPercentage(percentage);
				  }
			}
		}	
		
		if(constituencyIds != null && constituencyIds.size() > 0)
		{
			List<Long> parliamentIds = new ArrayList<Long>();
			List<Object[]> list = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentsAndDistrictForAssembly(constituencyIds);
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					CadreAmountDetailsVO vo1 = getMatchedVO(resultList,(Long)params[0]);
					if(vo1 != null)
					{
						vo1.setParliamentId((Long)params[1]);
						vo1.setParliament(params[2] != null ? params[2].toString() : "");
						vo1.setDistrictId((Long)params[3]);
						vo1.setDistrictName(params[4] != null ? params[4].toString() : "");
					}
					if(!parliamentIds.add((Long)params[1]))
					parliamentIds.add((Long)params[1]);
				}
			}
			
			List<Object[]> pcList = delimitationConstituencyDAO.getConstituencyNosByConstituency(parliamentIds,2009l,1l);
			Map<Long,String> pcConstiNoMap = new HashMap<Long, String>();
			if(pcList != null && pcList.size() > 0)
			{
				for(Object[] params : pcList)
				{
					String constiNo = params[1] != null ? params[1].toString() : "";
					pcConstiNoMap.put((Long)params[0], constiNo);
				}
			}
			List<Object[]> list1 = delimitationConstituencyDAO.getConstituencyNosByConstituency(constituencyIds,2009l,2l);
			if(list1 != null && list1.size() > 0)
			{
				for(Object[] params : list1)
				{
					CadreAmountDetailsVO vo1 = getMatchedVO(resultList,(Long)params[0]);
						if(vo1 != null)
						{
							String constiNo = params[1] != null ? params[1].toString() : "";
							vo1.setConstiNo(constiNo);
							vo1.setPcConstiNo(pcConstiNoMap.get(vo1.getParliamentId()));
						}
				 	}
				}
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	public CadreAmountDetailsVO getMatchedVO(List<CadreAmountDetailsVO> resultList,Long id)
	{
		try{
			if(resultList == null || resultList.size() == 0 || id == null)
				return null;
			for(CadreAmountDetailsVO vo :resultList)
			{
				if(vo.getId().longValue() == id.longValue())
					return vo;
			}
		}
		catch (Exception e) {
			return null;
		}
		return null;
	}
	public CadreDataAnalysisVO getMatchedVO2(List<CadreDataAnalysisVO> resultList,Long id)
	{
		try{
			if(resultList == null || resultList.size() == 0 || id == null)
				return null;
			for(CadreDataAnalysisVO vo :resultList)
			{
				if(vo.getId().longValue() == id.longValue())
					return vo;
			}
		}
		catch (Exception e) {
			return null;
		}
		return null;
	}
	public CadreAmountDetailsVO getMatchedVO1(List<CadreAmountDetailsVO> resultList,Long Id,String type)
	{
		try{
			if(resultList == null || resultList.size() == 0 || Id == null)
				return null;
			for(CadreAmountDetailsVO vo :resultList)
			{
				if(vo.getId().longValue() == Id.longValue()  && vo.getUserType().equalsIgnoreCase(type))
					return vo;
			}
		}
		catch (Exception e) {
			return null;
		}
		return null;
	}
	public CadreAmountDetailsVO getMatchedParliament(List<CadreAmountDetailsVO> resultList,Long id)
	{
		try{
			if(resultList == null || resultList.size() == 0 || id == null)
				return null;
			for(CadreAmountDetailsVO vo :resultList)
			{
				if(vo.getParliamentId().longValue() == id.longValue())
					return vo;
			}
		}
		catch (Exception e) {
			return null;
		}
		return null;
	}
	
	public String getStateByDistrictId(Long districtId)
	{
		String state = "";
		try{
			
			if(districtId <= 10)
				state = "TG";
			else if(districtId >= 11 && districtId <= 23)
				state = "AP";
		}
		catch (Exception e) {
			LOG.info("Enterd into getStateByDistrictId() in LeaderCaderDashBoardService");
		}
		return state;
	}
	
	public List<CadreAmountDetailsVO> getLocationWiseAsOfNowDetails(String locationtype,Long stateId,String accessType,Long accessValue){
		List<CadreAmountDetailsVO> resultList = new ArrayList<CadreAmountDetailsVO>(); 
		List<Long> constituenycIds = new ArrayList<Long>();
		List<Long> districtIds = new ArrayList<Long>();
		List<Object[]> voterCountList = null;
		
		
		int ok_status_count = 0;
		int good_status_count = 0;
		int best_status_count = 0;
		int poor_status_count = 0;
		int worst_status_count = 0;
		
		try{
				if(stateId == 2){
				for(int i=1;i<=10;i++)
					districtIds.add(new Long(i));}
				else if(stateId == 1){
			    for(int i=11;i<=23;i++)
					districtIds.add(new Long(i));}
				if(stateId == 0){
				    for(int i=1;i<=23;i++)
						districtIds.add(new Long(i));}
				
				
				List<Long> assemblyIds = new ArrayList<Long>();
				//List<Long> districts = new ArrayList<Long>();
				
				if(accessType.equalsIgnoreCase("STATE")){
					assemblyIds = constituencyDAO.getConstituenciesInAState(stateId);
				}
				if(accessType.equalsIgnoreCase("MLA")){
					districtIds = new ArrayList<Long>();
					assemblyIds.add(Long.valueOf(accessValue));
					
					List<Long> dists = constituencyDAO.getDistrictIdByConstituencyId(accessValue);
					districtIds = dists;
				}
				if(accessType.equalsIgnoreCase("MP")){
					districtIds = new ArrayList<Long>();
					List<Long> parlis = new ArrayList<Long>();
					parlis.add(Long.valueOf(accessValue));
					List<Object[]> list = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesListForAListOfParliamentConstituency(parlis);
					
					List<Object[]> list1 = delimitationConstituencyAssemblyDetailsDAO.findDistrictsOfParliamentConstituencies(accessValue);
					if(list!=null && list.size()>0){
						for(Object[] obj:list){
							assemblyIds.add(Long.valueOf(obj[0].toString()));
						}
					}
					if(list1!=null && list1.size()>0){
						for(Object[] obj:list1){
							districtIds.add(Long.valueOf(obj[0].toString()));
						}
					}
				}
				if(accessType.equalsIgnoreCase("DISTRICT")){
					districtIds = new ArrayList<Long>();
					List<Long> dists = new ArrayList<Long>();
					dists.add(Long.valueOf(accessValue));
					List<Long> list = constituencyDAO.getAllConstituencysByDistrictIds(dists, "Assembly");
					assemblyIds = list;
					
					districtIds.add(accessValue);
				}
				
			resultList =  getResultsSimplified(locationtype,accessValue,accessType,resultList,voterCountList,assemblyIds,districtIds,constituenycIds,"no",stateId);
			
			/*if(locationtype.equalsIgnoreCase(IConstants.DISTRICT))
				voterCountList = voterInfoDAO.getVotersCountInADistrictsList(districtIds,IConstants.VOTER_DATA_PUBLICATION_ID);
			else if(locationtype.equalsIgnoreCase(IConstants.CONSTITUENCY))
				voterCountList = voterInfoDAO.getVotersCountInConstituenciesByDistrictsList(districtIds,IConstants.VOTER_DATA_PUBLICATION_ID);
			if(voterCountList != null && voterCountList.size() > 0)
				for(Object[] params :voterCountList){
					if(locationtype.equalsIgnoreCase(IConstants.CONSTITUENCY)){
						if(assemblyIds.contains(Long.valueOf(params[0].toString()))){
							CadreAmountDetailsVO basicVo = new CadreAmountDetailsVO();
							basicVo.setId((Long)params[0]);
							basicVo.setName(params[1] != null ? params[1].toString() : "");
							basicVo.setTotalVoters((Long)params[2]);
							basicVo.setTotalRecords(0l);
							basicVo.setDifference(0l);
							basicVo.setPercentage("0.0");
							basicVo.setColorStatus("Worst");
							
							String currentDate = dateService.getCurrentDateAndTimeInStringFormat();
							if(stateId == 1)
							basicVo.setTargetCadres((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014);
							else
								basicVo.setTargetCadres((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_TG) / IConstants.TG_VOTERS_2014);
							if(locationtype.equalsIgnoreCase(IConstants.CONSTITUENCY))
								constituenycIds.add((Long)params[0]);
							resultList.add(basicVo);
						}
					}else{
						if(districtIds.contains(Long.valueOf(params[0].toString()))){
							CadreAmountDetailsVO basicVo = new CadreAmountDetailsVO();
							basicVo.setId((Long)params[0]);
							basicVo.setName(params[1] != null ? params[1].toString() : "");
							basicVo.setTotalVoters((Long)params[2]);
							basicVo.setTotalRecords(0l);
							basicVo.setDifference(0l);
							basicVo.setPercentage("0.0");
							basicVo.setColorStatus("Worst");
							
							String currentDate = dateService.getCurrentDateAndTimeInStringFormat();
							if(stateId == 1)
							basicVo.setTargetCadres((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014);
							else
								basicVo.setTargetCadres((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_TG) / IConstants.TG_VOTERS_2014);
							if(locationtype.equalsIgnoreCase(IConstants.CONSTITUENCY))
								constituenycIds.add((Long)params[0]);
							resultList.add(basicVo);
						}
						
					}
				}*/
			
			List<Object[]> receivedAmountDetails = cadreSurveyUserAssignDetailsDAO.getTDPCadreAmountDetails(districtIds,locationtype,null,null);
			if(receivedAmountDetails != null && receivedAmountDetails.size() > 0)
			{
				for(Object[] params : receivedAmountDetails)
				{
					  CadreAmountDetailsVO vo = getMatchedVO(resultList,(Long)params[1]);
					  if(vo != null)
					  vo.setPaidAmount((Long)params[0]);
					 
				}
			}
			
			List<Object[]> totalRecords = new ArrayList<Object[]>();
			
			if(locationtype.equalsIgnoreCase(IConstants.DISTRICT)){
				if(accessType.equalsIgnoreCase("MLA") || accessType.equalsIgnoreCase("MP")){
					totalRecords = tdpCadreDAO.getTotalRecordsByAccessType(assemblyIds,locationtype,null,null);
				}else{
					totalRecords = tdpCadreDAO.getTotalRecords(districtIds,locationtype,null,null);
				}
			}else{
				totalRecords = tdpCadreDAO.getTotalRecords(districtIds,locationtype,null,null);
			}
			
			
			if(totalRecords != null && totalRecords.size() > 0)
			{
				for(Object[] params : totalRecords)
				{
					  CadreAmountDetailsVO vo = getMatchedVO(resultList,(Long)params[1]);
					  if(vo != null)
					  {
						  vo.setTotalRecords(params[0] != null ? (Long)params[0] : 0);
						  vo.setTotalAmount(vo.getTotalRecords() * 100);
						  vo.setDifference(vo.getTotalRecords()-vo.getTargetCadres());
						  String percentage ="";
						  percentage = (new BigDecimal(vo.getTotalRecords()*(100.0)/vo.getTargetCadres().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						  //percentage = (new BigDecimal(vo.getDifference()*(100.0)/vo.getTargetCadres().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						  float fl_perc = Float.parseFloat(percentage);
						  if(fl_perc>=120f){
							  best_status_count = best_status_count+1;
							  vo.setColorStatus("Best");
						  }else if(fl_perc>=100f && fl_perc<120f){
							  good_status_count = good_status_count+1;
							  vo.setColorStatus("Good");
						  }else if(fl_perc>=75f && fl_perc<100f){
							  ok_status_count = ok_status_count+1;	
							  vo.setColorStatus("Ok");
						  }else if(fl_perc>=50f && fl_perc<75f){
							  poor_status_count =poor_status_count+1;
							  vo.setColorStatus("Poor");
						  }else {
							  vo.setColorStatus("Worst");
						  }
						  
						  vo.setPercentage(percentage);
					  }
				}
			}
			
			worst_status_count = resultList.size()-best_status_count-good_status_count-ok_status_count-poor_status_count;
			
			if(resultList!=null && resultList.size()>=1){
				CadreAmountDetailsVO cv = resultList.get(0);
				cv.setOkCount(ok_status_count);
				cv.setBestCount(best_status_count);
				cv.setGoodCount(good_status_count);
				cv.setPoorCount(poor_status_count);
				cv.setWorstCount(worst_status_count);
			}
			
			if(constituenycIds != null && constituenycIds.size() > 0)
			{
				List<Object[]> list = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentsAndDistrictForAssembly(constituenycIds);
				if(list != null && list.size() > 0)
				{
					for(Object[] params : list)
					{
						CadreAmountDetailsVO vo1 = getMatchedVO(resultList,(Long)params[0]);
						if(vo1 != null)
						{
							vo1.setParliamentId((Long)params[1]);
							vo1.setParliament(params[2] != null ? params[2].toString() : "");
							vo1.setDistrictId((Long)params[3]);
							vo1.setDistrictName(params[4] != null ? params[4].toString() : "");
							}
						
					}
				}
			}
			
			
		}
		catch (Exception e) {
			LOG.info("Enterd into getLocationWiseAsOfNowDetails() in LeaderCaderDashBoardService");
		}
		return resultList;
	}
	
	public List<CadreAmountDetailsVO> getLocationWiseToDayDetails(String locationtype,Long stateId,String fromTask,String accessType,Long accessValue){
		List<CadreAmountDetailsVO> resultList = new ArrayList<CadreAmountDetailsVO>(); 
		List<Long> constituenycIds = new ArrayList<Long>();
		List<Long> districtIds = new ArrayList<Long>();
		List<Object[]> voterCountList = null;
		
		String date = dateService.getCurrentDateInStringFormatYYYYMMDD();
		
		int ok_status_count = 0;
		int good_status_count = 0;
		int best_status_count = 0;
		int poor_status_count = 0;
		int worst_status_count = 0;
		
		
		try{
				if(stateId == 2){
				for(int i=1;i<=10;i++)
					districtIds.add(new Long(i));}
				else if(stateId == 1){
			    for(int i=11;i<=23;i++)
					districtIds.add(new Long(i));}
				if(stateId == 0){
				    for(int i=1;i<=23;i++)
						districtIds.add(new Long(i));}
				
				List<Long> assemblyIds = new ArrayList<Long>();
				//List<Long> districts = new ArrayList<Long>();
				
				if(accessType.equalsIgnoreCase("STATE")){
					assemblyIds = constituencyDAO.getConstituenciesInAState(stateId);
				}
				if(accessType.equalsIgnoreCase("MLA")){
					districtIds = new ArrayList<Long>();
					assemblyIds.add(Long.valueOf(accessValue));
					
					List<Long> dists = constituencyDAO.getDistrictIdByConstituencyId(accessValue);
					districtIds = dists;
				}
				if(accessType.equalsIgnoreCase("MP")){
					districtIds = new ArrayList<Long>();
					List<Long> parlis = new ArrayList<Long>();
					parlis.add(Long.valueOf(accessValue));
					List<Object[]> list = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesListForAListOfParliamentConstituency(parlis);
					
					List<Object[]> list1 = delimitationConstituencyAssemblyDetailsDAO.findDistrictsOfParliamentConstituencies(accessValue);
					if(list!=null && list.size()>0){
						for(Object[] obj:list){
							assemblyIds.add(Long.valueOf(obj[0].toString()));
						}
					}
					if(list1!=null && list1.size()>0){
						for(Object[] obj:list1){
							districtIds.add(Long.valueOf(obj[0].toString()));
						}
					}
				}
				if(accessType.equalsIgnoreCase("DISTRICT")){
					districtIds = new ArrayList<Long>();
					List<Long> dists = new ArrayList<Long>();
					dists.add(Long.valueOf(accessValue));
					List<Long> list = constituencyDAO.getAllConstituencysByDistrictIds(dists, "Assembly");
					assemblyIds = list;
					
					districtIds.add(accessValue);
				}
				
				
			
			resultList =  getResultsSimplified(locationtype,accessValue,accessType,resultList,voterCountList,assemblyIds,districtIds,constituenycIds,fromTask,stateId);
			
			List<Object[]> receivedAmountDetails = cadreSurveyUserAssignDetailsDAO.getTDPCadreAmountDetails(districtIds,locationtype,null,null);
			if(receivedAmountDetails != null && receivedAmountDetails.size() > 0)
			{
				for(Object[] params : receivedAmountDetails)
				{
					  CadreAmountDetailsVO vo = getMatchedVO(resultList,(Long)params[1]);
					  if(vo != null)
					  vo.setPaidAmount((Long)params[0]);
					 
				}
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date formatedDate = format.parse(date);
			
			List<Object[]> totalRecords = new ArrayList<Object[]>();
			
			if(fromTask.equalsIgnoreCase("today")){
				if(locationtype.equalsIgnoreCase(IConstants.DISTRICT)){
					if(accessType.equalsIgnoreCase("MLA") || accessType.equalsIgnoreCase("MP")){
						totalRecords = tdpCadreDAO.getTotalRecordsByAccessTypeByDate(assemblyIds,locationtype,formatedDate);
					}else{
						totalRecords = tdpCadreDAO.getTotalRecords(districtIds,locationtype,formatedDate);
					}
				}else{
					totalRecords = tdpCadreDAO.getTotalRecords(districtIds,locationtype,formatedDate);
				}
			}else{
				if(locationtype.equalsIgnoreCase(IConstants.DISTRICT)){
					if(accessType.equalsIgnoreCase("MLA") || accessType.equalsIgnoreCase("MP")){
						totalRecords = tdpCadreDAO.getTotalRecordsByAccessTypeUnderDate(assemblyIds,locationtype,formatedDate);
					}else{
						totalRecords = tdpCadreDAO.getTotalRecordsUnderDate(districtIds,locationtype,formatedDate);
					}
				}else{
					totalRecords = tdpCadreDAO.getTotalRecordsUnderDate(districtIds,locationtype,formatedDate);
				}
			}
			
			
			if(totalRecords != null && totalRecords.size() > 0)
			{
				for(Object[] params : totalRecords)
				{
					  CadreAmountDetailsVO vo = getMatchedVO(resultList,(Long)params[1]);
					  if(vo != null)
					  {
						  vo.setTotalRecords(params[0] != null ? (Long)params[0] : 0);
						  vo.setTotalAmount(vo.getTotalRecords() * 100);
						  vo.setDifference(vo.getTotalRecords()- vo.getTargetCadres());
						  String percentage ="";
						  percentage = (new BigDecimal(vo.getTotalRecords()*(100.0)/vo.getTargetCadres().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						  
						 // percentage = (new BigDecimal(vo.getDifference()*(100.0)/vo.getTargetCadres().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						  float fl_perc = Float.parseFloat(percentage);
						  if(fl_perc>=120f){
							  best_status_count = best_status_count+1;
							  vo.setColorStatus("Best");
						  }else if(fl_perc>=100f && fl_perc<120f){
							  good_status_count = good_status_count+1;
							  vo.setColorStatus("Good");
						  }else if(fl_perc>=75f && fl_perc<100f){
							  ok_status_count = ok_status_count+1;	
							  vo.setColorStatus("Ok");
						  }else if(fl_perc>=50f && fl_perc<75f){
							  poor_status_count =poor_status_count+1;
							  vo.setColorStatus("Poor");
						  }else {
							  vo.setColorStatus("Worst");
						  }
						  
						  vo.setPercentage(percentage);
					  }
				}
			}	
			
			worst_status_count = resultList.size()-best_status_count-good_status_count-ok_status_count-poor_status_count;
			
			if(resultList!=null && resultList.size()>=1){
				CadreAmountDetailsVO cv = resultList.get(0);
				cv.setOkCount(ok_status_count);
				cv.setBestCount(best_status_count);
				cv.setGoodCount(good_status_count);
				cv.setPoorCount(poor_status_count);
				cv.setWorstCount(worst_status_count);
			}
			
			if(constituenycIds != null && constituenycIds.size() > 0)
			{
				List<Object[]> list = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentsAndDistrictForAssembly(constituenycIds);
				if(list != null && list.size() > 0)
				{
					for(Object[] params : list)
					{
						CadreAmountDetailsVO vo1 = getMatchedVO(resultList,(Long)params[0]);
						if(vo1 != null)
						{
							vo1.setParliamentId((Long)params[1]);
							vo1.setParliament(params[2] != null ? params[2].toString() : "");
							vo1.setDistrictId((Long)params[3]);
							vo1.setDistrictName(params[4] != null ? params[4].toString() : "");
							}
						
					}
				}
			}
			
			
		}
		catch (Exception e) {
			LOG.info("Enterd into getLocationWiseAsOfNowDetails() in LeaderCaderDashBoardService");
		}
		return resultList;
	}
	
	public CadreAmountDetailsVO getDuplicateUsersInLocation(Date fromDate,Date toDate)
	{
		CadreAmountDetailsVO returnVo = new CadreAmountDetailsVO();
		List<CadreAmountDetailsVO> resultList = new ArrayList<CadreAmountDetailsVO>();
		List<CadreAmountDetailsVO> localbodyList = new ArrayList<CadreAmountDetailsVO>();
		List<Long> constituencyIds = new ArrayList<Long>();
		List<Long> tehsilIds = new ArrayList<Long>();
		List<Long> localbodyIds = new ArrayList<Long>();
		try{
			
			List<Object[]> list = tdpCadreDAO.getDuplicateUsersInConstituencies(fromDate,toDate,IConstants.TEHSIL);
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					if(!tehsilIds.contains((Long)params[2]))
					tehsilIds.add((Long)params[2]);
				}
			returnVo.setInfoList(resultList);
			setDataForDuplicateUsers(list,resultList,constituencyIds,IConstants.TEHSIL);
			List<Object[]> list1 = tdpCadreDAO.getDuplicateUsersCountInConstituencies(fromDate,toDate,IConstants.TEHSIL,tehsilIds);
			setCountForDuplicateUsers(list1,resultList,IConstants.TEHSIL);
			}
			List<Object[]> list2 = tdpCadreDAO.getDuplicateUsersInConstituencies(fromDate,toDate,IConstants.LOCAL_ELECTION_BODY);
			if(list2 != null && list2.size() > 0)
			{
				for(Object[] params : list2)
				{
					if(!localbodyIds.contains((Long)params[2]))
					localbodyIds.add((Long)params[2]);
				}
			
			setDataForDuplicateUsers(list2,resultList,constituencyIds,IConstants.LOCAL_ELECTION_BODY);
			List<Object[]> list3 = tdpCadreDAO.getDuplicateUsersCountInConstituencies(fromDate,toDate,IConstants.LOCAL_ELECTION_BODY,localbodyIds);
			setCountForDuplicateUsers(list3,resultList,IConstants.LOCAL_ELECTION_BODY);
			}
			
			
		}
		catch(Exception e)
		{
			LOG.info("Enterd into getDuplicateUsersInLocation() in LeaderCaderDashBoardService");	
		}
		return returnVo;
	}
	
	public void setDataForDuplicateUsers(List<Object[]> list,List<CadreAmountDetailsVO> resultList,List<Long> constituencyIds,String type)
	{
		try{
			if(list !=null && list.size()> 0)
			{
				for(Object[] params : list)
				{
				if(!constituencyIds.contains((Long)params[0]))
						{
						CadreAmountDetailsVO vo =new CadreAmountDetailsVO();
						vo.setId((Long)params[0]);
						vo.setName(params[1].toString());
						resultList.add(vo);
						constituencyIds.add((Long)params[0]);
						}
				}
				for(Object[] params : list)
				{
				 CadreAmountDetailsVO vo = getMatchedVO(resultList, (Long)params[0]);
					 if(vo != null)
					 {
						 CadreAmountDetailsVO subVo = new CadreAmountDetailsVO();
						 subVo.setId((Long)params[2]);
						 if(type.equalsIgnoreCase(IConstants.TEHSIL))
						 {
						 subVo.setName(params[3].toString());
						 subVo.setUserType((IConstants.TEHSIL));
						 vo.getInfoList().add(subVo);
						 }
						 if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
						 {
							 subVo.setName(params[3].toString() + " Muncipality");
							 subVo.setUserType((IConstants.LOCAL_ELECTION_BODY));
							 vo.getInfoList().add(subVo);
						 }
						
						 
					 }
				}
				if(constituencyIds != null && constituencyIds.size() > 0)
				{
					List<Object[]> list1 = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentsAndDistrictForAssembly(constituencyIds);
					if(list != null && list.size() > 0)
					{
						for(Object[] params : list1)
						{
							CadreAmountDetailsVO vo1 = getMatchedVO(resultList,(Long)params[0]);
							if(vo1 != null)
							{
								vo1.setParliamentId((Long)params[1]);
								vo1.setParliament(params[2] != null ? params[2].toString() : "");
								vo1.setDistrictId((Long)params[3]);
								vo1.setDistrictName(params[4] != null ? params[4].toString() : "");
								}
							
						}
					}
				}
				 
			}
			
		}
		catch(Exception e)
		{
			LOG.info("Enterd into setDataForDuplicateUsers() in LeaderCaderDashBoardService");		
		}
	}
	public void setCountForDuplicateUsers(List<Object[]> list,List<CadreAmountDetailsVO> resultList,String type)
	{
		try{
			if(list !=null && list.size()> 0)
			{
				
				for(Object[] params : list)
				{
					
				 CadreAmountDetailsVO vo = getMatchedVO(resultList, (Long)params[0]);
					 if(vo != null)
					 {
						
						 
							 CadreAmountDetailsVO subVo = getMatchedVO1(vo.getInfoList(), (Long)params[1],type);
							 
							 if(subVo != null)
							 {
								 CadreAmountDetailsVO userVo = new CadreAmountDetailsVO();
								 
									 userVo.setTotalCount((Long)params[2]);
									 userVo.setUserName(params[4] != null ? params[4].toString() : "");
									 userVo.setUserType(params[5] != null ? params[5].toString() : "");
									 userVo.setMobileNo(params[6] != null ? params[6].toString() : "");
									 userVo.setId((Long)params[3]);
									 subVo.getInfoList().add(userVo);
									 vo.setTotalAmount(vo.getTotalAmount() + 1); // all users in constituency
								
							 }
					 }
				}
			}
		 }
		catch(Exception e)
		{
			LOG.info("Enterd into setCountForDuplicateUsers() in LeaderCaderDashBoardService");		
		}
	}
	
	public CadreAmountDetailsVO getUsersInLocation(Long userId,Date fromDate,Date toDate,Long locationId,Long constituencyId,String type) {
	
		CadreAmountDetailsVO returnVo = new CadreAmountDetailsVO();
		List<CadreAmountDetailsVO> resultList = new ArrayList<CadreAmountDetailsVO>();
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			List<Object[]> list = tdpCadreDAO.getDuplicateUsersByUserId(fromDate,toDate,userId,locationId,constituencyId,type);
			if(list != null &&list.size() >0)
			{
				for(Object[] params : list)
				{
				CadreAmountDetailsVO vo = new CadreAmountDetailsVO();	
				vo.setDate(params[1] != null ? params[1].toString(): "");
				vo.setTotalCount((Long)params[0]);
				resultList.add(vo);
				}
	
			}
			
			List<Object[]> list1 = cadreSurveyUserAssigneeDAO.getDuplicateUsersForUserId(userId);
			
			if(list1 != null&& list1.size() > 0)
			{
				for(CadreAmountDetailsVO vo : resultList)
				{
					boolean flag = false;
					for(Object[] params : list1)
					{
						
						/*CadreAmountDetailsVO vo = getMatchedDateVO(resultList,params[2].toString());
						if(vo != null)
						{
						vo.setName(params[0] != null ? params[0].toString() : "");
						vo.setMobileNo(params[1] != null ? params[1].toString() : "");
						vo.setDate(params[2] != null ? params[2].toString() : "");
						vo.setToDate(params[3] != null ? params[3].toString() : "");
						}*/
						Date surveyDate = format.parse(vo.getDate());
						if((surveyDate.equals((Date)params[2])) || (((((Date) params[2]).before(surveyDate))  || (((Date) params[2]).equals(surveyDate)) )&& params[3]== null)||
								( ( (((Date) params[2]).before(surveyDate))  || (((Date) params[2]).equals(surveyDate)) ) && ( ((Date) params[3]).after(surveyDate)) || ((Date) params[3]).equals(surveyDate)) )
						{
							vo.setName(params[0] != null ? params[0].toString() : "");
							vo.setMobileNo(params[1] != null ? params[1].toString() : "");
							vo.setConstituency(params[2] != null ? params[2].toString() : "");
							vo.setToDate(params[3] != null ? params[3].toString() : "");
							flag = true;
						}
						if(flag)
						break;
							
					}
				}
			}
			returnVo.setInfoList(resultList);
		}
		catch(Exception e)
		{
			LOG.info("Enterd into getUsersInLocation() in LeaderCaderDashBoardService");		
		}
		return returnVo;
	}	
	
	public CadreAmountDetailsVO getMatchedDateVO(List<CadreAmountDetailsVO> resultList,String Date)
	{
		try{
			if(resultList == null || resultList.size() == 0 )
				return null;
			for(CadreAmountDetailsVO vo :resultList)
			{
				if(vo.getDate().toString().equalsIgnoreCase(Date.toString()))
					return vo;
			}
		}
		catch (Exception e) {
			return null;
		}
		return null;
	}
	public List<CadreAmountDetailsVO> getYouthMahilaInfo(String locationtype,Long stateId,String accessType,String accessValue,Date fromDate,Date toDate)
	{
		Map<Long,CadreAmountDetailsVO> locationsMap = new HashMap<Long,CadreAmountDetailsVO>();
		List<Long> ids = new ArrayList<Long>();
		List<Object[]> voterCountList = null;
		try{
				if(stateId == 2)
				{
				for(int i=1;i<=10;i++)
					ids.add(new Long(i));
				}
				else if(stateId == 1)
				{
			    for(int i=11;i<=23;i++)
			    	ids.add(new Long(i));
			    }
				if(stateId == 0)
				{
				    for(int i=1;i<=23;i++)
				    	ids.add(new Long(i));
				}
				
				if(accessType.trim().equalsIgnoreCase(IConstants.DISTRICT))
				{
					if(!ids.contains(Long.valueOf(accessValue.trim()).longValue()))
					{
						ids.clear();
					}
					else
					{
						ids.clear();
						ids.add(Long.valueOf(accessValue.trim()).longValue());
					}
				}
				else if(accessType.trim().equalsIgnoreCase(IConstants.MLA))
				{
					Constituency constituency = constituencyDAO.get(Long.valueOf(accessValue.trim()));
					
					if(!ids.contains(constituency.getDistrict().getDistrictId().longValue()))
					{
						ids.clear();
					}
					else
					{
						ids.clear();
						ids.add(constituency.getDistrict().getDistrictId());
					}

				}
				
				if(ids != null && ids.size()>0)
				{
					//getting total voters in location
					if(locationtype.equalsIgnoreCase(IConstants.DISTRICT)){
						//0id, 1name,2 totalvoters,3 male,4 female
						voterCountList = voterInfoDAO.getVotersCountInADistrictsList(ids,IConstants.VOTER_DATA_PUBLICATION_ID);
					}else if(locationtype.equalsIgnoreCase(IConstants.CONSTITUENCY)){//0id,1 name,2 total,3districtId,4male total,5female total
						voterCountList = voterInfoDAO.getVotersCountInConstituenciesByDistrictsList(ids,IConstants.VOTER_DATA_PUBLICATION_ID);
					}
					getOtherInfo(voterCountList, locationsMap, ids, locationtype, fromDate, toDate);
				}
		}
		catch (Exception e) {
			LOG.error("Exception rised in getYouthMahilaInfo() of LeaderCaderDashBoardService",e);
		}
		return new ArrayList<CadreAmountDetailsVO>(locationsMap.values());
	}
	
	public void getOtherInfo(List<Object[]> voterCountList,Map<Long,CadreAmountDetailsVO> locationsMap,List<Long> ids,String locationtype,Date fromDate,Date toDate){

		if(voterCountList != null && voterCountList.size() > 0)
			for(Object[] params :voterCountList)
			{
				CadreAmountDetailsVO basicVo = new CadreAmountDetailsVO();
				basicVo.setId((Long)params[0]);
				basicVo.setName(params[1] != null ? params[1].toString() : "");
				basicVo.setTotalVoters((Long)params[2]);//total voters
				
				if(locationtype.equalsIgnoreCase(IConstants.DISTRICT)){
					basicVo.setRegisteredCadres((Long)params[4]);//female total voters
					if(params[2] != null && ((Long)params[2]).longValue() >0 && params[4] != null){
						basicVo.setPercentage(new BigDecimal((((Long)params[4])*(100.0))/(Long)params[2]).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//femalePercent
					}
				}else{
					basicVo.setRegisteredCadres((Long)params[5]);//female total voters
					if(params[2] != null && ((Long)params[2]).longValue() >0 && params[5] != null){
						basicVo.setPercentage(new BigDecimal((((Long)params[5])*(100.0))/(Long)params[2]).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//femalePercent
					}
				}
				locationsMap.put((Long)params[0],basicVo);
			}
		
		//0 count 1Id
		       //getting total cadres register in location
				List<Object[]> totalRecords = new ArrayList<Object[]>();
				
				if(locationtype.equalsIgnoreCase("constituency")){
					totalRecords = tdpCadreDAO.getTotalRecordsForALocation(ids,locationtype,fromDate,toDate);
				}else{
					totalRecords = tdpCadreDAO.getTotalRecords(ids,locationtype,fromDate,toDate);
				}
					
					
					
				for(Object[] total:totalRecords){
					CadreAmountDetailsVO location = locationsMap.get((Long)total[1]);
					if(location != null){
						location.setTotalCount((Long)total[0]);//totalCadres
						if(location.getTotalVoters() != null && location.getTotalVoters().longValue() >0 && total[0] != null){
						  location.setCadrePerc(new BigDecimal((((Long)total[0])*(100.0))/location.getTotalVoters()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//total cadre Percentage
						}
					}
				}
				
		//0 count 1Id
		
				//getting total voters with age range 18 to 30(youth voters)
		List<Object[]> totalYouthRecords = null;
		if(locationtype.equalsIgnoreCase(IConstants.DISTRICT)){		
		    totalYouthRecords = voterAgeInfoDAO.getYouthVotersInfoForDistrict(ids);
		}else if(locationtype.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			totalYouthRecords = voterAgeInfoDAO.getYouthVotersInfo(null, ids, 1l);
		}else if(locationtype.equalsIgnoreCase(IConstants.TEHSIL)){
			totalYouthRecords = voterAgeInfoDAO.getYouthVotersInfo(null, ids, 2l);
		}else if(locationtype.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY)){
			totalYouthRecords = voterAgeInfoDAO.getYouthVotersInfo(null, ids, 5l);
		}
		if(totalYouthRecords != null){
			for(Object[] total:totalYouthRecords){
				CadreAmountDetailsVO location = locationsMap.get((Long)total[1]);
				if(location != null){
					location.setTargetCadres((Long)total[0]);//youth total voters
					if(location.getTotalVoters() != null && location.getTotalVoters().longValue() >0 && total[0] != null){
					 location.setTotalYouthPerc(new BigDecimal((((Long)total[0])*(100.0))/location.getTotalVoters()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//female Percentage
					}
				}
			}
		}
		totalRecords = null;
		
		//getting total female cadres register in location
		List<Object[]> femaleTotalRecords = tdpCadreDAO.getTotalFemaleRecords(ids,locationtype,fromDate,toDate);
		for(Object[] female:femaleTotalRecords){
			CadreAmountDetailsVO location = locationsMap.get((Long)female[1]);
			if(location != null){
				location.setTotalAmount((Long)female[0]);//female total
				if(location.getTotalCount() != null && location.getTotalCount().longValue() >0 && female[0] != null){
				 location.setFemalePerc(new BigDecimal((((Long)female[0])*(100.0))/location.getTotalCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//female Percentage
				}
			}
		}
		femaleTotalRecords = null;
		
		//getting total youth cadres register in location
        List<Object[]> youthTotalRecords = tdpCadreDAO.getTotalYouthRecords(ids,locationtype,fromDate,toDate);
		for(Object[] youth:youthTotalRecords){
			CadreAmountDetailsVO location = locationsMap.get((Long)youth[1]);
			if(location != null){
				location.setTotalRecords((Long)youth[0]);//young total
				if(location.getTotalCount() != null && location.getTotalCount().longValue() >0 && youth[0] != null){
				  location.setMalePerc(new BigDecimal((((Long)youth[0])*(100.0))/location.getTotalCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//young Percentage
				}
			}
		}
		youthTotalRecords = null;
	
	}
	public List<CadreAmountDetailsVO> getSubLevelLoationWiseYouthMahilaInfo(String type,Long id,String accessType,String accessValue,Date fromDate,Date toDate)
	{
		Map<Long,CadreAmountDetailsVO> locationsMap = new HashMap<Long,CadreAmountDetailsVO>();
		
		List<Object[]> voterCountList = null;
		String locationtype = "";
		List<Long> districtIds = new ArrayList<Long>();
		List<Long> tehsilIds = new ArrayList<Long>();
		List<Long> constituencyIds = new ArrayList<Long>();
		List<Long> localbodyIds = new ArrayList<Long>();
		try{
				if(type.equalsIgnoreCase(IConstants.DISTRICT))
				{
							locationtype = IConstants.CONSTITUENCY;
							districtIds.add(id);
							voterCountList = voterInfoDAO.getVotersCountInConstituenciesByDistrictsList(districtIds,IConstants.VOTER_DATA_PUBLICATION_ID);
							for(Object[] consti:voterCountList){
								constituencyIds.add((Long)consti[0]);
							}
							 getOtherInfo(voterCountList, locationsMap, constituencyIds, locationtype, fromDate, toDate);
				}
				
				if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
				{
							
							Constituency constituency = constituencyDAO.get(id);
							constituencyIds.add(id);
							if(constituency.getAreaType().equalsIgnoreCase(IConstants.RURAL)|| constituency.getAreaType().equalsIgnoreCase(IConstants.RURALURBAN))
							{
								 List<SelectOptionVO> list = regionServiceDataImp.getSubRegionsInConstituency(id,  IConstants.PRESENT_YEAR, constituency.getAreaType());
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
								locationtype = IConstants.TEHSIL;
							    voterCountList = voterInfoDAO.getVotersCountInATehsilList(tehsilIds,IConstants.VOTER_DATA_PUBLICATION_ID);
							    getOtherInfo(voterCountList, locationsMap, tehsilIds, locationtype, fromDate, toDate);
							}
							if(localbodyIds != null && localbodyIds.size() > 0)
							{
								locationtype = IConstants.LOCAL_ELECTION_BODY;
								voterCountList = voterInfoDAO.getVotersCountInALocalBodyList(localbodyIds,IConstants.VOTER_DATA_PUBLICATION_ID);
								getOtherInfo(voterCountList, locationsMap, localbodyIds, locationtype, fromDate, toDate);
							}
				}
				
				
			
			
		}
		catch (Exception e) {
			LOG.error("Exception rised in  getSubLevelLoationWiseYouthMahilaInfo() in LeaderCaderDashBoardService",e);
		}
		return new ArrayList<CadreAmountDetailsVO>(locationsMap.values());
	}
	
	public List<CadreDataAnalysisVO> getCadreBoothAnalysisReport(Long stateId)
	{
		List<CadreDataAnalysisVO> resultList = new ArrayList<CadreDataAnalysisVO>();
		try{
			
			 List<Long> constituencyIds = constituencyDAO.getAllAssemblyConstituencyIdsByStateId(stateId);
			
			 if(constituencyIds != null && constituencyIds.size() > 0)
			 {
			
			 List<Object[]> list = boothDAO.getBoothCountInfoByConstiIds(constituencyIds);
			 		if(list != null && list.size() > 0)
			 		{
			 			for(Object[] params : list)
			 			{
			 				CadreDataAnalysisVO vo = new CadreDataAnalysisVO();
			 				  vo.setId((Long)params[1]);
			 				  vo.setName(params[2].toString());
			 				  vo.setTotalBooths((Long)params[0]);
			 				  resultList.add(vo);
			 			}
			 		
			 			List<Object[]> startedBooths = tdpCadreDAO.getRegistrationStartedLocations(constituencyIds);
			 			if(startedBooths != null && startedBooths.size() > 0)
			 			{
			 				
			 				for(Object[] params : startedBooths)
			 				{
			 				CadreDataAnalysisVO vo1 = getMatchedVO2(resultList,(Long)params[1]);
			 				if(vo1 != null)
			 					vo1.setStartedBooths((Long)params[0]);
			 					
			 				}
			 			}
			 			List<Object[]> below10 = tdpCadreDAO. getBelowCadresBooths(constituencyIds);
			 			if(below10 != null && below10.size() > 0)
			 			{
			 				
			 				for(Object[] params : below10)
			 				{
			 				CadreDataAnalysisVO vo2 = getMatchedVO2(resultList,(Long)params[1]);
				 				if(vo2 != null)
				 				{
				 					vo2.setBelowCadres(vo2.getBelowCadres() + 1);
				 					vo2.getBelow10BoothIds().add((Long)params[0]);
				 				}
			 				}	
			 			}
			 			List<Object[]> genderInfo = tdpCadreDAO.getLocationWiseGenderCadreCount1(constituencyIds,IConstants.CONSTITUENCY);
			 			if(genderInfo != null && genderInfo.size()> 0)
			 			{
			 				for(Object[] params : genderInfo)
			 				{
			 				CadreDataAnalysisVO vo3 = getMatchedVO2(resultList,(Long)params[2]);
				 				if(vo3 != null)
				 				{
				 					CadreDataAnalysisVO boothVo = getMatchedVO2(vo3.getSubList(),(Long)params[3]);
				 					if(boothVo == null)
				 					{
				 						boothVo = new CadreDataAnalysisVO();
				 						if(params[1].toString().equalsIgnoreCase("Male") || params[1].toString().equalsIgnoreCase("M"))
					 						boothVo.setMaleCnt((Long)params[0]);
					 					else if(params[1].toString().equalsIgnoreCase("Female") || params[1].toString().equalsIgnoreCase("F"))
					 						boothVo.setFeMaleCnt((Long)params[0] );
				 						boothVo.setId((Long)params[3]);
					 					vo3.getSubList().add(boothVo);
				 					}
				 					else
				 					{
				 						if(params[1].toString().equalsIgnoreCase("Male") || params[1].toString().equalsIgnoreCase("M"))
					 						boothVo.setMaleCnt((Long)params[0] + boothVo.getMaleCnt());
					 					else if(params[1].toString().equalsIgnoreCase("Female") || params[1].toString().equalsIgnoreCase("F"))
					 						boothVo.setFeMaleCnt((Long)params[0] + boothVo.getFeMaleCnt());
				 					}
				 						
				 				}
			 				}	
			 			}
			 			
			 			if(resultList != null && resultList.size()> 0)
			 			{
			 				for(CadreDataAnalysisVO vo : resultList)
			 				{
			 					if(vo.getSubList() != null && vo.getSubList().size()> 0)
			 					for(CadreDataAnalysisVO boothVo :vo.getSubList())
			 					{
			 						boothVo.setTotal(boothVo.getMaleCnt() + boothVo.getFeMaleCnt());
			 						boothVo.setDifference(boothVo.getMaleCnt() - boothVo.getFeMaleCnt());
			 						boothVo.setPercentage(boothVo.getTotal() > 0 ? (new BigDecimal(boothVo.getDifference()*(100.0)/boothVo.getTotal().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString() : "-");
				 					boothVo.setMalePercentage(boothVo.getTotal() > 0 ? (new BigDecimal(boothVo.getMaleCnt()*(100.0)/boothVo.getTotal().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString() : "-");
				 					boothVo.setFemalePercentage(boothVo.getTotal() > 0 ? (new BigDecimal(boothVo.getFeMaleCnt()*(100.0)/boothVo.getTotal().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString() : "-");
			 						if((boothVo.getDifference()*(100))/boothVo.getTotal().longValue() >= IConstants.CADREPERCENTAGE.longValue())
			 						{
			 						vo.getBoothIds().add(boothVo.getId());
			 						vo.setCount(vo.getCount() + 1);	
			 						}
			 						if((boothVo.getMaleCnt()*(100))/boothVo.getTotal().longValue() >= IConstants.CADREPERCENTAGE.longValue())
			 							vo.setmCount(vo.getmCount() + 1);
			 						if((boothVo.getFeMaleCnt()*(100))/boothVo.getTotal().longValue()>= IConstants.CADREPERCENTAGE.longValue())
			 							vo.setfCount(vo.getfCount() + 1);
			 						
			 					}
			 				}
			 			}
			 			
			 			
			 		}
			 }
		}
		catch(Exception e)
		{
			LOG.error("Exception rised in  getCadreBoothAnalysisReport() in LeaderCaderDashBoardService",e);	
		}
		return resultList;
	}
	
	public List<CadreDataAnalysisVO> getBoothInfo(List<Long> boothIds,Long constituencyId)
	{
		List<CadreDataAnalysisVO> resultList = new ArrayList<CadreDataAnalysisVO>();
		
		try{
			Map<Long,String> boothLocationMap = new HashMap<Long, String>();
			 List<Object[]> list2 = boothDAO.getBoothsInAMuncipality( boothIds);
			 List<Object[]> list3 = boothDAO.getBooths(boothIds);
			 
			 for(Object[] params : list2){
					boothLocationMap.put((Long)params[0], params[2] != null ?  params[2].toString() +" Muncipality" : "");
			}
			for(Object[] params : list3){
					boothLocationMap.put((Long)params[0], params[2] != null ?  params[2].toString() : "");
			}
			List<Object[]> boothInfo = tdpCadreDAO.getBoothWiseGenderCadres(boothIds,constituencyId);
			
			if(boothInfo != null && boothInfo.size() > 0)
			{
				
				for(Object[] params : boothInfo)
	 				{
	 				
		 					CadreDataAnalysisVO boothVo = getMatchedVO2(resultList,(Long)params[2]);
		 					if(boothVo == null)
		 					{
		 						boothVo = new CadreDataAnalysisVO();
		 						if(params[1].toString().equalsIgnoreCase("Male") || params[1].toString().equalsIgnoreCase("M"))
		 						{
			 						boothVo.setMaleCnt((Long)params[0]);
		 						}
			 					else if(params[1].toString().equalsIgnoreCase("Female") || params[1].toString().equalsIgnoreCase("F"))
			 						boothVo.setFeMaleCnt((Long)params[0] );
		 						boothVo.setId((Long)params[2]);
		 						boothVo.setName(params[3] !=null ? params[3].toString():"");
		 						boothVo.setDistrictName(boothLocationMap.get((Long)params[2]));
		 						resultList.add(boothVo);
		 					}
		 					else
		 					{
		 						if(params[1].toString().equalsIgnoreCase("Male") || params[1].toString().equalsIgnoreCase("M"))
			 						boothVo.setMaleCnt((Long)params[0] + boothVo.getMaleCnt());
			 					else if(params[1].toString().equalsIgnoreCase("Female") || params[1].toString().equalsIgnoreCase("F"))
			 						boothVo.setFeMaleCnt((Long)params[0] + boothVo.getFeMaleCnt());
		 					}
		 		
			}
				
				
			}
		}
		catch(Exception e)
		{
			LOG.error("Exception rised in  getBoothInfo() in LeaderCaderDashBoardService",e);		
		}
		return resultList;
	}
	
	public Long getTargetOfDistrict(String fromTask,Long totalVoters,Long stateId){
		Long targetCadre = 0l;
		if(fromTask.equalsIgnoreCase("today")){
			Long noOfDays = dateService.noOfDayBetweenDates(IConstants.CADRE_2014_START_DATE, IConstants.CADRE_2014_LAST_DATE);
			
			if(stateId == 1){
				targetCadre = ((totalVoters * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014)/noOfDays;
			}else{
				targetCadre = ((totalVoters * IConstants.TARGET_CADRE_TG) / IConstants.TG_VOTERS_2014)/noOfDays;
			}
		}else if(fromTask.equalsIgnoreCase("no")){
			if(stateId == 1){
				targetCadre =	(totalVoters * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014;
			}else{
				targetCadre =  (totalVoters * IConstants.TARGET_CADRE_TG) / IConstants.TG_VOTERS_2014;
			}
		}else{
			String currentDate = dateService.getCurrentDateInStringFormatYYYYMMDD();
			Long tillDays = dateService.noOfDayBetweenDates(IConstants.CADRE_2014_START_DATE, currentDate);
			Long noOfDays = dateService.noOfDayBetweenDates(IConstants.CADRE_2014_START_DATE, IConstants.CADRE_2014_LAST_DATE);
			
			if(stateId == 1){
				targetCadre =  (((totalVoters * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014)/noOfDays)*tillDays;
			}else{
				targetCadre =  (((totalVoters * IConstants.TARGET_CADRE_TG) / IConstants.TG_VOTERS_2014)/noOfDays)*tillDays;
			}
			
		}
		return targetCadre;
	}
			
	
	public List<CadreAmountDetailsVO> getResultsSimplified(String locationtype,Long accessValue,String accessType,List<CadreAmountDetailsVO> resultList,List<Object[]> voterCountList,List<Long> assemblyIds,List<Long> districtIds,List<Long> constituenycIds,String fromTask,Long stateId){
		Map<Long,Long> distMap = new HashMap<Long, Long>();
		if(locationtype.equalsIgnoreCase(IConstants.DISTRICT)){
			if(accessType.equalsIgnoreCase("MLA") || accessType.equalsIgnoreCase("MP")){
				voterCountList = voterInfoDAO.getVotersCountInConstituenciesByDistrictsListAndConstituencies(districtIds,IConstants.VOTER_DATA_PUBLICATION_ID,assemblyIds);
				List<Object[]> tempList = voterInfoDAO.getVotersCountInConstituenciesByDistrictsList(districtIds,IConstants.VOTER_DATA_PUBLICATION_ID);
				
				if(tempList!=null && tempList.size()>0){
					for(Object[] obj:tempList){
						if(assemblyIds.contains(Long.valueOf(obj[0].toString()))){
							Long cnt = distMap.get(Long.valueOf(obj[3].toString()));
							Long target = getTargetOfDistrict(fromTask,Long.valueOf(obj[2].toString()),stateId);
							if(cnt==null){
								distMap.put(Long.valueOf(obj[3].toString()), target);
							}else{
								distMap.put(Long.valueOf(obj[3].toString()), cnt+target);
							}
						}
					}
				}
				
				
			}else{
				voterCountList = voterInfoDAO.getVotersCountInADistrictsList(districtIds,IConstants.VOTER_DATA_PUBLICATION_ID);
			}
		}else if(locationtype.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			voterCountList = voterInfoDAO.getVotersCountInConstituenciesByDistrictsList(districtIds,IConstants.VOTER_DATA_PUBLICATION_ID);
		}
		
		if(voterCountList != null && voterCountList.size() > 0)
			for(Object[] params :voterCountList){
				if(locationtype.equalsIgnoreCase(IConstants.CONSTITUENCY)){
					if(assemblyIds.contains(Long.valueOf(params[0].toString()))){
						CadreAmountDetailsVO basicVo = new CadreAmountDetailsVO();
						basicVo.setId((Long)params[0]);
						basicVo.setName(params[1] != null ? params[1].toString() : "");
						basicVo.setTotalVoters((Long)params[2]);
						basicVo.setTotalRecords(0l);
						basicVo.setDifference(0l);
						basicVo.setPercentage("0.0");
						basicVo.setColorStatus("Worst");
						
						
						if(fromTask.equalsIgnoreCase("today")){
							//Long noOfDays = dateService.noOfDayBetweenDates(IConstants.CADRE_2014_START_DATE, IConstants.CADRE_2014_LAST_DATE);
							Long noOfDays = 30l;
							
							if(stateId == 1){
								basicVo.setTargetCadres(((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014)/noOfDays);
							}else{
								basicVo.setTargetCadres(((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_TG) / IConstants.TG_VOTERS_2014)/noOfDays);
							}
						}else if(fromTask.equalsIgnoreCase("no")){
							if(stateId == 1){
								basicVo.setTargetCadres((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014);
							}else{
									basicVo.setTargetCadres((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_TG) / IConstants.TG_VOTERS_2014);
							}
						}else{
							String currentDate = dateService.getCurrentDateInStringFormatYYYYMMDD();
							//Long tillDays = dateService.noOfDayBetweenDates(IConstants.CADRE_2014_START_DATE, currentDate);
							Long tillDays = 30l;
							Long noOfDays = dateService.noOfDayBetweenDates(IConstants.CADRE_2014_START_DATE, IConstants.CADRE_2014_LAST_DATE);
							
							if(stateId == 1){
								basicVo.setTargetCadres((((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014)/noOfDays)*tillDays);
							}else{
								basicVo.setTargetCadres((((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_TG) / IConstants.TG_VOTERS_2014)/noOfDays)*tillDays);
							}
							
						}
						
						if(locationtype.equalsIgnoreCase(IConstants.CONSTITUENCY)){
							constituenycIds.add((Long)params[0]);
						}
						resultList.add(basicVo);
					}
					
				}else{
					
					CadreAmountDetailsVO basicVo = new CadreAmountDetailsVO();
					basicVo.setId((Long)params[0]);
					basicVo.setName(params[1] != null ? params[1].toString() : "");
					basicVo.setTotalVoters((Long)params[2]);
					basicVo.setTotalRecords(0l);
					basicVo.setDifference(0l);
					basicVo.setPercentage("0.0");
					basicVo.setColorStatus("Worst");
					
					if(accessType.equalsIgnoreCase("STATE") || accessType.equalsIgnoreCase("DISTRICT")){
						if(fromTask.equalsIgnoreCase("today")){
							Long noOfDays = dateService.noOfDayBetweenDates(IConstants.CADRE_2014_START_DATE, IConstants.CADRE_2014_LAST_DATE);
							
							if(stateId == 1){
								basicVo.setTargetCadres(((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014)/noOfDays);
							}else{
								basicVo.setTargetCadres(((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_TG) / IConstants.TG_VOTERS_2014)/noOfDays);
							}
						}else if(fromTask.equalsIgnoreCase("no")){
							if(stateId == 1){
								basicVo.setTargetCadres((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014);
							}else{
									basicVo.setTargetCadres((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_TG) / IConstants.TG_VOTERS_2014);
							}
						}else{
							String currentDate = dateService.getCurrentDateInStringFormatYYYYMMDD();
							Long tillDays = dateService.noOfDayBetweenDates(IConstants.CADRE_2014_START_DATE, currentDate);
							Long noOfDays = dateService.noOfDayBetweenDates(IConstants.CADRE_2014_START_DATE, IConstants.CADRE_2014_LAST_DATE);
							
							if(stateId == 1){
								basicVo.setTargetCadres((((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014)/noOfDays)*tillDays);
							}else{
								basicVo.setTargetCadres((((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_TG) / IConstants.TG_VOTERS_2014)/noOfDays)*tillDays);
							}
						}
					}else{
						basicVo.setTargetCadres(distMap.get(Long.valueOf(params[0].toString())));
					}
					
					resultList.add(basicVo);
				}
			}
		
		return resultList;
	}
		
	public List<CadreAmountDetailsVO> getBoothWiseDetails(Long constituencyId){
	  List<CadreAmountDetailsVO> resultList = new ArrayList<CadreAmountDetailsVO>();
	  List<Object[]> voterCountList = null;
	
	try{
		//0 id,1name,2total,3districtId,4tehsilName,5 localBody,6localBody type
			voterCountList = boothDAO.getTotalVotersInBooths(constituencyId,IConstants.VOTER_DATA_PUBLICATION_ID);
		if(voterCountList != null && voterCountList.size() > 0)
			for(Object[] params :voterCountList)
			{
				CadreAmountDetailsVO basicVo = new CadreAmountDetailsVO();
				basicVo.setId((Long)params[0]);
				basicVo.setName(params[1] != null ? params[1].toString() : "");
				basicVo.setTotalVoters((Long)params[2]);
				basicVo.setTotalRecords(0l);
				basicVo.setDifference(0l);
				basicVo.setPercentage("0.0");
				basicVo.setColorStatus("Worst");
				if(params[5] != null){
				    basicVo.setConstituency(params[5].toString()+" "+params[6].toString());
				}else{
					basicVo.setConstituency(params[4].toString()+" Mandal");
				}
				
				if(((Long)params[3]).longValue() > 10){
				    basicVo.setTargetCadres((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014);
				}else{
					basicVo.setTargetCadres((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_TG) / IConstants.TG_VOTERS_2014);
				}
				
				resultList.add(basicVo);
			}
		List<Object[]> totalRecords = tdpCadreDAO.getTotalRecordsBoothWise(constituencyId,null,null);
		
		if(totalRecords != null && totalRecords.size() > 0)
		{
			for(Object[] params : totalRecords)
			{
				  CadreAmountDetailsVO vo = getMatchedVO(resultList,(Long)params[1]);
				  if(vo != null)
				  {
					  vo.setTotalRecords(params[0] != null ? (Long)params[0] : 0);
				  }
			}
		}
		
	}
	catch (Exception e) {
		LOG.error("Exception rised in  getBoothWiseDetails() ",e);
	}
	return resultList;
 }
	
	public  TabRecordsStatusVO getMISReport(String batchCode,Long Id,String type) {
		TabRecordsStatusVO status = new TabRecordsStatusVO();
		try {
			
			List<Object[]> cadreInfo = null;
			String key = UUID.randomUUID().toString();
			String url = IConstants.STATIC_CONTENT_FOLDER_URL+"VMR/MIS/"+key+".xls";
			String returnUrl = "VMR/MIS/"+key+".xls";
			status.setMobileNo(returnUrl);
			LinkedHashMap<String, List<Object[]>> constituencyMap = new LinkedHashMap<String, List<Object[]>>();
			LinkedHashMap<String,LinkedHashMap<String,Long>> locationDetails = new LinkedHashMap<String,LinkedHashMap<String,Long>>();
			if(type == null)
			 cadreInfo = zebraPrintDetailsDAO.getAllCadreDetailsByBatchCode(batchCode);
			else
			{
				/* based on update date*/
				Date updatedDate = null;
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				updatedDate = formatter.parse(batchCode);
				
				if(type.equalsIgnoreCase("Parliament"))
				{
				List<Long> assemblyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliament(Id);
				if(assemblyIds != null && assemblyIds.size() > 0)
				cadreInfo = zebraPrintDetailsDAO.getAllCadreDetailsByParliament(updatedDate,type,assemblyIds);	
				}
				else
				 cadreInfo = zebraPrintDetailsDAO.getAllCadreDetailsByBatchCodeandLocation(updatedDate,type,Id);
			}
			if(cadreInfo.size() == 0){
				status.setName("noData");
				return status;
			}
			
			

			for(Object[] cadre:cadreInfo){
				 List<Object[]> cadres = constituencyMap.get(cadre[4].toString().trim());
				 if(cadres == null){
					 cadres = new ArrayList<Object[]>();
					 constituencyMap.put(cadre[4].toString().trim(),cadres);
				 }
				 cadres.add(cadre);
				 LinkedHashMap<String,Long> subLocation = locationDetails.get(cadre[4].toString().trim());
				 if(subLocation == null){
					 subLocation = new LinkedHashMap<String,Long>();
					 locationDetails.put(cadre[4].toString().trim(),subLocation);
				 }
				 if(cadre[6] != null && cadre[6].toString().trim().length() > 0){
					Long count = subLocation.get(cadre[6].toString().trim()+" "+StringEscapeUtils.unescapeJava("\u0C2E\u0C41\u0C28\u0C4D\u0C38\u0C3F\u0C2A\u0C3E\u0C32\u0C3F\u0C1F\u0C40"));
					if(count == null){
						subLocation.put(cadre[6].toString().trim()+" "+StringEscapeUtils.unescapeJava("\u0C2E\u0C41\u0C28\u0C4D\u0C38\u0C3F\u0C2A\u0C3E\u0C32\u0C3F\u0C1F\u0C40"),1l);
					}else{
						subLocation.put(cadre[6].toString().trim()+" "+StringEscapeUtils.unescapeJava("\u0C2E\u0C41\u0C28\u0C4D\u0C38\u0C3F\u0C2A\u0C3E\u0C32\u0C3F\u0C1F\u0C40"),subLocation.get(cadre[6].toString().trim()+" "+StringEscapeUtils.unescapeJava("\u0C2E\u0C41\u0C28\u0C4D\u0C38\u0C3F\u0C2A\u0C3E\u0C32\u0C3F\u0C1F\u0C40"))+1l);
					}
				 }else{
					 if(cadre[5] != null && cadre[5].toString().trim().length() > 0){
							Long count = subLocation.get(cadre[5].toString().trim());
							if(count == null){
								subLocation.put(cadre[5].toString().trim(),1l);
							}else{
								subLocation.put(cadre[5].toString().trim(),subLocation.get(cadre[5].toString().trim())+1l);
							}
						 }
				 }
			}
			HSSFWorkbook workbook = new HSSFWorkbook();
			CellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.YELLOW.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setAlignment(CellStyle.ALIGN_CENTER);
			Font font = workbook.createFont();
			font.setFontHeightInPoints((short)12);
			style.setFont(font);
			HSSFSheet sheet = workbook.createSheet("Abstract");
			Row row = sheet.createRow(0);
			
			 Cell cell = row.createCell(0);
			 cell.setCellValue("Constituency");
			 cell.setCellStyle(style);
			 
			 cell = row.createCell(1);
			 cell.setCellValue("Mandal/Municipality");
			 cell.setCellStyle(style);
			 
			 cell = row.createCell(2);
			 cell.setCellValue("Count");
			 cell.setCellStyle(style);
			
			int j =1;
			Long total = 0l;
			
			for(String constituency: new ArrayList<String>(locationDetails.keySet())){
				LinkedHashMap<String,Long> subLoc = locationDetails.get(constituency);
				for(String mandal:subLoc.keySet()){
					 row = sheet.createRow(j);
					row.createCell(0).setCellValue(constituency);
					row.createCell(1).setCellValue(mandal);
					row.createCell(2).setCellValue(subLoc.get(mandal));
					total = total+subLoc.get(mandal);
					j++;
				}
				
			}
			if(total > 0){
				 row = sheet.createRow(j);
				row.createCell(1).setCellValue("TOTAL");
				row.createCell(2).setCellValue(total);
			}
			for (String constituency : constituencyMap.keySet()) {
				sheet = workbook.createSheet(constituency);
				 row = sheet.createRow(0);
				
				 cell = row.createCell(0);
				 cell.setCellValue("Member Ship Number");
				 cell.setCellStyle(style);
				 
				 cell = row.createCell(1);
				 cell.setCellValue("Voter Name");
				 cell.setCellStyle(style);
				 
				 cell = row.createCell(2);
				 cell.setCellValue("Mobile Number");
				 cell.setCellStyle(style);
				 
				 cell = row.createCell(3);
				 cell.setCellValue("District Name");
				 cell.setCellStyle(style);
				 
				 cell = row.createCell(4);
				 cell.setCellValue("Constituency Name");
				 cell.setCellStyle(style);
				 
				 cell = row.createCell(5);
				 cell.setCellValue("Mandal Name");
				 cell.setCellStyle(style);
				 
				 cell = row.createCell(6);
				 cell.setCellValue("Municipality Name");
				 cell.setCellStyle(style);
				 
				 cell = row.createCell(7);
				 cell.setCellValue("Panchayat Name");
				 cell.setCellStyle(style);
				 
				 cell = row.createCell(8);
				 cell.setCellValue("Booth");
				 cell.setCellStyle(style);
				 
				 cell = row.createCell(9);
				 cell.setCellValue("House No");
				 cell.setCellStyle(style);
				 
				 cell = row.createCell(10);
				 cell.setCellValue("Location");
				 cell.setCellStyle(style);
				 
				 cell = row.createCell(11);
				 cell.setCellValue("Town");
				 cell.setCellStyle(style);
					
				 cell = row.createCell(12);
				 cell.setCellValue("zebra_print_details_id");
				 cell.setCellStyle(style);
				 
				int rowCount = 0;
				List<Object[]> results = constituencyMap.get(constituency);
				for (Object[] cadre : results) {
					rowCount++;
					row = sheet.createRow(rowCount);
					if (cadre[0] != null) {
						row.createCell(0).setCellValue(cadre[0].toString());
					} else {
						row.createCell(0).setCellValue("");
					}
					if (cadre[1] != null) {
						row.createCell(1).setCellValue(cadre[1].toString());
					} else {
						row.createCell(1).setCellValue("");
					}
					if (cadre[2] != null) {
						row.createCell(2).setCellValue(cadre[2].toString());
					} else {
						row.createCell(2).setCellValue("");
					}
					if (cadre[3] != null) {
						row.createCell(3).setCellValue(cadre[3].toString());
					} else {
						row.createCell(3).setCellValue("");
					}
					if (cadre[4] != null) {
						row.createCell(4).setCellValue(cadre[4].toString());
					} else {
						row.createCell(4).setCellValue("");
					}
					if (cadre[5] != null) {
						row.createCell(5).setCellValue(cadre[5].toString());
					} else {
						row.createCell(5).setCellValue("");
					}
					if (cadre[6] != null) {
						row.createCell(6).setCellValue(cadre[6].toString());
					} else {
						row.createCell(6).setCellValue("");
					}
					if (cadre[7] != null) {
						row.createCell(7).setCellValue(cadre[7].toString());
					} else {
						row.createCell(7).setCellValue("");
					}
					if (cadre[8] != null) {
						row.createCell(8).setCellValue(cadre[8].toString());
					} else {
						row.createCell(8).setCellValue("");
					}
					if (cadre[9] != null) {
						row.createCell(9).setCellValue(cadre[9].toString());
					} else {
						row.createCell(9).setCellValue("");
					}
					if (cadre[11] != null) {
						row.createCell(10).setCellValue(cadre[11].toString());
					} else {
						row.createCell(10).setCellValue("");
					}
					if (cadre[12] != null) {
						row.createCell(11).setCellValue(cadre[12].toString());
					} else {
						row.createCell(11).setCellValue("");
					}
					if (cadre[10] != null) {
						row.createCell(12).setCellValue(cadre[10].toString());
					} else {
						row.createCell(12).setCellValue("");
					}
				}
			}
			FileOutputStream out = new FileOutputStream(new File(url));
			workbook.write(out);
			out.close();
			status.setName("success");
		} catch (Exception e) {
			e.printStackTrace();
			status.setName("error");
		}
		return status;
	}
	
	
}
