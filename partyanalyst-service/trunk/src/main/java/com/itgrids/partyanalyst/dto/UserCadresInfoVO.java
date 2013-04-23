package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Narender Akula
 *
 */
@SuppressWarnings("serial")
public class UserCadresInfoVO implements Serializable{

	private Long userID = null;
	private String userAccessType = null;
	private String userAccessValue = null;
	private String userAccesDisplayValue = null;
	private Long totalCadres = null;
	private Long partyID = null;
	private Long totalNormalCadres = null;
	private Long totalNonAssignedToBoothActiveCadres = null;
	private Long totalNonAssignedToBoothNormalCadres = null;
	private Boolean isParent; 
	/**
	 *  regionLevel No Cadres contains like following
	 * Zero Cadres in 5 Districts
	 * Zero Cadres in 7 Mandal 
	 * Zero Cadres in  9 Village
	 */
	//private Map<String,Long> regionLevelZeroCadres = new LinkedHashMap<String, Long>(); 
	List<SelectOptionVO> regionLevelZeroCadres = new ArrayList<SelectOptionVO>();
	
	/**
	 *  regionLevelCadres contains info like following
	 *  Districts Level -  5 cadres
	 *  Mandal Level    -  7 cadres
	 *  Village Level   - 13 cadres
	 */ 
	private Map<String,Long> regionLevelCadres = new LinkedHashMap<String, Long>();
	 
	private Map<Long, String> userAccessStates = null;
	private Map<Long, String> userAccessDistricts = null;
	private Map<Long, String> userAccessConstituencies = null;
	private Map<Long, String> userAccessParlConstituencies = null;
	private Map<Long, String> userAccessMandals = null;
	private Map<Long, String> userAccessVillages = null;
	private Map<Long, String> userAccessHamlets = null;
	private Map<Long, String> userAccessLocalElectionBodies = null;
	private Map<Long, String> userAccessWards = null;
	private Map<Long, String> userAccessBooths = null;
	private Map<Long, String> userAccessBoothsInMandal = null;
	
	
	private Map<Long, String> zeroCadreStates = null;
	private Map<Long, String> zeroCadreDistricts = null;
	private Map<Long, String> zeroCadreConstituencies = null;
	private Map<Long, String> zeroCadreParlConstituencies = null;
	private Map<Long, String> zeroCadreMandals = null;
	private Map<Long, String> zeroCadreVillages = null;
	private Map<Long, String> zeroCadreHamlets = null;
	private Map<Long, String> zeroCadreLocalElectionBodies = null;
	private Map<Long, String> zeroCadreWards = null;
	private ResultStatus resultStatus = null;
	private Map<Long, String> zeroCadreBooths = null;
	private Map<Long, String> zeroCadreBoothsInMandal = null;
	
