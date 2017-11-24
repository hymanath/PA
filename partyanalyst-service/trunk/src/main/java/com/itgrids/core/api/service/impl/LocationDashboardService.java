package com.itgrids.core.api.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.itgrids.core.api.service.ILocationDashboardService;
import com.itgrids.core.api.service.ILocationWiseElectionInformationDetalsService;
import com.itgrids.partyanalyst.dao.IActivityDAO;
import com.itgrids.partyanalyst.dao.IActivityLocationInfoDAO;
import com.itgrids.partyanalyst.dao.IApplicationStatusDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoardLevelDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothInchargeCommitteeDAO;
import com.itgrids.partyanalyst.dao.IBoothInchargeDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.ICasteCategoryDAO;
import com.itgrids.partyanalyst.dao.ICensusDAO;
import com.itgrids.partyanalyst.dao.IConstituencyCensusDetailsDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictConstituenciesDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.IEnrollmentYearDAO;
import com.itgrids.partyanalyst.dao.IGovtSchemeBeneficiaryDetailsDAO;
import com.itgrids.partyanalyst.dao.IGovtSchemeBenefitsInfoDAO;
import com.itgrids.partyanalyst.dao.IInsuranceStatusDAO;
import com.itgrids.partyanalyst.dao.INominatedPostApplicationDAO;
import com.itgrids.partyanalyst.dao.INominatedPostDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IParliamentAssemblyDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingStatusDAO;
import com.itgrids.partyanalyst.dao.IPositionDAO;
import com.itgrids.partyanalyst.dao.IPublicRepresentativeDAO;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDetailsNewDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateLocationNewDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalDesignationTargetDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalToursMonthDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCandidateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCasteInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentYearDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeLevelDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dao.hibernate.VoterAgeRangeDAO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.BenefitCandidateVO;
import com.itgrids.partyanalyst.dto.BoothInchargesVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsForConstituencyTypesVO;
import com.itgrids.partyanalyst.dto.CandidateInfoForConstituencyVO;
import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.ConstituencyCadreVO;
import com.itgrids.partyanalyst.dto.ElectionInformationVO;
import com.itgrids.partyanalyst.dto.GrivenceStatusVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.LocationVotersVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.MeetingsVO;
import com.itgrids.partyanalyst.dto.NominatedPostDashboardVO;
import com.itgrids.partyanalyst.dto.NominatedPostDetailsVO;
import com.itgrids.partyanalyst.dto.ToursBasicVO;
import com.itgrids.partyanalyst.model.BoardLevel;
import com.itgrids.partyanalyst.model.CasteCategory;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.EnrollmentYear;
import com.itgrids.partyanalyst.model.Position;
import com.itgrids.partyanalyst.model.TdpCommitteeLevel;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.VoterAgeRange;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class LocationDashboardService  implements ILocationDashboardService  {
	private final static Logger LOG = Logger.getLogger(LocationDashboardService.class);
	private INominationDAO nominationDAO;
	private IConstituencyDAO constituencyDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IVoterAgeInfoDAO voterAgeInfoDAO;
	private ITdpCadreEnrollmentYearDAO tdpCadreEnrollmentYearDAO;
	private IVoterCastInfoDAO voterCastInfoDAO;
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IEnrollmentYearDAO enrollmentYearDAO;
	private ICasteCategoryDAO casteCategoryDAO;
	private IPartyMeetingStatusDAO partyMeetingStatusDAO;
	private ITdpCommitteeDAO tdpCommitteeDAO;
	private ICadreDetailsService cadreDetailsService;
	private CommonMethodsUtilService commonMethodsUtilService;
	//tour daos
	private ISelfAppraisalCandidateLocationNewDAO selfAppraisalCandidateLocationNewDAO;
	private ISelfAppraisalDesignationTargetDAO selfAppraisalDesignationTargetDAO;
	private ISelfAppraisalCandidateDetailsNewDAO selfAppraisalCandidateDetailsNewDAO;
	private ISelfAppraisalToursMonthDAO selfAppraisalToursMonthDAO;

	private IGovtSchemeBeneficiaryDetailsDAO govtSchemeBeneficiaryDetailsDAO;
	private ITdpCadreEnrollmentInfoDAO tdpCadreEnrollmentInfoDAO;
	private IInsuranceStatusDAO insuranceStatusDAO;
	private INominatedPostDAO nominatedPostDAO;
	private INominatedPostApplicationDAO nominatedPostApplicationDAO;
	private IBoardLevelDAO boardLevelDAO;
	private IElectionTypeDAO electionTypeDAO;
	private IPublicationDateDAO publicationDateDAO;
	//Activity
	private IActivityDAO activityDAO;
	private IConstituencyCensusDetailsDAO constituencyCensusDetailsDAO;
	private IDistrictDAO districtDAO;
	private IDistrictConstituenciesDAO districtConstituenciesDAO;
	private ICandidateDAO candidateDAO;
	private ICadreCommitteeService cadreCommitteeService;
	private IBoothInchargeCommitteeDAO boothInchargeCommitteeDAO;
	private IBoothInchargeDAO boothInchargeDAO;
	private IPositionDAO positionDAO;
	private IActivityLocationInfoDAO activityLocationInfoDAO;
	
	private IApplicationStatusDAO applicationStatusDAO;
	private IRegionServiceData regionServiceDataImp;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private IPanchayatDAO panchayatDAO;
 
	private IGovtSchemeBenefitsInfoDAO govtSchemeBenefitsInfoDAO;
	private IParliamentAssemblyDAO parliamentAssemblyDAO;
	private IBoothDAO boothDAO;
	private ITdpCadreCandidateDAO tdpCadreCandidateDAO;
	private IPublicRepresentativeDAO publicRepresentativeDAO;
	private ITdpCommitteeLevelDAO tdpCommitteeLevelDAO;
	private IPartyDAO partyDAO;
	private IElectionDAO electionDAO;
	private IDelimitationConstituencyMandalDetailsDAO delimitationConstituencyMandalDetailsDAO;
	private ILocationWiseElectionInformationDetalsService locationWiseElectionInformationDetalsService;
	
	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}
	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}
	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}
	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}
	public ITdpCommitteeLevelDAO getTdpCommitteeLevelDAO() {
		return tdpCommitteeLevelDAO;
	}
	public void setTdpCommitteeLevelDAO(ITdpCommitteeLevelDAO tdpCommitteeLevelDAO) {
		this.tdpCommitteeLevelDAO = tdpCommitteeLevelDAO;
	}
	
	public ISelfAppraisalCandidateLocationNewDAO getSelfAppraisalCandidateLocationNewDAO() {
		return selfAppraisalCandidateLocationNewDAO;
	}
	public ISelfAppraisalDesignationTargetDAO getSelfAppraisalDesignationTargetDAO() {
		return selfAppraisalDesignationTargetDAO;
	}
	public ISelfAppraisalCandidateDetailsNewDAO getSelfAppraisalCandidateDetailsNewDAO() {
		return selfAppraisalCandidateDetailsNewDAO;
	}
	public ISelfAppraisalToursMonthDAO getSelfAppraisalToursMonthDAO() {
		return selfAppraisalToursMonthDAO;
	}
	public IGovtSchemeBeneficiaryDetailsDAO getGovtSchemeBeneficiaryDetailsDAO() {
		return govtSchemeBeneficiaryDetailsDAO;
	}
	public ITdpCadreEnrollmentInfoDAO getTdpCadreEnrollmentInfoDAO() {
		return tdpCadreEnrollmentInfoDAO;
	}
	public IInsuranceStatusDAO getInsuranceStatusDAO() {
		return insuranceStatusDAO;
	}
	public ITdpCadreCandidateDAO getTdpCadreCandidateDAO() {
		return tdpCadreCandidateDAO;
	}
	public void setTdpCadreCandidateDAO(ITdpCadreCandidateDAO tdpCadreCandidateDAO) {
		this.tdpCadreCandidateDAO = tdpCadreCandidateDAO;
	}
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}
	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	public IParliamentAssemblyDAO getParliamentAssemblyDAO() {
		return parliamentAssemblyDAO;
	}
	public void setParliamentAssemblyDAO(
			IParliamentAssemblyDAO parliamentAssemblyDAO) {
		this.parliamentAssemblyDAO = parliamentAssemblyDAO;
	}
	public IPositionDAO getPositionDAO() {
			return positionDAO;
	}
	public void setPositionDAO(IPositionDAO positionDAO) {
			this.positionDAO = positionDAO;
	}
	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}
	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}
	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}
	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}
	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}
	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}
	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}
	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}
	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	public IApplicationStatusDAO getApplicationStatusDAO() {
		return applicationStatusDAO;
	}
	public void setApplicationStatusDAO(IApplicationStatusDAO applicationStatusDAO) {
		this.applicationStatusDAO = applicationStatusDAO;
	}
	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}
	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}

	public IPublicationDateDAO getPublicationDateDAO() {
		return publicationDateDAO;
	}
	public void setPublicationDateDAO(IPublicationDateDAO publicationDateDAO) {
		this.publicationDateDAO = publicationDateDAO;
	}
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public IElectionTypeDAO getElectionTypeDAO() {
		return electionTypeDAO;
	}
	public void setElectionTypeDAO(IElectionTypeDAO electionTypeDAO) {
		this.electionTypeDAO = electionTypeDAO;
	}
	public void setConstituencyCensusDetailsDAO(IConstituencyCensusDetailsDAO constituencyCensusDetailsDAO) {
		this.constituencyCensusDetailsDAO = constituencyCensusDetailsDAO;
	}
	public void setCensusDAO(ICensusDAO censusDAO) {
		this.censusDAO = censusDAO;
	}
	private ICensusDAO censusDAO;
	private ITehsilDAO tehsilDAO;
	private VoterAgeRangeDAO voterAgeRangeDAO;
	private ITdpCadreCasteInfoDAO tdpCadreCasteInfoDAO;
	
	
	public ITdpCadreCasteInfoDAO getTdpCadreCasteInfoDAO() {
		return tdpCadreCasteInfoDAO;
	}
	public void setTdpCadreCasteInfoDAO(ITdpCadreCasteInfoDAO tdpCadreCasteInfoDAO) {
		this.tdpCadreCasteInfoDAO = tdpCadreCasteInfoDAO;
	}
	public VoterAgeRangeDAO getVoterAgeRangeDAO() {
		return voterAgeRangeDAO;
	}
	public void setVoterAgeRangeDAO(VoterAgeRangeDAO voterAgeRangeDAO) {
		this.voterAgeRangeDAO = voterAgeRangeDAO;
	}
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}
	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}
	public IConstituencyCensusDetailsDAO getConstituencyCensusDetailsDAO(){
		return constituencyCensusDetailsDAO;

	}
	public ICensusDAO getCensusDAO(){
		return censusDAO;

	}
	public IActivityDAO getActivityDAO() {
		return activityDAO;
	}
	public void setActivityDAO(IActivityDAO activityDAO) {
		this.activityDAO = activityDAO;
	}
	public void setInsuranceStatusDAO(IInsuranceStatusDAO insuranceStatusDAO) {
		this.insuranceStatusDAO = insuranceStatusDAO;
	}

	public ICadreDetailsService getCadreDetailsService() {
		return cadreDetailsService;
	}

	public void setCadreDetailsService(ICadreDetailsService cadreDetailsService) {
		this.cadreDetailsService = cadreDetailsService;
	}

	public ITdpCommitteeDAO getTdpCommitteeDAO() {
		return tdpCommitteeDAO;
	}

	public void setTdpCommitteeDAO(ITdpCommitteeDAO tdpCommitteeDAO) {
		this.tdpCommitteeDAO = tdpCommitteeDAO;
	}

	public IPartyMeetingStatusDAO getPartyMeetingStatusDAO() {
		return partyMeetingStatusDAO;
	}

	public void setPartyMeetingStatusDAO(IPartyMeetingStatusDAO partyMeetingStatusDAO) {
		this.partyMeetingStatusDAO = partyMeetingStatusDAO;
	}

	public ICasteCategoryDAO getCasteCategoryDAO() {
		return casteCategoryDAO;
	}

	public void setCasteCategoryDAO(ICasteCategoryDAO casteCategoryDAO) {
		this.casteCategoryDAO = casteCategoryDAO;
	}

	public IEnrollmentYearDAO getEnrollmentYearDAO() {
		return enrollmentYearDAO;
	}

	public void setEnrollmentYearDAO(IEnrollmentYearDAO enrollmentYearDAO) {
		this.enrollmentYearDAO = enrollmentYearDAO;
	}

	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
		return userVoterDetailsDAO;
	}

	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}

	public IVoterCastInfoDAO getVoterCastInfoDAO() {
		return voterCastInfoDAO;
	}

	public void setVoterCastInfoDAO(IVoterCastInfoDAO voterCastInfoDAO) {
		this.voterCastInfoDAO = voterCastInfoDAO;
	}

	public ITdpCadreEnrollmentYearDAO getTdpCadreEnrollmentYearDAO() {
		return tdpCadreEnrollmentYearDAO;
	}

	public void setTdpCadreEnrollmentYearDAO(ITdpCadreEnrollmentYearDAO tdpCadreEnrollmentYearDAO) {
		this.tdpCadreEnrollmentYearDAO = tdpCadreEnrollmentYearDAO;
	}

	public IVoterAgeInfoDAO getVoterAgeInfoDAO() {
		return voterAgeInfoDAO;
	}

	public void setVoterAgeInfoDAO(IVoterAgeInfoDAO voterAgeInfoDAO) {
		this.voterAgeInfoDAO = voterAgeInfoDAO;
	}

	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}
	public INominatedPostDAO getNominatedPostDAO() {
		return nominatedPostDAO;
	}

	public void setNominatedPostDAO(INominatedPostDAO nominatedPostDAO) {
		this.nominatedPostDAO = nominatedPostDAO;
	}

	public INominatedPostApplicationDAO getNominatedPostApplicationDAO() {
		return nominatedPostApplicationDAO;
	}
	public void setSelfAppraisalCandidateLocationNewDAO(
			ISelfAppraisalCandidateLocationNewDAO selfAppraisalCandidateLocationNewDAO) {
		this.selfAppraisalCandidateLocationNewDAO = selfAppraisalCandidateLocationNewDAO;
	}

	public void setSelfAppraisalDesignationTargetDAO(
			ISelfAppraisalDesignationTargetDAO selfAppraisalDesignationTargetDAO) {
		this.selfAppraisalDesignationTargetDAO = selfAppraisalDesignationTargetDAO;
	}

	public void setSelfAppraisalCandidateDetailsNewDAO(
			ISelfAppraisalCandidateDetailsNewDAO selfAppraisalCandidateDetailsNewDAO) {
		this.selfAppraisalCandidateDetailsNewDAO = selfAppraisalCandidateDetailsNewDAO;
	}

	public void setSelfAppraisalToursMonthDAO(
			ISelfAppraisalToursMonthDAO selfAppraisalToursMonthDAO) {
		this.selfAppraisalToursMonthDAO = selfAppraisalToursMonthDAO;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public void setGovtSchemeBeneficiaryDetailsDAO(
			IGovtSchemeBeneficiaryDetailsDAO govtSchemeBeneficiaryDetailsDAO) {
		this.govtSchemeBeneficiaryDetailsDAO = govtSchemeBeneficiaryDetailsDAO;
	}
	public void setTdpCadreEnrollmentInfoDAO(
			ITdpCadreEnrollmentInfoDAO tdpCadreEnrollmentInfoDAO) {
		this.tdpCadreEnrollmentInfoDAO = tdpCadreEnrollmentInfoDAO;
	}

	public void setNominatedPostApplicationDAO(
			INominatedPostApplicationDAO nominatedPostApplicationDAO) {
		this.nominatedPostApplicationDAO = nominatedPostApplicationDAO;
	}

	public IBoardLevelDAO getBoardLevelDAO() {
		return boardLevelDAO;
	}

	public void setBoardLevelDAO(IBoardLevelDAO boardLevelDAO) {
		this.boardLevelDAO = boardLevelDAO;
	}

	public IDistrictConstituenciesDAO getDistrictConstituenciesDAO() {
		return districtConstituenciesDAO;
	}
	public void setDistrictConstituenciesDAO(
			IDistrictConstituenciesDAO districtConstituenciesDAO) {
		this.districtConstituenciesDAO = districtConstituenciesDAO;
	}

	public ICandidateDAO getCandidateDAO() {
		return candidateDAO;
	}
	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}
	public IBoothInchargeCommitteeDAO getBoothInchargeCommitteeDAO() {
		return boothInchargeCommitteeDAO;
	}
	public void setBoothInchargeCommitteeDAO(
			IBoothInchargeCommitteeDAO boothInchargeCommitteeDAO) {
		this.boothInchargeCommitteeDAO = boothInchargeCommitteeDAO;
	}
	public IBoothInchargeDAO getBoothInchargeDAO() {
		return boothInchargeDAO;
	}
	public void setBoothInchargeDAO(IBoothInchargeDAO boothInchargeDAO) {
		this.boothInchargeDAO = boothInchargeDAO;
	}
	
	
	public IActivityLocationInfoDAO getActivityLocationInfoDAO() {
		return activityLocationInfoDAO;
	}
	public void setActivityLocationInfoDAO(
			IActivityLocationInfoDAO activityLocationInfoDAO) {
		this.activityLocationInfoDAO = activityLocationInfoDAO;
	}
	
	public IGovtSchemeBenefitsInfoDAO getGovtSchemeBenefitsInfoDAO() {
		return govtSchemeBenefitsInfoDAO;
	}
	public void setGovtSchemeBenefitsInfoDAO(
			IGovtSchemeBenefitsInfoDAO govtSchemeBenefitsInfoDAO) {
		this.govtSchemeBenefitsInfoDAO = govtSchemeBenefitsInfoDAO;
	}
	
	public IPublicRepresentativeDAO getPublicRepresentativeDAO() {
		return publicRepresentativeDAO;
	}
	public void setPublicRepresentativeDAO(
			IPublicRepresentativeDAO publicRepresentativeDAO) {
		this.publicRepresentativeDAO = publicRepresentativeDAO;
	}//roleIds,committeeIds,enrollmentYears,basicCommoteeId,enrollmentId
	
	public IDelimitationConstituencyMandalDetailsDAO getDelimitationConstituencyMandalDetailsDAO() {
		return delimitationConstituencyMandalDetailsDAO;
	}
	public void setDelimitationConstituencyMandalDetailsDAO(
			IDelimitationConstituencyMandalDetailsDAO delimitationConstituencyMandalDetailsDAO) {
		this.delimitationConstituencyMandalDetailsDAO = delimitationConstituencyMandalDetailsDAO;
	}
	
	public ILocationWiseElectionInformationDetalsService getLocationWiseElectionInformationDetalsService() {
		return locationWiseElectionInformationDetalsService;
	}
	public void setLocationWiseElectionInformationDetalsService(
			ILocationWiseElectionInformationDetalsService locationWiseElectionInformationDetalsService) {
		this.locationWiseElectionInformationDetalsService = locationWiseElectionInformationDetalsService;
	}
	
	public List<CandidateDetailsForConstituencyTypesVO> getCandidateAndPartyInfoForConstituency(Long locationValue,Long locationTypeId,List<Long> representativTypeIds,List<Long> roleIds,List<Long> committeeIds,List<Long> enrollmentYears,Long basicCommoteeId,Long enrollmentId) {
		List<CandidateDetailsForConstituencyTypesVO> finalList= new ArrayList<CandidateDetailsForConstituencyTypesVO>();
		List<CandidateInfoForConstituencyVO> parliementfinalList= new ArrayList<CandidateInfoForConstituencyVO>();

		try {
			String electionType = "Assembly";
			String isnew = "false";
			List<Long> consistuencyIds = new ArrayList<Long>();
			List<Long> candidateIds = new ArrayList<Long>();
			
			if (locationTypeId != null && locationTypeId == 3l) {
				List<Object[]> constituencyList = constituencyDAO.getAllConstituenciesInADistrict(locationValue);
				for (Object[] objects : constituencyList) {
					if (objects != null) {
						consistuencyIds.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
					}
				}
			} else if (locationTypeId != null && locationTypeId == 4l) {
				consistuencyIds.add(locationValue);
			} else if (locationTypeId != null && locationTypeId == 10l) {
				@SuppressWarnings("unchecked")
				List<Object[]> findAssembliesConstituencies = (List<Object[]>) delimitationConstituencyAssemblyDetailsDAO
						.findAssembliesConstituencies(locationValue);
				for (Object[] objects : findAssembliesConstituencies) {
					if (objects != null) {
						consistuencyIds.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
					}
				}
				
			  }else if(locationTypeId != null && locationTypeId == 5l) {
				   List<Long>  constituencyIdMan=tehsilDAO.getAllConstituenciesByTehilId(locationValue);	
				consistuencyIds.addAll(constituencyIdMan);
				
			  }else if(locationTypeId != null && locationTypeId == 6l) {
				  List<Long>   constituencyIdPan=tehsilDAO.getAllConstituenciesByPanchayatId(locationValue);	
					consistuencyIds.addAll(constituencyIdPan);
			  }	else if(locationTypeId != null && locationTypeId == 7l) {
				  List<Long>   constituencyIdMun=tehsilDAO.getAllConstituenciesByLocalElectionBodyId(locationValue);	
					consistuencyIds.addAll(constituencyIdMun);
			  }		
			List<Object[]> parlimentlist = null;
			if(locationTypeId != null && locationTypeId != 2l){
			   parlimentlist = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssemblyIds(consistuencyIds);
			}
			for (Long constituencyId : consistuencyIds) {
				@SuppressWarnings("rawtypes")
				List candidateList = null;
				CandidateDetailsForConstituencyTypesVO candidateDetailsForConstituencyTypesVO = new CandidateDetailsForConstituencyTypesVO();
				candidateList = nominationDAO.getCandidateAndPartyInfo(constituencyId, electionType, 1L);
				List<String> yearList = nominationDAO.getCandidateAndPartyInfoYear(constituencyId,electionType);
				if (yearList.size() > 0 && candidateList.size() > 0) {
					Object[] values = (Object[]) candidateList.get(0);
					if (!(yearList.get(0).equalsIgnoreCase(values[11].toString()))) {
						isnew = "true";
					}
				}
				LOG.info("Nomination List :" + candidateList.size());
				CandidateInfoForConstituencyVO matchedVo = null;
				CandidateInfoForConstituencyVO candMatchedVO = null;
				if (candidateList.size() != 0) {
					String isMigrate ="false";
					List<CandidateInfoForConstituencyVO> candidateInfoList = extractCandidateNPartyDataFromList(candidateList,candidateIds);
					LOG.info("Candidate Info :" + candidateInfoList.size());
					List<Object[]> cadreCandidateDeatils = tdpCadreCandidateDAO.nomiantionCandidateDetails(candidateIds);
					List<Object[]> candidateDesignations = publicRepresentativeDAO.getLocationWiseCandidateDesignations(candidateIds);
					if(cadreCandidateDeatils != null && cadreCandidateDeatils.size()>0){
						for(Object[] param :cadreCandidateDeatils){
					       matchedVo = getMatchedVOForCadreId(candidateInfoList,commonMethodsUtilService.getLongValueForObject(param[1]));
					       if(matchedVo != null){
					    	   matchedVo.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(param[0]));
					    	   matchedVo.setEducation(commonMethodsUtilService.getStringValueForObject(param[2]));//cadreImage
					    	   if(matchedVo.getPartyId() !=872 && matchedVo.getTdpCadreId()!= null){
						    	   isMigrate ="true";
						       }
					       }
						}
					}
					if(candidateDesignations != null && candidateDesignations.size()>0){
						for(Object[] param : candidateDesignations){
							candMatchedVO = getMatchedVOForCadreId(candidateInfoList,commonMethodsUtilService.getLongValueForObject(param[0]));
							if(candMatchedVO != null){
								candMatchedVO.setCandDesignation(commonMethodsUtilService.getStringValueForObject(param[2]));
							}
						}
					}
					candidateDetailsForConstituencyTypesVO.setAssemblyCandidateInfo(candidateInfoList);
					candidateDetailsForConstituencyTypesVO.setIspartial(isnew);
					candidateDetailsForConstituencyTypesVO.setMigrateCandidate(isMigrate);
					finalList.add(candidateDetailsForConstituencyTypesVO);
				}
			}
			List<Long> parliamentCandidateIds = new ArrayList<Long>();
			if(parlimentlist != null){
			for (Object[] object : parlimentlist) {
				String isMigrate ="false";
				//CandidateDetailsForConstituencyTypesVO candidateDetailsForConstituencyTypesVO = new CandidateDetailsForConstituencyTypesVO();
				Long asemblyId = commonMethodsUtilService.getLongValueForObject(object[0]);
				List result = nominationDAO.getParliamentCandidateNPartyInfo(asemblyId, IConstants.PARLIAMENT_ELECTION_TYPE, 1L);
				if (result.size() != 0) {
					for (int i = 0; i < result.size(); i++) {
						String candidateFullName = "";
						CandidateInfoForConstituencyVO candidateInfo1 = new CandidateInfoForConstituencyVO();
						Object[] values = (Object[]) result.get(i);
						candidateInfo1.setConstituencyId((Long) values[0]);
						candidateInfo1.setConstituencyName(values[1].toString());
						if(!candidateIds.contains((Long) values[2]))
							candidateIds.add((Long) values[2]);
						candidateInfo1.setCandidateId((Long) values[2]);
						parliamentCandidateIds.add(candidateInfo1.getCandidateId());
						if (!StringUtils.isBlank((String) values[3]))
							candidateFullName = candidateFullName+ ((String) values[3]) + " ";
						if (!StringUtils.isBlank((String) values[4]))
							candidateFullName = candidateFullName+ ((String) values[4]) + " ";
						if (!StringUtils.isBlank((String) values[5]))
							candidateFullName = candidateFullName+ ((String) values[5]);

						candidateInfo1.setCandidateName(candidateFullName);
						candidateInfo1.setPartyId((Long) values[6]);
						candidateInfo1.setParty(values[7].toString());
						candidateInfo1.setConstituencyType(values[9].toString());
						if (values[8] == null || values[8].toString().length() == 0)
							candidateInfo1.setDeformDate("");
						else
							candidateInfo1.setDeformDate(values[9].toString());
						if (values[10] != null) {
							candidateInfo1.setPartyFlag(values[10].toString());
						}
						candidateInfo1.setLatestElecYear(values[11].toString());
						candidateInfo1.setMigrateCandidate(isMigrate);
						//candidateDetailsForConstituencyTypesVO.setParliamentCandidateInfo(candidateInfo1);
						parliementfinalList.add(candidateInfo1);
					}
				}
				//parliementfinalList.add(candidateInfo1);
				List<Object[]> parliamentCandidateDeatils = tdpCadreCandidateDAO.nomiantionCandidateDetails(parliamentCandidateIds);
				List<Object[]> parlaimentcandidateDesignations = publicRepresentativeDAO.getLocationWiseCandidateDesignations(parliamentCandidateIds);
				CandidateInfoForConstituencyVO matchedVo1 = null;
				CandidateInfoForConstituencyVO candidateMatchedVO = null;
				if(parliamentCandidateDeatils != null && parliamentCandidateDeatils.size()>0){
					for(Object[] param : parliamentCandidateDeatils){
						 matchedVo1 = getMatchedVOForCadreId(parliementfinalList,commonMethodsUtilService.getLongValueForObject(param[1]));
					       if(matchedVo1 != null){
					    	   matchedVo1.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(param[0]));
					    	   matchedVo1.setEducation(commonMethodsUtilService.getStringValueForObject(param[2]));//cadreImage
					    	   if(matchedVo1.getPartyId() !=872 && matchedVo1.getTdpCadreId()!= null){
						    	   isMigrate ="true";
						       }
					       }
					}
				}
				if(parlaimentcandidateDesignations != null && parlaimentcandidateDesignations.size()>0){
					for(Object[] obj:parlaimentcandidateDesignations){
						 candidateMatchedVO =getMatchedVOForCadreId(parliementfinalList, commonMethodsUtilService.getLongValueForObject(obj[0]));
						 if(candidateMatchedVO != null){
							 candidateMatchedVO.setCandDesignation(commonMethodsUtilService.getStringValueForObject(obj[2]));
						 }
					}
				}
			}
		}
			
			if(locationTypeId != null && locationTypeId != 2){
			    finalList.get(0).setSubList1(parliementfinalList);
			}else{
				Map<Long,CandidateDetailsForConstituencyTypesVO> postinsMap = new HashMap<Long,CandidateDetailsForConstituencyTypesVO>();
				List<Object[]> stateCandidateDesignations = publicRepresentativeDAO.getStateWiseCandidateDesignations(locationValue,locationTypeId,representativTypeIds);
				List<Object[]> commiteeCandidatObjs=tdpCommitteeDAO.getCommitteeCandidatesByLevelWiseDetails( roleIds, committeeIds, basicCommoteeId, enrollmentId, enrollmentYears);
				List<Long> candateIds = new ArrayList<Long>();
				if(stateCandidateDesignations != null && stateCandidateDesignations.size()>0){
					//CandidateDetailsForConstituencyTypesVO candidateDetailsForConstituencyTypesVO =new CandidateDetailsForConstituencyTypesVO();
					for(Object[] param :stateCandidateDesignations){
						CandidateDetailsForConstituencyTypesVO typeVO = postinsMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
						if(typeVO == null){
							typeVO  = new CandidateDetailsForConstituencyTypesVO();
							postinsMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),typeVO);
						}
						candateIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
						if(!candidateIds.contains(commonMethodsUtilService.getLongValueForObject(param[0]))){
							candidateIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
						}
						typeVO.setCondidateId(commonMethodsUtilService.getLongValueForObject(param[0]));//candidateId
						typeVO.setCandidateName(commonMethodsUtilService.getStringValueForObject(param[1]));//candidateName
						typeVO.setRepresentativeLevelId(commonMethodsUtilService.getLongValueForObject(param[2]));
						typeVO.setRepresentativeLevel(commonMethodsUtilService.getStringValueForObject(param[3]));
						//typeVO.setPartyFlag(commonMethodsUtilService.getStringValueForObject(param[4]));//party flag
						typeVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(param[4]));
						//typeVO.setCadreId(commonMethodsUtilService.getLongValueForObject(param[6]));//cadreId
						typeVO.setDesignation(commonMethodsUtilService.getStringValueForObject(param[5]));
						//typeVO.setCadreImage(commonMethodsUtilService.getStringValueForObject(param[7]));
						//typeVO.setTotalDesignation(typeVO.getTotalDesignation()+","+commonMethodsUtilService.getStringValueForObject(param[5]));
						finalList.add(typeVO);
						String constituencyName =" ";
						List<Object[]> candidatePartyDetails =nominationDAO.getCandidateNominationPartyDetails(candateIds);
						if(candidatePartyDetails != null && candidatePartyDetails.size()>0){
							for(Object[] objects :candidatePartyDetails){
						CandidateDetailsForConstituencyTypesVO matchedVo= getMatchedVOForCadreIdForPartyId(finalList,commonMethodsUtilService.getLongValueForObject(objects[0])); 
						  if(matchedVo != null){
							  matchedVo.setPartyId(commonMethodsUtilService.getLongValueForObject(objects[1]));
							  matchedVo.setParty(commonMethodsUtilService.getStringValueForObject(objects[2]));
							  matchedVo.setPartyFlag(commonMethodsUtilService.getStringValueForObject(objects[3]));
							  matchedVo.setConstituencyId(commonMethodsUtilService.getLongValueForObject(objects[4]));
							  matchedVo.setConstituencyName(commonMethodsUtilService.getStringValueForObject(objects[5]));
							    constituencyName = commonMethodsUtilService.getStringValueForObject(objects[5]);
							  matchedVo.setConstituencyType(commonMethodsUtilService.getStringValueForObject(objects[6]));
						  }
						 }
						}
						List<Object[]> parliamentCandidateDeatils = tdpCadreCandidateDAO.nomiantionCandidateDetails(candateIds);
						if(parliamentCandidateDeatils != null && parliamentCandidateDeatils.size()>0){
							CandidateDetailsForConstituencyTypesVO candidateMatchedVO = null;
							if(parliamentCandidateDeatils != null && parliamentCandidateDeatils.size()>0){
								for(Object[] param1 : parliamentCandidateDeatils){
									candidateMatchedVO = getMatchedVOForCadreIdForPartyId(finalList,commonMethodsUtilService.getLongValueForObject(param1[1]));
								       if(candidateMatchedVO != null){
								    	   candidateMatchedVO.setCadreId(commonMethodsUtilService.getLongValueForObject(param1[0]));
								    	   candidateMatchedVO.setCadreImage(commonMethodsUtilService.getStringValueForObject(param1[2]));
								       }
								}
							}
						}
						List<Object[]> candidateDesignations = publicRepresentativeDAO.getLocationWiseCandidateDesignations(candateIds);
						CandidateDetailsForConstituencyTypesVO matchedCadreVo =null;
						//String constituencyName = matchedVo.getConstituencyName()
						if(candidateDesignations != null && candidateDesignations.size()>0){
							for(Object[] obj:candidateDesignations){
								   matchedCadreVo= getMatchedVOForCadreIdForPartyId(finalList,commonMethodsUtilService.getLongValueForObject(obj[0]));
								if(matchedCadreVo != null && matchedCadreVo.getTotalDesignation().trim().length() != 0){
									matchedCadreVo.setTotalDesignation(matchedCadreVo.getTotalDesignation()+","+commonMethodsUtilService.getStringValueForObject(obj[2]));
								}else{
									matchedCadreVo.setTotalDesignation(commonMethodsUtilService.getStringValueForObject(obj[2]));
								}
							}
							
							
						}
						//if(matchedCadreVo != null){
							typeVO.setIspartial(constituencyName+" "+typeVO.getTotalDesignation());
						//}
					}// 0-tdpCadreId 1-name 2-image  3-mobileNo	4-memberShipNo	5-role	6-tdpCommitteeLevel	7-tdpCommitteeEnrollmentId
					if(commiteeCandidatObjs !=null && commiteeCandidatObjs.size()  >0){
						for(Object[] param:commiteeCandidatObjs){
							CandidateDetailsForConstituencyTypesVO condidatVo = getMatchedVOForCadreIdForCadreId(finalList,commonMethodsUtilService.getLongValueForObject(param[0])); 
							//CandidateDetailsForConstituencyTypesVO condidatVo=new CandidateDetailsForConstituencyTypesVO();
							if(condidatVo != null){
								condidatVo.setCommitteLevel(condidatVo.getDesignation()+" OF AP , "+commonMethodsUtilService.getStringValueForObject(param[5])+" OF "+commonMethodsUtilService.getStringValueForObject(param[6])+" "+"COMMITTEE");
							}else{
							condidatVo =new CandidateDetailsForConstituencyTypesVO();
							condidatVo.setCadreId(commonMethodsUtilService.getLongValueForObject(param[0]));
							condidatVo.setCandidateName(commonMethodsUtilService.getStringValueForObject(param[1]));
							condidatVo.setCadreImage(commonMethodsUtilService.getStringValueForObject(param[2]));
							condidatVo.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[3]));
							condidatVo.setMemberShipNo(commonMethodsUtilService.getStringValueForObject(param[4]));
							condidatVo.setDesignation(commonMethodsUtilService.getStringValueForObject(param[5]));
							condidatVo.setRepresentativeLevel(commonMethodsUtilService.getStringValueForObject(param[6]));
							condidatVo.setCommitteLevel(condidatVo.getDesignation()+" OF "+commonMethodsUtilService.getStringValueForObject(param[6])+" "+"COMMITTEE");
							condidatVo.setRepresentativeLevelId(commonMethodsUtilService.getLongValueForObject(param[7]));
							condidatVo.setParty("TDP");
							condidatVo.setPartyFlag("TDP.PNG");
							finalList.add(condidatVo);
							}
						
						}
					}
				}
				
			}
			Map<String,List<CandidateInfoForConstituencyVO>> posiCandList = new HashMap<String,List<CandidateInfoForConstituencyVO>>();
			List<Object[]> locPosiCandts = tdpCadreCandidateDAO.getPublicRepresetativesInLocation(locationValue, locationTypeId,representativTypeIds);
			if(commonMethodsUtilService.isListOrSetValid(locPosiCandts)){
				for (Object[] objects : locPosiCandts) {
					if(!candidateIds.contains(commonMethodsUtilService.getLongValueForObject(objects[23]))){
						candidateIds.add(commonMethodsUtilService.getLongValueForObject(objects[23]));
					
					CandidateInfoForConstituencyVO vo = new CandidateInfoForConstituencyVO();
					vo.setTdpCadreId((Long) objects[0]);//cadreId
					vo.setCandidateName(commonMethodsUtilService.getStringValueForObject(objects[1]));//cadreName
					vo.setPartyId(commonMethodsUtilService.getLongValueForObject(objects[2]));//representativeTypeId
					vo.setParty(commonMethodsUtilService.getStringValueForObject(objects[3]));//representativeType
					vo.setEducation(commonMethodsUtilService.getStringValueForObject(objects[6]));//image
					vo.setCandidateId(commonMethodsUtilService.getLongValueForObject(objects[23]));
					//UserAddress ua = (UserAddress)objects[7];
					Long representativeLevl = commonMethodsUtilService.getLongValueForObject(objects[4]);
					Long representativeVal = commonMethodsUtilService.getLongValueForObject(objects[5]);
					//if(ua != null){
						if(representativeLevl != 0l && representativeLevl.longValue() == 1l ){
							vo.setConstituencyId(commonMethodsUtilService.getLongValueForObject(objects[11]));
							vo.setConstituencyName(commonMethodsUtilService.getStringValueForObject(objects[12]));
						}else if(representativeLevl != 0l && representativeLevl.longValue() == 2l){
							vo.setConstituencyId(commonMethodsUtilService.getLongValueForObject(objects[13]));//13
							vo.setConstituencyName(commonMethodsUtilService.getStringValueForObject(objects[14]));//14
						}else if(representativeLevl != 0l &&( representativeLevl.longValue() == 3l || representativeLevl.longValue() == 4l) && representativeVal != null){
							vo.setConstituencyId(constituencyDAO.get(representativeVal).getConstituencyId());
							vo.setConstituencyName(constituencyDAO.get(representativeVal).getName());
						}else if(representativeLevl != 0l && representativeLevl.longValue() == 5l){
							vo.setConstituencyId(commonMethodsUtilService.getLongValueForObject(objects[9]));//9
							vo.setConstituencyName(commonMethodsUtilService.getStringValueForObject(objects[10]));//10
						}else if(representativeLevl != 0l && representativeLevl.longValue() == 6l ){
							vo.setConstituencyId(commonMethodsUtilService.getLongValueForObject(objects[7]));//7
							vo.setConstituencyName(commonMethodsUtilService.getStringValueForObject(objects[8]));//8
						}else if(representativeLevl != 0l && representativeLevl.longValue() == 7l  ){
							vo.setConstituencyId(commonMethodsUtilService.getLongValueForObject(objects[15]));//15
							vo.setConstituencyName(commonMethodsUtilService.getStringValueForObject(objects[16]));//16
						}else if(representativeLevl != 0l && representativeLevl.longValue() == 8l  ){
							vo.setConstituencyId(commonMethodsUtilService.getLongValueForObject(objects[19]));//19
							vo.setConstituencyName(commonMethodsUtilService.getStringValueForObject(objects[20]));//20
						}
				//}
						vo.setPartyFlag(commonMethodsUtilService.getStringValueForObject(objects[24]));
					List<CandidateInfoForConstituencyVO> locPosiCand = posiCandList.get(commonMethodsUtilService.getStringValueForObject(objects[3]));
					if(locPosiCand == null || locPosiCand.size() == 0){
						locPosiCand = new ArrayList<CandidateInfoForConstituencyVO>();
						posiCandList.put(commonMethodsUtilService.getStringValueForObject(objects[3]), locPosiCand);
					}
					locPosiCand.add(vo);
					}
				}
			}
			
			if(commonMethodsUtilService.isMapValid(posiCandList)){
				for(Entry<String,List<CandidateInfoForConstituencyVO>> entry :posiCandList.entrySet()){
					CandidateInfoForConstituencyVO positionVO = new CandidateInfoForConstituencyVO();
					positionVO.setParty(entry.getKey());
					positionVO.setList(entry.getValue());
					finalList.get(0).getList().add(positionVO);
				}
			}
			//finalList.get(0).setList(locPosiCand);
			return finalList;

		}catch(Exception e){
			LOG.error("Exception raised at getCandidateAndPartyInfoForConstituency", e);
			return null;
		}
		
	}

	public List<CandidateInfoForConstituencyVO> extractCandidateNPartyDataFromList(List candidateList,List<Long> candidateIds) {
		List<CandidateInfoForConstituencyVO> candidateInfoList = new ArrayList<CandidateInfoForConstituencyVO>();

		for (int i = 0; i < candidateList.size(); i++) {
			String candidateFullName = "";
			CandidateInfoForConstituencyVO candidateInfo1 = new CandidateInfoForConstituencyVO();
			Object[] values = (Object[]) candidateList.get(i);
			candidateInfo1.setConstituencyId((Long) values[0]);
			candidateInfo1.setConstituencyName(values[1].toString());
			candidateInfo1.setCandidateId((Long) values[2]);
			if(!candidateIds.contains(candidateInfo1.getCandidateId()))
			candidateIds.add(candidateInfo1.getCandidateId());
			List<Object[]> parliaments = parliamentAssemblyDAO.getParliamentByAssemblyId(commonMethodsUtilService.getLongValueForObject(values[0]));
			if(commonMethodsUtilService.isListOrSetValid(parliaments)){
				Object[] obj = parliaments.get(0);
				candidateInfo1.setParliamentId(commonMethodsUtilService.getLongValueForObject(obj[0]));
				candidateInfo1.setParliamnerName(commonMethodsUtilService.getStringValueForObject(obj[1]));
			}

			if (!StringUtils.isBlank((String) values[3]))
				candidateFullName = candidateFullName + ((String) values[3]) + " ";
			if (!StringUtils.isBlank((String) values[4]))
				candidateFullName = candidateFullName + ((String) values[4]) + " ";
			if (!StringUtils.isBlank((String) values[5]))
				candidateFullName = candidateFullName + ((String) values[5]);

			candidateInfo1.setCandidateName(candidateFullName);
			candidateInfo1.setPartyId((Long) values[6]);
			candidateInfo1.setParty(values[7].toString());
			candidateInfo1.setConstituencyType(values[9].toString());
			if (values[8] == null || values[8].toString().length() == 0)
				candidateInfo1.setDeformDate("");
			else
				candidateInfo1.setDeformDate(values[9].toString());
			if (values[10] != null) {
				candidateInfo1.setPartyFlag(values[10].toString());
			}
			candidateInfo1.setLatestElecYear(values[11].toString());
			
			candidateInfo1.setElectionId(commonMethodsUtilService.getLongValueForObject(values[12]));
			candidateInfo1.setElectionYear(commonMethodsUtilService.getLongValueForObject(values[13]));
			
			candidateInfoList.add(candidateInfo1);
		}

		return candidateInfoList;
	}

	public List<LocationVotersVO> getVotersAndcadreAgeWiseCount(Long locationTypeId,Long locationValue, Long publicationDateId) {
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			Long reportLevelId= 0l;
			List<Long> constituencyIds = new ArrayList<Long>();
			List<Long> locationIds = new ArrayList<Long>();
			locationIds.add(locationValue);
			if(locationTypeId == 3l){
		        List<Object[]> locationValuesObj = constituencyDAO.getDistrictConstituenciesList(locationIds);
		        for (Object[] objects : locationValuesObj) {
		          if(objects!=null){
		        	  constituencyIds.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
		          }
		        }
		        reportLevelId= 1l;
		      }else if(locationTypeId == 10l){
		    	  constituencyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(locationIds);
		    	  reportLevelId=1l;
		      }else if(locationTypeId == 4l){
		    	  constituencyIds.add(locationValue);
		    	  reportLevelId=1l;
		      }else if(locationTypeId == 5l){
		    	  reportLevelId=2l;
		    	  constituencyIds.add(locationValue);
		      }else if(locationTypeId == 6l){
		    	  reportLevelId=3l;
		    	  constituencyIds.add(locationValue);
		      }else if(locationTypeId == 7l){
		    	  reportLevelId=5l;
		    	  constituencyIds.add(locationValue);
		      }
		      else if(locationTypeId == 8l){
		    	  reportLevelId=6l;
		    	  constituencyIds.add(locationValue);
		      }
			Map<Long, LocationVotersVO> map = new LinkedHashMap<Long, LocationVotersVO>();
			 List<VoterAgeRange> voterAgeRangeList = voterAgeRangeDAO.getVoterAgeRangeList();
		
			 if(voterAgeRangeList != null && voterAgeRangeList.size() > 0 ){
				for (VoterAgeRange voterAgeRange : voterAgeRangeList) {
					if (voterAgeRange.getVoterAgeRangeId() != 7) {
						LocationVotersVO ageRangeVo = new LocationVotersVO();
						ageRangeVo.setAgeRangeId(voterAgeRange.getVoterAgeRangeId());
						ageRangeVo.setAgeRange(voterAgeRange.getAgeRange());
						map.put(voterAgeRange.getVoterAgeRangeId(), ageRangeVo);
					}
				}
			 }
			List<Object[]> votersObjList = voterAgeInfoDAO.getVotersAgeWiseCount(constituencyIds, publicationDateId,reportLevelId);
			if (votersObjList != null && votersObjList.size() > 0) {
				for (Object[] objects : votersObjList) {
					if (map.get(commonMethodsUtilService.getLongValueForObject(objects[0])) == null) {
						LocationVotersVO inVO = new LocationVotersVO();
						map.put(commonMethodsUtilService.getLongValueForObject(objects[0]), inVO);
					}else{
					LocationVotersVO vo = map.get(commonMethodsUtilService.getLongValueForObject(objects[0]));
					if(vo != null){
						vo.setTotalVoters(objects[1] != null ? (Long) objects[1] : 0l);
						vo.setTotalVotersPerc(objects[2] != null ? objects[2].toString() + " %" : "");
						vo.setMaleVoters(objects[3] != null ? (Long) objects[3] : 0l);
						vo.setMaleVotersPerc(objects[4] != null ? objects[4].toString() + " %" : "");
						vo.setFemaleVoters(objects[5] != null ? (Long) objects[5] : 0l);
						vo.setFemaleVotersPerc(objects[6] != null ? objects[6].toString() + " %" : "");
					}
					}
				}
			}

			List<Object[]> cadreObjList = tdpCadreEnrollmentYearDAO.getGenderAndAgeGroupWiseCadreCount(locationTypeId,locationValue);
			if (cadreObjList != null && cadreObjList.size() > 0) {
				for (Object[] objects : cadreObjList) {
					if (map.get(commonMethodsUtilService.getLongValueForObject(objects[0])) == null) {
						LocationVotersVO inVO = new LocationVotersVO();
						map.put(commonMethodsUtilService.getLongValueForObject(objects[0]), inVO);
					}

					if ((objects[1].toString().trim()).equalsIgnoreCase("M")) {
						map.get(commonMethodsUtilService.getLongValueForObject(objects[0])).setMaleCadres((Long) objects[2]);
					} else if ((objects[1].toString().trim()).equalsIgnoreCase("F")) {
						map.get(commonMethodsUtilService.getLongValueForObject(objects[0])).setFemaleCadres((Long) objects[2]);
					}
				}
			}

			if (map != null && map.size() > 0) {
				LocationVotersVO voForTotalCounts = new LocationVotersVO();
				Long totalCadres = 0l, maleTotalCadres = 0l, femaleTotalCadres = 0l;
				for (Entry<Long, LocationVotersVO> entry : map.entrySet()) {
					entry.getValue()
					.setTotalCadres(entry.getValue().getMaleCadres() + entry.getValue().getFemaleCadres());
					totalCadres = totalCadres + entry.getValue().getMaleCadres() + entry.getValue().getFemaleCadres();
					maleTotalCadres = maleTotalCadres + entry.getValue().getMaleCadres();
					femaleTotalCadres = femaleTotalCadres + entry.getValue().getFemaleCadres();
				}

				for (Entry<Long, LocationVotersVO> entry : map.entrySet()) {
					if (totalCadres > 0l)
						entry.getValue()
						.setTotalCadrePerc(((entry.getValue().getTotalCadres() * 100) / totalCadres) + "%");
					if (maleTotalCadres > 0l)
						entry.getValue()
						.setMaleCadrePerc(((entry.getValue().getMaleCadres() * 100) / maleTotalCadres) + "%");
					if (femaleTotalCadres > 0l)
						entry.getValue().setFemaleCadrePerc(
								((entry.getValue().getFemaleCadres() * 100) / femaleTotalCadres) + "%");

					voForTotalCounts
					.setTotalVoters(voForTotalCounts.getTotalVoters() + entry.getValue().getTotalVoters());
					voForTotalCounts
					.setTotalCadres(voForTotalCounts.getTotalCadres() + entry.getValue().getTotalCadres());
					voForTotalCounts.setMaleVoters(voForTotalCounts.getMaleVoters() + entry.getValue().getMaleVoters());
					voForTotalCounts.setMaleCadres(voForTotalCounts.getMaleCadres() + entry.getValue().getMaleCadres());
					voForTotalCounts
					.setFemaleVoters(voForTotalCounts.getFemaleVoters() + entry.getValue().getFemaleVoters());
					voForTotalCounts
					.setFemaleCadres(voForTotalCounts.getFemaleCadres() + entry.getValue().getFemaleCadres());
				}

				voList.addAll(map.values());
				voList.add(voList.size(), voForTotalCounts);
			}

		} catch (Exception e) {
			LOG.error("Exception raised at votersAndcadreAgeWiseCount", e);
		}
		return voList;
	}

	
	public List<LocationVotersVO> getVotersAndCadreCasteWiseCount(String type, Long locationTypeId,Long locationValue, Long publicationDateId) {
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			if (type.equalsIgnoreCase("voter")) {
				voList = getCasteGroupNAgeWiseVoterNCadreCounts(locationTypeId,locationValue, publicationDateId,null);
			} else if (type.equalsIgnoreCase("cadre")) {
				voList = getCadreCasteWiseCount(locationTypeId,locationValue, publicationDateId);
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getVotersAndCadreCasteWiseCount", e);
		}
		return voList;
	}
	
	
	public List<LocationVotersVO> getCadreCasteWiseCount(Long locationTypeId,Long locationValue,Long publicationDateId) {
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			
			List<Long> constituencyIds = new ArrayList<Long>();
			List<Long> locationIds = new ArrayList<Long>();
			locationIds.add(locationValue);
			if(locationTypeId == 3l){
		        List<Object[]> locationValuesObj = constituencyDAO.getDistrictConstituenciesList(locationIds);
		        for (Object[] objects : locationValuesObj) {
		          if(objects!=null){
		        	  constituencyIds.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
		          }
		        }
		        
		      }else if(locationTypeId == 10l){
		    	  constituencyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(locationIds);
		      }else if(locationTypeId == 4l){
		    	  constituencyIds.add(locationValue);
		      }
			// 0-casteCategoryId,1-casteCategory,2-casteId,3-caste,4-gender,5-cadreCount
			List<Object[]> objList = tdpCadreEnrollmentYearDAO.getCasteNGenderWiseCadreCounts(locationTypeId,locationValue);
			//List<Object[]> objList = tdpCadreEnrollmentYearDAO.getCasteWiseCadreCounts(constituencyIds);

			if (objList != null && objList.size() > 0) {
				for (Object[] objects : objList) {
					LocationVotersVO matchedCGVO = getMatchedVO(voList, (Long) objects[0]);
					if (matchedCGVO == null) {
						matchedCGVO = new LocationVotersVO();
						matchedCGVO.setAgeRangeId((Long) objects[0]);
						matchedCGVO.setAgeRange(objects[1].toString());

						LocationVotersVO casteVO = new LocationVotersVO();
						casteVO.setAgeRangeId((Long) objects[2]);
						casteVO.setAgeRange(objects[3].toString());
						if (objects[4].toString().equalsIgnoreCase("M")) {
							casteVO.setMaleCadres((Long) objects[5]);
						} else if (objects[4].toString().equalsIgnoreCase("F")) {
							casteVO.setFemaleCadres((Long) objects[5]);
						}

						matchedCGVO.getLocationVotersVOList().add(casteVO);

						voList.add(matchedCGVO);
					} else {
						LocationVotersVO matchedCVO = getMatchedVO(matchedCGVO.getLocationVotersVOList(),(Long) objects[2]);
						if (matchedCVO == null) {
							matchedCVO = new LocationVotersVO();
							matchedCVO.setAgeRangeId((Long) objects[2]);
							matchedCVO.setAgeRange(objects[3].toString());
							if (objects[4].toString().equalsIgnoreCase("M")) {
								matchedCVO.setMaleCadres((Long) objects[5]);
							} else if (objects[4].toString().equalsIgnoreCase("F")) {
								matchedCVO.setFemaleCadres((Long) objects[5]);
							}
							matchedCGVO.getLocationVotersVOList().add(matchedCVO);
						} else {
							if (objects[4].toString().equalsIgnoreCase("M")) {
								matchedCVO.setMaleCadres((Long) objects[5]);
							} else if (objects[4].toString().equalsIgnoreCase("F")) {
								matchedCVO.setFemaleCadres((Long) objects[5]);
							}
						}
					}
				}
			}

			// calculating totals and %'s
			if (voList != null && voList.size() > 0) {
				for (LocationVotersVO casteGroupVO : voList) {
					if (casteGroupVO.getLocationVotersVOList() != null && casteGroupVO.getLocationVotersVOList().size() > 0) { 
						Long maleTotalCadres = 0l, femaleTotalCadres = 0l;
						for (LocationVotersVO casteVO : casteGroupVO.getLocationVotersVOList()) {
							maleTotalCadres = maleTotalCadres + casteVO.getMaleCadres();
							femaleTotalCadres = femaleTotalCadres + casteVO.getFemaleCadres();
							casteVO.setTotalCadres(casteVO.getMaleCadres()+casteVO.getFemaleCadres());
						}
						casteGroupVO.setTotalCadres(maleTotalCadres+femaleTotalCadres);
						if(femaleTotalCadres != null && maleTotalCadres != null && femaleTotalCadres > 0  && maleTotalCadres > 0){

							for (LocationVotersVO casteVO : casteGroupVO.getLocationVotersVOList()) {
								casteVO.setMaleCadrePerc(((casteVO.getMaleCadres() * 100) / maleTotalCadres) + "%");
								casteVO.setFemaleCadrePerc(((casteVO.getFemaleCadres() * 100) / femaleTotalCadres) + "%");
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getCadreCasteWiseCount", e);
		}
		return voList;
	}


	public List<LocationVotersVO> getCasteGroupNAgeWiseVoterNCadreCounts(Long locationTypeId, Long locationValue, Long publicationDateId, Long castegroupId) {
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			Long reportLevelId= 0l;
			List<Long> constituencyIds = new ArrayList<Long>();
			List<Long> locationIds = new ArrayList<Long>();
			locationIds.add(locationValue);
			if(locationTypeId == 3l){
		        List<Object[]> locationValuesObj = constituencyDAO.getDistrictConstituenciesList(locationIds);
		        for (Object[] objects : locationValuesObj) {
		          if(objects!=null){
		        	  constituencyIds.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
		          }
		        }
		        reportLevelId= 1l;
		      }else if(locationTypeId == 10l){
		    	  constituencyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(locationIds);
		    	  reportLevelId=1l;
		      }else if(locationTypeId == 4l){
		    	  constituencyIds.add(locationValue);
		    	  reportLevelId=1l;
		      }else if(locationTypeId == 5l){
		    	  reportLevelId=2l;
		    	  constituencyIds.add(locationValue);
		      }else if(locationTypeId == 6l){
		    	  reportLevelId=3l;
		    	  constituencyIds.add(locationValue);
		      }else if(locationTypeId == 7l){
		    	  reportLevelId=5l;
		    	  constituencyIds.add(locationValue);
		      }
		      else if(locationTypeId == 8l){
		    	  reportLevelId=6l;
		    	  constituencyIds.add(locationValue);
		      }
				
			// 0-castegroupId,1-castegroup,2-casteId,3-castegroup,4-voterscount,5-percentage,6-maleVotersCount,7-femaleVotersCount
			List<Object[]> votersObjList = voterCastInfoDAO.getVotersCasteWiseCount(constituencyIds, publicationDateId, null,reportLevelId, castegroupId);

			if (votersObjList != null && votersObjList.size() > 0) {

				for (Object[] objects : votersObjList) {
					LocationVotersVO matchedCGVO = getMatchedVO(voList, (Long) objects[0]);
					if (matchedCGVO == null) {
						matchedCGVO = new LocationVotersVO();
						matchedCGVO.setAgeRangeId((Long) objects[0]);
						matchedCGVO.setAgeRange(objects[1].toString());

						LocationVotersVO casteVO = new LocationVotersVO();
						casteVO.setAgeRangeId((Long) objects[2]);
						casteVO.setAgeRange(objects[3].toString());
						casteVO.setTotalVoters((Long) objects[4]);
						casteVO.setTotalVotersPerc(objects[5].toString());
						casteVO.setMaleVoters((Long) objects[6]);
						casteVO.setFemaleVoters((Long) objects[7]);

						matchedCGVO.getLocationVotersVOList().add(casteVO);

						voList.add(matchedCGVO);
					} else {
						LocationVotersVO matchedCVO = getMatchedVO(matchedCGVO.getLocationVotersVOList(),(Long) objects[2]);
						if (matchedCVO == null) {
							matchedCVO = new LocationVotersVO();
							matchedCVO.setAgeRangeId((Long) objects[2]);
							matchedCVO.setAgeRange(objects[3].toString());
							matchedCVO.setTotalVoters((Long) objects[4]);
							matchedCVO.setTotalVotersPerc(objects[5].toString());
							matchedCVO.setMaleVoters((Long) objects[6]);
							matchedCVO.setFemaleVoters((Long) objects[7]);
							matchedCGVO.getLocationVotersVOList().add(matchedCVO);
						} else {
							matchedCGVO.setMaleVoters((Long) objects[6]);
							matchedCGVO.setFemaleVoters((Long) objects[7]);
						}
					}
				}
			}

			// calculating totals and %'s
			if (voList != null && voList.size() > 0) {
				for (LocationVotersVO casteGroupVO : voList) {
					if (casteGroupVO.getLocationVotersVOList() != null && casteGroupVO.getLocationVotersVOList().size() > 0) { 
						Long maleTotalVoters = 0l, femaleTotalVoters = 0l;
						for (LocationVotersVO casteVO : casteGroupVO.getLocationVotersVOList()) {
							maleTotalVoters = maleTotalVoters + casteVO.getMaleVoters();
							femaleTotalVoters = femaleTotalVoters + casteVO.getFemaleVoters();
						}
						casteGroupVO.setTotalVoters(maleTotalVoters+femaleTotalVoters);
						if(femaleTotalVoters != null && maleTotalVoters != null && femaleTotalVoters > 0  && maleTotalVoters > 0){
							for (LocationVotersVO casteVO : casteGroupVO.getLocationVotersVOList()) {
								casteVO.setMaleVotersPerc(((casteVO.getMaleVoters() * 100) / maleTotalVoters) + "%");
								casteVO.setFemaleVotersPerc(((casteVO.getFemaleVoters() * 100) / femaleTotalVoters) + "%");
							}
						}
					}
				}
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getCasteGroupNAgeWiseVoterNCadreCounts", e);
		}
		return voList;
	}

	public LocationVotersVO getMatchedVO(List<LocationVotersVO> voList, Long id) {
		if (voList != null && voList.size() > 0 && id != null && id > 0l) {
			for (LocationVotersVO locationVotersVO : voList) {
				if (locationVotersVO.getAgeRangeId().equals(id))
					return locationVotersVO;
			}
		}
		return null;
	}

	public List<LocationVotersVO> getCasteNAgeWiseVoterNCadreCounts(Long locationTypeId,Long locationValue, Long publicationDateId,
			Long casteGroupId, Long casteId) {
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			List<Long> constituencyIds = new ArrayList<Long>();
			List<Long> locationIds = new ArrayList<Long>();
			locationIds.add(locationValue);
			if(locationTypeId == 3l){
		        List<Object[]> locationValuesObj = constituencyDAO.getDistrictConstituenciesList(locationIds);
		        for (Object[] objects : locationValuesObj) {
		          if(objects!=null){
		        	  constituencyIds.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
		          }
		        }
		        
		      }else if(locationTypeId == 10l){
		    	  constituencyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(locationIds);
		      }else if(locationTypeId == 4l){
		    	  constituencyIds.add(locationValue);
		      }
			Map<Long, LocationVotersVO> map = new LinkedHashMap<Long, LocationVotersVO>();
			
			List<Object[]> ageRange = voterAgeRangeDAO.getSpecificAgeRangeList();
			// template building
			if (ageRange != null && ageRange.size() > 0) {
				for (Object[] objects : ageRange) {
					if (map.get(commonMethodsUtilService.getLongValueForObject(objects[0])) == null) {
						LocationVotersVO inVO = new LocationVotersVO();
						inVO.setAgeRangeId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						inVO.setAgeRange(commonMethodsUtilService.getStringValueForObject(objects[1]));
						map.put(commonMethodsUtilService.getLongValueForObject(objects[0]), inVO);
					}
				}
			}
			// 0-ageRangeId,1-ageRange,2-gender,3-votersCount
			List<Object[]> votersObjList = userVoterDetailsDAO.getVotersCasteNAgeGroupWiseCount(casteGroupId, casteId,constituencyIds, publicationDateId);

			if (votersObjList != null && votersObjList.size() > 0) {
				for (Object[] objects : votersObjList) {
					if (map.get(commonMethodsUtilService.getLongValueForObject(objects[0])) == null) {
						LocationVotersVO inVO = new LocationVotersVO();
						inVO.setAgeRangeId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						//inVO.setAgeRange(commonMethodsUtilService.getStringValueForObject(objects[1]));
						map.put(commonMethodsUtilService.getLongValueForObject(objects[0]), inVO);
					}

					if (objects[2].toString().equalsIgnoreCase("M")) {
						map.get((Long) objects[0]).setMaleVoters((Long) objects[3]);
					} else if (objects[2].toString().equalsIgnoreCase("F")) {
						map.get((Long) objects[0]).setFemaleVoters((Long) objects[3]);
					}

				}
			}

			List<Object[]> cadresObjList = tdpCadreEnrollmentYearDAO.getCadresCasteNAgeGroupWiseCounts(locationTypeId,locationValue,
					casteGroupId, casteId);
			if (cadresObjList != null && cadresObjList.size() > 0) {
				for (Object[] objects : cadresObjList) {
					if (map.get((Long) objects[0]) == null) {
						LocationVotersVO inVO = new LocationVotersVO();
						inVO.setAgeRangeId((Long) objects[0]);
						inVO.setAgeRange(objects[1].toString());
						map.put((Long) objects[0], inVO);
					}

					if (objects[2].toString().equalsIgnoreCase("M")) {
						map.get((Long) objects[0]).setMaleCadres((Long) objects[3]);
					} else if (objects[2].toString().equalsIgnoreCase("F")) {
						map.get((Long) objects[0]).setFemaleCadres((Long) objects[3]);
					}
				}
			}

			if (map != null && map.size() > 0) {
				Long totalVoters = 0l, totalMaleVoters = 0l, totalFemaleVoters = 0l, totalCadres = 0l,
						totalMaleCadres = 0l, totalFemaleCadres = 0l;
				for (Entry<Long, LocationVotersVO> entry : map.entrySet()) {
					entry.getValue()
					.setTotalVoters(entry.getValue().getMaleVoters() + entry.getValue().getFemaleVoters());
					entry.getValue()
					.setTotalCadres(entry.getValue().getMaleCadres() + entry.getValue().getFemaleCadres());

					totalVoters = totalVoters + entry.getValue().getMaleVoters() + entry.getValue().getFemaleVoters();
					totalMaleVoters = totalMaleVoters + entry.getValue().getMaleVoters();
					totalFemaleVoters = totalFemaleVoters + entry.getValue().getFemaleVoters();
					totalCadres = totalCadres + entry.getValue().getMaleCadres() + entry.getValue().getFemaleCadres();
					totalMaleCadres = totalMaleCadres + entry.getValue().getMaleCadres();
					totalFemaleCadres = totalFemaleCadres + entry.getValue().getFemaleCadres();
				}

				for (Entry<Long, LocationVotersVO> entry : map.entrySet()) {
					entry.getValue().setTotalVotersPerc(round(((entry.getValue().getTotalVoters() * 100.00) / totalVoters),2) + "%");
					entry.getValue().setMaleVotersPerc(round(((entry.getValue().getMaleVoters() * 100.00) / totalMaleVoters),2) + "%");
					entry.getValue().setFemaleVotersPerc(round(((entry.getValue().getFemaleVoters() * 100.00) / totalFemaleVoters),2) + "%");
					entry.getValue().setTotalCadrePerc(round(((entry.getValue().getTotalCadres() * 100.00) / totalCadres),2) + "%");
					entry.getValue().setMaleCadrePerc(round(((entry.getValue().getMaleCadres() * 100.00) / totalMaleCadres),2) + "%");
					entry.getValue().setFemaleCadrePerc(round(((entry.getValue().getFemaleCadres() * 100.00) / totalFemaleCadres),2) + "%");
				}

				voList.addAll(map.values());
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getCasteNAgeWiseVoterNCadreCounts", e);
		}
		return voList;
	}

	public static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	public List<KeyValueVO> getEnrollmentYearWiseCadres() {
		List<KeyValueVO> voList = new LinkedList<KeyValueVO>();
		try {
			List<Object[]> objList = tdpCadreEnrollmentYearDAO.getEnrollmentYearWiseCadres();

			if (objList != null && objList.size() > 0) {
				Map<Long, List<Long>> resultMap = new LinkedHashMap<Long, List<Long>>();
				for (Object[] objects : objList) {
					if (resultMap.get((Long) objects[0]) == null) {
						resultMap.put((Long) objects[0], new ArrayList<Long>(0));
					}
					resultMap.get((Long) objects[0]).add((Long) objects[1]);
				}

				Set<Long> keySet = resultMap.keySet();
				List<Long> ketList = new ArrayList<Long>(0);
				ketList.addAll(keySet);

				for (int i = 0; i < ketList.size(); i++) {
					List<Long> firstList = resultMap.get(ketList.get(i));
					List<Long> secondList = resultMap.get(ketList.get(i + 1));

					KeyValueVO resultVO = new KeyValueVO();
					resultVO.setId(ketList.get(i));
					resultVO.setCount(Long.parseLong(firstList.size() + ""));

					for (Long long1 : firstList) {
						if (secondList.contains(firstList)) {
							resultVO.setTotalCount(resultVO.getTotalCount() + 1l);
						} else {
							resultVO.setScopeValue(resultVO.getScopeValue() + 1l);
						}
					}

					voList.add(resultVO);
				}

				KeyValueVO lastVO = new KeyValueVO();
				lastVO.setId(ketList.get(ketList.size() - 1));
				lastVO.setCount(Long.parseLong(resultMap.get(ketList.get(ketList.size() - 1)).size() + ""));
				voList.add(lastVO);

				List<EnrollmentYear> enrolmentYear = enrollmentYearDAO.getAll();
				Map<Long, String> enrollmentMap = new HashMap<Long, String>();
				for (EnrollmentYear enrollmentYear : enrolmentYear) {
					enrollmentMap.put(enrollmentYear.getEnrollmentYearId(), enrollmentYear.getDescription());
				}

				if (voList != null && voList.size() > 0) {
					for (KeyValueVO kvv : voList) {
						kvv.setName(enrollmentMap.get(kvv.getId()));
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getEnrollmentYearWiseCadres", e);
		}
		return voList;
	}

	public List<LocationVotersVO> getEnrollmentYearAgeGroupWiseCadres(Long locationTypeId,Long locationValue,Long enrollmentYearId) {
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			// 0-voterAgeRangeId,1-ageRange,2-gender,3-casteCategoryId,4-categoryName,5-count
			List<Object[]> objList = tdpCadreEnrollmentYearDAO.getEnrollmentYearAgeGroupWiseCadres(locationTypeId,locationValue,enrollmentYearId);

			Map<Long, LocationVotersVO> ageRangeMap = new LinkedHashMap<Long, LocationVotersVO>();
			if (objList != null && objList.size() > 0) {
				List<CasteCategory> ccList = casteCategoryDAO.getAll();
				for (Object[] objects : objList) {
					if (ageRangeMap.get((Long) objects[0]) == null) {
						LocationVotersVO vo = new LocationVotersVO();
						vo.setAgeRangeId((Long) objects[0]);
						vo.setAgeRange(objects[1].toString());
						if (objects[2].toString().equalsIgnoreCase("M")) {
							vo.setMaleCadres(vo.getMaleCadres() + (Long) objects[5]);
						} else if (objects[2].toString().equalsIgnoreCase("F")) {
							vo.setFemaleCadres(vo.getFemaleCadres() + (Long) objects[5]);
						}

						vo.setLocationVotersVOList(getCasteCategorySkeleton(ccList));

						LocationVotersVO matchedCasteVO = getMatchedVO(vo.getLocationVotersVOList(), (Long) objects[3]);
						if (matchedCasteVO != null) {
							matchedCasteVO.setTotalCadres((Long) objects[5]);
						}

						ageRangeMap.put((Long) objects[0], vo);
					} else {
						LocationVotersVO vo = ageRangeMap.get((Long) objects[0]);
						if (objects[2].toString().equalsIgnoreCase("M")) {
							vo.setMaleCadres(vo.getMaleCadres() + (Long) objects[5]);
						} else if (objects[2].toString().equalsIgnoreCase("F")) {
							vo.setFemaleCadres(vo.getFemaleCadres() + (Long) objects[5]);
						}

						LocationVotersVO matchedCasteVO = getMatchedVO(vo.getLocationVotersVOList(), (Long) objects[3]);
						if (matchedCasteVO != null) {
							matchedCasteVO.setTotalCadres(matchedCasteVO.getTotalCadres() + (Long) objects[5]);
						}
					}

				}
			}

			if (ageRangeMap != null && ageRangeMap.size() > 0) {
				voList.addAll(ageRangeMap.values());
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getEnrollmentYearAgeGroupWiseCadres", e);
		}
		return voList;
	}

	public List<LocationVotersVO> getCasteCategorySkeleton(List<CasteCategory> ccList) {
		List<LocationVotersVO> voList = new ArrayList<LocationVotersVO>(0);
		if (ccList != null && ccList.size() > 0) {
			for (CasteCategory cc : ccList) {
				LocationVotersVO vo = new LocationVotersVO();
				vo.setAgeRangeId(cc.getCasteCategoryId());
				vo.setAgeRange(cc.getCategoryName());
				voList.add(vo);
			}
		}
		return voList;
	}
/**
 * 
 * LocationWise Meetings
 * Inputs locationid and type,
 * 
 */
	public List<LocationVotersVO> getLocationWiseMeetingsCount(Long locationTypeId, List<Long> locationValues,String fromDateStr,String toDateStr) {
		List<LocationVotersVO> voList = new ArrayList<LocationVotersVO>(0);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, LocationVotersVO> finalMap = new LinkedHashMap<String, LocationVotersVO>();

			finalMap.put("Y", null);
			finalMap.put("N", null);
			finalMap.put("M", null);
			finalMap.put("NU", null);
			Date startDate = null;
			Date endDate = null;
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			// 0-meetingStatus,1-levelId,2-level,3-count
			List<Object[]> objList = partyMeetingStatusDAO.getLocationWiseMeetings(locationValues, locationTypeId,startDate,endDate);
			if (objList != null && objList.size() > 0) {
				for (Object[] objects : objList) {
					if (finalMap.get(objects[0].toString()) == null) {
						finalMap.put(objects[0].toString(), getPartyMeetingSkeleton(objects[0].toString(),locationTypeId));
					}

					LocationVotersVO matchedLocationVO = getMatchedLocationVO(
							finalMap.get(objects[0].toString()).getLocationVotersVOList(), objects[2].toString());

					if (matchedLocationVO != null) {
						matchedLocationVO.setTotalVoters((Long) objects[3]);
					} else {
						matchedLocationVO = new LocationVotersVO();
						matchedLocationVO.setAgeRangeId((Long) objects[1]);
						matchedLocationVO.setAgeRange(objects[2].toString());
						matchedLocationVO.setTotalVoters((Long) objects[3]);
						finalMap.get(objects[0].toString()).getLocationVotersVOList().add(matchedLocationVO);
					}
				}

				if (finalMap != null && finalMap.size() > 0) {
					finalMap.values().removeAll(Collections.singleton(null)); //remove null values in map
					voList.addAll(finalMap.values());
				}
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseMeetingsCount", e);
		}
		return voList;
	}

	public LocationVotersVO getMatchedLocationVO(List<LocationVotersVO> voList, String str) {
		if (voList != null && voList.size() > 0) {
			for (LocationVotersVO locationVotersVO : voList) {
				String s[] = locationVotersVO.getAgeRange().split("/");
				for (int i = 0; i < s.length; i++) {
					if (s[i].equalsIgnoreCase(str))
						return locationVotersVO;
				}
			}
		}
		return null;
	}

	public LocationVotersVO getPartyMeetingSkeleton(String type, Long locationTypeId) {
		String type1 = type.equals("Y") ? "YES"
				: type.equals("N") ? "NO" : type.equals("M") ? "MAYBE" : type.equals("NU") ? "NOT UPDATED" : type;

		LocationVotersVO vo = new LocationVotersVO();
		vo.setAgeRange(type1);
		vo.setTotalVotersPerc(type);

		LocationVotersVO vwVO = new LocationVotersVO();
		vwVO.setAgeRangeId(7l);
		vwVO.setAgeRange("Village/Ward");
		vo.getLocationVotersVOList().add(vwVO);

		LocationVotersVO mtdVO = new LocationVotersVO();
		mtdVO.setAgeRangeId(4l);
		mtdVO.setAgeRange("Mandal/Town/Division");
		vo.getLocationVotersVOList().add(mtdVO);

		LocationVotersVO constVO = new LocationVotersVO();
		constVO.setAgeRangeId(3l);
		constVO.setAgeRange("Constituency");
		vo.getLocationVotersVOList().add(constVO);
		
		if(locationTypeId!=null && (locationTypeId == 3l || locationTypeId == 10l )){
		LocationVotersVO distVo = new LocationVotersVO();
		distVo.setAgeRangeId(2l);
		distVo.setAgeRange("District");
		vo.getLocationVotersVOList().add(distVo);
		}
		if(locationTypeId!=null && locationTypeId == 10l){
		LocationVotersVO pconstVO = new LocationVotersVO();
		pconstVO.setAgeRangeId(1l);
		pconstVO.setAgeRange("State");
		vo.getLocationVotersVOList().add(pconstVO);
		}
		return vo;
	}
	/* @param String locationType
	 * @param Long locationId
	 * @param Long enrollmentId
	 * @author R Nagarjuna Gowd
	 * @return CommitteeBasicVO object
	 * (non-Javadoc)
	 * @see com.itgrids.core.api.service.ILocationDashboardService#getLocationWiseCommitteesCount(java.lang.String, java.lang.Long, java.lang.Long)
	 */

	public CommitteeBasicVO getLocationWiseCommitteesCount(String locationType, Long locationId,Long tdpCommitteeEnrollmentYearId) {

		CommitteeBasicVO committeeCounts = new CommitteeBasicVO();
		try {
			// 0-tdp_base_comitteeId,1-levelId,2-levelName,3-committeeConfrimed,4-start
			// Date,5-completed Date
			//List<Object[]> listCmt=tdpCommitteeDAO.getLocationWiseCommitteesCnt( locationType,  locationId, tdpCommitteeEnrollmentYearId);
			//List<TdpCommitteeLevel> committee = tdpCommitteeLevelDAO.getAll();
			List<Object[]> objList = tdpCommitteeDAO.getLocationWiseCommittees(locationType, locationId, tdpCommitteeEnrollmentYearId);
			Long mainMandalCompletedCount = 0l;
			Long mainMandalStartCount = 0l;
			Long mainVillageCompletedCount = 0l;
			Long mainVillageStartCount = 0l;
			Long mainMandalNotStarted = 0l;
			Long mainVillageNotStarted = 0l;

			Long affliatedMandalCompletedCount = 0l;
			Long affliatedMandalStartCount = 0l;
			Long affliatedVillageCompletedCount = 0l;
			Long affliatedVillageStartCount = 0l;
			Long affliatedMandalNotStarted = 0l;
			Long affliatedVillageNotStarted = 0l;

			if (objList != null) {
				for (Object[] objects : objList) {
					if (commonMethodsUtilService.getLongValueForObject(objects[0]) == 1l) {
						if (commonMethodsUtilService.getLongValueForObject(objects[1]) == 5l || commonMethodsUtilService.getLongValueForObject(objects[1]) == 7l ||commonMethodsUtilService.getLongValueForObject(objects[1]) == 9l) {
							if (commonMethodsUtilService.getStringValueForObject(objects[3]).equalsIgnoreCase("Y") && objects[4]!=null && objects[5]!=null) {
								mainMandalCompletedCount++;
							} else if(commonMethodsUtilService.getStringValueForObject(objects[3]).equalsIgnoreCase("N") && objects[4]!=null && objects[5]==null  ) {
								mainMandalStartCount++;
							}else if(objects[3].toString().trim().equalsIgnoreCase("N") && objects[4]==null && objects[5]==null ){
								mainMandalNotStarted++;
							}

						} else if (commonMethodsUtilService.getLongValueForObject(objects[1]) == 6l || commonMethodsUtilService.getLongValueForObject(objects[1]) == 8l) {
							if (commonMethodsUtilService.getStringValueForObject(objects[3]).equalsIgnoreCase("Y") && objects[4]!=null && objects[5]!=null) {
								mainVillageCompletedCount++;
							} else if(commonMethodsUtilService.getStringValueForObject(objects[3]).equalsIgnoreCase("N") && objects[4]!=null && objects[5]==null ) {
								mainVillageStartCount++;
							}else if(commonMethodsUtilService.getStringValueForObject(objects[3]).equalsIgnoreCase("N") && objects[4]==null && objects[5]==null ){
								mainVillageNotStarted++;
							}
						}
					} else {
						if (commonMethodsUtilService.getLongValueForObject(objects[1]) == 5l || commonMethodsUtilService.getLongValueForObject(objects[1]) == 7l || commonMethodsUtilService.getLongValueForObject(objects[1]) == 9l) {
							if (commonMethodsUtilService.getStringValueForObject(objects[3]).equalsIgnoreCase("Y") && objects[4]!=null && objects[5]!=null) {
								affliatedMandalCompletedCount++;
							} else if(commonMethodsUtilService.getStringValueForObject(objects[3]).equalsIgnoreCase("N") && objects[4]!=null && objects[5]==null  ) {
								affliatedMandalStartCount++;
							}else if(commonMethodsUtilService.getStringValueForObject(objects[3]).equalsIgnoreCase("N") && objects[4]==null && objects[5]==null ){
								affliatedMandalNotStarted++;
							}

						} else if (commonMethodsUtilService.getLongValueForObject(objects[1]) == 6l || commonMethodsUtilService.getLongValueForObject(objects[1]) == 8l) {
							if (commonMethodsUtilService.getStringValueForObject(objects[3]).equalsIgnoreCase("Y") && objects[4]!=null && objects[5]!=null) {
								affliatedVillageCompletedCount++;
							} else if(commonMethodsUtilService.getStringValueForObject(objects[3]).equalsIgnoreCase("N") && objects[4]!=null && objects[5]==null  )  {
								affliatedVillageStartCount++;
							}else if(commonMethodsUtilService.getStringValueForObject(objects[3]).equalsIgnoreCase("N") && objects[4]==null && objects[5]==null ){
								affliatedVillageNotStarted++;
							}
						}
					}

				}
				committeeCounts.setMainMandalCompletedCount(mainMandalCompletedCount);//main committee mandal/town/division count
				committeeCounts.setMainMandalStartedCount(mainMandalStartCount);//main committee mandal/town/division count
				committeeCounts.setMainMandalTotal(mainMandalCompletedCount+mainMandalStartCount+mainMandalNotStarted);
				committeeCounts.setMainvillageCompletedCount(mainVillageCompletedCount);//main committee village/ward count
				committeeCounts.setMainVillageStartedCount(mainVillageStartCount);//main committee village/ward count
				committeeCounts.setMainVillageTotal(mainVillageCompletedCount+mainVillageStartCount+mainVillageNotStarted);
				committeeCounts.setMainMandalNotYetStartedCount(mainMandalNotStarted);//main committee not yet started count 
				committeeCounts.setMainVillageNotYetStartedCount(mainVillageNotStarted);//main committee not started count
				committeeCounts.setMainMandalCompletePer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getMainMandalTotal(), mainMandalCompletedCount))));
				committeeCounts.setMainMandalStartPer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getMainMandalTotal(), mainMandalStartCount))));
				committeeCounts.setMainVillageCompletePer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getMainVillageTotal(), mainVillageCompletedCount))));
				committeeCounts.setMainVillageStartPer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getMainVillageTotal(), mainVillageStartCount))));
				committeeCounts.setMainVillageNotPer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getMainVillageTotal(), mainVillageNotStarted))));
				committeeCounts.setMainMandalNotPer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getMainMandalTotal(), mainMandalNotStarted))));

				committeeCounts.setAffliatedMandalCompletedCount(affliatedMandalCompletedCount);
				committeeCounts.setAffliatedMandalStartedCount(affliatedMandalStartCount);
				committeeCounts.setAffiCommMandalNotStarted(affliatedMandalNotStarted);
				committeeCounts.setAffMandalTotal(affliatedMandalCompletedCount+affliatedMandalStartCount+affliatedMandalNotStarted);
				committeeCounts.setAffliatedVillageCompletedCount(affliatedVillageCompletedCount);//affliated committee village/ward count
				committeeCounts.setAffliatedVillageStartedCount(affliatedVillageStartCount);//affliated committee village/ward count
				committeeCounts.setAffiCommVillageNotStarted(affliatedVillageNotStarted);
				committeeCounts.setAffVillageTotal(affliatedVillageCompletedCount+affliatedVillageStartCount+affliatedVillageNotStarted);
				//committeeCounts.setAffiCommMandalNotStarted(affliatedMandalNotStarted);
				//committeeCounts.setAffiCommVillageNotStarted(affliatedVillageNotStarted);
				committeeCounts.setAffMandalCompletePer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getAffMandalTotal(), affliatedMandalCompletedCount))));
				committeeCounts.setAffMandalStartPer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getAffMandalTotal(), affliatedMandalStartCount))));
				committeeCounts.setAffVillageCompletePer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getAffVillageTotal(), affliatedVillageCompletedCount))));
				committeeCounts.setAffVillageStartPer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getAffVillageTotal(), affliatedVillageStartCount))));
				committeeCounts.setAffVillageNotPer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getAffVillageTotal(), affliatedVillageNotStarted))));
				committeeCounts.setAffMandalNotPer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getAffMandalTotal(), affliatedMandalNotStarted))));
				
			}
			
			List<CommitteeBasicVO>  comitteeList=getAllLocationWiseCommitteesCount(locationType,locationId,tdpCommitteeEnrollmentYearId);
			committeeCounts.setSubList(comitteeList);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseCommitteesCount", e);
		}

		return committeeCounts;
	}
	/*
	 * @author R Nagarjuna Gowd
	 * @return BasicVO object contains enrollmentIds and Years
	 * (non-Javadoc)
	 * @see com.itgrids.core.api.service.ILocationDashboardService#getEnrollmentIds()
	 */

	public List<BasicVO> getEnrollmentIds(List<Long> publicationDateIds) {
		List<BasicVO> finalList = new ArrayList<BasicVO>();
		List<Object[]> yearIdsList = tdpCadreEnrollmentYearDAO.getEnrollmentYears(publicationDateIds);
		if(yearIdsList != null && yearIdsList.size() >0){
			for(Object[] param :yearIdsList){
				BasicVO enrollmentList = new BasicVO();
				enrollmentList.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
				enrollmentList.setDescription(commonMethodsUtilService.getStringValueForObject(param[1]));
				finalList.add(enrollmentList);
			}
		}
		return finalList;
	}

	/*
	 * @author R Nagarjuna Gowd
	 * @return BasicVO object contains electionTypes table data
	 * (non-Javadoc)
	 * @see com.itgrids.core.api.service.ILocationDashboardService#getElectionTypes()
	 * Date 02-08-2017
	 */

	public List<BasicVO> getElectionTypes() {
		List<BasicVO> finalList = new ArrayList<BasicVO>();
		List<ElectionType> electionTypes = electionTypeDAO.getAll();
		for (ElectionType electionType : electionTypes) {
			BasicVO electionTypesData = new BasicVO();
			electionTypesData.setId(electionType.getElectionTypeId());
			electionTypesData.setName(electionType.getElectionType());
			electionTypesData.setDescription(electionType.getScope());
			finalList.add(electionTypesData);
		}
		
		List<Object[]> parties = partyDAO.getPartiesList();
		for (Object[] party : parties) {
			BasicVO partyVO = new BasicVO();
			partyVO.setId(commonMethodsUtilService.getLongValueForObject(party[0]));
			partyVO.setName(commonMethodsUtilService.getStringValueForObject(party[1]));
			finalList.get(0).getSelectedCasteDetails().add(partyVO);
		}
		List<String> electionYears = electionDAO.getElectionYears(null);
	
		finalList.get(0).getAgeRanges().addAll(electionYears);
		
		Collections.sort(finalList, new Comparator<BasicVO>() {
		    public int compare(BasicVO one, BasicVO other) {
		        return one.getId().compareTo(other.getId());
		    }
		}); 
		
		return finalList;
	}


	/*
	 * @author R Nagarjuna Gowd
	 * @return BasicVO object contains publication table data
	 * (non-Javadoc)
	 * @see com.itgrids.core.api.service.ILocationDashboardService#getElectionTypes()
	 * Date 02-08-2017
	 */

	public List<BasicVO> getPublications() {
		List<BasicVO> finalList = new ArrayList<BasicVO>();
		List<Object[]> publications = publicationDateDAO.getEnrollmentPublications();
		for (Object[] publication : publications) {
			BasicVO publicationData = new BasicVO();
			publicationData.setId(commonMethodsUtilService.getLongValueForObject(publication[0]));
			publicationData.setName(commonMethodsUtilService.getStringValueForObject(publication[2]));
			publicationData.setDate(commonMethodsUtilService.getStringValueForObject(publication[1].toString()));
			finalList.add(publicationData);
		}
		Collections.sort(finalList, new Comparator<BasicVO>() {
		    public int compare(BasicVO one, BasicVO other) {
		        return other.getId().compareTo(one.getId());
		    }
		}); 
		return finalList;
	}


	/*
	 * @param String fromDateStr
	 * @param String toDateStr
	 * @param Long locationType
	 * @param Long locationValue
	 * @author R Nagarjuna Gowd
	 * @return List<AlertOverviewVO> we have three list in final list 1.village/ward counts 2.mandal/town/division list 3.Constituency counts list
	 * (non-Javadoc)
	 * @see com.itgrids.core.api.service.ILocationDashboardService#getLevelWiseMeetingStatusCounts(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long)
	 */

	public List<MeetingsVO> getLevelWiseMeetingStatusCounts(String fromDateStr, String toDateStr, Long locationTypeId,
			List<Long> locationValues,String year) {
		List<MeetingsVO> meetingStatusCounts = new ArrayList<MeetingsVO>();
		MeetingsVO vwStatus = new MeetingsVO();
		vwStatus.setLocationName("village/ward");
		MeetingsVO mtdStatus = new MeetingsVO();
		mtdStatus.setLocationName("mandal/town/division");
		MeetingsVO cStatus = new MeetingsVO();
		cStatus.setLocationName("constituncy");
		Date frmDate = null;
		Date toDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//Here converting string to date formatte
		if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
			try {
				frmDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// 0-partyMeetingLevelId,1-LevelName,2-meeting Status,3-Meetings Count
		List<Object[]> objList = partyMeetingStatusDAO.getLevelWiseMeetingStatusCount(frmDate, toDate, locationTypeId,
				locationValues,year);
		try {
			if (objList != null) {
				for (Object[] objects : objList) {
					if (commonMethodsUtilService.getLongValueForObject(objects[0]) == 7l || commonMethodsUtilService.getLongValueForObject(objects[0]) == 8l) {

						if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("Y")) {
							vwStatus.setYesCount(vwStatus.getYesCount()+(Long)objects[3]);
						} else if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("N")) {
							vwStatus.setNoCount(vwStatus.getNoCount()+(Long)objects[3]);
						} else if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("NU")) {
							vwStatus.setNotUpDatedCount(vwStatus.getNotUpDatedCount()+(Long)objects[3]);
						} else if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("M")) {
							vwStatus.setMayBeCount(vwStatus.getMayBeCount()+(Long)objects[3]);
						}
					} else if (commonMethodsUtilService.getLongValueForObject(objects[0]) == 4l || commonMethodsUtilService.getLongValueForObject(objects[0]) == 5l || commonMethodsUtilService.getLongValueForObject(objects[0]) == 6l) {
						if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("Y")) {
							mtdStatus.setYesCount(mtdStatus.getYesCount()+(Long)objects[3]);
						} else if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("N")) {
							mtdStatus.setNoCount(mtdStatus.getNoCount()+(Long)objects[3]);
						} else if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("NU")) {
							mtdStatus.setNotUpDatedCount(mtdStatus.getNotUpDatedCount()+(Long)objects[3]);
						} else if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("M")) {
							mtdStatus.setMayBeCount(mtdStatus.getMayBeCount()+(Long)objects[3]);
						}
					} else if (commonMethodsUtilService.getLongValueForObject(objects[0]) == 3l) {
						if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("Y")) {
							cStatus.setYesCount(cStatus.getYesCount()+(Long)objects[3]);
						} else if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("N")) {
							cStatus.setNoCount(cStatus.getNoCount()+(Long)objects[3]);
						} else if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("NU")) {
							cStatus.setNotUpDatedCount(cStatus.getNotUpDatedCount()+(Long)objects[3]);
						} else if (commonMethodsUtilService.getStringValueForObject(objects[2]).equalsIgnoreCase("M")) {
							cStatus.setMayBeCount(cStatus.getMayBeCount()+(Long)objects[3]);
						}
					}
				}
				meetingStatusCounts.add(vwStatus);
				meetingStatusCounts.add(mtdStatus);
				meetingStatusCounts.add(cStatus);

			}
		} catch (Exception e) {
			Log.error("Exception raised in meetings method"+e);
		}
		return meetingStatusCounts;
	} 
	/*
	 * Swadhin K Lenka
	 */

	public List<KeyValueVO> getNominatedPostStatusWiseCount(Long locationTypeId,List<Long> locationValuesList,String fromDateStr, String toDateStr,String year){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date startDate = null;
			Date endDate = null;
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && toDateStr != null && !toDateStr.isEmpty() && toDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			List<Object[]> nominatedPostList = nominatedPostDAO.getNominatedPostStatusWiseCount(locationTypeId, locationValuesList, startDate, endDate,year);
			List<Object[]> nominatedPostStatusList = nominatedPostDAO.getAllNominatedStatusList();
			List<KeyValueVO> keyValueVOs = new CopyOnWriteArrayList<KeyValueVO>();
			KeyValueVO keyValueVO = null;
			for (Object[] statusList : nominatedPostStatusList) {
				keyValueVO = new KeyValueVO();
				keyValueVO.setId(commonMethodsUtilService.getLongValueForObject(statusList[0]));
				keyValueVO.setName(commonMethodsUtilService.getStringValueForObject(statusList[1]).toUpperCase());
				keyValueVO.setCount(0l);
				keyValueVO.setTotalCount(0l);
				keyValueVOs.add(keyValueVO);
			}
			
			Long totalCount= 0l;
			if(nominatedPostList != null && nominatedPostList.size() > 0){
				for(Object[] param : nominatedPostList){
					KeyValueVO vo = getMactchedKeyValueVO(keyValueVOs,commonMethodsUtilService.getLongValueForObject(param[0]));
					if(vo!=null){
					vo.setCount(vo.getCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
					}
					totalCount= totalCount+commonMethodsUtilService.getLongValueForObject(param[2]);
				}
			}
			if(keyValueVOs != null && keyValueVOs.size() > 0){
				keyValueVO = new KeyValueVO();
				keyValueVO.setId(4L);
				keyValueVO.setName("GO ISSUED");
				for(KeyValueVO param : keyValueVOs){
					if(param.getId().longValue() == 4L || param.getId().longValue() == 3L){
						keyValueVO.setCount(keyValueVO.getCount()+param.getCount());
						keyValueVOs.remove(param);
					}
					
				}
				keyValueVOs.add(keyValueVO);
			}
			keyValueVOs.get(0).setTotalCount(totalCount);
			for (KeyValueVO finalVo : keyValueVOs) {
				if(finalVo!=null){
					Long  percentage= (finalVo.getCount()*100)/totalCount;
					finalVo.setPercent(percentage);
				}
			}
			
			return keyValueVOs;
		}catch(Exception e){
			Log.error("Exception raised in getNominatedPostStatusWiseCount method of LocationDashboardService"+e);
		}
		return null;
	}
	
	private KeyValueVO getMactchedKeyValueVO(List<KeyValueVO> keyValueVOs,Long id) {

		try{
			if(keyValueVOs != null && keyValueVOs.size() > 0){
				for(KeyValueVO param : keyValueVOs){
					if(param.getId().longValue() == id){
						return param;
					}
				}
			}
		}catch(Exception e){
			Log.error("Exception raised in getMactchedKeyValueVO method of LocationDashboardService"+e);
		}
		return null;
	
	}
	/*
	 * Swadhin K Lenka
	 */

	public List<KeyValueVO> getNominatedPostApplicationStatusWiseCount(Long locationTypeId,List<Long> locationValuesList,String fromDateStr,
			String toDateStr,String year){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date startDate = null;
			Date endDate = null;
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			List<Object[]> nominatedPostApplicationList = nominatedPostApplicationDAO.getNominatedPostApplicationStatusWiseCount(locationTypeId,locationValuesList,startDate,endDate);
			List<Object[]> applcicationStatusList = applicationStatusDAO.getAllApplicationStatusList();
			List<KeyValueVO> keyValueVOs = new CopyOnWriteArrayList<KeyValueVO>();
			KeyValueVO keyValueVO = null;
			Long totalCount=0l;
			for (Object[] statusList : applcicationStatusList) {
				keyValueVO = new KeyValueVO();
				keyValueVO.setId(commonMethodsUtilService.getLongValueForObject(statusList[0]));
				keyValueVO.setName(commonMethodsUtilService.getStringValueForObject(statusList[1]).toUpperCase());
				keyValueVO.setCount(0l);
				keyValueVO.setTotalCount(0l);
				keyValueVOs.add(keyValueVO);
			}
			if(nominatedPostApplicationList != null && nominatedPostApplicationList.size() > 0){
				for(Object[] param : nominatedPostApplicationList){
						KeyValueVO vo = getMactchedKeyValueVO(keyValueVOs,commonMethodsUtilService.getLongValueForObject(param[0]));
						if(vo!=null){
						vo.setCount(vo.getCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
						}
						totalCount= totalCount+commonMethodsUtilService.getLongValueForObject(param[2]);
					}
			}
			//combine :Rejected,Rejected in Final Review,Rejected in Finalized
			if(keyValueVOs != null && keyValueVOs.size() > 0){
				keyValueVO = new KeyValueVO();
				keyValueVO.setId(2L);
				keyValueVO.setName("REJECTED");
				for(KeyValueVO param : keyValueVOs){
					if(param.getId().longValue() == 2L || param.getId().longValue() == 4L || param.getId().longValue() == 8L || param.getId().longValue() == 9L){
						keyValueVO.setCount(keyValueVO.getCount()+param.getCount());
						keyValueVOs.remove(param);
					}
					
				}
				keyValueVOs.add(keyValueVO);
			}
			//combine :confirmed,go passed
			if(keyValueVOs != null && keyValueVOs.size() > 0){
				keyValueVO = new KeyValueVO();
				keyValueVO.setId(5L);
				keyValueVO.setName("COMPLETED");
				for(KeyValueVO param : keyValueVOs){
					if(param.getId().longValue() == 5L || param.getId().longValue() == 7L){
						keyValueVO.setCount(keyValueVO.getCount()+param.getCount());
						keyValueVOs.remove(param);
					}
					
				}
				keyValueVOs.add(keyValueVO);
			}
			keyValueVOs.get(0).setTotalCount(totalCount);
			for (KeyValueVO finalVo : keyValueVOs) {
				if(finalVo!=null){
					Long  percentage= (finalVo.getCount()*100)/totalCount;
					finalVo.setPercent(percentage);
				}
			}
			ArrayList<KeyValueVO> tempArray = null;
			if(keyValueVOs != null && keyValueVOs.size() > 0){
				tempArray = new ArrayList<KeyValueVO>(keyValueVOs);
				Collections.sort(tempArray, new Comparator<KeyValueVO>(){

					public int compare(KeyValueVO obj1,KeyValueVO obj2) {
						return obj1.getId().compareTo(obj2.getId());
					}
				});
			}
			return tempArray;
		}catch(Exception e){
			Log.error("Exception raised in getNominatedPostApplicationStatusWiseCount method of LocationDashboardService"+e);
		}
		return null;
	}
	/*
	 * Swadhin K Lenka
	 */

	public List<KeyValueVO> getPositionWiseMemberCount(List<Long> locationValues,String fromDateStr, String toDateStr,Long locationTypeId,String year){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date startDate = null;
			Date endDate = null;
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			//build a template
			List<Object[]> list = boardLevelDAO.getBoardLevels();
			List<KeyValueVO> finalList = new CopyOnWriteArrayList<KeyValueVO>();
			buildTemplate(list,finalList);
			List<Object[]> positionList = nominatedPostDAO.getPositionWiseMemberCount(locationValues,startDate,endDate,locationTypeId,year);
			List<KeyValueVO> keyValueVOs = new CopyOnWriteArrayList<KeyValueVO>();
			KeyValueVO keyValueVO = null;
			if(positionList != null && positionList.size() > 0){
				for(Object[] param : positionList){
					keyValueVO = new KeyValueVO();
					keyValueVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					keyValueVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					keyValueVO.setCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					keyValueVOs.add(keyValueVO);
				}
			}
			//combine :Mandal,Muncipality/Corporation
			if(keyValueVOs != null && keyValueVOs.size() > 0){
				keyValueVO = new KeyValueVO();
				keyValueVO.setId(5L);
				keyValueVO.setName("Mandal/Muncipality/Corporation");
				for(KeyValueVO param : keyValueVOs){
					if(param.getId().longValue() == 5L || param.getId().longValue() == 6L){
						keyValueVO.setCount(keyValueVO.getCount()+param.getCount());
						keyValueVOs.remove(param);
					}
				}
				keyValueVOs.add(keyValueVO);
			}

			//combine :Village,Ward
			if(keyValueVOs != null && keyValueVOs.size() > 0){
				keyValueVO = new KeyValueVO();
				keyValueVO.setId(7L);
				keyValueVO.setName("Village/Ward");
				for(KeyValueVO param : keyValueVOs){
					if(param.getId().longValue() == 7L || param.getId().longValue() == 8L){
						keyValueVO.setCount(keyValueVO.getCount()+param.getCount());
						keyValueVOs.remove(param);
					}
				}
				keyValueVOs.add(keyValueVO);
			}
			//push data into template.
			if(finalList != null && finalList.size() > 0){
				for(KeyValueVO param : finalList){
					keyValueVO = getMatchedVO1(keyValueVOs,param.getId());
					if(keyValueVO != null){
						param.setCount(keyValueVO.getCount());
					}
				}
			}
			if(finalList != null && finalList.size() > 0){
				ArrayList<KeyValueVO> tempArray = new ArrayList<KeyValueVO>(finalList);
				Collections.sort(tempArray, new Comparator<KeyValueVO>(){

					public int compare(KeyValueVO obj1,KeyValueVO obj2) {
						return obj1.getId().compareTo(obj2.getId());
					}

				});
			}
			return finalList;
		}catch(Exception e){
			Log.error("Exception raised in getPositionWiseMemberCount method of LocationDashboardService"+e);
		}
		return null;
	}
	public void buildTemplate(List<Object[]> list,List<KeyValueVO> finalList){
		try{
			KeyValueVO keyValueVO= null;
			if(list != null && list.size() > 0){
				for(Object[] param : list){
					keyValueVO = new KeyValueVO();
					keyValueVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					keyValueVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					finalList.add(keyValueVO);
				}
			}
			//combine :Mandal,Muncipality/Corporation
			if(finalList != null && finalList.size() > 0){
				keyValueVO = new KeyValueVO();
				keyValueVO.setId(5L);
				keyValueVO.setName("Mandal/Muncipality/Corporation");
				for(KeyValueVO param : finalList){
					if(param.getId().longValue() == 5L || param.getId().longValue() == 6L){
						finalList.remove(param);
					}
				}
				finalList.add(keyValueVO);
			}

			//combine :Village,Ward
			if(finalList != null && finalList.size() > 0){
				keyValueVO = new KeyValueVO();
				keyValueVO.setId(7L);
				keyValueVO.setName("Panchayat/Ward/Division");
				for(KeyValueVO param : finalList){
					if(param.getId().longValue() == 7L || param.getId().longValue() == 8L){
						finalList.remove(param);
					}
				}
				finalList.add(keyValueVO);
			}
		}catch(Exception e){
			Log.error("Exception raised in buildTemplate method of LocationDashboardService"+e);
		}
	}
	public KeyValueVO getMatchedVO1(List<KeyValueVO> keyValueVOs,Long id){
		try{
			if(keyValueVOs != null && keyValueVOs.size() > 0){
				for(KeyValueVO param : keyValueVOs){
					if(param.getId().longValue() == id){
						return param;
					}
				}
			}
		}catch(Exception e){
			Log.error("Exception raised in getMatchedVO1 method of LocationDashboardService"+e);
		}
		return null;
	}
	/**
	 * @param String fromDateStr
	 * @param String toDateStr
	 * @param String locationType
	 * @param String locationValue
	 * @return List<ToursBasicVO>
	 * @author Santosh 
	 * @Description :This Service Method is used to get tour leader over all complaince details designation wise. 
	 *  @since 7-JULY-2017
	 */
	public List<ToursBasicVO> getLocationWiseTourMembersComplainceDtls(final Long locationTypeId,final List<Long> locationValues,final String fromDateStr,final String toDateStr,final String year,final Long stateId){
		List<ToursBasicVO> resultList = new ArrayList<ToursBasicVO>(0);
		Map<Long,Map<String,List<ToursBasicVO>>> designationWiseTargetMap = new HashMap<Long, Map<String,List<ToursBasicVO>>>(0);
		Map<String,String> categoryIdNameMap = new HashMap<String, String>(0);
		Map<Long,Map<Long,ToursBasicVO>> candiateDtlsMap = new HashMap<Long, Map<Long,ToursBasicVO>>(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fromDate=null;
		Date toDate=null;

		try{
			LOG.info("Entered into getLocationWiseTourMembersComplainceDtls() in LocationDashboardService class");

			if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}

			//Get month year in string format based on fromDate and toDate
			List<String> monthYear = selfAppraisalToursMonthDAO.getMonthAndYear(fromDate, toDate);
			//Get month year ids based on month year 
			List<Long> monthyearIds = selfAppraisalToursMonthDAO.getMonthYearByTourMonths(monthYear);
			List<Object[]> monthObjList = selfAppraisalToursMonthDAO.getMonthDtls(monthYear);

			List<Object[]> rtrnCandiateObjLst = selfAppraisalCandidateLocationNewDAO.getLocationWiseTourMemberDetails(locationTypeId, locationValues,stateId);	
			//getting designation wise target
			ToursBasicVO basicDtlsVO = getRequiredData(rtrnCandiateObjLst);
			if(monthyearIds != null && monthyearIds.size() > 0 && basicDtlsVO.getComplaincandidateIdsSet() != null && basicDtlsVO.getNoNComplaincandidateIdsSet() != null){
				List<Object[]> dsgntnWsTurCtgryTrgtObjLst = selfAppraisalDesignationTargetDAO.getCategoryWiseTargetCnt(monthyearIds, "tourCategory", new ArrayList<Long>(basicDtlsVO.getComplaincandidateIdsSet()));//getComplaincandidateIdsSet() set contain designationIds
				setDesignationWiseTarget(dsgntnWsTurCtgryTrgtObjLst,designationWiseTargetMap,categoryIdNameMap,"tourCategory");
				List<Object[]> dsgntnWsTurTypeTrgtObjLst  = selfAppraisalDesignationTargetDAO.getCategoryWiseTargetCnt(monthyearIds, "tourType", new ArrayList<Long>(basicDtlsVO.getComplaincandidateIdsSet()));//getComplaincandidateIdsSet() set contain designationIds
				setDesignationWiseTarget(dsgntnWsTurTypeTrgtObjLst,designationWiseTargetMap,categoryIdNameMap,"tourType");
			}
			//setting candidate wise target
			setCandidateDtls(rtrnCandiateObjLst,candiateDtlsMap,designationWiseTargetMap,categoryIdNameMap);
			//getting tour submitted details candidate wise
			if(monthyearIds != null && monthyearIds.size() > 0 && basicDtlsVO.getComplaincandidateIdsSet() != null && basicDtlsVO.getNoNComplaincandidateIdsSet() != null){
				List<Object[]> tourSubmittedDtlsObjLst = selfAppraisalCandidateDetailsNewDAO.getCategoryWiseLeaderTourSubmittedCnt("tourCategory", monthyearIds,  new ArrayList<Long>(basicDtlsVO.getComplaincandidateIdsSet()),  new ArrayList<Long>(basicDtlsVO.getNoNComplaincandidateIdsSet()));//getComplaincandidateIdsSet() set contain designationIds and getNoNComplaincandidateIdsSet() contains candidate ids
				setMonthWiseComplainceDetails(tourSubmittedDtlsObjLst,candiateDtlsMap,"tourCategory");
				List<Object[]> rtrnGovtDaysToursObjLst = selfAppraisalCandidateDetailsNewDAO.getCategoryWiseLeaderTourSubmittedCnt("tourType", monthyearIds,  new ArrayList<Long>(basicDtlsVO.getComplaincandidateIdsSet()),  new ArrayList<Long>(basicDtlsVO.getNoNComplaincandidateIdsSet()));//getComplaincandidateIdsSet() set contain designationIds and getNoNComplaincandidateIdsSet() contains candidate ids
				setMonthWiseComplainceDetails(rtrnGovtDaysToursObjLst,candiateDtlsMap,"tourType");
			}
			//getting designation wise leader submitted
			List<Object[]> rtrnSubmittedLdrObjLst = selfAppraisalCandidateDetailsNewDAO.getToursSubmittedLeaderCntDesignationBy(monthyearIds, basicDtlsVO.getNoNComplaincandidateIdsSet());
			Map<Long,Set<Long>> designationWiseTourSubmittedMap =  getDesignationWiseTourSubmittedUniqueMembers(rtrnSubmittedLdrObjLst);
		
            //prepare data for Complaince 
			prepareComplainceDataCandidateWise(candiateDtlsMap);
			//month wise candidate Complaince 
			calculatingCandidateMonthWiseComplancePer(candiateDtlsMap);
			//overall candidate Complaince 
			calculatteOverAllCandiatePercentage(candiateDtlsMap);
			
			  //MEARGING ORGANIZING SECRETARY/SECRETARY
			  if(candiateDtlsMap!=null && candiateDtlsMap.size()>0){
			        Map<Long,ToursBasicVO> orgSecAndSecMap = new ConcurrentHashMap<Long,ToursBasicVO>();
			        Map<Long,ToursBasicVO>  secreteriesMap = null;
			        if(candiateDtlsMap.containsKey(5l)){
			          secreteriesMap = candiateDtlsMap.get(5l);
			          orgSecAndSecMap.putAll(secreteriesMap);
			          //remove SECRETARY from Map
			          candiateDtlsMap.remove(5l); 
			        }
			        if(candiateDtlsMap.containsKey(4l)){
			          orgSecAndSecMap.putAll(candiateDtlsMap.get(4l));
			        }
			        if(orgSecAndSecMap!=null && orgSecAndSecMap.size()>0){
			        	candiateDtlsMap.put(4l, orgSecAndSecMap); 
			        }
			  }

			//Preparing final list
			if(candiateDtlsMap != null && candiateDtlsMap.size() > 0){
				for(Entry<Long,Map<Long,ToursBasicVO>> entry:candiateDtlsMap.entrySet()){
					ToursBasicVO designationVO = new ToursBasicVO();  
					designationVO.setDesignationId(entry.getKey());
					designationVO.setToursMonthId(Long.valueOf(monthyearIds.size()));
					 if (designationWiseTourSubmittedMap != null && designationWiseTourSubmittedMap.size() > 0) {
						 if (designationWiseTourSubmittedMap.get(entry.getKey()) != null){
							 designationVO.setNoOfLeaderCnt(designationVO.getNoOfLeaderCnt()+Long.valueOf(designationWiseTourSubmittedMap.get(entry.getKey()).size()));//this key contain tour submitted leader			 
						 }
							 
					 }
					 
					if(basicDtlsVO != null){
						 if(entry.getKey() == 4l){
							  designationVO.setDesignation("ORGANIZING SECRETARY/SECRETARY");
							  if (designationWiseTourSubmittedMap != null && designationWiseTourSubmittedMap.size() > 0 && designationWiseTourSubmittedMap.containsKey(5l)) {
									 designationVO.setNoOfLeaderCnt(designationVO.getNoOfLeaderCnt()+Long.valueOf(designationWiseTourSubmittedMap.get(5l).size()));//5 - SECRETARY Id 
								 }
						  }else{
							  designationVO.setDesignation(basicDtlsVO.getSubMap().get(entry.getKey()));  
						  }
					}
					if(entry.getValue() != null && entry.getValue().size() > 0){
						for(Entry<Long,ToursBasicVO> candiateEntry:entry.getValue().entrySet()){
							if(candiateEntry.getValue().getComplaincePer()>=100d){
								designationVO.setComplainceCnt(designationVO.getComplainceCnt()+1);//total complaince member designation wise
								candiateEntry.getValue().setIsComplaince("True");
							} else {
								candiateEntry.getValue().setIsComplaince("False");	
							}
							designationVO.getSubList().add(candiateEntry.getValue());
							designationVO.setCount(designationVO.getCount()+1);//total member designation wise
							
							if (candiateEntry.getValue().getMonthList() != null && candiateEntry.getValue().getMonthList().size() > 0) {
								designationVO.setTotalSubmittedToursCnt(designationVO.getTotalSubmittedToursCnt()+candiateEntry.getValue().getMonthList().size());
								for(ToursBasicVO monthVO:candiateEntry.getValue().getMonthList()){
									 if (monthVO.getComplainceDays() > 0d) {
										 designationVO.setSubmitedLeaderCnt(designationVO.getSubmitedLeaderCnt()+1);//this key contain no of month submitted tour
										 monthVO.setIsTourSubmitted("Y");
									 } else {
										 designationVO.setNotSubmitedLeaserCnt(designationVO.getNotSubmitedLeaserCnt()+1);//this key contain no of month not submitted tour
										 monthVO.setIsTourSubmitted("N");
									 }
								}
							}
						}
					}
					resultList.add(designationVO);
				}
			}
			//setting default month for those designation candidate who has added recently,because in UI,it is required
			if (resultList.size() > 0 ) {
				sefDefulatMonthCandiateWise(resultList,monthObjList);	
			}
			
		}catch(Exception e){
			Log.error("Exception Occured at getLocationWiseTourMembersComplainceDtls() in LocationDashboardService class",e);
		}
		return resultList;
	}
	public void setMonthWiseComplainceDetails(List<Object[]> objList, Map<Long,Map<Long,ToursBasicVO>> candiateDtlsMap,String type){
		try{
			if(objList != null && objList.size() > 0){
				for(Object[] param:objList){
					Long designationId = commonMethodsUtilService.getLongValueForObject(param[0]);
					Long candidateId = commonMethodsUtilService.getLongValueForObject(param[2]);
					String idStr = commonMethodsUtilService.getStringValueForObject(param[3]);//categoryId or tourTypeId 
					if(type.equalsIgnoreCase("tourType")){
						idStr = "0"+idStr;/* We are appending 0 before tourTypeId for Identification purpose because in single list
		                      					i am sending tour category and tour type which both has same id */ 
					}
					Long monthId = commonMethodsUtilService.getLongValueForObject(param[4]);
					Long tourDaysCntPerMonth = commonMethodsUtilService.getLongValueForObject(param[5]);

					if(candiateDtlsMap != null && candiateDtlsMap.size() > 0){

						Map<Long,ToursBasicVO> candiateMap = candiateDtlsMap.get(designationId);

						if(candiateMap != null && candiateMap.size() > 0){

							ToursBasicVO candiateVO = candiateMap.get(candidateId); 

							if(candiateVO != null ){

								ToursBasicVO categoryVO = getCategoryMatchVO(candiateVO.getSubList3(),idStr);

								if(categoryVO != null){

									ToursBasicVO monthVO = getMonthMatchVO(categoryVO.getMonthList(),monthId);

									if(monthVO != null){

										if(tourDaysCntPerMonth >= monthVO.getTargetDays()){

											monthVO.setComplainceDays(tourDaysCntPerMonth);
											Double complaincePer = calculatePercantage(monthVO.getComplainceDays(),monthVO.getTargetDays());
											if(complaincePer > 100d){
												monthVO.setComplaincePer(100d);
											}else{
												monthVO.setComplaincePer(complaincePer);	 
											}
										}else{

											monthVO.setComplainceDays(tourDaysCntPerMonth);
											Double complaincePer = calculatePercantage(monthVO.getComplainceDays(),monthVO.getTargetDays());
											monthVO.setComplaincePer(complaincePer);	 
										}
									}
								}	    
							}

						}  
					}
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured in setMonthWiseComplainceDetails() in LocationDashboardService  : ",e);	 
		}
	}
	public void setDesignationWiseTarget(List<Object[]> objLst,Map<Long,Map<String,List<ToursBasicVO>>> designationWiseTargetMap,Map<String,String> categoryIdNameMap,String type){
		try{
			if(objLst != null && objLst.size() > 0){
				for(Object[] param:objLst){
					Map<String,List<ToursBasicVO>> categoryMap = designationWiseTargetMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(categoryMap == null){
						categoryMap = new LinkedHashMap<String, List<ToursBasicVO>>(0);
						designationWiseTargetMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), categoryMap);
					}
					String idStr = commonMethodsUtilService.getStringValueForObject(param[2]);
					if(type.equalsIgnoreCase("tourType")){
						idStr = "0"+idStr;/* We are appending 0 before tourTypeId for Identification purpose because in single list
							                      i am sending tour category and tour type which both has same id */ 
					}
					List<ToursBasicVO> monthList = categoryMap.get(idStr);
					if(monthList == null){
						monthList = new ArrayList<ToursBasicVO>();
						categoryIdNameMap.put(idStr, commonMethodsUtilService.getStringValueForObject(param[3]));
						categoryMap.put(idStr, monthList);
					}
					ToursBasicVO monthVO = new ToursBasicVO();
					monthVO.setId(commonMethodsUtilService.getLongValueForObject(param[4]));//monthId
					String year = commonMethodsUtilService.getStringValueForObject(param[7]);
					monthVO.setName(commonMethodsUtilService.getStringValueForObject(param[5]).substring(0, 3)+"-"+year.substring(year.length()-2));//monthName & Year
					monthVO.setYear(commonMethodsUtilService.getLongValueForObject(param[7]));
					monthVO.setTargetDays(commonMethodsUtilService.getLongValueForObject(param[6]));
					monthList.add(monthVO);
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured in setDesignationWiseTarget() in LocationDashboardService  : ",e);
		}
	}
	public void setCandidateDtls(List<Object[]> rtrnMemberDtls,Map<Long,Map<Long,ToursBasicVO>> candiateDtlsMap, Map<Long,Map<String,List<ToursBasicVO>>> designationWiseTargetMap, Map<String,String> categoryIdNameMap){
		try{
			if(rtrnMemberDtls != null && rtrnMemberDtls.size() > 0){
				for(Object[] param:rtrnMemberDtls){
					Long designationId = commonMethodsUtilService.getLongValueForObject(param[0]);
					Map<Long,ToursBasicVO> candidateMap = candiateDtlsMap.get(designationId);
					if(candidateMap == null ){
						candidateMap = new LinkedHashMap<Long,ToursBasicVO>();
						candiateDtlsMap.put(designationId, candidateMap);
					}
					ToursBasicVO candiateVO = candidateMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));//candiateId
					if(candiateVO == null ){
						candiateVO = new ToursBasicVO();
						candiateVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(param[0]));
						candiateVO.setDesignation(commonMethodsUtilService.getStringValueForObject(param[1]));
						candiateVO.setId(commonMethodsUtilService.getLongValueForObject(param[2]));
						candiateVO.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
						candiateVO.setLocationScopeId(commonMethodsUtilService.getLongValueForObject(param[4]));
						List<ToursBasicVO> categoryList = setRequiredTargetDesignationWise(candiateVO.getDesignationId(),designationWiseTargetMap,categoryIdNameMap);
						if(categoryList != null && categoryList.size() > 0){
							candiateVO.setSubList3(new ArrayList<ToursBasicVO>(categoryList));
						}
						candidateMap.put(candiateVO.getId(), candiateVO);
					}
					candiateVO.getLocationSet().add(commonMethodsUtilService.getLongValueForObject(param[5]));//location value
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured in setCandidateDtls() in LocationDashboardService  : ",e);
		}
	}
	public List<ToursBasicVO> setRequiredTargetDesignationWise(Long designationId,Map<Long,Map<String,List<ToursBasicVO>>> designationWiseTargetMap,Map<String,String> categoryIdNameMap){
		try{
			if(designationWiseTargetMap != null && designationWiseTargetMap.size() > 0){

				List<ToursBasicVO> categoryList = new ArrayList<ToursBasicVO>();

				Map<String,List<ToursBasicVO>> categroyMap = designationWiseTargetMap.get(designationId);

				if(categroyMap != null && categroyMap.size() > 0){

					for(Entry<String, List<ToursBasicVO>> entry:categroyMap.entrySet()){

						ToursBasicVO categoryVO = new ToursBasicVO();
						categoryVO.setIdStr(entry.getKey());
						if(categoryIdNameMap != null && categoryIdNameMap.size() > 0){
							categoryVO.setName(categoryIdNameMap.get(entry.getKey()));	 
						}
						if(entry.getValue() != null && entry.getValue().size() > 0){
							for(ToursBasicVO mntVO:entry.getValue()){
								ToursBasicVO monthVO = new ToursBasicVO(); 
								monthVO.setId(mntVO.getId());
								monthVO.setName(mntVO.getName());
								monthVO.setYear(mntVO.getYear());
								monthVO.setTargetDays(mntVO.getTargetDays());
								categoryVO.getMonthList().add(monthVO);
							}
						}
						categoryList.add(categoryVO);
					}
				}
				return categoryList;
			} 
		}catch(Exception e){
			LOG.error("Exception Occured in setRequiredTargetDesignationWise() in LocationDashboardService  : ",e);	 
		}
		return null;
	}
	public void prepareComplainceDataCandidateWise(Map<Long,Map<Long,ToursBasicVO>> candiateDtlsMap){
		try{
			if(candiateDtlsMap != null && candiateDtlsMap.size() > 0){

				for(Entry<Long,Map<Long,ToursBasicVO>> designationEntry:candiateDtlsMap.entrySet()){

					if(designationEntry.getValue() != null && designationEntry.getValue().size() > 0){

						for(Entry<Long,ToursBasicVO> candiateEntry:designationEntry.getValue().entrySet()){

							ToursBasicVO candiateVO = candiateEntry.getValue();
                             
							if(candiateVO != null){
								
								candiateVO.setSubMap1(new HashMap<Long, ToursBasicVO>(0));
								
								if(candiateVO.getSubList3() != null && candiateVO.getSubList3().size() > 0){

									for(ToursBasicVO categoryVO:candiateVO.getSubList3()){

										List<ToursBasicVO> monthList = categoryVO.getMonthList();

										if(monthList != null && monthList.size() > 0){

											for(ToursBasicVO monthVO:monthList){
                                             
												ToursBasicVO mnthVO = candiateVO.getSubMap1().get(monthVO.getId());
												
												 if (mnthVO == null ){
													 mnthVO = new ToursBasicVO();
													 mnthVO.setId(monthVO.getId());
													 mnthVO.setName(monthVO.getName());
													 mnthVO.setYear(monthVO.getYear());
													 candiateVO.getSubMap1().put(mnthVO.getId(), mnthVO);
												 }
												 ToursBasicVO ctgryVO = (ToursBasicVO)categoryVO.clone();
												 ctgryVO.setTargetDays(monthVO.getTargetDays());
												 ctgryVO.setComplainceDays(monthVO.getComplainceDays());
												 ctgryVO.setComplaincePer(monthVO.getComplaincePer());
												 mnthVO.getSubList3().add(ctgryVO);
											}
										}
										 categoryVO.getMonthList().clear();
									}	 
								}
								candiateVO.getMonthList().addAll(candiateVO.getSubMap1().values());
								candiateVO.getSubMap1().clear();
							}
						}
					}
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured in prepareComplainceDataCandidateWise() in LocationDashboardService  : ",e); 
		}
	}
	
	public void calculatingCandidateMonthWiseComplancePer(Map<Long,Map<Long,ToursBasicVO>> candiateDtlsMap){
		try{
			if(candiateDtlsMap != null && candiateDtlsMap.size() > 0){

				for(Entry<Long,Map<Long,ToursBasicVO>> designationEntry:candiateDtlsMap.entrySet()){

					if(designationEntry.getValue() != null && designationEntry.getValue().size() > 0){

						for(Entry<Long,ToursBasicVO> candiateEntry:designationEntry.getValue().entrySet()){

							ToursBasicVO candiateVO = candiateEntry.getValue();
                            
							if(candiateVO != null){

								if(candiateVO.getMonthList().size() > 0){

									for(ToursBasicVO monthVO:candiateVO.getMonthList()){

										List<ToursBasicVO> categoryList = monthVO.getSubList3();

										if(categoryList != null && categoryList.size() > 0){

											Double totalPer= 0.0d;
											Long targetDays =0l;
											Long complainceDays =0l;

											for(ToursBasicVO categoryVO:categoryList){

												totalPer = totalPer+categoryVO.getComplaincePer();
												targetDays = targetDays + categoryVO.getTargetDays();
												complainceDays = complainceDays + categoryVO.getComplainceDays();
											}

											Integer totalCount =0;

											if(categoryList != null && categoryList.size() > 0){

												totalCount = categoryList.size() * 100;   
											}

											Double percentage = calculatePercantageBasedOnDouble(totalPer,totalCount.doubleValue());
											if(percentage > 100){
												monthVO.setComplaincePer(100d);
											}else{
												monthVO.setComplaincePer(percentage);   
											}
											monthVO.setTargetDays(targetDays);
											monthVO.setComplainceDays(complainceDays);
										}
									}	 
								}
							}
						}
					}
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured in calculateCategoryWiseComplaince() in LocationDashboardService  : ",e); 
		}
	}
	public void calculatteOverAllCandiatePercentage(Map<Long,Map<Long,ToursBasicVO>> candiateDtlsMap){
		try{
			if(candiateDtlsMap != null && candiateDtlsMap.size() > 0){

				for(Entry<Long,Map<Long,ToursBasicVO>> designationEntry:candiateDtlsMap.entrySet()){

					if(designationEntry.getValue() != null && designationEntry.getValue().size() > 0){

						for(Entry<Long,ToursBasicVO> candiateEntry:designationEntry.getValue().entrySet()){

							ToursBasicVO candiateVO = candiateEntry.getValue();
                            
							if(candiateVO != null){
								
								if(candiateVO.getMonthList() != null && candiateVO.getMonthList().size() > 0){

										Double totalPer =0.0d;
										Long complainceDays=0l;
										Long targetDays = 0l;
										for(ToursBasicVO VO:candiateVO.getMonthList()){
											totalPer = totalPer+VO.getComplaincePer(); 
											complainceDays = complainceDays +VO.getComplainceDays();
											targetDays = targetDays + VO.getTargetDays();
										}
										Integer totalCount =0;
										
										totalCount = candiateVO.getMonthList().size() * 100;   
										
										Double percentage = calculatePercantageBasedOnDouble(totalPer,totalCount.doubleValue());
										candiateEntry.getValue().setComplainceDays(complainceDays);
										candiateEntry.getValue().setTargetDays(targetDays);
										if(percentage > 100d){
											candiateEntry.getValue().setComplaincePer(100d);
										}else{
											candiateEntry.getValue().setComplaincePer(percentage);  
										}
									} 
							  }
							
						}
					}
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured in calculateCategoryWiseComplaince() in LocationDashboardService  : ",e); 
		}
	}
	public Map<Long,Set<Long>> getDesignationWiseTourSubmittedUniqueMembers(List<Object[]> objList){
		  Map<Long,Set<Long>> designationWiseTourSubmittedMap = new HashMap<Long, Set<Long>>(0);
		  try{
			  if(objList != null && objList.size() > 0){
				  for(Object[] param:objList){
					  Set<Long> candiateIdsSet = designationWiseTourSubmittedMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					   if(candiateIdsSet == null){
						   candiateIdsSet = new HashSet<Long>();
						   designationWiseTourSubmittedMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), candiateIdsSet);  
					   }
					   candiateIdsSet.add(commonMethodsUtilService.getLongValueForObject(param[2]));
				  }
			  }
		  }catch(Exception e){
			  LOG.error("Exception Occured in getDesignationWiseTourSubmittedUniqueMembers() in LocationDashboardService  : ",e); 
		  }
		  return designationWiseTourSubmittedMap;
	  }
	public ToursBasicVO getCategoryMatchVO(List<ToursBasicVO> categoryList,String id){
		try{
			if(categoryList == null || categoryList.size() == 0)
				return null;
			for(ToursBasicVO vo:categoryList){
				if(vo.getIdStr().equalsIgnoreCase(id.trim())){
					return vo;
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured in getCategoryMatchVO() in LocationDashboardService  : ",e);	 
		}
		return null;
	}
	public ToursBasicVO getMonthMatchVO(List<ToursBasicVO> monthList,Long id){
		try{
			if(monthList == null || monthList.size() == 0)
				return null;
			for(ToursBasicVO vo:monthList){
				if(vo.getId().equals(id)){
					return vo;
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured in getMonthMatchVO() in LocationDashboardService  : ",e);	 
		}
		return null;
	}

	public void sefDefulatMonthCandiateWise(List<ToursBasicVO> resultList,List<Object[]> monthObjList) {
		try {
			for (ToursBasicVO deignationVO : resultList) {
				
				if (deignationVO.getSubList() != null && deignationVO.getSubList().size() > 0) {
					
					for (ToursBasicVO candiateVO : deignationVO.getSubList()) {
						candiateVO.getSubList3().clear();
						if (monthObjList != null && monthObjList.size() > 0) {
							
							for (Object[] param : monthObjList) {
								
								ToursBasicVO monthVO = getMonthMatchVO(candiateVO.getMonthList(),commonMethodsUtilService.getLongValueForObject(param[0]));
								
								if (monthVO == null) {
									monthVO = new ToursBasicVO();
									monthVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));//monthId
									String year = commonMethodsUtilService.getStringValueForObject(param[2]);
									monthVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]).substring(0, 3)+"-"+year.substring(year.length()-2));//monthName & Year
									monthVO.setYear(commonMethodsUtilService.getLongValueForObject(param[2]));
									monthVO.setIsTourSubmitted("-");
									monthVO.setIsComplaince("No Target");
									candiateVO.getMonthList().add(monthVO);
								} else {
									monthVO.setIsComplaince("DataSumitted");
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in sefDefulatMonthCandiateWise() in LocationDashboardService  : ",e);
		}
	}
	
	public Double calculatePercantage(Long subCount,Long totalCount){
		Double d=0.0d;
		//if(subCount.longValue()>0l && totalCount.longValue()==0l)
		//	LOG.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount);

		if(totalCount.longValue() > 0l){
			d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	 
		}
		return d;
	}
	public Double calculatePercantageBasedOnDouble(Double subCount,Double totalCount){
		Double d=0.0d;
		if(subCount.longValue()>0l && totalCount.longValue()==0l)
			LOG.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount);

		if(totalCount.longValue() > 0l){
			d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	 
		}
		return d;
	}	
	public ToursBasicVO getRequiredData(List<Object[]> objList){
		ToursBasicVO vo = new ToursBasicVO();
		try{
			if(objList != null && objList.size() > 0){
				vo.setComplaincandidateIdsSet(new java.util.HashSet<Long>(0));//adding designationIds
				vo.setNoNComplaincandidateIdsSet(new java.util.HashSet<Long>(0));//adding candidateIds
				vo.setSubMap(new HashMap<Long, String>(0));
				for (Object[] param : objList) {
					vo.getSubMap().put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
					vo.getComplaincandidateIdsSet().add(commonMethodsUtilService.getLongValueForObject(param[0]));
					vo.getNoNComplaincandidateIdsSet().add(commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}
		}catch(Exception e){
			Log.error("Exception Occured at getRequiredData() in LocationDashboardService class",e);
		}
		return vo;
	}
	/**
	 * @param String locationType
	 * @param String locationValue
	 * @return List<BenefitCandidateVO>
	 * @author Santosh 
	 * @Description :This Service Method is used for getting government scheme wise benefit member count 
	 *  @since 7-JULY-2017
	 */
	public List<BenefitCandidateVO> getGovtSchemeWiseBenefitMembersCount(final Long locationTypeId, final Long locationValue,final Long publicationDateId) {
		List<BenefitCandidateVO> resultList = new ArrayList<BenefitCandidateVO>(0);
		try {
			//List<Object[]> benefitMemberObjLst = govtSchemeBeneficiaryDetailsDAO.getGovtSchemeWiseBenefitMemberCount(locationTypeId,locationValue);
			List<Long> constituencyIds = new ArrayList<Long>();
			if(locationTypeId == 10l){
		    	  constituencyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency1(locationValue);
			}
			List<Object[]> benefitMemberObjLst = govtSchemeBenefitsInfoDAO.getGovtSchemeWiseBenefitMemberCount(locationTypeId,locationValue,constituencyIds);
			List<Object[]> censusPopList = getCensusPopulation(benefitMemberObjLst,locationValue,locationTypeId,"constituency",publicationDateId);
			resultList = getGovtSchemeBenefitMemberDlstList(benefitMemberObjLst,censusPopList,"constituency");
		} catch (Exception e) {
			Log.error("Exception Occured at getGovtSchemeWiseBenefitMembersCount() in LocationDashboardService class",e);
		}
		return resultList;
	}

	private List<Object[]> getCensusPopulation(List<Object[]> benefitMemberObjLst, Long locationValue, Long locationTypeId, String type,Long publicationDateId) {

		Set<Long> locationIdSet = new HashSet<Long>();
		List<Object[]> censusPopList = new ArrayList<Object[]>();
		List<Long> yearList = new ArrayList<Long>();
		if(type.equalsIgnoreCase("constituency")){
			if(locationTypeId == 2l){
				censusPopList.clear();
				yearList.add(2011l);
				censusPopList=censusDAO.getDistrictPopulationForDifferentYears(locationValue, yearList);
				//censusPopList = boothDAO.getTotalVotersForLocations(locationIdSet,locationTypeId,publicationDateId);
			}else if(locationTypeId == 3l){
				censusPopList.clear();
				yearList.add(2011l);
				censusPopList=censusDAO.getDistrictPopulationForDifferentYears(locationValue, yearList);
				//censusPopList = boothDAO.getTotalVotersForLocations(locationIdSet,locationTypeId,publicationDateId);
			}else if(locationTypeId == 10l){
				censusPopList.clear();
				yearList.add(locationValue);
				List<Long> list = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(yearList);
				locationIdSet.addAll(list);
				List<Object[]> list1= constituencyCensusDetailsDAO.getTotalCensusPopulation(locationIdSet, 2011l);
				//List<Object[]> list1 = boothDAO.getTotalVotersForLocations(locationIdSet,locationTypeId,publicationDateId);
				Long count=0l;
				for (Object[] objects : list1) {
					count=commonMethodsUtilService.getLongValueForObject(objects[0])+count;
				}
				Object[] obj = new Object[] {count,locationValue};
				censusPopList.add(obj);
			}
			if(locationTypeId == 4l){	
				censusPopList.clear();
				locationIdSet.add(locationValue);
				censusPopList= constituencyCensusDetailsDAO.getTotalCensusPopulation(locationIdSet, 2011l);
				//censusPopList = boothDAO.getTotalVotersForLocations(locationIdSet,locationTypeId,publicationDateId);
			}else if(locationTypeId == 5l){
					locationIdSet.add(locationValue);
					//List<Object[]> list1 = boothDAO.getTotalVotersForLocations(locationIdSet,locationTypeId,publicationDateId);
				List<Object[]> list1= censusDAO.getTotalCensusPopulation(locationIdSet, 2011l);
				Long count =0l;
				for (Object[] objects : list1) {
					count=commonMethodsUtilService.getLongValueForObject(objects[0])+count;
				}
				Object[] obj = new Object[] {count,locationValue};
				censusPopList.add(obj);
			}
		}else{
			if (benefitMemberObjLst != null && benefitMemberObjLst.size() > 0) {
				censusPopList.clear();
				for (Object[] param : benefitMemberObjLst) {
					locationIdSet.add(commonMethodsUtilService.getLongValueForObject(param[0]));
				}
			}
			
			censusPopList = boothDAO.getTotalVotersForLocations(locationIdSet,locationTypeId,publicationDateId);
			//censusPopList = voterInfoDAO.getTotalVotersForLocations(locationIdSet,locationTypeId,publicationDateId,locationValue);
			//censusPopList= censusDAO.getTotalCensusPopulation(locationIdSet, 2011l,locationTypeId);
		}
		
		return censusPopList;

	}
	/**
	 * @param String locationType
	 * @param String locationValue
	 * @return List<BenefitCandidateVO>
	 * @author Santosh 
	 * @Description :This Service Method is used for getting mandal wise benefit member count 
	 *  @since 7-JULY-2017
	 */
	public List<BenefitCandidateVO> getMandalWiseBenefitMembersCount(final Long locationTypeId, final Long locationValue,final Long govtSchemeId,final Long publicationDateId) {
		List<BenefitCandidateVO> resultList = new ArrayList<BenefitCandidateVO>(0);
		try {
			List<Long> constituencyIds = new ArrayList<Long>();
			if(locationTypeId != null && locationTypeId == 10l){
		    	  constituencyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency1(locationValue);
			}
			//List<Object[]> benefitMemberObjLst = govtSchemeBeneficiaryDetailsDAO.getMandalWiseBenefitMemberCountByGovtScheme(locationTypeId,locationValue, govtSchemeId);
			List<Object[]> benefitMemberObjLst = govtSchemeBenefitsInfoDAO.getMandalWiseBenefitMemberCountByGovtScheme(locationTypeId,locationValue, govtSchemeId,constituencyIds);
			List<Object[]> censusPopList = getCensusPopulation(benefitMemberObjLst,locationValue,locationTypeId,"tehsil",publicationDateId);
			resultList = getGovtSchemeBenefitMemberDlstList(benefitMemberObjLst,censusPopList,"tehsil");
		} catch (Exception e) {
			Log.error("Exception Occured at getMandalWiseBenefitMembersCount() in LocationDashboardService class",e);
		}
		return resultList;
	}

	public List<BenefitCandidateVO> getGovtSchemeBenefitMemberDlstList(List<Object[]> objList, List<Object[]> censusPopList, String locationType) {
		List<BenefitCandidateVO> returnList = new ArrayList<BenefitCandidateVO>(0);
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					BenefitCandidateVO vo = new BenefitCandidateVO();
					
							vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
							vo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
							vo.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[2]));
							returnList.add(vo);
						if(commonMethodsUtilService.isListOrSetValid(censusPopList)){
					for(Object[] censusParam : censusPopList){
						if(locationType!= null && locationType.equalsIgnoreCase("constituency")){
							vo.setTotalPopulation(commonMethodsUtilService.getLongValueForObject(censusParam[0]));
							//vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
							//vo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
							//vo.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[2]));
							//returnList.add(vo);
						}if(locationType.equalsIgnoreCase("tehsil")){
							Long l2 = commonMethodsUtilService.getLongValueForObject(censusParam[1]);
							if(commonMethodsUtilService.getLongValueForObject(param[0]).compareTo(l2)==0){
								vo.setTotalPopulation(commonMethodsUtilService.getLongValueForObject(censusParam[0]));
								//vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
								//vo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
								//vo.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[2]));
								//returnList.add(vo);
							}
						}
					}
					}

				}

			}
			BenefitCandidateVO	benefitsVo  =null;
			if(returnList != null && returnList.size()>0){
			  if(locationType!= null && locationType.equalsIgnoreCase("constituency")){
				benefitsVo =new BenefitCandidateVO();
				for(BenefitCandidateVO vo: returnList){
					benefitsVo.setId(0l);
					benefitsVo.setName("All");
					benefitsVo.setTotalCount(benefitsVo.getTotalCount()+vo.getTotalCount());
					}
				}
			if(benefitsVo != null)
				returnList.add(0, benefitsVo);
			}
		} catch (Exception e) {
			Log.error("Exception Occured at getGovtSchemeBenefitMemberDlstList() in LocationDashboardService class",e);
		}
		return returnList;
	}
	/*
	 * @param String fromDateStr
	 * @param String toDateStr
	 * @param Long locationId
	 * @param Long locationValue
	 * @author R Nagarjuna Gowd
	 * @return InsuranceStatusCountsVO object with status counts
	 * (non-Javadoc)
	 * @see com.itgrids.core.api.service.ILocationDashboardService#getLocationWiseInsuranceStatusCounts(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long)
	 */

	public GrivenceStatusVO getLocationWiseInsuranceStatusCounts(String fromDateStr, String toDateStr, Long locationTypeId,List<Long> locationValues,String year) {
		GrivenceStatusVO grivenceStatusCount = new GrivenceStatusVO();
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			// Here converting stirng to date formatte 
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			//0-locationValue(DIstrict or ConstituencyId),1-locationName,2-Status,3-StatusId,4-Count
			List<Object[]> insuranceStatus = insuranceStatusDAO.getConstituencyWiseInsuranceStatusCounts(fromDate, toDate,locationTypeId,locationValues,year,Long.valueOf(year));
			List<Object[]> statusList = insuranceStatusDAO.grievanceInsuranceStatusId();
			Map<Long,GrivenceStatusVO> InsuranceStatusMap = new LinkedHashMap<Long, GrivenceStatusVO>(0);
			if(insuranceStatus!=null){
				//Here set the values for object 
				for (Object[] objects : insuranceStatus) {
					if(!commonMethodsUtilService.isMapValid(InsuranceStatusMap) && commonMethodsUtilService.isListOrSetValid(statusList)){
						for (Object[] status : statusList) {
							GrivenceStatusVO vo = new GrivenceStatusVO();
							vo.setName(commonMethodsUtilService.getStringValueForObject(status[1]));
							vo.setCount(0L);
							InsuranceStatusMap.put(commonMethodsUtilService.getLongValueForObject(status[0]), vo);
						}
					}
					
					GrivenceStatusVO vo = InsuranceStatusMap.get(commonMethodsUtilService.getLongValueForObject(objects[3]));
					if(vo != null){
						vo.setCount(vo.getCount()+commonMethodsUtilService.getLongValueForObject(objects[4]));
					}
				
				}
				if(commonMethodsUtilService.isMapValid(InsuranceStatusMap)){
					grivenceStatusCount.setGrivenceType("Insurance");
					for (Long status : InsuranceStatusMap.keySet()) {
						GrivenceStatusVO list= InsuranceStatusMap.get(status);
						grivenceStatusCount.getSubList().add(list);
					}
				}
				
			}
		}catch(Exception e){
			Log.error("Exception raised at insurance status counts service"+e);
		}
		return grivenceStatusCount;
	}
	/* @param String fromDateStr
	 * @param String toDateStr
	 * @param Long locationId
	 * @param Long locationValue
	 * @author R Nagarjuna Gowd
	 * @return List<List<GrivenceStatusVO>> we have two lists in final list 1.Grivence counts(Govt,party,welfare) 2.Trust counts
	 * (non-Javadoc)
	 * @see com.itgrids.core.api.service.ILocationDashboardService#getGrivenceTrustStatusCounts(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long)
	 */

	public List<GrivenceStatusVO> getGrivenceTrustStatusCounts(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year) {
		List<GrivenceStatusVO> finalList = new ArrayList<GrivenceStatusVO>();
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			// Here converting stirng to date formatte
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			//0-consId,1-Status,2-typeOfIssue,3-count
			List<Object[]> grivenceTrustList = insuranceStatusDAO.getGrivenceTrustStatusCounts(fromDate, toDate,locationTypeId,locationValues,year,Long.valueOf(year));
			List<String> statusList = insuranceStatusDAO.getGrivenceStatus();
			if(grivenceTrustList!=null){
				//  Here set the Grivence values for object
				
				Map<String,GrivenceStatusVO> grievanceStatusMap = new LinkedHashMap<String, GrivenceStatusVO>(0);
				Map<String,GrivenceStatusVO> trustStatusMap = new LinkedHashMap<String, GrivenceStatusVO>(0);
				for (Object[] objects : grivenceTrustList) {
					if(objects[2].toString().trim().equalsIgnoreCase("Govt") || objects[2].toString().trim().equalsIgnoreCase("Party")  || 
							objects[2].toString().trim().equalsIgnoreCase("Welfare")){
						
						if(!commonMethodsUtilService.isMapValid(grievanceStatusMap) && commonMethodsUtilService.isListOrSetValid(statusList)){
							for (String status : statusList) {
								GrivenceStatusVO vo = new GrivenceStatusVO();
								vo.setName(status.toUpperCase());
								vo.setCount(0L);
								grievanceStatusMap.put(status.toUpperCase(), vo);
							}
						}
						GrivenceStatusVO vo = grievanceStatusMap.get(commonMethodsUtilService.getStringValueForObject(objects[1]).toUpperCase());
						if(vo != null){
							vo.setCount(vo.getCount()+commonMethodsUtilService.getLongValueForObject(objects[3]));
						}
					}
					//  Here set the Trust values for object
					else if(objects[2].toString().trim().equalsIgnoreCase("Trust Education Support"))
					{
						if(!commonMethodsUtilService.isMapValid(trustStatusMap) && commonMethodsUtilService.isListOrSetValid(statusList)){
							for (String status : statusList) {
								GrivenceStatusVO vo = new GrivenceStatusVO();
								vo.setName(status.toUpperCase());
								vo.setCount(0L);
								trustStatusMap.put(status.toUpperCase(), vo);
							}
						}
						GrivenceStatusVO vo = trustStatusMap.get(commonMethodsUtilService.getStringValueForObject(objects[1]).toUpperCase());
						if(vo != null){
							vo.setCount(vo.getCount()+commonMethodsUtilService.getLongValueForObject(objects[3]));
						}
					}
				}
				
				/*Map<String,GrivenceStatusVO> grievanceStatusMap = new LinkedHashMap<String, GrivenceStatusVO>(0);
				Map<String,GrivenceStatusVO> trustStatusMap = new LinkedHashMap<String, GrivenceStatusVO>(0);*/
				
				if(commonMethodsUtilService.isMapValid(grievanceStatusMap)){
					GrivenceStatusVO grivenceStatusCount = new GrivenceStatusVO();
					grivenceStatusCount.setGrivenceType("Grivence");
					for (String status : grievanceStatusMap.keySet()) {
						grivenceStatusCount.getSubList().add(grievanceStatusMap.get(status));
					}
					finalList.add(grivenceStatusCount);
				}
				if(commonMethodsUtilService.isMapValid(trustStatusMap)){
					GrivenceStatusVO trustStatusCount = new GrivenceStatusVO();
					trustStatusCount.setGrivenceType("NTR Trust");
					for (String status : trustStatusMap.keySet()) {
						trustStatusCount.getSubList().add(trustStatusMap.get(status));
					}
					finalList.add(trustStatusCount);
				}
				
			}
		}catch(Exception e){
			Log.error("Exception raised at grivence and trust counts service"+e);
		}
		return finalList;
	}
	/**
	 * @param  String locationType
	 * @param  Long locationValue
	 * @return List<ConstituencyCadreVO>
	 * @author Santosh 
	 * @Description :This Service Method is used to location type cadre count details 
	 *  @since 27-JUNE-2017
	 */
	public List<ConstituencyCadreVO> getLocationTypeWiseCadreCount(Long locationTypeId,List<Long> locationValues,String year){
		List<ConstituencyCadreVO> resultList = new ArrayList<ConstituencyCadreVO>(0);
		try{
			/*if(locationTypeId == 3l){
				List<Object[]> locationValuesObj = constituencyDAO.getDistrictConstituenciesList(locationValues);
				locationValues.clear();
				for (Object[] objects : locationValuesObj) {
					if(objects!=null){
						locationValues.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
					}
				}
				
			}else if(locationTypeId == 10l){
				locationValues = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(locationValues);
			}*/
			
			List<Object[]> rtrnCaderCountObjLst = tdpCadreEnrollmentInfoDAO.getLocationTypeWiseCadreCount(locationValues,year,locationTypeId);
			if(rtrnCaderCountObjLst != null && rtrnCaderCountObjLst.size() > 0){
				for (Object[] param : rtrnCaderCountObjLst) {
					ConstituencyCadreVO enrollmentYearVO = new ConstituencyCadreVO();
					enrollmentYearVO.setEnrollmentYearId(commonMethodsUtilService.getLongValueForObject(param[0]));
					enrollmentYearVO.setEnrollmentYear(commonMethodsUtilService.getStringValueForObject(param[1]));
					enrollmentYearVO.setToalCadreCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					enrollmentYearVO.setNewCaderCount(commonMethodsUtilService.getLongValueForObject(param[3]));
					enrollmentYearVO.setRenewalCadreCount(commonMethodsUtilService.getLongValueForObject(param[4]));
					resultList.add(enrollmentYearVO);
				}
			}
		}catch(Exception e){
			LOG.error("Exception raised at getLocationTypeWiseCadreCount in LocationDashboardService class", e);
		}
		return resultList;
	}
	/**
	 * @param  String locationType
	 * @param  Long locationValue
	 * @param Long enrollmentYearId
	 * @return List<ConstituencyCadreVO>
	 * @author Santosh 
	 * @Description :This Service Method is for getting ageRange,gender,caste group by cadre count. 
	 *  @since 5-JULY-2017
	 */
	public List<ConstituencyCadreVO> getAgeRangeGenerAndCasteGroupByCadreCount(final Long locationTypeId, final Long locationValue,final Long enrollmentYearId) {
		List<ConstituencyCadreVO> resultList = new ArrayList<ConstituencyCadreVO>(0);
		try {
			Map<Long, ConstituencyCadreVO> ageRangeMap = new LinkedHashMap<Long, ConstituencyCadreVO>(0);
			List<Long> constituencyIds= new ArrayList<Long>();
			List<Long> locationIds= new ArrayList<Long>();
			locationIds.add(locationValue);
			
			if(locationTypeId == 10l){
		    	  constituencyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(locationIds);
		    	  
			}else{
				constituencyIds.addAll(locationIds);
			}
			
			//0-ageRangeId, 1- gender 2-castecatId, 3-copunt
			List<Object[]> rtrnCaderObjLst = tdpCadreCasteInfoDAO.getAgeGenerAndCasteGroupWiseCadresCount(locationTypeId,constituencyIds, enrollmentYearId);
			List<Object[]> ageRange = voterAgeRangeDAO.getSpecificAgeRangeList();
			
			//template Building
			if (ageRange != null && ageRange.size() > 0) {
				for (Object[] objects : ageRange) {
					List<Object[]> casteCategoryObjLst = casteCategoryDAO.getAllCasteCategoryDetails();
					if (ageRangeMap.get(commonMethodsUtilService.getLongValueForObject(objects[0])) == null) {
						ConstituencyCadreVO inVO = new ConstituencyCadreVO();
						inVO.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						inVO.setName(commonMethodsUtilService.getStringValueForObject(objects[1])+ " "+"Years");
						inVO.setCasteGroupList(getCasteCategoryList(casteCategoryObjLst));
						ageRangeMap.put(commonMethodsUtilService.getLongValueForObject(objects[0]), inVO);
					}
				}
			}
			
			if (rtrnCaderObjLst != null && rtrnCaderObjLst.size() > 0) {
				for (Object[] param : rtrnCaderObjLst) {
					Long totalCadreCount = commonMethodsUtilService.getLongValueForObject(param[3]);
					Long ageRangeId = commonMethodsUtilService.getLongValueForObject(param[0]);
					if(commonMethodsUtilService.getLongValueForObject(param[0]) == 1)
						ageRangeId = 2l;
					
					ConstituencyCadreVO ageRangeVO = ageRangeMap.get(ageRangeId);
					if (ageRangeVO != null) {
						
						ageRangeVO.setToalCadreCount(ageRangeVO.getToalCadreCount() + totalCadreCount);
						if (commonMethodsUtilService.getStringValueForObject(param[1]).equalsIgnoreCase("M")) {
							ageRangeVO.setMaleCount(ageRangeVO.getMaleCount()+ totalCadreCount);
						} else if (commonMethodsUtilService.getStringValueForObject(param[1]).equalsIgnoreCase("F")) {
							ageRangeVO.setFemaleCount(ageRangeVO.getFemaleCount() + totalCadreCount);
						}
						ConstituencyCadreVO casteCategoryVO = getCasteCategoryMatchVO(ageRangeVO.getCasteGroupList(), commonMethodsUtilService.getLongValueForObject(param[2]));
						if (casteCategoryVO != null) {
							casteCategoryVO.setToalCadreCount(casteCategoryVO.getToalCadreCount()+totalCadreCount);
						}
					}
				}
			}
			// Calculating Percentage
			if (ageRangeMap.size() > 0) {
				for (Entry<Long, ConstituencyCadreVO> entry : ageRangeMap.entrySet()) {
					entry.getValue().setMalePercentage(calculatePercantage(entry.getValue().getMaleCount(), entry.getValue().getToalCadreCount()));
					entry.getValue().setFemalePercentage(calculatePercantage(entry.getValue().getFemaleCount(), entry.getValue().getToalCadreCount()));
					if (entry.getValue() != null && entry.getValue().getCasteGroupList() != null) { 
						for (ConstituencyCadreVO casteCategoryVO : entry.getValue().getCasteGroupList()) {
							casteCategoryVO.setPercentage(calculatePercantage(casteCategoryVO.getToalCadreCount(), entry.getValue().getToalCadreCount()));
						}
					}
				}
			}
			resultList.addAll(ageRangeMap.values());
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationTypeWiseCadreCount in getAgeRangeGenerAndCasteGroupByCadreCount class",e);
		}
		return resultList;
	}
	public ConstituencyCadreVO getCasteCategoryMatchVO(List<ConstituencyCadreVO> categoryList,Long id){
		try{
			if(categoryList == null || categoryList.size() == 0)
				return null;
			for(ConstituencyCadreVO vo:categoryList){
				if(vo.getId().equals(id)){
					return vo;
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured in getCasteCategoryMatchVO() in LocationDashboardService  : ",e);	 
		}
		return null;
	}
	public List<ConstituencyCadreVO> getCasteCategoryList(List<Object[]> objList) {
		List<ConstituencyCadreVO> casteCategoryList = new ArrayList<ConstituencyCadreVO>(0);
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					ConstituencyCadreVO casteCategoryVO = new ConstituencyCadreVO();
					casteCategoryVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					casteCategoryVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					casteCategoryList.add(casteCategoryVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getCasteCategoryList in getCasteCategoryList class",e);
		}
		return casteCategoryList;
	}
	@Override
	public List<BasicVO> getLocationWiseActivitysStatus(String fromDateStr, String toDateStr, String year,List<Long> locationValues, Long locationTypeId) {
		List<BasicVO> returnList = new ArrayList<BasicVO>();
		try{
			List<BasicVO> finalList = new ArrayList<BasicVO>();
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			//0-activity_scope_id 1-activity_name 2-level 3-total
			List<Object[]> activityTotal=activityDAO.getActivitysTotal(fromDate, toDate, year, locationValues, locationTypeId);
			//0-activity_scope_id 1-activity_name 2-level 3-conducted_count
			List<Object[]> activityConductedCount=activityDAO.getActivitysConductedCount(fromDate, toDate, year, locationValues, locationTypeId);
			//0-activity_scope_id 1-activity_name 2-level 3-total
			List<Object[]> conductedInfoTotal = activityDAO.getConductedInfoTotal(fromDate, toDate, year, locationValues, locationTypeId);
			//0-activity_scope_id 1-activity_name 2-level 3-conductedCount
			List<Object[]> conductedCount = activityDAO.getConductedInfoCount(fromDate, toDate, year, locationValues, locationTypeId);
			
			for (Object[] object1 : activityTotal) {
				BasicVO vo = new BasicVO();
				vo.setName(object1[1].toString());
				vo.setDescription(object1[2].toString());
				vo.setTotalVoters((Long)object1[3]);
				vo.setPerc(Double.valueOf("0.00"));
				
				for (Object[] object2 : activityConductedCount) {
					if(((Long)object2[0]).equals((Long)object1[0])){
						vo.setPerc(calculatePercantage((Long)object2[3], (Long)object1[3]));
						vo.setTotalResult((Long)object2[3]);
					}
				}
				
				finalList.add(vo);
			}
			
			for (Object[] object3 : conductedInfoTotal) {
				BasicVO vo = new BasicVO();
				vo.setName(object3[1].toString());
				vo.setDescription(object3[2].toString());
				vo.setTotalVoters((Long)object3[3]);
				vo.setTotalResult((Long)object3[3]);
				vo.setPerc(0.0d);
				for (Object[] object4 : conductedCount) {
					if(((Long)object3[0]).equals((Long)object4[0])){
						vo.setPerc(calculatePercantage((Long)object4[3], (Long)object3[3]));
					}
				}
				finalList.add(vo);
			}

			Map<String,List<BasicVO>> actMap = new HashMap<String,List<BasicVO>>();
			if(commonMethodsUtilService.isListOrSetValid(finalList)){
				for (BasicVO vo : finalList) {
					List<BasicVO> voList = new ArrayList<BasicVO>(0);
					if(actMap.get(vo.getDescription().trim()) != null){
						voList = actMap.get(vo.getDescription().trim());
					}
					voList.add(vo);
					actMap.put(vo.getDescription().trim(),voList);
				}
			}
			
			
			if(commonMethodsUtilService.isMapValid(actMap)){
				for (String level : actMap.keySet()) {
					BasicVO returnVo = new BasicVO();
					returnVo.setDescription(level.trim());
					returnVo.getLocationsList().addAll(actMap.get(level.trim()));
					returnList.add(returnVo);
				}
				
				Map<String,BasicVO> totalCountMap = new HashMap<String, BasicVO>(0);
				if(commonMethodsUtilService.isListOrSetValid(returnList)){
					for (BasicVO vo : returnList) {
						 BasicVO countVO = totalCountMap.get(vo.getDescription().trim());
						if( countVO == null)
							countVO = new BasicVO();
						if(commonMethodsUtilService.isListOrSetValid(vo.getLocationsList())){
							for (BasicVO activityVO : vo.getLocationsList()){
								countVO.setName(vo.getDescription().trim());
								countVO.setTotalResult(countVO.getTotalResult()+activityVO.getTotalResult());
								countVO.setTotalVoters(countVO.getTotalVoters()+activityVO.getTotalVoters());
							}
						}
						totalCountMap.put(vo.getDescription().trim(),countVO);
					}
					returnList.get(0).getConstituencyList().addAll(totalCountMap.values());				}
			}
			
		}catch(Exception e){
			LOG.error("Exception raised at getActivityStatusList() in LocationDashBoardService class",e);
		}
		return returnList;
	}

	/**
	 * @param  Long stateId
	 * @return List<LocationWiseBoothDetailsVO>
	 * @author sanjeev 
	 * @Description :This Service Method is used to get All Distrits in a State
	 *  @since 02-AUG-2017
	 */
	
	public List<LocationWiseBoothDetailsVO> getAllDistricts(Long stateId){
		List<LocationWiseBoothDetailsVO> idNameVOList = new ArrayList<LocationWiseBoothDetailsVO>();
		try {
			List<Long> newDistrictArr = new ArrayList<Long>();
			Long[] ids = IConstants.AP_NEW_DISTRICTS_IDS;
			for (Long param : ids) {
				newDistrictArr.add(param);
			}
			List<Object[]> districtList = districtDAO.getAllNewDistrictDetailsForAState(1l, newDistrictArr);
			if (districtList != null && districtList.size() > 0) {
				for (Object[] objects : districtList) {
					LocationWiseBoothDetailsVO idNameVO = new LocationWiseBoothDetailsVO();
					idNameVO.setLocationId((Long) objects[0]);
					idNameVO.setLocationName(WordUtils.capitalizeFully(objects[1].toString()));
					idNameVO.setName("District");
					idNameVOList.add(idNameVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getAllDistricts", e);
		}
		return idNameVOList;
	}

	/**
	 * @param  Long districtId
	 * @return List<LocationWiseBoothDetailsVO>
	 * @author sanjeev 
	 * @Description :This Service Method is used to get All Constituencies in a District
	 *  @since 02-AUG-2017
	 */
	
	public List<LocationWiseBoothDetailsVO> getAllConstituenciesByDistrict(Long districtId) {
		List<LocationWiseBoothDetailsVO> idNameVOList = new ArrayList<LocationWiseBoothDetailsVO>();
		try {
			if (districtId.compareTo(IConstants.VISHAKAPATNAM_IDS.get(1)) != 0) {
				List<Object[]> constituencyList = constituencyDAO.getAllConstituenciesInADistrict(districtId);
				if (constituencyList != null && constituencyList.size() > 0) {
					for (Object[] objects : constituencyList) {
						LocationWiseBoothDetailsVO idNameVO = new LocationWiseBoothDetailsVO();
						idNameVO.setLocationId((Long) objects[0]);
						idNameVO.setLocationName(WordUtils.capitalizeFully(objects[1].toString()));
						idNameVO.setName("Constituency");
						idNameVOList.add(idNameVO);
					}
				}
			}
			if (IConstants.VISHAKAPATNAM_IDS.contains(districtId)) {
				List<Object[]> constituencyDistrictList = districtConstituenciesDAO.getAllConstituenciesInADistrict(IConstants.VISHAKAPATNAM_IDS.get(1));
				if (districtId.compareTo(IConstants.VISHAKAPATNAM_IDS.get(0)) == 0) {
					for (Object[] param : constituencyDistrictList) {
						for (int i = 0; i <= idNameVOList.size() - 1; i++) {
							if (idNameVOList.get(i).getLocationId().compareTo(commonMethodsUtilService.getLongValueForObject(param[0])) == 0) {
								idNameVOList.remove(i);
							}
						}

					}
				} else if (districtId.compareTo(IConstants.VISHAKAPATNAM_IDS.get(1)) == 0) {
					if (constituencyDistrictList != null && constituencyDistrictList.size() > 0) {
						for (Object[] objects : constituencyDistrictList) {
							LocationWiseBoothDetailsVO idNameVO = new LocationWiseBoothDetailsVO();
							idNameVO.setLocationId((Long) objects[0]);
							idNameVO.setLocationName(WordUtils.capitalizeFully(objects[1].toString()));
							idNameVO.setName("Constituency");
							idNameVOList.add(idNameVO);
						}
					}
				}

			}
		} catch (Exception e) {
			LOG.error("Exception raised in getAllConstituencysForADistrict", e);
		}
		return idNameVOList;
	}

	/**
	 * @param  String fromDate,toDate
	 * @param loctionType,locationValue
	 * @param electionScopeIds
	 * @return List<ElectionInformationVO>
	 * @author Sanjeev 
	 * @Description :This Service Method is used to get All party results based on location
	 *  @since 02-AUG-2017
	 */
	
	public List<ElectionInformationVO> getElectionInformationLocationWise(String fromDateStr, String toDateStr, Long locationTypeId,Long locationValue, List<Long> electionScopeIds,List<Long> partyIds,List<Long> electionYrs,List<String> subTypes) {
		List<ElectionInformationVO> electionInformationVOList= new ArrayList<ElectionInformationVO>();
		try{
			List<Long> yearsList=  new ArrayList<Long>();
			List<Long> tehsilIds = new ArrayList<Long>();
			List<Long> electionBodyIds =new ArrayList<Long>();
			List<Long> locationValues = new ArrayList<Long>();
			List<Long> constituencyIds = new ArrayList<Long>();
			locationValues.add(locationValue);
			if(locationTypeId ==4l){
				constituencyIds.add(locationValue);
			}else if(locationTypeId == 3l){
				List<Object[]> locationValuesObj = constituencyDAO.getDistrictConstituenciesList(locationValues);
				for (Object[] objects : locationValuesObj) {
					if (objects != null) {
						constituencyIds.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
					}
				}

			}else if(locationTypeId == 10l){
				constituencyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(locationValues);
			}
			for (Long constituencyId : constituencyIds) {
				List<LocationWiseBoothDetailsVO> mandalsAndElecBdy= cadreCommitteeService.getMandalsByConstituency(constituencyId);
				for (LocationWiseBoothDetailsVO locationWiseBoothDetailsVO : mandalsAndElecBdy) {
					if(locationWiseBoothDetailsVO != null){
						if(locationWiseBoothDetailsVO.getLocationId().compareTo(0l)!=0){
							char c= locationWiseBoothDetailsVO.getLocationId().toString().charAt(0);
							if(c=='2'){
								Long tehsilId=Long.valueOf(locationWiseBoothDetailsVO.getLocationId().toString().substring(1));
								tehsilIds.add(tehsilId);
							}else if(c=='1'){
								electionBodyIds.add(Long.valueOf(locationWiseBoothDetailsVO.getLocationId().toString().substring(1)));
							}
						}
	
					}
				}
			}
			if(fromDateStr != null && !fromDateStr.trim().isEmpty() && toDateStr != null && !toDateStr.trim().isEmpty()){
				Long fromYear = Long.parseLong(fromDateStr.split("-")[2]);
				Long toYear = Long.parseLong(toDateStr.split("-")[2]);

				for (Long i = fromYear; i <= toYear; i++) {
					yearsList.add(i);
				}
			}
			
			Map<Long,Map<String,ElectionInformationVO>> partyMap = new HashMap<Long,Map<String,ElectionInformationVO>>();
			List<Object[]> resultArray= candidateDAO.getElectionInformationLocationWise(electionYrs, locationTypeId, locationValue, electionScopeIds, electionBodyIds, tehsilIds,null,subTypes);
			if(commonMethodsUtilService.isListOrSetValid(resultArray)){
				
			for (Object[] param : resultArray) {
				if(param!=null){
					Map<String,ElectionInformationVO> yearMap = null;
					if(partyIds != null && partyIds.contains(commonMethodsUtilService.getLongValueForObject(param[0]))){
						 yearMap = partyMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
						if(yearMap == null){
							yearMap = new HashMap<String,ElectionInformationVO>();
							ElectionInformationVO electionInformationVO = new ElectionInformationVO();
							electionInformationVO.setPartyId(commonMethodsUtilService.getLongValueForObject(param[0]));
							electionInformationVO.setPartyName(commonMethodsUtilService.getStringValueForObject(param[1]));
							electionInformationVOList.add(electionInformationVO);
							partyMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),buildDistinctParties(resultArray,yearMap));
						}
					}else{
						 yearMap = partyMap.get(commonMethodsUtilService.getLongValueForObject(0l));
						if(yearMap == null){
							yearMap = new HashMap<String,ElectionInformationVO>();
							ElectionInformationVO electionInformationVO = new ElectionInformationVO();
							electionInformationVO.setPartyId(0l);
							electionInformationVO.setPartyName("OTHERS");
							electionInformationVOList.add(electionInformationVO);
							partyMap.put(0l,buildDistinctParties(resultArray,yearMap));
						}
					}
					
					
					
					ElectionInformationVO yearVO = yearMap.get(commonMethodsUtilService.getStringValueForObject(param[3])+"-"+commonMethodsUtilService.getStringValueForObject(param[5]));
					if(yearVO != null){
						yearVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[6]));
						yearVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[7]));
						yearVO.setTotalVoters(commonMethodsUtilService.getLongValueForObject(param[8]));
						yearVO.setValidVoters(commonMethodsUtilService.getLongValueForObject(param[9]));
						yearVO.setMissedVotes(commonMethodsUtilService.getLongValueForObject(param[10]));
						yearVO.setRejectedVotes(commonMethodsUtilService.getLongValueForObject(param[11]));
						yearVO.setEarnedVotes(commonMethodsUtilService.getLongValueForObject(param[12]));
						yearVO.setEarnedVotesPerc(commonMethodsUtilService.getLongValueForObject(param[13]));
						yearVO.setMarginVotes(commonMethodsUtilService.getLongValueForObject(param[14]));
						yearVO.setWonSeatsCount(commonMethodsUtilService.getLongValueForObject(param[15]));
						
					}
				}
			}
			}

			
			if(commonMethodsUtilService.isListOrSetValid(electionInformationVOList) && commonMethodsUtilService.isMapValid(partyMap)){
				for (ElectionInformationVO partyVO : electionInformationVOList) {
					Map<String,ElectionInformationVO> yearMap = partyMap.get(partyVO.getPartyId());
					if(commonMethodsUtilService.isMapValid(yearMap)){
						partyVO.getList().addAll(yearMap.values());
					}
				}
			for(ElectionInformationVO yearVO : electionInformationVOList){
				Collections.sort(yearVO.getList(), new Comparator<ElectionInformationVO>(){
					public int compare(ElectionInformationVO obj1,ElectionInformationVO obj2) {
						return obj1.getElectionYear().compareTo(obj2.getElectionYear());
					}

				});
			  }
			}
			
			/*ArrayList<KeyValueVO> tempArray = null;
			if(keyValueVOs != null && keyValueVOs.size() > 0){
				tempArray = new ArrayList<KeyValueVO>(keyValueVOs);
				Collections.sort(tempArray, new Comparator<KeyValueVO>(){

					public int compare(KeyValueVO obj1,KeyValueVO obj2) {
						return obj1.getId().compareTo(obj2.getId());
					}
				});
			}
			return tempArray;*/
		}catch(Exception e){
			LOG.error("Exception raised in getElectionInformationLocationWise", e);

		}

		return electionInformationVOList;
	}
	
	public ElectionInformationVO getMatchedVOForElectionYear(List<ElectionInformationVO> voList, Long id,Long id1) {
		if (voList != null && voList.size() > 0 && id != null && id > 0l) {
			for (ElectionInformationVO locationVotersVO : voList) {
				if (locationVotersVO.getElectionId().equals(id) && locationVotersVO.getElectionTypeId().equals(id1))
					return locationVotersVO;
			}
		}
		return null;
	}
	 public Map<String,ElectionInformationVO> buildDistinctParties(List<Object[]> objectlist, Map<String,ElectionInformationVO> returnMap){
		
		 try{
			if(commonMethodsUtilService.isListOrSetValid(objectlist)){
				for (Object[] param : objectlist) {
					ElectionInformationVO vo =  returnMap.get(commonMethodsUtilService.getStringValueForObject(param[3])+"-"+commonMethodsUtilService.getStringValueForObject(param[5]));
					if(vo == null){
						vo = new ElectionInformationVO();
						vo.setElectionId(commonMethodsUtilService.getLongValueForObject(param[2]));
						vo.setElectionYear(commonMethodsUtilService.getStringValueForObject(param[3]));
						vo.setElectionTypeId(commonMethodsUtilService.getLongValueForObject(param[4]));
						vo.setElectionType(commonMethodsUtilService.getStringValueForObject(param[5]));
						
						vo.setWonSeatsCount(0l);
						returnMap.put(commonMethodsUtilService.getStringValueForObject(param[3])+"-"+commonMethodsUtilService.getStringValueForObject(param[5]), vo);
					}
				}
			}
		 }catch (Exception e) {
			 e.printStackTrace();
			 LOG.error("Exception raised in buildDistinctParties", e);
		}
		 return returnMap;
		 
	 }

	 public Map<String,ElectionInformationVO> buildDistinctElectionYears(List<Object[]> objectlist, Map<String,ElectionInformationVO> returnMap){
			
		 try{
			if(commonMethodsUtilService.isListOrSetValid(objectlist)){
				for (Object[] param : objectlist) {
					ElectionInformationVO vo =  returnMap.get(commonMethodsUtilService.getLongValueForObject(param[3])+'-'+commonMethodsUtilService.getStringValueForObject(param[5]));
					if(vo == null){
						vo = new ElectionInformationVO();
						vo.setElectionId(commonMethodsUtilService.getLongValueForObject(param[2]));
						vo.setElectionYear(commonMethodsUtilService.getStringValueForObject(param[3]));
						vo.setElectionTypeId(commonMethodsUtilService.getLongValueForObject(param[4]));
						vo.setElectionType(commonMethodsUtilService.getStringValueForObject(param[5]));
						vo.setWonSeatsCount(0l);
						returnMap.put(vo.getElectionYear()+'-'+vo.getElectionType(), vo);
					}
				}
			}
		 }catch (Exception e) {
			 e.printStackTrace();
			 LOG.error("Exception raised in buildDistinctElectionYears", e);
		}
		 return returnMap;
	 }

	/**
	 *
	 * @param  String fromDate,toDate
	 * @param loctionType,locationValue
	 * @param electionScopeIds
	 * @return BoothInchargesVO
	 * @author Sanjeev 
	 * @Description :This Service Method is used to get All party results based on location
	 * @since 06-AUG-2017
	 * 
	 */
	public BoothInchargesVO getBoothAssignInchargeCount(String fromDateStr, String toDateStr, Long locationTypeId,Long locationValue,
			List<Long> committeeEnrollmentYearsIdsLst){
		BoothInchargesVO inchargeVo = new BoothInchargesVO();
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			Long startedCount= boothInchargeCommitteeDAO.getElectionBoothDetails(fromDate, toDate,locationTypeId, locationValue, "started",
					committeeEnrollmentYearsIdsLst);
			Long completedCount= boothInchargeCommitteeDAO.getElectionBoothDetails(fromDate, toDate,locationTypeId, locationValue, "completed",
					committeeEnrollmentYearsIdsLst);
			Long totalCount= boothInchargeCommitteeDAO.getElectionBoothDetails(fromDate, toDate,locationTypeId, locationValue, null,
					committeeEnrollmentYearsIdsLst);
			inchargeVo.setTotalCount(totalCount);
			inchargeVo.setStartedCount(startedCount);
			inchargeVo.setCompletedCount(completedCount);
			inchargeVo.setNotStartedCount(totalCount-(completedCount+startedCount));
			
		}catch(Exception e){
			LOG.error("Exception raised in getBoothAssignInchargeCount", e);

		}
		return inchargeVo;
	}
	/**
	 *
	 * @param  String fromDate,toDate
	 * @param loctionType,locationValue
	 * @param boothCommitteeEnrollmentYearsIdsLst
	 * @return List<BoothInchargesVO>
	 * @author Sanjeev 
	 * @Description :This Service Method is used to get All party results based on location
	 * @since 07-AUG-2017
	 * 
	 */
	@Override
	public List<BoothInchargesVO> getBoothCommitteeInchargesCount(Long locationId,Long locationValue,List<Long> boothCommitteeEnrollmentYearsIdsLst,String fromDateStr,String toDateStr){
		List<BoothInchargesVO> returnList = new ArrayList<BoothInchargesVO>(0);
		try{
			Date startDate = null;
			Date endDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
		    List<Object[]> boothInchargs = boothInchargeCommitteeDAO.getBoothInchargeCountDetails(locationId, locationValue, boothCommitteeEnrollmentYearsIdsLst, startDate, endDate);
		    setCountForBoothCommitteeIncharges(returnList,"defaultData",0l,null);
		    Map<Long,Long> boothMap = new HashMap<Long,Long>();
		    if(boothInchargs != null && boothInchargs.size() >0){
		    	for(Object[] obj :boothInchargs){
		    		Long cnt =boothMap.get(obj[0]);
		    		
		    		if(cnt != null && cnt.longValue() >0l){
		    			boothMap.put((Long)obj[0], cnt+1l);
		    		}else{
		    			boothMap.put((Long)obj[0], 1l);
		    		}
		    		
		    	}
		    }
		    
		    if(boothMap != null && boothMap.size() >0){
		    	for(Object[] obj :boothInchargs){
		    		Long cnt =boothMap.get(obj[0]);
		    		if(cnt != null && cnt.longValue() >0l){
		    			//boothMap.put((Long)obj[0], cnt+1l);
		    			setCountForBoothCommitteeIncharges(returnList,"matchedVO",cnt,obj);
		    		}else{
		    			//boothMap.put((Long)obj[0], 1l);
		    			setCountForBoothCommitteeIncharges(returnList,"matchedVO",1l,obj);
		    		}
		    	}
		    }
		    
		}catch(Exception e){
			e.printStackTrace();
			Log.error("Exception raised at getBoothCommitteeInchargesCount", e);
		}
		
		return returnList;
	}

	public void setCountForBoothCommitteeIncharges(List<BoothInchargesVO> returnList, String type, Long cnt,
			Object[] obj) {
		try {

			if (type != null && type.equalsIgnoreCase("defaultData")) {
				List<String> boothIncharges = new ArrayList<String>(0);
				boothIncharges.add("1");
				boothIncharges.add("2");
				boothIncharges.add("3");
				boothIncharges.add("4");
				boothIncharges.add("5-10");
				boothIncharges.add("10-Above");
				for (String str : boothIncharges) {
					BoothInchargesVO vo = new BoothInchargesVO();
					vo.setBoothInchargesAssnd(str);
					returnList.add(vo);
				}
			} else {
				BoothInchargesVO matchedVO = null;
				if (cnt != null && cnt == 1l) {
					matchedVO = getMatchVO(returnList, "1");
				} else if (cnt != null && cnt == 2l) {
					matchedVO = getMatchVO(returnList, "2");
				} else if (cnt != null && cnt == 3l) {
					matchedVO = getMatchVO(returnList, "3");
				} else if (cnt != null && cnt == 4l) {
					matchedVO = getMatchVO(returnList, "4");
				} else if (cnt != null && cnt >= 5l && cnt <= 10l) {
					matchedVO = getMatchVO(returnList, "5-10");
				} else if (cnt != null && cnt >= 10l) {
					matchedVO = getMatchVO(returnList, "10-Above");
				}

				if (matchedVO != null) {
					Long boothId = (Long) obj[0];
					matchedVO.getBoothIds().add(boothId);// booths
					if (matchedVO.getTdpCadreId() != null && matchedVO.getTdpCadreId().longValue() > 0)
						matchedVO.setTdpCadreId(matchedVO.getTdpCadreId() + 1l);// no  of booth  incharges
					else
						matchedVO.setTdpCadreId(1l);// no of booth incharges
					if (obj[2] != null) {
						String gender = (String) obj[2];
						if (gender.equalsIgnoreCase("M")) {
							if (matchedVO.getMaleCnt() != null && matchedVO.getMaleCnt().longValue() > 0)
								matchedVO.setMaleCnt(matchedVO.getMaleCnt() + 1l);
							else
								matchedVO.setMaleCnt(1l);
						} else if (gender.equalsIgnoreCase("F")) {
							if (matchedVO.getFemaleCnt() != null && matchedVO.getFemaleCnt().longValue() > 0)
								matchedVO.setFemaleCnt(matchedVO.getFemaleCnt() + 1l);
							else
								matchedVO.setFemaleCnt(1l);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BoothInchargesVO getMatchVO(List<BoothInchargesVO> returnList, String boothInchrgAss) {
		if (returnList == null || returnList.size() == 1)
			return null;
		for (BoothInchargesVO boothIncharg : returnList) {
			if (boothIncharg.getBoothInchargesAssnd().equalsIgnoreCase(
					boothInchrgAss)) {
				return boothIncharg;
			}
		}
		return null;
	}
	@Override
	public List<LocationWiseBoothDetailsVO> getAllParlimentsForLocationDashBoard() {
		try{
			List<LocationWiseBoothDetailsVO> idNameVOList = new ArrayList<LocationWiseBoothDetailsVO>();
			List<Long> districtids = new ArrayList<Long>();
			Long[] ids = IConstants.AP_NEW_DISTRICTS_IDS;
			for (Long obj : ids) {
				districtids.add(obj);
			}
			List<Object[]> parlimentList= delimitationConstituencyAssemblyDetailsDAO.getAllParliamentConstituencyByStateId(districtids);
			
			for (Object[] objects : parlimentList) {
				if(objects!=null){
					LocationWiseBoothDetailsVO locationVo= new LocationWiseBoothDetailsVO();
					locationVo.setLocationId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					locationVo.setLocationName(WordUtils.capitalizeFully(commonMethodsUtilService.getStringValueForObject(objects[1])));
					locationVo.setName("Parliment");
					idNameVOList.add(locationVo);
				}
				
			}
			return idNameVOList;
		}catch(Exception e){
			Log.error("Exception raised at getAllParlimentsForLocationDashBoard", e);
			return null;
		}
	
		
	}	 

	@Override
	public List<LocationWiseBoothDetailsVO> getAllConstituencyByParlimentId(Long parliamentIds) {
		try{
			List<LocationWiseBoothDetailsVO> idNameVOList = new ArrayList<LocationWiseBoothDetailsVO>();
			
			@SuppressWarnings("unchecked")
			List<Object[]> findAssembliesConstituencies = (List<Object[]>) delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituencies(parliamentIds);
			
			for (Object[] objects : findAssembliesConstituencies) {
				if(objects!=null){
					LocationWiseBoothDetailsVO locationVo= new LocationWiseBoothDetailsVO();
					locationVo.setLocationId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					locationVo.setLocationName(WordUtils.capitalizeFully(commonMethodsUtilService.getStringValueForObject(objects[1])));
					locationVo.setName("Constituency");
					idNameVOList.add(locationVo);
				}
				
			}
			return idNameVOList;
		}catch(Exception e){
			Log.error("Exception raised at getAllConstituencyByParlimentId", e);
			return null;
		}
		
}
	
	public List<LocationVotersVO> getVotersCastGroupWiseCount(Long locationTypeId,Long locationValue,Long publicationDateId){
		
		try{
			List<LocationVotersVO>  listVo = new ArrayList<LocationVotersVO>();
			Long reportLevelId= 0l;
			List<Long> constituencyIds = new ArrayList<Long>();
			List<Long> locationIds = new ArrayList<Long>();
			locationIds.add(locationValue);
			if(locationTypeId == 3l){
		        List<Object[]> locationValuesObj = constituencyDAO.getDistrictConstituenciesList(locationIds);
		        for (Object[] objects : locationValuesObj) {
		          if(objects!=null){
		        	  constituencyIds.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
		          }
		        }
		        reportLevelId= 1l;
		      }else if(locationTypeId == 10l){
		    	  constituencyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(locationIds);
		    	  reportLevelId=1l;
		      }else if(locationTypeId == 4l){
		    	  constituencyIds.add(locationValue);
		    	  reportLevelId=1l;
		      }else if(locationTypeId == 5l){
		    	  reportLevelId=2l;
		    	  constituencyIds.add(locationValue);
		      }else if(locationTypeId == 6l){
		    	  reportLevelId=3l;
		    	  constituencyIds.add(locationValue);
		      }else if(locationTypeId == 7l){
		    	  reportLevelId=5l;
		    	  constituencyIds.add(locationValue);
		      }
		      else if(locationTypeId == 8l){
		    	  reportLevelId=6l;
		    	  constituencyIds.add(locationValue);
		      }
			List<Object[]> votersObjList = voterCastInfoDAO.getVotersCastGroupWiseCount(constituencyIds,null, publicationDateId, reportLevelId);
			if(votersObjList!=null){
				for (Object[] objects : votersObjList) {
					LocationVotersVO vo = new LocationVotersVO();
					vo.setAgeRangeId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setAgeRange(commonMethodsUtilService.getStringValueForObject(objects[1]));
					vo.setTotalVoters(commonMethodsUtilService.getLongValueForObject(objects[2]));
				//	vo.setTotalCadres(commonMethodsUtilService.getLongValueForObject(objects[0]));
					listVo.add(vo);
				}
			}
			// 0-castegroupId,1-castegroup,2-casteId,3-castegroup,4-voterscount,5-percentage,6-maleVotersCount,7-femaleVotersCount
			List<Object[]> cadresObjList = tdpCadreEnrollmentYearDAO.getCasteGroupWiseCadreCounts(locationValue,locationTypeId,4l);
			
			if (cadresObjList != null && cadresObjList.size() > 0) {
				for (Object[] objects : cadresObjList) {
					LocationVotersVO matchedCGVO = getMatchedVO(listVo, (Long) objects[0]);
					if (matchedCGVO == null) {
						matchedCGVO = new LocationVotersVO();
						matchedCGVO.setAgeRangeId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						matchedCGVO.setAgeRange(commonMethodsUtilService.getStringValueForObject(objects[1]));
						matchedCGVO.setTotalCadres(commonMethodsUtilService.getLongValueForObject(objects[2]));
						listVo.add(matchedCGVO);
					} else {
						matchedCGVO.setTotalCadres(commonMethodsUtilService.getLongValueForObject(objects[2]));
					}
				}
			}
			return listVo;
		}catch(Exception e){
			Log.error("Exception raised at getVotersCastGroupWiseCount", e);
			return null;
		}
		
		
	}
	
	/**
	 * @param List<Long> locationValues
	 * @param Long boardLevelId
	 * @param Long searchLvlId
	 * @param String type
	 * @return List<Long> statusIds
	 * @author Hymavathi 
	 * @Description :This service to show Nominated Post Analysis Details for AgeWise,CasteCategory,Subcaste,Mandal/Town/Divisin Wise. 
	 *  @since 5-SEP-2017
	 */
	public List<NominatedPostDetailsVO> getLocationWiseNominatedPostAnalysisDetails(List<Long> locationValues,Long boardLevelId,Long searchLvlId,String type,List<Long> statusIds,String fromDateStr, String toDateStr,String year){
		 
		 List<NominatedPostDetailsVO> returnList = new ArrayList<NominatedPostDetailsVO>();
		 try{
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

				Date startDate = null;
				Date endDate = null;
				if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && toDateStr != null && !toDateStr.isEmpty() && toDateStr.trim().length() > 0){
					startDate = sdf.parse(fromDateStr);
					endDate = sdf.parse(toDateStr);
				}
			 Map<Long,Map<Long,NominatedPostDetailsVO>> mainMap = new HashMap<Long,Map<Long,NominatedPostDetailsVO>>();
			 if(type != null && type.equalsIgnoreCase("mandal")){
				 if(searchLvlId != null && searchLvlId.longValue() == 4l){
					 List<Object[]> analysisReport = nominatedPostDAO.getLocationWiseNominatedPostAnalysisDetails(locationValues,boardLevelId,searchLvlId,"muncipality",statusIds,startDate,endDate,year);
					 setLocationWiseNominatedPostAnalysisData(returnList,analysisReport,"positionWise",mainMap);
				}
				 List<Object[]> analysisReport1 = nominatedPostDAO.getLocationWiseNominatedPostAnalysisDetails(locationValues,boardLevelId,searchLvlId,type,statusIds,startDate,endDate,year);
				 setLocationWiseNominatedPostAnalysisData(returnList,analysisReport1,"positionWise",mainMap);
				 Map<Long, NominatedPostDetailsVO> locationsMap = getTemplateForLocations( locationValues, searchLvlId,"positionWise")	;
				 if(commonMethodsUtilService.isListOrSetValid(returnList)){
					 for (NominatedPostDetailsVO mainVO : returnList) {
						 Map<Long,NominatedPostDetailsVO> positionMap = mainMap.get(mainVO.getId());
						 if(commonMethodsUtilService.isMapValid(positionMap)){
							for (Entry<Long, NominatedPostDetailsVO> entry : positionMap.entrySet()) {
								NominatedPostDetailsVO positionVO = entry.getValue();
								 mainVO.setMaleCount(mainVO.getMaleCount()+positionVO.getMaleCount());
								 mainVO.setFemaleCount(mainVO.getFemaleCount()+positionVO.getFemaleCount());
								 mainVO.setTotalCount(mainVO.getTotalCount()+positionVO.getTotalCount());
								 //mainVO.getSubList().add(positionVO);
							}
						}
					}
				 }
				 if(commonMethodsUtilService.isMapValid(locationsMap)){
					 for (Map.Entry<Long, NominatedPostDetailsVO> entry : locationsMap.entrySet()) {
						 NominatedPostDetailsVO vo = getMatchedVO2(returnList,entry.getValue().getId());
						 if(vo == null ){
							 returnList.add(entry.getValue());
						 }
					}
				 }
			}else{
				 List<Object[]> analysisReport = nominatedPostDAO.getLocationWiseNominatedPostAnalysisDetails(locationValues,boardLevelId,searchLvlId,type,statusIds,startDate,endDate,year);
				 setLocationWiseNominatedPostAnalysisData(returnList,analysisReport,"positionWise",mainMap);
				 if(commonMethodsUtilService.isListOrSetValid(returnList)){
					 for (NominatedPostDetailsVO mainVO : returnList) {
						 Map<Long,NominatedPostDetailsVO> positionMap = mainMap.get(mainVO.getId());
						 if(commonMethodsUtilService.isMapValid(positionMap)){
							for (Entry<Long, NominatedPostDetailsVO> entry : positionMap.entrySet()) {
								NominatedPostDetailsVO positionVO = entry.getValue();
								 mainVO.setMaleCount(mainVO.getMaleCount()+positionVO.getMaleCount());
								 mainVO.setFemaleCount(mainVO.getFemaleCount()+positionVO.getFemaleCount());
								 mainVO.setTotalCount(mainVO.getTotalCount()+positionVO.getTotalCount());
								 //mainVO.getSubList().add(positionVO);
							}
						}
					}
				 }
			}
			 
			 
		}catch (Exception e) {
			 e.printStackTrace();
				LOG.error("Exception Occured in getLocationWiseNominatedPostAnalysisDetails()", e);
		}
		 
		 return returnList;
	 }
	 
	public Map<Long, NominatedPostDetailsVO> getTemplateForLocations(List<Long> locationValues,Long searchLvlId,String type){
		Map<Long, NominatedPostDetailsVO> returnMap = new HashMap<Long, NominatedPostDetailsVO>();
		try{
			List<LocationWiseBoothDetailsVO> locations = null;
			if(searchLvlId != null && searchLvlId.longValue() == 3l){
				 locations = getAllConstituenciesByDistrict(locationValues.get(0).longValue());
			}else if(searchLvlId != null && searchLvlId.longValue() == 10l){
				locations = getAllConstituencyByParlimentId(locationValues.get(0).longValue());
			}else if(searchLvlId != null && searchLvlId.longValue() == 4l){
				//CadreCommitteeService cadreCommitteeService = new CadreCommitteeService();
				locations = cadreCommitteeService.getMandalsByConstituency(locationValues.get(0).longValue());
				locations.remove(0);
			}else if(searchLvlId != null && searchLvlId.longValue() == 5l){
				List<Long>  constituencyIdMan=tehsilDAO.getAllConstituenciesByTehilId(locationValues.get(0).longValue());
				//CadreCommitteeService cadreCommitteeService = new CadreCommitteeService();
				if(commonMethodsUtilService.isListOrSetValid(constituencyIdMan))
				locations = cadreCommitteeService.getPanchayatWardByMandalId(locationValues.get(0).toString(), constituencyIdMan.get(0).longValue());
				locations.remove(0);
			}
			
			if(commonMethodsUtilService.isListOrSetValid(locations)){
				for (LocationWiseBoothDetailsVO locationWiseBoothDetailsVO : locations) {
					Map<Long,NominatedPostDetailsVO> positionMap = new HashMap<Long,NominatedPostDetailsVO>();
					NominatedPostDetailsVO locationVO = new NominatedPostDetailsVO();
					locationVO.setId(Long.valueOf(locationWiseBoothDetailsVO.getLocationId().toString().substring(1)));
					locationVO.setName(locationWiseBoothDetailsVO.getLocationName());
					
					if(type.equalsIgnoreCase("boardLevelWise"))
						 getBoardLevels(positionMap);
					 else
						 getPositions(positionMap);
					locationVO.getSubList().addAll(positionMap.values());
					returnMap.put(locationWiseBoothDetailsVO.getLocationId(), locationVO);
				}
			}
		}catch (Exception e) {
			 e.printStackTrace();
				LOG.error("Exception Occured in getTemplateForLocations()", e);
		}
		return returnMap;
	}
	/**
	 * @param List<NominatedPostDetailsVO> returnList
	 * @param List<Object[]> analysisReport
	 * @param String type
	 * @author Hymavathi 
	 * @Description :This service to build the structure of Vo for Nominated Post Analysis Details Of AgeWise,CasteCategory,Subcaste,Mandal/Town/Divisin Wise. 
	 *  @since 6-SEP-2017
	 */
	 public Map<Long,Map<Long,NominatedPostDetailsVO>> setLocationWiseNominatedPostAnalysisData(List<NominatedPostDetailsVO> returnList,List<Object[]> analysisReport,String type,Map<Long,Map<Long,NominatedPostDetailsVO>> mainMap){
		 
		 try{
			 //Map<Long,Map<Long,NominatedPostDetailsVO>> mainMap = new HashMap<Long, Map<Long,NominatedPostDetailsVO>>(); 
			 if(commonMethodsUtilService.isListOrSetValid(analysisReport)){
				 for (Object[] objects : analysisReport) {
					 Map<Long,NominatedPostDetailsVO> positionMap = mainMap.get(commonMethodsUtilService.getLongValueForObject(objects[3]));
					 NominatedPostDetailsVO mainVO = null;
					 if(positionMap == null){
						 positionMap = new HashMap<Long,NominatedPostDetailsVO>();
						 mainVO = new NominatedPostDetailsVO();
						 mainVO.setId(commonMethodsUtilService.getLongValueForObject(objects[3]));
						 mainVO.setName(commonMethodsUtilService.getStringValueForObject(objects[4]));
						 returnList.add(mainVO);
						 if(type.equalsIgnoreCase("boardLevelWise"))
							 getBoardLevels(positionMap);
						 else
							 getPositions(positionMap);
						 
						 if(commonMethodsUtilService.isMapValid(positionMap)){
							 mainVO.getSubList().addAll(positionMap.values());
						 }
						 mainMap.put(commonMethodsUtilService.getLongValueForObject(objects[3]), positionMap);
					 }
					 NominatedPostDetailsVO positionVO = null;
					 if(type.equalsIgnoreCase("boardLevelWise")){
						 Long boardLvlId = commonMethodsUtilService.getLongValueForObject(objects[1]);
					   if(objects[1] != null && (boardLvlId.longValue() == 5l || boardLvlId.longValue() == 6l)){
						    boardLvlId=5l;
					   }else if(objects[1] != null && (boardLvlId.longValue() == 7l || boardLvlId.longValue() == 8l)){
						    boardLvlId=7l;
					   }/*else {
						    boardLvlId=(Long)objects[1] ;
					   }*/
					    positionVO = positionMap.get(boardLvlId) ;
					 }else{
					    positionVO = positionMap.get(commonMethodsUtilService.getLongValueForObject(objects[1])) ;
					 }
					 
					 if(positionVO != null){
						if(commonMethodsUtilService.getStringValueForObject(objects[5]).toString().equalsIgnoreCase("M")){
							 positionVO.setMaleCount(positionVO.getMaleCount()+commonMethodsUtilService.getLongValueForObject(objects[0]));
						}else if(commonMethodsUtilService.getStringValueForObject(objects[5]).toString().equalsIgnoreCase("F")){
							 positionVO.setFemaleCount(positionVO.getFemaleCount()+commonMethodsUtilService.getLongValueForObject(objects[0]));
						}
						positionVO.setTotalCount(positionVO.getTotalCount()+commonMethodsUtilService.getLongValueForObject(objects[0]));
					 }
				}
			 }
			 
			 
			 /*if(commonMethodsUtilService.isListOrSetValid(returnList)){
				 for (NominatedPostDetailsVO mainVO : returnList) {
					 Map<Long,NominatedPostDetailsVO> positionMap = mainMap.get(mainVO.getId());
					 if(commonMethodsUtilService.isMapValid(positionMap)){
						for (Entry<Long, NominatedPostDetailsVO> entry : positionMap.entrySet()) {
							NominatedPostDetailsVO positionVO = entry.getValue();
							 mainVO.setMaleCount(mainVO.getMaleCount()+positionVO.getMaleCount());
							 mainVO.setFemaleCount(mainVO.getFemaleCount()+positionVO.getFemaleCount());
							 mainVO.setTotalCount(mainVO.getTotalCount()+positionVO.getTotalCount());
							 //mainVO.getSubList().add(positionVO);
						}
					}
				}
			 }*/
			
			 
		 }catch (Exception e) {
			 e.printStackTrace();
				LOG.error("Exception Occured in setLocationWiseNominatedPostAnalysisData()", e);
		 }
		 return mainMap;
	 }
	
	/**
	 * @param  Long boardLevelId
	 * @param List<Long> levelValues
	 * @param Long levelId
	 * @return NominatedPostDashboardVO
	 * @author Swapna
	 * @Description :This Service Method is for getting openPost,finalizedAndGoIssued,applicatnsReceived,totalPosts group by nominatedPostStatusId,  boardLevelId. 
	 *  @since 06-SEPTEMBER-2017
	 */
	@Override
	public NominatedPostDashboardVO getAllNominatedStatusListLevelWiseData(Long boardLevelId, List<Long> levelValues, Long levelId,String fromDateStr, String toDateStr,String year) {
	NominatedPostDashboardVO vo = new NominatedPostDashboardVO();
	   
	    try {
	    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date startDate = null;
			Date endDate = null;
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && toDateStr != null && !toDateStr.isEmpty() && toDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
	    	 List<Long>list=new ArrayList<Long>();
			    Map<Long,NominatedPostDashboardVO> locationDtlsMap =new HashMap<Long, NominatedPostDashboardVO>();
			    if(boardLevelId==2l){  
			    	list.add(1l);
			    	list.add(2l);
			    	list.add(3l);
			    	list.add(4l);
			    	list.add(5l);
			    	list.add(6l);
			    	list.add(7l);
			    	list.add(8l);
			    }	
			    else if(boardLevelId==3l){
			    	list.add(3l);
			    	list.add(4l);
			    	list.add(5l);
			    	list.add(6l);
			    	list.add(7l);
			    	list.add(8l);
			    }
			    else if(boardLevelId==4l|| levelId==10l){
			    	list.add(4l);
			    	list.add(5l);
			    	list.add(6l);
			    	list.add(7l);
			    	list.add(8l);
			    	
			    }
			    else if(boardLevelId==5l){
			    	list.add(5l);
			    	list.add(6l);
			    	list.add(7l);
			    	list.add(8l);
			   }else if(boardLevelId==6l){
			    	list.add(6l);
			    	list.add(7l);
			    	list.add(8l);
			    }	
			    else if(boardLevelId==7l){
			    	list.add(7l);
			    	list.add(8l);		  
			    }	
			    else if(boardLevelId==8l){
			    	list.add(8l);	
			    }
			    
			   /*if(levelId == 3l){
		    	        List<Object[]> locationValuesObj = constituencyDAO.getDistrictConstituenciesList(levelValues);
		    	        levelValues.clear();
		    	      for (Object[] objects2 : locationValuesObj) {
						    if(objects2!=null){
						    	levelValues.add(commonMethodsUtilService.getLongValueForObject(objects2[0]));
		    	          }
		    	        }
		    	     // levelId=4l;
		      }else*/ if(levelId == 10l){
		    		   levelValues = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(levelValues);
		    	    }
		    	   
			  List<Object[]> receivedapp =nominatedPostApplicationDAO.getTotalReceivedApplicationsForLocation(list, levelId, levelValues,startDate, endDate,year);
		      List<Object[]> nominatedList=nominatedPostDAO.getAllNominatedStatusListLevelWise(list, levelValues,  levelId,startDate,  endDate,year);
			     	if (nominatedList!=null && nominatedList.size()>0){
				      for (Object[] objects : nominatedList) {					    	 
				    	  NominatedPostDashboardVO boardLvlVO = null;
				    	  if((Long)objects[2] == 1l  ){
				    		   boardLvlVO = locationDtlsMap.get((Long)objects[2]);
				    		  if( boardLvlVO == null){
				    			  boardLvlVO=new NominatedPostDashboardVO();
				    			  boardLvlVO.setId((Long)objects[2]);
				    			  boardLvlVO.setName("Central Level");
				    			  locationDtlsMap.put((Long)objects[2], boardLvlVO);
				    		  }
				    	   }
				    	  if((Long)objects[2] == 2l  ){
				    		   boardLvlVO = locationDtlsMap.get((Long)objects[2]);
				    		  if( boardLvlVO == null){
				    			  boardLvlVO=new NominatedPostDashboardVO();
				    			  boardLvlVO.setId((Long)objects[2]);
				    			  boardLvlVO.setName("State Level");
				    			  locationDtlsMap.put((Long)objects[2], boardLvlVO);
				    		  }
				    	   }
				    	   if((Long)objects[2] == 3l  ){
				    		   boardLvlVO = locationDtlsMap.get((Long)objects[2]);
				    		  if( boardLvlVO == null){
				    			  boardLvlVO=new NominatedPostDashboardVO();
				    			  boardLvlVO.setId((Long)objects[2]);
				    			  boardLvlVO.setName("District Level");
				    			  locationDtlsMap.put((Long)objects[2], boardLvlVO);
				    		  }
				    	   }else if((Long)objects[2] == 4l  ){
				    		   boardLvlVO = locationDtlsMap.get((Long)objects[2]);
				    		  if( boardLvlVO == null){
				    			  boardLvlVO=new NominatedPostDashboardVO();
				    			  boardLvlVO.setId((Long)objects[2]);
				    			  boardLvlVO.setName("Constituency Level");
				    			  locationDtlsMap.put((Long)objects[2], boardLvlVO);
				    		  }
				    	   }else if((Long)objects[2] == 5l || (Long)objects[2] == 6l ){
				    		   boardLvlVO = locationDtlsMap.get(5l);
				    		  if( boardLvlVO == null){
				    			  boardLvlVO=new NominatedPostDashboardVO();
				    			  boardLvlVO.setId((Long)objects[2]);
				    			  boardLvlVO.setName("Mandal/Muncipality/Corporation");
				    			  locationDtlsMap.put(5l, boardLvlVO);
				    		  }
				    	      }else if((Long)objects[2] == 7l || (Long)objects[2] == 8l ){
						    		   boardLvlVO = locationDtlsMap.get(7l);
						    		  if( boardLvlVO == null){
						    			  boardLvlVO=new NominatedPostDashboardVO();
						    			  boardLvlVO.setId((Long)objects[2]);
						    			  boardLvlVO.setName("Village/Ward");
						    			  locationDtlsMap.put(7l, boardLvlVO);
						    		  }
						        }   
					    	  				    	   
						  if((Long)objects[1] == 3l || (Long)objects[1] == 4l ){						  
							  boardLvlVO.setFinalizedAndGoIssued(boardLvlVO.getFinalizedAndGoIssued()+commonMethodsUtilService.getLongValueForObject(objects[0]));
						  }
						  if((Long)objects[1] == 1l){
							  boardLvlVO.setOpenPost(boardLvlVO.getOpenPost()+commonMethodsUtilService.getLongValueForObject(objects[0]));
						  }
						  boardLvlVO.setTotalPosts(boardLvlVO.getTotalPosts()+commonMethodsUtilService.getLongValueForObject(objects[0]));
						 }
					      
					      if(receivedapp!=null && receivedapp.size()>0){
				    		 for (Object[] param : receivedapp) {
								  NominatedPostDashboardVO  boardLvlVO=null;
				    			  if((Long)param[1] == 3l){
				    				  boardLvlVO=  locationDtlsMap.get(3l);
				    				  if(boardLvlVO!=null){
				    					  boardLvlVO.setApplicatnsReceived(boardLvlVO.getApplicatnsReceived()+(commonMethodsUtilService.getLongValueForObject(param[0]))) ;
				    				    }
				    			  }else if((Long)param[1] == 1l){
			    					  boardLvlVO=locationDtlsMap.get(1l);
			    					  if(boardLvlVO!=null){
				    					  boardLvlVO.setApplicatnsReceived(boardLvlVO.getApplicatnsReceived()+commonMethodsUtilService.getLongValueForObject(param[0])) ;
				    				    }
			    				  }else if((Long)param[1] == 2l){
			    					  boardLvlVO=locationDtlsMap.get(2l);
			    					  if(boardLvlVO!=null){
				    					  boardLvlVO.setApplicatnsReceived(boardLvlVO.getApplicatnsReceived()+commonMethodsUtilService.getLongValueForObject(param[0])) ;
				    				    }
			    				  }else if((Long)param[1] == 4l){
				    					  boardLvlVO=locationDtlsMap.get(4l);
				    					  if(boardLvlVO!=null){
					    					  boardLvlVO.setApplicatnsReceived(boardLvlVO.getApplicatnsReceived()+commonMethodsUtilService.getLongValueForObject(param[0])) ;
					    				    }
				    			  }else if((Long)param[1] == 5l || (Long)param[1] == 6l ){
				    					  boardLvlVO=locationDtlsMap.get(5l);
				    					  if(boardLvlVO!=null){
					    					  boardLvlVO.setApplicatnsReceived(boardLvlVO.getApplicatnsReceived()+commonMethodsUtilService.getLongValueForObject(param[0])) ;
					    				    }
				    			  }else if((Long)param[1] == 7l || (Long)param[1] == 8l){
			    					  boardLvlVO=locationDtlsMap.get(7l);
			    					  if(boardLvlVO!=null){
				    					  boardLvlVO.setApplicatnsReceived(boardLvlVO.getApplicatnsReceived()+commonMethodsUtilService.getLongValueForObject(param[0])) ;
				    				    }
				    			      }				    			  
				    			    }    
				    		      }
					      if(commonMethodsUtilService.isMapValid(locationDtlsMap)){
					    	  for(Entry<Long,NominatedPostDashboardVO> entry : locationDtlsMap.entrySet()){
					    		  NominatedPostDashboardVO returnVo=entry.getValue();
					    		  vo.setApplicatnsReceived(vo.getApplicatnsReceived()+returnVo.getApplicatnsReceived());
					    		  vo.setTotalPosts(vo.getTotalPosts()+returnVo.getTotalPosts());
					    		  vo.setOpenPost(vo.getOpenPost()+returnVo.getOpenPost());
					    		  vo.setFinalizedAndGoIssued(vo.getFinalizedAndGoIssued()+returnVo.getFinalizedAndGoIssued());
					    		  vo.setPercentage(calculatePercentage(vo.getTotalPosts(), vo.getOpenPost()));//openCount%
					    		  vo.setPercentage1(calculatePercentage(vo.getTotalPosts(), vo.getFinalizedAndGoIssued()));//finalized%
					    		  vo.getPositinsList().add(returnVo);    		  
					    		  
					    	  }			    	  
					    	  
					      }
				     	      
				     	}
		                 }catch (Exception e) {
				          Log.error("Exception raised at getAllNominatedStatusListLevelWiseData", e);
			}
			return vo ;
			
		 }

	/**
	 *
	 * @param locationValues,levelId,statusIdsList,type
	 * @return List<NominatedPostDetailsVO>
	 * 
	 * @author Srujana 
	 * @Description :This Service Method is used to get the Location wise nominatedPost candidate AgeRange and CasteCategory Groups details
	 * @since 09-SEP-2017
	 * 
	 */
public List<NominatedPostDetailsVO> getLocationWiseNominatedPostCandidateAgeRangeAndCasteCategorDetails(List<Long> locationValues,Long levelId,List<Long> statusIdsList,String type,String fromDateStr, String toDateStr,String year){
		 
		 List<NominatedPostDetailsVO> returnList = new ArrayList<NominatedPostDetailsVO>();
		 try{
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

				Date startDate = null;
				Date endDate = null;
				if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && toDateStr != null && !toDateStr.isEmpty() && toDateStr.trim().length() > 0){
					startDate = sdf.parse(fromDateStr);
					endDate = sdf.parse(toDateStr);
				}
			 
			 Long totalCount =0l;
			 NominatedPostDetailsVO vo = null;
			 List<Object[]> candidateDetails = nominatedPostDAO.getLocationWiseNominatedPostCandidateAgeRangeAndCasteCategorDetails(locationValues,levelId,statusIdsList,type,startDate,endDate,year);
			 if(candidateDetails != null && candidateDetails.size()>0){
				 for(Object[] param : candidateDetails){
					    vo = new NominatedPostDetailsVO();
					    vo.setCount(commonMethodsUtilService.getLongValueForObject(param[0]));
					 vo.setId(commonMethodsUtilService.getLongValueForObject(param[1]));
					 vo.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
					 totalCount = totalCount+vo.getCount();
					
					 returnList.add(vo);
				 }
			 }
			 if(returnList != null && returnList.size()>0){
				 for(NominatedPostDetailsVO detailsVo :returnList){
					 detailsVo.setTotalCount(totalCount);
					 Double complaincePer = calculatePercantage(detailsVo.getCount(),detailsVo.getTotalCount());
					 if(complaincePer != null)
					     detailsVo.setPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(complaincePer));
					// param.setPerc(String.valueOf( (count* 100) / totalCount));
				 }
			 }
				
		     }catch (Exception e) {
			 e.printStackTrace();
				LOG.error("Exception Occured in getLocationWiseNominatedPostCandidateAgeRangeAndCasteCategorDetails()", e);
		}
		 
		 return returnList;
	 }
 /**
	 * @param Long levelId
	 * @param List<Long> levelVals
	 * @param List<Long> statusIds
	 * @author Hymavathi 
	 * @Description :This service to show boardlevel wise  Nominated post candidate details for mandal/muncipality/corporation . 
	 *  @since 6-SEP-2017
	 */
 public NominatedPostDetailsVO getAreaWiseDashboardCandidatesCountView(Long levelId,List<Long> levelVals,List<Long> statusIds,String fromDateStr, String toDateStr,String year){
	 NominatedPostDetailsVO returnVO = new NominatedPostDetailsVO();
	 try{
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date startDate = null;
			Date endDate = null;
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && toDateStr != null && !toDateStr.isEmpty() && toDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
		 Map<Long,Map<Long,NominatedPostDetailsVO>> mainMap = new HashMap<Long,Map<Long,NominatedPostDetailsVO>>();
		 if(levelId != null && levelId.longValue() == 4l){
			 List<Object[]> candidateList = nominatedPostDAO.getAreaWiseDashboardCandidatesCountView(levelVals,levelId,statusIds,"muncipality",startDate,endDate,year);
			  List<NominatedPostDetailsVO>  subList = new ArrayList<NominatedPostDetailsVO>();
			  setLocationWiseNominatedPostAnalysisData(subList,candidateList,"boardLevelWise",mainMap);
			  if(subList != null && subList.size() > 0){
				   returnVO.getSubList().addAll(subList);
			   }
		 }
		  List<Object[]> candidateList = nominatedPostDAO.getAreaWiseDashboardCandidatesCountView(levelVals,levelId,statusIds,"",startDate,endDate,year);
		  List<NominatedPostDetailsVO>  subList = new ArrayList<NominatedPostDetailsVO>();
		   setLocationWiseNominatedPostAnalysisData(subList,candidateList,"boardLevelWise",mainMap);
		   if(commonMethodsUtilService.isListOrSetValid(subList)){
				 for (NominatedPostDetailsVO mainVO : subList) {
					 Map<Long,NominatedPostDetailsVO> positionMap = mainMap.get(mainVO.getId());
					 if(commonMethodsUtilService.isMapValid(positionMap)){
						for (Entry<Long, NominatedPostDetailsVO> entry : positionMap.entrySet()) {
							NominatedPostDetailsVO positionVO = entry.getValue();
							 mainVO.setMaleCount(mainVO.getMaleCount()+positionVO.getMaleCount());
							 mainVO.setFemaleCount(mainVO.getFemaleCount()+positionVO.getFemaleCount());
							 mainVO.setTotalCount(mainVO.getTotalCount()+positionVO.getTotalCount());
							 //mainVO.getSubList().add(positionVO);
						}
					}
				}
			 }
		   if(subList != null && subList.size() > 0){
			   returnVO.getSubList().addAll(subList);
		   }
		  
		   Map<Long,NominatedPostDetailsVO> boardLvelMap = new HashMap<Long,NominatedPostDetailsVO>();
		   getBoardLevels(boardLvelMap);
		   
		   if(commonMethodsUtilService.isListOrSetValid(candidateList)){
			   for (Object[] obj : candidateList) {
				   Long boardLvlId = commonMethodsUtilService.getLongValueForObject(obj[1]);
				   
				   if(obj[1] != null && (boardLvlId.longValue() == 5l || boardLvlId.longValue() == 6l)){
					    boardLvlId=5l;
				   }if(obj[1] != null && (boardLvlId.longValue() == 7l || boardLvlId.longValue() == 8l)){
					    boardLvlId=7l;
				   }/*else if(obj[1] != null){
					    boardLvlId=(Long)obj[1] ;
				   }*/
				   NominatedPostDetailsVO vo = boardLvelMap.get(boardLvlId);
				   if(vo != null){
					   if(commonMethodsUtilService.getStringValueForObject(obj[5]).toString().equalsIgnoreCase("M")){
						   vo.setMaleCount(vo.getMaleCount()+commonMethodsUtilService.getLongValueForObject(obj[0]));
						   returnVO.setMaleCount(returnVO.getMaleCount()+commonMethodsUtilService.getLongValueForObject(obj[0]));
						}else if(commonMethodsUtilService.getStringValueForObject(obj[5]).toString().equalsIgnoreCase("F")){
							vo.setFemaleCount(vo.getFemaleCount()+commonMethodsUtilService.getLongValueForObject(obj[0]));
							returnVO.setFemaleCount(returnVO.getFemaleCount()+commonMethodsUtilService.getLongValueForObject(obj[0]));
						}
					   vo.setTotalCount(vo.getTotalCount()+commonMethodsUtilService.getLongValueForObject(obj[0]));
					   returnVO.setTotalCount(returnVO.getTotalCount()+commonMethodsUtilService.getLongValueForObject(obj[0]));
				   }
			   }
		   }
		   if(commonMethodsUtilService.isMapValid(boardLvelMap)){
				for (Entry<Long, NominatedPostDetailsVO> entry : boardLvelMap.entrySet()) {
					returnVO.getList().add(entry.getValue());
				}
			}
		   Map<Long, NominatedPostDetailsVO> locationsMap = getTemplateForLocations( levelVals, levelId,"boardLevelWise")	;
		   returnVO.setPerc(cadreDetailsService.calculatePercentage(returnVO.getTotalCount(), returnVO.getMaleCount()));//male%
		   returnVO.setPerc1(cadreDetailsService.calculatePercentage(returnVO.getTotalCount(), returnVO.getFemaleCount()));//female%
			 if(commonMethodsUtilService.isMapValid(locationsMap)){
				 for (Map.Entry<Long, NominatedPostDetailsVO> entry : locationsMap.entrySet()) {
					 NominatedPostDetailsVO vo = getMatchedVO2(returnVO.getSubList(),entry.getValue().getId());
					 if(vo == null ){
						 returnVO.getSubList().add(entry.getValue());
					 }
				}
			 }
	 }catch (Exception e) {
		 e.printStackTrace();
			LOG.error("Exception Occured in getAreaWiseDashboardCandidatesCountView()", e);
	}
	 return returnVO;
 }
 
 public Map<Long,NominatedPostDetailsVO> getBoardLevels(Map<Long,NominatedPostDetailsVO> boardLvelMap){
	try{
		List<BoardLevel> boardLvls = boardLevelDAO.getAll();
		   if(commonMethodsUtilService.isListOrSetValid(boardLvls)){
			   for (BoardLevel boardLevel : boardLvls) {
				   NominatedPostDetailsVO vo =null;
				   Long boardLvlId =0l;
				   String name = "";
				   if(boardLevel.getBoardLevelId() != null 
						   && (boardLevel.getBoardLevelId().longValue() == 5l || boardLevel.getBoardLevelId().longValue() == 6l)){
					    vo = boardLvelMap.get(5l);
					    boardLvlId=5l;
					    name= "Mandal/Muncipality/Corporation";
				   }else if(boardLevel.getBoardLevelId() != null 
						   && (boardLevel.getBoardLevelId().longValue() == 7l || boardLevel.getBoardLevelId().longValue() == 8l)){
					    vo = boardLvelMap.get(7l);
					    boardLvlId=7l;
					    name= "Panchayat/Ward";
				   }else {
					    vo = boardLvelMap.get(boardLevel.getBoardLevelId());
					    boardLvlId=boardLevel.getBoardLevelId();
					    name= boardLevel.getLevel();
				   }
				   
				   if(vo == null){
					   vo = new NominatedPostDetailsVO();
					   vo.setId(boardLvlId) ;
					   vo.setName(name);
					   boardLvelMap.put(boardLvlId, vo);
				   }
			}
		   }
	}catch (Exception e) {
		e.printStackTrace();
		LOG.error("Exception Occured in getBoardLevels()", e);
	}
	return boardLvelMap;
 }
 public Map<Long,NominatedPostDetailsVO> getPositions(Map<Long,NominatedPostDetailsVO> positionMap){
		try{
			List<Position> positions = positionDAO.getAll();
			   if(commonMethodsUtilService.isListOrSetValid(positions)){
				   for (Position position : positions) {
					   NominatedPostDetailsVO vo =null;
					   
					   if(vo == null){
						   vo = new NominatedPostDetailsVO();
						   vo.setId(position.getPositionId()) ;
						   vo.setName(position.getPositionName());
						   positionMap.put(vo.getId(), vo);
					   }
				}
			   }
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getPositions()", e);
		}
		return positionMap;
	 }
        //* @author Swapna
	    public LocationVO getAllLocationWiseCount(List<Long> locationValues, Long locationTypeId,
	    		Long publicationDateId) {
		LocationVO VO = new LocationVO();
		try {
			List<Long> districtIds = new ArrayList<Long>();
	       			List<Long> constituencyIds = new ArrayList<Long>();
			List<Long> tehsilIds = new ArrayList<Long>();
			List<Long> panchaythIds = new ArrayList<Long>();
			List<Long> localBodyIds = new ArrayList<Long>();
			 List<Long> parliamentIds = new ArrayList<Long>();
			Long constituencyCount=0l, mandalCount=0l,panchaythCount=0l,boothCount=0l,totalNoOfWards=0l, municipalityCount=0l,
					 hamletCount =0l,districtCount=0l, parlimentCount=0l;;
			List<Tehsil> mandals = new ArrayList<Tehsil>();
			List<Long> delimitationConstituency = new ArrayList<Long>();
			List<Object[]> localBodies = new ArrayList<Object[]>();
			List<Object[]> panchayatsList =null;
			List<Object[]> parlimentsList =null;
			List<Object[]> constituencyList =null;
		    List<Long> newDistrictArr = new ArrayList<Long>();
		      Long[] ids = IConstants.AP_NEW_DISTRICTS_IDS;
		      for (Long param : ids) {
		        newDistrictArr.add(param);
		      }
		      if (locationTypeId == 2l) {
		        List<Object[]> districtList = districtDAO.getAllNewDistrictDetailsForAState(1l, newDistrictArr);
		        for (Object[] objects : districtList) {
		          if (objects != null) {
		            districtIds.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
		            districtCount= districtCount+1;  
		          }
		        }
		        constituencyList = constituencyDAO.getDistrictConstituenciesList(districtIds);
		        for (Object[] obj : constituencyList) {
		          if (obj != null) {
		            constituencyIds.add(commonMethodsUtilService.getLongValueForObject(obj[0]));
		            constituencyCount = constituencyCount + 1;
		          }
		        }
		        parlimentsList =  delimitationConstituencyAssemblyDetailsDAO.getAllParliamentConstituencyByStateId(districtIds);
		        for (Object[] object : districtList) {
		          if (object != null) {
		            parliamentIds.add(commonMethodsUtilService.getLongValueForObject(object[0]));
		            parlimentCount=parlimentCount+1;
		            }  
		        }
		        mandals = tehsilDAO.findByDistrictIds(districtIds);
		        localBodies = assemblyLocalElectionBodyDAO.getAllLocalBodiesInAConstituencyList(constituencyIds);
		        boothCount = panchayatDAO.getBoothIdsCount(locationTypeId,constituencyIds,publicationDateId);
		    
		        VO.setConstituencyCount(constituencyCount);
		        VO.setDistrictCount(districtCount);
		        VO.setParlimentsCount(parlimentCount);
		       // if(commonMethodsUtilService.isListOrSetValid(parliamentIds))
		        //	VO.setParlimentsCount(Long.valueOf(String.valueOf(parliamentIds.size())));
		        
		      }
			if (locationTypeId == 3l) {
				List<Object[]> locationValuesObj = constituencyDAO.getDistrictConstituenciesList(locationValues);
				for (Object[] objects : locationValuesObj) {
					if (objects != null) {
						constituencyIds.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
						constituencyCount = constituencyCount + 1;
					}
				}
				mandals = tehsilDAO.findByDistrictIds(locationValues);
				localBodies = assemblyLocalElectionBodyDAO.getAllLocalBodiesInAConstituencyList(constituencyIds);
				boothCount = panchayatDAO.getBoothIdsCount(locationTypeId,constituencyIds,publicationDateId);
				VO.setConstituencyCount(constituencyCount);
			}else if(locationTypeId==10l){
				constituencyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(locationValues);
				delimitationConstituency = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyIDsForLocationDashBoard(constituencyIds);
				localBodies = assemblyLocalElectionBodyDAO.getAllLocalBodiesInAConstituencyList(constituencyIds);
				boothCount = panchayatDAO.getBoothIdsCount(locationTypeId,constituencyIds,publicationDateId);
				mandals = delimitationConstituencyMandalDAO.getTehsilsByDelimitationsConstituencyID(delimitationConstituency);
				VO.setConstituencyCount(Long.valueOf(constituencyIds.size()));
			}else if( locationTypeId == 4l){
				delimitationConstituency = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyIDsForLocationDashBoard(locationValues);
				localBodies = assemblyLocalElectionBodyDAO.getAllLocalBodiesInAConstituencyList(locationValues);
				boothCount = panchayatDAO.getBoothIdsCount(locationTypeId,locationValues,publicationDateId);
				mandals = delimitationConstituencyMandalDAO.getTehsilsByDelimitationsConstituencyID(delimitationConstituency);
			}
			if(locationTypeId == 5l){
				tehsilIds.addAll(locationValues);
				boothCount = panchayatDAO.getBoothIdsCount(locationTypeId,locationValues,publicationDateId);
			}else if(locationTypeId == 6l){
				panchaythIds.addAll(locationValues);
				boothCount = panchayatDAO.getBoothIdsCount(locationTypeId,locationValues,publicationDateId);
			}else if(locationTypeId == 7l){
				localBodyIds.addAll(locationValues);
				boothCount = panchayatDAO.getBoothIdsCount(locationTypeId,locationValues,publicationDateId);
			}
			
			if(mandals!=null && mandals.size()>0){
				for (Tehsil tehsil : mandals) {
					tehsilIds.add(tehsil.getTehsilId());
					mandalCount=mandalCount+1;
				}
				VO.setTehsilCount(mandalCount);
			}
			if(tehsilIds != null && tehsilIds.size()>0){
				panchayatsList = panchayatDAO.getAllPanchayatsInMandalsList(tehsilIds);
			}
			if (localBodies != null && localBodies.size()>0) {
				for (Object[] objects : localBodies) {
					municipalityCount = municipalityCount + 1;
					localBodyIds.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
				}
				VO.setMunicipalityCount(municipalityCount);
			}
			
			if (panchayatsList != null && panchayatsList.size()>0) {
				for (Object[] objects : panchayatsList) {
					panchaythIds.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
					panchaythCount = panchaythCount + 1;
				}
				VO.setVillageIdCount(panchaythCount);
			}
			if(panchaythIds!=null && panchaythIds.size()>0 ){
			 hamletCount = panchayatDAO.getHamletCountOnPanchayatIds(panchaythIds);
               /*int filterCount = 200;
               int i = 0; 
               int j = filterCount;
               int maxcount = panchaythIds.size();
               while (maxcount >0){  
                   if(maxcount<filterCount)
                       j = i+maxcount;
                   		Long tempHamletCount = panchayatDAO.getHamletCountOnPanchayatIds(panchaythIds.subList(i, j));
                      if(tempHamletCount != null && tempHamletCount.longValue()>0L){
                    	  hamletCount = hamletCount+ tempHamletCount;
                      }
	                       i=j;
	                       maxcount = maxcount-filterCount;
	                       j=j+filterCount;
               }*/
               
			 VO.setHamletCount(hamletCount);
			// VO.setHamletCount(50372L);
			}
			
			if(localBodyIds != null && localBodyIds.size() >0){
			List<Object[]> localBodyList = constituencyDAO.getWardsInLocalElectionBody(localBodyIds);
				if (localBodyList != null) {
					for (Object[] objects : localBodyList) {
						if(objects != null){
							totalNoOfWards = totalNoOfWards + 1;
						}
					}
				}
				  VO.setTotalNoOfWards(totalNoOfWards);
			}
		    VO.setBoothCount(boothCount);
		  
		} catch (Exception e) {
			LOG.error("Exception raised at getAllLocationWiseCount ", e);
		}
		return VO;
	}
	    
	public CandidateInfoForConstituencyVO getMatchedVOForCadreId(List<CandidateInfoForConstituencyVO> cadreVOs,Long id){
			try{
				if(cadreVOs != null && cadreVOs.size() > 0){
					for(CandidateInfoForConstituencyVO param : cadreVOs){
						if(param.getCandidateId().longValue() == id){
							return param;
						}
					}
				}
			}catch(Exception e){
				Log.error("Exception raised in getMatchedVOForCadreId method of LocationDashboardService"+e);
			}
			return null;
		}
	@SuppressWarnings("null")
	public List<CommitteeBasicVO>  getAllLocationWiseCommitteesCount(String  locationType, Long locationId,Long tdpCommitteeEnrollmentYearId){
		List<CommitteeBasicVO>  comitteeList=new ArrayList<CommitteeBasicVO>();
		 
		try{
				Map<String, Long> cntMap = new HashMap<String, Long>(0);
				
			 List<Object[]> listCmt=tdpCommitteeDAO.getLocationWiseCommitteesCnt( locationType,  locationId, tdpCommitteeEnrollmentYearId);
			  for (Object[] objects : listCmt) {
				  Long count=cntMap.get(commonMethodsUtilService.getStringValueForObject(objects[0]));
				  if(count == null ){
					  cntMap.put(commonMethodsUtilService.getStringValueForObject(objects[0]), 1l);
				  }else{
					 count=count+1;
					 cntMap.put(commonMethodsUtilService.getStringValueForObject(objects[0]), count);
				  }
			  }
		
			 List<TdpCommitteeLevel> committee = tdpCommitteeLevelDAO.getAll();
			 for (TdpCommitteeLevel tdpCommitteeLevel : committee) {
				 Long count=cntMap.get(tdpCommitteeLevel.getTdpCommitteeLevel());
				 CommitteeBasicVO vo = new CommitteeBasicVO();
				 if(count == null ){
					 vo.setAffiCommTotalCount(tdpCommitteeLevel.getTdpCommitteeLevelId());
					 vo.setCommiteeName(tdpCommitteeLevel.getTdpCommitteeLevel());
					 vo.setAffiCommCompletedCount(0l);
					 comitteeList.add(vo);
					 
				 }	else{
					 vo.setAffiCommTotalCount(tdpCommitteeLevel.getTdpCommitteeLevelId());
					 vo.setCommiteeName(tdpCommitteeLevel.getTdpCommitteeLevel());
					 vo.setAffiCommCompletedCount(count);
					 comitteeList.add(vo);
				 }
			}
		 		
		}catch(Exception e){
			Log.error("Exception raised in getAllLocationWiseCommitteesCount method of LocationDashboardService"+e);
		}
		return  comitteeList  ;
		 
	}
	public CandidateDetailsForConstituencyTypesVO getMatchedVOForCadreIdForPartyId(List<CandidateDetailsForConstituencyTypesVO> cadreVOs,Long id){
		try{
			if(cadreVOs != null && cadreVOs.size() > 0){
				for(CandidateDetailsForConstituencyTypesVO param : cadreVOs){
					if(param.getCondidateId().longValue() == id){
						return param;
					}
				}
			}
		}catch(Exception e){
			Log.error("Exception raised in getMatchedVOForCadreId method of LocationDashboardService"+e);
		}
		return null;
	}
	public CandidateDetailsForConstituencyTypesVO getPartyWiseMPandMLACandidatesCount(List<Long> electionIds,List<Long> electionScopeIds,Long loactionTypeId,Long loctionValue){
		CandidateDetailsForConstituencyTypesVO finalVO= new CandidateDetailsForConstituencyTypesVO();
		try{
			List<Object[]> mpPartyCountDetails = nominationDAO.partyWiseMemberOfParliments(electionIds,electionScopeIds, loactionTypeId,loctionValue);
			List<Object[]> mlaPartyCountDetails=nominationDAO.partyWiseMemberOfAssemblyCandidateCounts(electionIds,electionScopeIds, loactionTypeId,loctionValue);
			settingPartyWiseCandidateCount(mpPartyCountDetails,finalVO,"parlaiment");
			settingPartyWiseCandidateCount(mlaPartyCountDetails,finalVO,"assembly");
			if(commonMethodsUtilService.isListOrSetValid(finalVO.getSubList()) && commonMethodsUtilService.isListOrSetValid(finalVO.getSubList2())){
				commonMethodsUtilService.sortPartyVoList(finalVO.getSubList());
				commonMethodsUtilService.sortPartyVoList(finalVO.getSubList2());
			}
		}catch(Exception e){
			Log.error("Exception raised in getPartyWiseMPandMLACandidatesCount method of LocationDashboardService"+e);
		}
		
		return finalVO;
		
	}
	public void settingPartyWiseCandidateCount(List<Object[]> mpPartyCountDetails,CandidateDetailsForConstituencyTypesVO finalVO,String type){
		List<CandidateDetailsForConstituencyTypesVO> mpList = new ArrayList<CandidateDetailsForConstituencyTypesVO>();
		List<CandidateDetailsForConstituencyTypesVO> mlaList=  new ArrayList<CandidateDetailsForConstituencyTypesVO>();
		try{
			Map<Long,CandidateDetailsForConstituencyTypesVO> countMap = new HashMap<Long,CandidateDetailsForConstituencyTypesVO>();
			CandidateDetailsForConstituencyTypesVO partCountVo =null;
			if(mpPartyCountDetails != null && mpPartyCountDetails.size()>0){
				 for(Object[] param : mpPartyCountDetails){
					 Long partyId = commonMethodsUtilService.getLongValueForObject(param[1]);
					 if(partyId.longValue() == 163L || partyId.longValue() == 872L || partyId.longValue() == 1117L){
					   partCountVo=countMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));
					   if(partCountVo == null){
						   partCountVo =new CandidateDetailsForConstituencyTypesVO();
						   partCountVo.setScopeId(commonMethodsUtilService.getLongValueForObject(param[0]));
						   partCountVo.setPartyId(commonMethodsUtilService.getLongValueForObject(param[1]));
						   partCountVo.setParty(commonMethodsUtilService.getStringValueForObject(param[2]));
						   partCountVo.setCandidateName(commonMethodsUtilService.getStringValueForObject(param[4]));
						   partCountVo.setPartyFlag(commonMethodsUtilService.getStringValueForObject(param[5]));
						   partCountVo.setElectionId(commonMethodsUtilService.getLongValueForObject(param[6]));
						   partCountVo.setElectionYear(commonMethodsUtilService.getLongValueForObject(param[7]));
						   countMap.put(commonMethodsUtilService.getLongValueForObject(param[1]),partCountVo);
						   if(type != null && type.equalsIgnoreCase("parlaiment")){
						    mpList.add(partCountVo);
						    finalVO.setSubList(mpList);
						   }else if(type != null && type.equalsIgnoreCase("assembly")){
							  mlaList.add(partCountVo);
							  finalVO.setSubList2(mlaList);
						   }
					   }
					 }else{
						 partCountVo=countMap.get(0L);
						   if(partCountVo == null){
							   partCountVo =new CandidateDetailsForConstituencyTypesVO();
							   partCountVo.setScopeId(commonMethodsUtilService.getLongValueForObject(param[0]));
							   partCountVo.setPartyId(0l);
							   partCountVo.setParty("OTHERS");
							   countMap.put(0L,partCountVo);
							   if(type != null && type.equalsIgnoreCase("parlaiment")){
							   mpList.add(partCountVo);
							   finalVO.setSubList(mpList);
							   }else if(type != null && type.equalsIgnoreCase("assembly")){
								   mlaList.add(partCountVo);
								   finalVO.setSubList2(mlaList);  
							   }
						   }
					 }
				partCountVo.setCondidateCount(partCountVo.getCondidateCount()+commonMethodsUtilService.getLongValueForObject(param[3]));
					   
				 }
			 }
		}catch(Exception e){
			Log.error("Exception raised in settingPartyWiseCandidateCount method of LocationDashboardService"+e);
		}
	}
	
	/***
	 * electionYears 
	 */
	public KeyValueVO getElectionYears(List<String> subTypes) {
		KeyValueVO retrunVO = new KeyValueVO();
		try{
			List<String> electionYears = electionDAO.getElectionYears(subTypes);
			retrunVO.setImageList(electionYears);
			
		}catch (Exception e) {
			Log.error("Exception raised in getElectionYears method of LocationDashboardService"+e);
		}
		return retrunVO;
	}
	/* @param String fromDateStr
	 * @param String toDateStr
	 * @param Long locationId
	 * @param Long locationValue
	 * @author Sanjeev
	 * @return List<List<GrivenceStatusVO>> we have two lists in final list 1.Grivence counts(Govt,party,welfare) 2.Trust counts
	 * (non-Javadoc)
	 * @see com.itgrids.core.api.service.ILocationDashboardService#getGrivenceTrustStatusCounts(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<ElectionInformationVO> getElectionInformationLocationWiseVoterShare(String fromDateStr, String toDateStr, Long locationTypeId,
			Long locationValue, List<Long> electionScopeIds,List<Long> partyIds, List<Long> electionYrs, List<String> subTypes,String withAllaince) {
		try{
			List<ElectionInformationVO> finalPartyList = new ArrayList<ElectionInformationVO>();
			if(fromDateStr != null && !fromDateStr.trim().isEmpty() && toDateStr != null && !toDateStr.trim().isEmpty()){
				Long fromYear = Long.parseLong(fromDateStr.split("-")[2]);
				Long toYear = Long.parseLong(toDateStr.split("-")[2]);

				for (Long i = fromYear; i <= toYear; i++) {
					electionYrs.add(i);
				}
			}
			
			Map<Long,List<ElectionInformationVO>> yearMap = new TreeMap<Long, List<ElectionInformationVO>>();
			if(electionYrs !=null && electionYrs.size()>0l){
				for (Long year : electionYrs) {
					List<ElectionInformationVO> listEvo =yearMap.get(year);
					if(listEvo == null){
						listEvo = new ArrayList<ElectionInformationVO>();
					}
					ElectionInformationVO evo= new ElectionInformationVO();
					evo.setElectionYear(year.toString());
					listEvo.add(evo);
					yearMap.put(year, listEvo);
				}
			}
			
			Map<Long,List<ElectionInformationVO>> finalYearMap = new TreeMap<Long, List<ElectionInformationVO>>();
			List<Long> parliamentIdsList = new ArrayList<Long>(0);
			if(locationTypeId != null && locationTypeId.longValue()==10L)
				parliamentIdsList.add(locationValue);
			else{
				String[] parliamentIdsArr = IConstants.AP_PARLIAMENT_IDS_LIST;
				if(parliamentIdsArr != null && parliamentIdsArr.length>0){
					for (int i = 0; i < parliamentIdsArr.length; i++) {
						parliamentIdsList.add(Long.valueOf(parliamentIdsArr[i].trim()));
					}
				}
			}
			
			List<Object[]> validVoterList= null;
			
			if(locationTypeId != null && locationTypeId.longValue() !=5L && locationTypeId.longValue() !=7L && locationTypeId.longValue() !=6L ){
				validVoterList= candidateDAO.getElectionInformationLocationWiseVoterShare(electionYrs, locationTypeId, locationValue,electionScopeIds,null,subTypes,null,false);
				if(locationTypeId == 4l){
					
					List<Object[]> validVoterMandalList= candidateDAO.getElectionInformationLocationWiseVoterShare(electionYrs, locationTypeId, locationValue,electionScopeIds,null,subTypes,null,true);
					if(commonMethodsUtilService.isListOrSetValid(validVoterMandalList)){
						validVoterList.addAll(validVoterMandalList);
					}
				}

				if(!commonMethodsUtilService.isListOrSetValid(validVoterList)){
					validVoterList = new ArrayList<Object[]>();
				}
				List<Object[]> parliamentWiseValidVoterList= candidateDAO.getElectionInformationLocationWiseVoterShare(electionYrs, locationTypeId, locationValue,electionScopeIds,null,subTypes,parliamentIdsList,false);
				if(commonMethodsUtilService.isListOrSetValid(parliamentWiseValidVoterList))
					validVoterList.addAll(parliamentWiseValidVoterList);
			}else{
				validVoterList= candidateDAO.getElectionInformationLocationWiseVoterShare(electionYrs, locationTypeId, locationValue,electionScopeIds,"lowLevels",subTypes,null,false);
				if(locationTypeId == 5l){
					List<Object[]> validVoterMptcList= candidateDAO.getElectionInformationLocationWiseVoterShare(electionYrs, locationTypeId, locationValue,electionScopeIds,null,subTypes,null,true);
					if(commonMethodsUtilService.isListOrSetValid(validVoterMptcList)){
						validVoterList.addAll(validVoterMptcList);
					}
				}
			}
			
			Map<Long,ElectionInformationVO> partyMap = new HashMap<Long, ElectionInformationVO>(0);
			
			for (Object[] objects : validVoterList ) {
				if(electionScopeIds.contains(commonMethodsUtilService.getLongValueForObject(objects[2]))){
					List<ElectionInformationVO> totalVoList =yearMap.get(commonMethodsUtilService.getLongValueForObject(objects[1]));
					if(totalVoList == null){
						totalVoList = new ArrayList<ElectionInformationVO>();
					}
					
					for (ElectionInformationVO electionInformationVO : totalVoList) {
						if(electionInformationVO.getElectionYear().equalsIgnoreCase(commonMethodsUtilService.getStringValueForObject(objects[1]))){
							List<ElectionInformationVO> listEvo =finalYearMap.get(Long.valueOf(electionInformationVO.getElectionYear()));
							if(listEvo == null){
								listEvo = new ArrayList<ElectionInformationVO>();
							}
							electionInformationVO = new ElectionInformationVO();
							electionInformationVO.setElectionId(commonMethodsUtilService.getLongValueForObject(objects[4]));
							electionInformationVO.setValidVoters(commonMethodsUtilService.getLongValueForObject(objects[0]));
							electionInformationVO.setElectionYear(commonMethodsUtilService.getStringValueForObject(objects[1]));
							electionInformationVO.setElectionTypeId(commonMethodsUtilService.getLongValueForObject(objects[2]));
							electionInformationVO.setElectionType(commonMethodsUtilService.getStringValueForObject(objects[3]));
							listEvo.add(electionInformationVO);
							finalYearMap.put(Long.valueOf(electionInformationVO.getElectionYear()), listEvo);
						}
					}
				}
			}
			List<Object[]> earnedVotesList = null;
			if(locationTypeId != null && locationTypeId.longValue() !=5L && locationTypeId.longValue() !=7L && locationTypeId.longValue() !=6L ){
				earnedVotesList= candidateDAO.getElectionInformationLocationWiseEarnedVoterShare(electionYrs, locationTypeId, locationValue,electionScopeIds,null,subTypes,null,false);
				if(locationTypeId == 4l){
					List<Object[]>	earnedVotesListTmp= candidateDAO.getElectionInformationLocationWiseEarnedVoterShare(electionYrs, locationTypeId, locationValue,electionScopeIds,null,subTypes,null,true);
					if(commonMethodsUtilService.isListOrSetValid(earnedVotesListTmp)){
						earnedVotesList.addAll(earnedVotesListTmp);
					}
				}

				if(!commonMethodsUtilService.isListOrSetValid(earnedVotesList))
					earnedVotesList = new ArrayList<Object[]>();
					
				List<Object[]> tempearnedVotesList= candidateDAO.getElectionInformationLocationWiseEarnedVoterShare(electionYrs, locationTypeId, locationValue,electionScopeIds,null,subTypes,parliamentIdsList,false);
				if(commonMethodsUtilService.isListOrSetValid(tempearnedVotesList))
					earnedVotesList.addAll(tempearnedVotesList);
			}else{
				earnedVotesList= candidateDAO.getElectionInformationLocationWiseEarnedVoterShare(electionYrs, locationTypeId, locationValue,electionScopeIds,"lowLevels",subTypes,null,false);
				if(locationTypeId == 5l){
					List<Object[]>	earnedVotesListTmp= candidateDAO.getElectionInformationLocationWiseEarnedVoterShare(electionYrs, locationTypeId, locationValue,electionScopeIds,null,subTypes,null,true);
					if(commonMethodsUtilService.isListOrSetValid(earnedVotesListTmp)){
						earnedVotesList.addAll(earnedVotesListTmp);
					}
				}
			}
			
			Map<Long,Map<Long,ElectionInformationVO>> alliancedPartiesWithGroupIdMap = null;
			if(withAllaince != null && withAllaince.trim().equalsIgnoreCase("true"))
				alliancedPartiesWithGroupIdMap = locationWiseElectionInformationDetalsService.getSegregateAliancePartiesMap(subTypes,electionYrs,electionScopeIds);
			if(!commonMethodsUtilService.isMapValid(alliancedPartiesWithGroupIdMap))
				alliancedPartiesWithGroupIdMap = new HashMap<Long, Map<Long,ElectionInformationVO>>(0);
			
			for (Object[] objects : earnedVotesList) {
				List<ElectionInformationVO> polledVoList =finalYearMap.get(commonMethodsUtilService.getLongValueForObject(objects[5]));
				if(commonMethodsUtilService.isListOrSetValid(polledVoList)){
					for (ElectionInformationVO vo : polledVoList) {
						List<ElectionInformationVO> innerList = new ArrayList<ElectionInformationVO>();
						if(commonMethodsUtilService.getLongValueForObject(objects[6]).longValue()==vo.getElectionId().longValue()){
							innerList=vo.getList();
							if(!commonMethodsUtilService.isListOrSetValid(innerList)){
								innerList = new ArrayList<ElectionInformationVO>();
							}
							
							ElectionInformationVO innerVo = null;
							if(!partyIds.contains(commonMethodsUtilService.getLongValueForObject(objects[0]))){// for othrt parties
								if(!commonMethodsUtilService.isListOrSetValid(innerList)){
									innerVo = new ElectionInformationVO();
									innerVo.setPartyId(1887l);//otherparties
									innerVo.setPartyName("OTHERS");
									innerList.add(innerVo);
									vo.setList(innerList);
									partyMap.put(innerVo.getPartyId(), new ElectionInformationVO(innerVo.getPartyId(), "OTHERS"));
								}else{
									for (ElectionInformationVO tempInnervo : innerList) {
										if(tempInnervo.getPartyId().longValue()==1887l){
											innerVo=tempInnervo;break;
										}
									}
									if(innerVo == null){
										innerVo = new ElectionInformationVO();
										innerVo.setPartyId(1887l);//otherparties
										innerVo.setPartyName("OTHERS");
										innerList.add(innerVo);
										vo.setList(innerList);
										partyMap.put(innerVo.getPartyId(), new ElectionInformationVO(innerVo.getPartyId(), "OTHERS"));
									}
								}
							}else{//for selected parties
								for (ElectionInformationVO tempInnervo : innerList) {
									List<String> alliancePartyIdNameList  = findMatchedPartyId(alliancedPartiesWithGroupIdMap,tempInnervo.getElectionId(),tempInnervo.getPartyId());
									if(commonMethodsUtilService.isListOrSetValid(alliancePartyIdNameList)){
										Long alliancePartyId = Long.valueOf(alliancePartyIdNameList.get(0));
										if(alliancePartyId != null  && alliancePartyId.longValue()==commonMethodsUtilService.getLongValueForObject(objects[0]).longValue()){
											innerVo=tempInnervo;break;
										}
									}else if(tempInnervo.getPartyId().longValue()==commonMethodsUtilService.getLongValueForObject(objects[0]).longValue()){
										innerVo=tempInnervo;break;
									}
								}
								if(innerVo == null){// if party already not exist
									List<String> alliancePartyIdNameList  = findMatchedPartyId(alliancedPartiesWithGroupIdMap,commonMethodsUtilService.getLongValueForObject(objects[6]),commonMethodsUtilService.getLongValueForObject(objects[0]));
									if(commonMethodsUtilService.isListOrSetValid(alliancePartyIdNameList)){
										Long alliancePartyId = Long.valueOf(alliancePartyIdNameList.get(0));
										String alliancePartyName = alliancePartyIdNameList.get(1);
										
										for (ElectionInformationVO tempInnervo : innerList) {
											if(tempInnervo.getPartyId().longValue()==alliancePartyId.longValue()){
												innerVo=tempInnervo;break;
											}
										}
										if(innerVo == null){
											innerVo = new ElectionInformationVO();
											innerVo.setPartyId(alliancePartyId);
											innerVo.setPartyName(alliancePartyName);
											innerVo.setName(alliancePartyIdNameList.get(2));
											innerList.add(innerVo);
										}
									}else{
										innerVo = new ElectionInformationVO();
										innerVo.setPartyId(commonMethodsUtilService.getLongValueForObject(objects[0]));
										innerVo.setPartyName(commonMethodsUtilService.getStringValueForObject(objects[1]));
										innerList.add(innerVo);
									}
								}
								vo.setList(innerList);
								partyMap.put(innerVo.getPartyId(), new ElectionInformationVO(innerVo.getPartyId(), innerVo.getPartyName()));
							}
							
							innerVo.setEarnedVotes(innerVo.getEarnedVotes()+commonMethodsUtilService.getLongValueForObject(objects[2]));
							innerVo.setElectionId(commonMethodsUtilService.getLongValueForObject(objects[6]));
							innerVo.setElectionTypeId(commonMethodsUtilService.getLongValueForObject(objects[3]));					
							innerVo.setElectionType(commonMethodsUtilService.getStringValueForObject(objects[4]));
							innerVo.setElectionYear(commonMethodsUtilService.getStringValueForObject(objects[5]));
							String number =calculatePercentage(vo.getValidVoters(),innerVo.getEarnedVotes());
							innerVo.setLocationName(number);
						}
					}
				}
			}
			Map<Long,ElectionInformationVO> electionsMap = new TreeMap<Long, ElectionInformationVO>();
			if(commonMethodsUtilService.isMapValid(partyMap)){
				for (ElectionInformationVO partyVO : partyMap.values()) {
					ElectionInformationVO partyVO1 = new ElectionInformationVO();
					partyVO1.setPartyId(partyVO.getPartyId());
					partyVO1.setPartyName(partyVO.getPartyName());
					partyVO1.setName(partyVO.getName());
					
					for (Long year : finalYearMap.keySet()) {
						List<ElectionInformationVO> electionsList = finalYearMap.get(year);
						if(commonMethodsUtilService.isListOrSetValid(electionsList)){
							for (ElectionInformationVO electionVO : electionsList) {
								if(commonMethodsUtilService.isListOrSetValid(electionVO.getList())){
									for (ElectionInformationVO electionPartyVO : electionVO.getList()) {
										if(electionScopeIds.contains(electionVO.getElectionTypeId())){
											electionsMap.put(electionPartyVO.getElectionId(), new ElectionInformationVO(electionVO.getElectionYear(),electionPartyVO.getElectionId(),electionPartyVO.getElectionType()));
											if(partyVO.getPartyId().longValue() == electionPartyVO.getPartyId().longValue()){
												partyVO1.getList().add(electionPartyVO);
											}
										}
									}
								}
							}
						}
					}
					finalPartyList.add(partyVO1);
				}
			}
			
			List<Object[]> wonResultList=  null;
			if(locationTypeId != null && locationTypeId.longValue() !=5L && locationTypeId.longValue() !=7L && locationTypeId.longValue() !=6L ){
				 wonResultList= candidateDAO.getElectionInformationLocationWiseWonedCount(electionYrs, locationTypeId, locationValue, electionScopeIds, subTypes,null,false);
				 if(locationTypeId == 4l || locationTypeId == 10l){
					List<Object[]> wonResultListtemp = candidateDAO.getElectionInformationLocationWiseWonedCount(electionYrs, locationTypeId, locationValue, electionScopeIds, subTypes,null,true);
					 if(commonMethodsUtilService.isListOrSetValid(wonResultListtemp)){
						 wonResultList.addAll(wonResultListtemp);
					 }
				 }
				 if(!commonMethodsUtilService.isListOrSetValid(wonResultList))
					 wonResultList = new ArrayList<Object[]>();
					 
				List<Object[]> tempwonResultList = candidateDAO.getElectionInformationLocationWiseWonedCount(electionYrs, locationTypeId, locationValue, electionScopeIds, subTypes,parliamentIdsList,false);
				if(commonMethodsUtilService.isListOrSetValid(tempwonResultList))
					 wonResultList.addAll(tempwonResultList);
			}else if(locationTypeId == 5l){
				wonResultList = candidateDAO.getElectionInformationLocationWiseWonedCount(electionYrs, locationTypeId, locationValue, electionScopeIds, subTypes,null,true);
				
			}
			Map<Long,Map<Long,Long>> electionWiseWonSeatsMap = new HashMap<Long, Map<Long,Long>>(0);
			if(commonMethodsUtilService.isListOrSetValid(wonResultList)){
				for (Object[] param : wonResultList) {
					Long electonId = commonMethodsUtilService.getLongValueForObject(param[6]);
					Long partyId = commonMethodsUtilService.getLongValueForObject(param[0]);
					Long wonSeats = commonMethodsUtilService.getLongValueForObject(param[2]);
					Map<Long,Long> partyWiseWonSeatsMap = electionWiseWonSeatsMap.get(electonId);
					if(!commonMethodsUtilService.isMapValid(partyWiseWonSeatsMap)){
						partyWiseWonSeatsMap = new HashMap<Long, Long>(0);
					}
					if(partyIds.contains(partyId)){
						List<String> alliancePartyIdNameList  = findMatchedPartyId(alliancedPartiesWithGroupIdMap,electonId,partyId);
						if(commonMethodsUtilService.isListOrSetValid(alliancePartyIdNameList)){
							Long alliancePartyId = Long.valueOf(alliancePartyIdNameList.get(0));
							if(partyWiseWonSeatsMap.get(alliancePartyId) != null)
								wonSeats = wonSeats+partyWiseWonSeatsMap.get(alliancePartyId);
							
							partyWiseWonSeatsMap.put(alliancePartyId, wonSeats);
						}else{
							partyWiseWonSeatsMap.put(partyId, wonSeats);
						}
					}else{
						Long tempWonSeats = partyWiseWonSeatsMap.get(1887l);
						if(tempWonSeats == null)
							tempWonSeats = 0L;
						tempWonSeats = tempWonSeats+wonSeats;
						partyWiseWonSeatsMap.put(1887l, tempWonSeats);
					}
					electionWiseWonSeatsMap.put(electonId, partyWiseWonSeatsMap);
				}
			}
			
			if(commonMethodsUtilService.isListOrSetValid(finalPartyList)){
				//List<ElectionInformationVO> returnPartyList = new ArrayList<ElectionInformationVO>();
				Map<Long,Map<String,ElectionInformationVO>> electionPartyMap = new HashMap<Long, Map<String,ElectionInformationVO>>(0);
				Set<String> PartyList = new HashSet<String>(0);

				for (ElectionInformationVO partyVO : finalPartyList) {
					if(commonMethodsUtilService.isListOrSetValid(partyVO.getList())){
						// the party name should be same in all partyVO.list() , otherwise we are considering as a new party.
						for (ElectionInformationVO innerPartyVO : partyVO.getList()) {
							Map<String,ElectionInformationVO> allPartysMap = electionPartyMap.get(innerPartyVO.getElectionId());
							if(!commonMethodsUtilService.isMapValid(allPartysMap))
								allPartysMap = new HashMap<String, ElectionInformationVO>(0);
							allPartysMap.put(innerPartyVO.getPartyName(), innerPartyVO);
							electionPartyMap.put(innerPartyVO.getElectionId(), allPartysMap);
							PartyList.add(innerPartyVO.getPartyName());
						}
					}
				}
				
				if(commonMethodsUtilService.isMapValid(electionPartyMap)){
					finalPartyList.clear();
					for (Long electionId : electionPartyMap.keySet()) {
						Map<String,ElectionInformationVO> allPartysMap = electionPartyMap.get(electionId);
						if(commonMethodsUtilService.isMapValid(allPartysMap)){
							for (String partyName : allPartysMap.keySet()) {
								ElectionInformationVO partyVO = allPartysMap.get(partyName);
								ElectionInformationVO returnVO = getMatchedElectionInformationVOByPartyName(finalPartyList,partyName);
								if(returnVO == null){
									if(partyVO != null){
										ElectionInformationVO mainPartyVO = new ElectionInformationVO();
										mainPartyVO.setPartyId(partyVO.getPartyId());
										mainPartyVO.setPartyName(partyVO.getPartyName());
										mainPartyVO.setName(partyVO.getName());
										mainPartyVO.setElectionId(partyVO.getElectionId());
										mainPartyVO.setElectionYear(partyVO.getElectionYear());
										
										mainPartyVO.getList().add(partyVO);
										finalPartyList.add(mainPartyVO);
									}
								}else{
									returnVO.getList().add(partyVO);
								}
							}
						}
					}
				}
				
				for (ElectionInformationVO partyVO : finalPartyList) {
					List<ElectionInformationVO> electionList = new ArrayList<ElectionInformationVO>(0);
					if(commonMethodsUtilService.isMapValid(electionsMap)){
						List<Long> availableIds=new ArrayList<Long>(0);
						for (ElectionInformationVO vo : partyVO.getList()) {
							availableIds.add(vo.getElectionId());
							
							Map<Long,Long> partyWiseWonSeatsMap = electionWiseWonSeatsMap.get(vo.getElectionId());
							if(commonMethodsUtilService.isMapValid(partyWiseWonSeatsMap)){
								vo.setWonSeatsCount(partyWiseWonSeatsMap.get(vo.getPartyId()));
							}
							if(vo.getWonSeatsCount() == null)
								vo.setWonSeatsCount(0L);
							electionList.add(vo);
						}
						
						for (Long electionId : electionsMap.keySet()) {
							if(!availableIds.contains(electionId)){
								ElectionInformationVO vo = electionsMap.get(electionId);
								Map<Long,Long> partyWiseWonSeatsMap = electionWiseWonSeatsMap.get(vo.getElectionId());
								if(commonMethodsUtilService.isMapValid(partyWiseWonSeatsMap)){
									vo.setWonSeatsCount(partyWiseWonSeatsMap.get(vo.getPartyId()));
								}
								if(vo.getWonSeatsCount() == null)
									vo.setWonSeatsCount(0L);
								electionList.add(vo);
							}
						}
					}
					
					if(commonMethodsUtilService.isListOrSetValid(electionList)){
						partyVO.getList().clear();
						partyVO.setList(electionList);
					}
					
					Collections.sort(partyVO.getList(), new Comparator<ElectionInformationVO>() {
						public int compare(ElectionInformationVO o1,ElectionInformationVO o2) {
							return Long.valueOf(o2.getElectionId()).compareTo(Long.valueOf(o1.getElectionId()));
						}
					});
				}
			}
			if(finalPartyList != null && partyIds != null && finalPartyList.size() ==2 && partyIds.size()==1 ){
				for(int i=0; i<finalPartyList.size() ; i++){
					if(finalPartyList.get(i) !=null && finalPartyList.get(i).getPartyId().longValue() == 1887L){
						finalPartyList.remove(i);
					}
				}
			}
			
			for (ElectionInformationVO evo : finalPartyList) {
				Collections.sort(evo.getList(), new Comparator<ElectionInformationVO>() {
					@Override
					public int compare(ElectionInformationVO o1,ElectionInformationVO o2) {
						int val;
						 	val= (o2.getElectionYear()).compareTo(o1.getElectionYear());
						 if(val ==0)
							 val= (o1.getElectionType()).compareTo(o2.getElectionType());
						 return val;
					}
				});
			}
			
			return finalPartyList;
		}catch(Exception e){
			e.printStackTrace();
			Log.error("Exception raised in getElectionInformationLocationWiseVoterShare method of LocationDashboardService"+e);
			return null;
		}
	}
	
	public ElectionInformationVO getMatchedElectionInformationVOByPartyName(List<ElectionInformationVO> partyVOList , String partyName){
		ElectionInformationVO returnVO =null;
		try {
			if(commonMethodsUtilService.isListOrSetValid(partyVOList) && partyName != null && partyName.trim().length()>0){
				for (ElectionInformationVO vo : partyVOList) {
					if(vo != null && vo.getPartyName() != null && vo.getPartyName().trim().equalsIgnoreCase(partyName.trim()))
						return vo;
				}
			}
		} catch (Exception e) {
			Log.error("Exception raised in getElectionInformationLocationWiseVoterShare method of LocationDashboardService"+e);
		}
		return returnVO;
	}
	public List<String> findMatchedPartyId(Map<Long,Map<Long,ElectionInformationVO>> alliancedPartiesWithGroupId,Long electionId,Long partyId){
		List<String> groupIdAndName = null;
		   if(alliancedPartiesWithGroupId != null && alliancedPartiesWithGroupId.size() >0){
		    for (Entry<Long,Map<Long, ElectionInformationVO>> electionEntry : alliancedPartiesWithGroupId.entrySet()) {
		    	Long innerElectionId = electionEntry.getKey();
		    	if(innerElectionId.equals(electionId)){
		    		Map<Long, ElectionInformationVO> groupIdMap = electionEntry.getValue();
		    		 if(groupIdMap != null && groupIdMap.size() >0){
		    		    for (Entry<Long, ElectionInformationVO>  groupEntry : groupIdMap.entrySet()) {
		    		    	//Long innerGroupId = groupEntry.getKey();
		    		    	ElectionInformationVO electionInformationVO = groupEntry.getValue();
		    		    	List<Long> partyIdsList = electionInformationVO.getIdsList();
		    		    	if(partyIdsList.contains(partyId)){
		    		    		groupIdAndName = new ArrayList<String>();
		    		    		//String prefixedGroupId ="999999"+innerGroupId.toString();
		    		    		String prefixedGroupId =partyIdsList.get(0).toString();
		    		    		groupIdAndName.add(prefixedGroupId);
		    		    		groupIdAndName.add(electionInformationVO.getPartyName());
		    		    		List<String> partNamesList=electionInformationVO.getPartyNamesList();
		    		    		String commaSeparatedNames="";
		    		    		for(String partyName:partNamesList){
		    		    			commaSeparatedNames+=partyName+", ";
		    		    		}
		    		    		groupIdAndName.add(commaSeparatedNames.substring(0, commaSeparatedNames.length()-2));
		    		    		String commaSeparatedPartyIds="";
		    		    		for(Long partyIds:partyIdsList){
		    		    			commaSeparatedPartyIds+=partyIds.toString()+",";
		    		    		}
		    		    		groupIdAndName.add(commaSeparatedPartyIds.substring(0, commaSeparatedPartyIds.length()-1));
		    		    		return groupIdAndName;
		    		    	}
		    		     }
		    		   }
		    	}
		    }
		   }
		return groupIdAndName;
	}

	public String calculatePercentage(Long totalVoters,Long count)
	{
		try{
			if(totalVoters != null && totalVoters.longValue() > 0l && count != null && count.longValue()>0L)
			  return (new BigDecimal((count * 100.0)/totalVoters.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
			else{
				return "0";
			}
			
		}catch (Exception e) {
			LOG.error("Exception Occured in calculatePercentage() method, Exception - ",e);
		}
		return null;
	}
	
	/* @param String fromDateStr
	 * @param String toDateStr
	 * @param Long locationId
	 * @param Long locationValue
	 * @author K.Nandhini
	 * @return List<List<GrivenceStatusVO>> we have two lists in final list 1.Grivence counts(Govt,party,welfare) 2.Trust counts
	 * (non-Javadoc)
	 * @see com.itgrids.core.api.service.ILocationDashboardService#getGrivenceTrustStatusCounts(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long)
	 */

	public List<GrivenceStatusVO> getGrivenceDetails(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year) {
		List<GrivenceStatusVO> finalList = new ArrayList<GrivenceStatusVO>();
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			// Here converting stirng to date formatte
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			Map<String,GrivenceStatusVO> grievanceStatusMap = new LinkedHashMap<String, GrivenceStatusVO>(0);
			//0-consId,1-Status,2-typeOfIssue,3-count
			List<Object[]> grivenceTrustList = insuranceStatusDAO.getGrivenceTrustStatusCounts(fromDate, toDate,locationTypeId,locationValues,year,Long.valueOf(year));
			List<String> statusList = insuranceStatusDAO.getGrivenceStatus();
			if(grivenceTrustList!=null){
				for (Object[] objects : grivenceTrustList) {
					if(objects[2].toString().trim().equalsIgnoreCase("Govt") || objects[2].toString().trim().equalsIgnoreCase("Party")  || 
							objects[2].toString().trim().equalsIgnoreCase("Welfare")){
						
						if(!commonMethodsUtilService.isMapValid(grievanceStatusMap) && commonMethodsUtilService.isListOrSetValid(statusList)){
							for (String status : statusList) {
								GrivenceStatusVO vo = new GrivenceStatusVO();
								vo.setName(status.toUpperCase());
								vo.setCount(0L);
								grievanceStatusMap.put(status.toUpperCase(), vo);
							}
						}
						GrivenceStatusVO vo = grievanceStatusMap.get(commonMethodsUtilService.getStringValueForObject(objects[1]).toUpperCase());
						if(vo != null){
							vo.setCount(vo.getCount()+commonMethodsUtilService.getLongValueForObject(objects[3]));
						}
					}
				}
				
			}
				//IssueType Counts Logic
				Map<String,GrivenceStatusVO> partyIssueMap = new LinkedHashMap<String, GrivenceStatusVO>(0);
				Map<String,GrivenceStatusVO> welfareIssueMap = new LinkedHashMap<String, GrivenceStatusVO>(0);
				Map<String,GrivenceStatusVO> govtIssueMap = new LinkedHashMap<String, GrivenceStatusVO>(0);
				List<Object[]> issueTypeCuntsList = insuranceStatusDAO.getGrivenceIssueTypeCounts(fromDate, toDate,locationTypeId,locationValues,year,Long.valueOf(year));
				List<Object[]> tpeOfIssuesList = insuranceStatusDAO.getAllTypeOfIssues();
				if(commonMethodsUtilService.isListOrSetValid(tpeOfIssuesList)){
					for (Object[] param : tpeOfIssuesList) {
						GrivenceStatusVO vo = new GrivenceStatusVO();
						vo.setName(commonMethodsUtilService.getStringValueForObject(param[0]));
						//vo.setGrivenceType(commonMethodsUtilService.getStringValueForObject(param[0]));
						vo.setCount(0L);
						if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("party"))
							partyIssueMap.put(commonMethodsUtilService.getStringValueForObject(param[0]), vo);
						else if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("welfare"))
							welfareIssueMap.put(commonMethodsUtilService.getStringValueForObject(param[0]), vo);
						else if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("Govt"))
							govtIssueMap.put(commonMethodsUtilService.getStringValueForObject(param[0]), vo);
					}
				}
				
			//  Here set the IssueType Cunt Fr Party,Welfare,Govt
				if(commonMethodsUtilService.isListOrSetValid(issueTypeCuntsList)){
					for (Object[] objects : issueTypeCuntsList) {
						if(objects[2] != null && objects[2].toString().trim().equalsIgnoreCase("party")){
							GrivenceStatusVO vo = partyIssueMap.get(commonMethodsUtilService.getStringValueForObject(objects[1]));
							if(vo != null){
								vo.setCount(vo.getCount()+commonMethodsUtilService.getLongValueForObject(objects[3]));
							}
						}else if(objects[2] != null && objects[2].toString().trim().equalsIgnoreCase("welfare")){
							GrivenceStatusVO vo = welfareIssueMap.get(commonMethodsUtilService.getStringValueForObject(objects[1]));
							if(vo != null){
								vo.setCount(vo.getCount()+commonMethodsUtilService.getLongValueForObject(objects[3]));
							}
						}else if(objects[2] != null && objects[2].toString().trim().equalsIgnoreCase("govt")){
							GrivenceStatusVO vo = govtIssueMap.get(commonMethodsUtilService.getStringValueForObject(objects[1]));
							if(vo != null){
								vo.setCount(vo.getCount()+commonMethodsUtilService.getLongValueForObject(objects[3]));
							}
						}
					}
				}
				if(commonMethodsUtilService.isMapValid(grievanceStatusMap)){
					GrivenceStatusVO grivenceStatusCount = new GrivenceStatusVO();
					grivenceStatusCount.setGrivenceType("Grivence");
					for (String status : grievanceStatusMap.keySet()) {
						grivenceStatusCount.getSubList().add(grievanceStatusMap.get(status));
					}
					finalList.add(grivenceStatusCount);
				}
				if(commonMethodsUtilService.isMapValid(partyIssueMap)){
					GrivenceStatusVO partyIssuesCount = new GrivenceStatusVO();
					partyIssuesCount.setGrivenceType("Party");
					for (String issueType : partyIssueMap.keySet()) {
						partyIssuesCount.getSubList().add(partyIssueMap.get(issueType));
					}
					finalList.add(partyIssuesCount);
				}
				if(commonMethodsUtilService.isMapValid(welfareIssueMap)){
					GrivenceStatusVO welfareIssuesCount = new GrivenceStatusVO();
					welfareIssuesCount.setGrivenceType("Welfare");
					for (String issueType : welfareIssueMap.keySet()) {
						welfareIssuesCount.getSubList().add(welfareIssueMap.get(issueType));
					}
					finalList.add(welfareIssuesCount);
				}
				if(commonMethodsUtilService.isMapValid(govtIssueMap)){
					GrivenceStatusVO govtIssuesCount = new GrivenceStatusVO();
					govtIssuesCount.setGrivenceType("Govt");
					for (String issueType : govtIssueMap.keySet()) {
						govtIssuesCount.getSubList().add(govtIssueMap.get(issueType));
					}
					finalList.add(govtIssuesCount);
				}
				if(commonMethodsUtilService.isListOrSetValid(finalList)){
					for (GrivenceStatusVO finalVO : finalList) {
						if(finalVO != null){
							for (GrivenceStatusVO vo : finalVO.getSubList()) {
								finalVO.setCount(finalVO.getCount()+vo.getCount());
								//vo.setPerc(Double.valueOf(vo.getCount())*100/Double.valueOf(finalVO.getCount()));
							}
							for (GrivenceStatusVO vo : finalVO.getSubList()) {
								if(vo.getCount() != null && vo.getCount().longValue() >0L && finalVO.getCount() != null && finalVO.getCount().longValue() > 0l)
									vo.setPerc(new BigDecimal(Double.valueOf(vo.getCount())*100.00/Double.valueOf(finalVO.getCount())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
								else
									vo.setPerc("0.00");
							}
						}
						//finalVO.setCount(finalVO.getCount()+vo.getCount());
					}
				}
				
		}catch(Exception e){
			Log.error("Exception raised at grivence and trust counts service"+e);
		}
		return finalList;
	}
	/* @param String fromDateStr
	 * @param String toDateStr
	 * @param Long locationId
	 * @param Long locationValue
	 * @author K.Nandhini
	 * @return List<List<GrivenceStatusVO>> we have two lists in final list 
	 * (non-Javadoc)
	 * @see com.itgrids.core.api.service.ILocationDashboardService#getLevelWiseGrievanceCounts(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long)
	 */
	
	public List<GrivenceStatusVO> getLevelWiseGrievanceCounts(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year){
		List<GrivenceStatusVO> lvelCuntList = new ArrayList<GrivenceStatusVO>(0);
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			// Here converting stirng to date formatte
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			
			Long locationId = 0l;
			List<Object[]> list = null;
			Map<Long,GrivenceStatusVO> otherNameMap = new HashMap<Long, GrivenceStatusVO>(0);
			//Get Below locationValues By LevelId
			if(locationTypeId != null && locationTypeId.longValue() >0l && locationTypeId.longValue() == 2L)
				list = districtDAO.getDistrictIdsByState(locationValues);
			else if(locationTypeId != null && locationTypeId.longValue() >0l && locationTypeId.longValue() == 3L)
				list = constituencyDAO.getConstituencyListByDistrictId(locationValues);
			else if(locationTypeId != null && locationTypeId.longValue() >0l && locationTypeId.longValue() == 4L){
				list = boothDAO.getTehsilAndLEBIdsByConstituency(locationValues,24L);
				if(list != null && !list.isEmpty()){
					List<Object[]> muncipList = assemblyLocalElectionBodyDAO.getMuuncipalityByConstituency(locationValues);
					list.addAll(muncipList);
				}
			}else if(locationTypeId != null && locationTypeId.longValue() >0l && locationTypeId.longValue() == 10L){
				list = parliamentAssemblyDAO.getConsIdsByParliamntsIds(locationValues);
			}else if(locationTypeId != null && locationTypeId.longValue() >0l && locationTypeId.longValue() == 5L){
				list = boothDAO.getPanchayatByMandal(locationValues,24L);
			}else if(locationTypeId != null && locationTypeId.longValue() >0l && locationTypeId.longValue() == 7L){
				list = boothDAO.getMunciplaitiesByLeb(locationValues,24L);
			}
				
			
			//List<Object[]> list = insuranceStatusDAO.getLevelValuesByLevel(locationTypeId,locationValues);
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (Object[] param : list) {
					GrivenceStatusVO locationVO = new GrivenceStatusVO();
					//if(param[0] != null){
						locationVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					//}else if(param[2] != null){
						/*locationVO.setId(commonMethodsUtilService.getLongValueForObject(param[2]));
						locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[3]));*/
					//}
					lvelCuntList.add(locationVO);	
				}
			}
			
			
			//Set Grievance Counts
			List<Object[]>  grievancesCunt = insuranceStatusDAO.getGrievanceTypeCounts(fromDate, toDate,locationTypeId,locationValues,year,Long.valueOf(year));
			if(commonMethodsUtilService.isListOrSetValid(grievancesCunt)){
				for (Object[] param : grievancesCunt) {
					if(param[0] != null)
						locationId = commonMethodsUtilService.getLongValueForObject(param[0]);
					else if(param[3] != null)
						locationId = commonMethodsUtilService.getLongValueForObject(param[3]);
					GrivenceStatusVO vo = getMatchedLocationVO(lvelCuntList,locationId);
					if(vo != null){
						if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("party"))
							vo.setPartyCount(vo.getPartyCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
						else if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("govt"))
							vo.setGovtCount(vo.getGovtCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
						else if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("welfare"))
							vo.setWelfareCount(vo.getWelfareCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
					}/*else{
						GrivenceStatusVO otherVO = otherNameMap.get(0L);
						if(otherVO == null){
							otherVO = new GrivenceStatusVO();
							if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("party"))
								otherVO.setPartyCount(otherVO.getPartyCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
							else if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("govt"))
								otherVO.setGovtCount(otherVO.getGovtCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
							else if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("welfare"))
								otherVO.setWelfareCount(otherVO.getWelfareCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
							otherNameMap.put(0L, otherVO);
						}else{
							if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("party"))
								otherVO.setPartyCount(otherVO.getPartyCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
							else if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("govt"))
								otherVO.setGovtCount(otherVO.getGovtCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
							else if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("welfare"))
								otherVO.setWelfareCount(otherVO.getWelfareCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
						}
					}*/
				}
			}
			
			//Set Insurance Counts
			List<Object[]>  insuranceCunt = insuranceStatusDAO.getInsuranceTypeCounts(fromDate, toDate,locationTypeId,locationValues,year,Long.valueOf(year));
			if(commonMethodsUtilService.isListOrSetValid(insuranceCunt)){
				for (Object[] param : insuranceCunt) {
					if(param[0] != null)
						locationId = commonMethodsUtilService.getLongValueForObject(param[0]);
					else if(param[3] != null)
						locationId = commonMethodsUtilService.getLongValueForObject(param[3]);
					GrivenceStatusVO vo = getMatchedLocationVO(lvelCuntList,locationId);
					if(vo != null){
						if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("Death"))
							vo.setDeathCount(vo.getDeathCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
						else if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("Hospitalization"))
							vo.setHosptalCount(vo.getHosptalCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
					}/*else{
						GrivenceStatusVO otherVO = otherNameMap.get(0L);
						if(otherVO == null){
							otherVO = new GrivenceStatusVO();
							if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("Death"))
								otherVO.setDeathCount(otherVO.getDeathCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
							else if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("Hospitalization"))
								otherVO.setHosptalCount(otherVO.getHosptalCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
							otherNameMap.put(0L, otherVO);
						}else{
							if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("Death"))
								otherVO.setDeathCount(otherVO.getDeathCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
							else if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("Hospitalization"))
								otherVO.setHosptalCount(otherVO.getHosptalCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
						}
					}*/
				}
			}
			
			//Set NTR Trust Counts
			List<Object[]>  trustCunt = insuranceStatusDAO.getNTRTrustTypeCounts(fromDate, toDate,locationTypeId,locationValues,year,Long.valueOf(year));
			if(commonMethodsUtilService.isListOrSetValid(trustCunt)){
				for (Object[] param : trustCunt) {
					if(param[0] != null)
						locationId = commonMethodsUtilService.getLongValueForObject(param[0]);
					else if(param[3] != null)
						locationId = commonMethodsUtilService.getLongValueForObject(param[3]);
					GrivenceStatusVO vo = getMatchedLocationVO(lvelCuntList,locationId);
					if(vo != null){
						if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("Seat"))
							vo.setSeatCount(vo.getSeatCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
						else if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("Fee Concession"))
							vo.setFeeConsCount(vo.getFeeConsCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
					}/*else{
						GrivenceStatusVO otherVO = otherNameMap.get(0L);
						if(otherVO == null){
							otherVO = new GrivenceStatusVO();
							if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("Seat"))
								otherVO.setSeatCount(otherVO.getSeatCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
							else if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("Fee Concession"))
								otherVO.setFeeConsCount(otherVO.getFeeConsCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
							otherNameMap.put(0L, otherVO);
						}else{
							if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("Seat"))
								otherVO.setSeatCount(otherVO.getSeatCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
							else if(param[1] != null && param[1].toString().trim().equalsIgnoreCase("Fee Concession"))
								otherVO.setFeeConsCount(otherVO.getFeeConsCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
						}
					}*/
				}
			}
			
			
		} catch (Exception e) {
			Log.error("Exception raised at getLevelWiseGrievanceCounts service"+e);
		}
		return lvelCuntList;
	}
	
	public GrivenceStatusVO getMatchedLocationVO(List<GrivenceStatusVO> list,Long id){
		try {
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (GrivenceStatusVO grivenceStatusVO : list) {
					if(grivenceStatusVO.getId().longValue() == id.longValue()){
						return grivenceStatusVO;
					}
				}
			}
		} catch (Exception e) {
			Log.error("Exception raised at getMatchedVO service"+e);
		}
		return null;
	}
	public NominatedPostDetailsVO getMatchedVO2(List<NominatedPostDetailsVO> list,Long id){
		try{
			if(list != null && list.size() > 0){
				for(NominatedPostDetailsVO param : list){
					if(param.getId().longValue() == id){
						return param;
					}
				}
			}
		}catch(Exception e){
			Log.error("Exception raised in getMatchedVOForCadreId method of LocationDashboardService"+e);
		}
		return null;
	}
/**
   * @author babu kurakula <href:kondababu.kurakul@itgrids.com>
	 * @param levelId,List<Long> levelVals,fromDateStr,toDateStr,year
	 * @Description :this service used for get the loxcation wise Insurance deatils
	 *  @since 6-oct-2017
	 *  @return :List<GrivenceStatusVO> 
	 */
public List<GrivenceStatusVO> getConstituencyWiseInsuranceWiseIssueTypeCounts(String fromDateStr, String toDateStr, Long locationTypeId,List<Long> locationValues,String year){
	List<GrivenceStatusVO> finalList=new ArrayList<GrivenceStatusVO>();
	try{//cal the Existing service to get the insurance Status count 
		GrivenceStatusVO InsuranceStatusVo= getLocationWiseInsuranceStatusCounts( fromDateStr,toDateStr,locationTypeId,locationValues,year);
		finalList.add(InsuranceStatusVo);// add the insurance Status counts vo
		Date fromDate = null;
		Date toDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// Here converting stirng to date formatte 
		if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
			fromDate = sdf.parse(fromDateStr);
			toDate = sdf.parse(toDateStr);
		}
		List<Object[]> allGrivenceObjs=insuranceStatusDAO.getAllInsuranceGrievanceTpes();
		//here we preparing the templet 
		Map<String,List<GrivenceStatusVO>> templetMap=new HashMap<String,List<GrivenceStatusVO>>(0);
		if(allGrivenceObjs !=null && allGrivenceObjs.size() >0){
			for(Object[] param:allGrivenceObjs){
					String subGrivenceType=commonMethodsUtilService.getStringValueForObject(param[1]);
					if(subGrivenceType.equalsIgnoreCase("Road Accident") || subGrivenceType.equalsIgnoreCase("Animal Accident")){
						 String grivenceType=commonMethodsUtilService.getStringValueForObject(param[0]);
						 List<GrivenceStatusVO> listVo=templetMap.get(grivenceType);
						 if(listVo !=null){
							GrivenceStatusVO newVo=new GrivenceStatusVO();
							newVo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
							newVo.setCount(0L);
							listVo.add(newVo);
						 }else{
							List<GrivenceStatusVO> newListVo=new ArrayList<GrivenceStatusVO>();
							GrivenceStatusVO newVo1=new GrivenceStatusVO();
							newVo1.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
							newVo1.setCount(0L);
							newListVo.add(newVo1);
							templetMap.put(grivenceType,newListVo);
						}
				 }
			}// add the other Accident Vo because we need set other Accident count is show lost position
			for(Entry<String,List<GrivenceStatusVO>>  tempMap:templetMap.entrySet()){
				GrivenceStatusVO otherVo=new GrivenceStatusVO();
				otherVo.setName("Other Accident");
				otherVo.setCount(0L);
				tempMap.getValue().add(otherVo);
			}
		}
		// locationId 1-lacation name 2-issuType 3-subject 4-count
		List<Object[]> insuranceIssueTypeCountsObj=	insuranceStatusDAO.getConstituencyWiseInsuranceWiseIssueTypeCounts(fromDate,toDate,locationTypeId,locationValues, year,Long.valueOf(year));
		Map<String,List<GrivenceStatusVO>> isuueTypeCountVoMap=new HashMap<String,List<GrivenceStatusVO>>(0);
		if(insuranceIssueTypeCountsObj !=null && insuranceIssueTypeCountsObj.size() >0){
			//here prepare the map issueType is key and List<GrivenceStatusVO> as value in the vo set the subject and count
			for(Object[] param:insuranceIssueTypeCountsObj){
				String issueType=commonMethodsUtilService.getStringValueForObject(param[2]);
				List<GrivenceStatusVO> listVo=isuueTypeCountVoMap.get(issueType);
				if(listVo !=null){
					GrivenceStatusVO newVo=new GrivenceStatusVO();
					newVo.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
					newVo.setCount(commonMethodsUtilService.getLongValueForObject(param[4]));
					listVo.add(newVo);
					isuueTypeCountVoMap.put(issueType,listVo);
				}else{
					List<GrivenceStatusVO> newListVo=new ArrayList<GrivenceStatusVO>();
					GrivenceStatusVO newVo1=new GrivenceStatusVO();
					newVo1.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
					newVo1.setCount(commonMethodsUtilService.getLongValueForObject(param[4]));
					newListVo.add(newVo1);
					isuueTypeCountVoMap.put(issueType,newListVo);
				}
			}// here iterating isuueTypeCountVoMap map 
			//to set the issueType set to VO and and add all subject and counts to that VO in subList 
			Map<String,List<GrivenceStatusVO>> finlResultMap=new HashMap<String,List<GrivenceStatusVO>>(0);
			for(Entry<String,List<GrivenceStatusVO>> map :isuueTypeCountVoMap.entrySet()){
				Long totalOtherCount=0L;
				String isuueTpeStr=map.getKey();
				List<GrivenceStatusVO> subList=new ArrayList<GrivenceStatusVO>();
				for(GrivenceStatusVO subjectAndCountVo:map.getValue()){//iterating List
					 if(subjectAndCountVo.getName().trim().equalsIgnoreCase("Road Accident") ||subjectAndCountVo.getName().trim().equalsIgnoreCase("Animal Accident") ){
						 subList.add(subjectAndCountVo);// Road and Animal Accident add to subList			
					 }else{//remaing count add to  variable
						 totalOtherCount=totalOtherCount+ subjectAndCountVo.getCount();
					 }
				}
				GrivenceStatusVO otherAccidentVo=new GrivenceStatusVO();
				otherAccidentVo.setName("Other Accident");
				otherAccidentVo.setCount(totalOtherCount);//set total OtherCount
				subList.add(otherAccidentVo);
				finlResultMap.put(isuueTpeStr,subList);
			}//here we prepare the List of vos to sent to UI 
			//iterating  templetMap map set the finalResult map values set to templet map vos
			for(Entry<String ,List<GrivenceStatusVO>> entry2: templetMap.entrySet()){
				String subIssue=entry2.getKey();
				GrivenceStatusVO finalVo=new GrivenceStatusVO();
				finalVo.setGrivenceType(subIssue);
				for(GrivenceStatusVO vo:entry2.getValue()){
					GrivenceStatusVO matchedVo=grivenceStatusVOMatchedVo(finlResultMap.get(subIssue),vo.getName());
					if(matchedVo !=null){
						vo.setCount(matchedVo.getCount());
					}
					finalVo.getSubList().add(vo);
				}
				finalList.add(finalVo);
			}//here calculate the percrntage
				if(commonMethodsUtilService.isListOrSetValid(finalList)){
					for (GrivenceStatusVO finalVO : finalList) {
						if(finalVO != null){
							for (GrivenceStatusVO vo : finalVO.getSubList()) {
								finalVO.setCount(finalVO.getCount()+vo.getCount());
							}
							for (GrivenceStatusVO vo : finalVO.getSubList()) {
								if(vo.getCount() != null && vo.getCount().longValue() >0L && finalVO.getCount() != null && finalVO.getCount().longValue() > 0l)
									vo.setPerc(new BigDecimal(Double.valueOf(vo.getCount())*100.00/Double.valueOf(finalVO.getCount())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
								else
									vo.setPerc("0.00");
							}
						}
					}
			}
		}
	}catch(Exception e){
		Log.error("Exception raised in getConstituencyWiseInsuranceWiseIssueTypeCounts method of LocationDashboardService"+e);
	}
	return finalList;
	}
	/**
	* @author babu kurakula <href:kondababu.kurakul@itgrids.com>
	* @param levelId,List<Long> levelVals,fromDateStr,toDateStr,year
	* @Description :this service used for get the location wise issue type trust deatils
	*  @since 7-oct-2017
	*  @return :List<GrivenceStatusVO> 
	*/
	public List<GrivenceStatusVO> getLocationWiseGrivenceTrustIssueTypesCounts(String fromDateStr, String toDateStr, Long locationTypeId,List<Long> locationValues,String year) {
		List<GrivenceStatusVO> finalList=new ArrayList<GrivenceStatusVO>();
		try{    //call the existng service getGrivenceTrustStatusCounts get trust status count deatils
			List<GrivenceStatusVO> trustCompletVosList=getGrivenceTrustStatusCounts(fromDateStr,toDateStr,locationTypeId,locationValues,year);
			if(trustCompletVosList !=null && trustCompletVosList.size() >0){
				for(GrivenceStatusVO trustVo:trustCompletVosList){
					if(trustVo.getGrivenceType().equalsIgnoreCase("NTR Trust")){
						finalList.add(trustVo);// set the  Trust deatils vo to finallist
					}
				}
			}
			// Here converting stirng to date formatte 
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}	
			Map<String,List<GrivenceStatusVO>> PurposeAndSuppodrtMap=new HashMap<String,List<GrivenceStatusVO>>(0);
			List<Object[]> objcts=insuranceStatusDAO.getAllTrustIssueTypes();
			if(objcts !=null && objcts.size() >0){
				for(Object[] obj:objcts){
					String purpose=commonMethodsUtilService.getStringValueForObject(obj[0]);
					String supportFor=commonMethodsUtilService.getStringValueForObject(obj[1]);
					if(purpose !=null && purpose.length() >0 &&supportFor !=null && supportFor.length() > 0){
						List<GrivenceStatusVO> voList= PurposeAndSuppodrtMap.get(purpose);
						if(voList!=null){
							//here support_for and count set to VO and add to existing  list
							GrivenceStatusVO vo=new GrivenceStatusVO();
							vo.setName(supportFor);//support_for
							vo.setCount(0L);//count
							voList.add(vo);
						}else{
							List<GrivenceStatusVO> newList=new ArrayList<GrivenceStatusVO>(0);
							//here support_for and count set to VO and add to new  list
							GrivenceStatusVO vo1= new GrivenceStatusVO();
							vo1.setName(supportFor);
							vo1.setCount(0L);
							newList.add(vo1);
							PurposeAndSuppodrtMap.put(purpose, newList);//push support_purpose and list into map
						}
					}
				}
			}
			//0-locationId  1- support_for  2- support_purpose 3-counts
			List<Object[]> trustIssueTypeCountsObjs=insuranceStatusDAO.getLocationWiseGrivenceTrustIssueTypesCounts( fromDate,  toDate,  locationTypeId,locationValues, year,Long.valueOf(year));
				if(trustIssueTypeCountsObjs !=null && trustIssueTypeCountsObjs.size()>0){
				Map<String,List<GrivenceStatusVO>> isuueTypeCountVoMap=new HashMap<String,List<GrivenceStatusVO>>(0);
				// here prepre the map support_purpose key and  and List vos as values
					for(Object[] param:trustIssueTypeCountsObjs){
						String issueType=commonMethodsUtilService.getStringValueForObject(param[2]);
						List<GrivenceStatusVO> voList= isuueTypeCountVoMap.get(issueType);
						if(voList!=null){
							//here support_for and count set to VO and add to existing  list
							GrivenceStatusVO vo=new GrivenceStatusVO();
							vo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));//support_for
							vo.setCount(commonMethodsUtilService.getLongValueForObject(param[3]));//count
							voList.add(vo);
						}else{
							List<GrivenceStatusVO> newList=new ArrayList<GrivenceStatusVO>(0);
							//here support_for and count set to VO and add to new  list
							GrivenceStatusVO vo1= new GrivenceStatusVO();
							vo1.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
							vo1.setCount(commonMethodsUtilService.getLongValueForObject(param[3]));
							newList.add(vo1);
							isuueTypeCountVoMap.put(issueType, newList);//push support_purpose and list into map
						}
					}
				//here iterating PurposeAndSuppodrtMap map set to list to sent the UI
				for(Entry<String ,List<GrivenceStatusVO>> entry2: PurposeAndSuppodrtMap.entrySet()){
					String purpose=entry2.getKey();
					GrivenceStatusVO finalVo=new GrivenceStatusVO();
					finalVo.setGrivenceType(purpose);
					for(GrivenceStatusVO vo:entry2.getValue()){
						GrivenceStatusVO matchedVo=grivenceStatusVOMatchedVo(isuueTypeCountVoMap.get(purpose),vo.getName());
						if(matchedVo !=null){
						vo.setCount(matchedVo.getCount());
						}
						finalVo.getSubList().add(vo);
					}
					finalList.add(finalVo);
				}
			}
			if(commonMethodsUtilService.isListOrSetValid(finalList)){
			 for (GrivenceStatusVO finalVO : finalList) {
			   if(finalVO != null){
			     for (GrivenceStatusVO vo : finalVO.getSubList()) {
			       finalVO.setCount(finalVO.getCount()+vo.getCount());
			     }
			     for (GrivenceStatusVO vo : finalVO.getSubList()) {
			    	 if(vo.getCount() != null && vo.getCount().longValue() >0L && finalVO.getCount() != null && finalVO.getCount().longValue() > 0l)
			             vo.setPerc(new BigDecimal(Double.valueOf(vo.getCount())*100.00/Double.valueOf(finalVO.getCount())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			           else
			             vo.setPerc("0.00");
			     }
			   }
			 }
			}
		}catch(Exception e){
		Log.error("Exception raised in getLocationWiseGrivenceTrustIssueTypesCounts method of LocationDashboardService"+e);
		}
		return finalList;
	}
	public GrivenceStatusVO grivenceStatusVOMatchedVo(List<GrivenceStatusVO> list,String supportFor){
		if(list !=null && list.size() >0){
			for(GrivenceStatusVO gVo:list){
				if(gVo.getName().equalsIgnoreCase(supportFor)){
					return gVo;
				}
			}
		}
		return null;
	}
	public List<ElectionInformationVO> getLocationWiseElectionResults(List<Long> electionScopeId,List<String> subTypeList,Long lelevlId,List<Long> levelValue,List<Long> year,List<Long> partyIdsList,Long constituencyId,String withAllaince){
		List<ElectionInformationVO> finalList=new ArrayList<ElectionInformationVO>();
		try{ 
			
			Map<Long,Map<Long,ElectionInformationVO>> alliancedPartiesWithGroupIdMap = null;
			if(withAllaince != null && withAllaince.trim().equalsIgnoreCase("true"))
				alliancedPartiesWithGroupIdMap =locationWiseElectionInformationDetalsService.getSegregateAliancePartiesMap(subTypeList,year,electionScopeId);
			if(!commonMethodsUtilService.isMapValid(alliancedPartiesWithGroupIdMap))
				alliancedPartiesWithGroupIdMap = new HashMap<Long, Map<Long,ElectionInformationVO>>(0);

			 if (lelevlId != null && lelevlId.longValue() == 10l) {
					@SuppressWarnings("unchecked")
					List<Object[]> findAssembliesConstituencies = (List<Object[]>) delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituencies(levelValue.get(0));
					if(commonMethodsUtilService.isListOrSetValid(findAssembliesConstituencies)){
						levelValue.clear();
						for (Object[] objects : findAssembliesConstituencies) {
							if (objects != null) {
								levelValue.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
							}
						}
					}
				  }
			
			Map<Long,ElectionInformationVO> vacancyMap= new HashMap<Long,ElectionInformationVO>();
			Map<Long,List<ElectionInformationVO>> participantsMap= new HashMap<Long,List<ElectionInformationVO>>();
			Map<Long,List<ElectionInformationVO>> wonPartiesMap= new HashMap<Long,List<ElectionInformationVO>>();
			Map<Long,Long> electionWisePolledVotesMap= new HashMap<Long,Long>();
			Map<Long,Map<Long,Long>> electionPartyWisePolledVotesMap= new HashMap<Long,Map<Long,Long>>();
			 List<Object[]> vacancyList=null;
			 List<Object[]> participantsList=null;
			if(lelevlId != null && lelevlId.longValue() != 5l && lelevlId.longValue() != 7l && lelevlId.longValue() != 6l){
			    vacancyList= candidateDAO.getAvailableSeatsforElection(year,lelevlId,levelValue,electionScopeId,subTypeList,null);
			if(lelevlId.longValue() == 2l || lelevlId.longValue() == 3l || lelevlId.longValue() ==10l){
			List<Object[]> parliamVacancyList= candidateDAO.getAvailableSeatsforElection(year,lelevlId,levelValue,electionScopeId,subTypeList,"parliament");
			 if(parliamVacancyList != null && parliamVacancyList.size()>0){
			      vacancyList.addAll(parliamVacancyList);
			 }
			}
			}
			if(lelevlId != null && lelevlId.longValue() != 5l && lelevlId.longValue() != 7l && lelevlId.longValue() != 6l){
		      participantsList= candidateDAO.getParticipatedPartyListforElection(year,lelevlId,levelValue,electionScopeId,subTypeList,null);
				if(lelevlId.longValue() == 2l || lelevlId.longValue() == 3l || lelevlId.longValue() ==10l){
					List<Object[]> parlimParticipantsList= candidateDAO.getParticipatedPartyListforElection(year,lelevlId,levelValue,electionScopeId,subTypeList,"parliament");
					if(commonMethodsUtilService.isListOrSetValid(parlimParticipantsList)){
						participantsList.addAll(parlimParticipantsList);
					}
				}
			}else{
				 participantsList= candidateDAO.getParticipatedPartyListforElection(year,lelevlId,levelValue,electionScopeId,subTypeList,null);
				List<Object[]> parlimParticipantsList= candidateDAO.getParticipatedPartyListforElection(year,lelevlId,levelValue,electionScopeId,subTypeList,"parliament");
				if(commonMethodsUtilService.isListOrSetValid(participantsList) &&  parlimParticipantsList != null && parlimParticipantsList.size()>0){
					participantsList.addAll(parlimParticipantsList);
				}
					
			}
			List<Object[]> partyWonResultsList= candidateDAO.getParticipatedPartyListforElectionDetails(year,lelevlId,levelValue,electionScopeId,subTypeList,null,"WONRESULT");
			List<Object[]> partyVoteShareResultsList= candidateDAO.getParticipatedPartyListforElectionDetails(year,lelevlId,levelValue,electionScopeId,subTypeList,null,null);
			if(lelevlId.longValue() == 2l || lelevlId.longValue() == 3l || lelevlId.longValue() ==10l){
		    List<Object[]> parlimPartyWonResultsList= candidateDAO.getParticipatedPartyListforElectionDetails(year,lelevlId,levelValue,electionScopeId,subTypeList,"parliament","WONRESULT");
		    List<Object[]> parlimPartyVoteShareResultsList= candidateDAO.getParticipatedPartyListforElectionDetails(year,lelevlId,levelValue,electionScopeId,subTypeList,"parliament",null);

			 if(parlimPartyWonResultsList != null && parlimPartyWonResultsList.size()>0){
				 partyWonResultsList.addAll(parlimPartyWonResultsList);
			 }
			 if(parlimPartyVoteShareResultsList != null && parlimPartyVoteShareResultsList.size()>0){
				 partyVoteShareResultsList.addAll(parlimPartyVoteShareResultsList);
			 }
			}else if(lelevlId.longValue() == 4l || lelevlId.longValue() == 5l || lelevlId.longValue() == 6l){
				List<Object[]> assemblyList = candidateDAO.getAssemblyPartyListforElection(electionScopeId,subTypeList,year,constituencyId,lelevlId,levelValue);
				if(assemblyList != null && assemblyList.size()>0){
					partyWonResultsList.addAll(assemblyList);
				}
			}
			 
			for (Object[] param : partyVoteShareResultsList) {
				Long electionId = commonMethodsUtilService.getLongValueForObject(param[1]);
				Long polledVotes = electionWisePolledVotesMap.get(electionId);
				Long partyId =  commonMethodsUtilService.getLongValueForObject(param[5]);
				
				List<String> alliancePartyIdNameList  = findMatchedPartyId(alliancedPartiesWithGroupIdMap,electionId,partyId);
				if(commonMethodsUtilService.isListOrSetValid(alliancePartyIdNameList)){
					Long alliancePartyId = Long.valueOf(alliancePartyIdNameList.get(0));
					//String alliancePartyName = alliancePartyIdNameList.get(1);
					partyId = alliancePartyId;
				}
				
				if(polledVotes == null)
					polledVotes=0L;
				
				polledVotes = polledVotes+commonMethodsUtilService.getLongValueForObject(param[8]);
				electionWisePolledVotesMap.put(electionId, polledVotes);
				
				Map<Long,Long> partyWisePolledMap = electionPartyWisePolledVotesMap.get(electionId);
				if(partyWisePolledMap == null)
					partyWisePolledMap = new HashMap<Long, Long>(0);
				Long count = partyWisePolledMap.get(partyId);
				if(count == null)
					count=0L;
				count = count+commonMethodsUtilService.getLongValueForObject(param[8]);
				partyWisePolledMap.put(partyId, count);
				electionPartyWisePolledVotesMap.put(electionId, partyWisePolledMap);
			}
			
			
			if(commonMethodsUtilService.isListOrSetValid(participantsList)){
				for (Object[] param : participantsList) {
					Long electionId = commonMethodsUtilService.getLongValueForObject(param[1]);
					Long partyId = commonMethodsUtilService.getLongValueForObject(param[5]);
					String partyName = commonMethodsUtilService.getStringValueForObject(param[6]);
					boolean isAlliance = false;
					List<String> alliancePartyIdNameList  = findMatchedPartyId(alliancedPartiesWithGroupIdMap,electionId,partyId);
					if(commonMethodsUtilService.isListOrSetValid(alliancePartyIdNameList)){
						partyId = Long.valueOf(alliancePartyIdNameList.get(0));
						partyName = alliancePartyIdNameList.get(1);
						isAlliance = true;
						partyIdsList.add(partyId);
					}
					ElectionInformationVO vo = null;
					List<ElectionInformationVO> partysList = participantsMap.get(electionId);
					if(!commonMethodsUtilService.isListOrSetValid(partysList)){
						partysList = new ArrayList<ElectionInformationVO>(0);
					}else{
						if(partyId != null && !partyIdsList.contains(partyId)){
							if(!isAlliance)
								partyName = "OTHERS";
						}
						vo = getMatchedElectionInformationVOByPartyName(partysList,partyName);
					}
					if(vo == null){
						vo = new ElectionInformationVO();
						partysList.add(vo);
					}
					
					if(partyId != null && partyIdsList.contains(partyId)){
						vo.setPartyId(partyId);
						vo.setPartyName(partyName);
						vo.setPartyFlag(commonMethodsUtilService.getStringValueForObject(param[8]));						
						
						vo.setElectionId(commonMethodsUtilService.getLongValueForObject(param[1]));
						vo.setElectionYear(commonMethodsUtilService.getStringValueForObject(param[4]));
						vo.setElectionType(commonMethodsUtilService.getStringValueForObject(param[2]));
						vo.setElectionTypeId(commonMethodsUtilService.getLongValueForObject(param[3]));
						if(vo.getParticipatedSeatsCount() != null)
							vo.setParticipatedSeatsCount(vo.getParticipatedSeatsCount()+commonMethodsUtilService.getLongValueForObject(param[7]));
						else
							vo.setParticipatedSeatsCount(commonMethodsUtilService.getLongValueForObject(param[7]));
						
					}else{
						boolean isOthers=false;
						if(commonMethodsUtilService.isListOrSetValid(partysList)){
							for (ElectionInformationVO partyVO : partysList) {
								if(partyVO.getPartyId() != null && partyVO.getPartyId().longValue() == 1887L){
									vo = partyVO;
									isOthers = true;break;
								}
							}
						}else if(!isOthers){
							partysList.add(vo);
						}
						vo.setPartyId(1887l);
						vo.setPartyName("OTHERS");
						vo.setPartyFlag("");						
						
						vo.setElectionId(commonMethodsUtilService.getLongValueForObject(param[1]));
						vo.setElectionYear(commonMethodsUtilService.getStringValueForObject(param[4]));
						vo.setElectionType(commonMethodsUtilService.getStringValueForObject(param[2]));
						vo.setElectionTypeId(commonMethodsUtilService.getLongValueForObject(param[3]));
						vo.setParticipatedSeatsCount(vo.getParticipatedSeatsCount() + commonMethodsUtilService.getLongValueForObject(param[7]));
					}
					
					Map<Long,Long> partyWisePolledMap = electionPartyWisePolledVotesMap.get(electionId);
					if(partyWisePolledMap != null){
						Long count = partyWisePolledMap.get(partyId);
						if(count == null)
							count=0L;
						if(vo.getPartyId() != null && vo.getPartyId().longValue() == 1887L)
							vo.setEarnedVotes(vo.getEarnedVotes()+count);
						else
							vo.setEarnedVotes(count);
						
						Long polledVotes = electionWisePolledVotesMap.get(electionId);
						if(polledVotes != null && polledVotes.longValue()>0L){
							Double tempPerc  = (vo.getEarnedVotes()*100.0/polledVotes);
							vo.setPerc(""+commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(tempPerc));
						}
					}
					/*Object [] partiesArr={"TDP","BJP","TDP+BJP","TDP/BJP","TDP & BJP","YSRC","INC","CPM","CPI","OTHERS"};
					List<ElectionInformationVO> returnList = (List<ElectionInformationVO>) commonMethodsUtilService.sortElectionInformationVOsList(partysList,"partyName",partiesArr);
					if(commonMethodsUtilService.isListOrSetValid(returnList)){							
					 vo.getList().clear();
					 vo.getList().addAll(returnList);
					}*/
					participantsMap.put(electionId, partysList);
				}
			}
		
			if(commonMethodsUtilService.isListOrSetValid(partyWonResultsList)){
				for (Object[] param : partyWonResultsList) {
					Long electionId = commonMethodsUtilService.getLongValueForObject(param[1]);
					Long partyId = commonMethodsUtilService.getLongValueForObject(param[5]);
					String partyName = commonMethodsUtilService.getStringValueForObject(param[6]);
					boolean isAlliance = false;
					List<String> alliancePartyIdNameList  = findMatchedPartyId(alliancedPartiesWithGroupIdMap,electionId,partyId);
					if(commonMethodsUtilService.isListOrSetValid(alliancePartyIdNameList)){
						partyId = Long.valueOf(alliancePartyIdNameList.get(0));
						partyName = alliancePartyIdNameList.get(1);
						isAlliance = true;
						
					}
					ElectionInformationVO vo = null;
					List<ElectionInformationVO> partysList = wonPartiesMap.get(electionId);
					if(!commonMethodsUtilService.isListOrSetValid(partysList)){
						partysList = new ArrayList<ElectionInformationVO>(0);
					}else{
						if(partyId != null && !partyIdsList.contains(partyId)){
							if(!isAlliance)
								partyName = "OTHERS";
						}
						vo = getMatchedElectionInformationVOByPartyName(partysList,partyName);
					}
					if(vo == null){
						vo = new ElectionInformationVO();
						partysList.add(vo);
					}
					
					if(partyId != null && partyIdsList.contains(partyId)){
						vo.setPartyId(partyId);
						vo.setPartyName(partyName);
						vo.setPartyFlag(commonMethodsUtilService.getStringValueForObject(param[8]));
						vo.setElectionId(commonMethodsUtilService.getLongValueForObject(param[1]));
						vo.setElectionYear(commonMethodsUtilService.getStringValueForObject(param[4]));
						vo.setElectionType(commonMethodsUtilService.getStringValueForObject(param[2]));
						vo.setElectionTypeId(commonMethodsUtilService.getLongValueForObject(param[3]));
						
						if(vo.getTotalSeatsCount() != null)
							vo.setTotalSeatsCount(vo.getTotalSeatsCount()+commonMethodsUtilService.getLongValueForObject(param[7]));
						else
							vo.setTotalSeatsCount(commonMethodsUtilService.getLongValueForObject(param[7]));
						
					}else{
						boolean isOthers=false;
						if(commonMethodsUtilService.isListOrSetValid(partysList)){
							for (ElectionInformationVO partyVO : partysList) {
								if(partyVO.getPartyId() != null && partyVO.getPartyId().longValue() == 1887L){
									vo = partyVO;
									isOthers=true;break;
								}
							}
						}
						if(!isOthers){
							partysList.add(vo);
						}
						
						vo.setPartyId(1887l);
						vo.setPartyName("OTHERS");
						vo.setPartyFlag("");						
						
						vo.setElectionId(commonMethodsUtilService.getLongValueForObject(param[1]));
						vo.setElectionYear(commonMethodsUtilService.getStringValueForObject(param[4]));
						vo.setElectionType(commonMethodsUtilService.getStringValueForObject(param[2]));
						vo.setElectionTypeId(commonMethodsUtilService.getLongValueForObject(param[3]));
						vo.setTotalSeatsCount(vo.getTotalSeatsCount() + commonMethodsUtilService.getLongValueForObject(param[7]));
					}
					/*Object [] partiesArr={"TDP","BJP","TDP+BJP","TDP/BJP","TDP & BJP","YSRC","INC","CPM","CPI","OTHERS"};
					List<ElectionInformationVO> returnList = (List<ElectionInformationVO>) commonMethodsUtilService.sortElectionInformationVOsList(partysList,"partyName",partiesArr);
					if(commonMethodsUtilService.isListOrSetValid(returnList)){							
					 vo.getList().clear();
					 vo.getList().addAll(returnList);
					}*/
					wonPartiesMap.put(electionId, partysList);
				}
			}
			
			if(commonMethodsUtilService.isListOrSetValid(vacancyList)){
				for (Object[] param : vacancyList) {
					ElectionInformationVO vo = new ElectionInformationVO();
					vo.setElectionId(commonMethodsUtilService.getLongValueForObject(param[1]));
					vo.setElectionYear(commonMethodsUtilService.getStringValueForObject(param[4]));
					vo.setElectionType(commonMethodsUtilService.getStringValueForObject(param[2]));
					vo.setElectionTypeId(commonMethodsUtilService.getLongValueForObject(param[3]));
					vo.setTotalSeatsCount(commonMethodsUtilService.getLongValueForObject(param[5]));
					
					List<ElectionInformationVO> participantsPartyList = participantsMap.get(vo.getElectionId());
					if(commonMethodsUtilService.isListOrSetValid(participantsPartyList)){
						for (ElectionInformationVO pPartyVO : participantsPartyList) {							
							vo.setParticipatedSeatsCount(vo.getParticipatedSeatsCount()+pPartyVO.getParticipatedSeatsCount());
							List<ElectionInformationVO> wonPartyList = wonPartiesMap.get(vo.getElectionId());
							if(commonMethodsUtilService.isListOrSetValid(wonPartyList)){
								for (ElectionInformationVO wonParty : wonPartyList) {
									if( wonParty.getPartyId() != null &&  pPartyVO.getPartyId() != null && wonParty.getPartyId().longValue() == pPartyVO.getPartyId().longValue()){
										pPartyVO.setWonSeatsCount(wonParty.getTotalSeatsCount());break;
										//pPartyVO.setPerc(wonParty.getPerc());
									}
								}
							}
							vo.getList().add(pPartyVO);
						}
					}
					vacancyMap.put(vo.getElectionId(), vo);
				}
			}else if(commonMethodsUtilService.isListOrSetValid(partyWonResultsList)){
				//e.election_scope_id,e.election_id,et.election_type,e.election_scope_id,e.election_year,n.party_id,p.short_name,count(n.party_id),sum(cr.votes_earned)
				for (Object[] param : partyWonResultsList) {
					ElectionInformationVO vo = new ElectionInformationVO();
					vo.setElectionId(commonMethodsUtilService.getLongValueForObject(param[1]));
					vo.setElectionYear(commonMethodsUtilService.getStringValueForObject(param[4]));
					vo.setElectionType(commonMethodsUtilService.getStringValueForObject(param[2]));
					vo.setElectionTypeId(commonMethodsUtilService.getLongValueForObject(param[3]));
					//vo.setTotalSeatsCount(commonMethodsUtilService.getLongValueForObject(param[5]));						
					List<ElectionInformationVO> wonPartyList = wonPartiesMap.get(vo.getElectionId());
					if(commonMethodsUtilService.isListOrSetValid(wonPartyList)){
						for (ElectionInformationVO pPartyVO : wonPartyList) {
							ElectionInformationVO	partyVo= getMatchedVOForPartyId(wonPartyList,pPartyVO.getPartyId());
							if(partyVo != null){
								partyVo.setPerc(pPartyVO.getPerc());
								partyVo.setTotalSeatsCount(0l);
							}
							pPartyVO.setPerc(pPartyVO.getPerc());
							pPartyVO.setTotalSeatsCount(0l);
							vo.getList().add(pPartyVO);
						}
					}
					vacancyMap.put(vo.getElectionId(), vo);
				}	
			}
			
			if(commonMethodsUtilService.isMapValid(vacancyMap)){
				finalList.addAll(vacancyMap.values());
				Collections.sort(finalList, new Comparator<ElectionInformationVO>() {
					public int compare(ElectionInformationVO o1,ElectionInformationVO o2) {
						return Long.valueOf(o2.getElectionYear()).compareTo(Long.valueOf(o1.getElectionYear()));
					}
				});
			}
			if(partyIdsList !=null && partyIdsList.size()==1){
				for (ElectionInformationVO outerVO : finalList) {
					List<ElectionInformationVO> partylist = outerVO.getList();
					for (ElectionInformationVO electionInformationVO : partylist) {
						if(electionInformationVO.getPartyId().longValue()==1887L){
							partylist.remove(electionInformationVO);
							break;
						}
						break;
					}
					if(outerVO.getList() ==null || outerVO.getList().size() ==0){
						finalList.remove(outerVO);
						break;
					}
				}
			}
			/*if(finalList != null && finalList.size()>0){
				for(ElectionInformationVO vo : finalList){
					Object [] partiesArr={"TDP","BJP","TDP + BJP","TDP+BJP","TDP/BJP","TDP & BJP","YSRC","INC","CPM","CPI","OTHERS"};
					List<ElectionInformationVO> returnList = (List<ElectionInformationVO>) commonMethodsUtilService.sortElectionInformationVOsList(vo.getList(),"partyName",partiesArr);
					if(commonMethodsUtilService.isListOrSetValid(returnList)){							
					 vo.getList().clear();
					 vo.getList().addAll(returnList);
					}
				}
			}*/
		}catch(Exception e){
			e.printStackTrace();
			Log.error("Exception raised in getLocationWiseElectionResults method of LocationDashboardService"+e);
		}
		return finalList;
	}
	/**
	 * @param List<Long> locationValue
	 * @param Long locationTypeId
	 * @param Long electionId
	 * @param String electionYear
	 * @return  List<ElectionInformationVO> 
	 * @author Swapna
	 * @Description :This service to show Election Year Wise Details electionId,electionType,electionYears,partyIds,partynames,votesEarned 
	  */	
	public  List<ElectionInformationVO> getElectionDetailsData(List<Long> electionYears,Long locationTypeId,List<Long>locationValues,Long electionId,List<String> subTypes,List<Long> partyIds,List<Long> electionScopeIds,String withAllaince){
		List<ElectionInformationVO> returnList = new ArrayList<ElectionInformationVO>();
		try
		{	
			Map<Long,Map<Long,ElectionInformationVO>> alliancedPartiesWithGroupIdMap = null;
			if(withAllaince != null && withAllaince.trim().equalsIgnoreCase("true"))
				alliancedPartiesWithGroupIdMap =locationWiseElectionInformationDetalsService.getSegregateAliancePartiesMap(subTypes,electionYears,electionScopeIds);
			if(!commonMethodsUtilService.isMapValid(alliancedPartiesWithGroupIdMap))
				alliancedPartiesWithGroupIdMap = new HashMap<Long, Map<Long,ElectionInformationVO>>(0);
			
			List<Object[]>    totalCnt = null;
			if(locationTypeId == 2L){
				List<Long> districtids = new ArrayList<Long>(0);
				String[] distictsIdsArr = IConstants.AP_NEW_DISTRICTS_IDS_LIST.split(",");
				if(distictsIdsArr != null && distictsIdsArr.length>0){
					for (int i=0;i<distictsIdsArr.length;i++) {
						if(distictsIdsArr[i] != null && !distictsIdsArr[i].trim().equalsIgnoreCase("517"))
							districtids.add(Long.valueOf(distictsIdsArr[i].trim().toString()));
					}
				}
				
				List<Object[]> parliamentListByDistricts = delimitationConstituencyAssemblyDetailsDAO.getAllParliamentConstituencyByStateId(districtids);
				
				Map<Long,List<Long>> parliamentsDistrictsResultsMap = new HashMap<Long, List<Long>>(0);
				Map<Long,Map<Long,Map<Long,Object[]>>> districtWiseResultsMap = new HashMap<Long,Map<Long,Map<Long,Object[]>>>(0);
				Map<Long,String> distrtictMap = new HashMap<Long,String>();
						
				if(commonMethodsUtilService.isListOrSetValid(parliamentListByDistricts)){
					for (Object[] param : parliamentListByDistricts) {
						List<Long> districtsList = parliamentsDistrictsResultsMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
						if(!commonMethodsUtilService.isListOrSetValid(districtsList))
							districtsList = new ArrayList<Long>(0);
						districtsList.add(commonMethodsUtilService.getLongValueForObject(param[2]));
						distrtictMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getStringValueForObject(param[3]));
						parliamentsDistrictsResultsMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), districtsList);
					}
				}
				
				List<Object[]> assemblyListByDistricts = constituencyDAO.getAssemblyConstituencyDetlsByDistrictIds(districtids);
				
				Map<Long,List<Long>> assemblyDistrictsResultsMap = new HashMap<Long, List<Long>>(0);
						
				if(commonMethodsUtilService.isListOrSetValid(assemblyListByDistricts)){
					for (Object[] param : assemblyListByDistricts) {
						List<Long> districtsList = assemblyDistrictsResultsMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
						if(!commonMethodsUtilService.isListOrSetValid(districtsList))
							districtsList = new ArrayList<Long>(0);
						districtsList.add(commonMethodsUtilService.getLongValueForObject(param[2]));
						distrtictMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getStringValueForObject(param[3]));
						assemblyDistrictsResultsMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), districtsList);
					}
				}
				
				totalCnt = electionDAO.getElectionDetailsDistrictWise(electionYears, locationTypeId, locationValues, electionId,subTypes,partyIds,electionScopeIds,null);
				List<Object[]>  totalCnt1 = electionDAO.getElectionDetailsDistrictWise(electionYears, locationTypeId, locationValues, electionId,subTypes,partyIds,electionScopeIds,"parliament");
				List<Object[]>  acWisePCResults = electionDAO.getElectionDetailsDistrictWise(electionYears, locationTypeId, locationValues, electionId,subTypes,partyIds,electionScopeIds,"AC_WISE_PA_RESULTS");
				if(!commonMethodsUtilService.isListOrSetValid(totalCnt))
					totalCnt = new ArrayList<Object[]>();
					
				Set<Long> multipleDistrictsIdsForPA = new HashSet<Long>(0);
				if(commonMethodsUtilService.isListOrSetValid(totalCnt1)){
					for (Object[] param : totalCnt1) {
						Long partyId =commonMethodsUtilService.getLongValueForObject(param[6]);
						List<Long> districtIdsList = parliamentsDistrictsResultsMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));
						if(commonMethodsUtilService.isListOrSetValid(districtIdsList)){
							
							if(districtIdsList.size() == 1){// single district pa results
								for (Long districtId : districtIdsList) {
									Object[] obj = null;
									//Map<Long,Map<Long,Map<Long,Object[]>>>
									Map<Long,Map<Long,Object[]>> electionMap = districtWiseResultsMap.get(districtId);
									if(!commonMethodsUtilService.isMapValid(electionMap))
										electionMap = new HashMap<Long, Map<Long,Object[]>>(0);
									
									
									Map<Long,Object[]> partyMap  = electionMap.get(commonMethodsUtilService.getLongValueForObject(param[3]));
									if(!commonMethodsUtilService.isMapValid(partyMap)){
										partyMap = new HashMap<Long, Object[]>(0); 
										obj = param;
										obj[1] = districtId;
										obj[2] = distrtictMap.get(districtId);//district name 
									}else{
										obj = partyMap.get(partyId);
										if(obj ==null){
											obj = param;
											obj[1] = districtId;
											obj[2] = distrtictMap.get(districtId);//district name 
										}else{
											obj[8] = commonMethodsUtilService.getLongValueForObject(obj[8])+commonMethodsUtilService.getLongValueForObject(param[8]);
										}
									}
									partyMap.put(partyId, obj);
									electionMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), partyMap);
									districtWiseResultsMap.put(districtId, electionMap);
								}
							}else{
								multipleDistrictsIdsForPA.addAll(districtIdsList);
							}
						}
					}
				}districtWiseResultsMap.get(11L);
					
				if(commonMethodsUtilService.isListOrSetValid(acWisePCResults)){
					for (Object[] param : acWisePCResults) {
						Long partyId =commonMethodsUtilService.getLongValueForObject(param[6]);
						List<Long> districtIdsList = assemblyDistrictsResultsMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));
						if(commonMethodsUtilService.isListOrSetValid(districtIdsList)){
							boolean isOk = false;
							for (Long districtId : districtIdsList) {
								if(multipleDistrictsIdsForPA.contains(districtId))
									isOk = true;
							}
							if(isOk){// multi districts pa results
								for (Long districtId : districtIdsList) {
									Object[] obj = null;
									//Map<Long,Map<Long,Map<Long,Object[]>>>
									Map<Long,Map<Long,Object[]>> electionMap = districtWiseResultsMap.get(districtId);
									if(!commonMethodsUtilService.isMapValid(electionMap))
										electionMap = new HashMap<Long, Map<Long,Object[]>>(0);
									
									
									Map<Long,Object[]> partyMap  = electionMap.get(commonMethodsUtilService.getLongValueForObject(param[3]));
									if(!commonMethodsUtilService.isMapValid(partyMap)){
										partyMap = new HashMap<Long, Object[]>(0); 
										obj = param;
										obj[1] = districtId;
										obj[2] = distrtictMap.get(districtId);//district name 
									}else{
										obj = partyMap.get(partyId);
										if(obj ==null){
											obj = param;
											obj[1] = districtId;
											obj[2] = distrtictMap.get(districtId);//district name 
										}else{
											obj[8] = commonMethodsUtilService.getLongValueForObject(obj[8])+commonMethodsUtilService.getLongValueForObject(param[8]);
										}
									}
									
									partyMap.put(partyId, obj);
									electionMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), partyMap);
									districtWiseResultsMap.put(districtId, electionMap);
								}
							}
						}
					}
				}
				
				if(commonMethodsUtilService.isMapValid(districtWiseResultsMap)){
					for (Long districtId : districtWiseResultsMap.keySet()) {
						Map<Long,Map<Long,Object[]>> electionMap = districtWiseResultsMap.get(districtId);
						if(commonMethodsUtilService.isMapValid(electionMap)){
							for (Long electionsId : electionMap.keySet()) {
								Map<Long,Object[]> partyObjMap = electionMap.get(electionsId);
								if(commonMethodsUtilService.isMapValid(partyObjMap)){
									for (Long partyId : partyObjMap.keySet()) {
										totalCnt.add(partyObjMap.get(partyId));
									}
								}
							}
						}
					}
				}
				
				returnList = setElectionDetailsData(totalCnt,alliancedPartiesWithGroupIdMap,locationTypeId,electionScopeIds);
			}else if (locationTypeId.longValue() == 3L  ||locationTypeId.longValue() == 10L) {
				totalCnt = electionDAO.getElectionDetailsConstituencyWise(electionYears, locationTypeId, locationValues, electionId,subTypes,partyIds,electionScopeIds,null);
				List<Object[]>  totalCnt1 =  electionDAO.getElectionDetailsConstituencyWise(electionYears, locationTypeId, locationValues, electionId,subTypes,partyIds,electionScopeIds,"parliament");
				
				if(!commonMethodsUtilService.isListOrSetValid(totalCnt))
					totalCnt = new ArrayList<Object[]>();
					
				if(commonMethodsUtilService.isListOrSetValid(totalCnt1)){
					totalCnt.addAll(totalCnt1);
				}
				
				returnList = setElectionDetailsData(totalCnt,alliancedPartiesWithGroupIdMap,null,null);
			}else if (locationTypeId.longValue() == 4L) {
				totalCnt = electionDAO.getElectionDetailsMandalWise(electionYears, locationTypeId, locationValues, electionId,subTypes,partyIds,electionScopeIds,false);
				List<Object[]>  totalCntTmp = electionDAO.getElectionDetailsMandalWise(electionYears, locationTypeId, locationValues, electionId,subTypes,partyIds,electionScopeIds,true);
				if(commonMethodsUtilService.isListOrSetValid(totalCnt))
					totalCnt.addAll(totalCntTmp);
				returnList = setElectionDetailsData(totalCnt,alliancedPartiesWithGroupIdMap,null,null);
				totalCnt.clear();
				totalCnt = electionDAO.getElectionDetailsMuncipalityWise(electionYears, locationTypeId, locationValues, electionId,subTypes,partyIds,electionScopeIds);
				List<ElectionInformationVO> tempReturnList = setElectionDetailsData(totalCnt,alliancedPartiesWithGroupIdMap,null,null);
				if(commonMethodsUtilService.isListOrSetValid(tempReturnList)){
					if(!commonMethodsUtilService.isListOrSetValid(returnList))
						returnList = new ArrayList<ElectionInformationVO>();
					
					returnList.addAll(tempReturnList);
				}
			}else if (locationTypeId.longValue() == 5L) {
				totalCnt = electionDAO.getElectionDetailsPanchayatWise(electionYears, locationTypeId, locationValues, electionId,subTypes,partyIds,electionScopeIds);
				returnList = setElectionDetailsData(totalCnt,alliancedPartiesWithGroupIdMap,null,null);
			}	
			
		}catch(Exception e){
			e.printStackTrace();
			Log.error("Exception raised in getElectionDetailsDistrictWiseData method of LocationDashboardService"+e);
		}
		return returnList;
	}
		

public List<ElectionInformationVO> setElectionDetailsData( List<Object[]> totalCnt,Map<Long,Map<Long,ElectionInformationVO>> alliancedPartiesWithGroupIdMap,Long loctionTypeId,List<Long> electionScopeIds){
	List<ElectionInformationVO> finalList = new ArrayList<ElectionInformationVO>();
	try {
		Map<Long, ElectionInformationVO> levelMap=new HashMap<Long,ElectionInformationVO>(0);
		Map<Long,Map<Long,ElectionInformationVO>> electionWisePartyMap = new HashMap<Long, Map<Long,ElectionInformationVO>>(0);
		Map<Long,Map<Long,Long>> locationWisePolledVotesMap = new HashMap<Long, Map<Long,Long>>(0);
		Map<Long,String> partyNameMap = new HashMap<Long,String>(0);
		Map<Long,Map<Long,String>> electionwisePartyNameMap = new HashMap<Long,Map<Long,String>>(0);
		Map<Long,String> partyFlagMap = new HashMap<Long,String>(0);
		Map<Long,Map <Long,Map<String,Long>>> loctionIdEleIdpartyIdCountsMap = new HashMap<Long, Map<Long,Map<String,Long>>>();
		Set<Long> loctionidsSet = new HashSet<Long>(0);
		Set<Long> electionIdsSet = new HashSet<Long>(0);
		Map<String,List<String>> groupUIdAndPartyNamesMap=new HashMap<String, List<String>>();
		Map<Long,Map<String,List<Long>>> groupNameWithPartyIdsMAp=new HashMap<Long,Map<String, List<Long>>>();
		Map<Long,Map<String,List<String>>> groupNameWithPartyNamesMap = new LinkedHashMap<Long,Map<String, List<String>>>();
		
		if(totalCnt !=null && totalCnt.size()>0){
			Set<Long> partyIdList = new HashSet<Long>(0);
			for (Object[] param : totalCnt){
				partyIdList.add(commonMethodsUtilService.getLongValueForObject(param[6]));
				if(loctionTypeId !=null && loctionTypeId.longValue() ==2L){
					loctionidsSet.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					electionIdsSet.add(commonMethodsUtilService.getLongValueForObject(param[3]));
				}
			}
			List<Object[]> wonCountObjs=null;
			if(loctionTypeId !=null && loctionTypeId.longValue()== 2L){
				wonCountObjs=electionDAO.getWonConstituencyCountsInLocation(loctionidsSet,electionScopeIds,electionIdsSet);
			}
			
			if(wonCountObjs !=null && wonCountObjs.size() >0){
				for(Object[] obj:wonCountObjs){
					Map<Long,Map<String,Long>> electionIdAndPartsCountMap=loctionIdEleIdpartyIdCountsMap.get(commonMethodsUtilService.getLongValueForObject(obj[1]));
					if(!commonMethodsUtilService.isMapValid(electionIdAndPartsCountMap))
						electionIdAndPartsCountMap=new HashMap<Long, Map<String,Long>>();
					Map<String,Long> partyIdAndCountMap=electionIdAndPartsCountMap.get(commonMethodsUtilService.getLongValueForObject(obj[0]));
					if(!commonMethodsUtilService.isMapValid(partyIdAndCountMap))
						partyIdAndCountMap=new HashMap<String, Long>();
					partyIdAndCountMap.put(commonMethodsUtilService.getStringValueForObject(obj[2]), commonMethodsUtilService.getLongValueForObject(obj[3]));
					electionIdAndPartsCountMap.put(commonMethodsUtilService.getLongValueForObject(obj[0]), partyIdAndCountMap);
					loctionIdEleIdpartyIdCountsMap.put(commonMethodsUtilService.getLongValueForObject(obj[1]), electionIdAndPartsCountMap);
				}
			}
			List<Object[]> partyNamesList = partyDAO.getPartyShortNameByIds(new ArrayList<Long>(partyIdList));
			if(commonMethodsUtilService.isListOrSetValid(partyNamesList)){
				for (Object[] param : partyNamesList) {
					Long partyId=commonMethodsUtilService.getLongValueForObject(param[0]);
					String partyName=commonMethodsUtilService.getStringValueForObject(param[1]);

					partyNameMap.put(partyId,partyName);
					partyFlagMap.put(partyId, commonMethodsUtilService.getStringValueForObject(param[2]));
				}
			}
			
			for (Object[] objects : totalCnt){	

				Long partyId =commonMethodsUtilService.getLongValueForObject(objects[6]);
				String partyName =partyNameMap.get(partyId);
				Long electionId = commonMethodsUtilService.getLongValueForObject(objects[3]);
				
				List<String> alliancePartyIdNameList  = findMatchedPartyId(alliancedPartiesWithGroupIdMap,electionId,partyId);
				List<Long> alincepartiIdsList= new ArrayList<Long>();
				List<String> aliancePartyWiseNamesList = new ArrayList<String>();
				if(commonMethodsUtilService.isListOrSetValid(alliancePartyIdNameList)){
					partyId = Long.valueOf(alliancePartyIdNameList.get(0));
					partyName = alliancePartyIdNameList.get(1);
					String[] namesArr=alliancePartyIdNameList.get(2).split(",");
					groupUIdAndPartyNamesMap.put(partyName.trim(),Arrays.asList(namesArr));
					String [] partyIdsArrr=alliancePartyIdNameList.get(3).split(",");
					for(String alliancedPartyId:partyIdsArrr){
						alincepartiIdsList.add(Long.valueOf(alliancedPartyId));
					}
					//groupNameWithPartyIdsMAp.put(partyName,alincepartiIdsList);
					
					Map<String,List<Long>> alliancePartyListMap = groupNameWithPartyIdsMAp.get(electionId);
					if(!commonMethodsUtilService.isMapValid(alliancePartyListMap))
						alliancePartyListMap  = new HashMap<String, List<Long>>(0);
					
					alliancePartyListMap.put(partyName, alincepartiIdsList);
					groupNameWithPartyIdsMAp.put(electionId, alliancePartyListMap);
					
					for(String groupPartyName : namesArr){
						aliancePartyWiseNamesList.add(groupPartyName);
					}
					
					Map<String,List<String>> partyListMap = groupNameWithPartyNamesMap.get(electionId);
					if(!commonMethodsUtilService.isMapValid(partyListMap))
						partyListMap  = new HashMap<String, List<String>>(0);
					
					partyListMap.put(partyName, aliancePartyWiseNamesList);
					groupNameWithPartyNamesMap.put(electionId, partyListMap);
					
				}
				
				ElectionInformationVO locationVO = levelMap.get(commonMethodsUtilService.getLongValueForObject(objects[1]));
				if(locationVO == null){
					locationVO  = new ElectionInformationVO();
					locationVO.setLocationId(commonMethodsUtilService.getLongValueForObject(objects[1]));
					locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(objects[2]));
					
					ElectionInformationVO electionVO =new ElectionInformationVO();
					electionVO.setElectionId(commonMethodsUtilService.getLongValueForObject(objects[3]));
					electionVO.setElectionType(commonMethodsUtilService.getStringValueForObject(objects[4]));
					electionVO.setElectionYear(commonMethodsUtilService.getStringValueForObject(objects[5]));
					
					ElectionInformationVO partyVO = getMatchedElectionInformationVOByPartyName(electionVO.getSubList1(),partyName);
					if(partyVO == null){
						partyVO = new ElectionInformationVO();
						electionVO.getSubList1().add(partyVO);
					}
					partyVO.setPartyId(partyId);
					//partyVO.setPartyName(commonMethodsUtilService.getStringValueForObject(objects[7]));
					partyVO.setPartyName(partyName);
					partyVO.setPartyFlag(partyFlagMap.get(partyVO.getPartyId()));
					if(partyVO.getEarnedVote() != null && partyVO.getEarnedVote().trim().length()>0 && Long.valueOf(partyVO.getEarnedVote())>0L){
						Long earnedVotes = Long.valueOf(partyVO.getEarnedVote())+commonMethodsUtilService.getLongValueForObject(objects[8]);
						partyVO.setEarnedVote(String.valueOf(earnedVotes));
					}else{
						partyVO.setEarnedVote(commonMethodsUtilService.getStringValueForObject(objects[8]));
					}
					
					locationVO.getSubList1().add(electionVO);
					levelMap.put(commonMethodsUtilService.getLongValueForObject(objects[1]), locationVO);
					
					Map<Long,ElectionInformationVO> partyListMap = electionWisePartyMap.get(electionVO.getElectionId());
					if(!commonMethodsUtilService.isMapValid(partyListMap)){
						partyListMap = new HashMap<Long, ElectionInformationVO>(0);
					}
					
					ElectionInformationVO partyVO1 =  new ElectionInformationVO();
					partyVO1.setElectionId(commonMethodsUtilService.getLongValueForObject(objects[3]));
					partyVO1.setElectionType(commonMethodsUtilService.getStringValueForObject(objects[4]));
					partyVO1.setElectionYear(commonMethodsUtilService.getStringValueForObject(objects[5]));
					partyVO1.setPartyId(partyId);
					//partyVO1.setPartyNamesList(alincepartiIdsList);
					//partyVO1.setPartyName(commonMethodsUtilService.getStringValueForObject(objects[7]));
					partyVO1.setPartyName(partyName);
					partyVO1.setPartyFlag(partyFlagMap.get(partyVO1.getPartyId()));
					if(partyVO1.getEarnedVote() != null && partyVO.getEarnedVote().trim().length()>0 && Long.valueOf(partyVO.getEarnedVote())>0L){
						Long earnedVotes = Long.valueOf(partyVO1.getEarnedVote())+commonMethodsUtilService.getLongValueForObject(objects[8]);
						partyVO1.setEarnedVote(String.valueOf(earnedVotes));
					}else{
						partyVO1.setEarnedVote(commonMethodsUtilService.getStringValueForObject(objects[8]));
					}
					
					partyListMap.put(partyVO1.getPartyId(), partyVO1);
					electionWisePartyMap.put(electionVO.getElectionId(), partyListMap);
					
					Map<Long,Long> electionWiseVotersMap = locationWisePolledVotesMap.get(commonMethodsUtilService.getLongValueForObject(objects[1]));
					if(!commonMethodsUtilService.isMapValid(electionWiseVotersMap)){
						electionWiseVotersMap = new HashMap<Long, Long>(0);
					}
					
					Long earnedVotesCount = electionWiseVotersMap.get(commonMethodsUtilService.getLongValueForObject(objects[3]));
					if(earnedVotesCount == null)
						earnedVotesCount =0L;
					earnedVotesCount = earnedVotesCount+commonMethodsUtilService.getLongValueForObject(objects[8]);
					electionWiseVotersMap.put(commonMethodsUtilService.getLongValueForObject(objects[3]), earnedVotesCount);
					locationWisePolledVotesMap.put(commonMethodsUtilService.getLongValueForObject(objects[1]), electionWiseVotersMap);
				}
				else{
					
					Map<Long,ElectionInformationVO> partyListMap = electionWisePartyMap.get(commonMethodsUtilService.getLongValueForObject(objects[3]));
					if(!commonMethodsUtilService.isMapValid(partyListMap)){
						partyListMap = new HashMap<Long, ElectionInformationVO>(0);
					}
					
					ElectionInformationVO partyVO1 =new ElectionInformationVO();
					partyVO1.setElectionId(commonMethodsUtilService.getLongValueForObject(objects[3]));
					partyVO1.setElectionType(commonMethodsUtilService.getStringValueForObject(objects[4]));
					partyVO1.setElectionYear(commonMethodsUtilService.getStringValueForObject(objects[5]));
					partyVO1.setPartyId(partyId);
					//partyVO1.setPartyName(commonMethodsUtilService.getStringValueForObject(objects[7]));
					partyVO1.setPartyName(partyName);
					partyVO1.setPartyFlag(partyFlagMap.get(partyVO1.getPartyId()));
					partyVO1.setEarnedVote("0");
					partyListMap.put(partyVO1.getPartyId(), partyVO1);
					electionWisePartyMap.put(commonMethodsUtilService.getLongValueForObject(objects[3]), partyListMap);// electionId,map
					
					
					Map<Long,Long> electionWiseVotersMap = locationWisePolledVotesMap.get(commonMethodsUtilService.getLongValueForObject(objects[1]));
					if(!commonMethodsUtilService.isMapValid(electionWiseVotersMap)){
						electionWiseVotersMap = new HashMap<Long, Long>(0);
					}
					
					Long earnedVotesCount = electionWiseVotersMap.get(commonMethodsUtilService.getLongValueForObject(objects[3]));
					if(earnedVotesCount == null)
						earnedVotesCount =0L;
					earnedVotesCount = earnedVotesCount+commonMethodsUtilService.getLongValueForObject(objects[8]);
					electionWiseVotersMap.put(commonMethodsUtilService.getLongValueForObject(objects[3]), earnedVotesCount);
					locationWisePolledVotesMap.put(commonMethodsUtilService.getLongValueForObject(objects[1]), electionWiseVotersMap);
					
		           ElectionInformationVO electionVO = getMatchedEleVO(locationVO.getSubList1(), commonMethodsUtilService.getLongValueForObject(objects[3]));
				   if (electionVO == null) {
					   electionVO = new ElectionInformationVO();
					   electionVO.setElectionId(commonMethodsUtilService.getLongValueForObject(objects[3]));
					   electionVO.setElectionType(commonMethodsUtilService.getStringValueForObject(objects[4]));
					   electionVO.setElectionYear(commonMethodsUtilService.getStringValueForObject(objects[5])); 
						
					    ElectionInformationVO partyVO = getMatchedElectionInformationVOByPartyName(electionVO.getSubList1(),partyName);
						if(partyVO == null){
							partyVO = new ElectionInformationVO();
							electionVO.getSubList1().add(partyVO);
						}
						partyVO.setPartyId(partyId);
						partyVO.setPartyName(partyName);
						
						partyVO.setPartyFlag(partyFlagMap.get(partyVO.getPartyId()));
						if(partyVO.getEarnedVote() != null && partyVO.getEarnedVote().trim().length()>0 && Long.valueOf(partyVO.getEarnedVote())>0L){
							Long earnedVotes = Long.valueOf(partyVO.getEarnedVote())+commonMethodsUtilService.getLongValueForObject(objects[8]);
							partyVO.setEarnedVote(String.valueOf(earnedVotes));
						}else{
							partyVO.setEarnedVote(commonMethodsUtilService.getStringValueForObject(objects[8]));
						}
						locationVO.getSubList1().add(electionVO);
				   }
				   else{
					   ElectionInformationVO partyVO = getMatchedElectionInformationVOByPartyName(electionVO.getSubList1(),partyName);
					   if(partyVO == null){
						   partyVO = new ElectionInformationVO();
						   electionVO.getSubList1().add(partyVO);
					   }
					   partyVO.setPartyId(partyId);
					   partyVO.setPartyName(partyName);
					  // partyVO.setPartyName(commonMethodsUtilService.getStringValueForObject(objects[7]));
					   partyVO.setPartyFlag(partyFlagMap.get(partyVO.getPartyId()));
					   if(partyVO.getEarnedVote() != null && partyVO.getEarnedVote().trim().length()>0 && Long.valueOf(partyVO.getEarnedVote())>0L){
							Long earnedVotes = Long.valueOf(partyVO.getEarnedVote())+commonMethodsUtilService.getLongValueForObject(objects[8]);
							partyVO.setEarnedVote(String.valueOf(earnedVotes));
						}else{
							partyVO.setEarnedVote(commonMethodsUtilService.getStringValueForObject(objects[8]));
						}
					  
				   }
				}
			}
		}
		if(commonMethodsUtilService.isMapValid(levelMap)){
			finalList = new ArrayList<ElectionInformationVO>(levelMap.values());
			if(commonMethodsUtilService.isListOrSetValid(finalList)){
				for (ElectionInformationVO locationVO : finalList) {
					if(commonMethodsUtilService.isListOrSetValid(locationVO.getSubList1())){
						List<Long> availableElectionIdsList = new ArrayList<Long>(0);
						if(commonMethodsUtilService.isListOrSetValid(locationVO.getSubList1())){
							for (ElectionInformationVO electionVO : locationVO.getSubList1()) {
								availableElectionIdsList.add(electionVO.getElectionId());
							}
							if(commonMethodsUtilService.isMapValid(electionWisePartyMap) && electionWisePartyMap.keySet().size()>availableElectionIdsList.size()){
								for (Long electionId : electionWisePartyMap.keySet()) {
									if(!availableElectionIdsList.contains(electionId)){
										Map<Long,ElectionInformationVO> partyListMap  = electionWisePartyMap.get(electionId);
										if(commonMethodsUtilService.isMapValid(partyListMap)){
											int i=0;
											for (ElectionInformationVO tempVO : partyListMap.values()) {
												if(i==0){
													ElectionInformationVO electionVO =new ElectionInformationVO();
													electionVO.setElectionId(tempVO.getElectionId());
													electionVO.setElectionType(tempVO.getElectionType());
													electionVO.setElectionYear(tempVO.getElectionYear());
													locationVO.getSubList1().add(electionVO);
												}
												i=i+1;
											}
										}
									}
								}
							}
							Collections.sort(locationVO.getSubList1(), new Comparator<ElectionInformationVO>() {
								public int compare(ElectionInformationVO o1,ElectionInformationVO o2) {
									return o2.getElectionYear().compareTo(o1.getElectionYear());
								}
							});
							
						}
						
						for (ElectionInformationVO electionVO : locationVO.getSubList1()) {
							List<Long> availableIdsList = new ArrayList<Long>(0);
							Map<Long,Long> electionWisePolledVotesMap = locationWisePolledVotesMap.get(locationVO.getLocationId());
							if(commonMethodsUtilService.isListOrSetValid(electionVO.getSubList1())){
								for (ElectionInformationVO partyVO : electionVO.getSubList1()) {
									Long polledVotesCount = electionWisePolledVotesMap.get(electionVO.getElectionId());
									Double perc = (Double) (Long.valueOf(partyVO.getEarnedVote()) * 100.0/polledVotesCount);
									partyVO.setPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(perc));
									availableIdsList.add(partyVO.getPartyId());
								}
							}
							
							if(commonMethodsUtilService.isMapValid(electionWisePartyMap)){
								Map<Long,ElectionInformationVO> partyListMap  = electionWisePartyMap.get(electionVO.getElectionId());
								if(commonMethodsUtilService.isMapValid(partyListMap)){
									for (Long partyId : partyListMap.keySet()) {
										if(!availableIdsList.contains(partyId)){
											ElectionInformationVO partyVO = partyListMap.get(partyId);
											if(partyVO != null){
												   partyVO.setPartyId(partyVO.getPartyId());
												   //partyVO.setPartyName(partyVO.getPartyName());
												   partyVO.setPartyName(partyNameMap.get(partyVO.getPartyId()));
												   partyVO.setPartyFlag(partyFlagMap.get(partyVO.getPartyId()));
												   partyVO.setEarnedVote("0");
												   partyVO.setPerc("0.0");
												   electionVO.getSubList1().add(partyVO);
											}
										}
									}
								}
							}
							
							Collections.sort(electionVO.getSubList1(), new Comparator<ElectionInformationVO>() {
								public int compare(ElectionInformationVO o1,ElectionInformationVO o2) {
									if(o1.getPartyName() != null && o2.getPartyName() != null)
										return o1.getPartyName().compareTo(o2.getPartyName());
									else
										return 0;
								}
							});
						}
						
						/*Collections.sort(locationVO.getSubList1(), new Comparator<ElectionInformationVO>() {
							public int compare(ElectionInformationVO o1,ElectionInformationVO o2) {
								return o2.getElectionId().compareTo(o1.getElectionId());
							}
						});*/
					}
				}
			}
		}// here we adding the  won counts of each party in a location
		// iterating finalList and  get the counts from loctionIdEleIdpartyIdCountsMap map 
		if(commonMethodsUtilService.isListOrSetValid(finalList)){
			for(ElectionInformationVO loctionVo:finalList){		
				Map<Long,Map<String,Long>> elctionIdAndPartCounts=loctionIdEleIdpartyIdCountsMap.get(loctionVo.getLocationId());
				if(commonMethodsUtilService.isMapValid(elctionIdAndPartCounts) && commonMethodsUtilService.isListOrSetValid(loctionVo.getSubList1())){
					for(ElectionInformationVO electionVo:loctionVo.getSubList1()){
						Map<String,Long> partiesCounts=elctionIdAndPartCounts.get(electionVo.getElectionId());
						if(commonMethodsUtilService.isMapValid(partiesCounts) && commonMethodsUtilService.isListOrSetValid(electionVo.getSubList1())){
							for(ElectionInformationVO partVo:electionVo.getSubList1() ){
								if(partVo.getPartyName() !=null && partVo.getPartyName().trim().length() >0){
									//with ALLIANCE count							//this alianceparty map having group id and party names
									if(commonMethodsUtilService.isListOrSetValid(groupUIdAndPartyNamesMap.get(partVo.getPartyName().trim()))){
										Long alliancePartyCount=0L;		//this block calcute total parties won count in group
										for(String partyname : groupUIdAndPartyNamesMap.get(partVo.getPartyName().trim())){
											if(partyname !=null && partyname.trim().length() >0)
												alliancePartyCount=alliancePartyCount+((partiesCounts.get(partyname.trim()) !=null )? partiesCounts.get(partyname.trim()):0L);
											
											 Map<String,List<Long>> alliancePartyListMap = groupNameWithPartyIdsMAp.get(electionVo.getElectionId());
											 if(commonMethodsUtilService.isMapValid(alliancePartyListMap)){
												 if(alliancePartyListMap.get(partVo.getPartyName().trim()) != null && alliancePartyListMap.get(partVo.getPartyName().trim()).size() >0)
														partVo.getIdsList().addAll(alliancePartyListMap.get(partVo.getPartyName().trim()));
											 }
											 
											Map<String,List<String>> partyListMap = groupNameWithPartyNamesMap.get(electionVo.getElectionId());
											if(commonMethodsUtilService.isMapValid(partyListMap)){
												if(partyListMap.get(partVo.getPartyName().trim()) != null && partyListMap.get(partVo.getPartyName().trim()).size() >0)
													partVo.getPartyNamesList().addAll(partyListMap.get(partVo.getPartyName().trim()));
											}
											/*if(groupNameWithPartyIdsMAp.get(partVo.getPartyName().trim()) != null && groupNameWithPartyIdsMAp.get(partVo.getPartyName().trim()).size() >0)
												partVo.getIdsList().addAll(groupNameWithPartyIdsMAp.get(partVo.getPartyName().trim()));
											
											if(groupNameWithPartyNamesMap.get(partVo.getPartyName().trim()) != null && groupNameWithPartyNamesMap.get(partVo.getPartyName().trim()).size() >0)
												partVo.getPartyNamesList().addAll(groupNameWithPartyNamesMap.get(partVo.getPartyName().trim()));
											*/
											
										}
											partVo.setWonSeatsCount(alliancePartyCount);
											// with out alliance count
										}else if(partiesCounts.get(partVo.getPartyName()) !=null && partiesCounts.get(partVo.getPartyName().trim())>0L){
										partVo.setWonSeatsCount(partiesCounts.get(partVo.getPartyName().trim()));
									}
									
								}
							}
						}
					}
				}
			}
		}
	} catch (Exception e) {
		Log.error("Exception raised in setElectionDetailsData method of LocationDashboardService"+e);e.printStackTrace();
	}
	return finalList;
}	
	
public ElectionInformationVO getMatchedEleVO(List<ElectionInformationVO> finalList,Long id){
	try{
		if(finalList != null && finalList.size() > 0){
			for(ElectionInformationVO param : finalList){
				if(param.getElectionId().longValue() == id){
					return param;
				}
			}
		}
	}catch(Exception e){
		Log.error("Exception raised in getMatchedVOForCadreId method of LocationDashboardService"+e);
	}
	return null;
}
    
    
public ElectionInformationVO getMatchedPartyVO(List<ElectionInformationVO> finalList,Long id){
	try{
		if(finalList != null && finalList.size() > 0){
			for(ElectionInformationVO param : finalList){
				if(param.getPartyId().longValue() == id){
					return param;
				}
			}
		}
	}catch(Exception e){
		Log.error("Exception raised in getMatchedVOForCadreId method of LocationDashboardService"+e);
	}
	return null;
}
public CandidateDetailsForConstituencyTypesVO getMatchedVOForCadreIdForCadreId(List<CandidateDetailsForConstituencyTypesVO> cadreVOs,Long id){
	try{
		if(cadreVOs != null && cadreVOs.size() > 0){
			for(CandidateDetailsForConstituencyTypesVO param : cadreVOs){
				if(param.getCadreId().longValue() == id){
					return param;
				}
			}
		}
	}catch(Exception e){
		Log.error("Exception raised in getMatchedVOForCadreId method of LocationDashboardService"+e);
	}
	return null;
}
public ElectionInformationVO getMatchedVOForPartyId(List<ElectionInformationVO> cadreVOs,Long id){
	try{
		if(cadreVOs != null && cadreVOs.size() > 0){
			for(ElectionInformationVO param : cadreVOs){
				if(param.getPartyId().longValue() == id){
					return param;
				}
			}
		}
	}catch(Exception e){
		Log.error("Exception raised in getMatchedVOForCadreId method of LocationDashboardService"+e);
	}
	return null;
}
public List<CandidateDetailsForConstituencyTypesVO> getPartyWiseMPandMLACandidatesCountDetials(List<Long> electionIds,List<Long> electionScopeIds,Long loactionTypeId,Long loctionValue,List<Long> partyId,Long districtId,List<String> electionYears){
	List<CandidateDetailsForConstituencyTypesVO> finalList= new ArrayList<CandidateDetailsForConstituencyTypesVO>();
	List<Long> locationIdsList = new ArrayList<Long>();
	List<CandidateDetailsForConstituencyTypesVO> newCandidateList = new ArrayList<CandidateDetailsForConstituencyTypesVO>();
	try{
		List<Object[]> mpPartyCountDetails = nominationDAO.partyWiseMemberOfParlimentsDetails(electionIds,electionScopeIds, loactionTypeId,loctionValue,partyId,electionYears);
		settingPartyWiseCandidateDetails(mpPartyCountDetails,finalList);
		List<Object[]> mlaPartyCountDetails=nominationDAO.partyWiseMemberOfAssemblyCandidateDetails(electionIds,electionScopeIds, loactionTypeId,loctionValue,partyId,electionYears);
		settingPartyWiseCandidateDetails(mlaPartyCountDetails,finalList);
		
		List<Object[]> objsList = constituencyDAO.getConstituenciesIdsListByDistrictIds(districtId);
		if(objsList != null && objsList.size() >0){
			for(Object[] objs : objsList){
				Long constituencyId = commonMethodsUtilService.getLongValueForObject(objs[0]);
				locationIdsList.add(constituencyId);
			}
			
		}
		
		for(CandidateDetailsForConstituencyTypesVO mainVO : finalList){
			if(locationIdsList.contains(mainVO.getConstituencyId())){
				newCandidateList.add(mainVO);
			}	
	}
		if(locationIdsList !=null && locationIdsList.size() >0){
			finalList.clear();
			finalList.addAll(newCandidateList);
		}
	}catch(Exception e){
		Log.error("Exception raised in getPartyWiseMPandMLACandidatesCountDetials method of LocationDashboardService"+e);
	}
	return finalList;
	
}
public void settingPartyWiseCandidateDetails(List<Object[]> mpPartyCountDetails,List<CandidateDetailsForConstituencyTypesVO> finalList){
	List<Long> candidateIds = new ArrayList<Long>();
	if(mpPartyCountDetails != null && mpPartyCountDetails.size()>0){
		for(Object[] param : mpPartyCountDetails){
			CandidateDetailsForConstituencyTypesVO typeVo=new CandidateDetailsForConstituencyTypesVO();
			typeVo.setCondidateId(commonMethodsUtilService.getLongValueForObject(param[0]));
			candidateIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
			typeVo.setCandidateName(commonMethodsUtilService.getStringValueForObject(param[1]));
			typeVo.setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[2]));
			typeVo.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[3]));
			typeVo.setPartyId(commonMethodsUtilService.getLongValueForObject(param[4]));
			typeVo.setParty(commonMethodsUtilService.getStringValueForObject(param[5]));
			typeVo.setPartyFlag(commonMethodsUtilService.getStringValueForObject(param[6]));
			typeVo.setMarginVotes(commonMethodsUtilService.getStringValueForObject(param[7]));
			typeVo.setMarginVotesPercentage(commonMethodsUtilService.getStringValueForObject(param[8]));
			finalList.add(typeVo);
		}
		List<Object[]> cadreCandidateDeatils = tdpCadreCandidateDAO.nomiantionCandidateDetails(candidateIds);
			if(cadreCandidateDeatils != null && cadreCandidateDeatils.size()>0){
				for(Object[] obj : cadreCandidateDeatils){
					CandidateDetailsForConstituencyTypesVO matchedVo = getMatchedVOForCandiadteId(finalList,commonMethodsUtilService.getLongValueForObject(obj[1]));
					if(matchedVo != null){
						matchedVo.setCadreId(commonMethodsUtilService.getLongValueForObject(obj[0]));
						matchedVo.setImage(commonMethodsUtilService.getStringValueForObject(obj[2]));
					}
				}
			}
	}
}
public CandidateDetailsForConstituencyTypesVO getMatchedVOForCandiadteId(List<CandidateDetailsForConstituencyTypesVO> cadreVOs,Long id){
	try{
		if(cadreVOs != null && cadreVOs.size() > 0){
			for(CandidateDetailsForConstituencyTypesVO param : cadreVOs){
				if(param.getCondidateId().longValue() == id){
					return param;
				}
			}
		}
	}catch(Exception e){
		Log.error("Exception raised in getMatchedVOForCadreId method of LocationDashboardService"+e);
	}
	return null;
}
public List<LocationWiseBoothDetailsVO> getAllParliamentConstituencyByAllLevels(Long loactionTypeId,List<Long> locationValues) {
	try{
		List<LocationWiseBoothDetailsVO> idNameVOList = new ArrayList<LocationWiseBoothDetailsVO>();
		List<Object[]> parlimentList =null;
		List<Long> canstituencyIds =null;
		List<Long> districtids = new ArrayList<Long>();
		Long[] ids = IConstants.AP_NEW_DISTRICTS_IDS;
		for (Long obj : ids) {
			districtids.add(obj);
		}
		if(loactionTypeId != null && (loactionTypeId.longValue() == 5l || loactionTypeId.longValue() == 6l || loactionTypeId.longValue() == 7l ) ){
			 canstituencyIds = delimitationConstituencyMandalDetailsDAO.getAllParliamentMandalByAllLevels(locationValues,loactionTypeId);
		}
		if(loactionTypeId != null && loactionTypeId.longValue() == 2l){
		    parlimentList= delimitationConstituencyAssemblyDetailsDAO.getAllParliamentConstituencyByAllLevels(districtids,locationValues,loactionTypeId,null);   
		}else if(loactionTypeId != null && (loactionTypeId.longValue() == 3l || loactionTypeId.longValue() == 4l ||  loactionTypeId.longValue() == 10l )){
			parlimentList= delimitationConstituencyAssemblyDetailsDAO.getAllParliamentConstituencyByAllLevels(null,locationValues,loactionTypeId,null);
		}else if(loactionTypeId != null && (loactionTypeId.longValue() == 5l || loactionTypeId.longValue() == 6l || loactionTypeId.longValue() == 7l )){
			parlimentList= delimitationConstituencyAssemblyDetailsDAO.getAllParliamentConstituencyByAllLevels(null,locationValues,loactionTypeId,canstituencyIds);
		}
		for (Object[] objects : parlimentList) {
			if(objects!=null){
				LocationWiseBoothDetailsVO locationVo= new LocationWiseBoothDetailsVO();
				locationVo.setLocationId(commonMethodsUtilService.getLongValueForObject(objects[0]));
				locationVo.setLocationName(WordUtils.capitalizeFully(commonMethodsUtilService.getStringValueForObject(objects[1])));
				locationVo.setName("Parliment");
				idNameVOList.add(locationVo);
			}
			
		}
		return idNameVOList;
	}catch(Exception e){
		Log.error("Exception raised at getAllParliamentConstituencyByAllLevels", e);
		return null;
	}
}

/**
 * @author  Sai Kumar  <href:saikumar.mandal@itgrids.com >
 * @Date 17th oct,2017
 * @description to  get  Locationwise  meeting detailes
 * @param locationScopeId,locationValues, start date,end date,meetingLevelId,meetingTypeId,meetingMainTypeId
 * @return List of MeetingsVO 
*/
	@SuppressWarnings("null")
	@Override
	public List<MeetingsVO> getAreaWisePartyMeetingsDetails(Long locationScopeId,List<Long> locationValues, String startDate, String endDate,Long meetingLevelId, Long meetingTypeId, Long meetingMainTypeId,String searchFor) {
		List<MeetingsVO> finalList=new ArrayList<MeetingsVO>();
		Map<Long,MeetingsVO> locationWiseDetailsVOMap=new HashMap<Long, MeetingsVO>();
		String searchType=null;
		try{
		Date fromDate = null;
		Date toDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		// Here converting stirng to date formatte
		if(startDate != null && startDate.trim().length() > 0 && endDate != null && endDate.trim().length() > 0){
			fromDate = sdf.parse(startDate);
			toDate = sdf.parse(endDate);
		}
		
		List<Object[]> areaWisePartyMeetingDetailsObj=new ArrayList<Object[]>(0);
		
		//0 districtId,1 districtName,2 month,3 year 
		//4 meetingStatus,5 meetingsCount 
		if(locationScopeId == 4l || searchFor !=null && searchFor.equalsIgnoreCase("mandal")){
			 areaWisePartyMeetingDetailsObj.addAll(partyMeetingStatusDAO.getAreaWisePartyMeetingsDetails(locationScopeId, locationValues, fromDate, toDate, meetingLevelId, meetingTypeId, meetingMainTypeId,"rural",searchFor));
			 areaWisePartyMeetingDetailsObj.addAll(partyMeetingStatusDAO.getAreaWisePartyMeetingsDetails(locationScopeId, locationValues, fromDate, toDate, meetingLevelId, meetingTypeId, meetingMainTypeId,"urban",searchFor));
		}else{
		    areaWisePartyMeetingDetailsObj.addAll(partyMeetingStatusDAO.getAreaWisePartyMeetingsDetails(locationScopeId, locationValues, fromDate, toDate, meetingLevelId, meetingTypeId, meetingMainTypeId,null,searchFor));
		}
		
		Map<String,MeetingsVO>  monthTemplateMap=new LinkedHashMap<String,MeetingsVO>();
		if(areaWisePartyMeetingDetailsObj != null && areaWisePartyMeetingDetailsObj.size()>0){
		for(Object[] param : areaWisePartyMeetingDetailsObj){
			Long year=commonMethodsUtilService.getLongValueForObject(param[3]);
			Long month=commonMethodsUtilService.getLongValueForObject(param[2]);
			String key = month+"_"+year;
			MeetingsVO monthDtlsVO = monthTemplateMap.get(key);
			  if (monthDtlsVO == null ) {
				  monthDtlsVO = new MeetingsVO();
				  monthDtlsVO.setYear(year);
				  monthDtlsVO.setNoOfMonth(month);
				  monthDtlsVO.setMonthName(IConstants.MONTH_NAMES[Integer.parseInt(month.toString())-1]);
				  monthTemplateMap.put(key, monthDtlsVO);
			  }
		  }
		}
		
		
		if(areaWisePartyMeetingDetailsObj != null && areaWisePartyMeetingDetailsObj.size()>0){
			for(Object[] param : areaWisePartyMeetingDetailsObj){
				Long locationId=commonMethodsUtilService.getLongValueForObject(param[0]);
				Long year=commonMethodsUtilService.getLongValueForObject(param[3]);
				Long month=commonMethodsUtilService.getLongValueForObject(param[2]);
				
				if(locationWiseDetailsVOMap.containsKey(locationId)) {
					MeetingsVO locationVO=locationWiseDetailsVOMap.get(locationId);
					List<MeetingsVO> yearWiseVOList = locationVO.getYearWiseMeetingsCount();
						MeetingsVO matchedYearVO = getMatchedVO(yearWiseVOList,year,month);
						if(matchedYearVO != null){
							setStatusToVO(matchedYearVO,param,yearWiseVOList.get(0));
						}
				}else{
					MeetingsVO locationVO = new MeetingsVO();
					locationVO.setId(locationId);
					locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					
					MeetingsVO OverallStatusVO = new MeetingsVO();
					ArrayList<MeetingsVO> tempList=new ArrayList<MeetingsVO>(monthTemplateMap.values());
					//locationVO.setYearWiseMeetingsCount(new ArrayList<MeetingsVO>(monthTemplateMap.values()));
					locationVO.setYearWiseMeetingsCount(getMonthTemplate(monthTemplateMap));
					MeetingsVO matchedYearVO = getMatchedVO(locationVO.getYearWiseMeetingsCount(),year,month);
					if (matchedYearVO != null) {
						setStatusToVO(matchedYearVO, param, OverallStatusVO);
					}
					locationVO.getYearWiseMeetingsCount().add(0, OverallStatusVO);//OverallStatusVO at 0th position
					locationWiseDetailsVOMap.put(locationId, locationVO);
				  }
			   }
			}
		finalList.addAll(locationWiseDetailsVOMap.values());
		for (MeetingsVO meetingVo : finalList) {
			List<MeetingsVO> yearWiseVOList=meetingVo.getYearWiseMeetingsCount();
			if(yearWiseVOList != null && yearWiseVOList.size() >= 1) {
				boolean foundStatus=false;
				for(MeetingsVO yearWiseVO:yearWiseVOList.subList(1, yearWiseVOList.size())){
					if(yearWiseVO.getYesCount().longValue() >0l ||yearWiseVO.getNoCount().longValue() >0l ||yearWiseVO.getMayBeCount().longValue() >0l ||yearWiseVO.getNotUpDatedCount().longValue() >0l) {
						foundStatus=true;
						meetingVo.setTotal(yearWiseVO.getYesCount()+yearWiseVO.getNoCount()+yearWiseVO.getMayBeCount()+yearWiseVO.getNotUpDatedCount());
		                break;
					}

				}
				if(foundStatus ==false) {
					MeetingsVO yearWiseVO=yearWiseVOList.get(0);
					meetingVo.setTotal(yearWiseVO.getYesCount()+yearWiseVO.getNoCount()+yearWiseVO.getMayBeCount()+yearWiseVO.getNotUpDatedCount());		
				}
			}
			if(yearWiseVOList.get(0) != null){//get yearWiseVO(Overall) at 0st position from list and calculate percentage for all status count 
				DecimalFormat df = new DecimalFormat("###.##");
				MeetingsVO yearWiseVO=yearWiseVOList.get(0);
				yearWiseVO.setYesCountPercentage(df.format(((float)yearWiseVO.getYesCount()/(float)yearWiseVO.getTotal())*100));
				yearWiseVO.setNoCountPercentage(df.format(((float)yearWiseVO.getNoCount()/(float)yearWiseVO.getTotal())*100));
				yearWiseVO.setMayBeCountPercentage(df.format(((float)yearWiseVO.getMayBeCount()/(float)yearWiseVO.getTotal())*100));
				yearWiseVO.setNotUpDatedCountPercentage(df.format(((float)yearWiseVO.getNotUpDatedCount()/(float)yearWiseVO.getTotal())*100));

			}
		}
		}
		catch (Exception e) {
			Log.error("Exception raised at getAreaWisePartyMeetingsDetails in  LocationDashboardService ", e);
		}
		return finalList;
	}
	
	public MeetingsVO getMatchedVO(List<MeetingsVO> voList,Long year,Long month){
		try{
			if(voList == null || voList.size() == 0)
				return null;
			for(MeetingsVO vo:voList){
				if(vo.getYear().equals(year) && vo.getNoOfMonth().equals(month))
	    			return vo;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public MeetingsVO setStatusToVO(MeetingsVO vo,Object[] param,MeetingsVO OverallStatusVO){
		Long count=commonMethodsUtilService.getLongValueForObject(param[5]);
		OverallStatusVO.setTotal(OverallStatusVO.getTotal()+count);
		String status=commonMethodsUtilService.getStringValueForObject(param[4]);
	
		if(status.equalsIgnoreCase("Y")){
			vo.setYesCount(count);
			vo.setTotal(vo.getTotal()+count);
			OverallStatusVO.setYesCount(OverallStatusVO.getYesCount()+count);
		}else if(status.equalsIgnoreCase("N")){
			vo.setNoCount(count);
			vo.setTotal(vo.getTotal()+count);
			OverallStatusVO.setNoCount(OverallStatusVO.getNoCount()+count);
		}else if(status.equalsIgnoreCase("M")){
			vo.setMayBeCount(count);
			vo.setTotal(vo.getTotal()+count);
			OverallStatusVO.setMayBeCount(OverallStatusVO.getMayBeCount()+count);
		}else if(status.equalsIgnoreCase("NU")){
			vo.setNotUpDatedCount(count);
			vo.setTotal(vo.getTotal()+count);
			OverallStatusVO.setNotUpDatedCount(OverallStatusVO.getNotUpDatedCount()+count);
		}
		return vo;
	}
	public static void sortPartyList(List<ElectionInformationVO> list){
		
		try {
			Map<String,ElectionInformationVO> levelMap=new HashMap<String,ElectionInformationVO>(0);
			for(ElectionInformationVO vo : list){
				levelMap.put(vo.getPartyName().trim().toUpperCase(), vo);
			}
			list.clear();
			if(levelMap.containsKey("TDP")){
				list.add(0,levelMap.get("TDP"));
			}else if(levelMap.containsKey("TDP/BJP")){
				list.add(0,levelMap.get("TDP/BJP"));
			}
			
			if(levelMap.containsKey("BJP")){
				list.add(1,levelMap.get("BJP"));
			}
			if(levelMap.containsKey("YCP")){
				list.add(2,levelMap.get("YCP"));
			}else if(levelMap.containsKey("YSRC")){
				list.add(2,levelMap.get("YSRC"));
				}
			if(levelMap.containsKey("INC")){
				list.add(3,levelMap.get("INC"));
			}else if(levelMap.containsKey("OTHERS")){
				list.add(3,levelMap.get("OTHERS"));
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in sortPartyList() Method");

		}
		
	}
	
	/**
	 * @param String fromDateStr
	 * @param String toDateStr
	 * @param Long locationTypeId
	 * @param List<Long> locationValues
	 * @param Long stateId
	 * @return GrivenceStatusVO
	 * @author Santosh Kumar Verma
	 * @Description :This Service Method is used to get grivence overview details. 
	 * @Date 21-OCT-2017
	 */
	public GrivenceStatusVO getGrivenceOverviewDtls(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year,Long stateId) {
		GrivenceStatusVO resultVO = new GrivenceStatusVO();
		try{
			
			Date fromDate = getDates(fromDateStr,toDateStr)[0];
			Date toDate = getDates(fromDateStr,toDateStr)[1];
			
			List<Object[]> grivenceDtlsObjList = insuranceStatusDAO.getGrievanceTypeOfIssueIssueTypeAndStatusComplaintCnt(locationTypeId, locationValues, stateId, fromDate, toDate);
			
			Map<String,GrivenceStatusVO> statusMap = getStatusWiseComplaintCount(grivenceDtlsObjList);//getting status wise complaint count
			List<GrivenceStatusVO> typeOfIssueList = getTypeOfIssueStatusWiseComplaintCount(grivenceDtlsObjList);//getting type of issue and status wise complaint count
			List<GrivenceStatusVO> typeOfIssueIssueTypeList = getTypeOfIssueIssueTypeStatusWiseComplaintCount(grivenceDtlsObjList, statusMap);//getting type of issue,issue type and status list
			
			resultVO.getSubList().addAll(statusMap.values());
			resultVO.setSubList1(typeOfIssueList);
			resultVO.setSubList2(typeOfIssueIssueTypeList);
			
		}catch(Exception e){
			Log.error("Exception raised at getGrivenceOverviewDtls() in  LocationDashboardService class "+e);
		}
		return resultVO;
	}

	private Map<String, GrivenceStatusVO> getStatusWiseComplaintCount(List<Object[]> objList) {
		Map<String, GrivenceStatusVO> statusMap = new LinkedHashMap<String, GrivenceStatusVO>();
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					String status = commonMethodsUtilService.getStringValueForObject(param[2]).trim();
					GrivenceStatusVO statusVO = statusMap.get(getInUpperCase(status));
					if (statusVO == null) {
						statusVO = new GrivenceStatusVO();
						statusVO.setName(getInUpperCase(status));
						statusMap.put(statusVO.getName(), statusVO);
					}
					statusVO.setCount(statusVO.getCount()+ commonMethodsUtilService.getLongValueForObject(param[3]));
				}
			}

		} catch (Exception e) {
			Log.error("Exception raised at getStatusWiseComplaintCount() in  LocationDashboardService class "+ e);
		}
		return statusMap;
	}

	private List<GrivenceStatusVO> getTypeOfIssueStatusWiseComplaintCount(List<Object[]> objList) {
		List<GrivenceStatusVO> typeOfIssueList = new ArrayList<GrivenceStatusVO>(0);
		try {
			if (objList != null && objList.size() > 0) {
				Map<String, Map<String, GrivenceStatusVO>> typeOfIssueMap = new LinkedHashMap<String, Map<String, GrivenceStatusVO>>(0);
				for (Object[] param : objList) {
					Map<String, GrivenceStatusVO> statusMap = typeOfIssueMap.get(getInUpperCase(commonMethodsUtilService.getStringValueForObject(param[0]).trim()));// typeOfIssue
					if (statusMap == null) {
						statusMap = new LinkedHashMap<String, GrivenceStatusVO>();
						typeOfIssueMap.put(getInUpperCase(commonMethodsUtilService.getStringValueForObject(param[0]).trim()),statusMap);
					}
					String status = commonMethodsUtilService.getStringValueForObject(param[2]).trim();
					GrivenceStatusVO statusVO = statusMap.get(getInUpperCase(status));
					if (statusVO == null) {
						statusVO = new GrivenceStatusVO();
						statusVO.setName(getInUpperCase(status));
						statusMap.put(statusVO.getName(), statusVO);
					}
					statusVO.setCount(statusVO.getCount()+ commonMethodsUtilService.getLongValueForObject(param[3]));
				}
				
				//Preparing list
				if (typeOfIssueMap.size() > 0) {
					for (Entry<String, Map<String, GrivenceStatusVO>> typeOfIssueEntry : typeOfIssueMap.entrySet()) {
						GrivenceStatusVO typeOfIssueVO = new GrivenceStatusVO();
						typeOfIssueVO.setName(typeOfIssueEntry.getKey());
						 if (typeOfIssueEntry.getValue() != null ) {
							 for (Entry<String, GrivenceStatusVO> statusEntry : typeOfIssueEntry.getValue().entrySet()) {
								 typeOfIssueVO.getSubList().add(statusEntry.getValue());
								 typeOfIssueVO.setCount(typeOfIssueVO.getCount()+statusEntry.getValue().getCount());
							}
						 }
						typeOfIssueList.add(typeOfIssueVO);
					}
				}
			}

		} catch (Exception e) {
			Log.error("Exception raised at getTypeOfIssueStatusWiseComplaintCount() in  LocationDashboardService class "+ e);
		}
		return typeOfIssueList;
	}
   
	private List<GrivenceStatusVO> getTypeOfIssueIssueTypeStatusWiseComplaintCount(List<Object[]> objList, Map<String, GrivenceStatusVO> sttsMap) {
		List<GrivenceStatusVO> typeOfIssueList = new ArrayList<GrivenceStatusVO>(0);
		try {
			if (objList != null && objList.size() > 0) {
				Map<String, Map<String, Map<String, GrivenceStatusVO>>> typeOfIssueIssueTypeMap = new LinkedHashMap<String, Map<String, Map<String, GrivenceStatusVO>>>(0);
				for (Object[] param : objList) {
					Map<String, Map<String, GrivenceStatusVO>> issueTypeMap = typeOfIssueIssueTypeMap.get(getInUpperCase(commonMethodsUtilService.getStringValueForObject(param[0]).trim()));// typeOfIssue
					if (issueTypeMap == null) {
						issueTypeMap = new LinkedHashMap<String, Map<String, GrivenceStatusVO>>(0);
						typeOfIssueIssueTypeMap.put(getInUpperCase(commonMethodsUtilService.getStringValueForObject(param[0])).trim(),issueTypeMap);
					}
					Map<String, GrivenceStatusVO> statusMap = issueTypeMap.get(getInUpperCase(commonMethodsUtilService.getStringValueForObject(param[1])));//issue type
					if (statusMap == null) {
						statusMap = new LinkedHashMap<String, GrivenceStatusVO>();
						issueTypeMap.put(getInUpperCase(commonMethodsUtilService.getStringValueForObject(param[1])), statusMap);
					}
					String status = commonMethodsUtilService.getStringValueForObject(param[2]).trim();
					GrivenceStatusVO statusVO = statusMap.get(getInUpperCase(status));
					if (statusVO == null) {
						statusVO = new GrivenceStatusVO();
						statusVO.setName(getInUpperCase(status));
						statusMap.put(statusVO.getName(), statusVO);
					}
					statusVO.setCount(commonMethodsUtilService.getLongValueForObject(param[3]));
				}

				// Preparing list
				if (typeOfIssueIssueTypeMap.size() > 0) {
					for (Entry<String, Map<String, Map<String, GrivenceStatusVO>>> typeOfIssueEntry : typeOfIssueIssueTypeMap.entrySet()) {
						GrivenceStatusVO typeOfIssueVO = new GrivenceStatusVO();
						typeOfIssueVO.setName(typeOfIssueEntry.getKey());

						if (typeOfIssueEntry.getValue() != null) {
							for (Entry<String, Map<String, GrivenceStatusVO>> issueTypeEntry : typeOfIssueEntry.getValue().entrySet()) {
								GrivenceStatusVO issueTypeVO = new GrivenceStatusVO();
								issueTypeVO.setName(issueTypeEntry.getKey());
								if (issueTypeEntry != null) {
									Map<String, GrivenceStatusVO> statusMap = issueTypeEntry.getValue();
									if (sttsMap != null) {
										for (Entry<String, GrivenceStatusVO> statusEntry : sttsMap.entrySet()) {
											if (statusMap.get(statusEntry.getKey()) != null) {
												issueTypeVO.getSubList().add(statusMap.get(statusEntry.getKey()));
												issueTypeVO.setCount(issueTypeVO.getCount()+ statusMap.get(statusEntry.getKey()).getCount());
												typeOfIssueVO.setCount(typeOfIssueVO.getCount() + statusMap.get(statusEntry.getKey()).getCount());
											} else {
												 GrivenceStatusVO statusVO = (GrivenceStatusVO) statusEntry.getValue().clone();
												 statusVO.setCount(0l);
												 issueTypeVO.getSubList().add(statusVO);
											}
										}
									}
								}
								typeOfIssueVO.getSubList().add(issueTypeVO);
							}
						}
						typeOfIssueList.add(typeOfIssueVO);
					}
				}
			}

		} catch (Exception e) {
			Log.error("Exception raised at getTypeOfIssueStatusWiseComplaintCount() in  LocationDashboardService class "+ e);
		}
		return typeOfIssueList;
	}
	/**
	 * @param String fromDateStr
	 * @param String toDateStr
	 * @param Long locationTypeId
	 * @param List<Long> locationValues
	 * @param String year
	 * @param Long stateId
	 * @return List<GrivenceStatusVO>
	 * @author Santosh Kumar Verma
	 * @Description :This Service Method is used to get grivence complaint count department wise. 
	 * @Date 21-OCT-2017
	 */
	public List<GrivenceStatusVO> getGrivenceComplaintCountDepartmentWise(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year,Long stateId) {
		List<GrivenceStatusVO> resultList = new ArrayList<GrivenceStatusVO>(0);
		try{
			Date fromDate = getDates(fromDateStr,toDateStr)[0];
			Date toDate = getDates(fromDateStr,toDateStr)[1];
			
			List<Object[]> grivenceDeptDtlsObjList = insuranceStatusDAO.getGrievanceDepartmentWiseComplaintCnt(locationTypeId, locationValues, stateId, fromDate, toDate);
			
			Map<String,GrivenceStatusVO> statusMap = getAllStatus(grivenceDeptDtlsObjList,"department");//getting status wise complaint count
			
			if (grivenceDeptDtlsObjList != null && grivenceDeptDtlsObjList.size() > 0) {
				Map<String, GrivenceStatusVO> deptMap = new HashMap<String, GrivenceStatusVO>(0);
				for (Object[] param : grivenceDeptDtlsObjList) {

					GrivenceStatusVO deptVO = deptMap.get(getInUpperCase(commonMethodsUtilService.getStringValueForObject(param[0])));
					if (deptVO == null) {
						deptVO = new GrivenceStatusVO();
						deptVO.setName(getInUpperCase(commonMethodsUtilService.getStringValueForObject(param[0])));
						deptVO.getSubList().addAll(getRequiredTemplate(statusMap));//getting template
						deptMap.put(deptVO.getName(), deptVO);
					}
					String status = commonMethodsUtilService.getStringValueForObject(param[1]).trim();
					GrivenceStatusVO matchVO = getGrivanceStastusMatchVO(deptVO.getSubList(),getInUpperCase(status));
					if (matchVO != null) {
						matchVO.setCount(commonMethodsUtilService.getLongValueForObject(param[2]));
						deptVO.setCount(deptVO.getCount() + matchVO.getCount());
					}

				}
				resultList.addAll(deptMap.values());
			}
			
		}catch(Exception e){
			Log.error("Exception raised at getGrivenceComplaintCountDepartmentWise() in  LocationDashboardService class "+e);
		}
		return resultList;
	}

	private GrivenceStatusVO getGrivanceStastusMatchVO(List<GrivenceStatusVO> list, String status) {
		try {
			if (list == null || list.size() == 0) {
				return null;
			}
			for (GrivenceStatusVO grivenceStatusVO : list) {
				if (grivenceStatusVO.getName().equalsIgnoreCase(status)) {
					return grivenceStatusVO;
				}
			}
		} catch (Exception e) {
			Log.error("Exception raised at getGrivanceStastusMatchVO() in  LocationDashboardService class "+ e);
		}
		return null;
	}
	private Map<String, GrivenceStatusVO> getAllStatus(List<Object[]> objList,String type) {
		Map<String, GrivenceStatusVO> statusMap = new LinkedHashMap<String, GrivenceStatusVO>();
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					String status = "";
					if (type.equalsIgnoreCase("department")) {
						 status = commonMethodsUtilService.getStringValueForObject(param[1]).trim();	
					} else if (type.equalsIgnoreCase("trustEducation")) {
						 status = commonMethodsUtilService.getStringValueForObject(param[3]).trim();
					}
					
					GrivenceStatusVO statusVO = statusMap.get(getInUpperCase(status));
					if (statusVO == null) {
						statusVO = new GrivenceStatusVO();
						statusVO.setName(getInUpperCase(status));
						statusMap.put(statusVO.getName(), statusVO);
					}
				}
			}

		} catch (Exception e) {
			Log.error("Exception raised at getAllStatus() in  LocationDashboardService class "+ e);
		}
		return statusMap;
	}
	/**
	 * @param String fromDateStr
	 * @param String toDateStr
	 * @param Long locationTypeId
	 * @param List<Long> locationValues
	 * @param String year
	 * @param Long stateId
	 * @return GrivenceStatusVO
	 * @author Santosh Kumar Verma
	 * @Description :This Service Method is used to get grivence financial support amount type of issue wise. 
	 * @Date 21-OCT-2017
	 */
	public GrivenceStatusVO getGrivenceFinancialSupportDtls(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year,Long stateId) {
		GrivenceStatusVO resultVO = new GrivenceStatusVO();
		try{
			Date fromDate = getDates(fromDateStr,toDateStr)[0];
			Date toDate = getDates(fromDateStr,toDateStr)[1];
			
			List<Object[]> grivenceFinancialSupportDtlsObjList = insuranceStatusDAO.getGrievanceAmountByIssueType(locationTypeId, locationValues, stateId, fromDate, toDate);
			setGrivenceFinancialSupportDtls(grivenceFinancialSupportDtlsObjList, resultVO);
			
			if (grivenceFinancialSupportDtlsObjList != null && grivenceFinancialSupportDtlsObjList.size() > 0 ) {
				Map<String,GrivenceStatusVO> issueTypeMap = new HashMap<String, GrivenceStatusVO>();
				for (Object[] param : grivenceFinancialSupportDtlsObjList) {
					String status = commonMethodsUtilService.getStringValueForObject(param[2]); 
					String issueType = commonMethodsUtilService.getStringValueForObject(param[1]);
					GrivenceStatusVO issueTypeVO = issueTypeMap.get(getInUpperCase(issueType));
					if (issueTypeVO == null ) {
						 issueTypeVO = new GrivenceStatusVO();
						 issueTypeVO.setName(getInUpperCase(issueType));
						 issueTypeMap.put(issueTypeVO.getName(), issueTypeVO);
					 }
					issueTypeVO.setExpectedAmount(issueTypeVO.getExpectedAmount()+commonMethodsUtilService.getLongValueForObject(param[4]));
					issueTypeVO.setMemberCount(issueTypeVO.getMemberCount()+commonMethodsUtilService.getLongValueForObject(param[3]));
					 if (status.equalsIgnoreCase("Completed")) {
						 issueTypeVO.setCompletedCount(issueTypeVO.getCompletedCount()+commonMethodsUtilService.getLongValueForObject(param[5]));
					 } else if (status.equalsIgnoreCase("approved")) {
						 issueTypeVO.setApprovedAmount(issueTypeVO.getApprovedAmount()+commonMethodsUtilService.getLongValueForObject(param[5]));
					 }
					 issueTypeVO.setTotalAmount(issueTypeVO.getTotalAmount()+commonMethodsUtilService.getLongValueForObject(param[5]));//total amount = approved amount+completed amount
				}
				//preparing list
				resultVO.getSubList().addAll(issueTypeMap.values());
			}
			
		}catch(Exception e){
			Log.error("Exception raised at getGrivenceFinancialSupportDtls() in  LocationDashboardService class "+e);
		}
		return resultVO;
	}

	public void setGrivenceFinancialSupportDtls(List<Object[]> objList,GrivenceStatusVO resultVO) {
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					String typeOfIssue = commonMethodsUtilService.getStringValueForObject(param[0]);
					if (typeOfIssue.equalsIgnoreCase("Govt")) {
						/*resultVO.setMemberCount(resultVO.getMemberCount() + commonMethodsUtilService.getLongValueForObject(param[3]));*/
						resultVO.setGovtCount(resultVO.getGovtCount() + commonMethodsUtilService.getLongValueForObject(param[4]));
						resultVO.setGovtCount(resultVO.getGovtCount() + commonMethodsUtilService.getLongValueForObject(param[5]));
					} else if (typeOfIssue.equalsIgnoreCase("Party") || typeOfIssue.equalsIgnoreCase("Welfare")) {
						resultVO.setMemberCount(resultVO.getMemberCount() + commonMethodsUtilService.getLongValueForObject(param[3]));
						resultVO.setPartyCount(resultVO.getPartyCount()+ commonMethodsUtilService.getLongValueForObject(param[4]));
						resultVO.setPartyCount(resultVO.getPartyCount() + commonMethodsUtilService.getLongValueForObject(param[5]));
					}
				}
				resultVO.setTotalAmount(resultVO.getGovtCount()+resultVO.getPartyCount());
			}

		} catch (Exception e) {
			Log.error("Exception raised at setGrivenceFinancialSupportDtls() in  LocationDashboardService class "+ e);
		}
	}
	/**
	 * @param String fromDateStr
	 * @param String toDateStr
	 * @param Long locationTypeId
	 * @param List<Long> locationValues
	 * @param String year
	 * @param Long stateId
	 * @return GrivenceStatusVO
	 * @author Santosh Kumar Verma
	 * @Description :This Service Method is used to get location wise grivence complaint count. 
	 * @Date 21-OCT-2017
	 */
	public List<GrivenceStatusVO> getLocationWiseTypeOfIssueGrivenceComplaintCount(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year,Long stateId) {
		List<GrivenceStatusVO> resultList = new ArrayList<GrivenceStatusVO>(0);
		try{
			Date fromDate = getDates(fromDateStr,toDateStr)[0];
			Date toDate = getDates(fromDateStr,toDateStr)[1];
			String groupType = getGroupType(locationTypeId);
			
			if (locationTypeId != null && locationTypeId == IConstants.CONSTITUENCY_SCOPE_ID) {
				List<Object[]> mandalDtlsObjList = insuranceStatusDAO.getLocationWiseGrievanceComplaintCnt(locationTypeId, locationValues, stateId,groupType, fromDate, toDate);
				List<Object[]> locationEleBodyObjList = insuranceStatusDAO.getLocationWiseGrievanceComplaintCnt(locationTypeId, locationValues, stateId,"TownDivision", fromDate, toDate);
				List<Object[]> objList = new ArrayList<Object[]>(0);
				objList.addAll(mandalDtlsObjList);
				objList.addAll(locationEleBodyObjList);
				Map<String,GrivenceStatusVO> typeOfIssueMap = getTypeOfIssue(objList);
				List<GrivenceStatusVO> mandalList = getLocationWiseTypeOfIssueGrivenceComplaintDtls(mandalDtlsObjList, typeOfIssueMap, "1");
				List<GrivenceStatusVO> locationEleBodyList = getLocationWiseTypeOfIssueGrivenceComplaintDtls(locationEleBodyObjList, typeOfIssueMap, "2");
				resultList.addAll(mandalList);
				resultList.addAll(locationEleBodyList);
			} else {
				List<Object[]> typeOfIssueObjList = insuranceStatusDAO.getLocationWiseGrievanceComplaintCnt(locationTypeId, locationValues, stateId,groupType, fromDate, toDate);
				Map<String,GrivenceStatusVO> typeOfIssueMap = getTypeOfIssue(typeOfIssueObjList);
				List<GrivenceStatusVO> locationDtlsList = getLocationWiseTypeOfIssueGrivenceComplaintDtls(typeOfIssueObjList, typeOfIssueMap, "");
				resultList.addAll(locationDtlsList);
			}
			if(resultList != null && resultList.size()>0){
				Collections.sort(resultList, locationIdSorting);
			}

		}catch(Exception e){
			Log.error("Exception raised at getLocationWiseTypeOfIssueGrivenceComplaintCount() in  LocationDashboardService class "+e);
		}
		return resultList;
	}
	private List<GrivenceStatusVO> getLocationWiseTypeOfIssueGrivenceComplaintDtls(List<Object[]> objList,Map<String,GrivenceStatusVO> typeOfIssueMap,String locationTypeIdStr) {
		List<GrivenceStatusVO> resultList = new ArrayList<GrivenceStatusVO>(0);
		try {
			if (objList != null && objList.size() > 0 ) {
				Map<String,GrivenceStatusVO> locationMap = new HashMap<String, GrivenceStatusVO>();
				for (Object[] param : objList) {
					String locationId = commonMethodsUtilService.getStringValueForObject(param[0]); 
					GrivenceStatusVO locationVO = locationMap.get((locationTypeIdStr+locationId).trim());
					if (locationVO == null ) {
						locationVO = new GrivenceStatusVO();
						locationVO.setLocationIdStr((locationTypeIdStr+locationId).trim());
						locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						locationVO.getSubList().addAll(getRequiredTemplate(typeOfIssueMap));//getting template
						locationMap.put(locationVO.getLocationIdStr(), locationVO);
					 }
					 String typeOfIssue = commonMethodsUtilService.getStringValueForObject(param[2]).trim();
					 GrivenceStatusVO matchVO = getGrivanceStastusMatchVO(locationVO.getSubList(),getInUpperCase(typeOfIssue));
					if (matchVO != null) {
						matchVO.setCount(commonMethodsUtilService.getLongValueForObject(param[3]));
						locationVO.setCount(locationVO.getCount() + matchVO.getCount());
					}
				}
				//preparing list
				resultList.addAll(locationMap.values());
			}
		} catch (Exception e) {
			Log.error("Exception raised at getLocationWiseTypeOfIssueGrivenceComplaintDtls() in  LocationDashboardService class "+e);
		}
		return resultList;
	}
	public static Comparator<GrivenceStatusVO> locationIdSorting = new Comparator<GrivenceStatusVO>()
	{
		@Override
		public int compare(GrivenceStatusVO gsvo1, GrivenceStatusVO gsvo2) {
			
			return(Long.valueOf(gsvo1.getLocationIdStr()).intValue() - Long.valueOf(gsvo2.getLocationIdStr()).intValue());
		}
		
	};
	private Map<String,GrivenceStatusVO> getTypeOfIssue(List<Object[]> objList) {
		Map<String,GrivenceStatusVO> typeOfIssueMap = new HashMap<String, GrivenceStatusVO>(0);
		 try {
			  if (objList != null && objList.size() > 0 ) {
				  for (Object[] param : objList) {
					  String typeOfIssue = commonMethodsUtilService.getStringValueForObject(param[2]).trim();
					if (!typeOfIssueMap.containsKey(getInUpperCase(typeOfIssue))) {
						GrivenceStatusVO typeOfIssueVO = new GrivenceStatusVO();
						typeOfIssueVO.setName(getInUpperCase(typeOfIssue));
						typeOfIssueMap.put(typeOfIssueVO.getName(), typeOfIssueVO);
					}
				}
			  }
		 } catch (Exception e) {
			 Log.error("Exception raised at getTypeOfIssue() in  LocationDashboardService class "+e);
		 }
		 return typeOfIssueMap;
	}
	/**
	 * @param String fromDateStr
	 * @param String toDateStr
	 * @param Long locationTypeId
	 * @param List<Long> locationValues
	 * @param Long stateId
	 * @return GrivenceStatusVO
	 * @author Santosh Kumar Verma
	 * @Description :This Service Method is used to get insurance overview details. 
	 * @Date 23-OCT-2017
	 */
	public GrivenceStatusVO getInsuranceOverviewDetails(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year,Long stateId) {
		GrivenceStatusVO resultVO = new GrivenceStatusVO();
		try{
			
			Date fromDate = getDates(fromDateStr,toDateStr)[0];
			Date toDate = getDates(fromDateStr,toDateStr)[1];
			
			List<Object[]> grivenceDtlsObjList = insuranceStatusDAO.getInsuranceIssueTypeAndStatusWiseComplaintCnt(locationTypeId, locationValues, stateId, fromDate, toDate);
			
			Map<Long,GrivenceStatusVO> statusMap = getInsuranceStatusWiseIssueTypeComplaintCount(grivenceDtlsObjList);//getting status wise complaint count
			Map<String, GrivenceStatusVO> issueTypeMap = getInsuranceTypeOfIssueStatusWiseComplaintCount(grivenceDtlsObjList,statusMap);//getting type of issue and status wise complaint count
			
			resultVO.getSubList().addAll(statusMap.values());
			resultVO.setSubList1(new ArrayList<GrivenceStatusVO>(issueTypeMap.values()));
			
			
		}catch(Exception e){
			Log.error("Exception raised at getInsuranceOverviewDetails() in  LocationDashboardService class "+e);
		}
		return resultVO;
	}

	private Map<Long, GrivenceStatusVO> getInsuranceStatusWiseIssueTypeComplaintCount(List<Object[]> objList) {
		Map<Long, GrivenceStatusVO> typeOfIssueMap = new HashMap<Long, GrivenceStatusVO>(0);
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					String typeOfIssue = commonMethodsUtilService.getStringValueForObject(param[0]);
					GrivenceStatusVO statusVO = typeOfIssueMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));
					if (statusVO == null) {
						statusVO = new GrivenceStatusVO();
						statusVO.setId(commonMethodsUtilService.getLongValueForObject(param[1]));
						statusVO.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
						statusVO.getSubList().addAll(getTypeOfIssueTemplate());//getting template
						typeOfIssueMap.put(statusVO.getId(), statusVO);
					}
					GrivenceStatusVO issueTypeMatchVO = getGrivanceStastusMatchVO(statusVO.getSubList(),getInUpperCase(typeOfIssue));
					if (issueTypeMatchVO != null) {
						issueTypeMatchVO.setCount(commonMethodsUtilService.getLongValueForObject(param[3]));
						statusVO.setCount(statusVO.getCount() + issueTypeMatchVO.getCount());
					}
				}
			}

		} catch (Exception e) {
			Log.error("Exception raised at getInsuranceStatusWiseIssueTypeComplaintCount() in  LocationDashboardService class "+ e);
		}
		return typeOfIssueMap;

	}
	
	private Map<String, GrivenceStatusVO> getInsuranceTypeOfIssueStatusWiseComplaintCount(List<Object[]> objList,Map<Long,GrivenceStatusVO> statusMap) {
		Map<String, GrivenceStatusVO> typeOfIssueMap = new HashMap<String, GrivenceStatusVO>(0);
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					String typeOfIssue = commonMethodsUtilService.getStringValueForObject(param[0]);
					GrivenceStatusVO typeOfIssueVO = typeOfIssueMap.get(getInUpperCase(typeOfIssue));
					if (typeOfIssueVO == null) {
						typeOfIssueVO = new GrivenceStatusVO();
						typeOfIssueVO.setName(getInUpperCase(typeOfIssue));
						typeOfIssueVO.getSubList().addAll(getRequiredTemplate(statusMap));//getting template
						typeOfIssueMap.put(typeOfIssueVO.getName(), typeOfIssueVO);
					}
					GrivenceStatusVO statusMatchVO = getInsuranceStatusmatchVO(typeOfIssueVO.getSubList(),commonMethodsUtilService.getLongValueForObject(param[1]));
					if (statusMatchVO != null) {
						statusMatchVO.setCount(commonMethodsUtilService.getLongValueForObject(param[3]));
						typeOfIssueVO.setCount(typeOfIssueVO.getCount() + statusMatchVO.getCount());
					}
				}
			}

		} catch (Exception e) {
			Log.error("Exception raised at getInsuranceTypeOfIssueStatusWiseComplaintCount() in  LocationDashboardService class "+ e);
		}
		return typeOfIssueMap;

	}
	private List<GrivenceStatusVO> getTypeOfIssueTemplate() {
		List<GrivenceStatusVO> typeOfIssueList = new ArrayList<GrivenceStatusVO>(0);
		try {
			GrivenceStatusVO deathVO = new GrivenceStatusVO();
			deathVO.setName("DEATH");
			GrivenceStatusVO hospitalizationVO = new GrivenceStatusVO();
			hospitalizationVO.setName("HOSPITALIZATION");
			typeOfIssueList.add(deathVO);
			typeOfIssueList.add(hospitalizationVO);
		} catch (Exception e) {
			Log.error("Exception raised at getTypeOfIssueTemplate() in  LocationDashboardService class "+e);
		}
		return typeOfIssueList;
	}
	/**
	 * @param String fromDateStr
	 * @param String toDateStr
	 * @param Long locationTypeId
	 * @param List<Long> locationValues
	 * @param String year
	 * @param Long stateId
	 * @return List<GrivenceStatusVO>
	 * @author Santosh Kumar Verma
	 * @Description :This Service Method is used to get location wise insurance complaint count. 
	 * @Date 23-OCT-2017
	 */
	public List<GrivenceStatusVO> getLocationWiseInsuranceIssueTypeComplaintCount(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year,Long stateId) {
		List<GrivenceStatusVO> resultList = new ArrayList<GrivenceStatusVO>(0);
		try{
			Date fromDate = getDates(fromDateStr,toDateStr)[0];
			Date toDate = getDates(fromDateStr,toDateStr)[1];
			String groupType = getGroupType(locationTypeId);
			
			if (locationTypeId != null && locationTypeId == IConstants.CONSTITUENCY_SCOPE_ID) {
				List<Object[]> mandalDtlsObjList = insuranceStatusDAO.getLocationWiseInsuranceIssueTypeComplaintCnt(locationTypeId, locationValues, stateId,groupType, fromDate, toDate);
				List<Object[]> locationEleBodyObjList = insuranceStatusDAO.getLocationWiseInsuranceIssueTypeComplaintCnt(locationTypeId, locationValues, stateId,"TownDivision", fromDate, toDate);
				List<Object[]> objList = new ArrayList<Object[]>(0);
				objList.addAll(mandalDtlsObjList);
				objList.addAll(locationEleBodyObjList);
				Map<Long,GrivenceStatusVO> typeOfIssueMap = getInsuranceStatusMap(objList);
				List<GrivenceStatusVO> mandalList = getLocationWiseInsuranceComplaintDtls(mandalDtlsObjList, typeOfIssueMap, "1");
				List<GrivenceStatusVO> locationEleBodyList = getLocationWiseInsuranceComplaintDtls(locationEleBodyObjList, typeOfIssueMap, "2");
				resultList.addAll(mandalList);
				resultList.addAll(locationEleBodyList);
			} else {
				List<Object[]> typeOfIssueObjList = insuranceStatusDAO.getLocationWiseInsuranceIssueTypeComplaintCnt(locationTypeId, locationValues, stateId,groupType, fromDate, toDate);
				Map<Long,GrivenceStatusVO> typeOfIssueMap = getInsuranceStatusMap(typeOfIssueObjList);
				List<GrivenceStatusVO> locationDtlsList = getLocationWiseInsuranceComplaintDtls(typeOfIssueObjList, typeOfIssueMap, "");
				resultList.addAll(locationDtlsList);
			}
			if(resultList != null && resultList.size()>0){
				Collections.sort(resultList, locationIdSorting);
			}
		}catch(Exception e){
			Log.error("Exception raised at getLocationWiseInsuranceIssueTypeComplaintCount() in  LocationDashboardService class "+e);
		}
		return resultList;
	}
	private Map<Long,GrivenceStatusVO> getInsuranceStatusMap(List<Object[]> objList) {
		Map<Long,GrivenceStatusVO> statusMap = new HashMap<Long, GrivenceStatusVO>(0); 
		try {
			 if (objList != null && objList.size() > 0) {
				 for (Object[] param : objList) {
					GrivenceStatusVO statusVO = statusMap.get(commonMethodsUtilService.getLongValueForObject(param[3]));
					  if (statusVO == null ) {
						  statusVO = new GrivenceStatusVO();
						  statusVO.setId(commonMethodsUtilService.getLongValueForObject(param[3]));
						  statusVO.setName(commonMethodsUtilService.getStringValueForObject(param[4]));
						  statusMap.put(statusVO.getId(), statusVO);
					  }
				}
			 }
		 } catch (Exception e) {
			 Log.error("Exception raised at getInsuranceStatusMap() in  LocationDashboardService class "+e);
		 }
		return statusMap;
	}
	private List<GrivenceStatusVO> getLocationWiseInsuranceComplaintDtls(List<Object[]> objList,Map<Long,GrivenceStatusVO> statusMap,String locationTypeIdStr) {
		List<GrivenceStatusVO> resultList = new ArrayList<GrivenceStatusVO>(0);
		try {
			if (objList != null && objList.size() > 0 ) {
				Map<String,GrivenceStatusVO> locationMap = new HashMap<String, GrivenceStatusVO>();
				for (Object[] param : objList) {
					String locationId = commonMethodsUtilService.getStringValueForObject(param[0]); 
					GrivenceStatusVO locationVO = locationMap.get((locationTypeIdStr+locationId).trim());
					if (locationVO == null ) {
						locationVO = new GrivenceStatusVO();
						locationVO.setLocationIdStr((locationTypeIdStr+locationId).trim());
						locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						locationVO.getSubList().addAll(getIssueTypeTemplate(statusMap));//getting template
						locationMap.put(locationVO.getLocationIdStr(), locationVO);
					 }
					 String issueType = commonMethodsUtilService.getStringValueForObject(param[2]).trim();
					 GrivenceStatusVO issueTypeMatchVO = getGrivanceStastusMatchVO(locationVO.getSubList(),getInUpperCase(issueType));
					 if (issueTypeMatchVO != null) {
						 GrivenceStatusVO statusMatchVO = getInsuranceStatusmatchVO(issueTypeMatchVO.getSubList(), commonMethodsUtilService.getLongValueForObject(param[3]));
						 if (statusMatchVO != null) {
							 statusMatchVO.setCount(commonMethodsUtilService.getLongValueForObject(param[5]));
							 issueTypeMatchVO.setCount(issueTypeMatchVO.getCount() + statusMatchVO.getCount());
							 locationVO.setCount(locationVO.getCount()+statusMatchVO.getCount());
						}
					 }
					}
				//preparing list
				resultList.addAll(locationMap.values());
			}
		} catch (Exception e) {
			Log.error("Exception raised at getLocationWiseInsuranceComplaintDtls() in  LocationDashboardService class "+e);
		}
		return resultList;
	}

	private List<GrivenceStatusVO> getIssueTypeTemplate(Map<Long, GrivenceStatusVO> statusMap) {
		List<GrivenceStatusVO> issueTypeList = new ArrayList<GrivenceStatusVO>(0);
		try {
			for (GrivenceStatusVO issueTypeVO : getTypeOfIssueTemplate()) {
				GrivenceStatusVO issTypVO = (GrivenceStatusVO) issueTypeVO.clone();
				if (statusMap != null && statusMap.size() > 0) {
					for (Entry<Long, GrivenceStatusVO> statusEntry : statusMap.entrySet()) {
						issTypVO.getSubList().add((GrivenceStatusVO) statusEntry.getValue().clone());
					}
				}
				issueTypeList.add(issTypVO);
			}
		} catch (Exception e) {
			Log.error("Exception raised at getIssueTypeTemplate() in  LocationDashboardService class "+ e);
		}
		return issueTypeList;
	}
	private List<GrivenceStatusVO> getRequiredTemplate(Map<?, GrivenceStatusVO> statusMap) {
		List<GrivenceStatusVO> issueTypeList = new ArrayList<GrivenceStatusVO>(0);
		try {
			if (statusMap != null && statusMap.size() > 0) {
				for (Entry<?, GrivenceStatusVO> statusEntry : statusMap.entrySet()) {
					 GrivenceStatusVO vo = (GrivenceStatusVO) statusEntry.getValue().clone();
					 vo.setCount(0l);
					issueTypeList.add(vo);
				}
			}
		} catch (Exception e) {
			Log.error("Exception raised at getRequiredTemplate() in  LocationDashboardService class "+ e);
		}
		return issueTypeList;
	}
	private GrivenceStatusVO getInsuranceStatusmatchVO(List<GrivenceStatusVO> list, Long statusId) {
		try {
			if (list == null || list.size() == 0) {
				return null;
			}
			for (GrivenceStatusVO insuranceStatusVO : list) {
				if (insuranceStatusVO.getId() == statusId.longValue()) {
					return insuranceStatusVO;
				}
			}
		} catch (Exception e) {
			Log.error("Exception raised at getInsuranceStatusmatchVO() in  LocationDashboardService class "+ e);
		}
		return null;
	}
	/**
	 * @param String fromDateStr
	 * @param String toDateStr
	 * @param Long locationTypeId
	 * @param List<Long> locationValues
	 * @param Long stateId
	 * @return GrivenceStatusVO
	 * @author Santosh Kumar Verma
	 * @Description :This Service Method is used to get location wise Trust Education overview details. 
	 * @Date 23-OCT-2017
	 */
	public GrivenceStatusVO getTrustEducationOverviewDetails(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year,Long stateId) {
		GrivenceStatusVO resultVO = new GrivenceStatusVO();
		try{
			
			Date fromDate = getDates(fromDateStr,toDateStr)[0];
			Date toDate = getDates(fromDateStr,toDateStr)[1];
			
			List<Object[]> trustEduDtlsObjList = insuranceStatusDAO.getTrustEducationComplaintCnt(locationTypeId, locationValues, stateId, fromDate, toDate);
			
			Map<String,GrivenceStatusVO> statusMap = getStatusWiseComplaintCount(trustEduDtlsObjList);//getting status wise complaint count
			List<GrivenceStatusVO> supportPurposeList = getTrustEducationSupportForWiseComplaintCount(trustEduDtlsObjList,statusMap);//getting type of issue and status wise complaint count
			Map<String, GrivenceStatusVO> supportPurposeDtlsMap = getTrustEducationSupportPurPoseWiseComplaintCount(trustEduDtlsObjList,statusMap);//getting type of issue and status wise complaint count
			
			resultVO.getSubList().addAll(statusMap.values());
			resultVO.setSubList1(new ArrayList<GrivenceStatusVO>(supportPurposeDtlsMap.values()));
			resultVO.setSubList2(supportPurposeList);
			
			
		}catch(Exception e){
			Log.error("Exception raised at getTrustEducationOverviewDetails() in  LocationDashboardService class "+e);
		}
		return resultVO;
	}
	private List<GrivenceStatusVO> getTrustEducationSupportForWiseComplaintCount(List<Object[]> objList,Map<String,GrivenceStatusVO> statusMap) {
		List<GrivenceStatusVO>  subjectPurposeList = new ArrayList<GrivenceStatusVO>(0); 
		try {
			 if (objList != null && objList.size() > 0 ) {
				 Map<String,Map<String,GrivenceStatusVO>> supportPurposeMap = new HashMap<String,Map<String,GrivenceStatusVO>>(0);
				 for (Object[] param : objList) {
					  Map<String,GrivenceStatusVO> supportForMap = supportPurposeMap.get(getInUpperCase(commonMethodsUtilService.getStringValueForObject(param[0])));
					  if (supportForMap == null ) {
						  supportForMap = new HashMap<String, GrivenceStatusVO>();
						  supportPurposeMap.put(getInUpperCase(commonMethodsUtilService.getStringValueForObject(param[0])), supportForMap);
					  }
					  GrivenceStatusVO subjectForVO = supportForMap.get(getInUpperCase(commonMethodsUtilService.getStringValueForObject(param[1])));
					   if (subjectForVO == null) {
						   subjectForVO = new GrivenceStatusVO();
						   subjectForVO.setName(getInUpperCase(commonMethodsUtilService.getStringValueForObject(param[1])));
						   subjectForVO.getSubList().addAll(getRequiredTemplate(statusMap)); // getting template
						   supportForMap.put(subjectForVO.getName(), subjectForVO);
					
					   }
					   GrivenceStatusVO statusMatchVO = getGrivanceStastusMatchVO(subjectForVO.getSubList(), getInUpperCase(commonMethodsUtilService.getStringValueForObject(param[2])));
					   if (statusMatchVO != null) {
						   statusMatchVO.setCount(commonMethodsUtilService.getLongValueForObject(param[3]));
						   subjectForVO.setCount(subjectForVO.getCount()+statusMatchVO.getCount());
					   }
				}
				 //preparing list
				 if (supportPurposeMap.size() > 0) {
					 for (Entry<String, Map<String, GrivenceStatusVO>> subjectPuroseEntry : supportPurposeMap.entrySet()) {
						  GrivenceStatusVO subjectPurposeVO = new GrivenceStatusVO();
						  subjectPurposeVO.setName(subjectPuroseEntry.getKey());
						  subjectPurposeVO.getSubList().addAll(subjectPuroseEntry.getValue().values());
						  subjectPurposeList.add(subjectPurposeVO);
					}
				 }
			 }
			 
		 } catch (Exception e) {
			 Log.error("Exception raised at getTrustEducationSupportForWiseComplaintCount() in  LocationDashboardService class "+e);
		 }
		 return subjectPurposeList;
	}
	private Map<String,GrivenceStatusVO> getTrustEducationSupportPurPoseWiseComplaintCount(List<Object[]> objList,Map<String,GrivenceStatusVO> statusMap) {
		Map<String,GrivenceStatusVO> supportPurposeMap = new HashMap<String, GrivenceStatusVO>(0);
		 try {
			 if (objList != null && objList.size() > 0 ) {
				 for (Object[] param : objList) {
					 GrivenceStatusVO supportPurPoseVO = supportPurposeMap.get(getInUpperCase(commonMethodsUtilService.getStringValueForObject(param[0])));
					  if (supportPurPoseVO == null ) {
						  supportPurPoseVO = new GrivenceStatusVO();
						  supportPurPoseVO.setName(getInUpperCase(commonMethodsUtilService.getStringValueForObject(param[0])));
						  supportPurPoseVO.getSubList().addAll(getRequiredTemplate(statusMap)); // getting template
						  supportPurposeMap.put(supportPurPoseVO.getName(), supportPurPoseVO);
					  }
					  GrivenceStatusVO statusMatchVO = getGrivanceStastusMatchVO(supportPurPoseVO.getSubList(), getInUpperCase(commonMethodsUtilService.getStringValueForObject(param[2])));
					   if (statusMatchVO != null) {
						   statusMatchVO.setCount(statusMatchVO.getCount()+commonMethodsUtilService.getLongValueForObject(param[3]));
						   supportPurPoseVO.setCount(supportPurPoseVO.getCount()+commonMethodsUtilService.getLongValueForObject(param[3]));
					   }
				}
			 }
			 
		 } catch (Exception e) {
			 Log.error("Exception raised at getTrustEducationSupportPurPoseWiseComplaintCount() in  LocationDashboardService class "+e);
		 }
		 return supportPurposeMap;
	}
	/**
	 * @param String fromDateStr
	 * @param String toDateStr
	 * @param Long locationTypeId
	 * @param List<Long> locationValues
	 * @param String year
	 * @param Long stateId
	 * @return GrivenceStatusVO
	 * @author Santosh Kumar Verma
	 * @Description :This Service Method is used to get trust education subject for details. 
	 * @Date 24-OCT-2017
	 */
	public GrivenceStatusVO getTrustEducationSubjectForDetails(String fromDateStr, String toDateStr, Long locationTypeId,List<Long> locationValues, String year, Long stateId) {
		GrivenceStatusVO resultVO = new GrivenceStatusVO();
		try {

			Date fromDate = getDates(fromDateStr, toDateStr)[0];
			Date toDate = getDates(fromDateStr, toDateStr)[1];

			List<Object[]> trustEduDtlsObjList = insuranceStatusDAO.getTrustEducationComplaintCnt(locationTypeId,locationValues, stateId, fromDate, toDate);

			if (trustEduDtlsObjList != null && trustEduDtlsObjList.size() > 0) {
				Map<String, GrivenceStatusVO> subjectForDtlsMap = new HashMap<String, GrivenceStatusVO>(0);
				for (Object[] param : trustEduDtlsObjList) {
					String subjectPurpose = getInUpperCase(commonMethodsUtilService.getStringValueForObject(param[0])).trim();
					String subjectFor = getInUpperCase(commonMethodsUtilService.getStringValueForObject(param[1])).trim();
					Long complaintCount = commonMethodsUtilService.getLongValueForObject(param[3]);

					if (subjectPurpose.equalsIgnoreCase("FEE CONCESSION")) {
						resultVO.setFeeConsCount(resultVO.getFeeConsCount() + complaintCount);
					} else if (subjectPurpose.equalsIgnoreCase("SEAT")) {
						resultVO.setSeatCount(resultVO.getSeatCount() + complaintCount);
					}

					GrivenceStatusVO subjectForVO = subjectForDtlsMap.get(subjectFor);
					if (subjectForVO == null) {
						subjectForVO = new GrivenceStatusVO();
						subjectForVO.setName(subjectFor);
						subjectForVO.setSubList(getTrustEducationRequiredTemplate());// getting template
						subjectForDtlsMap.put(subjectForVO.getName(),subjectForVO);
					}
					GrivenceStatusVO subjectPurposeMatchVO = getGrivanceStastusMatchVO(subjectForVO.getSubList(), subjectPurpose);
					if (subjectPurposeMatchVO != null) {
						subjectPurposeMatchVO.setCount(subjectPurposeMatchVO.getCount()+complaintCount);
						subjectForVO.setCount(subjectForVO.getCount() + complaintCount);
					}
				}
				// preparing final list
				resultVO.setCount(resultVO.getFeeConsCount() + resultVO.getSeatCount());
				resultVO.getSubList().addAll(subjectForDtlsMap.values());
			}
		} catch (Exception e) {
			Log.error("Exception raised at getTrustEducationSubjectForDetails() in  LocationDashboardService class "+ e);
		}
		return resultVO;
	}
	/**
	 * @param String fromDateStr
	 * @param String toDateStr
	 * @param Long locationTypeId
	 * @param List<Long> locationValues
	 * @param String year
	 * @param Long stateId
	 * @return List<GrivenceStatusVO>
	 * @author Santosh Kumar Verma
	 * @Description :This Service Method is used to get location wise insurance complaint count. 
	 * @Date 23-OCT-2017
	 */
	public List<GrivenceStatusVO> getLocationWiseTrustEducationComplaintCount(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year,Long stateId) {
		List<GrivenceStatusVO> resultList = new ArrayList<GrivenceStatusVO>(0);
		try{
			Date fromDate = getDates(fromDateStr,toDateStr)[0];
			Date toDate = getDates(fromDateStr,toDateStr)[1];
			String groupType = getGroupType(locationTypeId);
			
			if (locationTypeId != null && locationTypeId == IConstants.CONSTITUENCY_SCOPE_ID) {
				List<Object[]> mandalDtlsObjList = insuranceStatusDAO.getLocationWiseTrustEducationComplaintCnt(locationTypeId, locationValues, stateId,groupType, fromDate, toDate);
				List<Object[]> locationEleBodyObjList = insuranceStatusDAO.getLocationWiseTrustEducationComplaintCnt(locationTypeId, locationValues, stateId,"TownDivision", fromDate, toDate);
				List<Object[]> objList = new ArrayList<Object[]>(0);
				objList.addAll(mandalDtlsObjList);
				objList.addAll(locationEleBodyObjList);
				Map<String,GrivenceStatusVO> typeOfIssueMap = getAllStatus(objList,"trustEducation");
				List<GrivenceStatusVO> mandalList = getLocationWiseTrustEducationComplaintDtls(mandalDtlsObjList, typeOfIssueMap, "1");
				List<GrivenceStatusVO> locationEleBodyList = getLocationWiseTrustEducationComplaintDtls(locationEleBodyObjList, typeOfIssueMap, "2");
				resultList.addAll(mandalList);
				resultList.addAll(locationEleBodyList);
			} else {
				List<Object[]> objList = insuranceStatusDAO.getLocationWiseTrustEducationComplaintCnt(locationTypeId, locationValues, stateId,groupType, fromDate, toDate);
				Map<String,GrivenceStatusVO> typeOfIssueMap = getAllStatus(objList,"trustEducation");
				List<GrivenceStatusVO> locationDtlsList = getLocationWiseTrustEducationComplaintDtls(objList, typeOfIssueMap, "");
				resultList.addAll(locationDtlsList);
			}
			if(resultList != null && resultList.size()>0){
				Collections.sort(resultList, locationIdSorting);
			}
			
		}catch(Exception e){
			Log.error("Exception raised at getLocationWiseTrustEducationComplaintCount() in  LocationDashboardService class "+e);
		}
		return resultList;
	}
	private List<GrivenceStatusVO> getLocationWiseTrustEducationComplaintDtls(List<Object[]> objList,Map<String,GrivenceStatusVO> statusMap,String locationTypeIdStr) {
		List<GrivenceStatusVO> resultList = new ArrayList<GrivenceStatusVO>(0);
		try {
			if (objList != null && objList.size() > 0 ) {
				Map<String,GrivenceStatusVO> locationMap = new HashMap<String, GrivenceStatusVO>();
				for (Object[] param : objList) {
					String locationId = commonMethodsUtilService.getStringValueForObject(param[0]); 
					GrivenceStatusVO locationVO = locationMap.get((locationTypeIdStr+locationId).trim());
					if (locationVO == null ) {
						locationVO = new GrivenceStatusVO();
						locationVO.setLocationIdStr((locationTypeIdStr+locationId).trim());
						locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						locationVO.getSubList().addAll(getSubjectPurposeTemplate(statusMap));//getting template
						locationMap.put(locationVO.getLocationIdStr(), locationVO);
					 }
					 String subjectPurpose = commonMethodsUtilService.getStringValueForObject(param[2]).trim();
					 GrivenceStatusVO subjectPurposeMatchVO = getGrivanceStastusMatchVO(locationVO.getSubList(),getInUpperCase(subjectPurpose));
					 if (subjectPurposeMatchVO != null) {
						 GrivenceStatusVO statusMatchVO = getGrivanceStastusMatchVO(subjectPurposeMatchVO.getSubList(), commonMethodsUtilService.getStringValueForObject(param[3]));
						 if (statusMatchVO != null) {
							 statusMatchVO.setCount(commonMethodsUtilService.getLongValueForObject(param[4]));
							 subjectPurposeMatchVO.setCount(subjectPurposeMatchVO.getCount() + statusMatchVO.getCount());
							 locationVO.setCount(locationVO.getCount()+statusMatchVO.getCount());
						}
					 }
					}
				//preparing list
				resultList.addAll(locationMap.values());
			}
		} catch (Exception e) {
			Log.error("Exception raised at getLocationWiseTrustEducationComplaintDtls() in  LocationDashboardService class "+e);
		}
		return resultList;
	}
	private List<GrivenceStatusVO> getSubjectPurposeTemplate(Map<String, GrivenceStatusVO> statusMap) {
		List<GrivenceStatusVO> issueTypeList = new ArrayList<GrivenceStatusVO>(0);
		try {
			for (GrivenceStatusVO subjectPurposeVO : getTrustEducationRequiredTemplate()) {
				GrivenceStatusVO sbjctPrposeVO = (GrivenceStatusVO) subjectPurposeVO.clone();
				if (statusMap != null && statusMap.size() > 0) {
					for (Entry<String, GrivenceStatusVO> statusEntry : statusMap.entrySet()) {
						sbjctPrposeVO.getSubList().add((GrivenceStatusVO) statusEntry.getValue().clone());
					}
				}
				issueTypeList.add(sbjctPrposeVO);
			}
		} catch (Exception e) {
			Log.error("Exception raised at getSubjectPurposeTemplate() in  LocationDashboardService class "+ e);
		}
		return issueTypeList;
	}
	private List<GrivenceStatusVO> getTrustEducationRequiredTemplate() {
		List<GrivenceStatusVO> typeOfIssueList = new ArrayList<GrivenceStatusVO>(0);
		try {
			GrivenceStatusVO deathVO = new GrivenceStatusVO();
			deathVO.setName("FEE CONCESSION");
			GrivenceStatusVO hospitalizationVO = new GrivenceStatusVO();
			hospitalizationVO.setName("SEAT");
			typeOfIssueList.add(deathVO);
			typeOfIssueList.add(hospitalizationVO);
		} catch (Exception e) {
			Log.error("Exception raised at getTrustEducationRequiredTemplate() in  LocationDashboardService class "+e);
		}
		return typeOfIssueList;
	}
	private String getGroupType(Long locationScopeId) {
		String groupType="";
		 try {
			 if (locationScopeId != null ) {
				   if (locationScopeId.longValue() == 2l) { // state
					   groupType = "District";
				   } else if (locationScopeId.longValue() == IConstants.DISTRICT_SCOPE_ID) {
					   groupType = "Constituency";
					} else if (locationScopeId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_SCOPE_ID) {
						groupType = "Constituency";
					} else if (locationScopeId.longValue() == IConstants.CONSTITUENCY_SCOPE_ID) {
						groupType = "Mandal";
					} else if (locationScopeId.longValue() == IConstants.TEHSIL_SCOPE_ID) {
						groupType = "Village";
					} else if (locationScopeId.longValue() == IConstants.MUNICIPAL_CORP_GMC_SCOPE_ID) { // town/division
						groupType = "ward";
					} else if (locationScopeId.longValue() == IConstants.VILLAGE_SCOPE_ID) {
						groupType = "Village";
					} else if (locationScopeId.longValue() == IConstants.WARD_SCOPE_ID) {
						groupType = "ward";
					}
				}
		 } catch (Exception e) {
			 Log.error("Exception raised at getGroupType() in  LocationDashboardService class "+e);
		 }
		 return groupType;
	}
	 private String getInUpperCase(String type) {
	    	String typeInUpperCase ="";
	    	try {
	    		typeInUpperCase = (type.length() > 0) ? type.toUpperCase():type;
	    	} catch (Exception e) {
	    		Log.error("Exception raised at getInUpperCase() in  LocationDashboardService class "+ e);
	    	}
	    	return typeInUpperCase;
	 }
	private Date[] getDates(String fromDateStr, String toDateStr) {
		Date[] dateArr = new Date[2];
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if (fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0) {
				dateArr[0] = sdf.parse(fromDateStr);
				dateArr[1] = sdf.parse(toDateStr);
			}
		} catch (Exception e) {
			Log.error("Exception occured at getDates() method in LocationDashboardService class",e);
		}
		return dateArr;
	}
	public List<MeetingsVO> getMonthTemplate(Map<String,MeetingsVO>  monthTemplateMap) {
		List<MeetingsVO> monthTemlateList = new ArrayList<MeetingsVO>();  
		try {
			  if (monthTemplateMap != null && monthTemplateMap.size() > 0) {
				  for (Entry<String, MeetingsVO> monthEntry : monthTemplateMap.entrySet()) {
					  MeetingsVO monthDtlsVO = new MeetingsVO();
					  monthDtlsVO.setYear(monthEntry.getValue().getYear());
					  monthDtlsVO.setNoOfMonth(monthEntry.getValue().getNoOfMonth());
					  monthDtlsVO.setMonthName(monthEntry.getValue().getMonthName());
					  monthTemlateList.add(monthDtlsVO);
				}
			  }
		  } catch (Exception e){
			  LOG.error("Exception Occured in getMonthTemplate() Method");
		  }
		return monthTemlateList;
		}
	
	/**
	 * @param List<Long> locationScopeIds
	 * @param List<String> subType
	 * @return List<ElectionInformationVO>
	 * @author krishna
	 * @Description :This Service Method is used to get election years. 
	 * @Date 24-OCT-2017
	 */
	public List<ElectionInformationVO> getElectionYearWisePartyDetails(List<Long> electionScopeIdsLst,List<String> subTypes){
		 List<ElectionInformationVO>  resultList = new ArrayList<ElectionInformationVO>();
		try{
			List<String> yearWiseObjsList = electionDAO.getElectionYearByScopeIds(electionScopeIdsLst,subTypes);
			if(yearWiseObjsList != null && yearWiseObjsList.size() >0){
				for(String param : yearWiseObjsList){
					ElectionInformationVO elecionVo = new ElectionInformationVO();
					
					elecionVo.setElectionYear(param);
					resultList.add(elecionVo);
				}
			}
		}catch(Exception e){
			LOG.error("Exception raised at LocationdashBoardService of getLocationWiseMemberDetails()",e);
		}
		return resultList;
	}
	
	public List<KeyValueVO> getLocationWiseElectionDetails(String locationType){
		List<KeyValueVO> finalList = new ArrayList<KeyValueVO>();
		
		try {
			LOG.info("Entered into LocationDashboardService of getLocationWiseElectionDetails() method");
			if(locationType != null && locationType.equalsIgnoreCase("state")){
				
				KeyValueVO districtVO = new KeyValueVO();
				districtVO.setId(3l);
				districtVO.setName("DISTRICT");
						
			   KeyValueVO constituencyVO = new KeyValueVO();
			   constituencyVO.setId(4l);
			   constituencyVO.setName("CONSTITUENCY");
			   
			   KeyValueVO mandalVO = new KeyValueVO();
			   mandalVO.setId(5l);
			   mandalVO.setName("MANDAL");
			   
			   KeyValueVO panchayatVO = new KeyValueVO();
			   panchayatVO.setId(6l);
			   panchayatVO.setName("PANCHAYAT");
			   
				finalList.add(districtVO);
				finalList.add(constituencyVO);
				finalList.add(mandalVO);
				finalList.add(panchayatVO);
			
			}else if(locationType != null && locationType.equalsIgnoreCase("district")){
				 KeyValueVO constituencyVO = new KeyValueVO();
				 constituencyVO.setId(4l);
				 constituencyVO.setName("CONSTITUENCY");
						
				 KeyValueVO mandalVO = new KeyValueVO();
				   mandalVO.setId(5l);
				   mandalVO.setName("MANDAL");
				   
				   KeyValueVO panchayatVO = new KeyValueVO();
				   panchayatVO.setId(6l);
				   panchayatVO.setName("PANCHAYAT");
				   
				finalList.add(constituencyVO);
				finalList.add(mandalVO);
				finalList.add(panchayatVO);
				
			}else if(locationType != null && locationType.equalsIgnoreCase("constituency")){
				 KeyValueVO mandalVO = new KeyValueVO();
				   mandalVO.setId(5l);
				   mandalVO.setName("MANDAL");
				   
				   KeyValueVO panchayatVO = new KeyValueVO();
				   panchayatVO.setId(6l);
				   panchayatVO.setName("PANCHAYAT");
				   
				finalList.add(mandalVO);
				finalList.add(panchayatVO);
				
			}else if(locationType != null && locationType.equalsIgnoreCase("mandal")){
				KeyValueVO mandalVO = new KeyValueVO();
						mandalVO.setId(6l);
						mandalVO.setName("PANCHAYAT");
				finalList.add(mandalVO);
			}
		} catch (Exception e) {
			LOG.error("Exception raised into LocationDashboardService of getLocationWiseElectionDetails() method", e);
		}
		return finalList;
	}
	
	/**
	 * @param Long locationScopeIds
	 * @param List<Long> locationValuesList
	 * @return List<ConstituencyCadreVO>
	 * @author Sai Kumar
	 * @Description :This Service Method is used to get category wise gender count. 
	 * @Date 27-OCT-2017
	 */
	@Override
	public List<ConstituencyCadreVO> getCategoryWiseGenderCount(Long locationScopeId,List<Long> locationValuesList, List<Long> enrollmentYearIdsList) {
		List<ConstituencyCadreVO> finalList=new ArrayList<ConstituencyCadreVO>();
		List<Object[]> categoryWiseGenderCountList=tdpCadreCasteInfoDAO.getCategoryWiseGenderCount(locationScopeId, locationValuesList, enrollmentYearIdsList);
		Map<Long,ConstituencyCadreVO> enrollmentYearMap = new HashMap<Long, ConstituencyCadreVO>();
		Map<Long,Long> enrollmentYearWithCountMap = new HashMap<Long, Long>();
		if(categoryWiseGenderCountList !=null && categoryWiseGenderCountList.size() >0){
		for(Object[] param:categoryWiseGenderCountList) {
			Long enrollmentYearId = commonMethodsUtilService.getLongValueForObject(param[0]);
			Long categoryId = commonMethodsUtilService.getLongValueForObject(param[2]);
			if(enrollmentYearMap.containsKey(enrollmentYearId)) {
				enrollmentYearWithCountMap.put(enrollmentYearId,enrollmentYearWithCountMap.get(enrollmentYearId)+commonMethodsUtilService.getLongValueForObject(param[4]));
				ConstituencyCadreVO enrollmentYearVO = enrollmentYearMap.get(enrollmentYearId);
				List<ConstituencyCadreVO> categorysList = enrollmentYearVO.getCasteGroupList();
				ConstituencyCadreVO matchedCategoryVO = getMatchedConstituencyCadreVO(categorysList,categoryId);
				if(matchedCategoryVO == null){
					ConstituencyCadreVO categoryVO = createCategoryVO(param);
					categorysList.add(categoryVO);
				}else{
					if(commonMethodsUtilService.getStringValueForObject(param[3]).equalsIgnoreCase("M")){
						matchedCategoryVO.setMaleCount(commonMethodsUtilService.getLongValueForObject(param[4]));
					}else{
						matchedCategoryVO.setFemaleCount(commonMethodsUtilService.getLongValueForObject(param[4]));
					}
				}
			}else{
				ConstituencyCadreVO enrollmentYearVO = new ConstituencyCadreVO();
				enrollmentYearVO.setEnrollmentYearId(enrollmentYearId);
				enrollmentYearVO.setEnrollmentYear(commonMethodsUtilService.getStringValueForObject(param[5]));
			
				List<ConstituencyCadreVO> categorysList = new ArrayList<ConstituencyCadreVO>();
				ConstituencyCadreVO categoryVO = createCategoryVO(param);
				categorysList.add(categoryVO);
				enrollmentYearVO.setCasteGroupList(categorysList);
				enrollmentYearMap.put(enrollmentYearId, enrollmentYearVO);
				enrollmentYearWithCountMap.put(enrollmentYearId, commonMethodsUtilService.getLongValueForObject(param[4]));
			}
		}
		}
		if(enrollmentYearMap != null && enrollmentYearMap.size() >0)
		finalList.addAll(enrollmentYearMap.values());
		
		if(finalList !=null && finalList.size() >0){
		for(ConstituencyCadreVO enrollmentYearVO:finalList){
			List<ConstituencyCadreVO> categorysList = enrollmentYearVO.getCasteGroupList();
			for(ConstituencyCadreVO categoryVO:categorysList){
				categoryVO.setToalCadreCount(categoryVO.getToalCadreCount()+categoryVO.getMaleCount()+categoryVO.getFemaleCount());
				enrollmentYearVO.setToalCadreCount(enrollmentYearVO.getToalCadreCount()+categoryVO.getMaleCount()+categoryVO.getFemaleCount());
				enrollmentYearVO.setMaleCount(enrollmentYearVO.getMaleCount()+categoryVO.getMaleCount());
				enrollmentYearVO.setFemaleCount(enrollmentYearVO.getFemaleCount()+categoryVO.getFemaleCount());
				
				DecimalFormat df = new DecimalFormat("###.##");
				Long totalCount = enrollmentYearWithCountMap.get(enrollmentYearVO.getEnrollmentYearId());
				categoryVO.setPercentage(Double.valueOf((df.format(((float)categoryVO.getToalCadreCount()/(float)totalCount)*100))));
				enrollmentYearVO.setMalePercentage(Double.valueOf((df.format(((float)enrollmentYearVO.getMaleCount()/(float)totalCount)*100))));
				enrollmentYearVO.setFemalePercentage(Double.valueOf((df.format(((float)enrollmentYearVO.getFemaleCount()/(float)totalCount)*100))));
			}
		 }
		}
		return finalList;
	}
	
	public ConstituencyCadreVO createCategoryVO(Object[] param){
		ConstituencyCadreVO categoryVO = new ConstituencyCadreVO();
		categoryVO.setId(commonMethodsUtilService.getLongValueForObject(param[2]));
		categoryVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
		if(commonMethodsUtilService.getStringValueForObject(param[3]).equalsIgnoreCase("M")){
			categoryVO.setMaleCount(commonMethodsUtilService.getLongValueForObject(param[4]));
		}else{
		categoryVO.setFemaleCount(commonMethodsUtilService.getLongValueForObject(param[4]));
		}
		return categoryVO;
	}
	
	public ConstituencyCadreVO getMatchedConstituencyCadreVO(List<ConstituencyCadreVO> categorysList,Long id){
		try{
			if(categorysList == null || categorysList.size() == 0)
				return null;
			for(ConstituencyCadreVO vo:categorysList){
				if(vo.getId().equals(id))
	    			return vo;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public List<IdNameVO> getElectionYearWisePartyList(List<Long> electionScopeIdsLst,List<String> subTypes,List<Long> yearList){
		 List<IdNameVO>  resultList = new ArrayList<IdNameVO>();
		try{
			
			List<Object[]> partyObjsLst =  electionDAO.getElectionYearWisePartyList(electionScopeIdsLst,subTypes, yearList);
			if(partyObjsLst != null && partyObjsLst.size() >0){
				for(Object[] param : partyObjsLst){
					IdNameVO idNameVO = new IdNameVO();
					idNameVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					resultList.add(idNameVO);
				}
			}
		}catch(Exception e){
			LOG.error("Exception raised at LocationdashBoardService of getLocationWiseMemberDetails()",e);
		}
		return resultList;
	}
	
}

