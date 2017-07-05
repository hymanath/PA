package com.itgrids.core.api.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.itgrids.core.api.service.ILocationDashboardService;
import com.itgrids.partyanalyst.dao.ICasteCategoryDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IEnrollmentYearDAO;
import com.itgrids.partyanalyst.dao.IGovtSchemeBeneficiaryDetailsDAO;
import com.itgrids.partyanalyst.dao.IInsuranceStatusDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingStatusDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDetailsNewDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateLocationNewDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalDesignationTargetDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalToursMonthDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentYearDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeEnrollmentDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dto.AlertOverviewVO;
import com.itgrids.partyanalyst.dto.BenefitCandidateVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsForConstituencyTypesVO;
import com.itgrids.partyanalyst.dto.CandidateInfoForConstituencyVO;
import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.GrivenceStatusVO;
import com.itgrids.partyanalyst.dto.InsuranceStatusCountsVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationVotersVO;
import com.itgrids.partyanalyst.dto.ToursBasicVO;
import com.itgrids.partyanalyst.model.CasteCategory;
import com.itgrids.partyanalyst.model.EnrollmentYear;
import com.itgrids.partyanalyst.model.TdpCommitteeEnrollment;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
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
	private ITdpCommitteeEnrollmentDAO tdpCommitteeEnrollmentDAO;
	private CommonMethodsUtilService commonMethodsUtilService;
    //tour daos
	private ISelfAppraisalCandidateLocationNewDAO selfAppraisalCandidateLocationNewDAO;
	private ISelfAppraisalDesignationTargetDAO selfAppraisalDesignationTargetDAO;
	private ISelfAppraisalCandidateDetailsNewDAO selfAppraisalCandidateDetailsNewDAO;
	private ISelfAppraisalToursMonthDAO selfAppraisalToursMonthDAO;
	
	private IGovtSchemeBeneficiaryDetailsDAO govtSchemeBeneficiaryDetailsDAO;
	
	private IInsuranceStatusDAO insuranceStatusDAO;


	public void setTdpCommitteeEnrollmentDAO(ITdpCommitteeEnrollmentDAO tdpCommitteeEnrollmentDAO) {
		this.tdpCommitteeEnrollmentDAO = tdpCommitteeEnrollmentDAO;
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

	@SuppressWarnings("unchecked")
	public CandidateDetailsForConstituencyTypesVO getCandidateAndPartyInfoForConstituency(Long constituencyId) {
		String electionType = "";
		String deformDate = "";
		String isnew = "false";
		CandidateDetailsForConstituencyTypesVO candidateDetailsForConstituencyTypesVO = new CandidateDetailsForConstituencyTypesVO();

		/**
		 * DAO method call to get the election type and delimitation info.If
		 * delimitation info is null returning null.
		 */
		List constituencyTypeDetails = constituencyDAO
				.getConstituencyTypeAndDelimitationInfoByConstituencyId(constituencyId);
		if (constituencyTypeDetails != null && constituencyTypeDetails.size() > 0) {
			Object[] obj = (Object[]) constituencyTypeDetails.get(0);
			electionType = (String) obj[0];
			if (obj[1] != null)
				deformDate = obj[1].toString();
		}
		LOG.info(" Election Type :" + electionType);
		LOG.info(" Deform Date :" + deformDate);
		if (!deformDate.equalsIgnoreCase("") || deformDate == null)
			return null;

		// ---------------
		List candidateList = null;
		if (electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE)) {
			candidateList = nominationDAO.getCandidateAndPartyInfoForParliament(constituencyId, electionType, 1L);
			List<String> yearList = nominationDAO.getCandidateAndPartyInfoForParliamentYear(constituencyId,
					electionType);
			if (yearList.size() > 0 && candidateList.size() > 0) {
				Object[] values = (Object[]) candidateList.get(0);
				if (!(yearList.get(0).equalsIgnoreCase(values[11].toString()))) {
					isnew = "true";
				}
			}
		} else {
			/*
			 * Long stateID = consti.getElectionScope().getState().getStateId();
			 * candidateList =
			 * nominationDAO.getCandidateNPartyInfo(constituencyId.toString(),
			 * electionType, 1L, IConstants.ELECTION_SUBTYPE_MAIN, stateID);
			 */
			candidateList = nominationDAO.getCandidateAndPartyInfo(constituencyId, electionType, 1L);
			List<String> yearList = nominationDAO.getCandidateAndPartyInfoYear(constituencyId, electionType);
			if (yearList.size() > 0 && candidateList.size() > 0) {
				Object[] values = (Object[]) candidateList.get(0);
				if (!(yearList.get(0).equalsIgnoreCase(values[11].toString()))) {
					isnew = "true";
				}
			}
		}

		LOG.info("Nomination List :" + candidateList.size());
		if (candidateList.size() == 0)
			return null;
		List<CandidateInfoForConstituencyVO> candidateInfoList = extractCandidateNPartyDataFromList(candidateList);
		LOG.info("Candidate Info :" + candidateInfoList.size());

		if (IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(electionType)) {
			candidateDetailsForConstituencyTypesVO.setAssemblyCandidateInfo(candidateInfoList);

			List list = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(constituencyId);
			if (list != null && list.size() > 0) {
				Object[] listData = (Object[]) list.get(0);
				Long asemblyId = (Long) listData[0];
				List result = nominationDAO.getParliamentCandidateNPartyInfo(asemblyId,
						IConstants.PARLIAMENT_ELECTION_TYPE, 1L);
				if (result.size() != 0) {
					candidateDetailsForConstituencyTypesVO
							.setParliamentCandidateInfo(extractCandidateNPartyDataFromList(result).get(0));
				} else {
					LOG.error("Assembly candidate data for this constituency is not present");
					candidateDetailsForConstituencyTypesVO.setParliamentCandidateInfo(null);
				}
			} else {
				candidateDetailsForConstituencyTypesVO.setParliamentCandidateInfo(null);
			}

		} else if (IConstants.PARLIAMENT_ELECTION_TYPE.equalsIgnoreCase(electionType)) {
			candidateDetailsForConstituencyTypesVO.setParliamentCandidateInfo(candidateInfoList.get(0));

			List assembliesData = delimitationConstituencyAssemblyDetailsDAO
					.findAssembliesConstituencies(constituencyId);
			/* Constituency assemConsti = null; */

			if (assembliesData != null && assembliesData.size() > 0) {
				/*
				 * StringBuilder idString = new StringBuilder(); for(int j = 0 ;
				 * j < assembliesData.size() ; j++) { Object[] ids = (Object[])
				 * assembliesData.get(j);
				 * idString.append(IConstants.COMMA).append((Long)ids[0]);
				 * 
				 * if(j == 0) assemConsti = constituencyDAO.get((Long)ids[0]); }
				 * 
				 * if(idString.length() > 0) { Long stateId = 0L; if(assemConsti
				 * != null) stateId =
				 * assemConsti.getElectionScope().getState().getStateId(); List
				 * result =
				 * nominationDAO.getCandidateNPartyInfo(idString.substring(1),
				 * IConstants.ASSEMBLY_ELECTION_TYPE, 1L,
				 * IConstants.ELECTION_SUBTYPE_MAIN,stateId);
				 */

				List<Object[]> result = new ArrayList<Object[]>(0);
				for (int j = 0; j < assembliesData.size(); j++) {
					try {
						Long constId = (Long) ((Object[]) assembliesData.get(j))[0];
						result.add(nominationDAO
								.getCandidateAndPartyInfo(constId, IConstants.ASSEMBLY_ELECTION_TYPE, 1L).get(0));
					} catch (Exception e) {
						// e.printStackTrace();
					}
				}
				if (result.size() != 0) {
					candidateDetailsForConstituencyTypesVO
							.setAssemblyCandidateInfo(extractCandidateNPartyDataFromList(result));
				} else {
					LOG.error("Parliament candidate data for this constituency is not present");
					candidateDetailsForConstituencyTypesVO.setAssemblyCandidateInfo(null);
				}
				/* } */
			} else {
				candidateDetailsForConstituencyTypesVO.setAssemblyCandidateInfo(null);
			}
		}
		candidateDetailsForConstituencyTypesVO.setIspartial(isnew);
		return candidateDetailsForConstituencyTypesVO;
	}

	public List<CandidateInfoForConstituencyVO> extractCandidateNPartyDataFromList(List candidateList) {
		List<CandidateInfoForConstituencyVO> candidateInfoList = new ArrayList<CandidateInfoForConstituencyVO>();

		for (int i = 0; i < candidateList.size(); i++) {
			String candidateFullName = "";
			CandidateInfoForConstituencyVO candidateInfo1 = new CandidateInfoForConstituencyVO();
			Object[] values = (Object[]) candidateList.get(i);
			candidateInfo1.setConstituencyId((Long) values[0]);
			candidateInfo1.setConstituencyName(values[1].toString());
			candidateInfo1.setCandidateId((Long) values[2]);

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
			candidateInfoList.add(candidateInfo1);
		}

		return candidateInfoList;
	}

	public List<LocationVotersVO> getVotersAndcadreAgeWiseCount(Long constituencyId, Long publicationDateId) {
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			Map<String, LocationVotersVO> map = new LinkedHashMap<String, LocationVotersVO>();
			List<Object[]> votersObjList = voterAgeInfoDAO.getVotersAgeWiseCount(constituencyId, publicationDateId);
			if (votersObjList != null && votersObjList.size() > 0) {
				for (Object[] objects : votersObjList) {
					LocationVotersVO vo = new LocationVotersVO();
					vo.setAgeRangeId((Long) objects[0]);
					vo.setAgeRange(objects[1].toString());
					vo.setTotalVoters(objects[2] != null ? (Long) objects[2] : 0l);
					vo.setTotalVotersPerc(objects[3] != null ? objects[3].toString() + " %" : "");
					vo.setMaleVoters(objects[4] != null ? (Long) objects[4] : 0l);
					vo.setMaleVotersPerc(objects[5] != null ? objects[5].toString() + " %" : "");
					vo.setFemaleVoters(objects[6] != null ? (Long) objects[6] : 0l);
					vo.setFemaleVotersPerc(objects[7] != null ? objects[7].toString() + " %" : "");
					map.put(objects[1].toString(), vo);
				}
			}

			List<Object[]> cadreObjList = tdpCadreEnrollmentYearDAO.getGenderAndAgeGroupWiseCadreCount(constituencyId);
			if (cadreObjList != null && cadreObjList.size() > 0) {
				for (Object[] objects : cadreObjList) {
					if (map.get(objects[1].toString()) == null) {
						LocationVotersVO inVO = new LocationVotersVO();
						map.put(objects[1].toString(), inVO);
					}

					if (objects[2].toString().trim().equalsIgnoreCase("M")) {
						map.get(objects[1].toString()).setMaleCadres((Long) objects[3]);
					} else if (objects[2].toString().trim().equalsIgnoreCase("F")) {
						map.get(objects[1].toString()).setFemaleCadres((Long) objects[3]);
					}
				}
			}

			if (map != null && map.size() > 0) {
				LocationVotersVO voForTotalCounts = new LocationVotersVO();
				Long totalCadres = 0l, maleTotalCadres = 0l, femaleTotalCadres = 0l;
				for (Entry<String, LocationVotersVO> entry : map.entrySet()) {
					entry.getValue()
							.setTotalCadres(entry.getValue().getMaleCadres() + entry.getValue().getFemaleCadres());
					totalCadres = totalCadres + entry.getValue().getMaleCadres() + entry.getValue().getFemaleCadres();
					maleTotalCadres = maleTotalCadres + entry.getValue().getMaleCadres();
					femaleTotalCadres = femaleTotalCadres + entry.getValue().getFemaleCadres();
				}

				for (Entry<String, LocationVotersVO> entry : map.entrySet()) {
					if (totalCadres > 0l)
						entry.getValue()
								.setTotalCadrePerc(((entry.getValue().getTotalCadres() * 100) / totalCadres) + "");
					if (maleTotalCadres > 0l)
						entry.getValue()
								.setMaleCadrePerc(((entry.getValue().getMaleCadres() * 100) / maleTotalCadres) + "");
					if (femaleTotalCadres > 0l)
						entry.getValue().setFemaleCadrePerc(
								((entry.getValue().getFemaleCadres() * 100) / femaleTotalCadres) + "");

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

	public List<LocationVotersVO> getVotersAndCadreCasteWiseCount(String type, Long constituencyId,
			Long publicationDateId) {
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			if (type.equalsIgnoreCase("voter")) {
				voList = getVotersCasteWiseCount(constituencyId, publicationDateId);
			} else if (type.equalsIgnoreCase("cadre")) {
				voList = getCadreCasteWiseCount(constituencyId);
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getVotersAndCadreCasteWiseCount", e);
		}
		return voList;
	}

	public List<LocationVotersVO> getVotersCasteWiseCount(Long constituencyId, Long publicationId) {
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			// 0-castegroupId,1-castegroup,2-casteId,3-castegroup,4-voterscount,5-percentage
			List<Object[]> votersObjList = voterCastInfoDAO.getVotersCasteWiseCount(constituencyId, publicationId);

			if (votersObjList != null && votersObjList.size() > 0) {
				Map<String, LocationVotersVO> casteGroupMap = new LinkedHashMap<String, LocationVotersVO>();
				Map<String, LocationVotersVO> casteMap = new LinkedHashMap<String, LocationVotersVO>();
				for (Object[] objects : votersObjList) {
					if (casteGroupMap.get(objects[1].toString()) == null) {
						LocationVotersVO inVO = new LocationVotersVO();
						inVO.setAgeRangeId((Long) objects[0]);
						inVO.setAgeRange(objects[1].toString());
						inVO.setMaleVotersPerc("casteGroup");
						casteGroupMap.put(objects[1].toString(), inVO);
					}
					LocationVotersVO matchedVO = casteGroupMap.get(objects[1].toString());
					matchedVO.setTotalVoters(matchedVO.getTotalVoters() + (Long) objects[4]);
					matchedVO
							.setTotalVotersPerc(
									matchedVO.getTotalVotersPerc() != ""
											? (Float.parseFloat(matchedVO.getTotalVotersPerc())
													+ Float.parseFloat(objects[5].toString())) + ""
											: objects[5].toString());

					if (casteMap.get(objects[3].toString()) == null) {
						LocationVotersVO inVO = new LocationVotersVO();
						inVO.setAgeRangeId((Long) objects[2]);
						inVO.setAgeRange(objects[3].toString());
						inVO.setMaleCadrePerc("caste");
						casteMap.put(objects[3].toString(), inVO);
					}
					LocationVotersVO matchedVO1 = casteMap.get(objects[3].toString());
					matchedVO1.setTotalVoters(matchedVO.getTotalVoters() + (Long) objects[4]);
					matchedVO1
							.setTotalVotersPerc(
									matchedVO1.getTotalVotersPerc() != ""
											? (Float.parseFloat(matchedVO1.getTotalVotersPerc())
													+ Float.parseFloat(objects[5].toString())) + ""
											: objects[5].toString());

				}
				voList.addAll(casteGroupMap.values());
				voList.addAll(casteMap.values());
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getVotersCasteWiseCount", e);
		}
		return voList;
	}

	public List<LocationVotersVO> getCadreCasteWiseCount(Long constituencyId) {
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			// 0-casteCategoryId,1-casteCategory,2-casteId,3-caste,4-cadreCount
			List<Object[]> objList = tdpCadreEnrollmentYearDAO.getCasteWiseCadreCounts(constituencyId);

			if (objList != null && objList.size() > 0) {
				Map<String, LocationVotersVO> casteGroupMap = new LinkedHashMap<String, LocationVotersVO>();
				Map<String, LocationVotersVO> casteMap = new LinkedHashMap<String, LocationVotersVO>();
				Long totalCadreCount = 0l;
				for (Object[] objects : objList) {
					totalCadreCount = totalCadreCount + (Long) objects[4];
					if (casteGroupMap.get(objects[1].toString()) == null) {
						LocationVotersVO inVO = new LocationVotersVO();
						inVO.setAgeRangeId((Long) objects[0]);
						inVO.setAgeRange(objects[1].toString());
						inVO.setMaleVotersPerc("casteGroup");
						casteGroupMap.put(objects[1].toString(), inVO);
					}
					LocationVotersVO matchedVO = casteGroupMap.get(objects[1].toString());
					matchedVO.setTotalVoters(matchedVO.getTotalVoters() + (Long) objects[4]);

					if (casteMap.get(objects[3].toString()) == null) {
						LocationVotersVO inVO = new LocationVotersVO();
						inVO.setAgeRangeId((Long) objects[2]);
						inVO.setAgeRange(objects[3].toString());
						inVO.setMaleCadrePerc("caste");
						casteMap.put(objects[3].toString(), inVO);
					}
					LocationVotersVO matchedVO1 = casteMap.get(objects[3].toString());
					matchedVO1.setTotalVoters(matchedVO.getTotalVoters() + (Long) objects[4]);

				}

				if (casteGroupMap != null && casteGroupMap.size() > 0 && totalCadreCount > 0) {
					for (Entry<String, LocationVotersVO> entry : casteGroupMap.entrySet()) {
						entry.getValue()
								.setTotalVotersPerc(((entry.getValue().getTotalVoters() * 100) / totalCadreCount) + "");
					}
					voList.addAll(casteGroupMap.values());
				}

				if (casteMap != null && casteMap.size() > 0 && totalCadreCount > 0) {
					for (Entry<String, LocationVotersVO> entry : casteMap.entrySet()) {
						entry.getValue()
								.setTotalVotersPerc(((entry.getValue().getTotalVoters() * 100) / totalCadreCount) + "");
					}
					voList.addAll(casteMap.values());
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getCadreCasteWiseCount", e);
		}
		return voList;
	}

	public List<LocationVotersVO> getCasteGroupNAgeWiseVoterNCadreCounts(Long constituencyId, Long publicationDateId) {
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			// 0-castegroupId,1-castegroup,2-casteId,3-castegroup,4-voterscount,5-percentage,6-maleVotersCount,7-femaleVotersCount
			List<Object[]> votersObjList = voterCastInfoDAO.getVotersCasteWiseCount(constituencyId, publicationDateId);

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
						LocationVotersVO casteVO = new LocationVotersVO();
						casteVO.setAgeRangeId((Long) objects[2]);
						casteVO.setAgeRange(objects[3].toString());
						casteVO.setTotalVoters((Long) objects[4]);
						casteVO.setTotalVotersPerc(objects[5].toString());
						casteVO.setMaleVoters((Long) objects[6]);
						casteVO.setFemaleVoters((Long) objects[7]);

						matchedCGVO.getLocationVotersVOList().add(casteVO);
					}
				}
			}

			// 0-casteCategoryId,1-casteCategory,2-casteId,3-caste,4-gender,5-cadreCount
			List<Object[]> cadresObjList = tdpCadreEnrollmentYearDAO.getCasteNGenderWiseCadreCounts(constituencyId);
			if (cadresObjList != null && cadresObjList.size() > 0) {
				for (Object[] objects : cadresObjList) {
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
						LocationVotersVO matchedCVO = getMatchedVO(matchedCGVO.getLocationVotersVOList(),
								(Long) objects[2]);
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
					if (casteGroupVO.getLocationVotersVOList() != null
							&& casteGroupVO.getLocationVotersVOList().size() > 0) {
						Long maleTotalVoters = 0l, femaleTotalVoters = 0l, totalCadres = 0l, maleTotalCadres = 0l,
								femaleTotalCadres = 0l;
						for (LocationVotersVO casteVO : casteGroupVO.getLocationVotersVOList()) {
							maleTotalVoters = maleTotalVoters + casteVO.getMaleVoters();
							femaleTotalVoters = femaleTotalVoters + casteVO.getFemaleVoters();
							maleTotalCadres = maleTotalCadres + casteVO.getMaleCadres();
							femaleTotalCadres = femaleTotalCadres + casteVO.getFemaleCadres();
							totalCadres = totalCadres + casteVO.getMaleCadres() + casteVO.getFemaleCadres();
						}

						for (LocationVotersVO casteVO : casteGroupVO.getLocationVotersVOList()) {
							casteVO.setMaleVotersPerc(((casteVO.getMaleVoters() * 100) / maleTotalVoters) + " %");
							casteVO.setFemaleVotersPerc(((casteVO.getFemaleVoters() * 100) / femaleTotalVoters) + " %");
							casteVO.setTotalCadres(casteVO.getMaleCadres() + casteVO.getFemaleCadres());
							casteVO.setTotalCadrePerc(((casteVO.getTotalCadres() * 100) / totalCadres) + " %");
							casteVO.setMaleCadrePerc(((casteVO.getMaleCadres() * 100) / maleTotalCadres) + " %");
							casteVO.setFemaleCadrePerc(((casteVO.getFemaleCadres() * 100) / femaleTotalCadres) + " &%");
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

	public List<LocationVotersVO> getCasteNAgeWiseVoterNCadreCounts(Long constituencyId, Long publicationDateId,
			Long casteGroupId, Long casteId) {
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			Map<Long, LocationVotersVO> map = new LinkedHashMap<Long, LocationVotersVO>();

			// 0-ageRangeId,1-ageRange,2-gender,3-votersCount
			List<Object[]> votersObjList = userVoterDetailsDAO.getVotersCasteNAgeGroupWiseCount(casteGroupId, casteId,
					constituencyId, publicationDateId);

			if (votersObjList != null && votersObjList.size() > 0) {
				for (Object[] objects : votersObjList) {
					if (map.get((Long) objects[0]) == null) {
						LocationVotersVO inVO = new LocationVotersVO();
						inVO.setAgeRangeId((Long) objects[0]);
						inVO.setAgeRange(objects[1].toString());
						map.put((Long) objects[0], inVO);
					}

					if (objects[2].toString().equalsIgnoreCase("M")) {
						map.get((Long) objects[0]).setMaleVoters((Long) objects[3]);
					} else if (objects[2].toString().equalsIgnoreCase("F")) {
						map.get((Long) objects[0]).setFemaleVoters((Long) objects[3]);
					}

				}
			}

			List<Object[]> cadresObjList = tdpCadreEnrollmentYearDAO.getCadresCasteNAgeGroupWiseCounts(casteGroupId,
					casteId, constituencyId);

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
					entry.getValue()
							.setTotalVotersPerc(((entry.getValue().getTotalVoters() * 100.00) / totalVoters) + " %");
					entry.getValue()
							.setMaleVotersPerc(((entry.getValue().getMaleVoters() * 100.00) / totalMaleVoters) + " %");
					entry.getValue().setFemaleVotersPerc(
							((entry.getValue().getFemaleVoters() * 100.00) / totalFemaleVoters) + " %");
					entry.getValue()
							.setTotalCadrePerc(((entry.getValue().getTotalCadres() * 100.00) / totalCadres) + " %");
					entry.getValue()
							.setMaleCadrePerc(((entry.getValue().getMaleCadres() * 100.00) / totalMaleCadres) + " %");
					entry.getValue().setFemaleCadrePerc(
							((entry.getValue().getFemaleCadres() * 100.00) / totalFemaleCadres) + " %");
				}

				voList.addAll(map.values());
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getCasteNAgeWiseVoterNCadreCounts", e);
		}
		return voList;
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

	public List<LocationVotersVO> getEnrollmentYearAgeGroupWiseCadres(Long constituencyId, Long enrollmentYearId) {
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			// 0-voterAgeRangeId,1-ageRange,2-gender,3-casteCategoryId,4-categoryName,5-count
			List<Object[]> objList = tdpCadreEnrollmentYearDAO.getEnrollmentYearAgeGroupWiseCadres(constituencyId,
					enrollmentYearId);

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

	public List<LocationVotersVO> getLocationWiseMeetingsCount(String locationType, Long constituencyId) {
		List<LocationVotersVO> voList = new ArrayList<LocationVotersVO>(0);
		try {
			Map<String, LocationVotersVO> finalMap = new LinkedHashMap<String, LocationVotersVO>();

			finalMap.put("Y", null);
			finalMap.put("N", null);
			finalMap.put("M", null);
			finalMap.put("NU", null);

			// 0-meetingStatus,1-levelId,2-level,3-count
			List<Object[]> objList = partyMeetingStatusDAO.getLocationWiseMeetings(locationType, constituencyId);
			if (objList != null && objList.size() > 0) {
				for (Object[] objects : objList) {
					if (finalMap.get(objects[0].toString()) == null) {
						finalMap.put(objects[0].toString(), getPartyMeetingSkeleton(objects[0].toString()));
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

	public LocationVotersVO getPartyMeetingSkeleton(String type) {
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
		mtdVO.setAgeRange("Mandal/Town/Divison");
		vo.getLocationVotersVOList().add(mtdVO);

		LocationVotersVO constVO = new LocationVotersVO();
		constVO.setAgeRangeId(3l);
		constVO.setAgeRange("Constituency");
		vo.getLocationVotersVOList().add(constVO);

		return vo;
	}

	@Override
	public CommitteeBasicVO getLocationWiseCommitteesCount(String locationType, Long locationId,
			Long enrollmentId) {

		CommitteeBasicVO committeeCounts = new CommitteeBasicVO();
		try {
			// 0-tdp_base_comitteeId,1-levelId,2-levelName,3-committeeConfrimed,4-start
			// Date,5-completed Date
			List<Object[]> objList = tdpCommitteeDAO.getLocationWiseCommittees(locationType, locationId, enrollmentId);
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
					if ((Long)objects[0] == 1l) {
						if ((Long)objects[1] == 5l || (Long)objects[1] == 7l || (Long)objects[1] == 9l) {
							if (objects[3].toString().trim().equalsIgnoreCase("Y")) {
								mainMandalCompletedCount++;
							} else if(objects[3].toString().trim().equalsIgnoreCase("N") && objects[4]!=null) {
								mainMandalStartCount++;
							}else if(objects[3].toString().trim().equalsIgnoreCase("N") && objects[4]==null && objects[5]==null ){
								mainMandalNotStarted++;
							}

						} else if ((Long)objects[1] == 6l || (Long)objects[1] == 8l) {
							if (objects[3].toString().trim().equalsIgnoreCase("Y")) {
								mainVillageCompletedCount++;
							} else if(objects[3].toString().trim().equalsIgnoreCase("N") && objects[4]!=null) {
								mainVillageStartCount++;
							}else if(objects[3].toString().trim().equalsIgnoreCase("N") && objects[4]==null && objects[5]==null ){
								mainVillageNotStarted++;
							}
						}
					} else {
						
						
						if ((Long)objects[1] == 5l || (Long)objects[1] == 7l || (Long)objects[1] == 9l) {
							if (objects[3].toString().trim().equalsIgnoreCase("Y")) {
								affliatedMandalCompletedCount++;
							} else if(objects[3].toString().trim().equalsIgnoreCase("N") && objects[4]!=null) {
								affliatedMandalStartCount++;
							}else if(objects[3].toString().trim().equalsIgnoreCase("N") && objects[4]==null && objects[5]==null ){
								affliatedMandalNotStarted++;
							}

						} else if ((Long)objects[1] == 6l || (Long)objects[1] == 8l) {
							if (objects[3].toString().trim().equalsIgnoreCase("Y")) {
								affliatedVillageCompletedCount++;
							} else if(objects[3].toString().trim().equalsIgnoreCase("N") && objects[4]!=null)  {
								affliatedVillageStartCount++;
							}else if(objects[3].toString().trim().equalsIgnoreCase("N") && objects[4]==null && objects[5]==null ){
								affliatedVillageNotStarted++;
							}
						}
					}
					
				}
				committeeCounts.setMainCommCompletedCount(mainMandalCompletedCount);//main committee mandal/town/division count
				committeeCounts.setMainCommStartedCount(mainMandalStartCount);//main committee village/ward count
				committeeCounts.setMainMandalTotal(mainMandalCompletedCount+mainMandalStartCount+mainMandalNotStarted);
				committeeCounts.setMainCommTotalCount(mainVillageCompletedCount);//main committee village/ward count
				committeeCounts.setMainCommTotalMembers(mainVillageStartCount);//main committee village/ward count
				committeeCounts.setMainVillageTotal(mainVillageCompletedCount+mainVillageStartCount+mainVillageNotStarted);
				committeeCounts.setMainCommNotYetStarted(mainMandalNotStarted);//main committee not yet started count 
				committeeCounts.setMainCommVillageNotStarted(mainVillageNotStarted);//main committee not started count
				committeeCounts.setMainMandalCompletePer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getMainMandalTotal(), mainMandalCompletedCount))));
				committeeCounts.setMainMandalStartPer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getMainMandalTotal(), mainMandalStartCount))));
				committeeCounts.setMainVillageCompletePer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getMainVillageTotal(), mainVillageCompletedCount))));
				committeeCounts.setMainMandalStartPer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getMainVillageTotal(), mainVillageStartCount))));
				
				committeeCounts.setAffiCommCompletedCount(affliatedMandalCompletedCount);
				committeeCounts.setAffiCommStartedCount(affliatedMandalStartCount);
				committeeCounts.setAffMandalTotal(affliatedMandalCompletedCount+affliatedMandalStartCount);
				committeeCounts.setAffiCommTotalCount(affliatedVillageCompletedCount);//affliated committee village/ward count
				committeeCounts.setAffiCommTotalMembers(affliatedVillageStartCount);//affliated committee village/ward count
				committeeCounts.setAffVillageTotal(affliatedVillageCompletedCount+affliatedVillageStartCount);
				committeeCounts.setAffiCommMandalNotStarted(affliatedMandalNotStarted);
				committeeCounts.setAffiCommVillageNotStarted(affliatedVillageNotStarted);
				committeeCounts.setAffMandalCompletePer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getAffMandalTotal(), affliatedMandalCompletedCount))));
	            committeeCounts.setAffMandalStartPer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getAffMandalTotal(), affliatedMandalStartCount))));
	            committeeCounts.setAffVillageCompletePer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getAffVillageTotal(), affliatedVillageCompletedCount))));
	            committeeCounts.setAffVillageStartPer((Double.parseDouble(cadreDetailsService.calculatePercentage(committeeCounts.getAffVillageTotal(), affliatedVillageStartCount))));
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseCommitteesCount", e);
		}

		return committeeCounts;
	}

	@Override
	public List<TdpCommitteeEnrollment> getEnrollmentIds() {
		List<TdpCommitteeEnrollment> TdpCommitteeEnrollment = tdpCommitteeEnrollmentDAO.getAll();
		return TdpCommitteeEnrollment;
	}
	
	@Override
	public List<List<AlertOverviewVO>> getLevelWiseMeetingStatusCounts(String fromDateStr, String toDateStr, Long locationId,
			Long locationValue) {
		List<List<AlertOverviewVO>> meetingStatusCounts = new ArrayList<List<AlertOverviewVO>>();
		List<AlertOverviewVO> vwStatusCountsList = new ArrayList<AlertOverviewVO>();
		List<AlertOverviewVO> mtdStatusCountsList = new ArrayList<AlertOverviewVO>();
		List<AlertOverviewVO> cStatusCountsList = new ArrayList<AlertOverviewVO>();
		AlertOverviewVO vwStatus = new AlertOverviewVO();
		AlertOverviewVO mtdStatus = new AlertOverviewVO();
		AlertOverviewVO cStatus = new AlertOverviewVO();
		Date frmDate = null;
		Date toDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
		List<Object[]> objList = partyMeetingStatusDAO.getLevelWiseMeetingStatusCount(frmDate, toDate, locationId,
				locationValue);
		try {
			
			//village/ward variables
			Long vwYesCount = 0l;
			Long vwNoCount = 0l;
			Long vwMayBeCount = 0l;
			Long vwNotUpCount = 0l;
			
			//mandal/town/division variables
			Long mtdYesCount = 0l;
			Long mtdNoCount = 0l;
			Long mtdMayBeCount = 0l;
			Long mtdNotUpCount = 0l;
			
			//constituency variables   
			Long cYesCount = 0l;
			Long cNoCount = 0l;
			Long cMayBeCount = 0l;
			Long cNotUpCount = 0l;

			if (objList != null) {
				for (Object[] objects : objList) {
					if ((Long)objects[0] == 7l || (Long)objects[0] == 8l) {
                         
						if (objects[2].toString().trim().equalsIgnoreCase("Y")) {
							vwYesCount = vwYesCount + (Long)objects[3];
						} else if (objects[2].toString().trim().equalsIgnoreCase("N")) {
							vwNoCount = vwNoCount + (Long)objects[3];
						} else if (objects[2].toString().trim().equalsIgnoreCase("NU")) {
							vwNotUpCount = vwNotUpCount + (Long)objects[3];
						} else if (objects[2].toString().trim().equalsIgnoreCase("M")) {
							vwMayBeCount = vwMayBeCount+(Long)objects[3];
						}
					} else if ((Long)objects[0] == 4l || (Long)objects[0] == 5l || (Long)objects[0] == 6l) {
						if (objects[2].toString().trim().equalsIgnoreCase("Y")) {
							mtdYesCount = mtdYesCount+(Long)objects[3];
						} else if (objects[2].toString().trim().equalsIgnoreCase("N")) {
							mtdNoCount = mtdNoCount+(Long)objects[3];
						} else if (objects[2].toString().trim().equalsIgnoreCase("NU")) {
							mtdNotUpCount = mtdNotUpCount+(Long)objects[3];
						} else if (objects[2].toString().trim().equalsIgnoreCase("M")) {
							mtdMayBeCount =mtdMayBeCount+(Long)objects[3];
						}
					} else if ((Long)objects[0] == 3l) {
						if (objects[2].toString().trim().equalsIgnoreCase("Y")) {
							cYesCount = cYesCount+(Long)objects[3];
						} else if (objects[2].toString().trim().equalsIgnoreCase("N")) {
							cNoCount = cNoCount+(Long)objects[3];
						} else if (objects[2].toString().trim().equalsIgnoreCase("NU")) {
							cNotUpCount = cNotUpCount+(Long)objects[3];
						} else if (objects[2].toString().trim().equalsIgnoreCase("M")) {
							cMayBeCount = cMayBeCount+(Long)objects[3];
						}
					}

					
					//here set the village/ward status counts
					vwStatus.setCompletedCnt(vwYesCount);
					vwStatus.setPendingCnt(vwNoCount);
					vwStatus.setNotifiedCnt(vwMayBeCount);
					vwStatus.setUnabletoResolveCnt(vwNotUpCount);
					
					//here set mandal/town/division status counts
					mtdStatus.setCompletedCnt(mtdYesCount);
					mtdStatus.setPendingCnt(mtdNoCount);
					mtdStatus.setNotifiedCnt(mtdNotUpCount);
					mtdStatus.setUnabletoResolveCnt(mtdMayBeCount);
					
					//here set the constituency status counts
					cStatus.setCompletedCnt(cYesCount);
					cStatus.setPendingCnt(cNoCount);
					cStatus.setNotifiedCnt(cNotUpCount);
					cStatus.setUnabletoResolveCnt(cMayBeCount);
				}
				vwStatusCountsList.add(vwStatus);
				mtdStatusCountsList.add(mtdStatus);
				cStatusCountsList.add(cStatus);
				meetingStatusCounts.add(vwStatusCountsList);
				meetingStatusCounts.add(mtdStatusCountsList);
				meetingStatusCounts.add(cStatusCountsList);

			}
		} catch (Exception e) {
            Log.error("Exception raised in meetings method"+e);
		}
		return meetingStatusCounts;
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
	public List<ToursBasicVO> getLocationWiseTourMembersComplainceDtls(final String locationType,final Long locationValue,final String fromDateStr,final String toDateStr){
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
			 
			 List<Object[]> rtrnCandiateObjLst = selfAppraisalCandidateLocationNewDAO.getLocationWiseTourMemberDetails(getUserAccessLevel(locationType), locationType, locationValue);
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
			  
			  //Calculating Category wise complaince
			  calculateCategoryWiseComplaince(candiateDtlsMap);
			  //Calculating OverAll percentage
			  calculatteOverAllPercentage(candiateDtlsMap);
			  
			  //Preparing final list
			  if(candiateDtlsMap != null && candiateDtlsMap.size() > 0){
				  for(Entry<Long,Map<Long,ToursBasicVO>> entry:candiateDtlsMap.entrySet()){
					  ToursBasicVO designationVO = new ToursBasicVO();  
					  designationVO.setDesignationId(entry.getKey());
					  if(basicDtlsVO != null){
						  designationVO.setDesignation(basicDtlsVO.getSubMap().get(entry.getKey()));  
					  }
					  designationVO.setIsComplaince("False");
					  if(entry.getValue() != null && entry.getValue().size() > 0){
						  for(Entry<Long,ToursBasicVO> candiateEntry:entry.getValue().entrySet()){
							  if(candiateEntry.getValue().getComplaincePer()>=100d){
								  designationVO.setComplainceCnt(designationVO.getComplainceCnt()+1);
								  designationVO.setIsComplaince("True");
							  }
							  designationVO.getSubList().add(candiateEntry.getValue());
						  }
					  }
					  resultList.add(designationVO);
				  }
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
	public void calculateCategoryWiseComplaince(Map<Long,Map<Long,ToursBasicVO>> candiateDtlsMap){
		 try{
			 if(candiateDtlsMap != null && candiateDtlsMap.size() > 0){
				 
				 for(Entry<Long,Map<Long,ToursBasicVO>> designationEntry:candiateDtlsMap.entrySet()){
					 
					 if(designationEntry.getValue() != null && designationEntry.getValue().size() > 0){
						 
						 for(Entry<Long,ToursBasicVO> candiateEntry:designationEntry.getValue().entrySet()){
							 
							 ToursBasicVO candiateVO = candiateEntry.getValue();
							 
							 if(candiateVO != null){
								 
								 if(candiateVO.getSubList3() != null && candiateVO.getSubList3().size() > 0){
									 
									 for(ToursBasicVO categoryVO:candiateVO.getSubList3()){
										 
										 List<ToursBasicVO> monthList = categoryVO.getMonthList();
										 
										 if(monthList != null && monthList.size() > 0){
											 
											   Double totalPer= 0.0d;
											   Long targetDays =0l;
											   Long complainceDays =0l;
											   
											   for(ToursBasicVO monthVO:monthList){
												   
												   totalPer = totalPer+monthVO.getComplaincePer();
												   targetDays = targetDays + monthVO.getTargetDays();
												   complainceDays = complainceDays + monthVO.getComplainceDays();
											   }
											   
											   Integer totalCount =0;
											   
											   if(monthList != null && monthList.size() > 0){
												   
												    totalCount = monthList.size() * 100;   
											   }
											   
										       Double percentage = calculatePercantageBasedOnDouble(totalPer,totalCount.doubleValue());
										       if(percentage > 100){
										    	   categoryVO.setComplaincePer(100d);
										       }else{
										    	   categoryVO.setComplaincePer(percentage);   
										       }
										       categoryVO.setTargetDays(targetDays);
										       categoryVO.setComplainceDays(complainceDays);
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
	public void calculatteOverAllPercentage(Map<Long,Map<Long,ToursBasicVO>> memberDtlsMap){
		 try{
			 if(memberDtlsMap != null && memberDtlsMap.size() > 0){
				 
				  for(Entry<Long,Map<Long,ToursBasicVO>> designationEntry:memberDtlsMap.entrySet()){
					  
					  if(designationEntry != null && designationEntry.getValue().size() >0){
						  
						  for(Entry<Long,ToursBasicVO> candiateEntry:designationEntry.getValue().entrySet()){
							  
							  List<ToursBasicVO> categoryList = candiateEntry.getValue().getSubList3();
							  
							   if(categoryList != null && categoryList.size() > 0){
								   
								   Double totalPer =0.0d;
								   Long complainceDays=0l;
								   for(ToursBasicVO VO:categoryList){
									   totalPer = totalPer+VO.getComplaincePer(); 
									   complainceDays = complainceDays +VO.getComplainceDays();
								   }
								   Integer totalCount =0;
								   if(categoryList != null && categoryList.size() > 0){
									    totalCount = categoryList.size() * 100;   
								   }
								   
								   Double percentage = calculatePercantageBasedOnDouble(totalPer,totalCount.doubleValue());
								   candiateEntry.getValue().setComplainceDays(complainceDays);
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
		 }catch(Exception e){
			 LOG.error("Exception Occured in calculatteOverAllPercentage() in LocationDashboardService  : ",e);	 
		 }
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
			 LOG.error("Exception Occured in getCategoryMatchVO() in LocationDashboardService  : ",e);	 
		 }
		 return null;
	 }
	 public Double calculatePercantage(Long subCount,Long totalCount){
			Double d=0.0d;
			if(subCount.longValue()>0l && totalCount.longValue()==0l)
			LOG.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount);

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
	public Long getUserAccessLevel(String locationType){
		Long userAccessLevelId=0l;
		if(locationType != null){
			if(locationType.equalsIgnoreCase("District")){
				userAccessLevelId = 3l;
			}else if(locationType.equalsIgnoreCase("ParliamentConstituency")){
				userAccessLevelId = 10l;
			}else if(locationType.equalsIgnoreCase("Constituency")){
				userAccessLevelId = 4l;
			}
		}
		return userAccessLevelId;
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
	public List<BenefitCandidateVO> getGovtSchemeWiseBenefitMembersCount(final String locationType, final Long locationValue) {
		List<BenefitCandidateVO> resultList = new ArrayList<BenefitCandidateVO>(0);
		try {
			List<Object[]> benefitMemberObjLst = govtSchemeBeneficiaryDetailsDAO.getGovtSchemeWiseBenefitMemberCount(locationType,locationValue);
			resultList = getGovtSchemeBenefitMemberDlstList(benefitMemberObjLst);
		} catch (Exception e) {
			Log.error("Exception Occured at getGovtSchemeWiseBenefitMembersCount() in LocationDashboardService class",e);
		}
		return resultList;
	}
	/**
	  * @param String locationType
	  * @param String locationValue
	  * @return List<BenefitCandidateVO>
	  * @author Santosh 
	  * @Description :This Service Method is used for getting mandal wise benefit member count 
	  *  @since 7-JULY-2017
	  */
	public List<BenefitCandidateVO> getMandalWiseBenefitMembersCount(final String locationType, final Long locationValue,final Long govtSchemeId) {
		List<BenefitCandidateVO> resultList = new ArrayList<BenefitCandidateVO>(0);
		try {
			List<Object[]> benefitMemberObjLst = govtSchemeBeneficiaryDetailsDAO.getMandalWiseBenefitMemberCountByGovtScheme(locationType,locationValue, govtSchemeId);
			resultList = getGovtSchemeBenefitMemberDlstList(benefitMemberObjLst);
		} catch (Exception e) {
			Log.error("Exception Occured at getMandalWiseBenefitMembersCount() in LocationDashboardService class",e);
		}
		return resultList;
	}

	public List<BenefitCandidateVO> getGovtSchemeBenefitMemberDlstList(List<Object[]> objList) {
		List<BenefitCandidateVO> returnList = new ArrayList<BenefitCandidateVO>(0);
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					BenefitCandidateVO vo = new BenefitCandidateVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					vo.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			Log.error("Exception Occured at getGovtSchemeBenefitMemberDlstList() in LocationDashboardService class",e);
		}
		return returnList;
	}
	
	@Override
	public InsuranceStatusCountsVO getLocationWiseInsuranceStatusCounts(String fromDateStr, String toDateStr, Long locationId,Long locationValue) {
		InsuranceStatusCountsVO insuranceStatusCounts = new InsuranceStatusCountsVO();
		try{
			Date fromDate = null;
 			Date toDate = null;
 			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
 				fromDate = sdf.parse(fromDateStr);
 				toDate = sdf.parse(toDateStr);
 			}
 			Long waitingDocs = 0l;
 			Long submittedInParty = 0l;
 			Long  foreadedInsu = 0l;
 			Long closedAtInsu = 0l;
 			Long closedAtParty = 0l;
 			Long approved = 0l;
 			Long closedLetters = 0l;
 			Long accountsRejected = 0l;
 			//0-locationValue(DIstrict or ConstituencyId),1-locationName,2-Status,3-StatusId,4-Count
 			List<Object[]> insuranceStatus = insuranceStatusDAO.getConstituencyWiseInsuranceStatusCounts(fromDate, toDate, locationId, locationValue);
 			if(insuranceStatus!=null){
 				for (Object[] objects : insuranceStatus) {
 					if((Long)objects[3]==1){
 						waitingDocs= (Long)objects[4];
 					}else if((Long)objects[3]==2){
 						submittedInParty=(Long)objects[4];
 					}else if((Long)objects[3]==3){
 						foreadedInsu=(Long)objects[4];
 					}else if((Long)objects[3]==4){
 						closedAtInsu = (Long)objects[4];
 					}else if((Long)objects[3]==5){
 						closedAtParty = (Long)objects[4];
 					}else if((Long)objects[3]==6){
 						approved= (Long)objects[4];
 					}else if((Long)objects[3]==7){
 						closedLetters = (Long)objects[4];
 					}else if((Long)objects[3]==8){
 						accountsRejected = (Long)objects[4];
 					}
				}
 				insuranceStatusCounts.setWaitingForDocs(waitingDocs);
 				insuranceStatusCounts.setSubmittedInparty(submittedInParty);
 				insuranceStatusCounts.setForeadedToInsurance(foreadedInsu);
 				insuranceStatusCounts.setClosedAtInsurance(closedAtInsu);
 				insuranceStatusCounts.setClosedAtParty(closedAtParty);
 				insuranceStatusCounts.setApproved(approved);
 				insuranceStatusCounts.setClosedLetters(closedLetters);
 				insuranceStatusCounts.setAccountRejected(accountsRejected);
 			}
		}catch(Exception e){
			Log.error("Exception raised at insurance status counts service"+e);
		}
		return insuranceStatusCounts;
	}

	@Override
	public List<List<GrivenceStatusVO>> getGrivenceTrustStatusCounts(String fromDateStr, String toDateStr,Long locationId, Long locationValue) {
		List<List<GrivenceStatusVO>> finalList = new ArrayList<List<GrivenceStatusVO>>();
		List<GrivenceStatusVO> grivenceList = new ArrayList<GrivenceStatusVO>();
		List<GrivenceStatusVO> trustList = new ArrayList<GrivenceStatusVO>();
		GrivenceStatusVO grivenceStatusCount = new GrivenceStatusVO();
		GrivenceStatusVO trustStatusCount = new GrivenceStatusVO();
		try{
			Date fromDate = null;
 			Date toDate = null;
 			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
 				fromDate = sdf.parse(fromDateStr);
 				toDate = sdf.parse(toDateStr);
 			}
 			//grivence counts
 			Long gnotVerified = 0l;
 			Long ginProgress = 0l;
 			Long gnotEligible = 0l;
 			Long gnotPossible = 0l;
 			Long gapproves = 0l;
 			Long gcompleted = 0l;
 			
 			//trust counts
 			Long tnotVerified = 0l;
 			Long tinProgress = 0l;
 			Long tnotEligible = 0l;
 			Long tnotPossible = 0l;
 			Long tapproves = 0l;
 			Long tcompleted = 0l;
 			//0-consId,1-Status,2-typeOfIssue,3-count
 			List<Object[]> grivenceTrustList = insuranceStatusDAO.getGrivenceTrustStatusCounts(fromDate, toDate, locationId, locationValue);
 			if(grivenceTrustList!=null){
 				for (Object[] objects : grivenceTrustList) {
 					if(objects[2].toString().trim().equalsIgnoreCase("Govt") || objects[2].toString().trim().equalsIgnoreCase("Party")  || 
 							objects[2].toString().trim().equalsIgnoreCase("Welfare")){
 						if(objects[1].toString().trim().equalsIgnoreCase("Not Verified") ){
 							gnotVerified = gnotVerified+(Long)objects[3];
 						}else if(objects[1].toString().trim().equalsIgnoreCase("in progress")){
 							ginProgress = ginProgress+(Long)objects[3];
 						}else if(objects[1].toString().trim().equalsIgnoreCase("Not Eligible")){
 							gnotEligible = gnotEligible+(Long)objects[3];
 						}else if(objects[1].toString().trim().equalsIgnoreCase("not possible")){
 							gnotPossible = gnotPossible+(Long)objects[3];
 						}else if(objects[1].toString().trim().equalsIgnoreCase("approved")){
 							gapproves = gapproves+(Long)objects[3];
 						}else if(objects[1].toString().trim().equalsIgnoreCase("completed")){
 							gcompleted = gcompleted+(Long)objects[3];
 						}
 					}else if(objects[2].toString().trim().equalsIgnoreCase("Trust Education Support")){
 						if(objects[1].toString().trim().equalsIgnoreCase("Not Verified") ){
 							tnotVerified = tnotVerified+(Long)objects[3];
 						}else if(objects[1].toString().trim().equalsIgnoreCase("in progress")){
 							tinProgress = tinProgress+(Long)objects[3];
 						}else if(objects[1].toString().trim().equalsIgnoreCase("Not Eligible")){
 							tnotEligible = tnotEligible+(Long)objects[3];
 						}else if(objects[1].toString().trim().equalsIgnoreCase("not possible")){
 							tnotPossible = tnotPossible+(Long)objects[3];
 						}else if(objects[1].toString().trim().equalsIgnoreCase("approved")){
 							tapproves = tapproves+(Long)objects[3];
 						}else if(objects[1].toString().trim().equalsIgnoreCase("completed")){
 							tcompleted = tcompleted+(Long)objects[3];
 						}
 					}
				}
 				grivenceStatusCount.setNotVerified(gnotVerified);
 				grivenceStatusCount.setInProgress(ginProgress);
 				grivenceStatusCount.setNotEligible(gnotEligible);
 				grivenceStatusCount.setNotPossible(gnotPossible);
 				grivenceStatusCount.setApproves(gapproves);
 				grivenceStatusCount.setCompleted(gcompleted);
 				
 				trustStatusCount.setNotVerified(tnotVerified);
 				trustStatusCount.setInProgress(tinProgress);
 				trustStatusCount.setNotEligible(tnotEligible);
 				trustStatusCount.setNotPossible(tnotPossible);
 				trustStatusCount.setApproves(tapproves);
 				trustStatusCount.setCompleted(tcompleted);
 			}
 			grivenceList.add(grivenceStatusCount);
 			trustList.add(trustStatusCount);
 			finalList.add(grivenceList);
 			finalList.add(trustList);
		}catch(Exception e){
			Log.error("Exception raised at grivence and trust counts service"+e);
		}
		return finalList;
	}
}