package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IBoothResultDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.dao.ICriticalPanchayatsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPRPWeightegesDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;
import com.itgrids.partyanalyst.dao.IPanchayatResultDAO;
import com.itgrids.partyanalyst.dao.IPartialBoothPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPartyTrendsDAO;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.dao.ISuggestiveRangeDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserConstituencyAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterCastBasicInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterReportLevelDAO;
import com.itgrids.partyanalyst.dto.AlliancePartyResultsVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.DelimitationEffectVO;
import com.itgrids.partyanalyst.dto.ExceptCastsVO;
import com.itgrids.partyanalyst.dto.OptionVO;
import com.itgrids.partyanalyst.dto.PanchayatVO;
import com.itgrids.partyanalyst.dto.PartyEffectVO;
import com.itgrids.partyanalyst.dto.PartyImpactVO;
import com.itgrids.partyanalyst.dto.PartyPositionVO;
import com.itgrids.partyanalyst.dto.PartyTrendsVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SuggestedLocationsVO;
import com.itgrids.partyanalyst.dto.SuggestiveModelVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterCountVO;
import com.itgrids.partyanalyst.dto.VoterDataVO;
import com.itgrids.partyanalyst.model.VoterFamilyCount;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.dto.YouthLeaderSelectionVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.SuggestiveRange;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.model.VoterCastInfo;
import com.itgrids.partyanalyst.model.VoterInfo;
import com.itgrids.partyanalyst.service.IPdfReportsService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.ISuggestiveModelService;
import com.itgrids.partyanalyst.service.IVoterModifiationPdfsGenerations;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;

public class SuggestiveModelService implements ISuggestiveModelService {
	
	private static final Logger LOG = Logger.getLogger(SuggestiveModelService.class);
	private IHamletBoothElectionDAO hamletBoothElectionDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IElectionDAO electionDAO;
	private IConstituencyDAO constituencyDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO ;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IBoothDAO boothDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private INominationDAO nominationDAO;
	private IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO;
	private ISuggestiveRangeDAO suggestiveRangeDAO;
	private IPanchayatDAO panchayatDAO;
	private IVoterInfoDAO voterInfoDAO;
	private IVoterCastBasicInfoDAO voterCastBasicInfoDAO;
	private IVoterCastInfoDAO voterCastInfoDAO;
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IPublicationDateDAO publicationDateDAO;
	private ITehsilDAO tehsilDAO;
	private IVoterModificationInfoDAO voterModificationInfoDAO;
	private IRegionServiceData regionServiceDataImp;
	private IPanchayatHamletDAO panchayatHamletDAO ;
	private IVotersAnalysisService votersAnalysisService;
	private ICasteStateDAO casteStateDAO;
	private IConstituencyElectionResultDAO constituencyElectionResultDAO;
	private IPartyDAO partyDAO;
	private IVoterModificationDAO voterModificationDAO;
	private IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO;
	private ILocalElectionBodyWardDAO localElectionBodyWardDAO;
	private IVoterReportLevelDAO voterReportLevelDAO;
	private IBoothResultDAO boothResultDAO;
	private ICandidateResultDAO candidateResultDAO;
	private IPartialBoothPanchayatDAO partialBoothPanchayatDAO;
	private IPRPWeightegesDAO prpWeightegesDAO;
	private IStaticDataService staticDataService;	
	private IVoterModifiationPdfsGenerations voterModifiationPdfsGenerations;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private AgeWiseExcelsGenerationService ageWiseExcelsGenerationService;
	private IAllianceGroupDAO allianceGroupDAO;
	private IPanchayatResultDAO panchayatResultDAO;
	private ICriticalPanchayatsDAO criticalPanchayatsDAO;
	private IPdfReportsService pdfReportService;
	private IPartyTrendsDAO partyTrendsDAO;
	
	

	public IPartyTrendsDAO getPartyTrendsDAO() {
		return partyTrendsDAO;
	}

	public void setPartyTrendsDAO(IPartyTrendsDAO partyTrendsDAO) {
		this.partyTrendsDAO = partyTrendsDAO;
	}

	public void setPdfReportService(IPdfReportsService pdfReportService) {
		this.pdfReportService = pdfReportService;
	}

	public ICriticalPanchayatsDAO getCriticalPanchayatsDAO() {
		return criticalPanchayatsDAO;
	}

	public void setCriticalPanchayatsDAO(
			ICriticalPanchayatsDAO criticalPanchayatsDAO) {
		this.criticalPanchayatsDAO = criticalPanchayatsDAO;
	}

	public IPanchayatResultDAO getPanchayatResultDAO() {
		return panchayatResultDAO;
	}

	public void setPanchayatResultDAO(IPanchayatResultDAO panchayatResultDAO) {
		this.panchayatResultDAO = panchayatResultDAO;
	}

	public IAllianceGroupDAO getAllianceGroupDAO() {
		return allianceGroupDAO;
	}

	public void setAllianceGroupDAO(IAllianceGroupDAO allianceGroupDAO) {
		this.allianceGroupDAO = allianceGroupDAO;
	}

	public void setAgeWiseExcelsGenerationService(
			AgeWiseExcelsGenerationService ageWiseExcelsGenerationService) {
		this.ageWiseExcelsGenerationService = ageWiseExcelsGenerationService;
	}

	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}

	public IVoterModifiationPdfsGenerations getVoterModifiationPdfsGenerations() {
		return voterModifiationPdfsGenerations;
	}

	public void setVoterModifiationPdfsGenerations(
			IVoterModifiationPdfsGenerations voterModifiationPdfsGenerations) {
		this.voterModifiationPdfsGenerations = voterModifiationPdfsGenerations;
	}

	public IVoterReportLevelDAO getVoterReportLevelDAO() {
		return voterReportLevelDAO;
	}

	public void setVoterReportLevelDAO(IVoterReportLevelDAO voterReportLevelDAO) {
		this.voterReportLevelDAO = voterReportLevelDAO;
	}

	public IBoothResultDAO getBoothResultDAO() {
		return boothResultDAO;
	}

	public void setBoothResultDAO(IBoothResultDAO boothResultDAO) {
		this.boothResultDAO = boothResultDAO;
	}

	public IAssemblyLocalElectionBodyWardDAO getAssemblyLocalElectionBodyWardDAO() {
		return assemblyLocalElectionBodyWardDAO;
	}

	public void setAssemblyLocalElectionBodyWardDAO(
			IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO) {
		this.assemblyLocalElectionBodyWardDAO = assemblyLocalElectionBodyWardDAO;
	}

	public ILocalElectionBodyWardDAO getLocalElectionBodyWardDAO() {
		return localElectionBodyWardDAO;
	}

	public void setLocalElectionBodyWardDAO(
			ILocalElectionBodyWardDAO localElectionBodyWardDAO) {
		this.localElectionBodyWardDAO = localElectionBodyWardDAO;
	}

	public IConstituencyElectionResultDAO getConstituencyElectionResultDAO() {
			return constituencyElectionResultDAO;
		}

		public void setConstituencyElectionResultDAO(
				IConstituencyElectionResultDAO constituencyElectionResultDAO) {
			this.constituencyElectionResultDAO = constituencyElectionResultDAO;
		}

		public IPartyDAO getPartyDAO() {
			return partyDAO;
		}

		public void setPartyDAO(IPartyDAO partyDAO) {
			this.partyDAO = partyDAO;
		}

		public IVoterModificationDAO getVoterModificationDAO() {
			return voterModificationDAO;
		}

		public void setVoterModificationDAO(IVoterModificationDAO voterModificationDAO) {
			this.voterModificationDAO = voterModificationDAO;
		}

	public ICasteStateDAO getCasteStateDAO() {
		return casteStateDAO;
	}

	public void setCasteStateDAO(ICasteStateDAO casteStateDAO) {
		this.casteStateDAO = casteStateDAO;
	}

	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}

	public IPanchayatHamletDAO getPanchayatHamletDAO() {
		return panchayatHamletDAO;
	}

	public void setPanchayatHamletDAO(IPanchayatHamletDAO panchayatHamletDAO) {
		this.panchayatHamletDAO = panchayatHamletDAO;
	}

	
	
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	
	public IVoterModificationInfoDAO getVoterModificationInfoDAO() {
		return voterModificationInfoDAO;
	}

	public void setVoterModificationInfoDAO(
			IVoterModificationInfoDAO voterModificationInfoDAO) {
		this.voterModificationInfoDAO = voterModificationInfoDAO;
	}


	public IVoterInfoDAO getVoterInfoDAO() {
		return voterInfoDAO;
	}

	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}
	public IUserConstituencyAccessInfoDAO getUserConstituencyAccessInfoDAO() {
		return userConstituencyAccessInfoDAO;
	}

	public void setUserConstituencyAccessInfoDAO(
			IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO) {
		this.userConstituencyAccessInfoDAO = userConstituencyAccessInfoDAO;
	}

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}
	public IHamletBoothElectionDAO getHamletBoothElectionDAO() {
		return hamletBoothElectionDAO;
	}

	public void setHamletBoothElectionDAO(
			IHamletBoothElectionDAO hamletBoothElectionDAO) {
		this.hamletBoothElectionDAO = hamletBoothElectionDAO;
	}
	
	public ICandidateBoothResultDAO getCandidateBoothResultDAO() {
		return candidateBoothResultDAO;
	}

	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}
	
	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}
	
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	
	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}
	
	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}
	
	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
	
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	
	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}

	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}	

	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}
	
	public ISuggestiveRangeDAO getSuggestiveRangeDAO() {
		return suggestiveRangeDAO;
	}

	public void setSuggestiveRangeDAO(ISuggestiveRangeDAO suggestiveRangeDAO) {
		this.suggestiveRangeDAO = suggestiveRangeDAO;
	}

	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}
	 public IVoterCastBasicInfoDAO getVoterCastBasicInfoDAO() {
			return voterCastBasicInfoDAO;
		}

		public void setVoterCastBasicInfoDAO(
				IVoterCastBasicInfoDAO voterCastBasicInfoDAO) {
			this.voterCastBasicInfoDAO = voterCastBasicInfoDAO;
		}
		
		public IVoterCastInfoDAO getVoterCastInfoDAO() {
			return voterCastInfoDAO;
		}

		public void setVoterCastInfoDAO(IVoterCastInfoDAO voterCastInfoDAO) {
			this.voterCastInfoDAO = voterCastInfoDAO;
		}
		
		public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
			return userVoterDetailsDAO;
		}

		public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
			this.userVoterDetailsDAO = userVoterDetailsDAO;
		}
		
		public IPublicationDateDAO getPublicationDateDAO() {
			return publicationDateDAO;
		}

		public void setPublicationDateDAO(IPublicationDateDAO publicationDateDAO) {
			this.publicationDateDAO = publicationDateDAO;
		}
		
		public IStaticDataService getStaticDataService() {
			return staticDataService;
		}

		public void setStaticDataService(IStaticDataService staticDataService) {
			this.staticDataService = staticDataService;
		}

		public ITehsilDAO getTehsilDAO() {
			return tehsilDAO;
		}

		public void setTehsilDAO(ITehsilDAO tehsilDAO) {
			this.tehsilDAO = tehsilDAO;
		}
		
		public ICandidateResultDAO getCandidateResultDAO() {
			return candidateResultDAO;
		}

		public void setCandidateResultDAO(ICandidateResultDAO candidateResultDAO) {
			this.candidateResultDAO = candidateResultDAO;
		}

		public IPartialBoothPanchayatDAO getPartialBoothPanchayatDAO() {
			return partialBoothPanchayatDAO;
		}

		public void setPartialBoothPanchayatDAO(
				IPartialBoothPanchayatDAO partialBoothPanchayatDAO) {
			this.partialBoothPanchayatDAO = partialBoothPanchayatDAO;
		}

		public IPRPWeightegesDAO getPrpWeightegesDAO() {
			return prpWeightegesDAO;
		}

		public void setPrpWeightegesDAO(IPRPWeightegesDAO prpWeightegesDAO) {
			this.prpWeightegesDAO = prpWeightegesDAO;
		}

		public List<PanchayatVO> getVotersGroupDetails(List<SelectOptionVO> groupVos,Long constituencyId,Long locationId,String type,List<Long> electionIds,Long userId,List<Long> casteIds,List<ExceptCastsVO> exptdCastes,List<ExceptCastsVO> exptdCastesMncpl){
			
			List<PanchayatVO> panchayatVOList=new ArrayList<PanchayatVO>();
			Map<Long,PanchayatVO> areaMap=new HashMap<Long, PanchayatVO>();
			Map<Long,PanchayatVO> muncipalMap=new HashMap<Long, PanchayatVO>();
			
			try {
				List<Object[]> constituencyType=constituencyDAO.getConstituencyType(constituencyId);
				String constAreaType=constituencyType.get(0)[1].toString();
				Long publicationId = voterInfoDAO.getLatestPublicationDate(constituencyId);
				String mncplName="";
				List<Object[]> ttlVtrsInPnchyt=new ArrayList<Object[]>();
				List<Object[]> ttlVtrsInBooths=new ArrayList<Object[]>();
				
				if(constAreaType.equalsIgnoreCase(IConstants.RURAL)){
					ttlVtrsInPnchyt=boothPublicationVoterDAO.getTotalVotersInPanchayatOfConstituency(constituencyId,publicationId,"rural");
				}else if(constAreaType.equalsIgnoreCase(IConstants.RURALURBAN)){
					ttlVtrsInPnchyt=boothPublicationVoterDAO.getTotalVotersInPanchayatOfConstituency(constituencyId,publicationId,"rural");
					ttlVtrsInBooths=boothPublicationVoterDAO.getTotalVotersInBoothOfMuncipalityByConstituencyId(constituencyId,publicationId);
				}else{
					ttlVtrsInPnchyt=boothPublicationVoterDAO.getTotalVotersInPanchayatOfConstituency(constituencyId,publicationId,"urban");
				}
				List<Long> ttlPnchytIds=new ArrayList<Long>();
				List<Long> ttlBoothIds=new ArrayList<Long>();
				for(Object[] obj:ttlVtrsInPnchyt){
					ttlPnchytIds.add(Long.parseLong(obj[0].toString()));
				}
				for(Object[] obj:ttlVtrsInBooths){
					ttlBoothIds.add(Long.parseLong(obj[0].toString()));
					//mncplName=obj[5].toString()+" - "+obj[6].toString();
				}
				
											
								
				for(SelectOptionVO group : groupVos){
					PanchayatVO panchaytVO1=new PanchayatVO();
					
					//String ageRange=group.getId()+"> & <"+group.getPopulateId();

					String ageRange = null;
					if(group.getId()==61 && group.getPopulateId()>=100)
						ageRange=" ABOVE 60";
					else
						ageRange=group.getId()+" TO "+group.getPopulateId();
					
					panchaytVO1.setMuncipalityName(mncplName);									
					
					panchaytVO1.setAgeRange(ageRange);
					
					panchaytVO1.setAreaType(constAreaType);
					
					if(constAreaType.equalsIgnoreCase(IConstants.CONST_TYPE_URBAN)){
						 List<Object[]> ttlVtrsInPnchytByAge=boothPublicationVoterDAO.getTotalVotersInPanchayatOfConstituencyByAge(constituencyId,publicationId, group.getId(), group.getPopulateId(),"urban");
					 	 areaMap=getTheMapForArea(ttlVtrsInPnchyt,ttlVtrsInPnchytByAge);
					 		
						 List<Object[]> ttlRslts=boothPublicationVoterDAO.getAgeAndGenderWiseVotersCountInPanchayatOfConstituency(constituencyId, publicationId, group.getId(), group.getPopulateId(),"urban",userId);
						 areaMap=getResults(ttlRslts,areaMap,casteIds,exptdCastes,ttlPnchytIds,"","");
						 List<PanchayatVO> list=new ArrayList<PanchayatVO>(areaMap.values());
						 panchaytVO1.setPanchayatList(list);
				 	}
				 	else if(constAreaType.equalsIgnoreCase(IConstants.CONST_TYPE_RURAL)|| constAreaType.equalsIgnoreCase(IConstants.RURALURBAN)){
				 	  
				 	   List<Object[]> ttlVtrsInPnchytByAge=boothPublicationVoterDAO.getTotalVotersInPanchayatOfConstituencyByAge(constituencyId,publicationId, group.getId(), group.getPopulateId(),"rural");
				 	   areaMap=getTheMapForArea(ttlVtrsInPnchyt,ttlVtrsInPnchytByAge);
				 		
					   List<Object[]> ttlRslts=boothPublicationVoterDAO.getAgeAndGenderWiseVotersCountInPanchayatOfConstituency(constituencyId, publicationId, group.getId(), group.getPopulateId(),"rural",userId);
					   areaMap=getResults(ttlRslts,areaMap,casteIds,exptdCastes,ttlPnchytIds,"","");
					   List<PanchayatVO> list=new ArrayList<PanchayatVO>(areaMap.values());
					   panchaytVO1.setPanchayatList(list);
					   
					   if(constAreaType.equalsIgnoreCase(IConstants.RURALURBAN)){
						   if(exptdCastesMncpl.size() > 0)
						   {
							  
							    List<Long> expCasteIds =new ArrayList<Long>();
								   
								for(ExceptCastsVO exceptCastsVO :exptdCastesMncpl)
								{
									expCasteIds.add(exceptCastsVO.getCasteId());
								}
								   Long totalMuncipalVoters = boothPublicationVoterDAO.getAgeAndGenderWisesMuncipaltiyVotersCount(constituencyId,publicationId,group.getId(), group.getPopulateId(),userId);
								   String name = boothDAO.getMuncipaltyName(constituencyId,publicationId);
								   List<Object[]> casteCountDetails = boothPublicationVoterDAO.getExpCasteForAgeAndGenderWisesMuncipaltiyVotersCount(constituencyId,publicationId,group.getId(), group.getPopulateId(),userId,expCasteIds);
								   panchaytVO1.setMuncipaltotalVoters(totalMuncipalVoters);
								   panchaytVO1.setMuncipalityName(name +""+ " Muncipality");
								   panchaytVO1.setMuncipalityCasteList(getResultsForMuncipality(exptdCastesMncpl,totalMuncipalVoters,casteCountDetails));
						   	}
						  
							   String arType="urban";
							   List<Object[]> ttlVtrsInBoothByAge=boothPublicationVoterDAO.getTotalVotersInBoothOfMuncipalityOfConstituencyByAge(constituencyId,publicationId, group.getId(), group.getPopulateId());
							   muncipalMap=getTheMapForArea(ttlVtrsInBooths,ttlVtrsInBoothByAge);
							   
							   List<Object[]> ttlRsltsInBooths=boothPublicationVoterDAO.getAgeAndGenderWiseVotersCountInBoothsOfMuncipalityOfConstituency(constituencyId, publicationId, group.getId(), group.getPopulateId(),userId);
							   String area= "";
							   if(ttlVtrsInBoothByAge != null && ttlVtrsInBoothByAge.size() > 0){
							    Object[] obj=ttlVtrsInBoothByAge.get(0);
							    area=obj[5].toString()+" "+obj[6].toString();
							   }
							   muncipalMap=getResults(ttlRsltsInBooths,muncipalMap,casteIds,exptdCastesMncpl,ttlBoothIds,area,arType);
							   List<PanchayatVO> list1=new ArrayList<PanchayatVO>(muncipalMap.values());
							   panchaytVO1.setBoothsList(list1);
						   
						   
					   }
				 		
				 		
				 	}
					panchayatVOList.add(panchaytVO1);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return panchayatVOList;
			
		}
		
		public String getConstituencyType(Long constituencyId){
			String type="";
			List<Object[]> constituencyType=constituencyDAO.getConstituencyType(constituencyId);
			String constAreaType=constituencyType.get(0)[1].toString();
			if(constAreaType.equalsIgnoreCase(IConstants.RURAL)){
				type="rural";
			}else if(constAreaType.equalsIgnoreCase(IConstants.RURALURBAN)){
				type="rural-urban";
			}else{
				type="urban";
			}
			
			return type;
		}
		
		public Map<Long,PanchayatVO> getTheMapForArea(List<Object[]> ttlVotersInArea,List<Object[]> ttlVotersInAreaByAge){
			Map<Long,PanchayatVO> areaMap=new HashMap<Long, PanchayatVO>();;
			PanchayatVO areaVO=null;
			try {
				for(Object[] obj:ttlVotersInArea){
					
					areaVO=new PanchayatVO();
					
					
					areaVO.setPanchayatId(Long.parseLong(obj[0].toString()));
					areaVO.setPanchayatName(obj[1].toString());
					areaVO.setTotalPanchayatVoters(Integer.parseInt(obj[2].toString()));
					
					areaMap.put(Long.parseLong(obj[0].toString()), areaVO);
				}
				areaMap=ageAndGenderWiseVoters(ttlVotersInAreaByAge,areaMap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return areaMap;
		}
		
		public Map<Long,PanchayatVO> ageAndGenderWiseVoters(List<Object[]> list,Map<Long,PanchayatVO> arMap){
			Map<Long,PanchayatVO> areaMap=new HashMap<Long, PanchayatVO>();
			try {
				for(Object[] obj:list){
					
					if(areaMap.get(Long.parseLong(obj[0].toString()))==null){
						PanchayatVO areaVO=new PanchayatVO();
						if(obj[3].toString().equalsIgnoreCase("M")){
							areaVO.setMaleVoters(Integer.parseInt(obj[2].toString()));
							areaVO.setTotalVoters(Integer.parseInt(obj[2].toString()));
						}else{
							areaVO.setFemaleVoters(Integer.parseInt(obj[2].toString()));
							areaVO.setTotalVoters(Integer.parseInt(obj[2].toString()));
						}
						
						areaMap.put(Long.parseLong(obj[0].toString()), areaVO);
					}else{
						
						if(obj[3].toString().equalsIgnoreCase("M")){
							int mVoters=areaMap.get(Long.parseLong(obj[0].toString())).getMaleVoters();
							areaMap.get(Long.parseLong(obj[0].toString())).setMaleVoters(mVoters+Integer.parseInt(obj[2].toString()));
							int totVoters=areaMap.get(Long.parseLong(obj[0].toString())).getTotalVoters();
							areaMap.get(Long.parseLong(obj[0].toString())).setTotalVoters(totVoters+Integer.parseInt(obj[2].toString()));
						}else{
							int fVoters=areaMap.get(Long.parseLong(obj[0].toString())).getMaleVoters();
							areaMap.get(Long.parseLong(obj[0].toString())).setFemaleVoters(fVoters+Integer.parseInt(obj[2].toString()));
							int totVoters=areaMap.get(Long.parseLong(obj[0].toString())).getTotalVoters();
							areaMap.get(Long.parseLong(obj[0].toString())).setTotalVoters(totVoters+Integer.parseInt(obj[2].toString()));
						}
					}
												
				}
				for (Entry<Long, PanchayatVO> entry : areaMap.entrySet())
				{
					DecimalFormat df = new DecimalFormat("#.##");
					PanchayatVO res=entry.getValue();
					
					arMap.get(entry.getKey()).setMaleVoters(res.getMaleVoters());
					arMap.get(entry.getKey()).setFemaleVoters(res.getFemaleVoters());
					arMap.get(entry.getKey()).setTotalVoters(res.getTotalVoters());
					Double percentage= Double.valueOf(df.format(Long.valueOf(arMap.get(entry.getKey()).getTotalVoters())*100/(float)arMap.get(entry.getKey()).getTotalPanchayatVoters()));
					arMap.get(entry.getKey()).setPercentage(percentage.toString());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return arMap;
		}
		
		public List<BasicVO> getResultsForMuncipality(List<ExceptCastsVO> exptdCastes,Long totalMuncipalVoters,List<Object[]> expCasteDetails)
		{
			List<BasicVO> returnList = null;
			BasicVO basicVOForOthers = new BasicVO();
			Map<Long,Double> expCatsePercMap = null;
			Map<Long,String> casteNamesMap = null;
			BasicVO basicVO = null;
			Long selectedCasteVoters = 0l;
			Long count = 0l;
			Map<Long,BasicVO> casteMap = new HashMap<Long, BasicVO>();
			
			if(exptdCastes != null && exptdCastes.size() > 0)
			{
				expCatsePercMap = new HashMap<Long, Double>();
				for (ExceptCastsVO exceptCastsVO : exptdCastes) {
					expCatsePercMap.put(exceptCastsVO.getCasteId(), exceptCastsVO.getCastePerc());
				}
			}
			
			if(expCasteDetails != null && expCasteDetails.size() > 0)
			{
				
				for (Object[] parms : expCasteDetails) {
					basicVO = new BasicVO();
					Long casteId = (Long)parms[1];
					if(casteMap.get(casteId) == null)
					{
						casteMap = new HashMap<Long, BasicVO>();
						casteMap.put(casteId, basicVO);
					}
					basicVO.setId((Long)parms[1]);
					basicVO.setCasteName(parms[0].toString());
					basicVO.setCount((Long)parms[2]);
				}
			}
			if(expCatsePercMap != null && expCatsePercMap.size() > 0)
			{
				returnList = new ArrayList<BasicVO>();
				Set<Long> expCasteIds = expCatsePercMap.keySet();
				List<Object[]> castesNamesList = casteStateDAO.getCasteListByCasteIds(new ArrayList<Long>(expCasteIds));
				if(castesNamesList != null && castesNamesList.size() > 0)
				{
					casteNamesMap = new HashMap<Long, String>();
					for (Object[] parms : castesNamesList) {
						casteNamesMap.put((Long)parms[0], parms[1].toString());
					}
				}
				for (Long expCasteId : expCasteIds) {
					
					if(expCasteId > 0)
					{
						BasicVO casteVO = casteMap.get(expCasteId);
						basicVO = new BasicVO();
						if(casteVO != null)
						{
							
							basicVO.setId(casteVO.getId());
							basicVO.setCasteName(casteVO.getCasteName());
							basicVO.setCount(casteVO.getCount());
							//basicVO.setName(casteVO.getName());
							Double expPerc = expCatsePercMap.get(expCasteId);
							Long expCount = (long) (basicVO.getCount() * expPerc);
							basicVO.setExpCount(expCount);
							basicVO.setPerc(expPerc);
							selectedCasteVoters = selectedCasteVoters + basicVO.getCount();
							returnList.add(basicVO);
						}
						else
						{
							basicVO.setId(expCasteId);
							basicVO.setCasteName(casteNamesMap.get(expCasteId));
							basicVO.setCount(0l);
							//basicVO.setName(casteVO.getName());
							Double expPerc = expCatsePercMap.get(expCasteId);
							//Long expCount = (long) (basicVO.getCount() * expPerc);
							basicVO.setExpCount(0l);
							basicVO.setPerc(expPerc);
							//selectedCasteVoters = selectedCasteVoters + basicVO.getCount();
							returnList.add(basicVO);
						}
						
					}
				}
				count = totalMuncipalVoters - selectedCasteVoters;
				basicVOForOthers.setId(0l);
				basicVOForOthers.setCasteName("Others");
				basicVOForOthers.setCount(count);
				//basicVOForOthers.setName(casteVO.getName());
				Double expPerc = expCatsePercMap.get(0l);
				Long expCount = (long) (count * expPerc);
				basicVOForOthers.setExpCount(expCount);
				basicVOForOthers.setPerc(expPerc);
				returnList.add(basicVOForOthers);
			}
			return returnList;
		}
		public Map<Long,PanchayatVO> getResults(List<Object[]> ttlRsltsList,Map<Long,PanchayatVO> areaMap,List<Long> casteIds,List<ExceptCastsVO> exptdCastes,List<Long> ttlPanchayatIds,String area,String areaType){
			Map<Long,List<CastVO>> castsMapOfPanchayat=new HashMap<Long, List<CastVO>>();
			
			Map<Long,String> castByIdMap=new HashMap<Long, String>();
			List<Long> panchayatIdList=new ArrayList<Long>();
			List<CastVO> cstVOlist=null;
			List<Object[]> allCsts=new ArrayList<Object[]>();
			Long mncplId=0l;
			try{
			if(casteIds.size()>0){
				allCsts=casteStateDAO.getCasteNamesByCasteIds(casteIds);
			}
			for(Object[] allcsts:allCsts){
				 String caste=allcsts[1].toString();
				 Long casteStateId=Long.valueOf(allcsts[0].toString());
				 if(castByIdMap.get(casteStateId)==null){
					 castByIdMap.put(casteStateId, caste);
				 }
			}
			
			
			
			for(Object[] obj:ttlRsltsList){
				
				Long pnchtId=Long.parseLong(obj[0].toString());
				String pnchtName=obj[1].toString();
				String cstName=obj[2].toString();
				Long casteStateId=Long.parseLong(obj[3].toString());
				Long cstCount=Long.parseLong(obj[4].toString());
				
				//String casteName=obj[3].toString();
				CastVO cstVO=new CastVO();
				if(areaType.equalsIgnoreCase("urban")){
					mncplId=Long.parseLong(obj[5].toString());
				}
				
				if(panchayatIdList.contains(pnchtId)){
						
						cstVO.setCastName(cstName);
						cstVO.setCastStateId(casteStateId);
						cstVO.setCastCount(cstCount);
						
						if(areaType.equalsIgnoreCase("urban")){
							cstVO.setExpctdPercentage(String.valueOf(getExpctdPercentage(exptdCastes,mncplId,casteStateId)));
						}else{
							cstVO.setExpctdPercentage(String.valueOf(getExpctdPercentage(exptdCastes,pnchtId,casteStateId)));
						}
						castsMapOfPanchayat.get(pnchtId).add(cstVO);
				}
				else{
					panchayatIdList.add(pnchtId);
					cstVOlist=new ArrayList<CastVO>();
					cstVO.setCastName(cstName);
					cstVO.setCastStateId(casteStateId);
					cstVO.setCastCount(cstCount);
					if(areaType.equalsIgnoreCase("urban")){
						cstVO.setExpctdPercentage(String.valueOf(getExpctdPercentage(exptdCastes,mncplId,casteStateId)));
					}else{
						cstVO.setExpctdPercentage(String.valueOf(getExpctdPercentage(exptdCastes,pnchtId,casteStateId)));
					}
					
					cstVOlist.add(cstVO);
					castsMapOfPanchayat.put(pnchtId, cstVOlist);
				}
				
			}
			List<Long> pnchytsWthoutRslts=new ArrayList<Long>();
			for(Long id:ttlPanchayatIds){
				if(!panchayatIdList.contains(id)){
					pnchytsWthoutRslts.add(id);
				}
			}
			if(pnchytsWthoutRslts.size()>0){
				for(Long id:pnchytsWthoutRslts){
					cstVOlist=new ArrayList<CastVO>();
					castsMapOfPanchayat.put(id, cstVOlist);
				}
			}
			
			
			
			for (Entry<Long, List<CastVO>> entry : castsMapOfPanchayat.entrySet())
			{
				
				List<CastVO> top3CstsList=new ArrayList<CastVO>();
				List<CastVO> cstVOList=(List<CastVO>) entry.getValue();
				List<CastVO> slctedCstsList=new ArrayList<CastVO>();
				
				Map<Long,CastVO> allSlctdCsts = new HashMap<Long, CastVO>();
				
				if(cstVOList!=null){
				Collections.sort(cstVOList,sourceSort);
				Collections.reverse(cstVOList);
				
				 //For TOP-3 Castes
				 int length=0;
				 if(cstVOList.size()>=3){
					 length=3;
				 }else{
					 length=cstVOList.size();
				 }
				 
				 for(int i=0;i<length;i++){
					 if(cstVOList.get(i)!=null){
						 top3CstsList.add(cstVOList.get(i));
					 }
				 }
				
				
				
				 //Getting Selected CastesList-Start 
				 for(CastVO cv:cstVOList){
					 if(casteIds.contains(cv.getCastStateId())){
						 slctedCstsList.add(cv);
						 allSlctdCsts.put(cv.getCastStateId(), cv);
					 }
				 }
				 
				}
				 
				 //All Selected Castes Scenario - Start
				 
				 for(Long id:casteIds){
					 if(allSlctdCsts.get(id)==null){
						 CastVO cs=new CastVO();
						 cs.setCastStateId(id);
						 cs.setCastName(castByIdMap.get(id));
						 if(areaType.equalsIgnoreCase("urban")){
							 cs.setExpctdPercentage(String.valueOf(getExpctdPercentage(exptdCastes,mncplId,id)));
						 }else{
							 cs.setExpctdPercentage(String.valueOf(getExpctdPercentage(exptdCastes,entry.getKey(),id)));
						 }
						 cs.setCastCount(0l);
						 
						 allSlctdCsts.put(id, cs);
					 }
				 }
				
				 			 
				areaMap.get(entry.getKey()).setTopCastes(top3CstsList);
				areaMap.get(entry.getKey()).setSelectedCastes(slctedCstsList);
				//areaMap.get(entry.getKey()).setAllSelectedCastes(getAllSelectedCastesList(allSlctdCsts,areaMap.get(entry.getKey()).getTotalVoters()));
				List<CastVO> allSlctdCastes=new ArrayList<CastVO>(getAllSelectedCastesList(allSlctdCsts));
				String db="";
				 
				if(areaType.equalsIgnoreCase("urban")){
					db=String.valueOf(getExpctdPercentage(exptdCastes,mncplId,0l));
				}else{
					db=String.valueOf(getExpctdPercentage(exptdCastes,entry.getKey(),0l));
				}
				Double.parseDouble(db);
				areaMap.get(entry.getKey()).setOthrExpctdPrcntg(db);
				
				Long expVotes=0l;
				Long totalOtherVoters = 0l;
				for(CastVO cst:allSlctdCastes){
					expVotes+=cst.getExpctdVotesCount();
					if(cst.getCastCount() != null)
					totalOtherVoters+=cst.getCastCount();
				}
				int otherVotes=(int) (areaMap.get(entry.getKey()).getTotalVoters()-totalOtherVoters);
				int othrExpctdVotes=(int)(otherVotes*(Double.parseDouble(db)));
				
				areaMap.get(entry.getKey()).setAllSelectedCastes(allSlctdCastes);
				areaMap.get(entry.getKey()).setOthrExpctdVotes(othrExpctdVotes);
				areaMap.get(entry.getKey()).setOtherVotes(otherVotes);
				areaMap.get(entry.getKey()).setMuncipalityName(area);
			}
			}catch (Exception e) {
				System.out.println("Exception Raised in getResults() in SuggestiveModel Service"+e);
			}
			return areaMap;
		}		
		public Double getExpctdPercentage(List<ExceptCastsVO> exptdCastes,Long panchayatId,Long casteStateId){
			Double result=0.00;
			try{
			if(exptdCastes.size()==0){
				return result;
			}else{
				for(ExceptCastsVO vo:exptdCastes){
					if(vo.getPanchayatId().equals(panchayatId)&& vo.getCasteId().equals(casteStateId)){
						result=vo.getCastePerc();
						return result;
					}
				}
			}
			}catch (Exception e) {
				System.out.println("Exception Raised in getExpctdPercentage() in SuggestiveModel Service");
			}
			return result;
			
		}
	/*public List<PanchayatVO> getVotersGroupDetails(List<SelectOptionVO> groupVos,Long constituencyId,Long locationId,String type,List<Long> electionIds,Long userId,List<Long> casteIds,List<ExceptCastsVO> exptdCastes){
		
		 List<PanchayatVO> panchayatVOList=new ArrayList<PanchayatVO>();
		 Map<Long,CastVO> allSlctdCsts = null;
		 List<Object[]> allCsts=casteStateDAO.getCasteNamesByCasteIds(casteIds);
		 
		 
		 
		try
		{

		 Long publicationId = 8l;
		 List<Long> publicationIds = new ArrayList<Long>();
		 List<Long> panchayatsList=new ArrayList<Long>();
		 Map<Long,String> panchayatsMap=new HashMap<Long, String>();
		 List<PanchayatVO> panchayatVOs=null;
		 List<PanchayatVO> boothsVO=null;
		 List<Object[]> panchayats=null;
		 publicationIds.add(publicationId);
		 List<Object[]> boothsList=null;
		 List<Long> boothIdsList=null;
		 Map<Long,String> boothsMap=new HashMap<Long, String>();
		 List<Object[]> muncipalitiesList=null;
		 Map<String,List<PanchayatVO>> MuncipalBoothsMap=null;
		 DecimalFormat df = new DecimalFormat("#.##");
		 
		 List<Object[]> constituencyType=constituencyDAO.getConstituencyType(constituencyId);
		 String constAreaType=constituencyType.get(0)[1].toString();
				 
		 if(constAreaType.equalsIgnoreCase(IConstants.CONST_TYPE_URBAN)){
			 
		 }
		 else if(constAreaType.equalsIgnoreCase(IConstants.CONST_TYPE_RURAL)){
			  panchayats=boothDAO.getPanchayatsNamesListByConstituencyId(constituencyId, publicationId);
		 }
		 else{
			  panchayats=boothDAO.getPanchayatsNamesListByConstituencyId(constituencyId, publicationId);
			 muncipalitiesList=boothDAO.getMuncipalitiesListNamesByConstituencyId(constituencyId, publicationId);
			 
			 for(Object[] param:muncipalitiesList){
				 List<Object[]> booths=boothDAO.getAllBoothsInAMuncipality(Long.valueOf(param[0].toString()), publicationId);
				 boothsList.addAll(booths);
			 }
		 }
		//  panchayats=boothDAO.getPanchayatsNamesListByConstituencyId(constituencyId, publicationId);
		// List<Object[]> panchayats=boothDAO.getPanchayatsListByTehsilId(locationId, publicationId);
		 for(Object[] list:panchayats){
			 panchayatsList.add(Long.valueOf(list[0].toString()));
			 panchayatsMap.put(Long.valueOf(list[0].toString()), list[1].toString());
			//System.out.println(list[0]+":"+list[1]);
		 }
		
		 
		 
		 	 for(SelectOptionVO group : groupVos){
				 panchayatVOs=new ArrayList<PanchayatVO>();
				 PanchayatVO pnchytVO1 =new PanchayatVO();
				 String ageRange=group.getId()+"> & <"+group.getPopulateId();
				 pnchytVO1.setAgeRange(ageRange);
				 boothsVO=new ArrayList<PanchayatVO>();
				 MuncipalBoothsMap=new HashMap<String, List<PanchayatVO>>();
				 
				 if(muncipalitiesList!=null){
				 for(Object[] param:muncipalitiesList){
					 
					 List<Object[]> booths=boothDAO.getAllBoothsInAMuncipality(Long.valueOf(param[0].toString()), publicationId);
					 boothsList=new ArrayList<Object[]>();
					 boothIdsList=new ArrayList<Long>();
					 boothsList.addAll(booths);
					 if(boothsList!=null){
						 for(Object[] list:boothsList){
							 boothIdsList.add(Long.valueOf(list[0].toString()));
							 boothsMap.put(Long.valueOf(list[0].toString()), "Booth-"+list[1].toString());
						 }
					 }
				 if(boothIdsList!=null){
					 for(Long boothId:boothIdsList){
						 List li=boothPublicationVoterDAO.getVotersCountForBooths(boothId, publicationId);
						 int totalVotersInBooth=Integer.parseInt((li.get(0)).toString());
						 
						 allSlctdCsts = new HashMap<Long, CastVO>();
						 for(Object[] allcsts:allCsts){
							 String caste=allcsts[1].toString();
							 Long casteStateId=Long.valueOf(allcsts[0].toString());
							 CastVO csvo= new CastVO();
							 	 csvo.setCastStateId(casteStateId);
							 	 csvo.setCastName(caste);
							 	 csvo.setCastCount(0l);
							 	 csvo.setExpctdPercentage("0.25");
							 allSlctdCsts.put(casteStateId, csvo);
							 }
						 
						 
						 List<Object[]> castesInBooths=boothPublicationVoterDAO.getVotersCasteDetailsForAgeRangeInBooth(group.getId(), group.getPopulateId(), boothId, userId);
						 List<Object[]> slctdCastesInBooths=new ArrayList<Object[]>();
						 if(casteIds.size()!=0){
							 slctdCastesInBooths=boothPublicationVoterDAO.getVotersCasteDetailsForAgeRangeInBoothForSelectedCastes(group.getId(), group.getPopulateId(), boothId, userId, casteIds);
						 }
						 List<CastVO> top3CstsListInBooth=new ArrayList<CastVO>();
						 top3CstsListInBooth=getTopCastes(castesInBooths,"top3castes");
						 
						 List<CastVO> slctedCstsList=new ArrayList<CastVO>();
						 slctedCstsList=getTopCastes(slctdCastesInBooths,"");
						 
						 List<Object[]> votersCount = boothPublicationVoterDAO.getVotersCountAgeWiseInBooth(group.getId(), group.getPopulateId(), boothId);
						 int maleVotersInBooth=0;
						 int femaleVotersInBooth=0;
						 int totalVoters=0;
						 int totalMales=0;
						 int totalFemales=0;
						 
						 for(Object[] param1:votersCount){
							 if((param1[1].toString()).equalsIgnoreCase("F")){
								 femaleVotersInBooth=Integer.parseInt(param1[0].toString());
								 totalFemales=totalFemales+femaleVotersInBooth;
							 }else{
								 maleVotersInBooth=Integer.parseInt(param1[0].toString());
								 totalMales=totalMales+maleVotersInBooth;
							 }
							 totalVoters=totalFemales+totalMales;
						 }
						 
						 PanchayatVO pnchytVO=new PanchayatVO();
						 pnchytVO.setPanchayatId(boothId);
						 pnchytVO.setAgeRange(ageRange);
						 pnchytVO.setPanchayatName(boothsMap.get(boothId));
						 pnchytVO.setMaleVoters(totalMales);
						 pnchytVO.setFemaleVoters(totalFemales);
						 pnchytVO.setTotalVoters(totalVoters);
						// float percentage=(totalVoters*100)/totalVotersInBooth;
						 
						 if(totalVotersInBooth != 0 )
						 {
						    Double percentage= Double.valueOf(df.format(Long.valueOf(totalVoters)*100/(float)totalVotersInBooth));
						    pnchytVO.setPercentage(percentage.toString());

						 }else
							 pnchytVO.setPercentage("0.00");
						 pnchytVO.setTotalPanchayatVoters(totalVotersInBooth);
						 pnchytVO.setTopCastes(top3CstsListInBooth);
						 pnchytVO.setSelectedCastes(slctedCstsList);
						 for(CastVO csvo:slctedCstsList){
							 if(allSlctdCsts.get(csvo.getCastStateId())!=null){
								 allSlctdCsts.put(csvo.getCastStateId(),csvo);
							 }
						 }
											 
						 pnchytVO.setAllSelectedCastes(getAllSelectedCastesList(allSlctdCsts));
						 
						 boothsVO.add(pnchytVO);
					 }
				 	}
				 MuncipalBoothsMap.put(param[1].toString()+" "+param[2].toString(), boothsVO);
				 }
				 }
				 
				 
				 if(panchayatsList!=null){
					 
					 Map<Long ,List<Long>> panchayatBoothsMap = new HashMap<Long, List<Long>>();
					 
						List<Object[]> boothsDetails = boothDAO.getBoothIdsByPanchayatIds(panchayatsList, constituencyId, publicationIds);
						
						for(Object[] obj:boothsDetails)
						{
							List<Long> boothsForPanchayat = null;
							if(panchayatBoothsMap.get((Long)obj[1]) != null)
								panchayatBoothsMap.get((Long)obj[1]).add((Long)obj[0]);
							else
							{
								boothsForPanchayat = new ArrayList<Long>();
								boothsForPanchayat.add((Long)obj[0]);
								panchayatBoothsMap.put((Long)obj[1], boothsForPanchayat);

							}
						}
				 for(Long panchayathId:panchayatsList){
				 if("panchayat".equalsIgnoreCase(type)){
					 List li=boothPublicationVoterDAO.getVotersCountForPanchayat(panchayathId, publicationId);
					 int totalVotersInPanchayat=Integer.parseInt((li.get(0)).toString());
					 
					 
					 allSlctdCsts = new HashMap<Long, CastVO>();
					 for(Object[] allcsts:allCsts){
						 String caste=allcsts[1].toString();
						 Long casteStateId=Long.valueOf(allcsts[0].toString());
						 CastVO csvo= new CastVO();
						 	 csvo.setCastStateId(casteStateId);
						 	 csvo.setCastName(caste);
						 	 csvo.setCastCount(0l);
						 	 csvo.setExpctdPercentage("0.25");
						 allSlctdCsts.put(casteStateId, csvo);
						 }
					// List<Long> boothIDs = boothDAO.getBoothIdsByLocalValuesList("panchayat", panchayathId, constituencyId, publicationIds);
					 
					 List<Long> boothIDs  = panchayatBoothsMap.get(panchayathId);
					 
					 //Getting Castes and Selecting top 3 Castes of a Panchayat
					 List<Object[]> castes=boothPublicationVoterDAO.getVotersCasteDetailsForAgeRange(group.getId(), group.getPopulateId(), boothIDs, userId);
					 
					 List<Object[]> castesSelected=new ArrayList<Object[]>();
					 if(casteIds.size()!=0){
						castesSelected=boothPublicationVoterDAO.getVotersCasteDetailsForAgeRangeForSelectedCastes(group.getId(), group.getPopulateId(), boothIDs, userId, casteIds);
					 }
					 List<CastVO> top3CstsList=new ArrayList<CastVO>();
					 top3CstsList=getTopCastes(castes,"top3castes");
					 
					 List<CastVO> slctedCstsList=new ArrayList<CastVO>();
					 slctedCstsList=getTopCastes(castesSelected,"");
					 
					 if(boothIDs != null && boothIDs.size() > 0){
						 int maleVotersInBooth=0;
						 int femaleVotersInBooth=0;
						 int totalVoters=0;
						 int totalMales=0;
						 int totalFemales=0;
						 
						 List<Object[]> votersCount = boothPublicationVoterDAO.getVotersCountAgeWiseForPanchayat(group.getId(), group.getPopulateId(), boothIDs);
						 
						 for(Object[] param:votersCount){
							 if((param[1].toString()).equalsIgnoreCase("F")){
								 femaleVotersInBooth=Integer.parseInt(param[0].toString());
								 totalFemales=totalFemales+femaleVotersInBooth;
							 }else{
								 maleVotersInBooth=Integer.parseInt(param[0].toString());
								 totalMales=totalMales+maleVotersInBooth;
							 }
							 totalVoters=totalFemales+totalMales;
						 }
						 PanchayatVO pnchytVO=new PanchayatVO();
						 
						 pnchytVO.setPanchayatId(panchayathId);
						 pnchytVO.setPanchayatName(panchayatsMap.get(panchayathId));
						 pnchytVO.setMaleVoters(totalMales);
						 pnchytVO.setFemaleVoters(totalFemales);
						 pnchytVO.setTotalVoters(totalVoters);
						 //float percentage=(totalVoters*100)/totalVotersInPanchayat;
						 Double percentage= Double.valueOf(df.format(Long.valueOf(totalVoters)*100/(float)totalVotersInPanchayat));
						 pnchytVO.setPercentage(percentage.toString());
						 pnchytVO.setTotalPanchayatVoters(totalVotersInPanchayat);
						 pnchytVO.setTopCastes(top3CstsList);
						 pnchytVO.setSelectedCastes(slctedCstsList);
						 
						 
						 for(CastVO csvo:slctedCstsList){
							 if(allSlctdCsts.get(csvo.getCastStateId())!=null){
								 allSlctdCsts.put(csvo.getCastStateId(),csvo);
							 }
						 }
						 pnchytVO.setAllSelectedCastes(getAllSelectedCastesList(allSlctdCsts));
						 
						 panchayatVOs.add(pnchytVO);
						 //System.out.println(panchayathId+":tot-"+totalVoters+":female-"+totalFemales+":male-"+totalMales);
					 }
				 }
				
			 }
				 }
			 pnchytVO1.setPanchayatList(panchayatVOs);
			 pnchytVO1.setBoothsList(boothsVO);
			 pnchytVO1.setMunicipalitesBoothsMap(MuncipalBoothsMap);
			 panchayatVOList.add(pnchytVO1);
		 }
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		 return panchayatVOList;
	}*/
	
	
	
	public List<CastVO> getAllSelectedCastesList(Map<Long,CastVO> castMap){
		List<CastVO> allSelectedCastesVOs= new ArrayList<CastVO>();
		 Long slctdCstsVtsCount=0l;
		for (Entry<Long, CastVO> entry : castMap.entrySet())
		 {
			//String exptdPrcntg=entry.getValue().getExpctdPercentage();
			Double exptPrcntg=Double.parseDouble(entry.getValue().getExpctdPercentage());
			Long totVotes=entry.getValue().getCastCount();
			int exptdVotes=(int) (exptPrcntg*totVotes);
			entry.getValue().setExpctdVotesCount(exptdVotes);
					
			allSelectedCastesVOs.add(entry.getValue());
		 }
		return allSelectedCastesVOs;
	}
	
	public List<CastVO> getTopCastes(List<Object[]> castes,String type){
		Map<Long,CastVO> castesMap= new HashMap<Long, CastVO>();
		 
		 for(Object[] param:castes){
			 CastVO value=castesMap.get(Long.valueOf(param[2].toString()));
			 if(value!=null){
				 Long count=value.getCastCount();
				 count=count+Long.valueOf(param[0].toString());
				 value.setCastCount(count);
				 
				 castesMap.put(Long.valueOf(param[2].toString()), value);
			 }
			 else{
				 CastVO castVO=new CastVO();
				 castVO.setCastName(param[1].toString());
				 castVO.setCastCount(Long.valueOf(param[0].toString()));
				 castVO.setCastStateId(Long.valueOf(param[2].toString()));
				 castVO.setExpctdPercentage("0.25");
				 castesMap.put(Long.valueOf(param[2].toString()), castVO);
			 }
		 }
		 List<CastVO> cstVOList=new ArrayList<CastVO>(castesMap.values());
		 Collections.sort(cstVOList,sourceSort);
		 Collections.reverse(cstVOList);
		 List<CastVO> top3CstsList=new ArrayList<CastVO>();
		 if(type.equalsIgnoreCase("top3castes")){
		 int length=0;
		 if(cstVOList.size()>=3){
			 length=3;
		 }else{
			 length=cstVOList.size();
		 }
		 
		 for(int i=0;i<length;i++){
			 if(cstVOList.get(i)!=null){
				 top3CstsList.add(cstVOList.get(i));
			 }
		 }
		 }
		 else{
			 top3CstsList=cstVOList;
		 }
		 return top3CstsList;
	} 
	 public static Comparator<CastVO> sourceSort = new Comparator<CastVO>()
				{
					  
				  public int compare(CastVO cstVO1, CastVO cstVO2)
					{
					   return (cstVO1.getCastCount().intValue()) - (cstVO2.getCastCount().intValue());
					}
			  };
			  
			  public  Comparator<PartyTrendsVO> finalLocationsSort = new Comparator<PartyTrendsVO>()
				{
					  
				  public int compare(PartyTrendsVO cstVO1, PartyTrendsVO cstVO2)
					{
					   return (cstVO2.getTotalWt().compareTo(cstVO1.getTotalWt()));
					}
			  };
			  public  Comparator<PartyPositionVO> finalOrderSort = new Comparator<PartyPositionVO>()
						{
							  
						  public int compare(PartyPositionVO cstVO1, PartyPositionVO cstVO2)
							{
							   return (cstVO2.getMinValue().compareTo(cstVO1.getMinValue()));
							}
					  };
					  public  Comparator<PartyPositionVO> finalOrderSort1 = new Comparator<PartyPositionVO>()
								{
									  
								  public int compare(PartyPositionVO cstVO1, PartyPositionVO cstVO2)
									{
									   return (cstVO2.getMaxValue().compareTo(cstVO1.getMaxValue()));
									}
							  };
	
	public OptionVO getPartyPerformantForSelectedConstituency(Long constituencyId,Long electionId,Long partyId)
	{
		 OptionVO optionVO = null;
		 try {
			 LOG.debug("Enterd Into getPartyPerformantForSelectedConstituency() method in SuggestiveModelService Class ");
			 Long electionYear = Long.valueOf(electionDAO.get(electionId).getElectionYear());
			 String constituencyType = constituencyDAO.get(constituencyId).getAreaType();
			 List<SelectOptionVO> mandals = null;
			 List<SelectOptionVO> wards = null;
			// List<SelectOptionVO> booths = null;
			 if(constituencyType.equalsIgnoreCase(IConstants.RURAL) || constituencyType.equalsIgnoreCase(IConstants.RURALURBAN))
			 {
				 List<Object[]> tehsilsList = delimitationConstituencyMandalDAO.getMandalIdsByConstituencyId(constituencyId,electionYear);
				 if(tehsilsList != null && tehsilsList.size() > 0)
				 {
					mandals = new ArrayList<SelectOptionVO>();
					mandals = processSelectOptionVO(tehsilsList);
				 }
			 }
			 else if(constituencyType.equalsIgnoreCase(IConstants.RURALURBAN))
			 {
				 Long localBodyId = assemblyLocalElectionBodyDAO.getLocalBodyIdBasedOnConstituencyId(constituencyId);
				 List<Object[]> tehsilsList =  localElectionBodyDAO.getTehsilsByLocalBody(localBodyId);
				 if(tehsilsList != null && tehsilsList.size() > 0)
				 {
					 mandals = new ArrayList<SelectOptionVO>();
					 mandals = processSelectOptionVO(tehsilsList);
				 }
				 
			 }
			 else if(constituencyType.equalsIgnoreCase(IConstants.URBAN))
			 {
				 Long localBodyId = assemblyLocalElectionBodyDAO.getLocalBodyIdBasedOnConstituencyId(constituencyId);
				 List<Object[]> wardsList = constituencyDAO.getWardsInALocalBody(localBodyId);
				 if(wardsList != null && wardsList.size() > 0)
				 {
					 wards = new ArrayList<SelectOptionVO>();
					 wards =  processSelectOptionVO(wardsList);
				 }
			 }
			 
			 if(constituencyType.equalsIgnoreCase(IConstants.RURAL) || constituencyType.equalsIgnoreCase(IConstants.RURALURBAN))
			 {
				 List<Long> tehsilIds = new ArrayList<Long>();
				 if(mandals != null && mandals.size() > 0)
				 {
					 for (SelectOptionVO selectOptionVO : mandals) {
						 Long tehsilId = selectOptionVO.getId();
						 tehsilIds.add(tehsilId);
					 }
				 }
				 if(tehsilIds != null && tehsilIds.size() > 0)
				 {
					List<Long> boothIds = new ArrayList<Long>();
					for (Long tehsilId : tehsilIds) {
						List<Long> boothsList =  boothDAO.getboothsByTehsilId(tehsilId);
						if(boothsList != null && boothsList.size() > 0)
						{
						  for (Long boothId : boothsList) {
						  boothIds.add(boothId);
						  }
						}
						List<Object[]> partyWiseVotesList =  candidateBoothResultDAO.getVotesEarnedByParyInEachBooth(electionId,boothIds);
						 if(partyWiseVotesList != null && partyWiseVotesList.size() > 0)
						 {
							 optionVO =  processOptionVO(partyWiseVotesList,partyId,tehsilId); 
						 }
					} 
				 }
			 }
			 else if(constituencyType.equalsIgnoreCase(IConstants.URBAN))
			 {
				 List<Long> wardIds = new ArrayList<Long>();
				 for (SelectOptionVO selectOptionVO : wards) {
					Long wardId = selectOptionVO.getId();
					wardIds.add(wardId);
				}
				if(wardIds != null && wardIds.size() > 0)
				{
					
					for (Long wardId : wardIds) {
						List<Long> boothIds = new ArrayList<Long>();
						List<Long> boothsList =  boothDAO.getboothsByWardId(wardId);
						if(boothsList != null && boothsList.size() > 0)
						{
						  for (Long boothId : boothsList) {
						  boothIds.add(boothId);
						  }
						}
						List<Object[]> partyWiseVotesList =  candidateBoothResultDAO.getVotesEarnedByParyInEachBooth(electionId,boothIds);
						 if(partyWiseVotesList != null && partyWiseVotesList.size() > 0)
						 {
							 optionVO =  processOptionVO(partyWiseVotesList,partyId,wardId);
						 }
					}
				  }
				 }
				
			
		} catch (Exception e) {
			LOG.error("Exception raised in getPartyPerformantForSelectedConstituency() method in SuggestiveModelService Class ", e);
		}
		return optionVO;
	}
	
	public OptionVO getPartyPerformantForSelectedWard(Long constituencyId,Long wardId,Long electionId,Long partyId)
	{
		OptionVO optionVO = null;
		try {
			LOG.debug("Enterd Into getPartyPerformantForSelectedWard() method in SuggestiveModelService Class ");
			List<Long> boothIds = new ArrayList<Long>();
			List<Long> boothsList =  boothDAO.getboothsByWardId(wardId);
			if(boothsList != null && boothsList.size() > 0)
			{
			  for (Long boothId : boothsList)
			  {
			     boothIds.add(boothId);
			  }
			}
		   List<Object[]> partyWiseVotesList =  candidateBoothResultDAO.getVotesEarnedByParyInEachBooth(electionId,boothIds);
		   if(partyWiseVotesList != null && partyWiseVotesList.size() > 0)
			{
			 optionVO =  processOptionVO(partyWiseVotesList,partyId,wardId);
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getPartyPerformantForSelectedWard() method in SuggestiveModelService Class ", e);
		}	
		return optionVO;
	}
	public List<SelectOptionVO> processSelectOptionVO(List<Object[]> values)
	{
		List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
		for (Object[] parms : values) {
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId((Long)parms[0]);
			selectOptionVO.setName(parms[1].toString());
			returnList.add(selectOptionVO);
			}
		return returnList;
	}
	
	public OptionVO processOptionVO(List<Object[]> values,Long partyId,Long id)
	{
		OptionVO optionVO = new OptionVO();
		try {
			LOG.debug("Enterd Into processOptionVO() method in SuggestiveModelService Class ");
			Long totalVoters = 0l;
			Long SelectedpartyVoters = 0l;
			Long otherPartyVoters = 0l;
			Long goodBoothCount = 0L;
		    Long veryGoodBoothCount = 0L;
		    Long badBoothCount = 0L;
		    Long veryBadBoothCount =0L;
		    Long averageBoothCount = 0L;
		      
		    List<Long> goodBoothIdsList = new ArrayList<Long>(0);
		  	List<Long> veryGoodBoothIdsList = new ArrayList<Long>(0);
		  	List<Long> badBoothIdsList = new ArrayList<Long>(0);
		    List<Long> veryBadBoothIdsList = new ArrayList<Long>(0);
		  	List<Long> averageBoothIdsList = new ArrayList<Long>(0);
			boolean tempVar = false;
			for (Object[] parms : values) {
				if(partyId.equals((Long)parms[1]))
				{
					SelectedpartyVoters = (Long)parms[0];
				}
				totalVoters = totalVoters + (Long)parms[0];
			}
			for (Object[] parms : values) {
				if(!partyId.equals((Long)parms[1]))
				{
					otherPartyVoters = (Long)parms[0];
					break;
				}
			}
			if(values.get(0)[1].equals(partyId))
			{
				tempVar = true;
			}
			double selectedPartyTotalPercent =  new BigDecimal((SelectedpartyVoters*100/totalVoters)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			double comparePartyTotalPercent =  new BigDecimal((otherPartyVoters*100/totalVoters)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			double difference = 0.00;
			if(tempVar)
			{
				difference = new BigDecimal(selectedPartyTotalPercent - comparePartyTotalPercent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
			else
			{
				 difference = new BigDecimal(comparePartyTotalPercent - selectedPartyTotalPercent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
			if(tempVar)
	    	{
	    	   if(difference <= IConstants.VERY_GOOD)
	    	   {
	    	    veryGoodBoothCount +=1;
	    	    veryGoodBoothIdsList.add(id);
	    	   }
	    	 
	    	   else if(difference <= IConstants.GOOD)
	    	   {
	    		  goodBoothCount +=1;
	    		  goodBoothIdsList.add(id);
	    	   }
	    	   else if(difference <= IConstants.AVERAGE)
	    	   {
		         averageBoothCount += 1;
		         averageBoothIdsList.add(id);
	    	   }
	    	}
	    	else
	    	{
	    	   
	    	   if(difference <= IConstants.BAD)
	    	   {
	    	    badBoothCount += 1;
	    	    badBoothIdsList.add(id);
	    	   }
	    	 
	    	   else
	    	   {
	    	    veryBadBoothCount +=1;
	    	    veryBadBoothIdsList.add(id);
	    	   }
	    	}
		     optionVO.setGoodBoothCount(goodBoothCount);
		   	 optionVO.setVeryGoodBoothCount(veryGoodBoothCount);
		   	 optionVO.setVeryBadBoothCount(veryBadBoothCount);
		   	 optionVO.setBadBoothCount(badBoothCount);
		   	 optionVO.setAverageBoothCount(averageBoothCount); 
		   	 optionVO.setVeryGoodBoothIdsList(veryGoodBoothIdsList);
		   	 optionVO.setVeryBadBoothIdsList(veryBadBoothIdsList);
		   	 optionVO.setGoodBoothIdsList(goodBoothIdsList);
		   	 optionVO.setBadBoothIdsList(badBoothIdsList);
		   	 optionVO.setAverageBoothIdsList(averageBoothIdsList);
		} catch (Exception e) {
			LOG.error("Exception raised in processOptionVO() method in SuggestiveModelService Class ", e);
		}
		 return optionVO;
	}
	public void getPartyPerformanceForlocation(Map<Long,Map<Long,Long>> resultMap,OptionVO optionVO, Long selectedpartyId)
	{
		try{
			
		 for(Long id:resultMap.keySet())
		 {
			boolean tempVar = false;
			Map<Long,Long> partyMap = resultMap.get(id);
			Long totalVotes = 0L;
				 
			for(Long partysId:partyMap.keySet())
			  totalVotes += partyMap.get(partysId); 
				 
			Long selectedPartyTotal = partyMap.get(selectedpartyId);
			Long comparePartyTotal = 0L;
				 
			  for(Long partysId:partyMap.keySet())
			  {
			    if(!partysId.equals(selectedpartyId) && comparePartyTotal < partyMap.get(partysId))
				 comparePartyTotal = partyMap.get(partysId);
				  
			  }
		   if(selectedPartyTotal > comparePartyTotal)
			tempVar = true;
			     
		  double selectedPartyTotalPercent =  new BigDecimal((selectedPartyTotal*100/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	      double comparePartyTotalPercent =  new BigDecimal((comparePartyTotal*100/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	    	 
	    	 double difference = 0.00;
	    	 
	    	 if(tempVar)
	    	  difference = new BigDecimal(selectedPartyTotalPercent - comparePartyTotalPercent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	    	 else
	    	  difference = new BigDecimal(comparePartyTotalPercent - selectedPartyTotalPercent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	    	 
	    	if(tempVar)
	    	{
	    	   if(difference >= IConstants.VERY_GOOD)
	    	   {
	    		optionVO.setVeryGoodBoothCount(optionVO.getVeryGoodBoothCount()+1);
	    	    optionVO.getVeryGoodBoothIdsList().add(id);
	    	   }
	    	 
	    	   else if(difference >= IConstants.GOOD)
	    	   {
	    		  optionVO.setGoodBoothCount(optionVO.getGoodBoothCount()+1);
	    		  optionVO.getGoodBoothIdsList().add(id);
	    	   }
	    	   else if(difference >= IConstants.AVERAGE)
	    	   {
		         optionVO.setAverageBoothCount(optionVO.getAverageBoothCount()+1);
		         optionVO.getAverageBoothIdsList().add(id);
	    	   }
	    	}
	    	else
	    	{
	    	  if(difference <= IConstants.AVERAGE)
		      {
			       optionVO.setAverageBoothCount(optionVO.getAverageBoothCount()+1);
			       optionVO.getAverageBoothIdsList().add(id);
		      }
	    		else if(difference <= IConstants.BAD)
	    	   {
	    	    optionVO.setBadBoothCount(optionVO.getBadBoothCount()+1);
	    	    optionVO.getBadBoothIdsList().add(id);
	    	   }
	    	 
	    	   else
	    	   {
	    	    optionVO.setVeryBadBoothCount(optionVO.getVeryBadBoothCount()+1);
	    	    optionVO.getVeryBadBoothIdsList().add(id);
	    	   }
	    	}
		}
		}catch (Exception e) {
		 e.printStackTrace();
		 LOG.error(" Exception Occured in getPartyPerformanceForSelectedlocation() method, Exception - "+e);
		}
	}
	
	
	
	//All ELection Years
	
	public List<PartyPositionVO> getPartyPerformenceReport(Long constituencyId,Long partyId,Long locationId,String locationType,List<Long> electionIds,String tempVar)
	{
		List<PartyPositionVO> resultList = null;
		try{
		List<Long> constituencyIdsList = new ArrayList<Long>(0);
		constituencyIdsList.add(constituencyId);
		List<Long> assemblyEleIdsList = new ArrayList<Long>(0);
		List<Object[]> electionList = null;
		String tempLocationName = "";
		
		if(electionIds != null && electionIds.size()> 0)
		{
			electionList = new ArrayList<Object[]>();
			for (Long eleId : electionIds) {
				Object[] eleIds = {eleId};
				 electionList.add(eleIds);
			}
		}
		if(locationType != null && locationType.equalsIgnoreCase(IConstants.MANDAL))
		{
		  if(locationId.toString().substring(0,1).equalsIgnoreCase("2"))
		  {
		   locationId = new Long(locationId.toString().substring(1));
		   if(tempVar != null && tempVar.equalsIgnoreCase("all"))
		    electionList = hamletBoothElectionDAO.findAllElectionsHappendInAMandal(locationId,constituencyIdsList); 
		  
		   locationType = IConstants.MANDAL;
		   tempLocationName = IConstants.PANCHAYAT;
		  }
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
		{
		  if(tempVar != null && tempVar.equalsIgnoreCase("all"))
			electionList = hamletBoothElectionDAO.findAllElectionsHappendInAPanchayat(locationId);
		  tempLocationName = IConstants.BOOTH;
		}
		
		if(electionList != null && electionList.size() > 0)
		{
		   for(Object[] params:electionList)
		   {
			   String electionType = electionDAO.get((Long)params[0]).getElectionScope().getElectionType().getElectionType();
			  if(electionType.equalsIgnoreCase("Assembly"))
			  {
				if(!assemblyEleIdsList.contains((Long)params[0]))
				assemblyEleIdsList.add((Long)params[0]);
			  }
		   } 	
		}
		
		
		
		if(assemblyEleIdsList != null && assemblyEleIdsList.size() > 0)
		{
			assemblyEleIdsList = electionDAO.getSortedElectionIds(assemblyEleIdsList);
			
			resultList = new ArrayList<PartyPositionVO>(0);
			  List<SuggestiveRange> suggestiveRangeList = suggestiveRangeDAO.getSuggestiveRangeList();
				  
			  PartyPositionVO partyPositionVO = null;
			  for(Long eleId :assemblyEleIdsList)
			  {
				Election election = electionDAO.get(eleId);
				partyPositionVO = new PartyPositionVO();
				List<PartyPositionVO> rangeList = new ArrayList<PartyPositionVO>();
				
				PartyPositionVO range = null;
				for(SuggestiveRange suggestiveRange:suggestiveRangeList)
				  {
					range = new PartyPositionVO();
					range.setName(suggestiveRange.getName());
					range.setMinValue(suggestiveRange.getMinValue());
					range.setMaxValue(suggestiveRange.getMaxValue());
					rangeList.add(range);
				  }
				partyPositionVO.setPartyPositionVOList(rangeList);
				partyPositionVO.setName(election.getElectionYear() != null?election.getElectionYear():" ");
				partyPositionVO.setId(eleId);
				partyPositionVO.setConstituencyId(constituencyId);
				
				if(locationType != null && locationType.equalsIgnoreCase(IConstants.MANDAL))
				 getMandalWisePartyPerformanceReport(constituencyId, locationId, eleId, partyPositionVO, partyId);
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
				 getPanchayatWisePartyPerformance(constituencyId, locationId, eleId, partyPositionVO, partyId);
				
				resultList.add(partyPositionVO);
			  }
		}
		
		if(resultList != null && resultList.size() == 2){
		  List<PartyPositionVO> suggestedLocations = getSuggestiveLocationsForAParty(resultList);
		  resultList.get(0).setSuggestedLocations(suggestedLocations);
		}	

		//For PollingPercentage Panchayats
		if(resultList != null && resultList.size() > 0){
			getPollingPercentageForALocation(resultList.get(0),tempLocationName,constituencyId);
			List<PartyPositionVO>  panchayatVos = getMoreVotersAddedLocDetailsWherePartyIsPoor(resultList.get(0).getPartyPositionVOList());
			resultList.get(0).setAddedVoterDetails(panchayatVos);
			
		//Percentage
		Map<Long,Map<String,Long>> map = new HashMap<Long, Map<String,Long>>(0);//<electionId,Map<strong,totalValidVotes>>
		for(PartyPositionVO partyPositionVO1:resultList)
		{
		   Map<String,Long> map2 = map.get(partyPositionVO1.getId());
		   if(map2 == null)
		   {
			  map2 = new HashMap<String, Long>(0);
			  map.put(partyPositionVO1.getId(), map2);
		   }
		   if(partyPositionVO1.getPartyPositionVOList() != null && partyPositionVO1.getPartyPositionVOList().size() > 0)
		   {
			 for(PartyPositionVO partyPositionVO2:partyPositionVO1.getPartyPositionVOList())
			 {
				 if(partyPositionVO2.getPartyPositionVOList() != null && partyPositionVO2.getPartyPositionVOList().size() > 0)
				 {
					for(PartyPositionVO partyPositionVO3:partyPositionVO2.getPartyPositionVOList())
					{
					  Long total = map2.get(partyPositionVO2.getName());
					   if(total == null)
						 map2.put(partyPositionVO2.getName(), partyPositionVO3.getTotalValidVotes());
					   else
						map2.put(partyPositionVO2.getName(), total+partyPositionVO3.getTotalValidVotes());
					}
				 }
			 }
		   }
		}
		
		for(PartyPositionVO partyPositionVO1:resultList)
		{
		  Long totalVotes = candidateBoothResultDAO.getConstituencyTotalVotes(constituencyId, partyPositionVO1.getId());
		  
		  Map<String, Long> totalVotesMap = map.get(partyPositionVO1.getId());
		  
		  if(partyPositionVO1.getPartyPositionVOList() != null && partyPositionVO1.getPartyPositionVOList().size() > 0)
		   {
			 for(PartyPositionVO partyPositionVO2:partyPositionVO1.getPartyPositionVOList())
			 {
				 if(partyPositionVO2.getPartyPositionVOList() != null && partyPositionVO2.getPartyPositionVOList().size() > 0)
				 {
					  double percentage =  new BigDecimal((totalVotesMap.get(partyPositionVO2.getName())*100.0/totalVotes)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
					  partyPositionVO2.setRangePercentage(percentage);
				 }
			 }
		   }
		}
		
		
		
		
		}
		 return resultList;
		}catch (Exception e) {
		 e.printStackTrace();
		 LOG.error(" Exception Occured in getPartyPerformenceReportForAllElections() method, Exception-"+e);
		 return resultList;
		}
	}
	
	
  public void getPollingPercentageForALocation(PartyPositionVO partyPositionVO,String locationType,Long constituencyId)
  {
	try{
	List<Long> weakLocationIdsList = new ArrayList<Long>(0);//<electionId,PanchayatIdsList>
	List<Long> strongLocationIdsList = new ArrayList<Long>(0);//<electionId,panchayatIdsList
	List<Long> totalLocationIdsList = new ArrayList<Long>(0); 
	
	   List<PartyPositionVO> partyPositionVOList = partyPositionVO.getPartyPositionVOList();
	   if(partyPositionVOList != null && partyPositionVOList.size() > 0)
	   {
		   for(PartyPositionVO positionVO :partyPositionVOList)
		   {
			  if(positionVO.getMinValue() < 0)
			  {
			    if(positionVO.getPartyPositionVOList() != null && positionVO.getPartyPositionVOList().size() > 0)
			    {
				   for(PartyPositionVO positionVO2 :positionVO.getPartyPositionVOList())
				    if(!weakLocationIdsList.contains(positionVO2.getId()))
				     weakLocationIdsList.add(positionVO2.getId());
			     }
			   }
			   else if(positionVO.getMinValue() > 0)
			   {
			     if(positionVO.getPartyPositionVOList() != null && positionVO.getPartyPositionVOList().size() > 0)
				 {
					for(PartyPositionVO positionVO2 :positionVO.getPartyPositionVOList())
					 if(!strongLocationIdsList.contains(positionVO2.getId()))
					  strongLocationIdsList.add(positionVO2.getId());
				  }
			   }
			  
			  if(positionVO.getPartyPositionVOList() != null && positionVO.getPartyPositionVOList().size() > 0)
			  {
				for(PartyPositionVO positionVO2 :positionVO.getPartyPositionVOList())
				 if(!totalLocationIdsList.contains(positionVO2.getId()))
					 totalLocationIdsList.add(positionVO2.getId());
			  }
			  
			 }
		   }
	   if(strongLocationIdsList != null && strongLocationIdsList.size() > 0)
		 getPollingPercentage(locationType,constituencyId,strongLocationIdsList,partyPositionVO.getId(),partyPositionVO,"strongLocations",totalLocationIdsList);
	   
	   if(weakLocationIdsList != null && weakLocationIdsList.size() > 0)
		 getPollingPercentage(locationType,constituencyId,weakLocationIdsList,partyPositionVO.getId(),partyPositionVO,"weakLocations",totalLocationIdsList);  
	   
	 
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error(" Exception Occured in getPollingPercentageForALocation() method, Exception - "+e);
			
		}
	}
  
  
  
  
  public void getPollingPercentage(String locationType,Long constituencyId,List<Long> locationIdsList,Long electionId,PartyPositionVO partyPositionVO,String tempVar,List<Long> totalLocationIdsList)
  {
	try{
		
		Map<Long,Double> resultMap = new HashMap<Long, Double>(0);//locationId,PollingPercentage
		Map<Long,Double> partyPercentage = new HashMap<Long, Double>(0);//locationId,selectedPartyPercentage
		Map<Long,String> locationNameMap = new HashMap<Long, String>(0);//locationId,locationName
		
		Map<Long,List<Long>> locationIdsMap = new HashMap<Long, List<Long>>(0);//Map<PanchayatId,List<BoothIds>>
		Map<Long,Long> totalVotesMap = new HashMap<Long, Long>(0);//Map<panchayatId,totalvotes
		Map<Long,Long> validvotesMap = new HashMap<Long, Long>(0);//Map<panchayatId,validvotes
		Map<Long,PartyPositionVO> partyPositionMap = new HashMap<Long, PartyPositionVO>(0);
		if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
		{
		  List<Object[]> list = hamletBoothElectionDAO.getPanchayatBoothDetailsByPanchayatIdsList(locationIdsList, electionId);
		  if(list != null && list.size() > 0)
		  {
			for(Object[] params:list)
			{
				 List<Long> boothList = locationIdsMap.get((Long)params[0]);
				 if(boothList == null)
				 {
					 boothList = new ArrayList<Long>(0);
					 locationIdsMap.put((Long)params[0], boothList);
				 }
				  if(!boothList.contains((Long)params[1]))
					  boothList.add((Long)params[1]);
			 }
		  }
		 }
		
		
		if(locationType.equalsIgnoreCase(IConstants.BOOTH) && locationIdsList != null && locationIdsList.size() > 0)
		{
		   for(Long boothId : locationIdsList)
		   {
			   List<Long> boothIds = new ArrayList<Long>(0);
			   boothIds.add(boothId);
			   locationIdsMap.put(boothId, boothIds);
		   }
		}
		
		if(locationIdsMap != null && locationIdsMap.size() > 0)
		{
		  for(Long locationId : locationIdsMap.keySet())
		  {
			 List<Long> boothIdList = locationIdsMap.get(locationId);
			 Long totalVotes = boothDAO.getTotalVotesByBoothIdsList(boothIdList);
			 totalVotesMap.put(locationId, totalVotes);
		  }
		}
		
		if(partyPositionVO.getPartyPositionVOList() != null && partyPositionVO.getPartyPositionVOList().size() > 0)
		{
		  for(PartyPositionVO positionVO:partyPositionVO.getPartyPositionVOList())
		  {
			  if(positionVO.getPartyPositionVOList() != null && positionVO.getPartyPositionVOList().size() > 0)
			  {
			   for(PartyPositionVO locationVO:positionVO.getPartyPositionVOList())
			   {
			     partyPercentage.put(locationVO.getId(), locationVO.getPartyPercentage());
			     locationNameMap.put(locationVO.getId(), locationVO.getName());
			     validvotesMap.put(locationVO.getId(), locationVO.getTotalValidVotes());
			     
			     
			     PartyPositionVO positionVO2 = partyPositionMap.get(locationVO.getId());
			     if(positionVO2 == null)
			     {
			    	 positionVO2 = new PartyPositionVO();
			    	 positionVO2.setId(locationVO.getId());
			    	 positionVO2.setName(locationVO.getName());
			    	 positionVO2.setMinValue(positionVO.getMinValue());
			    	 positionVO2.setMaxValue(positionVO.getMaxValue());
			    	 positionVO2.setPartyPercentage(locationVO.getPartyPercentage());
			    	 positionVO2.setTotalValidVotes(locationVO.getTotalValidVotes());
			    	 positionVO2.setTempVar(positionVO.getName());
			    	 partyPositionMap.put(locationVO.getId(), positionVO2);
			    	 
			     }
			     
			   }
			  }
		  }
		}
		  		  
		if(totalVotesMap != null && totalVotesMap.size() > 0)
		{
		  for(Long id : totalVotesMap.keySet())
		  {
			double difference =  new BigDecimal((validvotesMap.get(id)*100/totalVotesMap.get(id))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			if(tempVar != null && tempVar.equalsIgnoreCase("strongLocations")) 
			{
			 if(difference < IConstants.LOW_VOTING_PERCENTAGE_IN_STRONG_LOCATIONS) 
			   resultMap.put(id, difference);
			}
			else{
			 if(difference > IConstants.HIGH_VOTING_PERCENTAGE_IN_WEEK_LOCATIONS) 
				resultMap.put(id, difference);	
			}
		  }
		}
	if(resultMap != null && resultMap.size() > 0)
		{
			List<PartyPositionVO> pollingPercentageVOList = new ArrayList<PartyPositionVO>(0);
			
			for(Long locationId:resultMap.keySet())
			{
				PartyPositionVO resultVO = partyPositionMap.get(locationId);
				
				PartyPositionVO positionVO = checkPartyPositionVOExist(resultVO.getMinValue(), resultVO.getMaxValue(), pollingPercentageVOList);
				if(positionVO == null)
				{
					positionVO = new PartyPositionVO();
					positionVO.setName(resultVO.getTempVar());
					positionVO.setMinValue(resultVO.getMinValue());
					positionVO.setMaxValue(resultVO.getMaxValue());
					pollingPercentageVOList.add(positionVO);
				}
				List<PartyPositionVO> partyPositionVOList = positionVO.getPartyPositionVOList();
				if(partyPositionVOList == null)
					partyPositionVOList = new ArrayList<PartyPositionVO>(0);
				
				PartyPositionVO locationVO = checkPartyPositionVOExist(locationId, partyPositionVOList);
				if(locationVO == null)
				{
					locationVO = new PartyPositionVO();
					locationVO.setId(locationId);
					locationVO.setName(resultVO.getName());
					locationVO.setPartyPercentage(resultVO.getPartyPercentage());
					locationVO.setPollingPercentage(resultMap.get(locationId));
					partyPositionVOList.add(locationVO);
				}
				
				positionVO.setPartyPositionVOList(partyPositionVOList);
				
			}
			if(tempVar != null && tempVar.equalsIgnoreCase("strongLocations"))
			 partyPositionVO.setStrongPollingPercentVOList(pollingPercentageVOList);
			else
				partyPositionVO.setWeakPollingPercentVOList(pollingPercentageVOList);
			  
		  }
		  
	}catch (Exception e) {
	 e.printStackTrace();
	 LOG.error(" Exception Occured in getPollingPercentage method, Exception - "+e);
	}
  }
  
  
  public PartyPositionVO checkPartyPositionVOExist(Double minValue,Double maxValue,List<PartyPositionVO> list)
  {
	 try{
		if(list == null || list.size() == 0)
		 return null;
		for(PartyPositionVO partyPositionVO : list)
		 if(partyPositionVO.getMinValue().equals(minValue) && partyPositionVO.getMaxValue().equals(maxValue))
		  return partyPositionVO;
		 
	   return null;
	 }catch (Exception e) {
      e.printStackTrace();
      LOG.error(" Exception Occured in checkPartyPositionVOExist() method, Exception - "+e);
      return null;
	 }
  }
	
	public void getPanchayatWisePartyPerformance(Long constituencyId,Long panchayatId, Long electionId,PartyPositionVO partyPositionVO, Long partyId)
	{
		try{
			Map<Long,Map<Long,Long>> resultMap = new HashMap<Long, Map<Long,Long>>(0);//Map<boothId,Map<partyId,votesEarned>>
			List<Long> boothIdsList = hamletBoothElectionDAO.getBoothIdsByPanchayatId(panchayatId, electionId);
				if(boothIdsList != null && boothIdsList.size() > 0)
				{
				  List<Object[]> list = candidateBoothResultDAO.getVotesEarnedByBoothIdsList( electionId, boothIdsList);
				  if(list != null && list.size() > 0)
				  {
					  Map<Long,Long> partyMap = null;
					  for(Object[] params:list)
					  {
						  partyMap = resultMap.get((Long)params[0]);
						  if(partyMap == null)
						  {
							  partyMap = new HashMap<Long, Long>(0);
							  resultMap.put((Long)params[0], partyMap);
						  }
						 Long votesEarned = partyMap.get((Long)params[1]);
						 if(votesEarned == null)
						  partyMap.put((Long)params[1], (Long)params[2]);
						 else
						  partyMap.put((Long)params[1], (Long)params[2]+votesEarned);
					  }
				  }
				}
				
				if(resultMap != null && resultMap.size() > 0)
					getPartyPerformance(resultMap,partyPositionVO, partyId,"booth");	
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("ExceptionOccured in getPanchayatWisePartyPerformance() method, Exception - "+e);
		}
	}
	
	
	public void getMandalWisePartyPerformanceReport(Long constituencyId,Long locationId,Long electionId,PartyPositionVO partyPositionVO,Long partyId)
	{
		try{
		Map<Long,List<Long>> boothIds = new HashMap<Long, List<Long>>();//Map<boothId,List<panchayatId>>
		Map<Long,Map<Long,Long>> resultMap = new HashMap<Long, Map<Long,Long>>(0);//Map<panchayatId,Map<partyId,totalvoters>>
		
		
		List<Long> panchaytIdsList = hamletBoothElectionDAO.getPanchayatIdsByTehsilIdAndElectionId(locationId, electionId);
		
		if(panchaytIdsList != null && panchaytIdsList.size() > 0)
		{
		  List<Object[]> list = hamletBoothElectionDAO.getPanchayatBoothDetailsByPanchayatIdsList(panchaytIdsList, electionId); 
		  if(list != null && list.size() > 0)
		  {
			  for(Object[] params:list)
			  {
				  List<Long> panchayatIds = boothIds.get((Long)params[1]);
				  if(panchayatIds == null)
				  {
				   panchayatIds = new ArrayList<Long>(0);
				   boothIds.put((Long)params[1], panchayatIds);
				  }
				  if(!panchayatIds.contains((Long)params[0]))
				   panchayatIds.add((Long)params[0]);
			  }
			  
			  List<Object[]> resultList = candidateBoothResultDAO.findBoothResultsForMultipleBoothsAndElectionIdForSelElection(boothIds.keySet(), electionId);
			  if(resultList != null && resultList.size() > 0)
			  {
				 for(Object[] params:resultList)
				 {
				   List<Long> panchayatIdsList = boothIds.get((Long)params[0]);
				   if(panchayatIdsList != null && panchayatIdsList.size() > 0)
				   {
					 Map<Long,Long> partyMap = null;//Map<PartyId,totalvotes>
					 for(Long panchayatId :panchayatIdsList)
					 {
						 partyMap = resultMap.get(panchayatId);
						 if(partyMap == null)
						 {
							 partyMap = new HashMap<Long, Long>(0);
							 resultMap.put(panchayatId, partyMap);
						 }
						 Long votesEarned = partyMap.get((Long)params[1]);
						 if(votesEarned == null)
						  partyMap.put((Long)params[1],(Long)params[2]);
						 else
						  partyMap.put((Long)params[1], votesEarned+(Long)params[2]);
					 }
				   }
				   
				 }
			  }
			  
		  }
		}
		if(resultMap != null && resultMap.size() > 0)
			getPartyPerformance(resultMap,partyPositionVO, partyId,"panchayat"); 
		
		
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error(" Exception Occured in getMandalWisePartyPerformanceReport() method, Exception - "+e);
		  }
	}
	
	public void getPartyPerformance(Map<Long,Map<Long,Long>> resultMap,PartyPositionVO partyPositionVO, Long selectedpartyId,String locationType)
	{
		try{
		
			//resultMap -- Map<panchayatId,Map<partyId,totalvoters>>
			
			Map<Long,List<Long>> boothIdsMap = new HashMap<Long, List<Long>>(0);//<panchayatId,List<boothIds>>
			Map<Long,Long> panchayatTotalVotersMap = new HashMap<Long, Long>(0);//<locationId,totalVoters>
			
			if(locationType != null && locationType.equalsIgnoreCase("panchayat"))
			{
				List<Long> panchayatIdsList = new ArrayList<Long>(resultMap.keySet());
			  List<Object[]> boothList = hamletBoothElectionDAO.getPanchayatBoothDetailsByPanchayatIdsList(panchayatIdsList, partyPositionVO.getId());
			  if(boothList != null && boothList.size() > 0)
			  {
				  for(Object[] params:boothList)
				  {
					  List<Long> boothIdsList = boothIdsMap.get((Long)params[0]);
					  if(boothIdsList == null)
					  {
						boothIdsList = new ArrayList<Long>(0);
						boothIdsMap.put((Long)params[0], boothIdsList);  
					  }
					  if(!boothIdsList.contains((Long)params[1]))
						  boothIdsList.add((Long)params[1]);  
				  }
				  
			  }
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
			{
				for(Long id:resultMap.keySet())
				 {
					List<Long> boothIdsList = boothIdsMap.get(id);
					if(boothIdsList == null)
					{
					  boothIdsList = new ArrayList<Long>(0);
					  boothIdsMap.put(id, boothIdsList);
					}
					if(!boothIdsList.contains(id))
						boothIdsList.add(id);
				 }
			}
			
		if(boothIdsMap != null && boothIdsMap.size() > 0)
		{
		  for(Long id : boothIdsMap.keySet())
			panchayatTotalVotersMap.put(id, boothDAO.getTotalVotesByBoothIdsList(boothIdsMap.get(id)));
		}
			
		 for(Long id:resultMap.keySet())
		 {
			Map<Long,Long> partyMap = resultMap.get(id);
			Long totalVotes = 0L;
				 
			for(Long partysId:partyMap.keySet())
			  totalVotes += partyMap.get(partysId); 
				 
			Long selectedPartyTotal = partyMap.get(selectedpartyId);
			Long comparePartyTotal = 0L;
				 
			  for(Long partysId:partyMap.keySet())
			  {
			    if(!partysId.equals(selectedpartyId) && comparePartyTotal < partyMap.get(partysId))
				 comparePartyTotal = partyMap.get(partysId);
				  
			  }
		   
			     
		  double selectedPartyTotalPercent =  new BigDecimal((selectedPartyTotal*100/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	      double comparePartyTotalPercent =  new BigDecimal((comparePartyTotal*100/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	      
	     //double difference = new BigDecimal(selectedPartyTotalPercent - comparePartyTotalPercent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	     double difference = selectedPartyTotalPercent;
	    	
	      String locationName = "";
	      if(locationType != null && locationType.equalsIgnoreCase("panchayat"))
	    	 locationName = panchayatDAO.getPanchayatNameById(id); 
	      else if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
	    	 locationName = boothDAO.getBoothPartNoByBoothId(id);
	      
	      List<PartyPositionVO> partyPositionVOList = partyPositionVO.getPartyPositionVOList();
	      for(PartyPositionVO positionVO :partyPositionVOList)
	      {
	    	if(difference >= positionVO.getMinValue() && difference <= positionVO.getMaxValue())
	    	{
	    	 PartyPositionVO locationVO = null;
	    	 List<PartyPositionVO> locationList = positionVO.getPartyPositionVOList();
	    	 if(locationList == null || locationList.size() == 0)
	    		locationList = new ArrayList<PartyPositionVO>(0);
	    	 
	    	 locationVO = checkPartyPositionVOExist(id,locationList);
	    	 if(locationVO == null)
	    	 {
	    		 locationVO = new PartyPositionVO();
	    		 locationVO.setId(id);
	    		 locationVO.setName(locationName != null?locationName:" ");
	    		 locationVO.setPartyPercentage(selectedPartyTotalPercent);
	    		 locationVO.setSelectedPartyTotalVoters(selectedPartyTotal);
	    		 locationVO.setTotalValidVotes(totalVotes);
	    		 locationVO.setTotalVoters(panchayatTotalVotersMap.get(id));
	    		 locationVO.setPercentage(new BigDecimal((totalVotes*100.0/panchayatTotalVotersMap.get(id))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	    		 locationVO.setMargin(difference);
	    		 
	    		 locationList.add(locationVO);
	    		 positionVO.setPartyPositionVOList(locationList);
	    		 
	    	 }
	    	 
	    	}
	      }
	    	
		}
		}catch (Exception e) {
		 e.printStackTrace();
		 LOG.error(" Exception Occured in getPartyPerformanceForSelectedlocation() method, Exception - "+e);
		}
	}
	
	public PartyPositionVO checkPartyPositionVOExist(Long locationId,List<PartyPositionVO> list)
	{
		try{
		if(list == null)
		 return null;
		for(PartyPositionVO positionVO:list)
		 if(positionVO.equals(locationId))
		  return positionVO;
			
		 return null;
		}catch (Exception e) {
		 e.printStackTrace();
		 LOG.error(" ExceptionOccured in checkPartyPositionVOExist() method, Exception - "+e);
		 return null;
		}
	}
	
	
	
	 @SuppressWarnings("unchecked")
		public List<SelectOptionVO> getConstituenciesForUserAccessByStateId(List<SelectOptionVO> ConstituenciesForUserAccessed,Long electionId,Long electionYear)
		{
		 List<SelectOptionVO> constituencyList = new ArrayList<SelectOptionVO>(0);;
		try{
			List<Long> constituencyIds =  voterInfoDAO.getNONURBANConstituencyIds(electionId,electionYear,1L);
			if(ConstituenciesForUserAccessed != null && ConstituenciesForUserAccessed.size() > 0 && constituencyIds != null)
			{
				for(SelectOptionVO selectOptionVO : ConstituenciesForUserAccessed)
				{
					if(constituencyIds.contains(selectOptionVO.getId()))
						constituencyList.add(selectOptionVO);
				}
			}
			
			return constituencyList;
		}catch (Exception e) {
			e.printStackTrace();
			return constituencyList;
		}
		}
		public List<SelectOptionVO> getCasteAvaliableConstituencysService(List<SelectOptionVO> constituenciesForUserAccessed,Long electionId,Long electionYear,Long userId)
		{
		 List<SelectOptionVO> constituencyList = new ArrayList<SelectOptionVO>(0);
		 List<SelectOptionVO> returnList = null;
		try{
			/*List<Long> constituencyIds =  voterInfoDAO.getNONURBANConstituencyIds(electionId,electionYear,1L);
			if(ConstituenciesForUserAccessed != null && ConstituenciesForUserAccessed.size() > 0 && constituencyIds != null)
			{
				for(SelectOptionVO selectOptionVO : ConstituenciesForUserAccessed)
				{
					if(constituencyIds.contains(selectOptionVO.getId()))
						constituencyList.add(selectOptionVO);
				}
			}*/
			List<Long> consIds = new ArrayList<Long>();
			for (SelectOptionVO selectOptionVO : constituenciesForUserAccessed) {
				Long constituencyId = selectOptionVO.getId();
				consIds.add(constituencyId);
			}
			List<Object[]> constituencysList = voterCastInfoDAO.getCasteAvaliableConstituencyes(consIds,userId);
			if(constituencysList != null && constituencysList.size() > 0)
			{
				returnList = new ArrayList<SelectOptionVO>();
				for (Object[] parms : constituencysList) {
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)parms[0]);
					selectOptionVO.setName(parms[1].toString());
					returnList.add(selectOptionVO);
				}
			}
			return returnList;
		}catch (Exception e) {
			e.printStackTrace();
			return returnList;
		}
		}
	 public List<SelectOptionVO> getPartyDetailsByMandal(Long tehsilId){
		 List<SelectOptionVO> nominatedPartiesLists = null;
		 try{
			 List<Object[]> partyList= hamletBoothElectionDAO.getParticipatedPartiesByEleIdNTehsilId(tehsilId);
			 nominatedPartiesLists = new ArrayList<SelectOptionVO>();
				if(partyList !=null && partyList.size()>0)
					for (Object[] parms : partyList) {
						nominatedPartiesLists.add(new SelectOptionVO(Long.valueOf(parms[0].toString()),parms[1].toString()));
					}
			 return nominatedPartiesLists;
		 }catch(Exception e){
			 e.printStackTrace();
		 return null;
		}			
	}	
	 
	 public List<SelectOptionVO> getElectionIdsAndYearsBytehsilId(List<Long> electionScope,Long partyId,Long tehsilId){
		 List<SelectOptionVO> electionYearslist;
			List elections;
			try {
				electionYearslist = new ArrayList<SelectOptionVO>();
				if(electionScope !=null && electionScope.size()>0){
					for (Long scopeId : electionScope) {
						elections = nominationDAO.findByPartyIdAndTehsilId(scopeId,partyId,tehsilId);
						for (int i = 0; i < elections.size(); i++) {
							Object[] parms = (Object[]) elections.get(i);
							electionYearslist.add(new SelectOptionVO(Long.parseLong(parms[0].toString()), parms[1].toString().concat("("+parms[2].toString()+")")));
						}						
					}					
				}				
			return electionYearslist;
			} catch (Exception e) {
				e.printStackTrace();
			return null;
			}

	 }
 
	 public List<YouthLeaderSelectionVO> findingBoothInchargesForBoothLevelForMincipality(Long userId,Long constituencyId,List<Long> casteIdsList ,List<ExceptCastsVO> expCasteDetails,Boolean checkStatus,Long publicationId){
		 List<YouthLeaderSelectionVO> returnList = new ArrayList<YouthLeaderSelectionVO>();
		 List<SelectOptionVO> booths= null;
		 List<Long> boothIds= null;
		 List<BasicVO> basicVOListForBooth = null;
		 DecimalFormat deciamlFormat = new DecimalFormat("#.##");
		 //Long publicationId = 0L;
		 YouthLeaderSelectionVO boothyouthSelectionVO  = null;
		 List<YouthLeaderSelectionVO> botthDetailsList = null;
		 List<YouthLeaderSelectionVO> botthLevelList = null;
		 List<YouthLeaderSelectionVO> botthLevelLists = null;
		 Map<Long,Long> totalVotersInBooth = new HashMap<Long, Long>(); //Map<id,totalVoters>
		 Map<Long,List<BasicVO>> casteMapForBooth = new HashMap<Long, List<BasicVO>>();//Map<booyhid,catseDetails>
		 Map<Long,List<BasicVO>> casteMapForBooths = new HashMap<Long, List<BasicVO>>();//Map<booyhid,catseDetails>
		 Long boothTotalVoters = 0L;
		 List<BasicVO> expcastesList = null;
		 Map<Long,Double> expPanchayatCasteDetailsMap = new HashMap<Long,Double>();//Map<panchayatId,expPerc>
		 Map<Long,Map<Long,Double>> castePanchayatMap = new HashMap<Long, Map<Long,Double>>();//Map<casteId,Map<panchayatId,expperc>>
		 Map<Long,Double> casteMap = new HashMap<Long, Double>();//Map<casteId,expPerc>	
		 List<YouthLeaderSelectionVO> topCasteList = null;
		 List<YouthLeaderSelectionVO> topCastesListInTotalMuncipality = null;
		 List<BasicVO> selectedCastesinBooths = null;
		 Set<Long> expCastesIds  = null;
		 Map<Long,String> casteNamesMap = new HashMap<Long, String>();//Map<casteId,CasteName>
		 List<BasicVO> expCasteList = null;
		 Map<Long,List<BasicVO>> panchaytwiseCasteMap = new HashMap<Long, List<BasicVO>>();//Map<panchayatId,selectedCasteDetails>
		 Map<Long,Map<Long,BasicVO>> casteDetailsMap = new HashMap<Long, Map<Long,BasicVO>>();//Map<panchayatId,Map<casteId,casteDetails>>
		 Map<Long,Long> casteCountsMap = new HashMap<Long, Long>();//Map<caste,castevoters>
		 YouthLeaderSelectionVO youthLeaderSelectionVO = new YouthLeaderSelectionVO();
		 try{
			 //publicationId = publicationDateDAO.getLatestPublicationId();
			// publicationId = voterInfoDAO.getLatestPublicationDate(constituencyId);
			 List<Long> list = assemblyLocalElectionBodyDAO.getLocalEleBodyIdsListByConstituencyId(constituencyId, publicationId);
			 if(list == null || list.size() == 0)
			  return null;
			 Tehsil tehsilDetails = localElectionBodyDAO.get(list.get(0)).getTehsil();
			 if(checkStatus)
				{
					if(expCasteDetails != null && expCasteDetails.size() > 0)
					{
						expcastesList = new ArrayList<BasicVO>();
						for (ExceptCastsVO exceptCastsVO : expCasteDetails) {
							Long panchayatId = exceptCastsVO.getPanchayatId();
							Long casteId = exceptCastsVO.getCasteId();
							Double expPerc = exceptCastsVO.getCastePerc();
							expPanchayatCasteDetailsMap = castePanchayatMap.get(casteId);
							if(expPanchayatCasteDetailsMap == null)
							{
								expPanchayatCasteDetailsMap = new HashMap<Long, Double>();
								castePanchayatMap.put(casteId, expPanchayatCasteDetailsMap);
							}
							casteMap.put(casteId, expPerc);
							expPanchayatCasteDetailsMap.put(panchayatId, expPerc);
						}
						
					
						expCastesIds = casteMap.keySet();
						if(expCastesIds != null && expCastesIds.size() > 0)
						{
							List<Object[]> castNames = casteStateDAO.getCasteListByCasteIds(new ArrayList<Long>(expCastesIds));
							if(castNames != null && castNames.size() > 0)
							{
								for (Object[] parms : castNames) {
									casteNamesMap.put((Long)parms[0], parms[1].toString());
								}
								
							}
							List<Object[]> casteCounts = voterCastInfoDAO.getCasteWiseCountDetails(5l, list.get(0), publicationId, constituencyId, new ArrayList<Long>(expCastesIds), userId);
							if(casteCounts != null && casteCounts.size() > 0)
							{
								for (Object[] parms : casteCounts) {
									casteCountsMap.put((Long)parms[0], (Long)parms[1]);
								}
							}
						}
					}
				}
			 List<Booth> boothIdsList = boothDAO.getboothsDetailsByTehsilId(list.get(0),publicationId);			 
			if(boothIdsList != null && boothIdsList.size()>0){
				booths = new ArrayList<SelectOptionVO>();
		 		boothIds = new ArrayList<Long>();
				 
		 		if(!checkStatus)
		 		{
		 			for (Booth booth : boothIdsList) {
						SelectOptionVO selectOptionVO = new SelectOptionVO();
				 		selectOptionVO.setId(booth.getBoothId());
				 		selectOptionVO.setName(booth.getPartNo());
				 		booths.add(selectOptionVO);
				 		boothIds.add(selectOptionVO.getId());
					}
		 			if(boothIds != null && boothIds.size() > 0)
					{
			 			 Map<Long,Long> totalAssignedCastesMap = new HashMap<Long, Long>();
			 			List<Object[]> totalAssignedCastes = userVoterDetailsDAO.getUserAssignedCasteCountByBoothIdAndPublication(boothIds,publicationId,userId);
			 			if(totalAssignedCastes != null && totalAssignedCastes.size() > 0)
			 			for(Object[] params : totalAssignedCastes)
			 			{
			 			Long total = totalAssignedCastesMap.get((Long)params[0]);
			 			if(total == null)
			 			{
			 				total =(Long)params[1];  
			 			}
			 			totalAssignedCastesMap.put((Long)params[0], total);
			 			}
						for (Long boothId : boothIds) {
							Long totalVoter = boothPublicationVoterDAO.getTotalVoters(boothId);
							Long totalCastes = totalAssignedCastesMap.get(boothId);
							List<Object[]> casteDetails = userVoterDetailsDAO.getCasteDetailsOfVoterByBoothId(boothId,publicationId,userId);
							//boothTotalVoters = boothTotalVoters + totalVoter;
							totalVotersInBooth.put(boothId, totalVoter);
							int count = 0;
							basicVOListForBooth = new ArrayList<BasicVO>();
							selectedCastesinBooths = new ArrayList<BasicVO>();
							if(casteDetails != null && casteDetails.size() >0){
							for (Object[] parms : casteDetails) {
								
								
								if(IConstants.MAX_LEVEL > count)
								{
									BasicVO basicVO = new BasicVO();
									basicVO.setId(boothId);
									basicVO.setCount((Long)parms[1]);
									basicVO.setName(parms[0].toString());
								
									basicVO.setPerc(Double.valueOf(deciamlFormat.format((Long)parms[1]*100/totalCastes.floatValue())));
									
									basicVOListForBooth.add(basicVO);
								}
								
								if(casteIdsList.contains(parms[2])){
									BasicVO basicVO1 = new BasicVO();
									basicVO1.setId(boothId);
									basicVO1.setCount((Long)parms[1]);
									basicVO1.setName(parms[0].toString());
									basicVO1.setPerc(Double.valueOf(deciamlFormat.format((Long) parms[1]*100/totalCastes.floatValue())));
									
									selectedCastesinBooths.add(basicVO1);								
								}
										
								count ++;
							}
						}
							casteMapForBooth.put(boothId, basicVOListForBooth);
							casteMapForBooths.put(boothId, selectedCastesinBooths);
							
						}
					}
			 		
		 			botthDetailsList = new ArrayList<YouthLeaderSelectionVO>();
					
					for (Long boothId : boothIds) {
						boothyouthSelectionVO = new YouthLeaderSelectionVO();
						
						List<BasicVO> boothCasteDate = casteMapForBooth.get(boothId);
						if(boothCasteDate != null && boothCasteDate.size() > 0)
						{
							botthLevelList = new ArrayList<YouthLeaderSelectionVO>();
							for (BasicVO basicVO : boothCasteDate) {
								YouthLeaderSelectionVO youthSelectionVO = new YouthLeaderSelectionVO();										
								youthSelectionVO.setCasteName(basicVO.getName());
								youthSelectionVO.setCasteVoters(basicVO.getCount());
								youthSelectionVO.setCasteVotersPerc(basicVO.getPerc());
								botthLevelList.add(youthSelectionVO);
							}
							
						}
						
						List<BasicVO> boothCasteDatails = casteMapForBooths.get(boothId);
						botthLevelLists = new ArrayList<YouthLeaderSelectionVO>();
						if(boothCasteDatails != null && boothCasteDatails.size() > 0)
						{						
							for (BasicVO basicVO : boothCasteDatails) {
								YouthLeaderSelectionVO youthSelectionVO = new YouthLeaderSelectionVO();										
								youthSelectionVO.setCasteName(basicVO.getName());
								youthSelectionVO.setCasteVoters(basicVO.getCount());
								youthSelectionVO.setCasteVotersPerc(basicVO.getPerc());
								botthLevelLists.add(youthSelectionVO);
							}
							
						}
						boothyouthSelectionVO.setBoothId(boothId);
						boothyouthSelectionVO.setBoothName(boothDAO.get(boothId).getPartNo());
						boothyouthSelectionVO.setBoothTotalVoters(totalVotersInBooth.get(boothId));
						boothyouthSelectionVO.setBoothLevelLeadersList(botthLevelList);
						boothyouthSelectionVO.setSelectedCastesList(botthLevelLists);
						botthDetailsList.add(boothyouthSelectionVO);
						
					}
		 		}		
				List<VoterCastInfo> tehsilCastDetails = voterCastInfoDAO.getVotersCastInfo(5l,list.get(0),constituencyId,publicationId,userId);
				boothTotalVoters = voterInfoDAO.getTotalVotersForSelectdLevel(5l,list.get(0),publicationId,constituencyId);
					if(tehsilCastDetails != null && tehsilCastDetails.size()>0){
						int count =0;
						topCasteList = new ArrayList<YouthLeaderSelectionVO>();
						topCastesListInTotalMuncipality = new ArrayList<YouthLeaderSelectionVO>();
						for (VoterCastInfo parms : tehsilCastDetails) {
							
							if(IConstants.MAX_LEVEL > count){
								
								YouthLeaderSelectionVO youthLeaderSelectionVO1 = new YouthLeaderSelectionVO();
								youthLeaderSelectionVO1.setCasteName(parms.getCasteState().getCaste().getCasteName());
								youthLeaderSelectionVO1.setCasteVotersPerc(parms.getCastePercentage());
								topCasteList.add(youthLeaderSelectionVO1);
							}
							
							if(casteIdsList.contains(parms.getCasteState().getCasteStateId())){

								YouthLeaderSelectionVO youthLeaderSelectionVO1 = new YouthLeaderSelectionVO();
								youthLeaderSelectionVO1.setCasteName(parms.getCasteState().getCaste().getCasteName());
								youthLeaderSelectionVO1.setCasteVotersPerc(parms.getCastePercentage());
								topCastesListInTotalMuncipality.add(youthLeaderSelectionVO1);
							}
							count++;
						}
					}
					if(checkStatus)
					{
						
							expCasteList = new ArrayList<BasicVO>();
							Long casteCount = 0l;
							for (Long casteId : expCastesIds) {
									if(casteId > 0)
									{
										BasicVO casteVO = new BasicVO();
										casteVO.setId(casteId);
										Long total = casteCountsMap.get(casteId);
										Double expPerc = castePanchayatMap.get(casteId).get(list.get(0));
										Long expVoters = (long) (total*expPerc);
										casteVO.setCount(total);
										casteVO.setExpCount(expVoters);
										casteVO.setName(casteNamesMap.get(casteId));
										casteVO.setPerc(expPerc);
										expCasteList.add(casteVO);
										casteCount = casteCount + total;
										panchaytwiseCasteMap.put(list.get(0), expCasteList);
									}
										
									
								}
							BasicVO casteVO = new BasicVO();
							casteVO.setId(0l);
							Long total = boothTotalVoters - casteCount;
							Double expPerc = castePanchayatMap.get(0l).get(list.get(0));
							Long expVoters = (long) (total*expPerc);
							casteVO.setCount(total);
							casteVO.setExpCount(expVoters);
							casteVO.setName("OTHERS");
							casteVO.setPerc(expPerc);
							expCasteList.add(casteVO);
							panchaytwiseCasteMap.put(list.get(0), expCasteList);
							}
					}
				youthLeaderSelectionVO.setExceptdCateDetails(panchaytwiseCasteMap.get(list.get(0)));
				youthLeaderSelectionVO.setBoothLevelLeadersList(botthDetailsList);
				youthLeaderSelectionVO.setBoothTotalVoters(boothTotalVoters);
				youthLeaderSelectionVO.setMandalId(tehsilDetails.getTehsilId());
				youthLeaderSelectionVO.setMandalName(tehsilDetails.getTehsilName());
				youthLeaderSelectionVO.setPanchayatLevelLeadersList(topCasteList);
				youthLeaderSelectionVO.setSelectedCastesList(topCastesListInTotalMuncipality);
				returnList.add(youthLeaderSelectionVO);
		 }catch(Exception e){
			 LOG.error("Exception raised in findingBoothInchargesForBoothLevelForMincipality() method in Suggestive Model Service", e);
		 }
		 return returnList;		 
	 }
	 /**
	  * This method is used for to fill the excepted caste details caste wise and panchayat wise fills in a caste map
	  * @param expCaseList
	  * @param castePanchayatMap
	  * @param expPanchayatCasteDetailsMap
	  * @param casteMap
	  */
	 public void fillExpCasteForEachAndEveryCasteAndPanchayat(List<ExceptCastsVO> expCaseList,Map<Long,Map<Long,Double>> castePanchayatMap,Map<Long,Double> expPanchayatCasteDetailsMap,Map<Long,Double> casteMap)
	 {
		 try {
			 LOG.debug("Enterd Into fillExpCasteForEachAndEveryCasteAndPanchayat() method in SuggestiveModelService Class ");
			 for (ExceptCastsVO exceptCastsVO : expCaseList) {
					Long panchayatId = exceptCastsVO.getPanchayatId();
					Long casteId = exceptCastsVO.getCasteId();
					Double expPerc = exceptCastsVO.getCastePerc();
					expPanchayatCasteDetailsMap = castePanchayatMap.get(casteId);
					if(expPanchayatCasteDetailsMap == null)
					{
						expPanchayatCasteDetailsMap = new HashMap<Long, Double>();
						castePanchayatMap.put(casteId, expPanchayatCasteDetailsMap);
					}
					casteMap.put(casteId, expPerc);
					expPanchayatCasteDetailsMap.put(panchayatId, expPerc);
				}
		} catch (Exception e) {
			 LOG.error("Exception raised in fillExpCasteForEachAndEveryCasteAndPanchayat() method in Suggestive Model Service", e);
		}
		
	 }
	 /**
	  * This method is used for filling the panchayat ids into the VO .
	  * @param panchayats
	  * @param panchayaIds
	  * @param panchayatsList
	  */
	 public void getAllPanchayatsInATehsil(List<SelectOptionVO> panchayats,List<Long> panchayaIds,List<Object[]> panchayatsList)
	 {
		 try {
			 LOG.debug("Enterd Into getAllPanchayatsInATehsil() method in SuggestiveModelService Class ");
			 for (Object[] parms : panchayatsList) {
					SelectOptionVO selectOptionVO = new SelectOptionVO();	
					selectOptionVO.setId((Long)parms[0]);
					selectOptionVO.setName(parms[1].toString());
					panchayats.add(selectOptionVO);
					panchayaIds.add(selectOptionVO.getId());
				}
		} catch (Exception e) {
			 LOG.error("Exception raised in getAllPanchayatsInATehsil() method in Suggestive Model Service", e);
		}
			
	 }
	 
	 /**
	  * This method is used for filling the Booths ids into the VO .
	  * @param booths
	  * @param boothIds
	  * @param boothsList
	  */
	 public void getAllBoothsInAPanchayat(List<SelectOptionVO> booths,List<Long> boothIds,List<Object[]> boothsList)
	 {
		 try {
			 LOG.debug("Enterd Into getAllBoothsInAPanchayat() method in SuggestiveModelService Class ");
			 for (Object[] parms : boothsList) {
					SelectOptionVO selectOptionVO = new SelectOptionVO();	
					selectOptionVO.setId((Long)parms[0]);
					selectOptionVO.setName(parms[1].toString());
					booths.add(selectOptionVO);
					boothIds.add(selectOptionVO.getId());
				}
		} catch (Exception e) {
			 LOG.error("Exception raised in getAllBoothsInAPanchayat() method in Suggestive Model Service", e);
		}
		 
	 }
	 
	 /**
	  * this method is used for storing the selected caste and exp caste details into th VO
	  * @param selectedCastesInPanchayats
	  * @param basicVOListForPanchayat
	  * @param expCasteDetails
	  * @param casteDetails
	  * @param casteIdsList
	  * @param expCaste
	  * @param panchayatId
	  * @param casteDetailsMap
	  * @param casteDataMap
	  */
	 public void fillSelectedCastesAndExpCasteDatilsPanchayatWise(List<BasicVO> selectedCastesInPanchayats,List<BasicVO> basicVOListForPanchayat ,List<BasicVO> expCasteDetails ,List<Object[]> casteDetails,List<Long> casteIdsList,boolean expCaste,Long panchayatId,Map<Long,Map<Long,BasicVO>> casteDetailsMap,Map<Long,BasicVO> casteDataMap)
	 {
		 try {
			 LOG.debug("Enterd Into fillSelectedCastesAndExpCasteDatilsPanchayatWise() method in SuggestiveModelService Class ");
			 int count = 0;
				for (Object[] parms : casteDetails) {
					if(IConstants.MAX_LEVEL > count)
					{
						BasicVO basicVO = new BasicVO();
						basicVO.setId((Long)parms[0]);
						basicVO.setCount((Long)parms[2]);
						basicVO.setName(parms[1].toString());
						basicVO.setPerc((Double)parms[3]);
						basicVOListForPanchayat.add(basicVO);
						
					}
					
					if(casteIdsList.contains(parms[4])){
						BasicVO basicVO1 = new BasicVO();
						basicVO1.setId((Long)parms[0]);
						basicVO1.setCount((Long)parms[2]);
						basicVO1.setName(parms[1].toString());
						basicVO1.setPerc((Double)parms[3]);
						
						selectedCastesInPanchayats.add(basicVO1);
					}
					
					if(expCaste)
					{
					casteDataMap = casteDetailsMap.get(panchayatId);
					if(casteDataMap == null)
					{
						casteDataMap = new HashMap<Long, BasicVO>();
						casteDetailsMap.put(panchayatId, casteDataMap);
					}
					BasicVO casteVO = casteDataMap.get((Long)parms[4]);
					if(casteVO == null)
					{
						casteVO = new BasicVO();
						casteDataMap.put((Long)parms[4], casteVO);
					}
					casteVO.setId((Long)parms[4]);
					casteVO.setCount((Long)parms[2]);
					casteVO.setName(parms[1].toString());
					casteVO.setPerc((Double)parms[3]);
					}
					count ++;
				}
		} catch (Exception e) {
			 LOG.error("Exception raised in fillSelectedCastesAndExpCasteDatilsPanchayatWise() method in Suggestive Model Service", e);
		}
		 
	 }
	 
	 /**
	  * This method is used for storing the selected caste details booth wise.
	  * @param boothIds
	  * @param totalVotersInBooth
	  * @param basicVOListForBooth
	  * @param selectedCastesInBooths
	  * @param userid
	  * @param publicationId
	  * @param constituencyId
	  * @param casteIdsList
	  * @param casteMapForBooth
	  * @param casteMapForBooths
	  */
	 public void fillSelectedCastesAndExpCasteDatilsBoothWise(List<Long> boothIds,Map<Long, Long> totalVotersInBooth ,List<BasicVO> basicVOListForBooth , List<BasicVO> selectedCastesInBooths ,Long userid,Long publicationId,Long constituencyId,List<Long> casteIdsList,Map<Long,List<BasicVO>> casteMapForBooth,Map<Long,List<BasicVO>> casteMapForBooths)
	 {
		 try {
			 LOG.debug("Enterd Into fillSelectedCastesAndExpCasteDatilsBoothWise() method in SuggestiveModelService Class ");
			 for (Long boothId : boothIds) {
					Long totalVoter = boothPublicationVoterDAO.getTotalVoters(boothId);
					//List<Object[]> casteDetails = userVoterDetailsDAO.getCasteDetailsOfVoterByBoothId(boothId,publicationId,userid);
					List<VoterCastInfoVO> casteDetails = votersAnalysisService.getCastAndGenderWiseVotersCountByPublicationIdInALocation(userid,"booth",boothId,publicationId,constituencyId);
					
					Collections.sort(casteDetails, new Comparator<VoterCastInfoVO>(){
						public int compare(VoterCastInfoVO o1, VoterCastInfoVO o2) {
							  return o2.getTotalVoters().compareTo(o1.getTotalVoters());
						}
						
					});
					totalVotersInBooth.put(boothId, totalVoter);
					int count = 0;
					basicVOListForBooth = new ArrayList<BasicVO>();
					selectedCastesInBooths = new ArrayList<BasicVO>();
					for (VoterCastInfoVO params : casteDetails) {
						if(IConstants.MAX_LEVEL > count)
						{
							BasicVO basicVO = new BasicVO();
							
							basicVO.setId(boothId);
							basicVO.setName(params.getCastName());
							basicVO.setCount(params.getFemaleVoters()+params.getMaleVoters());
							basicVO.setPerc(Double.valueOf(params.getVotesPercent()));
							basicVOListForBooth.add(basicVO);
						}
						
						if(casteIdsList.contains(params.getCasteStateId())){
							BasicVO basicVO = new BasicVO();
							basicVO.setId(boothId);
							basicVO.setName(params.getCastName());
							basicVO.setCount(params.getFemaleVoters()+params.getMaleVoters());
							basicVO.setPerc(Double.valueOf(params.getVotesPercent()));
							
							selectedCastesInBooths.add(basicVO);
						}
						count ++;
						
					}								
					casteMapForBooth.put(boothId, basicVOListForBooth);
					casteMapForBooths.put(boothId, selectedCastesInBooths);
				}
		} catch (Exception e) {
			 LOG.error("Exception raised in fillSelectedCastesAndExpCasteDatilsBoothWise() method in Suggestive Model Service", e);
		}
		 
	 }
	 /**
	  * This method is used for filling the YouthLeaderSelectionVO For Selected catse details in panchayawise
	  * @param panchayatid
	  * @param publicationId
	  * @param botthDetailsList
	  * @param boothyouthSelectionVO
	  * @param casteMapForBooth
	  * @param botthLevelList
	  * @param botthLevelLists
	  * @param casteMapForBooths
	  * @param totalVotersInBooth
	  * @param youthLeaderSelectionVO
	  */
	 public void fillTheYouthLeaderSelectionVOForSelectedCatseDetialsPanchayatWise(Long panchayatid,Long publicationId,List<YouthLeaderSelectionVO> botthDetailsList,YouthLeaderSelectionVO boothyouthSelectionVO,Map<Long,List<BasicVO>> casteMapForBooth,List<YouthLeaderSelectionVO> botthLevelList ,List<YouthLeaderSelectionVO> botthLevelLists,Map<Long,List<BasicVO>> casteMapForBooths,Map<Long, Long> totalVotersInBooth,YouthLeaderSelectionVO youthLeaderSelectionVO)
	 {
		 try {
			 LOG.debug("Enterd Into fillTheYouthLeaderSelectionVOForSelectedCatseDetialsPanchayatWise() method in SuggestiveModelService Class ");
			 List<Long> boothList = boothDAO.getBoothsByPanchayatId(panchayatid,publicationId);
			 List<Object[]> boothsNamesList = boothDAO.getboothNamesByBoothIds(boothList);
			 Map<Long,String> boothsNameMap = null;
				 if(boothsNamesList != null && boothsNamesList.size() > 0)
				 {
					 boothsNameMap = new HashMap<Long, String>();
					for (Object[] parms : boothsNamesList) {
						boothsNameMap.put((Long)parms[0], parms[1].toString());
					} 
				 }
				if(boothList != null && boothList.size() > 0)
				{
					botthDetailsList = new ArrayList<YouthLeaderSelectionVO>();
					for (Long boothId : boothList) {
						boothyouthSelectionVO = new YouthLeaderSelectionVO();
						
						List<BasicVO> boothCasteDate = casteMapForBooth.get(boothId);
						if(boothCasteDate != null && boothCasteDate.size() > 0)
						{
							botthLevelList = new ArrayList<YouthLeaderSelectionVO>();
							for (BasicVO basicVO : boothCasteDate) {
								YouthLeaderSelectionVO youthSelectionVO = new YouthLeaderSelectionVO();										
								youthSelectionVO.setCasteName(basicVO.getName());
								youthSelectionVO.setCasteVoters(basicVO.getCount());
								youthSelectionVO.setCasteVotersPerc(basicVO.getPerc());
								botthLevelList.add(youthSelectionVO);
							}
							
						}
						
						List<BasicVO> boothCasteDatails = casteMapForBooths.get(boothId);
						botthLevelLists = new ArrayList<YouthLeaderSelectionVO>();
						if(boothCasteDatails != null && boothCasteDatails.size() > 0)
						{
							for (BasicVO basicVO : boothCasteDatails) {
								YouthLeaderSelectionVO youthSelectionVO = new YouthLeaderSelectionVO();										
								youthSelectionVO.setCasteName(basicVO.getName());
								youthSelectionVO.setCasteVoters(basicVO.getCount());
								youthSelectionVO.setCasteVotersPerc(basicVO.getPerc());
								botthLevelLists.add(youthSelectionVO);
							}
						}
						boothyouthSelectionVO.setBoothId(boothId);
						boothyouthSelectionVO.setBoothName(boothsNameMap.get(boothId));
						boothyouthSelectionVO.setBoothTotalVoters(totalVotersInBooth.get(boothId));
						boothyouthSelectionVO.setBoothLevelLeadersList(botthLevelList);
						boothyouthSelectionVO.setSelectedCastesList(botthLevelLists);
						botthDetailsList.add(boothyouthSelectionVO);
						
					}
					
					youthLeaderSelectionVO.setBoothLevelLeadersList(botthDetailsList);
				}
		} catch (Exception e) {
			 LOG.error("Exception raised in fillTheYouthLeaderSelectionVOForSelectedCatseDetialsPanchayatWise() method in Suggestive Model Service", e);
		}
		 
	 }
	 /**
	  * This method is used for filling the YouthLeaderSelectionVO For exp catse details in panchayawise
	  * @param panchayatid
	  * @param casteDetailsMap
	  * @param expCasteDetails
	  * @param expCastesIds
	  * @param casteNamesMap
	  * @param castePanchayatMap
	  * @param panchaytwiseCasteMap
	  * @param panchayatTotalVoters
	  * @param youthLeaderSelectionVO
	  */
	 public void fillTheYouthLeaderSelectionVOForExpCatseDetialsPanchayatWise(Long panchayatid,Map<Long,Map<Long,BasicVO>> casteDetailsMap ,List<BasicVO> expCasteDetails , Set<Long> expCastesIds ,Map<Long,String> casteNamesMap ,Map<Long,Map<Long,Double>> castePanchayatMap,Map<Long,List<BasicVO>> panchaytwiseCasteMap ,Long panchayatTotalVoters,YouthLeaderSelectionVO youthLeaderSelectionVO)
	 {
		 try {
			 LOG.debug("Enterd Into fillTheYouthLeaderSelectionVOForExpCatseDetialsPanchayatWise() method in SuggestiveModelService Class ");
			 Map<Long,BasicVO> casteData = casteDetailsMap.get(panchayatid);
				if(casteData != null && casteData.size() > 0)
				{
					Long casteCount = 0l;
					expCasteDetails = new ArrayList<BasicVO>();
					for (Long casteId : expCastesIds) {
						if(casteId > 0)
						{
							BasicVO basicVO = casteData.get(casteId);
							if(basicVO == null)
							{
								BasicVO casteVO = new BasicVO();
								casteVO.setId(casteId);
								casteVO.setName(casteNamesMap.get(casteId));
								casteVO.setPerc(castePanchayatMap.get(casteId).get(panchayatid));
								casteVO.setCount(0l);
								casteVO.setExpCount(0l);
								expCasteDetails.add(casteVO);
								panchaytwiseCasteMap.put(panchayatid, expCasteDetails);
							}
							else
							{
								BasicVO casteVO = new BasicVO();
								casteVO.setId(basicVO.getId());
								Long total = basicVO.getCount();
								Double expPerc = castePanchayatMap.get(casteId).get(panchayatid);
								Long expVoters = (long) (total*expPerc);
								casteVO.setCount(basicVO.getCount());
								casteVO.setExpCount(expVoters);
								casteVO.setName(basicVO.getName());
								casteVO.setPerc(expPerc);
								expCasteDetails.add(casteVO);
								casteCount = casteCount + total;
								panchaytwiseCasteMap.put(panchayatid, expCasteDetails);
							}
									
							
						}
					}
					BasicVO casteVO = new BasicVO();
					casteVO.setId(0l);
					Long total = panchayatTotalVoters - casteCount ;
					Double expPerc = castePanchayatMap.get(0l).get(panchayatid);
					Long expVoters = (long) (total*expPerc);
					casteVO.setCount(total);
					casteVO.setExpCount(expVoters);
					casteVO.setName("OTHERS");
					casteVO.setPerc(expPerc);
					expCasteDetails.add(casteVO);
					casteCount = casteCount + total;
					panchaytwiseCasteMap.put(panchayatid, expCasteDetails);
				}
				else
				{
					Long casteCount = 0l;
					expCasteDetails = new ArrayList<BasicVO>();
					for (Long casteId : expCastesIds) {
						if(casteId > 0)
						{
							BasicVO casteVO = new BasicVO();
							casteVO.setId(casteId);
							casteVO.setName(casteNamesMap.get(casteId));
							casteVO.setPerc(castePanchayatMap.get(casteId).get(panchayatid));
							casteVO.setCount(0l);
							casteVO.setExpCount(0l);
							expCasteDetails.add(casteVO);
							panchaytwiseCasteMap.put(panchayatid, expCasteDetails);
							
						}
					}
					BasicVO casteVO = new BasicVO();
					casteVO.setId(0l);
					Long total = panchayatTotalVoters - casteCount ;
					Double expPerc = castePanchayatMap.get(0l).get(panchayatid);
					Long expVoters = (long) (total*expPerc);
					casteVO.setCount(total);
					casteVO.setExpCount(expVoters);
					casteVO.setName("OTHERS");
					casteVO.setPerc(expPerc);
					expCasteDetails.add(casteVO);
					casteCount = casteCount + total;
					panchaytwiseCasteMap.put(panchayatid, expCasteDetails);
				}
				
				youthLeaderSelectionVO.setExceptdCateDetails(panchaytwiseCasteMap.get(panchayatid));
				
		} catch (Exception e) {
			 LOG.error("Exception raised in fillTheYouthLeaderSelectionVOForExpCatseDetialsPanchayatWise() method in Suggestive Model Service", e);
		}
		
	 }
	 /**
	  * This service is used for getiing top castes,selected castes and exp caste details in constituency laevel.
	  * @param Long userid
	  * @param Long constituencyId
	  * @param List<Long> casteIdsList
	  * @param Map<Long,Double> casteMap
	  * @param List<ExceptCastsVO> expCaseList
	  * @param boolean expCaste
	  * @return List<YouthLeaderSelectionVO>
	  */
	 public List<YouthLeaderSelectionVO> findingBoothInchargesForBoothLevel(Long userid,Long constituencyId,List<Long> casteIdsList,Map<Long,Double> casteMap,List<ExceptCastsVO> expCaseList,boolean expCaste,Long publicationId,List<ExceptCastsVO> expCaseListForMuncipal)
	 {
		 List<YouthLeaderSelectionVO> returnList = new ArrayList<YouthLeaderSelectionVO>();
		 try {
			 LOG.debug("Enterd Into findingBoothInchargesForBoothLevel() method in SuggestiveModelService Class ");
				List<SelectOptionVO> panchayats = new ArrayList<SelectOptionVO>();
				List<Long> panchayaIds = new ArrayList<Long>();
				List<SelectOptionVO> booths = new ArrayList<SelectOptionVO>();
				List<Long> boothIds = new ArrayList<Long>();
				Map<Long, Long> totalVotersInPanchayat = new HashMap<Long, Long>();//Map<id,totalVoters>
				Map<Long, Long> totalVotersInBooth = new HashMap<Long, Long>();//Map<id,totalVoters>
				Map<Long,List<BasicVO>> casteMapForPanchayat = new HashMap<Long, List<BasicVO>>();//Map<panchayatid,catseDetails>
				Map<Long,List<BasicVO>> casteMapForPanchayats = new HashMap<Long, List<BasicVO>>();//Map<panchayatid,catseDetails>
				Map<Long,List<BasicVO>> casteMapForBooth = new HashMap<Long, List<BasicVO>>();//Map<booyhid,catseDetails>
				Map<Long,List<BasicVO>> casteMapForBooths = new HashMap<Long, List<BasicVO>>();//Map<booyhid,catseDetails>
				Map<Long,String> casteNamesMap = new HashMap<Long, String>();//Map<casteId,casteName>
				//Long publicationId = 0l;
				List<BasicVO> basicVOListForPanchayat = null;
				List<BasicVO> basicVOListForBooth = null;
				List<YouthLeaderSelectionVO> botthLevelList = null;
				List<YouthLeaderSelectionVO> botthLevelLists = null;
				List<YouthLeaderSelectionVO> panchayatLevelList = null;
				List<YouthLeaderSelectionVO> panchayatLevelLists = null;
				YouthLeaderSelectionVO boothyouthSelectionVO  = null;
				List<YouthLeaderSelectionVO> botthDetailsList = null;
				List<BasicVO> selectedCastesInBooths = null;
				List<BasicVO> selectedCastesInPanchayats = null;
				List<BasicVO> expCasteDetails = null;
				//Map<Long,List<BasicVO>> expCasteMap = new HashMap<Long, List<BasicVO>>();//Map<casteId,expcastesList>
				Map<Long,List<BasicVO>> panchaytwiseCasteMap = new HashMap<Long, List<BasicVO>>();//Map<panchayatId,selectedCasteDetails>
				Map<Long,Map<Long,BasicVO>> casteDetailsMap = new HashMap<Long, Map<Long,BasicVO>>();//Map<panchayatId,Map<casteId,casteDetails>>
				Map<Long,BasicVO> casteDataMap = new HashMap<Long, BasicVO>();//Map<panchayatId,selectedCasteDetails>
				Map<Long,Double> expPanchayatCasteDetailsMap = new HashMap<Long,Double>();//Map<panchayatId,expPerc>
				Map<Long,Map<Long,Double>> castePanchayatMap = new HashMap<Long, Map<Long,Double>>();//Map<casteId,Map<panchayatId,expperc>>
				//List<BasicVO> expcastesList = null;
				Set<Long> expCastesIds  = null;
				DecimalFormat df = new DecimalFormat("#.##");
				//publicationId = publicationDateDAO.getLatestPublicationId();
				//publicationId = voterInfoDAO.getLatestPublicationDate(constituencyId);
				List<Long> tehsilIds = boothDAO.getTehsildByConstituency(constituencyId,publicationId);
				
				if(expCaste)
				{
					if(expCaseList != null && expCaseList.size() > 0)
					{
						//expcastesList = new ArrayList<BasicVO>();
						//this method is used for to fill the excepted caste details caste wise and panchayat wise fills in a caste map
						fillExpCasteForEachAndEveryCasteAndPanchayat( expCaseList, castePanchayatMap, expPanchayatCasteDetailsMap, casteMap);
								
					}
					expCastesIds = casteMap.keySet();// getting the exp caste ids .
					
					// Here we are processing the exp caste names with caste ids 
					if(expCastesIds != null && expCastesIds.size() > 0)
					{
						List<Object[]> castNames = casteStateDAO.getCasteListByCasteIds(new ArrayList<Long>(expCastesIds));
						if(castNames != null && castNames.size() > 0)
						{
							for (Object[] parms : castNames) {
								casteNamesMap.put((Long)parms[0], parms[1].toString());
							}
							
						}
					}
					

				}
				// here we are checkinthf the tehsils are exists are not if exists then then will get all the caste and exp percentages for selected caste .
				
				if(tehsilIds != null && tehsilIds.size() > 0)
				{
					for (Long mandalId : tehsilIds) {
						//List<Object[]> panchayatsList = panchayatDAO.getPanchayatsByTehsilId(mandalId);
						List<Object[]> panchayatsList =  voterInfoDAO.getPanchayatDetailsForConstituency(constituencyId,publicationId,mandalId);
						if(panchayatsList != null && panchayatsList.size() > 0)
						{
							panchayats = new ArrayList<SelectOptionVO>();
							panchayaIds = new ArrayList<Long>();
							// this method is used for filling the all panchaya ids into the VO
							getAllPanchayatsInATehsil(panchayats,panchayaIds,panchayatsList);
						}
						
						if(panchayaIds != null && panchayaIds.size() > 0)
						{
							//List<Object[]> panchaytVotersCount = voterCastBasicInfoDAO.getToatlVotersForSelectedLevl(panchayaIds,userid,publicationId,3l,constituencyId);
							List<Object[]> panchaytVotersCount = voterInfoDAO.getTotalVotersInAPanchayat(constituencyId,publicationId,3l,panchayaIds);
							if(panchaytVotersCount != null && panchaytVotersCount.size() > 0)
							{
								for (Object[] parms : panchaytVotersCount) {
									totalVotersInPanchayat.put((Long)parms[0],((Long)parms[1]));
								}
							}
							else
							{
								return returnList;
							}
							
							for (Long panchayatId : panchayaIds) {
								if(!expCaste)
								{
									List<Object[]> boothsList = boothDAO.getBoothsByPanchayat(panchayatId,publicationId);
									if(boothsList != null && boothsList.size() > 0)
									{
										
										//This method is used for filling the Booths ids into the VO .
										getAllBoothsInAPanchayat(booths, boothIds, boothsList);										
									}
								}
								
								List<Object[]> casteDetails = voterCastInfoDAO.getTopThreeCasteFoeSelctedLevel(panchayatId,3l,publicationId,userid);
								if(casteDetails != null && casteDetails.size() > 0)
								{
									selectedCastesInPanchayats = new ArrayList<BasicVO>();
									basicVOListForPanchayat = new ArrayList<BasicVO>();
									expCasteDetails = new ArrayList<BasicVO>();
									// this method is used for storing the selected caste and exp caste details into th VO
									fillSelectedCastesAndExpCasteDatilsPanchayatWise( selectedCastesInPanchayats, basicVOListForPanchayat , expCasteDetails , casteDetails, casteIdsList,expCaste, panchayatId, casteDetailsMap, casteDataMap);
								}
								casteMapForPanchayat.put(panchayatId, basicVOListForPanchayat);
								casteMapForPanchayats.put(panchayatId, selectedCastesInPanchayats);
								//panchaytwiseCasteMap.put(panchayatId, expCasteDetails);
							}
							
							
						}
						if(!expCaste)
						{
							if(boothIds != null && boothIds.size() > 0)
							{
								// this method is used for filling the selectd caste details booth wise
								fillSelectedCastesAndExpCasteDatilsBoothWise(boothIds,totalVotersInBooth,basicVOListForBooth,selectedCastesInBooths,userid,publicationId,constituencyId,casteIdsList,casteMapForBooth,casteMapForBooths);
							}
						}
						
						if(panchayaIds != null && panchayaIds.size() > 0)
						{
							for (Long panchayatid : panchayaIds) {
								YouthLeaderSelectionVO youthLeaderSelectionVO = new YouthLeaderSelectionVO();
								 List<BasicVO> panchayatDetails = casteMapForPanchayat.get(panchayatid);
								 Long panchayatTotalVoters = totalVotersInPanchayat.get(panchayatid);
								 panchayatLevelList = new ArrayList<YouthLeaderSelectionVO>();
								 if(panchayatDetails != null && panchayatDetails.size() > 0)
								 {
									 for (BasicVO basicVO : panchayatDetails) {
										 YouthLeaderSelectionVO youthLeaderSelection = new YouthLeaderSelectionVO();
										 youthLeaderSelection.setCasteName(basicVO.getName());
										 youthLeaderSelection.setCasteVoters(basicVO.getCount());
										 youthLeaderSelection.setCasteVotersPerc(basicVO.getPerc());
										 panchayatLevelList.add(youthLeaderSelection);
									}
								 }
								 
								 List<BasicVO> panchayatDetailse = casteMapForPanchayats.get(panchayatid);
								 panchayatLevelLists = new ArrayList<YouthLeaderSelectionVO>();
								 if(panchayatDetailse != null && panchayatDetailse.size() > 0)
								 {
									 for (BasicVO basicVO : panchayatDetailse) {
										 YouthLeaderSelectionVO youthLeaderSelection = new YouthLeaderSelectionVO();
										 youthLeaderSelection.setCasteName(basicVO.getName());
										 youthLeaderSelection.setCasteVoters(basicVO.getCount());
										 youthLeaderSelection.setCasteVotersPerc(basicVO.getPerc());
										 panchayatLevelLists.add(youthLeaderSelection);
									}
								 }
								if(!expCaste)
								{
									// This method is used for filling the YouthLeaderSelectionVO For Selected catse details in panchayawise
									fillTheYouthLeaderSelectionVOForSelectedCatseDetialsPanchayatWise( panchayatid, publicationId, botthDetailsList, boothyouthSelectionVO, casteMapForBooth, botthLevelList , botthLevelLists, casteMapForBooths,totalVotersInBooth, youthLeaderSelectionVO);
								}
								
								youthLeaderSelectionVO.setPanchayatTotalVoters(panchayatTotalVoters);
								youthLeaderSelectionVO.setPanchayatId(panchayatid);
								youthLeaderSelectionVO.setMandalName(tehsilDAO.get(mandalId).getTehsilName());
								youthLeaderSelectionVO.setPanchayatName(panchayatDAO.get(panchayatid).getPanchayatName());
								youthLeaderSelectionVO.setPanchayatLevelLeadersList(panchayatLevelList);
								youthLeaderSelectionVO.setSelectedCastesList(panchayatLevelLists);
								if(expCaste)
								{
									//This method is used for filling the YouthLeaderSelectionVO For exp catse details in panchayawise
									fillTheYouthLeaderSelectionVOForExpCatseDetialsPanchayatWise( panchayatid, casteDetailsMap , expCasteDetails ,  expCastesIds , casteNamesMap , castePanchayatMap, panchaytwiseCasteMap , panchayatTotalVoters, youthLeaderSelectionVO);
								}
								returnList.add(youthLeaderSelectionVO);
							}
						}
					}
				}
				if(constituencyDAO.get(constituencyId).getAreaType().equalsIgnoreCase("RURAL-URBAN"))
				{
					List<YouthLeaderSelectionVO> list = findingBoothInchargesForBoothLevelForMincipality(userid,constituencyId,casteIdsList , expCaseListForMuncipal, expCaste,publicationId);
					if(list != null && list.size() > 0)
					{
						returnList.addAll(list);
					}
				}
				
				
			} catch (Exception e) {
				LOG.error(" Exception Occured in findingBoothInchargesForBoothLevel() method, Exception - "+e);
			}
			
		return returnList;
	}
	 
	/*public void getExceptedCatses(Long userId,Long constituencyId,Map<Long,Double> casteMap,Long publicationId,List<Long> panchayatIds,Map<Long,List<BasicVO>> panchaytwiseCasteMap)
	{
		List<BasicVO> returnList = null;
		List<BasicVO> optionVOList = null;
		Set<Long> casteIds = casteMap.keySet();	
		List<Object[]> panchayatWisecasteDetails = voterCastInfoDAO.getTopCasteFoeSelctedLevel(panchayatIds,3l,publicationId,userId,casteIds);
		if(panchayatWisecasteDetails != null && panchayatWisecasteDetails.size() > 0)
		{
			returnList = new ArrayList<BasicVO>();
			for (Object[] parms : panchayatWisecasteDetails) {
				BasicVO basicVO = new BasicVO();
				if(panchaytwiseCasteMap.get((Long)parms[3]) == null)
				{
					optionVOList = new ArrayList<BasicVO>();
				}
				BasicVO casteBasicVO = new BasicVO();
				casteBasicVO.setId((Long)parms[0]);
				casteBasicVO.setCasteName(parms[1].toString());
				Long totalVoters = (Long)parms[2];
				Double expPerc = casteMap.get((Long)parms[0]);
				Long expVotes = (long) (totalVoters*expPerc);
				casteBasicVO.setVoterCount(expVotes);
				casteBasicVO.setPerc(expPerc);
				optionVOList.add(casteBasicVO);
				basicVO.setPanchayatVoterInfo(optionVOList);
				returnList.add(basicVO);
				panchaytwiseCasteMap.put((Long)parms[3], returnList);
			}
		}		
	}
	 */
	/* public List<YouthLeaderSelectionVO> findingBoothInchargesForBoothLevel(Long tehsilId,Long userid,Long constituencyId)
	 {
		 List<YouthLeaderSelectionVO>  returnList = new ArrayList<YouthLeaderSelectionVO>();
		 try {
			 LOG.debug("Enterd Into findingBoothInchargesForBoothLevel() method in SuggestiveModelService Class ");
				
				DecimalFormat df = new DecimalFormat("#.##");
				Long publicationId = 0l;
				Map<Long,Long> totalVotersInPanchayat = new HashMap<Long, Long>();//Map<panchayatId,totalVoters>
				Map<Long,List<BasicVO>> panchayatCasteMap = new HashMap<Long, List<BasicVO>>();//Map<panchayatId,CasteDetails>
				//Map<Long,Map<String,List<BasicVO>>> panchayatMap = new HashMap<Long, Map<String,List<BasicVO>>>();//Map<panchayatId,<Map<casteName,CasteDetails>
				Map<Long,List<BasicVO>> boothCasteMap = new HashMap<Long, List<BasicVO>>();//Map<boothId,<Map<casteName,CasteDetails>
				Map<Long,Map<Long,List<BasicVO>>> panchayatDataMap = new HashMap<Long, Map<Long,List<BasicVO>>>();//Map<panchayatId,Map<boothId,<Map<casteName,CasteDetails>
				Map<Long,Long> totalVotersInBooth = new HashMap<Long, Long>();//Map<boothId,totalVoters>
				//Map<String,List<BasicVO>> boothCasteMap = new HashMap<String, List<BasicVO>>();//Map<casteName,CasteDetails>
				List<Long> panchayatIds = new ArrayList<Long>();
				List<Long> boothIds = new ArrayList<Long>();
				List<Long> tehsilIds = new ArrayList<Long>();
				List<BasicVO> basicVOListForPanchayat = null;
				List<BasicVO> basicVOListForBooth =null;
				List<SelectOptionVO> SelectOptionVOList = null;
				Map<Long,String> panchayatNamesMap = new HashMap<Long, String>();//Map<panchayatId,Panchayat nane>
				Map<Long,String> boothNamesMap = new HashMap<Long, String>();//Map<boothId,boothName>
				Map<Long,String> tehsilNamesMap = new HashMap<Long, String>();//Map<panchayatId,tehsilName>
				List<YouthLeaderSelectionVO> panchayatTopCasteList = new ArrayList<YouthLeaderSelectionVO>();
				List<YouthLeaderSelectionVO> boothTopCasteList = new ArrayList<YouthLeaderSelectionVO>();
				publicationId = publicationDateDAO.getLatestPublicationId();
				List<Object[]> tehsilList = boothDAO.getTehsildByConstituency(constituencyId,publicationId);
				if(tehsilList != null && tehsilList.size() > 0)
				{
					SelectOptionVOList = new ArrayList<SelectOptionVO>();
					for (Object[] parms : tehsilList) {
						SelectOptionVO selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId((Long)parms[0]);
						selectOptionVO.setName(parms[1].toString());
						SelectOptionVOList.add(selectOptionVO);
					}
				}
				if(SelectOptionVOList != null && SelectOptionVOList.size() > 0)
				{
					for (SelectOptionVO selOptionVO : SelectOptionVOList) {
						Long tehId = selOptionVO.getId();
						tehsilIds.add(tehId);
						//tehsilNamesMap.put(tehId, selOptionVO.getName());
					}
				}
				if(tehsilIds != null && tehsilIds.size() > 0)
				{
					List<Object[]> panchayatsList= panchayatDAO.getPanchaytsForConstituencyList(tehsilIds);
					if(panchayatsList != null && panchayatsList.size() > 0)
					{
						SelectOptionVOList = new ArrayList<SelectOptionVO>();
						for (Object[] parms : panchayatsList) {
							SelectOptionVO selectOptionVO = new SelectOptionVO();
							selectOptionVO.setId((Long)parms[0]);
							selectOptionVO.setName(parms[1].toString());
							selectOptionVO.setLocation(parms[2].toString());
							SelectOptionVOList.add(selectOptionVO);
						}
					}
					if(SelectOptionVOList != null && SelectOptionVOList.size() > 0)
					{
						for (SelectOptionVO selOptionVO : SelectOptionVOList) {
							Long panchayatId = selOptionVO.getId();
							panchayatIds.add(panchayatId);
							panchayatNamesMap.put(panchayatId, selOptionVO.getName());
							tehsilNamesMap.put(panchayatId, selOptionVO.getLocation());
						}
					}
					if(panchayatIds != null && panchayatIds.size() > 0)
					{
						List<Object[]> panchaytVotersCount = voterCastBasicInfoDAO.getToatlVotersForSelectedLevl(panchayatIds,userid,publicationId,3l,constituencyId);
						if(panchaytVotersCount != null && panchaytVotersCount.size() > 0)
						{
							for (Object[] parms : panchaytVotersCount) {
								Long total = ((Long)parms[1] + (Long)parms[2]);
								totalVotersInPanchayat.put((Long)parms[0], total);
							}
						}
						List<Object[]> panchayatWisecasteDetails = voterCastInfoDAO.getTopCasteFoeSelctedLevel(panchayatIds,3l,publicationId,userid);
						if(panchayatWisecasteDetails != null && panchayatWisecasteDetails.size() > 0)
						{
							for (Object[] parms : panchayatWisecasteDetails) {
								BasicVO basicVO = new BasicVO();
								basicVO.setId((Long)parms[0]);
								basicVO.setCount((Long)parms[2]);
								basicVO.setName(parms[1].toString());
								basicVO.setPerc((Double)parms[3]);
								
								basicVOListForPanchayat = panchayatCasteMap.get((Long)parms[0]);
								if(basicVOListForPanchayat == null)
								{
									basicVOListForPanchayat = new ArrayList<BasicVO>();
									basicVOListForPanchayat.add(basicVO);
									panchayatCasteMap.put((Long)parms[0], basicVOListForPanchayat);
								}
								else
								{
									basicVOListForPanchayat.add(basicVO);
								}
							}
							
						}
						for (Long panchayatId : panchayatIds) {
							 List<Object[]> boothsList= boothDAO.getBoothsByPanchayat(panchayatId,publicationId);
							 if(boothsList != null && boothsList.size() > 0)
							 {
								 SelectOptionVOList = new ArrayList<SelectOptionVO>();
								 for (Object[] parms : boothsList) {
									SelectOptionVO selectOptionVO = new SelectOptionVO();
									selectOptionVO.setId((Long)parms[0]);
									selectOptionVO.setName(parms[1].toString());
									SelectOptionVOList.add(selectOptionVO);
								}
							 }
							 if(SelectOptionVOList != null && SelectOptionVOList.size() > 0)
							 {
								 for (SelectOptionVO seleVo : SelectOptionVOList) {
									 Long boothId = seleVo.getId();
									 boothIds.add(boothId);
									 boothNamesMap.put(boothId, seleVo.getName());
								}
								 
							 }
							if(boothIds != null && boothIds.size() > 0)
							{
								List<Object[]> boothWiseCount = boothPublicationVoterDAO.getTotalVotersByBooths(boothIds);
								if(boothWiseCount != null && boothWiseCount.size() > 0)
								{
									for (Object[] parms : boothWiseCount) {
										totalVotersInBooth.put((Long)parms[0], (Long)parms[1]);
									}
								}
								List<Object[]> boothWiseCasteDetails = userVoterDetailsDAO.getCasteDetailsOfVoterByBooths(boothIds,publicationId,userid);
								if(boothWiseCasteDetails != null && boothWiseCasteDetails.size() > 0)
								{
									for (Object[] parms : boothWiseCasteDetails) {
										BasicVO basicVO = new BasicVO();
										basicVO.setId((Long)parms[2]);
										basicVO.setCount((Long)parms[1]);
										basicVO.setName(parms[0].toString());
										Long totalVoter = totalVotersInBooth.get((Long)parms[2]);
										basicVO.setPerc(Double.valueOf(df.format((Long)parms[1]*100/totalVoter.floatValue())));
										basicVOListForBooth = boothCasteMap.get((Long)parms[2]);
										if(basicVOListForBooth == null)
										{
											basicVOListForBooth = new ArrayList<BasicVO>();
											basicVOListForBooth.add(basicVO);
											boothCasteMap.put((Long)parms[2], basicVOListForBooth);
										}
										else
										{
											basicVOListForBooth.add(basicVO);
										}
									}
									
								}
							}
							panchayatDataMap.put(panchayatId, boothCasteMap);
						}
						
					}
					
				}
				if(panchayatCasteMap != null && panchayatCasteMap.size() > 0)
				{
					Set<Long> panchayatIdsSet = panchayatCasteMap.keySet();
					if(panchayatIdsSet != null && panchayatIdsSet.size() > 0)
					{
						for (Long panchayatId : panchayatIdsSet) {
							List<BasicVO> panchayatCasteList = panchayatCasteMap.get(panchayatId);
							processBasicVO(panchayatId,panchayatCasteList,panchayatCasteMap);
						}
					}
				}
				if(boothCasteMap != null && boothCasteMap.size() > 0)
				{
					Set<Long> boothIdsSet = boothCasteMap.keySet();
					if(boothIdsSet != null && boothIdsSet.size() > 0)
					{
						for (Long boothId : boothIdsSet) {
							List<BasicVO> boothCasteList = boothCasteMap.get(boothId);
							processBasicVO(boothId,boothCasteList,boothCasteMap);
						}
					}
				}
				if(panchayatDataMap != null && panchayatDataMap.size() >0)
				{
					Set<Long> panIds = panchayatDataMap.keySet();
					if(panIds != null && panIds.size() > 0)
					{
						for (Long panchayatId : panIds)
						{
							YouthLeaderSelectionVO youthLeaderSelectionVO = new YouthLeaderSelectionVO();
							youthLeaderSelectionVO.setMandalName(tehsilNamesMap.get(panchayatId));
							youthLeaderSelectionVO.setPanchayatName(panchayatNamesMap.get(panchayatId));
							youthLeaderSelectionVO.setPanchayatTotalVoters(totalVotersInPanchayat.get(panchayatId));
							List<BasicVO> panCasteList = panchayatCasteMap.get(panchayatId);
							if(panCasteList != null && panCasteList.size() > 0)
							{
								for (BasicVO basicVO2 : panCasteList) {
									YouthLeaderSelectionVO panchayatLeaderSelectionVO = new YouthLeaderSelectionVO();
									panchayatLeaderSelectionVO.setCasteName(basicVO2.getName());
									panchayatLeaderSelectionVO.setCasteVoters(basicVO2.getCount());
									panchayatLeaderSelectionVO.setCasteVotersPerc(basicVO2.getPerc());
									panchayatTopCasteList.add(panchayatLeaderSelectionVO);
								}
								
							}
							boothCasteMap = panchayatDataMap.get(panchayatId);
							if(boothCasteMap != null && boothCasteMap.size() > 0)
							{
								Set<Long> booths = boothCasteMap.keySet();
								if(booths != null && booths.size() > 0)
								{
									for (Long boothId : booths) {
										List<BasicVO> boothCasteList = boothCasteMap.get(boothId);
										if(boothCasteList != null && boothCasteList.size() > 0)
										{
											for (BasicVO basicVO : boothCasteList) {
												YouthLeaderSelectionVO boothLeaderSelectionVO = new YouthLeaderSelectionVO();
												boothLeaderSelectionVO.setBoothName(boothNamesMap.get(boothId));
												boothLeaderSelectionVO.setBoothTotalVoters(totalVotersInBooth.get(boothId));
												boothLeaderSelectionVO.setCasteName(basicVO.getName());
												boothLeaderSelectionVO.setCasteVoters(basicVO.getCount());
												boothLeaderSelectionVO.setCasteVotersPerc(basicVO.getPerc());
												boothTopCasteList.add(boothLeaderSelectionVO);
											}
										}
										
									}
								}
								
							}
							youthLeaderSelectionVO.setPanchayatLevelLeadersList(panchayatTopCasteList);
							youthLeaderSelectionVO.setBoothLevelLeadersList(boothTopCasteList);
							returnList.add(youthLeaderSelectionVO);
						}
					}
				}
		 }
		 catch (Exception e) {
				LOG.error(" ExceptionOccured in findingBoothInchargesForBoothLevel() method, Exception - "+e);
			}
				
		 return returnList;
	 }
	 
	 public List<BasicVO> processBasicVO(Long id,List<BasicVO> casteList ,Map<Long,List<BasicVO>> casteMap)
	 {
		 List<BasicVO> returnList = new ArrayList<BasicVO>();
		 int count = 0;
		 if(casteList != null && casteList.size() > 0)
		 {
			 for (BasicVO basicVO : casteList) {
				 if(IConstants.MAX_LEVEL > count)
					{
						BasicVO basicVO1 = new BasicVO();
						basicVO1.setId(basicVO.getId());
						basicVO1.setCount(basicVO.getCount());
						basicVO1.setName(basicVO.getName());
						
						basicVO1.setPerc(basicVO.getPerc());
						returnList.add(basicVO1);
						
					}
					else
					{
						break;
					}
					count ++;
			}
		 }
		 casteMap.put(id, returnList);
		 return returnList;
	 }*/
	 public List<PartyPositionVO> getMoreVotersAddedLocDetailsWherePartyIsPoor(List<PartyPositionVO> partyPositions){
		 List<PartyPositionVO> returnVal = new ArrayList<PartyPositionVO>();
		 Map<Long,PartyPositionVO> panchatayats = new HashMap<Long,PartyPositionVO>();
		 Map<Long,PartyPositionVO> suggestions = new HashMap<Long,PartyPositionVO>();
		 for(PartyPositionVO positionVo:partyPositions){
			 if(positionVo.getMinValue() < 0 && positionVo.getPartyPositionVOList() != null && positionVo.getPartyPositionVOList().size() >0){
				 returnVal.add(positionVo);
				 for(PartyPositionVO panchayat:positionVo.getPartyPositionVOList()){
					 panchatayats.put(panchayat.getId(), panchayat);
					 suggestions.put(panchayat.getId(), positionVo);
				 }
			 }
		 }
		 getAddedVoterInfo(panchatayats,suggestions);
		 return returnVal;
	 }
	 
	 public void getAddedVoterInfo(Map<Long,PartyPositionVO> panchatayats,Map<Long,PartyPositionVO> suggestions)
	 {
		 PartyPositionVO vo = null;
		try{
			
				Long publicationId = publicationDateDAO.getLatestPublicationId();
				List<Object[]> deletedVoters = voterModificationInfoDAO.getAddedVotersByPanchayats(new ArrayList<Long>(panchatayats.keySet()),publicationId);
				if(deletedVoters != null && deletedVoters.size() > 0)
				{
					for(Object[] params : deletedVoters)
					{
					  if(params[0] != null && ((Long)params[0]).longValue() >= IConstants.MIN_ADDED_VOTERS){
						vo = panchatayats.get((Long)params[1]);
						vo.setAddedVotersCount((Long)params[0]);
						PartyPositionVO suggestion = suggestions.get((Long)params[1]);
						if(suggestion != null){
							suggestion.setAddedVotersPresent(true);
						}
					  }
					}
				}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	 }
		
	 public List<PartyPositionVO> getSuggestiveLocationsForAParty(List<PartyPositionVO> partyPositions){
		 
		   /* Formula used
		    List<String> ids = new ArrayList<String>();
			ids.add("WOREST");
			ids.add("VERY POOR");
			ids.add("POOR");
			ids.add("OK");
			ids.add("STRONG");
			ids.add("VERY STRONG");
		    int z = 1;
		     for(z = 1;z<ids.size();z++){
			  for(int j = 0;j<z;j++){//2009
					for(int i = ids.size()-z+j;i>=ids.size()-z+j;i--)
					{//2004
						System.out.println(ids.get(j)+","+ids.get(i)+","+z);
					}
				}
			  }
		    z = z-1;
		    System.out.println("-----------------");
		    for(int y = 2;y<=ids.size()-1;y++){
			  for(int j = y-2;j<y;j++){
				  z++;
				  System.out.println(ids.get(y-1)+","+ids.get(j)+","+z);
					for(int i = j,l=y;i>0&&l<ids.size()-1;i--,l++)
					{
						System.out.println(ids.get(l)+","+ids.get(i-1)+","+z);
					}
				}
			  } 
		    z = z+1;
		    System.out.println("-----------------");
		    
		    for(int last=0;last < ids.size() ; last++ ){
		    	System.out.println(ids.get(ids.size()-1)+","+ids.get(last)+","+z);
		    }
		    System.out.println(ids.get(0)+","+ids.get(0)+","+z);
		    */
		 List<PartyPositionVO> returnValues = new ArrayList<PartyPositionVO>(); 
	    try{
		 if(partyPositions.size() == 2){
			 List<PartyPositionVO> latestElec =  partyPositions.get(0).getPartyPositionVOList();
			 List<PartyPositionVO> prevElec =  partyPositions.get(1).getPartyPositionVOList();
			 Collections.reverse(latestElec);
			 Collections.reverse(prevElec);
			 int z = 1;
			 for(z = 1;z<latestElec.size();z++){
				  for(int j = 0;j<z;j++){//2009
					  List<PartyPositionVO> panchayaties = latestElec.get(j).getPartyPositionVOList();
					  if(panchayaties != null && panchayaties.size() >0){	
						for(int i = prevElec.size()-z+j;i>=prevElec.size()-z+j;i--)
						{//2004
							List<PartyPositionVO> prevPanchayaties = prevElec.get(i).getPartyPositionVOList();
							if(prevPanchayaties != null && prevPanchayaties.size() >0){	
								populateMachedValues(panchayaties,prevPanchayaties,returnValues,z);
							}
						}
					  }
					}
				  }
			 z = z-1;
			    for(int y = 2;y<=latestElec.size()-1;y++){
				  for(int j = y-2;j<y;j++){
					  z++;
					  List<PartyPositionVO> latestPanchayaties = latestElec.get(y-1).getPartyPositionVOList();
					  List<PartyPositionVO> prevPanchayaties = prevElec.get(j).getPartyPositionVOList();
					  populateMachedValues(latestPanchayaties,prevPanchayaties,returnValues,z);
						for(int i = j,l=y;i>0&&l<latestElec.size()-1;i--,l++)
						{   latestPanchayaties = latestElec.get(l).getPartyPositionVOList();
						    prevPanchayaties = prevElec.get(i-1).getPartyPositionVOList();
						    populateMachedValues(latestPanchayaties,prevPanchayaties,returnValues,z);
						}
					}
				  } 
			    z++;
			    for(int last=0;last < latestElec.size() ; last++ ){
			    	List<PartyPositionVO> latestPanchayaties = latestElec.get(latestElec.size()-1).getPartyPositionVOList();
			    	List<PartyPositionVO> prevPanchayaties = prevElec.get(last).getPartyPositionVOList();
				    populateMachedValues(latestPanchayaties,prevPanchayaties,returnValues,z);
			    }
			    populateMachedValues(latestElec.get(0).getPartyPositionVOList(),prevElec.get(0).getPartyPositionVOList(),returnValues,z);
			     Collections.reverse(latestElec);
				 Collections.reverse(prevElec);
		 }
	    }catch(Exception e){
		  
	    }
	    return returnValues;
	 }
	 
	 public void populateMachedValues(List<PartyPositionVO> panchayaties,List<PartyPositionVO> prevPanchayaties,List<PartyPositionVO> returnValues,int priorityOrder){
		if(prevPanchayaties != null && prevPanchayaties.size() > 0){
			 for(PartyPositionVO prev:prevPanchayaties){
				  if(panchayaties != null && panchayaties.size() > 0){ 
					    for(PartyPositionVO current:panchayaties){
						   if(current.getId().longValue() == prev.getId().longValue()){
							   current.setPriorityOrder(priorityOrder);
							  returnValues.add(current);
							}
						}
				  }
			 }
	    }
	 }
	 
	 	
		
		 public List<SelectOptionVO> getUserAssignedVotersCasteDetailsByConstId(Long constituencyId,Long userId,Long publicationId){
			 
			 List<SelectOptionVO> casteList = null;
			 List castes;
			 List<Long> constituencyIds = new ArrayList<Long>();
			 constituencyIds.add(constituencyId);
			 if(publicationId == null || publicationId.equals(0l))
			  publicationId = getLatestPublicationByConstituency(constituencyId);
			// Long publicationId = publicationDateDAO.getLatestPublicationId();
			 try{
				 if(constituencyId !=null){
					 casteList = new ArrayList<SelectOptionVO>();
					//castes = userVoterDetailsDAO.getUserAssignedVotersCastesByCosntiId(constituencyId,userId);
					 castes = voterCastInfoDAO.getCastAndPartyForSelectedLevel(userId,1l,constituencyIds,publicationId);
					 if(castes != null && castes.size() >0){
						 for (Object param : castes) {
							 Object[] caste = (Object[]) param;
							casteList.add(new SelectOptionVO((Long) caste[0],caste[1].toString()));
						}
					 }
				 }
				return casteList;	 
			 }catch(Exception e){
				 e.printStackTrace();
				 return null;
			 } 
		 }
		 /**
		  * This method will get  the election results by panchayat in a constituency for 
		  * present and previous elections
		  * @param Long constituencyId 
		  * @return Map<String ,Map<String,PartyImpactVO>>
		  */
		 public Map<String ,Map<String,PartyImpactVO>> getElectionResultsForSelectedElectionsForAConsttituency(Long constituencyId,String partyName,Long publicationId)
		 {	
			 
			 LOG.debug("Entered into the getElectionResultsForSelectedElectionsForAConsttituency service method");
			 
			Map<String ,Map<String,PartyImpactVO>> resultMap = new LinkedHashMap<String, Map<String,PartyImpactVO>>();

			Set<String> totalParties = new HashSet<String>();
			List<Object[]> panchayatResultsList = new ArrayList<Object[]>();

			 try
			 {	
				 //Here we are considering present and previous assembly elections
				 List<Long> elections = new ArrayList<Long>();
				 elections.add(Long.parseLong(IConstants.PREVIOUS_ASSEMBLY_ELECTION_ID));
				 elections.add(Long.parseLong(IConstants.PRESENT_ASSEMBLY_ELECTION_ID));
				 
				 List<Long> tehsilIds = new ArrayList<Long>();			
				 
				List<Object[]> tehsilsList = delimitationConstituencyMandalDAO
						.getMandalIdsByConstituencyId(constituencyId,
								Long.parseLong(IConstants.PRESENT_ELECTION_YEAR));
				
				 for(Object[] obj:tehsilsList)
					 tehsilIds.add((Long)obj[0]);

				 
				  getAllPanchayatWiseVotersDetailsForPartyAndElectionWise(constituencyId,tehsilIds,elections,panchayatResultsList);
				  
				  //panchayatResultsList contains the details in the form of
				  
				  // 0 - votes earned , 1 - panchayat-id , 2 - election-id , 3 - party-id , 4 - party name , 5 - panchayat- name
				 
				 Map<String , Long> votersCountMap = new HashMap<String, Long>();

				 //this method will set all the voter details 
				setAlltheVotersDetaiulsForPresentAndPreviousElections(resultMap,panchayatResultsList,totalParties ,votersCountMap,publicationId);
				 
				List<String> partiesList = new ArrayList<String>(totalParties);
				Collections.sort(partiesList);
				
				//this method will set all the voters details in percent
				processTheResultsToConvertFromVotesToPercent(resultMap , votersCountMap,partiesList);
				
				//this method will calculate and set the difference in voters percent in present and previous elections
				calculateTheDifferenceBetweenpresentAndPreviousElection(resultMap ,partiesList,partyName);
			
				
		 }catch(Exception e)
		 {
			 e.printStackTrace();		 
			 LOG.error("Exception raised getElectionResultsForSelectedElectionsForAConsttituency service method");
			 return resultMap;
		  }
			 return resultMap;
		 }
		 
		 /**
		  * This method will get all parties gained votes details for a constituency by panchayat
		  * @param tehsilIds
		  * @param elections
		  * @param panchayatResultsList
		  */
		public void getAllPanchayatWiseVotersDetailsForPartyAndElectionWise(
				Long constituencyId,List<Long> tehsilIds, List<Long> elections,
				List<Object[]> panchayatResultsList)	 
		{
			LOG.debug("Entered into getAllPanchayatWiseVotersDetailsForPartyAndElectionWise service method");
			
			try
			{
			
			 for(Long electionId:elections)			 
				 for(Long tehsilId:tehsilIds)
				 {
			 
				    List<Object[]> panchayatBoothsDetails = hamletBoothElectionDAO.getPanchayatWiseBoothDetails(tehsilId , electionId);
				 			 
				    Map<Long , List<Long>> map = new HashMap<Long, List<Long>>();//contains panchayatId as key and booths in that panchayat as value
				 
					 for(Object[] obj:panchayatBoothsDetails)
					 {
						 List<Long> booths = null;
						 if(map.get((Long)obj[2]) != null)
						 {
							 booths = map.get((Long)obj[2]);
							 booths.add((Long)obj[6]);
							 
						 }else
						 {
							 booths = new ArrayList<Long>();
							 booths.add((Long)obj[6]);
							 map.put((Long)obj[2], booths);						 
						 }				 
					 }
					 
					 for(Entry<Long,List<Long>> entryForBooths:map.entrySet())
					 {
						List<Object[]> list = candidateBoothResultDAO
								.findBoothResultsForBoothsAndElection(entryForBooths.getValue(),
										electionId);
						
						for(Object[] obj:list)
						{
							Object[] newObj = new Object[7]; 
							newObj[0] = obj[2];                    // no of voters
							newObj[1] = entryForBooths.getKey();   // panchayat id
							newObj[2] = electionId;                //election id
							newObj[3] = obj[0];                    //party id  
							newObj[4] = obj[1];                    //party name
							
							newObj[5] = panchayatDAO.getPanchayatNameById(entryForBooths.getKey()); // panchayat name
							newObj[6] = 3l;							//report level for panchayat
							panchayatResultsList.add(newObj);
							
						}
						
					 }
				 }
				
			 //for municipalities
			 for(Long electionId:elections){
					List<Long> staticParties = partyDAO.getStaticParties(IConstants.STATIC_PARTIES + ",'IND'");
					List<Object[]> list2 = assemblyLocalElectionBodyDAO.getAssLocalEleBodyIdAndLocalEleBodyIdByConstituency(constituencyId);
					Long localEleBodyId = 0l;
					Long assLocalEleBodyId = 0l;
					for(Object[] param:list2){
						localEleBodyId = (Long)param[0];
						assLocalEleBodyId = (Long)param[1];
					}
					List<Long> boothIdsList = boothConstituencyElectionDAO.getBoothIdsByLocalEleBody(localEleBodyId, electionId);
					if(boothIdsList.size()>0){
						List<Object[]> list = candidateBoothResultDAO.findBoothResultsForBoothsAndElectionAndAllParties(boothIdsList,electionId,staticParties);
						for(Object[] obj:list)
						{
							Object[] newObj1 = new Object[7];
							newObj1[0] = obj[2];                     	// no of voters
							newObj1[1] = localEleBodyId;   				// panchayat id
							newObj1[2] = electionId;  					//election id
							newObj1[3] = obj[0];                    	//party id  
							newObj1[4] = obj[1];                    	//party name
							newObj1[5] = (assemblyLocalElectionBodyDAO.getLocalElectionBodyName(assLocalEleBodyId)).get(0);	//muncipality name
							newObj1[6] = 5l;					//report level for municipality
							panchayatResultsList.add(newObj1);
						}
					}
			 }
			}catch(Exception e)
			{
				e.printStackTrace();
				LOG.debug("Exception raised in  getAllPanchayatWiseVotersDetailsForPartyAndElectionWise service method");

			}
		 }
		
		/**
		 * This method will set all the voter details of panchayitis in a constituency
		 * @param resultMap
		 * @param panchayatResultsList
		 * @param totalParties
		 * @param votersCountMap
		 */
		public void setAlltheVotersDetaiulsForPresentAndPreviousElections(Map<String ,Map<String,PartyImpactVO>> resultMap,List<Object[]> panchayatResultsList,Set<String> totalParties , Map<String , Long> votersCountMap , Long publicationId)
		{
			LOG.debug("Entered into the setAlltheVotersDetaiulsForPresentAndPreviousElections service method");
			try
			{
				List<BasicVO> panchayatList = null;
				for(Object[] obj:panchayatResultsList){
					List<Object[]> panchayatVoterCount = null;
					totalParties.add(obj[4].toString());
					
					if(votersCountMap.get(obj[5].toString()+obj[2].toString()) != null)
					{
						Long voters = votersCountMap.get(obj[5].toString()+obj[2].toString()) ;
						votersCountMap.put(obj[5].toString()+obj[2].toString(), voters + (Long)obj[0]);
						
					}else{
						votersCountMap.put(obj[5].toString()+obj[2].toString() , (Long)obj[0]);
						
						//For Panchayat highest castes
						if((Long)obj[6]==5l)
							panchayatVoterCount = voterCastInfoDAO.getTopThreeCasteForPanchayat((Long)obj[1],(Long)obj[6],publicationId,1l);
						else 
							panchayatVoterCount = voterCastInfoDAO.getTopThreeCasteForPanchayat((Long)obj[1],(Long)obj[6],publicationId,1l);
						
						 int j=0;
						 BasicVO basicVO =null;
						 panchayatList = new ArrayList<BasicVO>();
						 for(Object[] panchayat:panchayatVoterCount){
							 j++;
							 basicVO = new BasicVO();
							 
							 basicVO.setCount((Long)panchayat[1]);
							 basicVO.setPersent(panchayat[2].toString());
							 basicVO.setCasteName(panchayat[0].toString());
							 
							 if(j <= 3)
								 panchayatList.add(basicVO);
						 }
					}
					
					Map<String,PartyImpactVO> map = null;
					if(resultMap.get(obj[5].toString()) != null)
					{
						
						map = resultMap.get(obj[5].toString());
		                  
						if(map.get(obj[4].toString()) != null)
						{
							PartyImpactVO vo = map.get(obj[4].toString());
							
							if(obj[2].toString().equalsIgnoreCase(IConstants.PREVIOUS_ASSEMBLY_ELECTION_ID))						
									vo.setPreviousElectionVotesPercent(obj[0].toString());							
							else 
								vo.setPresentElectionVotesPercent(obj[0].toString());
							
						}else
						{
							PartyImpactVO vo = new PartyImpactVO();
							vo.setPartyName(obj[4].toString());
							vo.setPanchayatList(panchayatList);
							
							if(obj[2].toString().equalsIgnoreCase(IConstants.PREVIOUS_ASSEMBLY_ELECTION_ID))
								vo.setPreviousElectionVotesPercent(obj[0].toString());
							else 
								vo.setPresentElectionVotesPercent(obj[0].toString());
							
							map.put(obj[4].toString(), vo);
						}
						
					}else
					{
						map = new HashMap<String, PartyImpactVO>();
						
						PartyImpactVO vo = new PartyImpactVO();
						vo.setPartyName(obj[4].toString());
						vo.setPanchayatList(panchayatList);
						
						if(obj[2].toString().equalsIgnoreCase(IConstants.PREVIOUS_ASSEMBLY_ELECTION_ID))
							vo.setPreviousElectionVotesPercent(obj[0].toString());
						else 
							vo.setPresentElectionVotesPercent(obj[0].toString());
						map.put(obj[4].toString(),vo );
						
						resultMap.put(obj[5].toString(), map);					
					}
					
					resultMap.put(obj[5].toString(), map);
				}
				
			}catch(Exception e)
			{
				e.printStackTrace();
				LOG.debug("Exception raised in   setAlltheVotersDetaiulsForPresentAndPreviousElections service method");
				
			}
		}
		/**
		 * This method will set all the details to the as percent from total voters
		 * @param resultMap
		 * @param votersCountMap
		 * @param partiesList
		 */
		public void processTheResultsToConvertFromVotesToPercent(Map<String ,Map<String,PartyImpactVO>> resultMap, Map<String,Long> votersCountMap, List<String> partiesList)
		{
			LOG.debug("Entered into processTheResultsToConvertFromVotesToPercent service method");
			try
			{
				for(Entry<String ,Map<String,PartyImpactVO>> entry:resultMap.entrySet())
				{
					Map<String,PartyImpactVO> map = entry.getValue();
					
					for(Entry<String,PartyImpactVO> party:map.entrySet())
					{
						//if party participated in  previous and present elections
						if(party.getValue().getPreviousElectionVotesPercent() != null && party.getValue().getPresentElectionVotesPercent() != null)
						{		
							 Long votersInPanchayat = votersCountMap.get(entry.getKey()+IConstants.PREVIOUS_ASSEMBLY_ELECTION_ID);
							 Float votersInPanchayatInFloat = votersInPanchayat.floatValue();
							
							 Long previousVotes=  Long.parseLong(party.getValue().getPreviousElectionVotesPercent());
							 Float previousVotesInFloat  = previousVotes.floatValue();
							 
							 party.getValue().setPreviousElectionVotesPercent(roundTo2DigitsFloatValue((previousVotesInFloat/votersInPanchayatInFloat)*100));
			
								Long votersInPanchayat1 = votersCountMap.get(entry.getKey()+IConstants.PRESENT_ASSEMBLY_ELECTION_ID);
								Float votersInPanchayatInFloat1 = votersInPanchayat1.floatValue();
							 
							 Long presentVotes=  Long.parseLong(party.getValue().getPresentElectionVotesPercent());
							 Float presentVotesInFloat  = presentVotes.floatValue();
							 
							 party.getValue().setPresentElectionVotesPercent(roundTo2DigitsFloatValue((presentVotesInFloat/votersInPanchayatInFloat1)*100));
							
						}//if party participated in  previous election only
						else if(party.getValue().getPreviousElectionVotesPercent() != null)
						{
							Long votersInPanchayat = votersCountMap.get(entry.getKey()+IConstants.PREVIOUS_ASSEMBLY_ELECTION_ID);
							Float votersInPanchayatInFloat = votersInPanchayat.floatValue();
			
							 Long previousVotes=  Long.parseLong(party.getValue().getPreviousElectionVotesPercent());
							 Float previousVotesInFloat  = previousVotes.floatValue();
							 
							 party.getValue().setPreviousElectionVotesPercent(roundTo2DigitsFloatValue((previousVotesInFloat/votersInPanchayatInFloat)*100));
							
						}//if party participated in  present election only
						else if(party.getValue().getPresentElectionVotesPercent() != null)
						{
							Long votersInPanchayat = votersCountMap.get(entry.getKey()+IConstants.PRESENT_ASSEMBLY_ELECTION_ID);
							Float votersInPanchayatInFloat = votersInPanchayat.floatValue();
							 
							 Long presentVotes=  Long.parseLong(party.getValue().getPresentElectionVotesPercent());
							 Float presentVotesInFloat  = presentVotes.floatValue();
							 
							 party.getValue().setPresentElectionVotesPercent(roundTo2DigitsFloatValue((presentVotesInFloat/votersInPanchayatInFloat)*100));
							
						}
							
						party.getValue().setConsiderableParties(partiesList);
					}
				}
			}catch(Exception e)
			{
				e.printStackTrace();
				LOG.error("Exception raised in processTheResultsToConvertFromVotesToPercent service method");
			}
		}
		
		/**
		 * This method will calculate and set the difference in voters percent
		 * between present and previous elections
		 * @param resultMap
		 * @param partiesList
		 */
		public void calculateTheDifferenceBetweenpresentAndPreviousElection(Map<String ,Map<String,PartyImpactVO>> resultMap, List<String> partiesList,String partyName)
		{
			LOG.debug("Entered into the calculateTheDifferenceBetweenpresentAndPreviousElection service method");
			try
			{
				List<String> toRemove = new ArrayList<String>();
				for(Entry<String ,Map<String,PartyImpactVO>> entry:resultMap.entrySet())
				{
					Float selPartyDiff = 0.0f;
					Float prpPartyDiff = 0.0f;
					PartyImpactVO prpParty = null;
					Map<String,PartyImpactVO> map = entry.getValue();
					
					for(Entry<String,PartyImpactVO> party:map.entrySet()){
						//if party participated in  present election only
						if(party.getValue().getPreviousElectionVotesPercent() == null && party.getValue().getPresentElectionVotesPercent() != null)
						{						
							party.getValue().setPreviousElectionVotesPercent("--");
							party.getValue().setDifference(party.getValue().getPresentElectionVotesPercent());
							
						}//if party participated in  previous election only
						else if(party.getValue().getPreviousElectionVotesPercent() != null && party.getValue().getPresentElectionVotesPercent() == null)
						{
							party.getValue().setPresentElectionVotesPercent("--");
							party.getValue().setDifference("--");
						}//if party participated in  previous and present elections
						else{
							
							String present = "0";
							String pre = "0"; 
							if(!party.getValue().getPresentElectionVotesPercent().equalsIgnoreCase("--"))
								present = party.getValue().getPresentElectionVotesPercent();
						    if(!party.getValue().getPreviousElectionVotesPercent().equalsIgnoreCase("--"))
						    	pre = party.getValue().getPreviousElectionVotesPercent();
						    
							Float diff = Float.parseFloat(present) - Float.parseFloat(pre);
							party.getValue().setDifference(diff.toString());	
						}
						if(party.getKey().equalsIgnoreCase(partyName))
						{
							if(!party.getValue().getDifference().equalsIgnoreCase("--"))
							 selPartyDiff = Float.valueOf(party.getValue().getDifference());
						}
						if(party.getKey().equalsIgnoreCase("PRP"))
						{
							prpParty = party.getValue();
							prpPartyDiff = Float.valueOf(party.getValue().getDifference());
						}
							
						party.getValue().setConsiderableParties(partiesList);
					}
					if(prpParty != null)
					{
						if(selPartyDiff != null && selPartyDiff < 0)
						{
							selPartyDiff = -selPartyDiff;
							Float parVotestoGain = Math.min(selPartyDiff, prpPartyDiff);
							prpParty.setFromPrpVoters(parVotestoGain.toString());
						}
						else
						{
							prpParty.setFromPrpVoters("");
							 toRemove.add(entry.getKey());
						}
					}
					
					
				}

			for (String key: toRemove) {
				resultMap.remove(key);
			}
			
			}catch(Exception e)
			{
				e.printStackTrace();
				LOG.error("Exception raised in  calculateTheDifferenceBetweenpresentAndPreviousElection service method");

				
			}
		}
		 
		 public String roundTo2DigitsFloatValue(Float number){
				
				NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
				f.setMaximumFractionDigits(2);  
				f.setMinimumFractionDigits(2);
				
				return f.format(number);
				
			}
		 
		//All ELection Years
			
			public List<PartyPositionVO> getPartyPerformenceReport1(Long constituencyId,Long partyId,List<Long> electionIds,String tempVar)
			{
				List<PartyPositionVO> resultList = null;
				try{
				List<Long> constituencyIdsList = new ArrayList<Long>(0);
				constituencyIdsList.add(constituencyId);
				List<Long> assemblyEleIdsList = new ArrayList<Long>(0);
				List<Long> mandalIds = new ArrayList<Long>();
				List<Long> localbodyIds = new ArrayList<Long>();
				List<Object[]> electionList = null;
				List<PartyPositionVO> localelectionList = null;
				String tempLocationName = "";
				List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(constituencyId,IConstants.PRESENT_YEAR, null);
				if(mandalsList != null && mandalsList.size() > 0)
				for(SelectOptionVO vo : mandalsList)
				if(vo.getId().toString().substring(0,1).equalsIgnoreCase("2"))
				mandalIds.add(new Long(vo.getId().toString().substring(1)));
				else
					localbodyIds.add((Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(vo.getId().toString().substring(1))).get(0));
				if(electionIds != null && electionIds.size()> 0)
				{
					electionList = new ArrayList<Object[]>();
					for (Long eleId : electionIds) {
						Object[] eleIds = {eleId};
						 electionList.add(eleIds);
					}
				}
				
				Constituency constituency = constituencyDAO.get(constituencyId);
				
				if(electionList != null && electionList.size() > 0)
				{
				   for(Object[] params:electionList)
				   {
					   assemblyEleIdsList.add((Long)params[0]); 
				   } 	
				}
				
				if(assemblyEleIdsList != null && assemblyEleIdsList.size() > 0)
				{
					resultList = new ArrayList<PartyPositionVO>(0);
					  List<SuggestiveRange> suggestiveRangeList = suggestiveRangeDAO.getSuggestiveRangeList();
						  
					  PartyPositionVO partyPositionVO = null;
					  for(Long eleId :assemblyEleIdsList)
					  {
						Election election = electionDAO.get(eleId);
						partyPositionVO = new PartyPositionVO();
						List<PartyPositionVO> rangeList = new ArrayList<PartyPositionVO>();
						List<PartyPositionVO> rangeList1 = new ArrayList<PartyPositionVO>(0);
						
						PartyPositionVO range = null;
						PartyPositionVO newRange = null;
						for(SuggestiveRange suggestiveRange:suggestiveRangeList)
						  {
							range = new PartyPositionVO();
							range.setName(suggestiveRange.getName());
							range.setMinValue(suggestiveRange.getMinValue());
							range.setMaxValue(suggestiveRange.getMaxValue());
							range.setTempVar(suggestiveRange.getColor());							
							newRange = new PartyPositionVO();
							newRange.setName(suggestiveRange.getName());
							newRange.setMinValue(suggestiveRange.getMinValue());
							newRange.setMaxValue(suggestiveRange.getMaxValue());
							newRange.setTempVar(suggestiveRange.getColor());
							rangeList.add(range);
							rangeList1.add(newRange);
							
						  }
						
						partyPositionVO.setPartyPositionVOList(rangeList);
						
						/*if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL_URBAN))
						{
						 if(localbodyIds != null && localbodyIds.size() > 0)
						 {
						  localelectionList = new ArrayList<PartyPositionVO>(0);
						 
						 for(Long localBody : localbodyIds)
						 {
							 String name =localElectionBodyDAO.getLocalElectionBodyName(localBody);
								if(name != null)
								{
							 PartyPositionVO localElection = new PartyPositionVO();
							 localElection.setId(localBody);
							 localElection.setName(name);
							 localElection.setBoothwisePartyPositionVOList(rangeList1);
							 localelectionList.add(localElection);
								}
						 }
						 List<Object[]> boothIdsList = null;
						 
						 if(localbodyIds != null && localbodyIds.size() > 0)
						  boothIdsList = hamletBoothElectionDAO.getBoothsByLocalBodyNElectionId(localbodyIds,eleId);
						 if(boothIdsList != null && boothIdsList.size() > 0)
						 partyPositionVO.setBoothwisePartyPositionVOList(localelectionList);
						 }
						}*/
						
						 if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_URBAN))
						{
							localelectionList = new ArrayList<PartyPositionVO>(0);
							 PartyPositionVO localElection = new PartyPositionVO();
							 localElection.setId(constituencyId);
							 localElection.setName(constituencyDAO.get(constituencyId).getName());
							 localElection.setBoothwisePartyPositionVOList(rangeList1);
							 localelectionList.add(localElection);
						}
						partyPositionVO.setName(election.getElectionYear() != null?election.getElectionYear():" ");
						partyPositionVO.setId(eleId);
						partyPositionVO.setConstituencyId(constituencyId);
						
						if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL))
						{
							List<Long> panchayatIds = null;
							if(mandalIds != null && mandalIds.size() > 0)
							 panchayatIds =hamletBoothElectionDAO.getPanchayatIdsByEleIdAndMandalIdsList(mandalIds,eleId);
							if(panchayatIds != null && panchayatIds.size() > 0)
								getMandalWisePartyPerformanceReport1(constituencyId,eleId, partyPositionVO, partyId,panchayatIds,null);	
							
						}
						else if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_URBAN))
						{    partyPositionVO.setConstituencyType("urban");
							List<Object[]> boothIdsList = null;
							/*if(localbodyIds != null && localbodyIds.size() > 0)
							 boothIdsList = hamletBoothElectionDAO.getBoothsByLocalBodyNElectionId(localbodyIds,eleId);*/
							boothIdsList = boothConstituencyElectionDAO.getBoothIdsByConstituencyIdAndEleId(eleId, constituencyId);
							
							if(boothIdsList != null && boothIdsList.size() > 0)
							{
								partyPositionVO.setBoothwisePartyPositionVOList(localelectionList);
								getPanchayatWisePartyPerformance1(constituencyId,eleId, partyPositionVO, partyId,boothIdsList);
							}
							
						}
						else if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL_URBAN))
						{
							List<Long> panchayatIds = null;
							if(mandalIds != null && mandalIds.size() > 0)
							 panchayatIds =hamletBoothElectionDAO.getPanchayatIdsByEleIdAndMandalIdsList(mandalIds,eleId);
							List<Object[]> boothIdsList = null;
							if(localbodyIds != null && localbodyIds.size() > 0)
							 boothIdsList = hamletBoothElectionDAO.getBoothsByLocalBodyNElectionId(localbodyIds,eleId);
							if(panchayatIds != null && panchayatIds.size() > 0)
								getMandalWisePartyPerformanceReport1(constituencyId,eleId, partyPositionVO, partyId,panchayatIds,boothIdsList);
							
							
							/*if(boothIdsList != null && boothIdsList.size() > 0)
							{
								partyPositionVO.setBoothwisePartyPositionVOList(localelectionList);
								getPanchayatWisePartyPerformance1(constituencyId,eleId, partyPositionVO, partyId,boothIdsList);
							}*/
							
						}
						resultList.add(partyPositionVO);
					  }
				}
				
				if(resultList != null && resultList.size() == 2){
				  List<PartyPositionVO> suggestedLocations = getSuggestiveLocationsForAParty(resultList);
				  resultList.get(0).setSuggestedLocations(suggestedLocations);
				}	

				//For PollingPercentage Panchayats
				/*if(resultList != null && resultList.size() > 0){
					tempLocationName = IConstants.PANCHAYAT;
					getPollingPercentageForALocation(resultList.get(0),tempLocationName,constituencyId);
					List<PartyPositionVO>  panchayatVos = getMoreVotersAddedLocDetailsWherePartyIsPoor(resultList.get(0).getPartyPositionVOList());
					resultList.get(0).setAddedVoterDetails(panchayatVos);
				}*/
				
				//Percentage calculation used to show piechart
				Map<Long,Map<String,Long>> map = new HashMap<Long, Map<String,Long>>(0);//<electionId,Map<strong,totalValidVotes>>
				for(PartyPositionVO partyPositionVO1:resultList)
				{
				   Map<String,Long> map2 = map.get(partyPositionVO1.getId());
				   if(map2 == null)
				   {
					  map2 = new HashMap<String, Long>(0);
					  map.put(partyPositionVO1.getId(), map2);
				   }
				   if(partyPositionVO1.getPartyPositionVOList() != null && partyPositionVO1.getPartyPositionVOList().size() > 0)
				   {
					 for(PartyPositionVO partyPositionVO2:partyPositionVO1.getPartyPositionVOList())
					 {
						 if(partyPositionVO2.getPartyPositionVOList() != null && partyPositionVO2.getPartyPositionVOList().size() > 0)
						 {
							for(PartyPositionVO partyPositionVO3:partyPositionVO2.getPartyPositionVOList())
							{
							  Long total = map2.get(partyPositionVO2.getName());
							   if(total == null)
								 map2.put(partyPositionVO2.getName(), partyPositionVO3.getTotalValidVotes());
							   else
								map2.put(partyPositionVO2.getName(), total+partyPositionVO3.getTotalValidVotes());
							}
						 }
					 }
				   }
				}
				
				for(PartyPositionVO partyPositionVO1:resultList)
				{
				  Long totalVotes = candidateBoothResultDAO.getConstituencyTotalVotes(constituencyId, partyPositionVO1.getId());
				  
				  Map<String, Long> totalVotesMap = map.get(partyPositionVO1.getId());
				  
				  if(partyPositionVO1.getPartyPositionVOList() != null && partyPositionVO1.getPartyPositionVOList().size() > 0)
				   {
					 for(PartyPositionVO partyPositionVO2:partyPositionVO1.getPartyPositionVOList())
					 {
						 if(partyPositionVO2.getPartyPositionVOList() != null && partyPositionVO2.getPartyPositionVOList().size() > 0 && totalVotes != null && totalVotes > 0)
						 {
							  double percentage =  new BigDecimal((totalVotesMap.get(partyPositionVO2.getName())*100.0/totalVotes)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
							  partyPositionVO2.setRangePercentage(percentage);
						 }
					 }
				   }
				}

				 return resultList;
				}catch (Exception e) {
				 e.printStackTrace();
				 LOG.error(" Exception Occured in getPartyPerformenceReportForAllElections() method, Exception-"+e);
				 return resultList;
				}
			}
			
			public void getPanchayatWisePartyPerformance1(Long constituencyId,Long electionId,PartyPositionVO partyPositionVO, Long partyId,List<Object[]> boothIdsList)
			{
				try{
					Map<String,Map<Long,Map<Long,Long>>> resultMap = new HashMap<String, Map<Long,Map<Long,Long>>>(0);//<localbodyName,<boothId,<partyId,votesEarned>>
					Map<String,List<Long>> boothIdMap = new HashMap<String, List<Long>>(0);//<localBodyName,<boothIds>
					
					for(Object[] params: boothIdsList)
					{
							List<Long> boothIds = boothIdMap.get(params[1].toString());
							if(boothIds == null)
							{
							boothIds = new ArrayList<Long>(0);	
							boothIdMap.put(params[1].toString(), boothIds);
							}
							if(!boothIds.contains((Long)params[0]))
							boothIds.add((Long)params[0]);
							
					}
						for(String localbodyName : boothIdMap.keySet())	
						{
						 List<Object[]> list = candidateBoothResultDAO.getVotesEarnedByBoothIdsList( electionId, boothIdMap.get(localbodyName));
						  if(list != null && list.size() > 0)
						  {
							    Map<Long,Map<Long,Long>> boothMap = resultMap.get(localbodyName.toString());	
								 if(boothMap == null)
								 {
									 boothMap = new HashMap<Long, Map<Long,Long>>(0); 
									resultMap.put(localbodyName, boothMap);
									
								 }
								 for(Object[] params:list)
								  {
								Map<Long,Long> partyMap = boothMap.get((Long)params[0]);
								 if(partyMap == null)
								 {
									 partyMap = new HashMap<Long, Long>(0); 
									  boothMap.put((Long)params[0], partyMap);
								  }
								Long votesEarned = partyMap.get(params[1]);
								if(votesEarned == null)
									  partyMap.put((Long)params[1], (Long)params[2]);
								 else
									  partyMap.put((Long)params[1], (Long)params[2]+votesEarned);
								  }
								 
							 
						 }
						}
						
						if(resultMap != null && resultMap.size() > 0)
							//getPartyPerformanceForBooth(partyPositionVO, partyId,boothMap);	
							getPartyPerformanceForLocalBodyBooth(partyPositionVO, partyId,resultMap,electionId);	
				
			
				}catch (Exception e) {
					e.printStackTrace();
					LOG.error("ExceptionOccured in getPanchayatWisePartyPerformance() method, Exception - "+e);
				}
			}
			public PartyPositionVO checkPartyPositionVOExists(String name,List<PartyPositionVO> localbodyList)
			{
				try{
					if(localbodyList == null || localbodyList.size() == 0)
						return null;
					for(PartyPositionVO vo : localbodyList)
						if(vo.getName().equalsIgnoreCase(name))
							return vo;
					return null;
				}
				catch(Exception e)
				{
					return null;	
				}
			}
			public void getPartyPerformanceForLocalBodyBooth(PartyPositionVO partyPositionVO, Long selectedpartyId,Map<String,Map<Long,Map<Long,Long>>> resultMap,Long electionId)
			{
				try{
					Map<Long,Long> boothTotalMap = new HashMap<Long, Long>(0);
					for(String localbodyName : resultMap.keySet())
					{
						Map<Long,Map<Long,Long>> boothMap = resultMap.get(localbodyName);
						 for(Long id:boothMap.keySet())
							boothTotalMap.put(id, boothDAO.getTotalVotesForSelectedBooth(id));
						
					}
							
					
					for(String localbodyName : resultMap.keySet())
					{
				 List<PartyPositionVO> localbodyList = partyPositionVO.getBoothwisePartyPositionVOList();
				 partyPositionVO = checkPartyPositionVOExists(localbodyName,partyPositionVO.getBoothwisePartyPositionVOList());
				 for(PartyPositionVO localbodyVo : localbodyList)
				 {
				 Map<Long,Map<Long,Long>> boothMap = resultMap.get(localbodyName);
				 for(Long id:boothMap.keySet())
				 {
					Map<Long,Long> partyMap = boothMap.get(id);
					Long totalVotes = 0L;
						 
					for(Long partysId:partyMap.keySet())
					  totalVotes += partyMap.get(partysId); 
						 
					Long selectedPartyTotal = partyMap.get(selectedpartyId);
					Long comparePartyTotal = 0L;
						 
					if(selectedPartyTotal == null)
					{
						 AlliancePartyResultsVO alliancePartiesVO = staticDataService.getAlliancePartiesByElectionAndParty(electionId,selectedpartyId);
						  if(alliancePartiesVO == null || alliancePartiesVO.getAllianceParties() == null)
							  selectedPartyTotal = 0L;
						  else
							  for(SelectOptionVO alianceParty:alliancePartiesVO.getAllianceParties())
								  selectedPartyTotal = partyMap.get(alianceParty.getId());
					}
						
					  for(Long partysId:partyMap.keySet())
					  {
					    if(!partysId.equals(selectedpartyId) && comparePartyTotal < partyMap.get(partysId))
						 comparePartyTotal = partyMap.get(partysId);
						  
					  }
				   
				   
				  double selectedPartyTotalPercent =  new BigDecimal((selectedPartyTotal*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			      double comparePartyTotalPercent =  new BigDecimal((comparePartyTotal*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			      
			      //double difference = new BigDecimal(selectedPartyTotalPercent - comparePartyTotalPercent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			      double difference = selectedPartyTotalPercent;
			    	
			      String locationName = "";
			      locationName = boothDAO.getBoothPartNoByBoothId(id);
			  
			    	  List<PartyPositionVO> boothPositionVOList = partyPositionVO.getBoothwisePartyPositionVOList();
			    	  for(PartyPositionVO positionVO :boothPositionVOList)
				      {
				    	if(difference >= positionVO.getMinValue() && difference <= positionVO.getMaxValue())
				    	{
				    	 PartyPositionVO locationVO = null;
				    	
				    	 List<PartyPositionVO> boothwiselocationList = positionVO.getPartyPositionVOList(); 
				    	
				    	 if(boothwiselocationList == null || boothwiselocationList.size() == 0)
				    		 boothwiselocationList = new ArrayList<PartyPositionVO>(0);
				    	 
				    	 locationVO = checkPartyPositionVOExist(id,boothwiselocationList);
				    	 if(locationVO == null)
				    	 {
				    		 locationVO = new PartyPositionVO();
				    		 locationVO.setId(id);
				    		 locationVO.setName(locationName != null?locationName:" ");
				    		 locationVO.setPartyPercentage(selectedPartyTotalPercent);
				    		 locationVO.setTotalValidVotes(totalVotes);
				    		 locationVO.setSelectedPartyTotalVoters(selectedPartyTotal);
				    		 locationVO.setMargin(difference);
				    		 locationVO.setTotalVoters(boothTotalMap.get(id));
				    		 locationVO.setPercentage(new BigDecimal((totalVotes*100.0/boothTotalMap.get(id))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				    		 boothwiselocationList.add(locationVO);
				    		 positionVO.setPartyPositionVOList(boothwiselocationList);
				    		
				    	 }
				    	 
				    	}
				    	
				      }
				 }
			      }
				
				 
					}
			     }catch (Exception e) {
				 e.printStackTrace();
				 LOG.error(" Exception Occured in getPartyPerformanceForSelectedlocation() method, Exception - "+e);
				}
			}
			
			public void getMandalWisePartyPerformanceReport1(Long constituencyId,Long electionId,PartyPositionVO partyPositionVO,Long partyId,List<Long> panchaytIdsList,List<Object[]> localbodybooths)
			{
				try{
				Map<Long,List<Long>> boothIds = new HashMap<Long, List<Long>>();//Map<boothId,List<panchayatId>>
				Map<Long,Map<Long,Long>> resultMap = new HashMap<Long, Map<Long,Long>>(0);//Map<panchayatId,Map<partyId,totalvoters>>
				Map<Long,Map<Long,Long>> resultMap1 = new HashMap<Long,Map<Long,Long>>(0);//Map<localbodyname,Map<partyId,totalvoters>>
				Map<Long,List<Long>> boothIdMap = new HashMap<Long, List<Long>>(0);//<localBodyName,<boothIds>
				//List<Long> boothIds1 = null;
				List<Long> booths = new ArrayList<Long>();
				if(localbodybooths != null && localbodybooths.size() > 0)
				{
				for(Object[] params: localbodybooths)
				{
					List<Long> boothIds1 = boothIdMap.get(params[2]);
						if(boothIds1 == null)
						{
						boothIds1 = new ArrayList<Long>(0);	
						boothIdMap.put((Long)params[2], boothIds1);
						}
						if(!boothIds1.contains((Long)params[0]))
						boothIds1.add((Long)params[0]);
				}
				
				for(Long localbodyId : boothIdMap.keySet())
				{
					booths = boothIdMap.get(localbodyId);
					List<Object[]> partyResult = candidateBoothResultDAO.findBoothResultsForBoothsAndElectionAndAllParties( boothIdMap.get(localbodyId),electionId,null);
					Map<Long,Long> partyMap1 = null;
					for(Object[] params : partyResult)
					{
						partyMap1 = resultMap1.get(localbodyId);
						if(partyMap1 == null)
						{
							partyMap1 = new HashMap<Long, Long>(0);
							resultMap1.put(localbodyId, partyMap1);
						}
						Long votesEarned = partyMap1.get((Long)params[0]);
						if(votesEarned == null)
							partyMap1.put((Long)params[0], (Long)params[2]);	
						else
							partyMap1.put((Long)params[0], votesEarned+(Long)params[2]);	
					}
					
				}
				}
				if(panchaytIdsList != null && panchaytIdsList.size() > 0)
				{
				  List<Object[]> list = hamletBoothElectionDAO.getPanchayatBoothDetailsByPanchayatIdsList(panchaytIdsList, electionId); 
				  if(list != null && list.size() > 0)
				  {
					  for(Object[] params:list)
					  {
						  List<Long> panchayatIds = boothIds.get((Long)params[1]);
						  if(panchayatIds == null)
						  {
						   panchayatIds = new ArrayList<Long>(0);
						   boothIds.put((Long)params[1], panchayatIds);
						  }
						  if(!panchayatIds.contains((Long)params[0]))
						   panchayatIds.add((Long)params[0]);
					  }
					  
					  List<Object[]> resultList = candidateBoothResultDAO.findBoothResultsForMultipleBoothsAndElectionIdForSelElection(boothIds.keySet(), electionId);
					  if(resultList != null && resultList.size() > 0)
					  {
						 for(Object[] params:resultList)
						 {
						   List<Long> panchayatIdsList = boothIds.get((Long)params[0]);
						   if(panchayatIdsList != null && panchayatIdsList.size() > 0)
						   {
							 Map<Long,Long> partyMap = null;//Map<PartyId,totalvotes>
							 for(Long panchayatId :panchayatIdsList)
							 {
								 partyMap = resultMap.get(panchayatId);
								 if(partyMap == null)
								 {
									 partyMap = new HashMap<Long, Long>(0);
									 resultMap.put(panchayatId, partyMap);
								 }
								 Long votesEarned = partyMap.get((Long)params[1]);
								 if(votesEarned == null)
								  partyMap.put((Long)params[1],(Long)params[2]);
								 else
								  partyMap.put((Long)params[1], votesEarned+(Long)params[2]);
							 }
						   }
						   
						 }
					  }
					  
				  }
				}
				AlliancePartyResultsVO alliancePartiesVO = staticDataService.getAlliancePartiesByElectionAndParty(electionId,partyId);
				if(resultMap != null && resultMap.size() > 0)
					getPartyPerformanceForPanchayat1(resultMap,partyPositionVO, partyId,electionId,alliancePartiesVO); 
				if(resultMap1 != null && resultMap1.size() > 0)
				  getPartyPerformanceForLocalBody(partyPositionVO, partyId,resultMap1,boothIdMap,alliancePartiesVO);
				
				
				}catch (Exception e) {
					e.printStackTrace();
					LOG.error(" Exception Occured in getMandalWisePartyPerformanceReport() method, Exception - "+e);
				  }
			}
			public void getPartyPerformanceForPanchayat2(Map<Long,Map<Long,Long>> resultMap,PartyPositionVO partyPositionVO, Long selectedpartyId,Map<Long,Map<Long,Long>> resultMap1,List<Long> localbodyboothIds)
			{
				try{
					
					//resultMap -- Map<panchayatId,Map<partyId,totalvoters>>
					//resultMap1 -- Map<localbodyName,Map<partyId,totalvoters>>
					
					Map<Long,List<Long>> boothIdsMap = new HashMap<Long, List<Long>>(0);//<panchayatId,List<boothIds>>
					Map<Long,Long> panchayatTotalVotersMap = new HashMap<Long, Long>(0);//<locationId,totalVoters>
					Map<Long,List<Long>> localbodyboothIdsMap = new HashMap<Long, List<Long>>(0);//<panchayatId,List<boothIds>>
					Map<Long,Long> localbodyTotalVotersMap = new HashMap<Long, Long>(0);//<locationId,totalVoters>
					
					  List<Long> panchayatIdsList = new ArrayList<Long>(resultMap.keySet());
					  List<Object[]> boothList = hamletBoothElectionDAO.getPanchayatBoothDetailsByPanchayatIdsList(panchayatIdsList, partyPositionVO.getId());
					  if(boothList != null && boothList.size() > 0)
					  {
						  for(Object[] params:boothList)
						  {
							  List<Long> boothIdsList = boothIdsMap.get((Long)params[0]);
							  if(boothIdsList == null)
							  {
								boothIdsList = new ArrayList<Long>(0);
								boothIdsMap.put((Long)params[0], boothIdsList);  
							  }
							  if(!boothIdsList.contains((Long)params[1]))
								  boothIdsList.add((Long)params[1]);  
						  }
						  
					  }
					
					
					
				if(boothIdsMap != null && boothIdsMap.size() > 0)
				{
				  for(Long id : boothIdsMap.keySet())
					panchayatTotalVotersMap.put(id, boothDAO.getTotalVotesByBoothIdsList(boothIdsMap.get(id)));
				}
				Long localbodytotalVoters = 0l;
				
				if(localbodyboothIds != null && localbodyboothIds.size() > 0)
					localbodytotalVoters = boothDAO.getTotalaVotesByBoothIds(localbodyboothIds).get(0);	
			
				//panchayat start	
				 for(Long id:resultMap.keySet())
				 {
					Map<Long,Long> partyMap = resultMap.get(id);
					Long totalVotes = 0L;
						 
					for(Long partysId:partyMap.keySet())
					  totalVotes += partyMap.get(partysId); 
						 
					Long selectedPartyTotal = partyMap.get(selectedpartyId);
					Long comparePartyTotal = 0L;
						 
					  for(Long partysId:partyMap.keySet())
					  {
					    if(!partysId.equals(selectedpartyId) && comparePartyTotal < partyMap.get(partysId))
						 comparePartyTotal = partyMap.get(partysId);
						  
					  }
				   
				  if(selectedPartyTotal == null)
					  selectedPartyTotal = 0L;
				  if(comparePartyTotal == null)
					  comparePartyTotal = 0L;
				  
				  double selectedPartyTotalPercent =  new BigDecimal((selectedPartyTotal*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			      double comparePartyTotalPercent =  new BigDecimal((comparePartyTotal*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			      
			      //double difference = new BigDecimal(selectedPartyTotalPercent - comparePartyTotalPercent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			      double difference = selectedPartyTotalPercent;
			    	
			      String locationName = "";
			      
			    	 locationName = panchayatDAO.getPanchayatNameById(id); 
			     /* else if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
			    	 locationName = boothDAO.getBoothPartNoByBoothId(id);*/
			      
			      List<PartyPositionVO> partyPositionVOList = partyPositionVO.getPartyPositionVOList();
			      for(PartyPositionVO positionVO :partyPositionVOList)
			      {
			    	if(difference >= positionVO.getMinValue() && difference <= positionVO.getMaxValue())
			    	{
			    	 PartyPositionVO locationVO = null;
			    	 List<PartyPositionVO> locationList = positionVO.getPartyPositionVOList();
			    	 if(locationList == null || locationList.size() == 0)
			    		locationList = new ArrayList<PartyPositionVO>(0);
			    	 
			    	 locationVO = checkPartyPositionVOExist(id,locationList);
			    	 if(locationVO == null)
			    	 {
			    		 locationVO = new PartyPositionVO();
			    		 locationVO.setId(id);
			    		 locationVO.setName(locationName != null?locationName:" ");
			    		 locationVO.setPartyPercentage(selectedPartyTotalPercent);
			    		 locationVO.setSelectedPartyTotalVoters(selectedPartyTotal);
			    		 locationVO.setTotalValidVotes(totalVotes);
			    		 locationVO.setTotalVoters(panchayatTotalVotersMap.get(id));
			    		 locationVO.setPercentage(new BigDecimal((totalVotes*100.0/panchayatTotalVotersMap.get(id))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			    		 locationVO.setMargin(difference);
			    		 
			    		 locationList.add(locationVO);
			    		 positionVO.setPartyPositionVOList(locationList);
			    		 
			    	 }
			    	 
			    	}
			      }
			    	
				} // panchayat End
				 if(resultMap1 != null)
				 {
				 for(Long localbodyId : resultMap1.keySet())
				 {
					 	String localbodyName = localElectionBodyDAO.getLocalElectionBodyName(localbodyId);
						Map<Long,Long> partyMap1 = resultMap1.get(localbodyId);
						Long totalVotes = 0L;
							 
						for(Long partysId:partyMap1.keySet())
						  totalVotes += partyMap1.get(partysId); 
							 
						Long selectedPartyTotal = partyMap1.get(selectedpartyId);
						Long comparePartyTotal = 0L;
							 
						  for(Long partysId:partyMap1.keySet())
						  {
						    if(!partysId.equals(selectedpartyId) && comparePartyTotal < partyMap1.get(partysId))
							 comparePartyTotal = partyMap1.get(partysId);
							  
						  }
					   
					  if(selectedPartyTotal == null)
						  selectedPartyTotal = 0L;
					  if(comparePartyTotal == null)
						  comparePartyTotal = 0L;
					  
					  double selectedPartyTotalPercent =  new BigDecimal((selectedPartyTotal*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				      double comparePartyTotalPercent =  new BigDecimal((comparePartyTotal*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				     
				      double difference = selectedPartyTotalPercent;
				    	
				      List<PartyPositionVO> partyPositionVOList = partyPositionVO.getPartyPositionVOList();
				      for(PartyPositionVO positionVO :partyPositionVOList)
				      {
				    	if(difference >= positionVO.getMinValue() && difference <= positionVO.getMaxValue())
				    	{
				    	 PartyPositionVO locationVO = null;
				    	 List<PartyPositionVO> locationList = positionVO.getPartyPositionVOList();
				    	 if(locationList == null || locationList.size() == 0)
				    		locationList = new ArrayList<PartyPositionVO>(0);
				    	 
				    	 locationVO = checkPartyPositionVOExist(localbodyId,locationList);
				    	 if(locationVO == null)
				    	 {
				    		 locationVO = new PartyPositionVO();
				    		 locationVO.setId(localbodyId);
				    		 locationVO.setName(localbodyName != null?localbodyName:" ");
				    		 locationVO.setPartyPercentage(selectedPartyTotalPercent);
				    		 locationVO.setSelectedPartyTotalVoters(selectedPartyTotal);
				    		 locationVO.setTotalValidVotes(totalVotes);
				    		 locationVO.setTotalVoters(localbodytotalVoters);
				    		 locationVO.setPercentage(new BigDecimal((totalVotes*100.0/localbodytotalVoters)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				    		 locationVO.setMargin(difference);
				    		 locationList.add(locationVO);
				    		 positionVO.setPartyPositionVOList(locationList);
				    		 
				    	 }
				    	 
				    	}
				      }
				    	
					
				 }
				 }
				}catch (Exception e) {
				 e.printStackTrace();
				 LOG.error(" Exception Occured in getPartyPerformanceForSelectedlocation() method, Exception - "+e);
				}
			}
			
			
			public void getPartyPerformanceForPanchayat1(Map<Long,Map<Long,Long>> resultMap,PartyPositionVO partyPositionVO, Long selectedpartyId, Long electionId,AlliancePartyResultsVO alliancePartiesVO)
			{
				try{
					
					//resultMap -- Map<panchayatId,Map<partyId,totalvoters>>
					//resultMap1 -- Map<localbodyName,Map<partyId,totalvoters>>
					
					Map<Long,List<Long>> boothIdsMap = new HashMap<Long, List<Long>>(0);//<panchayatId,List<boothIds>>
					Map<Long,Long> panchayatTotalVotersMap = new HashMap<Long, Long>(0);//<locationId,totalVoters>
										
					  List<Long> panchayatIdsList = new ArrayList<Long>(resultMap.keySet());
					  List<Object[]> boothList = hamletBoothElectionDAO.getPanchayatBoothDetailsByPanchayatIdsList(panchayatIdsList, partyPositionVO.getId());
					  if(boothList != null && boothList.size() > 0)
					  {
						  for(Object[] params:boothList)
						  {
							  List<Long> boothIdsList = boothIdsMap.get((Long)params[0]);
							  if(boothIdsList == null)
							  {
								boothIdsList = new ArrayList<Long>(0);
								boothIdsMap.put((Long)params[0], boothIdsList);  
							  }
							  if(!boothIdsList.contains((Long)params[1]))
								  boothIdsList.add((Long)params[1]);  
						  }
						  
					  }
					
					
					
				if(boothIdsMap != null && boothIdsMap.size() > 0)
				{
				  for(Long id : boothIdsMap.keySet())
					panchayatTotalVotersMap.put(id, boothDAO.getTotalVotesByBoothIdsList(boothIdsMap.get(id)));
				}
				 
				//panchayat start	
				 for(Long id:resultMap.keySet())
				 {
					Map<Long,Long> partyMap = resultMap.get(id);
					Long totalVotes = 0L;
						 
					for(Long partysId:partyMap.keySet())
					  totalVotes += partyMap.get(partysId); 
						 
					Long selectedPartyTotal = partyMap.get(selectedpartyId);
					Long comparePartyTotal = 0L;
						 
					  for(Long partysId:partyMap.keySet())
					  {
					    if(!partysId.equals(selectedpartyId) && comparePartyTotal < partyMap.get(partysId))
						 comparePartyTotal = partyMap.get(partysId);
						  
					  }
					  
				  if(selectedPartyTotal == null){
					 
					  if(alliancePartiesVO == null || alliancePartiesVO.getAllianceParties() == null)
						  selectedPartyTotal = 0L;
					  else
						  for(SelectOptionVO alianceParty:alliancePartiesVO.getAllianceParties())
							  if(selectedPartyTotal == null || selectedPartyTotal.longValue() == 0l)
							  selectedPartyTotal = partyMap.get(alianceParty.getId());
				  }
				  if(selectedPartyTotal == null){
					  selectedPartyTotal = 0l;
				  }
				  if(comparePartyTotal == null)
					  comparePartyTotal = 0L;
				  double selectedPartyTotalPercent = 0d;
				  if(totalVotes != null && totalVotes > 0)
				   selectedPartyTotalPercent =  new BigDecimal((selectedPartyTotal*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			      
			      //double difference = new BigDecimal(selectedPartyTotalPercent - comparePartyTotalPercent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			      double difference = selectedPartyTotalPercent;
			    	
			      String locationName = "";
			      
			    	 locationName = panchayatDAO.getPanchayatNameById(id); 
			     /* else if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
			    	 locationName = boothDAO.getBoothPartNoByBoothId(id);*/
			      
			      List<PartyPositionVO> partyPositionVOList = partyPositionVO.getPartyPositionVOList();
			      for(PartyPositionVO positionVO :partyPositionVOList)
			      {
			    	if(difference >= positionVO.getMinValue() && difference <= positionVO.getMaxValue())
			    	{
			    	 PartyPositionVO locationVO = null;
			    	 List<PartyPositionVO> locationList = positionVO.getPartyPositionVOList();
			    	 if(locationList == null || locationList.size() == 0)
			    		locationList = new ArrayList<PartyPositionVO>(0);
			    	 
			    	 locationVO = checkPartyPositionVOExist(id,locationList);
			    	 if(locationVO == null)
			    	 {
			    		 locationVO = new PartyPositionVO();
			    		 locationVO.setId(id);
			    		 locationVO.setName(locationName != null?locationName:" ");
			    		 locationVO.setPartyPercentage(selectedPartyTotalPercent);
			    		 locationVO.setSelectedPartyTotalVoters(selectedPartyTotal);
			    		 locationVO.setTotalValidVotes(totalVotes);
			    		 locationVO.setTotalVoters(panchayatTotalVotersMap.get(id));
			    		 locationVO.setPercentage(new BigDecimal((totalVotes*100.0/panchayatTotalVotersMap.get(id))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			    		 locationVO.setMargin(difference);
			    		 
			    		 locationList.add(locationVO);
			    		 positionVO.setPartyPositionVOList(locationList);
			    		 
			    	 }
			    	 
			    	}
			      }
			    	
				} // panchayat End
				
				}catch (Exception e) {
				 e.printStackTrace();
				 LOG.error(" Exception Occured in getPartyPerformanceForSelectedlocation() method, Exception - "+e);
				}
			}
			
			 public void getPartyPerformanceForLocalBody(PartyPositionVO partyPositionVO, Long selectedpartyId,Map<Long,Map<Long,Long>> resultMap1,Map<Long,List<Long>> boothIdMap,AlliancePartyResultsVO alliancePartiesVO)
			 {
				 try{
					 Long localbodytotalVoters = 0l;
					
				    	
					 if(resultMap1 != null)
					 {
					 for(Long localbodyId : resultMap1.keySet())
					 {
						 localbodytotalVoters = boothDAO.getTotalaVotesByBoothIds(boothIdMap.get(localbodyId)).get(0);
						 	String localbodyName = localElectionBodyDAO.getLocalElectionBodyName(localbodyId);
							Map<Long,Long> partyMap1 = resultMap1.get(localbodyId);
							Long totalVotes = 0L;
								 
							for(Long partysId:partyMap1.keySet())
							  totalVotes += partyMap1.get(partysId); 
								 
							Long selectedPartyTotal = partyMap1.get(selectedpartyId);
							Long comparePartyTotal = 0L;
								 
							  for(Long partysId:partyMap1.keySet())
							  {
							    if(!partysId.equals(selectedpartyId) && comparePartyTotal < partyMap1.get(partysId))
								 comparePartyTotal = partyMap1.get(partysId);
								  
							  }
							  if(selectedPartyTotal == null){
									 
								  if(alliancePartiesVO == null || alliancePartiesVO.getAllianceParties() == null)
									  selectedPartyTotal = 0L;
								  else
									  for(SelectOptionVO alianceParty:alliancePartiesVO.getAllianceParties())
										  if(selectedPartyTotal == null || selectedPartyTotal.longValue() == 0l)
										  selectedPartyTotal = partyMap1.get(alianceParty.getId());
							  }
						  if(selectedPartyTotal == null)
							  selectedPartyTotal = 0L;
						  if(comparePartyTotal == null)
							  comparePartyTotal = 0L;
						  
						  double selectedPartyTotalPercent =  new BigDecimal((selectedPartyTotal*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					     
					     
					      double difference = selectedPartyTotalPercent;
					    	
					      List<PartyPositionVO> partyPositionVOList = partyPositionVO.getPartyPositionVOList();
					      for(PartyPositionVO positionVO :partyPositionVOList)
					      {
					    	if(difference >= positionVO.getMinValue() && difference <= positionVO.getMaxValue())
					    	{
					    	 PartyPositionVO locationVO = null;
					    	 List<PartyPositionVO> locationList = positionVO.getPartyPositionVOList();
					    	 if(locationList == null || locationList.size() == 0)
					    		locationList = new ArrayList<PartyPositionVO>(0);
					    	 
					    	 locationVO = checkPartyPositionVOExist(localbodyId,locationList);
					    	 if(locationVO == null)
					    	 {
					    		 locationVO = new PartyPositionVO();
					    		 locationVO.setId(localbodyId);
					    		 locationVO.setName(localbodyName != null?localbodyName+" Muncipality":" ");
					    		 locationVO.setPartyPercentage(selectedPartyTotalPercent);
					    		 locationVO.setSelectedPartyTotalVoters(selectedPartyTotal);
					    		 locationVO.setTotalValidVotes(totalVotes);
					    		 locationVO.setTotalVoters(localbodytotalVoters);
					    		 locationVO.setPercentage(new BigDecimal((totalVotes*100.0/localbodytotalVoters)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					    		 locationVO.setMargin(difference);
					    		 locationList.add(locationVO);
					    		 positionVO.setPartyPositionVOList(locationList);
					    		 
					    	 }
					    	 
					    	}
					      }
					    	
						
					 }
					 } 
				 }
				 catch(Exception e)
				 {
					 e.printStackTrace();
				 }
			 }
			 
			 
			 public List<BasicVO> getHamletDetailsByPanchayatIds(Long constituencyId,Long publicationId,Long userId,List<Long> candidateCastes){
				 
				 List<BasicVO> hamletDetails = new ArrayList<BasicVO>();
				 List<BasicVO> panchayatList =null;
				 List<Long> hamletIds = new ArrayList<Long>();
				 Map<Long,List<Long>> hamletIdsList = null;
				 BasicVO basicVO =null;
				 Map<Long,String> panchayatName = new HashMap<Long, String>(0);//<panchayatId,PanchayatName
				 List<SelectOptionVO> panchayats = new ArrayList<SelectOptionVO>();
				 List<Long> panchayaIds = new ArrayList<Long>();
				 List<Long> tehsilIds = boothDAO.getTehsildByConstituency(constituencyId,publicationId);
				 if(tehsilIds != null && tehsilIds.size() > 0)
					{
						for (Long mandalId : tehsilIds) {
							List<Object[]> panchayatsList = panchayatDAO.getPanchayatsByTehsilId(mandalId);
							if(panchayatsList != null && panchayatsList.size() > 0)
							{
								panchayats = new ArrayList<SelectOptionVO>();
								panchayaIds = new ArrayList<Long>();
								for (Object[] parms : panchayatsList) {
									SelectOptionVO selectOptionVO = new SelectOptionVO();	
									selectOptionVO.setId((Long)parms[0]);
									selectOptionVO.setName(parms[1].toString());
									panchayats.add(selectOptionVO);
									panchayaIds.add(selectOptionVO.getId());
								}
							}
				 
				 List<Object[]> values = panchayatHamletDAO.getHamletDetailsByPanchayatIds(panchayaIds,publicationId,userId);
				 hamletIdsList = new HashMap<Long, List<Long>>();//<panchayatId,List<hamletIds>
				
				 for(Object[] params:values){
					hamletIds = hamletIdsList.get((Long)params[0]);
					 
					 if(hamletIds == null)
					 {
						 hamletIds = new ArrayList<Long>();
						 hamletIdsList.put((Long)params[0], hamletIds);
					 }
					 if(!hamletIds.contains((Long)params[2])){
						 hamletIds.add((Long)params[2]);
					 }
					 
					 String panchayat = panchayatName.get((Long)params[0]);
					 if(panchayat == null){
						 panchayatName.put((Long)params[0], params[1] != null?params[1].toString():" ");
					 }
				 } 
				 for(Long panchayatId :hamletIdsList.keySet())
				 {
					 BasicVO basicVO2 = new BasicVO();
					 List<Object[]> panchayatVoterCount = voterCastInfoDAO.getTopThreeCasteForPanchayat(panchayatId,3l,publicationId,1l);
					 int j=0;
					 panchayatList = new ArrayList<BasicVO>();
					 
					 for(Object[] panchayat:panchayatVoterCount){
						 j++;
						 basicVO = new BasicVO();
						
						 basicVO.setCount((Long)panchayat[1]);
						 basicVO.setPersent(panchayat[2].toString());
						 basicVO.setCasteName(panchayat[0].toString());
						 
						 if(candidateCastes.contains(panchayat[3])){
							 BasicVO selectedCastevo = new BasicVO();
							 selectedCastevo.setCount((Long)panchayat[1]);
							 selectedCastevo.setPersent(panchayat[2].toString());
							 selectedCastevo.setCasteName(panchayat[0].toString());
							 basicVO2.getSelectedCasteDetails().add(selectedCastevo);
							}
						 if(j <= 3)
							 panchayatList.add(basicVO);
						 		
					 }
					 
					 basicVO2.setId(panchayatId);
					 basicVO2.setName(panchayatName.get(panchayatId));
					 List<Object[]> voterCount = userVoterDetailsDAO.getVoterCountByHamlet(hamletIdsList.get(panchayatId),userId,publicationId);
					 List<Object[]> casteCount = userVoterDetailsDAO.getCasteInHamlet(hamletIdsList.get(panchayatId),userId,publicationId);
					
					 List<BasicVO> basicVOList = new ArrayList<BasicVO>(0);
					 if(voterCount != null && voterCount.size() > 0)
					 {
						for(Object[] params:voterCount)
						{
							BasicVO basicVO3 = new BasicVO();
							basicVO3.setId((Long)params[0]);
							basicVO3.setName(params[1] != null ?params[1].toString():" ");
							basicVO3.setCount((Long)params[2]);
							basicVO3.setVoterCount((Long)params[3]);
			
							basicVOList.add(basicVO3);
						}
					 }
					
					 if(casteCount != null && casteCount.size() > 0)
					 {
						 BasicVO basicVO3 = null;
						for(Object[] params:casteCount)
						{
							basicVO3 = checkBasicVOExist((Long)params[0], basicVOList);
							if(basicVO3 == null)
							{
								basicVO3 = new BasicVO();
								basicVO3.setId((Long)params[0]);
								basicVOList.add(basicVO3);
							}
							BasicVO topThreeCaste = new BasicVO();
							topThreeCaste.setName(params[2] != null ?params[2].toString():" ");
							topThreeCaste.setCount((Long)params[1]);
							topThreeCaste.setPersent(new BigDecimal(((Long)params[1]*100.0)/(Long)basicVO3.getVoterCount()).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
							List<BasicVO> basicVOsList = basicVO3.getHamletCasteInfo();
							if(candidateCastes.contains(params[3])){
								basicVO3.getSelectedCasteDetails().add(topThreeCaste);
							}
							
							 if(basicVOList == null)
								 basicVOsList = new ArrayList<BasicVO>(0); 
							
							 if(basicVOsList.size() < 3)
							   basicVOsList.add(topThreeCaste);
						}
					 }
					 Long panchayatVotersCount = voterInfoDAO.getVotersCountInALocation(3l,panchayatId,publicationId,constituencyId);
					 basicVO2.setMandalName(tehsilDAO.get(mandalId).getTehsilName());
					 basicVO2.setCount(panchayatVotersCount);
					 basicVO2.setHamletVoterInfo(basicVOList);
					 basicVO2.setPanchayatVoterInfo(panchayatList);
					 hamletDetails.add(basicVO2);
				 }
				}
			}
				 return hamletDetails;
			 }
			 
			 public BasicVO checkBasicVOExist(Long hamletId,List<BasicVO> list)
			 {
				 try{
					if(list == null || list.size() == 0)
						 return null;
					for(BasicVO basicVO : list)
					 if(basicVO.getId().equals(hamletId))
						 return basicVO;
					 
					 return null;
				 }catch (Exception e) {
					 e.printStackTrace();
					 return null;
				}
			 }
			 
			 public List<VoterDataVO> getMandalsAndPanchayats(Long constituencyId)
			 {
				 List<VoterDataVO> returnList = new ArrayList<VoterDataVO>();
				 String constituencyType = constituencyDAO.get(constituencyId).getAreaType();
				// Long publicationId = publicationDateDAO.getLatestPublicationId();
				 Long publicationId = voterInfoDAO.getLatestPublicationDate(constituencyId);
				 if(constituencyType.equalsIgnoreCase(IConstants.RURAL )|| constituencyType.equalsIgnoreCase(IConstants.RURALURBAN))
				 {
					
					 List<Long> tehsilIds = boothDAO.getTehsildByConstituency(constituencyId,publicationId);
					 List<Object[]> panchayatList = panchayatDAO.getMandalsAnaPanchayays(tehsilIds);
					 if(panchayatList != null && panchayatList.size() > 0)
					 {
						 
						 for (Object[] parms : panchayatList) {
							 VoterDataVO voterDataVO = new VoterDataVO();
							 voterDataVO.setPanchayatId((Long)parms[0]);
							 voterDataVO.setPanchayatname(parms[1].toString());
							 voterDataVO.setMandaId(Long.valueOf("2" +parms[2]));
							 voterDataVO.setMandalName(parms[3].toString());
							 returnList.add(voterDataVO);
						}
					 }
				 }
				 if(constituencyType.equalsIgnoreCase(IConstants.RURALURBAN))
				 {
					 Long localElectionBodyId = assemblyLocalElectionBodyDAO.getLocalBodyIdBasedOnConstituencyId(constituencyId);
					// List<Object[]> wardsList = constituencyDAO.getWardsInALocalBody(localElectionBodyId);
					 Object[] muncipalityDetails = assemblyLocalElectionBodyDAO.getMuncipalityDetails(constituencyId);
					 Long muncipalityId = 0l;
					 String muncipalityName = "";
					 if(muncipalityDetails != null)
					 {
						 muncipalityId =  (Long)muncipalityDetails[0];
						 muncipalityName = muncipalityDetails[1].toString();
					 }
					 VoterDataVO voterDataVO = new VoterDataVO();
					 voterDataVO.setMandaId(Long.valueOf("1" +muncipalityId));
					 voterDataVO.setMandalName(muncipalityName +" MUNCIPALITY");
					 returnList.add(voterDataVO); 
				 }
				 if(constituencyType.equalsIgnoreCase(IConstants.URBAN))
				 {
					List<Object[]> boothsWardsList =  boothDAO.getBoothsAndWardsInUrbanConstituency(constituencyId,publicationId);
					if(boothsWardsList != null && boothsWardsList.size() > 0)
					{
						for (Object[] parms : boothsWardsList) {
							 VoterDataVO voterDataVO = new VoterDataVO();
							 voterDataVO.setPanchayatId((Long)parms[0]);
							 voterDataVO.setPanchayatname("Booth-" +parms[1].toString());
							 voterDataVO.setMandaId(Long.valueOf("2" +parms[2]));
							 voterDataVO.setMandalName(parms[3].toString());
							 returnList.add(voterDataVO);
						}
					}
				 }
				 
				 return returnList;
			 }
			 	
				public List<YouthLeaderSelectionVO> getLeadersInUrbanConstituencyes(Long userId,Long constituencyId,List<Long> casteIdsList ,List<ExceptCastsVO> expCasteDetails,Boolean checkStatus,Long publicationDateId)
				{
					List<YouthLeaderSelectionVO> returnList = null;
					try {
						LOG.debug("Entered into getLeadersInUrbanConstituencyes() method in Suggestive Model Service");
						Map<Long,String> wardsNameMap = new HashMap<Long, String>();//Map<wardId,wardname>
						Map<Long,String> boothsNameMap = new HashMap<Long, String>();//Map<boothId,boothName>
						Map<Long,Long> boothwiseTotalVotersMap = new HashMap<Long, Long>();//Map<boothId,totalvoters>
						//Long publicationDateId = voterInfoDAO.getLatestPublicationDate(constituencyId);
						//Long publicationDateId = publicationDateDAO.getLatestPublicationId();
						Long assemblyLocalBodiId = assemblyLocalElectionBodyDAO.getAssemblyLocalElectionBodyIdByConstituency(constituencyId);
						DecimalFormat deciamlFormat = new DecimalFormat("#.##");
						List<BasicVO> basicVOListForBooth = null; 
						List<BasicVO> selectedCastesinBooths = null;
						Map<Long,List<BasicVO>> topThreeCastesMap = new HashMap<Long, List<BasicVO>>();//Map<boothId,topthreecastsdetails>
						Map<Long,List<BasicVO>> selectedCastesMap = new HashMap<Long, List<BasicVO>>();//Map<boothId,selectedcastsdetails>
						Map<Long,Map<Long,Long>> expCastesMap = new HashMap<Long, Map<Long,Long>>();//Map<casteId,Map<boothId,total>>
						Map<Long,Long> boothTotalVotersMap = new HashMap<Long, Long>();//Map<boothId,SelCasteCount>
						Map<Long,List<Long>> wardsAndBoothsMap = new HashMap<Long, List<Long>>();//Map<wardsId,booths>
						List<Long> boothIds = null;
						//List<BasicVO> expcastesList = null;
						 Map<Long,Double> expPanchayatCasteDetailsMap = new HashMap<Long,Double>();//Map<boothId,expPerc>
						 Map<Long,Map<Long,Double>> castePanchayatMap = new HashMap<Long, Map<Long,Double>>();//Map<casteId,Map<boothId,expperc>>
						 Map<Long,Double> casteMap = new HashMap<Long, Double>();//Map<casteId,expPerc>
						 List<Long> expCastesIds = null;
						 Map<Long,String> casteNamesMap = new HashMap<Long, String>();//Map<casteId,CasteName>
						 Map<Long,Long> casteCountsMap = new HashMap<Long, Long>();//Map<caste,castevoters>
						 List<BasicVO> expCasteList = null;
						 Map<Long,List<BasicVO>> panchaytwiseCasteMap = new HashMap<Long, List<BasicVO>>();//Map<boothId,selectedCasteDetails>
						 List<BasicVO> expCastesInBooth = null;
						if(assemblyLocalBodiId > 0)
						{
							if(checkStatus)
							{
								if(expCasteDetails != null && expCasteDetails.size() > 0)
								{
									//expcastesList = new ArrayList<BasicVO>();
									for (ExceptCastsVO exceptCastsVO : expCasteDetails) {
										Long boothId = exceptCastsVO.getPanchayatId();
										Long casteId = exceptCastsVO.getCasteId();
										Double expPerc = exceptCastsVO.getCastePerc();
										expPanchayatCasteDetailsMap = castePanchayatMap.get(casteId);
										if(expPanchayatCasteDetailsMap == null)
										{
											expPanchayatCasteDetailsMap = new HashMap<Long, Double>();
											castePanchayatMap.put(casteId, expPanchayatCasteDetailsMap);
										}
										casteMap.put(casteId, expPerc);
										expPanchayatCasteDetailsMap.put(boothId, expPerc);
									}
									
								
									expCastesIds = new ArrayList<Long>(casteMap.keySet());
									if(expCastesIds != null && expCastesIds.size() > 0)
									{
										List<Object[]> castNames = casteStateDAO.getCasteListByCasteIds(expCastesIds);
										if(castNames != null && castNames.size() > 0)
										{
											for (Object[] parms : castNames) {
												casteNamesMap.put((Long)parms[0], parms[1].toString());
											}
											
										}
										/*List<Object[]> casteCounts = userVoterDetailsDAO.getCasteDetailsOfVoterByConstituency(constituencyId, publicationDateId,userId , expCastesIds );
										if(casteCounts != null && casteCounts.size() > 0)
										{
											for (Object[] parms : casteCounts) {
												casteCountsMap.put((Long)parms[0], (Long)parms[1]);
											}
										}*/
									}
								}
							}
							List<Long> wardIds = assemblyLocalElectionBodyWardDAO.findWardsByAssemblyLocalBody(assemblyLocalBodiId,IConstants.PRESENT_ELECTION_YEAR);
							if(wardIds != null && wardIds.size() > 0)
							{
								List<Object[]> wardsList = localElectionBodyWardDAO.getWardsByConstituency(wardIds);
								if(wardsList != null && wardsList.size() > 0)
								{
									for (Object[] parms : wardsList) {
										wardsNameMap.put((Long)parms[0], parms[2].toString().concat("(").concat(parms[1].toString().toUpperCase().concat(")")));
									}
								}
							}
							List<Object[]> boothsList = boothDAO.getBoothsForUrbanConstituencyes(wardIds,constituencyId,publicationDateId);
							{
								if(boothsList != null && boothsList.size() > 0)
								{
									for (Object[] parms : boothsList) {
										boothIds = wardsAndBoothsMap.get((Long)parms[3]);
										if(boothIds == null)
										{
											boothIds = new ArrayList<Long>();
											wardsAndBoothsMap.put((Long)parms[3], boothIds);
										}
										boothIds.add((Long)parms[0]);
										boothsNameMap.put((Long)parms[0], parms[1].toString());
										boothwiseTotalVotersMap.put((Long)parms[0], (Long)parms[2]);
									}
								}
							}
							List<Object[]> casteDetails = userVoterDetailsDAO.getCasteDetailsOfVoterByBoothIds(wardIds,publicationDateId,userId);
							if(casteDetails != null && casteDetails.size() > 0)
							{
								int count = 0;
								basicVOListForBooth = new ArrayList<BasicVO>();
								selectedCastesinBooths = new ArrayList<BasicVO>();
								expCastesInBooth = new ArrayList<BasicVO>();
								for (Object[] parms : casteDetails) {
									
									if(!checkStatus)
									{
										basicVOListForBooth = topThreeCastesMap.get((Long)parms[0]);
										if(basicVOListForBooth == null)
										{
											basicVOListForBooth = new ArrayList<BasicVO>();
											topThreeCastesMap.put((Long)parms[0], basicVOListForBooth);
											count = 0;
										}
										else
										{
											count++;
										}
										if(IConstants.MAX_LEVEL > count)
										{
											basicVOListForBooth = topThreeCastesMap.get((Long)parms[0]);
											if(basicVOListForBooth == null)
											{
												basicVOListForBooth = new ArrayList<BasicVO>();
												topThreeCastesMap.put((Long)parms[0], basicVOListForBooth);
											}
											BasicVO basicVO = new BasicVO();
											basicVO.setId((Long)parms[0]);
											basicVO.setCount((Long)parms[2]);
											basicVO.setName(parms[1].toString());
											basicVO.setPerc(Double.valueOf(deciamlFormat.format((Long)parms[2]*100/boothwiseTotalVotersMap.get((Long)parms[0]).floatValue())));
											basicVOListForBooth.add(basicVO);
										}
										
										//topThreeCastesMap.put((Long)parms[0], basicVOListForBooth);
									
									
										if(casteIdsList.contains(parms[3]))
										{
											selectedCastesinBooths = selectedCastesMap.get((Long)parms[0]);
											if(selectedCastesinBooths == null)
											{
												selectedCastesinBooths = new ArrayList<BasicVO>();
												selectedCastesMap.put((Long)parms[0], selectedCastesinBooths);
											}
											BasicVO basicVO1 = new BasicVO();
											basicVO1.setId((Long)parms[0]);
											basicVO1.setCount((Long)parms[2]);
											basicVO1.setName(parms[1].toString());
											basicVO1.setPerc(Double.valueOf(deciamlFormat.format((Long)parms[2]*100/boothwiseTotalVotersMap.get((Long)parms[0]).floatValue())));
											selectedCastesinBooths.add(basicVO1);
											
										//selectedCastesMap.put((Long)parms[0], selectedCastesinBooths);
										}
									}
										
									if(checkStatus)
									{
										boothTotalVotersMap = expCastesMap.get((Long)parms[3]);
										if(boothTotalVotersMap == null)
										{
											boothTotalVotersMap = new HashMap<Long, Long>();
											expCastesMap.put((Long)parms[3], boothTotalVotersMap);
										}
										boothTotalVotersMap.put((Long)parms[0], (Long)parms[2]);
									}
									
									
									
								}
								
							}
							
							if(wardsAndBoothsMap != null && wardsAndBoothsMap.size() > 0)
							{
								List<Long> wards = new ArrayList<Long>(wardsAndBoothsMap.keySet());
								if(wards != null && wards.size() > 0)
								{
									returnList = new ArrayList<YouthLeaderSelectionVO>();
									for (Long wardsId : wards) {
										Long votersCount = 0l;
										YouthLeaderSelectionVO youthLeaderSelectionVO = new YouthLeaderSelectionVO();
										youthLeaderSelectionVO.setPanchayatId(wardsId);
										youthLeaderSelectionVO.setPanchayatName(wardsNameMap.get(wardsId));
										youthLeaderSelectionVO.setMandalId(constituencyId);
										youthLeaderSelectionVO.setMandalName(constituencyDAO.get(constituencyId).getName());
										List<Long> booths = wardsAndBoothsMap.get(wardsId);
										List<YouthLeaderSelectionVO> boothLevelLeardsList = new ArrayList<YouthLeaderSelectionVO>();
										for (Long boothId : booths) {
											YouthLeaderSelectionVO boothYouthLeadresSelectionVO = new YouthLeaderSelectionVO();
											boothYouthLeadresSelectionVO.setBoothId(boothId);
											boothYouthLeadresSelectionVO.setBoothName(boothsNameMap.get(boothId));
											boothYouthLeadresSelectionVO.setBoothTotalVoters(boothwiseTotalVotersMap.get(boothId));
											boothYouthLeadresSelectionVO.setTopThreeCateList(topThreeCastesMap.get(boothId));
											boothYouthLeadresSelectionVO.setSelectedCateList(selectedCastesMap.get(boothId));
											
											votersCount = votersCount + boothwiseTotalVotersMap.get(boothId);
										
										if(checkStatus)
										{		
												expCasteList = new ArrayList<BasicVO>();
												Long casteCount = 0l;
												
													for (Long casteId : expCastesIds)
													{
														if(casteId > 0)
														{
															BasicVO casteVO = new BasicVO();
															casteVO.setId(casteId);
															Long total = expCastesMap.get(casteId).get(boothId);
															Double expPerc = castePanchayatMap.get(casteId).get(boothId);
															if(total != null)
															{
																Long expVoters = (long) (total*expPerc);
																casteVO.setExpCount(expVoters);
																casteCount = casteCount + total;
															}
															else
															{
																Long expVoters = 0l;
																casteVO.setExpCount(0l);
																casteCount = casteCount ;
															}
															casteVO.setCount(total);
															
															casteVO.setName(casteNamesMap.get(casteId));
															casteVO.setPerc(expPerc);
															expCasteList.add(casteVO);
															
															panchaytwiseCasteMap.put(boothId, expCasteList);
														}
													}
												
												BasicVO casteVO = new BasicVO();
												casteVO.setId(0l);
												Long total =  boothwiseTotalVotersMap.get(boothId)- casteCount;
												Double expPerc = castePanchayatMap.get(0l).get(boothId);
												Long expVoters = (long) (total*expPerc);
												casteVO.setCount(total);
												casteVO.setExpCount(expVoters);
												casteVO.setName("OTHERS");
												casteVO.setPerc(expPerc);
												expCasteList.add(casteVO);
												panchaytwiseCasteMap.put(boothId, expCasteList);
										   }	
										boothYouthLeadresSelectionVO.setExceptdCateDetails(panchaytwiseCasteMap.get(boothId));
										boothLevelLeardsList.add(boothYouthLeadresSelectionVO);
										}
										youthLeaderSelectionVO.setBoothLevelLeadersList(boothLevelLeardsList);
										youthLeaderSelectionVO.setExceptdCateDetails(panchaytwiseCasteMap.get(wardsId));
										youthLeaderSelectionVO.setPanchayatTotalVoters(votersCount);
										returnList.add(youthLeaderSelectionVO);
									}
								}
							}
							
						}
						
					} catch (Exception e) {
						LOG.error("Exception raised in getLeadersInUrbanConstituencyes() method in Suggestive Model Service", e);
					}
					
					
					return returnList;
				}

				 
			 public List<PartyPositionVO> getPollingPercentagesByParty(Long constituenycId,Long partyId,Long electionId,Long electionId1,String path,String type)
			 {
				 Long mainPartyId = partyId;
				 Long latestPublictaionId = 0l;
				 Long TotalVotesInConstituency = 0l;
				 Long TotalPolledVotesInConstituency = 0l;
				 Double partyPercentage = 0.0;
				 Map<Long,Map<Long,Long>> boothMap = new HashMap<Long, Map<Long,Long>>();//<boothId,PartyMap<PartyId,polledVotes>)
				 List<PartyPositionVO> result = new ArrayList<PartyPositionVO>();
				 List<Object[]> totalVotesForBooth = null;
				 List<Object[]> totalPolledVotesForBooth = null;
				 Map<Long,Object[]> totalVotersMap = new HashMap<Long, Object[]>();
				 Map<Long,String> panchayatMap = new HashMap<Long, String>();
				 Map<Long,Long> addedVotersMap = new HashMap<Long, Long>();
				 List<Long> electionIdsList = new ArrayList<Long>();
				 DecimalFormat decimalFormat = new DecimalFormat("#.##");
				 try{
					 electionIdsList.add(electionId);
					 electionIdsList.add(electionId1);
					 electionId = electionDAO.getSortedElectionIds(electionIdsList).get(0);
					 List<Long> boothIds = boothConstituencyElectionDAO.getBoothIdsByConstituencyId(constituenycId,electionId);
					 List<Object[]> totalVotersInBooth =boothDAO.getTotalVotesForBooth(boothIds);
					 List<Object[]> panchayatnames = hamletBoothElectionDAO.getPanchayatNamesByBoothIds(boothIds);
					 //latestPublictaionId = publicationDateDAO.getLatestPublicationId();
					 latestPublictaionId = voterInfoDAO.getLatestPublicationDate(constituenycId);
					 List<Object[]> addedVotersCount = voterModificationDAO.getAddedVotersByBoothIds(boothIds,latestPublictaionId,constituenycId);
					 Set<Long> booths = new HashSet<Long>(boothIds);
					 
					 List<Object[]> constituencyInfo = null;
					 Long partyPolledVotesInConstituency = null;
					 AlliancePartyResultsVO alliancePartiesVO = staticDataService.getAlliancePartiesByElectionAndParty(electionId,partyId);
					 if(electionDAO.get(electionId).getElectionScope().getElectionType().getElectionType().equalsIgnoreCase("Assembly")){
					     constituencyInfo = constituencyElectionResultDAO.findTotalVotesAndPolledVotesAndVotesPercentage(electionId,constituenycId);
					     try{
					     partyPolledVotesInConstituency = nominationDAO.getPartyPercentage(constituenycId, electionId, partyId).longValue();
					     }catch(Exception e){
					    	 
					     }
					     if((partyPolledVotesInConstituency == null || partyPolledVotesInConstituency.longValue() == 0) && alliancePartiesVO != null && alliancePartiesVO.getAllianceParties() != null && alliancePartiesVO.getAllianceParties().size() > 0){
					    	
						    	 for(SelectOptionVO alianceParty:alliancePartiesVO.getAllianceParties()){
						    		 try{ 
						    		  partyPolledVotesInConstituency = nominationDAO.getPartyPercentage(constituenycId, electionId,  alianceParty.getId()).longValue();
						    		 }catch(Exception e){
								    	 
								     }
							    	 if((partyPolledVotesInConstituency != null && partyPolledVotesInConstituency.longValue() > 0) ){
							    		 partyId =  alianceParty.getId();
							    		 break;
							    	 }
						    	 }
					    	
					     }
					 }else{
						 TotalVotesInConstituency = candidateBoothResultDAO.findTotalVotesForAssembInAParliament(booths, electionId);
						 TotalPolledVotesInConstituency = candidateBoothResultDAO.findPolledVotesForAssembInAParliament(booths, electionId);
						 partyPolledVotesInConstituency = candidateBoothResultDAO.findTotalVotesPolledForCandidateAssembInAParliament(booths, electionId, partyId);
						 
						 if((partyPolledVotesInConstituency == null || partyPolledVotesInConstituency.longValue() == 0) && alliancePartiesVO != null && alliancePartiesVO.getAllianceParties() != null && alliancePartiesVO.getAllianceParties().size() > 0){
						    	
					    	 for(SelectOptionVO alianceParty:alliancePartiesVO.getAllianceParties()){
					    		 try{ 
					    		  partyPolledVotesInConstituency = candidateBoothResultDAO.findTotalVotesPolledForCandidateAssembInAParliament(booths, electionId,  alianceParty.getId());
					    		 }catch(Exception e){
							    	 
							     }
						    	 if((partyPolledVotesInConstituency != null && partyPolledVotesInConstituency.longValue() > 0) ){
						    		 partyId =  alianceParty.getId();
						    		 break;
						    	 }
					    	 }
				    	
				     }
					 }
					 if(constituencyInfo != null && constituencyInfo.size() > 0)
						for(Object[] params : constituencyInfo)
						{
							TotalVotesInConstituency = new Double(params[0].toString()).longValue();
							TotalPolledVotesInConstituency =new Double(params[1].toString()).longValue();
						}
					 
					 Double pollingPerForConstituency = (TotalPolledVotesInConstituency * 100.0)/TotalVotesInConstituency;
					 
					 Double partyPerInConstituency = (partyPolledVotesInConstituency * 100.0)/TotalPolledVotesInConstituency;
					
					
					 totalVotesForBooth =candidateBoothResultDAO.findBoothResultsForMultipleBoothsAndElectionIdForSelElection(booths,electionId); 
					 
					 if(totalVotersInBooth != null && totalVotersInBooth.size() > 0)
					 {
						 for(Object[] params : totalVotersInBooth)
						 {
							
						 totalVotersMap.put((Long)params[0],params);
						 }
					 }
					 if(addedVotersCount != null && addedVotersCount.size() > 0)
					 {
						 for(Object[] params : addedVotersCount)
						 {
						 if(params[1] != null && ((Long)params[1]).longValue() >= IConstants.MIN_ADDED_VOTERS)
						 addedVotersMap.put((Long)params[0],(Long)params[1]);
						 }
					 }
					 if(panchayatnames !=null &&panchayatnames.size() > 0)
						 for(Object[] params : panchayatnames)
							 panchayatMap.put((Long)params[0],params[1].toString()); 
					 Set<Long> pancNameContainBooths  = panchayatMap.keySet();
					 try{
						 boothIds.removeAll(pancNameContainBooths);
						 if(boothIds.size() > 0){
							 List<Object[]> boothNames = hamletBoothElectionDAO.getMunicipalityNames(boothIds);
							 for(Object[] params : boothNames)
								 panchayatMap.put((Long)params[0],params[1].toString());
						 }
					 }catch(Exception e){
						 
					 }
  					 if(totalVotesForBooth != null && totalVotesForBooth.size() > 0)
					 {
						 for(Object[] params : totalVotesForBooth)
						 {
							Map<Long,Long> partyMap =  boothMap.get((Long)params[0]);
							if(partyMap == null)
							{
								partyMap = new HashMap<Long, Long>();
								boothMap.put((Long)params[0], partyMap);
							}
							Long polledVotes = partyMap.get((Long)params[1]);
							if(polledVotes == null)
								
								partyMap.put((Long)params[1],(Long)params[2]);
							else
							partyMap.put((Long)params[1], polledVotes+(Long)params[2]);
						 }
				 }
					 List<PartyPositionVO> PollingHighboothResultList = new ArrayList<PartyPositionVO>();
					 List<PartyPositionVO> PollingLowboothResultList = new ArrayList<PartyPositionVO>();
					 PartyPositionVO mainVo = new PartyPositionVO();
					 for(Long boothId :boothMap.keySet())
					 {
						 PartyPositionVO partyPositionVO = new PartyPositionVO();
						 partyPositionVO.setId(boothId);
						 partyPositionVO.setName(boothDAO.getBoothPartNoByBoothId(boothId));
						 if(panchayatMap.get(boothId) == null || panchayatMap.get(boothId).equals(""))
							 partyPositionVO.setLocalbodyName("");
						 else
						 partyPositionVO.setLocalbodyName(panchayatMap.get(boothId));
						 Long totalValidVotes = 0l;
						 List<PartyPositionVO> StrongpartyInfo = new ArrayList<PartyPositionVO>();
						 List<PartyPositionVO> WeakpartyInfo = new ArrayList<PartyPositionVO>();
						 Map<Long,Long> partyMap =  boothMap.get(boothId);
						 List<SelectOptionVO> partysList = new ArrayList<SelectOptionVO>();
						 for(Long partyId1 : partyMap.keySet()){
							 totalValidVotes +=partyMap.get(partyId1);
							 SelectOptionVO vo = new SelectOptionVO();
							 vo.setId(partyId1);
							 vo.setTotalCount(partyMap.get(partyId1));
							 partysList.add(vo);
						 }
						 Collections.sort(partysList,votesSort);
						  
						 for(Long partyId1 : partyMap.keySet())
						 {
							 if(partyId.equals(partyId1))
							 {
								 PartyPositionVO partyVo = new PartyPositionVO();
								 partyVo.setPartyName(partyDAO.getPartyShortNameById(mainPartyId));
								 partyVo.setPartyTotalvotes(partyMap.get(partyId1));
								 partyVo.setPartyPercentage(partyVo.getPartyTotalvotes() * 100.0 /totalValidVotes);
								 partyVo.setSelectedParty(true);
								 if(partysList.get(0).getId().equals(partyId)){
									 StrongpartyInfo.add(partyVo);
								 }
								 else{
									 WeakpartyInfo.add(partyVo); 
								 }
							 }
						}
						
						 partyPositionVO.setTotalValidVotes(totalValidVotes);
						 Object[] params1 = totalVotersMap.get(boothId);
						
						 partyPositionVO.setTotalVoters((Long)params1[1]);
						 partyPositionVO.setLocation(params1[2].toString());
						 partyPositionVO.setVillagesCovered(params1[3].toString());
						 partyPositionVO.setPollingPercentage((partyPositionVO.getTotalValidVotes() * 100.0 )/ partyPositionVO.getTotalVoters());
						 partyPositionVO.setRangePercentage((long)(pollingPerForConstituency * partyPositionVO.getTotalVoters())/100 );
						
						
						 if(pollingPerForConstituency < partyPositionVO.getPollingPercentage())
						 {
							 if(WeakpartyInfo != null && WeakpartyInfo.size() >0)
							 {
							 partyPositionVO.setMinValue(partyPositionVO.getPollingPercentage()-pollingPerForConstituency) ;
							 partyPositionVO.setLostSeats(new Double((partyPositionVO.getMinValue()* partyPositionVO.getTotalVoters())/100).longValue());
							 partyPositionVO.setWeakPollingPercentVOList(WeakpartyInfo);
							 if(addedVotersMap.get(boothId) == null || addedVotersMap.get(boothId).equals(""))
								 partyPositionVO.setAddedVotersCount(0l);
							 else
								partyPositionVO.setAddedVotersCount(addedVotersMap.get(boothId));
							 PollingHighboothResultList.add(partyPositionVO);
							 }
						 }
						 else
						 {
							 if(StrongpartyInfo != null && StrongpartyInfo.size() >0)
							 {
							 partyPositionVO.setMaxValue(pollingPerForConstituency-partyPositionVO.getPollingPercentage());
							 partyPositionVO.setToTarget(new Double((partyPositionVO.getMaxValue() * partyPositionVO.getTotalVoters())/100).longValue());
							 partyPositionVO.setToImprove(new Double((partyPositionVO.getToTarget() * StrongpartyInfo.get(0).getPartyPercentage())/100).longValue());
							 partyPositionVO.setStrongPollingPercentVOList(StrongpartyInfo);
							 PollingLowboothResultList.add(partyPositionVO);
							 }
						 }
						 }
					
					 mainVo.setPartyPercentage(Double.valueOf(decimalFormat.format(partyPerInConstituency)));
					 mainVo.setPollingPercentage(Double.valueOf(decimalFormat.format(pollingPerForConstituency)));
					 if(PollingHighboothResultList != null && PollingHighboothResultList.size() >0){	 
						 Collections.sort(PollingHighboothResultList,finalOrderSort);
					 mainVo.setStrongPollingPercentVOList(PollingHighboothResultList);
					 }
					 if(PollingLowboothResultList != null && PollingLowboothResultList.size() > 0){
						 Collections.sort(PollingLowboothResultList,finalOrderSort1);
					 mainVo.setWeakPollingPercentVOList(PollingLowboothResultList);
					 }
					 result.add(mainVo);
			 }
				 catch(Exception e)
				 {
					 
					 e.printStackTrace();
				 }
				 if(result != null && result.size() > 0){
					 result.get(0).setConstituencyType(constituencyDAO.get(constituenycId).getAreaType());
				 }
				 if(type != null)
			     {
					 if(result != null && result.size() > 0)
					 {
						 Document document = null;
							try 
							{
								document = new Document();
								Object[] values = constituencyDAO.constituencyName(constituenycId).get(0);
						    	String constituenyName = values[0].toString().toUpperCase();
						    	String districtName = values[1].toString().toUpperCase();
						    	Long constituenyNo = delimitationConstituencyDAO.getConstituencyNo(constituenycId,2009l);
						    	//String path = "C:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\PartyAnalyst\\";
						    	String filePath = "";
						    	if(type.equalsIgnoreCase("reducePolling"))
						  		{
						    		 filePath = "VMR"+"/"+""+districtName+"_"+constituenyNo+"_"+constituenyName+"Party_Low_Polling.pdf";
						  		}
						    	else
						    	{
						    		 filePath = "VMR"+"/"+""+districtName+"_"+constituenyNo+"_"+constituenyName+"Party_High_Polling.pdf";
						    	}
						    	
							    String FILE = path+filePath;
							    File file  = new File(FILE);
							    try {
									file.createNewFile();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							    result.get(0).setUrl(filePath);
							  	try {
							  		PdfWriter.getInstance(document, new FileOutputStream(FILE));
							  	} catch (FileNotFoundException e) {
							  		e.printStackTrace();
							  	} catch (DocumentException e) {
							  		e.printStackTrace();
							  	}
							  	
							  	
							  	
							  		document.open();
							  		if(type.equalsIgnoreCase("reducePolling"))
							  		{
							  			pdfReportService.pollingPercentageReport(document,result.get(0).getStrongPollingPercentVOList(),"Polling High,TDP Party Weak",result.get(0).getPollingPercentage());
							  		}
							  		else
							  		{
							  			pdfReportService.pollingPercentageReportForHighPolling(document,result.get(0).getWeakPollingPercentVOList(),"Polling Low,TDP Party Strong",result.get(0).getPollingPercentage());
							  			
							  		}

							} 
							catch (Exception e)
							{
								
							}
							finally
							{
								if(document != null)
								document.close();
							}
							
					 }
			  	 }
				
				return result;
			 }
			 
			 
			 public List<VoterVO> getAddedVotersDetailsByPartNo(Long ConstituencyId,Long partNo,Integer startIndex,Integer maxIndex)
			 {
				 List<VoterVO> result = new ArrayList<VoterVO>();
				 try{
				//	Long latestPublictaionId = publicationDateDAO.getLatestPublicationId();
					 Long latestPublictaionId = voterInfoDAO.getLatestPublicationDate(ConstituencyId);
					List<Object[]> list = voterModificationDAO.getAddedVotersDetailsByPartNo(partNo,latestPublictaionId,ConstituencyId,startIndex,maxIndex); 
					if(list != null && list.size() > 0)
						for(Object[] params : list)
						{
							VoterVO votervo = new VoterVO();
							votervo.setVoterId(params[0].toString());
							votervo.setName(params[1].toString());
							votervo.setAge((Long)params[2]);
							votervo.setGender(params[3].toString());
							votervo.setHouseNo(params[4].toString());
							
							result.add(votervo);	
						}
					result.get(0).setTotalVoters(new Long(voterModificationDAO.getAddedVotersDetailsByPartNo(partNo,latestPublictaionId,ConstituencyId,null,null).size()));
				 }
				 catch(Exception e)
				 {
					e.printStackTrace(); 
				 }
				return result;
			 }
			 
	public List<VoterCountVO> getVotersCountInPanchayats(Long constituencyId,Long publicationId)
	{
		List<VoterCountVO> voterCountVOList = new ArrayList<VoterCountVO>();
	
		try {
			LOG.debug("Enterd into getVotersCountInPanchayats() method in Suggestive Model Service");
			//Long publicationId = voterInfoDAO.getLatestPublicationDate(constituencyId);
			if(publicationId != null)
			{
				//String constituencyType = constituencyDAO.get(constituencyId).getAreaType();
				createObjectsForVoterCountVO(voterCountVOList);
				List<Object[]> votersCountList = voterInfoDAO.getVoterCountInPanchayatLevel(constituencyId,publicationId,3l);
				
				if(votersCountList != null && votersCountList.size() > 0)
				{
					for (Object[] parms : votersCountList) {
						
						for (int i = 0; i < voterCountVOList.size(); i++) {
							if((Long)parms[1] >= voterCountVOList.get(i).getMinValue() && (Long)parms[1] <= voterCountVOList.get(i).getMaxValue())
							{
								Long count = voterCountVOList.get(i).getCount();
								count = count + 1;
								voterCountVOList.get(i).setCount(count);
								break;
							}
						}
					}
					
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getVotersCountInPanchayats() method in Suggestive Model Service",e);
		}
		
		return voterCountVOList;
	}
	
	public void createObjectsForVoterCountVO(List<VoterCountVO> voterCountVOList)
	{
		VoterCountVO voterCountVO1 = new VoterCountVO();
		voterCountVO1.setMinValue(0l);
		voterCountVO1.setMaxValue(500l);
		voterCountVO1.setType("Below 500");
		voterCountVOList.add(voterCountVO1);
		
		VoterCountVO voterCountVO2 = new VoterCountVO();
		voterCountVO2.setMinValue(501l);
		voterCountVO2.setMaxValue(1000l);
		voterCountVO2.setType("501-1000");
		voterCountVOList.add(voterCountVO2);
		
		VoterCountVO voterCountVO3 = new VoterCountVO();
		voterCountVO3.setMinValue(1001l);
		voterCountVO3.setMaxValue(1500l);
		voterCountVO3.setType("1001-1500");
		voterCountVOList.add(voterCountVO3);
		
		VoterCountVO voterCountVO4 = new VoterCountVO();
		voterCountVO4.setMinValue(1501l);
		voterCountVO4.setMaxValue(2000l);
		voterCountVO4.setType("1501-2000");
		voterCountVOList.add(voterCountVO4);
		
		VoterCountVO voterCountVO5 = new VoterCountVO();
		voterCountVO5.setMinValue(2001l);
		voterCountVO5.setMaxValue(2500l);
		voterCountVO5.setType("2001-2500");
		voterCountVOList.add(voterCountVO5);
		
		VoterCountVO voterCountVO6 = new VoterCountVO();
		voterCountVO6.setMinValue(2501l);
		voterCountVO6.setMaxValue(3000l);
		voterCountVO6.setType("2501-3000");
		voterCountVOList.add(voterCountVO6);
		
		VoterCountVO voterCountVO7 = new VoterCountVO();
		voterCountVO7.setMinValue(3001l);
		voterCountVO7.setMaxValue(4000l);
		voterCountVO7.setType("3001-4000");
		voterCountVOList.add(voterCountVO7);
		
		VoterCountVO voterCountVO8 = new VoterCountVO();
		voterCountVO8.setMinValue(4001l);
		voterCountVO8.setMaxValue(5000l);
		voterCountVO8.setType("4001-5000");
		voterCountVOList.add(voterCountVO8);
		
		VoterCountVO voterCountVO9 = new VoterCountVO();
		voterCountVO9.setMinValue(5001l);
		voterCountVO9.setMaxValue(6000l);
		voterCountVO9.setType("5001-6000");
		voterCountVOList.add(voterCountVO9);
		
		VoterCountVO voterCountVO10 = new VoterCountVO();
		voterCountVO10.setMinValue(6001l);
		voterCountVO10.setMaxValue(60000l);
		voterCountVO10.setType("Above 6000");
		voterCountVOList.add(voterCountVO10);
		
	}
	
	
	public List<SelectOptionVO> getSelectedCountPAnchayatsDetails(Long constituencyId,Long minValue,Long maxValue)
	{
		List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
		try {
			LOG.debug("Enterd into getSelectedCountPAnchayatsDetails() method in Suggestive Model Service");
			Long publicatiobId = voterInfoDAO.getLatestPublicationDate(constituencyId);
			if(publicatiobId != null)
			{
				//String constituencyType = constituencyDAO.get(constituencyId).getAreaType();
				List<Long> pachayatIds  = voterInfoDAO.getCountForSelectdCountRange(constituencyId,publicatiobId,minValue,maxValue,3l);
				//List<Object[]> panchayatList = panchayatDAO.getPanchayatsByPanchayatIdsList(pachayatIds);
				List<Object[]> panchayatList1= panchayatDAO.getPanchayatsByPanchayatIdsListAlongMandal(pachayatIds);
				if(panchayatList1 != null && panchayatList1.size() > 0)
				{
					returnList = new ArrayList<SelectOptionVO>();
					for (Object[] parms : panchayatList1) {
						SelectOptionVO selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId((Long)parms[0]);
						selectOptionVO.setName(parms[1].toString());
						selectOptionVO.setLocation(parms[2].toString());
						returnList.add(selectOptionVO);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getSelectedCountPAnchayatsDetails() method in Suggestive Model Service",e);
		}
		
		
		return returnList;
	}
	
	public VotersInfoForMandalVO getVotersCount(Long constituencyId,Long publicationDateId,String locationType)
	{
		VotersInfoForMandalVO votersInfoForMandalVO =new VotersInfoForMandalVO();
		try {
			LOG.debug("Enterd into getVotersCount() method in Suggestive Model Service");
			Long reportLevelId = voterReportLevelDAO.getReportLevelIdByType(locationType);
			List<VoterInfo> list = voterInfoDAO.getVotersCount(reportLevelId, constituencyId, publicationDateId,constituencyId);
		
			if(list != null && list.size() > 0)
			{
				for(VoterInfo voterDetails : list)
				{
					votersInfoForMandalVO.setTotalVoters(voterDetails.getTotalVoters().toString());
					votersInfoForMandalVO.setMaleVoters(voterDetails.getMaleVoters());
					votersInfoForMandalVO.setTotalMalePercentage(voterDetails.getMaleVotersPercentage().toString());
					votersInfoForMandalVO.setFemaleVoters(voterDetails.getFemaleVoters());
					votersInfoForMandalVO.setTotalFemalePercentage(voterDetails.getFemaleVotersPercentage().toString());
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getVotersCount() method in Suggestive Model Service",e);
		}
		
		return votersInfoForMandalVO;
	}

	@SuppressWarnings("unused")
	public DelimitationEffectVO getDelimationEffectOnConstituency(Long constituencyId,Long partyId)
	{
		DelimitationEffectVO delimationDetails = new DelimitationEffectVO();
		try {
			List<DelimitationEffectVO> delimitationEffectList = null;
			List<DelimitationEffectVO> delimitationEffectList1 = null;
			List<DelimitationEffectVO> delimitationEffectList2 = null;
			Map<String, DelimitationEffectVO> delimationEffectMap = new HashMap<String, DelimitationEffectVO>();
			Map<String, DelimitationEffectVO> delimationEffectMap1 = new HashMap<String, DelimitationEffectVO>();
			LOG.debug("Enterd into getDelimationEffectOnConstituency() method in Suggestive Model Service");
			DelimitationEffectVO others = new DelimitationEffectVO();
			String presentElectionYear = IConstants.PRESENT_ELECTION_YEAR;
			String previousElectionYear = IConstants.PREVIOUS_ELECTION_YEAR;
			delimationDetails.setPresentYear(presentElectionYear);
			delimationDetails.setPreviousyear(previousElectionYear);
			Long presentElectionId = electionDAO.getElectionId(presentElectionYear,2l,1l);
			List<Object[]> afterDelimationtotalAndPolledVotesCount = boothResultDAO.getAfterDelimitationEffectBasedOnVoters(presentElectionId,constituencyId);
			if(afterDelimationtotalAndPolledVotesCount != null && afterDelimationtotalAndPolledVotesCount.size() > 0)
			{
				fillVotersCountForConstituency(afterDelimationtotalAndPolledVotesCount,delimationDetails,"after");
				
			}
			List<Object[]> afterDelimationPartyResult = candidateBoothResultDAO.getPartyWiseAfterDelimationEffectBasedOnVoters(presentElectionId,constituencyId);
			if(afterDelimationPartyResult != null && afterDelimationPartyResult.size() > 0)
			{
				fillPartyWiseVotersCountAndPercentage(afterDelimationPartyResult,delimationDetails,partyId,delimationEffectMap,"after",others);
			}
			

			Long previousElectionId = electionDAO.getElectionId(previousElectionYear,2l,1l);
			List<Long> tehsilIds = boothDAO.getTehsilsForAfterDelimation(constituencyId,Long.valueOf(presentElectionYear));
			//List<Long> panchayatIds = panchayatDAO.getPanchayatIdsForDelemationEffect(constituencyId,Long.valueOf(presentElectionYear));
			List<Long> boothIds  = boothDAO.getBoothsBeforDelimation(Long.valueOf(previousElectionYear),tehsilIds);
			//List<Long> boothIds  = panchayatHamletDAO.getboothdByPanchayat(Long.valueOf(previousElectionYear),panchayatIds);
			List<Object[]> beforeDelimationtotalAndPolledVotesCount = boothResultDAO.getBeforeDelimitationEffectBasedOnVoters(previousElectionId,boothIds);
			if(beforeDelimationtotalAndPolledVotesCount != null && beforeDelimationtotalAndPolledVotesCount.size() > 0)
			{
				fillVotersCountForConstituency(beforeDelimationtotalAndPolledVotesCount,delimationDetails,"before");
			}
			List<Object[]> beforeDelimationPartyResult = candidateBoothResultDAO.getPartyWiseBeforDelimationEffectBasedOnVoters(previousElectionId,boothIds);
			if(beforeDelimationPartyResult != null && beforeDelimationPartyResult.size() > 0)
			{
				fillPartyWiseVotersCountAndPercentage(beforeDelimationPartyResult,delimationDetails,partyId,delimationEffectMap,"before",others);
				
			}
			Set<String> totalParties = delimationEffectMap.keySet();
			if(totalParties != null && totalParties.size() > 0)
			{
			    delimitationEffectList = new ArrayList<DelimitationEffectVO>();
				for (DelimitationEffectVO delimitationEffectVO : delimationEffectMap.values()) {
					if(delimitationEffectVO.getPresentCount() > 0 || delimitationEffectVO.getPreviousCount() > 0)
					{
						DelimitationEffectVO delimitationEffect = new DelimitationEffectVO();
						delimitationEffect.setPartyId(delimitationEffectVO.getPartyId());
						delimitationEffect.setPartyName(delimitationEffectVO.getPartyName());
						delimitationEffect.setPresentCount(delimitationEffectVO.getPresentCount());
						delimitationEffect.setPresentPolledVotes(delimitationEffectVO.getPresentPolledVotes());
						delimitationEffect.setPresentPerc(delimitationEffectVO.getPresentPerc());
						delimitationEffect.setPreviousCount(delimitationEffectVO.getPreviousCount());
						delimitationEffect.setPreviousPolledVotes(delimitationEffectVO.getPreviousPolledVotes());
						delimitationEffect.setPreviousPerc(delimitationEffectVO.getPreviousPerc());
						
						delimitationEffectList.add(delimitationEffect);
					}
					
				}
			}
			
			delimationDetails.setDelimitationEffectVO(delimitationEffectList);
			delimitationEffectList1 = new ArrayList<DelimitationEffectVO>();
			for (DelimitationEffectVO delimitationEffectVO : delimationDetails.getDelimitationEffectVO()) {
				
				DelimitationEffectVO delimitationEffectVO2 = new DelimitationEffectVO();
				
				List<Object[]> allianceParites = allianceGroupDAO.findAlliancePartiesByElectionAndParty(38l, delimitationEffectVO.getPartyId());
				if(allianceParites != null && allianceParites.size() > 0)
				{
					Long presentCount = 0l;
					for (Object[] objects : allianceParites)
					{
						DelimitationEffectVO delimitationEffect = delimationEffectMap.get(objects[3].toString());
						if(delimitationEffect != null)
						{
							delimitationEffectVO2.setPartyId(delimitationEffectVO.getPartyId());
							delimitationEffectVO2.setPartyName(delimitationEffectVO.getPartyName());
							delimitationEffectVO2.setPresentCount(delimitationEffectVO2.getPresentCount() + delimitationEffect.getPresentCount());
							delimitationEffectVO2.setPresentPolledVotes(delimitationEffectVO2.getPresentPolledVotes() + delimitationEffect.getPresentPolledVotes());
							delimitationEffectVO2.setPresentPerc(delimitationEffectVO2.getPresentPerc() + delimitationEffect.getPresentPerc());
							
						}
						
					}
				}
				else
				{
					delimitationEffectVO = delimationEffectMap.get(delimitationEffectVO.getPartyName());
					delimitationEffectVO2.setPartyId(delimitationEffectVO.getPartyId());
					delimitationEffectVO2.setPartyName(delimitationEffectVO.getPartyName());
					delimitationEffectVO2.setPresentCount(delimitationEffectVO.getPresentCount());
					delimitationEffectVO2.setPresentPolledVotes(delimitationEffectVO.getPresentPolledVotes());
					delimitationEffectVO2.setPresentPerc(delimitationEffectVO.getPresentPerc());
					
				}
				if(delimitationEffectVO2.getPartyName() != null)
				{
					delimationEffectMap1.put(delimitationEffectVO.getPartyName(), delimitationEffectVO2);
					delimitationEffectList1.add(delimitationEffectVO2);
				}
				
			}
			//delimationDetails.setPresentElections(delimitationEffectList1);
			
			
			delimitationEffectList2 = new ArrayList<DelimitationEffectVO>();
			for (DelimitationEffectVO delimitationEffectVO : delimationDetails.getDelimitationEffectVO()) {
				
				
					//DelimitationEffectVO delimitationEffectVO2 = new DelimitationEffectVO();
				DelimitationEffectVO delimitationEffectVO2 = delimationEffectMap1.get(delimitationEffectVO.getPartyName());
					if(delimitationEffectVO != null)
					{
						
						List<Object[]> allianceParites = allianceGroupDAO.findAlliancePartiesByElectionAndParty(3l, delimitationEffectVO.getPartyId());
						if(allianceParites != null && allianceParites.size() > 0)
						{
							Long presentCount = 0l;
							for (Object[] objects : allianceParites)
							{
								DelimitationEffectVO delimitationEffect = delimationEffectMap.get(objects[3].toString());
								if(delimitationEffect != null)
								{
									delimitationEffectVO2.setPartyId(delimitationEffectVO.getPartyId());
									delimitationEffectVO2.setPartyName(delimitationEffectVO.getPartyName());
									delimitationEffectVO2.setPreviousCount(delimitationEffectVO2.getPreviousCount() + delimitationEffect.getPreviousCount());
									delimitationEffectVO2.setPreviousPolledVotes(delimitationEffectVO2.getPreviousPolledVotes() + delimitationEffect.getPreviousPolledVotes());
									delimitationEffectVO2.setPreviousPerc(delimitationEffectVO2.getPreviousPerc() + delimitationEffect.getPreviousPerc());
									
								}
								
							}
						}
						else
						{
							delimitationEffectVO = delimationEffectMap.get(delimitationEffectVO.getPartyName());
							delimitationEffectVO2.setPartyId(delimitationEffectVO.getPartyId());
							delimitationEffectVO2.setPartyName(delimitationEffectVO.getPartyName());
							delimitationEffectVO2.setPreviousCount(delimitationEffectVO.getPreviousCount());
							delimitationEffectVO2.setPreviousPolledVotes(delimitationEffectVO.getPreviousPolledVotes());
							delimitationEffectVO2.setPreviousPerc(delimitationEffectVO.getPreviousPerc());
							
						}
						
						if(delimitationEffectVO2.getPartyId() != null)
						{
							delimationEffectMap1.put(delimitationEffectVO.getPartyName(), delimitationEffectVO2);
							delimitationEffectList2.add(delimitationEffectVO2);
						}
					}
					else
					{
						delimationEffectMap1.put(delimitationEffectVO.getPartyName(), delimitationEffectVO2);
					}
				}
				

			//delimationDetails.setPervElections(delimitationEffectList2);
			
			
			String reqParties = IConstants.STATIC_PARTIESFOR_DELIMATION.replace("'", "");
			List<String> partyNames = Arrays.asList(reqParties.split("\\s*,\\s*"));
			List<DelimitationEffectVO> withoutAllianceList = new ArrayList<DelimitationEffectVO>();
			List<DelimitationEffectVO> withAllianceList = new ArrayList<DelimitationEffectVO>();
			for (String partyName : partyNames)
			{
				DelimitationEffectVO withoutAlliance = delimationEffectMap.get(partyName);
				DelimitationEffectVO withAlliance = delimationEffectMap1.get(partyName);
				if(withoutAlliance != null)
				withoutAllianceList.add(withoutAlliance);
				if(withAlliance != null)
				withAllianceList.add(withAlliance);
			}
			delimationDetails.setDelimitationEffectVO(withoutAllianceList);
			delimationDetails.setPresentElections(withAllianceList);
		} catch (Exception e) {
			LOG.error("Exception raised in getDelimationEffectOnConstituency() method in Suggestive Model Service",e);
		}
		
		
		return delimationDetails;		
	}
		
	
	
	public void fillPartyWiseVotersCountAndPercentage(List<Object[]> result,DelimitationEffectVO delimationDetails,Long partyId,Map<String, DelimitationEffectVO> delimationEffectMap,String type,DelimitationEffectVO others)
	{
		try {
			LOG.debug("Enterd into fillPartyWiseVotersCountAndPercentage() method in Suggestive Model Service");
			DecimalFormat df = new DecimalFormat("#.##");
			//String parties = IConstants.STATIC_PARTIESFOR_DELIMATION.replace("'", "");
			String parties = IConstants.STATIC_PARTIES.replace("'", "");
			List<String> partyNames = Arrays.asList(parties.split("\\s*,\\s*"));
			
			Long count = 0l;
			DelimitationEffectVO delimitationEffectVO = null;
			
				for (Object[] parms : result) {
					
					String party = parms[2].toString();
					delimitationEffectVO =  delimationEffectMap.get(party);
					if(delimitationEffectVO == null)
					{
						delimitationEffectVO = new DelimitationEffectVO();
						delimationEffectMap.put(party, delimitationEffectVO);
					}
					if(type.equalsIgnoreCase("after"))
					{
						if(partyNames.contains(party))
						{
							delimitationEffectVO.setPresentCount((Long)parms[1]);
							delimitationEffectVO.setPartyId((Long)parms[0]);
							delimitationEffectVO.setPartyName(parms[2] != null ? parms[2].toString() : "");
							if(delimationDetails.getPresentCount() > 0 && delimationDetails.getPresentPolledVotes() > 0)
							{
								Double percentage = Double.valueOf(df.format(Long.valueOf(delimitationEffectVO.getPresentCount())*100/(float)delimationDetails.getPresentPolledVotes()));
								delimitationEffectVO.setPresentPerc(percentage);
								
							}
						}
						else
						{
							count = count + (Long)parms[1];
							others.setPartyId(0l);
							others.setPresentCount(count);
							others.setPartyName("Others");
							Double percentage = Double.valueOf(df.format(Long.valueOf(count)*100/(float)delimationDetails.getPresentPolledVotes()));
							others.setPresentPerc(percentage);
						
						}
					}
					else
					{
						if(partyNames.contains(party))
						{
							delimitationEffectVO.setPreviousCount((Long)parms[1]);
							delimitationEffectVO.setPartyId((Long)parms[0]);
							delimitationEffectVO.setPartyName(parms[2] != null ? parms[2].toString() : "");
							if(delimationDetails.getPreviousCount() > 0 && delimationDetails.getPreviousPolledVotes() > 0)
							{
								Double percentage = Double.valueOf(df.format(Long.valueOf(delimitationEffectVO.getPreviousCount())*100/(float)delimationDetails.getPreviousPolledVotes()));
								delimitationEffectVO.setPreviousPerc(percentage);
								
							}
						}
						else
						{
							count = count + (Long)parms[1];
							others.setPartyId(0l);
							others.setPreviousCount(count);
							others.setPartyName("Others");
							Double percentage = Double.valueOf(df.format(Long.valueOf(count)*100/(float)delimationDetails.getPreviousPolledVotes()));
							others.setPreviousPerc(percentage);
						
						}
					}
					
				}
			
			
			if(others.getPreviousCount() > 0 || others.getPresentCount() > 0)
			{
				delimationEffectMap.put(others.getPartyName(), others);
			}
			
			
			
		} catch (Exception e) {
			LOG.error("Exception raised in fillPartyWiseVotersCountAndPercentage() method in Suggestive Model Service",e);
		}
		
	}
	
	
	public void fillVotersCountForConstituency(List<Object[]> result,DelimitationEffectVO delimationDetails,String type)
	{
		try {
			LOG.debug("Enterd into fillPartyWiseVotersCountAndPercentage() method in Suggestive Model Service");
			DecimalFormat df = new DecimalFormat("#.##");
			if(type.equalsIgnoreCase("after"))
			{
				for (Object[] parms : result) {
					delimationDetails.setPresentCount((Long)parms[0]);
					delimationDetails.setPresentPolledVotes((Long)parms[1]);
					if(delimationDetails.getPresentCount() >0 && delimationDetails.getPresentPolledVotes() > 0)
					{
						Double percentage = Double.valueOf(df.format(Long.valueOf(delimationDetails.getPresentPolledVotes())*100/(float)delimationDetails.getPresentCount()));
						delimationDetails.setPresentPerc(percentage);
					}
				}
			}
			else
			{
				for (Object[] parms : result) {
					delimationDetails.setPreviousCount((Long)parms[0]);
					delimationDetails.setPreviousPolledVotes((Long)parms[1]);
					if(delimationDetails.getPreviousCount() >0 && delimationDetails.getPreviousPolledVotes() > 0)
					{
						Double percentage = Double.valueOf(df.format(Long.valueOf(delimationDetails.getPreviousPolledVotes())*100/(float)delimationDetails.getPreviousCount()));
						delimationDetails.setPreviousPerc(percentage);
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in fillVotersCountForConstituency() method in Suggestive Model Service",e);
		}
		
	}
	
	public Long getLatestPublicationByConstituency(Long constituencyId)
	{
		Long publicationID = 0l;
		try{
		 //publicationID = publicationDateDAO.getLatestPublicationIdByConstiId(constituencyId);
			publicationID = voterInfoDAO.getLatestPublicationDate(constituencyId);
		}
		catch(Exception e)
		{
			LOG.error("Exception raised in getLatestPublicationByConstituency() method in Suggestive Model Service",e);
			e.printStackTrace();
		}
		return publicationID;
	}
	
	/*public List<VoterHouseInfoVO> getFamilyDetailsForConstituency(Long constituencyId,Long publicationId,Long minValue,Long maxValue,Integer startIndex,Integer maxIndex,Long userId,String path,String seltype)
	{
		List<VoterHouseInfoVO> result = null;
		try{
		List<Long> constituencyIds = new ArrayList<Long>();
		if(seltype.equalsIgnoreCase("district"))
		{
			List<Object[]> constiList =  constituencyDAO.getDistrictConstituencies(constituencyId);
			for (Object[] parms : constiList) {
				constituencyIds.add((Long)parms[0]);
			}
			startIndex = null;
			maxIndex = null;
		}
		else
		{
			constituencyIds.add(constituencyId);
		}
		for (Long id : constituencyIds) {
			result = new ArrayList<VoterHouseInfoVO>();
			constituencyId = id;
			
			List<?> list = partyTrendsDAO.callStoredProcedure();
			 
			 String type = null;
			 for (Object param : list) {
				 VoterHouseInfoVO voterHouseInfoVO = new VoterHouseInfoVO();
				 VoterFamilyCount vc=	(VoterFamilyCount)param;
				 Booth booth = boothDAO.get(vc.getBoothId());
					Constituency consti = constituencyDAO.get((Long)booth.getConstituency().getConstituencyId());
					type = consti.getAreaType();
					
				  voterHouseInfoVO.setTehsilName(vc.getTehsilName());
				  if(vc.getTehsilName().contains(IConstants.MUNCIPLE_ELECTION_TYPE))
					  voterHouseInfoVO.setWardName(vc.getPanchayatName());
				  else
					  voterHouseInfoVO.setPanchayatName(vc.getPanchayatName());
				  voterHouseInfoVO.setHamletName(vc.getHamletName());
				  voterHouseInfoVO.setPartNo(vc.getBoothNo().toString());
				  voterHouseInfoVO.setElderCaste(vc.getCaste() != null ? vc.getCaste() : " ");
				  voterHouseInfoVO.setVoterIdCardNo(vc.getElderVoterIdCardNo());
				  voterHouseInfoVO.setElder(vc.getElderPersonName());
				  voterHouseInfoVO.setElderGender(vc.getEldPersomGender());
				  voterHouseInfoVO.setElderAge(new Long(vc.getElderPersonAge()));
				  voterHouseInfoVO.setHouseNo(vc.getHouseNo());
				  voterHouseInfoVO.setCount(vc.getCount());
				  voterHouseInfoVO.setVoterGroup(vc.getYoungerVoterIdCardNo());
				  voterHouseInfoVO.setYounger(vc.getYoungerPersonName());
				  voterHouseInfoVO.setYoungerGender(vc.getYoungPersomGender());
				  voterHouseInfoVO.setYoungerAge(vc.getYoungerPersonAge());
				  
				  result.add(voterHouseInfoVO);
			  if(list != null && list.size() > 0)
					result.get(0).setConstituencyType(type);
				    result.get(0).setTotalHousesCount(new Long(list.size()));
			 }
				    try{
					    Object[] values = constituencyDAO.constituencyName(constituencyId).get(0);
				    	String constituenyName = values[0].toString().toUpperCase();
				    	String districtName = values[1].toString().toUpperCase();
				    	Long constituenyNo = delimitationConstituencyDAO.getConstituencyNo(constituencyId,2009l);
					    String filePath = "VMR"+"/"+""+districtName+"_"+constituenyNo+"_"+constituenyName+".xls";
					    String FILE = path+filePath;
					    //File file  = new File(FILE);
					    //file.createNewFile();
					    FileOutputStream out = new FileOutputStream(FILE);
					    
						HSSFWorkbook workbook = new HSSFWorkbook(); 
						HSSFSheet sheet  = workbook.createSheet("report");
						ageWiseExcelsGenerationService.generateExcelsForImportaneFamiles(result , sheet, workbook,type);
						workbook.write(out);
						if(result != null && result.size() > 0)
						{
							result.get(0).setInfluencePartyName(filePath);
						}
					  }
					  catch(Exception e)
					  {
						  e.printStackTrace();
					  }
			
		}
		
	}
		catch(Exception e)
		{
			LOG.error("Exception raised in getFamilyDetailsForConstituency() method in Suggestive Model Service",e);
			
		}
		return result;
	}
	*/
	public List<VoterHouseInfoVO> getFamilyDetailsForConstituency(Long constituencyId,Long publicationId,Long minValue,Long maxValue,Integer startIndex,Integer maxIndex,Long userId,String path,String seltype)
	{
		List<VoterHouseInfoVO> result = null;
		try{
		List<Long> constituencyIds = new ArrayList<Long>();
		if(seltype.equalsIgnoreCase("district"))
		{
			List<Object[]> constiList =  constituencyDAO.getDistrictConstituencies(constituencyId);
			for (Object[] parms : constiList) {
				constituencyIds.add((Long)parms[0]);
			}
			startIndex = null;
			maxIndex = null;
		}
		else
		{
			constituencyIds.add(constituencyId);
		}
		for (Long id : constituencyIds)
		{
			result = new ArrayList<VoterHouseInfoVO>();
			constituencyId = id;
			List<Object[]> list = boothPublicationVoterDAO.getHouseNosForBooth(constituencyId,publicationId,minValue,maxValue,startIndex,maxIndex);
			List<Object[]> totalList = boothPublicationVoterDAO.getHouseNosForBooth(constituencyId,publicationId,minValue,maxValue,null,null);
			Map<Long,List<String>> boothHousesMap = new HashMap<Long, List<String>>();
			Map<Long,String> wardMap = new HashMap<Long, String>();
			String type = null;
			String dname = null;
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					List<String> hnos = boothHousesMap.get((Long)params[0]);
					if(hnos == null)
					{
						hnos = new ArrayList<String>();
						boothHousesMap.put((Long)params[0], hnos);
					}
					if(!hnos.contains(params[1].toString()))
						hnos.add(params[1].toString().toUpperCase());
				}
			}
			
			for(Long boothId :boothHousesMap.keySet())
			{ //1
				Map<Long,VoterHouseInfoVO> basicInfo = new HashMap<Long,VoterHouseInfoVO>();
				Booth booth = boothDAO.get(boothId);
				Constituency consti = constituencyDAO.get((Long)booth.getConstituency().getConstituencyId());
				type = consti.getAreaType();
				dname = consti.getDistrict().getDistrictName();
			
				if(booth != null)
				{ //2
					String tehsil ="";
					String panchayat = "";
					String hamlet = "";
					String ward = "";
					String localbody = "";
					String hamletName = "";
					String wardName= "";
					
					VoterHouseInfoVO votervo = new VoterHouseInfoVO();
					
					if(type.equalsIgnoreCase(IConstants.CONST_TYPE_RURAL))
					{
						tehsil = booth.getTehsil().getTehsilName();
						if(booth.getPanchayat() != null)
							panchayat =	booth.getPanchayat().getPanchayatName();
					}
					
					else if(type.equalsIgnoreCase(IConstants.CONST_TYPE_RURAL_URBAN))
					{
						if (booth.getLocalBody() != null && booth.getLocalBody().getName() != null)
							localbody = booth.getLocalBody().getName();
						else
							tehsil = booth.getTehsil().getTehsilName();	
						
						if(booth.getPanchayat() != null)
							panchayat = booth.getPanchayat().getPanchayatName();
					
						if(localbody != null && localbody != "")
						{
							if(booth.getLocalBody().getElectionType() != null)
							{
								String electionType = booth.getLocalBody().getElectionType().getElectionType();
								
								if(electionType.equalsIgnoreCase(IConstants.GHMC) || electionType.equalsIgnoreCase(IConstants.CORPORATION_ELECTION_TYPE))
								{
									tehsil = localbody +" Corporation";
									
									if(booth.getLocalBodyWard() != null && booth.getLocalBodyWard().getName() != null)
										wardName = booth.getLocalBodyWard().getName();
								}
						
								else if(electionType.equalsIgnoreCase(IConstants.MUNCIPLE_ELECTION_TYPE))
								{
									tehsil = localbody +" Muncipality";
									if(booth.getLocalBody() != null && booth.getLocalBody().getLocalElectionBodyId() != null)
									{
										List<Object> assemblyLocalbodyId = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(booth.getLocalBody().getLocalElectionBodyId().toString()));
										List<Object[]> wardNames = userVoterDetailsDAO.getWardBYLocalElectionBodyId((Long) assemblyLocalbodyId.get(0) ,publicationId ,userId);
										
										if(wardNames != null && wardNames.size() > 0)
											for(Object[] wardparams : wardNames)
											{
												wardMap.put((Long)wardparams[0],wardparams[1]!= null ?  wardparams[1].toString() :"");
											}
									}
								}
							}
						
						}
					
					}
					else if(type.equalsIgnoreCase(IConstants.CONST_TYPE_URBAN))
					{
						if (booth.getLocalBody() != null)
							localbody = booth.getLocalBody().getName();
			           
						if(localbody != null && localbody != "")
						{
							if( booth.getLocalBody().getElectionType() != null)
							{
								String electionType = booth.getLocalBody().getElectionType().getElectionType();
								
								if(electionType.equalsIgnoreCase(IConstants.GHMC) || electionType.equalsIgnoreCase(IConstants.CORPORATION_ELECTION_TYPE))
								{
									tehsil = localbody +" Corporation";
									if(booth.getLocalBodyWard() != null)
										wardName = booth.getLocalBodyWard().getName();
								}
					      
								else if(electionType.equalsIgnoreCase(IConstants.MUNCIPLE_ELECTION_TYPE))
								{
									tehsil = localbody +" Muncipality";
									if(booth.getLocalBody() != null && booth.getLocalBody().getLocalElectionBodyId() != null)
									{
										List<Object> assemblyLocalbodyId = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(booth.getLocalBody().getLocalElectionBodyId().toString()));
										List<Object[]> wardNames = userVoterDetailsDAO.getWardBYLocalElectionBodyId((Long) assemblyLocalbodyId.get(0) ,publicationId ,userId);
										
										if(wardNames != null && wardNames.size() > 0)
											for(Object[] wardparams : wardNames)
											{
												wardMap.put((Long)wardparams[0],wardparams[1]!= null ?  wardparams[1].toString() :"");
											}
									}
							
								}
							}
						}
					
					}
					votervo.setTehsilName(tehsil);
					votervo.setPanchayatName(panchayat);
					votervo.setHamletName(hamletName);
					votervo.setWardName(wardName);
					basicInfo.put(boothId, votervo);
				
			}//2	
			
				List<Object[]> list1 = boothPublicationVoterDAO.getFamilyWiseInfoForBooth(boothId, boothHousesMap.get(boothId));
				Map<String,List<VoterHouseInfoVO>> resultmap = new HashMap<String, List<VoterHouseInfoVO>>();
				List<Long> voterIds = new ArrayList<Long>();
			  
				for(Object[] params1 :list1)
				{
					List<VoterHouseInfoVO> voterDetails = resultmap.get(params1[0].toString());
					Voter voter = (Voter) params1[1];
					VoterHouseInfoVO vo = new VoterHouseInfoVO();
				  
					if(voterDetails == null)
					{
						voterDetails = new ArrayList<VoterHouseInfoVO>();
						resultmap.put(params1[0].toString(), voterDetails);
					}
					
					vo.setVoterIdCardNo(voter.getVoterIDCardNo());
					vo.setVoterId(voter.getVoterId());
					vo.setName(voter.getName());
					vo.setAge(voter.getAge());
					vo.setGender(voter.getGender());
					vo.setHouseNo("# " +voter.getHouseNo());
					vo.setRelationship(voter.getRelationshipType());
					voterIds.add(voter.getVoterId());
					voterDetails.add(vo);
				}
			 
				Map<Long,String> voterCaste = new HashMap<Long, String>();
				Map<Long,String> voterHamlet = new HashMap<Long, String>();
				List<Object[]> casteInfo =  userVoterDetailsDAO.getCasteForVoter(voterIds);
				List<Object[]> hamletInfo =  userVoterDetailsDAO.getHamletForVoter(voterIds,1L);
				
				if(casteInfo != null && casteInfo.size() > 0)
				for(Object[] casteName : casteInfo)
				{
					voterCaste.put((Long)casteName[0],casteName[1].toString());
				}
				
				if(hamletInfo != null && hamletInfo.size() > 0)
				for(Object[] hn : hamletInfo)
					voterHamlet.put(((BigInteger)hn[0]).longValue(),hn[1].toString());
					
				for(String houseNo : resultmap.keySet())
				{
					  VoterHouseInfoVO voterHouseInfoVO = new VoterHouseInfoVO();
					  voterHouseInfoVO.setBoothId(boothId);
					  voterHouseInfoVO.setPartNo(boothDAO.get(boothId).getPartNo());
					  voterHouseInfoVO.setHouseNo("# " +houseNo);
					  List<VoterHouseInfoVO> voterDetails = resultmap.get(houseNo.toString());
					 
					  voterHouseInfoVO.setCount(new Long(voterDetails.size()));
					  voterHouseInfoVO.setElder(voterDetails.get(voterDetails.size()-1).getName());
					  voterHouseInfoVO.setElderAge((voterDetails.get(voterDetails.size()-1).getAge()));
					  voterHouseInfoVO.setElderGender(voterDetails.get(voterDetails.size()-1).getGender());
					  voterHouseInfoVO.setVoterIdCardNo(voterDetails.get(voterDetails.size()-1).getVoterIdCardNo().toString());
					  voterHouseInfoVO.setRelationship(voterDetails.get(voterDetails.size()-1).getRelationship().toString());
					  String cast = voterCaste.get(voterDetails.get(voterDetails.size()-1).getVoterId());
					  String hname = voterHamlet.get(voterDetails.get(voterDetails.size()-1).getVoterId());
					  voterHouseInfoVO.setElderCaste(cast != null ? cast : " ");
					  VoterHouseInfoVO basicVo = basicInfo.get(boothId);
					
					  voterHouseInfoVO.setTehsilName(basicVo.getTehsilName());
					  
					  if(basicVo.getTehsilName().contains("Muncipality") && wardMap != null)
						  voterHouseInfoVO.setWardName(wardMap.get(basicVo.getVoterId()) != null ?wardMap.get(basicVo.getVoterId()) : "");
					  else if(basicVo.getTehsilName().contains("Corporation"))  
						  voterHouseInfoVO.setWardName(basicVo.getWardName() !=null ? basicVo.getWardName().toString() : "");
					  
					  voterHouseInfoVO.setPanchayatName(basicVo.getPanchayatName());
					  voterHouseInfoVO.setHamletName(hname != null ? hname : "");
					  
					  boolean flag = false;
					  
					  List<VoterHouseInfoVO> youngervoterDetails  = voterDetails;
				 
						for(int i=0;i<youngervoterDetails.size()-1;i++)
						{
							if(youngervoterDetails.get(i).getGender().equalsIgnoreCase("M"))
							{
								voterHouseInfoVO.setYounger(youngervoterDetails.get(i).getName());
								voterHouseInfoVO.setYoungerAge(youngervoterDetails.get(i).getAge());
								voterHouseInfoVO.setYoungerGender(youngervoterDetails.get(i).getGender());
								voterHouseInfoVO.setVoterGroup(youngervoterDetails.get(i).getVoterIdCardNo().toString());
								flag = true;
							}
						}
						if(!flag)
						{
							voterHouseInfoVO.setYounger(youngervoterDetails.get(0).getName());
							voterHouseInfoVO.setYoungerAge(youngervoterDetails.get(0).getAge());
							voterHouseInfoVO.setYoungerGender(youngervoterDetails.get(0).getGender());
							voterHouseInfoVO.setVoterGroup(youngervoterDetails.get(0).getVoterIdCardNo().toString());
						}
						
						String relation = "";
						if(voterHouseInfoVO.getRelationship() == null)
				    		   relation = "C/O";
						
						 else if(voterHouseInfoVO.getRelationship().equalsIgnoreCase("Mother") || 
								 voterHouseInfoVO.getRelationship().equalsIgnoreCase("Father"))
						 {
			    		   if(voterHouseInfoVO.getElderGender().equalsIgnoreCase(IConstants.MALE))
			    			   relation = "S/O";
			    		   else
			    			   relation = "D/O";
						 }
						 else if(voterHouseInfoVO.getRelationship().equalsIgnoreCase("Husband"))
				    		   relation = "W/O";
						 else if(voterHouseInfoVO.getRelationship().equalsIgnoreCase("Others"))
				    		   relation = "C/O";
				    	   
				    	   relation = (relation + " " +voterHouseInfoVO.getElder()).trim();
				    	
				    	 String addrStr = voterHouseInfoVO.getElder()+",\n";
				    	 addrStr = addrStr + relation +",\n";
				    	 addrStr += "H.No: "+voterHouseInfoVO.getHouseNo() +",\n";
				    	 
				    	 if(voterHouseInfoVO.getHamletName() != null)
				    		   addrStr +=voterHouseInfoVO.getHamletName()+",\n";
				    	 
				    	 if(voterHouseInfoVO.getPanchayatName() != null)
				    		   addrStr += voterHouseInfoVO.getPanchayatName()+",\n";
				    	   
				    	   if(voterHouseInfoVO.getTehsilName() != null)
				    		   addrStr += voterHouseInfoVO.getTehsilName() + " (Mandal),\n";
				    	   
				    	   addrStr += dname+" (District)";
				    	   
				    	   String pincode = boothDAO.get(boothId).getPincode();
				    	   
				    	   if(pincode != null)
				    		   addrStr += " - "+pincode;
				    	
				    	voterHouseInfoVO.setAddress(addrStr);
						result.add(voterHouseInfoVO);
						if(totalList != null && totalList.size() > 0)
						result.get(0).setConstituencyType(type);
					    result.get(0).setTotalHousesCount(new Long(totalList.size()));
				 }
			  
				
			  /*Document document = null;
			  try
			  {
				   document = new Document();

	    			Object[] values = constituencyDAO.constituencyName(constituencyId).get(0);
	    	    	String constituenyName = values[0].toString().toUpperCase();
	    	    	String districtName = values[1].toString().toUpperCase();
	    	    	Long constituenyNo = delimitationConstituencyDAO.getConstituencyNo(constituencyId,2009l);
	    		    String filePath = "VMR"+"/"+""+districtName+"_"+constituenyNo+"_"+constituenyName+" IMP Familes.pdf";
	    		    String FILE = path+filePath;
	    		    File file  = new File(FILE);
	    		    file.createNewFile();
	    		    PdfWriter.getInstance(document, new FileOutputStream(FILE));
	    			document.open();
	    			voterModifiationPdfsGenerations.generatePdfsForImpFamiles(document,result,constituencyDAO.get(constituencyId).getName());
	    			document.close();
	    			  				
	    			    FileOutputStream out = new FileOutputStream(FILE);
	    			    
	    				HSSFWorkbook workbook = new HSSFWorkbook(); 
	    				HSSFSheet sheet  = workbook.createSheet("report");
	    				ageWiseExcelsGenerationService.generateExcelsForImportaneFamiles(result , sheet, workbook,constituenyName);
	    				workbook.write(out);
	    				result.get(0).setInfluencePartyName(filePath);
	    			
			  } 
			  catch (Exception e)
			  {
				
			  }
			  finally
			  {
				  if(document != null)
				  document.close();
			  }*/
			  
			 } //1
			
			try{
				Object[] values = constituencyDAO.constituencyName(constituencyId).get(0);
				String constituenyName = values[0].toString().toUpperCase();
				String districtName = values[1].toString().toUpperCase();
				Long constituenyNo = delimitationConstituencyDAO.getConstituencyNo(constituencyId,2009l);
				String filePath = "Family_Details"+"/"+""+districtName+"_"+constituenyNo+"_"+constituenyName+".xls";
				String FILE = path+filePath;
		    //File file  = new File(FILE);
		    //file.createNewFile();
				FileOutputStream out = new FileOutputStream(FILE);
				HSSFWorkbook workbook = new HSSFWorkbook(); 
				
				if(result.size() <= 65000)
				{
					HSSFSheet sheet  = workbook.createSheet("report");
					ageWiseExcelsGenerationService.generateExcelsForImportaneFamiles(result , sheet, workbook,type);
				}
				else
				{
					Integer sheetIndex = 1;
					int fromIndex = 0;
					int toIndex = 65000;
					for(;;)
					{
						if(fromIndex >= result.size())
							break;
						if(toIndex > result.size())
							toIndex = result.size();
						HSSFSheet sheet  = workbook.createSheet("report"+sheetIndex.toString());
						ageWiseExcelsGenerationService.generateExcelsForImportaneFamiles(result.subList(fromIndex, toIndex) , sheet, workbook,type);
						fromIndex+=65000;
						toIndex+=65000;
						sheetIndex++;
					}
				}
				
				workbook.write(out);
				result =  new ArrayList<VoterHouseInfoVO>();
				VoterHouseInfoVO info = new VoterHouseInfoVO();
				result.add(info);
				if(result != null && result.size() > 0)
				{
					result.get(0).setInfluencePartyName(filePath);
				}
			}
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		}
		
	}
		catch(Exception e)
		{
			LOG.error("Exception raised in getFamilyDetailsForConstituency() method in Suggestive Model Service",e);
			
		}
		return result;
	}
	
	public List<PartyTrendsVO> calculateOrderOfPriorityForConstituency(Long userId,List<Long> constituencyIds,List<Long> casteIdsList,List<ExceptCastsVO> exceptCasteList,List<SelectOptionVO> groups,List<ExceptCastsVO> exceptCasteMncplList,String party,List<Long> electionIds,Long partyId,SuggestedLocationsVO weigthPerc){
		//double casteWeigthPerc = weigthPerc.getCasteWeight();
		double prevTrendWeigthPerc = 0d;//weigthPerc.getPrevTrendWeight();
		//double agedVotrsWeigthPerc = weigthPerc.getAgedVotrWeight();
		float youngVotrsWeigthPerc = 10.00f;//weigthPerc.getYoungVotrWeight();
		Double regainVotrsWeigthPerc = 0d;//weigthPerc.getRegainableWeight();
		List<PartyTrendsVO> returnVal = new ArrayList<PartyTrendsVO>();
	  for(Long constituencyId:constituencyIds){
		 try{
		  LOG.info("Enter in to calc for consti Id "+constituencyId);
		  regainVotrsWeigthPerc = prpWeightegesDAO.getPRPWeightageByConstiId(constituencyId);
		  if(regainVotrsWeigthPerc == null || regainVotrsWeigthPerc == 0d)
			  regainVotrsWeigthPerc = 0d;
		  prevTrendWeigthPerc = 90d-regainVotrsWeigthPerc;
		  LOG.info("prevTrendWeigthPerc: "+prevTrendWeigthPerc+" regainVotrsWeigthPerc: "+regainVotrsWeigthPerc);
			//Map<String ,Map<String,PartyImpactVO>> prpImpact = getElectionResultsForSelectedElectionsForAConsttituency(constituencyId,"TDP");//prp impac
			Map<Long,PartyEffectVO> prpEffect = new HashMap<Long,PartyEffectVO>();
			List<PartyPositionVO> orderOfPriority = getPartyPerformenceReportNew(constituencyId,prpEffect);//order of prio
			
			
			SuggestiveModelVO suggestiveModelVO = new SuggestiveModelVO();
			Map<Long,SuggestedLocationsVO> panchayatLocs = new HashMap<Long,SuggestedLocationsVO>();
			//Map<String,SuggestedLocationsVO> panchayatNames = new HashMap<String,SuggestedLocationsVO>();
			
			//calculations for Previous trends
			Constituency consti = constituencyDAO.get(constituencyId);
			calculateWeightsForPreviousTrents(orderOfPriority,panchayatLocs,prevTrendWeigthPerc);
			getYoungVotersWeight(constituencyId,youngVotrsWeigthPerc,consti.getAreaType(),panchayatLocs);
			//calculations for caste
			//calculateWeightsForCasts(casteData,panchayatLocs,panchayatNames,casteWeigthPerc);
			
			//calculations for younger voters
			//List<PanchayatVO> youngVoters = youngOldVoters.get(0).getPanchayatList();
			//List<PanchayatVO> oldVoters = youngOldVoters.get(1).getPanchayatList();
			//calculateWeightsForYoungAndOldVoters(youngVoters,panchayatLocs,panchayatNames,youngVotrsWeigthPerc,"young");
			//calculateWeightsForYoungAndOldVoters(oldVoters,panchayatLocs,panchayatNames,agedVotrsWeigthPerc,"old");
			
			//calculations for prp impact
			calculateWeightsForPrpImpact(prpEffect,panchayatLocs,regainVotrsWeigthPerc);
			
			//final calculation
			List<SuggestedLocationsVO> locations = new ArrayList<SuggestedLocationsVO>(panchayatLocs.values());
			//calculateFinalOrderOfPriority(locations);
			
			//suggestiveModelVO.setCasteData(casteData);
			//suggestiveModelVO.setYoungOldVoters(youngOldVoters);
			//suggestiveModelVO.setPrpImpact(prpImpact);
			suggestiveModelVO.setOrderOfPriority(orderOfPriority);
			suggestiveModelVO.setSuggestedLocations(locations);
			
		    for(SuggestedLocationsVO location:locations){
		    	PartyTrendsVO trend = new PartyTrendsVO();
		    	trend.setConstituencyId(constituencyId);
		    	trend.setId(location.getId());
		    	trend.setLocName(location.getType());
		    	trend.setConstituencyName(consti.getName());
		    	trend.setName(location.getName());
		    	if(location.getPrevTrendWeight() != null)
		    	  trend.setPervTrenzWt(location.getPrevTrendWeight().floatValue());
		    	else
		    	  trend.setPervTrenzWt(0f);
		    	if(location.getRegainableWeight() != null)
		    	  trend.setPrpWt(location.getRegainableWeight().floatValue());
		    	else
		    	  trend.setPrpWt(0f);
		    	if(location.getYoungVotrWeight() != null)
		    	  trend.setYoungVotersWt(location.getYoungVotrWeight().floatValue());
		    	else
			      trend.setYoungVotersWt(0f);
		    	trend.setTotalWt(new BigDecimal(trend.getPervTrenzWt()+trend.getPrpWt()+trend.getYoungVotersWt()).setScale(4, BigDecimal.ROUND_HALF_UP).floatValue());
		    	returnVal.add(trend);
		    	
		    }
		    Collections.sort(returnVal,finalLocationsSort);
		    int totalCount = returnVal.size();
			for(int i =1;i<=totalCount;i++){
				PartyTrendsVO pt = returnVal.get(i-1);
				Long priorty = new Long((i*100)/totalCount);
				if(priorty.longValue() == 0l)
					priorty = 1l;
				pt.setPriority(priorty);
				constituencyDAO.flushAndclearSession();
			}
		 }catch(Exception e){
			 LOG.error("Exception rised in calculation for constiId "+constituencyId+" ",e);
		 }
	  }
	  return returnVal;
	}
	
	
		  
	public void calculateWeightsForPreviousTrents(List<PartyPositionVO> orderOfPriority,Map<Long,SuggestedLocationsVO> panchayatLocs,double prevTrendWeigthPerc){
		if(orderOfPriority != null && orderOfPriority.size() > 0 && orderOfPriority.get(0) != null ){
			for(PartyPositionVO location : orderOfPriority.get(0).getSuggestedLocations()){
				
				SuggestedLocationsVO previousTrend = getSuggestedLocation1(location.getId(),location.getName(),panchayatLocs,location.getType());
								
				double priorityVal = 0.0d;
				int priorityAreasCount = 0;
				int priorityCount = orderOfPriority.get(0).getPartyPositionVOList().size();
				if(priorityCount > 4){
					priorityAreasCount = (2*priorityCount)+(priorityCount-4);
				}else{
					priorityAreasCount = (2*priorityCount);
				}
				priorityVal = new BigDecimal(100.00d/priorityAreasCount).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
				previousTrend.setPrevTrendPoint(new BigDecimal((priorityAreasCount+1-location.getPriorityOrder())*priorityVal).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
				previousTrend.setPrevTrendWeight(new BigDecimal(previousTrend.getPrevTrendPoint()*prevTrendWeigthPerc/100).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
				if(location.getPartyPercentage() != null)
				  previousTrend.setPartyPercentage(location.getPartyPercentage());
				location.setPrevTrendPoint(previousTrend.getPrevTrendPoint());
			}
			for(PartyPositionVO priority:orderOfPriority.get(0).getPartyPositionVOList()){
				for(PartyPositionVO panchayat:priority.getPartyPositionVOList()){
					SuggestedLocationsVO previousTrend = getSuggestedLocation1(panchayat.getId(),panchayat.getName(),panchayatLocs,panchayat.getType());
					if(panchayat.getSelectedPartyTotalVoters() != null)
					  previousTrend.setPartySecured(panchayat.getSelectedPartyTotalVoters());
				}
			}
		}
	}
	
	public void calculateWeightsForCasts(List<YouthLeaderSelectionVO> casteData,Map<Long,SuggestedLocationsVO> panchayatLocs,Map<String,SuggestedLocationsVO> panchayatNames,double casteWeigthPerc){
		for(YouthLeaderSelectionVO casteDetails:casteData){
			Long totalTargetCount = 0l;
			Double totalTargetPerc = 0.0d;
			if(casteDetails.getExceptdCateDetails() != null)
				for(BasicVO targetCaste : casteDetails.getExceptdCateDetails()){
					totalTargetCount+=targetCaste.getExpCount();
				}
			if(casteDetails.getPanchayatTotalVoters() != null && casteDetails.getPanchayatTotalVoters() > 0)
			totalTargetPerc = new BigDecimal((float)totalTargetCount*100/casteDetails.getPanchayatTotalVoters()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			
			SuggestedLocationsVO previousTrend = getSuggestedLocation(casteDetails.getPanchayatId(),casteDetails.getPanchayatName(),panchayatLocs,panchayatNames);
		    if(casteDetails.getPanchayatTotalVoters() != null)
			 previousTrend.setCurrentVoters(casteDetails.getPanchayatTotalVoters());
		    else
		     previousTrend.setCurrentVoters(0l);
			previousTrend.setTarget(totalTargetCount);
			previousTrend.setTotalTargetPerc(totalTargetPerc);
			previousTrend.setPartyDiffForCaste(previousTrend.getTotalTargetPerc()-previousTrend.getPartyPercentage());
		}
		
		List<SuggestedLocationsVO> casteList = new ArrayList<SuggestedLocationsVO>(panchayatLocs.values());
		Collections.sort(casteList,locationSort);
		Double leastValue = casteList.get(casteList.size()-1).getPartyDiffForCaste();
		if(leastValue < 0){
			leastValue = leastValue*(-1);
			for(int i=0;i<casteList.size();i++){
				SuggestedLocationsVO data = casteList.get(i);
				data.setDiffWithOutNegVal(data.getPartyDiffForCaste()+leastValue);
				if(casteList.get(0).getDiffWithOutNegVal() != null && casteList.get(0).getDiffWithOutNegVal() > 0)
				  data.setCastePoint(new BigDecimal(data.getDiffWithOutNegVal()*100/casteList.get(0).getDiffWithOutNegVal()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				data.setCasteWeight(new BigDecimal(data.getCastePoint()*casteWeigthPerc/100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
		}
		
	}
	
	public void calculateWeightsForYoungAndOldVoters(List<PanchayatVO> youngOldVoters,Map<Long,SuggestedLocationsVO> panchayatLocs,Map<String,SuggestedLocationsVO> panchayatNames,double youngOldWeigthPerc,String type){
		for(PanchayatVO youngOldVoter : youngOldVoters){
			Long totalTargetCount = new Long(youngOldVoter.getOthrExpctdVotes());
			for(CastVO selectedCaste:youngOldVoter.getAllSelectedCastes()){
				totalTargetCount+=selectedCaste.getExpctdVotesCount();
			}
			youngOldVoter.setTotalTargetCount(totalTargetCount);
			if(youngOldVoter.getTotalVoters() > 0)
			  youngOldVoter.setTargetPerc(new BigDecimal((float)totalTargetCount*100/youngOldVoter.getTotalVoters()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		Collections.sort(youngOldVoters,youngOldVoterSort);
		for(int i=0;i<youngOldVoters.size();i++){
			PanchayatVO youngOldVoter = youngOldVoters.get(i);
			if(youngOldVoters.get(0).getTargetPerc() != null && youngOldVoters.get(0).getTargetPerc() > 0)
			 youngOldVoter.setVoterPoints(new BigDecimal(youngOldVoter.getTargetPerc()*100/youngOldVoters.get(0).getTargetPerc()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            
            SuggestedLocationsVO youngOld = getSuggestedLocation(youngOldVoter.getPanchayatId(),youngOldVoter.getPanchayatName(),panchayatLocs,panchayatNames);
				
			if("young".equalsIgnoreCase(type)){
				youngOld.setYoungVotrPoint(youngOldVoter.getVoterPoints());
				youngOld.setYoungVotrWeight(new BigDecimal(youngOldVoter.getVoterPoints()*youngOldWeigthPerc/100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}else{
				youngOld.setAgedVotrPoint(youngOldVoter.getVoterPoints());
				youngOld.setAgedVotrWeight(new BigDecimal(youngOldVoter.getVoterPoints()*youngOldWeigthPerc/100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
		}
	}
	
	public void calculateWeightsForPrpImpact(Map<Long,PartyEffectVO> prpEffect,Map<Long,SuggestedLocationsVO> panchayatLocs,double regainVotrsWeigthPerc){
		for(Long location:prpEffect.keySet()){
			PartyEffectVO locationVo = prpEffect.get(location);
			if(locationVo.getPrpCurrentPerc() != null && locationVo.getTdpCurrentPerc() != null && locationVo.getTdpPrevPerc() != null){
				Double prpPerc = locationVo.getPrpCurrentPerc();
				Double partyDiff = null;
			
					
					Double partyPrev = locationVo.getTdpPrevPerc();
					Double partyCurrent = locationVo.getTdpCurrentPerc();
				    partyDiff = partyCurrent-partyPrev;
				   if(partyDiff < 0){
					partyDiff = partyDiff*(-1);
					if(prpPerc > partyDiff){
						locationVo.setDifference(partyDiff);
					}else{
						locationVo.setDifference(prpPerc);
					}
				}
			}
		}
		for(Long location:prpEffect.keySet()){
			PartyEffectVO locationVo = prpEffect.get(location);
			if(locationVo.getDifference() == null){
				locationVo.setDifference(0d);
			}
			SuggestedLocationsVO locVo = panchayatLocs.get(location);
			if(locVo != null){
				locVo.setPrpEffect(locationVo.getDifference());
			}
		}
		List<SuggestedLocationsVO> panchayats = new ArrayList<SuggestedLocationsVO>(panchayatLocs.values());
		Collections.sort(panchayats,prpEffectSort);
		if(panchayats.size() > 0 && panchayats.get(0).getPrpEffect() != null && panchayats.get(0).getPrpEffect() > 0){
			for(int i=0;i<panchayats.size();i++){
				SuggestedLocationsVO data = panchayats.get(i);
				if(panchayats.get(0).getPrpEffect() != null && panchayats.get(0).getPrpEffect() > 0)
				  data.setRegainablePoint(new BigDecimal(data.getPrpEffect()*100/panchayats.get(0).getPrpEffect()).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
				data.setRegainableWeight(new BigDecimal(data.getRegainablePoint()*regainVotrsWeigthPerc/100).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
				
			}
		}	
	}
	
	public void calculateFinalOrderOfPriority(List<SuggestedLocationsVO> locations){
		DecimalFormat df = new DecimalFormat("#.##");
		for(SuggestedLocationsVO location:locations){
			location.setTotalWeight(Double.valueOf(df.format(location.getCasteWeight()+location.getPrevTrendWeight()+location.getAgedVotrWeight()+location.getYoungVotrWeight()+location.getRegainableWeight())));
			location.setDifference(location.getPartySecured()-location.getTarget());
			if(location.getCurrentVoters() != null && location.getCurrentVoters() != 0){
				location.setDifferencePerc(new BigDecimal((float)location.getDifference()*100/location.getCurrentVoters()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
		}
		Collections.sort(locations,finalSort);
	}
	
	public SuggestedLocationsVO getSuggestedLocation(Long id,String name,Map<Long,SuggestedLocationsVO> panchayatLocs,Map<String,SuggestedLocationsVO> panchayatNames){
		SuggestedLocationsVO location = panchayatLocs.get(id);
		
		if(location == null){
			location = new SuggestedLocationsVO();
			location.setId(id);
			location.setName(name);
			panchayatLocs.put(id, location);
			panchayatNames.put(name, location);
		}
		
		return location;
	}
	public SuggestedLocationsVO getSuggestedLocation1(Long id,String name,Map<Long,SuggestedLocationsVO> panchayatLocs,String type){
		SuggestedLocationsVO location = panchayatLocs.get(id);
		
		if(location == null){
			location = new SuggestedLocationsVO();
			location.setId(id);
			location.setName(name);
			location.setType(type);
			panchayatLocs.put(id, location);
		}
		
		return location;
	}
	public static Comparator<SuggestedLocationsVO> locationSort = new Comparator<SuggestedLocationsVO>()
			{
				  
			  public int compare(SuggestedLocationsVO loc1, SuggestedLocationsVO loc2)
				{
				   return (loc2.getPartyDiffForCaste().compareTo(loc1.getPartyDiffForCaste()));
				}
		  };
		  
    public static Comparator<PanchayatVO> youngOldVoterSort = new Comparator<PanchayatVO>()
		  {
						  
		    public int compare(PanchayatVO loc1, PanchayatVO loc2)
			    {
				   return (loc2.getTargetPerc().compareTo(loc1.getTargetPerc()));
				}
	     };
	     
    public static Comparator<PartyTrendsVO> partyTrendsSort = new Comparator<PartyTrendsVO>()
 		 {
 				  
 			  public int compare(PartyTrendsVO loc1, PartyTrendsVO loc2)
 				{
 				   return (loc2.getTotalWt().compareTo(loc1.getTotalWt()));
 				}
 		  };
 		 public static Comparator<SuggestedLocationsVO> prpEffectSort = new Comparator<SuggestedLocationsVO>()
 		 		 {
 		 				  
 		 			  public int compare(SuggestedLocationsVO loc1, SuggestedLocationsVO loc2)
 		 				{
 		 				   return (loc2.getPrpEffect().compareTo(loc1.getPrpEffect()));
 		 				}
 		 		  };		  
	public static Comparator<SuggestedLocationsVO> finalSort = new Comparator<SuggestedLocationsVO>()
		 {
						  
		   public int compare(SuggestedLocationsVO loc1, SuggestedLocationsVO loc2)
			   {
				  return (loc2.getTotalWeight().compareTo(loc1.getTotalWeight()));
			}
	    };
	    
	  public void getYoungVotersWeight(Long constituencyId,Float weightPerc,String constituencyType,Map<Long,SuggestedLocationsVO> panchayatLocs){
		 
		  
		  Map<Long,Long> totalVotersMap = new HashMap<Long,Long>();
		  Map<Long,Long> youngVotersMap = new HashMap<Long,Long>();
		  Map<Long,String> namesMap = new HashMap<Long,String>();
		  List<Long> lclIds = new ArrayList<Long>();
		  List<PanchayatVO> orderList = new ArrayList<PanchayatVO>();
		  PanchayatVO vo = null;
		  Long publicationId = 10l;//voterInfoDAO.getLatestPublicationDate(constituencyId);
		  Set<Long> partialIds = new HashSet<Long>();
		  List<Object[]> partialBooths = partialBoothPanchayatDAO.getPartialPanchayatsList(constituencyId,publicationId);
			for(Object[] panchayat:partialBooths){
				if(panchayat[0] !=null){
					partialIds.add((Long)panchayat[0]);
				}
				if(panchayat[1] !=null){
					partialIds.add((Long)panchayat[1]);
				}
			}
			List<Object[]> totalVoters = null;
			List<Object[]> youngVoters = null;
		 if(partialIds.size() > 0){
			  totalVoters = boothPublicationVoterDAO.getConstiPanchayatVoters(constituencyId, publicationId,partialIds, false);
			  youngVoters = boothPublicationVoterDAO.getConstiPanchayatVoters(constituencyId, publicationId,partialIds, true);
			  List<Object[]> totalPartialVoters = boothPublicationVoterDAO.getConstiPanchayatVotersForPartial(constituencyId, publicationId,partialIds, false);
			  totalVoters.addAll(totalPartialVoters);
			  List<Object[]> youngPartialVoters = boothPublicationVoterDAO.getConstiPanchayatVotersForPartial(constituencyId, publicationId,partialIds, true);
			  youngVoters.addAll(youngPartialVoters);
		 }else{
			  totalVoters = boothPublicationVoterDAO.getConstiPanchayatVoters(constituencyId, publicationId,null, false);
			  youngVoters = boothPublicationVoterDAO.getConstiPanchayatVoters(constituencyId, publicationId,null, true);
		 }
		 for(Object[] voters:totalVoters){
			 totalVotersMap.put((Long)voters[1], (Long)voters[0]);
		 }
		 List<Object[]> panchayatNames = panchayatDAO.getPanchayatsByPanchayatIdsListAlongMandal(new ArrayList<Long>(totalVotersMap.keySet()));
		 for(Object[] name:panchayatNames){
		 namesMap.put((Long)name[0], name[1].toString());
		 }
		 if(constituencyType.equalsIgnoreCase("RURAL-URBAN")){
        	 List<Object[]> lclTotalVoters = boothPublicationVoterDAO.getConstiLEBVoters(constituencyId,publicationId,false);
 			 List<Object[]> lclYoungVoters = boothPublicationVoterDAO.getConstiLEBVoters(constituencyId,publicationId,true);
			if(lclTotalVoters != null){
				totalVoters.addAll(lclTotalVoters);
				List<Long> lebIds = new ArrayList<Long>();
				for(Object[] lebVoters:lclTotalVoters){
					lebIds.add((Long)lebVoters[1]);
					totalVotersMap.put((Long)lebVoters[1], (Long)lebVoters[0]);
				}
				if(lebIds.size() > 0){
					List<Object[]> names = localElectionBodyDAO.getLocalElectionBodyNames(lebIds);
					
					 for(Object[] name:names){
					 namesMap.put((Long)name[0], name[1].toString());
					 lclIds.add((Long)name[0]);
					 }
				}
			}
			if(lclYoungVoters != null){
				youngVoters.addAll(lclYoungVoters);
			}
			 
		 }
		 
		 for(Object[] voters:youngVoters){
			 youngVotersMap.put((Long)voters[1], (Long)voters[0]);
		 }
		 for(Long key:totalVotersMap.keySet()){
			 Long totalVot = totalVotersMap.get(key);
			 Long youngVot = youngVotersMap.get(key);
			 if(totalVot != null && youngVot != null){
				 vo = new PanchayatVO();
				 vo.setPanchayatId(key);
				 vo.setTargetPerc(new BigDecimal(youngVot*100/totalVot.doubleValue()).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
				 orderList.add(vo);
			 }
		 }
		
		
		 Collections.sort(orderList,youngOldVoterSort);
		 Double maxPerc = orderList.get(0).getTargetPerc();
		 for(PanchayatVO orderVo:orderList){
			 SuggestedLocationsVO rangeVo = panchayatLocs.get(orderVo.getPanchayatId());
			 if(rangeVo != null){
				 rangeVo.setYoungVotrWeight(new BigDecimal((orderVo.getTargetPerc()*100*weightPerc)/(100*maxPerc)).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
			 }else{
				 rangeVo = new SuggestedLocationsVO();
				 rangeVo.setId(orderVo.getPanchayatId());
				 panchayatLocs.put(orderVo.getPanchayatId(),rangeVo);
				 if(lclIds.contains(orderVo.getPanchayatId()))
				     rangeVo.setType("Municipality");
				 else
					 rangeVo.setType("Panchayat");
				 rangeVo.setYoungVotrWeight(new BigDecimal((orderVo.getTargetPerc()*100*weightPerc)/(100*maxPerc)).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
				 if(namesMap.get(orderVo.getPanchayatId()) != null)
				  rangeVo.setName(namesMap.get(orderVo.getPanchayatId()));
				 else
				  rangeVo.setName("");
			 }
		 }
		 
	  }
	  
	  public List<PartyPositionVO> getPartyPerformenceReportNew(Long constituencyId ,Map<Long,PartyEffectVO> prpEffect)
		{
		  List<PartyPositionVO> resultList = null;
	 
			try{
			List<Long> constituencyIdsList = new ArrayList<Long>(0);
			constituencyIdsList.add(constituencyId);
			List<Long> assemblyEleIdsList = new ArrayList<Long>(0);
			List<Long> mandalIds = new ArrayList<Long>();
			List<Long> localbodyIds = new ArrayList<Long>();
			List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(constituencyId,IConstants.PRESENT_YEAR, null);
			if(mandalsList != null && mandalsList.size() > 0)
			for(SelectOptionVO vo : mandalsList)
			if(vo.getId().toString().substring(0,1).equalsIgnoreCase("2"))
			mandalIds.add(new Long(vo.getId().toString().substring(1)));
			else
				localbodyIds.add((Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(vo.getId().toString().substring(1))).get(0));

			
			Constituency constituency = constituencyDAO.get(constituencyId);
			

			assemblyEleIdsList.add(38l);
			assemblyEleIdsList.add(3l);
			List<PartyPositionVO> prevPancResultList = new ArrayList<PartyPositionVO>();
			List<PartyPositionVO> currPancResultList = new ArrayList<PartyPositionVO>();
			if(assemblyEleIdsList != null && assemblyEleIdsList.size() > 0)
			{
				resultList = new ArrayList<PartyPositionVO>(0);
				  //List<SuggestiveRange> suggestiveRangeList = suggestiveRangeDAO.getSuggestiveRangeList();
					  
				  PartyPositionVO partyPositionVO = null;
				  for(Long eleId :assemblyEleIdsList)
				  {
					Election election = electionDAO.get(eleId);
					partyPositionVO = new PartyPositionVO();
					List<PartyPositionVO> pancResultList = null;
                    if(eleId.longValue() ==38l){
					  pancResultList = currPancResultList;
                    }else{
                      pancResultList = prevPancResultList;
                    }
					
					partyPositionVO.setWeakPollingPercentVOList(pancResultList);


					partyPositionVO.setName(election.getElectionYear() != null?election.getElectionYear():" ");
					partyPositionVO.setId(eleId);
					partyPositionVO.setConstituencyId(constituencyId);
					
					if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL))
					{
						List<Long> panchayatIds = null;
						if(mandalIds != null && mandalIds.size() > 0)
						 panchayatIds =hamletBoothElectionDAO.getPanchayatIdsByEleIdAndMandalIdsList(mandalIds,eleId);
						if(panchayatIds != null && panchayatIds.size() > 0)
							getMandalWisePartyPerformanceReportNew(constituencyId,eleId,pancResultList,panchayatIds,null,prpEffect);	
						
					}
					else if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL_URBAN))
					{
						List<Long> panchayatIds = null;
						if(mandalIds != null && mandalIds.size() > 0)
						 panchayatIds =hamletBoothElectionDAO.getPanchayatIdsByEleIdAndMandalIdsList(mandalIds,eleId);
						List<Object[]> boothIdsList = null;
						if(localbodyIds != null && localbodyIds.size() > 0)
						 boothIdsList = hamletBoothElectionDAO.getBoothsByLocalBodyNElectionId(localbodyIds,eleId);
						if(panchayatIds != null && panchayatIds.size() > 0)
							getMandalWisePartyPerformanceReportNew(constituencyId,eleId,pancResultList,panchayatIds,boothIdsList,prpEffect);
					}
					resultList.add(partyPositionVO);
				  }
			}
			Double prevPerc = 0d;
			int prevCount = 0;
			Double currentPerc = 0d;
			int currentCount = 0;
			if(resultList != null && resultList.size() == 2){
				List<PartyPositionVO> currResults = resultList.get(0).getWeakPollingPercentVOList();
				List<PartyPositionVO> prevResults = resultList.get(1).getWeakPollingPercentVOList();
				//StringBuilder tab= new StringBuilder();
				//tab.append("<table>");
				for(PartyPositionVO result:currResults){
					//tab.append("<tr><td>"+result.getName()+"</td><td>"+result.getPrp()+"</td></tr>");
					if(result.getPartyPercentage() != null && result.getPartyPercentage() > 0){
						//tab.append("<tr><td></td><td></td></tr>");
						currentPerc = currentPerc+result.getPartyPercentage();
						currentCount = currentCount+1;
					}
				}
				/*tab.append("<tr><td></td><td></td></tr>");
				tab.append("<tr><td></td><td></td></tr>");
				tab.append("<tr><td></td><td></td></tr>");*/
				for(PartyPositionVO result:prevResults){
					//tab.append("<tr><td>"+result.getName()+"</td><td>"+result.getPartyPercentage()+"</td></tr>");
					if(result.getPartyPercentage() != null && result.getPartyPercentage() > 0){
						prevPerc = prevPerc+result.getPartyPercentage();
						prevCount = prevCount+1;
					}
				}
				//tab.append("</table>");
				//System.out.println(tab);
				if(prevCount > 0)
				  prevPerc = new BigDecimal(prevPerc/prevCount).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
				if(currentCount > 0)
				  currentPerc = new BigDecimal(currentPerc/currentCount).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
				addRanges(prevPerc,currentPerc,resultList.get(0));
				addRanges(prevPerc,currentPerc,resultList.get(1));
			  List<PartyPositionVO> suggestedLocations = getSuggestiveLocationsForAParty(resultList);
			  resultList.get(0).setSuggestedLocations(suggestedLocations);
			}	

	
			Map<Long,Map<String,Long>> map = new HashMap<Long, Map<String,Long>>(0);//<electionId,Map<strong,totalValidVotes>>
			for(PartyPositionVO partyPositionVO1:resultList)
			{
			   Map<String,Long> map2 = map.get(partyPositionVO1.getId());
			   if(map2 == null)
			   {
				  map2 = new HashMap<String, Long>(0);
				  map.put(partyPositionVO1.getId(), map2);
			   }
			   if(partyPositionVO1.getPartyPositionVOList() != null && partyPositionVO1.getPartyPositionVOList().size() > 0)
			   {
				 for(PartyPositionVO partyPositionVO2:partyPositionVO1.getPartyPositionVOList())
				 {
					 if(partyPositionVO2.getPartyPositionVOList() != null && partyPositionVO2.getPartyPositionVOList().size() > 0)
					 {
						for(PartyPositionVO partyPositionVO3:partyPositionVO2.getPartyPositionVOList())
						{
						  Long total = map2.get(partyPositionVO2.getName());
						   if(total == null)
							 map2.put(partyPositionVO2.getName(), partyPositionVO3.getTotalValidVotes());
						   else
							map2.put(partyPositionVO2.getName(), total+partyPositionVO3.getTotalValidVotes());
						}
					 }
				 }
			   }
			}
			
			for(PartyPositionVO partyPositionVO1:resultList)
			{
			  Long totalVotes = candidateBoothResultDAO.getConstituencyTotalVotes(constituencyId, partyPositionVO1.getId());
			  
			  Map<String, Long> totalVotesMap = map.get(partyPositionVO1.getId());
			  
			  if(partyPositionVO1.getPartyPositionVOList() != null && partyPositionVO1.getPartyPositionVOList().size() > 0)
			   {
				 for(PartyPositionVO partyPositionVO2:partyPositionVO1.getPartyPositionVOList())
				 {
					 if(partyPositionVO2.getPartyPositionVOList() != null && partyPositionVO2.getPartyPositionVOList().size() > 0 && totalVotes != null && totalVotes > 0)
					 {
						  double percentage =  new BigDecimal((totalVotesMap.get(partyPositionVO2.getName())*100.0/totalVotes)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
						  partyPositionVO2.setRangePercentage(percentage);
					 }
				 }
			   }
			}

			 return resultList;
			}catch (Exception e) {
			 LOG.error(" Exception Occured in getPartyPerformenceReportForAllElections() method, Exception-",e);
			 return resultList;
			}
		
     }
	  
	  public void addRanges(Double prevPerc,Double currentPerc,PartyPositionVO partyPositionVO){
		  List<PartyPositionVO> rangeList = new ArrayList<PartyPositionVO>();
		  Float worestMin = 0.0f;
		  Float worestMax = 0.0f;
		  Float veryPoorMin = 0.0f;
		  Float veryPoorMax = 0.0f;
		  Float poorMin = 0.0f;
		  Float poorMax = 0.0f;
		  Float okMin = 0.0f;
		  Float okMax = 0.0f;
		  Float strongMin = 0.0f;
		  Float strongMax = 0.0f;
		  Float veryStrongMin = 0.0f;
		  Float veryStrongMax = 100.00f;
		  Float constiAvgPerc = new BigDecimal((prevPerc+currentPerc)/2).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
			okMin = constiAvgPerc-2.50f;
			okMax = constiAvgPerc+2.50f;
			Float minDifference = new BigDecimal(constiAvgPerc/5).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
			poorMin = okMin-minDifference;
			poorMax = okMin-0.01f;
			veryPoorMin = poorMin-minDifference;
			veryPoorMax = poorMin-0.01f;
			worestMax = veryPoorMin-0.01f;
			strongMin = okMax+0.01f;
			strongMax = okMax+minDifference;
			veryStrongMin = strongMax+0.01f;
			LOG.info("consticurrentPerc: "+currentPerc+"constiprevPerc: "+prevPerc+" constiAvgPerc: "+constiAvgPerc+"okMin: "+okMin+" okMax: "+okMax+" minDifference: "+minDifference+" poorMin: "+poorMin+" poorMax: "+poorMax+"veryPoorMin: "+veryPoorMin+" veryPoorMax: "+veryPoorMax+" worestMin: "+worestMin+" worestMax: "+worestMax+" strongMin: "+strongMin+" strongMax: "+strongMax);
			PartyPositionVO range = null;

				
				range = new PartyPositionVO();
				range.setName("VERY STRONG");
				range.setMinValue(veryStrongMin.doubleValue());
				range.setMaxValue(veryStrongMax.doubleValue());
				rangeList.add(range);
				
				range = new PartyPositionVO();
				range.setName("STRONG");
				range.setMinValue(strongMin.doubleValue());
				range.setMaxValue(strongMax.doubleValue());
				rangeList.add(range);
				
				range = new PartyPositionVO();
				range.setName("OK");
				range.setMinValue(okMin.doubleValue());
				range.setMaxValue(okMax.doubleValue());
				rangeList.add(range);
				
				range = new PartyPositionVO();
				range.setName("POOR");
				range.setMinValue(poorMin.doubleValue());
				range.setMaxValue(poorMax.doubleValue());
				rangeList.add(range);
				
				range = new PartyPositionVO();
				range.setName("VERY POOR");
				range.setMinValue(veryPoorMin.doubleValue());
				range.setMaxValue(veryPoorMax.doubleValue());
				rangeList.add(range);
				
				range = new PartyPositionVO();
				range.setName("WORST");
				range.setMinValue(worestMin.doubleValue());
				range.setMaxValue(worestMax.doubleValue());
				rangeList.add(range);

			partyPositionVO.setPartyPositionVOList(rangeList);
			List<PartyPositionVO> partyPositionVOList = partyPositionVO.getPartyPositionVOList();
			List<PartyPositionVO> resultVOList = partyPositionVO.getWeakPollingPercentVOList();
		      for(PartyPositionVO positionVO :partyPositionVOList)
		      {
		       for(PartyPositionVO result:resultVOList){
		    	if(result.getPartyPercentage() >= positionVO.getMinValue() && result.getPartyPercentage() <= positionVO.getMaxValue())
		    	{
		    	 List<PartyPositionVO> locationList = positionVO.getPartyPositionVOList();
		    	 if(locationList == null || locationList.size() == 0)
		    		locationList = new ArrayList<PartyPositionVO>(0);
		    		locationList.add(result);
		    		positionVO.setPartyPositionVOList(locationList);
		    	}
		      }
		      }
	  }
	  public void getMandalWisePartyPerformanceReportNew(Long constituencyId,Long electionId,List<PartyPositionVO> pancResultList,List<Long> panchaytIdsList,List<Object[]> localbodybooths,Map<Long,PartyEffectVO> prpEffect)
		{
			try{
			Map<Long,List<Long>> boothIds = new HashMap<Long, List<Long>>();//Map<boothId,List<panchayatId>>
			Map<Long,Map<Long,Long>> resultMap = new HashMap<Long, Map<Long,Long>>(0);//Map<panchayatId,Map<partyId,totalvoters>>
			Map<Long,Map<Long,Long>> resultMap1 = new HashMap<Long,Map<Long,Long>>(0);//Map<localbodyname,Map<partyId,totalvoters>>
			Map<Long,List<Long>> boothIdMap = new HashMap<Long, List<Long>>(0);//<localBodyName,<boothIds>
			//List<Long> boothIds1 = null;
			List<Long> booths = new ArrayList<Long>();
			if(localbodybooths != null && localbodybooths.size() > 0)
			{
			for(Object[] params: localbodybooths)
			{
				List<Long> boothIds1 = boothIdMap.get(params[2]);
					if(boothIds1 == null)
					{
					boothIds1 = new ArrayList<Long>(0);	
					boothIdMap.put((Long)params[2], boothIds1);
					}
					if(!boothIds1.contains((Long)params[0]))
					boothIds1.add((Long)params[0]);
			}
			
			for(Long localbodyId : boothIdMap.keySet())
			{
				booths = boothIdMap.get(localbodyId);
				List<Object[]> partyResult = candidateBoothResultDAO.findBoothResultsForBoothsAndElectionAndAllParties( boothIdMap.get(localbodyId),electionId,null);
				Map<Long,Long> partyMap1 = null;
				for(Object[] params : partyResult)
				{
					partyMap1 = resultMap1.get(localbodyId);
					if(partyMap1 == null)
					{
						partyMap1 = new HashMap<Long, Long>(0);
						resultMap1.put(localbodyId, partyMap1);
					}
					Long votesEarned = partyMap1.get((Long)params[0]);
					if(votesEarned == null)
						partyMap1.put((Long)params[0], (Long)params[2]);	
					else
						partyMap1.put((Long)params[0], votesEarned+(Long)params[2]);	
				}
				
			}
			}
			if(panchaytIdsList != null && panchaytIdsList.size() > 0)
			{
			  List<Object[]> list = hamletBoothElectionDAO.getPanchayatBoothDetailsByPanchayatIdsList(panchaytIdsList, electionId); 
			  if(list != null && list.size() > 0)
			  {
				  for(Object[] params:list)
				  {
					  List<Long> panchayatIds = boothIds.get((Long)params[1]);
					  if(panchayatIds == null)
					  {
					   panchayatIds = new ArrayList<Long>(0);
					   boothIds.put((Long)params[1], panchayatIds);
					  }
					  if(!panchayatIds.contains((Long)params[0]))
					   panchayatIds.add((Long)params[0]);
				  }
				  
				  List<Object[]> resultList = candidateBoothResultDAO.findBoothResultsForMultipleBoothsAndElectionIdForSelElection(boothIds.keySet(), electionId);
				  if(resultList != null && resultList.size() > 0)
				  {
					 for(Object[] params:resultList)
					 {
					   List<Long> panchayatIdsList = boothIds.get((Long)params[0]);
					   if(panchayatIdsList != null && panchayatIdsList.size() > 0)
					   {
						 Map<Long,Long> partyMap = null;//Map<PartyId,totalvotes>
						 for(Long panchayatId :panchayatIdsList)
						 {
							 partyMap = resultMap.get(panchayatId);
							 if(partyMap == null)
							 {
								 partyMap = new HashMap<Long, Long>(0);
								 resultMap.put(panchayatId, partyMap);
							 }
							 Long votesEarned = partyMap.get((Long)params[1]);
							 if(votesEarned == null)
							  partyMap.put((Long)params[1],(Long)params[2]);
							 else
							  partyMap.put((Long)params[1], votesEarned+(Long)params[2]);
						 }
					   }
					   
					 }
				  }
				  
			  }
			}
			AlliancePartyResultsVO alliancePartiesVO = staticDataService.getAlliancePartiesByElectionAndParty(electionId,872l);
			if(resultMap != null && resultMap.size() > 0)
				getPartyPerformanceForPanchayatNew(resultMap,pancResultList,prpEffect,electionId,alliancePartiesVO); 
			if(resultMap1 != null && resultMap1.size() > 0)
				getPartyPerformanceForLocalBodyNew(pancResultList,resultMap1,boothIdMap,prpEffect,electionId,alliancePartiesVO);
			
			
			}catch (Exception e) {
				e.printStackTrace();
				LOG.error(" Exception Occured in getMandalWisePartyPerformanceReport() method, Exception - "+e);
			  }
		}
	  
	  public void getPartyPerformanceForPanchayatNew(Map<Long,Map<Long,Long>> resultMap,List<PartyPositionVO> resultList,Map<Long,PartyEffectVO> prpEffect,Long electionId,AlliancePartyResultsVO alliancePartiesVO)
		{
			try{

			//panchayat start	
			 for(Long id:resultMap.keySet())
			 {
				Map<Long,Long> partyMap = resultMap.get(id);
				Long totalVotes = 0L;
					 
				for(Long partysId:partyMap.keySet())
				  totalVotes += partyMap.get(partysId); 
					 
				Long selectedPartyTotal = partyMap.get(872l);
				Long prp = null;
				if(electionId.longValue() == 38l)
					prp = partyMap.get(662l);
					 
				if(selectedPartyTotal == null){
					 
					  if(alliancePartiesVO == null || alliancePartiesVO.getAllianceParties() == null)
						  selectedPartyTotal = 0L;
					  else
						  for(SelectOptionVO alianceParty:alliancePartiesVO.getAllianceParties())
							  if(selectedPartyTotal == null || selectedPartyTotal.longValue() == 0l)
							  selectedPartyTotal = partyMap.get(alianceParty.getId());
				  }
			   
			  if(selectedPartyTotal == null)
				  selectedPartyTotal = 0L;
			  double selectedPartyTotalPercent = 0d;
			  if(totalVotes != null && totalVotes > 0)
			   selectedPartyTotalPercent =  new BigDecimal((selectedPartyTotal*100.0/totalVotes)).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
		      
		     Double prpperc = 0d;
		     if(prp != null && totalVotes != null && totalVotes > 0)
		    	 prpperc =  new BigDecimal((prp*100.0/totalVotes)).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
		      double difference = selectedPartyTotalPercent;
		    	
		      String locationName = "";
		      
		    	 locationName = panchayatDAO.getPanchayatNameById(id); 
		    	 PartyEffectVO effect = prpEffect.get(id);
		    	 if(effect == null){
		    	  effect = new PartyEffectVO();
		    	  prpEffect.put(id, effect);
		    	 }
		    	 if(electionId.longValue() == 38l){
		    		 effect.setPrpCurrentPerc(prpperc);
		    		 effect.setTdpCurrentPerc(selectedPartyTotalPercent);
		    		 effect.setId(id);
		    	 }else{
		    		 effect.setTdpPrevPerc(selectedPartyTotalPercent); 
		    	 }
		  

		    	 PartyPositionVO locationVO = new PartyPositionVO();

		    		 locationVO = new PartyPositionVO();
		    		 locationVO.setId(id);
		    		 locationVO.setName(locationName != null?locationName:" ");
		    		 locationVO.setPartyPercentage(selectedPartyTotalPercent);
		    		 locationVO.setSelectedPartyTotalVoters(selectedPartyTotal);
		    		 locationVO.setTotalValidVotes(totalVotes);
		    		 locationVO.setMargin(difference);
		    		 locationVO.setPrp(prpperc);
		    		 locationVO.setType("Panchayat");
		    		 resultList.add(locationVO);
		      
		    	
			} // panchayat End
			
			}catch (Exception e) {
			
			 LOG.error(" Exception Occured in getPartyPerformanceForPanchayatNew() method, Exception - ",e);
			}
		}
	  public void getPartyPerformanceForLocalBodyNew(List<PartyPositionVO> pancResultList,Map<Long,Map<Long,Long>> resultMap1,Map<Long,List<Long>> boothIdMap,Map<Long,PartyEffectVO> prpEffect,Long electionId,AlliancePartyResultsVO alliancePartiesVO)
		 {
			 try{
				 Long localbodytotalVoters = 0l;
				 
			     
				 if(resultMap1 != null)
				 {
				 for(Long localbodyId : resultMap1.keySet())
				 {
					 localbodytotalVoters = boothDAO.getTotalaVotesByBoothIds(boothIdMap.get(localbodyId)).get(0);	
					 	String localbodyName = localElectionBodyDAO.getLocalElectionBodyName1(localbodyId);
						Map<Long,Long> partyMap1 = resultMap1.get(localbodyId);
						Long totalVotes = 0L;
							 
						for(Long partysId:partyMap1.keySet())
						  totalVotes += partyMap1.get(partysId); 
							 
						Long selectedPartyTotal = partyMap1.get(872l);
						if(selectedPartyTotal == null){
							 
							  if(alliancePartiesVO == null || alliancePartiesVO.getAllianceParties() == null)
								  selectedPartyTotal = 0L;
							  else
								  for(SelectOptionVO alianceParty:alliancePartiesVO.getAllianceParties())
									  if(selectedPartyTotal == null || selectedPartyTotal.longValue() == 0l)
									  selectedPartyTotal = partyMap1.get(alianceParty.getId());
						  }
					  if(selectedPartyTotal == null)
						  selectedPartyTotal = 0L;
					  
					  Long prp = null;
						if(electionId.longValue() == 38l)
							prp = partyMap1.get(662l);
					    double selectedPartyTotalPercent =  new BigDecimal((selectedPartyTotal*100.0/totalVotes)).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
					    Double prpperc = null;
					     if(prp != null)
					    	 prpperc =  new BigDecimal((prp*100.0/totalVotes)).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
					  
				      double difference = selectedPartyTotalPercent;
				      PartyEffectVO effect = prpEffect.get(localbodyId);
				    	 if(effect == null){
				    	  effect = new PartyEffectVO();
				    	  prpEffect.put(localbodyId, effect);
				    	 }
				    	 if(electionId.longValue() == 38l){
				    		 effect.setPrpCurrentPerc(prpperc);
				    		 effect.setTdpCurrentPerc(selectedPartyTotalPercent);
				    		 effect.setId(localbodyId);
				    	 }else{
				    		 effect.setTdpPrevPerc(selectedPartyTotalPercent); 
				    	 }
				    
				    	  PartyPositionVO locationVO = new PartyPositionVO();
				    		 locationVO.setId(localbodyId);
				    		 locationVO.setName(localbodyName);
				    		 locationVO.setPartyPercentage(selectedPartyTotalPercent);
				    		 locationVO.setSelectedPartyTotalVoters(selectedPartyTotal);
				    		 locationVO.setTotalValidVotes(totalVotes);
				    		 locationVO.setTotalVoters(localbodytotalVoters);
				    		 locationVO.setPercentage(new BigDecimal((totalVotes*100.0/localbodytotalVoters)).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
				    		 locationVO.setMargin(difference);
				    		 locationVO.setType("Municipality");
				    		 locationVO.setPrp(prpperc);
				    		 pancResultList.add(locationVO);

				 }
				 } 
			 }
			 catch(Exception e)
			 {
				 LOG.error(" Exception Occured in getPartyPerformanceForLocalBodyNew() method, Exception - ",e);
			 }
		 }
	  
	  /*public List<VoterHouseInfoVO> getFamilyDetailsForConstituency(Long constituencyId,Long publicationId,Long minValue,Long maxValue,Integer startIndex,Integer maxIndex,String path,Long districtId)
		{
			List<VoterHouseInfoVO> result = new ArrayList<VoterHouseInfoVO>();
			try{
	    		List<Long> districtIdslist = new ArrayList<Long>();
	    		districtIdslist.add(districtId);
	    		List<Object[]> constiList =  constituencyDAO.getDistrictConstituenciesList(districtIdslist);
	    		int count = 0;
	    		for (Object[] objects : constiList) {
	    		
	    			List<Object[]> list = boothPublicationVoterDAO.getHouseNosForBooth(constituencyId,publicationId,minValue,maxValue,startIndex,maxIndex);
	    			List<Object[]> totalList = boothPublicationVoterDAO.getHouseNosForBooth(constituencyId,publicationId,minValue,maxValue,null,null);
	    			Map<Long,List<String>> boothHousesMap = new HashMap<Long, List<String>>();
	    			if(list !=null && list.size() > 0)
	    			{
	    				for(Object[] params : list)
	    				{
	    					List<String> hnos = boothHousesMap.get((Long)params[0]);
	    					if(hnos == null)
	    					{
	    						hnos = new ArrayList<String>();
	    						boothHousesMap.put((Long)params[0], hnos);
	    					}
	    					if(!hnos.contains(params[1].toString()))
	    						hnos.add(params[1].toString());
	    				}
	    			}
	    			for(Long boothId :boothHousesMap.keySet())
	    			{
	    			Map<Long,VoterHouseInfoVO> basicInfo = new HashMap<Long,VoterHouseInfoVO>();
	    			Booth booth = boothDAO.get(boothId);
	    			Constituency consti = constituencyDAO.get((Long)booth.getConstituency().getConstituencyId());
	    			String type = consti.getAreaType();
	    			if(booth != null)
	    			{
	    				String tehsil ="";
	    				String panchayat = "";
	    				String hamlet = "";
	    				String ward = "";
	    				String localbody = "";
	    				String hamletName = "";
	    				String wardName= "";
	    				VoterHouseInfoVO votervo = new VoterHouseInfoVO();
	    				if(type.equalsIgnoreCase(IConstants.CONST_TYPE_RURAL))
	    				{
	    					tehsil = booth.getTehsil().getTehsilName();
	    					if(booth.getPanchayat() != null)
	    					{
	    					panchayat =booth.getPanchayat().getPanchayatName();
	    					List hamlets = panchayatHamletDAO.getHamletByPanchayatId(booth.getPanchayat().getPanchayatId());
	    					if(hamlets != null && hamlets.size() > 0)
	    						hamletName = hamlets.get(0).toString();
	    					}
	    				}
	    				else if(type.equalsIgnoreCase(IConstants.CONST_TYPE_RURAL_URBAN))
	    				{
	    					if (booth.getLocalBody() != null)
	    					localbody = booth.getLocalBody().getName();
	    					if(localbody != null && localbody != "")
	    						tehsil = localbody +" Muncipality";
	    					else
	    						tehsil = booth.getTehsil().getTehsilName();	
	    					if(booth.getPanchayat() != null)
	    					{
	    					panchayat =booth.getPanchayat().getPanchayatName();
	    					List hamlets = panchayatHamletDAO.getHamletByPanchayatId(booth.getPanchayat().getPanchayatId());
	    					if(hamlets != null && hamlets.size() > 0)
	    						hamletName = hamlets.get(0).toString();
	    					}
	    					
	    				}
	    				else if(type.equalsIgnoreCase(IConstants.CONST_TYPE_URBAN))
	    				{
	    					if (booth.getLocalBody() != null)
	    						localbody = booth.getLocalBody().getName();
	    					String electionType =consti.getElectionScope().getElectionType().getElectionType();
	    						if(localbody != null && localbody != "")
	    						{
	    							if(electionType.equalsIgnoreCase(IConstants.GHMC))
	    							tehsil = localbody +" Corporation";
	    							if(booth.getLocalBodyWard() != null)
	    							wardName = booth.getLocalBodyWard().getName();
	    						}
	    						else if(electionType.equalsIgnoreCase(IConstants.MUNCIPLE_ELECTION_TYPE))
	    						{
	    							tehsil = localbody +" Muncipality";
	    						}
	    					
	    				}
	    				votervo.setTehsilName(tehsil);
	    				votervo.setPanchayatName(panchayat);
	    				votervo.setHamletName(hamletName);
	    				votervo.setWardName(wardName);
	    				basicInfo.put(boothId, votervo);
	    				
	    			}		
	    			
	    			  List<Object[]> list1 = boothPublicationVoterDAO.getFamilyWiseInfoForBooth(boothId, boothHousesMap.get(boothId));
	    			  Map<String,List<VoterHouseInfoVO>> resultmap = new HashMap<String, List<VoterHouseInfoVO>>();
	    			  List<Long> voterIds = new ArrayList<Long>();
	    			  
	    			  for(Object[] params1 :list1)
	    			  {
	    				  List<VoterHouseInfoVO> voterDetails = resultmap.get(params1[0].toString());
	    				  Voter voter = (Voter) params1[1];
	    				  VoterHouseInfoVO vo = new VoterHouseInfoVO();
	    				  if(voterDetails == null)
	    				  {
	    					  voterDetails = new ArrayList<VoterHouseInfoVO>();
	    					  resultmap.put(params1[0].toString(), voterDetails);
	    				  }
	    				vo.setVoterIdCardNo(voter.getVoterIDCardNo());
	    				vo.setVoterId(voter.getVoterId());
	    				vo.setName(voter.getName());
	    				vo.setAge(voter.getAge());
	    				vo.setGender(voter.getGender());
	    				vo.setHouseNo("#  " +voter.getHouseNo());
	    				voterIds.add(voter.getVoterId());
	    				voterDetails.add(vo);
	    				}
	    			 Map<Long,String> voterCaste =new HashMap<Long, String>();
	    			 List<Object[]> casteInfo =  userVoterDetailsDAO.getCasteForVoter(voterIds);
	    				if(casteInfo != null && casteInfo.size() > 0)
	    					for(Object[] casteName : casteInfo)
	    					{
	    						String caste = voterCaste.get((Long)casteName[0]);
	    						if(caste == null)
	    						voterCaste.put((Long)casteName[0],casteName[1].toString());
	    						else
	    						voterCaste.put((Long)casteName[0],caste);	
	    					}
	    				
	    			
	    			  for(String houseNo : resultmap.keySet())
	    			  {
	    				  
	    				  VoterHouseInfoVO voterHouseInfoVO = new VoterHouseInfoVO();
	    				  voterHouseInfoVO.setBoothId(boothId);
	    				  voterHouseInfoVO.setPartNo(boothDAO.get(boothId).getPartNo());
	    				  voterHouseInfoVO.setHouseNo("#  " +houseNo);
	    				  List<VoterHouseInfoVO> voterDetails = resultmap.get(houseNo.toString());
	    				 
	    				  voterHouseInfoVO.setCount(new Long(voterDetails.size()));
	    				  voterHouseInfoVO.setElder(voterDetails.get(voterDetails.size()-1).getName());
	    				  voterHouseInfoVO.setElderAge((voterDetails.get(voterDetails.size()-1).getAge()));
	    				  voterHouseInfoVO.setElderGender(voterDetails.get(voterDetails.size()-1).getGender());
	    				  voterHouseInfoVO.setVoterIdCardNo(voterDetails.get(voterDetails.size()-1).getVoterIdCardNo().toString());
	    				  String cast = voterCaste.get(voterDetails.get(voterDetails.size()-1).getVoterId());
	    				  voterHouseInfoVO.setElderCaste(cast != null ? cast : " ");
	    				  VoterHouseInfoVO basicVo = basicInfo.get(boothId);
	    				  voterHouseInfoVO.setTehsilName(basicVo.getTehsilName());
	    				  voterHouseInfoVO.setPanchayatName(basicVo.getPanchayatName());
	    				  voterHouseInfoVO.setWardName(basicVo.getWardName());
	    				  voterHouseInfoVO.setHamletName(basicVo.getHamletName());
	    				  boolean flag = false;
	    				  List<VoterHouseInfoVO> youngervoterDetails  = voterDetails;
	    				 
	    						for(int i=0;i<youngervoterDetails.size()-1;i++)
	    						{
	    							if(youngervoterDetails.get(i).getGender().equalsIgnoreCase("M"))
	    							{
	    								voterHouseInfoVO.setYounger(youngervoterDetails.get(i).getName());
	    								voterHouseInfoVO.setYoungerAge(youngervoterDetails.get(i).getAge());
	    								voterHouseInfoVO.setYoungerGender(youngervoterDetails.get(i).getGender());
	    								voterHouseInfoVO.setVoterGroup(youngervoterDetails.get(i).getVoterIdCardNo().toString());
	    								flag = true;
	    							}
	    						}
	    						if(!flag)
	    						{
	    							voterHouseInfoVO.setYounger(youngervoterDetails.get(0).getName());
	    							voterHouseInfoVO.setYoungerAge(youngervoterDetails.get(0).getAge());
	    							voterHouseInfoVO.setYoungerGender(youngervoterDetails.get(0).getGender());
	    							voterHouseInfoVO.setVoterGroup(youngervoterDetails.get(0).getVoterIdCardNo().toString());
	    						}
	    						
	    						result.add(voterHouseInfoVO);
	    						if(totalList != null && totalList.size() > 0)
	    						result.get(0).setConstituencyType(type);
	    					    result.get(0).setTotalHousesCount(new Long(totalList.size()));
	    			  }
	    			 
	    			
	    			 
	    			 }
	    			
	    			Document document = new Document();

	    			Object[] values = constituencyDAO.constituencyName(constituencyId).get(0);
	    	    	String constituenyName = values[0].toString().toUpperCase();
	    	    	String districtName = values[1].toString().toUpperCase();
	    	    	Long constituenyNo = delimitationConstituencyDAO.getConstituencyNo(constituencyId,2009l);
	    		    String filePath = "VMR"+"/"+""+districtName+"_"+constituenyNo+"_"+constituenyName+".pdf";
	    		    String FILE = path+filePath;
	    		    File file  = new File(FILE);
	    		    file.createNewFile();
	    		    PdfWriter.getInstance(document, new FileOutputStream(FILE));
	    			document.open();
	    			voterModifiationPdfsGenerations.generatePdfsForImpFamiles(document,result,constituencyDAO.get(constituencyId).getName());
	    			document.close();
	    			
	    				Object[] values = constituencyDAO.constituencyName(constituencyId).get(0);
	    		    	String constituenyName = values[0].toString().toUpperCase();
	    		    	String districtName = values[1].toString().toUpperCase();
	    		    	Long constituenyNo = delimitationConstituencyDAO.getConstituencyNo(constituencyId,2009l);
	    			    String filePath = "VMR"+"/"+""+districtName+"_"+constituenyNo+"_"+constituenyName+".xls";
	    			    String FILE = path+filePath;
	    			    //File file  = new File(FILE);
	    			    //file.createNewFile();
	    			    FileOutputStream out = new FileOutputStream(FILE);
	    			    
	    				HSSFWorkbook workbook = new HSSFWorkbook(); 
	    				HSSFSheet sheet  = workbook.createSheet("report");
	    				ageWiseExcelsGenerationService.generateExcelsForImportaneFamiles(result , sheet, workbook);
	    				workbook.write(out);
	    				
	    			
			}
			}
			catch(Exception e)
			{
				LOG.error("Exception raised in getFamilyDetailsForConstituency() method in Suggestive Model Service",e);
				
			}		
			return result;
		}
		
	  */
	  public List<PartyPositionVO> getPartyPerfromanceStratagicReport(Long constituencyId,Long partyId,Long electionId,String path)
	  {
		  
		  PartyPositionVO partyPositionVO = null;
		  List<Long> panchayatIds = null;
		  List<PartyPositionVO> resultList = new ArrayList<PartyPositionVO>();
		  try{
				List<Long> mandalIds = new ArrayList<Long>();
				List<Long> localbodyIds = new ArrayList<Long>();
				List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(constituencyId,IConstants.PRESENT_YEAR, null);
				if(mandalsList != null && mandalsList.size() > 0)
				for(SelectOptionVO vo : mandalsList)
				if(vo.getId().toString().substring(0,1).equalsIgnoreCase("2"))
				mandalIds.add(new Long(vo.getId().toString().substring(1)));
				else
				localbodyIds.add((Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(vo.getId().toString().substring(1))).get(0));
				if(mandalIds != null && mandalIds.size() > 0)
					 panchayatIds =hamletBoothElectionDAO.getPanchayatIdsByEleIdAndMandalIdsList(mandalIds,electionId);
					if(panchayatIds != null && panchayatIds.size() > 0)
						getMandalWisePartyPerformanceStratagicReport(constituencyId,electionId, resultList, partyId,panchayatIds);
				
		  }
		  catch(Exception e)
		  {
				LOG.error("Exception raised in getPartyPerfromanceStratagicReport() method in Suggestive Model Service",e);  
		  }
		  if(path != null)
		  {
			  if(resultList != null && resultList.size() > 0)
			  {
				  Document document = null;
					try 
					{
						document = new Document();
						Object[] values = constituencyDAO.constituencyName(constituencyId).get(0);
				    	String constituenyName = values[0].toString().toUpperCase();
				    	String districtName = values[1].toString().toUpperCase();
				    	Long constituenyNo = delimitationConstituencyDAO.getConstituencyNo(constituencyId,2009l);
				    	//String path = "C:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\PartyAnalyst\\";
					    String filePath = "VMR"+"/"+""+districtName+"_"+constituenyNo+"_"+constituenyName+".pdf";
					    String FILE = path+filePath;
					    File file  = new File(FILE);
					    try {
							file.createNewFile();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					    resultList.get(0).setUrl(filePath);
					  	try {
					  		PdfWriter.getInstance(document, new FileOutputStream(FILE));
					  	} catch (FileNotFoundException e) {
					  		e.printStackTrace();
					  	} catch (DocumentException e) {
					  		e.printStackTrace();
					  	}
					  	
					  	document.open();
						
						
						pdfReportService.buildPanchaytWiseReport(document,resultList);
						
						
					} 
					catch (Exception e)
					{
						
					}
					finally
					{
						if(document != null)
						document.close();
					}
			  }
		  }
		 
		return resultList;
	  }
	  
	  
		
		public void getMandalWisePartyPerformanceStratagicReport(Long constituencyId,Long electionId,List<PartyPositionVO> partyPositionVOList,Long partyId,List<Long> panchaytIdsList)
		{
			try{
			Map<Long,List<Long>> boothIds = new HashMap<Long, List<Long>>();//Map<boothId,List<panchayatId>>
			Map<Long,Map<Long,Long>> resultMap = new HashMap<Long, Map<Long,Long>>(0);//Map<panchayatId,Map<partyId,totalvoters>>
			if(panchaytIdsList != null && panchaytIdsList.size() > 0)
			{
			  List<Object[]> list = hamletBoothElectionDAO.getPanchayatBoothDetailsByPanchayatIdsList(panchaytIdsList, electionId); 
			  if(list != null && list.size() > 0)
			  {
				  for(Object[] params:list)
				  {
					  List<Long> panchayatIds = boothIds.get((Long)params[1]);
					  if(panchayatIds == null)
					  {
					   panchayatIds = new ArrayList<Long>(0);
					   boothIds.put((Long)params[1], panchayatIds);
					  }
					  if(!panchayatIds.contains((Long)params[0]))
					   panchayatIds.add((Long)params[0]);
				  }
				  
				  List<Object[]> resultList = candidateBoothResultDAO.findBoothResultsForMultipleBoothsAndElectionIdForSelElection(boothIds.keySet(), electionId);
				  if(resultList != null && resultList.size() > 0)
				  {
					 for(Object[] params:resultList)
					 {
					   List<Long> panchayatIdsList = boothIds.get((Long)params[0]);
					   if(panchayatIdsList != null && panchayatIdsList.size() > 0)
					   {
						 Map<Long,Long> partyMap = null;//Map<PartyId,totalvotes>
						 for(Long panchayatId :panchayatIdsList)
						 {
							 partyMap = resultMap.get(panchayatId);
							 if(partyMap == null)
							 {
								 partyMap = new HashMap<Long, Long>(0);
								 resultMap.put(panchayatId, partyMap);
							 }
							 Long votesEarned = partyMap.get((Long)params[1]);
							 if(votesEarned == null)
							  partyMap.put((Long)params[1],(Long)params[2]);
							 else
							  partyMap.put((Long)params[1], votesEarned+(Long)params[2]);
						 }
					   }
					   
					 }
				  }
				  
			  }
			}
			if(resultMap != null && resultMap.size() > 0)
				getPartyPerformanceForStratagicPanchayat(resultMap,partyPositionVOList, partyId,electionId); 
		
			}catch (Exception e) {
				e.printStackTrace();
				LOG.error(" Exception Occured in getMandalWisePartyPerformanceReport() method, Exception - "+e);
			  }
		}
		
		public void getPartyPerformanceForStratagicPanchayat(Map<Long,Map<Long,Long>> resultMap,List<PartyPositionVO> partyPositionVOList, Long selectedpartyId, Long electionId)
		{
			try{
				
				//resultMap -- Map<panchayatId,Map<partyId,totalvoters>>
				//resultMap1 -- Map<localbodyName,Map<partyId,totalvoters>>
				boolean winPartyExist = false;
				Map<Long,List<Long>> boothIdsMap = new HashMap<Long, List<Long>>(0);//<panchayatId,List<boothIds>>
				Map<Long,Long> panchayatTotalVotersMap = new HashMap<Long, Long>(0);//<locationId,totalVoters>
			    Map<Long,PartyPositionVO> winpartyMap = new HashMap<Long, PartyPositionVO>();						
				List<Long> panchayatIdsList = new ArrayList<Long>(resultMap.keySet());
				List<Object[]> boothList = hamletBoothElectionDAO.getPanchayatBoothDetailsByPanchayatIdsList(panchayatIdsList, electionId);
				List<Object[]> panchayatresult = panchayatResultDAO.getPartyWiseWonInPanchayts(panchayatIdsList);
				
				  if(panchayatresult != null && panchayatresult.size() > 0)
				  {
					  winPartyExist = true;
					  for(Object[] params : panchayatresult)
					  {
					  PartyPositionVO vo = winpartyMap.get((Long)params[2]);  
					  if(vo == null)
					  {
						  vo = new PartyPositionVO();
						  winpartyMap.put((Long)params[2], vo);
					  }
					  vo.setWinPartyName(params[1] != null ?params[1].toString() : "");
					  vo.setWinPartyTotal((Long)params[4]);
					  }
				  }
				  if(winPartyExist == false)
				  {
					return ;
				  }
				  else
				  if(boothList != null && boothList.size() > 0)
				  {
					  for(Object[] params:boothList)
					  {
						  List<Long> boothIdsList = boothIdsMap.get((Long)params[0]);
						  if(boothIdsList == null)
						  {
							boothIdsList = new ArrayList<Long>(0);
							boothIdsMap.put((Long)params[0], boothIdsList);  
						  }
						  if(!boothIdsList.contains((Long)params[1]))
							  boothIdsList.add((Long)params[1]);  
					  }
					  
				  }
				
				
				
			if(boothIdsMap != null && boothIdsMap.size() > 0)
			{
			  for(Long id : boothIdsMap.keySet())
				panchayatTotalVotersMap.put(id, boothDAO.getTotalVotesByBoothIdsList(boothIdsMap.get(id)));
			}
			 AlliancePartyResultsVO alliancePartiesVO = staticDataService.getAlliancePartiesByElectionAndParty(electionId,selectedpartyId);
			//panchayat start	
			 for(Long id:resultMap.keySet())
			 {
				Map<Long,Long> partyMap = resultMap.get(id);
				Long totalVotes = 0L;
					 
				for(Long partysId:partyMap.keySet())
				  totalVotes += partyMap.get(partysId); 
					 
				Long selectedPartyTotal = partyMap.get(selectedpartyId);
				Long comparePartyTotal = 0L;
					 
				 
				  
			  if(selectedPartyTotal == null){
				 
				  if(alliancePartiesVO == null || alliancePartiesVO.getAllianceParties() == null)
					  selectedPartyTotal = 0L;
				  else
					  for(SelectOptionVO alianceParty:alliancePartiesVO.getAllianceParties()){
						  if(selectedPartyTotal == null || selectedPartyTotal.longValue() == 0l){
						     selectedPartyTotal = partyMap.get(alianceParty.getId());
						     selectedpartyId = alianceParty.getId();
						  }
					  }
			  }
			  for(Long partysId:partyMap.keySet())
			  {
			    if(!partysId.equals(selectedpartyId) && comparePartyTotal < partyMap.get(partysId))
				 comparePartyTotal = partyMap.get(partysId);
				  
			  }
			  if(comparePartyTotal == null)
				  comparePartyTotal = 0L;
			  double selectedPartyTotalPercent = 0d;
			  double comparePartyTotalPercent = 0d;
			  if(totalVotes != null && totalVotes > 0){
			   selectedPartyTotalPercent =  new BigDecimal((selectedPartyTotal*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			    comparePartyTotalPercent =  new BigDecimal((comparePartyTotal*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			  }
		      double difference = selectedPartyTotalPercent;
		      double diff = selectedPartyTotalPercent-comparePartyTotalPercent;
		      if(diff<0){
		    	  diff=-1*diff;
		      }
		      diff=new BigDecimal(diff).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		      String locationName = "";
		      locationName = panchayatDAO.getPanchayatNameById(id); 
		         PartyPositionVO locationVO = null;
		    	 locationVO = checkPartyPositionVOExist(id,partyPositionVOList);
		    	 if(locationVO == null)
		    	 {
		    		 locationVO = new PartyPositionVO();
		    		 locationVO.setId(id);
		    		 locationVO.setName(locationName != null?locationName:" ");
		    		 locationVO.setPartyPercentage(selectedPartyTotalPercent);
		    		 locationVO.setSelectedPartyTotalVoters(selectedPartyTotal);
		    		 locationVO.setTotalValidVotes(totalVotes);
		    		 locationVO.setTotalVoters(panchayatTotalVotersMap.get(id));
		    		 locationVO.setPercentage(new BigDecimal((totalVotes*100.0/panchayatTotalVotersMap.get(id))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		    		 locationVO.setMargin(diff);
		    		 if(difference >= 0.00 && difference <= 40.00)
		    	     locationVO.setRank(2l) ;
		    	     else if(difference >=40.01 && difference <= 100.00)
		    			 locationVO.setRank(1l) ; 
		    		 String winPartyName ="";
		    		 Long winPartyTotal =0l;
		    		 if(winpartyMap != null )
		    		 {
		    		 PartyPositionVO winVo = new PartyPositionVO();
		    		 winVo = winpartyMap.get(id);
		    		 if(winVo != null)
		    		 {
		    			 winPartyName = winVo.getWinPartyName();
		    			 winPartyTotal = winVo.getWinPartyTotal();
		    	     }
		    		 }
		    		 locationVO.setWinPartyName(winPartyName);
		    		 locationVO.setWinPartyTotal(winPartyTotal);
		    		 partyPositionVOList.add(locationVO);
		    		
		      }
		    
		    } // panchayat End
		

		  
			}catch (Exception e) {
			 e.printStackTrace();
			 LOG.error(" Exception Occured in getPartyPerformanceForSelectedlocation() method, Exception - "+e);
			}
		}
		
		
		public List<SelectOptionVO> getCriticalPanchayatConstituencies()
		{
			List<SelectOptionVO> resultList = new ArrayList<SelectOptionVO>();
			List<Object[]> list = new ArrayList<Object[]>();
			try{
				 list = criticalPanchayatsDAO.getConstituenciesFromCriticalPanchayats();
				if(list != null && list.size() > 0)
				{
					for(Object[] params : list)
					resultList.add(new SelectOptionVO((Long)params[0],params[1].toString()));	
				}
				
			}catch (Exception e) {
				 e.printStackTrace();
				 LOG.error(" Exception Occured in getCriticalPanchayatConstituencies() method, Exception - "+e);
				}
			return resultList;
		}
		
		public List<VoterHouseInfoVO> getFamilyDetailsForPanchayat(VoterVO votervo,Long userId,String path)
		{
			
			List<VoterHouseInfoVO> result = new ArrayList<VoterHouseInfoVO>();
			try{
				Long constituencyId = votervo.getConstituencyId();
				Long publicationId = voterInfoDAO.getLatestPublicationDate(votervo.getConstituencyId());
				
				List<Object[]> list = criticalPanchayatsDAO.getCriticalPanchayatBoothHnos(votervo,votervo.getStartIndex(),votervo.getMaxIndex());
				List<Object[]> totalList = criticalPanchayatsDAO.getCriticalPanchayatBoothHnos(votervo,null,null);
				Map<Long,List<String>> boothHousesMap = new HashMap<Long, List<String>>();
				Map<Long,String> wardMap = new HashMap<Long, String>();
				if(list !=null && list.size() > 0)
				{
					for(Object[] params : list)
					{
						List<String> hnos = boothHousesMap.get((Long)params[0]);
						if(hnos == null)
						{
							hnos = new ArrayList<String>();
							boothHousesMap.put((Long)params[0], hnos);
						}
						if(!hnos.contains(params[1].toString()))
							hnos.add(params[1].toString().toUpperCase());
					}
				}
				for(Long boothId :boothHousesMap.keySet())
				{
				Map<Long,VoterHouseInfoVO> basicInfo = new HashMap<Long,VoterHouseInfoVO>();
				Booth booth = boothDAO.get(boothId);
				Constituency consti = constituencyDAO.get((Long)booth.getConstituency().getConstituencyId());
				String type = consti.getAreaType();
				if(booth != null)
				{
					String tehsil ="";
					String panchayat = "";
					String hamlet = "";
					String ward = "";
					String localbody = "";
					String hamletName = "";
					String wardName= "";
					VoterHouseInfoVO votervo1 = new VoterHouseInfoVO();
					if(type.equalsIgnoreCase(IConstants.CONST_TYPE_RURAL))
					{
						tehsil = booth.getTehsil().getTehsilName();
						if(booth.getPanchayat() != null)
						{
						panchayat =booth.getPanchayat().getPanchayatName();
						List hamlets = panchayatHamletDAO.getHamletByPanchayatId(booth.getPanchayat().getPanchayatId());
						if(hamlets != null && hamlets.size() > 0)
							hamletName = hamlets.get(0).toString();
						}
					}
					else if(type.equalsIgnoreCase(IConstants.CONST_TYPE_RURAL_URBAN))
					{
						if (booth.getLocalBody() != null && booth.getLocalBody().getName() != null)
						localbody = booth.getLocalBody().getName();
					     else
							tehsil = booth.getTehsil().getTehsilName();	
						if(booth.getPanchayat() != null)
						{
						panchayat = booth.getPanchayat().getPanchayatName();
						List hamlets = panchayatHamletDAO.getHamletByPanchayatId(booth.getPanchayat().getPanchayatId());
						if(hamlets != null && hamlets.size() > 0)
							hamletName = hamlets.get(0).toString();
						}
						
						if(localbody != null && localbody != "")
						{
							if( booth.getLocalBody().getElectionType() != null)
							{
							String electionType = booth.getLocalBody().getElectionType().getElectionType();
							if(electionType.equalsIgnoreCase(IConstants.GHMC) || electionType.equalsIgnoreCase(IConstants.CORPORATION_ELECTION_TYPE))
							{
							tehsil = localbody +" Corporation";
							if(booth.getLocalBodyWard() != null && booth.getLocalBodyWard().getName() != null)
							wardName = booth.getLocalBodyWard().getName();
							}
							else if(electionType.equalsIgnoreCase(IConstants.MUNCIPLE_ELECTION_TYPE))
							{
								tehsil = localbody +" Muncipality";
								if(booth.getLocalBody() != null && booth.getLocalBody().getLocalElectionBodyId() != null)
										{
								List<Object> assemblyLocalbodyId = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(booth.getLocalBody().getLocalElectionBodyId().toString()));
								List<Object[]> wardNames = userVoterDetailsDAO.getWardBYLocalElectionBodyId((Long) assemblyLocalbodyId.get(0) ,publicationId ,userId);
								if(wardNames != null && wardNames.size() > 0)
									for(Object[] wardparams : wardNames)
									{
										wardMap.put((Long)wardparams[0],wardparams[1]!= null ?  wardparams[1].toString() :"");
									}
										}
							}
						   }
							
						}
						
					}
					else if(type.equalsIgnoreCase(IConstants.CONST_TYPE_URBAN))
					{
						if (booth.getLocalBody() != null)
							localbody = booth.getLocalBody().getName();
				           if(localbody != null && localbody != "")
							{
				        	   if( booth.getLocalBody().getElectionType() != null)
				        	   {
								String electionType = booth.getLocalBody().getElectionType().getElectionType();
								if(electionType.equalsIgnoreCase(IConstants.GHMC) || electionType.equalsIgnoreCase(IConstants.CORPORATION_ELECTION_TYPE))
								{
								tehsil = localbody +" Corporation";
								if(booth.getLocalBodyWard() != null)
								wardName = booth.getLocalBodyWard().getName();
								}
						      
							else if(electionType.equalsIgnoreCase(IConstants.MUNCIPLE_ELECTION_TYPE))
							{
								tehsil = localbody +" Muncipality";
								if(booth.getLocalBody() != null && booth.getLocalBody().getLocalElectionBodyId() != null)
										{
								List<Object> assemblyLocalbodyId = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(booth.getLocalBody().getLocalElectionBodyId().toString()));
								List<Object[]> wardNames = userVoterDetailsDAO.getWardBYLocalElectionBodyId((Long) assemblyLocalbodyId.get(0) ,publicationId ,userId);
								if(wardNames != null && wardNames.size() > 0)
									for(Object[] wardparams : wardNames)
									{
										wardMap.put((Long)wardparams[0],wardparams[1]!= null ?  wardparams[1].toString() :"");
									}
									}
								
							}
				       }
					}
						
					}
					votervo1.setTehsilName(tehsil);
					votervo1.setPanchayatName(panchayat);
					votervo1.setHamletName(hamletName);
					votervo1.setWardName(wardName);
					basicInfo.put(boothId, votervo1);
					
				}		
				
				List<Object[]> list1 = boothPublicationVoterDAO.getFamilyWiseInfoForBooth(boothId, boothHousesMap.get(boothId));
				  Map<String,List<VoterHouseInfoVO>> resultmap = new HashMap<String, List<VoterHouseInfoVO>>();
				  List<Long> voterIds = new ArrayList<Long>();
				  
				  for(Object[] params1 :list1)
				  {
					  List<VoterHouseInfoVO> voterDetails = resultmap.get(params1[0].toString());
					  Voter voter = (Voter) params1[1];
					  VoterHouseInfoVO vo = new VoterHouseInfoVO();
					  if(voterDetails == null)
					  {
						  voterDetails = new ArrayList<VoterHouseInfoVO>();
						  resultmap.put(params1[0].toString(), voterDetails);
					  }
					vo.setVoterIdCardNo(voter.getVoterIDCardNo());
					vo.setVoterId(voter.getVoterId());
					vo.setName(voter.getName());
					vo.setAge(voter.getAge());
					vo.setGender(voter.getGender());
					vo.setHouseNo("# " +voter.getHouseNo());
					voterIds.add(voter.getVoterId());
					voterDetails.add(vo);
					}
				 Map<Long,String> voterCaste =new HashMap<Long, String>();
				 List<Object[]> casteInfo =  userVoterDetailsDAO.getCasteForVoter(voterIds);
					if(casteInfo != null && casteInfo.size() > 0)
						for(Object[] casteName : casteInfo)
						{
							String caste = voterCaste.get((Long)casteName[0]);
							if(caste == null)
							voterCaste.put((Long)casteName[0],casteName[1].toString());
							else
							voterCaste.put((Long)casteName[0],caste);	
						}
					
				
				  for(String houseNo : resultmap.keySet())
				  {
					  
					  VoterHouseInfoVO voterHouseInfoVO = new VoterHouseInfoVO();
					  voterHouseInfoVO.setBoothId(boothId);
					  voterHouseInfoVO.setPartNo(boothDAO.get(boothId).getPartNo());
					  voterHouseInfoVO.setHouseNo("#  " +houseNo);
					  List<VoterHouseInfoVO> voterDetails = resultmap.get(houseNo.toString());
					 
					  voterHouseInfoVO.setCount(new Long(voterDetails.size()));
					  voterHouseInfoVO.setElder(voterDetails.get(voterDetails.size()-1).getName());
					  voterHouseInfoVO.setElderAge((voterDetails.get(voterDetails.size()-1).getAge()));
					  voterHouseInfoVO.setElderGender(voterDetails.get(voterDetails.size()-1).getGender());
					  voterHouseInfoVO.setVoterIdCardNo(voterDetails.get(voterDetails.size()-1).getVoterIdCardNo().toString());
					  String cast = voterCaste.get(voterDetails.get(voterDetails.size()-1).getVoterId());
					  voterHouseInfoVO.setElderCaste(cast != null ? cast : " ");
					  VoterHouseInfoVO basicVo = basicInfo.get(boothId);
					
					  voterHouseInfoVO.setTehsilName(basicVo.getTehsilName());
					  if(basicVo.getTehsilName().contains("Muncipality") && wardMap != null)
					  voterHouseInfoVO.setWardName(wardMap.get(basicVo.getVoterId()) != null ?wardMap.get(basicVo.getVoterId()) : "");
					  else if(basicVo.getTehsilName().contains("Corporation"))  
					  voterHouseInfoVO.setWardName(basicVo.getWardName() !=null ? basicVo.getWardName().toString() : "");
					  voterHouseInfoVO.setPanchayatName(basicVo.getPanchayatName());
					  voterHouseInfoVO.setHamletName(basicVo.getHamletName());
					  boolean flag = false;
					  List<VoterHouseInfoVO> youngervoterDetails  = voterDetails;
					 
							for(int i=0;i<youngervoterDetails.size()-1;i++)
							{
								if(youngervoterDetails.get(i).getGender().equalsIgnoreCase("M"))
								{
									voterHouseInfoVO.setYounger(youngervoterDetails.get(i).getName());
									voterHouseInfoVO.setYoungerAge(youngervoterDetails.get(i).getAge());
									voterHouseInfoVO.setYoungerGender(youngervoterDetails.get(i).getGender());
									voterHouseInfoVO.setVoterGroup(youngervoterDetails.get(i).getVoterIdCardNo().toString());
									flag = true;
								}
							}
							if(!flag)
							{
								voterHouseInfoVO.setYounger(youngervoterDetails.get(0).getName());
								voterHouseInfoVO.setYoungerAge(youngervoterDetails.get(0).getAge());
								voterHouseInfoVO.setYoungerGender(youngervoterDetails.get(0).getGender());
								voterHouseInfoVO.setVoterGroup(youngervoterDetails.get(0).getVoterIdCardNo().toString());
							}
							
							result.add(voterHouseInfoVO);
							if(totalList != null && totalList.size() > 0)
							result.get(0).setConstituencyType(type);
						    result.get(0).setTotalHousesCount(new Long(totalList.size()));
				  }
				 /* try{
				    Object[] values = constituencyDAO.constituencyName(constituencyId).get(0);
			    	String constituenyName = values[0].toString().toUpperCase();
			    	String districtName = values[1].toString().toUpperCase();
			    	Long constituenyNo = delimitationConstituencyDAO.getConstituencyNo(constituencyId,2009l);
				    String filePath = "VMR"+"/"+""+districtName+"_"+constituenyNo+"_"+constituenyName+".xls";
				    String FILE = path+filePath;
				    //File file  = new File(FILE);
				    //file.createNewFile();
				    FileOutputStream out = new FileOutputStream(FILE);
				    
					HSSFWorkbook workbook = new HSSFWorkbook(); 
					HSSFSheet sheet  = workbook.createSheet("report");
					ageWiseExcelsGenerationService.generateExcelsForImportaneFamiles(result , sheet, workbook,type);
					workbook.write(out);
				  }
				  catch(Exception e)
				  {
					  e.printStackTrace();
				  }*/
				 }
			}
			
		
			catch(Exception e)
			{
				LOG.error("Exception raised in getFamilyDetailsForConstituency() method in Suggestive Model Service",e);
				
			}
			return result;
		}
		
		public Comparator<SelectOptionVO> votesSort = new Comparator<SelectOptionVO>()
			{						  
			   public int compare(SelectOptionVO vo1, SelectOptionVO vo2)
				{
				   return (vo2.getTotalCount().intValue()) - (vo1.getTotalCount().intValue());
				}
			};
}