	private Boolean cadreView = true;
	private Boolean cadreCreate = true;
	private Boolean cadreUpdate = true;
	private Boolean cadreDelete = true;

	
	public Boolean getCadreView() {
		return cadreView;
	}
	public void setCadreView(Boolean cadreView) {
		this.cadreView = cadreView;
	}
	public Boolean getCadreCreate() {
		return cadreCreate;
	}
	public void setCadreCreate(Boolean cadreCreate) {
		this.cadreCreate = cadreCreate;
	}
	public Boolean getCadreUpdate() {
		return cadreUpdate;
	}
	public void setCadreUpdate(Boolean cadreUpdate) {
		this.cadreUpdate = cadreUpdate;
	}
	public Boolean getCadreDelete() {
		return cadreDelete;
	}
	public void setCadreDelete(Boolean cadreDelete) {
		this.cadreDelete = cadreDelete;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public String getUserAccessType() {
		return userAccessType;
	}
	public void setUserAccessType(String userAccessType) {
		this.userAccessType = userAccessType;
	}
	public String getUserAccessValue() {
		return userAccessValue;
	}
	public void setUserAccessValue(String userAccessValue) {
		this.userAccessValue = userAccessValue;
	}
	public String getUserAccessDisplayValue() {
		return userAccesDisplayValue;
	}
	public void setUserAccessDisplayValue(String userAccesDisplayValue) {
		this.userAccesDisplayValue = userAccesDisplayValue;
	}
	public Long getTotalCadres() {
		return totalCadres;
	}
	public void setTotalCadres(Long totalCadres) {
		this.totalCadres = totalCadres;
	}
	
	
	public List<SelectOptionVO> getRegionLevelZeroCadres() {
		return regionLevelZeroCadres;
	}
	public void setRegionLevelZeroCadres(List<SelectOptionVO> regionLevelZeroCadres) {
		this.regionLevelZeroCadres = regionLevelZeroCadres;
	}
	public Map<String, Long> getRegionLevelCadres() {
		return regionLevelCadres;
	}
	public void setRegionLevelCadres(Map<String, Long> regionLevelCadres) {
		this.regionLevelCadres = regionLevelCadres;
	}
	public Map<Long, String> getUserAccessStates() {
		return userAccessStates;
	}
	public void setUserAccessStates(Map<Long, String> userAccessStates) {
		this.userAccessStates = userAccessStates;
	}
	public Map<Long, String> getUserAccessDistricts() {
		return userAccessDistricts;
	}
	public void setUserAccessDistricts(Map<Long, String> userAccessDistricts) {
		this.userAccessDistricts = userAccessDistricts;
	}
	
	public Map<Long, String> getUserAccessMandals() {
		return userAccessMandals;
	}
	public void setUserAccessMandals(Map<Long, String> userAccessMandals) {
		this.userAccessMandals = userAccessMandals;
	}
	public Map<Long, String> getUserAccessVillages() {
		return userAccessVillages;
	}
	public void setUserAccessVillages(Map<Long, String> userAccessVillages) {
		this.userAccessVillages = userAccessVillages;
	}
	public String getUserAccesDisplayValue() {
		return userAccesDisplayValue;
	}
	public void setUserAccesDisplayValue(String userAccesDisplayValue) {
		this.userAccesDisplayValue = userAccesDisplayValue;
	}
	public Map<Long, String> getZeroCadreStates() {
		return zeroCadreStates;
	}
	public void setZeroCadreStates(Map<Long, String> zeroCadreStates) {
		this.zeroCadreStates = zeroCadreStates;
	}
	public Map<Long, String> getZeroCadreDistricts() {
		return zeroCadreDistricts;
	}
	public void setZeroCadreDistricts(Map<Long, String> zeroCadreDistricts) {
		this.zeroCadreDistricts = zeroCadreDistricts;
	}
	public Map<Long, String> getZeroCadreMandals() {
		return zeroCadreMandals;
	}
	public void setZeroCadreMandals(Map<Long, String> zeroCadreMandals) {
		this.zeroCadreMandals = zeroCadreMandals;
	}
	public Map<Long, String> getZeroCadreVillages() {
		return zeroCadreVillages;
	}
	public void setZeroCadreVillages(Map<Long, String> zeroCadreVillages) {
		this.zeroCadreVillages = zeroCadreVillages;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public Long getPartyID() {
		return partyID;
	}
	public void setPartyID(Long partyID) {
		this.partyID = partyID;
	}
	public Map<Long, String> getUserAccessHamlets() {
		return userAccessHamlets;
	}
	public void setUserAccessHamlets(Map<Long, String> userAccessHamlets) {
		this.userAccessHamlets = userAccessHamlets;
	}
	public Map<Long, String> getZeroCadreHamlets() {
		return zeroCadreHamlets;
	}
	public void setZeroCadreHamlets(Map<Long, String> zeroCadreHamlets) {
		this.zeroCadreHamlets = zeroCadreHamlets;
	}
	public Map<Long, String> getUserAccessLocalElectionBodies() {
		return userAccessLocalElectionBodies;
	}
	public void setUserAccessLocalElectionBodies(
			Map<Long, String> userAccessLocalElectionBodies) {
		this.userAccessLocalElectionBodies = userAccessLocalElectionBodies;
	}
	public Map<Long, String> getZeroCadreLocalElectionBodies() {
		return zeroCadreLocalElectionBodies;
	}
	public void setZeroCadreLocalElectionBodies(
			Map<Long, String> zeroCadreLocalElectionBodies) {
		this.zeroCadreLocalElectionBodies = zeroCadreLocalElectionBodies;
	}
	public Map<Long, String> getUserAccessWards() {
		return userAccessWards;
	}
	public void setUserAccessWards(Map<Long, String> userAccessWards) {
		this.userAccessWards = userAccessWards;
	}
	public Map<Long, String> getZeroCadreWards() {
		return zeroCadreWards;
	}
	public void setZeroCadreWards(Map<Long, String> zeroCadreWards) {
		this.zeroCadreWards = zeroCadreWards;
	}
	public Map<Long, String> getUserAccessConstituencies() {
		return userAccessConstituencies;
	}
	public void setUserAccessConstituencies(
			Map<Long, String> userAccessConstituencies) {
		this.userAccessConstituencies = userAccessConstituencies;
	}
	public Map<Long, String> getZeroCadreConstituencies() {
		return zeroCadreConstituencies;
	}
	public void setZeroCadreConstituencies(Map<Long, String> zeroCadreConstituencies) {
		this.zeroCadreConstituencies = zeroCadreConstituencies;
	}
	public Map<Long, String> getUserAccessBooths() {
		return userAccessBooths;
	}
	public void setUserAccessBooths(Map<Long, String> userAccessBooths) {
		this.userAccessBooths = userAccessBooths;
	}
	public Map<Long, String> getZeroCadreBooths() {
		return zeroCadreBooths;
	}
	public void setZeroCadreBooths(Map<Long, String> zeroCadreBooths) {
		this.zeroCadreBooths = zeroCadreBooths;
	}
	public Map<Long, String> getUserAccessBoothsInMandal() {
		return userAccessBoothsInMandal;
	}
	public void setUserAccessBoothsInMandal(
			Map<Long, String> userAccessBoothsInMandal) {
		this.userAccessBoothsInMandal = userAccessBoothsInMandal;
	}
	public Map<Long, String> getZeroCadreBoothsInMandal() {
		return zeroCadreBoothsInMandal;
	}
	public void setZeroCadreBoothsInMandal(Map<Long, String> zeroCadreBoothsInMandal) {
		this.zeroCadreBoothsInMandal = zeroCadreBoothsInMandal;
	}
	public Long getTotalNormalCadres() {
		return totalNormalCadres;
	}
	public void setTotalNormalCadres(Long totalNormalCadres) {
		this.totalNormalCadres = totalNormalCadres;
	}
	public Long getTotalNonAssignedToBoothActiveCadres() {
		return totalNonAssignedToBoothActiveCadres;
	}
	public void setTotalNonAssignedToBoothActiveCadres(
			Long totalNonAssignedToBoothActiveCadres) {
		this.totalNonAssignedToBoothActiveCadres = totalNonAssignedToBoothActiveCadres;
	}
	public Long getTotalNonAssignedToBoothNormalCadres() {
		return totalNonAssignedToBoothNormalCadres;
	}
	public void setTotalNonAssignedToBoothNormalCadres(
			Long totalNonAssignedToBoothNormalCadres) {
		this.totalNonAssignedToBoothNormalCadres = totalNonAssignedToBoothNormalCadres;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	public Map<Long, String> getUserAccessParlConstituencies() {
		return userAccessParlConstituencies;
	}
	public void setUserAccessParlConstituencies(
			Map<Long, String> userAccessParlConstituencies) {
		this.userAccessParlConstituencies = userAccessParlConstituencies;
	}
	public Map<Long, String> getZeroCadreParlConstituencies() {
		return zeroCadreParlConstituencies;
	}
	public void setZeroCadreParlConstituencies(
			Map<Long, String> zeroCadreParlConstituencies) {
		this.zeroCadreParlConstituencies = zeroCadreParlConstituencies;
	}
	
	
}
