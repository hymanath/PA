package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAppDbUpdateDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserAssignDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserAssigneeDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dto.CadreAmountDetailsVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
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
								  percentage = (new BigDecimal(vo.getTotalRecords()*(100.0)/vo.getTargetCadres().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
								  vo.setPercentage(percentage);
							  }
						}
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
								locationtype = IConstants.LOCAL_ELECTION_BODY;
								voterCountList = voterInfoDAO.getVotersCountInALocalBodyList(localbodyIds,IConstants.VOTER_DATA_PUBLICATION_ID);
							    setLocationWiseCadreData(locationtype,localbodyIds,voterCountList,constituencyIds,resultList,fromDate,toDate);
							}
				}
				
				
			
			
		}
		catch (Exception e) {
			LOG.info("Enterd into getLoationWiseLeaderCadreDetails() in LeaderCaderDashBoardService");
		}
		return resultList;
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
				if(locationtype.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION))
				basicVo.setName(params[1] != null ? params[1].toString()+ "Muncipality" : "");
				else
				basicVo.setName(params[1] != null ? params[1].toString() : "");	
				basicVo.setTotalVoters((Long)params[2]);
				if(locationtype.equalsIgnoreCase(IConstants.CONSTITUENCY))
				 state = getStateByDistrictId(Ids.get(0));//DistrictId
				else if(locationtype.equalsIgnoreCase(IConstants.TEHSIL) || locationtype.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION))
					 state = getStateByDistrictId((Long)params[2]);//DistrictId
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
				  CadreAmountDetailsVO vo = getMatchedVO(resultList,(Long)params[1]);
				  if(vo != null)
				  {
					  vo.setTotalRecords(params[0] != null ? (Long)params[0] : 0);
					  vo.setTotalAmount(vo.getTotalRecords() * 100);
					  if(locationtype.equalsIgnoreCase(IConstants.CONSTITUENCY))
					  vo.setDifference(vo.getTotalAmount() - vo.getPaidAmount());
					  String percentage ="";
					  percentage = (new BigDecimal(vo.getTotalRecords()*(100.0)/vo.getTargetCadres().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					  vo.setPercentage(percentage);
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
				if(vo.getId().longValue() == id)
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
				if(vo.getId().longValue() == Id  && vo.getUserType().equalsIgnoreCase(type))
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
	
	public List<CadreAmountDetailsVO> getLocationWiseAsOfNowDetails(String locationtype,Long stateId){
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
			List<Object[]> totalRecords = tdpCadreDAO.getTotalRecords(districtIds,locationtype,null,null);
			
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
			
			worst_status_count = totalRecords.size()-best_status_count-good_status_count-ok_status_count-poor_status_count;
			
			if(resultList!=null && resultList.size()>1){
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
	
	public List<CadreAmountDetailsVO> getLocationWiseToDayDetails(String locationtype,Long stateId,String fromTask){
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
					basicVo.setTotalRecords(0l);
					basicVo.setDifference(0l);
					basicVo.setPercentage("0.0");
					basicVo.setColorStatus("Worst");
					
					
					if(fromTask.equalsIgnoreCase("today")){
						Long noOfDays = dateService.noOfDayBetweenDates(IConstants.CADRE_2014_START_DATE, IConstants.CADRE_2014_LAST_DATE);
						
						if(stateId == 1){
							basicVo.setTargetCadres(((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014)/noOfDays);
						}else{
							basicVo.setTargetCadres(((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_TG) / IConstants.TG_VOTERS_2014)/noOfDays);
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
					
					if(locationtype.equalsIgnoreCase(IConstants.CONSTITUENCY)){
						constituenycIds.add((Long)params[0]);
					}
					resultList.add(basicVo);
				}
			
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
				totalRecords = tdpCadreDAO.getTotalRecords(districtIds,locationtype,formatedDate);
			}else{
				totalRecords = tdpCadreDAO.getTotalRecordsUnderDate(districtIds,locationtype,formatedDate);
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
			
			if(resultList!=null && resultList.size()>1){
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
	
	public CadreAmountDetailsVO getUsersInLocation(Date reqFromDate,Date reqToDate,Long userId,Long locationId,String type,Long constituencyId) {
	
		CadreAmountDetailsVO returnVo = new CadreAmountDetailsVO();
		List<CadreAmountDetailsVO> resultList = new ArrayList<CadreAmountDetailsVO>();
		try{
			List<Object[]> list = tdpCadreDAO.getDuplicateUsersByUserId(reqFromDate,reqToDate,userId,locationId,type,constituencyId);
			if(list != null&& list.size() > 0)
			{
				for(Object[] params : list)
				{
					CadreAmountDetailsVO vo = new CadreAmountDetailsVO();	
					
					vo.setName(params[0] != null ? params[0].toString() : "");
					vo.setMobileNo(params[1] != null ? params[1].toString() : "");
					vo.setDate(params[2] != null ? params[2].toString() : "");
					vo.setToDate(params[3] != null ? params[3].toString() : "");
					resultList.add(vo);
					
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
					}else if(locationtype.equalsIgnoreCase(IConstants.CONSTITUENCY)){
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
					if(params[2] != null && ((Long)params[2]).longValue() >0 && params[4] != null){
						basicVo.setPercentage(new BigDecimal((((Long)params[4])*(100.0))/(Long)params[2]).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//femalePercent
					}
				}else{
					if(params[2] != null && ((Long)params[2]).longValue() >0 && params[5] != null){
						basicVo.setPercentage(new BigDecimal((((Long)params[5])*(100.0))/(Long)params[2]).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//femalePercent
					}
				}
				locationsMap.put((Long)params[0],basicVo);
			}
		
		//0 count 1Id
		//getting total cadres register in location
		List<Object[]> totalRecords = tdpCadreDAO.getTotalRecords(ids,locationtype,fromDate,toDate);
		for(Object[] total:totalRecords){
			CadreAmountDetailsVO location = locationsMap.get((Long)total[1]);
			if(location != null){
				location.setTotalCount((Long)total[0]);//totalCadres
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
	
}
