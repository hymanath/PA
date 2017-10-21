package com.itgrids.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "habitation")
public class Habitation {
	private Long habitationId;
	private String districtName;
	private String mandalName;
	private String panchayatName;
	private String villageName;
	private String panchName;
	private String panchCode;
	private Long censusYear;
	private Long censusPlainPopulation;
	private Long censusScPopulation;
	private Long censusStPopulation;
	private Long totalPopulation;
	private Long plainPopulationCovered;
	private Date statusDate;
	private Double unSafeLpcd;
	private Double safeLpcd;
	private String coverageStatus;
	private String previousYearStatus;
	private String latitude;
	private String longtitude;
	private Long scPopulationCovered;
	private Long districtCode;
	private Long mandalCode;
	private Long panchayatCode;
	private Long villageCode;
	private String isDeleted;
	@Id
	@Column(name="habitation_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getHabitationId() {
		return habitationId;
	}
	public void setHabitationId(Long habitationId) {
		this.habitationId = habitationId;
	}
	@Column(name="district_name")
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	@Column(name="mandal_name")
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	@Column(name="panchayat_name")
	public String getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	@Column(name="village_name")
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	@Column(name="panch_name")
	public String getPanchName() {
		return panchName;
	}
	public void setPanchName(String panchName) {
		this.panchName = panchName;
	}
	@Column(name="panch_code")
	public String getPanchCode() {
		return panchCode;
	}
	public void setPanchCode(String panchCode) {
		this.panchCode = panchCode;
	}
	@Column(name="census_year")
	public Long getCensusYear() {
		return censusYear;
	}
	public void setCensusYear(Long censusYear) {
		this.censusYear = censusYear;
	}
	@Column(name="census_plain_population")
	public Long getCensusPlainPopulation() {
		return censusPlainPopulation;
	}
	public void setCensusPlainPopulation(Long censusPlainPopulation) {
		this.censusPlainPopulation = censusPlainPopulation;
	}
	@Column(name="census_sc_population")
	public Long getCensusScPopulation() {
		return censusScPopulation;
	}
	public void setCensusScPopulation(Long censusScPopulation) {
		this.censusScPopulation = censusScPopulation;
	}
	@Column(name="census_st_population")
	public Long getCensusStPopulation() {
		return censusStPopulation;
	}
	public void setCensusStPopulation(Long censusStPopulation) {
		this.censusStPopulation = censusStPopulation;
	}
	@Column(name="total_population")
	public Long getTotalPopulation() {
		return totalPopulation;
	}
	public void setTotalPopulation(Long totalPopulation) {
		this.totalPopulation = totalPopulation;
	}
	@Column(name="plain_population_covered")
	public Long getPlainPopulationCovered() {
		return plainPopulationCovered;
	}
	public void setPlainPopulationCovered(Long plainPopulationCovered) {
		this.plainPopulationCovered = plainPopulationCovered;
	}
	@Column(name="status_date")
	public Date getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}
	@Column(name="unSafe_lpcd")
	public Double getUnSafeLpcd() {
		return unSafeLpcd;
	}
	public void setUnSafeLpcd(Double unSafeLpcd) {
		this.unSafeLpcd = unSafeLpcd;
	}
	@Column(name="safe_lpcd")
	public Double getSafeLpcd() {
		return safeLpcd;
	}
	public void setSafeLpcd(Double safeLpcd) {
		this.safeLpcd = safeLpcd;
	}
	@Column(name="coverage_status")
	public String getCoverageStatus() {
		return coverageStatus;
	}
	public void setCoverageStatus(String coverageStatus) {
		this.coverageStatus = coverageStatus;
	}
	@Column(name="previous_year_status")
	public String getPreviousYearStatus() {
		return previousYearStatus;
	}
	public void setPreviousYearStatus(String previousYearStatus) {
		this.previousYearStatus = previousYearStatus;
	}
	@Column(name="latitude")
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	@Column(name="longtitude")
	public String getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}
	@Column(name="sc_population_covered")
	public Long getScPopulationCovered() {
		return scPopulationCovered;
	}
	public void setScPopulationCovered(Long scPopulationCovered) {
		this.scPopulationCovered = scPopulationCovered;
	}
	@Column(name="district_code")
	public Long getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(Long districtCode) {
		this.districtCode = districtCode;
	}
	@Column(name="mandal_code")
	public Long getMandalCode() {
		return mandalCode;
	}
	public void setMandalCode(Long mandalCode) {
		this.mandalCode = mandalCode;
	}
	@Column(name="panchayat_code")
	public Long getPanchayatCode() {
		return panchayatCode;
	}
	public void setPanchayatCode(Long panchayatCode) {
		this.panchayatCode = panchayatCode;
	}
	@Column(name="village_code")
	public Long getVillageCode() {
		return villageCode;
	}
	public void setVillageCode(Long villageCode) {
		this.villageCode = villageCode;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((censusPlainPopulation == null) ? 0 : censusPlainPopulation.hashCode());
		result = prime * result + ((censusScPopulation == null) ? 0 : censusScPopulation.hashCode());
		result = prime * result + ((censusStPopulation == null) ? 0 : censusStPopulation.hashCode());
		result = prime * result + ((censusYear == null) ? 0 : censusYear.hashCode());
		result = prime * result + ((coverageStatus == null) ? 0 : coverageStatus.hashCode());
		result = prime * result + ((districtCode == null) ? 0 : districtCode.hashCode());
		result = prime * result + ((mandalCode == null) ? 0 : mandalCode.hashCode());
		result = prime * result + ((panchCode == null) ? 0 : panchCode.hashCode());
		result = prime * result + ((panchayatCode == null) ? 0 : panchayatCode.hashCode());
		result = prime * result + ((plainPopulationCovered == null) ? 0 : plainPopulationCovered.hashCode());
		result = prime * result + ((previousYearStatus == null) ? 0 : previousYearStatus.hashCode());
		result = prime * result + ((safeLpcd == null) ? 0 : safeLpcd.hashCode());
		result = prime * result + ((scPopulationCovered == null) ? 0 : scPopulationCovered.hashCode());
		result = prime * result + ((statusDate == null) ? 0 : statusDate.hashCode());
		result = prime * result + ((unSafeLpcd == null) ? 0 : unSafeLpcd.hashCode());
		result = prime * result + ((villageCode == null) ? 0 : villageCode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Habitation other = (Habitation) obj;
		if (censusPlainPopulation == null) {
			if (other.censusPlainPopulation != null)
				return false;
		} else if (!censusPlainPopulation.equals(other.censusPlainPopulation))
			return false;
		if (censusScPopulation == null) {
			if (other.censusScPopulation != null)
				return false;
		} else if (!censusScPopulation.equals(other.censusScPopulation))
			return false;
		if (censusStPopulation == null) {
			if (other.censusStPopulation != null)
				return false;
		} else if (!censusStPopulation.equals(other.censusStPopulation))
			return false;
		if (censusYear == null) {
			if (other.censusYear != null)
				return false;
		} else if (!censusYear.equals(other.censusYear))
			return false;
		if (coverageStatus == null) {
			if (other.coverageStatus != null)
				return false;
		} else if (!coverageStatus.equals(other.coverageStatus))
			return false;
		if (districtCode == null) {
			if (other.districtCode != null)
				return false;
		} else if (!districtCode.equals(other.districtCode))
			return false;
		if (mandalCode == null) {
			if (other.mandalCode != null)
				return false;
		} else if (!mandalCode.equals(other.mandalCode))
			return false;
		if (panchCode == null) {
			if (other.panchCode != null)
				return false;
		} else if (!panchCode.equals(other.panchCode))
			return false;
		if (panchayatCode == null) {
			if (other.panchayatCode != null)
				return false;
		} else if (!panchayatCode.equals(other.panchayatCode))
			return false;
		if (plainPopulationCovered == null) {
			if (other.plainPopulationCovered != null)
				return false;
		} else if (!plainPopulationCovered.equals(other.plainPopulationCovered))
			return false;
		if (previousYearStatus == null) {
			if (other.previousYearStatus != null)
				return false;
		} else if (!previousYearStatus.equals(other.previousYearStatus))
			return false;
		if (safeLpcd == null) {
			if (other.safeLpcd != null)
				return false;
		} else if (!safeLpcd.equals(other.safeLpcd))
			return false;
		if (scPopulationCovered == null) {
			if (other.scPopulationCovered != null)
				return false;
		} else if (!scPopulationCovered.equals(other.scPopulationCovered))
			return false;
		
		if (statusDate == null) {
			if (other.statusDate != null)
				return false;
		} else if (!statusDate.equals(other.statusDate))
			return false;
		
		if (unSafeLpcd == null) {
			if (other.unSafeLpcd != null)
				return false;
		} else if (!unSafeLpcd.equals(other.unSafeLpcd))
			return false;
		if (villageCode == null) {
			if (other.villageCode != null)
				return false;
		} else if (!villageCode.equals(other.villageCode))
			return false;
		return true;
	}
	
}
