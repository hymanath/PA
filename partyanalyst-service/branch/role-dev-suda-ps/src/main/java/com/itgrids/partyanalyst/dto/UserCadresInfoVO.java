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
	private Map<Long, String> userAccessMandals = null;
	private Map<Long, String> userAccessVillages = null;
	
	private Map<Long, String> zeroCadreStates = null;
	private Map<Long, String> zeroCadreDistricts = null;
	private Map<Long, String> zeroCadreMandals = null;
	private Map<Long, String> zeroCadreVillages = null;
	
	private ResultStatus resultStatus = null;
	
	

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
}
