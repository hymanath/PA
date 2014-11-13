package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.aspectj.apache.bcel.generic.ICONST;

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
import com.itgrids.partyanalyst.dao.hibernate.VoterInfoDAO;
import com.itgrids.partyanalyst.dto.AppDbDataVO;
import com.itgrids.partyanalyst.dto.CadreAmountDetailsVO;
import com.itgrids.partyanalyst.dto.CadreBasicInformationVO;
import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SurveyTransactionVO;
import com.itgrids.partyanalyst.dto.WSResultVO;
import com.itgrids.partyanalyst.model.CadreSurveyUser;
import com.itgrids.partyanalyst.model.CadreSurveyUserAssignee;
import com.itgrids.partyanalyst.model.DelimitationConstituency;
import com.itgrids.partyanalyst.service.ICadreDashBoardService;
import com.itgrids.partyanalyst.service.ILeaderCadreDashBoardService;
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
	public List<CadreAmountDetailsVO> getLoationWiseLeaderCadreDetails(String locationtype,Long stateId)
	{
		List<CadreAmountDetailsVO> resultList = new ArrayList<CadreAmountDetailsVO>(); 
		List<Long> constituenycIds = new ArrayList<Long>();
		List<Long> districtIds = new ArrayList<Long>();
		List<Object[]> voterCountList = null;
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
					if(stateId == 1)
					basicVo.setTargetCadres((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014);
					else
						basicVo.setTargetCadres((basicVo.getTotalVoters() * IConstants.TARGET_CADRE_TG) / IConstants.TG_VOTERS_2014);
					if(locationtype.equalsIgnoreCase(IConstants.CONSTITUENCY))
						constituenycIds.add((Long)params[0]);
					resultList.add(basicVo);
				}
			
			List<Object[]> receivedAmountDetails = cadreSurveyUserAssignDetailsDAO.getTDPCadreAmountDetails(districtIds,locationtype);
			if(receivedAmountDetails != null && receivedAmountDetails.size() > 0)
			{
				for(Object[] params : receivedAmountDetails)
				{
					  CadreAmountDetailsVO vo = getMatchedVO(resultList,(Long)params[1]);
					  if(vo != null)
					  vo.setPaidAmount((Long)params[0]);
					 
				}
			}
			List<Object[]> totalRecords = tdpCadreDAO.getTotalRecords(districtIds,locationtype);
			
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
		catch (Exception e) {
			LOG.info("Enterd into getLoationWiseLeaderCadreDetails() in LeaderCaderDashBoardService");
		}
		return resultList;
	}
	
	public CadreAmountDetailsVO getMatchedVO(List<CadreAmountDetailsVO> resultList,Long Id)
	{
		try{
			if(resultList == null || resultList.size() == 0)
				return null;
			for(CadreAmountDetailsVO vo :resultList)
			{
				if(vo.getId().longValue() == Id)
					return vo;
			}
		}
		catch (Exception e) {
			return null;
		}
		return null;
	}
}
